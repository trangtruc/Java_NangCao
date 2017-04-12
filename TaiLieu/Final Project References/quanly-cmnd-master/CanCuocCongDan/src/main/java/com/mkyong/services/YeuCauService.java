package com.mkyong.services;

import java.util.List;

import bean.Chung.YeuCau;

public interface YeuCauService {
	YeuCau getYeuCauID(int maYeuCau);
	List<YeuCau> getDSYeuCau();
	List<YeuCau> getDSYeuCauHoatDong();
	List<YeuCau> getDSYeuCauTamDung();
	Boolean insertYeuCau(YeuCau yeuCau);
	Boolean updateYeuCau(YeuCau yeuCau);
	Boolean tamDungYeuCau(YeuCau yeuCau);
	Boolean tiepTucYeuCau(YeuCau yeuCau);
}
