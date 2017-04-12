package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.TTDKKhaiSinh;

public class TTDKKhaiSinhRowMapper implements RowMapper<TTDKKhaiSinh> {

	@Override
	public TTDKKhaiSinh mapRow(ResultSet rs, int rowNum) throws SQLException {
		TTDKKhaiSinhExtractor ttdkExt = new TTDKKhaiSinhExtractor();
		return ttdkExt.extractData(rs);
	}

}
