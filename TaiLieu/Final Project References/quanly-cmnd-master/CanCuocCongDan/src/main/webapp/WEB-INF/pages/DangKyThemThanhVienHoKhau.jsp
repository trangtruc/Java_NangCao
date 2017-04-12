<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="resources/js/dangky-cccd.js"></script>
<script src="resources/js/NhapKhaiSinh.js"></script>
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Thêm Nhân khẩu</title>
</head>
<body>
<div class="container">
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
</div>
<div class="container" style="min-height:364px">
	<div class="row bg-content ">
	<div class="bg-white ml5 mr5 mt5 mb5">
	<div class="row text-center  title mt5">
			Đăng Ký Thêm Nhân Khẩu
	</div>
	
		<div class="form-border row" style="border: so_lid black 1px; ">	
				<form name="dienthongtin" action="ket-qua-dang-ky-nhan-khau?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" method="POST"  >
					<div class="row mt20">
						<div class="col-md-1"></div>
						<div class="col-md-2 mt5">
							<label>Nhập số sổ hộ khẩu</label>
						</div>
						<div class="col-md-2">
							<input class="form-control" type="text" name="soSoHoKhau" id="soSoHoKhau"  oninput='kiemTraDoDai(this.id, this.value);' onkeypress='chenGachNgang(this.id, this.value);' onkeyup='kiemTraSo(this.id, this.value);' placeholder="Số sổ hộ khẩu">
						</div >
						<div class="col-md-1">
							<input class="form-control btn-primary" type="button" name="button" value="Đồng Ý " onclick="thongTinSHK();">
						</div>
					</div>
					<div class="row">
						<div class='col-md-12 ' id="thongTinSHK"></div>
					</div>
					<div class="row">
						<div id="createForm" style="margin-left: 100px;"></div>
					</div>
					<div class="row pull-right " id="btn-submit">
						
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
			<input type="hidden" id="dsQuanHe" value="${DSQuanHe }">
			<script>
			function kiemTraDoDai(id, vlue){
				if(vlue.length > 15) {
					var new_vlue = vlue.slice(0,15);
					$("#"+id).val(new_vlue);
				}
			}
			function checkNull(val) {
				return (val == null || val.length < 1 || val == 0 || "Không tìm thấy" == val);
			}
			
			
			function thongTinSHK() {
				if($("#soSoHoKhau").val().length <15){
					alert("Nhập sai số hộ khẩu");
				}
				var soHK =  replaceAll($("#soSoHoKhau").val(),"-","");
				soHK = "soHK=" + soHK;
				$.ajax({url: 'string-chi-tiet-so-ho-khau',
					type: 'GET',
					data: soHK ,
				  	success: function(result){
				  		if(checkNull(result)){
				  			alert("Số Hộ Khẩu không tồn tại");
				  		}else if(result =="Không thuộc hộ khẩu"){
				  			alert("Bạn không thuộc sổ hộ khẩu này!!! Hãy nhập sổ hộ khẩu hiện tại của bạn!!!");
				  		}else{
					  		var html ="<table border='1' class='thongtin mt20' >"
					           +" <tbody>"
						       +"     <tr>"
						       +"         <th colspan='6' style='width:84%'>Thông tin các thành viên trong gia đình</th>"
						       +"    </tr>"
						       +"    <tr>"
						       +"    	<th style='width:15%'>Số Hộ Khẩu</th>"
						       +"    	<th style='width:15%'>Số Khai Sinh</th>"
						       +"		<th style='width:15%'>Số Căn Cước</th>"
						       +"		<th style='width:15%'>Họ Tên</th>"
						       +"		<th style='width:20%'>Tình trạng nhân khẩu</th>"
						       +"		<th style='width: 12%'>Quan Hệ Với Chủ Hộ</th>"
						       +"	</tr>"
						       +	 result
						       +" </tbody>"			               
				               +" </table>"
				               +"<div class='row col-md-8 col-md-offset-2'>"
				               +"<table border='1' class='thongtin mt20' id='noiLamViec' >"
				               +"	<tr>"
				               +"		<th colspan='4'>Làm việc tại Ủy Ban:" 
				               +"		<span style ='color: #fbf300;'>(Nơi sẽ được hẹn làm việc để hoàn tất thủ tục)</span>"
				               +"		</th>"
				               +"	</tr>"
				               +"	<tr>"
				               +"		<th>Tỉnh</th>"
				               +"		<td>"
				               +"			<select name='noiDKLVTinh' id='noiDKLVTinh' class='form-control' onclick='getHuyen(this.id, this.value);' onchange='getHuyen(this.id, this.value);'>"
				               +"				<c:forEach items='${dsTinh}' var='tinh'>"
				               +"					<option id='option' value='${tinh.maTinh}'>${tinh.tenTinh}</option>"
				               +"				</c:forEach>"
				               +"			</select>"
				               +"		</td>"
				               +"		<th>Huyện</th>"
				               +"		<td colspan='1'>"
				               +"			<select name='noiDKLVHuyen' id='noiDKLVHuyen' onfocus='getHuyenFocus(this.id);' class='form-control'>"
				               +"				<option value='0'>Chọn Huyện</option>"
				               +"			</select>"
				               +"		</td>"
				               +"	</tr>"
				               +"</table>"
				               +"</div>"
				               +" <div class='mt20 col-md-12'>"
				               +" 	<div class='row col-md-4 col-md-offset-1' style='margin-top:4px;'><label>Nhập số lượng nhân khẩu muốn đăng ký: </label></div>"
				               +" 	<div class='col-md-2'><input class='form-control ' maxlength='2' type='text' id='soLuong' name='soLuong' onkeyup='kiemTraSo(this.id, this.value)'></div>"
				               +" 	<div class='col-md-1'><input class='form-control btn-primary' type='button' name='button1' value='Đồng Ý' onclick='createForm(); '></div>"
				               +" </div>";
					  		$("#thongTinSHK").html(html);
					  		//$("#btn-submit").html("<button type=submit  class='btn btn-primary' >Nhập</button>");
				  		}
				  	}
				});
			}
			
			function createForm(){
				var i = 0 ;
				var html = "<div class='row'>";
				var sl = $("#soLuong").val();
				var dsQuanHe = $("#dsQuanHe").val();
				if(sl == null){
					alert("Hãy nhập số lượng nhân khẩu muốn thêm vào hộ khẩu")
				}else{
					for(i ; i < sl ; i++ ){
						html +="<table border='1' class='thongtin mt20 col-md-5'>"
					         +"	<tbody>"
						     +"		<tr>"
						     +"			<th colspan='3' style='width:84%;'>Thành viên thứ "+(i+1)+"</th>"
						     +"		</tr>"
						     +"		<tr>"	
						     +"			<td><label class='mb5'>Số khai sinh</label><span style ='color: red;'>(*)</span>"
							 +"			<td><label class='mb5'>Số căn cước công dân</label></td>"
							 +"			<td></td>"	
						     +"		</tr>"
						     +"		<tr>"	
						     +"			<td><input type=text class='form-control' name ='soKSThanhVien"+i+"' id ='soKSThanhVien"+i+"' oninput='kiemTraDoDai(this.id, this.value);' onkeypress='chenGachNgang(this.id, this.value);' onblur='kiemTraDK(this.id, this.value)' /></td>"
							 +"			<input type=hidden name ='soCCTV"+i+"' id ='soCCTV"+i+"' />"
							 +"			<td><input type=text class='form-control' name ='soCCThanhVienDis"+i+"' id ='soCCThanhVienDis"+i+"' value='' disabled/></td>"
							 +"			<td style='width:20%'><select class='form-control' name = quanHeDK"+(i)+" id = quanHeDK"+(i)+" onchange='ktHonNhan(this.id, this.value)'>"
						   	 +				dsQuanHe
						     +"				</select>"
						     +"			</td>"
							 +"		</tr>"
						     +"		<tr>"
						     +"		<td colspan='4'><input type=text style='text-align: center;' class='form-control' name='hoTen"+i+"' id='hoTen"+i+"' disabled/></td>"
						     +"		</tr>"
						     +"		<tr>"
						     +"		<td><label class='mb5'>Tình trạng nhân khẩu</label></td>"
						     +"		<td colspan='3'><input type=text style='text-align: center;' class='form-control' name='distinhTrangThanhVien"+i+"' id='distinhTrangThanhVien"+i+"' disabled/></td>"
						     +"		</tr>"
						     +" </tbody>"
						     +" </table>"
						     +"<input type=hidden class='form-control' name ='soCCThanhVien"+i+"' id ='soCCThanhVien"+i+"' value=''/>"
						     +"<input type=hidden class='form-control' name ='tinhTrangThanhVien"+i+"' id ='tinhTrangThanhVien"+i+"' value=''/>";
						
					}
					html +="</div><div class='row col-md-offset-5 col-md-1 mt20'><button type=submit onclick='DKSubmit();'  class='btn btn-primary' >Hoàn Tất</button></div>"
						 +"<input type=hidden name='soLuongDK' id='soLuongDK' value='"+sl+"'>";
					$("#createForm").html(html);
				}
			}
			
			function kiemTraDK(id,vailo){
				var soLuongTV = $("#soLuongTV").val();
				var a= 0;
				for(var i=0 ; i < soLuongTV; i++){
					var soKSDK = replaceAll(vailo,"-","");
					var soKSTrongHK = $("#soKSTrongHK"+i).val();
					if( soKSDK == soKSTrongHK){
						alert("Người này đã có trong sổ hộ khẩu hiện tại");
						$("#"+id).val("");
						var hoTen = id.replace("soKSThanhVien","hoTen") ;
						$("#"+hoTen).val("");
						a = 1;
					}
				}
				
				var newVal = replaceAll(vailo, "-", "");
				//Lay ky tu cuoi cung vua nhap vao
				var lastChar = vailo.substr(vailo.length - 1);
				//Neu khong phai la so
				if(isNaN(newVal) || (lastChar == " ")) {
					alert("Không được nhập chữ hoặc khoảng cách vào.");
					if (isNaN(lastChar)) {
						a=1;
						$("#"+id).val("");
					} else {
						a=1;
						$("#"+id).val("");
					}
				}
				
				
				if(a==0){
					layThongTinKS(id, vailo);	
				}
			}
			
			function layThongTinKS(id, val) {
				var soKS = replaceAll(val, "-", "");
				var hoTen = id.replace("soKSThanhVien","hoTen");
				var soCCThanhVien = id.replace("KS","CC");
				var soCCThanhVienDis = id.replace("KSThanhVien","CCThanhVienDis");
				var tinhTrangThanhVien = id.replace("soKS","tinhTrang");
				var ten;
				var soCC;
				var kq;
				var tinhTrang;
				var kqMacDinh = "Không tìm thấy";
				soKS = "soKS=" + soKS;
				$.ajax({url: 'lay-thong-tin-ks',
					type: 'GET',
					data: soKS ,
				  	success: function(result){
				  		if(checkNull(result)) {
				  			$("#"+id).val("");
				  			$("#"+hoTen).val(kqMacDinh);
				  			alert("Số khai sinh không tồn tại");
				  		} else {
				  			kq = result.split("soCC");
				  			ten = kq[0];
				  			soCC = kq[1].split("tinhTrang");
				  			tinhTrang = soCC[1];
				  			$("#"+hoTen).attr("aria-value","1");
				  			$("#"+hoTen).val(kq[0]);
				  			$("#"+soCCThanhVien).val(soCC[0]);
				  			$("#"+soCCThanhVienDis).val(soCC[0]);
				  			$("#"+tinhTrangThanhVien).val(tinhTrang);
				  			$("#dis"+tinhTrangThanhVien).val(tinhTrang);
				  			var quanheDK = id.replace("soKSThanhVien","quanHeDK");
				  			ktHonNhan(quanheDK, "em");
				  		}
				  	}
				});
			}
			
			function DKSubmit(){
				//dua du lieu tu SoCCThanhVien bi disabled vao khung hidden 
				var sl = $("#soLuong").val();
				for(var i=0; i<sl;i++){
					var soCCThanhVien = "soCCThanhVien"+i;
					var soCCTV = "soCCTV"+i;
					document.getElementById(soCCTV).value=$("#"+soCCThanhVien).val();
				}
			}
			
			function ktHonNhan(id, val) {
				var slTV = $("#soLuongTV").val();
				var quanHeTV = "";
				var stt = id.replace("quanHeDK","");
				
				if(val =="chong" || val == "vo"){
					var soCC1 = $("#soCCThanhVien"+stt).val();
					var soCCChuHo;
					for(var i = 0 ; i < slTV ; i++){
						if($("#quanHeTV"+i).val() == "chuho"){
							soCCChuHo ="soCCChuHo="+$("#soCCTrongHK"+i).val();
						}
					}
					$.ajax({url: "lay-thong-tin-hon-nhan",
						type: 'GET',
						data: soCCChuHo ,
					  	success: function(result){
					  		var quanHe;
					  		if(!checkNull(result)) {
					  			if(result.search("soCCChong")>=0){
					  				quanHe = "chong";
					  				result = result.replace("soCCChong","");
					  			}else{
					  				quanHe = "vo";
					  				result = result.replace("soCCVo","");
					  			}
					  			if(soCC1 == result){
								  	$("#"+id).val(quanHe);
					  			}else{
					  				alert("Thành viên này với chủ hộ chưa kết hôn");
									$("#"+id).val("em");
					  			}
					  		}else{
				  				alert("Thành viên này với chủ hộ chưa kết hôn");
								$("#"+id).val("em");
				  			}
					}
					});
				}
				
				if(val !="chong" || val != "vo"){
					var soCC1 = $("#soCCThanhVien"+stt).val();
					var soCCChuHo;
					for(var i = 0 ; i < slTV ; i++){
						if($("#quanHeTV"+i).val() == "chuho"){
							soCCChuHo ="soCCChuHo="+$("#soCCTrongHK"+i).val();
						}
					}
					$.ajax({url: "lay-thong-tin-hon-nhan",
						type: 'GET',
						data: soCCChuHo ,
					  	success: function(result){
					  		var quanHe;
					  		if(!checkNull(result)) {
					  			if(result.search("soCCChong")>=0){
					  				quanHe = "chong";
					  				result = result.replace("soCCChong","");
					  			}else{
					  				quanHe = "vo";
					  				result = result.replace("soCCVo","");
					  			}
					  			if(soCC1 == result){
					  				alert("Thành viên này với chủ hộ đã kết hôn");
								  	$("#"+id).val(quanHe);
					  			}
							}
					  	}
					});
			}
			
			}
			
			</script>	
<div class="container">
	<div class="row">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>

</body>
</html>