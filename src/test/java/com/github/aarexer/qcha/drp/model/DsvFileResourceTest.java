package com.github.aarexer.qcha.drp.model;

import org.junit.Assert;
import org.junit.Test;

import java.net.URI;

public class DsvFileResourceTest {
    @Test
    public void createResourceAndFillOnlyUri() throws Exception {
        final DsvFileResource resource = DsvFileResource.builder().uri(URI.create("file:///c:/path/to/file.txt")).build();
        Assert.assertEquals("file:///c:/path/to/file.txt", resource.getUri().toString());
    }

    @Test(expected=IllegalArgumentException.class)
    public void createResourceWithoutFillingFinalField() throws Exception {
        final DsvFileResource resource = DsvFileResource.builder().build();
    }
}
