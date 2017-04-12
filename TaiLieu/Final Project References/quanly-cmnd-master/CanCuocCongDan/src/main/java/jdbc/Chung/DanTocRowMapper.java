package jdbc.Chung;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.DanToc;
public class DanTocRowMapper implements RowMapper<DanToc> {

	@Override
	public DanToc mapRow(ResultSet rs, int rowNum) throws SQLException {
		DanTocExtractor danTocExt = new DanTocExtractor();
		return danTocExt.extractData(rs);
	}

}
