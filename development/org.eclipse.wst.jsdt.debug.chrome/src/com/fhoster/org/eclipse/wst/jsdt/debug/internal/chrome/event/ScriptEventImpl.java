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

import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.ScriptReference;
import com.fhoster.org.eclipse.wst.jsdt.debug.core.jsdi.event.ScriptLoadEvent;

/**
 *
 */
public class ScriptEventImpl extends LocatableEventImpl implements
		ScriptLoadEvent {

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.core.jsdi.event.ScriptLoadEvent#script()
	 */
	public ScriptReference script() {
		return null;
	}

}
