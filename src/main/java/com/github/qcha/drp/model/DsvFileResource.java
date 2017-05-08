package com.github.qcha.drp.model;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.net.URI;

@Getter
public class DsvFileResource {
    private static final Logger logger = LogManager.getLogger();

    @NotNull
    private final URI uri;
    @NotNull
    private final DsvPreference preference;

    private DsvFileResource(@NotNull URI uri, @NotNull DsvPreference preference) {
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