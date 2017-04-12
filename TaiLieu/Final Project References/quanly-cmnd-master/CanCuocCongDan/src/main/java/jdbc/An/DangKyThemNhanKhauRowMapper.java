package jdbc.An;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.An.DSThemNhanKhau;

public class DangKyThemNhanKhauRowMapper implements RowMapper<DSThemNhanKhau> {
	@Override
	public DSThemNhanKhau mapRow(ResultSet rs, int rowNum) throws SQLException {
		DangKyThemNhanKhauExtractor shk = new DangKyThemNhanKhauExtractor();
		return shk.extractData(rs);
	}
}
