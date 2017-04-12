package com.mkyong.services;

import java.util.List;

import bean.Vu.TTDKCCCD;

public interface TTDKCCCDService {
	
	Boolean insertTTDKCCCD(TTDKCCCD cccd);
	List<TTDKCCCD> getDSTTDKCCCD();
	List<TTDKCCCD> getDSQuaHan();
	List<TTDKCCCD> getDSNgayHen(String noiDangKy, String date);
	List<TTDKCCCD> getDSCanBoXacNhan();
	List<TTDKCCCD> getDSCanBoDaXacNhan();
	List<TTDKCCCD> getDSGiamDocDuyet();
	List<TTDKCCCD> getDSGiamDocDaDuyet();
	List<TTDKCCCD> getDSGiamDocTuChoi();
	List<TTDKCCCD> getThongTinDangKy();
	List<TTDKCCCD> getDSCanBoXacNhanTheoNgay(String ngay);
	List<TTDKCCCD> getDSCanBoDaXacNhanTheoNgay(String ngay);
	List<TTDKCCCD> timKiemTTDKChuaDuyet(String key);
	List<TTDKCCCD> timKiemTTDKDaDuyet(String key);
	List<TTDKCCCD> timKiemTTDKBiTuChoi(String key);
	List<TTDKCCCD> thongKeDonDangKy(String thangNam);
	List<TTDKCCCD> thongKeDonDangKyDV(String dv, String thangNam);
	int getMaxMaSo();
	TTDKCCCD getTTDKCCCDBangMa(String maSo);
	Boolean deleteTTDKCCCD(int i);
	Boolean updateTTDKCCCD(String set, String where);
	List<TTDKCCCD> timkiemTTDKCCCD(String tuKhoa);
	List<bean.Vu.TTDKCCCD> timkiemTTDKCCCDDaXacNhan(String tuKhoa);
	Boolean capNhatTTDKCCCD(bean.Vu.TTDKCCCD cccdTam);
	Boolean xemTTDKCCCD(int maSo, String taiKhoan);
	Boolean daXemTTDKCCCD(int maSo, String taiKhoan);
}
