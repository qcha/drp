package com.github.qcha.drp.deserializers;

import java.util.List;

public interface DsvDeserializer extends AutoCloseable, Iterable<List<String>> {
    boolean hasNext();

    List<String> next();
}
