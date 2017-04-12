package jdbc.An;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.An.SoHoKhau;

public class SoHoKhauExtractor1 implements ResultSetExtractor<SoHoKhau>{
	@Override
	public SoHoKhau extractData(ResultSet rs) throws SQLException,
	DataAccessException {
		final String soHK = "SO_HK";
		final String diaChi = "DIA_CHI";
		SoHoKhau shk = new SoHoKhau();
		shk.setSoHK(rs.getString(soHK));
		shk.setDiaChi(rs.getString(diaChi));
		return shk;
	}
}
