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
	<title>DANH SÁCH THẺ CĂN CƯỚC CÔNG DÂN</title>
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
<sec:authorize access="!hasRole('IN_TRA_THE')">
	<div class="container mt20" style="min-height:240px">
		<div class="mt20">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('IN_TRA_THE')">
<div class="container" style="min-height:240px">
		<div class="row bg-content">
			<div class="col-md-3 mb5">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
		    <div class="col-md-9 radius-01 bg-white">
		    	<div class="text-center row title">
					DANH SÁCH THẺ CĂN CƯỚC CÔNG DÂN
				</div>
			    	<div class="row">
			    		<div class="col-md-12">
			    			<div class="col-md-7" style="min-width:120px;">
				    			</div>
					    		<div class="col-md-5">
					    			<label class="pull-left mt5">Tìm kiếm</label>
					    			<div class="col-md-8">
						    			<input type=text id=txtTimKiem
						    			 placeholder="Nhập số căn cước hoặc mã số .."
						    			  class="form-control" onkeyup="timKiemThe();"/>
					    			 </div>
					    		</div>
			    		</div>		    		
			    	</div>
			    	<div class="row mt5">
					    <table border="1" class="thongtin">
				            <tbody>
					            <tr>
					            	<th>Mã số</th>
					                <th style="width:6%;">Số CMND/CCCD</th>
					                <th style="width:10%;">Họ tên</th>
					                <th style="width:4%;">Giới tính</th>
					                <th style="width:9%;">Ngày sinh</th>
					                <th style="width:5%;">Dân Tộc</th>
					                <th style="width:10%;">Hạn sử dụng</th>
					                <th style="width:10%;">Chi tiết</th>
					           </tr>
					          </tbody>
					        <tbody id='DS-THE'>
			            		${dsTheCC}
			                </tbody>
			            </table>
		            </div>
		            <div class="row col-md-12 mt20">
		            	<span class="pull-right">
		            		<button class="btn btn-primary" onclick="location.reload();">Làm mới</button>
		            	</span>
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
<style>
	td a{
		color:blue;
	}
</style>
<script>
	function timKiemThe(){
		var str = "tuKhoa="+$("#txtTimKiem").val();
		$.ajax({url: 'tim-kiem-the-cmt',
			type: 'GET',
			data: str,
		  	success: function(result){
		  		$("#DS-THE").html(result);
	        }});
	}
</script>
</body>
</html>
