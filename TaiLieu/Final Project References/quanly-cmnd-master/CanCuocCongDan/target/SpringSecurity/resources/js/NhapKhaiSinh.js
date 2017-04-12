function kiemTraDoDai(id, vlue){
	if(vlue.length > 15) {
		var new_vlue = vlue.slice(0,15);
		$("#"+id).val(new_vlue);
	}
}
function chenGachNgang(id, vlue){
	var new_value = vlue;
	if(vlue.length == 3){
		new_value += "-";
	}
	if(vlue.length == 7){
		new_value += "-";
	}
	if(vlue.length == 11){
		new_value += "-";
	}
	$("#"+id).val(new_value);
}

function getHuyen(id_tinh, val){
	var tinh = "tinh="+val;
	var id_huyen = id_tinh.replace("Tinh", "Huyen");
	$.ajax({url: 'get-huyen',
		type: 'GET',
		data: tinh,
	  	success: function(result){
	  		$("#"+id_huyen).html(result);
        }});
}

function getXa(id_huyen, val){
	var huyen = "huyen="+val;
	var id_xa = id_huyen.replace("Huyen", "Xa");
	$.ajax({url: 'get-xa',
		type: 'GET',
		data:huyen,
	  	success: function(result){
	  		$("#"+id_xa).html(result);
        }});
}

function replaceAll(str, find, replace) {
	return str.replace(new RegExp(find, 'g'), replace);
}

function checkNull(val) {
	return (val == null || val.length < 1 || val == 0 || "Không tìm thấy" == val);
}

function onSubmitCheckNull() {
	
	var hoTen = document.getElementById("hoTen").value;
	var ngaySinh = document.getElementById("ngaySinh").value;
	var noiSinhTinh = document.getElementById("noiSinhTinh").value;
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
	var noiCap = document.getElementById("noiCap").value;
	var ngayCap = document.getElementById("ngayCap").value;
	
	
	
	if(checkNull(hoTen)) {
		alert("Vui lòng nhập đầy đủ họ tên.");
		$("#hoTen").focus(); 
		return false;
	} else if(checkNull(ngaySinh)) {
		alert("Vui lòng chọn ngày tháng năm sinh.");
		$("#ngaySinh").focus(); 
		return false;
	} else if(checkNull(noiSinhTinh)) {
		alert("Vui lòng chọn tinh/huyện/xã nơi sinh");
		$("#noiSinhTinh").focus(); 
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
	} else if(checkNull(soCCNguoiYeuCau)) {
		alert("Vui lòng nhập số căn cước người yêu cầu");
		$("#soCCNguoiYeuCau").focus(); 
		return false;
	} else if(checkNull(quanHeVoiNguoiKS)) {
		alert("Vui lòng nhập quan hệ với người được khai sinh");
		$("#quanHeVoiNguoiKS").focus(); 
		return false;
	} else if(checkNull(soCCCha)) {
		alert("Vui lòng nhập số căn cước cha");
		$("#soCCCha").focus(); 
		return false;
	} else if(checkNull(soCCMe)) {
		alert("Vui lòng nhập số căn cước mẹ");
		$("#soCCMe").focus(); 
		return false;
	} else if(checkNull(attrHoTenNguoiYeuCau)) {
		alert("Số căn cước người yêu cầu chưa có trong hệ thống");
		return false;
	} else if(checkNull(attrHoTenNguoiYeuCau)) {
		alert("Số căn cước cha chưa có trong hệ thống");
		return false;
	} else if(checkNull(attrHoTenNguoiYeuCau)) {
		alert("Số căn cước mẹ chưa có trong hệ thống");
		return false;
	} else if(checkNull(ngayCap)) {
		alert("Vui lòng chọn ngày cấp");
		$("#ngayCap").focus(); 
		return false;
	} else if(checkNull(noiCap)) {
		alert("Vui lòng chọn nơi cấp");
		$("#noiCap").focus(); 
		return false;
	} else if(checkNull(soCCNguoiDuyet)) {
		alert("Số căn cước người duyệt chưa có trong hệ thống");
		return false;
	}
}

function getThongTinCCCD(id, val) {
	var soCC = replaceAll(val, "-", "");
	var hoTen = id.replace("soCC", "hoTen");
	var thuongTru = id.replace("soCC",  "thuongTru");
	var noiCap = id.replace("so", "noiCap");
	var namSinh = id.replace("soCC", "namSinh");
	var danToc = id.replace("soCC", "danToc");
	var quocTich = id.replace("soCC", "quocTich");
	var kq;
	var kqMacDinh = "Không tìm thấy";
	soCC = "soCC=" + soCC;
	
	$.ajax({url: 'get-thong-tin-cccd',
		type: 'GET',
		data: soCC,
	  	success: function(result){
	  		if(result == "") {
	  			$("#"+hoTen).attr("aria-value","0");
	  			$("#"+hoTen).val(kqMacDinh);
	  			$("#"+thuongTru).attr("aria-value","0");
	  			$("#"+thuongTru).val(kqMacDinh);
	  			$("#"+noiCap).attr("aria-value","0");
	  			$("#"+noiCap).val(kqMacDinh);
	  			$("#"+namSinh).attr("aria-value","0");
	  			$("#"+namSinh).val(kqMacDinh);
	  			$("#"+danToc).attr("aria-value","0");
	  			$("#"+danToc).val(kqMacDinh);
	  			$("#"+quocTich).attr("aria-value","0");
	  			$("#"+quocTich).val(kqMacDinh);
	  		} else {
	  			kq = result.split("@@");
	  			$("#"+hoTen).attr("aria-value","1");
	  			$("#"+hoTen).val(kq[0]);
	  			//kq[1] thuong tru xa, kq[2] thuong tru huyen, kq[3] thuong tru tinh
	  			$("#"+thuongTru).attr("aria-value","1");
	  			$("#"+thuongTru).val(kq[1] + ", " + kq[2] + ", " + kq[3]);
	  			//kq[4] noi cap CCCD cha
	  			$("#"+noiCap).attr("aria-value","1");
	  			$("#"+noiCap).val("Công an " + kq[4]);
	  			//kq[5] nam sinh, kq[6] thang sinh, kq[7] ngay sinh
	  			$("#"+namSinh).attr("aria-value","1");
	  			$("#"+namSinh).val(kq[5]);
	  			//kq[6] dan toc
	  			$("#"+danToc).attr("aria-value","1");
	  			$("#"+danToc).val(kq[6]);
	  			//kq[7] quoc tich
	  			$("#"+quocTich).attr("aria-value","1");
	  			$("#"+quocTich).val(kq[7]);
	  		}
	  	}
	});
}
