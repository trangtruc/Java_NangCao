<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>CẬP NHẬT ĐĂNG KÝ KẾT HÔN</title>
	<script type="text/javascript">
		function ktHonNhan1() {
			var soCCNguoiDangKy = document.getElementById("soCCNguoiDangKy").value;
			soCCNguoiDangKy = replaceAll(soCCNguoiDangKy, "-", "");
			var soCCVoHoacChong = document.getElementById("soCCVoHoacChong").value;
			soCCVoHoacChong = replaceAll(soCCVoHoacChong, "-", "");
			if ((soCCVoHoacChong.length == 12) && (soCCNguoiDangKy.length == 12)) {
				var soCC = "soCCNguoiDK=" + soCCNguoiDangKy + "&soCCB=" + soCCVoHoacChong;
				$.ajax({url: 'kt-dang-ky-ket-hon',
					type: 'GET',
					data: soCC,
				  	success: function(kq) {
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
			var soCCNguoiDangKy = document.getElementById("soCCNguoiDangKy").value;
			soCCNguoiDangKy = replaceAll(soCCNguoiDangKy, "-", "");
			var hoTenNguoiDangKy = document.getElementById("hoTenNguoiDangKy").value;
			var soCCVoHoacChong = document.getElementById("soCCVoHoacChong").value;
			soCCVoHoacChong = replaceAll(soCCVoHoacChong, "-", "");
			var hoTenVoHoacChong = document.getElementById("hoTenVoHoacChong").value;
			var ktKetHon = document.getElementById("ktKetHon").value;
			if (checkNull(soCCNguoiDangKy)) {
				alert("Số căn cước không được rỗng.");
				$("#soCCNguoiDangKy").focus(); 
				return false;
			} else if (checkNull(hoTenNguoiDangKy)) {
				alert("Số căn cước " + soCCNguoiDangKy + " không có trong hệ thống.");
				$("#soCCNguoiDangKy").focus();
				return false;
			} else if (checkNull(soCCVoHoacChong)) {
				alert("Số căn cước không được rỗng.");
				$("#soCCVoHoacChong").focus(); 
				return false;
			} else if (checkNull(hoTenVoHoacChong)) {
				alert("Số căn cước " + soCCVoHoacChong + " không có trong hệ thống.");
				$("#soCCVoHoacChong").focus();
				return false;
			} else if ((ktKetHon == '0')) {
				alert("Bạn không thể đăng ký kết hôn trong những trường hợp pháp luật cấm.");
				return false;
			}
		}
		var kqCapNhat = <%= session.getAttribute("kqCapNhat")%>;
		<% session.removeAttribute("kqCapNhat"); %>
		if (kqCapNhat) {
			alert("Cập nhật thành công");
		} else if (kqCapNhat == false) {
			alert("Cập nhật chưa thành công.");
		}
	</script>
</head>
<body>
<div class="container">
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
</div>

<form   name="dkKetHon" 
		action="cap-nhat-dk-ket-hon?${_csrf.parameterName}=${_csrf.token}" 
		enctype="multipart/form-data"
		method='POST' 
		onsubmit='return checkOnSubmit();'>
<div class="container bg-white" style="min-height:400px">
	<div class="rơw mt20">
		<div class="text-center col-md-12">
			<div class="title">
					CẬP NHẬT ĐĂNG KÝ KẾT HÔN
			</div>
		</div>
	</div>
	
	<div class="row mb40">
		<div class='col-md-3'></div>
		<div class="col-md-6 mt20">
			<div class="row">
				<table border="1">
					<tr><th colspan="2">THÔNG TIN CẬP NHẬT</th></tr>
					<tr>
						<td>Số đăng ký</td>
						<td colspan="3"><input type=text 
							id='soDK'
							name='soDK' 
							value = '${ttdk.soDK}'
							disabled
							class='form-control' />
						</td>
					</tr>
					<tr>
						<td>Số căn cước người đăng ký</td>
						<td colspan="3"><input type=text 
							onkeypress='chenGachNgang(this.id, this.value);' 
							oninput='kiemTraDoDai(this.id, this.value);' 
							onblur='getThongTinCCCD(this.id, this.value); ktHonNhan1();' 
							onkeyup = 'kiemTraSo(this.id, this.value);'
							id='soCCNguoiDangKy'
							name='soCCNguoiDangKy' 
							value = '${ttdk.soCCNguoiDK}'
							class='form-control' />
						</td>
					</tr>
					<tr>
						<td>Họ và tên người đăng ký</td>
						<td colspan="3"><input type=text 
							id='hoTenNguoiDangKy' 
							name='hoTenNguoiDangKy' 
							value='${cccdNguoiDK.hoTen}'
							class='form-control' disabled />
						</td>
					</tr>
					<tr>
						<td>Số căn cước vợ hoặc chồng</td>
						<td colspan="3"><input type=text 
							onkeypress='chenGachNgang(this.id, this.value);' 
							oninput='kiemTraDoDai(this.id, this.value);' 
							onblur='getThongTinCCCD(this.id, this.value); ktHonNhan1();' 
							onkeyup = 'kiemTraSo(this.id, this.value);'
							id='soCCVoHoacChong'
							name='soCCVoHoacChong' 
							value='${ttdk.soCCVoHoacChong}' 
							class='form-control' />
						</td>
					</tr>
					<tr>
						<td>Họ và tên vợ hoặc chồng</td>
						<td colspan="3"><input type=text 
							id='hoTenVoHoacChong' 
							name='hoTenVoHoacChong' 
							value='${cccdVoHoacChong.hoTen}'
							class='form-control' disabled />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="row" style="margin-bottom: 20px;">
		<div class="col-md-6 text-right">
			<button type='submit' id="luu" disabled class='btn btn-primary'>Lưu</button>
		</div>
		<div class="col-md-6 text-left">
			<button type='button' onclick='dongTrang();' class='btn btn-primary' >
				Đóng
			</button>
		</div>
	</div>
</div>
<input type="hidden" value="1" id="ktKetHon"/>
<input type="hidden" value='${ttdk.soDK }' name='soDK'/>
</form>
<script src="resources/js/NhapKhaiSinh.js">
</script>
<div class="container">
	<div class="row">
		<div class="container">
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
</div>
</div>
<script>
$("input, select").click(function (e){
	$("#luu").attr("change-value","1");
	$("#luu").removeAttr("disabled");
	
})
$("a").click(function (e){
	if($("#luu").attr("change-value") == '1'){
		if(!confirm("Bạn chưa lưu các thay đổi, bạn có muốn tiếp tục rời trang không?")){
			return false;
		}
	}
});
function dongTrang(){
	if($("#luu").attr("change-value") == '1'){
		if(!confirm("Bạn chưa lưu các thay đổi, bạn có muốn tiếp tục rời trang không?")){
			return false;
		}
	}
	window.close();
}
</script>
</body>
</html>