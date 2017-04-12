package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkyong.dao.DanSoHuyenDao;
import com.mkyong.dao.DanSoTinhDao;
import com.mkyong.dao.DanSoVungDao;
import com.mkyong.dao.DanSoXaDao;
import com.mkyong.dao.DuyetDKKSDao;
import com.mkyong.dao.KhaiSinhDao;
import com.mkyong.dao.TTDKKhaiSinhDao;

import bean.Chung.DanSoHuyen;
import bean.Chung.DanSoTinh;
import bean.Chung.DanSoVung;
import bean.Chung.DanSoXa;
import bean.Chung.DuyetDKKS;
import bean.Chung.KhaiSinh;
import bean.Chung.TTDKKhaiSinh;
import bean.Chung.TaiKhoan;

public class KhaiSinhServiceImpl implements KhaiSinhService{
	/**.
	 * {@khaiSinhDao} KhaiSinhDao
	 */
	@Autowired(required=true)
	private KhaiSinhDao khaiSinhDao;
	/**.
	 * {@ttdkKhaiSinhDao} TTDKKhaiSinhDao
	 */
	@Autowired(required=true)
	private TTDKKhaiSinhDao ttdkKhaiSinhDao;
	/**.
	 * {@duyetDKKSDao} DuyetDKKSDao
	 */
	@Autowired(required=true)
	private DuyetDKKSDao duyetDKKSDao;
	@Autowired(required=true)
	private DanSoTinhDao danSoTinhDao;
	@Autowired(required=true)
	private DanSoHuyenDao danSoHuyenDao;
	@Autowired(required=true)
	private DanSoXaDao danSoXaDao;
	@Autowired(required=true)
	private DanSoVungDao danSoVungDao;
	
	@Override
	public List<KhaiSinh> getDSKhaiSinh(TaiKhoan tk, String ngayHienTai) {
		return khaiSinhDao.getDSKhaiSinh(tk, ngayHienTai);
	}

	@Override
	public Boolean themGiayKhaiSinh(KhaiSinh ks) {
		return khaiSinhDao.themGiayKhaiSinh(ks);
	}

	@Override
	public KhaiSinh getKhaiSinhBangSoKS(String soKS) {
		return khaiSinhDao.getKhaiSinhBangSoKS(soKS);
	}

	@Override
	public String taoSoKS() {
		return khaiSinhDao.taoSoKS();
	}

	@Override
	public Boolean capNhatKhaiSinh(KhaiSinh ks) {
		return khaiSinhDao.capNhatKhaiSinh(ks);
	}

	@Override
	public List<KhaiSinh> tkKhaiSinhBangHoTen(TaiKhoan tk, String hoTen) {
		return khaiSinhDao.tkKhaiSinhBangHoTen(tk, hoTen);
	}

	@Override
	public List<KhaiSinh> tkKhaiSinhBangSoKS(TaiKhoan tk, String soKS) {
		return khaiSinhDao.tkKhaiSinhBangSoKS(tk, soKS);
	}

	@Override
	public List<KhaiSinh> DSKhaiSinhBangMaHK(String soHK) {
		return khaiSinhDao.DSKhaiSinhBangMaHK(soHK);
	}

	@Override
	public Boolean themTTDKKhaiSinh(TTDKKhaiSinh ttdkKhaiSinh) {
		return ttdkKhaiSinhDao.themTTDKKhaiSinh(ttdkKhaiSinh);
	}

	@Override
	public String taoNgayHen(String ngayDangKy, String coQuan,
			int soDanTrongNgay) {
		return khaiSinhDao.taoNgayHen(ngayDangKy, coQuan, soDanTrongNgay);
	}

	@Override
	public TTDKKhaiSinh getHangCuoi() {
		return ttdkKhaiSinhDao.getHangCuoi();
	}

	@Override
	public TTDKKhaiSinh getTTDKKhaiSinhBangSoKS(String soKS) {
		return ttdkKhaiSinhDao.getTTDKKhaiSinhBangSoKS(soKS);
	}

	@Override
	public Boolean capNhatTTDKKhaiSinh(TTDKKhaiSinh ttdk) {
		return ttdkKhaiSinhDao.capNhatTTDKKhaiSinh(ttdk);
	}

	@Override
	public Boolean themDuyetDKKS(DuyetDKKS duyetDKKS) {
		return duyetDKKSDao.themDuyetDKKS(duyetDKKS);
	}

//	@Override
//	public Boolean xoaTTDKKhaiSinh(String soDK) {
//		return ttdkKhaiSinhDao.xoaTTDKKhaiSinh(soDK);
//	}

	@Override
	public Boolean capNhatTrangThai(String soDK, int trangThai, String ghiChu) {
		return ttdkKhaiSinhDao.capNhatTrangThai(soDK, trangThai, ghiChu);
	}

	@Override
	//Lay danh sach TTDKKhaiSinh
	public List<TTDKKhaiSinh> getDSTTDKKhaiSinh(String ngayHen, TaiKhoan tk, 
			String tenQuyen, int soLuongTrongNgay) {
		return ttdkKhaiSinhDao.getDSTTDKKhaiSinh(ngayHen, tk, tenQuyen, soLuongTrongNgay);
	}

	@Override
	public Boolean capNhatSoKS(String soKS, String soDK, 
			String nguoiDuyet, String ngayDuyet) {
		return duyetDKKSDao.capNhatSoKS(soKS, soDK, nguoiDuyet, ngayDuyet);
	}

	@Override
	public DuyetDKKS getDuyetDKKS(String soKS) {
		return duyetDKKSDao.getDuyetDKKS(soKS);
	}

	@Override
	public List<TTDKKhaiSinh> getDSTTDKKhaiSinhBangHoTen(TaiKhoan tk, String hoTen) {
		return ttdkKhaiSinhDao.getDSTTDKKhaiSinhBangHoTen(tk, hoTen);
	}

	@Override
	public List<TTDKKhaiSinh> getDSTTDKKhaiSinhBangSoKS(TaiKhoan tk, String soKS,
			int trangThai) {
		return ttdkKhaiSinhDao.getDSTTDKKhaiSinhBangSoKS(tk, soKS, trangThai);
	}

	@Override
	public List<TTDKKhaiSinh> getDSTTDKKhaiSinhBangSoCC(TaiKhoan tk,String soCC) {
		return ttdkKhaiSinhDao.getDSTTDKKhaiSinhBangSoCC(tk, soCC);
	}

	@Override
	public List<DanSoTinh> getDanSoTinh(int nam) {
		return danSoTinhDao.getDanSoTinh(nam);
	}
	@Override
	public List<DanSoHuyen> getDanSoHuyen(String maTinh, int nam) {
		return danSoHuyenDao.getDanSoHuyen(maTinh, nam);
	}
	@Override
	public KhaiSinh getNoiCapBangSoKS(String soKS) {
		// TODO Auto-generated method stub
		return khaiSinhDao.getNoiCapBangSoKS(soKS);
	}

	@Override
	public List<DanSoXa> getDanSoXaBangMaTinh(String maTinh, int nam) {
		return danSoXaDao.getDanSoXaBangMaTinh(maTinh, nam);
	}

	@Override
	public List<DanSoXa> getDanSoXaBangMaHuyen(String maHuyen, int nam) {
		return danSoXaDao.getDanSoXaBangMaHuyen(maHuyen, nam);
	}

	@Override
	public List<KhaiSinh> tkKhaiSinhBangNgayDuocDuyet(TaiKhoan tk, String ngayDuocDuyet) {
		return khaiSinhDao.tkKhaiSinhBangNgayDuocDuyet(tk, ngayDuocDuyet);
	}

	@Override
	public DanSoHuyen getDanSoHuyenBangMaHuyen(String maHuyen, int nam) {
		// TODO Auto-generated method stub
		return danSoHuyenDao.getDanSoHuyenBangMaHuyen(maHuyen, nam);
	}

	@Override
	public Boolean ktTonTaiSoKS(String soKS) {
		return khaiSinhDao.ktTonTaiSoKS(soKS);
	}

	@Override
	public List<DanSoVung> getDSDanSoVung(int nam, int soVung) {
		return danSoVungDao.getDSDanSoVung(nam, soVung);
	}

	@Override
	public List<TTDKKhaiSinh> getDSTTDKKhaiSinhDaDuyet(String ngayHen,
			TaiKhoan tk, int soLuongTrongNgay) {
		return ttdkKhaiSinhDao.getDSTTDKKhaiSinhDaDuyet(ngayHen, tk, soLuongTrongNgay);
	}

	@Override
	public Boolean capNhatGhiChu(String soDK, String lyDo, TaiKhoan tk) {
		return ttdkKhaiSinhDao.capNhatGhiChu(soDK, lyDo, tk);
	}

	@Override
	public long getTongDanSo(int nam) {
		return danSoTinhDao.getTongDanSo(nam);
	}

	@Override
	public String taoSoKSChoTTDK() {
		return ttdkKhaiSinhDao.taoSoKS();
	}

	@Override
	public List<TTDKKhaiSinh> getDSTTDKKhaiSinhBiTuChoi(TaiKhoan tk,
			int soLuongTrongNgay) {
		return ttdkKhaiSinhDao.getDSTTDKKhaiSinhBiTuChoi(tk, soLuongTrongNgay);
	}

	@Override
	public Boolean xoaDuyetDKKSBangSoKS(String soKS) {
		return duyetDKKSDao.xoaDuyetDKKSBangSoKS(soKS);
	}

	@Override
	public Boolean xoaKhaiSinh(String soKS) {
		return khaiSinhDao.xoaKhaiSinh(soKS);
	}

	@Override
	public Boolean xoaDuyetDKKSBangSoKS1(String soKS, String nguoiDuyet) {
		return duyetDKKSDao.xoaDuyetDKKSBangSoKS1(soKS, nguoiDuyet);
	}
	
}
