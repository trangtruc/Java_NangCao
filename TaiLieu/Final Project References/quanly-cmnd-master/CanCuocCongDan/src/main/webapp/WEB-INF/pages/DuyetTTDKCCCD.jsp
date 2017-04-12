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
</head>
<body class="bg-white">
<c:if test="${ssTaiKhoan == null}">
	<script>
		window.location = "login";
	</script>
</c:if>
<sec:authorize access="!hasRole('DUYET_TTDK_CCCD')">
	<div class="container mt20" style="min-height:360px">
		<div class="mt80">
			<div class="text-center col-md-12">
				<div class="text-red title">
						LỖI: BẠN KHÔNG CÓ QUYỀN HẠN ĐỂ THỰC HIỆN THAO TÁC
				</div>
			</div>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('DUYET_TTDK_CCCD')">
<div class="container">
	<jsp:include page="ChiTietTTDKCCCD.jsp"></jsp:include>
	<div class="row mt20" id="button">
		<div class="col-md-12 text-center">
			<button type=button onclick="goBack()" class='btn btn-primary'>Quay lại</button>
			<button type=button onclick='duyet(${maSo});' class='btn btn-primary'>Phê Duyệt</button>
			<button type=button onclick='moThongBao(${maSo});' class='btn btn-primary'>Từ chối</button>
			<button type=button onclick='go(${maSo})' class='btn btn-primary'>Tạo bản in</button>
		</div>
	</div>
	<div class="row mt20">
	</div>
</div>
<script>
function duyet(maSo){
	if(!confirm("Bạn có chắc chắn thực hiện điều này?")){
		return false;
	}
	var duyet = "maSo="+maSo;
	$.ajax({url: 'duyet-don',
		type: 'GET',
		data: duyet,
	  	success: function(result){
	  		window.location = "don-dang-ky-cccd-chua-duyet";
    }});
}

function khongDuyet(){
	$("#error").html("");
	if($("#txtLyDo").val() < 1){
		$("#error").html("Chưa nhập lý do");
		return false;
	}
	if(!confirm("Bạn có chắc chắn thực hiện điều này?")){
		return false;
	}
	var lyDo = $("#txtLyDo").val();
	var maSo = $("#thongBao").attr("aria-value");
	
	// Chỗ cần tham khảo
	var duyet = "maSo="+maSo+"&lyDo="+lyDo;
	$.ajax({url: 'khong-duyet-don',
		type: 'GET',
		data: duyet,
	  	success: function(result){
	  		window.location = "don-dang-ky-cccd-chua-duyet";
        }});
}
function moThongBao(maSo){
	$("#thongBao").attr("aria-value", maSo);
	$("#thongBao").removeClass("display-none");
	$("#thongBao").addClass("display-block");
	$("#txtLyDo").focus();
}
function dongThongBao(){
	$("#thongBao").removeClass("display-block");
	$("#thongBao").addClass("display-none");
}
</script>
</sec:authorize>
</body>
</html>