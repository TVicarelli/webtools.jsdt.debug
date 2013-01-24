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
package com.fhoster.org.eclipse.wst.jsdt.debug.internal.rhino.jsdi.event;


import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.Location;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.ThreadReference;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.event.StepEvent;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.request.StepRequest;
import com.fhoster.org.eclipse.wst.jsdt.debug.internal.rhino.jsdi.VirtualMachineImpl;

/**
 * Rhino implementation of {@link StepEvent}
 * 
 * @since 1.0
 */
public final class StepEventImpl extends LocatableEventImpl implements StepEvent {

	/**
	 * Constructor
	 * @param vm
	 * @param thread
	 * @param location
	 * @param request
	 */
	public StepEventImpl(VirtualMachineImpl vm, ThreadReference thread, Location location, StepRequest request) {
		super(vm, thread, location, request);
	}
}