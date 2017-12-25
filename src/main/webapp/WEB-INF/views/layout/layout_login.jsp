<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>

<html>
	<head>
	    <meta charset="utf-8"/>
	    <title><s:message code="button.login" /> - <s:message code="project.name" /></title>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	    <meta name="description" content=""/>
	    <meta name="author" content=""/>
		<c:url value="/" var="url"></c:url>
	    <!-- Favicon-->
	    <link rel="shortcut icon" href="${url}theme/assets/images/icon/favicon.ico" type="image/x-icon"/>
	    <!-- Vendor CSS-->
	    <link href="${url}theme/libs/metronic-admin/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	
	    <!-- Theme CSS-->
	    <link rel="stylesheet" href="${url}theme/assets/css/admin.css"/>
	
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries-->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file://-->
	    <!--if lt IE 9
	    script(src='https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js')
	    
	    -->
	    <script type="text/javascript" src="${url}theme/libs/metronic-admin/global/plugins/jquery.min.js"></script>
	    <script type="text/javascript" src="${url}theme/libs/metronic-admin/global/plugins/bootstrap/js/bootstrap.min.js"></script>
	
	    <!-- Theme Script-->
  </head>
	<body class="login-page">
	   	<tiles:insertAttribute name="body" />
	    <!-- Vendor jQuery-->
	    
  </body>
</html>