package com.github.qcha.drp.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

public class DsvFileResourceTest {
    private static final char DEFAULT_QUOTES = '"';
    private static final char DEFAULT_DELIMITER = ';';
    private static final String DEFAULT_LINE_SEPARATOR = System.lineSeparator();
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final ArchiveType DEFAULT_ARCHIVE_TYPE = null;
    private static final CompressType DEFAULT_COMPRESS_TYPE = null;
    private static final String TEST_URI = "file:///path/to/file.txt";

    @Before
    public void setUp() throws Exception {
        //todo get default values from DsvPreference by Reflection
    }

    @Test
    public void createResourceWithDefaultPreferences() throws Exception {
        final DsvFileResource resource = new DsvFileResource(URI.create(TEST_URI));

        Assert.assertEquals(TEST_URI, resource.getUri().toString());
        Assert.assertEquals(DEFAULT_DELIMITER, resource.getPreference().getDelimiter());
        Assert.assertEquals(DEFAULT_QUOTES, resource.getPreference().getQuotes());
        Assert.assertEquals(DEFAULT_CHARSET, resource.getPreference().getEncoding());
        Assert.assertEquals(DEFAULT_LINE_SEPARATOR, resource.getPreference().getLineSeparator());
        Assert.assertEquals(DEFAULT_ARCHIVE_TYPE, resource.getPreference().getArchiveType());
        Assert.assertEquals(DEFAULT_COMPRESS_TYPE, resource.getPreference().getCompressType());
    }

    //todo rewrite
    @Test
    public void createResourceWithArchiveAndCompress() throws Exception {
        final DsvFileResource resource = new DsvFileResource(
                URI.create(TEST_URI),
                DEFAULT_ARCHIVE_TYPE,
                DEFAULT_COMPRESS_TYPE
        );
        Assert.assertEquals(TEST_URI, resource.getUri().toString());
        Assert.assertEquals(DEFAULT_ARCHIVE_TYPE, resource.getPreference().getArchiveType());
        Assert.assertEquals(';', resource.getPreference().getDelimiter());
        Assert.assertEquals('"', resource.getPreference().getQuotes());
        Assert.assertEquals("UTF-8", resource.getPreference().getEncoding());
        Assert.assertEquals(System.lineSeparator(), resource.getPreference().getLineSeparator());
        Assert.assertEquals(DEFAULT_COMPRESS_TYPE, resource.getPreference().getCompressType());
    }

    //todo rewrite
    @Test
    public void createResourceWithPreferences() throws Exception {
        final DsvFileResource resource = new DsvFileResource(
                URI.create(TEST_URI),
                DEFAULT_DELIMITER,
                DEFAULT_QUOTES,
                DEFAULT_CHARSET,
                DEFAULT_LINE_SEPARATOR,
                DEFAULT_ARCHIVE_TYPE,
                DEFAULT_COMPRESS_TYPE
        );
        Assert.assertEquals(TEST_URI, resource.getUri().toString());
        Assert.assertEquals(DEFAULT_DELIMITER, resource.getPreference().getDelimiter());
        Assert.assertEquals(DEFAULT_QUOTES, resource.getPreference().getQuotes());
        Assert.assertEquals(DEFAULT_CHARSET, resource.getPreference().getEncoding());
        Assert.assertEquals(DEFAULT_LINE_SEPARATOR, resource.getPreference().getLineSeparator());
        Assert.assertEquals(DEFAULT_ARCHIVE_TYPE, resource.getPreference().getArchiveType());
        Assert.assertEquals(DEFAULT_COMPRESS_TYPE, resource.getPreference().getCompressType());
    }
}
