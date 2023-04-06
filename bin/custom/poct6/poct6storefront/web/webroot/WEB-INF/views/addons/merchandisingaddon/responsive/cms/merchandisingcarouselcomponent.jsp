<%--

    Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.

--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<div id="merchcarouselComponent${fn:escapeXml(componentID)}"
	data-currency="${fn:escapeXml(currency)}"
	data-strategy="${fn:escapeXml(strategy)}"
	data-name="${fn:escapeXml(name)}"
	data-numbertodisplay="${fn:escapeXml(numberToDisplay)}"
	data-scroll="${fn:escapeXml(scroll)}"
	data-title="${fn:escapeXml(title)}"
	data-backgroundcolour="${fn:escapeXml(backgroundColour)}"
	data-textcolour="${fn:escapeXml(textColour)}"
	data-url="${fn:escapeXml(serviceUrl)}"
	data-viewportpercentage="${fn:escapeXml(viewportPercentage)}"
	>
</div>
<script>
	(function () {
		'use strict';
		var current = {
			el: document.querySelector('#merchcarouselComponent${ycommerce:encodeJavaScript(componentID)}')
		};
		window.__merchcarousels = window.__merchcarousels || {};
		window.__merchcarousels[current.el.id] = current;

		if (window.__merchcarousels.CarouselComponent) {
			window.__merchcarousels.CarouselComponent.init();
		}
	})();
</script>

