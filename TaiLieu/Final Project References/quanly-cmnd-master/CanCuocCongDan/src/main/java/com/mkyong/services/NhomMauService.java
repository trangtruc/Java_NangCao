package com.mkyong.services;

import java.util.List;

import bean.Chung.NhomMau;

public interface NhomMauService {
	List<NhomMau> getDSNhomMau();
	List<NhomMau> getNhomMauBangMa(String maNM);
}
