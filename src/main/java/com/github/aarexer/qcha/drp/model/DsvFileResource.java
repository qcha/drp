package com.github.aarexer.qcha.drp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.net.URI;

@Getter
@Builder
public class DsvFileResource {
    @NonNull
    private final URI uri;
    private final String archiveType;
    private final String compressType;
    private final String delimiter;
    private final String encoding;
    private final String quotes;
    private final String lineSeparator;
}