package com.mkyong.dao;
import java.util.List;

import bean.Chung.YeuCau;
public interface YeuCauDao {
	YeuCau getYeuCauID(int maYeuCau);
	List<YeuCau> getDSYeuCau();
	List<YeuCau> getDSYeuCauHoatDong();
	List<YeuCau> getDSYeuCauTamDung();
	Boolean insertYeuCau(YeuCau yeuCau);
	Boolean updateYeuCau(YeuCau yeuCau);
	Boolean tamDungYeuCau(YeuCau yeuCau);
	Boolean tiepTucYeuCau(YeuCau yeuCau);
}
