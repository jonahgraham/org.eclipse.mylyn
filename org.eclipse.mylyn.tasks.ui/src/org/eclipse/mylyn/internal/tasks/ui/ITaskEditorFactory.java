/*******************************************************************************
 * Copyright (c) 2004 - 2006 University Of British Columbia and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     University Of British Columbia - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylar.internal.tasks.ui;

import org.eclipse.mylar.internal.tasks.ui.editors.MylarTaskEditor;
import org.eclipse.mylar.tasks.core.ITask;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

/**
 * @author Mik Kersten
 */
public interface ITaskEditorFactory {

	public IEditorPart createEditor(MylarTaskEditor parentEditor, IEditorInput editorInput);

	public IEditorInput createEditorInput(ITask task);
	
	public boolean providesOutline();

	public String getTitle();

	public boolean canCreateEditorFor(ITask task);
	
	public boolean canCreateEditorFor(IEditorInput input);
}
