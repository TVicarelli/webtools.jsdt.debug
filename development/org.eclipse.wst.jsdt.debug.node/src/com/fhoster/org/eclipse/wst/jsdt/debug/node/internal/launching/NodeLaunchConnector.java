/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.fhoster.org.eclipse.wst.jsdt.debug.node.internal.launching;

import java.io.IOException;
import java.util.Map;

import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.VirtualMachine;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.connect.LaunchingConnector;

/**
 * {@link LaunchingConnector} for Node
 */
public class NodeLaunchConnector implements LaunchingConnector {

	public static final String CONNECTOR_ID = "com.fhoster.org.eclipse.wst.jsdt.debug.node.launching.connector"; //$NON-NLS-1$
	
	/**
	 * Constructor
	 */
	public NodeLaunchConnector() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.core.jsdi.connect.Connector#defaultArguments()
	 */
	public Map defaultArguments() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.core.jsdi.connect.Connector#description()
	 */
	public String description() {
		return Messages.NodeLaunchConnector_0;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.core.jsdi.connect.Connector#name()
	 */
	public String name() {
		return Messages.NodeLaunchConnector_1;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.core.jsdi.connect.Connector#id()
	 */
	public String id() {
		return CONNECTOR_ID;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.core.jsdi.connect.LaunchingConnector#launch(java.util.Map)
	 */
	public VirtualMachine launch(Map arguments) throws IOException {
		return null;
	}
}
