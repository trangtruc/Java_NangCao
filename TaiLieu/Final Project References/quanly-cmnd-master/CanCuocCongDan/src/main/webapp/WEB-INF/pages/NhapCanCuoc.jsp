<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NHẬP CĂN CƯỚC CÔNG DÂN</title>
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
<sec:authorize access="!hasRole('NHAP_CCCD')">
	<div class="container" style="min-height:240px">
		<div class="mt80">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('NHAP_CCCD')">
<div class="container " style="min-height:240px">
	<form action='nhap-can-cuoc-cong-dan?${_csrf.parameterName}=${_csrf.token}' enctype="multipart/form-data" method="POST" onsubmit="return nhapCCCD();">
	<div class="row bg-content">
		<div class="col-md-3 mb5">
			<jsp:include page="menu-trai.jsp"></jsp:include>
		</div>
		<div class="col-md-9">
			<div class="row radius-01 bg-white">
				<div class="col-md-12">
					<div class="row text-center">
						<c:if test="${not empty error}">
							<div class="error ml5">${error}</div>
						</c:if>
						<c:if test="${not empty msg}">
							<div class="msg ml5">${msg}</div>
						</c:if>
					</div>
					<div class="row text-center title">
						Nhập Căn Cước công dân
					</div>
					<hr>
					<div class="row">	
						<div class="title">nhập hình ảnh</div>
						<table border=0>
							<tr>
									<td><label>Hình Thẻ</label></td>
									<td colspan="2"><input type=file name='hinhThe' class='form-control' /></td>
									<td><label>Hình Vân Tay Trỏ Trái</label></td>
									<td colspan="2"><input type=file name='vanTayTroTrai' class='form-control' /></td>
									<td><label>Hình Vân Tay Trỏ Phải</label></td>
									<td colspan="2"><input type=file name='vanTayTroPhai' class='form-control' /></td>
								</tr>
						</table>
					</div>
					<hr>
					<div class="row">
						<div class="title">Nhập thông tin</div>
						<div id="ketQuaKiemTraSoCC" class="error display-none"></div>
						<table border="0">
							<tr>
								<td><label>Số CMND/CCCD </label></td>
								<td colspan=3><input type=text name=soCC id=soCC class='form-control'
									onkeyup='kiemTraSo(this.id,this.value);'
							    	oninput='kiemTraDoDai(this.id, this.value); getThongTinKhaiSinh(this.value);'
							    	onkeypress='chenGachNgang(this.id, this.value);'
							    	onblur='getSoCCCD()' placeholder="Nhập số CMND/CCCD" /></td>
								
							</tr>
							<tr>
								<td><label>Số khai sinh</label></td>
								<td><input type=text name=soKhaiSinh id=soKhaiSinh class='form-control' 
								onkeyup='kiemTraSo(this.id,this.value);' 
								oninput='kiemTraDoDai(this.id, this.value);' 
								onkeypress='chenGachNgang(this.id, this.value);' placeholder="Nhập số khai sinh" /></td>
								<td><label>Họ tên khác</label></td>
								<td><input type=text name=hoTenKhac id=hoTenKhac class='form-control' placeholder="Nhập nếu có" value="" /></td>
							</tr>
							<tr>
								<td><label>Đặc điểm dị hình</label></td>
								<td><input type=text name=dacDiem id=dacDiem class='form-control' placeholder="Nhập nếu có" value="" /></td>
								<td><label>Tôn giáo</label></td>
								<td><input type=text name=tonGiao class='form-control' placeholder="Nhập nếu có" value="" /></td>
							</tr>
							<tr>
								<td><label>Trình độ</label></td>
								<td><input type=text name=trinhDo id=trinhDo class='form-control' placeholder="Nhập trình độ" /></td>
								<td><label>Nghề nghiệp</label></td>
								<td><input type=text name=ngheNghiep id=ngheNghiep class='form-control' placeholder="Nhập nghề nghiệp" /></td>
							</tr>
							<tr>
							<td><label>Nơi ở hiện tại</label></td>
							<td style="width:27%">
								<select name=hienTaiTinh id=hienTaiTinh class='form-control' onclick='getHuyen(this.id, this.value);' onchange='getHuyen(this.id, this.value);'>
									<c:forEach items='${dsTinh}' var='tinh'>
										<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
									</c:forEach>
								</select>
							</td>
							<td style="width:27%">
								<select name="hienTaiHuyen" id="hienTaiHuyen" class="form-control" 
									onfocus='getHuyenFocus(this.id);'
									onchange='getXa(this.id, this.value);'>
								</select>
							</td>
							<td style="width:27%">
								<select name="hienTaiXa" id="hienTaiXa" class="form-control">
								</select>
							</td>
							</tr>
							<tr>
								<td><label>Nhóm máu</label></td>
								<td><select name=nhomMau class='form-control'>
									<c:forEach items='${dsNhomMau}' var='nhomMau'>
										<option id="option" value="${nhomMau.maNM}">${nhomMau.tenNM}</option>
									</c:forEach>
									</select>
								</td>
								<td><label>Lần cấp</label></td>
								<td><input type=number name=lanCap class='form-control' value="1"/></td>
							</tr>
							<tr>
								<td><label>Nơi cấp</label></td>
								<td>
									<select name=noiCapTinh id=noiCapTinh class='form-control'>
										<c:forEach items='${dsTinh}' var='tinh'>
											<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
										</c:forEach>
									</select>
								</td>
								<td><label>Ngày cấp</label></td>
								<td><input type=date name=ngayCap id=ngayCap class='form-control' value="0" /></td>
							</tr>
							<tr>
								<td><label>Người cấp</label></td>
								<td><input type=text name=nguoiCap id=nguoiCap 
									class="form-control"
									placeholder="Nhập người cấp" /></td>
								<td><label>Tình trạng</label></td>
								<td>
									<select name=tinhTrang class='form-control' >
										<option value="1"> Hoạt động
										<option value="0"> Bị khóa
									</select>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="row mt10">
					<div class="col-md-12 text-center">
						<button type=submit class='btn btn-primary' >Xong</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	</form>
</div>
</sec:authorize>
<div class="container">
	<div class="row">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>
<script>
	function nhapCCCD(){
		if($("#soCC").val().length < 11){
			$("#soCC").focus();
			alert("Chưa nhập đủ số Căn Cước");
			return false;
		} 
		if($("#trinhDo").val().length < 1){
			$("#trinhDo").focus();
			alert("Chưa nhập trình độ");
			return false;
		}
		if($("#ngheNghiep").val().length < 1){
			$("#ngheNghiep").focus();
			alert("Chưa nhập nghề nghiệp");
			return false;
		}
		if($("#ngayCap").val() == "0" || $("#ngayCap").val().length < 1){
			$("#ngayCap").focus();
			alert("Chưa chọn ngày cấp");
			return false;
		}
		if($("#hienTaiHuyen").val() == "0"){
			$("#hienTaiHuyen").focus();
			alert("Chưa chọn huyện");
			return false;
		}
		if($("#hienTaiXa").val() == "0"){
			$("#hienTaiXa").focus();
			alert("Chưa chọn Xã");
			return false;
		}
	}
	function getSoCCCD(){
		var vlue = $("#soCC").val();
		dong("ketQuaKiemTraSoCC");
		var soCC = "soCC="+vlue;
		$.ajax({url: 'get-so-cccd',
			type: 'GET',
			data: soCC,
		  	success: function(result){
		  		var ketQua = "";
		  		if(result == "0"){
		  			ketQua = "Số Căn Cước này đã tồn tại - hãy nhập Số Căn Cước mới.";
		  			$("#soCC").focus();
		  			mo("ketQuaKiemTraSoCC");
		  			$("#ketQuaKiemTraSoCC").html(ketQua);
		  			return 0;
		  		} else {
		  			return 1;
		  		}
	    }});	
	}
</script>
<style>
table tr:hover {
    background-color: transparent;
}
table tr:nth-child(even):hover {
	background-color: transparent;
}
table td{
	padding:5px;
}
</style>
</body>
</html>