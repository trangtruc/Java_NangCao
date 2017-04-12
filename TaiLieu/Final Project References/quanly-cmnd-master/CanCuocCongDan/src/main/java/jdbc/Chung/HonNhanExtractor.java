package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.Chung.HonNhan;
import bean.Chung.TinhTrangHN;


public class HonNhanExtractor implements ResultSetExtractor<HonNhan> {
	
	@Override
	public HonNhan extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		
		final String soDK = "SO_DK";
		final String soCCChong = "SO_CC_CHONG";
		final String soCCVo = "SO_CC_VO";
		final String maTinhTrang = "TINH_TRANG_HN";
		final String tenTinhTrang = "TEN_TINH_TRANG";
		final String ghiChu = "GHI_CHU";
		
		HonNhan honNhan = new HonNhan();
		TinhTrangHN ttHonNhan = new TinhTrangHN();
		
		honNhan.setSoDK(rs.getString(soDK));
		honNhan.setSoCCChong(rs.getString(soCCChong));
		honNhan.setSoCCVo(rs.getString(soCCVo));
		//Set tinh trang hon nhan
		ttHonNhan.setMaTinhTrang(rs.getString(maTinhTrang));
		ttHonNhan.setTenTinhTrang(rs.getString(tenTinhTrang));
		honNhan.setTinhTrangHN(ttHonNhan);
		honNhan.setGhiChu(rs.getString(ghiChu));
		
		
		return honNhan;
	}
	
}
