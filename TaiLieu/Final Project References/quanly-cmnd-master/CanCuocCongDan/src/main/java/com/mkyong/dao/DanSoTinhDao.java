package com.mkyong.dao;

import java.util.List;

import bean.Chung.DanSoTinh;

public interface DanSoTinhDao {
	//Lay danh sach tinh cung ty le dan so cua tinh do so voi quoc gia
	List<DanSoTinh> getDanSoTinh(int nam);
	//Tinh tong dan so ca nuoc den nam hien tai
	long getTongDanSo(int nam);
}
