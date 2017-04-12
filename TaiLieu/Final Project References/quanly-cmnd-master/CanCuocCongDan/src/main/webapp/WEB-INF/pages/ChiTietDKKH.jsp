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
<sec:authorize access="!hasAnyRole('DUYET_KET_HON_1', 'DUYET_KET_HON_2')">
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
		var kqDuyet = <%= session.getAttribute("kqDuyet")%>;
		<% session.removeAttribute("kqDuyet"); %>
		if (kqDuyet) {
			alert("Duyệt thành công");
		} else if (kqDuyet == false) {
			alert("Duyệt chưa thành công.");
		}
</script>
</head>
<body>
<sec:authorize access="hasAnyRole('DUYET_KET_HON_1', 'DUYET_KET_HON_2')">
<form   action="duyet-dk-ket-hon?${_csrf.parameterName}=${_csrf.token}"
		enctype="multipart/form-data">
<div class="container" style="border: 1px solid black; background-color: white; width: 650px; margin-top: 50px;">
	<div class="row text-center mt40">
		<label>CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM</label>
	</div>
	<div class="row text-center">
		<label>Độc lập - Tự do - Hạnh Phúc</label>
		<p class="title">**********</p>
	</div>
	<div class="row text-center">
		<h4><label>TỜ KHAI ĐĂNG KÝ KẾT HÔN</label></h4>
	</div>
	<div class="row mt50">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-md-3">Kính gửi UBND:</div>
					<div class="col-md-9">
						${noiDKLV.tenXa}-${noiDKLV.huyen.tenHuyen}-${noiDKLV.huyen.tinh.tenTinh}
					</div>
				</div>
			</div>
		</div>
		<div class="row mt20">
			<div class="col-md-12">
				<table border="1">
					<tr>
						<th>Thông tin</th>
						<th>Bên nữ</th>
						<th>Bên nam</th>
					</tr>
					<tr>
						<td>Họ, chữ đệm, tên</td>
						<td>${cccdVo.hoTen }</td>
						<td>${cccdChong.hoTen }</td>
					</tr>
					<tr>
						<td>Ngày tháng năm sinh</td>
						<td>${cccdVo.ngaySinh }</td>
						<td>${cccdChong.ngaySinh }</td>
					</tr>
					<tr>
						<td>Dân tộc</td>
						<td>${dtVo.tenDT }</td>
						<td>${dtChong.tenDT }</td>
					</tr>
					<tr>
						<td>Quốc tịch</td>
						<td>${cccdVo.quocTich }</td>
						<td>${cccdChong.quocTich }</td>
					</tr>
					<tr>
						<td>Nơi cư trú</td>
						<td>${noiCuTruVo.tenXa }-${noiCuTruVo.huyen.tenHuyen }-${noiCuTruVo.huyen.tinh.tenTinh}</td>
						<td>${noiCuTruChong.tenXa }-${noiCuTruChong.huyen.tenHuyen }-${noiCuTruChong.huyen.tinh.tenTinh}</td>
					</tr>
					<tr>
						<td>Giấy tờ tùy thân</td>
						<td>${cccdVo.soCC}</td>
						<td>${cccdChong.soCC}</td>
					</tr>
				</table>
			</div>
		</div>
		<!-- 		End table -->
		<div class="row mt20">
			<div class="col-md-1"></div>
			<div class="col-md-11">
				Chúng tôi cam đoan những lời khai trên đây là đúng sự thật, việc kết hôn của chúng tôi là tự nguyện, không vi phạm quy định của Luật hôn nhân và gia đình Việt Nam.
			</div>
		</div>
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-11">
				Chúng tôi chịu hoàn toàn trách nhiệm trước pháp luật về cam đoan của mình. 
			</div>
		</div>
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-11">
				Đề nghị Quý cơ quan đăng ký.
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 text-right">
				..........................., ngày ..........…tháng ............ năm............…
			</div>
		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<span class="col-md-4 text-center">
				<label>Bên nữ</label>
			</span>
			<span class="col-md-4 text-center">
				<label>Bên nam</label>
			</span>
		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<span class="col-md-4 text-center">
				(Ký, ghi rõ họ, chữ đệm, tên)
			</span>
			<span class="col-md-4 text-center">
				(Ký, ghi rõ họ, chữ đệm, tên)
			</span>
		</div>
		<div class="row mt40"></div>
		<div class="row">
			<div class="col-md-4"></div>
			<span class="col-md-4 text-center">
				---------------------------
			</span>
			<span class="col-md-4 text-center">
				---------------------------
			</span>
		</div>
		<div class="row mt40">
			<div class="col-md-12 text-center" id='button'>
				<sec:authorize access="hasRole('DUYET_KET_HON_1')"> 
						<c:if test="${(ttdkKetHon.trangThai == 0) || (ttdkKetHon.trangThai == 3) || (ttdkKetHon.trangThai == 4)}">
							<button type=submit style="width:120px"
									class='btn btn-primary'>
								Xác nhận
							</button>
						</c:if>
				</sec:authorize>
				<sec:authorize access="hasRole('DUYET_KET_HON_2')"> 
						<c:if test="${ttdkKetHon.trangThai == 1 }">
							<button type=submit style="width:120px"
									class='btn btn-primary'>
								Duyệt
							</button>
						</c:if>
						<c:if test="${ttdkKetHon.trangThai == 2 }">
							<button type=button style="width:120px" onclick="huyXacNhan();" 
									class='btn btn-primary'>
								Hủy xác nhận
							</button>
						</c:if>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('DUYET_KET_HON_1')"> 
						<c:if test="${(ttdkKetHon.trangThai == 1)}">
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
<input type=hidden id=soDK name=soDK value="${ttdkKetHon.soDK}"/>
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
		var soDK = $("#soDK").val();
		alert(soDK);
		window.location = "huy-xac-nhan-ttdk-ket-hon?soDK="+soDK;
	}
</script>
</body>
</html>