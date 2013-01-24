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
package com.fhoster.org.eclipse.wst.jsdt.debug.internal.ui.actions;

import org.eclipse.debug.core.DebugException;
import org.eclipse.jface.viewers.Viewer;

import com.fhoster.org.eclipse.wst.jsdt.debug.core.model.IJavaScriptValue;
import com.fhoster.org.eclipse.wst.jsdt.debug.internal.core.model.JavaScriptProperty;
import com.fhoster.org.eclipse.wst.jsdt.debug.internal.core.model.JavaScriptVariable;
import com.fhoster.org.eclipse.wst.jsdt.debug.internal.ui.Constants;


/**
 * Viewer filter action for filtering functions
 * 
 * @since 1.1
 */
public class FunctionFilterAction extends ViewFilterAction {

	/* (non-Javadoc)
	 * @see org.eclipse.wst.jsdt.debug.internal.ui.actions.ViewFilterAction#getPreferenceKey()
	 */
	protected String getPreferenceKey() {
		return Constants.SHOW_FUNCTIONS;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(element instanceof JavaScriptVariable){
			JavaScriptVariable var = (JavaScriptVariable) element;
			try {
				if(IJavaScriptValue.FUNCTION.equals(var.getReferenceTypeName())) {
					return getValue();
				}
			} catch (DebugException e) {
			}
		}
		if(element instanceof JavaScriptProperty){
			JavaScriptProperty prop = (JavaScriptProperty) element;
			try {
				if(IJavaScriptValue.FUNCTION.equals(prop.getReferenceTypeName())) {
					return getValue();
				}
			} catch (DebugException e) {
			}
		}
		return true;
	}
}
