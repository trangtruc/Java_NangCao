<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<style>
.soKS {
    position: absolute;
    top: 43px;
    right: 365px;
    font-size: 10px;
}
.hoTen {
    position: absolute;
    top: 188px;
    left: 470px;
    font-size: 12px;
    color: black;
}
.ngaySinh {
    position: absolute;
    top: 207px;
    left: 500px;
    font-size: 12px;
    color: black;
}
.ngaySinhBangChu {
    position: absolute;
    top: 225px;
    left: 386px;
    font-size: 10px;
    color: black;
}
.gioiTinh {
    position: absolute;
    top: 188px;
    left: 700px;
    font-size: 12px;
    color: black;
}
.danToc {
    position: absolute;
    top: 260px;
    left: 450px;
    font-size: 12px;
    color: black;
}
.quocTich {
    position: absolute;
    top: 260px;
    left: 650px;
    font-size: 12px;
    color: black;
}
.noiSinh {
    position: absolute;
    top: 241px;
    left: 430px;
    font-size: 12px;
    color: black;
}
.hoTenCha {
	position: absolute;
    top: 277px;
    left: 470px;
    font-size: 12px;
    color: black;
}
.khongCha {
	position: absolute;
    top: 277px;
    left: 470px;
    font-size: 12px;
    color: black;
}
.danTocCha {
	position: absolute;
    top: 295px;
    left: 450px;
    font-size: 12px;
    color: black;
}
.quocTichCha {
	position: absolute;
    top: 295px;
    left: 590px;
    font-size: 12px;
    color: black;
}
.namSinhCha {
	position: absolute;
    top: 295px;
    left: 720px;
    font-size: 12px;
    color: black;
}
.thuongTruCha {
	position: absolute;
    top: 330px;
    left: 440px;
    font-size: 12px;
    color: black;
}
.hoTenMe {
	position: absolute;
    top: 347px;
    left: 470px;
    font-size: 12px;
    color: black;
}
.khongMe {
	position: absolute;
    top: 347px;
    left: 470px;
    font-size: 12px;
    color: black;
}
.danTocMe {
	position: absolute;
    top: 366px;
    left: 450px;
    font-size: 12px;
    color: black;
}
.quocTichMe {
	position: absolute;
    top: 366px;
    left: 590px;
    font-size: 12px;
    color: black;
}
.namSinhMe {
	position: absolute;
    top: 366px;
    left: 720px;
    font-size: 12px;
    color: black;
}
.thuongTruMe {
	position: absolute;
    top: 401px;
    left: 440px;
    font-size: 12px;
    color: black;
}
.noiCap {
	position: absolute;
    top: 435px;
    left: 440px;
    font-size: 12px;
    color: black;
}
.hoTenNguoiDK {
	position: absolute;
    top: 507px;
    left: 540px;
    font-size: 12px;
    color: black;
}
.quanHeVoiNguoiKS {
	position: absolute;
    top: 526px;
    left: 540px;
    font-size: 12px;
    color: black;
}
label{
	 font-size: 16px;
}
table[border="1"] tr:hover {
    background: #fafafa;
}
</style>
</head>
<body>
<sec:authorize access="!hasRole('XEM_KHAI_SINH')">
	<div class="container mt20" style="min-height:240px">
		<div class="mt20">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('XEM_KHAI_SINH')">
<c:if test="${error != null }">
	<div class="error">${error }</div>
</c:if>
<c:if test="${error == null }">
	<div class="container">
		<input type=hidden id=soCC value="${cccd.soCC }" />
		<div class="col-md-12 text-center">
			<img alt="" src="resources/image/giay_khai_sinh.jpg">
			<div class="soKS"><b>${khaiSinh.soKS }</b></div>
			<div class="hoTen"><b>${khaiSinh.hoTen }</b></div>
			<div class="ngaySinh"><b>${khaiSinh.ngaySinh }</b></div>
			<div class="ngaySinhBangChu"><b>${ngaySinhBangChu }</b></div>
			<div class="gioiTinh"><b>${khaiSinh.gioiTinh }</b></div>
			<div class="danToc"><b>${khaiSinh.danToc.tenDT }</b></div>
			<div class="noiSinh"><b>${noiSinh.tenXa } - ${noiSinh.huyen.tenHuyen} - ${noiSinh.huyen.tinh.tenTinh}</b></div>
			<div class="quocTich"><b>${khaiSinh.quocTich }</b></div>
			<div class="hoTenCha"><b>${cccdCha.hoTen }</b></div>
			<div class="danTocCha"><b>${danTocCha.tenDT }</b></div>
			<div class="quocTichCha"><b>${cccdCha.quocTich }</b></div>
			<div class="thuongTruCha"><b>${thuongTruCha.tenXa} - ${thuongTruCha.huyen.tenHuyen} - ${thuongTruCha.huyen.tinh.tenTinh}</b></div>
			<div class="namSinhCha"><b>${cccdCha.ngaySinh }</b></div>
			<div class="hoTenMe"><b>${cccdMe.hoTen }</b></div>
			<div class="danTocMe"><b>${danTocMe.tenDT }</b></div>
			<div class="quocTichMe"><b>${cccdMe.quocTich }</b></div>
			<div class="namSinhMe"><b>${cccdMe.ngaySinh }</b></div>
			<div class="thuongTruMe"><b>${thuongTruMe.tenXa} - ${thuongTruMe.huyen.tenHuyen} - ${thuongTruMe.huyen.tinh.tenTinh}</b></div>
			<div class="noiCap"><b>${noiCap.tenXa} - ${noiCap.huyen.tenHuyen} - ${noiCap.huyen.tinh.tenTinh}</b></div>
			<div class="hoTenNguoiDK"><b>${cccdNguoiDK.hoTen}</b></div>
			<div class="quanHeVoiNguoiKS"><b>${khaiSinh.quanHeVoiNguoiKS}</b></div>
			<c:if test="${cccdCha == null }">
				<div class="khongCha"><b>KHÔNG CÓ</b></div>
			</c:if>
			<c:if test="${cccdCha == null }">
				<div class="khongMe"><b>KHÔNG CÓ</b></div>
			</c:if>
		</div>
	</div>
</c:if>
</sec:authorize>
<div class="row text-center mt20">
	<div class="col-md-12" id=button>
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
<script>
	$("#barcode").barcode($("#soKS").val(),"code39");
</script>
<script>
	function inTrang() {
		inPage();
	}
</script>
</body>
</html>