<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QUẢN LÝ YÊU CẦU ĐĂNG KÝ CMND/CCCD</title>
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
<sec:authorize access="!hasRole('QUAN_LY_YEU_CAU_CCCD')">
<div class="container" style="min-height:420px">
	<div class="row text-center">
		<div class="error title">ERROR: Bạn không có quyền thực hiện thao tác này</div>
	</div>
</div>
</sec:authorize>
<sec:authorize access="hasRole('QUAN_LY_YEU_CAU_CCCD')">
<div class="container" style="min-height:420px">
	<div class="row text-center">
		<div class="col-md-3"></div>
		${kqYeuCau }
		<% session.removeAttribute("kqYeuCau"); %>
	</div>
		<div class="row bg-content">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form action='them-yeu-cau?${_csrf.parameterName}=${_csrf.token}' enctype="multipart/form-data" method="POST" class="row mt20 display-none radius-01 bg-white" id='YeuCauForm' onsubmit='return checkThemYeuCau();'>
					<label class='pull-left title'>Thêm yêu cầu</label>
					<div class="row">
						<hr>
						<div class='text-center error display-none' id='kqthemyeucau'></div>
					</div>
					
					<div class="col-md-0"></div>
					<div class="col-md-10">
						<table border=0 class="row mt20">
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Tên yêu cầu: </label></td>
								<td>
								 	<input type=text name=themTenYeuCau id=themTenYeuCau class='form-control' placeholder='Nhập yêu cầu ..'/>
								 </td>
								 
							</tr>
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Mô tả:</label></td>
								 <td >
								 	<input type=text name=themMoTa id=themMoTa class='form-control' placeholder='Nhập mô tả ..'/>
								 </td>
							</tr>
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Giấy tờ đi kèm:</label></td>
								 <td >
								 	<input type=text name=themGiayTo id=themGiayTo class='form-control' placeholder='Nhập giấy tờ ..'/>
								 </td>
							</tr>
							<tr class="row mt5">
								<td><label class='pull-left mt5'>Lệ phí: </label></td>
								<td >
								 	<input type="number" name=themLePhi id=themLePhi class='form-control' style='color:red' placeholder='Nhập lệ phí. VD: 15000 '/>
								 </td>
							</tr>
							<tr class="row mt5">
								<td></td>
								<td colspan=3 class='text-center'><button class='btn btn-primary' type=submit >Xong</button>
								<button class='btn btn-primary' type=button onclick='dongYeuCauForm()'>Đóng</button></td>
								<td></td>
							</tr>
						</table>
					</div>
				</form>
				<div class="radius-01 bg-white text-center">
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
					<label class="title ">Quản lý yêu cầu đăng ký CCCD/CMND</label>
					<div class="row" style='height:320px;overflow:auto;'>
						<table border=1>
							<tr>
							<th style='width:8%'>Tên yêu cầu</th>
							<th style='width:20%'>Mô tả</th>
							<th style='width:18%'>Giấy tờ kèm theo</th>
							<th>Lệ phí</th>
							<th style='width:8%'>Cập nhật</th>
							<th style='width:10%'>Tình trạng</th>
							</tr>
							
						<tbody>
							${dsYeuCau}
						</tbody>
						</table>
					</div>
					<div class="row mt5"></div>
				</div>
				<div class='pull-right mt10 mb5'>
					<button type=text class="btn btn-primary" onclick='moYeuCauForm()'>Thêm yêu cầu mới</button>
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
	function checkThemYeuCau(){
		dong("kqthemyeucau");
		if($("#themTenYeuCau").val().length < 2){
			$("#themTenYeuCau").focus();
			mo("kqthemyeucau");
			$("#kqthemyeucau").html("Chưa nhập tên yêu cầu");
			return false;
		}
		if($("#themMoTa").val().length < 2){
			$("#themMoTa").focus();
			mo("kqthemyeucau");
			$("#kqthemyeucau").html("Chưa nhập mô tả yêu cầu");
			return false;
		}
		if($("#themGiayTo").val().length < 2){
			$("#themGiayTo").focus();
			mo("kqthemyeucau");
			$("#kqthemyeucau").html("Chưa nhập giấy tờ đi kèm");
			return false;
		}
		if($("#themLePhi").val().length < 1){
			$("#themLePhi").focus();
			mo("kqthemyeucau");
			$("#kqthemyeucau").html("Chưa nhập lệ phí");
			return false;
		}
	} 
	function tamDungYeuCau(YeuCau){
		if(!confirm("Bạn có chắc chắn tạm dừng yêu cầu: "+$("#txtTen"+YeuCau).val())){
			return false;
		}
		var YeuCau = "maYeuCau="+YeuCau;
		$.ajax({url: 'tam-dung-yeu-cau-cccd',
			type: 'GET',
			data: YeuCau,
		  	success: function(result){
		  		window.location = "quan-ly-yeu-cau-dang-ky-cmnd-cccd";
	    }});
	}
	function tiepTucYeuCau(YeuCau){
		if(!confirm("Bạn có chắc chắn tiếp tục yêu cầu: "+$("#txtTen"+YeuCau).val())){
			return false;
		}
		var YeuCau = "maYeuCau="+YeuCau;
		$.ajax({url: 'tiep-tuc-yeu-cau-cccd',
			type: 'GET',
			data: YeuCau,
		  	success: function(result){
		  		window.location = "quan-ly-yeu-cau-dang-ky-cmnd-cccd";
	    }});
	}
	function capNhatYeuCau(vlue){
		$("#button"+vlue).html("<a onclick=luu('"+vlue+"')>Lưu </a>|<a onclick=huy('"+vlue+"')> Hủy</a>")
		$("#inputTen"+vlue).html("<input type=text class='form-control' id='updateTen"+vlue+"' value='"+$("#txtTen"+vlue).val()+"' />");
		$("#inputMoTa"+vlue).html("<textarea rows=4 cols=40 class='form-control' id='updateMoTa"+vlue+"'>"+$("#txtMoTa"+vlue).val()+"</textarea>");
		$("#inputGiayTo"+vlue).html("<textarea rows=4 cols=40 class='form-control' id='updateGiayTo"+vlue+"' >"+$("#txtGiayTo"+vlue).val()+"</textarea>");
		$("#inputLePhi"+vlue).html("<input type=number class='form-control' id='updateLePhi"+vlue+"' value="+$("#txtLePhi"+vlue).val()+" />");
	}
	function luu(vlue){
		if(!confirm("Bạn có chắc chắn thực hiện điều này? ")){
			return false;
		}
		var update = "maYC="+vlue+"&updateTenYC="+$("#updateTen"+vlue).val()+"&updateMoTa="+$("#updateMoTa"+vlue).val()+"&updateGiayTo="+$("#updateGiayTo"+vlue).val()+"&updateLePhi="+$("#updateLePhi"+vlue).val();
		$.ajax({url: 'update-yeu-cau',
			type: 'GET',
			data: update,
		  	success: function(result){
		  		window.location = "quan-ly-yeu-cau-dang-ky-cmnd-cccd";
	    }});
	}
	function huy(vlue){
		$("#inputTen"+vlue).html($("#txtTen"+vlue).val());
		$("#inputMoTa"+vlue).html($("#txtMoTa"+vlue).val());
		$("#inputGiayTo"+vlue).html($("#txtGiayTo"+vlue).val());
		$("#inputLePhi"+vlue).html($("#txtLePhi"+vlue).val());
		$("#button"+vlue).html("<a onclick=capNhatYeuCau('"+vlue+"') >Cập nhật</a>")
	}
	function moYeuCauForm(){
		dong("kqthemyeucau");
		$("#YeuCauForm").removeClass("display-none");
		$("#YeuCauForm").addClass("display-block");
	}
	function dongYeuCauForm(){
		$("#YeuCauForm").removeClass("display-block");
		$("#YeuCauForm").addClass("display-none");
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