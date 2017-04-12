<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QUẢN LÝ CẤU HÌNH EMAIL</title>
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
<sec:authorize access="!hasRole('QUAN_LY_CAU_HINH_EMAIL')">
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
<sec:authorize access="hasRole('QUAN_LY_CAU_HINH_EMAIL')">
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
						<div class="title">Cấu Hình Email</div>
					</div>
					<div class="row text-center" id='kqcauhinh'></div>
					<div class="row">
						<table border=1>
							<tr>
								<th>Mã email</th>
								<th>Email gửi</th>
								<th>Mật khẩu</th>
								<th style='width:15%'>Tiêu đề</th>
								<th style='width:25%'>Nội dung</th>
								<th>Cập nhật</th>
							</tr>
							<tbody>
								${dsEmail }
							</tbody>
						</table>
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
function capNhatEmail(vlue){
	$("#button"+vlue).html("<a onclick=luu('"+vlue+"')>Lưu </a>|<a onclick=huy('"+vlue+"')> Hủy</a>")
	$("#inputEmail"+vlue).html("<input type=text class='form-control' id='updateEmail"+vlue+"' value='"+$("#txtEmail"+vlue).val()+"' />");
	$("#inputMatKhau"+vlue).html("<input type=text class='form-control' id='updateMatKhau"+vlue+"' value='"+$("#txtMatKhau"+vlue).val()+"' />");
	$("#inputTieuDe"+vlue).html("<textarea rows=4 cols=40 class='form-control' id='updateTieuDe"+vlue+"' >"+$("#txtTieuDe"+vlue).val()+" </textarea>");
	$("#inputNoiDung"+vlue).html("<textarea rows=4 cols=50 class='form-control' id='updateNoiDung"+vlue+"' >"+$("#txtNoiDung"+vlue).val()+"</textarea>");
}
function luu(vlue){
	if(!confirm("Bạn có chắc chắn thực hiện điều này? ")){
		return false;
	}
	var update = "maMail="+vlue+"&updateEmail="+$("#updateEmail"+vlue).val()+"&updateMatKhau="+$("#updateMatKhau"+vlue).val()+"&updateTieuDe="+$("#updateTieuDe"+vlue).val()+"&updateNoiDung="+$("#updateNoiDung"+vlue).val();
	$.ajax({url: 'update-cau-hinh-email',
		type: 'GET',
		data: update,
	  	success: function(result){
	  		window.location = "quan-ly-cau-hinh-email";
    }});
}
function huy(vlue){
	$("#inputEmail"+vlue).html($("#txtEmail"+vlue).val());
	$("#inputMatKhau"+vlue).html($("#txtMatKhau"+vlue).val());
	$("#inputTieuDe"+vlue).html($("#txtTieuDe"+vlue).val());
	$("#inputNoiDung"+vlue).html($("#txtNoiDung"+vlue).val());
	$("#button"+vlue).html("<a onclick=capNhatEmail('"+vlue+"') >Cập nhật</a>")
}
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