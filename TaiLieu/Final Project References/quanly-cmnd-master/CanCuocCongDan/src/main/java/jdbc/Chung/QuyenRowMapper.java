package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.Quyen;

public class QuyenRowMapper implements RowMapper<Quyen> {
	@Override
	public Quyen mapRow(ResultSet rs, int rowNum) throws SQLException {
		QuyenExtractor quyenExt = new QuyenExtractor();
		return quyenExt.extractData(rs);
	}
}
