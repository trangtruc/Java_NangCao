package com.mkyong.dao;
import java.util.List;

import bean.Chung.DanSoXa;

public interface DanSoXaDao {
	//Lay dan so cua xa trong tinh, tinhs theo ty le %
	List<DanSoXa> getDanSoXaBangMaTinh(String maTinh, int nam);
	//Lay dan so cua trong huyen, tinhs theo ty le %
	List<DanSoXa> getDanSoXaBangMaHuyen(String maHuyen, int nam);
}
