package com.mkyong.services;

import java.util.List;

import bean.Chung.Xa;

public interface XaService {
	List<Xa> getDSXaAll();
	List<Xa> getDSXa(String maHuyen);
	List<Xa> timKiemXa(String tuKhoa);
	Xa getXaBangMa(String maXa);
	Xa getXaHuyenTinh(String maXa);
	Boolean insertXa(Xa xa);
	Boolean updateXa(Xa xa);
}
