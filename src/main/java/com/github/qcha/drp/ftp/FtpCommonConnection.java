package com.github.qcha.drp.ftp;

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

        //fixme may be write better
        connect();
        login(username, password);
    }

    private void connect() throws IOException {
        ftpClient.connect(host, port);
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            logger.error("Can't connect to: {} on port: {}", host, port);
            //fixme bad design.
            ftpClient.disconnect();
            throw new IOException(String.format("Can't connect to host: %s by port %s.", host, port));
        }
    }

    private void login(final String username, final String password) throws IOException {
        if (!ftpClient.login(username, password)) {
            logger.error("Can't logging to: {} on port: {} with given username: {} and password: {}", host, port, username, password);
            //fixme bad design.
            ftpClient.logout();
            ftpClient.disconnect();
            throw new IOException("Can't logging to host.");
        }
    }

    @Override
    public InputStream getResourceInputStream(final String filename) throws IOException {
        return ftpClient.retrieveFileStream(filename);
    }

    @Override
    public boolean changeDir(final String dirname) throws IOException {
        return ftpClient.changeWorkingDirectory(dirname);
    }

    @Override
    public void close() throws IOException {
        if (ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }
}
