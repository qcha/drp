package qcha.drp.ftp;

import java.io.IOException;
import java.io.InputStream;

public interface FtpConnection extends AutoCloseable {
    InputStream getResourceInputStream(String filename) throws IOException;
}
