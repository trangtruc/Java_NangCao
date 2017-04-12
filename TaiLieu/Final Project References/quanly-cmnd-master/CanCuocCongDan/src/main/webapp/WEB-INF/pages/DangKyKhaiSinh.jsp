<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="include.jsp"></jsp:include>
	<title>ĐĂNG KÝ KHAI SINH</title>
	<script>
		var kqDangKy = <%= session.getAttribute("kqDangKy")%>;
		<% session.removeAttribute("kqDangKy"); %>
		if (kqCapNhat) {
			alert("Đăng ký thành công");
		} else if (kqCapNhat == false) {
			alert("Đăng ký chưa thành công.");
		}
	</script>
	<style>
	#progressbar li{
		font-size:14px;
		width:16%
		}
	</style>
</head>
<body>
	<div class="container">
		<!-- 			Page header -->
				<jsp:include page="header.jsp"></jsp:include>
			<!-- 			End page header -->
	</div>	
	<div class="container bg-content">
		<div class="row">
			<article class='col-md-12 col-xs-12'>
		        <div class='step'>
		           <ul id='progressbar'>
		              <li id="liStep1" class='active'><span>Thiết lập Yêu cầu</span></li>
		              <li id="liStep2"><span>Điền thông tin</span></li>
		              <li id="liStep3"><span>Thông tin người yêu cầu</span></li>
		              <li id="liStep4"><span>Thông tin cha</span></li>
		              <li id="liStep5"><span>Thông tin mẹ</span></li>
		              <li id="liStep6"><span>Điền mã xác nhận</span></li>
		           </ul>
		        </div>
    		</article>
		</div>
		<div class="row">
			<!-- 			form dang ky CCCD -->
			<div class="radius-01 bg-white">
			<div class="row" style="padding:10px">
			<div class="col-md-2"></div>
			<div class="col-md-10">
				
				<form 	name="dangkyKhaiSinh" 
						action="dang-ky-khai-sinh?${_csrf.parameterName}=${_csrf.token}" 
						enctype="multipart/form-data" 
						method="POST" 
						onsubmit="return ktOnSubmit();">
					<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
					<div class="form-group row" style="margin-bottom: 40px;">
						<h3><label for="" class="col-md-10 text-center col-form-label">TỜ KHAI ĐĂNG KÝ KHAI SINH</label></h3>
					</div>
					<section id="Step1" style="">
						<div class="form-group row">
							<label for="" class="col-md-10 text-center col-form-label">THIẾT LẬP YÊU CẦU</label>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-2 col-form-label">Bạn muốn:</label>
							<c:forEach items='${dsYeuCau}' var='yeuCau'>
								<div class="col-md-2">
									<c:if test='${(yeuCau.maYeuCau == "1") || (yeuCau.maYeuCau ==  "3") }' >
										<div class="radio-inline">
									      <input type="radio" name="yeuCau" 
									      		id="yeuCau_${yeuCau.maYeuCau}" 
									      		value="${yeuCau.maYeuCau}" 
									      		onclick="kiemtra_yeucau(${yeuCau.maYeuCau});">${yeuCau.tenYeuCau}
									    </div>
									 </c:if>
								</div>
							</c:forEach>
						</div>
						<div class="form-group row">
						<div class="col-md-1"></div>
						<label class="col-md-2 col-form-label">Làm việc tại UBND:</label>
						<div class="col-md-2">
							 <select name="noiDKLVTinh" id="noiDKLVTinh" 
							 		class="form-control" 
							 		onchange='getHuyen(this.id, this.value);'>
							 		<c:forEach items='${dsTinh}' var='tinh'>
										<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
									</c:forEach>
							 </select>
					    </div>
					    <div class="col-md-2">
							 <select name="noiDKLVHuyen" id="noiDKLVHuyen" 
							 		onfocus='getHuyenFocus(this.id);' 
							 		onchange='getHuyen(this.id, this.value);' 
							 		class="form-control">
							 	<option value="0">Chọn Huyện</option>
							 </select>
					    </div>
					     <div class="col-md-2">
							 <select name="noiDKLVXa" id="noiDKLVXa" 
							 		onfocus='getXaFocus(this.id);' class="form-control">
							 	<option value="0">Chọn xã</option>
							 </select>
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
<!-- 							<div class=" row col-md-10 text-center text-red mt20" id='ketQuaKiemTraSoKS'> -->
			
<!-- 							</div> -->
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">1. Mã số khai sinh (cũ):</label>
							<div class="col-md-4">
							   	<input 
							   		placeholder='Nhập mã số khai sinh (cũ) nếu có'
									onkeypress='chenGachNgang(this.id, this.value);' 
									onkeyup = 'kiemTraSo(this.id, this.value);' 
									oninput ='kiemTraDoDai(this.id, this.value);' 
									onblur='getThongTinKS(this.id);'
									type=text 	
									id='soKSCu' 	
									name='soKSCu' 	
									class='form-control' />
							  </div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label for="" class="col-md-3 col-form-label">2. Họ, chữ đệm và tên:</label>
							<div class="col-md-4">
						    	<input type=text id='hoTen' name='hoTen' class='form-control' />
						  	</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label for="" class="col-md-3 col-form-label">3. Ngày tháng năm sinh:</label>
							<div class="col-md-4">
						   		<input type='date' id='ngaySinh' 
									name=ngaySinh class='form-control' />
						   	</div>
						</div>
						
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">4. Giới tính:</label>
							<div class="radio-inline">
								<input type="radio" checked name="gioiTinh" id="gioiTinh_1" value="Nam">Nam
							</div>
							<div class="radio-inline">
								<input type="radio" name="gioiTinh" id="gioiTinh_2" value="Nu">Nữ
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">5. Quốc tịch:</label>
							<div class="col-md-4">
						   		<input type=text name=quocTich id='quocTich' 
									class='form-control' value="Việt Nam"/>
						   	</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">6. Dân tộc:</label>
							<div class="col-md-4">
								<select id="danToc" name='maDT' class='form-control'>
									<c:forEach items='${dsDanToc}' var='danToc'>
										<option id="option" value="${danToc.maDT}">${danToc.tenDT}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">8. Tên bệnh viện nơi sinh:</label>
							<div class="col-md-4">
								<input type=text id='benhVien' 
									placeholder="Nhập tên bệnh viện nơi sinh (Nếu có)"
									name='benhVien' class='form-control'/>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">7. Nơi sinh:</label>
							<div class="col-md-2">
								<select name=noiSinhTinh id=noiSinhTinh class='form-control' onchange='getHuyen(this.id, this.value);'>
									<option id="option" value="">Chọn tỉnh</option>
									<c:forEach items='${dsTinh}' var='tinh'>
										<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-2">
								<select name="noiSinhHuyen" id="noiSinhHuyen" 
										class="form-control" 
										onfocus='getHuyenFocus(this.id);'
										onchange='getXa(this.id, this.value);'>
								</select>
							</div>
							<div class="col-md-2">
								<select name="noiSinhXa" id="noiSinhXa" 
										onfocus='getXaFocus(this.id);' class="form-control">
								</select>
							</div>
						</div>
						
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">9. Quê quán:</label>
							<div class="col-md-2">
								<select name=queQuanTinh id=queQuanTinh class='form-control' onchange='getHuyen(this.id, this.value);'>
									<option id="option" value="">Chọn tỉnh</option>
									<c:forEach items='${dsTinh}' var='tinh'>
										<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-2">
								<select name="queQuanHuyen" id="queQuanHuyen" 
										class="form-control" 
										onfocus='getHuyenFocus(this.id);'
										onchange='getXa(this.id, this.value);'>
								</select>
							</div>
							<div class="col-md-2">
								<select name="queQuanXa" id="queQuanXa" 
										class="form-control" 
										onfocus='getXaFocus(this.id);'>
								</select>
							</div>
						</div>
					</section>
					<section id="Step3" class="display-none">
						<div class="form-group row">
							<label for="" class="col-md-10 text-center col-form-label">THÔNG TIN NGƯỜI YÊU CẦU LÀM KHAI SINH</label>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label for="" class="col-md-3 col-form-label">1. Số căn cước người yêu cầu:</label>
							<div class="col-md-4">
						    	<input type=text 
								onkeypress='chenGachNgang(this.id, this.value);' 
								oninput='kiemTraDoDai(this.id, this.value);' 
								onblur='getThongTinCCCD(this.id, this.value);' 
								onkeyup = 'kiemTraSo(this.id, this.value);'
								id='soCCNguoiYeuCau' 
								name='soCCNguoiYeuCau' 
								value='${taiKhoan.username}'
								class='form-control' />
						  	</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">2. Quan hệ với người khai sinh:</label>
						   	<div class="col-md-4">
						   		<input type=text 
								id='quanHeVoiNguoiKS' 
								name='quanHeVoiNguoiKS' class='form-control' />
						   	</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">3. Họ, chữ đệm, tên người yêu cầu:</label>
						   	<div class="col-md-5">
						   		<input type=text 
										id='hoTenNguoiYeuCau' 
										name='hoTenNguoiYeuCau' 
										value='${taiKhoan.hoTen}'
										class='form-control' disabled />
						   	</div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">4. Nơi thường trú:</label>
							<div class="col-md-5">
							   	<input type=text 
										id='thuongTruNguoiYeuCau' 
										value="${thuongTru}" 
										name='thuongTruNguoiYeuCau' class='form-control' disabled/>
							 </div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">5. Nơi cấp:</label>
							<div class="col-md-5">
								<input type=text 
										id='noiCapCCNguoiYeuCau' 
										name='noiCapCCNguoiYeuCau' 
										value="${noiCap.tenTinh}"
										class='form-control' disabled />
							 </div>
						</div>
					</section>
					<section id="Step4" class="display-none">
						<div class="form-group row">
							<label for="" class="col-md-10 text-center col-form-label">THÔNG TIN CHA</label>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">Số căn cước cha:</label>
							<div class="col-md-4">
								<input type=text 
										onkeypress='chenGachNgang(this.id, this.value);' 
										oninput='kiemTraDoDai(this.id, this.value);' 
										onblur='getThongTinCCCD(this.id, this.value);' 
										onkeyup = 'kiemTraSo(this.id, this.value);' 
										id='soCCCha' 
										name='soCCCha' 
										value=""
										class='form-control' />
						    </div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">Họ tên cha:</label>
							<div class="col-md-4">
								<input type=text 
										id='hoTenCha'
										name='hoTenCha' 
										value=""
										class='form-control' disabled />
						    </div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">Dân tộc cha:</label>
							<div class="col-md-4">
								<input type=text 
										id='danTocCha' 
										name='danTocCha' 
										value="" 
										class='form-control' disabled />
						    </div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">Quốc tịch cha:</label>
							<div class="col-md-4">
								<input type=text 
									id='quocTichCha' 
									name='quocTichCha' 
									value="" 
									class='form-control' disabled />
						    </div>
						</div>
					</section>
					<section id="Step5" class="display-none">
						<div class="form-group row">
							<label for="" class="col-md-10 text-center col-form-label">THÔNG TIN MẸ</label>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">Số căn cước mẹ:</label>
							<div class="col-md-4">
								<input type=text 
										onkeypress='chenGachNgang(this.id, this.value);' 
										oninput='kiemTraDoDai(this.id, this.value);' 
										onblur='getThongTinCCCD(this.id, this.value);' 
										onkeyup = 'kiemTraSo(this.id, this.value);'
										id='soCCMe' 
										name='soCCMe' class='form-control' />
						    </div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">Họ tên mẹ:</label>
							<div class="col-md-4">
								<input type=text 
										id="hoTenMe" 
										name='hoTenMe' class='form-control' disabled />
						    </div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">Dân tộc mẹ:</label>
							<div class="col-md-4">
								<input type=text 
								id='danTocMe' 
								name='danTocMe' class='form-control' disabled />
						    </div>
						</div>
						<div class="form-group row">
							<div class="col-md-1"></div>
							<label class="col-md-3 col-form-label">Quốc tịch mẹ:</label>
							<div class="col-md-4">
								<input type=text 
								id='quocTichMe' 
								name='quocTichMe' class='form-control' disabled />
						    </div>
						</div>
						
					</section>
					<section id="Step6" class="display-none">
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
							<input type="hidden" id="step" value=1 />
							<div class="form-group row mt40">
								<div class="col-md-4"></div>
								<div class="col-md-2">
									<button type="submit" name="submit" class="form-control btn-primary">
										Đăng ký
									</button>
								</div>
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
			<!-- 			End form dang ky CCCD -->
		</div>
	</div>
	<div class='container'>
		<div class="row">
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</div>
	<!-- 	End container -->
    <script src="resources/js/DangKyKhaiSinh.js"></script>
   	${resultCaptcha }
	
</body>
</html>
