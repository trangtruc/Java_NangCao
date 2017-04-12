package com.mkyong.services;

import java.util.List;

import bean.Chung.DanToc;

public interface DanTocService {
	List<DanToc> getDSDanToc();
	DanToc getDanTocID(String maDanToc);
	List<DanToc> timKiemDanToc(String tuKhoa);
	Boolean insertDanToc(DanToc danToc);
	Boolean updateDanToc(DanToc danToc);
}
