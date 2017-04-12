<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
table tr:hover {
    background-color: white;
}
table tr:nth-child(even):hover {
	background-color: white;
}
table td{
	padding:5px;
}
.select-control{
	width:160px;height: 34px;padding: 6px 12px;border-radius: 4px;
}
</style>
<title>CẤU HÌNH CMND/CCCD</title>
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
<sec:authorize access="!hasRole('QUAN_LY_CAU_HINH_CCCD')">
	<div class="container mt20">
		<div class="mt80">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('QUAN_LY_CAU_HINH_CCCD')">
<div class="container" style="min-height:400px;">
		<div class="row bg-content">
			<div class="col-md-3 mb10">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<div class="row radius-01 bg-white">
					<div class="row text-center">
						<c:if test="${not empty error}">
							<div class="error ml5">${error}</div>
							<% session.removeAttribute("error"); %>
						</c:if>
						<c:if test="${not empty msg}">
							<div class="msg ml5">${msg}</div>
							<% session.removeAttribute("msg"); %>
						</c:if>
					</div>
					<div class="row text-center">
						<div class="title">Cấu Hình căn cước công dân</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<form id="form-config" action="update-cau-hinh-cmnd-cccd?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" method="POST" onsubmit="return updateCauHinhCCCD();">
								<hr>
								<h4>Cấu hình cơ bản </h4>
								<table border=0>
									<tr>
										<td><label>Vòng đời hồ sơ</label></td>
										<td><input type=number id=txtHoSo name=thoiHanHoSo
										placeholder='Nhập số ngày'
										 class='form-control' 
										 value="${configCCCD.hanHoSo }"/> </td>
										 <td>Ngày</td>
									</tr>
									<tr>
										<td><label>Giới hạn hồ sơ trong 1 ngày</label></td>
										<td><input type=number id=txtGioiHan name=gioiHanHoSo
										placeholder='Nhập số ngày'
										class='form-control'
										value="${configCCCD.soHoSo1Ngay }"/> </td>
										<td>Hồ sơ/ngày</td>
									</tr>
									<tr>
										<td><label>Hạn sử dụng của thẻ</label></td>
										<td><input type=number id=txtHanSD name=hanSuDung
										placeholder='Nhập số năm' 
										class='form-control' 
										value="${configCCCD.hanSuDung }"/> </td>
										<td>Năm (0 là không thời hạn)</td>
									</tr>
									<tr>
										<td><label>Độ tuổi làm thẻ</label></td>
										<td><input type=number id=txtTuoi name=tuoi
										placeholder='Nhập số tuổi' 
										class='form-control' 
										value="${configCCCD.tuoi }"/> </td>
										<td>Tuổi</td>
									</tr>
								</table>
								<hr>
								<h4>Cấu hình tạo số Căn cước mới</h4> 
								ví dụ: 092<font color=red>0</font><font color=blue>94</font>089191
								<ul>
									<li> 94 là năm sinh 1994.</li>
									<li> 0 là nam.</li>
								</ul>
								<table border=1 class="mt20">
									<tr>
										<th style="width:100px;">Năm</th>
										<th style="width:40%;">Mô tả</th>
										<th style="width:80px;">Nam trước</th>
										<th style="width:80px;">Nữ trước</th>
										<th style="width:80px;">Nam sau</th>
										<th style="width:80px;">Nữ sau</th>
									</tr>
										<tr>
											<td><input type="number" name="nam" class="form-control" value="${csc.nam}" /></td>
											<td>
											<input type="text" name="moTa" class="form-control" value="${csc.moTa}">
											
											</td>
											<td><input type="number" name="namTruoc" class="form-control" value="${csc.giaTriNamTruoc}" /></td>
											<td><input type="number" name="nuTruoc" class="form-control" value="${csc.giaTriNuTruoc}" /></td>
											<td><input type="number" name="namSau" class="form-control" value="${csc.giaTriNamSau}" /></td>
											<td><input type="number" name="nuSau" class="form-control" value="${csc.giaTriNuSau}" /></td>
										</tr>
								</table>
							</form>
						</div>
					</div>
					<div class="row text-center mt20">
						<button type=submit id="submit" disabled class='btn btn-primary'>Lưu cấu hình</button>
					</div>
				</div>
			</div>
		</div>
</div>
</sec:authorize>
<div class="container">
	<div class="row">
			<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>
<script>
	function updateCauHinhCCCD(){
		if(!confirm("Bạn có chắc chắn muốn lưu cấu hình này?")){
			return false;
		}
	}
	$("input").click(function (e){
		$("#submit").attr("change-value","1");
		$("#submit").removeAttr("disabled");
	})
	$("a").click(function (e){
	if($("#submit").attr("change-value") == '1'){
		if(!confirm("Bạn chưa lưu các thay đổi, bạn có muốn tiếp tục rời trang không?")){
			return false;
		}
	}
	})
	$("#submit").click(function (e){
		$("#form-config").submit();
	});
</script>

</body>
</html>