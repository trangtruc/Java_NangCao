package jdbc.Config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.Config.ConfigEmail;

public class ConfigEmailExtractor implements ResultSetExtractor<ConfigEmail>{
	public ConfigEmail extractData(ResultSet rs) throws SQLException,
	DataAccessException {
	final String maMail = "MA_EMAIL";
	final String email = "EMAIL";
	final String matKhau = "MAT_KHAU";
	final String tieuDeGui = "TIEU_DE_GUI";
	final String noiDungGui = "NOI_DUNG_GUI";
	ConfigEmail configEmail = new ConfigEmail();
	configEmail.setMaEmail(rs.getString(maMail));
	configEmail.setEmail(rs.getString(email));
	configEmail.setMatKhau(rs.getString(matKhau));
	configEmail.setTieuDeGui(rs.getString(tieuDeGui));
	configEmail.setNoiDungGui(rs.getString(noiDungGui));
	return configEmail;
	}
}
