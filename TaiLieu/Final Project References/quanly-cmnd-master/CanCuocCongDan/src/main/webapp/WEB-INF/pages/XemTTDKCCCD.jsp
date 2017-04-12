<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
</head>
<body class="bg-white">
<c:if test="${ssTaiKhoan == null}">
	<script>
		window.location = "login";
	</script>
</c:if>
<sec:authorize access="!hasRole('XAC_NHAN_TTDK_CCCD')">
	<div class="container mt20" style="min-height:360px">
		<div class="mt80">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('XAC_NHAN_TTDK_CCCD')">
<div class="container">
	<jsp:include page="ChiTietTTDKCCCD.jsp"></jsp:include>
	<div class="row mt20" id="button">
		<div class="col-md-12 text-center">	
			<button type=button onclick="goBack()" class='btn btn-primary'>Quay lại</button>
			<button type=button onclick="go(${maSo});" class='btn btn-primary'>Tạo bản in</button>
		</div>
	</div>
	<div class="row mt20">
				
	</div>
</div>
</sec:authorize>
</body>
</html>