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
package com.fhoster.org.eclipse.wst.jsdt.debug.internal.chrome.request;


import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.ThreadReference;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.request.ThreadExitRequest;
import com.fhoster.org.eclipse.wst.jsdt.debug.internal.chrome.jsdi.VMImpl;

/**
 * {@link ThreadExitRequest} impl
 * 
 * @since 1.0
 */
public class ThreadExitReqImpl extends EventReqImpl implements ThreadExitRequest {

	private ThreadReference thread = null;
	
	/**
	 * Constructor
	 * @param vm
	 * @param enabled
	 */
	public ThreadExitReqImpl(VMImpl vm, boolean enabled) {
		super(vm, enabled);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.core.jsdi.request.ThreadExitRequest#addThreadFilter(org.eclipse.wst.jsdt.debug.core.jsdi.ThreadReference)
	 */
	public void addThreadFilter(ThreadReference thread) {
		this.thread = thread;
	}

	/**
	 * Returns the underlying {@link ThreadReference} this request applies to
	 * 
	 * @return the underlying {@link ThreadReference}
	 */
	public synchronized ThreadReference thread() {
		return this.thread;
	}
}
