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
	<title>CHI TIẾT SỔ HỘ KHÂU</title>
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
	</div>

<div class="container" ">
	<div class="row bg-content">
		<div class="col-md-3">
			<jsp:include page="menu-trai.jsp"></jsp:include>
		</div>
		 
		<div class="col-md-9 bg-white mt5">
		<div class="row">
			<div class="text-center col-md-12">
				<div class="title">
					CHI TIẾT SỔ HỘ KHẨU
				</div>
		</div>
			<form action="">
			
		    	<div class="row">
				    <table border="1" class="thongtin ">
			            <tbody>
				            <tr>
				                <th colspan="7" style="width:84%;">Thông tin các thành viên trong gia đình</th>
				           </tr>
				           <tr>				           		
				           		<th style="width:18%;">Số Khai Sinh</th>
		            			<th style="width:18%;">Số Căn Cước</th>
		            			<th style="width:18%;">Họ Tên</th>
		            			<th style="width:20%;">Tình Trạng nhân khẩu</th>
		            			<th>Quan Hệ Với Chủ Hộ</th>
		            			<th style="width:18%;">Ngày chuyển đến</th>
		            		</tr>
		            		 ${chiTietSoHoKhau};
		                </tbody>
		               
		            </table>
	            </div>
	            <div class="row col-md-12 mt5">
					<span ><button type=button onclick="goBack()" class='btn btn-primary ml5 mb5' hidden>Quay lại</button></span>
				
				</div>
		 </div>
		 </form>
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
	function duyet(){
		if(!confirm("Bạn có chắc chắn thực hiện điều này?")){
			return false;
		}
		else return true;
	}
	
	function capNhat(stt){
		var backup=$("#backupTT"+stt).val();
		$("#tinhTrang"+stt).html("<input type=text class='form-control' id='updateTinhTrang"+stt+"' value='"+backup+"' /><a onclick=luu('"+stt+"')>Lưu </a>|<a onclick=huy('"+stt+"')> Hủy</a>");
	}
	
	function luu(stt){
		if(!confirm("Bạn có chắc chắn thực hiện điều này? ")){
			return false;
		}
		var soHK = $("#soHK").val();
		var update ="&soKS="+$("#soKS"+stt).val()+"&tinhTrang="+$("#updateTinhTrang"+stt).val();
		$.ajax({url: 'update-tinh-trang-chi-tiet-ho-khau',
			type: 'GET',
			data: update,
		  	success: function(result){
		  		window.location = "chi-tiet-so-ho-khau?soHK="+soHK;
	    }});
	}
	function huy(stt){
		$("#tinhTrang"+stt).html($("#backupTT"+stt).val()+"<br><a onclick=capNhat("+stt+")>[Cập nhật]</a>");
	}
</script>
</body>
</html>
<style>
	td a{
		color:blue;
	}
</style>