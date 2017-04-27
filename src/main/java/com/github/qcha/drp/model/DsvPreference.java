package com.github.qcha.drp.model;

import static com.github.qcha.drp.Constants.DsvPreference.*;
import lombok.Getter;

@Getter
public class DsvPreference {

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
