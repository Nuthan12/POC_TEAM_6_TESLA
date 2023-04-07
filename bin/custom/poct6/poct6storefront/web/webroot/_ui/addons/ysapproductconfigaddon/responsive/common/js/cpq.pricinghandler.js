/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */


























CPQ.pricinghandler = {
    cpqPricingSummaryError: ".cpq-pricing-summary-error",

    getPricing : function() {
        if ($("#asyncPricingMode").text() === "true") {
            var ret = CPQ.core.prepareAsyncServiceCall();
            $.post(CPQ.core.getPricingUrl(), ret.data, function(response) {
					 var sanitized = ACC.sanitizer.sanitize(response);
                if (CPQ.core.ajaxRunCounterAsyncServices === 1) {
                    CPQ.core.ajaxServerStopTimeAsyncServices = new Date()
                            .getTime();
                }
                if (ret.configState === CPQ.core.configState) {
                    CPQ.pricinghandler.updateValuePrices(sanitized);
                    CPQ.pricinghandler.updatePriceSummary(sanitized);
                    CPQ.config.generateAriaDescribedBy();
                }
                CPQ.core.ajaxRunCounterAsyncServices--;
                if (CPQ.core.ajaxRunCounterAsyncServices === 0) {
                    CPQ.core.ajaxStopTimeAsyncServices = new Date().getTime();
                }
            });
        }
    },

    updatePriceSummary : function(data) {
        clearTimeout(CPQ.core.priceSummaryDropHandle);
        var obj = $.parseJSON(data);
        if (obj.pricingError) {
            $(CPQ.pricinghandler.cpqPricingSummaryError).slideDown(300, function() {
                $(CPQ.pricinghandler.cpqPricingSummaryError).attr('role', 'alert');
            });
        } else if ($(CPQ.pricinghandler.cpqPricingSummaryError).is(":visible")) {
            $(CPQ.pricinghandler.cpqPricingSummaryError).removeAttr('role');
            $(CPQ.pricinghandler.cpqPricingSummaryError).slideUp(300);
        }
        $("#basePriceValue").html(obj.basePriceValue);
        $("#basePriceValue").attr("title", obj.basePriceValue);
        $("#selectedOptionsValue").html(obj.selectedOptionsValue);
        $("#selectedOptionsValue").attr("title", obj.selectedOptionsValue);
        $("#currentTotalValue").html(obj.currentTotalValue);
        $("#currentTotalValue").attr("title", obj.currentTotalValue);
        if (obj.currentTotalSavings !== '-') {
            $("#savingsValue").html(obj.currentTotalSavings);
            $("#savingsValue").attr("title", obj.currentTotalSavings);
            $("#savings").show();
        } else {
            $("#savings").hide();
        }
    },

    updateValuePrices : function(data) {
        clearTimeout(CPQ.core.valuePriceDropHandle);
        clearTimeout(CPQ.core.valuePriceDropdownDropHandle);
        var obj = $.parseJSON(data);
        for (var i = 0; i < obj.valuePricesArray.length; i++) {
            var cstic = obj.valuePricesArray[i];
            var csticKey = cstic.csticKey;
            var showDeltaPrices = cstic.showDeltaPrices;
            var selectedValue = $(CPQ.core.encodeId(csticKey + ".ddlb")).val();
            for (var j = 0; j < cstic.csticValuesArray.length; j++) {
                var csticValue = cstic.csticValuesArray[j];
                var csticValueKey = csticValue.csticValueKey;
                var idHeader = csticKey + "." + csticValueKey;
                var csticValueOption = idHeader + ".option";
                var csticValueLabel = idHeader + ".label";
                var csticCheckboxId = csticKey + ".checkBox";
                var elementId = idHeader + ".valuePrice";
                var elementCheckBoxId = csticKey + ".valuePrice";
                var strikeThroughElementId = idHeader + ".strikeThroughPrice";
                var strikeThroughElementCheckBoxId = csticKey
                        + ".strikeThroughPrice";
                var conflictCsticValueOption = "conflict." + csticValueOption;
                var conflictCsticValueLabel = 'conflict.' + csticValueLabel;
                var conflictElementId = 'conflict.' + elementId;
                var conflictStrikeThroughElementId = 'conflict.'
                        + strikeThroughElementId;

                var valuePriceStyle = "cpq-csticValueDeltaPrice";
                if (csticValue.valueObsoletePrice !== "" && !showDeltaPrices) {
                    valuePriceStyle += " cpq-price-savings";
                }
                var obsoletePriceStyle = "cpq-csticValueStrikeThroughPrice";
                if (showDeltaPrices) {
                    obsoletePriceStyle += " cpq-price-savings";
                }

                CPQ.pricinghandler.setValuePrice(csticValueLabel, $(CPQ.core
                        .encodeId(csticValueLabel)), elementId,
                        valuePriceStyle, csticValue.valuePrice, true);
                CPQ.pricinghandler.setValuePrice(csticCheckboxId, $(CPQ.core
                        .encodeId(csticCheckboxId)), elementCheckBoxId,
                        valuePriceStyle, csticValue.valuePrice, true);

                CPQ.pricinghandler.setValuePrice(csticValueLabel, $(CPQ.core
                        .encodeId(csticValueLabel)), strikeThroughElementId,
                        obsoletePriceStyle, csticValue.valueObsoletePrice,
                        !showDeltaPrices);
                CPQ.pricinghandler.setValuePrice(csticCheckboxId, $(CPQ.core
                        .encodeId(csticCheckboxId)),
                        strikeThroughElementCheckBoxId, obsoletePriceStyle,
                        csticValue.valueObsoletePrice, !showDeltaPrices);

                CPQ.pricinghandler.setValuePriceForDropDown($(CPQ.core
                        .encodeId(csticValueOption)), csticValue.valuePrice,
                        csticValue.valueObsoletePrice, selectedValue,
                        showDeltaPrices);
                CPQ.pricinghandler.setValueStyleForDropDown($(CPQ.core
                        .encodeId(csticValueOption)),
                        csticValue.valueObsoletePrice);

                CPQ.pricinghandler.setValuePriceForDropDown($(CPQ.core
                        .encodeId(conflictCsticValueOption)),
                        csticValue.valuePrice, csticValue.valueObsoletePrice,
                        selectedValue, showDeltaPrices);
                CPQ.pricinghandler.setValueStyleForDropDown($(CPQ.core
                        .encodeId(conflictCsticValueOption)),
                        csticValue.valueObsoletePrice);

                CPQ.pricinghandler.setValuePrice(conflictCsticValueLabel,
                        $(CPQ.core.encodeId(conflictCsticValueLabel)),
                        conflictElementId, valuePriceStyle,
                        csticValue.valuePrice, true);
                CPQ.pricinghandler.setValuePrice(conflictCsticValueLabel,
                        $(CPQ.core.encodeId(conflictCsticValueLabel)),
                        conflictStrikeThroughElementId, obsoletePriceStyle,
                        csticValue.valueObsoletePrice, !showDeltaPrices);
            }
        }
        $(".cpq-ddlb").ddlbselectmenu().each(function() {
            $(this).ddlbselectmenu("refresh");
        });
    },

    setValuePriceForDropDown : function(csticValue, valuePrice,
            valueObsoletePrice, selectedValue, showDeltaPrices) {
        if (csticValue.length > 0) {
            valuePrice = CPQ.pricinghandler.addPlusIfRequired(valuePrice);
            if (!showDeltaPrices) {
                valueObsoletePrice = CPQ.pricinghandler
                        .addPlusIfRequired(valueObsoletePrice);
            }
            csticValue.attr("data-price", valuePrice);
            csticValue.attr("data-obsolete-price", valueObsoletePrice);
        }
    },

    setValueStyleForDropDown : function(csticValue, valueObsoletePrice) {
        if (csticValue.length > 0 && valueObsoletePrice.length > 0) {
            csticValue.attr("data-promostyle", "cpq-promo-applied");
        } else if (csticValue.length > 0) {
            csticValue.attr("data-promostyle", "");
        }
    },

    addPlusIfRequired : function(price) {
        if (!price.startsWith("-") && price.match(/\d+/g) != null) {
            price = "+" + price;
        }
        return price;
    },

    setValuePrice : function(csticValueLabel, csticValueByLabel, elementId,
            csticValuePriceStyle, valuePrice, addPlus) {
        if (csticValueByLabel.length > 0) {
            if (addPlus) {
                valuePrice = CPQ.pricinghandler.addPlusIfRequired(valuePrice);
            }
            var html = "<div id='" + elementId + "' class=' "
                    + csticValuePriceStyle + " cpq-csticValueLabel' title='"
                    + valuePrice + "'>" + valuePrice + "</div>";
            // if value price is already present it needs to be removed
            $(CPQ.core.encodeId(elementId)).remove();
            var label = $(CPQ.core.encodeId(csticValueLabel));
            if (label.next().length === 0) {
                label.after('<div class="cpq-valuePrices"/>');
            }
            label.next().append(html);
        }
    },

    storePricingState : function() {
        var state = {};
        var saveCallBack = function(obj, item) {
            item.labelId = obj.parent().children('label').attr('id');
            item.html = obj.wrap('<p/>').parent().html();
        };
        state.valuePriceState = CPQ.uihandler.storeState('.cpq-valuePrices',
                saveCallBack);
        state.pricingSummary = CPQ.uihandler
                .storeState('#basePriceValue, #currentTotalValue, #savingsValue, #selectedOptionsValue');
        return state;
    },

    restoreValuePricingState : function(state) {
        if (state.valuePriceState) {
            var restoreCallBack = function(item) {
                var label = $(CPQ.core.encodeId(item.labelId));
                label.after(item.html);
            };

            CPQ.uihandler.restoreState(state.valuePriceState, restoreCallBack);
            CPQ.core.valuePriceDropHandle = setTimeout(function() {
                $('.cpq-valuePrices').remove();
            }, 250);
        }
    },

    restorePriceSummaryState : function(state) {
        CPQ.uihandler.restoreState(state.pricingSummary);
        CPQ.core.priceSummaryDropHandle = setTimeout(
                function() {
                    var selector = "#basePriceValue, #currentTotalValue, #savingsValue, #selectedOptionsValue";
                    $(selector).text('-');
                    $(selector).prop('title', '-');
                }, 250);
    }
};
