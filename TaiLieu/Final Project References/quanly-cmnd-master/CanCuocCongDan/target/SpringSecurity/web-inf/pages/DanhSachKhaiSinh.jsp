<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<head>
	<meta http-equiv='refresh' content='5'/>
	<jsp:include page="include.jsp"></jsp:include>
	<title>Danh sách khai sinh</title>
</head>
</head>
<body>
<div>
				<jsp:include page="header.jsp"></jsp:include>
			</div>
	<div class="container">
		<div class="row">
			<!-- 			Page header -->
			
			<!-- 			End page header -->
		<!-- 			Start table -->
		<div class="row">
		<div class="col-md-12">
			<table class="table table-hover table-striped table-responsive"  id="css-serial" >
				<thead>
					<tr class="success">
						<th>Số khai sinh</th>
						<th>Họ tên</th>
						<th>Ngày sinh</th>
						<th>Giới tính</th>
						<th>Quốc tịch</th>
						<th>Cập nhật</th>
						<th>Chi tiết</th>
					</tr>
				</thead>
						
				<c:forEach var="ds" items="${dsKhaiSinh}">
			         <tbody>
						<tr>
							<td>${ds.soKS}</td>
							<td width="13%">${ds.hoTen}</td>
							<td width="9%">${ds.ngaySinh}</td>
							<c:if test="${ds.gioiTinh == 'Nữ'}">
								<td>Nữ</td>
							</c:if>
							<c:if test="${ds.gioiTinh == 'Nam'}">
								<td>Nam</td>
							</c:if>
							<td>${ds.quocTich}</td>
							<td><a href="trang-cap-nhat-khai-sinh?soKS=${ds.soKS}" target="_blank">Cập nhật</a></td>
							<td><a href="chi-tiet-khai-sinh?soKS=${ds.soKS}" target="_blank">Chi tiết</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- 			End table -->
		</div>
		</div>
			<!-- 			Page footer -->
			<jsp:include page="footer.jsp"></jsp:include>
			
			<!-- 			End page footer -->
		</div>
	</div>
</body>
</html>