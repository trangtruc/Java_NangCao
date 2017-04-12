package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.Chung.DuyetDKKS;

public class DuyetDKKSExtractor implements ResultSetExtractor<DuyetDKKS> {

	@Override
	public DuyetDKKS extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		
		final String soDK = "SO_DK";
		final String soKS = "SO_KS";
		final String ngayDuocDuyet = "NGAY_DUOC_DUYET";
		final String nguoiDuyet = "NGUOI_DUYET";
		final String ghiChu = "GHI_CHU";
		
		DuyetDKKS duyetDKKS = new DuyetDKKS();
		duyetDKKS.setSoDK(rs.getInt(soDK));
		duyetDKKS.setSoKS(rs.getString(soKS));
		duyetDKKS.setNgayDuocDuyet(rs.getString(ngayDuocDuyet));
		duyetDKKS.setNguoiDuyet(rs.getString(nguoiDuyet));
		duyetDKKS.setGhiChu(rs.getString(ghiChu));
		
		return duyetDKKS;
	}

}
