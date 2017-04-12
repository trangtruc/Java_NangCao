package jdbc.Config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.Config.ConfigCCCD;

public class ConfigCCCDExtractor implements ResultSetExtractor<ConfigCCCD>{
	public ConfigCCCD extractData(ResultSet rs) throws SQLException,
	DataAccessException {
	final String hanHoSo = "HAN_HO_SO";
	final String hanSuDung = "HAN_SU_DUNG";
	final String soHoSo1Ngay = "SO_HS_1_NGAY";
	final String tuoi = "TUOI";
	ConfigCCCD configCCCD = new ConfigCCCD();
	configCCCD.setHanHoSo(rs.getInt(hanHoSo));
	configCCCD.setHanSuDung(rs.getInt(hanSuDung));
	configCCCD.setSoHoSo1Ngay(rs.getInt(soHoSo1Ngay));
	configCCCD.setTuoi(rs.getInt(tuoi));
	return configCCCD;
	}
}
