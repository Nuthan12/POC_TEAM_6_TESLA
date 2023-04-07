/**
 *
 */
package de.hybris.poct6.storefront.controllers.pages;

/**
 * @author Harshith.V
 *
 */




import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/tesla")
public class TeslaPageController extends AbstractPageController
{
	private static final String TESLA_CMS_PAGE = "teslaCMSPage";

	@RequestMapping(method = RequestMethod.GET)
	private String getTesla(final Model model) throws CMSItemNotFoundException
	{
		final ContentPageModel teslaCMSPage = getContentPageForLabelOrId(TESLA_CMS_PAGE);
		storeCmsPageInModel(model, teslaCMSPage);
		setUpMetaDataForContentPage(model, teslaCMSPage);
		return getViewForPage(model);
	}
}
