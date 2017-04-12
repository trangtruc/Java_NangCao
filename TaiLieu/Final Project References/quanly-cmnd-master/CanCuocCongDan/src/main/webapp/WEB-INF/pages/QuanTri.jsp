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
	<title>TRANG QUẢN TRỊ</title>
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
<c:if test="${ssSoQuyen < 2}">
	<div class="container" style="min-height:420px">
	<div class="row text-center">
		<div class="error title">ERROR: Bạn không có quyền thực hiện thao tác này</div>
	</div>
</div>
</c:if>
<div class="container " style="min-height:420px">
		<div class="row bg-content">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
		    <div class="col-md-9">
		    	<div class="row radius-01 bg-white">
		    		<div class="row title text-center">
			    		Thông tin tài khoản
			    	</div>
			    	<div class="row">
			    		<ul>
			    			<li>Họ tên: <b>${taiKhoan.hoTen }</b>.</li>
			    			<li>Làm việc tại: <b>${coQuan }</b>.</li>
			    		</ul>
			    	</div>
			    	<hr>
			    	<div class="row title text-center">
			    		Nội Quy
			    	</div>
			    	<div class="row">
			    		<ul>
			    			<li> Không được tự ý sửa đổi CSDL.</li>
			    			<li> Không được copy link và paste vào thanh URL (có thể dẫn đến lỗi).</li>
			    			<li> ....</li>
			    		</ul>
			    	</div>
			    	<hr>
			    	<div class="row title text-center">
			    		Phím tắt
			    	</div>
			    	<div class="row">
			    		<ul>
			    			<li> Ctrl + P ( Để mở trang In cửa window ).</li>
			    			<li> ....</li>
			    		</ul>
			    	</div>
		    	</div>
		 	</div>
		</div>
	</div>
<div class="container">
	<div class="row">
			<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>
</body>
</html>
<style>
	td a{
		color:blue;
	}
</style>