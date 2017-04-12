<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
<style type="text/css">
	p {
    height: 50px;
    border-radius:5px;
	}
</style>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>HỆ THỐNG CHỨNG MINH THƯ ĐIỆN TỬ</title>
</head>
<body>
	<div class="container">
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
	</div>
	<div class="container ">
		<div class="row bg-content">
			<div  class="col-md-3 ">
				<jsp:include page="MenuLeft.jsp"></jsp:include>
			</div>
			<div class="col-md-6" style="border-radius: 5px !important;background: #fff9e9;min-height:  400px">
				<jsp:include page="Center.jsp"></jsp:include>
			</div >
			<div class="col-md-3 MenuRight" style="min-height: 400px;" >
				<jsp:include page="MenuRight.jsp"></jsp:include>
			</div>
		</div>
		<div class="row">
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>