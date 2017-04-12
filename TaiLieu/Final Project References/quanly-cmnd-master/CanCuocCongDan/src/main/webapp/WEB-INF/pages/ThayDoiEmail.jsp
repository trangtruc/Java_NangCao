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
	<title>THAY ĐỔI EMAIL - ${ssHoTen }</title>
	
</head>
<body>
<c:if test="${ssTaiKhoan == null}">
	<script>
		window.location = "login";
	</script>
</c:if>
<div class="container">
	<jsp:include page="header.jsp"></jsp:include>
</div>

<div class="container">
	<div class="row bg-content" style="min-height:280px;">
		<div class="row mt20">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<div class="radius-01 bg-white text-center">
					<div class="title-small" id="title-email">Khai báo Email mới</div>
					<c:if test="${not empty error}">
						<div class="error ml5">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="msg ml5">${msg}</div>
					</c:if>
					<form action="thay-doi-email?${_csrf.parameterName}=${_csrf.token}" method='POST' onsubmit="return checkSubmit();">
						<table border="0" style="width:100%;"  id="form-cap-nhat">
							<tr>
								<td colspan=2>
									Email này dùng để xác thực các yêu cầu của người dùng trên hệ thống
								</td>
							</tr>
							<tr>
								<td>Email đang dùng</td>
								<td><label>${email }</label>
									<input type=hidden id="emailDangDung" value="${email }" />
								</td>
							</tr>
							<tr>
								<td> Email Mới</td>
								<td><input type='email' name='email' id="email" class='form-control' placeholder="Nhập Email" value="" /></td>
							</tr>
							<tr>
								<td colspan=2><button type="button" class="btn btn-primary form-control" onclick="openFormXacThucEmail();">Tiếp theo <span class="fa fa-arrow-right"></span></button></td>
							</tr>
							<tr>
								<td colspan=2><a href='thong-tin-tai-khoan'>Quay về trang tài khoản</a></td>
							</tr>
						</table>
						<table border="0" style="width:100%;" id="form-xac-thuc-mail" class="display-none">
							<tr>
								<td colspan=3>
									<font color=gray>Mã OTP đã được gửi đến Email <font color="" id='txtEmail'></font>, hãy đăng nhập Email để kiểm tra.</font>
								</td>
							</tr>
							<tr>
								<td><span class="fa fa-key fa-2x" style="color:#d88835;"></span></td>
								<td style="width:90%">
										<input type='text' name='OTPXacThuc' id='OTPXacThuc' class='form-control' placeholder="Nhập mã OTP">		
								</td>
								<td><a onclick="sendMail();" title="Gửi lại"><span id="gui-lai" class="fa fa-refresh fa-fw"></span></a></td>
							</tr>
							<tr>
								<td colspan=3><button type="button" class="btn btn-primary form-control" onclick="xuLyXacThuc();">Tiếp theo <span class="fa fa-arrow-right"></span></button></td>
							</tr>
							<tr>
								<td colspan=3><a href='thong-tin-tai-khoan'>Quay về trang tài khoản</a></td>
							</tr>
						</table>
						<table border="0" style="width:100%;" id="form-xac-nhan-mail" class="display-none" >
							<tr>
								<td colspan=3>
									<font color=gray>Mã OTP đã được gửi đến Email <font color="">${email }</font>, hãy đăng nhập Email để kiểm tra.</font>
								</td>
							</tr>
							<tr>
								<td><span class="fa fa-key fa-2x" style="color:#d88835;"></span></td>
								<td style="width:90%">
										<input type='text' name='OTPXacNhan' id='OTPXacNhan' class='form-control' placeholder="Nhập mã OTP">		
								</td>
								<td><a onclick="sendMail();" title="Gửi lại"><span id="gui-lai" class="fa fa-refresh fa-fw"></span></a></td>
							</tr>
							<tr>
								<td><span class="fa fa-unlock fa-2x" style="color:#d88835;"></span></td>
								<td colspan=2><input type='password' name='matKhau' id="matKhau" class='form-control' placeholder="Nhập mật khẩu" /></td>
							</tr>
							<tr>
								<td colspan=3><button type="submit" class="btn btn-primary form-control">Xong</button></td>
							</tr>
							<tr>
								<td colspan=3><a href='thong-tin-tai-khoan'>Quay về trang tài khoản</a></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		</div>
		<div class="row">
				<jsp:include page="footer.jsp"></jsp:include>
		</div>
</div>
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
<script>
	function checkSubmit(){
		if($("#OTPXacNhan").val().length < 1){
			alert("Chưa nhập mã otp");
			return false;
		}
		var mk = $("#matKhau").val();
		if(mk.length < 1){
			alert("Chưa nhập mật khẩu");
			return false;
		}
		$("#matKhau").val(md5(mk));
	}
	function kiemTraSoDienThoai() {
		var vlue = $("#soDienThoai").val();
		var fisrtChar = vlue.substr(0,1);
		if(fisrtChar != "0" && vlue.length != 0){
			alert("Số điện thoại sai định dạng");
			return false;
		}
		if(isNaN(vlue)) {
			alert("Số điện thoại không chứa chữ hoặc khoảng trắng");
			$("#soDienThoai").val("");
			return false;
		}
		if(vlue.length != 10 && vlue.length != 11 && vlue.length != 0){
			alert("Số điện thoại có 10 hoặc 11 số")
			return false;
		}
		return true;
	}
	function kiemTraEmail(){
		var reg_mail = /^[A-Za-z0-9]+([_\.\-]?[A-Za-z0-9])*@[A-Za-z0-9]+([\.\-]?[A-Za-z0-9]+)*(\.[A-Za-z]+)+$/;
		var email = $("#email").val();
		if(reg_mail.test(email) == false){ 
			  alert('Email không hợp lệ (ví dụ: abc@gmail.com)');
			  return false;
		}
		return true;
	}
	function openFormXacThucEmail(){
		$("#title-email").html("Xác thực Email mới");
		if(!kiemTraEmail()){
			return false;
		}
		$("#form-cap-nhat").addClass("display-none");
		$("#txtEmail").html($("#email").val());
		$("#form-xac-thuc-mail").removeClass("display-none");
		var data = "mailNhan="+$("#email").val();
		$.ajax({url: 'send-otp',
			type: 'GET',
			data: data,
		  	success: function(result){
		  		
	    }});
	}
	function xuLyXacThuc(){
		var data = "OTP="+$("#OTPXacThuc").val();
		$.ajax({url: 'xac-thuc-otp',
			type: 'GET',
			data: data,
		  	success: function(result){
		  		if(result == "1"){
		  			$("#title-email").attr("otp-value","1");
		  		} else {
		  			$("#title-email").attr("otp-value","0");
		  		}
		  		setTimeout(openFormXacNhanEmail(), 5000);
	    }});
	}
	function openFormXacNhanEmail(){
		if($("#title-email").attr("otp-value") == "0"){
			alert("Mã OTP xác thực không chính xác");
			return false;
		}
		$("#title-email").html("Xác nhận thay đổi");
		$("#form-xac-thuc-mail").addClass("display-none");
		$("#form-xac-nhan-mail").removeClass("display-none");
		var data = "mailNhan="+$("#emailDangDung").val();
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
	${script}
</script>
</body>
</html>