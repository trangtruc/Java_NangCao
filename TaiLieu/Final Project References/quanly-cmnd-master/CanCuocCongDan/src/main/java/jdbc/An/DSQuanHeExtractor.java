package jdbc.An;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.An.DSQuanHe;

public class DSQuanHeExtractor implements ResultSetExtractor<DSQuanHe>{
	@Override
	public DSQuanHe extractData(ResultSet rs) throws SQLException,
	DataAccessException {
		final String quanHe = "quan_he";
		final String tenQuanHe = "ten_quan_he";
		DSQuanHe dsQuanHe = new DSQuanHe();
		dsQuanHe.setQuanHe(rs.getString(quanHe));
		dsQuanHe.setTenQuanHe(rs.getString(tenQuanHe));
		return dsQuanHe;
	}
}
