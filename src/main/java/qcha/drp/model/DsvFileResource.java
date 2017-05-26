package qcha.drp.model;

import com.google.common.base.Verify;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;

@Getter
public class DsvFileResource {
    private static final Logger logger = LogManager.getLogger();

    private final URI uri;
    private final DsvPreference preference;

    private DsvFileResource(URI uri, DsvPreference preference) {
        Verify.verifyNotNull(preference, "Preference for Dsv resource can't be null.");
        Verify.verifyNotNull(uri, "Uri can't be null");

        this.uri = uri;
        this.preference = preference;
    }

    public DsvFileResource(URI uri, char delimiter, char quotes, String encoding, String lineSeparator, ArchiveType archiveType, CompressType compressType) {
        this(uri, new DsvPreference(delimiter, quotes, encoding, lineSeparator, archiveType, compressType));
    }

    public DsvFileResource(URI uri) {
        this(uri, new DsvPreference());
    }

    public DsvFileResource(URI uri, ArchiveType archiveType, CompressType compressType) {
        this(uri, new DsvPreference(archiveType, compressType));
    }
}