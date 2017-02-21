package com.github.aarexer.qcha.drp.model;

import lombok.Getter;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Getter
public class DsvFileResource {
    private static final Logger logger = LogManager.getLogger();
    private static final char DEFAULT_QUOTES = '"';
    private static final char DEFAULT_DELIMITER = ';';
    private static final String DEFAULT_LINE_SEPARATOR = System.lineSeparator();
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    @NonNull
    private final URI uri;
    @NonNull
    private final char delimiter;
    private final char quotes;
    private final Charset encoding;
    private final String lineSeparator;
    //todo it should not be strings
    private final String archiveType;
    private final String compressType;

    public DsvFileResource(URI uri, char delimiter, Charset encoding, String lineSeparator, char quotes, String archiveType, String compressType) {
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

    public DsvFileResource(URI uri, char delimiter, String archiveType, String compressType) {
        this(uri, delimiter, StandardCharsets.UTF_8, System.lineSeparator(), DEFAULT_QUOTES, archiveType, compressType);
    }
}