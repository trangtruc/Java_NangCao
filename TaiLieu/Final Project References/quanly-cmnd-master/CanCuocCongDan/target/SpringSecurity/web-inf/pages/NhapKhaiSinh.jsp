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
<div >
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
</div>

<form name="themGiayKhaiSinh" action="them-giay-khai-sinh" onsubmit='return onSubmitCheckNull();'>
<div class="container" style="min-height:400px">
	<div class="mt80">
		<div class="text-center col-md-12">
			<div class="text-orange title">
					ĐĂNG KÝ KHAI SINH
			</div>
		</div>
	</div>
	
	<div class="row mt40 mb40">
		<div class="col-md-6 mt20">
			<div class="row">
				<table border="1">
					<tr><th colspan="4">THÔNG TIN NGƯỜI ĐƯỢC KHAI SINH</th></tr>
					<tr><th>Thuộc tính</th><th style="width:20%" colspan="3" >Dữ liệu</th></tr>
					<tr>
						<td>Họ, chữ đệm, tên</td>
						<td colspan="3"><input type=text id='hoTen' name='hoTen' class='form-control' /></td>
					</tr>
					<tr>
						<td>Ngày, tháng, năm sinh</td>
						<td colspan="3">
							<input type='date' id='ngaySinh' 
								name=ngaySinh class='form-control' />
						</td>
					</tr>
					<tr>
						<td>Ngày, tháng, năm sinh bằng chữ</td>
						<td colspan="3"><input type=text name='ngaySinhChu' class='form-control' disabled/></td>
					</tr>
					<tr>
						<td>Giới tính</td>
						<td colspan="3">
							<input type=radio name=gioiTinh value="Nam" checked/>Nam &nbsp;&nbsp;&nbsp;&nbsp; 
							<input type=radio name=gioiTinh value="Nữ" />Nữ
						</td>
					</tr>
					<tr>
						<td>Quốc tịch</td>
						<td colspan="3"><input type=text name=quocTich 
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
						<td rowspan="2">Nơi sinh</td>
						<td>
							<select name=noiSinhTinh id=noiSinhTinh class='form-control' onchange='getHuyen(this.id, this.value);'>
								<option id="option" value="">Chọn tỉnh</option>
								<c:forEach items='${dsTinh}' var='tinh'>
									<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<select name="noiSinhHuyen" id="noiSinhHuyen" class="form-control" onchange='getXa(this.id, this.value);'>
							</select>
						</td>
						<td>
							<select name="noiSinhXa" id="noiSinhXa" class="form-control" onchange='getXa(this.id, this.value);'>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="3"><input type=text id='benhVien' 
								placeholder="Nhập tên bệnh viện nơi sinh (Nếu có)"
								name='benhVien' class='form-control'/></td>
					</tr>
					<tr>
						<td>Quê quán</td>
						<td width="1%">
							<select name=queQuanTinh id=queQuanTinh class='form-control' onchange='getHuyen(this.id, this.value);'>
								<option id="option" value="">Chọn tỉnh</option>
								<c:forEach items='${dsTinh}' var='tinh'>
									<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
								</c:forEach>
							</select>
							
						</td>
						<td>
							<select name="queQuanHuyen" id="queQuanHuyen" class="form-control" onchange='getXa(this.id, this.value);'>
							</select>
						</td>
						<td>
							<select name="queQuanXa" id="queQuanXa" class="form-control" onchange='getXa(this.id, this.value);'>
							</select>
						</td>
					</tr>
					<tr>
						<td>Nơi cấp</td>
						<td colspan="3">
							<select name=noiCap id=noiCap class='form-control' onchange='getHuyen(this.id, this.value);'>
								<option id="option" value="">Chọn tỉnh</option>
								<c:forEach items='${dsTinh}' var='tinh'>
									<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
								</c:forEach>
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
							oninput='kiemTraDoDai(this.id, this.value);' 
							onblur='getThongTinCCCD(this.id, this.value);' 
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
					<tr><th >Thuộc tính</th><th style="width:20%" >Dữ liệu</th></tr>
					<tr>
					<tr>
						<td>Số căn cước người yêu cầu</td>
						<td colspan="3"><input type=text 
							onkeypress='chenGachNgang(this.id, this.value);' 
							oninput='kiemTraDoDai(this.id, this.value);' 
							onblur='getThongTinCCCD(this.id, this.value);' 
							id='soCCNguoiYeuCau'
							name='soCCNguoiYeuCau' class='form-control' />
						</td>
					</tr>
					<tr>
						<td>Quan hệ với người được khai sinh</td>
						<td colspan="3"><input type=text 
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
						<td colspan="3"><input type=text 
							id='thuongTruNguoiYeuCau' 
							name='thuongTruNguoiYeuCau' class='form-control' disabled/>
						</td>
					</tr>
					<tr>
						<td>Nơi cấp</td>
						<td colspan="3"><input type=text 
							id='noiCapCCNguoiYeuCau' 
							name='noiCapCCNguoiYeuCau' class='form-control' disabled />
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="col-md-6 mt20">
			<div class="row mt3">
				<table border="1">
					<tr><th colspan="2">THÔNG TIN CHA</th></tr>
					<tr><th >Thuộc tính</th><th style="width:20%" >Dữ liệu</th></tr>
					<tr>
					<tr>
						<td>Số căn cước cha</td>
						<td colspan="3"><input type=text 
							onkeypress='chenGachNgang(this.id, this.value);' 
							oninput='kiemTraDoDai(this.id, this.value);' 
							onblur='getThongTinCCCD(this.id, this.value);' 
							id='soCCCha' 
							name='soCCCha' class='form-control' />
						</td>
					</tr>
					<tr>
						<td>Họ, chữ đêm, tên cha</td>
						<td colspan="3"><input type=text 
							id='hoTenCha'
							name='hoTenCha' class='form-control' disabled />
						</td>
					</tr>
					<tr>
						<td>Năm sinh</td>
						<td colspan="3"><input type=text 
							id='namSinhCha' 
							name='namSinhCha' class='form-control' disabled />
						</td>
					</tr>
					<tr>
						<td>Dân tộc</td>
						<td colspan="3"><input type=text 
							id='danTocCha' 
							name='danTocCha' class='form-control' disabled />
						</td>
					</tr>
					<tr>
						<td>Quốc tịch</td>
						<td colspan="3"><input type=text 
							id='quocTichCha' 
							name='quocTichCha' class='form-control' disabled />
						</td>
					</tr>
					<tr>
						<td>Nơi thường trú</td>
						<td colspan="3"><input type=text 
							id='thuongTruCha'
							name='thuongTruCha' class='form-control' disabled />
						</td>
					</tr>
					<tr>
						<td>Nơi cấp</td>
						<td colspan="3"><input type=text 
							id='noiCapCCCha' 
							name='noiCapCCCha' class='form-control' disabled />
						</td>
					</tr>
				</table>
			</div>
			<div class="row mt20">
				<table border="1">
					<tr><th colspan="2">THÔNG TIN MẸ</th></tr>
					<tr><th >Thuộc tính</th><th style="width:20%" >Dữ liệu</th></tr>
					<tr>
					<tr>
						<td>Số căn cước mẹ</td>
						<td colspan="3"><input type=text 
							onkeypress='chenGachNgang(this.id, this.value);' 
							oninput='kiemTraDoDai(this.id, this.value);' 
							onblur='getThongTinCCCD(this.id, this.value);' 
							id='soCCMe' 
							name='soCCMe' class='form-control' />
						</td>
					</tr>
					<tr>
						<td>Họ, chữ đêm, tên cha</td>
						<td colspan="3"><input type=text 
							id="hoTenMe" 
							name='hoTenMe' class='form-control' disabled />
						</td>
					</tr>
					<tr>
						<td>Năm sinh</td>
						<td colspan="3"><input type=text 
							id='namSinhMe' 
							name='namSinhMe' class='form-control' disabled />
						</td>
					</tr>
					<tr>
						<td>Dân tộc</td>
						<td colspan="3"><input type=text 
							id='danTocMe' 
							name='danTocMe' class='form-control' disabled />
						</td>
					</tr>
					<tr>
						<td>Quốc tịch</td>
						<td colspan="3"><input type=text 
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
						<td colspan="3"><input type=text 
							id="noiCapCCMe" 
							name='noiCapCCMe' class='form-control' disabled />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12 text-center">
			<button type='submit' class='btn btn-primary'>THÊM</button>
		</div>
	</div>
</div>
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
</body>
</html>