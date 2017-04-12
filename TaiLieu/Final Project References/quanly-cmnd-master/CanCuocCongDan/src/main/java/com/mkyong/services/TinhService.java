package com.mkyong.services;

import java.util.List;

import bean.Chung.Tinh;

public interface TinhService {
	List<Tinh> getDSTinh();
	List<Tinh> timKiemTinh(String tuKhoa);
	Tinh getTinhBangMa(String maTinh);
	Boolean insertTinh(Tinh tinh);
	Boolean updateTinh(Tinh tinh);
}
