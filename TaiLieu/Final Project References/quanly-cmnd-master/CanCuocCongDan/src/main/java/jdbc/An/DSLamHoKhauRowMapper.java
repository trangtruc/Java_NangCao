package jdbc.An;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.An.DSLamHoKhau;

public class DSLamHoKhauRowMapper implements RowMapper<DSLamHoKhau> {
	@Override
	public DSLamHoKhau mapRow(ResultSet rs, int rowNum) throws SQLException {
		DSLamHoKhauExtractor shk = new DSLamHoKhauExtractor();
		return shk.extractData(rs);
	}
}
