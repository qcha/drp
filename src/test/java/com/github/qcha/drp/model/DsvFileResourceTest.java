package com.github.qcha.drp.model;

import org.junit.Assert;
import org.junit.Test;

import java.net.URI;

public class DsvFileResourceTest {

    private static final char DEFAULT_QUOTES = '\'';
    private static final char DEFAULT_DELIMITER = ',';
    private static final String DEFAULT_LINE_SEPARATOR = System.lineSeparator();
    private static final String DEFAULT_CHARSET = "UTF-16";
    private static final ArchiveType DEFAULT_ARCHIVE_TYPE = ArchiveType.AR;
    private static final CompressType DEFAULT_COMPRESS_TYPE = CompressType.BZIP2;
    private static final String DEFAULT_PATH = "file:///path/to/file.txt";

    @Test
    public void createResourceWithDefaultPreferences() throws Exception {
        final DsvFileResource resource = new DsvFileResource(URI.create(DEFAULT_PATH));
        Assert.assertEquals(DEFAULT_PATH, resource.getUri().toString());
        Assert.assertEquals(';', resource.getPreference().getDelimiter());
        Assert.assertEquals('"', resource.getPreference().getQuotes());
        Assert.assertEquals("UTF-8", resource.getPreference().getEncoding());
        Assert.assertEquals(System.lineSeparator(), resource.getPreference().getLineSeparator());
        Assert.assertEquals(null, resource.getPreference().getArchiveType());
        Assert.assertEquals(null, resource.getPreference().getCompressType());
    }

    @Test
    public void createResourceWithArchiveAndCompress() throws Exception{
        final DsvFileResource resource = new DsvFileResource(
                URI.create(DEFAULT_PATH),
                DEFAULT_ARCHIVE_TYPE,
                DEFAULT_COMPRESS_TYPE
        );
        Assert.assertEquals(DEFAULT_PATH, resource.getUri().toString());
        Assert.assertEquals(DEFAULT_ARCHIVE_TYPE, resource.getPreference().getArchiveType());
        Assert.assertEquals(';', resource.getPreference().getDelimiter());
        Assert.assertEquals('"', resource.getPreference().getQuotes());
        Assert.assertEquals("UTF-8", resource.getPreference().getEncoding());
        Assert.assertEquals(System.lineSeparator(), resource.getPreference().getLineSeparator());
        Assert.assertEquals(DEFAULT_COMPRESS_TYPE, resource.getPreference().getCompressType());
    }

    @Test
    public void createResourceWithPreferences() throws Exception{
        final DsvFileResource resource = new DsvFileResource(
                URI.create(DEFAULT_PATH),
                DEFAULT_DELIMITER,
                DEFAULT_QUOTES,
                DEFAULT_CHARSET,
                DEFAULT_LINE_SEPARATOR,
                DEFAULT_ARCHIVE_TYPE,
                DEFAULT_COMPRESS_TYPE
        );
        Assert.assertEquals(DEFAULT_PATH, resource.getUri().toString());
        Assert.assertEquals(DEFAULT_DELIMITER, resource.getPreference().getDelimiter());
        Assert.assertEquals(DEFAULT_QUOTES, resource.getPreference().getQuotes());
        Assert.assertEquals(DEFAULT_CHARSET, resource.getPreference().getEncoding());
        Assert.assertEquals(DEFAULT_LINE_SEPARATOR, resource.getPreference().getLineSeparator());
        Assert.assertEquals(DEFAULT_ARCHIVE_TYPE, resource.getPreference().getArchiveType());
        Assert.assertEquals(DEFAULT_COMPRESS_TYPE, resource.getPreference().getCompressType());
    }
}
