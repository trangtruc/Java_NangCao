package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.DanSoHuyen;
import bean.Chung.Huyen;

public class DanSoHuyenRowMapper implements RowMapper<DanSoHuyen> {

	@Override
	public DanSoHuyen mapRow(ResultSet rs, int rowNum) throws SQLException {
		DanSoHuyenExtractor danSoHuyenExt = new DanSoHuyenExtractor();
		return danSoHuyenExt.extractData(rs);
	}

}
