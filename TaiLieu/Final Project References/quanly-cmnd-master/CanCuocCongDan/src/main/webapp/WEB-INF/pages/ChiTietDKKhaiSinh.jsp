<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5.01 Transitional//EN" "http://www.w3.org/TR/html5/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<style>
	a {
		color: blue;
	}
</style>
<c:if test="${ssTaiKhoan == null}">
	<script>
		window.location = "login";
	</script>
</c:if>
<sec:authorize access="!hasAnyRole('DUYET_KHAI_SINH_1', 'DUYET_KHAI_SINH_2')">
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
<script>
		var kqDuyetDKKS = <%= session.getAttribute("kqDuyetDKKS")%>;
		<% session.removeAttribute("kqDuyetDKKS"); %>
		if (kqDuyetDKKS) {
			alert("Duyệt thành công");
		} else if (kqDuyetDKKS == false) {
			alert("Duyệt chưa thành công.");
		}
</script>
</head>
<body>
<sec:authorize access="hasAnyRole('DUYET_KHAI_SINH_1', 'DUYET_KHAI_SINH_2')">
<form   action="duyet-dk-khai-sinh?${_csrf.parameterName}=${_csrf.token}"
		enctype="multipart/form-data">
<div class="container" style="background-color: white; width: 650px; margin-top: 50px;">
	<div class="row text-center mt40">
		<label>CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM</label>
	</div>
	<div class="row text-center">
		<label>Độc lập - Tự do - Hạnh Phúc</label>
		<p class="title">**********</p>
	</div>
	<div class="row text-center">
		<h4><label>TỜ KHAI ĐĂNG KÝ KHAI SINH</label></h4>
	</div>
	<div class="row mt50">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-10 pull-left">
				<div class="row">
					<div class="col-md-2">Kính gửi:</div>
					<div class="col-md-10">
						UBND ${noiDKLV.tenXa}-${noiDKLV.huyen.tenHuyen}-${noiDKLV.huyen.tinh.tenTinh}
					</div>
				</div>
				<div class="row mt20">
					<label class="col-md-6">Họ, chữ đệm, tên người yêu cầu: </label>
					<label class="col-md-6">${cccdNguoiYeuCau.hoTen}</label>
				</div>
				<div class="row">
					<div class="col-md-6">Số căn cước người yêu cầu: </div>
					<span class="col-md-6" id='linkNguoiYeuCau'>
						<a href="xem-thong-tin-cccd?soCC=${ttdkKhaiSinh.soCCNguoiYeuCau}" target="_blank">
							${ttdkKhaiSinh.soCCNguoiYeuCau}
						</a>
						<input type='hidden' value='${ttdkKhaiSinh.soCCNguoiYeuCau}' id='soCCNguoiYeuCau'/>
					</span>
				</div>
				<div class="row">
					<div class="col-md-3">Nơi cư trú: </div>
					<div class="col-md-9">${queQuanNguoiYeuCau.tenXa}-${queQuanNguoiYeuCau.huyen.tenHuyen}-${queQuanNguoiYeuCau.huyen.tinh.tenTinh}</div>
				</div>
				<div class="row">
					<div class="col-md-6">Quan hệ với người được khai sinh: </div>
					<div class="col-md-6">${ttdkKhaiSinh.quanHeVoiNguoiKS}</div>
				</div>
				<div class="row mt20">
					<label class="col-md-10">
					Đề nghị cơ quan ${ttdkKhaiSinh.yeuCau.tenYeuCau} khai sinh cho  người dưới đây:
					</label>
				</div>
				<div class="row">
					<label class="col-md-6">Họ, chữ đệm, tên: </label>
					<label class="col-md-6">${ttdkKhaiSinh.hoTen}</label>
				</div>
				<div class="row">
					<div class="col-md-3">Ngày sinh: </div>
					<span class="col-md-3">${ttdkKhaiSinh.ngaySinh}</span>
					<span class="col-md-3">ghi bằng chữ: </span>
				</div>
				<div class="row">
					<div class="col-md-12">${ngaySinhChu}</div>
				</div>
				<div class="row">
					<div class="col-md-3">Nơi sinh: </div>
					<span class="col-md-9">
							${noiSinh.tenXa}, ${noiSinh.huyen.tenHuyen}, ${noiSinh.huyen.tinh.tenTinh}
					</span>
				</div>
				<div class="row">
					<div class="col-md-3">Giới tính: </div>
					<c:if test="${ttdkKhaiSinh.gioiTinh != 'Nam' }">
						<span class="col-md-3">Nữ</span>
					</c:if>
					<c:if test="${ttdkKhaiSinh.gioiTinh == 'Nam' }">
						<span class="col-md-3">Nam</span>
					</c:if>
					<div class="col-md-3">Dân tộc: </div>
					<span class="col-md-3">${ttdkKhaiSinh.danToc.tenDT}</span>
				</div>
				<div class="row">
					<div class="col-md-3">Quốc tịch: </div>
					<span class="col-md-5">${ttdkKhaiSinh.quocTich}</span>
				</div>
				<div class="row">
					<div class="col-md-3">Nơi cư trú: </div>
					<span class="col-md-9">
						${queQuan.tenXa}, ${queQuan.huyen.tenHuyen}, ${queQuan.huyen.tinh.tenTinh}
					</span>
				</div>
				<div class="row mt20">
					<label class="col-md-6">Họ, chữ đệm, tên cha: </label>
					<c:if test="${cccdCha.hoTen == null}">
						<label class="col-md-6">Không có</label>
					</c:if>
					<c:if test="${cccdCha.hoTen != null}">
						<label class="col-md-6">${cccdCha.hoTen}</label>
					</c:if>
				</div>
				<div class="row">
					<label class="col-md-5">Số căn cước cha: </label>
					<span class="col-md-5" id='linkCha'>
						<a href="xem-thong-tin-cccd?soCC=${ttdkKhaiSinh.soCCCha}" target="_blank">
							${ttdkKhaiSinh.soCCCha}
						</a>
						<input type='hidden' value='${ttdkKhaiSinh.soCCCha}' id='soCCCha'/>
					</span>
				</div>
				<div class="row">
					<span class="col-md-3">${cccdCha.ngaySinh}</span>
					<div class="col-md-3">Dân tộc: </div>
					<span class="col-md-3">${dtCha.tenDT}</span>
				</div>
				<div class="row">
					<div class="col-md-3">Quốc tịch: </div>
					<span class="col-md-5">${cccdCha.quocTich}</span>
				</div>
				
				<div class="row mt20">
					<label class="col-md-6">Họ, chữ đệm, tên mẹ: </label>
					<c:if test="${cccdMe.hoTen == null}">
						<label class="col-md-6">Không có</label>
					</c:if>
					<c:if test="${cccdMe.hoTen != null}">
						<label class="col-md-6">${cccdMe.hoTen}</label>
					</c:if>
				</div>
				<div class="row">
					<label class="col-md-5">Số căn cước mẹ: </label>
					<span class="col-md-5" id='linkMe'>
						<a href="xem-thong-tin-cccd?soCC=${ttdkKhaiSinh.soCCMe}" target="_blank">
							${ttdkKhaiSinh.soCCMe}
						</a>
						<input type='hidden' value='${ttdkKhaiSinh.soCCMe}' id='soCCMe'/>
					</span>
				</div>
				<div class="row">
					<div class="col-md-3">Năm sinh: </div>
					<span class="col-md-3">${cccdMe.ngaySinh}</span>
					<div class="col-md-3">Dân tộc: </div>
					<span class="col-md-3">${dtMe.tenDT}</span>
				</div>
				<div class="row">
					<div class="col-md-3">Quốc tịch: </div>
					<span class="col-md-5">${cccdMe.quocTich}</span>
				</div>
				<div class="row mt20">
					<label class="col-md-12">Thông tin bổ xung</label>
				</div>
				<c:if test="${ttdkKhaiSinh.soKSCu != null}">
					<div class="row">
						<label class="col-md-5">Số khai sinh (cũ): </label>
						<span class="col-md-5 ml20">${ttdkKhaiSinh.soKSCu}</span>
					</div>
				</c:if>
				<div class="row">
					<input type='hidden' name='soKS' id='soKS' value='${ttdkKhaiSinh.soKS}'/>
				</div>
				<c:if test="${!(ttdkKhaiSinh.benhVien == null)}">
				<div class="row">
					<label class="col-md-5">Bệnh viện nơi sinh: </label>
					<span class="col-md-5 ml20">${ttdkKhaiSinh.benhVien}</span>
				</div>
				</c:if>
				<div class="row">
					<label class="col-md-5">Ngày đăng ký: </label>
					<span class="col-md-5 ml20">${ttdkKhaiSinh.ngayDangKy}</span>
				</div>
				<div class="row">
					<label class="col-md-5">Ngày hẹn làm việc: </label>
					<span class="col-md-5 ml20">${ttdkKhaiSinh.ngayHenLamViec}</span>
				</div>
				<div class="row">
					<div class="col-md-12">
						Tôi cam đoan nội dung đề nghị đăng ký khai sinh trên đây là đúng sự thật,
						sự thỏa đáng nhất trí của các bên liên quan theo quy định pháp luật.
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						Tôi chịu hoàn toàn trách nhiệm trước pháp luật về nội dung cam đoan của mình.
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 text-right mt20">
						Làm tại:..........., ngày.....tháng.....năm.....  .
					</div>
				</div>
				<div class="row">
					<div class="col-md-12" style="margin-left:370px">
						<label>Người yêu cầu</label>
					</div>
				</div>
				<div id='button' class="row mt20 text-center" style="margin-bottom: 20px;">
						<sec:authorize access="hasAnyRole('DUYET_KHAI_SINH_1')"> 
						<c:if test="${(ttdkKhaiSinh.trangThai == 0) || (ttdkKhaiSinh.trangThai == 3) || (ttdkKhaiSinh.trangThai == 4)}">
							<button type=submit style="width:120px"
									class='btn btn-primary'>
								Xác nhận
							</button>
						</c:if>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('DUYET_KHAI_SINH_2')"> 
						<c:if test="${ttdkKhaiSinh.trangThai == 1 }">
							<button type=submit style="width:120px"
									class='btn btn-primary'>
								Duyệt
							</button>
						</c:if>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('DUYET_KHAI_SINH_1', 'DUYET_KHAI_SINH_2')"> 
						<c:if test="${((ttdkKhaiSinh.trangThai == 1) || (ttdkKhaiSinh.trangThai == 2))}">
							<button type=button style="width:120px" onclick="huyXacNhan();" 
									class='btn btn-primary'>
								Hủy xác nhận
							</button>
						</c:if>
						</sec:authorize>
						<button type=button style="width:120px"
								class='btn btn-primary' onclick="window.close();">
							Đóng
						</button>					
						<button type=button style="width:120px"
								class='btn btn-primary' onclick="inTrang();">
							In
						</button>
					
				</div>
			</div>
		</div>
	</div>
</div>
<input type='hidden' name='yeuCau' id='yeuCau' value='${ttdkKhaiSinh.yeuCau.maYeuCau}'/>
</form>
</sec:authorize>
<script>
	function inTrang() {
		$("#linkNguoiYeuCau").html($("#soCCNguoiYeuCau").val());
		$("#linkCha").html($("#soCCCha").val());
		$("#linkMe").html($("#soCCMe").val());
		inPage();
	}
</script>
<script>
	function huyXacNhan(){
		var soKS = $("#soKS").val();
		alert(soKS);
		window.location = "huy-xac-nhan-ttdk-khai-sinh?soKS="+soKS;
	}
</script>
</body>
</html>