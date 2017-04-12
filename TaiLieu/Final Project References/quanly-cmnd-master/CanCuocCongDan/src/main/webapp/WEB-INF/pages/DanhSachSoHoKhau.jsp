<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DANH SÁCH SỔ HỘ KHẨU</title>
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
	</div>
		
	<div class="container" style="min-height:364px">
		<div class="row bg-content">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
		<!-- 			Start table -->
		<div class="col-md-9 mb10 mt5 bg-white radius-01" >
			<div class="row mt5">
				<div class="text-center  title col-md-offset-3 ">
						Danh Sách Sổ Hộ Khẩu
				</div>
			</div>
			<div class="col-md-offset-5 col-md-2">
		    	<label class='mt5' style="font-size: 20px;"> Tìm kiếm</label>
		    </div>
		    <div class=' col-md-4'>
		    	<input type=text name='timKiem' id=timKiem class='form-control' onkeyup='timKiemHK()' placeholder='Nhập mã sổ hộ khẩu' />
		    </div>
			<table class="table table-condensed" style="width: 97%"  >
					<tr  >
						<th style = "width: 20%; text-align: center; ">Số sổ hộ khẩu</th>
						<th style = "width: 70% ; text-align: center;">Địa chỉ</th>
						<th style = "width: 10% ; text-align: center;">Chi tiết</th>
					</tr>
					<tbody id="DS-HK" style=" background-color: rgba(245, 173, 0, 0.38);">
					</tbody>
				<c:forEach items='${dsSHK}' var="ds" >
			         <tbody>
						<tr>
							<td>${ds.soHK}</td>
							<td>${ds.diaChi}</td>
							<td><a href="chi-tiet-so-ho-khau?soHK=${ds.soHK}" >Chi tiết</a></td>
						</tr>
					</tbody>
				</c:forEach>
				
				
			</table>
			<!-- 			End table -->
		</div>
		</div>
		</div>
<div class="container">
	<div class="row">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>
<script>
	function timKiemHK(){
		var str = "tuKhoa="+$("#timKiem").val();
		$.ajax({url: 'tim-kiem-danh-sach-so-ho-khau',
			type: 'GET',
			data: str,
		  	success: function(result){
		  		var html;
		  		
		  		$("#DS-HK").html(result);
	        }});
	}
</script>
</body>
</html>