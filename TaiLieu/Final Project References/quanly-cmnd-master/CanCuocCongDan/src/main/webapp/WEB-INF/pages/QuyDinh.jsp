<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QUY ĐỊNH</title>
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
	</div>
	<div class="container ">
		<div class="row bg-content">
			<div  class="col-md-3">
				<jsp:include page="MenuLeft.jsp"></jsp:include>
			</div>
			<div class="col-md-6" style="border-radius: 5px !important;background: white;min-height:600px">
				<div class="row">
					<label class="title">Quy định</label>
				</div>
				<div class="row" id="quy-dinh">
					<jsp:include page="MenuQuyDinh.jsp"></jsp:include>
				</div>
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