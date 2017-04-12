package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bean.Chung.DuyetDKKH;
import bean.Chung.HonNhan;
import bean.Chung.TTDKKetHon;
import bean.Chung.TaiKhoan;

import com.mkyong.dao.DuyetDKKHDao;
import com.mkyong.dao.HonNhanDao;
import com.mkyong.dao.TTDKKetHonDao;

public class HonNhanServiceImpl implements HonNhanService {
	/**.
	 * {@honNhanDao} HonNhanDao
	 */
	@Autowired(required=true)
	private HonNhanDao honNhanDao;
	/**.
	 * {@ttdkKetHonDao} TTDKKetHonDao
	 */
	@Autowired(required=true)
	private TTDKKetHonDao ttdkKetHonDao;
	/**.
	 * {@duyetDKKHDao} DuyetDKKHDao
	 */
	@Autowired(required=true)
	private DuyetDKKHDao duyetDKKHDao;
	
	
	@Override
	public Boolean ktTuoiKH(String ngaySinh, int tuoiQuyDinh) {
		// TODO Auto-generated method stub
		return honNhanDao.ktTuoiKH(ngaySinh, tuoiQuyDinh);
	}
	
	//Kiem tra gioi tinh giua vo va chong (Vo chong phai khac gioi tinh)
	@Override
	public Boolean ktGioiTinh(String gioiTinhA, String gioiTinhB) {
		// TODO Auto-generated method stub
		return honNhanDao.ktGioiTinh(gioiTinhA, gioiTinhB);
	}
	@Override
	public List<TTDKKetHon> getDSTTDKKetHon(int trangThai, String noiDKLV, String ngayHen, int soDonGioiHan) {
		return ttdkKetHonDao.getDSTTDKKetHon(trangThai, noiDKLV, ngayHen, soDonGioiHan);
	}
	@Override
	public TTDKKetHon getTTTDKKetHon(String soDK) {
		return ttdkKetHonDao.getTTTDKKetHon(soDK);
	}

	@Override
	public Boolean ktDaKetHon(String soCC, String gioiTinh) {
		return honNhanDao.ktDaKetHon(soCC, gioiTinh);
	}

	@Override
	public Boolean capNhatTTDKKetHon(TTDKKetHon ttdk) {
		return ttdkKetHonDao.capNhatTTDKKetHon(ttdk);
	}

	@Override
	public List<TTDKKetHon> getDSTTTDKKetHon(String soDK) {
		return ttdkKetHonDao.getDSTTTDKKetHon(soDK);
	}

	@Override
	public List<TTDKKetHon> getDSTTDKKetHonBangSoCC(String soCC) {
		return ttdkKetHonDao.getDSTTDKKetHonBangSoCC(soCC);
	}

	@Override
	public List<TTDKKetHon> getDSDKKHTheoNgayDK(String ngay) {
		return ttdkKetHonDao.getDSDKKHTheoNgayDK(ngay);
	}

	@Override
	public List<TTDKKetHon> getDSDKKHTheoNgayHen(String ngay) {
		return ttdkKetHonDao.getDSDKKHTheoNgayHen(ngay);
	}

	@Override
	public List<TTDKKetHon> getDSDKKHTheoNgayNhanGiay(String ngay) {
		return ttdkKetHonDao.getDSDKKHTheoNgayNhanGiay(ngay);
	}

	@Override
	public Boolean themDuyetDKKH(DuyetDKKH duyetDKKH) {
		return duyetDKKHDao.themDuyetDKKH(duyetDKKH);
	}

	@Override
	public HonNhan getHonNhanBangSoCC(String soCC) {
		return honNhanDao.getHonNhanBangSoCC(soCC);
	}

	@Override
	public Boolean themTTDKKetHon(TTDKKetHon ttdk) {
		return ttdkKetHonDao.themTTDKKetHon(ttdk);
	}

	@Override
	public Boolean xacNhanDK(String soCCA, String soCCB, String maXacNhan) {
		return ttdkKetHonDao.xacNhanDK(soCCA, soCCB, maXacNhan);
	}

	@Override
	public Boolean xacNhanDK(String soCC, String maXacNhan) {
		return ttdkKetHonDao.xacNhanDK(soCC, maXacNhan);
	}

	@Override
	public Boolean capNhatTrangThai(TTDKKetHon ttdk, int trangThai) {
		return ttdkKetHonDao.capNhatTrangThai(ttdk, trangThai);
	}

	@Override
	public Boolean capNhatTrangThai(String soDK, int trangThai, String lyDo) {
		return ttdkKetHonDao.capNhatTrangThai(soDK, trangThai, lyDo);
	}

	@Override
	public TTDKKetHon getDKKetHonBangSoCCNguoiDK(String soCC) {
		return ttdkKetHonDao.getDKKetHonBangSoCCNguoiDK(soCC);
	}

	@Override
	public List<TTDKKetHon> getDKKetHonBangSoCCVoHoacChong(String soCC) {
		return ttdkKetHonDao.getDKKetHonBangSoCCVoHoacChong(soCC);
	}

	@Override
	public List<HonNhan> getDSHonNhan(TaiKhoan tk, String ngayDuyet,
			int soDonTrongNgay) {
		return honNhanDao.getDSHonNhan(tk, ngayDuyet, soDonTrongNgay);
	}

	@Override
	public int taoSoDK() {
		return honNhanDao.taoSoDK();
	}

	@Override
	public Boolean themHonNhan(HonNhan hn) {
		return honNhanDao.themHonNhan(hn);
	}

	@Override
	public List<HonNhan> getHonNhan(String inputTimKiem) {
		return honNhanDao.getHonNhan(inputTimKiem);
	}

	@Override
	public HonNhan getHonNhanBangSoDK(int soDK) {
		return honNhanDao.getHonNhanBangSoDK(soDK);
	}

	@Override
	public Boolean ktXacNhan(String soCC, int trangThai) {
		return ttdkKetHonDao.ktXacNhan(soCC, trangThai);
	}

	@Override
	public List<TTDKKetHon> getDSTTDKKetHonDaDuyet(String ngayHen, TaiKhoan tk,
			int soLuongTrongNgay) {
		return ttdkKetHonDao.getDSTTDKKetHonDaDuyet(ngayHen, tk, soLuongTrongNgay);
	}

	@Override
	public List<TTDKKetHon> getDSTTDKKetHonBiTuChoi(TaiKhoan tk,
			int soLuongTrongNgay) {
		return ttdkKetHonDao.getDSTTDKKetHonBiTuChoi(tk, soLuongTrongNgay);
	}

	@Override
	public Boolean xoaDuyetDKKHBangSoDK(String soDK) {
		return duyetDKKHDao.xoaDuyetDKKHBangSoDK(soDK);
	}

	@Override
	public Boolean xoaDuyetDKKHBangSoDK(String soDK, String nguoiDuyet) {
		return duyetDKKHDao.xoaDuyetDKKHBangSoDK(soDK, nguoiDuyet);
	}

	@Override
	public Boolean xoaHonNhan(String soCCChong) {
		return honNhanDao.xoaHonNhan(soCCChong);
	}


}
