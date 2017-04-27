package com.github.qcha.drp.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.net.URI;

public class DsvFileResourceTest {
    private static final String TEST_URI = "file:///path/to/file.txt";
    private static final char TEST_DELIMITER = ',';
    private static final char TEST_QUOTES = '\'';
    private static final String TEST_CHARSET = "UTF-16";
    private static final String TEST_LINE_SEPARATOR = System.lineSeparator();
    private static final ArchiveType TEST_ARCHIVE_TYPE = ArchiveType.AR;
    private static final CompressType TEST_COMPRESS_TYPE = CompressType.BZIP2;

    private char defaultDelimiter;
    private char defaultQuotes;
    private String defaultCharset;
    private String defaultLineSeparator;
    private ArchiveType defaultArchiveType;
    private CompressType defaultCompressType;

    @Before
    public void setUp() throws Exception {
        DsvPreference preference = new DsvPreference();
        Class c = preference.getClass();

        Field defDelField = c.getDeclaredField("DEFAULT_DELIMITER");
        Field defQuoField = c.getDeclaredField("DEFAULT_QUOTES");
        Field defChaField = c.getDeclaredField("DEFAULT_CHARSET");
        Field defLinField = c.getDeclaredField("DEFAULT_LINE_SEPARATOR");
        Field defArcField = c.getDeclaredField("DEFAULT_ARCHIVE_TYPE");
        Field defComField = c.getDeclaredField("DEFAULT_COMPRESS_TYPE");

        defDelField.setAccessible(true);
        defQuoField.setAccessible(true);
        defChaField.setAccessible(true);
        defLinField.setAccessible(true);
        defArcField.setAccessible(true);
        defComField.setAccessible(true);

        defaultDelimiter = (char) defDelField.get(preference);
        defaultQuotes = (char) defQuoField.get(preference);
        defaultCharset = (String) defChaField.get(preference);
        defaultLineSeparator = (String) defLinField.get(preference);
        defaultArchiveType = (ArchiveType) defArcField.get(preference);
        defaultCompressType = (CompressType) defComField.get(preference);
    }

    @Test
    public void createResourceWithDefaultPreferences() throws Exception {
        final DsvFileResource resource = new DsvFileResource(URI.create(TEST_URI));

        Assert.assertEquals(TEST_URI, resource.getUri().toString());
        Assert.assertEquals(defaultDelimiter, resource.getPreference().getDelimiter());
        Assert.assertEquals(defaultQuotes, resource.getPreference().getQuotes());
        Assert.assertEquals(defaultCharset, resource.getPreference().getEncoding());
        Assert.assertEquals(defaultLineSeparator, resource.getPreference().getLineSeparator());
        Assert.assertEquals(defaultArchiveType, resource.getPreference().getArchiveType());
        Assert.assertEquals(defaultCompressType, resource.getPreference().getCompressType());
    }

    @Test
    public void createResourceWithArchiveAndCompress() throws Exception {
        final DsvFileResource resource = new DsvFileResource(
                URI.create(TEST_URI),
                TEST_ARCHIVE_TYPE,
                TEST_COMPRESS_TYPE
        );
        Assert.assertEquals(TEST_URI, resource.getUri().toString());
        Assert.assertEquals(TEST_ARCHIVE_TYPE, resource.getPreference().getArchiveType());
        Assert.assertEquals(defaultDelimiter, resource.getPreference().getDelimiter());
        Assert.assertEquals(defaultQuotes, resource.getPreference().getQuotes());
        Assert.assertEquals(defaultCharset, resource.getPreference().getEncoding());
        Assert.assertEquals(defaultLineSeparator, resource.getPreference().getLineSeparator());
        Assert.assertEquals(TEST_COMPRESS_TYPE, resource.getPreference().getCompressType());
    }

    @Test
    public void createResourceWithPreferences() throws Exception {
        final DsvFileResource resource = new DsvFileResource(
                URI.create(TEST_URI),
                TEST_DELIMITER,
                TEST_QUOTES,
                TEST_CHARSET,
                TEST_LINE_SEPARATOR,
                TEST_ARCHIVE_TYPE,
                TEST_COMPRESS_TYPE
        );
        Assert.assertEquals(TEST_URI, resource.getUri().toString());
        Assert.assertEquals(TEST_DELIMITER, resource.getPreference().getDelimiter());
        Assert.assertEquals(TEST_QUOTES, resource.getPreference().getQuotes());
        Assert.assertEquals(TEST_CHARSET, resource.getPreference().getEncoding());
        Assert.assertEquals(TEST_LINE_SEPARATOR, resource.getPreference().getLineSeparator());
        Assert.assertEquals(TEST_ARCHIVE_TYPE, resource.getPreference().getArchiveType());
        Assert.assertEquals(TEST_COMPRESS_TYPE, resource.getPreference().getCompressType());
    }
}
