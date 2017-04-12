<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	<%@ include file="/resources/css/bootstrap.min.css" %>
	<%@ include file="/resources/css/style.css" %>
	<%@ include file="/resources/css/theme.css" %>
	<%@ include file="/resources/css/morris.css" %>
	<%@ include file="/resources/css/jquery.jqplot.min.css" %>
	table th{
		text-align: center;
		width:auto;
	}
	table {
		width:100%;
		text-align: center;
	}
</style>
<spring:url value="/resources/js/jquery.min.js" var="jqueryJs" />
<script src="${jqueryJs}"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>THỐNG KÊ ĐƠN ĐĂNG KÝ CĂN CƯỚC CÔNG DÂN ${donVi}, ${thoiGian }</title>
</head>
<body class="bg-white">
<c:if test="${ssTaiKhoan == null}">
	<script>
		window.location = "login";
	</script>
</c:if>
<sec:authorize access="!hasRole('THONG_KE_TTDK_CCCD')">
	<div class="container mt20" style="min-height:360px">
		<div class="mt80">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('THONG_KE_TTDK_CCCD')">
<div class="container mt20">
	<div class="row text-center">
		<label class="title">Thống kê đơn đăng ký căn cước công dân</label> <br>
		<label class="title">${donVi}, ${thoiGian }</label>
		<hr>
		<h4 class="text-left">Cơ cấu đơn đăng ký căn cước công dân ${donVi}, ${thoiGian }</h4>
		<table border="1">
			<tr>
				<td style="width:33%"></td>
				<th style="width:33%">Đơn đăng ký</th>
				<th style="width:33%">Tỷ lệ (%)</th>
			</tr>
			<tr>
				<td>Số đơn chưa duyệt: </td>
				<td>${donChuaDuyet}</td>
				<td>${ptChuaDuyet}</td>
			</tr>
			<tr>
				<td>Số đơn đã cấp phép: </td>
				<td>${donDaCapPhep }</td>
				<td>${ptDaCapPhep}</td>
			</tr>
			<tr>
				<td>Số đơn bị từ chối: </td>
				<td>${donBiTuChoi }</td>
				<td>${ptBiTuChoi}</td>
			</tr>
			<tr>
				<td>Tổng số đơn</td>
				<td>${tong }</td>
				<td>100</td>
			</tr>
		</table>
		<h4 class="text-left">So sánh mức độ tăng giảm</h4>
		${inHinhCot }
	</div>
	<div class="row text-center mt40" id="button">
		<button type=button onclick="inPage();" class='btn btn-primary'>In</button>
		<button type=button onclick="window.close();" class='btn btn-primary'>Đóng</button>
	</div>
</div>
</sec:authorize>
<script>
	function inPage(){
		$("#button").hide();
		window.print();
	}
</script>
</body>
</html>