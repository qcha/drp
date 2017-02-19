package com.github.aarexer.qcha.drp.deserializers;


import com.github.aarexer.qcha.drp.model.DsvFileResource;
import lombok.NonNull;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class DsvFileIterator implements Iterator<List<String>> {
    private final ICsvListReader reader;
    private List<String> current;

    public DsvFileIterator(@NonNull final DsvFileResource resource) throws IOException {
        final CsvPreference csvPreference = new CsvPreference.Builder(
                getQuotes(resource.getQuotes()),
                getDelimiter(resource.getDelimiter()),
                getLineSeparator(resource.getLineSeparator())
        ).build();
        reader = new CsvListReader(Files.newBufferedReader(Paths.get(resource.getUri())), csvPreference);
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
            return null;
        }
    }

    private char getQuotes(final String propertyQuotes) {
        return Objects.nonNull(propertyQuotes) && propertyQuotes.length() > 0 ? propertyQuotes.charAt(0) : '"';
    }

    private char getDelimiter(final String propertyDelimiter) {
        return Objects.nonNull(propertyDelimiter) && propertyDelimiter.length() > 0 ? propertyDelimiter.charAt(0) : ';';
    }

    private String getLineSeparator(final String propertyLineSeparator) {
        return Objects.nonNull(propertyLineSeparator) && propertyLineSeparator.length() > 0 ? propertyLineSeparator : System.getProperty("line.separator");
    }
}
