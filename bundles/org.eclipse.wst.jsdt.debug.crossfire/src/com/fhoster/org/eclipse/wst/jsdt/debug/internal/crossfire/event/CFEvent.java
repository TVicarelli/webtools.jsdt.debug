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

import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.VirtualMachine;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.event.Event;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.request.EventRequest;

/**
 * Default implementation of an {@link CFEvent} for Crossfire
 * 
 * @since 1.0
 */
public class CFEvent implements Event {

	private VirtualMachine vm = null;
	private EventRequest request = null;
	
	/**
	 * Constructor
	 * 
	 * @param vm
	 * @param request
	 */
	public CFEvent(VirtualMachine vm, EventRequest request) {
		this.vm = vm;
		this.request = request;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.core.jsdi.Mirror#virtualMachine()
	 */
	public VirtualMachine virtualMachine() {
		return vm;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.core.jsdi.event.Event#request()
	 */
	public EventRequest request() {
		return request;
	}
}
