package jdbc.Config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.Config.ConfigNgayNghi;

public class ConfigNgayNghiExtractor implements ResultSetExtractor<ConfigNgayNghi> {
	public ConfigNgayNghi extractData(ResultSet rs) throws SQLException,
	DataAccessException {
	final String ngay = "NGAY";
	final String thang = "THANG";
	
	ConfigNgayNghi ngayNghi = new ConfigNgayNghi();
	ngayNghi.setNgay(rs.getInt(ngay));
	ngayNghi.setThang(rs.getInt(thang));
	return ngayNghi;
}
}
