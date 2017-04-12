package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.DuyetDKKS;

public class DuyetDKKSRowMapper implements RowMapper<DuyetDKKS> {

	@Override
	public DuyetDKKS mapRow(ResultSet rs, int rowNum) throws SQLException {
		DuyetDKKSExtractor duyetDKKSExt = new DuyetDKKSExtractor();
		return duyetDKKSExt.extractData(rs);
	}

}
