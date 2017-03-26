package com.github.qcha.drp.deserializers;


import com.github.qcha.drp.model.DsvPreference;
import lombok.NonNull;

import java.io.IOException;
import java.io.InputStream;

/**
 * Iterator for plain dsv file.
 */
public class DsvFileIterator extends DsvIterator {
    public DsvFileIterator(@NonNull final InputStream is, @NonNull final DsvPreference preference) throws IOException {
        super(is, preference);
    }

    public DsvFileIterator(@NonNull final InputStream is, final int bufferSize, @NonNull final DsvPreference preference) throws IOException {
        super(is, bufferSize, preference);
    }
}
