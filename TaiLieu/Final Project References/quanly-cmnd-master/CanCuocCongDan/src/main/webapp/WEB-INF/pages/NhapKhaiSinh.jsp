<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NHẬP KHAI SINH</title>
	<script>
		var kqThemKS = <%= session.getAttribute("kqThemKS")%>;
		<% session.removeAttribute("kqThemKS"); %>
		if (kqThemKS) {
			alert("Thêm thành công");
		} else if (kqThemKS == false) {
			alert("Thêm chưa thành công.");
		}
	</script>
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
<sec:authorize access="!hasRole('NHAP_KHAI_SINH')">
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
<sec:authorize access="hasRole('NHAP_KHAI_SINH')">
<form 	action='nhap-khai-sinh?${_csrf.parameterName}=${_csrf.token}'  
		enctype="multipart/form-data" 
		onsubmit="return onSubmitCheckNull();" method="POST">
<div class="container" style="min-height:240px">
	<div class="row bg-content">
		<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include> 
		</div> 
		<div class="col-md-9 bg-white">
			<div class="row radius-01 bg-white">
				<div class="row text-center title">
						NHẬP KHAI SINH
				</div>
				<div class="col-md-6">
					<div class="row">
						<table border="1">
							<tr><th colspan="4">THÔNG TIN NGƯỜI ĐƯỢC KHAI SINH</th></tr>
							<tr>
								<td>Số khai sinh (cũ)</td>
								<td colspan="2">
									<input 
										onkeypress='chenGachNgang(this.id, this.value);' 
										onkeyup = 'kiemTraSo(this.id, this.value); ktTonTaiSoKS(this.id);' 
										oninput ='kiemTraDoDai(this.id, this.value);' 
										type=text 	
										id='soKS' 	
										name='soKS' 
										value='${ks.soKS}'	
										class='form-control' />
								</td>
								<td>
									<label> Tự sinh: </label>
									<input type='checkbox' value='0' 
											id='tuSinhSoKS' name='tuSinhSoKS' onclick='getSoKS();'/>
								</td>
							</tr>
							<tr>
								<td>Họ, chữ đệm, tên</td>
								<td colspan="3"><input type=text id='hoTen' name='hoTen' value='${ks.hoTen}' class='form-control' /></td>
							</tr>
							<tr>
								<td>Ngày, tháng, năm sinh</td>
								<td colspan="3">
									<input type='date' id='ngaySinh' 
										name=ngaySinh class='form-control' />
								</td>
							</tr>
							<tr>
								<td>Giới tính</td>
								<td colspan="3">
									<input type=radio name=gioiTinh id="nam" value="Nam" checked/>Nam &nbsp;&nbsp;&nbsp;&nbsp; 
									<input type=radio name=gioiTinh id="nu" value="Nữ" />Nữ
								</td>
							</tr>
							<tr>
								<td>Quốc tịch</td>
								<td colspan="3"><input type=text name=quocTich id='quocTich'
										class='form-control' value="Việt Nam"/></td>
							</tr>
							<tr>
								<td>Dân tộc</td>
								<td colspan="3">
									<select name='maDT' class='form-control'>
										<c:forEach items='${dsDanToc}' var='danToc'>
											<option id="option" value="${danToc.maDT}">${danToc.tenDT}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td rowspan='2'>Nơi sinh</td>
								<td colspan="3"><input type=text id='benhVien' 
										placeholder="Nhập tên bệnh viện nơi sinh (Nếu có)"
										name='benhVien' class='form-control'/></td>
							</tr>
							<tr>
								<td style="width: 28%;">
									<select name=noiSinhTinh id=noiSinhTinh class='form-control' 
											onchange='getHuyen(this.id, this.value);'>
										<option id="option" value="">Chọn tỉnh</option>
										<c:forEach items='${dsTinh}' var='tinh'>
											<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
										</c:forEach>
									</select>
								</td>
								<td style="width: 28%;">
									<select name="noiSinhHuyen" id="noiSinhHuyen" class="form-control" 
											onchange='getXa(this.id, this.value);' 
											onfocus='getHuyenFocus(this.id);'>
									</select>
								</td>
								<td style="width: 28%;">
									<select name="noiSinhXa" id="noiSinhXa" 
											onfocus='getXaFocus(this.id);' 
											class="form-control">
									</select>
								</td>
							</tr>
							<tr>
								<td>Quê quán</td>
								<td>
									<select name="queQuanTinh" id="queQuanTinh" class='form-control' onchange='getHuyen(this.id, this.value);'>
										<option id="option" value="">Chọn tỉnh</option>
										<c:forEach items='${dsTinh}' var='tinh'>
											<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
										</c:forEach>
									</select>
									
								</td>
								<td>
									<select name="queQuanHuyen" id="queQuanHuyen" class="form-control" 
											onchange='getXa(this.id, this.value);'
											onfocus='getHuyenFocus(this.id)'>
									</select>
								</td>
								<td>
									<select name="queQuanXa" id="queQuanXa" 
											onfocus='getXaFocus(this.id);'
											class="form-control">
									</select>
								</td>
							</tr>
							<tr>
								<td>Nơi cấp</td>
								<td>
									<select name="noiCapTinh" id="noiCapTinh" class='form-control'>
										<option id="option" value="">Chọn tỉnh</option>
										<c:forEach items='${dsTinh}' var='tinh'>
											<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
										</c:forEach>
									</select>
								</td>
								<td>
									<select name="noiCapHuyen" id="noiCapHuyen" class="form-control" 
											onchange='getXa(this.id, this.value);'
											onfocus='getHuyenFocus(this.id)'>
									</select>
								</td>
								<td>
									<select name="noiCapXa" id="noiCapXa" 
											onfocus='getXaFocus(this.id);'
											class="form-control">
									</select>
								</td>
							</tr>
							<tr>
								<td>Ngày cấp</td>
								<td colspan="3"><input type='date' id='ngayCap' 
										name='ngayCap' class='form-control' />
								</td>
							</tr>
							<tr>
								<td>Số căn cước người cấp</td>
								<td colspan="3"><input type=text 
									onkeypress='chenGachNgang(this.id, this.value);' 
									placeholder="Số căn cước" 
									oninput='kiemTraDoDai(this.id, this.value);' 
									onblur='getThongTinCCCD(this.id, this.value);' 
									onkeyup = 'kiemTraSo(this.id, this.value);' 
									id='soCCNguoiDuyet' 
									name='soCCNguoiDuyet' class='form-control' />
								</td>
							</tr>
								<tr>
								<td>Họ tên người cấp</td>
								<td colspan="3"><input type=text id='hoTenNguoiDuyet' 
									name='hoTenNguoiDuyet' class='form-control' disabled/></td>
							</tr>
						</table>
					</div>
					<div class="row mt20">
						<table border="1">
							<tr><th colspan="2">THÔNG TIN NGƯỜI YÊU CẦU LÀM KHAI SINH</th></tr>
							<tr>
								<td>Số căn cước người yêu cầu</td>
								<td style='width: 75%;'><input type=text 
									onkeypress='chenGachNgang(this.id, this.value);' 
									oninput='kiemTraDoDai(this.id, this.value);' 
									onblur='getThongTinCCCD(this.id, this.value);' 
									onkeyup = 'kiemTraSo(this.id, this.value);'
									id='soCCNguoiYeuCau' 
									name='soCCNguoiYeuCau' class='form-control' />
								</td>
							</tr>
							<tr>
								<td>Quan hệ với người được khai sinh</td>
								<td><input type=text 
									id='quanHeVoiNguoiKS' 
									name='quanHeVoiNguoiKS' class='form-control' />
								</td>
							</tr>
							<tr>
								<td>Họ, chữ đêm, tên người yêu cầu</td>
								<td colspan="3"><input type=text 
									id='hoTenNguoiYeuCau' 
									name='hoTenNguoiYeuCau' class='form-control' disabled />
								</td>
							</tr>
							<tr>
								<td>Nơi thường trú</td>
								<td><input type=text 
									id='thuongTruNguoiYeuCau' 
									name='thuongTruNguoiYeuCau' class='form-control' disabled/>
								</td>
							</tr>
							<tr>
								<td>Nơi cấp</td>
								<td><input type=text 
									id='noiCapCCNguoiYeuCau' 
									name='noiCapCCNguoiYeuCau' class='form-control' disabled />
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="col-md-6">
					<div class="row">
						<table border="1">
							<tr><th colspan="2">THÔNG TIN CHA</th></tr>
							<tr>
								<td>Số căn cước cha</td>
								<td style='width: 75%;'><input type=text 
									onkeypress='chenGachNgang(this.id, this.value);' 
									oninput='kiemTraDoDai(this.id, this.value);' 
									onblur='getThongTinCCCD(this.id, this.value);' 
									onkeyup = 'kiemTraSo(this.id, this.value);'
									id='soCCCha' 
									name='soCCCha' class='form-control' />
								</td>
							</tr>
							<tr>
								<td>Họ, chữ đêm, tên cha</td>
								<td><input type=text 
									id='hoTenCha'
									name='hoTenCha' class='form-control' disabled />
								</td>
							</tr>
							<tr>
								<td>Năm sinh</td>
								<td><input type=text 
									id='namSinhCha' 
									name='namSinhCha' class='form-control' disabled />
								</td>
							</tr>
							<tr>
								<td>Dân tộc</td>
								<td><input type=text 
									id='danTocCha' 
									name='danTocCha' class='form-control' disabled />
								</td>
							</tr>
							<tr>
								<td>Quốc tịch</td>
								<td><input type=text 
									id='quocTichCha' 
									name='quocTichCha' class='form-control' disabled />
								</td>
							</tr>
							<tr>
								<td>Nơi thường trú</td>
								<td><input type=text 
									id='thuongTruCha'
									name='thuongTruCha' class='form-control' disabled />
								</td>
							</tr>
							<tr>
								<td>Nơi cấp</td>
								<td><input type=text 
									id='noiCapCCCha' 
									name='noiCapCCCha' class='form-control' disabled />
								</td>
							</tr>
						</table>
					</div>
					<div class="row mt20">
						<table border="1">
							<tr><th colspan="2">THÔNG TIN MẸ</th></tr>
							<tr>
								<td>Số căn cước mẹ</td>
								<td style='width: 75%;'><input type=text 
									onkeypress='chenGachNgang(this.id, this.value);' 
									oninput='kiemTraDoDai(this.id, this.value);' 
									onblur='getThongTinCCCD(this.id, this.value);' 
									onkeyup = 'kiemTraSo(this.id, this.value);'
									id='soCCMe' 
									name='soCCMe' class='form-control' />
								</td>
							</tr>
							<tr>
								<td>Họ, chữ đêm, tên mẹ</td>
								<td><input type=text 
											id="hoTenMe" 
											name='hoTenMe' class='form-control' disabled />
								</td>
							</tr>
							<tr>
								<td>Năm sinh</td>
								<td><input type=text 
											id='namSinhMe' 
											name='namSinhMe' class='form-control' disabled />
								</td>
							</tr>
							<tr>
								<td>Dân tộc</td>
								<td><input type=text 
									id='danTocMe' 
									name='danTocMe' class='form-control' disabled />
								</td>
							</tr>
							<tr>
								<td>Quốc tịch</td>
								<td><input type=text 
									id='quocTichMe' 
									name='quocTichMe' class='form-control' disabled />
								</td>
							</tr>
							<tr>
								<td>Nơi thường trú</td>
								<td colspan="3"><input type=text 
									id="thuongTruMe" 
									name='thuongTruMe' class='form-control' disabled />
								</td>
							</tr>
							<tr>
								<td>Nơi cấp</td>
								<td><input type=text 
									id="noiCapCCMe" 
									name='noiCapCCMe' class='form-control' disabled />
								</td>
							</tr>
						</table>
					</div>
				</div>
			
				<div class="row mt20 mb20">
					<div class="col-md-12 text-center">
						<button type='submit' class='btn btn-primary'>Thêm</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</form>
<script src="resources/js/NhapKhaiSinh.js">
</script>
<script>
	function getThongTinCCCDNhapKhaiSinh(vlue){
		var jHoTen = "";
		var jNgaySinh = "";
		var jGioiTinh = "";
		var jQuocTich = "Việt Nam";
		var data = "soCC="+vlue;
		$.ajax({url: 'get-thong-tin-cccd-nhap-khai-sinh',
			type: 'GET',
			data: data,
		  	success: function(result){
		  		if(result.length > 1){
		  		var ketQua = result.split("_");
			  		jHoTen = ketQua[0];
			  		jNgaySinh = ketQua[1];
			  		jGioiTinh = ketQua[2];
			  		jQuocTich = ketQua[3];
		  		}
		  		$("#hoTen").val(jHoTen);
		  		var namThangNgay = jNgaySinh.split("-");
				$("#ngaySinh").val(namThangNgay[2]+"-"+namThangNgay[1]+"-"+namThangNgay[0]);
				if(jGioiTinh == "Nữ"){
					$("#nu").prop("checked",true);
				} else {
					$("#nam").prop("checked",true);
				}
				$("#quocTich").val(jQuocTich);
	    }});	
	}
</script>
</sec:authorize>
 
<div class="container">
	<div class="row">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>
</body>
</html>