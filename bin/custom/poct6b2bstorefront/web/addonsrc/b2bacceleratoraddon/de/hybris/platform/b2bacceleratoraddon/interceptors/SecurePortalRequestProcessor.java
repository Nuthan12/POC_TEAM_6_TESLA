/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.b2bacceleratoraddon.interceptors;

import javax.servlet.http.HttpServletRequest;


public interface SecurePortalRequestProcessor
{
	/**
	 * get other request parameters
	 * 
	 * @param request
	 * @return request parameters
	 */
	String getOtherRequestParameters(final HttpServletRequest request);

	/**
	 * whether we want to skip secure check in secureportal
	 */
	boolean skipSecureCheck();
}
