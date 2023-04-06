/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.poct6.core.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import de.hybris.poct6.core.constants.Poct6CoreConstants;
import de.hybris.poct6.core.setup.CoreSystemSetup;


/**
 * Do not use, please use {@link CoreSystemSetup} instead.
 * 
 */
public class Poct6CoreManager extends GeneratedPoct6CoreManager
{
	public static final Poct6CoreManager getInstance()
	{
		final ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (Poct6CoreManager) em.getExtension(Poct6CoreConstants.EXTENSIONNAME);
	}
}
