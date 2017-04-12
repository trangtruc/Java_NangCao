<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<style>
.so-cc{
    position: absolute;
    top: 90px;
    left: 260px;
    font-size: 24px;
    color: red;
}
.ho-ten{
    position: absolute;
    top: 122px;
    left: 290px;
    font-size: 16px;
    color: black;
}
.ho-ten-khac{
    position: absolute;
    top: 157px;
    left: 290px;
    font-size: 16px;
    color: black;
}
.ngay-sinh{
    position: absolute;
    top: 195px;
    left: 310px;
    font-size: 16px;
    color: black;
}
.gioi-tinh{
    position: absolute;
    top: 214px;
    left: 230px;
    font-size: 16px;
    color: black;
}
.dan-toc{
    position: absolute;
    top: 214px;
    left: 370px;
    font-size: 16px;
    color: black;
}
.que-quan{
    position: absolute;
    top: 234px;
    left: 240px;
    font-size: 16px;
    color: black;
}
.thuong-tru{
    position: absolute;
    width:240px;
    top: 272px;
    left: 260px;
    font-size: 14px;
    color: black;
}
.thoi-han{
    position: absolute;
    top: 295px;
    left: 105px;
    font-size: 12px;
    color: black;
}
.hinh-the{
    position: absolute;
    top: 120px;
    left: 25px;
}
.hinh-the img{
	border-radius:5px;
}
.dac-diem{
	position: absolute;
    top: 107px;
    left: 305px;
    font-size: 16px;
    color: black;
}
.ngay-cap{
	position: absolute;
    top: 155px;
    left: 330px;
    font-size: 16px;
    color: black;
}
.thang-cap{
	position: absolute;
    top: 155px;
    left: 395px;
    font-size: 16px;
    color: black;
}
.nam-cap{
	position: absolute;
    top: 155px;
    left: 450px;
    font-size: 16px;
    color: black;
}
.ky-ten{
	position: absolute;
    top: 250px;
    left: 280px;
    width:220px;
   text-align:center;
    font-size: 16px;
    color: black;
}
.bar-code{
	position: absolute;
	top: 22px;
    left: 50px;
}
.van-tay-trai{
	position: absolute;
	top: 70px;
    left: 35px;
	transform: rotate(90deg);
	-webkit-transform: rotate(90deg);
	-ms-transform: rotate(90deg);
	-moz-transform: rotate(90deg);
}
.van-tay-phai{
	position: absolute;
	top: 190px;
    left: 35px;
	transform: rotate(90deg);
	-webkit-transform: rotate(90deg);
	-ms-transform: rotate(90deg);
	-moz-transform: rotate(90deg);
}
label{
	 font-size: 16px;
}
table[border="1"] tr:hover {
    background: #fafafa;
}
</style>
</head>
<body>
<sec:authorize access="!hasRole('IN_TRA_THE')">
	<div class="container mt20" style="min-height:240px">
		<div class="mt20">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('IN_TRA_THE')">
<c:if test="${error != null }">
	<div class="error">${error }</div>
</c:if>
<c:if test="${error == null }">
	<div class="container">
		<input type=hidden id=soCC value="${cccd.soCC }" />
		<div class="col-md-6">
			<img alt="" src="resources/image/the-cmnd-mat-truoc.png" >
			<div class="so-cc"><b>${cccd.soCC }</b></div>
			<div class="ho-ten"><b>${cccd.hoTen }</b></div>
			<div class="ho-ten-khac"><b>${cccd.hoTenKhac }</b></div>
			<div class="ngay-sinh"><b>${ngaySinh}</b></div>
			<div class="gioi-tinh"><b>${cccd.gioiTinh}</b></div>
			<div class="dan-toc"><b>${danToc.tenDT }</b></div>
			<div class="que-quan"><b>${queQuan }</b></div>
			<div class="thuong-tru"><b>${thuongTru }</b></div>
			<div class="thoi-han"><b>${the }</b></div>
			<div class="hinh-the"><img src="hinh-the-cccd?id=${cccd.soCC }" width=135 height=175 /></div>
		</div>
		<div class="col-md-6">
			<img alt="" src="resources/image/the-cmnd-mat-sau.png" >
			<div class="dac-diem"><b>${cccd.nhanDang }</b></div>
			<div class="ngay-cap"><b>${ngayCap }</b></div>
			<div class="thang-cap"><b>${thangCap }</b></div>
			<div class="nam-cap"><b>${namCap }</b></div>
			<div class="ky-ten"><b>${nguoiCap }</b></div>
			<div class="bar-code" id="barcode">
			</div>
			<div class="van-tay-trai"><img src="van-tay-tro-trai-cccd?id=${cccd.soCC }" width=105 height=135 /></div>
			<div class="van-tay-phai"><img src="van-tay-tro-phai-cccd?id=${cccd.soCC }" width=105 height=135 /></div>
		</div>
		<div class="col-md-12">
			<label>Mật khẩu: ${matKhau }</label>
		</div>
		<div class="col-md-12 mt20 radius-01 bg-white" id="button">
			<div id="thongBao" class="row display-none">
			<div class="col-md-3"></div>
			<div class="col-md-6">
			<div class="modal-dialog ly-do">	
				<div class="modal-content">
					<div class="modal-header">
					<button type="button" id="close" class="close" data-dismiss="modal" aria-hidden="true" onclick="dongThongBao();">×</button>
					<h3 class="modal-title">Thông Báo <small><font color="red" id="error"></font></small></h3>
					</div>
					<div class="modal-body">
						<div class="container-fluid">
							<div class="row">
								<label>Nhập họ tên, và chữ đệm người nhận</label>
							</div>
							<div class="row">
								<input type=text  id="txtNguoiNhan"  class="form-control"  />
							</div>
							<div class="row mt10">
								<div class="col-md-3 pull-right">
									<input type=button class="btn-warning form-control" onclick="dongThongBao();" value="Hủy thao tác" />
								</div>
								<div class="col-md-3 pull-right">
									<input type=button class="btn-primary form-control" onclick="kiemTraSoCC();" value="Trả" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		</div>
			<div class="title row text-center">thông tin thẻ</div>
			<div class="col-md-4"></div>
			<div class="col-md-5">
				<table border=1>
					<tr>
						<td><label>Tình trạng:</label></td>
						<td style="min-width:150px">${tinhTrangThe }</td>
						<td>${btnTinhTrang }</td>
					</tr>
					<tr>
						<td><label>Người nhận:</label></td>
						<td>${nguoiNhan }</td>
						<td rowspan=3>${btnTraThe }</td>
					</tr>
					<tr>
						<td><label>Người trả:</label></td>
						<td>${nguoiTra }</td>
					</tr>
					<tr>
						<td><label>Ngày trả:</label></td>
						<td>${ngayTra }</td>
					</tr>
				</table>
				<table border=1>
					<tr>
						<td style="width:100px"></td>
						<td><button class="btn btn-primary" onclick="inPage();">In</button></td>
						<td><button class="btn btn-primary" onclick="window.close();">Đóng</button></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</c:if>
</sec:authorize>
<script>
	$("#barcode").barcode($("#soCC").val(),"code39");
	function moThongBao(maSo){
		$("#thongBao").attr("aria-value", maSo);
		$("#thongBao").removeClass("display-none");
		$("#thongBao").addClass("display-block");
		$("#txtNguoiNhan").focus();
	}
	function dongThongBao(){
		$("#thongBao").removeClass("display-block");
		$("#thongBao").addClass("display-none");
	}
	function daLamThe(stt){
		if(!confirm("Bạn có chắc chắn thẻ đã sẵn sàng?")){
			return false;
		}
		var lamThe = "stt="+stt;
		$.ajax({url: 'da-lam-the',
			type: 'GET',
			data: lamThe,
		  	success: function(result){
		  		window.location = "the?soCC="+$("#soCC").val();
	    }});
	}
	function kiemTraSoCC(){
		var soCC = $("#txtNguoiNhan").val();
		$("#error").html("");
		if($("#txtNguoiNhan").val().length < 4){
			$("#error").html("Nhập đầy đủ họ tên và chữ đệm của người nhận");
			return false;
		}
		if(!confirm("Bạn có chắc chắn thực hiện điều này?")){
				return false;
		}
		traThe();
		setTimeout(goThe,2000);
	}
	function traThe(){
		var nguoiNhan = $("#txtNguoiNhan").val();
		var stt = $("#thongBao").attr("aria-value");
		var tra = "stt="+stt+"&nguoiNhan="+nguoiNhan;
		$.ajax({url: 'tra-the-cmt',
			type: 'GET',
			data: tra,
		  	success: function(result){
		  			
	    }});
	}
	function goThe(){
		window.location = "the?soCC="+$("#soCC").val();
	}
</script>
</body>
</html>