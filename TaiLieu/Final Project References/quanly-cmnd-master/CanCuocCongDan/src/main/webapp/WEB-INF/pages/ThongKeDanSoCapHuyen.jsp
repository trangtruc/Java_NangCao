<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>THỐNG KÊ DÂN SÔ CẤP HUYỆN</title>
	<style>
table tr:hover {
    background-color: transparent;
}
table tr:nth-child(even):hover {
	background-color: transparent;
}
table td{
	padding:5px;
}
table th{
	width:auto;
}
	.display-inline{
		display: inline-block;
	}
	.chu-thich{
		width:40px;
		height:20px;	
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
<sec:authorize access="!hasRole('THONG_KE_DAN_SO')">
	<div class="container mt20" style="min-height:240px">
		<div class="row mt20">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('THONG_KE_DAN_SO')">
<div class="container" style="min-height:240px">
		<div class="row bg-content">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<div class="row radius-01 bg-white">
					<div class="text-center row title">
						THỐNG KÊ DÂN SỐ<br>
						${tinh.tenTinh } - ${thoiGian }
					</div>
						<label class='col-md-2 mt10'>
							Năm
							<select name="nam" id="nam">
								${selectNam }
							</select>
						</label>
					<label class="col-md-4 mt5"> 
						<button type=button class="btn btn-primary" onclick="thongKeNam();">Thống kê</button> 
					</label>
				</div>
			    <div class="row radius-01 bg-white">
			   
			    	<div class="row">
				    	<div class="col-md-6 mt80 ml20">
				    		<div class="row">
				    			<div class="col-md-2"></div>
					    		<table border=1>
					    			<tr>
					    				<td></td>
					    				<td>Người</td>
					    			</tr>
					    			<c:forEach items='${danSoHuyen}' var='dsh'>
						    			<tr>
						    				<th>${dsh.huyen.tenHuyen}: </th>
						    				<td class='text-left'>${dsh.danSo}</td>
						    			</tr>
					    			</c:forEach>
					    			<tr>
					    				<th>Tổng dân: </th>
					    				<td>${tongDan }</td>
					    			</tr>
					    		</table>
				    		</div>	    		
				    	</div>
				    	<div class="col-md-3 mt5">
						  	<div id="hinhTron">${thongBao }</div>
			            </div>
		            </div>
		            <div class="row mt20">
				    	<div class="col-md-12 mt5" style="min-height:420px;">
						  	<div id="hinhDuong"></div>
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
${hinhTronDanSo}
${hinhDuongDanSo}
function thongKeNam(){
	var nam = $("#nam").val();
	window.location = "thong-ke-dan-so-huyen?nam="+nam;
}
</script>
</body>
</html>