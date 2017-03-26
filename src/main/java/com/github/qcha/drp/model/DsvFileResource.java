package com.github.qcha.drp.model;

import lombok.Getter;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;

@Getter
public class DsvFileResource {
    private static final Logger logger = LogManager.getLogger();

    @NonNull
    private final URI uri;
    @NonNull
    private final DsvPreference preference;

    private DsvFileResource(URI uri, DsvPreference preference) {
        this.uri = uri;
        this.preference = preference;
    }

    public DsvFileResource(URI uri, char delimiter, String encoding, String lineSeparator, char quotes, ArchiveType archiveType, CompressType compressType) {
        this(uri, new DsvPreference(delimiter, quotes, encoding, lineSeparator, archiveType, compressType));
    }

    public DsvFileResource(URI uri) {
        this(uri, new DsvPreference());
    }

    public DsvFileResource(URI uri, char delimiter, ArchiveType archiveType, CompressType compressType) {
        this(uri, new DsvPreference(archiveType, compressType));
    }
}