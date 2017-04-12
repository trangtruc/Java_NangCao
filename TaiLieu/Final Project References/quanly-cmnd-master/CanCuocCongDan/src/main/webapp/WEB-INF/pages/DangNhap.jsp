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
	<title>THÔNG TIN NGƯỜI DÙNG</title>
	<style>
	td a{
		color:blue;
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
<c:if test="${not empty boSungThongTin}">
	<div id="thongBao" class="row display-block" style="position:fixed;top:0;left:0;bottom:0;right:0;z-index:2010">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<div class="modal-dialog ly-do">	
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" id="close" class="close" onclick="dongThongBao();">×</button>
						<h3 class="modal-title title">BỔ SUNG THÔNG TIN	 <small><font color="red" id="error"></font></small></h3>
					</div>
					<div class="modal-body">
						<div class="container-fluid">
							<div class="row">
								<label>Hãy bổ sung đầy đủ thông tin để sử dụng được tất cả tính năng của hệ thống</label>
							</div>
							<div class="row">
								<img alt="" src="resources/image/logo.jpg" width=200px height=300px>
							</div>
							<div class="row mt10">
								<div class="col-md-3 pull-right">
									<input type=button class="btn btn-primary form-control" onclick="dongThongBao();" value="Bỏ qua" />
								</div>
								<div class="col-md-3 pull-right">
									<input type=button class="btn btn-primary form-control" onclick="chuyen();" value="Tiếp tục" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:if>
<div class="container">
	<div class="row bg-content" style="min-height:280px;">
		<div class="col-md-4">
            <div class="radius-01 div-center" style="background:#fff; min-width:480px;">
                <table width="95%" border="1" cellspacing="0" cellpadding="0">
                     <tbody>
                     	<tr><td colspan=3><span class="title">Thông tin tài khoản</span></td></tr>
                     	<tr>
                        	<td style="width:30%;" class="bordersv">
                          		<div class="bg" align="left">Tài Khoản</div>
                            </td>
                            <td colspan=2><div class="bold" align="left">${ssTaiKhoan }</div>
                            </td>
                       	</tr>
                       	<tr>
                        	<td class="bordersv">
                          		<div class="bg" align="left">Tên người dùng</div>
                            </td>
                            <td colspan=2><div class="bold" align="left">${ssHoTen }</div>
                            </td>
                       	</tr>
                       	<tr>
                        	<td class="bordersv">
                          		<div class="bg" align="left">Giới tính</div>
                            </td>
                            <td colspan=2><div class="bold" align="left">${ssGioiTinh }</div>
                            </td>
                       	</tr>
                       	<tr>
                        	<td class="bordersv">
                          		<div class="bg" align="left">Ngày sinh</div>
                            </td>
                            <td colspan=2><div class="bold" align="left">${ssNgaySinh }</div>
                            </td>
                       	</tr>
                       	<tr>
                        	<td class="bordersv">
                          		<div class="bg" align="left">Email</div>
                            </td>
                            <td colspan=2><div class="bold" align="left">${ssEmail }</div>
                            </td>
                       	</tr>
                       	<tr>
                        	<td class="bordersv">
                          		<div class="bg" align="left">Số điện thoại</div>
                          		<input type="hidden" id="sdt" value="${ssSoDienThoai }" />
                            </td>
                            <td>
                            	<font color=#31708f id="rsUpdateSDT"></font>
                           		<div class="bold" align="left" id="updateSDT">
                           			${ssSoDienThoai }
                           		</div>
                            </td>
                            <td id="btnSDT">
                            	<a onclick="updateSDT();">Chỉnh sửa</a>
                            </td>
                       	</tr>
                       	<tr>
                        	<td class="bordersv">
                          		<div class="bg" align="left">Tình trạng</div>
                            </td>
                            <td colspan=2>
                            	<div class="bold" align="left">
                            		<c:if test="${ssTrangThai == '1'}">
                            			Đang hoạt động
                            		</c:if>
                            		<c:if test="${ssTrangThai == '0'}">
                            			Bị khóa
                            		</c:if>	
                            	</div>
                            </td>
                       	</tr>
               		</tbody>
               	</table>
            </div>
		</div>
		<div class="col-md-2"></div>
		<div class="col-md-6">
			<div class="radius-01 bg-white" style="min-width:480px;">
				<div class="row">
					<c:if test="${ssEmail == null}">
						<label class="col-md-4">
							<a class="btn btn-warning" href="bo-sung-email">
								<span class="fa fa-envelope fa-3x" style="width:120px;font-size: 24px"></span>
								<br>
								Bổ sung Email
							</a>
						</label>
					</c:if>
					<c:if test="${ssEmail != null}">
						<label class="col-md-4">
							<a class="btn btn-warning" href="thay-doi-email">
								<span class="fa fa-envelope fa-3x" style="width:120px;font-size: 24px"></span>
								<br>
								Đổi Email
							</a>
						</label>
					</c:if>
					<label class="col-md-4">
						<a class="btn btn-warning" href="doi-mat-khau">
							<span class="fa fa-lock fa-3x" style="width:120px;font-size: 24px"></span>
							<br>
							Đổi mật khẩu
						</a>
					</label>
					<label class="col-md-4">
						<a class="btn btn-warning" href="chi-tiet-so-ho-khau?soHK=${soHK}">
							<span class="fa fa-address-book fa-3x" style="width:120px;font-size: 24px"></span>
							<br>
							Sổ hộ khẩu
						</a>
					</label>
				</div>
				<hr>
				<div class="row">
					<label class="col-md-12">Lập đơn đăng ký trực tuyến</label>
				</div>
				<div class="row">
					<label class="col-md-4">
						<a class="btn btn-primary" href="dang-ky-ket-hon">
							<span class="fa fa-heart fa-3x" style="width:120px;font-size: 24px"></span>
							<br>
							Kết hôn
						</a>
					</label>
					<label class="col-md-4">
						<a class="btn btn-primary" href="dang-ky-khai-sinh">
							<span class="fa fa-user-plus fa-3x" style="width:120px;font-size: 24px"></span>
							<br>
							Khai sinh
						</a>
					</label>
					<label class="col-md-4">
						<a class="btn btn-primary" href="dang-ky-so-ho-khau">
							<span class="fa fa-address-book fa-3x" style="width:120px;font-size: 24px"></span>
							<br>
							Tách hộ khẩu
						</a>
					</label>
					<label class="col-md-4">
						<a class="btn btn-primary" href="dang-ky-lam-can-cuoc-cong-dan">
							<span class="fa fa-id-card-o fa-3x" style="width:120px;font-size: 24px"></span>
							<br>
							CMND/CCCD
						</a>
					</label>
					<c:if test="${trangThaiDKKH != null }">
						<label class="col-md-4">
							<a class="btn btn-primary" href="${linkThongBao}">
								<span class="fa fa-heart fa-3x" style="width:120px;font-size: 24px"></span>
								<br>
								${trangThaiDKKH}
							</a>
						</label>
					</c:if>
				</div>
				<c:if test="${ssSoQuyen > 1 }">
					<hr>
					<div class="row">
						<label class="col-md-4">
							<a class="btn btn-danger" href="${lienKet}">
								<span class="fa fa-user-md fa-3x" style="width:120px;font-size: 24px"></span>
								<br>
								Quản Lý
								<c:if test="${(ssSoDonTTDKCCCDChoXacNhan  != null && ssSoDonTTDKCCCDChoXacNhan != '0') || 
								(ssSoDonTTDKCCCDChoDuyet != null && ssSoDonTTDKCCCDChoDuyet != '0') || 
								(soDonDKKSChoXacNhan  != null && soDonDKKSChoXacNhan != '0') || 
								(soDonDKKSChoDuyet  != null && soDonDKKSChoDuyet != '0') || 
								(soDonThemNhanKhau  != null && soDonThemNhanKhau != '0') || 
								(soDonLamHoKhau  != null && soDonLamHoKhau != '0')}">	
									<sup><span class='message-menu fa fa-exclamation'></span></sup>
								</c:if>
							</a>
						</label>
					</div>
				</c:if>
			</div>
		</div>
		
	</div>
	<div class="row">
			<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>
<script>
	function dongThongBao(){
		$("#thongBao").removeClass("display-block");
		$("#thongBao").addClass("display-none");
	}
	function chuyen(){
		window.location = "bo-sung-email";
	}
	function send(){
        var str = "soCC=092078000005";
       $.ajax({url: 'webservice',type: 'GET',data:str,
               success: function(result){
                  json = JSON.parse(result);
                   alert(json.ho_ten);
       }});
   }
	function updateSDT(){
		$("#updateSDT").html("<input type='number' id='newSDT' value='"+$("#sdt").val()+"' class='form-control' />");
		$("#btnSDT").html("<a onclick='sendUpdateSDT();'>Lưu</a> | <a onclick='huyUpdateSDT();'>Hủy</a>");
	}
	function huyUpdateSDT(){
		$("#btnSDT").html("<a onclick='updateSDT();'>Chỉnh sửa</a>");
		$("#updateSDT").html($("#sdt").val());
	}
	function sendUpdateSDT(){
		var newSDT = $("#newSDT").val();
		var fisrtChar = newSDT.substring(0,1);
		if((newSDT.length < 10 || newSDT.length > 11)){
			alert("Số điện thoại không hợp lệ 1");
			return false;
		}
		if(fisrtChar != '0'){
			alert("Số điện thoại không hợp lệ 12");
			return false;
		}
        var str = "sdt="+newSDT;
       $.ajax({url: 'change-sdt',type: 'GET',data:str,
               success: function(result){
                 $("#rsUpdateSDT").html("Số điện thoại đã được cập nhật");
                 
       }});
       $("#sdt").val(newSDT);
       huyUpdateSDT();
   }
</script>
</body>
</html>
