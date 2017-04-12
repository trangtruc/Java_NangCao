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
<style>
	.soDK {
	    position: absolute;
	    top: 82px;
	    right: 250px;
	    font-size: 18px;
	}
	.hoTenChong {
	    position: absolute;
	    top: 270px;
	    left: 300px;
	    font-size: 18px;
	}
	.ngaySinhChong {
	    position: absolute;
	    top: 300px;
	    left: 340px;
	    font-size: 18px;
	}
	.danTocChong {
	    position: absolute;
	    top: 328px;
	    left: 250px;
	    font-size: 18px;
	}
	.quocTichChong {
	    position: absolute;
	    top: 328px;
	    left: 430px;
	    font-size: 18px;
	}
	.thuongTruChong {
	    position: absolute;
	    top: 388px;
	    left: 170px;
	    font-size: 15px;
	}
	.soCCChong {
	    position: absolute;
	    top: 418px;
	    left: 380px;
	    font-size: 18px;
	}
	
	.hoTenVo {
	    position: absolute;
	    top: 270px;
	    right: 230px;
	    font-size: 18px;
	}
	.ngaySinhVo {
	    position: absolute;
	    top: 300px;
	    right: 270px;
	    font-size: 18px;
	}
	.danTocVo {
	    position: absolute;
	    top: 328px;
	    right: 400px;
	    font-size: 18px;
	}
	.quocTichVo {
	    position: absolute;
	    top: 328px;
	    right: 200px;
	    font-size: 18px;
	}
	.thuongTruVo {
	    position: absolute;
	    top: 388px;
	    right: 129px;
	    font-size: 15px;
	}
	.soCCVo {
	    position: absolute;
	    top: 418px;
	    right: 220px;
	    font-size: 18px;
	}
</style>
</head>
<body>
<sec:authorize access="!hasRole('XEM_HON_NHAN')">
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
<sec:authorize access="hasRole('XEM_HON_NHAN')">
<c:if test="${error != null }">
	<div class="error">${error }</div>
</c:if>
<c:if test="${error == null }">
	<div class="container">
		<input type=hidden id=soCC value="${cccd.soCC }" />
		<div class="col-md-12 text-center">
			<img alt="" src="resources/image/giay_chung_nhan_ket_hon.jpg">
			<div class="soDK"><b>${honNhan.soDK }</b></div>
			<div class="hoTenChong"><b>${cccdChong.hoTen }</b></div>
			<div class="ngaySinhChong"><b>${cccdChong.ngaySinh }</b></div>
			<div class="danTocChong"><b>${dtChong.tenDT }</b></div>
			<div class="quocTichChong"><b>${cccdChong.quocTich }</b></div>
			<div class="thuongTruChong"><b>${thuongTruChong.tenXa } - ${thuongTruChong.huyen.tenHuyen } - ${thuongTruChong.huyen.tinh.tenTinh }</b></div>
			<div class="soCCChong"><b>${cccdChong.soCC }</b></div>
		
			<div class="hoTenVo"><b>${cccdVo.hoTen }</b></div>
			<div class="ngaySinhVo"><b>${cccdVo.ngaySinh }</b></div>
			<div class="danTocVo"><b>${dtVo.tenDT }</b></div>
			<div class="quocTichVo"><b>${cccdVo.quocTich }</b></div>
			<div class="thuongTruVo"><b>${thuongTruVo.tenXa } - ${thuongTruVo.huyen.tenHuyen } - ${thuongTruVo.huyen.tinh.tenTinh }</b></div>
			<div class="soCCVo"><b>${cccdVo.soCC }</b></div>
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
	function inTrang() {
		inPage();
	}
</script>
</body>
</html>