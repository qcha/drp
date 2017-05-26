package qcha.drp.ftp;

import java.io.IOException;

public final class FtpConnectionFabric {
    private FtpConnectionFabric() {
    }

    public static FtpCommonConnection getFtpConnection(String host, int port, String username, String password) throws IOException {
        return new FtpCommonConnection(host, port, username, password);
    }
}
