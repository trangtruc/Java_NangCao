<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="resources/js/dangky-cccd.js"></script>
<script src="resources/js/NhapKhaiSinh.js"></script>
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Duyệt Thêm Nhân Khẩu</title>
</head>
<body>
<div class="container">
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
</div>
<div class="container" style="min-height:364px">
	
	<div class="row bg-content mt20">
		
		<div class="col-md-3 ">
			<jsp:include page="menu-trai.jsp"></jsp:include>
		</div>
		<div class="col-md-9 bg-white mt5">
			<div class="row text-center text-orange title">
				Đơn Thêm Nhân Khẩu Đã Duyệt
			</div>
			<form name="thongTin" action="duyet-dang-ky-them-nhan-khau?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" method="POST"  >
			    <div class="col-md-12 ">
			    	<div class="row col-md-12 mb10">
					    <table border="1" class="thongtin">
				            <tbody>
					           <tr>
					           		<th colspan="7">Thông tin sổ hộ khẩu được đăng ký thêm nhân khẩu</th>
					           </tr>
					           <tr>
					           		<th style="width:15%;">Số Hộ Khẩu</th>
					           		<th style="width:15%;">Số Khai Sinh</th>
			            			<th style="width:15%;">Số Căn Cước</th>
			            			<th style="width:18%;">Họ Tên</th>
			            			<th style="width:22%;">Tình Trạng nhân khẩu </th>
			            			<th style="width:15%;">Quan Hệ Với Chủ Hộ</th>
			            		</tr>
			            		${dsNhanKhau};
			            		
			                </tbody>
			            </table>
		            </div>
		            <div class="row col-md-12 mb10">
					    <table border="1" class="thongtin">
				            <tbody>
					           <tr>
					           		<th colspan="7">Thông tin nhân khẩu được đăng ký</th>
					           </tr>
					           <tr>
					           		<th style="width:15%;">Số Hộ Khẩu Cũ</th>
					           		<th style="width:15%;">Số Khai Sinh</th>
			            			<th style="width:15%;">Số Căn Cước</th>
			            			<th style="width:18%;">Họ Tên</th>
			            			<th style="width:22%;">Tình trạng nhân khẩu </th>
			            			<th style="width:15%;">Quan Hệ Với Chủ Hộ</th>
			            		</tr>
			            		${dsNhanKhauDK};
			            		
			                </tbody>
			            </table>
		            </div>
				</div>
				
				<div class="row col-md-12 mt20 mb5">
					<span class='pull-left' style='margin-left: 10px'><button type=button onclick="goBack()" class='btn btn-primary'>Quay lại</button></span>
				</div>
				</div>
			</form>
			
	</div>
</div>


<div class="container">
	<div class="row">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>

</body>
</html>