package com.mkyong.dao;
import java.util.List;

import bean.Chung.DanToc;

public interface DanTocDao {
	List<DanToc> getDSDanToc();
	DanToc getDanTocID(String maDanToc);
	Boolean insertDanToc(DanToc danToc);
	Boolean updateDanToc(DanToc danToc);
	List<DanToc> timKiemDanToc(String tuKhoa);
}
