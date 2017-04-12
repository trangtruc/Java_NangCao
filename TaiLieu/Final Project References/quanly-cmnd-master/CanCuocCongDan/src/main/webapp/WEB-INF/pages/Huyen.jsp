<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QUẢN LÝ HUYỆN</title>
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
<sec:authorize access="!hasRole('QUAN_LY_HUYEN')">
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
<sec:authorize access="hasRole('QUAN_LY_HUYEN')">
<div class="container" style="min-height:420px">
		<div class="row bg-content">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				
				<form action='them-huyen?${_csrf.parameterName}=${_csrf.token}' enctype="multipart/form-data" method="POST" class="row mt20 display-none radius-01 bg-white" id='huyenForm' onsubmit='return checkHuyen();'>
					<label class='pull-left title'>Thêm huyện</label>
					<div class="row">
						<hr>
						<div class='text-center error display-none' id='kqhuyen'></div>
					</div>
					<div class="col-md-0"></div>
					<div class="col-md-10">
						
						<table border=0 class="row mt20">
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Chọn tỉnh: </label></td>
								<td colspan=3>
								 	<select type=text name=themVaoTinh id=themVaoTinh class='form-control'>
								 		<c:forEach items='${dsTinh}' var='tinh'>
											<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
										</c:forEach>
								 	</select>
								 </td>
							</tr>
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Tự động sinh mã huyện: </label></td>
								<td>
								 	<input type=checkbox name=autoMaHuyen id=autoMaHuyen onclick='sinhMaHuyen();' value="1"/>
								 </td>
								 <td><label class='pull-left mt5'></label></td>
								 <td >
								 </td>
							</tr>
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Mã huyện: </label></td>
								<td>
								 	<input type=text name=themMaHuyen id=themMaHuyen class='form-control' placeholder='Nhập mã huyện ..' onblur='checkMaHuyen()'/>
								 </td>
								 <td><label class='pull-left mt5'>Tên huyện:</label></td>
								 <td >
								 	<input type=text name=themTenHuyen id=themTenHuyen class='form-control' placeholder='Nhập tên huyện ..'/>
								 </td>
							</tr>
							<tr class="row mt5">
								<td></td>
								<td class='pull-right'><button class='btn btn-primary' type=submit >Xong</button></td>
								<td>
								<button class='btn btn-primary' type=button onclick='dongHuyenForm()'>Đóng</button></td>
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
						<div class="col-md-5">
							<div class="title">Danh sách Huyện - Quận của</div>
							<select name=tinh id=tinh onchange='getDanhSachHuyen(this.value)' class='form-control'>
								<c:forEach items='${dsTinh}' var='tinh'>
									<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-4  pull-right tim-kiem">
							<label class='pull-left mt5'>Tìm kiếm</label>
							<div class='col-md-9'>
								<input type=text placeholder='Nhập ít nhất 6 ký tự muốn tìm ..' id='txtTimKiem' onkeyup='timKiemHuyen();' class='form-control'/>
							</div>
						</div>
					</div>
					<div class="row" style='height:420px;overflow:auto;'>
						<div class="col-md-6">
							<table border=1>
								<tr>
								<th style="width:3%">Mã huyện</th>
								<th>Tên huyện</th>
								<th>Tên tỉnh</th>
								<th style="width:4%">Cập nhật</th>
								</tr>
							<tbody id='dsHuyen1'>
								${dsHuyen1}
							</tbody>
							</table>
						</div>
						<div class="col-md-6">
							<table border=1>
								<tr>
								<th style="width:3%">Mã huyện</th>
								<th>Tên huyện</th>
								<th>Tên tỉnh</th>
								<th style="width:4%">Cập nhật</th>
								</tr>
							<tbody id='dsHuyen2'>
								${dsHuyen2}
							</tbody>
							</table>
						</div>
					</div>
					<div class="row mt5"></div>
				</div>
				<div class='pull-right mt10 mb5' >
					<button type=text class="btn btn-primary" onclick='moHuyenForm()'>Thêm huyện</button>
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
	function checkHuyen(){
		dong("kqhuyen");
		var autoChecked = document.getElementById("autoMaHuyen");
		if(!autoChecked.checked){
			if($("#themMaHuyen").val().length != 3){
				$("#themMaHuyen").focus();
				mo("kqhuyen");
				$("#kqhuyen").html("Mã huyện có 3 ký tự");
				return false;
			}
		}
		if($("#themTenHuyen").val() < 1){
			$("#themTenHuyen").focus();
			mo("kqhuyen");
			$("#kqhuyen").html("Chưa nhập tên Huyện");
			return false;
		}
		if($("#themMaHuyen").attr("aria-vlue") == "0"){
			return false;
		}
	}
	function sinhMaHuyen(){
		var autoChecked = document.getElementById("autoMaHuyen");
		if(autoChecked.checked){
			$("#themMaHuyen").attr("disabled","true");
			$("#themMaHuyen").val("auto");
		} else {
			$("#themMaHuyen").removeAttr("disabled");
			$("#themMaHuyen").val("");
		}
	}
	function capNhatHuyen(vlue){
		$("#input"+vlue).html("<textarea rows=4 cols=40 name='"+vlue+"' id='"+vlue+"' class='form-control'>"+$("#txt"+vlue).val()+"</textarea>");
		$("#button"+vlue).html("<a onclick=luu('"+vlue+"')>Lưu </a>|<a onclick=huy('"+vlue+"')> Hủy</a>")
		var dsTinh = "maHuyen="+vlue;
		$.ajax({url: 'get-danh-sach-tinh-update-huyen',
			type: 'GET',
			data: dsTinh,
		  	success: function(result){
		  		$("#select"+vlue).html("<select id='updateTinh"+vlue+"' class='form-control'>"+result+"</select>");
	    }});
		
	}
	function luu(vlue){
		if(!confirm("Bạn có chắc chắn thực hiện điều này? ")){
			return false;
		}
		var update = "maHuyen="+vlue+"&tenHuyen="+$("#"+vlue).val()+"&maTinh="+$("#updateTinh"+vlue).val();
		$.ajax({url: 'update-huyen',
			type: 'GET',
			data: update,
		  	success: function(result){
		  		window.location = "quan-ly-huyen";
	    }});
	}
	function huy(vlue){
		$("#input"+vlue).html($("#txt"+vlue).val());
		$("#button"+vlue).html("<a onclick=capNhatHuyen('"+vlue+"') >Cập nhật</a>")
		$("#select"+vlue).html($("#tenTinh"+vlue).val());
	}
	function getDanhSachHuyen(tinh){
		var tinh = "tinh="+tinh;
		$.ajax({url: 'get-danh-sach-huyen',
			type: 'GET',
			data: tinh,
		  	success: function(result){
		  		var data = result.split("_");
		  		$("#dsHuyen1").html(data[0]);
		  		$("#dsHuyen2").html(data[1]);
	    }});
	}
	function checkMaHuyen(){
		var check = "maHuyen="+$("#themMaHuyen").val();
		$.ajax({url: 'check-ma-huyen',
			type: 'GET',
			data: check,
		  	success: function(result){
		  		$("#kqhuyen").html(result);
		  		if(result.length > 0){
		  			$("#themMaHuyen").attr("aria-vlue","0");
		  		} else {
		  			$("#themMaHuyen").attr("aria-vlue","1");
		  		}
	    }});
	}
	function timKiemHuyen(){
		var key = $("#txtTimKiem").val();
		if(key.length < 6){
			return false;
		}
		var tuKhoa = "keySearch="+key;
		$.ajax({url: 'tim-kiem-huyen',
			type: 'GET',
			data: tuKhoa,
		  	success: function(result){
		  		var data = result.split("_");
		  		$("#dsHuyen1").html(data[0]);
		  		$("#dsHuyen2").html(data[1]);
	    }});
	}
	function moHuyenForm(){
		dong("kqhuyen");
		$("#huyenForm").removeClass("display-none");
		$("#huyenForm").addClass("display-block");
	}
	function dongHuyenForm(){
		$("#huyenForm").removeClass("display-block");
		$("#huyenForm").addClass("display-none");
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