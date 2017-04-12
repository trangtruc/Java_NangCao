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
</div>
<div class="container">
	<div class="ui divider"></div>
	<form class="ui form" style="max-width: none; width: 1037px;">
	<div class="row">
			<div class="col-md-12">
				<div class="message">
						<div class="row text-center">
							<h2>CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM</h2>
							<label>Độc lập - Tự do - Hạnh Phúc</label>
							<p class="title">**********</p>
						</div>
						<div class="row text-center">
							<h2><label>${tieuDe}</label></h2>
						</div>
						<div class="col-md-2"></div>
					  <div class="col-md-8">
							<div class="row">
								<div class="col-md-12">
									Mã số đăng ký:  <b class="text-red">${ttdkKhaiSinh.soKS}</b>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									Họ Tên:  <b>${ttdkKhaiSinh.hoTen}</b>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									Ngày đăng ký:  <b>${ttdkKhaiSinh.ngayDangKy}</b>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									Ngày hẹn:  <b>${ttdkKhaiSinh.ngayHenLamViec}</b>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									Giấy Tờ Đi Kèm:
									 <b>${giayTo}</b>
								</div>
							</div>
						</div>
				</div>
			</div>
		</div>
		</form>
		<div class="row mt40">
			<div class="col-md-6">
				<span class="pull-right">
					<a href="http://localhost:8080/CanCuocCongDan/" ><button class="btn btn-primary">về trang chủ</button></a>
				</span>
			</div>
			<div class="col-md-6">
				<span class="pull-left">
					<button class="btn btn-primary" id="create_pdf">Lưu thành file</button>
				</span>
			</div>
		</div>
</div>
<script src="resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="resources/js/jspdf.min.js"></script>
	<script type="text/javascript" src="resources/js/html2canvas.min.js"></script>
	<script type="text/javascript" src="resources/js/app.js"></script>
</body>
</html>