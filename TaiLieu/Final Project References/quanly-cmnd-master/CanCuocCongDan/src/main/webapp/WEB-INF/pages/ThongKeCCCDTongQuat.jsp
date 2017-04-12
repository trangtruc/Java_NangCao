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
	<title>THỐNG KÊ HỒ SƠ ĐĂNG KÝ CHỨNG MINH THƯ ĐIỆN TỬ</title>
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
<sec:authorize access="!hasRole('THONG_KE_TTDK_CCCD')">
	<div class="container" style="min-height:240px">
		<div class="row ">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('THONG_KE_TTDK_CCCD')">
<div class="container" style="min-height:240px">
		<div class="row bg-content">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9 mb5">
				<div class="row radius-01 bg-white">
					<div class="text-center row title">
						THỐNG KÊ HỒ SƠ ĐĂNG KÝ CĂN CƯỚC CÔNG DÂN <br>
						${thoiGian }
					</div>	
					<label class="text-center">
						Tháng
						<select name="thang" id="thang">
						<%
							for(int i = 1; i <= 12; i++){
								if(i < 10){
									out.print("<option value='0"+i+"' >0"+i+"</option>");
								} else {
									out.print("<option value="+i+" >"+i+"</option>");
								}
							}
						%>
						</select>
						năm
						<select name="nam" id="nam">
						${selectNam }
						</select>
						<button type=button class="btn btn-primary" onclick="thongKeThang();">Thống kê tháng</button> 
						<button type=button class="btn btn-primary" onclick="thongKeNam();">Thống kê năm</button> 
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
					    				<td>Đơn đăng ký</td>
					    			</tr>
					    			<tr>
					    				<th>Số đơn chưa duyệt: </th>
					    				<td class='text-left'>${donChuaDuyet}</td>
					    			</tr>
					    			<tr>
					    				<th>Số đơn đã cấp phép: </th>
					    				<td>${donDaCapPhep }</td>
					    			</tr>
					    			<tr>
					    				<th>Số đơn bị từ chối: </th>
					    				<td>${donBiTuChoi }</td>
					    			</tr>
					    			<tr>
					    				<th>Tổng số đơn</th>
					    				<td>${tong }</td>
					    			</tr>
					    		</table>
				    		</div>	    		
				    	</div>
				    	<div class="col-md-3 mt5">
						  	<div id="hinhTron">
						  		<c:if test="${not empty error}">
									<div class="error ml5 text-center">${error}</div>
									<% session.removeAttribute("error"); %>
								</c:if>
						  	</div>
			            </div>
		            </div>
		            <div class="row">
		            	<div class="col-md-12 mt5">
			            	<div class="row">
				            	<div class="col-md-3 pull-right">
				            		<label>Chú thích</label>
				            	</div>
			            	</div>
			            	<div class="row">
				            	<div class="col-md-3 pull-right">
				            		<div class="chu-thich-y chu-thich display-inline">
				            			
				            		</div>
				            		Y: Đã cấp phép
				            	</div>
			            	</div>
			            	<div class="row">
				            	<div class="col-md-3 pull-right">
				            		<div class="chu-thich-a chu-thich display-inline">
				            			
				            		</div>
				            		A: Chưa duyệt
				            	</div>
			            	</div>
			            	<div class="row">
				            	<div class="col-md-3 pull-right">
				            		<div class="chu-thich-z chu-thich display-inline">
				            			
				            		</div>
				            		Z: Bị từ chối
				            	</div>
			            	</div>
			            	
			            	<div class="row">
				            	<div class="col-md-3 pull-right">          
				            		Đơn vị: 
				            				Đơn đăng ký
				            	</div>
			            	</div>
			            </div>
				    	<div class="col-md-12 mt5">
						  	<div id="hinhCot"></div>
			            </div>
			            <div class="col-md-12 mt20 text-center">
			            	<c:if test="${!thang eq '' || thang != null }">
			            		<a href='in-thong-ke-cccd-tong-quat?thang=${thang }&nam=${nam}' class="btn btn-primary" target=_blank>In thống kê</a>
			           		</c:if>
			           		<c:if test="${(thang eq '' || thang == null) && (nam eq '' || nam == null) }">
			            		<a href='in-thong-ke-cccd-tong-quat' class="btn btn-primary" target=_blank>In thống kê</a>
			           		</c:if>
			           		<c:if test="${(thang eq '' || thang == null) && (!nam eq '' || nam != null) }">
			            		<a href='in-thong-ke-cccd-tong-quat?nam=${nam }' class="btn btn-primary" target=_blank>In thống kê</a>
			           		</c:if>
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
${soDoHinhTron}
${soDoHinhCot}
function thongKeNam(){
	var nam = $("#nam").val();
	window.location = "thong-ke-cccd-tong-quat?nam="+nam;
}
function thongKeThang(){
	var thang = $("#thang").val();
	var nam = $("#nam").val();
	window.location = "thong-ke-cccd-tong-quat?thang="+thang+"&nam="+nam;
}
</script>
</body>
</html>
<style>
	td a{
		color:blue;
	}
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
	.chu-thich-y{
		background:rgb(11, 98, 164);
	}
	.chu-thich-a{
		background:rgb(77, 167, 77);
	}
	.chu-thich-z{
		background:rgb(122, 146, 163);
	}
</style>