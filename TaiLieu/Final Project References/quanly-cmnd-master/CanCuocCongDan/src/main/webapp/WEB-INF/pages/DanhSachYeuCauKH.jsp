<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>YÊU CẦU KẾT HÔN</title>
	<script>
		var ktXacNhan = <%= session.getAttribute("ktXacNhan")%>;
		
<%-- 		<% session.removeAttribute("ktXacNhan"); %> --%>
		if (ktXacNhan) {
			alert("Bạn không thể đồng ý hai lần");
		} else if (ktXacNhan == false) {
			alert("Xác nhận thành công");
		}
	</script>
</head>
<body>
<div class="container">
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
</div>

<form action="dang-ky-ket-hon?${_csrf.parameterName}=${_csrf.token}" 
		enctype="multipart/form-data"
		onsubmit='return checkOnSubmit();'>
<div class="container bg-white" style="min-height:400px">
	<div class="mt80">
		<div class="text-center col-md-12">
			<div class="text-orange title">
					YÊU CẦU KẾT HÔN
			</div>
		</div>
	</div>
	
	<div class="row mt40 mb40">
		<div class='col-md-2'></div>
		<div class="col-md-8 mt20">
			<div class="row mt20">
				<table border="1" >
						<thead>
							<tr class="success">
								<th>Số đăng ký</th>
								<th>Số căn cước người yêu cầu</th>
								<th>Ngày đăng ký</th>
								<th>Đồng ý</th>
								<th>Không đồng ý</th>
								<th>Trạng thái</th>
							</tr>
						</thead>
						<c:forEach var="ds" items="${dsYeuCauKH}" varStatus="status">
					         <tbody>
								<tr>
									<td>${ds.soDK}</td>
									<td>${ds.soCCNguoiDK}</td>
									<td>${ds.ngayDangKy }</td>
									<td>
										<c:if test="${((ds.trangThai == -1) || (ds.trangThai == -2)) }">
										<a href="dong-y-dang-ky-ket-hon?soDK=${ds.soDK}">
											Đồng ý
										</a>
										</c:if>
									</td>
									<td>
										<c:if test="${((ds.trangThai == 0) || (ds.trangThai == -1)) }">
										<a href="tu-choi-dang-ky-ket-hon?soDK=${ds.soDK}">
											Từ chối
										</a>
										</c:if>
									</td>
									<c:if test="${ds.trangThai == -1 }">
										<td style="color: red">Chưa xác nhận</td>
									</c:if>
									<c:if test="${ds.trangThai == 0 }">
										<td style="color: red">Đã đồng ý</td>
									</c:if>
									<c:if test="${ds.trangThai == -2 }">
										<td style="color: red">Không đồng ý</td>
									</c:if>
									<c:if test="${ds.trangThai == 1 }">
										<td style="color: red">Đã được cơ quan xác nhận thông tin</td>
									</c:if>
									<c:if test="${ds.trangThai == 2 }">
										<td style="color: red">Đã được duyệt</td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- 			End table -->
			</div>
		</div>
	</div>
</div>
</form>
<div class="row">
	<div class="container">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>
</body>
</html>