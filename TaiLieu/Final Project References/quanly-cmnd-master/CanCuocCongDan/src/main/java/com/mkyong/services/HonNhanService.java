package com.mkyong.services;

import java.util.List;

import bean.Chung.DuyetDKKH;
import bean.Chung.HonNhan;
import bean.Chung.TTDKKetHon;
import bean.Chung.TaiKhoan;

public interface HonNhanService {
	
	//Kiem tra gioi tinh giua vo va chong, vo chong phai khac gioi tinh
	Boolean ktGioiTinh(String gioiTinhA, String gioiTinhB);
	//Kiem tra da ket hon
	Boolean ktDaKetHon(String soCC, String gioiTinh);
	//Kiem tra do tuoi co the ket hon
	Boolean ktTuoiKH(String ngaySinh, int tuoiQuyDinh);
	//Get danh sach TTDKKetHon chua duoc duyet
	List<TTDKKetHon> getDSTTDKKetHon(int trangThai, String noiDKLV, String ngayHen, int soDonGioiHan);
	//Get TTDKKetHon bang ma
	TTDKKetHon getTTTDKKetHon(String soDK);
	//Get TTDKKetHon bang ma, tra ve danh sach de tien xu ly
	List<TTDKKetHon> getDSTTTDKKetHon(String soDK);
	//Cap nhat TTDKKetHon
	Boolean capNhatTTDKKetHon(TTDKKetHon ttdk);
	//Get danh sach TTDKKetHon bang soCC
	List<TTDKKetHon> getDSTTDKKetHonBangSoCC(String soCC);
	//Get danh sach TTDKKetHon theo ngayDangKy
	List<TTDKKetHon> getDSDKKHTheoNgayDK(String ngay);
	//Get danh sach TTDKKetHon theo ngayHen
	List<TTDKKetHon> getDSDKKHTheoNgayHen(String ngay);
	//Get danh sach TTDKKetHon theo ngayNhanGiay
	List<TTDKKetHon> getDSDKKHTheoNgayNhanGiay(String ngay);
	//Them mot hang moi vao bang DUYET_DKKH
	Boolean themDuyetDKKH(DuyetDKKH duyetDKKH);
	
	// Lay thong tin ket hon
	HonNhan getHonNhanBangSoCC(String soCC);
	//Them ttdk_hon_nhan
	Boolean themTTDKKetHon(TTDKKetHon ttdk);
	//Xac nhan dang ky ket hon
	Boolean xacNhanDK(String soCCA, String soCCB, String maXacNhan);
	//Xac nhan dang ky ket hon
	Boolean xacNhanDK(String soCC, String maXacNhan);
	//Cap nhat trang thai TTDK_KET_HON
	Boolean capNhatTrangThai(TTDKKetHon ttdk, int trangThai);
	//Cap nhat trang thai TTDK_KET_HON
	Boolean capNhatTrangThai(String soDK, int trangThai, String lyDo);
	//Get danh sach TTDKKetHon bang soCCNguoiDK
	TTDKKetHon getDKKetHonBangSoCCNguoiDK(String soCC);
	//Get danh sach TTDKKetHon bang soCCNguoiDK
	List<TTDKKetHon> getDKKetHonBangSoCCVoHoacChong(String soCC);
	//Lay danh sach hon nhan
	List<HonNhan> getDSHonNhan(TaiKhoan tk, String ngayDuyet, int soDonTrongNgay);
	//Tao soDK
	int taoSoDK();
	//Them hon_nhan
	Boolean themHonNhan(HonNhan hn);
	//Tim kiem hon nhan
	List<HonNhan> getHonNhan(String inputTimKiem);
	//Get HonNhan bang soDK
	HonNhan getHonNhanBangSoDK(int soDK);
	//Kiem tra nguoi da xac nhan yeu cau ket hon chua
	Boolean ktXacNhan(String soCC, int trangThai);
	//Get danh sach TTDKKetHon chua duoc duyet
	List<TTDKKetHon> getDSTTDKKetHonDaDuyet(String ngayHen, TaiKhoan tk, int soLuongTrongNgay);
	//Get danh sach TTDKKetHon bi tu choi
	List<TTDKKetHon> getDSTTDKKetHonBiTuChoi(TaiKhoan tk, int soLuongTrongNgay);
	//Xoa duyet TTDK_KetHon
	Boolean xoaDuyetDKKHBangSoDK(String soDK);
	//Xoa duyet TTDK_KetHon
	Boolean xoaDuyetDKKHBangSoDK(String soDK, String nguoiDuyet);
	//Xoa hon_nhan
	Boolean xoaHonNhan(String soCCChong);
}
