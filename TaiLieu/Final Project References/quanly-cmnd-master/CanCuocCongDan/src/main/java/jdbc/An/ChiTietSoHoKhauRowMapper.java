package jdbc.An;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.An.SoHoKhau;

public class ChiTietSoHoKhauRowMapper implements RowMapper<SoHoKhau> {
	@Override
	public SoHoKhau mapRow(ResultSet rs, int rowNum) throws SQLException {
		ChiTietSoHoKhauExtractor shk = new ChiTietSoHoKhauExtractor();
		return shk.extractData(rs);
	}
}
