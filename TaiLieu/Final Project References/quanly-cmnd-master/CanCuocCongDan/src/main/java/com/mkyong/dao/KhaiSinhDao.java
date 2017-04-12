package com.mkyong.dao;
import java.util.Calendar;
import java.util.List;

import bean.Chung.KhaiSinh;
import bean.Chung.TaiKhoan;

public interface KhaiSinhDao {
	
	List<KhaiSinh> getDSKhaiSinh(TaiKhoan tk, String ngayHienTai);
	Boolean themGiayKhaiSinh(KhaiSinh ks);
	KhaiSinh getKhaiSinhBangSoKS(String soKS);
	Boolean capNhatKhaiSinh(KhaiSinh ks);
	KhaiSinh layHangCuoi();
	String taoSoKS();
	List<KhaiSinh> tkKhaiSinhBangHoTen(TaiKhoan tk, String hoTen);
	List<KhaiSinh> tkKhaiSinhBangSoKS(TaiKhoan tk, String soKS);
	List<KhaiSinh> DSKhaiSinhBangMaHK(String soHK);
	//Kiem tra xem hen nay co full so luong chua (Trong mot co quan) cua Tin
	Boolean ktFullSoDonDKTrongNgay(Calendar ngay, String coQuan, int soDonTrongNgay);
	//Tao ngay hen
	String taoNgayHen(String ngayDangKy, String coQuan, int soDanTrongNgay);
	//Lay noi cap trong khai sinh
	KhaiSinh getNoiCapBangSoKS(String soKS);
	//Tim kiem khai sinh bang ngay sinh
	List<KhaiSinh> tkKhaiSinhBangNgayDuocDuyet(TaiKhoan tk, String ngayDuocDuyet);
	//Kiem tra da ton tai soKS
	Boolean ktTonTaiSoKS(String soKS);
	//xoa khai_sinh
	Boolean xoaKhaiSinh(String soKS);
}
