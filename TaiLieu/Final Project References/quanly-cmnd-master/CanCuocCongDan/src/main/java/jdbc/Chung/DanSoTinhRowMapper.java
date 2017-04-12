package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.DanSoTinh;

public class DanSoTinhRowMapper implements RowMapper<DanSoTinh> {

	@Override
	public DanSoTinh mapRow(ResultSet rs, int rowNum) throws SQLException {
		DanSoTinhExtractor danSoTinhExt = new DanSoTinhExtractor();
		return danSoTinhExt.extractData(rs);
	}

}
