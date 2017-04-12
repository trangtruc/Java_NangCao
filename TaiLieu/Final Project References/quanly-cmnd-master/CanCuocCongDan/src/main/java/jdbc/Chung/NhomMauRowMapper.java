package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.NhomMau;


public class NhomMauRowMapper implements RowMapper<NhomMau> {

	@Override
	public NhomMau mapRow(ResultSet rs, int rowNum) throws SQLException {
		NhomMauExtractor nhomMauExt = new NhomMauExtractor();
		return nhomMauExt.extractData(rs);
	}
}
