package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.HonNhan;


public class HonNhanRowMapper implements RowMapper<HonNhan> {

	@Override
	public HonNhan mapRow(ResultSet rs, int rowNum) throws SQLException {
		HonNhanExtractor honNhanExt = new HonNhanExtractor();
		return honNhanExt.extractData(rs);
	}

}
