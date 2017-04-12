package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.Huyen;

public class HuyenTinhRowMapper implements RowMapper<Huyen> {

	@Override
	public Huyen mapRow(ResultSet rs, int rowNum) throws SQLException {
		HuyenExtractor huyenExt = new HuyenExtractor();
		return huyenExt.extractData1(rs);
	}
	
}
