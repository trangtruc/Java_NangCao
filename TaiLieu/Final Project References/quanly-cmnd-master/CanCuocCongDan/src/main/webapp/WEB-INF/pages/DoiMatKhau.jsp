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
	<title>ĐỔI MẬT KHẨU - ${ssHoTen }</title>
	
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
					<div class="title-small">Đổi mật khẩu - ${ssTaiKhoan }</div>
					<c:if test="${not empty error}">
						<div class="error ml5">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="msg ml5">${msg}</div>
					</c:if>
					<form action="doi-mat-khau?${_csrf.parameterName}=${_csrf.token}" method='POST' onsubmit="return MH5();" >
						<table border="0" style="width:100%;">
							<tr>
								<td><span class="fa fa-unlock fa-2x" style="color:#d88835;"></span></td>
								<td style="width:90%" colspan=2><input type='password' name='matKhauCu' id="matKhauCu" class='form-control' placeholder="Mật khẩu cũ"></td>
							</tr>
							<tr>
								<td><span class="fa fa-lock fa-2x" style="color:#d88835;"></span></td>
								<td colspan=2><input type='password' name='matKhauMoi' id="matKhauMoi" class='form-control' placeholder="Mật khẩu mới" /></td>
							</tr>
							<tr>
								<td><span class="fa fa-lock fa-2x" style="color:#d88835;"></span></td>
								<td colspan=2><input type='password' name='xacNhanMatKhauMoi' id="xacNhanMatKhauMoi" class='form-control' placeholder="Nhập lại mật khẩu mới" /></td>
							</tr>
							<c:if test="${ssEmail != null}">
								<tr>
									<td>
										<span class="fa fa-key fa-2x" style="color:#d88835;"></span>
										<input type="hidden" id="email" value="${ssEmail}" />
									</td>
									<td style="width:90%">
											<input type='text' name='OTP' id='OTP' class='form-control' placeholder="Nhập mã OTP đã nhận trong Email">		
									</td>
									<td><a onclick="sendMail();" title="Gửi lại"><span id="gui-lai" class="fa fa-refresh fa-fw"></span></a></td>
								</tr>
							</c:if>
							<c:if test="${ssEmail == null}">
								<input type=hidden name="OTP" value="${ssOTP }" />
							</c:if>
							<tr>
								<td colspan=3><input name="submit" type="submit"  value="Đổi mật khẩu" class='btn btn-primary form-control' /></td>
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
	${sendMail}
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