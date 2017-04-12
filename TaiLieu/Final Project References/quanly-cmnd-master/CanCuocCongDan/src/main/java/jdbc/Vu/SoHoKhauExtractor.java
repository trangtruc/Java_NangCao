package jdbc.Vu;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import bean.An.SoHoKhau;
public class SoHoKhauExtractor {
	public SoHoKhau extractData(ResultSet rs) throws SQLException,
	DataAccessException {
		final String soHK = "SO_HK";
		final String soCC = "SO_CC";
		final String soKS = "SO_KS";
		final String quanHe = "QUAN_HE";
		SoHoKhau shk = new SoHoKhau();
		shk.setSoHK(rs.getString(soHK));
		shk.setSoCC(rs.getString(soCC));
		shk.setSoKS(rs.getString(soKS));
		shk.setQuanHe(rs.getString(quanHe));
		shk.setDiaChi("");
		return shk;
	}
}
