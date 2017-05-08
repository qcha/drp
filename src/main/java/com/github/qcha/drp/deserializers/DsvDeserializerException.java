package com.github.qcha.drp.deserializers;


import org.jetbrains.annotations.NotNull;

public class DsvDeserializerException extends RuntimeException {
    DsvDeserializerException(@NotNull String message) {
        super(message);
    }

    DsvDeserializerException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }
}
