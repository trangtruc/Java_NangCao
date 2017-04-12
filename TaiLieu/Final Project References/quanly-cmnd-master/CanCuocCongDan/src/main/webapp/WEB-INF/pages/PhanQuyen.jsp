<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QUẢN LÝ QUYỀN TÀI KHOẢN</title>
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
table[border="1"] td {
    text-align: left;
    font-size: 14px;
    margin:10px;
}
</style>
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
<sec:authorize access="!hasRole('QUAN_LY_TAI_KHOAN')">
<div class="container" style="min-height:420px">
	<div class="row text-center">
		<div class="error title">ERROR: Bạn không có quyền thực hiện thao tác này</div>
	</div>
</div>
</sec:authorize>
<sec:authorize access="hasRole('QUAN_LY_TAI_KHOAN')">
<div class="container" style="min-height:420px">
		<div class="row bg-content">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<div class="radius-01 bg-white">
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
					<div class="row">
						<div class="col-md-12">
								<div class="title">Danh sách Quyền - ${taiKhoan } (${hoTen})</div>
						</div>
					</div>
					<form action='phan-quyen?tk=${taiKhoan}&${_csrf.parameterName}=${_csrf.token}' enctype="multipart/form-data" method="POST">
						<div class="row">
							<input type=hidden name="taiKhoan" value="${taiKhoan }">
							<div class="col-md-6">
								<label>Quyền dành cho Công Dân - CB Quản Trị</label>
								${tableQuyen1 }
							</div>
							<div class="col-md-6">
								<label>Quyền dành cho CB Căn Cước Công Dân</label>
								${tableQuyen2 }
							</div>	
						</div>
						<div class="row mt40">
							<div class="col-md-6">
								<label>Quyền dành cho CB Khai Sinh</label>
								${tableQuyen3 }
							</div>
							<div class="col-md-6">
								<label>Quyền dành cho CB Kết Hôn</label>
								${tableQuyen4 }
							</div>	
						</div>
						<div class="row mt40">
							<div class="col-md-6">
								<label>Quyền dành cho CB Hộ Khẩu</label>
								${tableQuyen5 }
							</div>
						</div>
						<div class='pull-left mt10 mb5' >
							<button type="button" class="btn btn-primary" id="btnBack">Quay lại</button>
						</div>
		 				<div class='pull-right mt10 mb5' >
							<button type="submit" class="btn btn-primary" id="luu" disabled>Lưu thay đổi</button>
						</div>
					</form>
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
	$("input[type=checkbox]").click(function (e){
		$("#luu").attr("change-value","1");
		$("#luu").removeAttr("disabled");
	})
	$("a").click(function (e){
		if($("#luu").attr("change-value") == '1'){
			if(!confirm("Bạn chưa lưu các thay đổi, bạn có muốn tiếp tục rời trang không?")){
				return false;
			}
		}
	})
	$("#btnBack").click(function (e){
		if($("#luu").attr("change-value") == '1'){
			if(!confirm("Bạn chưa lưu các thay đổi, bạn có muốn tiếp tục rời trang không?")){
				return false;
			}
		}
		window.location = "quan-ly-tai-khoan";
	})
</script>
</body>
</html>