<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<body>
	<h1>HTTP Status 403 - Access is denied</h1>
	<style>
<%@ include file="/resources/css/bootstrap.min.css" %>
</style>
	<c:choose>
		<c:when test="${empty username}">
			<h2 style="color: red;">Bạn Không có quyền thực hiện chức năng này</h2>
			<h2 style="color: red;">${thongBao }</h2>
		</c:when>
		<c:otherwise>
			<h2>Username : ${username} <br/>You do not have permission to access this page!</h2>
		</c:otherwise>
	</c:choose>
	<Button type="button" class="btn btn-primary" onclick="goBack()">Quay lại</Button>
	
	
	<script type="text/javascript">
	function goBack() {
	    window.history.back();
	}
	</script>
</body>
</html>