	<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
span{
	margin-left:30px;
}
a{
	color:blue
}
</style>
	<div id="thongBao" class="row display-none">
			<div class="col-md-3"></div>
			<div class="col-md-6">
			<div class="modal-dialog ly-do">	
				<div class="modal-content">
					<div class="modal-header">
					<button type="button" id="close" class="close" data-dismiss="modal" aria-hidden="true" onclick="dongThongBao();">×</button>
					<h3 class="modal-title">Thông Báo <small><font color="red" id="error"></font></small></h3>
					</div>
					<div class="modal-body">
						<div class="container-fluid">
							<div class="row">
								<label>Hãy cho biết lý do hồ sơ này không được duyệt</label>
							</div>
							<div class="row">
								<input type=text placeholder="Nhập vào lý do không duyệt" id="txtLyDo"  class="form-control"/>
							</div>
							<div class="row mt10">
								<div class="col-md-3 pull-right">
									<input type=button class="btn-warning form-control" onclick="dongThongBao();" value="Hủy thao tác" />
								</div>
								<div class="col-md-3 pull-right">
									<input type=button class="btn-danger form-control" onclick="khongDuyet();" value="Không duyệt" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		</div>
	<div class="row mt40">
		<div class="row text-center" style="width:300px;
			position: absolute;
   	 		right: 40px;"
   	 	>
				<c:if test="${not empty error}">
					<div class="error ml5">
						<b>${error}</b>
						<div class="text-left">
							<p>Thời gian đăng ký: ${ngayDK }</p>
							<p>Cán bộ xác nhận: ${nguoiXacNhan}</p>
						</div>
					</div>
					<% session.removeAttribute("error"); %>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg ml5">
						<b>${msg}</b>
						<div class="text-left">
							<p>Thời gian đăng ký: ${ngayDK }</p>
							<c:if test="${not empty lanCap}">
								<p>Lần cấp: ${lanCap }</p>
							</c:if>
							<p>Cán bộ xác nhận: ${nguoiXacNhan}</p>
						</div>
					</div>
					<% session.removeAttribute("msg"); %>
				</c:if>
			</div>
		<div class="row">
			<div class="row text-center">
					<h2>CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM</h2>
					<label>Độc lập - Tự do - Hạnh Phúc</label>
					<p class="title">**********</p>
			</div>
			<div class="row mt10 text-center">
				<h2>TỜ KHAI CĂN CƯỚC CÔNG DÂN</h2>
			</div>
			<div class="row mt20">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<div class="row">
						<span>1. Họ, chữ đệm và tên: </span>
						<label class="ml20">${hoTen}</label>
					</div>
					<div class="row">
						<span>2. Họ, chữ đệm và tên gọi khác (nếu có): </span>
						<label class="ml20">${hoTenKhac}</label>
					</div>
					<div class="row">
						<span>3. Ngày, tháng, năm sinh: </span>
						<label class="ml20" style="width:180px;">${ngaySinh}</label>
						<span>4. Giới tính (Nam/nữ): </span>
						<label class="ml20">${gioiTinh}</label>
					</div>
					<div class="row">
						<span>5. Số CMND/CCCD: </span>
						<label class="ml20">${soCC}</label>
					</div>
					<div class="row">
						<span>6. Dân tộc: </span>
						<label class="ml20" style="width:120px;">${danToc}</label>
						<span>7. Tôn giáo: </span>
						<label class="ml20" style="width:120px;">${tonGiao}</label>
						<span>8. Quốc tịch: </span>
						<label class="ml20">${quocTich}</label>
						
					</div>
					<div class="row">
						<span>9. Tình trạng hôn nhân: </span>
						<label class="ml20" style="width:180px;">${tinhTrangHonNhan}</label>
						<span>10. Nhóm máu (nếu có): </span>
						<label class="ml20">${nhomMau}</label>
					</div>
					<div class="row">
						<span>11. Nơi đăng ký khai sinh: </span>
						<label class="ml20">${khaiSinh}</label>
					</div>
					<div class="row">
						<span>12. Quê quán: </span>
						<label class="ml20">${queQuan}</label>
					</div>
					<div class="row">
						<span>13. Nơi thường trú: </span>
						<label class="ml20">${thuongTru}</label>
					</div>
					<div class="row">
						<span>14. Nơi ở hiện tại: </span>
						<label class="ml20">${noiOHienTai}</label>
					</div>
					<div class="row">
						<span>15. Nghề nghiệp: </span>
						<label class="ml20" style="width:180px;">${ngheNghiep}</label>
						<span>16. Trình độ học vấn: </span>
						<label class="ml20">${hocVan}</label>
					</div>
					<div class="row">
						<span>17. Họ, chữ đệm và tên của cha: </span>
						<label class="ml20" style="width:220px;">${hoTenCha}</label>
						<span>Quốc tịch: </span>
						<label class="ml20">${quocTichCha}</label>
					</div>
					<div class="row">
						<span>Số CMND/CCCD: </span>
						<label class="ml20"><a href="xem-thong-tin-cccd?soCC=${soCCCha}" target=_blank >${soCCCha}</a></label>
					</div>
					<div class="row">
						<span>18. Họ, chữ đệm và tên của mẹ: </span>
						<label class="ml20" style="width:220px;">${hoTenMe}</label>
						<span>Quốc tịch: </span>
						<label class="ml20">${quocTichMe}</label>
					</div>
					<div class="row">
						<span>Số CMND/CCCD: </span>
						<label class="ml20"><a href="xem-thong-tin-cccd?soCC=${soCCMe}" target=_blank >${soCCMe}</a></label>
					</div>
					<div class="row">
						<span>19. Họ, chữ đệm và tên của vợ (chồng): </span>
						<label class="ml20" style="width:220px;">${hoTenVoChong}</label>
						<span>Quốc tịch: </span>
						<label class="ml20">${quocTichVoChong}</label>
					</div>
					<div class="row">
						<span>Số CMND/CCCD: </span>
						<label class="ml20"><a href="xem-thong-tin-cccd?soCC=${soCCVoChong}" target=_blank >${soCCVoChong}</a></label>
					</div>
					<div class="row">
						<span>20. Họ, chữ đệm và tên của người ĐDHP: </span>
						<label class="ml20" style="width:220px;">${hoTenDD}</label>
						<span>Quốc tịch: </span>
						<label class="ml20">${quocTichDD}</label>
					</div>
					<div class="row">
						<span>Số CMND/CCCD: </span>
						<label class="ml20"><a href="xem-thong-tin-cccd?soCC=${soCCDD}" target=_blank >${soCCDD}</a></label>
					</div>
					<div class="row">
						<span>21. Họ, chữ đệm và tên của chủ hộ: </span>
						<label class="ml20" style="width:220px;">${hoTenChuHo}</label>
						<span>Quốc tịch: </span>
						<label class="ml20">${quocTichChuHo}</label>
					</div>
					<div class="row">
						<span>Số CMND/CCCD: </span>
						<label class="ml20"><a href="xem-thong-tin-cccd?soCC=${soCCChuHo}" target=_blank >${soCCChuHo}</a></label>
					</div>
					<div class="row">
						<span>Quan hệ với chủ hộ: </span>
						<label class="ml20">${quanHeChuHo}</label>
					</div>
					<div class="row">
						<span>22. Yêu cầu của công dân: </span>
					</div>
					<div class="row">
						<span>- Cấp, đổi, cấp lại thẻ Căn cước công dân: </span>
						<label class="ml20">${yeuCau}</label>
					</div>
					<div class="row">
						<span>- Xác nhận số Chứng minh nhân dân (có/không): </span>
						<span class="ml20">...................................................................................................</span>
					</div>
					<div class="row">
						<span>- Chuyển phát bằng đường Bưu điện đến tận tay công dân (có/không): </span>
						<c:if test="${chuyenPhat != '0'}">
							<label class="ml20">${chuyenPhat }</label>
							<% session.removeAttribute("chuyenPhat"); %>
						</c:if>
						<c:if test="${chuyenPhat == '0'}">
							<span class="ml20">.................................................................</span>
						</c:if>
					</div>
					<div class="row">
						<span>Địa chỉ nhận: ................................................................................... </span>
						<span class="ml20">Số điện thoại: .................................................</span>
					</div>
					<div class="row">
						<span>Tôi xin cam đoan những thông tin kê khai trên là đúng sự thật ./.</span>
					</div>
					<div class="row" style="margin-left:400px;">
						<span><i>............., ngày............tháng............năm...........</i></span>
					</div>
					<div class="row" style="margin-left:500px;">
						<label class="ml30 mt10">NGƯỜI KHAI</label>
					</div>
					<div class="row" style="margin-left:500px;">
						<span class="ml20 mt10"><i>(Ký, ghi rõ họ tên)</i></span>
					</div>
					<div class="row mt40 end-page">
					</div>
					<div class="row mt80 text-center">
						<label>KẾT QUẢ XÁC MINH</label>
					</div>
					<div class="row">
						<span>Đội Tàng thư căn cước công dân - Phòng Cảnh sát QLHC về TTXH trả lời kết quả đối chiếu, xác minh với hồ sơ gốc </span><br>
						<span>(có hoặc không có hồ sơ gốc, nếu có hồ sơ gốc thì có nội dung gì khác với tờ khai CCCD hoặc Phiếu thu nhận thông</span><br>
						<span>tin CCCD kèm theo?)</span>
					</div>
					<div class="row mt10">
						<c:if test="${ketQuaXacMinh != '0'}">
							<label class="ml40">${ketQuaXacMinh }</label>
							<% session.removeAttribute("ketQuaXacMinh"); %>
						</c:if>
						<c:if test="${ketQuaXacMinh == '0'}">
							<span>.........................................................................................................................................................................................</span>
							<span>.........................................................................................................................................................................................</span>
							<span>.........................................................................................................................................................................................</span>
							<span>.........................................................................................................................................................................................</span>
							<span>.........................................................................................................................................................................................</span>
							<span>.........................................................................................................................................................................................</span>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		function go(maSo){
			window.open("in-thong-tin-dang-ky-can-cuoc-cong-dan?maSo="+maSo);
		}
	</script>