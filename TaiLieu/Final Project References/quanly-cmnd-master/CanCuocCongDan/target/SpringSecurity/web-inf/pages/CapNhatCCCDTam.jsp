<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CẬP NHẬT THÔNG TIN ĐĂNG KÝ</title>
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
					CẬP NHẬT THÔNG TIN ĐĂNG KÝ
			</div>
		</div>
	</div>
	<div class="row mt40 mb40">
		<div class="col-md-6 mt20">
			<div class="row">
				<table border="1">
					<tr><th colspan="2">THÔNG TIN ĐĂNG KÝ</th></tr>
					<tr><th >thuộc tính</th><th style='width:30%;'>dữ liệu</th></tr>
					<tr><td>Số khai sinh </td><td><input type=text name=soKhaiSinh id=soKhaiSinh class='form-control' value="${cccdTam.soKhaiSinh }" disabled/></td></tr>
					<tr><td>Số CMND/CCCD </td><td><input type=text name=soCC id=soCC class='form-control' value="${cccdTam.soCC }" disabled /></td></tr>
					<tr><td>Họ tên</td><td><input type=text name=hoTen class='form-control' value="${cccdTam.hoTen }" disabled/></td></tr>
					<tr><td>Ngày sinh</td><td><input type=text name=ngaySinh class='form-control' value="${cccdTam.ngaySinh }" disabled /></td></tr>
					<tr><td>Giới tính</td><td><input type=text name=gioiTinh class='form-control' value="${cccdTam.gioiTinh }" disabled /></td></tr>
					<tr><td>Quốc tịch</td><td><input type=text name=quocTich class='form-control' value="${cccdTam.quocTich }" disabled /></td></tr>
					<tr><td>Dân tộc</td><td><input type=text name=danToc class='form-control' value="${dsDanToc.tenDT }" disabled /></td></tr>
					<tr><td>Quê quán</td><td><input type=text style='font-size:small' class='form-control' name='queQuan' value="${queQuan.tenXa}, ${queQuan.getHuyen().tenHuyen}, ${queQuan.getHuyen().getTinh().tenTinh}" disabled /></td></tr>
					<tr><td>Khai sinh</td><td><input type=text style='font-size:small' class='form-control' name='khaiSinh' value="${khaiSinh.tenXa}, ${khaiSinh.getHuyen().tenHuyen}, ${khaiSinh.getHuyen().getTinh().tenTinh}" disabled /></td></tr>
					<tr><td>Số CMND/CCCD của Cha</td><td><input type=text name='soCCCha' id='soCCCha' class='form-control' value="${cha.soCC }" disabled /></td></tr>
					<tr><td>Họ tên Cha</td><td><input type=text class='form-control' value="${cha.hoTen }" disabled /></td></tr>
					<tr><td>Số CMND/CCCD của Mẹ</td><td><input type=text name='soCCMe' id='soCCMe' class='form-control' value="${me.soCC }" disabled /></td></tr>
					<tr><td>Họ tên Mẹ</td><td><input type=text class='form-control' value="${me.hoTen }" disabled /></td></tr>
					<tr><td>Số CMND/CCCD của Người ĐDHP</td><td><input type=text name='soCCDD' id='soCCDD' class='form-control' value="${dD.soCC }" disabled /></td></tr>
					<tr><td>Họ tên của Người ĐDHP</td><td><input type=text class='form-control' name="hoTenDD" value="${dD.hoTen }" disabled/></td></tr>
					<tr><td>Số CMND/CCCD của Chủ hộ</td><td><input type=text name='soCCChuHo' id='soCCChuHo' class='form-control' value="${chuHo.soCC }" disabled /></td></tr>
					<tr><td>Họ tên Chủ hộ</td><td><input type=text class='form-control' value="${chuHo.hoTen }" disabled /></td></tr>
					<tr><td>Mối quan hệ với Chủ hộ</td><td><input type=text name=quanHeChuHo class='form-control' value="${cccdTam.quanHeChuHo }" disabled /></td></tr>
					<tr><td>Số hộ khẩu</td><td><input type=text name=hoKhau id=hoKhau class='form-control' value="${cccdTam.soHoKhau }" disabled /></td></tr>
				</table>
			</div>
		</div>
		<form action='cap-nhat-thong-tin-dang-ky' method="POST">
		<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="col-md-6 mt20">
				<div class="row">
					<table border="1">
						<tr><th colspan="4">THÔNG TIN BỔ SUNG</th></tr>
						<tr><th >thuộc tính</th><th colspan="3" style="width:20%" >dữ liệu</th></tr>
						<tr><td>Hình Đại Diện</td><td colspan="3"><input type=file name='hinhAnh' class='form-control' /></td></tr>
						<tr><td>Hình Vân Tay</td><td colspan="3"><input type=file name='vanTay' class='form-control' /></td></tr>
						<tr><td>Đặc điểm dị hình</td><td colspan="3"><input type=text name=dacDiem class='form-control' /></td></tr>
						<tr><td>Họ tên khác</td><td colspan="3"><input type=text name=hoTenKhac class='form-control' value="${cccdTam.hoTenKhac }" /></td></tr>
						<tr><td>Tôn giáo</td><td colspan="3"><input type=text name=tonGiao class='form-control' value="${cccdTam.tonGiao }" /></td></tr>
						<tr><td>Nhóm máu</td><td colspan="3"><input type=text name=nhomMau class='form-control' value="${nm.tenNM }" /></td></tr>
						<tr><td>Trình độ học vấn</td><td colspan="3"><input type=text name=trinhDo class='form-control' value="${cccdTam.trinhDo }" /></td></tr>
						<tr><td>Nghề nghiệp</td><td colspan="3"><input type=text name=ngheNghiep class='form-control' value="${cccdTam.ngheNghiep }" /></td></tr>
						<tr><td>Thường trú</td>
							<td style="width:20%;">
								<select name=thuongTruTinh id=thuongTruTinh class='form-control' onclick='getHuyen(this.id, this.value);' onchange='getHuyen(this.id, this.value);'>
									${thuongTruTinh }
								</select>
							</td>
							<td style="width:20%;">
								<select name="thuongTruHuyen" id="thuongTruHuyen" class="form-control" onchange='getXa(this.id, this.value);'>
									${thuongTruHuyen }
								</select>
							</td>
							<td style="width:20%;">
								<select name="thuongTruXa" id="thuongTruXa" class="form-control">
									${thuongTruXa }
								</select>
							</td>
						</tr>
						<tr><td>Nơi ở hiện tại</td>
							<td>
								<select name=noiOHienTaiTinh id=noiOHienTaiTinh class='form-control' onclick='getHuyen(this.id, this.value);' onchange='getHuyen(this.id, this.value);'>
									${hienTaiTinh }
								</select>
							</td>
							<td>
								<select name="noiOHienTaiHuyen" id="noiOHienTaiHuyen" class="form-control" onchange='getXa(this.id, this.value);'>
									${hienTaiHuyen }
								</select>
							</td>
							<td>
								<select name="noiOHienTaiXa" id="noiOHienTaiXa" class="form-control">
									${hienTaiXa }
								</select>
							</td>
						</tr>
						</table>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 text-center">
					<button type=button class='btn btn-primary' onclick='saveCCCD();'>LƯU</button>
				</div>
			</div>
		</form>
	</div>

</div>
<script>
	function saveCCCD(){
		var dacDiem = $("#dacDiem").val();
		var hoTenKhac = $("#hoTenKhac").val();
		var str = "dacDiem="+dacDiem+"&hoTenKhac="+hoTenKhac;
		$.ajax({url: 'cap-nhat-thong-tin-dang-ky',
			type: 'GET',
			data: str,
		  	success: function(result){
    		  		window.location = "danh-sach-dang-ky";
	        }});
	}
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