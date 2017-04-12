package com.mkyong.dao;

import java.util.List;

import bean.Chung.DanSoHuyen;

public interface DanSoHuyenDao {
	//Lay huyen do cung ti le dan so cua huyen so voi tinh
	List<DanSoHuyen> getDanSoHuyen(String maTinh, int nam);
	DanSoHuyen getDanSoHuyenBangMaHuyen(String maHuyen, int nam);
}
