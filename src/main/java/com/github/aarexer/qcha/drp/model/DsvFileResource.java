package com.github.aarexer.qcha.drp.model;

import lombok.Getter;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;

@Getter
public class DsvFileResource {
    private static final Logger logger = LogManager.getLogger();
    private static final char DEFAULT_QUOTES = '"';
    private static final char DEFAULT_DELIMITER = ';';
    private static final String DEFAULT_LINE_SEPARATOR = System.lineSeparator();
    private static final String DEFAULT_CHARSET = "UTF-8";

    @NonNull
    private final URI uri;
    @NonNull
    private final char delimiter;
    private final char quotes;
    private final String encoding;
    private final String lineSeparator;
    private final ArchiveType archiveType;
    private final CompressType compressType;

    public DsvFileResource(URI uri, char delimiter, String encoding, String lineSeparator, char quotes, ArchiveType archiveType, CompressType compressType) {
        this.uri = uri;
        this.delimiter = delimiter;
        this.encoding = encoding;
        this.lineSeparator = lineSeparator;
        this.quotes = quotes;
        this.archiveType = archiveType;
        this.compressType = compressType;
    }

    public DsvFileResource(URI uri) {
        this(uri, DEFAULT_DELIMITER, DEFAULT_CHARSET, DEFAULT_LINE_SEPARATOR, DEFAULT_QUOTES, null, null);
    }

    public DsvFileResource(URI uri, char delimiter, ArchiveType archiveType, CompressType compressType) {
        this(uri, delimiter, DEFAULT_CHARSET, System.lineSeparator(), DEFAULT_QUOTES, archiveType, compressType);
    }
}