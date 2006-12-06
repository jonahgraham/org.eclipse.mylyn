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

package org.eclipse.mylar.internal.context.ui.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.util.TransferDragSourceListener;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.mylar.context.core.AbstractRelationProvider;
import org.eclipse.mylar.context.core.ContextCorePlugin;
import org.eclipse.mylar.context.core.IMylarContext;
import org.eclipse.mylar.context.core.IMylarContextListener;
import org.eclipse.mylar.context.core.IMylarElement;
import org.eclipse.mylar.context.core.AbstractContextStructureBridge;
import org.eclipse.mylar.context.core.MylarStatusHandler;
import org.eclipse.mylar.context.ui.ContextUiPlugin;
import org.eclipse.mylar.context.ui.AbstractContextUiBridge;
import org.eclipse.mylar.internal.context.ui.ActiveViewSelectionDragAdapter;
import org.eclipse.mylar.internal.context.ui.DoiOrderSorter;
import org.eclipse.mylar.internal.context.ui.ContextUiImages;
import org.eclipse.mylar.internal.context.ui.actions.LinkActiveSearchWithEditorAction;
import org.eclipse.mylar.internal.context.ui.actions.ShowQualifiedNamesAction;
import org.eclipse.mylar.internal.context.ui.actions.ToggleRelationshipProviderAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

/**
 * @author Mik Kersten
 */
public class ActiveSearchView extends ViewPart {

	private static final String STOP_JOBS_LABEL = "Stop Active Search Jobs";

	public static final String ID = "org.eclipse.mylar.ui.views.active.search";

	private TreeViewer viewer;

	private List<ToggleRelationshipProviderAction> relationshipProviderActions = new ArrayList<ToggleRelationshipProviderAction>();

	private DelegatingContextLabelProvider labelProvider = new DelegatingContextLabelProvider();

	/**
	 * For testing.
	 */
	private boolean syncExecForTesting = true;

	private final IMylarContextListener REFRESH_UPDATE_LISTENER = new IMylarContextListener() {

		public void interestChanged(List<IMylarElement> nodes) {
			refresh(nodes.get(nodes.size() - 1), false);
		}

		public void contextActivated(IMylarContext taskscape) {
			ContextUiPlugin.getDefault().refreshRelatedElements();
			refresh(null, true);
		}

		public void contextDeactivated(IMylarContext taskscape) {
			refresh(null, true);
		}

		public void presentationSettingsChanging(UpdateKind kind) {
			refresh(null, true);
		}

		public void landmarkAdded(IMylarElement node) {
			refresh(null, true);
		}

		public void landmarkRemoved(IMylarElement node) {
			refresh(null, true);
		}

		public void relationsChanged(IMylarElement node) {
			refresh(node, true);
		}

		public void elementDeleted(IMylarElement node) {
			refresh(null, true);
		}

		public void presentationSettingsChanged(UpdateKind kind) {
			if (viewer != null && !viewer.getTree().isDisposed()) {
				if (kind == IMylarContextListener.UpdateKind.HIGHLIGHTER)
					viewer.refresh();
			}
		}
	};

	public static ActiveSearchView getFromActivePerspective() {
		if (PlatformUI.getWorkbench() == null)
			return null;
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if (activePage == null)
			return null;
		IViewPart view = activePage.findView(ID);
		if (view instanceof ActiveSearchView)
			return (ActiveSearchView) view;
		return null;
	}

	public ActiveSearchView() {
		ContextCorePlugin.getContextManager().addListener(REFRESH_UPDATE_LISTENER);
		for (AbstractRelationProvider provider : ContextCorePlugin.getContextManager().getActiveRelationProviders()) {
			provider.setEnabled(true);
		}
		ContextUiPlugin.getDefault().refreshRelatedElements();
	}

	@Override
	public void dispose() {
		super.dispose();
		ContextCorePlugin.getContextManager().removeListener(REFRESH_UPDATE_LISTENER);
	}
	
	/**
	 * fix for bug 109235
	 * 
	 * @param node
	 * @param updateLabels
	 */
	void refresh(final IMylarElement node, final boolean updateLabels) {
		if (!syncExecForTesting) { // for testing
			// if (viewer != null && !viewer.getTree().isDisposed()) {
			// internalRefresh(node, updateLabels);
			// }
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				public void run() {
					try {
						internalRefresh(node, updateLabels);
					} catch (Throwable t) {
						MylarStatusHandler.log(t, "active searchrefresh failed");
					}
				}
			});
		} else {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					try {
						internalRefresh(node, updateLabels);
					} catch (Throwable t) {
						MylarStatusHandler.log(t, "active searchrefresh failed");
					}
				}
			});
		}
	}

	private void internalRefresh(final IMylarElement node, boolean updateLabels) {
		Object toRefresh = null;
		if (node != null) {
			AbstractContextStructureBridge bridge = ContextCorePlugin.getDefault().getStructureBridge(node.getContentType());
			toRefresh = bridge.getObjectForHandle(node.getHandleIdentifier());
		}
		if (viewer != null && !viewer.getTree().isDisposed()) {
			viewer.getControl().setRedraw(false);
			if (toRefresh != null && containsNode(viewer.getTree(), toRefresh)) {
				viewer.refresh(toRefresh, updateLabels);
			} else if (node == null) {
				viewer.refresh();
			}
			viewer.expandToLevel(3);
			viewer.getControl().setRedraw(true);
		}
	}

	private boolean containsNode(Tree tree, Object object) {
		boolean contains = false;
		for (int i = 0; i < tree.getItems().length; i++) {
			TreeItem item = tree.getItems()[i];
			if (object.equals(item.getData()))
				contains = true;
		}
		return contains;
	}

	private void initDrop() {
		Transfer[] types = new Transfer[] { LocalSelectionTransfer.getTransfer() };
		viewer.addDropSupport(DND.DROP_MOVE, types, new ActiveViewDropAdapter(viewer));
	}

	private void initDrag() {
		int ops = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		Transfer[] transfers = new Transfer[] { LocalSelectionTransfer.getTransfer() };
		TransferDragSourceListener[] dragListeners = new TransferDragSourceListener[] {
				new ActiveViewSelectionDragAdapter(viewer) };//, new ActiveViewResourceDragAdapter(viewer) };
		viewer.addDragSupport(ops, transfers, new ActiveViewDelegatingDragAdapter(dragListeners));
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@Override
	public void createPartControl(Composite parent) {

		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setUseHashlookup(true);
		viewer.setContentProvider(new ContextContentProvider(viewer.getTree(), this.getViewSite(), true));
		// viewer.setLabelProvider(labelProvider);
		viewer.setLabelProvider(new DecoratingLabelProvider(labelProvider, PlatformUI.getWorkbench()
				.getDecoratorManager().getLabelDecorator()));
		viewer.setSorter(new DoiOrderSorter());
		viewer.setInput(getViewSite());
		hookContextMenu();
		initDrop();
		initDrag();
		getSite().setSelectionProvider(getViewer());
		
		viewer.addOpenListener(new ContextNodeOpenListener(viewer));

		contributeToActionBars();
		viewer.expandToLevel(2);
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ActiveSearchView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(new Separator());
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		IAction qualifyElements = new ShowQualifiedNamesAction(this);
		manager.add(qualifyElements);
		fillActions(manager);
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalPullDown(IMenuManager manager) {
		fillActions(manager);
		IAction stopAction = new Action() {
			@Override
			public void run() {
				for (AbstractRelationProvider provider : ContextCorePlugin.getContextManager().getActiveRelationProviders()) {
					provider.stopAllRunningJobs();
				}
			}
		};
		stopAction.setToolTipText(STOP_JOBS_LABEL);
		stopAction.setText(STOP_JOBS_LABEL);
		stopAction.setImageDescriptor(ContextUiImages.STOP_SEARCH);
		manager.add(stopAction);
		manager.add(new Separator());
		manager.add(new LinkActiveSearchWithEditorAction());
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillActions(IContributionManager manager) {
		List<AbstractContextUiBridge> bridges = ContextUiPlugin.getDefault().getUiBridges();
		for (AbstractContextUiBridge uiBridge : bridges) {
			AbstractContextStructureBridge structureBridge = ContextCorePlugin.getDefault().getStructureBridge(uiBridge.getContentType());
			
//			AbstractContextStructureBridge bridge = entry.getValue(); // bridges.get(extension);
			List<AbstractRelationProvider> providers = structureBridge.getRelationshipProviders();
			if (providers != null && providers.size() > 0) {
				ToggleRelationshipProviderAction action = new ToggleRelationshipProviderAction(structureBridge, uiBridge);
				relationshipProviderActions.add(action);
				manager.add(action);
			}
		}
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		viewer.refresh();
		viewer.getControl().setFocus();
		// TODO: foo
	}

	public TreeViewer getViewer() {
		return viewer;
	}

	/**
	 * Set to false for testing
	 */
	public void setSyncExecForTesting(boolean asyncRefreshMode) {
		this.syncExecForTesting = asyncRefreshMode;
	}

	public void setQualifiedNameMode(boolean qualifiedNameMode) {
		DelegatingContextLabelProvider.setQualifyNamesMode(qualifiedNameMode);
		refresh(null, true);
	}
}
