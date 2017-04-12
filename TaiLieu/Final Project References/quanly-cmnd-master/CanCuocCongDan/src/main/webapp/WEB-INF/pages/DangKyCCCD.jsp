<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="include.jsp"></jsp:include>
	<title>ĐĂNG KÝ LÀM CĂN CƯỚC CÔNG DÂN</title>
	<style>
		.xem-quy-dinh a{color:blue;}
		.xem-quy-dinh a:hover{color:#5cb85c;}
	</style>
</head>
<body>
	<div class="container">
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
	</div>
	<div class="container">
	<div class="row bg-content">	
	<div class="col-md-12">
		<div class="row mt5">
			<article class='col-md-12 col-xs-12'>
		        <div class='step'>
		           <ul id='progressbar'>
		              <li id="liStep1" class='active'><span>Thiết lập Yêu cầu</span></li>
		              <li id="liStep2"><span>Xác nhận thông tin</span></li>
		              <li id="liStep3"><span>Bổ sung thông tin</span></li>
		              <li id="liStep4"><span>Xác nhận đăng ký</span></li>
		           </ul>
		        </div>
    		</article>
		</div>
		<div class="row">
			<!-- 			form dang ky CCCD -->
			<div class="radius-01  bg-white">
			<div class="row" style="padding:10px">
			<div class="col-md-2"></div>
			<div class="col-md-10">
				
				<form name="dangkyCCCD" action="ket-qua-dang-ky?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" method="POST" onsubmit="return kiemtra_submit();">
					<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
					<div class="form-group row text-center" style="margin-bottom: 40px;">
						<h3><label for="" class="col-md-10  col-form-label">TỜ KHAI CĂN CƯỚC CÔNG DÂN</label></h3>
					</div>
					<section id="Step1" style="">
						<div class="form-group row">
							<label for="" class="col-md-10 text-center col-form-label">THIẾT LẬP YÊU CẦU</label>
						</div>
						<div class="row">
							<label class="col-md-2 col-form-label">Xem quy định:</label>
							<div class="col-md-4 xem-quy-dinh">
								<a href='quy-dinh-cap-can-cuoc-cong-dan?#cap-moi' target=_blank >Cấp mới</a>, 
								<a href='quy-dinh-cap-can-cuoc-cong-dan?#cap-doi' target=_blank >Cấp đổi</a>, 
								<a href='quy-dinh-cap-can-cuoc-cong-dan?#cap-lai' target=_blank >Cấp lại</a>
							</div>
						</div>
						<div class="row">
							<label class="col-md-2 col-form-label">Bạn muốn:</label>
							<c:forEach items='${dsYeuCau}' var='yeuCau'>
								<div class="col-md-2">
									<div class="radio-inline">
								      <input type="radio" name="yeuCau" id="yeuCau_${yeuCau.maYeuCau}" value="${yeuCau.maYeuCau}" onclick="kiemtra_yeucau(${yeuCau.maYeuCau});">${yeuCau.tenYeuCau}
								    </div>
								</div>
							</c:forEach>
						</div>
						<div class="form-group row">
						<label class="col-md-2 col-form-label">Chọn Ủy ban làm việc:</label>
						<div class="col-md-2">
							 <select name="noiDKLVTinh" id="noiDKLVTinh" class="form-control" onclick='getHuyen(this.id, this.value);' onchange='getHuyen(this.id, this.value);'>
							 		<c:forEach items='${dsTinh}' var='tinh'>
										<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
									</c:forEach>
							 </select>
					    </div>
					    <div class="col-md-2">
							 <select name="noiDKLVHuyen" id="noiDKLVHuyen" onfocus='getHuyenFocus(this.id);' class="form-control">
							 	<option value="0">Chọn Huyện</option>
							 </select>
					    </div>
					    <div class="col-md-6 mt5">
					    	Lưu ý: Muốn làm tại Ủy Ban Tỉnh thì không chọn Huyện
					    </div>
					</div>
						<div class="form-group row">
							<div class="col-md-3"></div>
							<div class="col-md-4"><hr /></div>
						</div>
					</section>
					
					<section id="Step2" class="display-none">
						<div class="form-group row">
							<label class="col-md-10 text-center col-form-label">XÁC NHẬN THÔNG TIN</label>
							<div class=" row col-md-10 text-center text-red mt20" id='ketQuaKiemTraSoKS'>
			
							</div>
						</div>
						
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">Mã số khai sinh:</label>
							<div class="col-md-4">
						    	<input class="form-control" type="text" value="" name="maSoKhaiSinh" id="maSoKhaiSinh" 
						    	onkeyup='kiemTraSo(this.id,this.value);'
						    	oninput='kiemTraDoDai(this.id, this.value); getThongTinKhaiSinh(this.value);'
						    	onkeypress='chenGachNgang(this.id, this.value);'
						    	onblur="getThongTinDangKy(); if(document.getElementById('yeuCau_1').checked)getSoCCDangKy();"
						    	placeholder="Nhập số khai sinh" />
						  	</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1" > </div>
							<label class="col-md-3 col-form-label">Số CMND/CCCD cũ:</label>
							<div class="col-md-4">
						    	<input class="form-control" type="text" name="soCC" id="soCC" value=" " 
						    	onkeyup='kiemTraSo(this.id,this.value); kiemTraCCKS();'
						    	oninput='kiemTraDoDai(this.id, this.value);'
						    	onkeypress='chenGachNgang(this.id, this.value);'
						    	oninput='getThongTin(this.id);' 
						    	placeholder="Nhập số CMND/CCCD cũ" />
						  	</div>
						</div>
						
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label for="" class="col-md-3 col-form-label">1. Họ, chữ đệm và tên:</label>
							<div class="col-md-4">
						    	<input class="form-control" type="text" value="" name="hoTen" id="hoTen" value="" disabled>
						  	</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label for="" class="col-md-3 col-form-label">2. Ngày tháng năm sinh:</label>
							<div class="col-md-4">
						   		<input type='text' class="form-control" name="ngaySinh" id="ngaySinh" value="" disabled/>
						   	</div>
						</div>
						
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">3. Giới tính:</label>
							<div class="col-md-4">
								<input type=text name='gioiTinh' id='gioiTinh' class='form-control' value="" disabled />
						    </div>
						    <label class="col-md-1 mt5 text-red"></label>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">4. Dân tộc:</label>
							<div class="col-md-4">
						   		<input type=text name=danToc id='danToc' class='form-control' value="" disabled />
						   	</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">5. Họ, chữ đệm và tên của cha:</label>
							<div class="col-md-4">
								<input type="text" name="hoTenCha" id="hoTenCha" class="form-control" value="" disabled="disabled"/>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">6. Họ, chữ đệm và tên của Mẹ:</label>
							<div class="col-md-4">
								<input type="text" name="hoTenMe" id="hoTenMe" class="form-control" value="" disabled="disabled"/>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">7. Họ, chữ đệm và tên của Chủ hộ:</label>
							<div class="col-md-4">
								<input type="text" name="hoTenChuHo" id="hoTenChuHo" class="form-control" value="" disabled="disabled"/>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">8. Số hộ khẩu:</label>
							<div class="col-md-4">
								<input type="number" name="soHoKhau" id="soHoKhau" class="form-control" value="" disabled/>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2"></div>
							<div class="col-md-6 text-center">
								<a href='dang-ky-khai-sinh'><i>Thông tin sai, click <b>Vào đây</b> để đăng ký sửa thông tin khai sinh</i></a>
							</div>
						</div>
					</section>
					<section id="Step3" class="display-none">
						<div class="form-group row">
							<label for="" class="col-md-10 text-center col-form-label">BỔ SUNG THÔNG TIN</label>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label for="" class="col-md-3 col-form-label">1. Tên gọi khác (nếu có):</label>
							<div class="col-md-4">
						    	<input class="form-control" type="text" value="" name="hoTenKhac" id="hoTenKhac" placeholder="Nhập tên gọi khác nếu có">
						  	</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">2. Tôn giáo:</label>
						   	<div class="col-md-4">
						   		<input type="text" class="form-control" name="tonGiao" id="tonGiao" placeholder="Nhập tôn giáo" />
						   	</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">3. Trình độ học vấn:</label>
							<div class="col-md-4">
								<input type="text" name="trinhDoHocVan" id="trinhDoHocVan" class="form-control" placeholder="Nhập trình độ học vấn"/>
							</div>
							<label class="col-md-1 mt5 text-red">*</label>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">4. Nghề nghiệp:</label>
							<div class="col-md-4">
								<input type="text" name="ngheNghiep" id="ngheNghiep" class="form-control" placeholder="Nhập nghề nghiệp"/>
							</div>
							<label class="col-md-1 mt5 text-red">*</label>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">5. Nhóm máu (nếu có):</label>
						   	<div class="col-md-4">
						   		<select name=nhomMau id=nhomMau class='form-control'>
									<c:forEach items='${dsNhomMau}' var='nhomMau'>
										<option id="option" value="${nhomMau.maNM}">${nhomMau.tenNM}</option>
									</c:forEach>
								</select>
						   	</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">6. Nơi ở hiện tại:</label>
							<div class="col-md-2">
								<select name="oTinh" id="oTinh" class="form-control" onclick='getHuyen(this.id, this.value);' onchange='getHuyen(this.id, this.value);'>
									<c:forEach items='${dsTinh}' var='tinh'>
										<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-2">
								<select name="oHuyen" id="oHuyen" class="form-control" onchange='getXa(this.id, this.value);'>
								</select>
							</div>
							<div class="col-md-2">
								<select name="oXa" id="oXa" class="form-control">
								</select>
							</div>
							<label class="col-md-1 mt5 text-red">*</label>
						</div>
					</section>
					<section id="Step4" class="display-none">
						<div class="form-group row">
							<label for="" class="col-md-10 text-center col-form-label">XÁC NHẬN ĐĂNG KÝ</label>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-2 col-form-label">Mã xác nhận:</label>
							<div class="col-md-4">
								<input type="text" name="maXacNhan" id="maXacNhan" placeholder="Nhập mã xác nhận"  class="form-control" value=""/> 
						    </div>
						    <div class="col-md-3">
						    	<label class="text-red mt5">${maXacNhan}</label>
						    </div>
						</div>
						<div class="form-group row">
							<input type="hidden" name="tonTaiCC" id="tonTaiCC" />
							<input type="hidden" name="soCCDDHP" id="soCCDDHP" value="000000000000" />
							<input type="hidden" name="soCCChuHo" id="soCCChuHo" value="000000000000" />
							<input type="hidden" id="step" value=1 />
						</div>
						<div class="form-group row mt40">
							<div class="col-md-4"></div>
							<div class="col-md-2">
								<button type="submit" name="submit" class="form-control btn-primary">Đăng ký</button>
							</div>
						</div>
					</section>
				</form>
				<div class="row mt40 mb40">
					<div class="col-md-10" id="btnOK">
						<div class="pull-left">
							<button type=button onclick='step(-1)' id="btnQuayLai" class='btn btn-primary display-none'>Quay lại</button>
						</div>
						
						<div class="pull-right">
							<button type=button onclick='step(1)' id="btnTiepTheo" class='btn btn-primary'>Tiếp theo</button>
						</div>
					</div>
				</div>
			</div>
			</div>
			</div>
			
		</div>
	</div>
	</div>
</div>
<div class='container'>
	<div class="row">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</div>
	<!-- 	End container -->
    <script src="resources/js/dangky-cccd.js"></script>
   	${resultCaptcha }
    <script>
    function kiemTraSo(id, vlue) {
		var newVal = replaceAll(vlue, "-", "");
		//Lay ky tu cuoi cung vua nhap vao
		var lastChar = vlue.substr(vlue.length - 1);
		//Neu khong phai la so
		if(isNaN(newVal) || (lastChar == " ")) {
			alert("Số Khai sinh - CMND/CCCD không được nhập ký tự chữ hoặc khoảng trắng");
			if (isNaN(lastChar)) {
				$("#"+id).val("");
			} else {
				$("#"+id).val("");
			}
			return false;
		}
	}
    	function getThongTinKhaiSinh(vlue){
    		if(vlue.length < 15){
    			$("#hoTen").val("");
		  		$("#ngaySinh").val("");
		  		$("#gioiTinh").val("");
		  		$("#quocTich").val("");
		  		$("#danToc").val("");
		  		$("#queQuan").val("");
		  		$("#soCCCha").val("");
		  		$("#hoTenCha").val("");
		  		$("#soCCMe").val("");
		  		$("#hoTenMe").val("");
		  		$("#soCCDD").val("");
		  		$("#hoTenDD").val("");
		  		$("#soCCChuHo").val("");
		  		$("#hoTenChuHo").val("");
		  		$("#soHoKhau").val("");
		  		$("#maSoKhaiSinh").attr("aria-value","0");
    		}
    		if(vlue.length >= 11){
    		var data = vlue.split("-");
    		data = data[0]+data[1]+data[2]+data[3];
    		var soKS = "soKS="+data;
    		$.ajax({url: 'get-thong-tin-khai-sinh',
    			type: 'GET',
    			data: soKS,
    		  	success: function(result){
    		  			if(result.length == null || result == "" || result.length < 1){
    		  				$("#maSoKhaiSinh").attr("aria-value","0");
    		  				return false;
    		  			} else {
    		  				$("#maSoKhaiSinh").attr("aria-value","1");
    		  			}
	    		  		var data = result.split("_");
	    		  		var jHoTen = data[0];
	    		  		var jNgaySinh = data[1];
	    		  		var jGioiTinh = data[2];		  		
	    		  		var jDanToc = data[4];
	    		  		var jHoTenCha = data[5];		  		
	    		  		var jHoTenMe = data[6];	    		  	
	    		  		var jHoTenChuHo = data[7];
	    		  		var jSoHoKhau = data[8];
	    		  		$("#hoTen").val(jHoTen);
	    		  		$("#ngaySinh").val(jNgaySinh);
	    		  		$("#gioiTinh").val(jGioiTinh);
	    		  		$("#danToc").val(jDanToc);
	    		  		$("#hoTenCha").val(jHoTenCha);
	    		  		$("#hoTenMe").val(jHoTenMe);
						$("#hoTenChuHo").val(jHoTenChuHo);
	    		  		$("#soHoKhau").val(jSoHoKhau);
    	        }});
    		}
    	}
    </script>
</body>
</html>
