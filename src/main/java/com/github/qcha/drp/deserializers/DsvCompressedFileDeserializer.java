package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.DsvPreference;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class DsvCompressedFileDeserializer implements DsvDeserializer {
    private final DsvIterator iterator;

    public DsvCompressedFileDeserializer(@NotNull final InputStream is, @NotNull final DsvPreference preference) {
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
}
