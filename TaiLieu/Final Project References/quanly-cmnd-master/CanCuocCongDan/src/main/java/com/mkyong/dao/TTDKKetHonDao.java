package com.mkyong.dao;

import java.util.List;

import bean.Chung.TTDKKetHon;
import bean.Chung.TaiKhoan;

public interface TTDKKetHonDao {
	//Get danh sach TTDKKetHon chua duoc duyet
	List<TTDKKetHon> getDSTTDKKetHon(int trangThai, String noiDKLV, String ngayHen, int soDonGioiHan);
	//Cap nhat TTDKKetHon
	Boolean capNhatTTDKKetHon(TTDKKetHon ttdk);
	//Get TTDKKetHon bang ma
	TTDKKetHon getTTTDKKetHon(String soDK);
	//Get TTDKKetHon bang soDK, tra ve danh sach de tien xu ly
	List<TTDKKetHon> getDSTTTDKKetHon(String soDK);
	//Get danh sach TTDKKetHon bang soCC
	List<TTDKKetHon> getDSTTDKKetHonBangSoCC(String soCC);
	//Get danh sach TTDKKetHon theo ngayDangKy
	List<TTDKKetHon> getDSDKKHTheoNgayDK(String ngay);
	//Get danh sach TTDKKetHon theo ngayHen
	List<TTDKKetHon> getDSDKKHTheoNgayHen(String ngay);
	//Get danh sach TTDKKetHon theo ngayNhanGiay
	List<TTDKKetHon> getDSDKKHTheoNgayNhanGiay(String ngay);
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
	//Kiem tra nguoi da xac nhan yeu cau ket hon chua
	Boolean ktXacNhan(String soCC, int trangThai);
	//Get danh sach TTDKKetHon chua duoc duyet
	List<TTDKKetHon> getDSTTDKKetHonDaDuyet(String ngayHen, TaiKhoan tk, int soLuongTrongNgay);
	//Get danh sach TTDKKetHon bi tu choi
	List<TTDKKetHon> getDSTTDKKetHonBiTuChoi(TaiKhoan tk, int soLuongTrongNgay);
	
}
