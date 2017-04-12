<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>GIẤY HẸN ĐĂNG KÝ SỔ HỘ KHẨU</title>
</head>
<body>
<div class="container">
	<div class="ui divider"></div>
	<form class="ui form" style="max-width: none; width: 1037px;">
	<div class="row">
			<div >
				<div class="message col-md-offset-1 bg-white mt40">
						<div class="row text-center">
							<label><h2>Giấy Hẹn</h2></label>
						</div>
					 	<div >
							
								<div style="margin-left: 30%">
									Mã số đăng ký:  
									<b class='text-red' style="margin-left: 60px">${maSo}</b>
								</div>
							
							
								<div style=" margin-left: 30%">
									Người Đăng Ký:  
								<!--  <div class='col-md-1'></div>-->
									<b style="margin-left: 50px">${hoTenNguoiDK}</b>
								</div>
						
								<div style="margin-left: 30%">
									Ngày làm việc :  
									<b style="margin-left: 115px">${ngayHen}</b>
								</div>
								
								<div style="margin-left: 30%">
									Địa Điểm:  
									<b style="margin-left: 115px">UBND ${diaDiem}</b>
								</div>
						
							
								<div style=" margin-left: 30%">
									Giấy Tờ Đi Kèm:
									<b style="margin-left: 47px">Giấy hẹn và Sổ hộ khẩu hiện tại</b>
								</div>
						
							<i style="color: red; font-size: medium;" id="luuy">Sau khi đăng ký, in giấy hẹn trước khi đến cơ quan làm việc</i>
						</div>
				</div>
			</div>
		</div>
		</form>
		<div class="row mt40">
			<span class="pull-left">
				<a href="http://localhost:8080/CanCuocCongDan/" ><button id='quayve' class="btn btn-primary">về trang chủ</button></a>
			</span>
			<span class="pull-right">
				<button class="btn btn-primary" id="create_pdf" onclick="luuy.style.display='none'">Lưu thành file</button>
				<input class="btn btn-primary" type="button" id="print_button"  value="In" onclick="this.style.display ='none';luuy.style.display ='none';quayve.style.display ='none';create_pdf.style.display ='none'; window.print()" />
			</span>
		</div>
</div>
<script src="resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="resources/js/jspdf.min.js"></script>
	<script type="text/javascript" src="resources/js/html2canvas.min.js"></script>
	<script type="text/javascript" src="resources/js/app.js"></script>
</body>
</html>