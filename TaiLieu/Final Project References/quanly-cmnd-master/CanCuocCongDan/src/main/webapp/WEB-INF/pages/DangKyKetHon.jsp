<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ĐĂNG KÝ KẾT HÔN</title>
	<script type="text/javascript">
		function ktHonNhan() {
			var soCCVoHoacChong = document.getElementById("soCCVoHoacChong").value;
			soCCVoHoacChong = replaceAll(soCCVoHoacChong, "-", "");
			if (ktDoDai(soCCVoHoacChong) && (soCCVoHoacChong != null)) {
				var soCCB = "soCCB=" + soCCVoHoacChong;
				$.ajax({url: 'kt-dang-ky-ket-hon',
					type: 'GET',
					data: soCCB,
				  	success: function(kq){
				  		if(kq != "") {
				  			alert(kq);
				  			$("#ktKetHon").val("0");
				  		} else {
				  			$("#ktKetHon").val("1");
				  		}
			        }});
			}	
		}
		function checkOnSubmit() {
			var noiDKLVTinh = document.getElementById("noiDKLVTinh").value;
			var noiDKLVHuyen = document.getElementById("noiDKLVHuyen").value;
			var noiDKLVXa = document.getElementById("noiDKLVXa").value;
			var soCCVoHoacChong = document.getElementById("soCCVoHoacChong").value;
			soCCVoHoacChong = replaceAll(soCCVoHoacChong, "-", "");
			var hoTenVoHoacChong = document.getElementById("hoTenVoHoacChong").value;
			var ktKetHon = document.getElementById("ktKetHon").value;
			if (checkNull(noiDKLVXa) || checkNull(noiDKLVHuyen) || checkNull(noiDKLVTinh)) {
				alert("Vui lòng chọn nơi làm việc.");
				return false;
			} else if (checkNull(soCCVoHoacChong)) {
				alert("Số căn cước người Ông/Bà muốn kết hôn không được rỗng.");
				return false;
			} else if (checkNull(hoTenVoHoacChong)) {
				alert("Số căn cước người Ông/Bà muốn kết hôn không có trong hệ thống.");
				return false;
			} else if (checkNull(ktKetHon)) {
				alert("Bạn không thể đăng ký kết hôn trong những trường hợp pháp luật cấm.");
				return false;
			}
		}
		
	</script>
</head>
<body>
<div class="container">
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
</div>

<form action="dang-ky-ket-hon?${_csrf.parameterName}=${_csrf.token}" 
		enctype="multipart/form-data"
		method='POST' 
		onsubmit='return checkOnSubmit();'>
<div class="container bg-white" style="min-height:400px">
	<div class="mt80">
		<div class="text-center col-md-12">
			<div class="text-orange title">
					ĐĂNG KÝ KẾT HÔN
			</div>
		</div>
	</div>
	
	<div class="row mt40 mb40">
		<div class='col-md-2'></div>
		<div class="col-md-8 mt20">
			<div class="row">
				<div class='col-md-3'>
					<span class="pull-right"><font size="5">Nơi làm việc:</font></span>
				</div>
				<div class='col-md-3'>
				<select name=noiDKLVTinh id=noiDKLVTinh class='form-control' 
										onchange='getHuyen(this.id, this.value);'>
					<option id="option" value="">Chọn tỉnh</option>
						<c:forEach items='${dsTinh}' var='tinh'>
								<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
						</c:forEach>
				</select>
				</div>
				<div class='col-md-3'>
				<select name="noiDKLVHuyen" id="noiDKLVHuyen" class="form-control" 
										onchange='getXa(this.id, this.value);' 
										onfocus='getHuyenFocus(this.id);'>
				</select>
				</div>
				<div class='col-md-3'>
				<select name="noiDKLVXa" id="noiDKLVXa" 
										onfocus='getXaFocus(this.id);' 
										class="form-control">
				</select>
				</div>
			</div>
			<div class="row mt20">
				<table border="1">
					<tr><th colspan="4">THÔNG TIN NGƯỜI ÔNG/BÀ MUỐN KẾT HÔN</th></tr>
					<tr>
						<td>Số căn căn cước người bạn muốn kết hôn</td>
						<td colspan="3"><input type=text 
							onkeypress='chenGachNgang(this.id, this.value);' 
							oninput='kiemTraDoDai(this.id, this.value);' 
							onblur='getThongTinCCCD(this.id, this.value); ktHonNhan();' 
							id='soCCVoHoacChong'
							name='soCCVoHoacChong' class='form-control' />
						</td>
					</tr>
					<tr>
						<td>Họ và tên:</td>
						<td colspan="3"><input type=text 
							id='hoTenVoHoacChong' 
							name='hoTenVoHoacChong' class='form-control' disabled />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6 text-right">
			<button type='submit' class='btn btn-primary'>Đăng ký</button>
		</div>
		<div class="col-md-6 text-left">
			<button type='button' class='btn btn-primary' >
				Hủy đăng ký
			</button>
		</div>
	</div>
</div>
<input type="hidden" value="${tinNhan}"/>
<input type="hidden" value="1" id="ktKetHon"/>
</form>
<div class="row">
	<div class="container">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>
</body>
</html>