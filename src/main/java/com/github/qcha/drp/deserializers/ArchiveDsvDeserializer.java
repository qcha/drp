package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.DsvPreference;
import com.sun.istack.internal.NotNull;
import lombok.NonNull;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class ArchiveDsvDeserializer implements DsvDeserializer {
    private static final Logger logger = LogManager.getLogger();

    private DsvIterator iterator;
    private ArchiveInputStream ais;

    ArchiveDsvDeserializer(@NotNull final ArchiveInputStream ais, @NonNull final DsvPreference preference) {
        this.ais = ais;
        //get first entry in archive
        try {
            ais.getNextEntry();
        } catch (IOException e) {
            logger.error("Error while reading: {}", e);
            throw new DsvDeserializerException("Error while reading", e);
        }
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
            logger.error("Error while reading: {}", e);
            throw new DsvDeserializerException("Can't do next.", e);
        }
    }
}
