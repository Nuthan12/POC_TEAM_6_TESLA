/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */




























CPQ.analyticshandler = {
    cpqLabelDefault : ".cpq-label-default",
    mobileTooltip: ".mobileTooltip",
    titleData: 'title-data',


    init : function() {
        CPQ.analyticshandler.USER_CAN_TOUCH = (('ontouchstart' in window) || (navigator.msMaxTouchPoints > 0));
        window.addEventListener('mouseover', function onFirstHover() {
            CPQ.analyticshandler.USER_CAN_HOVER = true;
            window.removeEventListener('mouseover', onFirstHover, false);
        }, false);
    },

    storeAnalyticsState : function() {
        CPQ.uihandler.storeState('.cpq-csticValueAnalytics');
    },

    restoreAnalyticsState : function(analyticState) {
        CPQ.uihandler.restoreState(analyticState, function(item) {
            var div = $(CPQ.core.encodeId(item.id));
            if (div.length > 0) {
                div.html(item.html);
                div.removeClass('cpq-csticValueAnalyticsTemplate').addClass(
                        'cpq-csticValueAnalytics');
            }
        });
    },

    getAnalytics : function() {
        if ($("#analyticsEnabled").text() === "true") {
            var ret = CPQ.core.prepareAsyncServiceCall();
            $.post(CPQ.core.getAnalyticsUrl(), ret.data, function(response) {
                if (CPQ.core.ajaxRunCounterAsyncServices === 1) {
                    CPQ.core.ajaxServerStopTimeAsyncServices = new Date()
                            .getTime();
                }
                if (ret.configState === CPQ.core.configState) {
                    CPQ.analyticshandler.insertAnalyticValues(response);
                }
                CPQ.core.ajaxRunCounterAsyncServices--;
                if (CPQ.core.ajaxRunCounterAsyncServices === 0) {
                    CPQ.core.ajaxStopTimeAsyncServices = new Date().getTime();
                }
            });
        }
    },

    replaceAnalyticValues : function(obj, uiKey, analyticValue) {
        var uiValueKey = uiKey + '.' + analyticValue.csticValueName;
        var analyticsDiv = $(CPQ.core.encodeId(uiValueKey + '.analytics'));
        if (analyticsDiv.length <= 0) {
            return;
        }
        var popularityTooltipSpan = $(CPQ.core.encodeId(uiValueKey
                + '.popularityTooltip'));
        var popularityInPercentSpan = $(CPQ.core.encodeId(uiValueKey
                + '.popularityInPercent'));
        var popularityAriaText = $(CPQ.core.encodeId(uiValueKey
                + '.popularityAriaText'));

        popularityTooltipSpan.attr("title", (popularityTooltipSpan
                .attr("title").replace(obj.popularityInPercent.placeHolder,
                analyticValue.popularityInPercent)));
        popularityInPercentSpan.html(popularityInPercentSpan.html().replace(
                obj.popularityInPercent.placeHolder,
                analyticValue.popularityInPercent));
        popularityAriaText.html(popularityAriaText.html().replace(
                obj.popularityInPercent.placeHolder,
                analyticValue.popularityInPercent));
        analyticsDiv.removeClass('cpq-csticValueAnalyticsTemplate').addClass(
                'cpq-csticValueAnalytics');
        if (popularityAriaText.html()
                .startsWith(popularityInPercentSpan.html())) {
            popularityAriaText.html(popularityAriaText.html().replace(
                    popularityInPercentSpan.html(), ""));
        }
    },

    insertAnalyticValues : function(reposnse) {
        var obj = $.parseJSON(reposnse);
        for (var ii = 0, lenII = obj.analyticCstics.length; ii < lenII; ++ii) {
            var analyticCstic = obj.analyticCstics[ii];
            var uiKey = analyticCstic.csticUiKey;
            if ($(CPQ.core.encodeId(uiKey + '.key')).length === 0) {
                continue;
            }

            for (var jj = 0, lenJJ = analyticCstic.analyticValues.length; jj < lenJJ; ++jj) {
                CPQ.analyticshandler.replaceAnalyticValues(obj, uiKey,
                        analyticCstic.analyticValues[jj]);
            }
        }
        CPQ.imagehandler.makeLabelsUnderImagesSameHeight('.cpq-csticValue',
                '.cpq-csticValueImageLabel', true);
    },

    registerAnalyticsTooltip : function() {
        $(CPQ.analyticshandler.cpqLabelDefault).click(
                function(e) {
                    var mobileTooltip = $(this).find(CPQ.analyticshandler.mobileTooltip);
                    if (!mobileTooltip.length) {
                        if (!CPQ.analyticshandler.USER_CAN_HOVER
                                || CPQ.analyticshandler.USER_CAN_TOUCH) {
                            $(CPQ.analyticshandler.mobileTooltip).remove();
                            CPQ.analyticshandler.removeDefaultToolTip();
                            var style = "";
                            // frist image container
                            var positionToElem = $(this).closest(
                                    ".cpq-csticValue").find(
                                    ".cpq-cstic-value-with-image");

                            if (positionToElem[0]) {
                                // image container clicked
                                var actualElem = $(this).closest(
                                        ".cpq-cstic-value-with-image");
                                var left = -15 + actualElem.offset().left
                                        - positionToElem.offset().left;
                                var top = 175 + actualElem.offset().top
                                        - positionToElem.offset().top;
                                style = ' style="left:' + left + 'px; top:'
                                        + top + 'px"';
                            }
                            var span = document.createElement("span");
                            span.style = style;
                            span.classList.add("mobileTooltip");
                            span.innerText = $(this).attr("title-data");
                            $(this).append(span);
                            e.preventDefault();
                            e.stopPropagation();
                        }
                    } else {
                        mobileTooltip.remove();
                        e.preventDefault();
                        e.stopPropagation();
                    }
                });
        $(document).click(function(e) {
            $(CPQ.analyticshandler.mobileTooltip).remove();
        });
        $(CPQ.analyticshandler.cpqLabelDefault).mouseover(function(e) {
            CPQ.analyticshandler.restoreDefaultToolTip();
            $(CPQ.analyticshandler.mobileTooltip).remove();
        });
    },

    removeDefaultToolTip : function() {
        $(CPQ.analyticshandler.cpqLabelDefault).each(function() {
            if (!$(this).attr(CPQ.analyticshandler.titleData)) {
                $(this).attr(CPQ.analyticshandler.titleData, $(this).attr("title"));
                $(this).removeAttr("title");
            }
        });
    },

    restoreDefaultToolTip : function() {
        $(CPQ.analyticshandler.cpqLabelDefault).each(function() {
            if (!$(this).attr("title")) {
                $(this).attr("title", $(this).attr(CPQ.analyticshandler.titleData));
                $(this).removeAttr(CPQ.analyticshandler.titleData);
            }
        });
    }

};
