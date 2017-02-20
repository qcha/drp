package com.github.aarexer.qcha.drp.model;

import lombok.Getter;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Getter
public class DsvFileResource {
    private URI uri;
    private String archiveType;
    private String compressType;
    private String delimiter = ";";
    private Charset encoding = StandardCharsets.UTF_8;
    private Character quotes = '"';
    private String lineSeparator = System.lineSeparator();

    private DsvFileResource() {
    }

    //todo add logging and warning if null
    public char getDelimiter(final String propertyDelimiter) {
        return Objects.nonNull(propertyDelimiter) && propertyDelimiter.length() > 0 ? propertyDelimiter.charAt(0) : ';';
    }

    public static Builder builder() {
        return new DsvFileResource().new Builder();
    }

    public final class Builder {
        private Builder() {
        }

        public Builder uri(URI uri) {
            DsvFileResource.this.uri = uri;

            return this;
        }

        public Builder compressType(String compressType) {
            DsvFileResource.this.compressType = compressType;

            return this;
        }

        public Builder archiveType(String archiveType) {
            DsvFileResource.this.archiveType = archiveType;

            return this;
        }

        public Builder delimiter(String delimiter) {
            DsvFileResource.this.delimiter = delimiter;

            return this;
        }

        public Builder encoding(Charset encoding) {
            DsvFileResource.this.encoding = encoding;

            return this;
        }

        public Builder quotes(Character quotes) {
            DsvFileResource.this.quotes = quotes;

            return this;
        }

        public Builder lineSeparator(String lineSeparator) {
            DsvFileResource.this.lineSeparator = lineSeparator;

            return this;
        }

        public DsvFileResource build() {
            if (Objects.isNull(uri)) {
                throw new IllegalArgumentException("URI can't be null.");
            }

            return DsvFileResource.this;
        }

    }
}