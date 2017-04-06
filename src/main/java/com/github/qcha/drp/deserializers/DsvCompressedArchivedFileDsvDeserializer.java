package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.DsvPreference;
import lombok.NonNull;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import java.io.InputStream;
import java.util.Objects;

public class DsvCompressedArchivedFileDsvDeserializer extends ArchiveDsvDeserializer {

    public DsvCompressedArchivedFileDsvDeserializer(@NonNull final InputStream is, @NonNull final DsvPreference preference) throws ArchiveException, CompressorException {
        super(new ArchiveStreamFactory().createArchiveInputStream(
                preference.getArchiveType().name(),
                new CompressorStreamFactory().createCompressorInputStream(
                        preference.getCompressType().name(),
                        is)), preference);

        if (Objects.isNull(preference.getArchiveType())) {
            throw new IllegalArgumentException("ArchiveType can't be null.");
        }

        if (Objects.isNull(preference.getCompressType())) {
            throw new IllegalArgumentException("CompressType can't be null.");
        }
    }
}
