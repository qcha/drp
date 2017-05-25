package com.github.qcha.drp.model;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import static com.github.qcha.drp.model.DsvDefaultConstants.*;

@Getter
public class DsvPreference {
    private final char delimiter;
    private final char quotes;
    private final String encoding;
    private final String lineSeparator;
    @Nullable
    private final ArchiveType archiveType;
    @Nullable
    private final CompressType compressType;

    public DsvPreference(char delimiter, char quotes, String encoding, String lineSeparator, @Nullable ArchiveType archiveType, @Nullable CompressType compressType) {
        this.delimiter = delimiter;
        this.quotes = quotes;
        this.encoding = encoding;
        this.lineSeparator = lineSeparator;
        this.archiveType = archiveType;
        this.compressType = compressType;
    }

    public DsvPreference() {
        this(
                DEFAULT_DELIMITER,
                DEFAULT_QUOTES,
                DEFAULT_CHARSET,
                DEFAULT_LINE_SEPARATOR,
                DEFAULT_ARCHIVE_TYPE,
                DEFAULT_COMPRESS_TYPE
        );
    }

    public DsvPreference(ArchiveType archiveType, CompressType compressType) {
        this(
                DEFAULT_DELIMITER,
                DEFAULT_QUOTES,
                DEFAULT_CHARSET,
                DEFAULT_LINE_SEPARATOR,
                archiveType,
                compressType
        );
    }
}
