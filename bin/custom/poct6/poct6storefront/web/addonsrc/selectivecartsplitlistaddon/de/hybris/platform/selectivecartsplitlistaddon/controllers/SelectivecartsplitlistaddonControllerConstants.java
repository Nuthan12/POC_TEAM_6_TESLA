/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.selectivecartsplitlistaddon.controllers;

import de.hybris.platform.acceleratorcms.model.components.MiniCartComponentModel;
import de.hybris.platform.selectivecartsplitlistaddon.model.components.SaveForLaterCMSComponentModel;


/**
 * Selectivecartsplitlistaddon controller constants
 */
public interface SelectivecartsplitlistaddonControllerConstants
{
	// implement here controller constants used by this extension
	String ADDON_PREFIX = "addon:/selectivecartsplitlistaddon";

	/**
	 * Class with action name constants
	 */
	interface Actions
	{
		interface Cms
		{
			String _Prefix = "/view/";
			String _Suffix = "Controller";

			/**
			 * Default CMS component controller
			 */
			String SaveForLaterComponent = _Prefix + SaveForLaterCMSComponentModel._TYPECODE + _Suffix;
			String MiniCartComponent = _Prefix + MiniCartComponentModel._TYPECODE + _Suffix;
		}
	}

	/**
	 * Class with view name constants
	 */
	interface Views
	{
		interface Cms
		{
			String ComponentPrefix = "cms/";
		}

		interface Fragments
		{
			interface Cart
			{
				String MiniCartPanel = "fragments/cart/miniCartPanel";
				String CartPopup = "fragments/cart/cartPopup";
			}
		}
	}
}
