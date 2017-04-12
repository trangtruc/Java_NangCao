package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.CCCDTam;

public class CCCDTamRowMapper implements RowMapper<CCCDTam> {

	@Override
	public CCCDTam mapRow(ResultSet rs, int rowNum) throws SQLException {
		CCCDTamExtractor cccdTamExt = new CCCDTamExtractor();
		return cccdTamExt.extractData(rs);
	}

}
