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

package org.eclipse.mylyn.context.sdk.util.search;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.mylyn.internal.context.core.AbstractRelationProvider;
import org.eclipse.mylyn.internal.context.core.IActiveSearchListener;

/**
 * @author Mik Kersten
 */
public class TestActiveSearchListener implements IActiveSearchListener {

	private AbstractRelationProvider prov = null;

	private List<?> results = null;

	public TestActiveSearchListener(AbstractRelationProvider prov) {
		this.prov = prov;
	}

	private boolean gathered = false;

	public void searchCompleted(List<?> l) {
		List<Object> accepted = new ArrayList<Object>(l.size());
		if (prov != null) {
			for (Object o : l) {
				if (prov.acceptResultElement(o)) {
					accepted.add(o);
				}
			}
			results = accepted;
		} else {
			results = l;
		}
		gathered = true;
	}

	public boolean resultsGathered() {
		return gathered;
	}

	public List<?> getResults() {
		return results;
	}

}
