package qcha.drp.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class FtpCommonConnection implements FtpConnection, AutoCloseable {
    private static final Logger logger = LogManager.getLogger();

    private final FTPClient ftpClient;
    private final String host;
    private final int port;

    public FtpCommonConnection(String host, int port, String username, String password) throws IOException {
        this.host = host;
        this.port = port;

        ftpClient = new FTPClient();
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        createConnection(username, password);
    }

    private void createConnection(String username, String password) throws IOException {
        ftpClient.connect(host, port);
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            logger.error("Can't connect to: {} on port: {}", host, port);
            throw new IOException(String.format("Can't connect to host: %s by port %s.", host, port));
        }

        if (!ftpClient.login(username, password)) {
            logger.error("Can't logging to {} with username:%s and password: {}", host, username, password);
            throw new IOException(String.format("Can't login to %s with username: %s and password: %s", host, username, password));
        }
    }

    @Override
    public InputStream getResourceInputStream(final String filename) throws IOException {
        return ftpClient.retrieveFileStream(filename);
    }

    @Override
    public void close() throws IOException {
        if (ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }
}
