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
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
</div>
<div class="container mt20" style="min-height:400px">
	<div class="mt80">
				<div class="text-center col-md-12">
					<div class="text-orange title">
						HỆ THỐNG QUẢN LÝ SỔ HỘ KHẨU
					</div>
				</div>
	</div>
	<div class="row">
		<div class="row">
			<div class="col-md-12 text-center" ><h3>Nhập sổ hộ khẩu</h3></div>
		</div>
		<div class="row">
			<div class="col-md-12 text-center" id="ket-qua"></div>
		</div>
		<div class="form-border row mt20" style="border: so_lid black 1px;">	
				<form name="nhapHoKhau" action="ket-qua-them-ho-khau"  >
					<div class="row">
						<div class="col-md-1"></div>
						<div class="col-md-2 mt5">
							<label>Nhập số lượng nhân khẩu</label>
						</div>
						<div class="col-md-2">
							<input class="form-control" type="text" oninput="kiemtra_nhapso(this.id, this.value);" name="soNhanKhau" id="soNhanKhau" placeholder="Số Lượng">
						</div>
						<div class="col-md-1">
							<input class="form-control btn-primary" type="button" name="button" value="Đồng Ý " onclick="createForm();">
						</div>
						
					</div>
					<div class="row">
						<div id="form-input"></div>
					</div>
					<div class="row pull-right " id="btn-submit">
						
					</div>
				</form>
			</div>
	</div>
</div>

			<script>
				function createForm(){
					
					var soluong = $("#soNhanKhau").val();
					var member = "Chủ hộ";
					var html = "<div class='row mt20'><div class='col-md-4 text-right mt5'>Nhập địa chỉ: </div><div class='col-md-4'><input type=text id=diaChi class='form-control' /></div></div>"
							 + "<table border=1 class='col-md-5 mt20'>"
							 + "<tr><th colspan='2'>"+member+"</th></tr>"
							 + "<tr><td><input type=text class='form-control' id=chuHo oninput='kiemTraDoDai(this.id, this.value);' onkeypress='kiemTra(this.id, this.value);'/></td>"
							 + "<td><label>Nhập số Căn Cước</label></tr>"
							 + "</table>";
					for(var i = 2; i <= soluong; i++){
						html += "<table border=1 class='col-md-5 mt20'>";
						member = "Thành viên thứ "+i;
						html += "<tr><th colspan='2'>"+member+"</th></tr>"
							   + "<tr><td><input type=text class='form-control' id=number"+i+" oninput='kiemTraDoDai(this.id, this.value);' onkeypress='kiemTra(this.id, this.value);'/></td>"
							   + "<td><input type=radio name=thanhVien-"+i+" id=soCC"+i+" value='1' /> <label class='mb5'>số CC</label> <input type=radio name=thanhVien-"+i+" id=soKS"+i+" value='2'/> <label>số khai sinh</label></td></tr>"
							   + "</table>";
					}
					$("#form-input").html(html);
					$("#btn-submit").html("<button type=button onclick='send();' class='btn btn-primary' >Nhập</button>");
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
			function send(){
				var soluong = $("#soNhanKhau").val();
				var str = "diaChi="+$("#diaChi").val()+"&abc="+soluong+"s"+$("#chuHo").val()+"tv";
				alert(str);
				for(var i = 2; i <= soluong; i++){
					var xyz = document.getElementById("soCC"+i);
					var catChuoi = $("#number"+i).val().split("-");
					var so = catChuoi[0]+catChuoi[1]+catChuoi[2];
					if(xyz.checked){
						str += "x1s"+so;
					} else {
						str += "x2s"+so;
					}
				}

				$.ajax({url: 'abc-xyz',
					type: 'GET',
					data: str,
				  	success: function(result){
				  		$("#ket-qua").html(result);
			        }});
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