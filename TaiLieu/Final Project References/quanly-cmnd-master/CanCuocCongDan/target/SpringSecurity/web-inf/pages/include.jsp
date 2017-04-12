<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
	<%@ include file="/resources/css/bootstrap.min.css" %>
	<%@ include file="/resources/css/style.css" %>
	<%@ include file="/resources/css/table.css" %>
</style>
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <spring:url value="/resources/js/jquery.min.js" var="jqueryJs" />
	<spring:url value="/resources/js/bootstrap.min.js" var="mainJs" />
	<spring:url value="/resources/js/scripts.js" var="Js" />
	<spring:url value="/resources/js/function.js" var="Fs" />
    <script src="${jqueryJs}"></script>
    <script src="${mainJs}"></script> 	
    <script src="${Js}"></script> 
    <script src="${Fs}"></script>
    