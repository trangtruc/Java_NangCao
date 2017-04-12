<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DANH SÁCH LÀM SỔ HỘ KHÂU</title>
</head>
<body>
<% session.removeAttribute("soDonLamHoKhau");%>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
	</div>

<div class="container " style="min-height:364px">
		<div class="row bg-content">
			<div class="col-md-3 ">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
		<div class="col-md-9 bg-white radius-01" style="margin-right: 100px" >
			<div class="row mt5">
				<div class="text-center title col-md-offset-3">
						DANH SÁCH ĐĂNG KÝ TÁCH SỔ HỘ KHẨU
				</div>
			</div>
			
			
			<div class="row">		    		
		    		<div class="row text-center col-md-12 mt10"><label class=" row mt5 " id="title-the" style="font-size: 20px; text-align: center;" >${title}</label></div>
		    </div>
		    <div class="col-md-12 ">
		    	<div class="col-md-offset-5 col-md-2">
		    		<label class='mt5' style="font-size: 20px;"> Tìm kiếm</label>
		    	</div>
		    	<div class=' col-md-4'>
		    		<input type=text name='timKiem' id=timKiem class='form-control' onkeyup='timKiemHK()' placeholder='Nhập mã đăng ký, mã HK cũ' />
		    	</div>
		    	<div class="row col-md-12 mb10">
				    <table border="1" class="thongtin">
			            <tbody>
				            <tr>
				                <th colspan="7" style="width:84%;">Thông tin người đăng ký</th>
				                <th style="width:6%;" rowspan="2">Chi tiết</th>
				           </tr>
				           <tr>
				           		<th style="width:10%;">Mã</th>
				           		<th style="width:18%;">Số Hộ Khẩu Cũ</th>
		            			<th style="width:18%;">Số căn cước</th>
		            			<th style="width:18%;">Họ tên</th>
		            			<th style="width:8%;">Giới Tính</th>
		            			<th style="width:10%;">Dân Tộc</th>
		            			<th style="width:44%;">Địa Chỉ</th>
		            		</tr>
		            		
		                </tbody>
		                <tbody id='DS-THE'>
		            		${dsLamHoKhau}
		                </tbody>
		            </table>
	            </div>
		 </div>
		</div>
		</div>
	</div>
<div class="container">
	<div class="row">
		<div class="container">
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
</div>
</div>
<script>
	function timKiemHK(){
		var str = "tuKhoa="+$("#timKiem").val();
		$.ajax({url: 'tim-kiem-ds-lam-so-ho-khau',
			type: 'GET',
			data: str,
		  	success: function(result){
		  		$("#DS-THE").html(result);
	        }});
	}
</script>
</body>
</html>
<style>
	td a{
		color:blue;
	}
</style>