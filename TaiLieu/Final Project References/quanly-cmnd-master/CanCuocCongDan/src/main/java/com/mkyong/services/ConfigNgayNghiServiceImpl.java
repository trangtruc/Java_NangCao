package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkyong.dao.ConfigNgayNghiDao;

import bean.Config.ConfigNgayNghi;

public class ConfigNgayNghiServiceImpl implements ConfigNgayNghiService {
	
	@Autowired(required=true)
	private ConfigNgayNghiDao ngayNghiDao;
	@Override
	public Boolean insertNgayNghi(int ngay, int thang) {
		// TODO Auto-generated method stub
		return ngayNghiDao.insertNgayNghi(ngay, thang);
	}

	@Override
	public Boolean deleteNgayNghi(int ngay, int thang) {
		// TODO Auto-generated method stub
		return ngayNghiDao.deleteNgayNghi(ngay, thang);
	}

	@Override
	public List<ConfigNgayNghi> getDSNgayNghi() {
		// TODO Auto-generated method stub
		return ngayNghiDao.getDSNgayNghi();
	}

	@Override
	public Boolean ktNgayNghi(ConfigNgayNghi ngayNghi) {
		return ngayNghiDao.ktNgayNghi(ngayNghi);
	}

}
