/*******************************************************************************
 * Copyright (c) 2010,2014 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *     Maarten Meijer       - added confirmation dialog bug#340986
 *******************************************************************************/

package org.eclipse.mylyn.internal.builds.ui.commands;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.mylyn.builds.core.IBuildPlan;
import org.eclipse.mylyn.builds.core.IBuildServer;
import org.eclipse.mylyn.internal.builds.ui.BuildsUiInternal;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author Steffen Pingel
 */
public class DeleteBuildElementHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			Object item = ((IStructuredSelection) selection).getFirstElement();
			if (item instanceof IBuildServer) {
				IBuildServer server = (IBuildServer) item;
				boolean deleteConfirmed = MessageDialog.openQuestion(
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
						"Delete Build Server",
						NLS.bind("Are you sure you want to delete build server \"{0}\"?\n"
								+ "You will also lose the settings for the builds that you watched.", server.getLabel()));

				if (deleteConfirmed) {
					List<IBuildPlan> plans = BuildsUiInternal.getModel().getPlans((IBuildServer) item);
					BuildsUiInternal.getModel().getPlans().removeAll(plans);
					BuildsUiInternal.getModel().getServers().remove(item);
				}
			}
		}
		return null;
	}

}
