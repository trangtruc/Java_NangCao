package jdbc.Config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.Config.ConfigSoCC;

public class ConfigSoCCExtractor implements ResultSetExtractor<ConfigSoCC>{

	@Override
	public ConfigSoCC extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		final String nam = "NAM";
		final String moTa = "MO_TA";
		final String giaTriNamTruoc = "GIA_TRI_NAM_TRUOC";
		final String giaTriNuTruoc = "GIA_TRI_NU_TRUOC";
		final String giaTriNamSau = "GIA_TRI_NAM_SAU";
		final String giaTriNuSau = "GIA_TRI_NU_SAU";
		ConfigSoCC csc = new ConfigSoCC();
		csc.setNam(rs.getInt(nam));
		csc.setMoTa(rs.getString(moTa));
		csc.setGiaTriNamTruoc(rs.getInt(giaTriNamTruoc));
		csc.setGiaTriNuTruoc(rs.getInt(giaTriNuTruoc));
		csc.setGiaTriNamSau(rs.getInt(giaTriNamSau));
		csc.setGiaTriNuSau(rs.getInt(giaTriNuSau));
		return csc;
	}

}
