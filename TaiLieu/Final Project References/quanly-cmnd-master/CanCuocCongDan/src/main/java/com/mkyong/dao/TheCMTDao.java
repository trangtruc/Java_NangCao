package com.mkyong.dao;
import java.util.List;

import bean.Vu.TheCMT;
public interface TheCMTDao {
	List<TheCMT> getDSThe();
	List<TheCMT> getDSChuaLamThe();
	List<TheCMT> getDSTheChuaTra();
	List<TheCMT> getDSTheDaTra();
	List<TheCMT> getDSTheBangMa(String maSo);
	List<TheCMT> timKiemThe(String tuKhoa);
	List<TheCMT> timKiemTheChuaLam(String tuKhoa);
	List<TheCMT> timKiemTheChuaTra(String tuKhoa);
	TheCMT getTheBangSoCC(String soCC);
	Boolean updateThe(String set, String where);
	Boolean insertThe(TheCMT the);
	Boolean deleteThe(TheCMT the);
}
