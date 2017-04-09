package com.github.qcha.drp.deserializers;

import com.sun.istack.internal.NotNull;

public class DsvDeserializingException extends RuntimeException {
    DsvDeserializingException(@NotNull String message) {
        super(message);
    }

    DsvDeserializingException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }
}
