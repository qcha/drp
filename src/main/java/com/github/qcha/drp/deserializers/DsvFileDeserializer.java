package com.github.qcha.drp.deserializers;


import com.github.qcha.drp.model.DsvPreference;
import lombok.NonNull;

import java.io.InputStream;
import java.util.List;

/**
 * Iterator for plain dsv file.
 */
public class DsvFileDeserializer implements DsvDeserializer {
    private DsvIterator iterator;

    public DsvFileDeserializer(@NonNull final InputStream is, @NonNull final DsvPreference preference) {
        this.iterator = new DsvIterator(is, preference);
    }

    public DsvFileDeserializer(@NonNull final InputStream is, final int bufferSize, @NonNull final DsvPreference preference) {
        this.iterator = new DsvIterator(is, bufferSize, preference);
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
