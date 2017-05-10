package com.github.qcha.drp.deserializers;


import com.github.qcha.drp.model.DsvPreference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Iterator for plain dsv file.
 */
public class DsvFileDeserializer implements DsvDeserializer {
    private static final Logger logger = LogManager.getLogger();

    private final DsvIterator iterator;

    public DsvFileDeserializer(final InputStream is, @NotNull final DsvPreference preference) {
        this.iterator = new DsvIterator(is, preference);
    }

    public DsvFileDeserializer(final InputStream is, final DsvPreference preference, final int bufferSize) {
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

    @NotNull
    @Override
    public Iterator<List<String>> iterator() {
        return iterator;
    }
}
