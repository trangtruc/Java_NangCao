package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkyong.dao.QuyenDao;

import bean.Chung.Quyen;

public class QuyenServiceImpl implements QuyenService {
	@Autowired(required=true)
	private QuyenDao quyenDao;
	@Override
	public List<Quyen> getDSQuyen() {
		// TODO Auto-generated method stub
		return quyenDao.getDSQuyen();
	}
	@Override
	public Quyen getQuyen(int maQuyen) {
		// TODO Auto-generated method stub
		return quyenDao.getQuyen(maQuyen);
	}
	@Override
	public Quyen getQuyenBangTen(String tenQuyen) {
		// TODO Auto-generated method stub
		return quyenDao.getQuyenBangTen(tenQuyen);
	}
	@Override
	public List<Quyen> getQuyenTaiKhoan(String taiKhoan) {
		// TODO Auto-generated method stub
		return quyenDao.getQuyenTaiKhoan(taiKhoan);
	}
	@Override
	public Boolean kiemTraQuyen(String taiKhoan, int maQuyen) {
		// TODO Auto-generated method stub
		return quyenDao.kiemTraQuyen(taiKhoan, maQuyen);
	}
	@Override
	public Boolean addQuyen(String taiKhoan, int maQuyen) {
		// TODO Auto-generated method stub
		return quyenDao.addQuyen(taiKhoan, maQuyen);
	}
	@Override
	public Boolean deleteQuyen(String taiKhoan, int maQuyen) {
		// TODO Auto-generated method stub
		return quyenDao.deleteQuyen(taiKhoan, maQuyen);
	}
	@Override
	public Boolean kiemTraQuyenBangTen(String taiKhoan, String tenQuyen) {
		// TODO Auto-generated method stub
		return quyenDao.kiemTraQuyenBangTen(taiKhoan, tenQuyen);
	}

}
