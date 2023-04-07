/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.sap.productconfig.frontend.controllers;

import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.sap.productconfig.facades.ConfigurationVariantData;
import de.hybris.platform.sap.productconfig.facades.ConfigurationVariantFacade;
import de.hybris.platform.sap.productconfig.frontend.constants.SapproductconfigfrontendWebConstants;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.webservicescommons.util.YSanitizer;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * Controller for variant search
 */
@Controller
@RequestMapping()
public class SearchSimilarVariantsController extends AbstractProductConfigController
{

	private static final Logger LOG = Logger.getLogger(SearchSimilarVariantsController.class);
	private static final String AJAX_VIEW_NAME = SapproductconfigfrontendWebConstants.SEARCH_VARIANTS_VIEW_NAME + SapproductconfigfrontendWebConstants.AJAX_SUFFIX;

	@Resource(name = "sapProductConfigVariantFacade")
	private ConfigurationVariantFacade variantFacade;

	/**
	 * Searches similar variants considering the value assignments of the given runtime configuration. Renders the
	 * results as variant carousel.
	 *
	 * @param configId    configuration session id
	 * @param productCode code of the configurable product
	 * @param model       view model
	 * @return view name
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/cpq/searchConfigVariant", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView searchVariant(@RequestParam(value = "configId") final String configId, @RequestParam(value = "productCode") final String productCode, final Model model) throws BusinessException
	{
		final String sanitizedConfigId = YSanitizer.sanitize(configId);
		final String sanitizedProductCode = YSanitizer.sanitize(productCode);
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Recieved search request for similar variants with configId=" + sanitizedConfigId + " and productCode="
					+ sanitizedProductCode);
		}

		final List<ConfigurationVariantData> variants = getVariantFacade().searchForSimilarVariants(sanitizedConfigId,
				sanitizedProductCode);
		checkForCartEntryLink(variants, sanitizedConfigId);

		model.addAttribute(SapproductconfigfrontendWebConstants.VARIANT_SEARCH_RESULT_ATTRIBUTE, variants);
		final ModelAndView view = new ModelAndView(getViewName());

		if (LOG.isDebugEnabled())
		{
			LOG.debug("Rendering " + variants.size() + " matched variants");
		}
		return view;
	}

	protected void checkForCartEntryLink(final List<ConfigurationVariantData> variants, final String configId) throws BusinessException
	{
		final String cartItemKey = getAbstractOrderEntryLinkStrategy().getCartEntryForDraftConfigId(configId);
		if (cartItemKey != null)
		{
			final Optional<OrderEntryData> orderEntry = super.getOrderEntry(cartItemKey, getCartFacade().getSessionCart());
			if (!orderEntry.isPresent())
			{
				throw new IllegalStateException("No cart entry found for:" + cartItemKey);
			}
			final Integer entryNumber = orderEntry.get().getEntryNumber();
			variants.stream().forEach(variant -> variant.setCartEntryNumber(entryNumber));
		}

	}

	protected String getViewName()
	{
		return AJAX_VIEW_NAME;
	}


	protected ConfigurationVariantFacade getVariantFacade()
	{
		return variantFacade;
	}


	/**
	 * @param variantFacade injects the facade object used by this controller for variant search
	 */
	public void setVariantFacade(final ConfigurationVariantFacade variantFacade)
	{
		this.variantFacade = variantFacade;
	}
}
