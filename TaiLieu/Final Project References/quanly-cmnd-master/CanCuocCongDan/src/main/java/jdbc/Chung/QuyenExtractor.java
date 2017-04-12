package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.Chung.Quyen;

public class QuyenExtractor implements ResultSetExtractor<Quyen> {
	@Override
	public Quyen extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		final String maQuyen = "MA_QUYEN";
		final String tenQuyen = "TEN_QUYEN";
		final String moTaQuyen = "MO_TA";
		Quyen quyen = new Quyen();
		quyen.setMaQuyen(rs.getInt(maQuyen));
		quyen.setTenQuyen(rs.getString(tenQuyen));
		quyen.setMoTaQuyen(rs.getString(moTaQuyen));
		return quyen;
	}
}
