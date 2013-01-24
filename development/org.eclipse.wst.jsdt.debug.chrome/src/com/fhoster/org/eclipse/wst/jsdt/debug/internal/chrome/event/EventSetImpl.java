/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.fhoster.org.eclipse.wst.jsdt.debug.internal.chrome.event;

import java.util.HashSet;

import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.VirtualMachine;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.event.EventSet;

/**
 *
 */
public class EventSetImpl extends HashSet implements EventSet {

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.core.jsdi.Mirror#virtualMachine()
	 */
	public VirtualMachine virtualMachine() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.core.jsdi.event.EventSet#suspended()
	 */
	public boolean suspended() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.core.jsdi.event.EventSet#resume()
	 */
	public void resume() {
	}

}
