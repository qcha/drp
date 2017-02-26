package com.github.aarexer.qcha.drp.ftp;

import java.io.IOException;

public final class FtpService {
    private FtpService() {
    }

    public static FtpClient getFtpClient(String host, int port, String username, String password) throws IOException {
        return new FtpClient(host, port, username, password);
    }
}
