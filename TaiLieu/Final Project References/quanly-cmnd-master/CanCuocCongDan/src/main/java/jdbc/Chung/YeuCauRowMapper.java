package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.YeuCau;

public class YeuCauRowMapper implements RowMapper<YeuCau> {
	
	@Override
	public YeuCau mapRow(ResultSet rs, int rowNum) throws SQLException {
		YeuCauExtractor yeuCauExt = new YeuCauExtractor();
		return yeuCauExt.extractData(rs);
	}
}
