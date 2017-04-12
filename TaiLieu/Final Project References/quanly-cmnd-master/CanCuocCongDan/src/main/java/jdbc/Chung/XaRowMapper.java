package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.Xa;

public class XaRowMapper implements RowMapper<Xa> {

	@Override
	public Xa mapRow(ResultSet rs, int rowNum) throws SQLException {
		XaExtractor xaExt = new XaExtractor();
		return xaExt.extractData(rs);
	}

}
