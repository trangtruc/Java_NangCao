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
	<title>BỔ SUNG EMAIL - ${ssHoTen }</title>
	
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
					<div class="title-small">Bổ sung Email - ${ssTaiKhoan }</div>
					<c:if test="${not empty error}">
						<div class="error ml5">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="msg ml5">${msg}</div>
					</c:if>
					<div class="error ml5 display-none" id="error"></div>
					<form action="bo-sung-email?${_csrf.parameterName}=${_csrf.token}" method='POST' onsubmit="return checkSubmit();">
						<table border="0" style="width:100%;"  id="form-cap-nhat">
							<tr>
								<td colspan=2>
									Email này dùng để xác thực các yêu cầu của người dùng trên hệ thống
								</td>
							</tr>
							<tr>
								<td><span class="fa fa-envelope fa-2x" style="color:#d88835;"></span></td>
								<td><input type='email' name='email' id="email" class='form-control' placeholder="Nhập Email" value="${email }" /></td>
							</tr>
							<tr>
								<td colspan=2><button type="button" class="btn btn-primary form-control" onclick="kiemTraEmail();">Tiếp theo <span class="fa fa-arrow-right"></span></button></td>
							</tr>
							<tr>
								<td colspan=2><a href='thong-tin-tai-khoan'>Quay về trang tài khoản</a></td>
							</tr>
						</table>
						<table border="0" style="width:100%;" id="form-xac-nhan-mail" class="display-none" data-scrollreveal="enter top and move 100px, wait 0.7s">
							<tr>
								<td colspan=3>
									<font color=gray>Mã OTP đã được gửi đến Email <font color="" id='txtEmail'></font>, hãy đăng nhập Email để kiểm tra.</font>
								</td>
							</tr>
							<tr>
								<td><span class="fa fa-key fa-2x" style="color:#d88835;"></span></td>
								<td style="width:90%">
										<input type='text' name='OTP' id='OTP' class='form-control' placeholder="Nhập mã OTP">		
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
		dong("error");
		if($("#OTP").val().length < 1){
			mo("error");
			thongBao("error","Chưa nhập mã otp")
			return false;
		}
		var mk = $("#matKhau").val();
		if(mk.length < 1){
			mo("error");
			thongBao("error","Chưa nhập mật khẩu")
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
		dong("error");
		var reg_mail = /^[A-Za-z0-9]+([_\.\-]?[A-Za-z0-9])*@[A-Za-z0-9]+([\.\-]?[A-Za-z0-9]+)*(\.[A-Za-z]+)+$/;
		var email = $("#email").val();
		if(reg_mail.test(email) == false){
			mo("error");
			thongBao("error","Email không hợp lệ (ví dụ: abc@gmail.com)")
			return false;
		}
		var data = "email="+$("#email").val();
		$.ajax({url: 'email-ton-tai',
			type: 'GET',
			data: data,
		  	success: function(result){
		  		if(result == "1"){
		  			mo("error");
		  			thongBao("error","Email này đã có người sử dụng, hãy chọn Email khác.")
		  			return false;
		  		} else {
		  			openFormXacNhanEmail();
		  		}
		  		
	    }});
		return true;
	}
	function openFormXacNhanEmail(){
		dong("form-cap-nhat");
		mo("form-xac-nhan-mail");
		$("#txtEmail").html($("#email").val());
		var data = "mailNhan="+$("#email").val();
		$.ajax({url: 'send-otp',
			type: 'GET',
			data: data,
		  	success: function(result){
		  		
	    }});
	}
	function sendMail(){
		$("#gui-lai").addClass("fa-spin");
		$("#gui-lai").attr("title","Đang gửi ....");
		var data = "mailNhan="+$("#email").val();
		$.ajax({url: 'send-otp',
			type: 'GET',
			data: data,
		  	success: function(result){
		  		$("#gui-lai").removeClass("fa-spin");
		  		$("#gui-lai").attr("title","Gửi lại");
	    }});
	}
	${script}
</script>
</body>
</html>