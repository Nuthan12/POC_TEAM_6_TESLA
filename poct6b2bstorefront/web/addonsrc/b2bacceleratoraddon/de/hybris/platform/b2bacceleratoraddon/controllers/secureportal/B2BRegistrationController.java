/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.b2bacceleratoraddon.controllers.secureportal;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.b2bacceleratoraddon.constants.B2bacceleratoraddonWebConstants;
import de.hybris.platform.b2bacceleratoraddon.forms.RegistrationForm;
import de.hybris.platform.b2bacceleratorfacades.exception.CustomerAlreadyExistsException;
import de.hybris.platform.b2bacceleratorfacades.exception.RegistrationNotEnabledException;
import de.hybris.platform.b2bacceleratorfacades.registration.B2BRegistrationFacade;
import de.hybris.platform.b2bcommercefacades.data.B2BRegistrationData;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.localization.Localization;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Collections;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Registration page controller: Handles Get and Post request and dispatches relevant wortkflow facades and necessary
 * services
 */
@RequestMapping(value = B2bacceleratoraddonWebConstants.RequestMappings.ACCOUNT_REGISTRATION)
public class B2BRegistrationController extends AbstractB2BRegistrationController
{

	private static final Logger LOG = Logger.getLogger(B2BRegistrationController.class);

	private static final String REGISTER_SUBMIT_CONFIRMATION = "text.secureportal.register.submit.confirmation";
	private static final String SCP_LINK_CREATE_ACCOUNT = "text.secureportal.link.createAccount";
	private static final String REGISTER_ACCOUNT_EXISTING = "text.secureportal.register.account.existing";

	private static final String HOME_REDIRECT = REDIRECT_PREFIX + ROOT;
	private static final String DEFAULT_LOGIN_URI_CONFIG = "default.login.uri";

	@Resource(name = "checkoutFacade")
	private CheckoutFacade checkoutFacade;

	@Resource(name = "b2bRegistrationFacade")
	private B2BRegistrationFacade b2bRegistrationFacade;

	@Resource(name = "modelService")
	private ModelService modelService;

	@Resource(name = "securePortalRegistrationValidator")
	private Validator registrationValidator;

	@Resource(name = "configurationService")
	private ConfigurationService configurationService;

	@RequestMapping(method = RequestMethod.GET)
	public String showRegistrationPage(final HttpServletRequest request, final Model model) throws CMSItemNotFoundException
	{
		if (getCmsSiteService().getCurrentSite().isEnableRegistration())
		{
			return getDefaultRegistrationPage(model, getContentPageForLabelOrId(getRegistrationCmsPage()));
		}
		return HOME_REDIRECT;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitRegistration(final RegistrationForm form, final BindingResult bindingResult, final Model model,
			final HttpServletRequest request, final HttpSession session, final RedirectAttributes redirectModel)
			throws CMSItemNotFoundException
	{

		populateModelCmsContent(model, getContentPageForLabelOrId(getRegistrationCmsPage()));
		model.addAttribute(form);

		getRegistrationValidator().validate(form, bindingResult);
		if (bindingResult.hasErrors())
		{
			return getRegistrationView();
		}

		try
		{
			b2bRegistrationFacade.register(convertFormToData(form));
		}
		catch (final CustomerAlreadyExistsException e) //NOSONAR
		{
			LOG.error("Failed to register account. Account already exists.");
			GlobalMessages.addErrorMessage(model, Localization.getLocalizedString(REGISTER_ACCOUNT_EXISTING));
			return getRegistrationView();
		}
		catch (final RegistrationNotEnabledException e)
		{
			LOG.error("Failed to register account. Registration is not enabled.");
			return getRegistrationView();
		}
		catch (final RuntimeException re)
		{
			LOG.error("Failed to register account. Encountered an internal server error.");
			return getRegistrationView();
		}

		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.INFO_MESSAGES_HOLDER, REGISTER_SUBMIT_CONFIRMATION);
		return REDIRECT_PREFIX + this.configurationService.getConfiguration().getString(DEFAULT_LOGIN_URI_CONFIG);
	}

	/**
	 * @param form
	 *           Form data as submitted by user
	 * @return A DTO object built from the form instance
	 */
	protected B2BRegistrationData convertFormToData(final RegistrationForm form)
	{
		final B2BRegistrationData registrationData = new B2BRegistrationData();
		BeanUtils.copyProperties(form, registrationData);
		registrationData.setName(StringUtils.trim(form.getFirstName()) + " " + StringUtils.trim(form.getLastName()));
		return registrationData;
	}

	@Override
	protected Validator getRegistrationValidator()
	{
		return registrationValidator;
	}

	@Override
	protected String getRegistrationView()
	{
		return B2bacceleratoraddonWebConstants.Views.REGISTRATION_PAGE;
	}

	@Override
	protected String getRegistrationCmsPage()
	{
		return B2bacceleratoraddonWebConstants.CMS_REGISTER_PAGE_NAME;
	}

	@Override
	protected void populateModelCmsContent(final Model model, final ContentPageModel contentPageModel)
	{

		storeCmsPageInModel(model, contentPageModel);
		setUpMetaDataForContentPage(model, contentPageModel);

		final Breadcrumb registrationBreadcrumbEntry = new Breadcrumb("#",
				getMessageSource().getMessage(SCP_LINK_CREATE_ACCOUNT, null, getI18nService().getCurrentLocale()), null);
		model.addAttribute("breadcrumbs", Collections.singletonList(registrationBreadcrumbEntry));
	}

	@Override
	protected String getView()
	{
		return B2bacceleratoraddonWebConstants.Views.LOGIN_PAGE;
	}

	@Override
	protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException
	{
		return getContentPageForLabelOrId("login");
	}

	@Override
	protected String getSuccessRedirect(final HttpServletRequest request, final HttpServletResponse response)
	{
		return HOME_REDIRECT;
	}

}
