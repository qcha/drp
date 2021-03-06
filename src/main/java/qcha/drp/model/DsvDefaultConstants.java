package qcha.drp.model;

import qcha.drp.ftp.FtpConnectionType;

final class DsvDefaultConstants {
    private DsvDefaultConstants() {
        throw new IllegalAccessError("Class for constants!");
    }

    static final char DEFAULT_DELIMITER = ';';
    static final char DEFAULT_QUOTES = '"';
    static final String DEFAULT_CHARSET = "UTF-8";
    static final String DEFAULT_LINE_SEPARATOR = System.lineSeparator();
    static final ArchiveType DEFAULT_ARCHIVE_TYPE = ArchiveType.NONE;
    static final CompressType DEFAULT_COMPRESS_TYPE = CompressType.NONE;
    static final FtpConnectionType DEFAULT_CONNECTION_TYPE = FtpConnectionType.NONE;
}
