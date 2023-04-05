/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.b2bacceleratoraddon.constants;

/**
 * Global class for all B2bacceleratoraddon web constants. You can add global constants for your extension into this class.
 */
public final class B2bacceleratoraddonWebConstants
{
	//Dummy field to avoid pmd error - delete when you add the first real constant!
	public static final String deleteThisDummyField = "DELETE ME";
	public static final String ADD_ON_PREFIX = "addon:";
	public static final String VIEW_PAGE_PREFIX = ADD_ON_PREFIX + "/" + B2bacceleratoraddonConstants.EXTENSIONNAME;
	public static final String CMS_REGISTER_PAGE_NAME = "SecureCustomerPortalRegisterPage";
	public static final String REDIRECT_PREFIX = "redirect:";

	private B2bacceleratoraddonWebConstants()
	{
		//empty to avoid instantiating this constant class
	}

	/**
	 * List of any new request URIs defined and handled in this add on.
	 */
	public static final class RequestMappings
	{

		public static final String ACCOUNT_REGISTRATION = "/register";
		public static final String HOME = "/";
		public static final String LOGIN = "/secureLogin";

		private RequestMappings()
		{
		}
	}

	/**
	 * List of all new Views defined and used in this add on.
	 */
	public static final class Views
	{

		public static final String LOGIN_PAGE = VIEW_PAGE_PREFIX + "/pages/secureportal/login";
		public static final String REGISTRATION_PAGE = VIEW_PAGE_PREFIX + "/pages/secureportal/accountRegistration";

		private Views()
		{
		}

	}
}
