/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.fhoster.org.eclipse.wst.jsdt.debug.internal.rhino.transport;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import com.fhoster.org.eclipse.wst.jsdt.debug.transport.Connection;
import com.fhoster.org.eclipse.wst.jsdt.debug.transport.packet.Packet;
import com.fhoster.org.eclipse.wst.jsdt.debug.transport.socket.SocketConnection;
import com.fhoster.org.eclipse.wst.jsdt.debug.transport.socket.SocketTransportService;


/**
 * Implementation of a transport service that using a {@link Socket} for
 * communication
 *
 * @since 1.0
 */
public class TrustedRhinoSecureTransportService extends SocketTransportService {

    /*
     * (non-Javadoc)
     *
     * @see fhoster.eclipse.debug.transport.socket.SocketTransportService#
     * getConnection(java.net.Socket)
     */
    @Override
    public SocketConnection getConnection(Socket socket) throws IOException {
        return new TrustedRhinoSocketConnection(socket);
    }

    /*
     * (non-Javadoc)
     *
     * @see fhoster.eclipse.debug.transport.socket.SocketTransportService#
     * handleAccept(fhoster.eclipse.debug.transport.Connection)
     */
    @Override
    public void handleAccept(Connection connection) throws IOException {
        Packet packet = connection.readPacket();
        if (packet instanceof RhinoRequest) {
            RhinoRequest request = (RhinoRequest) packet;
            if (!request.getCommand().equals(JSONConstants.CONNECT)) {
                throw new IOException("failure establishing connection"); //$NON-NLS-1$
            }
            RhinoResponse response = new RhinoResponse(request.getSequence(), request.getCommand());
            connection.writePacket(response);
            return;
        }
        throw new IOException("failure establishing connection"); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     *
     * @see fhoster.eclipse.debug.transport.socket.SocketTransportService#
     * handleAttach(fhoster.eclipse.debug.transport.Connection)
     */
    @Override
    public void handleAttach(Connection connection, String username, String password) throws Exception {
        TrustedRhinoSocketConnection conn = (TrustedRhinoSocketConnection) connection;
        conn.authenticate(username, password);
        RhinoRequest request = new RhinoRequest(JSONConstants.CONNECT);
        conn.writePacket(request);
        Packet packet = conn.readPacket();
        if (packet instanceof RhinoResponse) {
            RhinoResponse response = (RhinoResponse) packet;
            if (!response.getCommand().equals(JSONConstants.CONNECT) || response.getRequestSequence() != request.getSequence() || !response.isSuccess() || !response.isRunning()) {
                throw new IOException("failure establishing connection"); //$NON-NLS-1$
            }
            return;
        }
        throw new IOException("failure establishing connection"); //$NON-NLS-1$
    }

    @Override
    protected Socket createSocket(String host, int port) throws Exception {
        /*
         * FIXME: use a relative path for the certificate. where we put that?
         */

        InputStream is = null;
        try {
        	is = this.getClass().getResourceAsStream("/keystore.jks"); //$NON-NLS-1$
            String keystorePassword = "password"; //$NON-NLS-1$
            String keystoreType = "JKS"; //$NON-NLS-1$

            String algorithm = "SunX509"; //$NON-NLS-1$
            String sslProtocol = "SSLv3"; //$NON-NLS-1$

            char[] passphrase = keystorePassword.toCharArray();
            KeyStore keystore = KeyStore.getInstance(keystoreType);
            keystore.load(is, passphrase);

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(algorithm);
            tmf.init(keystore);

            SSLContext context = SSLContext.getInstance(sslProtocol);
            TrustManager[] trustManagers = tmf.getTrustManagers();

            context.init(null, trustManagers, null);

            SSLSocketFactory sf = context.getSocketFactory();

            Socket s = sf.createSocket(host, port);

            return s;
        }
        finally {
            if (is != null)
                is.close();
        }
    }

    @Override
    protected ServerSocket createServerSocket(int port) throws Exception {
        /*
         * FIXME: use a relative path for the certificate. where we put that?
         */
        InputStream is = null;
        try {
            is = this.getClass().getResourceAsStream("/keystore.jks"); //$NON-NLS-1$
            String keystorePassword = "password"; //$NON-NLS-1$
            String keystoreType = "JKS"; //$NON-NLS-1$

            String algorithm = "SunX509"; //$NON-NLS-1$
            String sslProtocol = "SSLv3"; //$NON-NLS-1$

            SSLContext context;
            KeyManagerFactory kmf;
            KeyStore ks;
            char[] storepass = keystorePassword.toCharArray();
            char[] keypass = "leonforte".toCharArray(); //$NON-NLS-1$

            context = SSLContext.getInstance(sslProtocol);
            kmf = KeyManagerFactory.getInstance(algorithm);

            ks = KeyStore.getInstance(keystoreType);

            ks.load(is, storepass);
            kmf.init(ks, keypass);
            context.init(kmf.getKeyManagers(), null, null);
            SSLServerSocketFactory ssf = context.getServerSocketFactory();

            ServerSocket ss = ssf.createServerSocket(port);
            return ss;
        }
        finally {
            if (is != null)
                is.close();
        }
    }

}
