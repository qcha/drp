package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.deserializers.DsvDeserializer;
import com.github.qcha.drp.deserializers.DsvIterator;
import com.github.qcha.drp.model.DsvPreference;
import lombok.NonNull;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class DsvCompressedFileDeserializer implements DsvDeserializer {
    private final DsvIterator iterator;

    public DsvCompressedFileDeserializer(@NonNull final InputStream is, @NonNull final DsvPreference preference) throws CompressorException {
        if (Objects.isNull(preference.getCompressType())) {
            throw new IllegalArgumentException("CompressType can't be null.");
        }

        this.iterator = new DsvIterator(new CompressorStreamFactory().createCompressorInputStream(preference.getCompressType().name(), is), preference);
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
