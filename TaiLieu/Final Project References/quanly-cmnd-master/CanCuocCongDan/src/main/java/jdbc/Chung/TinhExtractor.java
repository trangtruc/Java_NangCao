package jdbc.Chung;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import Constant.Const;
import bean.Chung.Tinh;

public class TinhExtractor implements ResultSetExtractor<Tinh> {

	@Override
	public Tinh extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		Tinh tinh = new Tinh();
		tinh.setMaTinh(rs.getString(Const.MA_TINH));
		tinh.setTenTinh(rs.getString(Const.TEN_TINH));
		
		return tinh;
	}

}
