package com.mkyong.dao;

import java.util.List;

import bean.Chung.Xa;

public interface XaDao {
	List<Xa> getDSXaAll();
	List<Xa> getDSXa(String maHuyen);
	List<Xa> timKiemXa(String tuKhoa);
	Xa getXaBangMa(String maXa);
	Xa getXaHuyenTinh(String maXa);
	Boolean insertXa(Xa xa);
	Boolean updateXa(Xa xa);
}
