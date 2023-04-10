<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="https://hybris.com/tld/cmstags" %>

<template:page pageTitle="${pageTitle}" >
<cms:pageSlot position="textParagraph" var="feature" element="div" class="testBannerParagraph">
<cms:component component="${feature}"/>
</cms:pageSlot>

</template:page>

