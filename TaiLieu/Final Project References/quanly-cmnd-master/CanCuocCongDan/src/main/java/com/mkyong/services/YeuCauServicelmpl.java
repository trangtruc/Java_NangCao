package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bean.Chung.YeuCau;

import com.mkyong.dao.YeuCauDao;

public class YeuCauServicelmpl implements YeuCauService {
	/**.
	 * {@yeuCauDao} yeuCauDao
	 */
	@Autowired(required=true)
	private YeuCauDao yeuCauDao;

	@Override
	public List<YeuCau> getDSYeuCau() {
		return yeuCauDao.getDSYeuCau();
	}
	
	@Override
	public YeuCau getYeuCauID(int maYeuCau) {
		return yeuCauDao.getYeuCauID(maYeuCau);
	}

	@Override
	public List<YeuCau> getDSYeuCauHoatDong() {
		// TODO Auto-generated method stub
		return yeuCauDao.getDSYeuCauHoatDong();
	}

	@Override
	public List<YeuCau> getDSYeuCauTamDung() {
		// TODO Auto-generated method stub
		return yeuCauDao.getDSYeuCauTamDung();
	}

	@Override
	public Boolean insertYeuCau(YeuCau yeuCau) {
		// TODO Auto-generated method stub
		return yeuCauDao.insertYeuCau(yeuCau);
	}

	@Override
	public Boolean updateYeuCau(YeuCau yeuCau) {
		// TODO Auto-generated method stub
		return yeuCauDao.updateYeuCau(yeuCau);
	}

	@Override
	public Boolean tamDungYeuCau(YeuCau yeuCau) {
		// TODO Auto-generated method stub
		return yeuCauDao.tamDungYeuCau(yeuCau);
	}

	@Override
	public Boolean tiepTucYeuCau(YeuCau yeuCau) {
		// TODO Auto-generated method stub
		return yeuCauDao.tiepTucYeuCau(yeuCau);
	}

}
