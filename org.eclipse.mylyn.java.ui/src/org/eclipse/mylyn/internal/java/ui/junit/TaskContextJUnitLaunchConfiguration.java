/*******************************************************************************
 * Copyright (c) 2004, 2011 Tasktop Technologies and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylyn.internal.java.ui.junit;

import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.junit.launcher.JUnitLaunchConfigurationDelegate;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @author Mik Kersten
 */
public class TaskContextJUnitLaunchConfiguration extends JUnitLaunchConfigurationDelegate {

	@Override
	protected IMember[] evaluateTests(ILaunchConfiguration configuration, IProgressMonitor monitor)
			throws CoreException {
		Set<IType> contextTestCases = InteractionContextTestUtil.getTestCasesInContext();
		InteractionContextTestUtil.setupTestConfiguration(contextTestCases, configuration, monitor);

		if (contextTestCases.isEmpty()) {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
							Messages.TaskContextJUnitLaunchConfiguration_Context_Test_Suite,
							Messages.TaskContextJUnitLaunchConfiguration_No_test_types_found_in_the_active_task_context);
				}
			});
		}
		return contextTestCases.toArray(new IMember[contextTestCases.size()]);
	}
}
