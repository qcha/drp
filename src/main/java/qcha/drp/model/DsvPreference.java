package qcha.drp.model;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import qcha.drp.ftp.FtpConnectionType;

import static qcha.drp.model.DsvDefaultConstants.*;

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
    @Nullable
    private final FtpConnectionType connectionType;

    public DsvPreference(char delimiter,
                         char quotes,
                         String encoding,
                         String lineSeparator,
                         @Nullable ArchiveType archiveType,
                         @Nullable CompressType compressType,
                         @Nullable FtpConnectionType connectionType
    ) {
        this.delimiter = delimiter;
        this.quotes = quotes;
        this.encoding = encoding;
        this.lineSeparator = lineSeparator;
        this.archiveType = archiveType;
        this.compressType = compressType;
        this.connectionType = connectionType;
    }

    public DsvPreference() {
        this(
                DEFAULT_DELIMITER,
                DEFAULT_QUOTES,
                DEFAULT_CHARSET,
                DEFAULT_LINE_SEPARATOR,
                DEFAULT_ARCHIVE_TYPE,
                DEFAULT_COMPRESS_TYPE,
                DEFAULT_CONNECTION_TYPE
        );
    }

    public DsvPreference(ArchiveType archiveType, CompressType compressType) {
        this(
                DEFAULT_DELIMITER,
                DEFAULT_QUOTES,
                DEFAULT_CHARSET,
                DEFAULT_LINE_SEPARATOR,
                archiveType,
                compressType,
                DEFAULT_CONNECTION_TYPE
        );
    }

    public DsvPreference(ArchiveType archiveType, CompressType compressType, FtpConnectionType connectionType) {
        this(
                DEFAULT_DELIMITER,
                DEFAULT_QUOTES,
                DEFAULT_CHARSET,
                DEFAULT_LINE_SEPARATOR,
                archiveType,
                compressType,
                connectionType
        );
    }
}
