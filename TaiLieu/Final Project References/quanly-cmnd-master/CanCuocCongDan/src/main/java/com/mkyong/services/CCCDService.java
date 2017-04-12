package com.mkyong.services;

import java.util.List;

import bean.Vu.CCCD;
import bean.Vu.TTDKCCCD;

public interface CCCDService {
	
	Boolean insertCCCD(TTDKCCCD cccd_TamThoi);
	Boolean nhapCCCD(TTDKCCCD cccd);
	Boolean updateCCCD(TTDKCCCD cccd, String soCCCu);
	Boolean updateCCCDSW(String set, String where);
	Boolean changeSoCC9To12(String sql, String _12So, String _9So);
	List<CCCD> getCCCD(String soCC);
	List<CCCD> getDSCCCD();
	List<CCCD> getDSCCCDTheoTinh();
	List<CCCD> getDSCCCDTheoNguoiCap(String taiKhoan);
	List<CCCD> timKiemCCCD(String tuKhoa);
	Boolean deleteCCCD(String soCC);
	CCCD getCCCDBangMa(String soCC);
	//Ham get CCCD cua Tin
	bean.Chung.CCCD getCCCD1(String soCC);
	//Lay so cc cha, so cc me, so cc ong ba noi, so cc ong ba ngoai
	List<bean.Vu.CCCD> getDSNguoiThan(String soCC);
	//Kiem tra quan he huyet thong giua A va B
	Boolean ktHuyetThong(String soCCA, String soCCB);
	CCCD layHangCuoi(String noiCap);// lấy hàng cuối trong danh sách cccd
	String maNgauNhien(String noiCap); // mã ngẫu nhiên 6 số cuối
	String sinhMaCanCuoc(TTDKCCCD cccdTam);//sinh mã căn cước

}
