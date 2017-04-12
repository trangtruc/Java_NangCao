package jdbc.An;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.An.SoHoKhau;

public class ChiTietSoHoKhauExtractor implements ResultSetExtractor<SoHoKhau>{
	@Override
	public SoHoKhau extractData(ResultSet rs) throws SQLException,
	DataAccessException {
		final String soHK = "SO_HK";
		final String soCC = "SO_CC";
		final String soKS = "SO_KS";
		final String quanHe = "QUAN_HE";
		final String diaChi = "DIA_CHI";
		final String tinhTrang = "TINH_TRANG";
		final String ngayChuyenDen = "NGAY_CHUYEN_DEN";
		SoHoKhau shk = new SoHoKhau();
		shk.setSoHK(rs.getString(soHK));
		shk.setSoCC(rs.getString(soCC));
		shk.setSoKS(rs.getString(soKS));
		shk.setQuanHe(rs.getString(quanHe));
		shk.setDiaChi(rs.getString(diaChi));
		shk.setTinhTrang(rs.getString(tinhTrang));
		shk.setNgayChuyenDen(rs.getString(ngayChuyenDen));
		return shk;
	}
}
