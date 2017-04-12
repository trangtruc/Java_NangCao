package com.mkyong.dao;

import java.util.List;

import bean.Chung.DanSoVung;

public interface DanSoVungDao {
	//Lay danh sach dan so cua 7 vung kinh te, tinh den nam dau vao
	//Vi du: Vung 1: 5 nguoi
	//		 Vung 2: 15 nguoi
	List<DanSoVung> getDSDanSoVung(int nam, int soVung);
}
