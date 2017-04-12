package jdbc.Vu;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.An.SoHoKhau;
public class SoHoKhauRowMapper implements RowMapper<SoHoKhau> {
	public SoHoKhau mapRow(ResultSet rs, int rowNum) throws SQLException {
		SoHoKhauExtractor shk = new SoHoKhauExtractor();
		return shk.extractData(rs);
	}
}
