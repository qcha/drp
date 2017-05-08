package com.github.qcha.drp.deserializers;


import com.github.qcha.drp.model.DsvPreference;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Iterator for plain dsv file.
 */
public class DsvFileDeserializer implements DsvDeserializer {
    private final DsvIterator iterator;

    public DsvFileDeserializer(@NotNull final InputStream is, @NotNull final DsvPreference preference) {
        this.iterator = new DsvIterator(is, preference);
    }

    public DsvFileDeserializer(@NotNull final InputStream is, @NotNull final DsvPreference preference, final int bufferSize) {
        this.iterator = new DsvIterator(is, preference, bufferSize);
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
        iterator.close();
    }
}
