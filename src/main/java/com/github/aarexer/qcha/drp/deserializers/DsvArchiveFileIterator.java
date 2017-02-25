package com.github.aarexer.qcha.drp.deserializers;

import com.github.aarexer.qcha.drp.model.DsvPreference;
import lombok.NonNull;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Iterator for archived files, but without compression.
 */
public class DsvArchiveFileIterator extends DsvIterator {

    public DsvArchiveFileIterator(@NonNull final InputStream is, @NonNull final DsvPreference preference) throws IOException, ArchiveException {
        super(new ArchiveStreamFactory().createArchiveInputStream(preference.getArchiveType().name(), is), preference);
    }
}
