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
	<title>DANH SÁCH CĂN CƯỚC CÔNG DÂN ĐÃ CẤP PHÉP</title>
</head>
<body>
<c:if test="${ssTaiKhoan == null}">
	<script>
		window.location = "login";
	</script>
</c:if>
<div class="container">
	<jsp:include page="header.jsp"></jsp:include>
</div>
<sec:authorize access="!hasRole('DUYET_TTDK_CCCD')">
	<div class="container mt20" style="min-height:300px">
		<div class="row mt20">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('DUYET_TTDK_CCCD')">
<div class="container " style="min-height:360px">
		<div class="row bg-content">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
		    <div class="col-md-9">
		    	<div class="row radius-01 bg-white">
			    	<div class="row title col-md-12 text-center">
						HỒ SƠ ĐĂNG KÝ CĂN CƯỚC CÔNG DÂN ĐÃ CẤP PHÉP
					</div>
			    	<div class="row">
			    		<div class="col-md-12">
			    			<div class="col-md-7" style="min-width:120px;">
				    			</div>
				    			
					    		<div class="col-md-5">
					    			<label class="pull-left mt5">Tìm kiếm</label>
					    			<div class="col-md-8">
						    			<input type=text id=txtTimKiem
						    			 placeholder="Nhập họ tên hoặc mã số đăng ký .."
						    			  class="form-control" onkeyup="timKiemDonDaDuyet();"/>
					    			 </div>
					    		</div>
			    		</div>		    		
			    	</div>
			    	<div class="row mt5" id="danh-sach">
			            ${dsDaDuyet}     
		            </div>
		             <div class="row col-md-12 mt20">
		            	<span class="pull-right">
		            		<button class="btn btn-primary" onclick="location.reload();">Làm mới</button>
		            	</span>
		            </div>
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
<script>
function timKiemDonDaDuyet(){
	var tuKhoa = "keySearch="+$("#txtTimKiem").val();
	$.ajax({url: 'tim-kiem-don-ttdk-cccd-da-duyet',
		type: 'GET',
		data: tuKhoa,
	  	success: function(result){
	  		$("#danh-sach").html(result);
    }});
}
</script>
<style>
	td a{
		color:blue;
	}
	.table-striped>tbody>tr:nth-of-type(odd):hover{
		background:#adf1b5;
	}
</style>
</body>
</html>