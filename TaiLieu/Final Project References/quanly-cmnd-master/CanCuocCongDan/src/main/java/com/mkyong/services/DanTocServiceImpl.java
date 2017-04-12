package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkyong.dao.DanTocDao;

import bean.Chung.DanToc;

public class DanTocServiceImpl implements DanTocService {
	@Autowired(required=true)
	private DanTocDao danTocDao;
	@Override
	public List<DanToc> getDSDanToc() {
		return danTocDao.getDSDanToc();
	}
	@Override
	public DanToc getDanTocID(String maDanToc) {
		return danTocDao.getDanTocID(maDanToc);
	}
	@Override
	public Boolean insertDanToc(DanToc danToc) {
		// TODO Auto-generated method stub
		return danTocDao.insertDanToc(danToc);
	}
	@Override
	public Boolean updateDanToc(DanToc danToc) {
		// TODO Auto-generated method stub
		return danTocDao.updateDanToc(danToc);
	}
	@Override
	public List<DanToc> timKiemDanToc(String tuKhoa) {
		// TODO Auto-generated method stub
		return danTocDao.timKiemDanToc(tuKhoa);
	}

}
