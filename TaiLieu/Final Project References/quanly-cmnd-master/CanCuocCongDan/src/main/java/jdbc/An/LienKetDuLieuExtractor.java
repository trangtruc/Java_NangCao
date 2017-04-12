package jdbc.An;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import bean.An.LienKetDuLieu;

public class LienKetDuLieuExtractor {

	public LienKetDuLieu extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		final String maSo = "ma_so";
		final String soCC = "so_cc";
		final String soKS = "so_ks";
		LienKetDuLieu lk = new LienKetDuLieu();
		lk.setMaSo(rs.getInt(maSo));
		lk.setSoCC(rs.getString(soCC));
		lk.setSoKS(rs.getString(soKS));
		
		return lk;
	}

}
