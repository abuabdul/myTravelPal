<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/siteTags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<tiles:insertAttribute name="metaTags"/>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<tiles:insertAttribute name="siteHeaderInclude"/>
</head>
<body>
	<tiles:insertAttribute name="topmenu"/>
	<tiles:insertAttribute name="body"/>
	<tiles:insertAttribute name="siteFooterInclude"/>
</body>
</html>
