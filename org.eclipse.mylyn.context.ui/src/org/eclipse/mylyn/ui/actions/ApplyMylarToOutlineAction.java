/*******************************************************************************
 * Copyright (c) 2004 - 2005 University Of British Columbia and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     University Of British Columbia - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylar.ui.actions;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.mylar.ui.IMylarUiBridge;
import org.eclipse.mylar.ui.InterestFilter;
import org.eclipse.mylar.ui.MylarUiPlugin;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.internal.Workbench;

/**
 * @author Shawn Minto
 */
public class ApplyMylarToOutlineAction extends AbstractApplyMylarAction {
	
	public static ApplyMylarToOutlineAction INSTANCE;
	
	public ApplyMylarToOutlineAction() {
		super(new InterestFilter());
		INSTANCE = this;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<StructuredViewer> getViewers() {
		List<StructuredViewer> viewers = new ArrayList<StructuredViewer>();
		if (Workbench.getInstance() == null || Workbench.getInstance().getActiveWorkbenchWindow() == null) return null;
		IEditorPart[] parts = Workbench.getInstance().getActiveWorkbenchWindow().getActivePage().getEditors();
		for (int i = 0; i < parts.length; i++) {
			IMylarUiBridge bridge = MylarUiPlugin.getDefault().getUiBridgeForEditor(parts[i]);
			List<TreeViewer> outlineViewers = bridge.getContentOutlineViewers(parts[i]);
			for (TreeViewer viewer : outlineViewers) {
				if (viewer != null && !viewers.contains(viewer)) viewers.add(viewer);
			}
		}
		return viewers;
	}

	public static ApplyMylarToOutlineAction getDefault() {
		return INSTANCE;
	}

	public void propertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}