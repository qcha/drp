package com.github.aarexer.qcha.drp.ftp;

import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class FtpsConnection implements IFtpConnection {
    private static final Logger logger = LogManager.getLogger();

    private static final String DEFAULT_PROTOCOL = "TLS";
    private static final String DEFAULT_PROT = "P";
    private static final long DEFAULT_PBSZ = 0;

    private final FTPSClient ftpsClient;
    private final String host;
    private final int port;

    public FtpsConnection(String host, int port, String username, String password, boolean isImplicit) throws IOException {
        this(host, port, username, password, DEFAULT_PROTOCOL, isImplicit, DEFAULT_PBSZ, DEFAULT_PROT);
        logger.debug("Using TLS protocol: {}, by default", DEFAULT_PROTOCOL);
        logger.debug("Using PBSZ = {}, by default", DEFAULT_PBSZ);
        logger.debug("Using PROT = 'P'(Protected), by default");
    }

    public FtpsConnection(String host, int port, String username, String password, String protocol, boolean isImplicit, long pbsz, String prot) throws IOException {
        this.host = host;
        this.port = port;

        if (pbsz < 0) {
            logger.warn("PBSZ can't be less zero - using PBSZ = {}, by default", DEFAULT_PBSZ);
            pbsz = DEFAULT_PBSZ;
        }

        if (Objects.isNull(prot)) {
            logger.warn("Prot can't be null - using 'P'(Protected), by default");
            prot = "P";
        }

        if (Objects.isNull(protocol)) {
            logger.warn("Protocol can't be null - using TLS protocol: {}, by default", DEFAULT_PROTOCOL);
            protocol = DEFAULT_PROTOCOL;
        }

        ftpsClient = new FTPSClient(protocol, isImplicit);
        ftpsClient.execPBSZ(pbsz);
        ftpsClient.execPROT(prot);

        connect();
        login(username, password);
    }

    private void connect() throws IOException {
        ftpsClient.connect(host, port);
        if (!FTPReply.isPositiveCompletion(ftpsClient.getReplyCode())) {
            logger.error("Can't connect to: {} on port: {}", host, port);
            //fixme bad design.
            ftpsClient.disconnect();
            throw new IOException(String.format("Can't connect to host: %s by port %s.", host, port));
        }
    }

    private void login(final String username, final String password) throws IOException {
        if (!ftpsClient.login(username, password)) {
            logger.error("Can't logging to {} with username:%s and password: {}", host, username, password);
            //fixme bad design.
            ftpsClient.logout();
            ftpsClient.disconnect();
            throw new IOException(String.format("Can't login with username: %s and password: %s", username, password));
        }
    }

    @Override
    public InputStream getResourceInputStream(final String filename) throws IOException {
        return ftpsClient.retrieveFileStream(filename);
    }

    @Override
    public boolean changeDir(final String dirname) throws IOException {
        return ftpsClient.changeWorkingDirectory(dirname);
    }

    @Override
    public void close() throws IOException {
        if (ftpsClient.isConnected()) {
            ftpsClient.logout();
            ftpsClient.disconnect();
        }
    }
}
