<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true"%>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true"%>
<%@ attribute name="pageCss" required="false" fragment="true"%>
<%@ attribute name="pageScripts" required="false" fragment="true"%>
<%@ attribute name="hideHeaderLinks" required="false"%>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/responsive/common/footer"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<template:master pageTitle="${pageTitle}">

	<jsp:attribute name="pageCss">
		<jsp:invoke fragment="pageCss" />
	</jsp:attribute>

	<jsp:attribute name="pageScripts">
		<jsp:invoke fragment="pageScripts" />
	</jsp:attribute>

	<jsp:body>
		<div class="branding-mobile hidden-md hidden-lg">
			<div class="js-mobile-logo"><a href="https://powertools.local:9002/poct6b2bstorefront/powertools/en/USD/"><img title="TESLA" alt="TESLA" src="https://www.google.com/url?sa=i&url=https%3A%2F%2F1000logos.net%2Ftesla-logo%2F&psig=AOvVaw0fCitXzwcK-sq4Hd1dB4g4&ust=1680025723086000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCPjdttfV_P0CFQAAAAAdAAAAABAE"></a>
				<%--populated by JS acc.navigation--%>
			</div>
		</div>
	<main data-currency-iso-code="${fn:escapeXml(currentCurrency.isocode)}">
			<spring:theme code="text.skipToContent" var="skipToContent" />
			<a href="#skip-to-content" class="skiptocontent" data-role="none">${fn:escapeXml(skipToContent)}</a>
			<spring:theme code="text.skipToNavigation" var="skipToNavigation" />
			<a href="#skiptonavigation" class="skiptonavigation" data-role="none">${fn:escapeXml(skipToNavigation)}</a>


			<header:header hideHeaderLinks="${hideHeaderLinks}" />


			
			
			<a id="skip-to-content"></a>
		
			<div class="main__inner-wrapper">
				<common:globalMessages />
				<cart:cartRestoration />
				<jsp:doBody />
			</div>

			<footer:footer />
		</main>
	
		
		
		<!DOCTYPE html>
<!-- <html>
  <head>
    <title>Example Form</title>
    <style>
      /* Internal CSS */
      form {
        margin: 20px;
        padding: 20px;
        border: 1px solid black;
      }
      label {
        display: block;
        margin-bottom: 10px;
        font-weight: bold;
      }
      input[type="text"], textarea {
        width: 100%;
        padding: 5px;
        margin-bottom: 10px;
        border-radius: 5px;
        border: 1px solid #ccc;
      }
      input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        border-radius: 5px;
        cursor: pointer;
      }
    </style>
  </head>
  <body>
    <form>
      <label for="name">Name:</label>
      <input type="text" id="name" name="name" required>
      
      <label for="email">Email:</label>
      <input type="text" id="email" name="email" required>
      
      <label for="message">Message:</label>
      <textarea id="message" name="message" required></textarea>
      
      <input type="submit" value="Submit">
    </form>
  </body>
</html>
		 -->

	</jsp:body>

</template:master>
