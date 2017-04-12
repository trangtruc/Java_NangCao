/**
 * 
 */
function step(s){
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
			var cc = "";
			for(var i = 1; i <= 12; i++){
				cc += $("#so_"+i).val();
			}
			var t = $("#tonTaiCC").attr("aria-value");
			if(t == 0){
				alert("Số căn cước không tồn tại");
				return false;
			}
			if(cc.length != 9){
				if(cc.length != 12){
					$("#so_1").focus();
					alert("Số căn cước phải có 9 hoặc 12 số");
					return false;
					}
			}
		}
		if(!kiemtra_ngaysinh($("#ngaySinh").val())) return false;
		if($("#maSoKhaiSinh").attr("aria-value") == "0"){
			alert("Số khai sinh không tồn tại");
			return false;
		}
		if(!confirm("Bạn chắc chắn thông tin chính xác?")){
			return false;
		}
		// Xủ lý radio giới tính.
		// Xủ lý radio tình trạng hôn nhân.
		}
	if($("#step").val() == 3 && s != -1){
		if($("#thuongTruTinh").val() == 0){$("#thuongTruTinh").focus(); alert("Chưa chọn Tỉnh - Thành phố thường trú");  return false;}
		if($("#thuongTruHuyen").val() == 0 || $("#thuongTruHuyen").val() == null){$("#thuongTruHuyen").focus(); alert("Chưa chọn Quận - huyện thường trú");  return false;}
		if($("#thuongTruXa").val() == 0 || $("#thuongTruXa").val() == null){$("#thuongTruXa").focus(); alert("Chưa chọn Xã -Phường thường trú");  return false;}
		if($("#oTinh").val() == 0 || $("#oTinh").val() == null){$("#oTinh").focus(); alert("Chưa chọn Tỉnh - Thành phố nơi ở hiện tại");  return false;}
		if($("#oHuyen").val() == 0 || $("#oHuyen").val() == null){$("#oHuyen").focus(); alert("Chưa chọn Quận - huyện nơi ở hiện tạ");  return false;}
		if($("#oXa").val() == 0 || $("#oXa").val() == null){$("#oXa").focus(); alert("Chưa chọn Xã -Phường nơi ở hiện tạ");  return false;}
		if($("#trinhDoHocVan").val().length < 1){$("#trinhDoHocVan").focus(); alert("Chưa nhập trình độ học vấn");  return false;}
		if($("#ngheNghiep").val().length < 3){$("#ngheNghiep").focus(); alert("Chưa nhập nghề nghiệp của bản thân");  return false;}
		if($("#quanHeChuHo").val().length < 3){$("#quanHeChuHo").focus(); alert("Chưa nhập mối quan hệ với Chủ hộ (tối thiểu 3 ký tự)"); return false;}
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
	                for(var i = 1; i <= 12; i++){
	                    $("#so_"+i).attr("disabled","true");
	                    $("#so_"+i).val("");
	                }
	              
	            } else {
	            	for(var i = 1; i <= 12; i++){
	                    $("#so_"+i).removeAttr("disabled");
	                }
	            }
		}
		function kiemtra_honnhan(v){
			if(v == 0){
				for(var i = 1; i <= 12; i++){
					$("#soVoChong_"+i).attr("disabled", "true");
				}
			} else {
				for(var i = 1; i <= 12; i++){
					$("#soVoChong_"+i).removeAttr("disabled");
				}
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
			if($("#thuongTruTinh").val() == 0){$("#thuongTruTinh").focus(); alert("Chưa chọn Tỉnh - Thành phố thường trú");  return false;}
			if($("#thuongTruHuyen").val() == 0){$("#thuongTruHuyen").focus(); alert("Chưa chọn Quận - huyện thường trú");  return false;}
			if($("#thuongTruXa").val() == 0){$("#thuongTruXa").focus(); alert("Chưa chọn Xã -Phường thường trú");  return false;}
			if($("#oTinh").val() == 0){$("#oTinh").focus(); alert("Chưa chọn Tỉnh - Thành phố nơi ở hiện tại");  return false;}
			if($("#oHuyen").val() == 0){$("#oHuyen").focus(); alert("Chưa chọn Quận - huyện nơi ở hiện tạ");  return false;}
			if($("#oXa").val() == 0){$("#oXa").focus(); alert("Chưa chọn Xã -Phường nơi ở hiện tạ");  return false;}
			
			if($("#ngheNghiep").val().length < 3){$("#ngheNghiep").focus(); alert("Chưa nhập nghề nghiệp của bản thân");  return false;}
			
			if($("#trinhDoHocVan").val().length < 1){$("#trinhDoHocVan").focus(); alert("Chưa nhập trình độ học vấn");  return false;}
			if($("#quanHeChuHo").val().length < 3){$("#quanHeChuHo").focus(); alert("Chưa nhập mối quan hệ với Chủ hộ (tối thiểu 3 ký tự)"); return false;}
			if($("#maXacNhan").val().length < 1){$("#maXacNhan").focus(); alert("Chưa nhập mã xác nhận"); return false;}
		}
		
		function kiemtra_nguoithan(hidden, ip, batbuoc){
			var cc = "";
			for(var i = 1; i <= 12; i++){
				cc += $("#"+ip+i).val();
			}
			if(batbuoc == 0){
				if(cc.length > 0){
					if(cc.length != 9){
						if(cc.length != 12){
							$("#"+ip+"1").focus();
							alert("Số căn cước phải có 9 hoặc 12 số");
							return false;
							}
					}
				}
			} else if(batbuoc == 1){
				if(cc.length != 9){
					if(cc.length != 12){
						$("#"+ip+"1").focus();
						alert("Số căn cước phải có 9 hoặc 12 số");
						return false;
						}
				}
			}
			var x = ip.split("_"); // ket qua x[0] la soCha,  x[1] la 1
			var s = x[0].split("so"); // ket qua s[0] la null, s[1] la Cha
			var resultID = "hoTen"+s[1];
			var t = $("#"+resultID).attr("aria-value");
			if(t == 0){
				$("#"+ip+"1").focus();
				alert("Số căn cước không tồn tại");
				return false;
			}
			document.getElementById(hidden).value = cc;
			return 1;
		}