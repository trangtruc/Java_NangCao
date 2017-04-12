<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<title>Danh sach dang ky lam can cuoc</title>
</head>
</head>
<body>
	<div class="container">
		<div class="row">
			<!-- 			Page header -->
			<div class="col-md-12">
				<jsp:include page="header.jsp"></jsp:include>
			</div>
			<!-- 			End page header -->
		<!-- 			Start table -->
		<div class="row">
		<div class="col-md-12">
			<table class="table table-hover table-striped table-responsive"  id="css-serial" >
				<thead>
					<tr class="success">
						<th>Mã số</th>
						<th>Yêu cầu</th>
						<th>Họ tên</th>
						<th>Ngày sinh</th>
						<th>Giới tính</th>
						<th>Quốc tịch</th>
						<th>Dân tộc</th>
						<th>Cập nhật</th>
						<th>Xóa</th>
						<th>Chi tiết</th>
						<th><input type="submit" name="" class="form-control btn-primary" value="Chọn tất cả"/></th>
					</tr>
				</thead>
						
				<c:forEach var="ds" items="${dsTam}">
			         <tbody>
						<tr>
							<td>${ds.maSo}</td>
							<td>${ds.yeuCau}</td>
							<td width="13%">${ds.hoTen}</td>
							<td width="9%">${ds.ngaySinh}</td>
							<td>${ds.gioiTinh}</td>
							<td>${ds.quocTich}</td>
							<td>${ds.danToc}</td>
							<td><a href="trangCapNhatCCCDTamThoi?maSo=${ds.maSo}" target="_blank">Cập nhật</a></td>
							<td><a href="xoaCCCDTamThoi?maSo=${ds.maSo}">Xóa</a></td>
							<td><a href="chiTietCCCD" target="_blank">Chi tiết</a></td>
							<td><div class="checkbox"><input style="margin-left: 40px" type="checkbox" name="chonCCCD"/></div></td>
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