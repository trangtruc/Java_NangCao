<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
	<%@ include file="/resources/css/bootstrap.min.css" %>
	<%@ include file="/resources/css/style.css" %>
	<%@ include file="/resources/css/table.css" %>
	<%@ include file="/resources/css/theme.css" %>
	<%@ include file="/resources/css/morris.css" %>
	<%@ include file="/resources/css/jquery.jqplot.min.css" %>
</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <spring:url value="/resources/js/jquery.min.js" var="jqueryJs" />
	<spring:url value="/resources/js/bootstrap.min.js" var="mainJs" />
	<spring:url value="/resources/js/scripts.js" var="Js" />
	<spring:url value="/resources/js/function.js" var="Fs" />
	<spring:url value="/resources/js/morris.min.js" var="Cms" />
	<spring:url value="/resources/js/morris.js" var="Cs" />
	<spring:url value="/resources/js/jquery.jqplot.min.js" var="Jqps" />
	<spring:url value="/resources/js/jquery.canvasjs.min.js" var="Cvs" />
	<spring:url value="/resources/js/barcode.js" var="BCs" />
	<spring:url value="/resources/js/raphael-min.js" var="Rs" />
	<spring:url value="/resources/js/scrollReveal.js" var="SRs" />
    <script src="${jqueryJs}"></script>
    <script src="${mainJs}"></script> 	
    <script src="${Js}"></script> 
    <script src="${Fs}"></script>
    <script src="${Cms}"></script>
    <script src="${Cs}"></script>
    <script src="${Rs}"></script>
    <script src="${SRs}"></script>
    <script src="${Jqps}"></script>
    <script src="${Cvs}"></script>
     <script src="${BCs}"></script>
    