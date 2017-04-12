package jdbc.Vu;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Vu.TTDKCCCD;

public class TTDKCCCDRowMapper implements RowMapper<TTDKCCCD> {
	public TTDKCCCD mapRow(ResultSet rs, int rowNum) throws SQLException {
		TTDKCCCDExtractor cccdTamThoi = new TTDKCCCDExtractor();
		return cccdTamThoi.extractData(rs);
	}
}
