package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.DsvPreference;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * Iterator for archived files, but without compression.
 */
public class DsvArchiveDeserializer implements DsvDeserializer {
    private static final Logger logger = LogManager.getLogger();

    @NotNull
    private final DsvIterator iterator;
    @NotNull
    private final ArchiveInputStream ais;

    public DsvArchiveDeserializer(@NotNull final InputStream is, @NotNull final DsvPreference preference) {
        if (Objects.isNull(preference.getArchiveType())) {
            throw new IllegalArgumentException("Archive type is null in archive deserializer!");
        }

        //get first entry in archive
        try {
            ais = Objects.isNull(preference.getCompressType()) ? new ArchiveStreamFactory().createArchiveInputStream(preference.getArchiveType().name(), is) : new ArchiveStreamFactory().createArchiveInputStream(
                    preference.getArchiveType().name(),
                    new CompressorStreamFactory().createCompressorInputStream(
                            preference.getCompressType().getAbbreviation(),
                            is));

            ais.getNextEntry();
        } catch (IOException e) {
            logger.error("Error while reading: {}", e);
            throw new DsvDeserializerException("Error while reading", e);
        } catch (ArchiveException | CompressorException e) {
            logger.error("Can't create ArchiveInputStream, cause: {}", e);
            throw new IllegalArgumentException("Can't create ArchiveInputStream.", e);
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
        iterator.close();
    }
}
