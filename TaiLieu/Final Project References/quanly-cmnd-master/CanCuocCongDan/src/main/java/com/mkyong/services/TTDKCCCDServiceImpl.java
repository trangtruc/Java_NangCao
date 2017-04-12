package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkyong.dao.TTDKCCCDDao;

import bean.Vu.TTDKCCCD;

public class TTDKCCCDServiceImpl implements TTDKCCCDService {
	/**.
	 * {@CCCDDao} CCCDDao
	 */
	@Autowired(required=true)
	private TTDKCCCDDao cccdDao;
	@Override
	public Boolean insertTTDKCCCD(TTDKCCCD cccd) {
		return cccdDao.insertTTDKCCCD(cccd);
	}
	@Override
	public Boolean deleteTTDKCCCD(int maSo) {
		return cccdDao.deleteTTDKCCCD(maSo);
	}
	@Override
	public Boolean updateTTDKCCCD(String set, String where) {
		return cccdDao.updateTTDKCCCD(set, where);
	}
	@Override
	public List<TTDKCCCD> getDSTTDKCCCD() {
		return cccdDao.getDSTTDKCCCD();
	}
	
	@Override
	public List<TTDKCCCD> getDSNgayHen(String noiDangKy, String date) {
		return cccdDao.getDSNgayHen(noiDangKy, date);
	}
	
	@Override
	public List<TTDKCCCD> getDSCanBoXacNhan() {
		return cccdDao.getDSCanBoXacNhan();
	}
	@Override
	public List<TTDKCCCD> getDSCanBoDaXacNhan() {
		return cccdDao.getDSCanBoDaXacNhan();
	}
	@Override
	public List<TTDKCCCD> getDSGiamDocDuyet() {
		return cccdDao.getDSGiamDocDuyet();
	}
	@Override
	public TTDKCCCD getTTDKCCCDBangMa(String maSo) {
		return cccdDao.getTTDKCCCDBangMa(maSo);
	}
	@Override
	public List<bean.Vu.TTDKCCCD> timkiemTTDKCCCD(String tuKhoa) {
		return cccdDao.timkiemTTDKCCCD(tuKhoa);
	}
	
	@Override
	public Boolean capNhatTTDKCCCD(bean.Vu.TTDKCCCD cccdTam) {
		return cccdDao.capNhatTTDKCCCD(cccdTam);
	}
	@Override
	public int getMaxMaSo() {
		// TODO Auto-generated method stub
		return cccdDao.getMaxMaSo();
	}
	@Override
	public List<TTDKCCCD> getThongTinDangKy() {
		// TODO Auto-generated method stub
		return cccdDao.getThongTinDangKy();
	}
	@Override
	public List<TTDKCCCD> getDSQuaHan() {
		// TODO Auto-generated method stub
		return cccdDao.getDSQuaHan();
	}
	@Override
	public List<TTDKCCCD> getDSGiamDocDaDuyet() {
		// TODO Auto-generated method stub
		return cccdDao.getDSGiamDocDaDuyet();
	}
	@Override
	public List<TTDKCCCD> getDSGiamDocTuChoi() {
		// TODO Auto-generated method stub
		return cccdDao.getDSGiamDocTuChoi();
	}
	@Override
	public List<TTDKCCCD> timKiemTTDKChuaDuyet(String key) {
		// TODO Auto-generated method stub
		return cccdDao.timKiemTTDKChuaDuyet(key);
	}
	@Override
	public List<TTDKCCCD> timKiemTTDKDaDuyet(String key) {
		// TODO Auto-generated method stub
		return cccdDao.timKiemTTDKDaDuyet(key);
	}
	@Override
	public List<TTDKCCCD> timKiemTTDKBiTuChoi(String key) {
		// TODO Auto-generated method stub
		return cccdDao.timKiemTTDKBiTuChoi(key);
	}
	@Override
	public List<TTDKCCCD> thongKeDonDangKy(String thangNam) {
		// TODO Auto-generated method stub
		return cccdDao.thongKeDonDangKy(thangNam);
	}
	@Override
	public List<TTDKCCCD> thongKeDonDangKyDV(String dv, String thangNam) {
		// TODO Auto-generated method stub
		return cccdDao.thongKeDonDangKyDV(dv, thangNam);
	}
	@Override
	public List<TTDKCCCD> timkiemTTDKCCCDDaXacNhan(String tuKhoa) {
		// TODO Auto-generated method stub
		return cccdDao.timkiemTTDKCCCDDaXacNhan(tuKhoa);
	}
	@Override
	public List<TTDKCCCD> getDSCanBoXacNhanTheoNgay(String ngay) {
		// TODO Auto-generated method stub
		return cccdDao.getDSCanBoXacNhanTheoNgay(ngay);
	}
	@Override
	public List<TTDKCCCD> getDSCanBoDaXacNhanTheoNgay(String ngay) {
		// TODO Auto-generated method stub
		return cccdDao.getDSCanBoDaXacNhanTheoNgay(ngay);
	}
	@Override
	public Boolean xemTTDKCCCD(int maSo, String taiKhoan) {
		// TODO Auto-generated method stub
		return cccdDao.xemTTDKCCCD(maSo, taiKhoan);
	}
	@Override
	public Boolean daXemTTDKCCCD(int maSo, String taiKhoan) {
		// TODO Auto-generated method stub
		return cccdDao.daXemTTDKCCCD(maSo, taiKhoan);
	}
}
