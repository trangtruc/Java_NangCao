package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import Constant.Const;
import bean.Chung.DanSoXa;
import bean.Chung.Xa;

public class DanSoXaExtractor implements ResultSetExtractor<DanSoXa> {

	@Override
	public DanSoXa extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		
		DanSoXa danSoXa = new DanSoXa();
		Xa xa = new Xa();
		xa.setMaXa(rs.getString(Const.MA_XA));
		xa.setTenXa(rs.getString(Const.TEN_XA));
		danSoXa.setXa(xa);
		danSoXa.setDanSo(rs.getLong(Const.DAN_SO));
		danSoXa.setTyLePhanTram(rs.getDouble(Const.TY_LE_PHAN_TRAM));
		
		return danSoXa;
	}
	
}
