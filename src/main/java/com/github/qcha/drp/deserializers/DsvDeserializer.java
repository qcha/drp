package com.github.qcha.drp.deserializers;

import java.util.List;

public interface DsvDeserializer {
    boolean hasNext();

    List<String> next();
}
