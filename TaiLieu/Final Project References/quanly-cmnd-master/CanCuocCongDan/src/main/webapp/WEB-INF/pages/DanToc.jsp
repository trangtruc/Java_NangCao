<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QUẢN LÝ DÂN TỘC</title>
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
<sec:authorize access="!hasRole('QUAN_LY_DAN_TOC')">
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
<sec:authorize access="hasRole('QUAN_LY_DAN_TOC')">
<div class="container" style="min-height:420px">
	<div class="row text-center">
		<div class="col-md-3"></div>
		${kqDanToc }
		<%
			session.removeAttribute("kqDanToc");
		%>
	</div>
		<div class="row bg-content">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				
				<form action='them-dan-toc?${_csrf.parameterName}=${_csrf.token}' enctype="multipart/form-data" method="POST" class="row mt20 display-none radius-01 bg-white" id='DanTocForm' onsubmit='return checkDanToc();'>
					<label class='pull-left title'>Thêm Dân Tộc</label>
					<div class="row">
						<hr>
					</div>
					<div class="col-md-0"></div>
					<div class="col-md-10">
						<div class='text-center error display-none' id='kqdantoc'></div>
						<table border=0 class="row mt20">
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Tự động sinh mã dân tộc: </label></td>
								<td>
								 	<input type=checkbox name=autoMaDanToc id=autoMaDanToc onclick='sinhMaDanToc();' value="1"/>
								 </td>
								 <td><label class='pull-left mt5'></label></td>
								 <td >
								 </td>
							</tr>
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Mã dân tộc: </label></td>
								<td>
								 	<input type=text name=themMaDanToc id=themMaDanToc class='form-control' placeholder='Nhập mã dân tộc ..' onblur='checkMaDanToc()'/>
								 </td>
								 <td><label class='pull-left mt5'>Tên dân tộc:</label></td>
								 <td >
								 	<input type=text name=themTenDanToc id=themTenDanToc class='form-control' placeholder='Nhập tên dân tộc ..'/>
								 </td>
							</tr>
							<tr class="row mt5">
								<td></td>
								<td class='pull-right'><button class='btn btn-primary' type=submit >Xong</button></td>
								<td>
								<button class='btn btn-primary' type=button onclick='dongDanTocForm()'>Đóng</button></td>
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
						<div class="col-md-5">
							<div class="title">Danh sách dân tộc</div>
						</div>
						<div class="col-md-4  pull-right tim-kiem">
							<label class='pull-left mt5'>Tìm kiếm</label>
							<div class='col-md-9'>
								<input type=text placeholder='Nhập tên dân tộc muốn tìm ..' id='txtTimKiem' onkeyup='timKiemDanToc();' class='form-control'/>
							</div>
						</div>
					</div>
					<div class="row mt5" style='height:420px;overflow:auto;'>
						<div class="col-md-6">
							<table border=1>
								<tr>
								<th style="width:3%">Mã dân tộc</th>
								<th>Tên dân tộc</th>
								<th style="width:4%">Cập nhật</th>
								</tr>
							<tbody id='dsDanToc1'>
								${dsDanToc1}
							</tbody>
							</table>
						</div>
						<div class="col-md-6">
							<table border=1>
								<tr>
								<th style="width:3%">Mã dân tộc</th>
								<th>Tên dân tộc</th>
								<th style="width:4%">Cập nhật</th>
								</tr>
							<tbody id='dsDanToc2'>
								${dsDanToc2}
							</tbody>
							</table>
						</div>
					</div>
					<div class='row mt5'>
						</div>
				</div>
				<div class='pull-right mt10 mb5' >
					<button type=text class="btn btn-primary" onclick='moDanTocForm()'>Thêm Dân tộc</button>
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
	function checkDanToc(){
		dong("kqdantoc")
		var autoChecked = document.getElementById("autoMaDanToc");
		if(!autoChecked.checked){
			if($("#themMaDanToc").val().length != 2){
				$("#themMaDanToc").focus();
				mo("kqdantoc");
				$("#kqdantoc").html("Mã dân tộc có 2 ký tự");
				return false;
			}
		}
		if($("#themTenDanToc").val() < 1){
			$("#themTenDanToc").focus();
			mo("kqdantoc");
			$("#kqdantoc").html("Chưa nhập tên dân tộc");
			return false;
		}
		if($("#themMaDanToc").attr("aria-vlue") == "0"){
			return false;
		}
	}
	function sinhMaDanToc(){
		var autoChecked = document.getElementById("autoMaDanToc");
		if(autoChecked.checked){
			$("#themMaDanToc").attr("disabled","true");
			$("#themMaDanToc").val("auto");
		} else {
			$("#themMaDanToc").removeAttr("disabled");
			$("#themMaDanToc").val("");
		}
	}
	function checkMaDanToc(){
		var check = "maDT="+$("#themMaDanToc").val();
		$.ajax({url: 'check-ma-dan-toc',
			type: 'GET',
			data: check,
		  	success: function(result){
		  		$("#kqdantoc").html(result);
		  		if(result.length > 0){
		  			$("#themMaDanToc").attr("aria-vlue","0");
		  		} else {
		  			$("#themMaDanToc").attr("aria-vlue","1");
		  		}
	    }});
	}
	function capNhatDanToc(vlue){
		$("#input"+vlue).html("<input type=text name='"+vlue+"' id='"+vlue+"' value='"+$("#txt"+vlue).val()+"' class='form-control'>");
		$("#button"+vlue).html("<a onclick=luu('"+vlue+"')>Lưu </a>|<a onclick=huy('"+vlue+"')> Hủy</a>")
	}
	function huy(vlue){
		$("#input"+vlue).html($("#txt"+vlue).val());
		$("#button"+vlue).html("<a onclick=capNhatDanToc('"+vlue+"') >Cập nhật</a>")
	}
	function luu(vlue){
		if(!confirm("Bạn có chắc chắn thực hiện điều này? ")){
			return false;
		}
		var update = "maDanToc="+vlue+"&tenDanToc="+$("#"+vlue).val();
		$.ajax({url: 'update-dan-toc',
			type: 'GET',
			data: update,
		  	success: function(result){
		  		window.location = "quan-ly-dan-toc";
	    }});
	}
	function timKiemDanToc(){
		var tuKhoa = "keySearch="+$("#txtTimKiem").val();
		$.ajax({url: 'tim-kiem-dan-toc',
			type: 'GET',
			data: tuKhoa,
		  	success: function(result){
		  		var data = result.split("_");
		  		$("#dsDanToc1").html(data[0]);
		  		$("#dsDanToc2").html(data[1]);
	    }});
	}
	function moDanTocForm(){
		dong("kqdantoc");
		$("#DanTocForm").removeClass("display-none");
		$("#DanTocForm").addClass("display-block");
	}
	function dongDanTocForm(){
		$("#DanTocForm").removeClass("display-block");
		$("#DanTocForm").addClass("display-none");
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