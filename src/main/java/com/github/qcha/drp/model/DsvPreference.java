package com.github.qcha.drp.model;


import lombok.Getter;

@Getter
public class DsvPreference {
    private static final char DEFAULT_DELIMITER = ';';
    private static final char DEFAULT_QUOTES = '"';
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_LINE_SEPARATOR = System.lineSeparator();
    private static final ArchiveType DEFAULT_ARCHIVE_TYPE = null;
    private static final CompressType DEFAULT_COMPRESS_TYPE = null;

    private final char delimiter;
    private final char quotes;
    private final String encoding;
    private final String lineSeparator;
    private final ArchiveType archiveType;
    private final CompressType compressType;

    public DsvPreference(char delimiter, char quotes, String encoding, String lineSeparator, ArchiveType archiveType, CompressType compressType) {
        this.delimiter = delimiter;
        this.quotes = quotes;
        this.encoding = encoding;
        this.lineSeparator = lineSeparator;
        this.archiveType = archiveType;
        this.compressType = compressType;
    }

    public DsvPreference() {
        delimiter = DEFAULT_DELIMITER;
        quotes = DEFAULT_QUOTES;
        encoding = DEFAULT_CHARSET;
        lineSeparator = DEFAULT_LINE_SEPARATOR;
        archiveType = DEFAULT_ARCHIVE_TYPE;
        compressType = DEFAULT_COMPRESS_TYPE;
    }

    public DsvPreference(ArchiveType archiveType, CompressType compressType) {
        delimiter = DEFAULT_DELIMITER;
        quotes = DEFAULT_QUOTES;
        encoding = DEFAULT_CHARSET;
        lineSeparator = DEFAULT_LINE_SEPARATOR;
        this.archiveType = archiveType;
        this.compressType = compressType;
    }
}
