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
<c:if test="${ssTaiKhoan == null}">
	<script>
		window.location = "login";
	</script>
</c:if>
<div class="container">
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
</div>
<sec:authorize access="!hasRole('CAP_NHAT_TTDK_CCCD')">
	<div class="container mt20" style="min-height:380px">
		<div class="mt80">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('CAP_NHAT_TTDK_CCCD')">
<div class="container" style="min-height:380px;min-width:800px;">
	<div class="row bg-content">
		<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
		</div>
		<div class="col-md-9 radius-01 bg-white">
		<div class="col-md-12">
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
			<c:if test="${not empty yes }">
				<div class="row">
					<div class="title">Thông tin đăng ký</div>
					<table border="0">
						<tr>
							<td><label>Mã số đăng ký:</label></td>
							<td colspan=5><label><font color=red size=6 >${maSo }</font></label></td>
						</tr>
						<tr>
							<td><label>Số khai sinh:</label></td><td>${cccdTam.soKhaiSinh }</td>
							<td><label>Số CMND/CCCD: </label></td><td>
							
							<c:if test="${cccdTam.soCC != '000000000000' }">
								${cccdTam.soCC }
							</c:if>
							
							</td>
							<td><label>Số hộ khẩu: </label></td><td>${shk.soHK }</td>
						</tr>
						<tr>
							<td><label>Họ tên:</label></td><td>${cccdTam.hoTen }</td>
							<td><label>Ngày sinh:</label></td><td>${cccdTam.ngaySinh }</td>
							<td><label>Giới tính:</label></td><td>${cccdTam.gioiTinh }</td>
						</tr>
					</table>
				</div>
				<hr>
				<form action='cap-nhat-thong-tin-dang-ky?${_csrf.parameterName}=${_csrf.token}' enctype="multipart/form-data" method="POST">
					<div class="row">
						<div class="title">cập nhật hình ảnh</div>
						<table border=0>
							<tr>
								<td><label>Hình thẻ</label></td><td><img src='hinh-the-ttdk?id=${maSo}' width=100 height=60 /></td>
								<td><label>Hình vân tay trỏ trái</label></td><td><img src='van-tay-tro-trai-ttdk?id=${maSo}' width=100 height=60 /></td>
								<td><label>Hình vân tay trỏ phải</label></td><td><img src='van-tay-tro-phai-ttdk?id=${maSo }' width=100 height=60/></td>
							</tr>
							<tr>
								<td colspan="2"><input type=file name='hinhThe' class='form-control' /></td>
								<td colspan="2"><input type=file name='vanTayTroTrai' class='form-control' /></td>
								<td colspan="2"><input type=file name='vanTayTroPhai' class='form-control' /></td>
							</tr>
						</table>
					</div>
					<hr>
					<div class="row">
							<div class="title">cập nhật thông tin</div>
							<table border=0>
								<tr>
									<td><label>Đặc điểm dị hình:</label></td><td><input type=text name=dacDiem class='form-control' value="${cccdTam.nhanDang }" /></td>
									<td><label>Họ tên khác:</label></td><td><input type=text name=hoTenKhac class='form-control' value="${cccdTam.hoTenKhac }" /></td>
								</tr>
								<tr>
									<td><label>Tôn giáo:</label></td><td><input type=text name=tonGiao class='form-control' value="${cccdTam.tonGiao }" /></td>
									<td><label>Trình độ học vấn:</label></td><td><input type=text name=trinhDo class='form-control' value="${cccdTam.trinhDo }" /></td>
								</tr>
								<tr>
									<td><label>Nghề nghiệp</label></td><td><input type=text name=ngheNghiep class='form-control' value="${cccdTam.ngheNghiep }" /></td>
									<td><label>Nhóm máu</label></td><td>
										<select name=nhomMau class='form-control'>
											${nm }
										</select>
									</td>
								</tr>
								<tr>
									<td><label>Nơi ở hiện tại</label></td>
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
					<hr>
					<div class="row">
						<div class="title">dịch vụ</div>
						<table border=0>
							<tr>
								<td><label>Chuyển phát</label></td>
								<td>
									<div class="checkboxFour">
									  <input type="checkbox" value="1" id="checkboxFourInput" name="chuyenPhat" class="display-none" ${chuyenPhat }/>
									  <label for="checkboxFourInput"></label>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<hr>
					<div class="row">
						<div class="title">kết quả xác minh</div>
						<table border=0>
							<tr>
								<td><label>Kết quả</label></td>
								<td>
									<input type=text name="ketQuaXacMinh" size=100% class="form-control" value="${cccdTam.ketQuaXacMinh }">
								</td>
							</tr>
						</table>
					</div>
					<div class="row">
						<div class="col-md-12 text-center">
							<input type=hidden name=maSoTTDK value='${maSo}' /> 
							<button type=button onclick="goBack()" class='btn btn-primary'>Quay lại</button>
							<button type=submit id="luu" class='btn btn-primary' disabled>Lưu</button>
							<sec:authorize access="hasRole('XAC_NHAN_TTDK_CCCD')">
								<button type=button onclick="duyetThongTinDangKy('${maSo}')" class='btn btn-primary' ${disabled }>Xác nhận</button>
							</sec:authorize>
						</div>
					</div>
				</form>
			</c:if>
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
.checkboxFour {
    width: 25px;
    margin: 20px 100px;
    position: relative;
}
.checkboxFour label {
    cursor: pointer;
    width: 23px;
    height: 20px;
    top: 0;
    left: 0;
    background: #eee;
    border:1px solid #ddd;
}
.checkboxFour label:after {
    opacity: 0.2;
    content: '';
    position: absolute;
    width: 9px;
    height: 5px;
    background: transparent;
    top: 6px;
    left: 7px;
    border: 3px solid #333;
    border-top: none;
    border-right: none;
 
    -webkit-transform: rotate(-45deg);
    -moz-transform: rotate(-45deg);
    -o-transform: rotate(-45deg);
    -ms-transform: rotate(-45deg);
    transform: rotate(-45deg);
}
.checkboxFour label:hover::after {
    opacity: 0.5;
}
.checkboxFour input[type=checkbox]:checked + label:after {
    opacity: 1;
}
</style>
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
})

function duyetThongTinDangKy(maSo){
	if($("#luu").attr("change-value") == '1'){
		if(!confirm("Bạn chưa lưu các thay đổi, bạn có muốn tiếp tục hành động này không?")){
			return false;
		}
	}
	if(!confirm("Bạn có chắc chắn thực hiện điều này?")){
		return false;
	}
	var duyet = "maSo="+maSo;
	$.ajax({url: 'duyet-thong-tin-dang-ky',
		type: 'GET',
		data: duyet,
	  	success: function(result){
	  		window.location = "danh-sach-dang-ky";
        }});
}

</script>
</body>
</html>