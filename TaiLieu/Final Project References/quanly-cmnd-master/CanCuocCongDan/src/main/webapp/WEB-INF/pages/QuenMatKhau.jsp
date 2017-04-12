<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>LÀM LẠI MẬT KHẨU</title>
	
</head>
<body>
<div class="container">
	<jsp:include page="header.jsp"></jsp:include>
</div>

<div class="container">
	<div class="row bg-content" style="min-height:280px;">
		<div class="row mt20">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<div class="radius-01 bg-white text-center">
					<div class="title-small">Làm lại mật khẩu</div>
					<c:if test="${not empty error}">
						<div class="error ml5">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="msg ml5">${msg}</div>
					</c:if>
					<form action="lam-lai-mat-khau?${_csrf.parameterName}=${_csrf.token}" method='POST' onsubmit="return MH5();" >
							<table border="0" style="width:100%;" id="form-tai-khoan">
								<tr>
									<td colspan=2><div id="errorTaiKhoan" class="error ml5 display-none"></div></td>
								</tr>
								
								<tr>
									<td><span class="fa fa-user fa-2x" style="color:#d88835;"></span></td>
									<td colspan=2><input type='text' name='USER_NAME' id="USER_NAME" class='form-control' placeholder="Nhập tài khoản ..." /></td>
								</tr>
								
								<tr>
									<td colspan=3>
									<button type="button"
									 onclick="kiemTraTaiKhoan();"
									 class='btn btn-primary form-control'>
									 Tiếp theo
									  <span class="fa fa-arrow-right"></span>
									  </button>
									 </td>
								</tr>
								<tr>
									<td colspan=3><a href='login'>Đăng nhập</a></td>
								</tr>
							</table>
							<table border="0" style="width:100%;" id="form-xac-thuc-mail" class="display-none">
								<tr>
									<td colspan=3>
										<font color="gray">Mã OTP đã được gửi đến Email của Tài khoản, mời bạn nhập mã OTP</font>
									</td>
								</tr>
								<tr>
									<td>
										<span class="fa fa-phone fa-2x" style="color:#d88835;"></span>
										<input type="hidden" id="email" value="" />
									</td>
									<td style="width:90%">
											<input type='text' name='OTP' id='OTP' class='form-control' placeholder="Nhập mã OTP đã nhận trong Email">		
									</td>
									<td><a onclick="sendMail();" title="Gửi lại"><span id="gui-lai" class="fa fa-refresh fa-fw"></span></a></td>
								</tr>
								
								<tr>
									<td colspan=3><input name="submit" type="submit"  value="Làm lại mật khẩu" class='btn btn-primary form-control' /></td>
								</tr>
								<tr>
									<td colspan=3><a href='login'>Đăng nhập</a></td>
								</tr>
							</table>
							<div id="errorEmail" class="error ml5 display-none"></div>
						
					</form>
				</div>
			</div>
		</div>
		</div>
		<div class="row">
				<jsp:include page="footer.jsp"></jsp:include>
		</div>
</div>
<script>
	function MH5(){
		if(!checkSubmit()){
			return false;
		}
		var mk = $("#matKhauCu").val();
		var mk1 = $("#matKhauMoi").val();
		var mk2 = $("#xacNhanMatKhauMoi").val();
		$("#matKhauCu").val(md5(mk));
		$("#matKhauMoi").val(md5(mk1));
		$("#xacNhanMatKhauMoi").val(md5(mk2))
	}
	function checkSubmit(){
		if($("#matKhauCu").val().length < 1){
			alert("Chưa điền mật khẩu cũ");
			return false;
		}
		if($("#matKhauMoi").val().length < 1){
			alert("Chưa điền mật khẩu mới");
			return false;
		}
		if($("#xacNhanMatKhauMoi").val().length < 1){
			alert("Chưa xác nhận mật khẩu mới");
			return false;
		}
		return true;
	}
	function kiemTraTaiKhoan(){
		dongErrorTaiKhoan();
		var username = $("#USER_NAME").val();
		if(username.length != 12){
			$("#errorTaiKhoan").html("Tài khoản là số CMND/CCCD, phải có 12 ký tự");
			moErrorTaiKhoan();
			return false;
		}
		var data = "USER_NAME="+username;
		$.ajax({url: 'tai-khoan-ton-tai',
			type: 'GET',
			data: data,
		  	success: function(result){
		  		if(result == "0"){
		  			moErrorTaiKhoan();
		  			$("#errorTaiKhoan").html("Tài khoản không tồn tại, hãy kiểm tra lại");
		  		} else {
		  			if(result != "-1"){
		  				$("#email").val(result);
			  			dongErrorTaiKhoan();
			  			openFormXacThucEmail();
			  		} else {
			  			$("#form-tai-khoan").addClass("display-none");
			  			$("#errorEmail").html("Tài khoản này chưa đăng ký Email, để làm lại mật khẩu bạn vui lòng đến cơ quan làm việc");
			  			$("#errorEmail").removeClass("display-none");
			  			$("#errorEmail").addClass("display-block");
			  		}
		  		}
	    }});
	}
	function openFormXacThucEmail(){
		$("#form-tai-khoan").addClass("display-none");
		$("#form-xac-thuc-mail").removeClass("display-none");
		var data = "mailNhan="+$("#email").val();
		$.ajax({url: 'send-otp',
			type: 'GET',
			data: data,
		  	success: function(result){
		  		
	    }});
	}
	function sendMail(){
		$("#gui-lai").addClass("fa-spin");
		var data = "mailNhan="+$("#email").val();
		$.ajax({url: 'send-otp',
			type: 'GET',
			data: data,
		  	success: function(result){
		  		$("#gui-lai").removeClass("fa-spin");
	    }});
	}
	function dongErrorTaiKhoan(){
		$("#errorTaiKhoan").addClass("display-none");
		$("#errorTaiKhoan").removeClass("display-block");
	}
	function moErrorTaiKhoan(){
		$("#errorTaiKhoan").addClass("display-block");
		$("#errorTaiKhoan").removeClass("display-none");
	}
</script>
</body>
</html>
<style>
	td a{
		color:blue;
	}
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