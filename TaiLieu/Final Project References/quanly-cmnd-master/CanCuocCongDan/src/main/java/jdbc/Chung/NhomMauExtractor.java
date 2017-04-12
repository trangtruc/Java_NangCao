package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.Chung.NhomMau;

public class NhomMauExtractor implements ResultSetExtractor<NhomMau> {
	@Override
	public NhomMau extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		final String maNM = "MA_NHOM_MAU";
		final String tenNM = "TEN_NHOM_MAU";
		NhomMau nhomMau = new NhomMau();
		nhomMau.setMaNM(rs.getString(maNM));
		nhomMau.setTenNM(rs.getString(tenNM));
		
		return nhomMau;
	}
}
