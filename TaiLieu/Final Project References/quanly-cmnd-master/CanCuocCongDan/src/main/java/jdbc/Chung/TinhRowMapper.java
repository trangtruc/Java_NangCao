package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.Tinh;

public class TinhRowMapper implements RowMapper<Tinh> {

	@Override
	public Tinh mapRow(ResultSet rs, int rowNum) throws SQLException {
		TinhExtractor tinhExt = new TinhExtractor();
		return tinhExt.extractData(rs);
	}
	
}
