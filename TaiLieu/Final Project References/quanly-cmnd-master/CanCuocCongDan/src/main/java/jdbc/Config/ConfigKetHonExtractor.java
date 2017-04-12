package jdbc.Config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.Config.ConfigKetHon;

public class ConfigKetHonExtractor implements ResultSetExtractor<ConfigKetHon>{
	public ConfigKetHon extractData(ResultSet rs) throws SQLException,
	DataAccessException {
	final String tuoiNam = "TUOI_NAM";
	final String tuoiNu = "TUOI_NU";
	ConfigKetHon ck = new ConfigKetHon();
	ck.setTuoiNam(rs.getInt(tuoiNam));
	ck.setTuoiNu(rs.getInt(tuoiNu));
	return ck;
	}
}
