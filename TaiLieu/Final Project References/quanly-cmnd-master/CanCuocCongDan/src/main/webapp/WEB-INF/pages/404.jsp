<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Page Not Found</title>
</head>
<body>
	<div class="container">
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
			<div class="row error text-center">
				<div class="title">
						LỖI: KHÔNG TÌM THẤY TRANG
				</div>
				<div>
					<a href='<c:url value="/"></c:url>' style="color:blue;"> <span class="fa fa-arrow-left"></span> Quay về trang chủ</a>
				</div>
			</div>
	</div>
</body>
</html>