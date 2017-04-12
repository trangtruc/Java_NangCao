<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DANH SÁCH LÀM CĂN CƯỚC CÔNG DÂN</title>
</head>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div >
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
</div>
<div class="container" style="min-height:400px">
		<div class="mt80">
			<div class="text-center col-md-12">
				<div class="text-orange title">
					HỆ THỐNG QUẢN LÝ CHỨNG MINH THƯ ĐIỆN TỬ
				</div>
			</div>
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
								<label>Hãy cho biết lý do đơn này không được duyệt</label>
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
		
		
		<div class="row">
		    <div class="col-md-12">
			    <table border="1" class="thongtin">
		            <tbody>
			            <tr>
			                <th style="width:10%;">Họ tên</th>
			                <th style="width:4%;">Giới tính</th>
			                <th>Ngày sinh</th>
			                <th>CMND/CCCD</th>
			                <th style="width:15%;">Quê quán</th>
			                <th>Dân Tộc</th>
			                <th>Quốc tịch</th>
			                <th style="width:6%;">Yêu cầu</th>
			                <th style="width:5%;">Chi tiết</th>
			                <th>Duyệt</th>
			                <th style="width:6%;" id='duyetTatCa'><button onclick="chonTatCa(1);" style="color:blue">Chọn tất cả</button></th>
			            </tr>
	            
	                        ${dsLamCCCD}
	                    
	                </tbody>
	            </table>
	            <div class="row">
	            	<div class="pull-right mr80 mt20">
	            		<button type=button onclick="duyetDS();" class="btn btn-success">Duyệt đơn đã chọn</button>
	            	</div>
	            </div>
		 </div>
		</div>
	</div>
<div class="container">
	<div class="row">
		<div class="container">
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
</div>
</div>


</body>
</html>
<script>
	function chonTatCa(duyet){
		var row = document.getElementsByName("dsDuyet[]");
		if(duyet == 1){
			for(var i= 0; i < row.length; i++){
				document.getElementById("duyet"+i).checked = true;
			}
			$("#duyetTatCa").html("<button onclick='chonTatCa(0);' style='color:blue;'>Bỏ<br> tất cả</button>");
		} else {
			for(var i= 0; i < row.length; i++){
				document.getElementById("duyet"+i).checked = false;
			}
			$("#duyetTatCa").html("<button onclick='chonTatCa(1);' style='color:blue;'>Chọn tất cả</button>");
		}
		
	}
	function duyetDS(){
		var n = $("#soDong").val();
		if(n == null){
			alert("Không có đơn nào được chọn");
			return false;
		} else {
			if(!confirm("Bạn có chắc chắn thực hiện điều này?")){
				return false;
			}
			
			// Chỗ cần tham khảo
			var danhSach = "danhSach=";
			for(var i = 0; i < n; i++){
				var ck = document.getElementById("duyet"+i);
				if(ck.checked){
					danhSach += $("#duyet"+i).val()+"-";
				}
			}
			$.ajax({url: 'duyet-danh-sach',
				type: 'GET',
				data:danhSach,
			  	success: function(result){
			  		window.location = "duyet-danh-sach-lam-can-cuoc";
		        }});
		}
	}
	function duyet(maSo){
		if(!confirm("Bạn có chắc chắn thực hiện điều này?")){
			return false;
		}
		var duyet = "maSo="+maSo;
		$.ajax({url: 'duyet-don',
			type: 'GET',
			data: duyet,
		  	success: function(result){
		  		window.location = "duyet-danh-sach-lam-can-cuoc";
	        }});
	}
	
	function khongDuyet(){
		if($("#txtLyDo").val() < 1){
			$("#error").html("Chưa nhập lý do");
			return false;
		}
		if(!confirm("Bạn có chắc chắn thực hiện điều này?")){
			return false;
		}
		var lyDo = $("#txtLyDo").val();
		var maSo = $("#thongBao").attr("aria-value");
		
		// Chỗ cần tham khảo
		var duyet = "maSo="+maSo+"&lyDo="+lyDo;
		$.ajax({url: 'khong-duyet-don',
			type: 'GET',
			data: duyet,
		  	success: function(result){
		  		window.location = "duyet-danh-sach-lam-can-cuoc";
	        }});
	}
	function moThongBao(maSo){
		$("#thongBao").attr("aria-value", maSo);
		$("#thongBao").removeClass("display-none");
		$("#thongBao").addClass("display-block");
	}
	function dongThongBao(){
		$("#thongBao").removeClass("display-block");
		$("#thongBao").addClass("display-none");
	}
</script>
<style>
	td a{
		color:blue;
	}
</style>