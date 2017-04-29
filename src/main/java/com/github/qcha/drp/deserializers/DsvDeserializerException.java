package com.github.qcha.drp.deserializers;


import lombok.NonNull;

public class DsvDeserializerException extends RuntimeException {
    DsvDeserializerException(@NonNull String message) {
        super(message);
    }

    DsvDeserializerException(@NonNull String message, @NonNull Throwable cause) {
        super(message, cause);
    }
}
