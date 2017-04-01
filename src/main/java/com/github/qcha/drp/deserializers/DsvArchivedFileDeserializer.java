package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.DsvPreference;
import lombok.NonNull;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * Iterator for archived files, but without compression.
 */
public class DsvArchivedFileDeserializer implements DsvDeserializer {
    private DsvIterator iterator;
    private ArchiveInputStream ais;

    public DsvArchivedFileDeserializer(@NonNull final InputStream is, @NonNull final DsvPreference preference) throws IOException, ArchiveException {
        if (Objects.isNull(preference.getArchiveType())) {
            throw new IllegalArgumentException("ArchiveType can't be null.");
        }

        this.ais = new ArchiveStreamFactory().createArchiveInputStream(preference.getArchiveType().name(), is);
        ais.getNextEntry();
        this.iterator = new DsvIterator(ais, preference);
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public List<String> next() {
        try {
            List<String> res = iterator.next();
            if (Objects.isNull(res) && Objects.isNull(ais.getNextEntry())) {
                return null;
            } else {
                return iterator.next();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Can't do next.", e);
        }
    }
}
