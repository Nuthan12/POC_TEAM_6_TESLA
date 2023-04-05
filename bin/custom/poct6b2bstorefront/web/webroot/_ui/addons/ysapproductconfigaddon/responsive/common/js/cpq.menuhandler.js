/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */




























CPQ.menuhandler = {
    hiddenSmallSize: "hidden-xs hidden-sm",
    mainFooter: ".main-footer",
    mainHeader: ".main-header",

    menuOpen: function (e) {
        var sideBar = $("#configSidebarSlot").parent();
        sideBar.removeClass(CPQ.menuhandler.hiddenSmallSize);
        var menu = $("#cpqMenuArea");
        menu.addClass(CPQ.menuhandler.hiddenSmallSize);
        var config = $("#configContentSlot").parent();
        config.addClass(CPQ.menuhandler.hiddenSmallSize);
        config.removeClass("col-xs-12 col-sm-12");
        // show footer header
        $(CPQ.menuhandler.mainFooter).removeClass(CPQ.menuhandler.hiddenSmallSize);
        $(CPQ.menuhandler.mainHeader).removeClass(CPQ.menuhandler.hiddenSmallSize);
        $(".product-details-header").removeClass(CPQ.menuhandler.hiddenSmallSize);
        // hide footer header
        $(CPQ.menuhandler.mainFooter).addClass(CPQ.menuhandler.hiddenSmallSize);
        $(CPQ.menuhandler.mainHeader).addClass(CPQ.menuhandler.hiddenSmallSize);
        $("#product-details-header").addClass(CPQ.menuhandler.hiddenSmallSize);
        CPQ.focushandler.focusRestore($(".cpq-menu-leaf-selected").attr("id"),
            true, $(window).height() / 4);
        e.preventDefault();
        e.stopPropagation();
    },

    menuRemove: function () {
        var sideBar = $("#configSidebarSlot").parent();
        sideBar.addClass(CPQ.menuhandler.hiddenSmallSize);
        var menu = $("#cpqMenuArea");
        menu.removeClass(CPQ.menuhandler.hiddenSmallSize);
        var config = $("#configContentSlot").parent();
        config.removeClass(CPQ.menuhandler.hiddenSmallSize);
        config.addClass("col-xs-12 col-sm-12");
        // show footer header
        $(CPQ.menuhandler.mainFooter).removeClass(CPQ.menuhandler.hiddenSmallSize);
        $(CPQ.menuhandler.mainHeader).removeClass(CPQ.menuhandler.hiddenSmallSize);
        $("#product-details-header").removeClass(CPQ.menuhandler.hiddenSmallSize);
    },

    menuClose: function (e) {
        CPQ.menuhandler.menuRemove();
        CPQ.focushandler.focusOnFirstInput();
        e.preventDefault();
        e.stopPropagation();
    },

    menuGroupToggle: function (e) {
        var menuNodeId = $(e.currentTarget).attr("id");
        var nodeId = CPQ.idhandler.getGroupIdFromMenuNodeId(menuNodeId);
        $("#autoExpand").val(false);
        var data = CPQ.config.getSerializedConfigForm("MENU_NAVIGATION", "",
            false, "", nodeId);
        CPQ.core.firePost(CPQ.config.doUpdatePost, [e, data]);
    },

    menuNavigation: function (e) {
        var menuNodeId = $(e.currentTarget).attr("id");
        var nodeId = CPQ.idhandler.getGroupIdFromMenuNodeId(menuNodeId);
        $("#groupIdToDisplay").val(nodeId);
        var data = CPQ.config.getSerializedConfigForm("MENU_NAVIGATION", "",
            true, nodeId);
        CPQ.core.firePost(CPQ.config.doUpdatePost, [e, data, "##first##"]);
        CPQ.menuhandler.menuRemove();
    }

};
