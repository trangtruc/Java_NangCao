package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bean.Chung.Xa;

import com.mkyong.dao.XaDao;

public class XaServiceImpl implements XaService {
	/**.
	 * {@xaDao} xaDao
	 */
	@Autowired(required=true)
	private XaDao xaDao;

	@Override
	public List<Xa> getDSXa(String maHuyen) {
		return xaDao.getDSXa(maHuyen);
	}

	@Override
	public Xa getXaBangMa(String maXa) {
		// TODO Auto-generated method stub
		return xaDao.getXaBangMa(maXa);
	}

	@Override
	public Xa getXaHuyenTinh(String maXa) {
		return xaDao.getXaHuyenTinh(maXa);
	}

	@Override
	public List<Xa> getDSXaAll() {
		// TODO Auto-generated method stub
		return xaDao.getDSXaAll();
	}

	@Override
	public Boolean insertXa(Xa xa) {
		// TODO Auto-generated method stub
		return xaDao.insertXa(xa);
	}

	@Override
	public Boolean updateXa(Xa xa) {
		// TODO Auto-generated method stub
		return xaDao.updateXa(xa);
	}

	@Override
	public List<Xa> timKiemXa(String tuKhoa) {
		// TODO Auto-generated method stub
		return xaDao.timKiemXa(tuKhoa);
	}
}
