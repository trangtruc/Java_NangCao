				function createForm1(){
					var soCCNguoiDK = $("#soCCNguoiDK").val();
						soCCNguoiDK = replaceAll(soCCNguoiDK,"-","");
					var soHKCu =  $("#soHKCu").val();
						soHKCu = replaceAll(soHKCu,"-","");
					var soHK = "soHK="+ soHKCu+"&soCCNguoiDK="+soCCNguoiDK;
					//alert("toi day rui nek "+ soHK);
					$.ajax({url: 'lay-thong-tin-ho-khau',
						type: 'GET',
						data: soHK ,
					  	success:function(result){
					  				if(checkNull(result)) {
										alert("Nhập sai số sổ hộ khẩu hoặc người đăng ký không thuộc hộ khẩu");
								  	}if(result == "khongcoquyendangky"){
								  		alert("Người đăng nhập không có quyền đăng ký");
								  	}else if(!checkNull(result)){
									var danhSachThanhVien = "<table border=1 >"
															+ "<tr ><th colspan='7'>Danh Sách Các Thành Viên Trong Gia Đình</th></tr>"
															+ "<tr>"
															+ "<th>STT</th>"
															+ "<th>Họ Tên</th>"
															+ "<th>Tình Trạng</th>"
															+ "<th>Số khai sinh</th>"
															+ "<th>Số căn cước</th>"
															+ "<th>Quan hệ với chủ hộ</th>"
															+ "<th>Các thành viên cùng chuyển hộ khẩu</th>"
															+ "</tr>"
															+ result
															+ "<div style='margin-left: 90%; margin-top: 10px; margin-bottom: 10px;'><input type=button class='btn form-controll btn-primary' name=chapnhan id=chapnhan value='Chấp Nhận' onclick='createForm2();' /></div>";
										$("#form-input").html(danhSachThanhVien);
										//$("#btn-submit").html("<button type=submit  class='btn btn-primary' >Hoàn Tất</button>");
									}
					  			}
					});
				
				}			
			
				function createForm2(){
					var sl = $("#soLuong").val(); //lay so luong
					var html = "";
					html += "<table border=1 >"
						+ "<tr ><th colspan='6'>Thông tin hộ khẩu mới</th></tr>"
						+ "<tr>"
						+ "<th colspan = '2'>Địa chỉ hiện tại</th>"
						+ "<td colspan = '4' style='width = 100%'><input type=text id=diaChiDK name=diaChiDK class='form-control' /></td>"
						+ "<tr>"
						+ "<th>STT</th>"
						+ "<th>Họ Tên</th>"
						+ "<th>Tình Trạng</th>"
						+ "<th>Số khai sinh</th>"
						+ "<th>Số căn cước</th>"
						+ "<th>Quan hệ với chủ hộ</th>"
						+ "</tr>"
					var i = 0;
					var y = 0;
					var soLuongDangKy = 0;
					var dsQuanHe = $("#dsQuanHe").val();
					for(i = 0 ; i < sl ; i ++){
						if( document.getElementById("chuyen"+i).checked  ){
							html += "<tr>"
								+ "<td style='width:5%'>Thành viên thứ "+(y+1)+"</td>"
								+ "<td style='width:15%'><input type=text class='form-control' value='"+$("#hoTen"+i).val()+"' disabled/></td>"
								+ "<td style='width:25%'><input type=text class='form-control' value='"+$("#tinhTrang"+i).val()+"' disabled/></td>"
								+ "<td style='width:17%'><input type=text class='form-control' value='"+$("#soKSThanhVien"+i).val()+"' disabled/></td>"
								+ "<td style='width:17%'><input type=text class='form-control' value='"+$("#soCCThanhVien"+i).val()+"' disabled/></td>"
								+ "<td style='width:11%'><select name = 'quanHeDK"+y+"' id='quanHeDK"+y+"' class='form-control' onchange='ktChuHo(this.id, this.value);ktHonNhan(this.id, this.value)'>"
							   		+dsQuanHe
							   +"</select></td>"
								+ "</tr>"
								//hidden
								+ "<input type=text name = hoTenDK"+(y)+" id = hoTenDK"+(y)+"  value='"+$("#hoTen"+i).val()+"' hidden/>"
								+ "<input type=text name = tinhTrangDK"+(y)+" id = tinhTrangDK"+(y)+"  value='"+$("#tinhTrang"+i).val()+"' hidden />"
								+ "<input type=text name = soKSThanhVienDK"+(y)+" id = soKSThanhVienDK"+(y)+"  value='"+$("#soKSThanhVien"+i).val()+"' hidden />"
								+ "<input type=text name = soCCThanhVienDK"+(y)+" id = soCCThanhVienDK"+(y)+"  value='"+$("#soCCThanhVien"+i).val()+"' hidden />";
							y++;
							soLuongDangKy ++;
						}
					}
								html += "</table>" 
									 + "<div><input type=text id=soLuongDK name=soLuongDK value='"+soLuongDangKy+"' hidden /></div>"
									 + "<div style='margin-left: 45%; margin-right: 45%; margin-top: 10px; margin-bottom: 10px;'>"
									 + "<input type=submit class='form-control btn btn-primary' name=hoanTat id=hoanTat value='Hoàn Tất' /></div>"
									 + "<script src='resources/js/function.js'></script>";
					$("#form-input1").html(html);
				
				}
				
				
				function checkNull(val) {
					return (val == null || val.length < 1 || val == 0 || "Không tìm thấy" == val);
				}
				
				
				function layThongTinKS(id, val) {
					var soKS = replaceAll(val, "-", "");
					var hoTen = id.replace("soKSThanhVien","hoTen");
					
					var kq;
					var kqMacDinh = "Không tìm thấy";
					soKS = "soKS=" + soKS;
					$.ajax({url: 'lay-thong-tin-ks',
						type: 'GET',
						data: soKS ,
					  	success: function(result){
					  		if(checkNull(result)) {
					  			$("#"+id).val("");
					  			$("#"+hoTen).val(kqMacDinh);
					  			alert("Số khai sinh không tồn tại");
					  		} else {
					  			kq = result;
					  			$("#"+hoTen).attr("aria-value","1");
					  			$("#"+hoTen).val(kq);
					  			
					  		}
					  	}
					});
				}
				
				function layThongTinCC(id, val) {
					
					var soCC = replaceAll(val, "-", "");
					var hoTen = id.replace("soCC","hoTen");
					var gioiTinh = id.replace("soCCNguoiDK","gioiTinh")
					var kqMacDinh = "Không tìm thấy";
					soCC = "soCC=" + soCC;
					$.ajax({url: 'get-thong-tin-cccd',
						type: 'GET',
						data: soCC ,
					  	success: function(result){
					  		if(checkNull(result)) {
					  			$("#"+hoTen).attr("aria-value","0");
					  			$("#"+hoTen).val(kqMacDinh);
					  			$("#"+gioiTinh).attr("aria-value","0");
					  			$("#"+goiTinh).val(kqMacDinh);

					  		} else {
					  			//alert(result);
					  			var kq = result.split("@@");
					  			$("#"+hoTen).attr("aria-value","1");
					  			$("#"+hoTen).val(kq[0]);
					  			$("#"+gioiTinh).attr("aria-value","0");
					  			$("#"+gioiTinh).val(kq[7]);
					  			
					  		}
					  	}
					});
				}
		
				function ktChuHo(id, val) {
					var sl = $("#soLuongDK").val();
					var stt = id.replace("quanHeDK","");
					var temp;
					var soChuHo =0;
					//alert("id: "+id+" gia tri: "+val+" so tt : "+stt+" so luong: "+sl);
					if( val == "chuho" ){
						for( var i = 0 ; i < sl ; i ++){
							temp = $('#quanHeDK'+(i)).val();
							if(temp == "chuho"){
								soChuHo ++;
							}
						}
						if(soChuHo >=2){
							alert("Không được có hai chủ hộ");
						}
					}
					
				}
				
				function ktHonNhan(id, val) {
					var sl = $("#soLuongDK").val();
					var stt = id.replace("quanHeDK","");
					var soCCChuHo;
					var soLuongVoChong = 0;
					//alert("id: "+id+" gia tri: "+val+" so tt : "+stt+" so luong: "+sl);
					if( val == "chuho" ){
						soCCChuHo ="soCCChuHo="+$("#soCCThanhVienDK"+stt).val();
					$.ajax({url: "lay-thong-tin-hon-nhan",
						type: 'GET',
						data: soCCChuHo ,
					  	success: function(result){
					  		var soCCThanhVienDK;
					  		var quanHe;
					  		if(!checkNull(result)) {
					  			if(result.search("soCCChong")>=0){
					  				quanHe = "chong";
					  				result = result.replace("soCCChong","");
					  			}else{
					  				quanHe = "vo";
					  				result = result.replace("soCCVo","");
					  			}
					  			for(var i = 0; i < sl ; i++){
							  			soCCThanhVienDK = $("#soCCThanhVienDK"+i).val();
							  			if(result == soCCThanhVienDK){
							  				$("#quanHeDK"+i).val(quanHe);
							  			}
					  			}
					  		
					  		} 
					  	}
					});
					}
					
					
					if(val =="chong" || val == "vo"){
						var soCC1 = $("#soCCThanhVienDK"+stt).val();
						var soCCChuHo;
						for(var i = 0 ; i < sl ; i++){
							if($("#quanHeDK"+i).val() == "chuho"){
								soCCChuHo ="soCCChuHo="+$("#soCCThanhVienDK"+i).val();
							}
						}
						$.ajax({url: "lay-thong-tin-hon-nhan",
							type: 'GET',
							data: soCCChuHo ,
						  	success: function(result){
						  		var soCCThanhVienDK;
						  		var quanHe;
						  		if(!checkNull(result)) {
						  			if(result.search("soCCChong")>=0){
						  				quanHe = "chong";
						  				result = result.replace("soCCChong","");
						  			}else{
						  				quanHe = "vo";
						  				result = result.replace("soCCVo","");
						  			}
						  			if(soCC1 == result){
							  			alert("Thành Viên này với chủ hộ đã kết hôn");
									  	$("#"+id).val(quanHe);
						  			}else{
						  				alert("Thành viên này với chủ hộ không phải vợ chồng");
										$("#"+id).val("em");
						  			}
						  		}else{
					  				alert("Thành viên này với chủ hộ không phải vợ chồng");
									$("#"+id).val("em");
					  			}
						}
						});
					}
					
					
					if( val == "vo" || val == "chong" ){
						
					}
					
				}
				
				
				function dkSubmit() {
					var sl = $("#soLuongDK").val();
					var noiDKLamViec = document.getElementById("noiDKLVTinh");
					var diaChiDK = $("#diaChiDK").val();
					diaChiDK = diaChiDK.replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g,"").replace(/\s+/g," ");
					var temp;
					var soChuHo = 0;
					//kiem tra dia chi
					if(diaChiDK == "" || diaChiDK == null){
						alert("Chưa nhập địa chỉ mới");
						return false;
					}
					//kiem tra so luong chu ho
					for(var i = 0; i < sl; i++){
						temp = $("#quanHeDK"+i).val();
						if(temp == "chuho"){		
							soChuHo ++;
						}
					}
					if(soChuHo ==0){
						alert("Phải có một chủ hộ");
						return false;
					}else if(soChuHo >1){
						alert("Không được có nhiều hơn một chủ hộ");
						return false;
					}
					return true;
				}