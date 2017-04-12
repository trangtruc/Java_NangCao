package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import Constant.Const;
import bean.Chung.Huyen;
import bean.Chung.Tinh;

public class HuyenExtractor implements ResultSetExtractor<Huyen> {
	@Override
	public Huyen extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		Huyen huyen = new Huyen();
		Tinh tinh = new Tinh();
		tinh.setMaTinh(rs.getString(Const.MA_TINH));
		huyen.setTinh(tinh);
		huyen.setMaHuyen(rs.getString(Const.MA_HUYEN));
		huyen.setTenHuyen(rs.getString(Const.TEN_HUYEN));
		
		return huyen;
	}
	public Huyen extractData1(ResultSet rs) throws SQLException,
			DataAccessException {
		Huyen huyen = new Huyen();
		Tinh tinh = new Tinh();
		tinh.setMaTinh(rs.getString(Const.MA_TINH));
		tinh.setTenTinh(rs.getString(Const.TEN_TINH));
		huyen.setMaHuyen(rs.getString(Const.MA_HUYEN));
		huyen.setTenHuyen(rs.getString(Const.TEN_HUYEN));
		huyen.setTinh(tinh);
		return huyen;
	}
}
