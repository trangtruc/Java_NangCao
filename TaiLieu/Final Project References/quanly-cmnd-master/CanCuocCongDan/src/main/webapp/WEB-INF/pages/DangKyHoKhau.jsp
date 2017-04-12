<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="resources/js/dangky-cccd.js"></script>
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Đăng Ký Hộ Khẩu</title>
</head>
<body>
	<div class="container">
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
		<!-- 			End page header -->
	</div>
	
	<div class = 'col-md-12 ' >
		<div class="container bg-content">
			<div class="mt5 row">
						<div class=" row text-center col-md-12">
							<div class="text-orange title">
								ĐĂNG KÝ TÁCH SỔ HỘ KHẨU
							</div>
						</div>
			</div>
		
			<form name="dangKyHoKhau" action="xu-ly-dang-ky-ho-khau?${_csrf.parameterName}=${_csrf.token}"
			enctype="multipart/form-data"
			method="POST" onsubmit="return dkSubmit();">
				<div class= " col-md-3"></div>
				<div class = "col-md-6 " >
					<div class='row'>
						<table border="1" style="margin: auto; ">
							<tr>
								<th colspan="4">Thông Tin Người Đăng Ký</th>
							</tr>
							<tr>
								<td>Số CCCD</td>
								<td colspan="3"><input type="text" value="${soCC }"  class='form-control' disabled="disabled"></td>
								<input type="hidden"  name=soCCNguoiDK id=soCCNguoiDK value="${soCC }" class='form-control'>
							</tr>
							<tr>
								<td>Họ và Tên</td>
								<td colspan="3" style="width: 70%"><input type="text" value="${hoTen }" name=hoTenNguoiDK id=hoTenNguoiDK class="form-control" disabled="disabled"></td>
							</tr>
							<tr>
								<td>Giới Tính</td>
								<td colspan="3" ><input type="text" value="${gioiTinh }" name=gioiTinh id=gioiTinh class="form-control" style="width: 30%; margin-left; text-align: center;" disabled="disabled"></td>
							</tr>	
							<tr>
								<td colspan="1">Làm việc tại Ủy Ban:<br> 
									<span style ='color: red;'>(Nơi sẽ được hẹn làm việc để hoàn tất thủ tục)</span></td>
								<td colspan="2">	<select name="noiDKLVTinh" id="noiDKLVTinh" class="form-control" onclick='getHuyen(this.id, this.value);' onchange='getHuyen(this.id, this.value);'>
								 			<c:forEach items='${dsTinh}' var='tinh'>
												<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
											</c:forEach>
								 		</select>
								 </td>
								 <td colspan="1">
								 		<select name="noiDKLVHuyen" id="noiDKLVHuyen" onfocus='getHuyenFocus(this.id);' class="form-control">
								 			<option value="0">Chọn Huyện</option>
								 		</select>
								 </td>
							</tr>		
							<tr>
								<td>Số sổ hộ khẩu hiện tại<span style ='color: red;'>(*)</span></td>
								<td colspan="2"><input type=text class=form-control name='soHKCu' id='soHKCu' oninput="kiemTraDoDai(this.id, this.value);" onkeypress="chenGachNgang(this.id, this.value);" onkeyup='kiemTraSo(this.id, this.value);' ></td>
								<td ><input type=button name=xacNhan class='form-control btn-primary' value='Xác Nhận'  onclick="createForm1();"></td>
							</tr>
							
							
						</table>
						
					</div>	
					</div>
					<div class = 'row ' >			
						<div class='col-md-12' style='margin-left: 20px; padding-top: 20px' id=form-input ><i style='color:red; margin-left: 26%'>(*):là bắt buộc</i></div>
						
								
						<div class='col-md-12' style='margin-left: 20px; padding-top: 20px' id=form-input1 ></div>
						
					</div>
			</form>
		</div>
	</div>
					
				
		
	<input type="hidden" id = "dsQuanHe" value="${DSQuanHe }">
	
	<div class="container"></div>
	<div class="row">
		<div class="container" style='margin-bottom: 10px; margin-top: 80px; '>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>

	</div>

	<script src="resources/js/NhapKhaiSinh.js"></script>
	<script src="resources/js/DKHoKhau.js"></script>	
	
</body>
</html>