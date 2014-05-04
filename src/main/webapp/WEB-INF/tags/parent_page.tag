<!DOCTYPE html>
<%@tag description="Sort of a template that other jsps inherit" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="title" required="true"%>
<c:set var="rootUrl" value="${pageContext.request.contextPath}" scope="request"/>

<html>
	<head>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="<c:url value="/scripts/tracker.js" />"></script>
		<script src="<c:url value="/scripts/bootstrap.js" />"></script>		
		<link href="<c:url value="/styles/bootstrap.css" />" rel="stylesheet">	
		<link href="<c:url value="/styles/bootstrap-theme.css" />" rel="stylesheet">
		<link href="<c:url value="/styles/tracker.css" />" rel="stylesheet">	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Tracker - ${title}</title>
	</head>
	<body>
		<div id="header">
			<h2>Tracker Application</h2>
		
			<div id=menu>
				<a href="${rootUrl}/">Overview</a> |
				<a href="${rootUrl}/developers">Developers</a> |		
				<a href="${rootUrl}/stories">Stories</a> |
				<a href="${rootUrl}/bugs">Bugs</a> |
				<a href="${rootUrl}/planning">Project Planning</a> |
				<a href="${rootUrl}/about">About</a>		
			</div>
		</div>
		<jsp:doBody/>
	</body>
</html>