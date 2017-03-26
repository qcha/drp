package com.github.qcha.drp.ftp;

import java.io.IOException;

public final class FtpService {
    private FtpService() {
    }

    public static FtpConnection getFtpConnection(String host, int port, String username, String password) throws IOException {
        return new FtpConnection(host, port, username, password);
    }
}
