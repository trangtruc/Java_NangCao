package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bean.Chung.Tinh;

import com.mkyong.dao.TinhDao;

public class TinhServiceImpl implements TinhService {
	/**.
	 * {@TinhDao} TinhDao
	 */
	@Autowired(required=true)
	private TinhDao tinhDao;
	@Override
	public List<Tinh> getDSTinh() {
		return tinhDao.getDSTinh();
	}
	@Override
	public Tinh getTinhBangMa(String maTinh) {
		// TODO Auto-generated method stub
		return tinhDao.getTinhBangMa(maTinh);
	}
	@Override
	public Boolean insertTinh(Tinh tinh) {
		// TODO Auto-generated method stub
		return tinhDao.insertTinh(tinh);
	}
	@Override
	public List<Tinh> timKiemTinh(String tuKhoa) {
		// TODO Auto-generated method stub
		return tinhDao.timKiemTinh(tuKhoa);
	}
	@Override
	public Boolean updateTinh(Tinh tinh) {
		// TODO Auto-generated method stub
		return tinhDao.updateTinh(tinh);
	}

}
