package com.mkyong.dao;
import java.util.List;

import bean.Config.ConfigNgayNghi;
public interface ConfigNgayNghiDao {
	Boolean insertNgayNghi(int ngay, int thang);
	Boolean deleteNgayNghi(int ngay, int thang);
	List<ConfigNgayNghi> getDSNgayNghi();
	//Kiem tra xem ngay dau vao co trong ConfigNgayNghi chua
	Boolean ktNgayNghi(ConfigNgayNghi ngayNghi);
}
