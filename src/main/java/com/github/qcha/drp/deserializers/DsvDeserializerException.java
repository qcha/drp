package com.github.qcha.drp.deserializers;

import com.sun.istack.internal.NotNull;

public class DsvDeserializerException extends RuntimeException {
    DsvDeserializerException(@NotNull String message) {
        super(message);
    }

    DsvDeserializerException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }
}
