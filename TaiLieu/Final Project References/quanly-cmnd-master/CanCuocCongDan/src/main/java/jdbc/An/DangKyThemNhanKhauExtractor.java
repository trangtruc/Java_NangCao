package jdbc.An;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.An.DSThemNhanKhau;

public class DangKyThemNhanKhauExtractor implements ResultSetExtractor<DSThemNhanKhau>{
		@Override
		public DSThemNhanKhau extractData(ResultSet rs) throws SQLException,
		DataAccessException {
			final String soHKMoi = "SO_HK_MOI";
			final String lyDo = "LY_DO";
			final String duyet = "DUYET";
			final String ngayHen ="NGAY_HEN";
			DSThemNhanKhau shk = new DSThemNhanKhau();
			shk.setSoHKMoi(rs.getString(soHKMoi));
			shk.setLyDo(rs.getString(lyDo));
			shk.setDuyet(rs.getString(duyet));
			shk.setNgayHen(rs.getString(ngayHen));
			return shk;
	}

}
