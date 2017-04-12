<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QUẢN LÝ XÃ</title>
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
<sec:authorize access="!hasRole('QUAN_LY_XA')">
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
<sec:authorize access="hasRole('QUAN_LY_XA')">
<div class="container" style="min-height:420px">
	<div class="row text-center">
		<div class="col-md-3"></div>
		${kqXa }
		<%
			session.removeAttribute("kqXa");
		%>
	</div>
		<div class="row bg-content">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				
				<form action='them-xa?${_csrf.parameterName}=${_csrf.token}' enctype="multipart/form-data" method="POST" class="row mt20 display-none radius-01 bg-white" id='XaForm' onsubmit='return checkXa();'>
					<label class='pull-left title'>Thêm xã</label>
					<div class="row">
						<hr>
						<div class='text-center error display-none' id='kqxa'></div>
					</div>
					<div class="col-md-0"></div>
					<div class="col-md-10">
						
						<table border=0 class="row mt20">
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Chọn tỉnh: </label></td>
								<td colspan=3>
								 	<select type=text name=themVaoTinh id=themVaoTinh class='form-control' onclick='getHuyen(this.id, this.value);' onchange='getHuyen(this.id, this.value);'>
								 		<c:forEach items='${dsTinh}' var='tinh'>
											<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
										</c:forEach>
								 	</select>
								 </td>
							</tr>
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Chọn huyện: </label></td>
								<td colspan=3>
								 	<select type=text name=themVaoHuyen id=themVaoHuyen class='form-control'>
								 		<c:forEach items='${dsHuyen}' var='huyen'>
											<option id="option" value="${huyen.maHuyen}">${huyen.tenHuyen}</option>
										</c:forEach>
								 	</select>
								 </td>
							</tr>
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Tự động sinh mã xã: </label></td>
								<td>
								 	<input type=checkbox name=autoMaXa id=autoMaXa onclick='sinhMaXa();' value="1"/>
								 </td>
								 <td><label class='pull-left mt5'></label></td>
								 <td >
								 </td>
							</tr>
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Mã xã: </label></td>
								<td>
								 	<input type=text name=themMaXa id=themMaXa class='form-control' placeholder='Nhập mã xã ..' onblur='checkMaXa()'/>
								 </td>
								 <td><label class='pull-left mt5'>Tên xã:</label></td>
								 <td >
								 	<input type=text name=themTenXa id=themTenXa class='form-control' placeholder='Nhập tên xã ..'/>
								 </td>
							</tr>
							<tr class="row mt5">
								<td></td>
								<td class='pull-right'><button class='btn btn-primary' type=submit >Xong</button></td>
								<td>
								<button class='btn btn-primary' type=button onclick='dongXaForm()'>Đóng</button></td>
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
					<div class="row mt5">
						<div class="col-md-7">
							<div class="title">Danh sách Xã - Phường của</div>
							<div class="col-md-5">
								<select name=xaTinh id=xaTinh onclick='getHuyen(this.id, this.value);' onchange='getHuyen(this.id, this.value);' class='form-control'>
									<c:forEach items='${dsTinh}' var='tinh'>
										<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-5">
								<select name=xaHuyen id=xaHuyen onchange='getDanhSachXa(this.value)' class='form-control'>
									<c:forEach items='${dsHuyen}' var='huyen'>
										<option id="option" value="${huyen.maHuyen}">${huyen.tenHuyen}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-4  pull-right tim-kiem">
							<label class='pull-left mt5'>Tìm kiếm</label>
							<div class='col-md-9'>
								<input type=text placeholder='Nhập ít nhất 6 ký tự muốn tìm ..' id='txtTimKiem' onkeyup='timKiemXa();' class='form-control'/>
							</div>
						</div>
					</div>
					<div class="row mt5" style='height:420px;overflow:auto;'>
						<div class="col-md-6">
							<table border=1>
								<tr>
								<th style="width:3%">Mã xã</th>
								<th>Tên xã</th>
								<th>Tên huyện</th>
								<th>Tên tỉnh</th>
								<th style="width:4%">Cập nhật</th>
								</tr>
							<tbody id='dsXa1'>
								${dsXa1}
							</tbody>
							</table>
						</div>
						<div class="col-md-6">
							<table border=1>
								<tr>
								<th style="width:3%">Mã xã</th>
								<th>Tên xã</th>
								<th>Tên huyện</th>
								<th>Tên tỉnh</th>
								<th style="width:4%">Cập nhật</th>
								</tr>
							<tbody id='dsXa2'>
								${dsXa2}
							</tbody>
							</table>
						</div>
					</div>
					<div class="row mt5"></div>
				</div>
				<div class='pull-right mt10 mb5' >
					<button type=text class="btn btn-primary" onclick='moXaForm()'>Thêm xã</button>
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
	function checkXa(){
		var autoChecked = document.getElementById("autoMaXa");
		if(!autoChecked.checked){
			if($("#themMaXa").val().length != 5){
				$("#themMaXa").focus();
				mo("kqxa");
				$("#kqxa").html("Mã xã có 5 ký tự");
				return false;
			}
		}
		if($("#themTenXa").val() < 1){
			$("#themTenXa").focus();
			mo("kqxa");
			$("#kqxa").html("Chưa nhập tên xã");
			return false;
		}
		if($("#themMaXa").attr("aria-vlue") == "0"){
			return false;
		}
	}
	function sinhMaXa(){
		var autoChecked = document.getElementById("autoMaXa");
		if(autoChecked.checked){
			$("#themMaXa").attr("disabled","true");
			$("#themMaXa").val("0");
		} else {
			$("#themMaXa").removeAttr("disabled");
		}
	}
	function checkMaXa(){
		var check = "maXa="+$("#themMaXa").val();
		$.ajax({url: 'check-ma-xa',
			type: 'GET',
			data: check,
		  	success: function(result){
		  		$("#kqxa").html(result);
		  		if(result.length > 0){
		  			$("#themMaXa").attr("aria-vlue","0");
		  		} else {
		  			$("#themMaXa").attr("aria-vlue","1");
		  		}
	    }});
	}
	function capNhatXa(vlue){
		$("#input"+vlue).html("<textarea rows=4 cols=40 name='"+vlue+"' id='"+vlue+"' class='form-control'>"+$("#txt"+vlue).val()+"</textarea>");
		$("#button"+vlue).html("<a onclick=luu('"+vlue+"')>Lưu </a>|<a onclick=huy('"+vlue+"')> Hủy</a>")
		var dsHuyenTinh = "maXa="+vlue;
		$.ajax({url: 'get-danh-sach-huyen-tinh-update-xa',
			type: 'GET',
			data: dsHuyenTinh,
		  	success: function(result){
		  		var data = result.split("_");
		  		$("#selectHuyen"+vlue).html("<select id='update"+vlue+"Huyen' class='form-control'>"+data[0]+"</select>");
		  		$("#selectTinh"+vlue).html("<select id='update"+vlue+"Tinh' class='form-control' onclick='getHuyen(this.id, this.value);' onchange='getHuyen(this.id, this.value);' >"+data[1]+"</select>");
		  	}});
	}
	function huy(vlue){
		$("#input"+vlue).html($("#txt"+vlue).val());
		$("#button"+vlue).html("<a onclick=capNhatXa('"+vlue+"') >Cập nhật</a>")
		$("#selectTinh"+vlue).html($("#tenTinh"+vlue).val());
		$("#selectHuyen"+vlue).html($("#tenHuyen"+vlue).val());
	}
	function luu(vlue){
		if(!confirm("Bạn có chắc chắn thực hiện điều này? ")){
			return false;
		}
		var update = "maXa="+vlue+"&tenXa="+$("#"+vlue).val()+"&maHuyen="+$("#update"+vlue+"Huyen").val();
		$.ajax({url: 'update-xa',
			type: 'GET',
			data: update,
		  	success: function(result){
		  		window.location = "quan-ly-xa";
	    }});
	}
	function getDanhSachXa(huyen){
		var huyen = "huyen="+huyen;
		$.ajax({url: 'get-danh-sach-xa',
			type: 'GET',
			data: huyen,
		  	success: function(result){
		  		var data = result.split("_");
		  		$("#dsXa1").html(data[0]);
		  		$("#dsXa2").html(data[1]);
	    }});
	}
	function timKiemXa(){
		var key = $("#txtTimKiem").val();
		if(key.length < 6){
			return false
		}
		var tuKhoa = "keySearch="+key
		$.ajax({url: 'tim-kiem-xa',
			type: 'GET',
			data: tuKhoa,
		  	success: function(result){
		  		var data = result.split("_");
		  		$("#dsXa1").html(data[0]);
		  		$("#dsXa2").html(data[1]);
	    }});
	}
	function moXaForm(){
		dong("kqxa");
		$("#XaForm").removeClass("display-none");
		$("#XaForm").addClass("display-block");
	}
	function dongXaForm(){
		$("#XaForm").removeClass("display-block");
		$("#XaForm").addClass("display-none");
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