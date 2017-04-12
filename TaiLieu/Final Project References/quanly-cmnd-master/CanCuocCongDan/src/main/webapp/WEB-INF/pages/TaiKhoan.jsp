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
<div class="container" style="min-height:420px">
	<div class="row text-center">
		<div class="error title">ERROR: Bạn không có quyền thực hiện thao tác này</div>
	</div>
</div>
</sec:authorize>
<sec:authorize access="hasRole('QUAN_LY_TAI_KHOAN')">
<div class="container" style="min-height:420px">
		<div class="row bg-content">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form action='cap-nhat-tai-khoan?${_csrf.parameterName}=${_csrf.token}' enctype="multipart/form-data" method="POST" class="row mt20 display-none radius-01 bg-white" id='capNhatForm'>
					<label class='pull-left title'>Cập nhật tài khoản</label>
					<div class="row">
						<hr>
					</div>
					<div class="col-md-0"></div>
					<div class="col-md-10">
						<table border=0 class="row mt20">
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Tài khoản: </label></td>
								<td colspan=3>
								 	<select name=updateTaiKhoan id=updateTaiKhoan class='form-control' onchange="getQuyen(this.value); getCoQuan(this.value);">
										
										<c:forEach items='${updateTaiKhoan}' var='tk'>
											<option id="option" value="${tk.username}">${tk.username} - ${tk.hoTen}</option>
										</c:forEach>
									</select>
								 </td>
							</tr>
							<tr class="row mt5">
								<td>
									<label class='pull-left mt5'>Cơ quan làm việc:</label>
								</td>
								<td colspan=3>
								<select name=updateTinh id=updateTinh 
								 	class='select-control'
								 	onclick='getHuyen(this.id, this.value);' 
								 	onchange='getHuyen(this.id, this.value);'>
								 	<option value=0>Chọn tỉnh</option>
									${updateTinh}
								</select>
								<select name=updateHuyen id=updateHuyen 
								 	class='select-control'
								 	onfocus='getHuyenFocus(this.id);' onchange='getXa(this.id, this.value);'>
								 	<option value=0>Chọn huyện</option>
									${updateHuyen }
								</select>
								<select name=updateXa id=updateXa
									class='select-control'
									onfocus='getXaFocus(this.id);'>
									<option value=0>Chọn xã
									</option>
									${updateXa }
								</select></td>
							</tr>
							<tr class="row mt5">
								<td></td>
								<td colspan=2 class='text-center'><button class='btn btn-primary' type=submit >Lưu</button>
								<button class='btn btn-primary' type=button onclick='dongCapNhatForm()'>Đóng</button></td>
								<td></td>
							</tr>
						</table>
					</div>
				</form>
				<div class="radius-01 bg-white">
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
					<div class="title row text-center">Danh sách tài khoản</div>
					<div class="row">
						<div class="col-md-7">
						</div>
						<div class="col-md-4 tim-kiem pull-right">
							<label class='pull-left mt5'>Tìm kiếm</label>
							<div class='col-md-9'>
								<input type=text placeholder='Nhập tài khoàn muốn tìm ..' id='txtTimKiem' onkeyup='timKiemTaiKhoan();' class='form-control'/>
							</div>
						</div>
					</div>
					<div class="row" style='height:420px;overflow:auto;'>
						<table border=1>
							<tr><th>Tài khoản</th>
							<th>Họ tên</th>
							<th style='width:6%'>Email</th>
							<th>Số ĐT</th>
							<th style='width:10%'>Quyền</th>
							<th>Cập nhật</th>
							<th>Tình trạng</th>
							</tr>
							
						<tbody id='kqTimKiem'>
							${dsTaiKhoan}
						</tbody>
						</table>
					</div>
					<div class="row mt5"></div>
				</div>
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
	function timKiemTaiKhoan(){
		var tuKhoa = "keySearch="+$("#txtTimKiem").val();
		$.ajax({url: 'tim-kiem-tai-khoan',
			type: 'GET',
			data: tuKhoa,
		  	success: function(result){
		  		$("#kqTimKiem").html(result);
	    }});
	}
	function khoaUser(taiKhoan){
		if(!confirm("Bạn có chắc chắn khóa tài khoản: "+taiKhoan)){
			return false;
		}
		var taiKhoan = "taiKhoan="+taiKhoan;
		$.ajax({url: 'khoa-user',
			type: 'GET',
			data: taiKhoan,
		  	success: function(result){
		  		window.location = "quan-ly-tai-khoan";
	    }});
	}
	function moUser(taiKhoan){
		if(!confirm("Bạn có chắc chắn mở khóa tài khoản: "+taiKhoan)){
			return false;
		}
		var taiKhoan = "taiKhoan="+taiKhoan;
		$.ajax({url: 'mo-khoa-user',
			type: 'GET',
			data: taiKhoan,
		  	success: function(result){
		  		window.location = "quan-ly-tai-khoan";
	    }});
	}
	function getQuyen(taiKhoan){
		var taiKhoan = "taiKhoan="+taiKhoan;
		$.ajax({url: 'get-quyen-user',
			type: 'GET',
			data: taiKhoan,
		  	success: function(result){
		  		$("#updateMaQuyen").html(result);
	    }});
	}
	function getCoQuan(taiKhoan){
		var taiKhoan = "taiKhoan="+taiKhoan;
		$.ajax({url: 'get-co-quan-user',
			type: 'GET',
			data: taiKhoan,
		  	success: function(result){
		  		var data = result.split("_");
		  		$("#updateTinh").html(data[0]);
		  		$("#updateHuyen").html(data[1]);
		  		$("#updateXa").html(data[2]);
	    }});
	}
	function capNhat(taiKhoan){
		moCapNhatForm();
		$("#updateTaiKhoan").val(taiKhoan);
		getQuyen(taiKhoan);
		getCoQuan(taiKhoan);
	}
	function moTaiKhoanForm(){
		dongCapNhatForm()
		$("#taiKhoanForm").removeClass("display-none");
		$("#taiKhoanForm").addClass("display-block");
	}
	function moCapNhatForm(){
		dongTaiKhoanForm();
		$("#capNhatForm").removeClass("display-none");
		$("#capNhatForm").addClass("display-block");
	}
	function dongTaiKhoanForm(){
		$("#taiKhoanForm").removeClass("display-block");
		$("#taiKhoanForm").addClass("display-none");
	}
	function dongCapNhatForm(){
		$("#capNhatForm").removeClass("display-block");
		$("#capNhatForm").addClass("display-none");
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