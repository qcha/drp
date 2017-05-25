package com.github.qcha.drp.model;

import com.google.common.base.VerifyException;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;

import static com.github.qcha.drp.model.DsvDefaultConstants.*;


public class DsvFileResourceTest {
    private static final String TEST_URI = "file:///path/to/file.txt";

    @Test
    public void createResourceWithDefaultPreferences() throws Exception {
        DsvFileResource resource = new DsvFileResource(URI.create(TEST_URI));

        Assert.assertEquals(TEST_URI, resource.getUri().toString());
        Assert.assertEquals(DEFAULT_DELIMITER, resource.getPreference().getDelimiter());
        Assert.assertEquals(DEFAULT_QUOTES, resource.getPreference().getQuotes());
        Assert.assertEquals(DEFAULT_CHARSET, resource.getPreference().getEncoding());
        Assert.assertEquals(DEFAULT_LINE_SEPARATOR, resource.getPreference().getLineSeparator());
        Assert.assertEquals(DEFAULT_ARCHIVE_TYPE, resource.getPreference().getArchiveType());
        Assert.assertEquals(DEFAULT_COMPRESS_TYPE, resource.getPreference().getCompressType());
    }

    @Test
    public void createResourceWithArchiveAndCompressPreferences() throws Exception {
        DsvFileResource resource = new DsvFileResource(
                URI.create(TEST_URI),
                ArchiveType.AR,
                CompressType.BZIP2
        );

        Assert.assertEquals(TEST_URI, resource.getUri().toString());
        Assert.assertEquals(DEFAULT_DELIMITER, resource.getPreference().getDelimiter());
        Assert.assertEquals(DEFAULT_QUOTES, resource.getPreference().getQuotes());
        Assert.assertEquals(DEFAULT_CHARSET, resource.getPreference().getEncoding());
        Assert.assertEquals(DEFAULT_LINE_SEPARATOR, resource.getPreference().getLineSeparator());
        Assert.assertEquals(ArchiveType.AR, resource.getPreference().getArchiveType());
        Assert.assertEquals(CompressType.BZIP2, resource.getPreference().getCompressType());
    }

    @Test
    public void createResourceWithFullPreferences() throws Exception {
        DsvFileResource resource = new DsvFileResource(
                URI.create(TEST_URI),
                ',',
                '\'',
                "UTF-8",
                System.lineSeparator(),
                ArchiveType.AR,
                CompressType.BZIP2
        );

        Assert.assertEquals(TEST_URI, resource.getUri().toString());
        Assert.assertEquals(',', resource.getPreference().getDelimiter());
        Assert.assertEquals('\'', resource.getPreference().getQuotes());
        Assert.assertEquals("UTF-8", resource.getPreference().getEncoding());
        Assert.assertEquals(System.lineSeparator(), resource.getPreference().getLineSeparator());
        Assert.assertEquals(ArchiveType.AR, resource.getPreference().getArchiveType());
        Assert.assertEquals(CompressType.BZIP2, resource.getPreference().getCompressType());
    }

    @Test(expected = VerifyException.class)
    public void createResourceWithNullUri() throws Exception {
        new DsvFileResource(null);
    }

    @Test(expected = VerifyException.class)
    public void createResourceWithNullUriNullArchiveAndCompress() throws Exception {
        new DsvFileResource(null, null, null);
    }

    @Test(expected = VerifyException.class)
    public void createResourceWithNullUriAndNullPrefernce() throws Exception {
        new DsvFileResource(null, ' ', ' ', null, null, null, null);
    }
}
