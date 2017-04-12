package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import Constant.Const;
import bean.Chung.Huyen;
import bean.Chung.Tinh;
import bean.Chung.Xa;

public class XaExtractor implements ResultSetExtractor<Xa> {

	@Override
	public Xa extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		Xa xa = new Xa();
		Huyen huyen = new Huyen();
		huyen.setMaHuyen(rs.getString(Const.MA_HUYEN));
		xa.setHuyen(huyen);
		xa.setMaXa(rs.getString(Const.MA_XA));
		xa.setTenXa(rs.getString(Const.TEN_XA));
		
		return xa;
	}
	public Xa extractData1(ResultSet rs) throws SQLException,
			DataAccessException {
		Xa xa = new Xa();
		Huyen huyen = new Huyen();
		Tinh tinh = new Tinh();
		tinh.setMaTinh(rs.getString(Const.MA_TINH));
		tinh.setTenTinh(rs.getString(Const.TEN_TINH));
		huyen.setMaHuyen(rs.getString(Const.MA_HUYEN));
		huyen.setTenHuyen(rs.getString(Const.TEN_HUYEN));
		huyen.setTinh(tinh);
		xa.setMaXa(rs.getString(Const.MA_XA));
		xa.setTenXa(rs.getString(Const.TEN_XA));
		xa.setHuyen(huyen);
		return xa;
	}
	
}
