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


import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.ScriptReference;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.ThreadReference;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.event.ScriptLoadEvent;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.request.ScriptLoadRequest;
import com.fhoster.org.eclipse.wst.jsdt.debug.internal.rhino.jsdi.ScriptReferenceImpl;
import com.fhoster.org.eclipse.wst.jsdt.debug.internal.rhino.jsdi.VirtualMachineImpl;

/**
 * Rhino implementation of {@link ScriptLoadEvent}
 * 
 * @since 1.0
 */
public final class ScriptLoadEventImpl extends LocatableEventImpl implements ScriptLoadEvent {

	private final ScriptReferenceImpl script;

	/**
	 * Constructor
	 * @param vm
	 * @param thread
	 * @param script
	 * @param request
	 */
	public ScriptLoadEventImpl(VirtualMachineImpl vm, ThreadReference thread, ScriptReferenceImpl script, ScriptLoadRequest request) {
		super(vm, thread, null, request);
		this.script = script;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.core.jsdi.event.ScriptLoadEvent#script()
	 */
	public ScriptReference script() {
		return script;
	}
}