package jdbc.An;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.An.SoHoKhau;

public class SoHoKhauRowMapper1 implements RowMapper<SoHoKhau> {
	@Override
	public SoHoKhau mapRow(ResultSet rs, int rowNum) throws SQLException {
		SoHoKhauExtractor1 shk = new SoHoKhauExtractor1();
		return shk.extractData(rs);
	}
}
