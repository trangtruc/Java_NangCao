package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.Chung.YeuCau;

public class YeuCauExtractor implements ResultSetExtractor<YeuCau> {
	@Override
	public YeuCau extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		final String maYeuCau = "MA_YEU_CAU";
		final String tenYeuCau = "TEN_YEU_CAU";
		final String moTa = "MO_TA";
		final String giayTo = "GIAY_TO";
		final String lePhi = "LE_PHI";
		final String tinhTrang = "TINH_TRANG";
		YeuCau yeuCau = new YeuCau();
		yeuCau.setMaYeuCau(rs.getInt(maYeuCau));
		yeuCau.setTenYeuCau(rs.getString(tenYeuCau));
		yeuCau.setMoTa(rs.getString(moTa));
		yeuCau.setGiayTo(rs.getString(giayTo));
		yeuCau.setLePhi(rs.getInt(lePhi));
		yeuCau.setTinhTrang(rs.getString(tinhTrang));
		return yeuCau;
	}
}
