package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.DsvPreference;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Deserializer for compressed file.
 * example.csv.bz2 - for example.
 */
public class DsvCompressedFileDeserializer implements DsvDeserializer {
    private static final Logger logger = LogManager.getLogger();

    private final DsvIterator iterator;

    public DsvCompressedFileDeserializer(final InputStream is, final DsvPreference preference) {
        //fixme repeatable bunch of code
        if (Objects.isNull(preference)) {
            throw new IllegalArgumentException("Preferences can't be null");
        }

        if (Objects.isNull(preference.getCompressType())) {
            throw new IllegalArgumentException("CompressType can't be null.");
        }

        try {
            this.iterator = new DsvIterator(new CompressorStreamFactory().createCompressorInputStream(
                    preference.getCompressType().getAbbreviation(), is), preference);
        } catch (CompressorException e) {
            throw new RuntimeException("Can't create CompressorInputStream.", e);
        }
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
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

    @NotNull
    @Override
    public Iterator<List<String>> iterator() {
        return iterator;
    }
}
