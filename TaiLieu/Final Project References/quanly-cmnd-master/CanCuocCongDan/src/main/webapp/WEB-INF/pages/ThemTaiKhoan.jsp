<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QUẢN LÝ TÀI KHOẢN</title>
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
<sec:authorize access="!hasRole('QUAN_LY_TAI_KHOAN')">
	<div class="container mt20" style="min-height:420px">
		<div class="mt80">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('QUAN_LY_TAI_KHOAN')">
<div class="container " style="min-height:420px">
		<div class="row bg-content">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form action='them-tai-khoan?${_csrf.parameterName}=${_csrf.token}' enctype="multipart/form-data" method="POST" class="row mt20 radius-01 bg-white" id='taiKhoanForm' onsubmit='return checkThemTaiKhoan();'>
					<div class="row text-center">
						<c:if test="${not empty error}">
							<div class="error ml5">${error}</div>
							<% session.removeAttribute("error"); %>
						</c:if>
						<c:if test="${not empty msg}">
							<div class="msg ml5">${msg}</div>
							<% session.removeAttribute("msg"); %>
						</c:if>
					</div>
					<div class='text-center row title'>Thêm tài khoản</div>
					<div class="row">
						<hr>
						<div class='text-center error display-none' id='kqThemTaiKhoan'></div>
					</div>
					<div class="col-md-0"></div>
					<div class="col-md-10">
						<table border=0 class="row mt20">
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Tài khoản: </label></td>
								<td colspan=3>
								 	<input type=text name=themTaiKhoan id=themTaiKhoan class='form-control' placeholder='Nhập tài khoản ..'/>
								 </td>
							</tr>
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Mật khẩu: </label></td>
								<td >
								 	<input type=password name=password1 id=password1 class='form-control' style='color:red' placeholder='Nhập mật khẩu '/>
								 </td>
								 <td><label class='pull-left mt5'>Nhập lại:</label></td>
								 <td >
								 	<input type=password id=password2 class='form-control' style='color:red' placeholder='Nhập lại mật khẩu'/>
								 </td>
							</tr>
							<tr class="row mt5">
								<td>
									<label class='pull-left mt5'>Cơ quan làm việc:</label>
								</td>
								<td colspan=3>
								<select name=coQuanTinh id=coQuanTinh 
								 	class='select-control'
								 	onclick='getHuyen(this.id, this.value);' 
								 	onchange='getHuyen(this.id, this.value);'>
								 	<option value=0>Chọn tỉnh</option>
									<c:forEach items='${dsTinh}' var='tinh'>
										<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
									</c:forEach>
								</select>
								<select name=coQuanHuyen id=coQuanHuyen 
								 	class='select-control'
								 	onfocus='getHuyenFocus(this.id);' onchange='getXa(this.id, this.value);'></select>
								<select name=coQuanXa id=coQuanXa
									class='select-control'></select></td>
							</tr>
							<tr class="row mt5">
								<td></td>
								<td colspan=3 class='text-center'><button class='btn btn-primary' type=submit >Xong</button>
								<button class='btn btn-primary' type=reset >reset</button></td>
								<td></td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		</div>
</div>
</sec:authorize>
<div class="container">
	<div class="row">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>
<script>
	function checkThemTaiKhoan(){
		dong("kqThemTaiKhoan");
		if($("#themTaiKhoan").val()< 4){
			mo("kqThemTaiKhoan");
			$("#kqThemTaiKhoan").html("Tài khoản phải có ít nhất 6 ký tự");
			return false;
		}
		if($("#hoTen").val()< 4){
			mo("kqThemTaiKhoan");
			$("#kqThemTaiKhoan").html("Họ tên ít nhất phải có 4 ký tự");
			return false;
		}
		if($("#password1").val().length < 6){
			mo("kqThemTaiKhoan");
			$("#kqThemTaiKhoan").html("Mật khẩu phải có tối thiểu 6 ký tự");
			return false;
		}
		if($("#password1").val() != $("#password2").val()){
			mo("kqThemTaiKhoan");
			$("#kqThemTaiKhoan").html("Mật khẩu không khớp");
			return false;
		}
		if($("#themMaQuyen").val() == "2" || $("#themMaQuyen").val() == "3"){
			if($("#coQuanTinh").val() == "0"){
				mo("kqThemTaiKhoan");
				$("#kqThemTaiKhoan").html("Chưa chọn cơ quan làm việc");
				return false;
			}
		}
	}
</script>
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
.select-control{
	width:160px;height: 34px;padding: 6px 12px;border-radius: 4px;
}
</style>
</body>
</html>