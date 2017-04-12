package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.Huyen;

public class HuyenRowMapper1 implements RowMapper<Huyen> {

	@Override
	public Huyen mapRow(ResultSet rs, int rowNum) throws SQLException {
		HuyenExtractor1 huyenExt = new HuyenExtractor1();
		return huyenExt.extractData(rs);
	}
	
}
