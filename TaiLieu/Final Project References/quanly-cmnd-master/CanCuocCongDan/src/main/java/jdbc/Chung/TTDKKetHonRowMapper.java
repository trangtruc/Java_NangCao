package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.TTDKKetHon;

public class TTDKKetHonRowMapper implements RowMapper<TTDKKetHon>{

	@Override
	public TTDKKetHon mapRow(ResultSet rs, int rowNum) throws SQLException {
		TTDKKetHonExtractor ttdkExt = new TTDKKetHonExtractor();
		return ttdkExt.extractData(rs);
	}

}
