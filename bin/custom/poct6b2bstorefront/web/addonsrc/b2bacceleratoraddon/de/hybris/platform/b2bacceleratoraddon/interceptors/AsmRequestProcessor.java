/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.b2bacceleratoraddon.interceptors;

import de.hybris.platform.addonsupport.valueprovider.AddOnValueProvider;
import de.hybris.platform.addonsupport.valueprovider.AddOnValueProviderRegistry;
import de.hybris.platform.servicelayer.session.SessionService;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;




public class AsmRequestProcessor implements SecurePortalRequestProcessor
{
	private String assistedServiceAddOnName;
	private String agentLoggedInKey;
	private String asmRequestParameter;
	private String quitAsmRequestUri;
	private SessionService sessionService;
	private AddOnValueProviderRegistry addOnValueProviderRegistry;


	public String getOtherRequestParameters(final HttpServletRequest request)
	{
		String assistedServiceModeRequested = request.getParameter(getAsmRequestParameter());
		if (request.getRequestURI().endsWith(getQuitAsmRequestUri()))
		{
			assistedServiceModeRequested = "false";
		}

		return assistedServiceModeRequested == null ? null : (getAsmRequestParameter() + "=" + assistedServiceModeRequested);
	}

	public boolean skipSecureCheck()
	{
		final Optional<AddOnValueProvider> optionalValueProvider = getAddOnValueProviderRegistry().get(
				getAssistedServiceAddOnName());

		if (optionalValueProvider.isPresent())
		{
			final Optional<Boolean> value = optionalValueProvider.get().getValue(getAgentLoggedInKey(), Boolean.class);
			return value.isPresent() && value.get().booleanValue();
		}

		return false;
	}

	protected String getAssistedServiceAddOnName()
	{
		return assistedServiceAddOnName;
	}

	public void setAssistedServiceAddOnName(final String assistedServiceAddOnName)
	{
		this.assistedServiceAddOnName = assistedServiceAddOnName;
	}

	protected String getAgentLoggedInKey()
	{
		return agentLoggedInKey;
	}

	public void setAgentLoggedInKey(final String agentLoggedInKey)
	{
		this.agentLoggedInKey = agentLoggedInKey;
	}

	protected String getAsmRequestParameter()
	{
		return asmRequestParameter;
	}

	public void setAsmRequestParameter(final String asmRequestParameter)
	{
		this.asmRequestParameter = asmRequestParameter;
	}

	protected String getQuitAsmRequestUri()
	{
		return quitAsmRequestUri;
	}

	public void setQuitAsmRequestUri(final String quitAsmRequestUri)
	{
		this.quitAsmRequestUri = quitAsmRequestUri;
	}

	protected SessionService getSessionService()
	{
		return sessionService;
	}

	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	protected AddOnValueProviderRegistry getAddOnValueProviderRegistry()
	{
		return addOnValueProviderRegistry;
	}

	public void setAddOnValueProviderRegistry(final AddOnValueProviderRegistry addOnValueProviderRegistry)
	{
		this.addOnValueProviderRegistry = addOnValueProviderRegistry;
	}
}
