<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thông báo</title>
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="row text-center">
			<div class="error ml5"><h3>${thongBaoLoi}</h3></div>
		</div>
	</div>
	<div class="container">
		<div class="row">
				<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</div>	
</body>
</html>