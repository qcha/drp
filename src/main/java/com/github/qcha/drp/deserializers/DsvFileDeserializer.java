package com.github.qcha.drp.deserializers;


import com.github.qcha.drp.model.DsvPreference;
import lombok.NonNull;

import java.io.InputStream;
import java.util.List;

/**
 * Iterator for plain dsv file.
 */
public class DsvFileDeserializer implements DsvDeserializer {
    private final DsvIterator iterator;

    public DsvFileDeserializer(@NonNull final InputStream is, @NonNull final DsvPreference preference) {
        this.iterator = new DsvIterator(is, preference);
    }

    public DsvFileDeserializer(@NonNull final InputStream is, @NonNull final DsvPreference preference, final int bufferSize) {
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
}
