package com.mkyong.dao;

import java.util.List;

import bean.An.DSQuanHe;
import bean.An.DSThemNhanKhau;
import bean.An.LienKetDuLieu;
import bean.An.SoHoKhau;
import bean.An.DSLamHoKhau;

public interface SoHoKhauDao {
	Boolean themSoHoKhau(String maSo, String diaChi, String coQuan);
	Boolean themChiTietSoHoKhau(String soHK, String soCC, String soKS, String quanHe, String ngayChuyenDen);
	Boolean themDangKySoHoKhau(String soHKCu, String soCCNguoiDK, String diaChi, String soCC,String soKS, String quanHe, String noiDKLamViec, String Duyet, String ngayDK, String ngayHen, String tinhTrang);//them dang ky tach so ho khau
	Boolean xoaHoKhau(String soHK);
	List<SoHoKhau> dsSoHoKhau(String coQuan);
	SoHoKhau getSoHoKhau(String where);
	SoHoKhau layHangCuoi(); //lay hang cuoi cao nhat trong sohokhau
	
	
	String taoMaSoHK(); // tao ma cho so ho khau
	String layTenQuanHe(String quanHe);
	String taoNgayHen(String ngayDK);
	
	List<DSQuanHe> dsQuanHe();
	List<SoHoKhau> dsSoHoKhauBangTuKhoa(String noiDKLV, String tuKhoa);
	List<SoHoKhau> getSoHoKhauBangMa(String soHK); //lay dnah sach lam so ho khau bang ma 
	List<DSLamHoKhau> dsLamHoKhauChuaDuyet(String noiDKLV);
	List<DSLamHoKhau> dsLamHoKhauKhongDuyet(String noiDKLV);
	List<DSLamHoKhau> dsLamHoKhauBangTuKhoa(String tuKhoa);
	List<DSLamHoKhau> dsLamHoKhauBangMa(String soCC); // so CC cua nguoi dnag ky
	List<DSThemNhanKhau> dsDangKyThemNhanKhau(String noiDKLV);//danh sach so ho khau dang ky them thanh vien 
	List<DSThemNhanKhau> dsNhanKhauDK(String soHKMoi);//danh sach nhung nhan khau duoc dang ky 
	List<DSThemNhanKhau> dsDangKyThemNhanKhauBangTuKhoa(String noiDKLV, String tuKhoa);
	
	
	Boolean duyetLamHoKhau(String soCCNguoiDK); //chinh sua cot DA_KIEM_DUYET trong bang dang ky shk thanh 1
	Boolean chuyenHoKhau(String soHKMoi, String soKSThanhVien, String quanHe, String ngayChuyenDen, String tinhTrang);// cap nhat so ho khau moi cho thanh vien trong chi tiet ho khau
	Boolean khongDuyet(String soCCNguoiDK, String lydo);
	Boolean dkThemThanhVien(String soHKCu, String soHKMoi, String soKS, String soCC, String quanHe, String ngayDK,String ngayHen, String noiDKLamViec, String tinhTrang);//nguoi dan dangky them thanh vien trong bat ky so ho khau nao
	Boolean duyetThemNhanKhau(String duyet, String soHKMoi, String ngayDuyet, String nguoiDuyet, String lyDo); //chinh sua cot DA_KIEM_DUYET NGAY_DUYET trong bang them_nhan_khau thanh 1
	Boolean capNhatQuanHe(DSQuanHe dsQuanHe, String maQuanHe);
	Boolean themQuanHe(DSQuanHe dsQuanHe);
	Boolean xoaQuanHe(String maQuanHe);
	Boolean capNhatTinhTrangSHK(String soKS, String tinhTrang);
	
	LienKetDuLieu thongTinCaNhan(String soKS);
	
	// ham Vu handsome viet
	SoHoKhau getSoCCBangSoKS(String soKS);
	SoHoKhau getSoKSBangSoCC(String soCC);
	Boolean updateSoCC(SoHoKhau shk);
	Boolean updateSoKS(SoHoKhau shk);
	List<SoHoKhau> getDSNhapKhaiSinh();
}
