package com.github.aarexer.qcha.drp;

import com.github.aarexer.qcha.drp.model.DsvFileResource;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import java.io.InputStream;
import java.util.Map;

public final class FileResourceHandler {
    private InputStream is;

    public FileResourceHandler(DsvFileResource resource) {
    }
}
