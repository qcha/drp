package com.github.qcha.drp.model;

import lombok.Getter;

@Getter
public enum CompressType {

    /**
     * Constant used to identify the BZIP2 compression algorithm.
     */
    BZIP2("bzip2"),

    /**
     * Constant used to identify the GZIP compression algorithm.
     */
    GZIP("gz"),
    /**
     * Constant used to identify the PACK200 compression algorithm.
     */
    PACK200("pack200"),

    /**
     * Constant used to identify the XZ compression method.
     */
    XZ("xz"),

    /**
     * Constant used to identify the LZMA compression method.
     */
    LZMA("lzma"),

    /**
     * Constant used to identify the "framed" Snappy compression method.
     */
    SNAPPY_FRAMED("snappy-framed"),

    /**
     * Constant used to identify the "raw" Snappy compression method.
     */
    SNAPPY_RAW("snappy-raw"),

    /**
     * Constant used to identify the traditional Unix compress method.
     */
    Z("z");

    CompressType(String name) {
        this.name = name;
    }

    private String name;
}
