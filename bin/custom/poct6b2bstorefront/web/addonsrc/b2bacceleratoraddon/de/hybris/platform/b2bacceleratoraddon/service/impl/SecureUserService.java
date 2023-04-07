/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.b2bacceleratoraddon.service.impl;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.impl.DefaultUserService;

import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * Provides services for secure user.
 *
 */
public class SecureUserService extends DefaultUserService
{
	public static final String SECURE_GUID_SESSION_KEY = "acceleratorSecureGUID";

	private transient CMSSiteService cmsSiteService;

	@Override
	public boolean isAnonymousUser(final UserModel user)
	{
		final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		final HttpSession session = attr.getRequest().getSession(false);
		final CMSSiteModel site = getCmsSiteService().getCurrentSite();

		boolean isUserAnonymous = user == null || super.isAnonymousUser(user);
		if (session == null
				|| (site.isRequiresAuthentication() && ((String) session.getAttribute(SECURE_GUID_SESSION_KEY) == null)))
		{
			isUserAnonymous = true;
		}
		return isUserAnonymous;
	}

	protected CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

}
