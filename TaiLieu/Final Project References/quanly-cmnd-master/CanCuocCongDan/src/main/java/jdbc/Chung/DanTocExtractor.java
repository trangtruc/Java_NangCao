package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.Chung.DanToc;

public class DanTocExtractor implements ResultSetExtractor<DanToc>{

	@Override
	public DanToc extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		final String maDanToc = "MA_DT";
		final String tenDanToc = "TEN_DT";
		DanToc danToc =  new DanToc();
		danToc.setMaDT(rs.getString(maDanToc));
		danToc.setTenDT(rs.getString(tenDanToc));
		return danToc;
	}

}
