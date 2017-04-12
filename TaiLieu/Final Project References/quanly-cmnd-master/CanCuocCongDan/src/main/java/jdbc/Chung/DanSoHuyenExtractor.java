package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import Constant.Const;
import bean.Chung.DanSoHuyen;
import bean.Chung.Huyen;

public class DanSoHuyenExtractor implements ResultSetExtractor<DanSoHuyen> {

	@Override
	public DanSoHuyen extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		
		DanSoHuyen danSoHuyen = new DanSoHuyen();
		Huyen huyen = new Huyen();
		
		huyen.setMaHuyen(rs.getString(Const.MA_HUYEN));
		huyen.setTenHuyen(rs.getString(Const.TEN_HUYEN));
		danSoHuyen.setHuyen(huyen);
		danSoHuyen.setDanSo(rs.getLong(Const.DAN_SO));
		danSoHuyen.setTyLePhanTram(rs.getDouble(Const.TY_LE_PHAN_TRAM));
		
		return danSoHuyen;
	}

}
