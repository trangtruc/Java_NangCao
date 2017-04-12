package com.mkyong.dao;

import java.util.List;

import bean.Chung.NhomMau;

public interface NhomMauDao {
	List<NhomMau> getDSNhomMau();
	List<NhomMau> getNhomMauBangMa(String maNM);
}
