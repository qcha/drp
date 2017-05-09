package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.DsvPreference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class DsvIterator implements Iterator<List<String>>, AutoCloseable {
    private static final Logger logger = LogManager.getLogger();

    @NotNull
    private final ICsvListReader reader;
    @Nullable
    private List<String> current;

    DsvIterator(@NotNull final InputStream is, @NotNull final DsvPreference preference) {
        this(is, preference, 8192);
    }

    DsvIterator(@NotNull final InputStream is, @NotNull final DsvPreference preference, final int bufferSize) {
        final CsvPreference csvPreference = new CsvPreference.Builder(
                preference.getQuotes(),
                preference.getDelimiter(),
                preference.getLineSeparator()
        ).build();

        try {
            reader = new CsvListReader(new BufferedReader(new InputStreamReader(is, preference.getEncoding()), bufferSize), csvPreference);
            current = reader.read();
        } catch (IOException e) {
            logger.error("Error while reading: {}", e);
            throw new DsvDeserializerException("Error while reading.", e);
        }
    }

    @Override
    public boolean hasNext() {
        return Objects.nonNull(current);
    }

    @Override
    public List<String> next() {
        try {
            List<String> before = current;
            current = reader.read();

            return before;

        } catch (IOException e) {
            logger.error("Error while reading: {}", e);
            throw new DsvDeserializerException("Errors while reading.", e);
        }
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
