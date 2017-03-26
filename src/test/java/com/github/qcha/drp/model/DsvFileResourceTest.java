package com.github.qcha.drp.model;

import org.junit.Assert;
import org.junit.Test;

import java.net.URI;

//todo tests
public class DsvFileResourceTest {
    @Test
    public void createResourceAndFillOnlyUri() throws Exception {
        final DsvFileResource resource = new DsvFileResource(URI.create("file:///path/to/file.txt"));
        Assert.assertEquals("file:///path/to/file.txt", resource.getUri().toString());
    }
}
