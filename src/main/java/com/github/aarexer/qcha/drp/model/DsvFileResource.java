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
    private static final Character DEFAULT_QUOTES = '"';
    private static final Character DEFAULT_DELIMITER = ';';
    private static final String DEFAULT_LINE_SEPARATOR = System.lineSeparator();
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    @NonNull
    private final URI uri;
    @NonNull
    private final Character delimiter;
    private final Charset encoding;
    private final String lineSeparator;
    private final Character quotes;
    //todo it should not be strings
    private final String archiveType;
    private final String compressType;

    public DsvFileResource(URI uri, Character delimiter, Charset encoding, String lineSeparator, Character quotes, String archiveType, String compressType) {
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

    public DsvFileResource(URI uri, Character delimiter, String archiveType, String compressType) {
        this(uri, delimiter, StandardCharsets.UTF_8, System.lineSeparator(), DEFAULT_QUOTES, archiveType, compressType);
    }
}