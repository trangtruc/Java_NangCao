<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5.01 Transitional//EN" "http://www.w3.org/TR/html5/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<style>
	a {
		color: blue;
	}
</style>
</head>
<body>
<div class="container" style="border: 1px solid black; background-color: white; width: 700px; margin-top: 50px;">
	<div class="row text-center">
		<h2><label>CHI TIẾT KHAI SINH</label></h2>
	</div>
	<div class="row mt50">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-10 pull-left">
				<div class="row">
					<label class="col-md-5 text-right">Số khai sinh: </label>
					<span class="col-md-5 ml20">${khaiSinh.soKS}</span>
				</div>
				<div class="row">
					<label class="col-md-5 text-right">Họ tên: </label>
					<span class="col-md-5 ml20">${khaiSinh.hoTen}</span>
				</div>
				<div class="row">
					<label class="col-md-5 text-right">Ngày sinh: </label>
					<span class="col-md-5 ml20">${khaiSinh.ngaySinh}</span>
				</div>
				<div class="row">
					<label class="col-md-5 text-right">Giới tính: </label>
					<c:if test="${khaiSinh.gioiTinh == 'Nam'}">
						<span class="col-md-5 ml20">Nam</span>
					</c:if>
					<c:if test="${khaiSinh.gioiTinh != 'Nam'}">
						<span class="col-md-5 ml20">Nữ</span>
					</c:if>
				</div>
				<div class="row">
					<label class="col-md-5 text-right">Quốc tịch: </label>
					<span class="col-md-5 ml20">${khaiSinh.quocTich}</span>
				</div>
				<div class="row">
					<label class="col-md-5 text-right">Dân tộc: </label>
					<span class="col-md-5 ml20">${khaiSinh.danToc.tenDT}</span>
				</div>
				<div class="row">
					<label class="col-md-5 text-right">Bệnh viện: </label>
					<span class="col-md-5 ml20">${khaiSinh.benhVien}</span>
				</div>
				<div class="row">
					<label class="col-md-5 text-right">Nơi sinh: </label>
					<span class="col-md-5 ml20">
							${noiSinh.tenXa}, ${noiSinh.huyen.tenHuyen}, ${noiSinh.huyen.tinh.tenTinh}
					</span>
				</div>
				<div class="row">
					<label class="col-md-5 text-right">Quê quán: </label>
					<span class="col-md-5 ml20">
						${queQuan.tenXa}, ${queQuan.huyen.tenHuyen}, ${queQuan.huyen.tinh.tenTinh}
					</span>
				</div>
				<div class="row">
					<label class="col-md-5 text-right">Số căn cước người yêu cầu: </label>
					<span class="col-md-5 ml20" id="linkNguoiYeuCau">
						<a href="xem-thong-tin-cccd?soCC=${khaiSinh.soCCNguoiYeuCau}" target="_blank">
							${khaiSinh.soCCNguoiYeuCau}
						</a>
						<input type='hidden' value='${khaiSinh.soCCNguoiYeuCau}' id='soCCNguoiYeuCau'/>
					</span>
				</div>
				<div class="row">
					<label class="col-md-5 text-right">Quan hệ với người khai sinh: </label>
					<span class="col-md-5 ml20">${khaiSinh.quanHeVoiNguoiKS}</span>
				</div>
				<div class="row">
					<label class="col-md-5 text-right">Số căn cước cha: </label>
					<span class="col-md-5 ml20" id="linkCha">
						<a href="xem-thong-tin-cccd?soCC=${khaiSinh.soCCCha}" target="_blank">
							${khaiSinh.soCCCha}
						</a>
						<input type='hidden' value='${ttdkKhaiSinh.soCCCha}' id='soCCCha'/>
					</span>
				</div>
				<div class="row">
					<label class="col-md-5 text-right">Số căn cước mẹ: </label>
					<span class="col-md-5 ml20" id="linkMe">
						<a href="xem-thong-tin-cccd?soCC=${khaiSinh.soCCMe}" target="_blank">
							${khaiSinh.soCCMe}
						</a>
						<input type='hidden' value='${ttdkKhaiSinh.soCCMe}' id='soCCMe'/>
					</span>
				</div>
				<div class="row">
					<label class="col-md-5 text-right">Nơi cấp: </label>
					<span class="col-md-5 ml20">UBND ${xaNoiCap.tenXa}</span>
				</div>
				<div class="row">
					<label class="col-md-5 text-right">Ngày cấp: </label>
					<span class="col-md-5 ml20">${duyetDKKS.ngayDuocDuyet}</span>
				</div>
				<div class="row">
					<label class="col-md-5 text-right">Số căn cước người duyệt: </label>
					<span class="col-md-5 ml20" id="linkNguoiDuyet">
						<a href="xem-thong-tin-cccd?soCC=${duyetDKKS.nguoiDuyet}" target="_blank">
							${duyetDKKS.nguoiDuyet}
						</a>
						<input type='hidden' value='${ttdkKhaiSinh.nguoiDuyet}' id='nguoiDuyet'/>
					</span>
				</div>
				<div id='button' class="row mt20" style="margin-bottom: 20px;">
					<div class="col-md-5 text-right">
					<button type=button style="width:120px" class='btn btn-primary' onclick="window.close();">
						Đóng
					</button>
					</div>
					<form   action="giay-khai-sinh?${_csrf.parameterName}=${_csrf.token}"
							enctype="multipart/form-data"
							method="POST" >
						<button type=submit style="width:120px" class='btn btn-primary' onclick="inTrang();">
							In
						</button>
						<input type=hidden name=soKS value="${khaiSinh.soKS}"/>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function inTrang() {
		$("#linkNguoiYeuCau").html($("#soCCNguoiYeuCau").val());
		$("#linkCha").html($("#soCCCha").val());
		$("#linkMe").html($("#soCCMe").val());
		$("#linkNguoiDuyet").html($("#nguoiDuyet").val());
		//inPage();
	}
</script>
</body>
</html>