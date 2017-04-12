<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="include.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>CẬP NHẬT ĐĂNG KÝ KHAI SINH</title>
	<script>
		var kqCapNhat = <%= session.getAttribute("kqCapNhat")%>;
		<% session.removeAttribute("kqCapNhat"); %>
		if (kqCapNhat) {
			alert("Cập nhật thành công");
		} else if (kqCapNhat == false) {
			alert("Cập nhật chưa thành công.");
		}
	</script>
	<script src="resources/js/CapNhatDKKhaiSinh.js">
	</script>
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
<sec:authorize access="!hasRole('DUYET_KHAI_SINH_1')">
	<div class="container mt20" style="min-height:420px">
		<div class="mt80">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('DUYET_KHAI_SINH_1')">
<form   action="cap-nhat-dk-khai-sinh?${_csrf.parameterName}=${_csrf.token}"
		enctype="multipart/form-data"
		method="post" 
		onsubmit='return onSubmitCheckNull();'>
<div class="container bg-content" style="min-height:420px">
	<div class="row mt40">
<!-- 		<div class="col-md-3"> -->
<%-- 				<jsp:include page="menu-trai.jsp"></jsp:include> --%>
<!-- 		</div> -->
		<div class="col-md-12 bg-white">
			<div class="row mt20">
				<div class="text-center">
					<div class="title">
							CẬP NHẬT ĐĂNG KÝ KHAI SINH
					</div>
				</div>
			</div>
			<div class="col-md-6 mt20">
				<div class="row">
					<table border="1">
						<tr><th colspan="4">THÔNG TIN NGƯỜI ĐƯỢC KHAI SINH</th></tr>
						<tr>
							<td>Số đăng ký</td>
							<td colspan="3"><input type=text id='so' 
									name='so' value="${ttdkKhaiSinh.soKS}" class='form-control' disabled/></td>
						</tr>
						<tr>
							<td>Họ, chữ đệm, tên</td>
							<td colspan="3"><input type=text id='hoTen' 
									name='hoTen' value="${ttdkKhaiSinh.hoTen}" class='form-control' /></td>
						</tr>
						<tr>
							<td>Ngày, tháng, năm sinh</td>
							<td colspan="3">
								<input type=date id='ngaySinh' 
									name=ngaySinh value="${ttdkKhaiSinh.ngaySinh}" class='form-control' />
							</td>
						</tr>
						<tr>
							<td>Giới tính</td>
							<td colspan="3">
								<c:if test="${ttdkKhaiSinh.gioiTinh == 'Nam'}">
									<div class="radio-inline">
							      		<input type="radio" checked name="gioiTinh" id="gioiTinh_1" value="Nam">Nam
								    </div>
								    <div class="radio-inline">
								    	<input type="radio" name="gioiTinh" id="gioiTinh_2" value="Nu">Nữ
								    </div>
								</c:if>
								<c:if test="${ttdkKhaiSinh.gioiTinh != 'Nam'}">
									<div class="radio-inline">
							      		<input type="radio" name="gioiTinh" id="gioiTinh_1" value="Nam">Nam
								    </div>
								    <div class="radio-inline">
								    	<input type="radio" checked name="gioiTinh" id="gioiTinh_2" value="Nu">Nữ
								    </div>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>Quốc tịch</td>
							<td colspan="3"><input type=text name=quocTich id='quocTich'
									class='form-control' value="${ttdkKhaiSinh.quocTich}"/></td>
						</tr>
						<tr>
							<td>Dân tộc</td>
							<td colspan="3">
								<select name="danToc" id="danToc" class="form-control">
						   			<c:forEach items='${dsDanToc}' var='danToc'>
										<c:if test="${danToc.maDT == ttdkKhaiSinh.danToc.maDT}">
											<option id="option" value="${danToc.maDT}" selected>${danToc.tenDT}</option>
										</c:if>
										<c:if test="${danToc.maDT != ttdkKhaiSinh.danToc.maDT}">
											<option id="option" value="${danToc.maDT}">${danToc.tenDT}</option>
										</c:if>
									</c:forEach>
						   		</select>
							</td>
						</tr>
						<tr>
							<td rowspan="2">Nơi sinh</td>
							<td style="width: 28%;">
								<select name=noiSinhTinh id=noiSinhTinh 
										class='form-control' onchange='getHuyen(this.id, this.value);'>
									<option id="option" value="0">Chọn tỉnh</option>
									<c:forEach items='${dsTinh}' var='tinh'>
										<c:if test="${tinh.maTinh == noiSinhXa.huyen.tinh.maTinh}">
											<option id="option" value="${tinh.maTinh}" selected>${tinh.tenTinh}</option>
										</c:if>
										<c:if test="${tinh.maTinh != noiSinhXa.huyen.tinh.maTinh}">
											<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
							<td style="width: 28%;">
								<select name="noiSinhHuyen" id="noiSinhHuyen" 
										class="form-control"
										onfocus='getHuyenFocus(this.id);'>
									<c:if test="${noiSinhXa.huyen != null}">
										<option id="option" value="${noiSinhXa.huyen.maHuyen}" selected>
											${noiSinhXa.huyen.tenHuyen}
										</option>
									</c:if>
								</select>
							</td>
							<td style="width: 28%;">
								<select name="noiSinhXa" id="noiSinhXa" 
										class="form-control" onfocus='getXaFocus(this.id);'>
									<c:if test="${noiSinhXa.maXa != null}">
										<option id="option" value="${noiSinhXa.maXa}" selected>
											${noiSinhXa.tenXa}
										</option>
									</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="3"><input type=text id='benhVien' 
									placeholder="Nhập tên bệnh viện nơi sinh (Nếu có)"
									value='${ttdkKhaiSinh.benhVien }' 
									name='benhVien' class='form-control'/></td>
						</tr>
						<tr>
							<td>Quê quán</td>
							<td width="1%">
								<select name=queQuanTinh id=queQuanTinh class='form-control' onchange='getHuyen(this.id, this.value);'>
									<option id="option" value="0">Chọn tỉnh</option>
									<c:forEach items='${dsTinh}' var='tinh'>
										<c:if test="${tinh.maTinh == queQuanXa.huyen.tinh.maTinh}">
											<option id="option" value="${tinh.maTinh}" selected>${tinh.tenTinh}</option>
										</c:if>
										<c:if test="${tinh.maTinh != queQuanXa.huyen.tinh.maTinh}">
											<option id="option" value="${tinh.maTinh}">${tinh.tenTinh}</option>
										</c:if>
									</c:forEach>
								</select>
								
							</td>
							<td>
								<select name="queQuanHuyen" id="queQuanHuyen" 
										class="form-control"
										onfocus='getHuyenFocus(this.id);'>
									<c:if test="${queQuanXa.huyen != null}">
										<option id="option" value="${queQuanXa.huyen.maHuyen}" selected>
											${queQuanXa.huyen.tenHuyen}
										</option>
									</c:if>
								</select>
							</td>
							<td>
								<select name="queQuanXa" id="queQuanXa" class="form-control" 
										onfocus='getXaFocus(this.id);'>
									<c:if test="${queQuanXa.maXa != null}">
										<option id="option" value="${queQuanXa.maXa}" selected>
											${queQuanXa.tenXa}
										</option>
									</c:if>
								</select>
							</td>
						</tr>
					</table>
				</div>
				<div class="row mt20">
					<table border="1">
						<tr><th colspan="2">THÔNG TIN NGƯỜI YÊU CẦU LÀM KHAI SINH</th></tr>
						<tr>
						<tr>
							<td>Số căn cước người yêu cầu</td>
							<td style='width: 75%;'><input type=text 
								onkeypress='chenGachNgang(this.id, this.value);' 
								oninput='kiemTraDoDai(this.id, this.value);' 
								onblur='getThongTinCCCD(this.id, this.value);' 
								onkeyup = 'kiemTraSo(this.id, this.value);' 
								id='soCCNguoiYeuCau'
								name='soCCNguoiYeuCau' 
								value="${ttdkKhaiSinh.soCCNguoiYeuCau}" 
								class='form-control' />
							</td>
						</tr>
						<tr>
							<td>Quan hệ với người được khai sinh</td>
							<td><input type=text 
								id='quanHeVoiNguoiKS' 
								name='quanHeVoiNguoiKS' 
								value="${ttdkKhaiSinh.quanHeVoiNguoiKS}"
								class='form-control' />
							</td>
						</tr>
						<tr>
							<td>Họ, chữ đêm, tên người yêu cầu</td>
							<td><input type=text 
								id='hoTenNguoiYeuCau' 
								name='hoTenNguoiYeuCau' 
								value="${cccdNguoiYeuCau.hoTen}"
								class='form-control' disabled />
							</td>
						</tr>
						<tr>
							<td>Nơi thường trú</td>
							<c:if test="${cccdNguoiYeuCau.thuongTru.tenXa != null}">
								<td><input type=text 
									id='thuongTruNguoiYeuCau' 
									value="${cccdNguoiYeuCau.thuongTru.tenXa}, ${cccdNguoiYeuCau.thuongTru.huyen.tenHuyen}, ${cccdNguoiYeuCau.thuongTru.huyen.tinh.tenTinh}" 
									name='thuongTruNguoiYeuCau' 
									class='form-control' disabled/>
								</td>
							</c:if>
							<c:if test="${cccdNguoiYeuCau.thuongTru.tenXa == null}">
								<td><input type=text 
									id='thuongTruNguoiYeuCau' 
									value="" 
									name='thuongTruNguoiYeuCau' class='form-control' disabled/>
								</td>
							</c:if>
						</tr>
						<tr>
							<td>Nơi cấp</td>
							<td><input type=text 
								id='noiCapCCNguoiYeuCau' 
								name='noiCapCCNguoiYeuCau' 
								value="Công an ${cccdNguoiYeuCau.noiCap.tenTinh}"
								class='form-control' disabled />
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="col-md-6 mt20">
				<div class="row mt3">
					<table border="1">
						<tr><th colspan="2">THÔNG TIN CHA</th></tr>
						<tr>
						<tr>
							<td>Số căn cước cha</td>
							<td style='width: 75%;'><input type=text 
								onkeypress='chenGachNgang(this.id, this.value);' 
								oninput='kiemTraDoDai(this.id, this.value);' 
								onblur='getThongTinCCCD(this.id, this.value);' 
								onkeyup = 'kiemTraSo(this.id, this.value);' 
								id='soCCCha' 
								name='soCCCha' 
								value="${ttdkKhaiSinh.soCCCha}"
								class='form-control' />
							</td>
						</tr>
						<tr>
							<td>Họ, chữ đêm, tên cha</td>
							<td><input type=text 
													id='hoTenCha'
													name='hoTenCha' 
													value="${cccdCha.hoTen}"
													class='form-control' disabled />
							</td>
						</tr>
						<tr>
							<td>Năm sinh</td>
							<td><input type=text 
													id='namSinhCha' 
													name='namSinhCha' 
													value="${cccdCha.ngaySinh}"
													class='form-control' disabled />
							</td>
						</tr>
						<tr>
							<td>Dân tộc</td>
							<td><input type=text 
													id='danTocCha' 
													name='danTocCha' 
													value="${cccdCha.danToc.tenDT}" 
													class='form-control' disabled />
							</td>
						</tr>
						<tr>
							<td>Quốc tịch</td>
							<td><input type=text 
								id='quocTichCha' 
								name='quocTichCha' 
								value="${cccdCha.quocTich}" 
								class='form-control' disabled />
							</td>
						</tr>
						<tr>
							<td>Nơi thường trú</td>
							<c:if test="${cccdCha.thuongTru.tenXa != null}">
								<td><input type=text 
									id='thuongTruCha' 
									value="${cccdCha.thuongTru.tenXa},${cccdCha.thuongTru.huyen.tenHuyen},${cccdCha.thuongTru.huyen.tinh.tenTinh}"
									name='thuongTruCha' class='form-control' disabled />
								</td>
							</c:if>
							<c:if test="${cccdCha.thuongTru.tenXa == null}">
								<td><input type=text 
									id='thuongTruCha' 
									value="" 
									name='thuongTruCha' class='form-control' disabled />
								</td>
							</c:if>
						</tr>
						<tr>
							<td>Nơi cấp</td>
							<c:if test="${cccdCha.noiCap.tenTinh != null}">
								<td colspan="3"><input type=text 
									id='noiCapCCCha' 
									name='noiCapCCCha' 
									value="Công an ${cccdCha.noiCap.tenTinh}" 
									class='form-control' disabled />
								</td>
							</c:if>
							<c:if test="${cccdCha.noiCap.tenTinh == null}">
								<td><input type=text 
									id='noiCapCCCha' 
									name='noiCapCCCha' 
									value="" 
									class='form-control' disabled />
								</td>
							</c:if>
						</tr>
					</table>
				</div>
				<div class="row mt20">
					<table border="1">
						<tr><th colspan="2">THÔNG TIN MẸ</th></tr>
						<tr>
						<tr>
							<td>Số căn cước mẹ</td>
							<td style='width: 75%;'><input type=text 
								onkeypress='chenGachNgang(this.id, this.value);' 
								oninput='kiemTraDoDai(this.id, this.value);' 
								onblur='getThongTinCCCD(this.id, this.value);' 
								onkeyup = 'kiemTraSo(this.id, this.value);' 
								id='soCCMe' 
								name='soCCMe' 
								value="${ttdkKhaiSinh.soCCMe}" 
								class='form-control' />
							</td>
						</tr>
						<tr>
							<td>Họ, chữ đêm, tên mẹ</td>
							<td><input type=text 
								id="hoTenMe" 
								name='hoTenMe' 
								value="${cccdMe.hoTen }"
								class='form-control' disabled />
							</td>
						</tr>
						<tr>
							<td>Năm sinh</td>
							<td><input type=text 
								id='namSinhMe' 
								name='namSinhMe' 
								value="${cccdMe.ngaySinh}" 
								class='form-control' disabled />
							</td>
						</tr>
						<tr>
							<td>Dân tộc</td>
							<td><input type=text 
								id='danTocMe' 
								name='danTocMe' 
								value="${cccdMe.danToc.tenDT }" 
								class='form-control' disabled />
							</td>
						</tr>
						<tr>
							<td>Quốc tịch</td>
							<td colspan="3"><input type=text 
								id='quocTichMe' 
								name='quocTichMe' 
								value="${cccdMe.quocTich }"
								class='form-control' disabled />
							</td>
						</tr>
						<tr>
							<td>Nơi thường trú</td>
							<c:if test="${cccdMe.thuongTru.tenXa != null}">
								<td><input type=text 
									id="thuongTruMe" 
									name='thuongTruMe' 
									value="${cccdMe.thuongTru.tenXa}, ${cccdMe.thuongTru.huyen.tenHuyen}, ${cccdMe.thuongTru.huyen.tinh.tenTinh}"
									class='form-control' disabled />
								</td>
							</c:if>
							<c:if test="${cccdMe.thuongTru.tenXa == null}">
								<td><input type=text 
									id="thuongTruMe" 
									name='thuongTruMe' 
									value="" 
									class='form-control' disabled />
								</td>
							</c:if>
						</tr>
						<tr>
							<td>Nơi cấp</td>
							<c:if test="${cccdMe.noiCap.tenTinh != null}">
								<td><input type=text 
									id="noiCapCCMe" 
									name='noiCapCCMe' 
									value="Công an ${cccdMe.noiCap.tenTinh}"
									class='form-control' disabled />
								</td>
							</c:if>
							<c:if test="${cccdMe.noiCap.tenTinh == null}">
								<td><input type=text 
									id="noiCapCCMe" 
									name='noiCapCCMe' 
									value="" 
									class='form-control' disabled />
								</td>
							</c:if>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="row  mt20">
		<div class="col-md-3"></div>	
		<div class="col-md-9 text-center" style="margin-bottom: 20px;">
			<button type='submit' style="width: 140px" id='luu' disabled class='btn btn-primary'>CẬP NHẬT</button>
			<button type='button' style="width: 140px"
						class='btn btn-primary' onclick="dongTrang()">
				Đóng
			</button>
		</div>
	</div>
	<input type="hidden" id='soKS' name='soKS' value="${ttdkKhaiSinh.soKS}"/>
</div>
</form>
</sec:authorize>
<div class="container">
	<div class="row">
		<div class="container">
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
</div>
</div>
<script>
$("input, select").click(function (e){
	$("#luu").attr("change-value","1");
	$("#luu").removeAttr("disabled");
	
})
$("a").click(function (e){
	if($("#luu").attr("change-value") == '1'){
		if(!confirm("Bạn chưa lưu các thay đổi, bạn có muốn tiếp tục rời trang không?")){
			return false;
		}
	}
});
function dongTrang(){
	if($("#luu").attr("change-value") == '1'){
		if(!confirm("Bạn chưa lưu các thay đổi, bạn có muốn tiếp tục rời trang không?")){
			return false;
		}
	}
	window.close();
}
</script>
</body>
</html>