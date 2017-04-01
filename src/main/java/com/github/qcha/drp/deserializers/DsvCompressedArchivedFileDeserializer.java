package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.DsvPreference;
import lombok.NonNull;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class DsvCompressedArchivedFileDeserializer implements DsvDeserializer {
    private DsvIterator iterator;
    private ArchiveInputStream ais;

    public DsvCompressedArchivedFileDeserializer(@NonNull final InputStream is, @NonNull final DsvPreference preference) throws IOException, ArchiveException, CompressorException {
        if (Objects.isNull(preference.getArchiveType())) {
            throw new IllegalArgumentException("ArchiveType can't be null.");
        }

        if (Objects.isNull(preference.getCompressType())) {
            throw new IllegalArgumentException("CompressType can't be null.");
        }

        ais = new ArchiveStreamFactory().createArchiveInputStream(
                preference.getArchiveType().name(),
                new CompressorStreamFactory().createCompressorInputStream(
                        preference.getCompressType().name(),
                        is));

        ais.getNextEntry();

        this.iterator = new DsvIterator(ais, preference);
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    //fixme
    @Override
    public List<String> next() {
        return iterator.next();
    }
}
