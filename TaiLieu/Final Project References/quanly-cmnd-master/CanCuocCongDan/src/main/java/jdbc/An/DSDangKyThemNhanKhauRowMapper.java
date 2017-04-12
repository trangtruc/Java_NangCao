package jdbc.An;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.An.DSThemNhanKhau;

public class DSDangKyThemNhanKhauRowMapper implements RowMapper<DSThemNhanKhau> {
	@Override
	public DSThemNhanKhau mapRow(ResultSet rs, int rowNum) throws SQLException {
		DSDangKyThemNhanKhauExtractor shk = new DSDangKyThemNhanKhauExtractor();
		return shk.extractData(rs);
	}
}
