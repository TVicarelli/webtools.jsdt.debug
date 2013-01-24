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
import java.net.Socket;

import com.fhoster.org.eclipse.wst.jsdt.debug.transport.socket.TrustedConnection;

/**
 * A specialized {@link Connection} that communicates using {@link Socket}s
 * 
 * @since 1.0
 */
public class TrustedRhinoSocketConnection extends RhinoSocketConnection implements TrustedConnection {

    public TrustedRhinoSocketConnection(Socket socket) throws IOException {
        super(socket);
    }

    public void authenticate(String username, String Password) throws Exception {

        if (username == null || Password == null) {
            throw new Exception("AuthenticationException"); //$NON-NLS-1$
            //TODO:refine this exception
        }

    }

}
