package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.DsvPreference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Iterator for archived files, but without compression.
 */
public class DsvArchiveDeserializer implements DsvDeserializer {
    private static final Logger logger = LogManager.getLogger();

    private final DsvArchiveIterator iterator;

    public DsvArchiveDeserializer(final InputStream is, final DsvPreference preference) {
        //fixme repeatable bunch of code
        if (Objects.isNull(preference)) {
            throw new IllegalArgumentException("Preferences can't be null");
        }

        this.iterator = new DsvArchiveIterator(is, preference);
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
