package jdbc.Chung;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import Constant.Const;
import bean.Chung.DanSoVung;
import bean.Chung.Vung;

public class DanSoVungExtractor implements ResultSetExtractor<DanSoVung> {

	@Override
	public DanSoVung extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		
		DanSoVung danSoVung = new DanSoVung();
		Vung vung = new Vung();
		
		vung.setMaVung(rs.getInt(Const.MA_VUNG));
		vung.setTenVung(rs.getString(Const.TEN_VUNG));
		danSoVung.setVung(vung);
		danSoVung.setDanSo(rs.getLong(Const.DAN_SO));
		danSoVung.setTyLePhanTram(rs.getDouble(Const.TY_LE_PHAN_TRAM));
		
		return danSoVung;
	}

}
