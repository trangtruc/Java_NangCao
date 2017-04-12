package com.mkyong.dao;

import bean.Chung.HonNhan;
import bean.Chung.TaiKhoan;

import java.util.List;

public interface HonNhanDao {
	
	//Kiem tra gioi tinh giua vo va chong, vo chong phai khac gioi tinh
	Boolean ktGioiTinh(String gioiTinhA, String gioiTinhB);
	//Kiem tra do tuoi co the ket hon,
	Boolean ktTuoiKH(String ngaySinh, int tuoiQuyDinh);
	//Kiem tra da ket hon
	Boolean ktDaKetHon(String soCC, String gioiTinh);
	// Lay thong tin ket hon
	HonNhan getHonNhanBangSoCC(String soCC);
	//Lay danh sach hon nhan
	List<HonNhan> getDSHonNhan(TaiKhoan tk, String ngayDuyet, int soDonTrongNgay);
	//Them mot hang hon_nhan
	Boolean themHonNhan(HonNhan hn);
	HonNhan layHangCuoi();
	int taoSoDK();
	//Tim kiem hon nhan
	List<HonNhan> getHonNhan(String inputTimKiem);
	HonNhan getHonNhanBangSoDK(int soDK);
	//Xoa hon_nhan
	Boolean xoaHonNhan(String soCCChong);
}
