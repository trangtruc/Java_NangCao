package jdbc.Vu;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Vu.CCCD;

public class CCCDRowMapper implements RowMapper<CCCD> {
	public CCCD mapRow(ResultSet rs, int rowNum) throws SQLException {
		CCCDExtractor cccd = new CCCDExtractor();
		return cccd.extractData(rs);
	}
}
