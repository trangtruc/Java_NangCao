/**
 * 
 */
function step(s){
	$("#ketQuaKiemTraSoKS").html("");
	if($("#step").val() == 1){
		y1 = document.getElementById("yeuCau_1");
		y2 = document.getElementById("yeuCau_2");
		y3 = document.getElementById("yeuCau_3");
		if(!y1.checked){
			if(!y2.checked){
				if(!y3.checked){
					$("#yeuCau_1").focus(); 
					alert("Chưa chọn Yêu cầu");
					return false;
				} 
			}  
		}
		if($("#noiDKLVTinh").val() == 0){
			$("#noiDKLVTinh").focus();
			alert("Chưa chọn Ủy Ban muốn làm việc");
			return false;
		}
	}
	if($("#step").val() == 2 && s != -1){
		// mã số khai sinh
		if($("#maSoKhaiSinh").val().length < 15){
			$("#maSoKhaiSinh").focus();
			alert("Chưa nhập đủ mã số khai sinh");
			return false;
		}
		//kiểm tra nếu cấp đổi hoặc cấp lại người dùng có điền đúng CCCD/CMND hay không
		if(y2.checked || y3.checked){
			var cc = $("#soCC").val();
			if(replaceAll(cc,"-","").length != 9){
				if(replaceAll(cc,"-","").length != 12){
					$("#soCC").focus();
					alert("Số căn cước phải có 9 hoặc 12 số");
					return false;
					}
			}
			kiemTraCCKS();
			if($("#ketQuaKiemTraSoKS").attr("ccks-value") == "0"){
				alert("Số căn cước không trùng khớp với số khai sinh");
				return false;
			}
		}
		getThongTinDangKy(); 
		if($("#ketQuaKiemTraSoKS").attr("ks-value") == "0") return false;
		if(y1.checked){
			getSoCCDangKy();
			if($("#ketQuaKiemTraSoKS").attr("cc-value") == "0") return false;
		}
		if(!kiemtra_ngaysinh($("#ngaySinh").val())) return false;
		if($("#maSoKhaiSinh").attr("aria-value") == "0"){
			alert("Số khai sinh không tồn tại, hoặc chưa nhập hộ khẩu");
			return false;
		}
		if(!confirm("Bạn chắc chắn thông tin chính xác?")){
			return false;
		}
		// Xủ lý radio giới tính.
		// Xủ lý radio tình trạng hôn nhân.
		}
	if($("#step").val() == 3 && s != -1){
		if($("#oTinh").val() == 0 || $("#oTinh").val() == null){$("#oTinh").focus(); alert("Chưa chọn Tỉnh - Thành phố nơi ở hiện tại");  return false;}
		if($("#oHuyen").val() == 0 || $("#oHuyen").val() == null){$("#oHuyen").focus(); alert("Chưa chọn Quận - huyện nơi ở hiện tạ");  return false;}
		if($("#oXa").val() == 0 || $("#oXa").val() == null){$("#oXa").focus(); alert("Chưa chọn Xã -Phường nơi ở hiện tạ");  return false;}
		if(replaceAll($("#trinhDoHocVan").val()," ","").length < 1){$("#trinhDoHocVan").val("");$("#trinhDoHocVan").focus(); alert("Chưa nhập trình độ học vấn");  return false;}
		if(replaceAll($("#ngheNghiep").val()," ","").length < 3){$("#ngheNghiep").val("");$("#ngheNghiep").focus(); alert("Chưa nhập nghề nghiệp của bản thân");  return false;}
	}
	
	var step = $("#step").val();
	$("#Step"+step).addClass("display-none");
	$("#Step"+step).removeClass("display-block");
	var next_step = parseInt(step) + parseInt(s);
	if(next_step == 2){
		$("#btnQuayLai").removeClass("display-none");
		$("#btnQuayLai").addClass("display-block");
	} else if(next_step == 1){
		$("#btnQuayLai").addClass("display-none");
		$("#btnQuayLai").removeClass("display-block");
	} else if(next_step == 5){
		$("#btnQuayLai").addClass("display-none");
		$("#btnQuayLai").removeClass("display-block");
	} 
	if(next_step == 3){
		$("#btnTiepTheo").removeClass("display-none");
		$("#btnTiepTheo").addClass("display-block");
	}
	else if(next_step == 4){
		$("#btnTiepTheo").addClass("display-none");
		$("#btnTiepTheo").removeClass("display-block");
	}
	$("#liStep"+next_step).addClass("active");
	$("#liStep"+parseInt(next_step+1)).removeClass("active");
	document.getElementById("step").value = next_step;
	$("#Step"+next_step).removeClass("display-none");
	$("#Step"+next_step).addClass("display-block");
}
	function getThongTinDangKy(){
		var vlue = $("#maSoKhaiSinh").val();
		$("#ketQuaKiemTraSoKS").html("");
		var soKS = "soKS="+vlue;
		$.ajax({url: 'get-so-khai-sinh-dang-ky',
			type: 'GET',
			data: soKS,
		  	success: function(result){
		  		var ketQua = "";
		  		if(result == "0"){
		  			ketQua = "Số Khai sinh này đã đăng ký (Đang chờ làm thủ tục) - hãy kiểm tra lại số khai sinh.";
		  			alert(ketQua);
		  			$("#ketQuaKiemTraSoKS").attr("ks-value","0");
		  		} else {
		  			$("#ketQuaKiemTraSoKS").attr("ks-value","1");
		  		}
	    }});
		}
	function getSoCCDangKy(){
		if($("#maSoKhaiSinh").val().length >= 11){
			var vlue = $("#maSoKhaiSinh").val();
			$("#ketQuaKiemTraSoKS").html("");
			var soKS = "soKS="+vlue;
			$.ajax({url: 'get-socc-dang-ky',
				type: 'GET',
				data: soKS,
			  	success: function(result){
			  		var ketQua = "";
			  		if(result == "0"){
			  			ketQua = "Số Khai sinh đã có số CMND/CCCD, mời bạn chuyển sang yêu cầu cấp đổi và điền số CMND/CCCD";
			  			alert(ketQua);
			  			$("#ketQuaKiemTraSoKS").attr("cc-value","0");
			  		} else if(result == "1"){
			  			$("#ketQuaKiemTraSoKS").attr("cc-value","1");
			  		} else {
			  			ketQua = "Số khai sinh này không tồn tại";
			  			alert(ketQua);
			  			$("#ketQuaKiemTraSoKS").attr("cc-value","0");
			  		}
		    }});
		}
		}
	function kiemTraCCKS(){
		var cc = $("#soCC").val();
		var ks = $("#maSoKhaiSinh").val();
		var data = "soKS="+ks+"&soCC="+cc;
		$.ajax({url: 'kiem-tra-cc-ks',
			type: 'GET',
			data: data,
		  	success: function(result){
		  		var ketQua = "";
		  		if(result == "0"){
		  			$("#ketQuaKiemTraSoKS").attr("ccks-value","0");
		  		} else {
		  			$("#ketQuaKiemTraSoKS").attr("ccks-value","1");
		  		}
	    }});
		}
	function kiemtra_number(id,vlue){
			if(isNaN(vlue) || (vlue == " ")){
				$("#"+id).val("");
			} else {
				var next_id = id.split("_");
				next_id[1] = parseInt(next_id[1]) +1;
				if(vlue.length > 1){
					var new_vlue = vlue.slice(1);
					$("#"+id).val(new_vlue);
				}
				$("#"+next_id[0]+"_"+next_id[1]).focus();
			}
		}
		function kiemtra_yeucau(y){
	            if(y == 1){
	                $("#soCC").attr("disabled","true");
	                $("#soCC").val(" ");
	            } else {
	                $("#soCC").removeAttr("disabled");
	                $("#soCC").val("");
	            }
		}
		function kiemtra_ngaysinh(n){
			var data = n.split("-");
			nam = parseInt(data[2]);
			thang = parseInt(data[1]);
			ngay = parseInt(data[0]);
			var d = new Date();
			var tuoi = 1;
			if(d.getFullYear() - nam < 14 || d.getFullYear() == ""){
				tuoi = 0;
			} else if(d.getFullYear() - nam == 14){
				if (d.getMonth() < (thang-1)){
					tuoi = 0;
				} else if(d.getMonth() == (thang-1)){
					if(d.getDate() - ngay < 0){
						tuoi = 0;
					}
				}
			}
			if(tuoi == 0){
				$("#ngaySinh").focus();
				alert("Chưa đủ tuổi (14) để đăng ký Căn Cước Công Dân");
				return 0;
			} else return 1;
		}
		function kiemtra_submit(){
			var cc_banthan = "";
			var cc_cha = "";
			var cc_me = "";
			var cc_chuho = "";
			var cc_ddhp = "";
			if($("#maXacNhan").val() < 1){
				return false;
			}
			y1 = document.getElementById("yeuCau_1");
			y2 = document.getElementById("yeuCau_2");
			y3 = document.getElementById("yeuCau_3");
			if(!y1.checked){
				if(!y2.checked){
					if(!y3.checked){
						$("#yeuCau_1").focus(); 
						alert("Chưa chọn Yêu cầu");
						return false;
					} else {
						if(!kiemtra_nguoithan("soCC", "so_", 1)) return false;
					}
				}  else {
					if(!kiemtra_nguoithan("soCC", "so_", 1)) return false;
				}
			}
			// xử lý ngày sinh
			if(!kiemtra_ngaysinh($("#ngaySinh").val())) return false;
			// Xủ lý radio giới tính.
			// Xủ lý radio tình trạng hôn nhân.
			if($("#oTinh").val() == 0){$("#oTinh").focus(); alert("Chưa chọn Tỉnh - Thành phố nơi ở hiện tại");  return false;}
			if($("#oHuyen").val() == 0){$("#oHuyen").focus(); alert("Chưa chọn Quận - huyện nơi ở hiện tạ");  return false;}
			if($("#oXa").val() == 0){$("#oXa").focus(); alert("Chưa chọn Xã -Phường nơi ở hiện tạ");  return false;}
			
			if($("#ngheNghiep").val().length < 3){$("#ngheNghiep").focus(); alert("Chưa nhập nghề nghiệp của bản thân");  return false;}
			
			if($("#trinhDoHocVan").val().length < 1){$("#trinhDoHocVan").focus(); alert("Chưa nhập trình độ học vấn");  return false;}
			if($("#quanHeChuHo").val().length < 3){$("#quanHeChuHo").focus(); alert("Chưa nhập mối quan hệ với Chủ hộ (tối thiểu 3 ký tự)"); return false;}
			if($("#maXacNhan").val().length < 1){$("#maXacNhan").focus(); alert("Chưa nhập mã xác nhận"); return false;}
		}
		
		function kiemtra_nguoithan(hidden, ip, batbuoc){
			var cc = $("#"+ip).val();
			if(batbuoc == 0){
				if(replaceAll(cc,"-","").length > 0){
					if(replaceAll(cc,"-","").length != 9){
						if(cc.length != 12){
							$("#"+ip).focus();
							alert("Số căn cước phải có 9 hoặc 12 số");
							return false;
							}
					}
				}
			} else if(batbuoc == 1){
				if(replaceAll(cc,"-","").length != 9){
					if(replaceAll(cc,"-","").length != 12){
						$("#"+ip).focus();
						alert("Số căn cước phải có 9 hoặc 12 số");
						return false;
						}
				}
			}
			var t = $("#"+ip).attr("aria-value");
			if(t == 0){
				$("#"+ip).focus();
				alert("Số căn cước không tồn tại");
				return false;
			}
			document.getElementById(hidden).value = cc;
			return 1;
		}