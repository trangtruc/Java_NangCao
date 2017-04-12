function onSubmitCheckNull() {
	
	var hoTen = document.getElementById("hoTen").value;
	var ngaySinh = document.getElementById("ngaySinh").value;
	var quocTich = document.getElementById("quocTich").value;
	var noiSinhTinh = document.getElementById("noiSinhTinh").value;
	var noiSinhHuyen = document.getElementById("noiSinhHuyen").value;
	var noiSinhXa = document.getElementById("noiSinhXa").value;
	var queQuanTinh = document.getElementById("queQuanTinh").value;
	var queQuanHuyen = document.getElementById("queQuanHuyen").value;
	var queQuanXa = document.getElementById("queQuanXa").value;
	var soCCNguoiYeuCau = document.getElementById("soCCNguoiYeuCau").value;
	var soCCCha = document.getElementById("soCCCha").value;
	var soCCMe = document.getElementById("soCCMe").value;
	var quanHeVoiNguoiKS = document.getElementById("quanHeVoiNguoiKS").value;
	var hoTenNguoiYeuCau = document.getElementById("hoTenNguoiYeuCau").value;
	var hoTenCha = document.getElementById("hoTenCha").value;
	var hoTenMe = document.getElementById("hoTenMe").value;

	//Neu soKS khong bang 12 hoac null
	if(checkNull(hoTen)) {
		alert("Vui lòng nhập đầy đủ họ tên.");
		$("#hoTen").focus(); 
		return false;
	} else if(checkNull(ngaySinh)) {
		alert("Vui lòng chọn ngày tháng năm sinh.");
		$("#ngaySinh").focus(); 
		return false;
	} else if (checkNull(quocTich)){
		alert("Vui lòng chọn nhập quốc tịch.");
		$("#quocTich").focus(); 
		return false;
	} else if(checkNull(noiSinhTinh)) {
		alert("Vui lòng chọn tỉnh/huyện/xã nơi sinh");
		$("#noiSinhTinh").focus(); 
		return false;
	} else if(checkNull(noiSinhHuyen)) {
		alert("Vui lòng chọn tỉnh/huyện/xã nơi sinh");
		$("#noiSinhHuyen").focus(); 
		return false;
	} else if(checkNull(noiSinhXa)) {
		alert("Vui lòng chọn tỉnh/huyện/xã nơi sinh");
		$("#noiSinhXa").focus(); 
		return false;
	} else if(checkNull(queQuanTinh)) {
		alert("Vui lòng chọn quê quán");
		$("#quenQuanTinh").focus(); 
		return false;
	} else if(checkNull(queQuanHuyen)) {
		alert("Vui lòng chọn quê quán");
		$("#quenQuanHuyen").focus(); 
		return false;
	} else if(checkNull(queQuanXa)) {
		alert("Vui lòng chọn quê quán");
		$("#quenQuanXa").focus(); 
		return false;
	} else if((!ktDoDai(soCCNguoiYeuCau)) || checkNull(soCCNguoiYeuCau)) {
		alert("Vui lòng nhập số căn cước người yêu cầu có 9 hoặc 12 số");
		$("#soCCNguoiYeuCau").focus(); 
		return false;
	} else if(checkNull(quanHeVoiNguoiKS)) {
		alert("Vui lòng nhập quan hệ với người được khai sinh");
		$("#quanHeVoiNguoiKS").focus(); 
		return false;
	} else if(checkNull(hoTenNguoiYeuCau)) {
		alert("Số căn cước người yêu cầu chưa có trong hệ thống");
		return false;
	} else if(!checkNull(soCCCha)) {
		if (!ktDoDai(soCCCha)) {
			alert("Số căn cước cha phải có độ dài 9 hoặc 12 số");
			$("#soCCCha").focus(); 
			return false;
		} else if (checkNull(hoTenCha)) {
			alert("Số căn cước cha chưa có trên hệ thống");
			$("#soCCCha").focus();
			return false;
		}
	}
	if(!checkNull(soCCMe)) {
		if (checkNull(hoTenMe)) {
			alert("Số căn cước mẹ chưa có trên hệ thống");
			$("#soCCMe").focus();
			return false;
		}
	}
	if (!ktNgaySinh(ngaySinh)) {
		alert("Ngày sinh không được lớn hơn ngày hiện tại");
		$("#ngaySinh").focus(); 
		return false;
	}
}
