package jdbc.An;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.An.LienKetDuLieu;



public class LienKetDuLieuRowMapper implements RowMapper<LienKetDuLieu>{

	@Override
	public LienKetDuLieu mapRow(ResultSet rs, int rowNum) throws SQLException {
		LienKetDuLieuExtractor lienKetDuLieu = new LienKetDuLieuExtractor();
		return lienKetDuLieu.extractData(rs);
	}

}
