package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.DanSoXa;

public class DanSoXaRowMapper implements RowMapper<DanSoXa> {

	@Override
	public DanSoXa mapRow(ResultSet rs, int rowNum) throws SQLException {
		DanSoXaExtractor danSoXaExt = new DanSoXaExtractor();
		return danSoXaExt.extractData(rs);
	}

}
