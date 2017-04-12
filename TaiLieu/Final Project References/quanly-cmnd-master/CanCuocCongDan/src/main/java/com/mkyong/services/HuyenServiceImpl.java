package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bean.Chung.Huyen;

import com.mkyong.dao.HuyenDao;

public class HuyenServiceImpl implements HuyenService {
	/**.
	 * {@huyenDao} HuyenDao
	 */
	@Autowired(required=true)
	private HuyenDao huyenDao;
	@Override
	public List<Huyen> getDSHuyen(String maTinh) {
		return huyenDao.getDSHuyen(maTinh);
	}
	@Override
	public Huyen getHuyenBangMa(String maHuyen) {
		// TODO Auto-generated method stub
		return huyenDao.getHuyenBangMa(maHuyen);
	}
	
	@Override
	public 	Huyen getTinhHuyenBangMa(String maHuyen){
		// TODO Auto-generated method stub
		return huyenDao.getTinhHuyenBangMa(maHuyen);
	}
	@Override
	public List<Huyen> timKiemHuyen(String tuKhoa) {
		// TODO Auto-generated method stub
		return huyenDao.timKiemHuyen(tuKhoa);
	}
	@Override
	public List<Huyen> getDSHuyenAll() {
		// TODO Auto-generated method stub
		return huyenDao.getDSHuyenAll();
	}
	@Override
	public Boolean insertHuyen(Huyen huyen) {
		// TODO Auto-generated method stub
		return huyenDao.insertHuyen(huyen);
	}
	@Override
	public Boolean updateHuyen(Huyen huyen) {
		// TODO Auto-generated method stub
		return huyenDao.updateHuyen(huyen);
	}
	
}
