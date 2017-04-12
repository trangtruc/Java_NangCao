<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<title>TÌM KIẾM ĐĂNG KÝ KẾT HÔN</title>
	<style type="text/css">
		td a {
			color: blue;
		}
	</style>
	<script src="resources/js/NhapKhaiSinh.js">
	</script>
	<script>
		function checkOnSubmit() {
			var inputTimKiem = document.getElementById("inputTimKiem").value;
			if (checkNull(inputTimKiem)) {
				alert("Vui lòng nhập thông tin để tiềm kiếm.");
				return false;
			}
		}
		function setInputText() {
			var optionTimKiem = document.getElementById("optionTimKiem").value;
			optionTimKiem = optionTimKiem.substr(0,4);
			if (optionTimKiem == "ngay") {
				$("#changeInput").html("<input type=date id='inputTimKiem' name='inputTimKiem' class='form-control'/>");
			} else {
				$("#changeInput").html(
							"<input type=text "
							+ "placeholder='Tìm kiếm đơn đăng ký kết hôn' "
							+ "onkeyup='kiemTraSo(this.id, this.value);' "
							+ "id='inputTimKiem' "
							+ "name='inputTimKiem' "
							+ "class='form-control' />"
						);
			}
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
<sec:authorize access="!hasAnyRole('DUYET_KET_HON_1','DUYET_KET_HON_2')">
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
<sec:authorize access="hasAnyRole('DUYET_KET_HON_1','DUYET_KET_HON_2')">
	<div class="container bg-content">
		<div class="row mt40">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9 bg-white">
				<div class="row mt20">
				<div class="text-center col-md-12">
					<div class="title">
							KẾT QUẢ TÌM KIẾM ĐƠN ĐĂNG KÝ KẾT HÔN
					</div>
				</div>
			</div>
			<form action="tim-kiem-dk-ket-hon?${_csrf.parameterName}=${_csrf.token}"
					enctype="multipart/form-data"
					method="post" 
					onsubmit='return checkOnSubmit();'>
				<div class="row mt20">
					<div class='col-md-4' id='changeInput' style="margin-left: 20px;">
						<input type=text
								placeholder="Tiềm kiếm đơn đăng ký kết hôn" 
								onkeyup = 'kiemTraSo(this.id, this.value);'
								onblur='checkInput();'
								id='inputTimKiem' 
								name='inputTimKiem'
								class='form-control' />
					</div>
					<div class='col-md-2'>
						<select name='optionTimKiem' id='optionTimKiem' 
								onchange="setInputText();"
								class='form-control'>
							<option value="soDK">Số đăng ký</option>
							<option value="soCC">Số căn cước</option>
							<option value="ngayDK">Ngày đăng ký</option>
							<option value="ngayHen">Ngày hẹn</option>
						</select>
					</div>
					<div class="row">
						<div class="col-md-2">
							<button type=submit class='btn btn-primary'>
									Tiềm kiếm
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
								<th>Số căn cước người đăng ký</th>
								<th>Số căn cước chồng hoặc vợ</th>
								<th>Ngày đăng ký</th>
								<th>Ngày hẹn làm việc</th>
								<th>Cập nhật</th>
								<th>Chi tiết</th>
							</tr>
						</thead>
						<c:forEach var="dkKetHon" items="${dsDKKetHon}"> 
							<c:if test="${dsDKKetHon != null }">
								<tbody>
									<tr>
										<td>${dkKetHon.soDK}</td>
										<td>
											<a href="xem-thong-tin-cccd?soCC=${dkKetHon.soCCNguoiDK}" target="_blank">
												${dkKetHon.soCCNguoiDK}
											</a>
										</td>
										<td>
											<a href="xem-thong-tin-cccd?soCC=${dkKetHon.soCCVoHoacChong}" target="_blank">
												${dkKetHon.soCCVoHoacChong}
											</a>
										</td>
										<td>${dkKetHon.ngayDangKy}</td>
										<td>${dkKetHon.ngayHenLamViec}</td>
										<td>
											<a href="cap-nhat-dk-ket-hon?soDK=${dkKetHon.soDK}" target="_blank">
												Cập nhật
											</a>
										</td>
										<td>
											<a href="chi-tiet-dkkh?soDK=${dkKetHon.soDK}" target="_blank">
												Chi tiết
											</a>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
<!-- 					<input type="hidden" value='0' name='duLieuTimKiem' id='duLieuTimKiem'/> -->
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