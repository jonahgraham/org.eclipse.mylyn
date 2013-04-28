/*******************************************************************************
 * Copyright (c) 2013 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylyn.internal.commons.repositories.ui.auth;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.mylyn.internal.commons.repositories.ui.auth.messages"; //$NON-NLS-1$

	public static String CertificateCredentialsProviderUi_Enter_key_store_password;

	public static String OpenIdCredentialsProviderUi_Login;

	public static String OpenIdCredentialsProviderUi_Login_to_OpenID_Provider;

	public static String UserCredentialsProviderUi_Credentials_for;

	public static String UserCredentialsProviderUi_Enter_HTTP_credentials;

	public static String UserCredentialsProviderUi_Enter_proxy_credentials;

	public static String UserCredentialsProviderUi_Enter_repository_credentials;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
