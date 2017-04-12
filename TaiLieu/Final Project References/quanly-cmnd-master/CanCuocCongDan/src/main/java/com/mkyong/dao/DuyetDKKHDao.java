package com.mkyong.dao;

import bean.Chung.DuyetDKKH;

public interface DuyetDKKHDao {

	//Them mot hang moi vao bang DUYET_DKKH
	Boolean themDuyetDKKH(DuyetDKKH duyetDKKH);
	//Xoa duyet TTDK_KetHon
	Boolean xoaDuyetDKKHBangSoDK(String soDK);
	//Xoa duyet TTDK_KetHon
	Boolean xoaDuyetDKKHBangSoDK(String soDK, String nguoiDuyet);

}
