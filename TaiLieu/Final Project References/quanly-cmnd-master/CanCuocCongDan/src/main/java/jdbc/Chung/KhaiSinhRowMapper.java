
package jdbc.Chung;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.KhaiSinh;

public class KhaiSinhRowMapper implements RowMapper<KhaiSinh> {

	@Override
	public KhaiSinh mapRow(ResultSet rs, int rowNum) throws SQLException {
		KhaiSinhExtractor ksExt = new KhaiSinhExtractor();
		return ksExt.extractData(rs);
	}

}
