<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QUẢN LÝ NGÀY LÀM VIỆC</title>
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
					<div class="row text-center" id='kqNgayNghi'></div>
					<div class="row text-center">
						<div class="title">Cấu Hình ngày không làm việc</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-md-1"></div>
						<div class="col-md-3"> 
							<input type=date id='ngayNghi' class="form-control" />
						</div>
						<div class="col-md-1">
							<button type=button class='btn btn-primary' onclick='themNgayNghi()'>Thêm</button>
						</div>
						<div class="col-md-7">
							<table border=1>
								<th>Ngày, tháng</th>
								<th>Xóa</th>
								<tbody id='dsNgayNghi'>
									${dsNgayNghi }
								</tbody>
							</table>
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
	function themNgayNghi(){
		dong("kqNgayNghi");
		var ngayNghi = "ngayThang="+$("#ngayNghi").val();
		$.ajax({url: 'them-ngay-nghi',
			type: 'GET',
			data: ngayNghi,
		  	success: function(result){
		  		mo("kqNgayNghi");
		  		$("#kqNgayNghi").addClass("msg");
		  		$("#kqNgayNghi").html(result);
		  		getDSNgayNghi();
	    }});
	}
	function getDSNgayNghi(){
		var ds = "";
		$.ajax({url: 'get-danh-sach-ngay-nghi',
			type: 'GET',
			data: ds,
		  	success: function(result){
		  		$("#dsNgayNghi").html(result);
	    }});
	}
	function deleteNgayNghi(ngay){
		dong("kqNgayNghi");
		if(!confirm("Bạn có chắc chắn xóa ngày: "+ngay)){
			return false;
		}
		var ngayNghi = "ngayThang="+ngay;
		$.ajax({url: 'xoa-ngay-nghi',
			type: 'GET',
			data: ngayNghi,
		  	success: function(result){
		  		mo("kqNgayNghi");
		  		$("#kqNgayNghi").addClass("msg");
		  		$("#kqNgayNghi").html(result);
		  		getDSNgayNghi();
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