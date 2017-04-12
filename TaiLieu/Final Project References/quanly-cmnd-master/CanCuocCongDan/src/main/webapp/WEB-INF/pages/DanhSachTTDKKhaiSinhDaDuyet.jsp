<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<head>
	<meta http-equiv='refresh' content='20'/>
	<jsp:include page="include.jsp"></jsp:include>
	<title>DANH SÁCH ĐĂNG KÝ KHAI SINH ĐÃ DUYỆT</title>
	<style type="text/css">
		td a {
			color: blue;
		}
	</style>
	<script>
		function getLyDo(soKS){
			//alert("vao js");
			var soKS = "soKS="+soKS;
			$.ajax({url: 'get-ly-do-tu-choi',
				type: 'GET',
				data: soKS,
			  	success: function(lyDo){
			  			alert(lyDo);
		        }});
		}
	</script>
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
<!-- 			<div class="col-md-3"> -->
<%-- 				<jsp:include page="menu-trai.jsp"></jsp:include> --%>
<!-- 			</div> -->
			<div class="col-md-12 bg-white">
			<div class="row">
				<div class="text-center col-md-12 mt20">
					<div class="col-md-1"></div>
					<div class="title">
							DANH SÁCH ĐĂNG KÝ KHAI SINH ĐÃ DUYỆT
					</div>
				</div>
			</div>
			<form   action="tim-kiem-dk-khai-sinh?${_csrf.parameterName}=${_csrf.token}"
					enctype="multipart/form-data"
					method="post" >
				<div class="row mt40">
					<div class='col-md-4' style="margin-left: 20px">
						<input type=text
								placeholder="Nhập thông tin tìm kiếm" 
								id='inputTimKiem' 
								name='inputTimKiem'
								class='form-control' />
					</div>
					<div class="col-md-2">
						<button type=submit class='btn btn-primary' formtarget="_blank">
								Tìm kiếm
						</button>
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
								<th>Số khai sinh cũ</th>
								<th>Họ tên người khai sinh</th>
								<th>Ngày sinh</th>
								<th>Ngày hẹn</th>
								<sec:authorize access="hasRole('DUYET_KHAI_SINH_1')">
									<th>Cập nhật</th>
								</sec:authorize>
								<th>Chi tiết</th>
								<th>Trạng thái</th>
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
									<c:if test="${ds.soKS != null }">
										<td>${ds.soKS}</td>
									</c:if>
									<c:if test="${ds.soKS == null}">
										<td>Không có</td>
									</c:if>
									<td width="13%" >${ds.hoTen}</td>
									<td width="9%">${ds.ngaySinh}</td>
									<td>${ds.ngayHenLamViec}</td>
									<sec:authorize access="hasAnyRole('DUYET_KHAI_SINH_1')">
										<td>
											<a href="cap-nhat-dk-khai-sinh?soKS=${ds.soKS}" target="_blank">
												Cập nhật
											</a>
										</td>
									</sec:authorize>
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
										<td style="color: red" onMouseover="getLyDo('${ds.soKS }');">Đã từ chối</td>
									</c:if>
									<c:if test="${ds.trangThai == 4 }">
										<td style="color: red" onMouseover="getLyDo('${ds.soKS }');">Đã từ chối</td>
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