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
	<title>CHI TIẾT TÁCH SỔ HỘ KHÂU</title>
</head>
<body>
	<div >
		<jsp:include page="header.jsp"></jsp:include>
	</div>

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
								<label>Hãy cho biết lý do hồ sơ này không được duyệt</label>
							</div>
							<div class="row">
								<input type=text placeholder="Nhập vào lý do không duyệt" id="txtLyDo"  class="form-control"/>
							</div>
							<div class="row mt10">
								<div class="col-md-3 pull-right">
									<input type=button class="btn-warning form-control" onclick="dongThongBao();" value="Hủy thao tác" />
								</div>
								<div class="col-md-3 pull-right">
									<input type=button class="btn-danger form-control" onclick="khongDuyet();" value="Không duyệt" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
	</div>

<div class="container mt20" style="min-height:364px">
			<div class="row bg-content">
			<div class="col-md-3">
				<jsp:include page="menu-trai.jsp"></jsp:include>
			</div>
			 <div class="col-md-9 bg-white mt5 mb5 ">
			<div class=" row mt20  text-center">
				<div class=" col-md-offset-6 title">
					CHI TIẾT ĐĂNG KÝ TÁCH LÀM SỔ HỘ KHẨU
				</div>
			</div>
		
			
			<form action="duyet-lam-so-ho-khau?${_csrf.parameterName}=${_csrf.token}"
		enctype="multipart/form-data"
		method="POST">
			
		    	<div class="row">
				    <table border="1" class="thongtin mt20">
			            <tbody>
				            <tr>
				                <th colspan="5" style="width:84%;">Thông Tin Người Đăng Ký</th>
				           </tr>
				           <tr>
		            			<th style="width:15%;">Số căn cước</th>
		            			<th style="width:18%;">Họ tên</th>
		            			<th style="width:10%;">Giới Tính</th>
		            			<th style="width:10%;">Dân Tộc</th>
		            			<th style="width:1000px;">Địa Chỉ</th>
		            		</tr>
		            		${NguoiDK}
		                </tbody>
		            </table>
	            </div>
		
		 
		
		    	<div class="row">
				    <table border="1" class="thongtin mt20">
			            <tbody>
				            <tr>
				                <th colspan="7" style="width:84%;">Thông tin người cùng chuyển hộ khẩu</th>
				           </tr>
				           <tr>
				           		<th style="width:18%;">Số Hộ Khẩu Cũ</th>
				           		<th style="width:18%;">Số Khai Sinh</th>
		            			<th style="width:18%;">Số Căn Cước</th>
		            			<th style="width:18%;">Họ Tên</th>
		            			<th style="width:20%;">Tình trạng nhân khẩu </th>
		            			<th>Quan Hệ Với Chủ Hộ</th>
		            		</tr>
		            		 ${chiTietLamHoKhau}
		                </tbody>
		               
		            </table>
	            </div>
	            <div class="mt10 ">
	            	<div class="col-md-3 mt5" style="font-size: 18px">Lý do không duyệt: </div>
	            	<div class="row col-md-5"><input class = "form-control " type="text" value="${lyDo }" disabled="disabled"> </div>
	            	
	            </div>
	            <div class="row col-md-12 mt20 mb5">
					<span class='pull-left'><button type=button onclick="goBack()" class='btn btn-primary'>Quay lại</button></span>
					<span class='pull-right'><button type=button onclick='moThongBao();' class='btn btn-primary ml20'>Từ chối</button></span>
					<span class='pull-right'><button type="submit" onclick='duyet();' class='btn btn-primary'>Duyệt</button></span>
				</div>
		 </div>
		 </form>
		</div>
	</div>
<div class="container">
	<div class="row">
		<div class="container">
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
</div>
</div>
<script>
	function duyet(){
		if(!confirm("Bạn có chắc chắn thực hiện điều này?")){
			return false;
		}
		else return true;
	}
	
	function khongDuyet(){
		$("#error").html("");
		if($("#txtLyDo").val() < 1){
			$("#error").html("Chưa nhập lý do");
			return false;
		}
		if(!confirm("Bạn có chắc chắn thực hiện điều này?")){
			return false;
		}
		var lyDo = $("#txtLyDo").val();
		var soCCNguoiDK = $("#thongBao").attr("aria-value");
		
		// Chỗ cần tham khảo
		var duyet = "soCCNguoiDK="+soCCNguoiDK+"&lyDo="+lyDo;
		$.ajax({url: 'khong-duyet-ho-khau',
			type: 'GET',
			data: duyet,
		  	success: function(result){
		  		window.location = "danh-sach-lam-so-ho-khau";
	        }});
	}
	
	function moThongBao(){
		$("#thongBao").attr("aria-value", $("#soCCNguoiDK").val());
		$("#thongBao").removeClass("display-none");
		$("#thongBao").addClass("display-block");
		$("#txtLyDo").focus();
	}
	
	function dongThongBao(){
		$("#thongBao").removeClass("display-block");
		$("#thongBao").addClass("display-none");
	}
</script>
</body>
</html>
<style>
	td a{
		color:blue;
	}
</style>