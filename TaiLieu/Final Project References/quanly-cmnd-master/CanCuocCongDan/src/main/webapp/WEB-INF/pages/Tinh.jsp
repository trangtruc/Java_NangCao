<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QUẢN LÝ TỈNH</title>
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
<sec:authorize access="!hasRole('QUAN_LY_TINH')">
	<div class="container mt20" style="min-height:420px">
		<div class="mt80">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('QUAN_LY_TINH')">
<div class="container" style="min-height:420px">
		<div class="row bg-content">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form action='them-tinh?${_csrf.parameterName}=${_csrf.token}' enctype="multipart/form-data" method="POST" class="row display-none radius-01 bg-white" id='tinhForm' onsubmit='return checkTinh();'>
					<label class='pull-left title'>Thêm tỉnh</label>
					<div class="row">
						<hr>
						<div class='text-center error display-none' id='kqtinh'></div>
					</div>
					<div class="col-md-0"></div>
					<div class="col-md-12">
						<table border=0 class="row mt20">
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Tự động sinh mã tỉnh: </label></td>
								<td>
								 	<input type=checkbox name=autoMaTinh id=autoMaTinh onclick='sinhMaTinh();' value="1"/>
								 </td>
								 <td><label class='pull-left mt5'></label></td>
								 <td >
								 </td>
							</tr>
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Mã tỉnh: </label></td>
								<td>
								 	<input type=text name=themMaTinh id=themMaTinh class='form-control' placeholder='Nhập mã tỉnh ..' onblur='checkMaTinh()'/>
								 </td>
								 <td><label class='pull-left mt5'>Tên tỉnh:</label></td>
								 <td >
								 	<input type=text name=themTenTinh id=themTenTinh class='form-control' placeholder='VD: Thành Phố Hồ Chí Minh hoặc Tỉnh Đồng Tháp'/>
								 </td>
							</tr>
							<tr class="row mt5">
								<td></td>
								<td class='pull-right'><button class='btn btn-primary' type=submit >Xong</button></td>
								<td>
								<button class='btn btn-primary' type=button onclick='dongTinhForm()'>Đóng</button></td>
								<td></td>
							</tr>
						</table>
					</div>
				</form>
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
					<div class="row mt20">
						<div class="col-md-7">
							<div class="title">Danh sách tỉnh</div>
						</div>
						<div class="col-md-4  pull-right tim-kiem">
							<label class='pull-left mt5'>Tìm kiếm</label>
							<div class='col-md-9'>
								<input type=text placeholder='Nhập tỉnh muốn tìm ..' id='txtTimKiem' onkeyup='timKiemTinh();' class='form-control'/>
							</div>
						</div>
					</div>
					
					<div class="row" style='height:420px;overflow:auto;'>
						<div class="col-md-6">
							<table border=1>
								<tr>
								<th>Mã tỉnh</th>
								<th>Tên tỉnh</th>
								<th>Cập nhật</th>
								</tr>
							<tbody id='dsTinh1'>
								${dsTinh1}
							</tbody>
							</table>
						</div>
						<div class="col-md-6">
							<table border=1>
								<tr>
								<th>Mã tỉnh</th>
								<th>Tên tỉnh</th>
								<th>Cập nhật</th>
								</tr>
							<tbody id='dsTinh2'>
								${dsTinh2}
							</tbody>
							</table>
						</div>
					</div>
					<div class='row mt5'></div>
				</div>
				<div class='pull-right mt10 mb5' >
					<button type='button' class="btn btn-primary" onclick='moTinhForm()'>Thêm tỉnh</button>
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
	function checkTinh(){
		dong("kqtinh");
		var autoChecked = document.getElementById("autoMaTinh");
		if(!autoChecked.checked){
			if($("#themMaTinh").val().length != 2){
				$("#themMaTinh").focus();
				mo("kqtinh");
				$("#kqtinh").html("Mã tỉnh có 2 ký tự");
				return false;
			}
		}
		if($("#themTenTinh").val()<1){
			$("#themTenTinh").focus();
			mo("kqtinh");
			$("#kqtinh").html("Chưa nhập tên tỉnh");
			return false;
		}
		if($("#themMaTinh").attr("aria-vlue") == "0"){
			return false;
		}
	}
	function sinhMaTinh(){
		var autoChecked = document.getElementById("autoMaTinh");
		if(autoChecked.checked){
			$("#themMaTinh").attr("disabled","true");
			$("#themMaTinh").val("auto");
		} else {
			$("#themMaTinh").removeAttr("disabled");
			$("#themMaTinh").val("");
		}
	}
	function capNhatTinh(vlue){
		$("#input"+vlue).html("<textarea rows=4 cols=40 name='"+vlue+"' id='"+vlue+"' class='form-control'>"+$("#txt"+vlue).val()+"</textarea>");
		$("#button"+vlue).html("<a onclick=luu('"+vlue+"')>Lưu </a>|<a onclick=huy('"+vlue+"')> Hủy</a>")
	}
	function luu(vlue){
		if(!confirm("Bạn có chắc chắn thực hiện điều này? ")){
			return false;
		}
		var update = "maTinh="+vlue+"&tenTinh="+$("#"+vlue).val();
		$.ajax({url: 'update-tinh',
			type: 'GET',
			data: update,
		  	success: function(result){
		  		window.location = "quan-ly-tinh";
	    }});
	}
	function huy(vlue){
		$("#input"+vlue).html($("#txt"+vlue).val());
		$("#button"+vlue).html("<a onclick=capNhatTinh('"+vlue+"') >Cập nhật</a>")
	}
	function checkMaTinh(){
		var check = "maTinh="+$("#themMaTinh").val();
		$.ajax({url: 'check-ma-tinh',
			type: 'GET',
			data: check,
		  	success: function(result){
		  		$("#kqtinh").html(result);
		  		if(result.length > 0){
		  			$("#themMaTinh").attr("aria-vlue","0");
		  		} else {
		  			$("#themMaTinh").attr("aria-vlue","1");
		  		}
	    }});
	}
	function timKiemTinh(){
		var tuKhoa = "keySearch="+$("#txtTimKiem").val();
		$.ajax({url: 'tim-kiem-tinh',
			type: 'GET',
			data: tuKhoa,
		  	success: function(result){
		  		var data = result.split("_");
		  		$("#dsTinh1").html(data[0]);
		  		$("#dsTinh2").html(data[1]);
	    }});
	}
	function moTinhForm(){
		dong("kqtinh");
		mo("tinhForm");
	}
	function dongTinhForm(){
		dong("tinhForm");
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