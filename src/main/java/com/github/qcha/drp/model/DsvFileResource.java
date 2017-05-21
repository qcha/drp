package com.github.qcha.drp.model;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.util.Objects;

@Getter
public class DsvFileResource {
    private static final Logger logger = LogManager.getLogger();

    private final URI uri;
    private final DsvPreference preference;

    private DsvFileResource(URI uri, DsvPreference preference) {
        //fixme repeatable bunch of code
        if (Objects.isNull(preference) || Objects.isNull(uri)) {
            throw new IllegalArgumentException("Preferences or URI can't be null");
        }

        this.uri = uri;
        this.preference = preference;
    }

    public DsvFileResource(URI uri, char delimiter, char quotes, String encoding, String lineSeparator, ArchiveType archiveType, CompressType compressType) {
        this(uri, new DsvPreference(delimiter, quotes, encoding, lineSeparator, archiveType, compressType));
    }

    public DsvFileResource(URI uri) {
        this(uri, new DsvPreference());
    }

    public DsvFileResource(URI uri, ArchiveType archiveType, CompressType compressType) {
        this(uri, new DsvPreference(archiveType, compressType));
    }
}