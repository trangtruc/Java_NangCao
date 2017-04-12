<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="resources/js/dangky-cccd.js"></script>
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Nhập sổ hộ khẩu</title>
</head>
<body>
<div >
		<!-- 			Page header -->
			<div class="container">
				<jsp:include page="header.jsp"></jsp:include>
			</div>
			<!-- 			End page header -->
</div>
<div class="container " style="min-height:364px">
	<div class="row bg-content">
		<div class="col-md-3">
			<jsp:include page="menu-trai.jsp"></jsp:include>
		</div>
		<div class="form-border row mt5 bg-white col-md-9 " style="border: so_lid black 1px; ">	
			<div class="row  text-center title col-md-offset-3 ">
				Nhập sổ hộ khẩu
			</div>
			<div class="row">
				<div class="col-md-12 text-center" id="ket-qua"></div>
			</div>
			<div class="row">

				<form class="bg-white radius-01" name="nhapHoKhau" action="ket-qua-nhap-ho-khau" onsubmit="return ktSubmit();" >
					<div class="row">
						<div class="col-md-4">
							<label style="font-size: 13pt">Nhập số lượng nhân khẩu</label>
						</div>
						<div class="col-md-2">
							<input class="form-control" type="text" maxlength="2" oninput="kiemtra_nhapso(this.id, this.value);" onkeyup="kiemTraSo(this.id, this.value);" name="soNhanKhau" id="soNhanKhau" placeholder="Số Lượng">
						</div>
						<div>
							<input class="btn btn-primary" type="button" name="button" value="Đồng Ý " onclick="createForm();">
						</div>
					</div>
					<div class="row mt20" id="form-input"></div>
					<div class="row pull-right mt20" style="margin-right: 35px; margin-bottom: 10px"  id="btn-submit">
						
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
			<input type="hidden" name="dsQuanHe" id = "dsQuanHe" value='${DSQuanHe }'>
			<script>
				function createForm(){
					var dsQuanHe= $("#dsQuanHe").val();
					var soluong = $("#soNhanKhau").val();
					var member = "Chủ hộ";
					var html ="<div class='col-md-12 mt20' >"
								+"	<div class='col-md-4 text-right mt5' ><label>Nhập địa chỉ: </label></div>"
								+"	<div class='col-md-4'><input type=text id='diaChi' name='diaChi' class='form-control' /></div>"
								+"</div>"
							 + "<table border=1 class='mt20 col-md-6'style='width:45%'>"
							 + "<tr><th colspan='2'>"+member+"</th></tr>"
							 + "<tr><td><label>Nhập số khai sinh</label><span style ='color: red;'>(*)</span></td>"
							 + "<td><label>Nhập số căn cước</label><span style ='color: red;'>(*)</span></td></tr>"
							 + "<tr><td><input type=text class='form-control' name='soKSChuHo' id='soKSChuHo' oninput='kiemTraDoDai(this.id, this.value);' onkeypress='kiemTra(this.id, this.value);' onblur='layThongTinKS(this.id, this.value);'onkeyup='kiemTraSo(this.id, this.value);'/></td>"
							 + "<td><input type=text class='form-control' name='soCCDisChuHo' id='soCCDisChuHo' disabled/></td></tr>"
							 + "<tr><td><label>Họ và Tên</label></td><td><input type=text style='text-align: center;' class='form-control' name=hoTenDisChuHo id=hoTenDisChuHo disabled/></td></tr>"
							 + "<input type=hidden style='text-align: center;' class='form-control' name=hoTenChuHo id=hoTenChuHo/>"
							 + "</table>"
							 + "<input type=hidden class='form-control' name='soCCChuHo' id='soCCChuHo' />";
					for(var i = 2; i <= soluong; i++){
						html += "<table border=1 class='col-md-6 mt20' style='width:45%'>";
						member = "Thành viên thứ "+i;
						html += "<tr><th colspan='4'>"+member+"</th></tr>"
								+ "<tr style='width: 38%;'><td><label class='mb5'>Số khai sinh</label><span style ='color: red;'>(*)</span></td>"
								+ "<td style='width: 38%;'><label class='mb5'>Số căn cước công dân</label></td>"
								+ "<td style='width: 24%;'><label class='mb5'>QH chủ hộ</label></td></tr>"
								+ "<tr></tr>"
						   		+ "<tr><td ><input type=text class='form-control' name ='soKSThanhVien"+(i-1)+"' id='soKSThanhVien"+(i-1)+"' oninput='kiemTraDoDai(this.id, this.value);' onkeypress='kiemTra(this.id, this.value);' onblur='layThongTinKS(this.id, this.value);'onkeyup='kiemTraSo(this.id, this.value);' /></td>"
							   	+ "<td ><input type=text class='form-control' name='soCCDisThanhVien"+(i-1)+"' id='soCCDisThanhVien"+(i-1)+"' disabled/></td>"
							   	+ "<td ><select name = 'quanHe"+(i-1)+"' id = 'quanHe"+(i-1)+"' onchange='ktQuanHe(this.id, this.value)' class='form-control'>"
								+ dsQuanHe;
							  html += "</select></td>"
							   + "</tr>"
							   + "<tr ><td><label>Họ và Tên</label></td><td colspan='2'><input type=text style='text-align: center;' class='form-control' name=hoTenDisThanhVien"+(i-1)+" id=hoTenDisThanhVien"+(i-1)+" disabled/></td></tr>"
							   + "</table>"
							   + "<input type=hidden name=hoTenThanhVien"+(i-1)+" id=hoTenThanhVien"+(i-1)+" />"
							   + "<input type=hidden name='soCCThanhVien"+(i-1)+"' id='soCCThanhVien"+(i-1)+"'/>";
					}
					$("#form-input").html(html);
					$("#btn-submit").html("<button type=submit  class='btn btn-primary'  >Nhập</button>");
				}			
			function kiemTraDoDai(id, vlue){
				if(vlue.length > 15) {
					var new_vlue = vlue.slice(0,15);
					$("#"+id).val(new_vlue);
				}
			}
			function kiemTra(id, vlue){
				var new_value = vlue;
				if(vlue.length == 15) return false;
				if(vlue.length == 3){
					new_value += "-";
				}
				if(vlue.length == 7){
					new_value += "-";
				}
				if(vlue.length == 11){
					new_value += "-";
				}
				$("#"+id).val(new_value);
			}

			
			function checkNull(val) {
				return (val == null || val.length < 1 || val == 0 || "Không tìm thấy" == val);
			}
			
			
			function layThongTinKS(id, giatri) {
				var so = giatri.split("-");
				var soKS=so[0]+so[1]+so[2]+so[3];				
				var hoTen = id.replace("soKS","hoTen");
				var hoTenDis = id.replace("soKS","hoTenDis");
				var soCC = id.replace("soKS","soCC");
				var soCCDis = id.replace("soKS","soCCDis");
				var kq;
				var kqMacDinh = "Không tìm thấy";
				soKS = "soKS=" + soKS;
				$.ajax({url: 'lay-thong-tin-ks-nhap-ho-khau',
					type: 'GET',
					data: soKS ,
				  	success: function(result){
				  		if(checkNull(result)) {
				  			$("#"+id).attr("");
				  			$("#"+hoTenDis).val(kqMacDinh);
				  			$("#"+hoTen).val(kqMacDinh);
				  			alert("Số khai sinh không tồn tại");

				  		} else if(result == "datontai"){
				  			alert("Người này đã có trong sổ hộ khẩu");
				  			$("#"+hoTen).val("");
				  			$("#"+hoTenDis).val("");
				  		}else{
				  			kq = result.split("@@");
				  			var giaTriTen = kq[0];
				  			var soCCCD = kq[1];
				  			$("#"+hoTen).attr("aria-value","1");
				  			$("#"+hoTen).val(giaTriTen);
				  			$("#"+hoTenDis).val(giaTriTen);
				  			$("#"+soCC).val(soCCCD);
				  			$("#"+soCCDis).val(soCCCD);
				  			ktHonNhan(soCC, soCCCD);
				  		}
				  	}
				});
				
			
			
			
			}
			
			function ktHonNhan(id, val) {
				var soluong = $("#soNhanKhau").val();
				var stt = id.replace("soCCThanhVien","");
				var soCCChuHo="soCCChuHo="+$("#soCCChuHo").val();
				$.ajax({url: "lay-thong-tin-hon-nhan",
					type: 'GET',
					data: soCCChuHo ,
				  	success: function(result){
				  		var thisCC = val;
				  		var QH ="";
				  		if(!checkNull(result)) {
				  			if(result.search("soCCChong")>=0){
				  				QH = "chong";
				  				result = result.replace("soCCChong","");
				  			}else{
				  				QH = "vo";
				  				result = result.replace("soCCVo","");
				  			}
							if(result == thisCC){
								$("#quanHe"+stt).val(QH);
							}
				  		} 
				  	}
				});
			}
			
			function ktQuanHe(id, val) {
				var stt = id.replace("quanHe","");				
				var soCCChuHo="soCCChuHo="+$("#soCCChuHo").val();
				var soLuongVoChong = 0;
				$.ajax({url: "lay-thong-tin-hon-nhan",
					type: 'GET',
					data: soCCChuHo ,
				  	success: function(result){
				  		var thisCC = $("#soCCThanhVien"+stt).val();
				  		var QH ="";
				  		if(!checkNull(result)) {
				  			if(result.search("soCCChong")>=0){
				  				QH = "chong";
				  				result = result.replace("soCCChong","");
				  			}else{
				  				QH = "vo";
				  				result = result.replace("soCCVo","");
				  			}
							if(result == thisCC){
								alert("Thành viên này với Chủ hộ là vợ chồng");
								$("#"+id).val(QH);
							}else if(val == "vo" || val == "chong"){
								alert("Thành viên này với chủ hộ không phải vợ chồng");
								$("#"+id).val("em");
							}
				  		} 
				  	}
				});
				
			}
			
			function ktSubmit(){
				if($("#soCCChuHo").val().length < 11){
					$("#soCCChuHo").focus();
					alert("Chưa nhập đủ số CCCD chủ hộ");
					return false;
				} 
				if($("#soKSChuHo").val().length < 11){
					$("#soKSChuHo").focus();
					alert("chưa nhập đủ số Khai sinh chủ hộ");
					return false;
				}
				if($("#hoTenChuHo").val() == ("Không tìm thấy") || $("#hoTenChuHo").val()== ""){
					alert("Số Khai Sinh chủ hộ không tồn tại");
					return false;
				}
					
				var sl = $("#soNhanKhau").val();
				for(var i = 0; i < (sl-1) ; i++ ){
					if($("#soKSThanhVien"+(i+1)).val().length < 11){
						$("#soKSThanhVien"+(i+1)).focus();
						alert("chưa nhập đủ số Khai sinh của thành viên thứ "+(i+1));
						return false;
					}
					if($("#hoTenThanhVien"+(i+1)).val() == "Không tìm thấy" || $("#hoTenThanhVien"+(i+1)).val() == ""){
						alert("Số Khai Sinh thành viên thứ "+(i+1)+" không tồn tại");
						return false;
					}
				}
			}
			
			</script>	
<div class="container">
	<div class="row">
		<div class="container">
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
</div>
</body>
</html>