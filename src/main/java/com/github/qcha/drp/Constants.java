package com.github.qcha.drp;

import com.github.qcha.drp.model.ArchiveType;
import com.github.qcha.drp.model.CompressType;

final public class Constants {

    public final static class DsvPreference{
        public static final char DEFAULT_DELIMITER = ';';
        public static final char DEFAULT_QUOTES = '"';
        public static final String DEFAULT_CHARSET = "UTF-8";
        public static final String DEFAULT_LINE_SEPARATOR = System.lineSeparator();
        public static final ArchiveType DEFAULT_ARCHIVE_TYPE = null;
        public static final CompressType DEFAULT_COMPRESS_TYPE = null;
    }

    public final static class DsvFileResourceTest{
        public static final String DEFAULT_URI = "file:///path/to/file.txt";
    }
}
