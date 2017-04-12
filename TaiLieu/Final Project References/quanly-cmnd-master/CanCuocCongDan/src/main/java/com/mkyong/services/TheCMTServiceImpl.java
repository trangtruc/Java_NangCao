package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkyong.dao.TheCMTDao;

import bean.Vu.TheCMT;

public class TheCMTServiceImpl implements TheCMTService {
	@Autowired(required=true)
	private TheCMTDao theCMTDao;
	
	@Override
	public List<TheCMT> getDSThe() {
		// TODO Auto-generated method stub
		return theCMTDao.getDSThe();
	}

	@Override
	public List<TheCMT> getDSChuaLamThe() {
		// TODO Auto-generated method stub
		return theCMTDao.getDSChuaLamThe();
	}

	@Override
	public Boolean insertThe(TheCMT the) {
		// TODO Auto-generated method stub
		return theCMTDao.insertThe(the);
	}

	@Override
	public List<TheCMT> getDSTheChuaTra() {
		// TODO Auto-generated method stub
		return theCMTDao.getDSTheChuaTra();
	}

	@Override
	public List<TheCMT> getDSTheDaTra() {
		// TODO Auto-generated method stub
		return theCMTDao.getDSTheDaTra();
	}

	@Override
	public Boolean updateThe(String set, String where) {
		// TODO Auto-generated method stub
		return theCMTDao.updateThe(set, where);
	}

	@Override
	public List<TheCMT> getDSTheBangMa(String maSo) {
		// TODO Auto-generated method stub
		return theCMTDao.getDSTheBangMa(maSo);
	}

	@Override
	public Boolean deleteThe(TheCMT the) {
		// TODO Auto-generated method stub
		return theCMTDao.deleteThe(the);
	}

	@Override
	public TheCMT getTheBangSoCC(String soCC) {
		// TODO Auto-generated method stub
		return theCMTDao.getTheBangSoCC(soCC);
	}

	@Override
	public List<TheCMT> timKiemTheChuaLam(String tuKhoa) {
		// TODO Auto-generated method stub
		return theCMTDao.timKiemTheChuaLam(tuKhoa);
	}

	@Override
	public List<TheCMT> timKiemTheChuaTra(String tuKhoa) {
		// TODO Auto-generated method stub
		return theCMTDao.timKiemTheChuaTra(tuKhoa);
	}

	@Override
	public List<TheCMT> timKiemThe(String tuKhoa) {
		// TODO Auto-generated method stub
		return theCMTDao.timKiemThe(tuKhoa);
	}

}
