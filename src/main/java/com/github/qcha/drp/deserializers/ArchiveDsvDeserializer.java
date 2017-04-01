package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.DsvPreference;
import com.sun.istack.internal.NotNull;
import lombok.NonNull;
import org.apache.commons.compress.archivers.ArchiveInputStream;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class ArchiveDsvDeserializer implements DsvDeserializer {
    private DsvIterator iterator;
    private ArchiveInputStream ais;

    public ArchiveDsvDeserializer(@NotNull final ArchiveInputStream ais, @NonNull final DsvPreference preference) throws IOException {
        this.ais = ais;
        //get first entry in archive
        ais.getNextEntry();
        this.iterator = new DsvIterator(ais, preference);
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public List<String> next() {
        try {
            List<String> res = iterator.next();
            if (Objects.nonNull(res)) {
                return res;
            } else {
                //move entry
                if (Objects.nonNull(ais.getNextEntry())) {
                    //skip current null last entry
                    iterator.next();
                    return iterator.next();
                } else {
                    return null;
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Can't do next.", e);
        }
    }
}
