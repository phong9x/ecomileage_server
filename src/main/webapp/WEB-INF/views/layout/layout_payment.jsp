<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/include.jsp" %>

<html>
	<head>
	    <meta charset="utf-8"/>
	    <title><tiles:insertAttribute name="title" /></title>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	    <meta name="description" content=""/>
	    <meta name="author" content=""/>
	    <script type="text/javascript" src="${url}theme/libs/metronic-admin/global/plugins/jquery.min.js"></script>
	    <script type="text/javascript" src="${url}theme/libs/metronic-admin/global/plugins/bootstrap/js/bootstrap.min.js"></script>
	
	    <!-- Theme Script-->
  </head>
  
  <tiles:insertAttribute name="body" />
	    <!-- Vendor jQuery-->
</html>