/*******************************************************************************
 * Copyright (c) 2016 Frank Becker and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Frank Becker - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylyn.bugzilla.rest.core.tests;

import java.util.List;

import org.eclipse.mylyn.commons.sdk.util.CommonTestUtil;
import org.eclipse.mylyn.commons.sdk.util.ManagedSuite;
import org.eclipse.mylyn.commons.sdk.util.ManagedSuite.SuiteClassProvider;
import org.eclipse.mylyn.commons.sdk.util.ManagedSuite.TestConfigurationProperty;
import org.eclipse.mylyn.commons.sdk.util.TestConfiguration;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(ManagedSuite.class)
@Suite.SuiteClasses({ RepositoryKeyTest.class })
@TestConfigurationProperty()
public class AllBugzillaRestCoreTests {
	static {
		if (CommonTestUtil.fixProxyConfiguration()) {
			CommonTestUtil.dumpSystemInfo(System.err);
		}
	}

	@SuiteClassProvider
	public static void add2SuiteClasses(List<Class<?>> suiteClassList, TestConfiguration testConfiguration) {
		if (!testConfiguration.isLocalOnly()) {
			suiteClassList.add(BugzillaRestClientTest.class);
			suiteClassList.add(BugzillaRestConfigurationTest.class);
			suiteClassList.add(BugzillaRestConnectorTest.class);
		}
	}
}
