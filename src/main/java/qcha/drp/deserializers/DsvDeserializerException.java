package qcha.drp.deserializers;

/**
 * Basic Exception for Deserializer problems.
 */
public class DsvDeserializerException extends RuntimeException {
    DsvDeserializerException(String message) {
        super(message);
    }

    DsvDeserializerException(String message, Throwable cause) {
        super(message, cause);
    }
}
