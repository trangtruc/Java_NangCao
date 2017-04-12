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
<% session.removeAttribute("soDonThemNhanKhau");%>
<div class="container">
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
</div>
<div class="container" style="min-height:364px">
	<div class="row bg-content mt5">
			<div class="col-md-3 ">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9 bg-white mt5">
				<div class="row  mt5 text-center title">
						Danh Sách Sổ Hộ Khẩu Đăng Ký Thêm Nhân Khẩu
				</div>
		    		<div class="row">
			    		<div class="col-md-12">
			    			<div class="col-md-7" style="min-width:120px;">
				    			</div>
					    		<div class="col-md-5">
					    			<label class="pull-left mt5"style="font-size: 20px;">Tìm kiếm</label>
					    			<div class="col-md-8">
						    			<input type=text name='timKiem' id=timKiem class='form-control' onkeyup='timKiem()' placeholder='Nhập mã HK cũ, CCCD người đăng ký' />
					    			 </div>
					    		</div>
			    		</div>		    		
			    	</div>
		    	<div class="row col-md-12 mb10 ">
				    <table  border="1" class=" thongtin mt5 mb10">
			            <tbody>
				           <tr style="width: 97%; background-color: rgba(245, 173, 0, 0.38);">
				           		<th style="width:18%; text-align:center;" >Số Hộ Khẩu</th>
		            			<th style="width:44%; text-align:center;">Địa Chỉ</th>
		            			<th style="width:18%; text-align:center;">Số căn cước chủ hộ</th>
		            			<th style="width:18%; text-align:center;">Họ tên chủ hộ</th>
		            			<th style="width:8%; text-align:center;">Giới Tính</th>
		            			<th style="width:10%; text-align:center;">Dân Tộc</th>
		            			<th style="width:10%; text-align:center;">Tình Trạng</th>
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
				$.ajax({url: 'tim-kiem-danh-sach-them-nhan-khau-so-ho-khau',
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