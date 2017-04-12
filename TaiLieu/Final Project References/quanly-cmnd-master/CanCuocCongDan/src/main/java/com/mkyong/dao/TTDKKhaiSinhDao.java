package com.mkyong.dao;

import java.util.List;

import bean.Chung.TTDKKhaiSinh;
import bean.Chung.TaiKhoan;

public interface TTDKKhaiSinhDao {

	//Lay danh sach TTDKKhaiSinh
	List<TTDKKhaiSinh> getDSTTDKKhaiSinh(String ngayHen, TaiKhoan tk, 
			String tenQuyen, int soLuongTrongNgay);
	//Them mot hang vao TTDKKhaiSinh
	Boolean themTTDKKhaiSinh(TTDKKhaiSinh ttdkKhaiSinh);
	//Lay hang cuoi cua TTDK_KHAI_SINH
	TTDKKhaiSinh getHangCuoi();
	//Lay TTDK_KHAI_SINH bang soDK
	TTDKKhaiSinh getTTDKKhaiSinhBangSoKS(String soKS);
	//Cap nhat thay doi TTDK_KHAI_SINH
	Boolean capNhatTTDKKhaiSinh(TTDKKhaiSinh ttdk);
	//Xoa mot hang trong TTDK_KHAI_SINH
	Boolean xoaTTDKKhaiSinh(String soKS);
	//Cap nhat lai TRANG_THAI trong bang TTDK_KHAI_SINH
	Boolean capNhatTrangThai(String soKS, int trangThai, String ghiChu);
	//Lay TTDK_KHAI_SINH bang hoTen
	List<TTDKKhaiSinh> getDSTTDKKhaiSinhBangHoTen(TaiKhoan tk, String hoTen);
	//Lay TTDK_KHAI_SINH bang soCC va soDK
	List<TTDKKhaiSinh> getDSTTDKKhaiSinhBangSoCC(TaiKhoan tk, String soCC);
	//Lay TTDK_KHAI_SINH bang soDK, tra ve danh sach
	List<TTDKKhaiSinh> getDSTTDKKhaiSinhBangSoKS(TaiKhoan tk, String soKS, int trangThai);
	//Lay danh sach TTDK_KHAI_SINH da duoc duyet
	List<TTDKKhaiSinh> getDSTTDKKhaiSinhDaDuyet(String ngayHen, 
			TaiKhoan tk, int soLuongTrongNgay);
	//Cap nhat ghi chu duyet_dkks
	Boolean capNhatGhiChu(String soKS, String lyDo, TaiKhoan tk);
	TTDKKhaiSinh layHangCuoi();
	String taoSoKS();
	//Lay danh sach TTDK_KHAI_SINH bi tu choi
	List<TTDKKhaiSinh> getDSTTDKKhaiSinhBiTuChoi(TaiKhoan tk, int soLuongTrongNgay);
	
}
