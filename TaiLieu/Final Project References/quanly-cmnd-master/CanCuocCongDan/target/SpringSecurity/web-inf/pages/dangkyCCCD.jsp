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
</head>
<body>
	<div >
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
	</div>	
	<div class="container">
		<div class="row">
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
			<div class="form-border" style="border: solid black 1px;">
			<div class="row" style="padding:10px">
			<div class="col-md-2"></div>
			<div class="col-md-10">
				
				<form name="dangkyCCCD" action="ket-qua-dang-ky" onsubmit="return kiemtra_submit();">
					<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
					<div class="form-group row" style="margin-bottom: 40px;">
						<h3><label for="" class="col-md-10 text-center col-form-label">TỜ KHAI CĂN CƯỚC CÔNG DÂN</label></h3>
					</div>
					<secsion id="Step1" style="">
						<div class="form-group row">
							<label for="" class="col-md-10 text-center col-form-label">THIẾT LẬP YÊU CẦU</label>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-2 col-form-label">Bạn muốn:</label>
							<div class="col-md-2">
								<div class="radio-inline">
							      <input type="radio" name="yeuCau" id="yeuCau_1" value="1" onclick="kiemtra_yeucau(1);">Cấp mới
							    </div>
							</div>
							<div class="col-md-2">
								<div class="radio-inline">
							      <input type="radio" name="yeuCau" id="yeuCau_2" value="2" onclick="kiemtra_yeucau(2);">Cấp đổi
							    </div>
							</div>
							<div class="col-md-2">
								<div class="radio-inline">
							      <input type="radio" name="yeuCau" id="yeuCau_3" value="3" onclick="kiemtra_yeucau(2);">Cấp lại
							    </div>
							</div>
						</div>
						<div class="form-group row">
						<div class="col-md-1"></div>
						<label class="col-md-2 col-form-label">Làm việc tại Ủy Ban:</label>
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
					    <div class="col-md-4 mt5">
					    	Lưu ý: Muốn làm tại Ủy Ban Tỉnh thì không chọn Huyện
					    </div>
					</div>
						<div class="form-group row">
							<div class="col-md-3"></div>
							<div class="col-md-4"><hr /></div>
						</div>
					<div class="form-group row">
						<div class="col-md-1"></div>
						<label class="col-md-3 col-form-label">Chuyển phát:</label>
						<div class="col-md-4">
							<div class="radio-inline">
						      <input type="radio" name="chuyenPhat"  value="1">Có
						    </div>
						    <div class="radio-inline">
						      <input type="radio" name="chuyenPhat"  value="0" checked>Không
						    </div>
					    </div>
					</div>
					<div class="form-group row">
						<div class="col-md-1"></div>
						<label class="col-md-3 col-form-label">Chụp ảnh trực tuyến:</label>
						<div class="col-md-2">
							<a href="open-webcam" target="_blank" ><input type=button class="btn btn-success" value="Mở WEBCAM"/></a>
					    </div>
					    <label class="col-md-4 mt5">Chọn để tiết kiệm 1 phần chi phí chụp ảnh</label>
					</div>
					</secsion>
					
					<secsion id="Step2" class="display-none">
						<div class="form-group row">
							<label for="" class="col-md-10 text-center col-form-label">XÁC NHẬN THÔNG TIN</label>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label for="" class="col-md-3 col-form-label">Mã số khai sinh:</label>
							<div class="col-md-4">
						    	<input class="form-control" type="text" value="" name="maSoKhaiSinh" id="maSoKhaiSinh" oninput='kiemTraDoDai(this.id, this.value); getThongTinKhaiSinh(this.value);' onkeypress='chenGachNgang(this.id, this.value);' />
						  	</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1" > </div>
							<label class="col-md-3 col-form-label">Số CMND/CCCD cũ:</label>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<div class="col-md-10">
								<div class="table-responsive">
								  <table class="table" padding=0>
								    <thead>
								      	<%
								      	String html = "<tr>";
									      for(int i = 1; i <= 12; i++){
									    	if(i >= 9){
									    		html += "<th><input type=text size=1 oninput='kiemtra_number(this.id, this.value); getThongTin(this.id)' name='so_"+i+"' id='so_"+i+"' /></th>";
									    	} else {
									      		html += "<th><input type=text size=1 oninput='kiemtra_number(this.id, this.value);' name='so_"+i+"' id='so_"+i+"' /></th>";
									    	}
									      }
									      html += "</tr>";
									      out.println(html);
								       %>
								       
								    </thead>
								  </table>
								 </div>
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
					</secsion>
					<secsion id="Step3" class="display-none">
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
							<label class="col-md-3 col-form-label">3. Nhóm máu (nếu có):</label>
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
							<label class="col-md-3 col-form-label">4. Nơi đăng ký khai sinh:</label>
							<div class="col-md-2">
								<select name="khaiSinhTinh" id="khaiSinhTinh" class="form-control" onclick='getHuyen(this.id, this.value);' onchange='getHuyen(this.id, this.value);'>
									<c:forEach items='${dsTinh}' var='tinh'>
										<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-2">
								<select name="khaiSinhHuyen" id="khaiSinhHuyen" class="form-control" onchange='getXa(this.id, this.value);'>
								</select>
							</div>
							<div class="col-md-2">
								<select name="khaiSinhXa" id="khaiSinhXa" class="form-control">
								</select>
							</div>
							<label class="col-md-1 mt5 text-red">*</label>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">5. Nơi thường trú:</label>
							<div class="col-md-2">
								<select name="thuongTruTinh" id="thuongTruTinh" class="form-control" onclick='getHuyen(this.id, this.value);' onchange='getHuyen(this.id, this.value);'>
									<c:forEach items='${dsTinh}' var='tinh'>
										<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-2">
								<select name="thuongTruHuyen" id="thuongTruHuyen" class="form-control" onchange='getXa(this.id, this.value);'>
								</select>
							</div>
							<div class="col-md-2">
								<select name="thuongTruXa" id="thuongTruXa" class="form-control">
								</select>
							</div>
							<label class="col-md-1 mt5 text-red">*</label>
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
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">7. Trình độ học vấn:</label>
							<div class="col-md-4">
								<input type="text" name="trinhDoHocVan" id="trinhDoHocVan" class="form-control" placeholder="Nhập trình độ học vấn"/>
							</div>
							<label class="col-md-1 mt5 text-red">*</label>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">8. Nghề nghiệp:</label>
							<div class="col-md-4">
								<input type="text" name="ngheNghiep" id="ngheNghiep" class="form-control" placeholder="Nhập nghề nghiệp"/>
							</div>
							<label class="col-md-1 mt5 text-red">*</label>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">9. Quan hệ với chủ hộ:</label>
							<div class="col-md-4">
								<input type="text" name="quanHeChuHo" id="quanHeChuHo" class="form-control" placeholder="Nhập mối quan hệ với chủ hộ"/>
							</div>
							<label class="col-md-1 mt5 text-red">*</label>
						</div>
					</secsion>
					<secsion id="Step4" class="display-none">
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
							<input type="hidden" name="soCC" id="soCC" value="000000000000" />
							<input type="hidden" name="soCCCha" id="soCCCha" value="000000000000" />
							<input type="hidden" name="soCCMe" id="soCCMe" value="000000000000" />
							<input type="hidden" name="soCCVoChong" id="soCCVoChong" value="000000000000" />
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
					</secsion>
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
			<!-- 			End form dang ky CCCD -->
		
			<!-- 			Page footer -->
			<jsp:include page="footer.jsp"></jsp:include>
			<!-- 			End page footer -->
		
		</div>
		<!-- 		End row -->
	</div>
	<!-- 	End container -->
	<sec:authorize access="hasRole('ROLE_USER')">
		<!-- For login user -->
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>


	</sec:authorize>
    <script src="resources/js/dangky-cccd.js"></script>
    <script>
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
    		if(vlue.length == 15){
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
	    		  		
	    		  		
	    		  		var jHoTenCha = data[7];
	    		  		
	    		  		var jHoTenMe = data[9];
	    		  		
	    		  	
	    		  	
	    		  		var jHoTenChuHo = data[13];
	    		  		var jSoHoKhau = data[14];
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