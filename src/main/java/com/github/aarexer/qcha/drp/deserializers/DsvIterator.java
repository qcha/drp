package com.github.aarexer.qcha.drp.deserializers;

import com.github.aarexer.qcha.drp.model.DsvPreference;
import lombok.NonNull;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public abstract class DsvIterator implements Iterator<List<String>> {
    private static final Logger logger = LogManager.getLogger();

    private final ICsvListReader reader;
    private List<String> current;

    public DsvIterator(@NonNull final InputStream is, @NonNull final DsvPreference preference) throws IOException {
        final CsvPreference csvPreference = new CsvPreference.Builder(
                preference.getQuotes(),
                preference.getDelimiter(),
                preference.getLineSeparator()
        ).build();

        //todo test it and rewrite
        if (is instanceof ArchiveInputStream) {
            ((ArchiveInputStream) is).getNextEntry();
        }

        reader = new CsvListReader(new BufferedReader(new InputStreamReader(is, preference.getEncoding())), csvPreference);
        current = reader.read();
    }

    public DsvIterator(@NonNull final InputStream is, final int bufferSize, @NonNull final DsvPreference preference) throws IOException {
        final CsvPreference csvPreference = new CsvPreference.Builder(
                preference.getQuotes(),
                preference.getDelimiter(),
                preference.getLineSeparator()
        ).build();
        reader = new CsvListReader(new BufferedReader(new InputStreamReader(is, preference.getEncoding()), bufferSize), csvPreference);
        current = reader.read();
    }

    @Override
    public boolean hasNext() {
        return Objects.nonNull(current);
    }

    @Override
    public List<String> next() {
        try {
            final List<String> before = current;
            current = reader.read();
            return before;
        } catch (IOException e) {
            logger.error("Error while reading!");
            return null;
        }
    }
}
