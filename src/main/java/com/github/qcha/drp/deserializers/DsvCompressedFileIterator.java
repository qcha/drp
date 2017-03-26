package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.DsvPreference;
import lombok.NonNull;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class DsvCompressedFileIterator extends DsvIterator {
    public DsvCompressedFileIterator(@NonNull final InputStream is, @NonNull final DsvPreference preference) throws IOException, CompressorException {
        super(new CompressorStreamFactory().createCompressorInputStream(preference.getCompressType().name(), is), preference);

        if (Objects.isNull(preference.getCompressType())) {
            throw new IllegalArgumentException("CompressType can't be null.");
        }
    }
}
