<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<title>KẾT QUẢ TÌM KIẾM ĐĂNG KÝ KHAI SINH</title>
	<style type="text/css">
		td a {
			color: blue;
		}
	</style>
</head>
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
<sec:authorize access="!hasAnyRole('DUYET_KHAI_SINH_1', 'DUYET_KHAI_SINH_2')">
	<div class="container mt20" style="min-height:420px">
		<div class="mt80">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasAnyRole('DUYET_KHAI_SINH_1', 'DUYET_KHAI_SINH_2')">
	<div class="container bg-content">
		<div class="row mt40">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9 bg-white">
			<div class="row">
				<div class="text-center col-md-12 mt20">
					<div class="title">
							KẾT QUẢ TÌM KIẾM ĐĂNG KÝ KHAI SINH
					</div>
				</div>
			</div>
			<form   action="tim-kiem-dk-khai-sinh?${_csrf.parameterName}=${_csrf.token}"
					enctype="multipart/form-data"
					method="post" >
				<div class="row mt20">
					<div class='col-md-4' style="margin-left: 20px">
						<input type=text
								placeholder="Nhập thông tin tìm kiếm" 
								id='inputTimKiem' 
								name='inputTimKiem'
								class='form-control' />
					</div>
					<div class="row">
						<div class="col-md-2">
							<button type=submit class='btn btn-primary'>
									Tìm kiếm
							</button>
						</div>
					</div>
				</div>
			</form>
				<div class="row col-md-12" style='height: 400px; overflow: auto; margin-top: 10px'>
					<table border="1" >
						<thead>
							<tr class="success">
								<th>Số đăng ký</th>
								<th>Yêu cầu</th>
								<th>Số căn cước người đăng ký</th>
								<th>Họ tên người khai sinh</th>
								<th>Ngày sinh</th>
								<c:if test="${ssViTri == 'CAN_BO_KS_1'}">
									<th>Cập nhật</th>
								</c:if>
								<th>Chi tiết</th>
								<th></th>
							</tr>
						</thead>
								
						<c:forEach var="ds" items="${dsDKKhaiSinh}">
					         <tbody id="kqTimKiem">
								<tr>
									<td>${ds.soKS}</td>
									<td>${ds.yeuCau.tenYeuCau}</td>
									<td>
										<a href="xem-thong-tin-cccd?soCC=${ds.soCCNguoiYeuCau}" target="_blank">
											${ds.soCCNguoiYeuCau}
										</a>
									</td>
									<td>${ds.hoTen}</td>
									<td>${ds.ngaySinh}</td>
									<c:if test="${ssViTri == 'CAN_BO_KS_1'}">
										<td>
											<a href="cap-nhat-dk-khai-sinh?soKS=${ds.soKS}" target="_blank">
												Cập nhật
											</a>
										</td>
									</c:if>
									<td><a href="chi-tiet-dk-khai-sinh?soKS=${ds.soKS}" target="_blank">Chi tiết</a></td>
									<c:if test="${ds.trangThai == 0 }">
										<td style="color: red">Chưa xác nhận</td>
									</c:if>
									<c:if test="${ds.trangThai == 1 }">
										<td style="color: red">Đã xác nhận</td>
									</c:if>
									<c:if test="${ds.trangThai == 2 }">
										<td style="color: red">Đã duyệt</td>
									</c:if>
									<c:if test="${ds.trangThai == 3 }">
										<td style="color: red">Đã từ chối</td>
									</c:if>
									<c:if test="${ds.trangThai == 4 }">
										<td style="color: red">Đã từ chối</td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- 			End table -->
				</div>
			</div>
		</div>
			<!-- 			Page footer -->
			<jsp:include page="footer.jsp"></jsp:include>
			
			<!-- 			End page footer -->
	</div>
	</sec:authorize>
</body>
</html>