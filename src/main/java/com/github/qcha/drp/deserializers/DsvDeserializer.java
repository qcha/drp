package com.github.qcha.drp.deserializers;

import java.util.List;

public interface DsvDeserializer extends AutoCloseable{
    boolean hasNext();

    List<String> next();


}
