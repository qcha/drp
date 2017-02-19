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
    private CompressorInputStream cis;
    private ArchiveInputStream ais;

    private final Map<String, String> ASSOCIATED_TYPES = ImmutableMap.of(
            "zip", ArchiveStreamFactory.ZIP,
            "tar", ArchiveStreamFactory.TAR,
            "gz", CompressorStreamFactory.GZIP,
            "bz2", CompressorStreamFactory.BZIP2
    );

    public FileResourceHandler(DsvFileResource resource) {
    }

//    private void resolveType() {
//        new CompressorStreamFactory().
//    }
}
