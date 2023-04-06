/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.customercouponaddon.controllers;


/**
 */
public interface CustomercouponaddonControllerConstants
{
	String ADDON_PREFIX = "addon:/customercouponaddon";

	interface Views
	{
		interface Pages
		{
			interface COUPONS
			{
				String ConponsPage = "/my-account/coupons";
			}

		}

		interface Fragments
		{
			interface Coupons
			{
				String CustomerCouponSubPage = ADDON_PREFIX + "/fragments/customer360/customerCouponSubPage";
			}
		}

	}

	/**
	 * Class with action name constants
	 */
	interface Actions
	{
		interface Cms
		{
			String _Prefix = "/view/";
			String _Suffix = "Controller";
		}
	}

}
