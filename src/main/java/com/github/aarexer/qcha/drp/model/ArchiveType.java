package com.github.aarexer.qcha.drp.model;

import lombok.Getter;

@Getter
public enum ArchiveType {

    /**
     * Constant used to identify the AR archive format.
     */
    AR("ar"),
    /**
     * Constant used to identify the ARJ archive format.
     */
    ARJ("arj"),
    /**
     * Constant used to identify the CPIO archive format.
     */
    CPIO("cpio"),
    /**
     * Constant used to identify the Unix DUMP archive format.
     */
    DUMP("dump"),
    /**
     * Constant used to identify the JAR archive format.
     */
    JAR("jar"),
    /**
     * Constant used to identify the TAR archive format.
     */
    TAR("tar"),
    /**
     * Constant used to identify the ZIP archive format.
     */
    ZIP("zip"),
    /**
     * Constant used to identify the 7z archive format.
     */
    SEVEN_Z("7z");


    private String name;

    ArchiveType(String name) {
        this.name = name;
    }
}
