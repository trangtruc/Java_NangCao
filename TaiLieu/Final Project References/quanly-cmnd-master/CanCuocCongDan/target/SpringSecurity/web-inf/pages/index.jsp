<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>HỆ THỐNG CHỨNG MINH THƯ ĐIỆN TỬ</title>
</head>
<body>
	<div >
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
	</div>
	<div class="container mt20">
		<div class="row">
			<div class="col-md-12">
				<jsp:include page="banner.jsp"></jsp:include>				
			</div>
		</div>
<!-- 			<form name="homepage" action></form> -->
				<div class="row">
					<div class="col-md-4">
						<div class="thumbnail">
						 <a href="dang-ky-lam-can-cuoc-cong-dan" title="" onclick="waitingDialog.show();setTimeout(function () {waitingDialog.hide();}, 10);"><img alt="scan"  src="resources/image/scan.png"></a>	
							<div class="caption">
								<h3 align="center">
									Đăng ký cấp, đổi, cấp lại CCCD
								</h3>
								<p>
									<!-- To check stastus computers in LAN network -->
								</p>
								
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="thumbnail">
							 <a href="statistic" title=""><img alt="statistics" src="resources/image/chart-icon.png"></a>
							<div class="caption">
								<h3 align="center">
										Quy trình làm CCCD/CMND
								</h3>
								<p>
								
								</p>
								
							</div>
							
						</div>
					</div>
					<div class="col-md-4">
						<div class="thumbnail">
							<a href="history" title=""><img alt="About" src="resources/image/history.png" style="width:128px;height:128px;"></a>
							<div class="caption">
								<h3 align="center">
									History
								</h3>
								<p>
									
								</p>
							</div>
						</div>
					</div>
				</div>
			<!-- 			End row -->
			
			<!-- 			Page footer -->
				<jsp:include page="footer.jsp"></jsp:include>
			<!-- 			End page footer -->
	</div>
	<!-- 	End container -->
	
	<script>
		$("#hoTen")
	</script>
	<sec:authorize access="hasRole('QUAN_TRI')">
		<!-- For login user -->
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>


	</sec:authorize>
</body>
</html>