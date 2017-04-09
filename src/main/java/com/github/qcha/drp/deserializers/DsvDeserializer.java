package com.github.qcha.drp.deserializers;

import java.util.List;

interface DsvDeserializer {
    boolean hasNext();

    List<String> next();
}
