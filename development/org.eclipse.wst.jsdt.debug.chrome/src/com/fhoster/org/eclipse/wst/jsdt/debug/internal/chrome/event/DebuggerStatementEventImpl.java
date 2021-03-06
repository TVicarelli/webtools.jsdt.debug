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

import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.Location;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.ThreadReference;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.event.DebuggerStatementEvent;

/**
 *
 */
public class DebuggerStatementEventImpl extends LocatableEventImpl implements
		DebuggerStatementEvent {

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.core.jsdi.event.LocatableEvent#thread()
	 */
	public ThreadReference thread() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.core.jsdi.Locatable#location()
	 */
	public Location location() {
		return null;
	}

}
