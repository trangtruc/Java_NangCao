package com.mkyong.services;

import java.util.List;

import bean.Chung.Huyen;

public interface HuyenService {
	List<Huyen> getDSHuyenAll();
	List<Huyen> getDSHuyen(String maTinh);
	List<Huyen> timKiemHuyen(String tuKhoa);
	Huyen getHuyenBangMa(String maHuyen);
	Huyen getTinhHuyenBangMa(String maHuyen);
	Boolean insertHuyen(Huyen huyen);
	Boolean updateHuyen(Huyen huyen);
}
