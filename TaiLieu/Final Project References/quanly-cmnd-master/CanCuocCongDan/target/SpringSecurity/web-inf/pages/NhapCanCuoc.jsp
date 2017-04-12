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
<div class="container mt20" style="min-height:380px">
	<div class="mt80">
		<div class="text-center col-md-12">
			<div class="text-orange title">
					NHẬP CHỨNG MINH THƯ ĐIỆN TỬ
			</div>
		</div>
	</div>
	<div class="row mt40 mb40">
		<div class="col-md-6 mt20">
			<div class="row">
				<table border="1">
					<tr><th colspan="2">THÔNG TIN CÁ NHÂN</th></tr>
					<tr><th >thuộc tính</th><th style="width:20%" >dữ liệu</th></tr>
					<tr><td>Số CMND/CCCD </td><td><input type=text name=soCC id=soCC class='form-control' oninput='kiemTraDoDai(this.id, this.value);' onkeypress='chenGachNgang(this.id, this.value);' /></td></tr>
					<tr><td>Đặc điểm dị hình</td><td><input type=text name=dacDiem class='form-control' /></td></tr>
					<tr><td>Họ tên</td><td><input type=text name=hoTen class='form-control' /></td></tr>
					<tr><td>Họ tên khác</td><td><input type=text name=hoTenKhac class='form-control' /></td></tr>
					<tr><td>Ngày sinh</td><td><input type=date name=ngaySinh class='form-control' /></td></tr>
					<tr><td>Giới tính</td><td><input type=radio name=gioiTinh value="Nam" checked />Nam &nbsp;&nbsp;&nbsp;&nbsp; <input type=radio name=gioiTinh value="Nữ" />Nữ</td></tr>
					<tr><td>Quốc tịch</td><td><input type=text name=quocTich class='form-control' /></td></tr>
					<tr><td>Dân tộc</td><td><select name=danToc class='form-control'>
						<c:forEach items='${dsDanToc}' var='danToc'>
							<option id="option" value="${danToc.maDT}">${danToc.tenDT}</option>
						</c:forEach>
					</select></td></tr>
					<tr><td>Tôn giáo</td><td><input type=text name=tonGiao class='form-control' /></td></tr>
					<tr><td>Nhóm máu</td><td><select name=nhomMau class='form-control'>
						<c:forEach items='${dsNhomMau}' var='nhomMau'>
							<option id="option" value="${nhomMau.maNM}">${nhomMau.tenNM}</option>
						</c:forEach>
					</select></td></tr>
					<tr><td>Hôn nhân</td><td><input type=radio name=honNhan value="1" />Đã kết hôn &nbsp;&nbsp;&nbsp;&nbsp; <input type=radio name=honNhan value="0" />Chưa kết hôn</td></tr>
					<tr><td>Trình độ</td><td><input type=text name=trinhDo class='form-control' /></td></tr>
					<tr><td>Nghề nghiệp</td><td><input type=text name=ngheNghiep class='form-control' /></td></tr>
				</table>
			</div>
			<div class="row mt20">
				<table border="1">
					<tr><th colspan="2">THÔNG TIN CMND/CCCD</th></tr>
					<tr><th >thuộc tính</th><th style="width:20%" >dữ liệu</th></tr>
					<tr>
					<td>Nơi cấp</td>
					<td>
						<select name=noiCapTinh id=noiCapTinh class='form-control'>
							<c:forEach items='${dsTinh}' var='tinh'>
								<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
							</c:forEach>
						</select>
					</td>
					</tr>
					<tr><td>Ngày cấp</td><td><input type=date name=ngayCap class='form-control' /></td></tr>
					<tr><td>Lần cấp</td><td><input type=number name=lanCap class='form-control' /></td></tr>
					<tr><td>Tình trạng</td>
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
		<div class="col-md-6 mt20">
			<div class="row">
				<table border="1">
					<tr><th colspan="2">HÌNH ẢNH</th></tr>
					<tr><th >thuộc tính</th><th style="width:20%" >dữ liệu</th></tr>
					<tr><td>Hình Đại Diện</td><td><input type=file name='hinhAnh' class='form-control' /></td></tr>
					<tr><td>Hình Vân Tay</td><td><input type=file name='vanTay' class='form-control' /></td></tr>
				</table>
			</div>
			<div class="row mt20">
				<table border="1">
					<tr><th colspan="4">ĐỊA CHỈ</th></tr>
					<tr><th rowspan="2">thuộc tính</th><th style="width:20%" colspan="3">dữ liệu</th></tr>
					<tr><th>Tỉnh</th><th>Huyện</th><th>Xã</th></tr>
					<tr>
					<td>Quê quán</td>
					<td>
						<select name=queQuanTinh id=queQuanTinh class='form-control' onchange='getHuyen(this.id, this.value);'>
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
						<select name="queQuanXa" id="queQuanXa" class="form-control">
						</select>
					</td>
					</tr>
					<tr>
					<td>Đăng ký Khai Sinh</td>
					<td>
						<select name=khaiSinhTinh id=khaiSinhTinh class='form-control' onchange='getHuyen(this.id, this.value);'>
							<c:forEach items='${dsTinh}' var='tinh'>
								<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select name="khaiSinhHuyen" id="khaiSinhHuyen" class="form-control" onchange='getXa(this.id, this.value);'>
						</select>
					</td>
					<td>
						<select name="khaiSinhXa" id="khaiSinhXa" class="form-control">
						</select>
					</td>
					</tr>
					<tr>
					<td>Thường trú</td>
					<td>
						<select name=thuongTruTinh id=thuongTruTinh class='form-control' onchange='getHuyen(this.id, this.value);'>
							<c:forEach items='${dsTinh}' var='tinh'>
								<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select name="thuongTruHuyen" id="thuongTruHuyen" class="form-control" onchange='getXa(this.id, this.value);'>
						</select>
					</td>
					<td>
						<select name="thuongTruXa" id="thuongTruXa" class="form-control">
						</select>
					</td>
					</tr>
					<tr>
					<td>Nơi ở hiện tại</td>
					<td>
						<select name=hienTaiTinh id=hienTaiTinh class='form-control' onchange='getHuyen(this.id, this.value);'>
							<c:forEach items='${dsTinh}' var='tinh'>
								<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select name="hienTaiHuyen" id="hienTaiHuyen" class="form-control" onchange='getXa(this.id, this.value);'>
						</select>
					</td>
					<td>
						<select name="hienTaiXa" id="hienTaiXa" class="form-control">
						</select>
					</td>
					</tr>
				</table>
			</div>
			<div class="row mt20">
				<table border="1">
					<tr><th colspan="2">THÔNG TIN GIA ĐÌNH</th></tr>
					<tr><th >thuộc tính</th><th style="width:20%" >dữ liệu</th></tr>
					<tr><td>Số CMND/CCCD của Cha</td><td><input type=text name='soCCCha' id='soCCCha' class='form-control' oninput='kiemTraDoDai(this.id, this.value);' onkeypress='chenGachNgang(this.id, this.value);' /></td></tr>
					<tr><td>Số CMND/CCCD của Mẹ</td><td><input type=text name='soCCMe' id='soCCMe' class='form-control' oninput='kiemTraDoDai(this.id, this.value);' onkeypress='chenGachNgang(this.id, this.value);' /></td></tr>
					<tr><td>Số CMND/CCCD của Vợ (Chồng)</td><td><input type=text name='soCCVoChong' id='soCCVoChong' class='form-control' oninput='kiemTraDoDai(this.id, this.value);' onkeypress='chenGachNgang(this.id, this.value);' /></td></tr>
					<tr><td>Số CMND/CCCD của Người ĐDHP</td><td><input type=text name='soCCDD' id='soCCDD' class='form-control' oninput='kiemTraDoDai(this.id, this.value);' onkeypress='chenGachNgang(this.id, this.value);' /></td></tr>
					<tr><td>Số CMND/CCCD của Chủ Hộ</td><td><input type=text name='soCCChuHo' id='soCCChuHo' class='form-control' oninput='kiemTraDoDai(this.id, this.value);' onkeypress='chenGachNgang(this.id, this.value);' /></td></tr>
					<tr><td>Mối quan hệ với Chủ hộ</td><td><input type=text name=quanHeChuHo class='form-control' /></td></tr>
					<tr><td>Số hộ khẩu</td><td><input type=text name=hoKhau id=hoKhau class='form-control' oninput='kiemTraDoDai(this.id, this.value);' onkeypress='chenGachNgang(this.id, this.value);' /></td></tr>
				</table>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12 text-center">
			<button type=button class='btn btn-primary' onclick='addCCCD();'>THÊM</button>
		</div>
	</div>
</div>
<script>
			
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