/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.fhoster.org.eclipse.wst.jsdt.debug.transport.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.fhoster.org.eclipse.wst.jsdt.debug.transport.Connection;
import com.fhoster.org.eclipse.wst.jsdt.debug.transport.Constants;
import com.fhoster.org.eclipse.wst.jsdt.debug.transport.ListenerKey;
import com.fhoster.org.eclipse.wst.jsdt.debug.transport.TransportService;

/**
 * Implementation of a {@link TransportService} that using a {@link Socket} for
 * communication
 * 
 * @since 1.0
 */
public abstract class SocketTransportService implements TransportService {

    /**
     * Map of {@link ListenerKey} to {@link ServerSocket}s
     */
    private final Map listeners = new HashMap();

    /*
     * (non-Javadoc)
     * 
     * @see
     * fhoster.eclipse.debug.transport.TransportService#startListening(
     * java.lang.String)
     */
    public synchronized ListenerKey startListening(String address) throws Exception {
        String host = null;
        int port = 0;
        if (address != null) {
            String[] strings = address.split(Constants.COLON);
            if (strings.length == 2) {
                host = strings[0];
                port = Integer.parseInt(strings[1]);
            }
            else {
                port = Integer.parseInt(strings[0]);
            }
        }
        if (host == null) {
            host = Constants.LOCALHOST;
        }
        ListenerKey key = new SocketListenerKey(host + Constants.COLON + port);
        ServerSocket serverSocket = this.createServerSocket(port);
        this.listeners.put(key, serverSocket);
        return key;

    }

    protected abstract ServerSocket createServerSocket(int port) throws Exception;

    /*
     * (non-Javadoc)
     * 
     * @see
     * fhoster.eclipse.debug.transport.TransportService#stopListening(org
     * .eclipse.wst.jsdt.debug.transport.TransportService.ListenerKey)
     */
    public void stopListening(ListenerKey key) throws IOException {
        ServerSocket serverSocket = (ServerSocket) this.listeners.remove(key);
        if (serverSocket != null) {
            serverSocket.close();
        }
    }

    /**
     * Returns the {@link ServerSocket} mapped to the given {@link ListenerKey}
     * or <code>null</code> if the mapping does not exist
     * 
     * @param key
     *            the {@link ListenerKey} to get the {@link ServerSocket} for
     * @return the mapped {@link ServerSocket} or <code>null</code>
     */
    public final ServerSocket getServerSocket(ListenerKey key) {
        synchronized (this.listeners) {
            return (ServerSocket) this.listeners.get(key);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fhoster.eclipse.debug.transport.TransportService#accept(org.eclipse
     * .wst.jsdt.debug.transport.TransportService.ListenerKey, long, long)
     */
    public Connection accept(ListenerKey key, long attachTimeout, long handshakeTimeout) throws IOException {
        ServerSocket serverSocket = this.getServerSocket(key);
        if (serverSocket == null) {
            throw new IllegalStateException("Accept failed. Not listening for address: key.address()"); //$NON-NLS-1$
        }
        int timeout = (int) attachTimeout;
        if (timeout > 0) {
            if (timeout > Integer.MAX_VALUE) {
                timeout = Integer.MAX_VALUE; // approximately 25 days!
            }
            serverSocket.setSoTimeout(timeout);
        }
        Connection connection = this.getConnection(serverSocket.accept());
        this.handleAccept(connection);
        return connection;
    }

    /**
     * This method allows implementors to perform specific actions immediately
     * following
     * a successful accept for {@link Socket} communication (the socket has been
     * successfully opened).
     * 
     * @param connection
     *            the connection that just opened
     * @param password
     * @param username
     * @throws IOException
     */
    public abstract void handleAccept(Connection connection) throws IOException;

    /**
     * Returns the {@link SocketConnection} for the given {@link Socket}. <br>
     * <br>
     * This method cannot return <code>null</code>, if a connection cannot be
     * created
     * an {@link IOException} must be thrown.
     * 
     * @param socket
     *            the socket to get the connection for
     * @return the {@link SocketConnection} for the given {@link Socket}, never
     *         <code>null</code>
     * @throws IOException
     */
    public abstract SocketConnection getConnection(Socket socket) throws IOException;

    /*
     * (non-Javadoc)
     * 
     * @see
     * fhoster.eclipse.debug.transport.TransportService#attach(java.lang
     * .String, long, long)
     */

    public Connection attach(String address, String username, String password, long attachTimeout, long handshakeTimeout) throws IOException {
        String host = null;
        int port = 0;
        if (address != null) {
            String[] strings = address.split(Constants.COLON);
            if (strings.length == 2) {
                host = strings[0];
                port = Integer.parseInt(strings[1]);
            }
            else {
                port = Integer.parseInt(strings[0]);
            }
        }
        if (host == null) {
            host = Constants.LOCALHOST;
        }
        Connection connection = null;
        try {
            connection = this.getConnection(this.createSocket(host, port));
            this.handleAttach(connection, username, password);
        }
        catch (Exception e) {
            //TODO: manage exception
            e.printStackTrace();
        }
        return connection;
    }

    protected abstract Socket createSocket(String host, int port) throws Exception;

    /**
     * This method allows implementors to perform specific actions immediately
     * following
     * a successful attach for {@link Socket} communication (the socket has been
     * successfully opened).
     * 
     * @param connection
     *            the connection that just opened
     * @param password
     * @param username
     * @throws Exception
     */
    public abstract void handleAttach(Connection connection, String username, String password) throws Exception;
}
