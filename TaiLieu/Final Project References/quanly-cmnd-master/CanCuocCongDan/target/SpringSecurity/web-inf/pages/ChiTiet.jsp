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
<body>
<div class="container">
	<div class="row text-center">
		<label><h2>TỜ KHAI CĂN CƯỚC CÔNG DÂN</h2></label>
	</div>
	<div class="row mt40">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-2 pull-left"><img src="" alt="image" width="200" height="220"/></div>
			<div class="col-md-7 pull-left">
				<div class="row">
					<label class="col-md-3 text-right">Họ tên: </label>
					<span class="col-md-6 ml20">${hoTen}</span>
				</div>
				<div class="row">
					<label class="col-md-3 text-right">Tên gọi khác: </label>
					<span class="col-md-6 ml20">${hoTenKhac}</span>
				</div>
				<div class="row">
					<label class="col-md-3 text-right">Ngày sinh: </label>
					<span class="col-md-6 ml20">${ngaySinh}</span>
				</div>
				<div class="row">
					<label class="col-md-3 text-right">Giới tính: </label>
					<span class="col-md-3 ml20">${gioiTinh}</span>
					<span class="ml40"><B>Nhóm máu: </B></span>
					<span>${nhomMau}</span>
				</div>
				<div class="row">
					<label class="col-md-3 text-right">Dân tộc: </label>
					<span class="col-md-3 ml20">${danToc}</span>
					<span class="ml40"><b>Tôn giáo: </b></span>
					<span>${tonGiao}</span>
				</div>
				<div class="row">
					<label class="col-md-3 text-right">Số CMND/CCCD: </label>
					<span class="col-md-6 ml20">${soCC}</span>
				</div>
				<div class="row">
					<label class="col-md-3 text-right">Đặc điểm nhận dạng: </label>
					<span class="col-md-6 ml20">${nhanDang}</span>
				</div>
				<div class="row">
					<label class="col-md-3 text-right">Yêu cầu: </label>
					<span class="col-md-3 ml20">${yeuCau}</span>
					<span class="ml40"><B>Lần cấp: </B></span>
					<span>${lanCap}</span>
				</div>
				<div class="row">
					<label class="col-md-3 text-right">Ngày đăng ký: </label>
					<span class="col-md-6 ml20">${ngayDK}</span>
				</div>
			</div>
		</div>
		<div class="row mt10">
			<div class="col-md-2"></div>
			<div class="col-md-9">
				<div class="row ml20">
					<label class="col-md-4 text-right">Quốc tịch: </label>
					<span class="col-md-3">${quocTich}</span>
					<span class="ml40"><b>Tình trạng hôn nhân: </b></span>
					<span>${tinhTrangHonNhan}</span>
				</div>
				<div class="row ml20">
					
				</div>
				<div class="row ml20">
					<label class="col-md-4 text-right">Nơi đăng ký khai sinh: </label>
					<span class="col-md-6">${khaiSinh}</span>
				</div>
				<div class="row ml20">
					<label class="col-md-4 text-right">Quê quán: </label>
					<span class="col-md-6">${queQuan}</span>
				</div>
				<div class="row ml20">
					<label class="col-md-4 text-right">Địa chỉ thường trú: </label>
					<span class="col-md-6">${thuongTru}</span>
				</div>
				<div class="row ml20">
					<label class="col-md-4 text-right">Nơi ở hiện tại: </label>
					<span class="col-md-6">${noiOHienTai}</span>
				</div>
				<div class="row ml20">
					<label class="col-md-4 text-right">Trình độ học vấn: </label>
					<span class="col-md-3">${hocVan}</span>
					<span class="ml40"><b>Nghề nghiệp: </b></span>
					<span>${ngheNghiep}</span>
				</div>
				<c:if test='${soCCCha != null }'>
					<div class="row ml20">
						<label class="col-md-4 text-right">Họ tên cha: </label>
						<span class="col-md-3">${hoTenCha}</span>
						<span class="ml40"><b>Quốc tịch cha: </b></span>
						<span>${quocTichCha}</span>
					</div>
					<div class="row ml20">
						<label class="col-md-4 text-right">Số CMND/CCCD của cha: </label>
						<span class="col-md-6">${soCCCha}</span>
					</div>
				</c:if>
				<c:if test='${soCCMe != null }'>
					<div class="row ml20">
						<label class="col-md-4 text-right">Họ tên mẹ: </label>
						<span class="col-md-3">${hoTenMe}</span>
						<span class="ml40"><b>Quốc tịch mẹ: </b></span>
						<span>${quocTichMe}</span>
					</div>
					<div class="row ml20">
						<label class="col-md-4 text-right">Số CMND/CCCD của mẹ: </label>
						<span class="col-md-6">${soCCMe}</span>
					</div>
				</c:if>
				<c:if test='${soCCVoChong != null }'>
					<div class="row ml20">
						<label class="col-md-4 text-right">Họ tên Vợ hoặc Chồng: </label>
						<span class="col-md-6">${hoTenVoChong}</span>
					</div>
					<div class="row ml20">
						<label class="col-md-4 text-right">Quốc tịch Vợ hoặc Chồng: </label>
						<span class="col-md-6">${quocTichVoChong}</span>
					</div>
					<div class="row ml20">
						<label class="col-md-4 text-right">Số CMND/CCCD của Vợ hoặc Chồng: </label>
						<span class="col-md-6">${soCCVoChong}</span>
					</div>
				</c:if>
				<c:if test='${soCCDD != null }'>
					<div class="row ml20">
						<label class="col-md-4 text-right">Họ tên ĐDHP: </label>
						<span class="col-md-3">${hoTenDD}</span>
						<span class="ml40"><b>Quốc tịch ĐDHP: </b></span>
						<span>${quocTichDD}</span>
					</div>
					<div class="row ml20">
						<label class="col-md-4 text-right">Số CMND/CCCD của ĐDHP: </label>
						<span class="col-md-6">${soCCDD}</span>
					</div>
				</c:if>
				<div class="row ml20">
					<label class="col-md-4 text-right">Họ tên Chủ hộ: </label>
					<span class="col-md-3">${hoTenChuHo}</span>
					<span class="ml40"><b>Quốc tịch Chủ hộ: </b></span>
					<span>${quocTichChuHo}</span>
				</div>
				<div class="row ml20">
					<label class="col-md-4 text-right">Số CMND/CCCD của Chủ hộ: </label>
					<span class="col-md-6">${soCCChuHo}</span>
				</div>
				<div class="row ml20">
					<label class="col-md-4 text-right">Quan hệ với Chủ hộ: </label>
					<span class="col-md-6">${quanHeChuHo}</span>
				</div>
				
				<div class="row mt20">
					
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>