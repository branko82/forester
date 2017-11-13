<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
	<title>forester</title>
	<spring:url value="/resources/css" var="cssResources"></spring:url>
	<spring:url value="/resources/images" var="imagesResources"></spring:url>
    <link rel="shortcut icon" href="${imagesResources}/forester.png"></link>
	<link type="text/css" rel="stylesheet" href="${cssResources}/forester.css"></link>
</head>

<body class="bodyContent">
	<table cellpadding="10" cellspacing="0">
		<!-- ============ HEADER SECTION ============== -->
		<%@ include file ="header.jsp" %>
		<tr class="central">
			<!-- ============ LEFT COLUMN (MENU) ============== -->
			<%@ include file ="left.jsp" %>
			<!-- ============ RIGHT COLUMN (CONTENT) ============== -->
			<%@ include file ="content.jsp" %>
		</tr>
		<!-- ============ FOOTER SECTION ============== -->
		<%@ include file ="footer.jsp" %>
	</table>
</body>
</html>