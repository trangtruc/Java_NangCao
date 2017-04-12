package jdbc.Chung;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.DanSoVung;

public class DanSoVungRowMapper implements RowMapper<DanSoVung> {

	@Override
	public DanSoVung mapRow(ResultSet rs, int rowNum) throws SQLException {
		DanSoVungExtractor danSoVungExt = new DanSoVungExtractor();
		return danSoVungExt.extractData(rs);
	}

}
