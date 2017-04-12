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
#login-box {
	padding: 20px;
	margin: 40 auto;
	background: #fff;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
</style>
<style>
table tr:hover {
    background-color: white;
}
table tr:nth-child(even):hover {
	background-color: white;
}
table td{
	padding:5px;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>
<div class="container">
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
</div>
<div class="container bg-content" style="min-height:280px">
	<div class="row mt10">
		<div class="text-center col-md-12">
			<div class="text-orange title">
				ĐĂNG NHẬP HỆ THỐNG QUẢN LÝ THÔNG TIN CÔNG DÂN
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
				<div class="error display-none" id="checkLogin"></div>
				<form name='loginForm'
					action="<c:url value='/login' />" method='POST' onsubmit="return checkLogin();">
		
					<table border="0" style="width:100%;">
						<tr>
							<td><span class="fa fa-user fa-2x" style="color:#d88835;"></span></td>
							<td style="width:90%"><input type='text' name='USER_NAME' id="USER_NAME" class='form-control' placeholder="Tài khoản (CMND/CCCD)"></td>
						</tr>
						<tr>
							<td><span class="fa fa-lock fa-2x" style="color:#d88835;"></span></td>
							<td><input type='password' name='PASSWORD' id="PASSWORD" class='form-control' placeholder="Mật khẩu" /></td>
						</tr>
						<tr>
							<td colspan=2><input name="submit" type="submit" value="Đăng nhập" class='btn btn-primary form-control' /></td>
						</tr>
						<tr>
							<td colspan=2 class='text-center'><a href='lam-lai-mat-khau'>Quên mật khẩu</a></td>
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
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>

<script>
	function checkLogin(){
		dong("checkLogin");
		if($("#USER_NAME").val().length < 5){
			mo("checkLogin");
			$("#checkLogin").html("Độ dài tài khoản không hợp lệ");
			return false;
		}
		if($("#PASSWORD").val().length < 1){
			mo("checkLogin");
			$("#checkLogin").html("Độ dài mật khẩu không hợp lệ");
			return false;
		}
		MH5();
	}
	function MH5(){
		var mk = $("#PASSWORD").val();
		mk = md5(mk);
		var data = "matKhau="+mk;
		$.ajax({url: 'set-session-mat-khau',
			type: 'GET',
			data: data,
		  	success: function(result){
		  		
	    }});
		$("#PASSWORD").val(md5(mk));
	}
</script>
</body>
</html>