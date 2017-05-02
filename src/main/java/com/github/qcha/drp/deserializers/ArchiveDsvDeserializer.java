package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.DsvPreference;
import lombok.NonNull;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class ArchiveDsvDeserializer implements DsvDeserializer {
    private static final Logger logger = LogManager.getLogger();

    private final DsvIterator iterator;
    private final ArchiveInputStream ais;

    ArchiveDsvDeserializer(@NonNull final ArchiveInputStream ais, @NonNull final DsvPreference preference) {
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
        try {
            if (iterator.hasNext()) {
                return true;
            } else {
                //try to move entry in archive
                if (Objects.nonNull(ais.getNextEntry())) {
                    //skip current null in last entry
                    iterator.next();
                    return iterator.hasNext();
                }

                return false;
            }
        } catch (IOException e) {
            logger.error("Error while reading: {}", e);
            throw new DsvDeserializerException("Can't do next.", e);
        }
    }

    @Override
    public List<String> next() {
        return iterator.next();
    }

    @Override
    public void close() throws IOException {
        if (Objects.nonNull(iterator)) {
            iterator.close();
        }
    }
}
