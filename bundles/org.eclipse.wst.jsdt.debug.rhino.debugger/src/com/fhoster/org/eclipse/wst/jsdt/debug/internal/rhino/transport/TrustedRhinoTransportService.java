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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

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
public class TrustedRhinoTransportService extends SocketTransportService {

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
    protected Socket createSocket(String host, int port) throws UnknownHostException, IOException {
        return new Socket(host, port);
    }

    @Override
    protected ServerSocket createServerSocket(int port) throws IOException {
        return new ServerSocket(port);
    }

}
