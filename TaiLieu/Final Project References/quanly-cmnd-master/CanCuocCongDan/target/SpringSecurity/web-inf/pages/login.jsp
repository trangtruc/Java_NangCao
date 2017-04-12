<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ĐĂNG NHẬP</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	padding: 20px;
	margin: 40 auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>
<div >
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
</div>
<div class="container mt20" style="min-height:380px">
	<div class="mt80">
				<div class="text-center col-md-12">
					<div class="text-orange title">
						ĐĂNG NHẬP HỆ THỐNG CHỨNG MINH THƯ ĐIỆN TỬ
					</div>
				</div>
	</div>
	<div class="row col-md-12">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<div id="login-box">
				<c:if test="${not empty error}">
					<div class="error ml10">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg ml10">${msg}</div>
				</c:if>
		
				<form name='loginForm'
					action="<c:url value='/login' />" method='POST'>
		
					<table border="1" style="width:100%;background-color:white">
						<tr><th style="width:100%;">Đăng nhập</th></tr>
						<tr>
							<td><input type='text' name='USER_NAME' class='form-control' placeholder="Tài khoản (CMND/CCCD)"></td>
						</tr>
						<tr>
							<td><input type='password' name='PASSWORD' class='form-control' placeholder="Mật khẩu" /></td>
						</tr>
						<tr>
							<td><input name="submit" type="submit" value="Đăng nhập" class='btn btn-primary form-control' /></td>
						</tr>
						<tr>
							<td><a class='text-blue' href='' >Quên mật khẩu</a></td>
						</tr>
					</table>
		
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
		
				</form>
			</div>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="container">
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
</div>
</body>
</html>