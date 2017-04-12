package jdbc.An;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.An.DSQuanHe;

public class DSQuanHeRowMapper implements RowMapper<DSQuanHe> {
	@Override
	public DSQuanHe mapRow(ResultSet rs, int rowNum) throws SQLException {
		DSQuanHeExtractor quanHe = new DSQuanHeExtractor();
		return quanHe.extractData(rs);
	}
}
