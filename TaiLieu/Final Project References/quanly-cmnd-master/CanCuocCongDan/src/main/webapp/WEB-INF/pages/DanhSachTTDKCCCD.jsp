<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<title>DANH SÁCH ĐĂNG KÝ</title>
</head>
<body>
<c:if test="${ssTaiKhoan == null}">
	<script>
		window.location = "login";
	</script>
</c:if>
<% session.removeAttribute("ssSoDonTTDKCCCDChoXacNhan"); %>
<div class="container">
	<jsp:include page="header.jsp"></jsp:include>
</div>
<sec:authorize access="!hasAnyRole('XEM_DANH_SACH_TTDK_CCCD','XAC_NHAN_TTDK_CCCD','CAP_NHAT_TTDK_CCCD')">
	<div class="container mt20" style="min-height:300px">
		<div class="mt80">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasAnyRole('XEM_DANH_SACH_TTDK_CCCD','XAC_NHAN_TTDK_CCCD','CAP_NHAT_TTDK_CCCD')">
<div class="container" style="min-height:300px">
		<div class="row bg-content">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<div class="row radius-01 bg-white">
					<div class="row">
			    		<div class="col-md-12 text-center">
				    		<c:if test="${not empty error}">
								<div class="error ml5">${error}</div>
								<% session.removeAttribute("error"); %>
							</c:if>
							<c:if test="${not empty msg}">
								<div class="msg ml5">${msg}</div>
								<% session.removeAttribute("msg"); %>
							</c:if>
						</div>
			    	</div>
			    	<div class="row title col-md-12 text-center">
			    		THÔNG TIN ĐĂNG KÝ CĂN CƯỚC CÔNG DÂN
			    	</div>
					<div class="row mt60">
						<div class="col-md-12">
			    			<div class="col-md-7" style="min-width:120px;">
			    			</div>
			    			
				    		<div class="col-md-5">
				    			<label class="pull-left mt5">Tìm kiếm</label>
				    			<div class="col-md-8">
					    			<input type=text id=txtTimKiem
					    			 placeholder="Nhập họ tên hoặc mã số .."
					    			  class="form-control" onkeyup="timKiem();"/>
				    			 </div>
				    		</div>
			    		</div>	
					</div>
					<div class="row mt5" id="danh-sach">
		            ${dsLamCCCD}     
	            </div>
				</div>
			</div>
		</div>
</div>
</sec:authorize>
<div class='container'>
	<div class="row">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>
</body>
</html>
<style>
	td a{
		color:blue;
	}
	.table-striped>tbody>tr:nth-of-type(odd):hover{
		background:#adf1b5;
	}
</style>
<script>
	function timKiem(){
		var str = "tuKhoa="+$("#txtTimKiem").val();
		$.ajax({url: 'tim-kiem-don-dang-ky',
			type: 'GET',
			data: str,
		  	success: function(result){
		  		$("#danh-sach").html(result);
	        }});
	}
	function changeNgay(vlue){
		var ngay = ngayThangNam(vlue);
		window.location = "danh-sach-dang-ky?ngay="+ngay;
	}
</script>