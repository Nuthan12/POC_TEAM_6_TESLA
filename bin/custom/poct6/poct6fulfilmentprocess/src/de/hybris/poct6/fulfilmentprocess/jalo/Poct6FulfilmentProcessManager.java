/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.poct6.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import de.hybris.poct6.fulfilmentprocess.constants.Poct6FulfilmentProcessConstants;

public class Poct6FulfilmentProcessManager extends GeneratedPoct6FulfilmentProcessManager
{
	public static final Poct6FulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (Poct6FulfilmentProcessManager) em.getExtension(Poct6FulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
