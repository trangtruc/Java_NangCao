package com.mkyong.services;

import java.util.List;

import bean.Chung.DanSoHuyen;
import bean.Chung.DanSoTinh;
import bean.Chung.DanSoVung;
import bean.Chung.DanSoXa;
import bean.Chung.DuyetDKKS;
import bean.Chung.KhaiSinh;
import bean.Chung.TTDKKhaiSinh;
import bean.Chung.TaiKhoan;

public interface KhaiSinhService {
	List<KhaiSinh> getDSKhaiSinh(TaiKhoan tk, String ngayHienTai);
	Boolean themGiayKhaiSinh(KhaiSinh ks);
	Boolean capNhatKhaiSinh(KhaiSinh ks);
	KhaiSinh getKhaiSinhBangSoKS(String soKS);
	String taoSoKS();
	List<KhaiSinh> tkKhaiSinhBangHoTen(TaiKhoan tk, String hoTen);
	List<KhaiSinh> tkKhaiSinhBangSoKS(TaiKhoan tk, String soKS);
	//Ham cua An
	List<KhaiSinh> DSKhaiSinhBangMaHK(String soHK);
	//Lay danh sach TTDKKhaiSinh
	List<TTDKKhaiSinh> getDSTTDKKhaiSinh(String ngayHen, TaiKhoan tk, 
				String tenQuyen, int soLuongTrongNgay);
	//Them mot hang vao TTDKKhaiSinh
	Boolean themTTDKKhaiSinh(TTDKKhaiSinh ttdkKhaiSinh);
	//Tao ngay hen
	String taoNgayHen(String ngayDangKy, String coQuan, int soDanTrongNgay);
	//Lay hang cuoi cua TTDK_KHAI_SINH
	TTDKKhaiSinh getHangCuoi();
	//Lay TTDK_KHAI_SINH bang soDK
	TTDKKhaiSinh getTTDKKhaiSinhBangSoKS(String soKS);
	//Cap nhat thay doi TTDK_KHAI_SINH
	Boolean capNhatTTDKKhaiSinh(TTDKKhaiSinh ttdk);
	//Them mot hang vao duyet_dkks
	Boolean themDuyetDKKS(DuyetDKKS duyetDKKS);
	//Xoa mot hang trong TTDK_KHAI_SINH
	//Boolean xoaTTDKKhaiSinh(String soKS);
	//Cap nhat lai TRANG_THAI trong bang TTDK_KHAI_SINH
	Boolean capNhatTrangThai(String soKS, int trangThai, String ghiChu);
	//Cap nhat soKS trong bang DUYET_DKKS
	Boolean capNhatSoKS(String soKS, String soDK, 
				String nguoiDuyet, String ngayDuyet);
	//Lay DUYET_DKKS bang soKS
	DuyetDKKS getDuyetDKKS(String soKS);
	//Lay TTDK_KHAI_SINH bang hoTen
	List<TTDKKhaiSinh> getDSTTDKKhaiSinhBangHoTen(TaiKhoan tk, String hoTen);
	//Lay TTDK_KHAI_SINH bang soCC va soKS
	List<TTDKKhaiSinh> getDSTTDKKhaiSinhBangSoCC(TaiKhoan tk, String soCC);
	//Lay TTDK_KHAI_SINH bang soKS, tra ve danh sach
	List<TTDKKhaiSinh> getDSTTDKKhaiSinhBangSoKS(TaiKhoan tk, String soKS, int trangThai);
	//Lay danh sach tinh cung dan so cua tung tinh
	List<DanSoTinh> getDanSoTinh(int nam);
	//Lay huyen do cung ti le dan so cua huyen so voi tinh
	List<DanSoHuyen> getDanSoHuyen(String maTinh, int nam);
	DanSoHuyen getDanSoHuyenBangMaHuyen(String maHuyen, int nam);
	// Lay noi cap trong bang Khai Sinh
	KhaiSinh getNoiCapBangSoKS(String soKS);
	//Lay dan so cua xa trong tinh, tinhs theo ty le
	List<DanSoXa> getDanSoXaBangMaTinh(String maTinh, int nam);
	//Lay dan so cua trong huyen, tinhs theo ty le %
	List<DanSoXa> getDanSoXaBangMaHuyen(String maHuyen, int nam);
	//Tim kiem khai sinh bang ngay sinh
	List<KhaiSinh> tkKhaiSinhBangNgayDuocDuyet(TaiKhoan tk, String ngayDuocDuyet);
	//Kiem tra da ton tai soKS
	Boolean ktTonTaiSoKS(String soKS);
	//Lay danh sach dan so cua 7 vung kinh te, tinh den nam dau vao
	//Vi du: Vung 1: 5 nguoi
	//		 Vung 2: 15 nguoi
	List<DanSoVung> getDSDanSoVung(int nam, int soVung);
	//Lay danh sach TTDK_KHAI_SINH da duoc duyet
	List<TTDKKhaiSinh> getDSTTDKKhaiSinhDaDuyet(String ngayHen, 
					TaiKhoan tk, int soLuongTrongNgay);
	//Cap nhat ghi chu duyet_dkks
	Boolean capNhatGhiChu(String soKS, String lyDo, TaiKhoan tk);
	//Tinh tong dan so ca nuoc den nam hien tai
	long getTongDanSo(int nam);
	String taoSoKSChoTTDK();
	//Lay danh sach TTDK_KHAI_SINH bi tu choi
	List<TTDKKhaiSinh> getDSTTDKKhaiSinhBiTuChoi(TaiKhoan tk, int soLuongTrongNgay);
	Boolean xoaDuyetDKKSBangSoKS(String soKS);
	//xoa khai_sinh
	Boolean xoaKhaiSinh(String soKS);
	//Boolean xoaDuyetDKKSBangSoKS
	Boolean xoaDuyetDKKSBangSoKS1(String soKS, String nguoiDuyet);
}
