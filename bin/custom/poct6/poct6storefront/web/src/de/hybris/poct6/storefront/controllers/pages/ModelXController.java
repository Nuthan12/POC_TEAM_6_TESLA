/**
 *
 */
package de.hybris.poct6.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Santhosh.A
 *
 */
@Controller
@RequestMapping("/modelX")
public class ModelXController extends AbstractPageController
{
	private static final String MODELX_CMS_PAGE = "modelXCMSPage";

	@RequestMapping(method = RequestMethod.GET)
	private String getTesla(final Model model) throws CMSItemNotFoundException
	{
		final ContentPageModel teslaCMSPage = getContentPageForLabelOrId(MODELX_CMS_PAGE);
		storeCmsPageInModel(model, teslaCMSPage);
		setUpMetaDataForContentPage(model, teslaCMSPage);
		return getViewForPage(model);
	 }

}
