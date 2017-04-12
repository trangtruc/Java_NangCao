package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import Constant.Const;
import bean.Chung.DanSoTinh;
import bean.Chung.Tinh;

public class DanSoTinhExtractor implements ResultSetExtractor<DanSoTinh> {

	@Override
	public DanSoTinh extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		
		DanSoTinh danSoTinh = new DanSoTinh();
		Tinh tinh = new Tinh();
		//Set tinh
		tinh.setMaTinh(rs.getString(Const.MA_TINH));
		tinh.setTenTinh(rs.getString(Const.TEN_TINH));
		danSoTinh.setTinh(tinh);
		danSoTinh.setDanSo(rs.getLong(Const.DAN_SO));
		danSoTinh.setTyLePhanTram(rs.getDouble(Const.TY_LE_PHAN_TRAM));
		
		return danSoTinh;
	}

}
