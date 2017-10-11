package qcha.drp.model;

import com.google.common.base.Verify;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import qcha.drp.ftp.FtpConnectionType;

import java.net.URI;
import java.util.Objects;

@Getter
public class DsvResource {
    private static final Logger logger = LogManager.getLogger();

    private final URI uri;
    private final DsvPreference preference;

    private DsvResource(URI uri, DsvPreference preference) {
        Verify.verify(Objects.nonNull(preference), "Preference for Dsv resource can't be null.");
        Verify.verify(Objects.nonNull(uri), "Uri can't be null");

        this.uri = uri;
        this.preference = preference;
    }

    public DsvResource(URI uri,
                       char delimiter,
                       char quotes,
                       String encoding,
                       String lineSeparator,
                       ArchiveType archiveType,
                       CompressType compressType,
                       FtpConnectionType connectionType
    ) {
        this(uri, new DsvPreference(delimiter, quotes, encoding, lineSeparator, archiveType, compressType, connectionType));
    }

    public DsvResource(URI uri) {
        this(uri, new DsvPreference());
    }

    public DsvResource(URI uri, ArchiveType archiveType, CompressType compressType) {
        this(uri, new DsvPreference(archiveType, compressType));
    }

    public DsvResource(URI uri, ArchiveType archiveType, CompressType compressType, FtpConnectionType connectionType) {
        this(uri, new DsvPreference(archiveType, compressType, connectionType));
    }
}