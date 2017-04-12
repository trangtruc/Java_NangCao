package com.mkyong.services;

import java.util.List;

import bean.Chung.TaiKhoan;
import bean.Vu.TTDKCCCD;

public interface TaiKhoanService {
	Boolean addTaiKhoan(TTDKCCCD cccdTam, String password, String hoTen);
	Boolean themTaiKhoan(TaiKhoan taiKhoan);
	TaiKhoan getTaiKhoan(String taiKhoan);
	List<TaiKhoan> getDSTaiKhoan();
	List<TaiKhoan> getDSTaiKhoanHoatDong();
	List<TaiKhoan> getDSTaiKhoanBiKhoa();
	List<TaiKhoan> timKiemTaiKhoanHoatDong(String tuKhoa);
	List<TaiKhoan> timKiemTaiKhoanBiKhoa(String tuKhoa);
	List<TaiKhoan> timKiemTaiKhoan(String tuKhoa);
	TaiKhoan getTaiKhoanBangEmail(String email);
	TaiKhoan dangNhap(String taiKhoan, String password, String maQuyen);
	Boolean khoaUser(String taiKhoan);
	Boolean moKhoaUser(String taiKhoan);
	Boolean setQuyenTaiKhoan(String taiKhoan, int maQuyen);
	Boolean updateTaiKhoan(TaiKhoan taiKhoan);
}
