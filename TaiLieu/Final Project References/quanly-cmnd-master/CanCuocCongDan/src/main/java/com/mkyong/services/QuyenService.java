package com.mkyong.services;
import java.util.List;

import bean.Chung.Quyen;
public interface QuyenService {
	List<Quyen> getDSQuyen();
	List<Quyen> getQuyenTaiKhoan(String taiKhoan);
	Boolean kiemTraQuyen(String taiKhoan, int maQuyen);
	Boolean kiemTraQuyenBangTen(String taiKhoan, String tenQuyen);
	Boolean addQuyen(String taiKhoan, int maQuyen);
	Boolean deleteQuyen(String taiKhoan, int maQuyen);
	Quyen getQuyen(int maQuyen);
	Quyen getQuyenBangTen(String tenQuyen);
}
