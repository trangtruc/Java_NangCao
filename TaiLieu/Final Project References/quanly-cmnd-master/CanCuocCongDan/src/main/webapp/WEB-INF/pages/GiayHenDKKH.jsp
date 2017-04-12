<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="include.jsp"></jsp:include>
	<title>${tieuDe}</title>
	<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.12.0/semantic.min.css">
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="row text-center">
			<div class="error ml5"><h3>${thongBao}</h3></div>
		</div>
	</div>
	<c:if test="${ttdkKetHon.soDK != null}">
	<div class="container">
	<div class="row mt20">
			<div class="message">
				<div class="row text-center">
					<h2>CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM</h2>
					<label>Độc lập - Tự do - Hạnh Phúc</label>
					<p class="title">**********</p>
				</div>
				<div class="row mt20 text-center">
							<h2>GIẤY HẸN</h2>
							<h3 style='margin-top:-10px;'>Làm thủ tục kết hôn</h3>
				</div>
				<div class="row mt20">
				<div class="col-md-2"></div>
				<div class="col-md-8">
							<div class="row">
								<div class="col-md-12">
									Mã số đăng ký:  <b class="text-red">${ttdkKetHon.soDK}</b>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									Họ Tên:  <b>${cccdNguoiDK.hoTen}</b>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									Ngày sinh:  <b>${cccdNguoiDK.ngaySinh}</b>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									Giới tính:  <b>${cccdNguoiDK.gioiTinh}</b>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									Ngày hẹn:  <b>${ttdkKetHon.ngayHenLamViec}</b> (lưu ý: giấy hẹn có hiệu lực ${hetHan} ngày kể từ ngày hẹn)
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									Địa điểm:  <b>UBND ${diaDiem.tenXa} - ${diaDiem.huyen.tenHuyen} - ${diaDiem.huyen.tinh.tenTinh}</b>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									Khi đi mang theo:
									 <b>${giayTo}</b>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		<div class="row mt40" id="button">
			<c:if test="${empty print}">
				<div class="col-md-12 text-center">
						<a href="<c:url value="/"></c:url>" ><button class="btn btn-primary">về trang chủ</button></a>
<!-- 						<a href="in-giay-hen" target="_blank" class="btn btn-primary" >Tạo bản in</a> -->
				</div>
			</c:if>
			<c:if test="${not empty print }">
				<div class="col-md-12 text-center">
<!-- 						<button class="btn btn-primary" onclick="inPage()">In</button> -->
						<button class="btn btn-primary" onclick="window.close()">Đóng</button>
				</div>
			</c:if>
		</div>
</div>
</c:if>
<script src="resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="resources/js/jspdf.min.js"></script>
	<script type="text/javascript" src="resources/js/html2canvas.min.js"></script>
	<script type="text/javascript" src="resources/js/app.js"></script>
</body>
</html>