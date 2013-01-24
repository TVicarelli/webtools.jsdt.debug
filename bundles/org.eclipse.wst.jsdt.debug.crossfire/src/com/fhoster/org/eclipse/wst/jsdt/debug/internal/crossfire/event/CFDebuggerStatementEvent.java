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
package com.fhoster.org.eclipse.wst.jsdt.debug.internal.crossfire.event;

import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.Location;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.ThreadReference;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.VirtualMachine;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.event.DebuggerStatementEvent;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.request.EventRequest;

/**
 * Default implementation of {@link DebuggerStatementEvent} for Crossfire
 * 
 * @since 1.0
 */
public class CFDebuggerStatementEvent extends CFLocatableEvent implements DebuggerStatementEvent {

	/**
	 * Constructor
	 * @param vm
	 * @param request
	 * @param thread
	 * @param location
	 */
	public CFDebuggerStatementEvent(VirtualMachine vm, EventRequest request, ThreadReference thread, Location location) {
		super(vm, request, thread, location);
	}
}
