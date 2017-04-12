package com.mkyong.dao;

import bean.Chung.DuyetDKKS;

public interface DuyetDKKSDao {
	//Them mot hang vao duyet_dkks
	Boolean themDuyetDKKS(DuyetDKKS duyetDKKS);
	//Cap nhat soKS trong bang DUYET_DKKS
	Boolean capNhatSoKS(String soKS, String soDK, 
			String nguoiDuyet, String ngayDuocDuyet);
	//Lay DUYET_DKKS bang soKS
	DuyetDKKS getDuyetDKKS(String soKS);
	//Boolean xoaDuyetDKKSBangSoKS
	Boolean xoaDuyetDKKSBangSoKS(String soKS);
	//Boolean xoaDuyetDKKSBangSoKS
	Boolean xoaDuyetDKKSBangSoKS1(String soKS, String nguoiDuyet);
	
}
