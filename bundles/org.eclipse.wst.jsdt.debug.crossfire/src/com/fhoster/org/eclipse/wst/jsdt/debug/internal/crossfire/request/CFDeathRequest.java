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
package com.fhoster.org.eclipse.wst.jsdt.debug.internal.crossfire.request;

import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.VirtualMachine;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.request.VMDeathRequest;

/**
 * Default implementation of {@link VMDeathRequest} for Crossfire
 * 
 * @since 1.0
 */
public class CFDeathRequest extends CFEventRequest implements VMDeathRequest {

	/**
	 * Constructor
	 * @param vm
	 */
	public CFDeathRequest(VirtualMachine vm) {
		super(vm);
	}
}
