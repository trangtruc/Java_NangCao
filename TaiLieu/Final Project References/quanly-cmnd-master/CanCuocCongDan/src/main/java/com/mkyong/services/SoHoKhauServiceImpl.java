package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkyong.dao.SoHoKhauDao;
import com.mkyong.services.SoHoKhauService;

import bean.An.DSLamHoKhau;
import bean.An.DSQuanHe;
import bean.An.DSThemNhanKhau;
import bean.An.LienKetDuLieu;
import bean.An.SoHoKhau;

public class SoHoKhauServiceImpl implements SoHoKhauService {
	
	@Autowired(required=true)
	private SoHoKhauDao shkDao;

	@Override
	public List<DSQuanHe> dsQuanHe() {
		// TODO Auto-generated method stub
		return shkDao.dsQuanHe();
	}
	
	@Override
	public String layTenQuanHe(String quanHe) {
		// TODO Auto-generated method stub
		return shkDao.layTenQuanHe(quanHe);
	}
	
	@Override
	public Boolean themSoHoKhau(String maSo, String diaChi, String coQuan) {
		// TODO Auto-generated method stub
		return shkDao.themSoHoKhau(maSo, diaChi, coQuan);
	}
	
	@Override
	public Boolean themChiTietSoHoKhau(String soHK, String soCC, String soKS, String quanHe, String ngayChuyenDen) {
		// TODO Auto-generated method stub
		return shkDao.themChiTietSoHoKhau(soHK, soCC, soKS, quanHe, ngayChuyenDen);
	}

	@Override
	public Boolean xoaHoKhau(String soHK) {
		// TODO Auto-generated method stub
		return shkDao.xoaHoKhau(soHK);
	}

	@Override
	public List<SoHoKhau> dsSoHoKhau(String coQuan) {
		// TODO Auto-generated method stub
		return shkDao.dsSoHoKhau(coQuan);
	}

	@Override
	public SoHoKhau layHangCuoi() {
		// TODO Auto-generated method stub
		return shkDao.layHangCuoi();
	}

	@Override
	public String taoMaSoHK() {
		// TODO Auto-generated method stub
		System.out.println("Vao service impl.");
		return shkDao.taoMaSoHK();
	}


	@Override
	public List<SoHoKhau> getSoHoKhauBangMa(String soHK) {
		// TODO Auto-generated method stub
		//System.out.println("Vao service Impl: ");
		return shkDao.getSoHoKhauBangMa(soHK);
	}

	@Override
	public SoHoKhau getSoHoKhau(String where) {
		// TODO Auto-generated method stub
		return shkDao.getSoHoKhau(where);
	}

	@Override
	public Boolean themDangKySoHoKhau(String soHKCu, String soCCNguoiDK, String diaChi,
			String soCC, String soKS, String quanHe, String noiDKLamViec, String duyet, String ngayDK, String ngayHen, String tinhTrang) {
		// TODO Auto-generated method stub
		return shkDao.themDangKySoHoKhau(soHKCu, soCCNguoiDK, diaChi, soCC, soKS, quanHe, noiDKLamViec, duyet, ngayDK, ngayHen, tinhTrang);
	}

	@Override
	public List<DSLamHoKhau> dsLamHoKhauChuaDuyet(String noiDKLV) {
		// TODO Auto-generated method stub
		return shkDao.dsLamHoKhauChuaDuyet(noiDKLV);
	}

	@Override
	public List<DSLamHoKhau> dsLamHoKhauBangTuKhoa(String tuKhoa) {
		// TODO Auto-generated method stub
		return shkDao.dsLamHoKhauBangTuKhoa(tuKhoa);
	}
	
	@Override
	public List<DSLamHoKhau> dsLamHoKhauBangMa(String soCC) {
		// TODO Auto-generated method stub
		return shkDao.dsLamHoKhauBangMa(soCC);
	}

	@Override
	public Boolean duyetLamHoKhau(String soCCNguoiDK) {
		// TODO Auto-generated method stub
		return shkDao.duyetLamHoKhau(soCCNguoiDK);
	}

	@Override
	public Boolean chuyenHoKhau(String soHKMoi, String soKSThanhVien, String quanHe, String ngayChuyenDen, String tinhTrang) {
		// TODO Auto-generated method stub
		return shkDao.chuyenHoKhau(soHKMoi, soKSThanhVien, quanHe, ngayChuyenDen, tinhTrang);
	}

	
    // ham cua Vu handsome viet
	@Override
	public SoHoKhau getSoCCBangSoKS(String soKS) {
		// TODO Auto-generated method stub
		return shkDao.getSoCCBangSoKS(soKS);
	}

	@Override
	public List<DSLamHoKhau> dsLamHoKhauKhongDuyet(String noiDKLV) {
		// TODO Auto-generated method stub
		return shkDao.dsLamHoKhauKhongDuyet(noiDKLV);
	}

	@Override
	public Boolean updateSoCC(SoHoKhau shk) {
		// TODO Auto-generated method stub
		return shkDao.updateSoCC(shk);
	}
	public Boolean khongDuyet(String soCCNguoiDK, String lydo) {
		// TODO Auto-generated method stub
		return shkDao.khongDuyet(soCCNguoiDK, lydo);
	}

	@Override
	public SoHoKhau getSoKSBangSoCC(String soCC) {
		// TODO Auto-generated method stub
		return shkDao.getSoKSBangSoCC(soCC);
	}
	public Boolean dkThemThanhVien(String soHKCu, String soHKMoi, String soKS,
			String soCC, String quanHe, String ngayDK, String ngayHen, String noiDKLamViec, String tinhTrang) {
		// TODO Auto-generated method stub
		return shkDao.dkThemThanhVien(soHKCu, soHKMoi, soKS, soCC, quanHe, ngayDK, ngayHen, noiDKLamViec, tinhTrang);
	}

	@Override
	public List<DSThemNhanKhau> dsDangKyThemNhanKhau(String noiDKLV) {
		// TODO Auto-generated method stub
		return shkDao.dsDangKyThemNhanKhau(noiDKLV);
	}

	@Override
	public List<DSThemNhanKhau> dsNhanKhauDK(String soHKMoi) {
		// TODO Auto-generated method stub
		return shkDao.dsNhanKhauDK(soHKMoi);
	}

	@Override
	public Boolean duyetThemNhanKhau(String duyet, String soHKMoi, String ngayDuyet, String nguoiDuyet, String lyDo) {
		// TODO Auto-generated method stub
		return shkDao.duyetThemNhanKhau(duyet, soHKMoi, ngayDuyet, nguoiDuyet, lyDo);
	}

	@Override
	public List<DSThemNhanKhau> dsDangKyThemNhanKhauBangTuKhoa(String noiDKLV,
			String tuKhoa) {
		// TODO Auto-generated method stub
		return shkDao.dsDangKyThemNhanKhauBangTuKhoa(noiDKLV, tuKhoa);
	}
	public List<SoHoKhau> getDSNhapKhaiSinh() {
		// TODO Auto-generated method stub
		return shkDao.getDSNhapKhaiSinh();
	}

	@Override
	public Boolean updateSoKS(SoHoKhau shk) {
		// TODO Auto-generated method stub
		return shkDao.updateSoKS(shk);
	}

	@Override
	public List<SoHoKhau> dsSoHoKhauBangTuKhoa(String noiDKLV, String tuKhoa) {
		// TODO Auto-generated method stub
		return shkDao.dsSoHoKhauBangTuKhoa(noiDKLV, tuKhoa);
	}

	@Override
	public Boolean capNhatQuanHe(DSQuanHe dsQuanHe, String maQuanHe) {
		// TODO Auto-generated method stub
		return shkDao.capNhatQuanHe(dsQuanHe, maQuanHe);
	}

	@Override
	public Boolean themQuanHe(DSQuanHe dsQuanHe) {
		// TODO Auto-generated method stub
		return shkDao.themQuanHe(dsQuanHe);
	}

	@Override
	public Boolean xoaQuanHe(String maQuanHe) {
		// TODO Auto-generated method stub
		return shkDao.xoaQuanHe(maQuanHe);
	}

	@Override
	public LienKetDuLieu thongTinCaNhan(String soKS) {
		// TODO Auto-generated method stub
		return shkDao.thongTinCaNhan(soKS);
	}

	@Override
	public String taoNgayHen(String ngayDK) {
		// TODO Auto-generated method stub
		return shkDao.taoNgayHen(ngayDK);
	}

	@Override
	public Boolean capNhatTinhTrangSHK(String soKS, String tinhTrang) {
		// TODO Auto-generated method stub
		return shkDao.capNhatTinhTrangSHK(soKS, tinhTrang);
	}

	

	
	

}
