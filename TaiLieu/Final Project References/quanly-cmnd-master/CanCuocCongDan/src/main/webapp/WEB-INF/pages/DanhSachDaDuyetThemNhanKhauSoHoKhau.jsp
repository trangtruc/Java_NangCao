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
	<title>Thêm Nhân Khẩu</title>
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
			<div class="row text-center title">
				Danh Sách Đăng Ký Thêm Nhân Khẩu Đã Được Duyệt
			</div>
		    
		    	<div class="row">
			    	<div class="col-md-12 ">
			    		<div class="col-md-7" style="min-width:120px;"></div>
					    <div class="col-md-5">
					    			<label class='mt5' style="font-size: 20px;"> Tìm kiếm</label>
					    			<div class="col-md-8">
						    			<input type=text name='timKiem' id=timKiem class='form-control' onkeyup='timKiem()' placeholder='Nhập mã HK cũ' />
					    			</div>
					    </div>
			    	</div>
			    </div>
		    	<div class="row col-md-12 mb10">
				    <table border="1" class="thongtin">
			            <tbody>
				           <tr>
				           		<th style="width:10%;" >Số Hộ Khẩu</th>
		            			<th style="width:30%;">Địa Chỉ</th>
		            			<th style="width:10%;">Số căn cước chủ hộ</th>
		            			<th style="width:16%;">Họ tên chủ hộ</th>
		            			<th style="width:30%;">Lý Do Không Duyệt</th>
		            			<th style="width:5%;"></th>
		            		</tr>
		            		
		                </tbody>
		                <tbody id='DS'>
		            		${dsThemNhanKhau}
		                </tbody>
		            </table>
	            </div>
		 
		</div>
		
	</div>
</div>

			<script>
			function timKiem(){
				var str = "tuKhoa="+$("#timKiem").val();
				$.ajax({url: 'tim-kiem-danh-sach-da-duyet-them-nhan-khau-so-ho-khau',
					type: 'GET',
					data: str,
				  	success: function(result){
				  		$("#DS").html(result);
			        }});
			}
			</script>	
<div class="container">
	<div class="row">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>

</body>
</html>