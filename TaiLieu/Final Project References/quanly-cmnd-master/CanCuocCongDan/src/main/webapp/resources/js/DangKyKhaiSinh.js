/**
 * 
 */
		function step(s) {
			//Kiem tra input o step 1 not null
			if($("#step").val() == 1){
				y1 = document.getElementById("yeuCau_1");
				y3 = document.getElementById("yeuCau_3");
				if(!y1.checked){
					if(!y3.checked){
						$("#yeuCau_1").focus(); 
						alert("Chưa chọn Yêu cầu");
						return false;
					} 
					$("#soKSCu").removeAttr("disabled");
				} else {
					$("#soKSCu").attr("disabled", "true");
				}
				if(($("#noiDKLVTinh").val() == 0) || ($("#noiDKLVHuyen").val() == 0) || ($("#noiDKLVXa").val() == 0)){
					$("#noiDKLVTinh").focus();
					alert("Vui lòng chọn tỉnh - huyện - xã bạn muốn làm việc.");
					return false;
				}
			}
			//Kiem tra input o step 2 not null
			if(($("#step").val() == 2) && (s != -1)) {
				
				var hoTen = document.getElementById("hoTen").value;
				var ngaySinh = document.getElementById("ngaySinh").value;
				var quocTich = document.getElementById("quocTich").value;
				var noiSinhTinh = document.getElementById("noiSinhTinh").value;
				var noiSinhHuyen = document.getElementById("noiSinhHuyen").value;
				var noiSinhXa = document.getElementById("noiSinhXa").value;
				var queQuanTinh = document.getElementById("queQuanTinh").value;
				var queQuanHuyen = document.getElementById("queQuanHuyen").value;
				var queQuanXa = document.getElementById("queQuanXa").value;
			
				if(checkNull(hoTen)) {
					alert("Vui lòng nhập đầy đủ họ tên.");
					$("#hoTen").focus(); 
					return false;
				} else if (checkNull(ngaySinh)){
					alert("Vui lòng chọn ngày tháng năm sinh.");
					$("#ngaySinh").focus(); 
					return false;
				} else if (checkNull(quocTich)){
					alert("Vui lòng chọn nhập quốc tịch.");
					$("#quocTich").focus(); 
					return false;
				} else if(checkNull(noiSinhTinh) || checkNull(noiSinhHuyen) || checkNull(noiSinhXa)) {
					alert("Vui lòng chọn tỉnh/huyện/xã nơi sinh");
					$("#noiSinhTinh").focus(); 
					return false;
				} else if(checkNull(queQuanTinh) || checkNull(queQuanHuyen) || checkNull(queQuanXa)) {
					alert("Vui lòng chọn tỉnh/huyện/xã quê quán của bạn");
					$("#quenQuanTinh").focus(); 
					return false;
				}
				if (!ktNgaySinh(ngaySinh)) {
					alert("Ngày sinh không được lớn hơn ngày hiện tại");
					$("#ngaySinh").focus(); 
					return false;
				}
			}
			//Kiem tra input step 3 not null
			if(($("#step").val() == 3) && (s != -1)) {
					var soCCNguoiYeuCau = document.getElementById("soCCNguoiYeuCau").value;
					var quanHeVoiNguoiKS = document.getElementById("quanHeVoiNguoiKS").value;
					var hoTenNguoiYeuCau = document.getElementById("hoTenNguoiYeuCau").value;
					if (checkNull(soCCNguoiYeuCau)) {
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
					}
			}
			//Kiem tra input o step 4 not null
			if(($("#step").val() == 4) && (s != -1)){
				var soCCCha = document.getElementById("soCCCha").value;
				var hoTenCha = document.getElementById("hoTenCha").value;
				if(!checkNull(soCCCha)) {
					if (!ktDoDai(soCCCha)) {
						alert("Số căn cước cha phải có độ dài 12 số");
						$("#soCCCha").focus(); 
						return false;
					} else if (checkNull(hoTenCha)) {
						alert("Số căn cước cha chưa có trên hệ thống");
						$("#soCCCha").focus();
						return false;
					}
				}
			}
			//Kiem tra input o step 5 not null
			if(($("#step").val() == 5) && (s != -1)){
				var soCCMe = document.getElementById("soCCMe").value;
				var hoTenMe = document.getElementById("hoTenMe").value;
				if(!checkNull(soCCMe)) {
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
			} else if(next_step == 7){
				$("#btnQuayLai").addClass("display-none");
				$("#btnQuayLai").removeClass("display-block");
			} 
			if(next_step == 5){
				$("#btnTiepTheo").removeClass("display-none");
				$("#btnTiepTheo").addClass("display-block");
			}
			else if(next_step == 6){
				$("#btnTiepTheo").addClass("display-none");
				$("#btnTiepTheo").removeClass("display-block");
			}
			$("#liStep"+next_step).addClass("active");
			$("#liStep"+parseInt(next_step+1)).removeClass("active");
			document.getElementById("step").value = next_step;
			$("#Step"+next_step).removeClass("display-none");
			$("#Step"+next_step).addClass("display-block");
		}
		
		function getThongTinKS(id) {
			var soKSCu = document.getElementById(id).value;
			if (ktDoDai(soKSCu)) {
				soKSCu = replaceAll(soKSCu, "-", "");
				soKSCu = "soKS=" + soKSCu;
				$.ajax({url: 'get-thong-tin-ks',
					type: 'GET',
					data: soKSCu,
				  	success: function(data) {
				  		if (data != "") {
				  			var thongTinKS = data.split("@@");
				  			//thongTinKS[0] la hoTen
				  			$("#hoTen").val(thongTinKS[0]);
				  			//thongTinKS[1] la ngaySinh (Nam-thang-ngay)
				  			$("#ngaySinh").val(thongTinKS[1]);
				  			//thongTinKS[2] la gioiTinh
				  			$("#gioiTinh").val(thongTinKS[2]);
				  			//thongTinKS[3] la quocTich
				  			$("#quocTich").val(thongTinKS[3]);
				  			//thongTinKS[4] la danToc
				  			$("#danToc").val(thongTinKS[4]);
				  			//thongTinKS[5] la benhVien
				  			if (thongTinKS[5] != "null") {
				  				$("#benhVien").val(thongTinKS[5]);
				  			}
				  		}
				  	} 
				});
			}	
		}
		function ktOnSubmit(){
			var maXacNhan = document.getElementById("maXacNhan").value;
			if (checkNull(maXacNhan)) {
				alert("Vui lòng nhập mã xác nhận.");
				$("#maXacNhan").focus();
				return false;
			}
		}


		