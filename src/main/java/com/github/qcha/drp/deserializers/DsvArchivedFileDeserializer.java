package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.DsvPreference;
import lombok.NonNull;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Iterator for archived files, but without compression.
 */
public class DsvArchivedFileDeserializer extends ArchiveDsvDeserializer {

    public DsvArchivedFileDeserializer(@NonNull final InputStream is, @NonNull final DsvPreference preference) throws IOException, ArchiveException {
        super(new ArchiveStreamFactory().createArchiveInputStream(preference.getArchiveType().name(), is), preference);

        if (Objects.isNull(preference.getArchiveType())) {
            throw new IllegalArgumentException("ArchiveType can't be null.");
        }
    }
}
