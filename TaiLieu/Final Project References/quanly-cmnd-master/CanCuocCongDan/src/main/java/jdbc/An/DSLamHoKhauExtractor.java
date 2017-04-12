package jdbc.An;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.An.DSLamHoKhau;

public class DSLamHoKhauExtractor implements ResultSetExtractor<DSLamHoKhau>{
	@Override
	public DSLamHoKhau extractData(ResultSet rs) throws SQLException,
	DataAccessException {
		final String maSo = "MA_SO";
		final String soHKCu = "SO_HK_CU";
		final String soCCNguoiDK = "SO_CC_NGUOI_DK";
		final String diaChi = "DIA_CHI";
		final String soKSThanhVien = "SO_KS_THANH_VIEN";
		final String soCCThanhVien = "SO_CC_THANH_VIEN";
		final String quanHe = "QUAN_HE";
		final String duyet = "DUYET";
		final String lyDo = "LY_DO";
		final String tinhTrang ="TINH_TRANG";
		DSLamHoKhau shk = new DSLamHoKhau();
		shk.setMaSo(rs.getString(maSo));
		shk.setSoHKCu(rs.getString(soHKCu));
		shk.setSoCCNguoiDK(rs.getString(soCCNguoiDK));
		shk.setDiaChi(rs.getString(diaChi));
		shk.setSoKSThanhVien(rs.getString(soKSThanhVien));
		shk.setSoCCThanhVien(rs.getString(soCCThanhVien));
		shk.setQuanHe(rs.getString(quanHe));
		shk.setDuyet(rs.getString(duyet));
		shk.setLydo(rs.getString(lyDo));
		shk.setTinhTrang(rs.getString(tinhTrang));
		return shk;
	}
}
