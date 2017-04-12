
function onSubmitCheckNull() {
	var soKS = document.getElementById("soKS").value;
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
	var hoTenNguoiDuyet = document.getElementById("hoTenNguoiDuyet").value;
	
	var soCCNguoiDuyet = document.getElementById("soCCNguoiDuyet").value;
	var noiCapTinh = document.getElementById("noiCapTinh").value;
	var noiCapHuyen = document.getElementById("noiCapHuyen").value;
	var noiCapXa = document.getElementById("noiCapXa").value;
	var ngayCap = document.getElementById("ngayCap").value;
	//Neu soKS khong bang 12 hoac null
	if ((!ktDoDai(soKS)) || checkNull(soKS)) {
		alert("Số khai sinh phải có độ dài 12 số.");
		$("#soKS").focus(); 
		return false;
	} else if(checkNull(hoTen)) {
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
		alert("Vui lòng chọn tỉnh nơi sinh");
		$("#noiSinhTinh").focus(); 
		return false;
	} else if(checkNull(noiSinhXa)) {
		alert("Vui lòng chọn huyện nơi sinh");
		$("#noiSinhHuyen").focus(); 
		return false;
	} else if(checkNull(noiSinhXa)) {
		alert("Vui lòng chọn xã nơi sinh");
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
	} else if(checkNull(noiCapTinh)) {
		alert("Vui lòng chọn tỉnh nơi cấp");
		$("#noiCapTinh").focus(); 
		return false;
	} else if(checkNull(noiCapHuyen)) {
		alert("Vui lòng chọn huyện nơi cấp");
		$("#noiCapHuyen").focus(); 
		return false;
	} else if(checkNull(noiCapXa)) {
		alert("Vui lòng chọn xã nơi cấp");
		$("#noiCapXa").focus(); 
		return false;
	} else if(checkNull(ngayCap)) {
		alert("Vui lòng chọn ngày cấp");
		$("#ngayCap").focus(); 
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
	} else if(!checkNull(soCCMe)) {
		if (!ktDoDai(soCCMe)) {
			alert("Số căn cước mẹ phải có độ dài 9 hoặc 12 số");
			$("#soCCMe").focus(); 
			return false;
		} else if (checkNull(hoTenMe)) {
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
	if (!ktNgaySinh(ngayCap)) {
		alert("Ngày cấp không được lớn hơn ngày hiện tại");
		$("#ngayCap").focus(); 
		return false;
	}
	if (!ktNgayCap(ngaySinh, ngayCap)) {
		alert("Ngày cấp không được lớn hơn ngày hiện tại");
		$("#ngayCap").focus(); 
		return false;
	}
}
function ktTonTaiSoKS(soKS) {
	var soKS = $("#"+soKS).val();
	soKS = replaceAll(soKS, "-", "");
	if (ktDoDai(soKS)) {
		soKS = "soKS="+soKS;
		$.ajax({url: 'kt-ton-tai-so-ks',
			type: 'GET',
			data: soKS,
		  	success: function(data) {
		  		if (data == 'true') {
		  			alert("Số khai sinh này đã tồn tài trong hệ thống.");
		  		}
		    }});
	}	
}
function getSoKS() {
	var tuSinhSoKS = document.getElementById("tuSinhSoKS");
	if (tuSinhSoKS.checked) {
		$.ajax({url: 'tu-sinh-so-ks',
			type: 'GET',
		  	success: function(data) {
			  	if (data != "") {
			  		$("#soKS").val(data);
			  	} else {
			  		alert("Đã xãy ra lỗi.");
			  	}
		}});
	} else {
		$("#soKS").val("");
	}
}

//Ngày sinh phải nhỏ hơn ngày cấp
function ktNgayCap(ngaySinhInput, ngayCapInput) {
	//Lay ngay cap
	var ngayCap = new Date(ngayCapInput);
	var nam = ngayCap.getFullYear();
	var thang = (ngayCap.getMonth() + 1);
	var ngay = ngayCap.getDate();
	//Lay ngay sinh 
	var ngaySinh = new Date(ngaySinhInput);
	if (ngaySinh.valueOf()) { // Valid date
		
	    namSinh = ngaySinh.getFullYear();
	    thangSinh = (ngaySinh.getMonth() + 1);
	    ngaySinh = ngaySinh.getDate();
	    if (namSinh < nam) {
	    	return true;
	    } 
	    if (namSinh == nam) {
	    	alert("Bang nam");
	    	if (thangSinh < thang) {
	    		return true;
	    	} 
	    	if (thangSinh == thang) {
	    		alert("bang thang");
	    		if (ngaySinh <= ngay) {
	    			return true;
	    		}
	    	}
	    }
	} 
	return false;
}

