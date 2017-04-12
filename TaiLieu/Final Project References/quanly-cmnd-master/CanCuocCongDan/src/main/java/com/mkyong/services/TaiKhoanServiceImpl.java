package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bean.Chung.TaiKhoan;
import bean.Vu.TTDKCCCD;

import com.mkyong.dao.TaiKhoanDao;

public class TaiKhoanServiceImpl implements TaiKhoanService {
	@Autowired(required=true)
	private TaiKhoanDao taiKhoanDao;
	@Override
	public Boolean addTaiKhoan(TTDKCCCD cccdTam, String password, String hoTen) {
		return taiKhoanDao.addTaiKhoan(cccdTam, password, hoTen);
	}
	@Override
	public TaiKhoan getTaiKhoan(String taiKhoan) {
		return taiKhoanDao.getTaiKhoan(taiKhoan);
	}
	@Override
	public Boolean setQuyenTaiKhoan(String taiKhoan, int maQuyen) {
		// TODO Auto-generated method stub
		return taiKhoanDao.setQuyenTaiKhoan(taiKhoan, maQuyen);
	}
	@Override
	public Boolean updateTaiKhoan(TaiKhoan taiKhoan) {
		// TODO Auto-generated method stub
		return taiKhoanDao.updateTaiKhoan(taiKhoan);
	}
	@Override
	public List<TaiKhoan> getDSTaiKhoan() {
		// TODO Auto-generated method stub
		return taiKhoanDao.getDSTaiKhoan();
	}
	@Override
	public Boolean khoaUser(String taiKhoan) {
		// TODO Auto-generated method stub
		return taiKhoanDao.khoaUser(taiKhoan);
	}
	@Override
	public Boolean moKhoaUser(String taiKhoan) {
		// TODO Auto-generated method stub
		return taiKhoanDao.moKhoaUser(taiKhoan);
	}
	@Override
	public List<TaiKhoan> timKiemTaiKhoan(String tuKhoa) {
		// TODO Auto-generated method stub
		return taiKhoanDao.timKiemTaiKhoan(tuKhoa);
	}
	@Override
	public TaiKhoan dangNhap(String taiKhoan, String password, String maQuyen) {
		// TODO Auto-generated method stub
		return taiKhoanDao.dangNhap(taiKhoan, password, maQuyen);
	}
	@Override
	public Boolean themTaiKhoan(TaiKhoan taiKhoan) {
		// TODO Auto-generated method stub
		return taiKhoanDao.themTaiKhoan(taiKhoan);
	}
	@Override
	public List<TaiKhoan> getDSTaiKhoanHoatDong() {
		// TODO Auto-generated method stub
		return taiKhoanDao.getDSTaiKhoanHoatDong();
	}
	@Override
	public List<TaiKhoan> getDSTaiKhoanBiKhoa() {
		// TODO Auto-generated method stub
		return taiKhoanDao.getDSTaiKhoanBiKhoa();
	}
	@Override
	public List<TaiKhoan> timKiemTaiKhoanHoatDong(String tuKhoa) {
		// TODO Auto-generated method stub
		return taiKhoanDao.timKiemTaiKhoanHoatDong(tuKhoa);
	}
	@Override
	public List<TaiKhoan> timKiemTaiKhoanBiKhoa(String tuKhoa) {
		// TODO Auto-generated method stub
		return taiKhoanDao.timKiemTaiKhoanBiKhoa(tuKhoa);
	}
	@Override
	public TaiKhoan getTaiKhoanBangEmail(String email) {
		// TODO Auto-generated method stub
		return taiKhoanDao.getTaiKhoanBangEmail(email);
	}
}
