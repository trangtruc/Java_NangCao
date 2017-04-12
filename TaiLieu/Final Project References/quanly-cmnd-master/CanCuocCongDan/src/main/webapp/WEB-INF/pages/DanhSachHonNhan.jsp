<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv='refresh' content='20'/>
	<jsp:include page="include.jsp"></jsp:include>
	<title>DANH SÁCH KHAI SINH</title>
	<style type="text/css">
		td a {
			color: blue;
		}
	</style>
	<% session.removeAttribute("soHonNhan"); %>
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
<sec:authorize access="!hasRole('XEM_HON_NHAN')">
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
<sec:authorize access="hasRole('XEM_HON_NHAN')">
	<div class="container bg-content">
	
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9 bg-white">
			<div class="row mt20">
				<div class="text-center col-md-12">
					<div class="title">
							DANH SÁCH KHAI SINH
					</div>
				</div>
			</div>
			<form   action="tim-kiem-hon-nhan?${_csrf.parameterName}=${_csrf.token}"
					enctype="multipart/form-data"
					method="post" >
				<div class="row mt20">
					<div class='col-md-5' style="margin-left: 20px">
						<input type=text
								placeholder="Số đăng ký, số căn cước vợ hoặc chồng" 
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
								<th>Số căn cước chồng</th>
								<th>Số căn cước vợ</th>
								<th>Tình trạng hôn nhân</th>
								<th>Ghi chú</th>
								<th>Chi tiết</th>
							</tr>
						</thead>
								
						<c:forEach var="ds" items="${dsHonNhan}">
					         <tbody>
								<tr>
									<td>${ds.soDK}</td>
									<td><a href="xem-thong-tin-cccd?soCC=${ds.soCCChong}" target="_blank">${ds.soCCChong}</a></td>
									<td><a href="xem-thong-tin-cccd?soCC=${ds.soCCVo}" target="_blank">${ds.soCCVo}</a></td>
									<td>${ds.tinhTrangHN.tenTinhTrang}</td>
									<td>${ds.ghiChu}</td>
									<td><a href="giay-chung-nhan-ket-hon?soDK=${ds.soDK}" target="_blank">Chi tiết</a></td>
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