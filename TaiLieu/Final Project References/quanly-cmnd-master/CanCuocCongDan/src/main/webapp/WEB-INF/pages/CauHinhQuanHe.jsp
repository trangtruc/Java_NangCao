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
<sec:authorize access="!hasRole('QUAN_LY_CAU_QUAN_HE')">
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
<sec:authorize access="hasRole('QUAN_LY_CAU_QUAN_HE')">
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
						<div class="title">Cấu Hình Quan Hệ Trong Sổ Hộ Khẩu</div>
					</div>
					<div class="row text-center" id='kqcauhinh'></div>
					<div class="row">
						<table border=1>
							<tr>
								<th>Mã quan hệ</th>
								<th>Tên quan Hệ</th>
								<th>Cập nhật</th>
							</tr>
							<tbody>
								${dsQuanHe }
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
function Them(stt){
	$("#suaQuanHe"+stt).html("<a onclick=them('"+stt+"')>Lưu </a>|<a onclick=huyThem('"+stt+"')> Hủy</a>");
	$("#inputMaQuanHe"+stt).html("<input type=text class='form-control' id='themMaQuanHe"+stt+"' />");
	$("#inputTenQuanHe"+stt).html("<input type=text class='form-control' id='themTenQuanHe"+stt+"'/>");
	
}

function them(stt){
	if(!confirm("Bạn có chắc chắn thực hiện điều này? ")){
		return false;
	}
	
	var them = "themMaQuanHe="+$("#themMaQuanHe"+stt).val()+"&themTenQuanHe="+$("#themTenQuanHe"+stt).val();
	$.ajax({url: 'them-cau-hinh-quan-he',
		type: 'GET',
		data: them,
	  	success: function(result){
	  		window.location = "quan-ly-cau-hinh-quan-he-ho-khau";
    }});
}

function capNhat(stt){
	$("#suaQuanHe"+stt).html("<a onclick=luu('"+stt+"')>Lưu </a>|<a onclick=huy('"+stt+"')> Hủy</a>");
	$("#inputMaQuanHe"+stt).html("<input type=text class='form-control' id='updateMaQuanHe"+stt+"' value='"+$("#maQuanHe"+stt).val()+"' />");
	$("#inputTenQuanHe"+stt).html("<input type=text class='form-control' id='updateTenQuanHe"+stt+"' value='"+$("#tenQuanHe"+stt).val()+"' />");
	
}

function luu(stt){
	if(!confirm("Bạn có chắc chắn thực hiện điều này? ")){
		return false;
	}
	var maQuanHe ="&maQuanHe="+$("#maQuanHe"+stt).val();
	var update = "updateMaQuanHe="+$("#updateMaQuanHe"+stt).val()+"&updateTenQuanHe="+$("#updateTenQuanHe"+stt).val()+maQuanHe;
	$.ajax({url: 'update-cau-hinh-quan-he',
		type: 'GET',
		data: update,
	  	success: function(result){
	  		window.location = "quan-ly-cau-hinh-quan-he-ho-khau";
    }});
}
function huy(stt){
	$("#suaQuanHe"+stt).html("<a onclick=capNhat("+stt+")>Cập nhật</a>");
	$("#inputMaQuanHe"+stt).html($("#maQuanHe"+stt).val());
	$("#inputTenQuanHe"+stt).html($("#tenQuanHe"+stt).val());
}
function huyThem(stt){
	$("#suaQuanHe"+stt).html("<a onclick=Them("+stt+")>Thêm</a>");
	$("#inputMaQuanHe"+stt).html($("#maQuanHe"+stt).val());
	$("#inputTenQuanHe"+stt).html($("#tenQuanHe"+stt).val());
}
function xoa(stt){
	if(!confirm("Bạn có chắc chắn thực hiện điều này? ")){
		return false;
	}
	var maQuanHe ="maQuanHe="+$("#maQuanHe"+stt).val();
	$.ajax({url: 'xoa-cau-hinh-quan-he',
		type: 'GET',
		data: maQuanHe,
	  	success: function(result){
	  		window.location = "quan-ly-cau-hinh-quan-he-ho-khau";
    }});
	
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