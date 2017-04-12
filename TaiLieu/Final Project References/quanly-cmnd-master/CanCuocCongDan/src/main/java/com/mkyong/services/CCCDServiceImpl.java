package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkyong.dao.CCCDDao;

import bean.Vu.CCCD;
import bean.Vu.TTDKCCCD;

public class CCCDServiceImpl implements CCCDService {
	/**.
	 * {@CCCDDao} CCCDDao
	 */
	@Autowired(required=true)
	private CCCDDao cccdDao;
	@Override
	public Boolean insertCCCD(TTDKCCCD cccd) {
		return cccdDao.insertCCCD(cccd);
	}
	@Override
	public Boolean updateCCCD(TTDKCCCD cccd, String soCCCu) {
		return cccdDao.updateCCCD(cccd, soCCCu);
	}
	@Override
	public Boolean deleteCCCD(String soCC) {
		return cccdDao.deleteCCCD(soCC);
	}
	@Override
	public List<CCCD> getCCCD(String soCC){
		return cccdDao.getCCCD(soCC);
	}
	@Override
	public CCCD getCCCDBangMa(String soCC) {
		return cccdDao.getCCCDBangMa(soCC);
	}
	@Override
	public bean.Chung.CCCD getCCCD1(String soCC) {
		return cccdDao.getCCCD1(soCC);
	}
	@Override
	public String maNgauNhien(String noiCap) {
		return cccdDao.maNgauNhien(noiCap);
	}
	@Override
	public CCCD layHangCuoi(String noiCap) {
		return cccdDao.layHangCuoi(noiCap);
	}
	@Override
	public String sinhMaCanCuoc(TTDKCCCD cccdTam) {
		return cccdDao.sinhMaCanCuoc(cccdTam);
	}
	@Override
	public List<CCCD> getDSCCCD() {
		// TODO Auto-generated method stub
		return cccdDao.getDSCCCD();
	}
	@Override
	public List<CCCD> getDSNguoiThan(String soCC) {
		// TODO Auto-generated method stub
		return cccdDao.getDSNguoiThan(soCC);
	}
	@Override
	public Boolean ktHuyetThong(String soCCA, String soCCB) {
		// TODO Auto-generated method stub
		return cccdDao.ktHuyetThong(soCCA, soCCB);
	}
	@Override
	public List<CCCD> getDSCCCDTheoTinh() {
		// TODO Auto-generated method stub
		return cccdDao.getDSCCCDTheoTinh();
	}
	@Override
	public List<CCCD> timKiemCCCD(String tuKhoa) {
		// TODO Auto-generated method stub
		return cccdDao.timKiemCCCD(tuKhoa);
	}
	@Override
	public List<CCCD> getDSCCCDTheoNguoiCap(String taiKhoan) {
		// TODO Auto-generated method stub
		return cccdDao.getDSCCCDTheoNguoiCap(taiKhoan);
	}
	@Override
	public Boolean updateCCCDSW(String set, String where) {
		// TODO Auto-generated method stub
		return cccdDao.updateCCCDSW(set, where);
	}
	@Override
	public Boolean nhapCCCD(TTDKCCCD cccd) {
		// TODO Auto-generated method stub
		return cccdDao.nhapCCCD(cccd);
	}
	@Override
	public Boolean changeSoCC9To12(String sql, String _12So, String _9So) {
		// TODO Auto-generated method stub
		return cccdDao.changeSoCC9To12(sql, _12So, _9So);
	}
}
