package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CCCDRowMapper implements RowMapper<bean.Chung.CCCD> {
	public bean.Chung.CCCD mapRow(ResultSet rs, int rowNum) throws SQLException {
		CCCDExtractor cccd = new CCCDExtractor();
		return cccd.extractData(rs);
	}
}
