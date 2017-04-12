<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="menu" class="ml10">
	<ul>
	<sec:authorize access="hasAnyRole('XEM_DANH_SACH_TTDK_CCCD','XAC_NHAN_TTDK_CCCD','CAP_NHAT_TTDK_CCCD')">
			<li>
			 	<a href='danh-sach-dang-ky'>
			 		Đơn đăng ký CCCD
			 		<c:if test="${ssSoDonTTDKCCCDChoXacNhan  != null && ssSoDonTTDKCCCDChoXacNhan != '0'}">	
								<sup><span class='message-menu'>${ssSoDonTTDKCCCDChoXacNhan }</span></sup>
							</c:if>
			 	</a>
			 	<ul class="sub-menu">
			 		<li>
				 		<a href='danh-sach-dang-ky' >Chưa xác nhận
						 	<c:if test="${ssSoDonTTDKCCCDChoXacNhan  != null && ssSoDonTTDKCCCDChoXacNhan != '0'}">	
								<sup><span class='message-menu'>${ssSoDonTTDKCCCDChoXacNhan }</span></sup>
							</c:if>
						 </a>
			 		</li>
			 		<sec:authorize access="hasAnyRole('XEM_DANH_SACH_TTDK_CCCD','XAC_NHAN_TTDK_CCCD')">
				 		<li>
				 			<a href='danh-sach-dang-ky-da-xac-nhan'>
				 				Đã xác nhận
				 			</a>
				 		</li>
			 		</sec:authorize>
			 	</ul>
			 </li>
	</sec:authorize>
<c:forEach items='${ssQuyen}' var='quyen'>
	<c:if test="${quyen.tenQuyen eq 'QUAN_LY_TAI_KHOAN' }">
			<li><a href='quan-ly-tai-khoan'>Quản lý tài khoản</a>
				<ul class='sub-menu'>
					<li><a href='quan-ly-tai-khoan'>Danh sách tài khoản</a></li>
					<li><a href='quan-ly-tai-khoan-hoat-dong'>Tài khoản hoạt động</a></li>
					<li><a href='quan-ly-tai-khoan-bi-khoa'>Tài khoản bị khóa</a></li>
					<li><a href='them-tai-khoan'>Thêm tài khoản</a></li>
				</ul>
			</li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'QUAN_LY_CAU_HINH_CCCD' }">
			<li><a href='quan-ly-cau-hinh-dang-ky-cmnd-cccd'>Quản lý cấu hình</a>
				<ul class='sub-menu'>
					<li><a href="quan-ly-cau-hinh-dang-ky-cmnd-cccd">Cấu hình CCCD</a></li>
					<li><a href="quan-ly-cau-hinh-so-tuoi-ket-hon">Cấu hình số tuổi kết hôn</a></li>
					<li><a href="quan-ly-lich-lam-viec">Cấu hình lịch làm việc</a></li>
					<li><a href="quan-ly-cau-hinh-email">Cấu hình Email</a></li>
				</ul>
			</li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'QUAN_LY_TINH' }">
			<li><a href='quan-ly-tinh'>Quản lý danh mục Tỉnh</a></li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'QUAN_LY_HUYEN' }">
			<li><a href='quan-ly-huyen'>Quản lý danh mục Huyện</a></li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'QUAN_LY_XA' }">
			<li><a href='quan-ly-xa'>Quản lý danh mục Xã</a></li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'QUAN_LY_DAN_TOC' }">
			<li><a href='quan-ly-dan-toc'>Quản lý danh mục dân tộc</a></li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'QUAN_LY_YEU_CAU_CCCD' }">
			<li><a href="quan-ly-yeu-cau-dang-ky-cmnd-cccd">Quản lý yêu cầu CCCD</a></li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'IN_TRA_THE' }">
			<li><a href='danh-sach-tra-the-can-cuoc-cong-dan'>In Trả thẻ Căn Cước</a>
			 	<ul class='sub-menu'>
			 		<li><a href='danh-sach-lam-the-can-cuoc-cong-dan' >
			 			Danh sách thẻ chưa làm
			 			</a></li>
			 		<li><a href='danh-sach-tra-the-can-cuoc-cong-dan' >
			 			Danh sách thẻ chờ trả
			 			</a></li>
			 		<li><a href='danh-sach-the-can-cuoc-cong-dan'>
			 			Danh sách thẻ đã phát hành
			 			</a></li>
			 	</ul>
			 </li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'NHAP_CCCD' }">
			<li><a href=nhap-can-cuoc-cong-dan >Nhập căn cước (Cũ)</a></li>
	</c:if>
	
	<c:if test="${quyen.tenQuyen eq 'DUYET_TTDK_CCCD' }">
			<li>
				<a href='don-dang-ky-cccd-chua-duyet'>Hồ sơ đăng ký CCCD
			 		<c:if test="${ssSoDonTTDKCCCDChoDuyet  != null && ssSoDonTTDKCCCDChoDuyet != '0'}">	
							<sup><span class='message-menu'>${ssSoDonTTDKCCCDChoDuyet }</span></sup>
						</c:if>
				</a>
				<ul class='sub-menu'>
					<li><a href='don-dang-ky-cccd-chua-duyet'>Chưa duyệt
						<c:if test="${ssSoDonTTDKCCCDChoDuyet  != null && ssSoDonTTDKCCCDChoDuyet != '0'}">	
							<sup><span class='message-menu'>${ssSoDonTTDKCCCDChoDuyet }</span></sup>
						</c:if>
					</a></li>
					<li><a href='don-dang-ky-cccd-da-duyet'>Đã cấp phép</a></li>
					<li><a href='don-dang-ky-cccd-bi-tu-choi'>Bị từ chối</a></li>
				</ul>
			</li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'XEM_DANH_SACH_CCCD' }">
			<li><a href='danh-sach-can-cuoc-cong-dan'>Danh sách CCCD</a></li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'THONG_KE_TTDK_CCCD' }">
			<li><a>Thống kê đơn đăng ký CCCD</a>
				<ul class="sub-menu">
					<li><a href="thong-ke-cccd-tong-quat">Toàn bộ</a></li>
					<li><a href="thong-ke-cccd-don-vi">Từng đơn vị</a></li>
				</ul>
			</li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'THONG_KE_DAN_SO' }">
			<li><a>Thống kê Dân số</a>
				<ul class="sub-menu">
					<li><a href="thong-ke-dan-so-xa">Cấp xã</a></li>
					<li><a href="thong-ke-dan-so-huyen">Cấp huyện</a></li>
					<li><a href="thong-ke-dan-so-tinh">Cấp tỉnh</a></li>
					<li><a href="thong-ke-dan-vung">Vùng</a></li>
				</ul>
			</li>
	</c:if>
	<!-- Sổ hộ khẩu -->
	<c:if test="${quyen.tenQuyen eq 'XEM_HO_KHAU' }">
			<li><a href='danh-sach-so-ho-khau'>Danh sách sổ hộ khẩu</a></li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'NHAP_HO_KHAU' }">
			<li><a href='nhap-ho-khau'>Nhập sổ hộ khẩu</a></li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'DUYET_TACH_HO_KHAU' }">
			<li>
				<a href='danh-sach-lam-so-ho-khau'>
					DS đăng ký tách sổ hộ khẩu
					<c:if test="${soDonLamHoKhau  != null && soDonLamHoKhau != '0'}">	
						<sup><span class='message-menu'>${soDonLamHoKhau }</span></sup>
					</c:if>
				</a>
				<ul class="sub-menu">
					<li>
						<a href="danh-sach-lam-so-ho-khau-chua-duyet">
							Danh sách chưa duyệt
							<c:if test="${soDonLamHoKhau  != null && soDonLamHoKhau != '0'}">	
								<sup><span class='message-menu'>${soDonLamHoKhau }</span></sup>
							</c:if>
						</a>
					</li>
					<li><a href="danh-sach-lam-so-ho-khau-khong-duyet">Danh Sách từ chối</a></li>
				</ul>
			</li>
	</c:if>
	
	<c:if test="${quyen.tenQuyen eq 'DUYET_THEM_NHAN_KHAU' }">
			<li>
				<a href='danh-sach-them-nhan-khau-so-ho-khau'>
					DS đăng ký thêm nhân khẩu
					<c:if test="${soDonThemNhanKhau  != null && soDonThemNhanKhau != '0'}">	
						<sup><span class='message-menu'>${soDonThemNhanKhau }</span></sup>
					</c:if>
				</a>
				<ul class="sub-menu">
					<li>
						<a href="danh-sach-them-nhan-khau-so-ho-khau">
							Danh sách chưa duyệt
							<c:if test="${soDonThemNhanKhau  != null && soDonThemNhanKhau != '0'}">	
								<sup><span class='message-menu'>${soDonThemNhanKhau }</span></sup>
							</c:if>
						</a>
					</li>
					<li>
						<a href="danh-sach-khong-duyet-them-nhan-khau-so-ho-khau">
							Danh Sách từ chối
						</a>
					</li>
					<li>
						<a href="danh-sach-da-duyet-them-nhan-khau-so-ho-khau">
							Danh Sách đã duyệt
						</a>
					</li>
				</ul>
			</li>
	</c:if>
	
	<c:if test="${quyen.tenQuyen eq 'QUAN_LY_CAU_QUAN_HE' }">
		<li><a href="quan-ly-cau-hinh-quan-he-ho-khau">Cấu hình quan hệ hộ khẩu</a></li>
	</c:if>
	
	<!-- KHAI SINH -->
	<c:if test="${quyen.tenQuyen eq 'DUYET_KHAI_SINH_1' }">
			<li>
				<a href='danh-sach-dk-khai-sinh'>
					Xác nhận đăng ký khai sinh
					<c:if test="${soDonDKKSChoXacNhan  != null && soDonDKKSChoXacNhan != '0'}">	
						<sup><span class='message-menu'>${soDonDKKSChoXacNhan }</span></sup>
					</c:if>
				</a>
			</li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'DUYET_KHAI_SINH_2' }">
			<li>
				<a href='danh-sach-dk-khai-sinh'>
					Duyệt đăng ký khai sinh
					<c:if test="${soDonDKKSChoDuyet  != null && soDonDKKSChoDuyet != '0'}">	
						<sup><span class='message-menu'>${soDonDKKSChoDuyet }</span></sup>
					</c:if>
				</a>
			</li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'NHAP_KHAI_SINH' }">
			<li><a href='nhap-khai-sinh'>Nhập khai sinh (Cũ)</a></li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'XEM_KHAI_SINH' }">
			<li><a href='danh-sach-khai-sinh'>Xem khai sinh</a></li>
			<c:if test="${soKSTRongNgay  != null && soKSTRongNgay != '0'}">	
				<sup><span class='message-menu'>${soKSTRongNgay }</span></sup>
			</c:if>
	</c:if>
	
	<!-- 	KET HON -->
	<c:if test="${quyen.tenQuyen eq 'DUYET_KET_HON_1' }">
			<li>
				<a href='danh-sach-dk-ket-hon'>
					Danh sách đăng ký kết hôn cần xác nhận
				</a>
				<c:if test="${soDonDKKHChuaXacNhan  != null && soDonDKKHChuaXacNhan != '0'}">	
					<sup><span class='message-menu'>${soDonDKKHChuaXacNhan }</span></sup>
				</c:if>
			</li>
	</c:if>
	<c:if test="${quyen.tenQuyen eq 'DUYET_KET_HON_2' }">
			<li>
				<a href='danh-sach-dk-ket-hon'>
					Danh sách đăng ký kết hôn cần duyệt
				</a>
				<c:if test="${soDonDKKHChuaDuyet  != null && soDonDKKHChuaDuyet != '0'}">	
					<sup><span class='message-menu'>${soDonDKKHChuaDuyet }</span></sup>
				</c:if>
			</li>
	</c:if>

	<c:if test="${quyen.tenQuyen eq 'XEM_HON_NHAN' }">
			<li>
				<a href='danh-sach-hon-nhan'>Danh sách hôn nhân</a>
				<c:if test="${soHonNhan  != null && soHonNhan != '0'}">	
					<sup><span class='message-menu'>${soHonNhan }</span></sup>
				</c:if>
			</li>
	</c:if>
	
	
</c:forEach>
	</ul>
 </div>