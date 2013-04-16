/*******************************************************************************
 * Copyright (c) 2011 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylyn.gerrit.tests.support;

import org.eclipse.mylyn.commons.sdk.util.FixtureConfiguration;
import org.eclipse.mylyn.commons.sdk.util.TestConfiguration;
import org.eclipse.mylyn.internal.gerrit.core.GerritConnector;
import org.eclipse.mylyn.tests.util.TestFixture;

/**
 * @author Steffen Pingel
 */
public class GerritFixture extends TestFixture {

	public static GerritFixture GERRIT_ECLIPSE_ORG = new GerritFixture("https://git.eclipse.org/r", "2.2.2", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	public static GerritFixture DEFAULT = TestConfiguration.getDefault().discoverDefault(GerritFixture.class, "gerrit"); //$NON-NLS-1$

	private static GerritFixture current;

	private final boolean excluded;

	public GerritFixture(String url, String version, String description) {
		super(GerritConnector.CONNECTOR_KIND, url);
		setInfo(url, version, description);
		excluded = "Test".equals(description); //$NON-NLS-1$
	}

	public GerritFixture(FixtureConfiguration configuration) {
		this(configuration.getUrl(), configuration.getVersion(), configuration.getInfo());
	}

	public static GerritFixture current() {
		if (current == null) {
			DEFAULT.activate();
		}
		return current;
	}

	@Override
	protected GerritFixture activate() {
		current = this;
		setUpFramework();
		return this;
	}

	@Override
	protected GerritFixture getDefault() {
		return DEFAULT;
	}

	public GerritHarness harness() {
		return new GerritHarness(this);
	}

	public boolean canAuthenticate() {
		return true;
	}

	@Override
	public boolean isExcluded() {
		return super.isExcluded() || excluded;
	}

}
