package com.github.aarexer.qcha.drp.ftp;

import java.io.IOException;
import java.io.InputStream;

public interface IFtpClient {
    InputStream getResourceInputStream(String filename) throws IOException;

    boolean changeDir(String dirname) throws IOException;
}
