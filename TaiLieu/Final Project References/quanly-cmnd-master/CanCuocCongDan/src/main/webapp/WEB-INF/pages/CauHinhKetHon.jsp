<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CẤU HÌNH SỐ TUỔI KẾT HÔN</title>
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
			<div class="col-md-3 mb5">
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
						<div class="title">Cấu Hình tuổi kết hôn</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-6">
							<form action="update-cau-hinh-so-tuoi-ket-hon?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" method="POST" onsubmit="return updateCauHinhKetHon();">
								<table border=0>
									<tr>
										<td><label>Tuổi Nam</label></td>
										<td><input type=number id=txtTuoiNam name=tuoiNam
										placeholder='Nhập số tuổi'
										 class='form-control' 
										 value="${configKetHon.tuoiNam }"/> </td>
									</tr>
									<tr>
										<td><label>Tuổi Nữ</label></td>
										<td><input type=number id=txtTuoiNu name=tuoiNu
										placeholder='Nhập số tuổi'
										class='form-control'
										value="${configKetHon.tuoiNu }"/> </td>
									</tr>
									<tr>
										<td colspan=2 class='text-center'>
											<button type=submit id="submit" disabled class='btn btn-primary' >Lưu cấu hình</button>
										</td>
									</tr>
								</table>
							</form>
						</div>
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
	function updateCauHinhKetHon(){
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
</script>
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
</body>
</html>