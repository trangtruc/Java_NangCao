package jdbc.Vu;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Vu.TheCMT;

public class TheCMTRowMapper implements RowMapper<TheCMT> {
	public TheCMT mapRow(ResultSet rs, int rowNum) throws SQLException {
		TheCMTExtractor dsLamThe = new TheCMTExtractor();
		return dsLamThe.extractData(rs);
	}
}
