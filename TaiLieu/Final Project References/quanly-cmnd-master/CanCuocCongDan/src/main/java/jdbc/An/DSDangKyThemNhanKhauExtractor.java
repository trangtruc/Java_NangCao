package jdbc.An;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.An.DSThemNhanKhau;

public class DSDangKyThemNhanKhauExtractor implements ResultSetExtractor<DSThemNhanKhau>{
		@Override
		public DSThemNhanKhau extractData(ResultSet rs) throws SQLException,
		DataAccessException {
			final String soHKMoi = "SO_HK_MOI";
			final String soHKCU = "SO_HK_CU";
			final String soKS = "SO_KS";
			final String soCC = "SO_CC";
			final String quanHe = "QUAN_HE";
			final String ngayDuyet = "NGAY_DUYET";
			final String ngayDangKy = "NGAY_DANG_KY";
			final String noiDangKyLamViec = "NOI_DK_LAM_VIEC";
			final String nguoiDuyet = "NGUOI_DUYET";
			final String lyDo=  "LY_DO";
			final String duyet = "DUYET";
			final String tinhTrang = "TINH_TRANG";
			final String ngayHen = "NGAY_HEN";
			DSThemNhanKhau shk = new DSThemNhanKhau();
			shk.setSoHKMoi(rs.getString(soHKMoi));
			shk.setSoHKCU(rs.getString(soHKCU));
			shk.setSoKS(rs.getString(soKS));
			shk.setSoCC(rs.getString(soCC));
			shk.setQuanHe(rs.getString(quanHe));
			shk.setNgayDangKy(rs.getString(ngayDangKy));
			shk.setNgayDuyet(rs.getString(ngayDuyet));
			shk.setNoiDangKyLamViec(rs.getString(noiDangKyLamViec));
			shk.setNguoiDuyet(rs.getString(nguoiDuyet));
			shk.setLyDo(rs.getString(lyDo));
			shk.setDuyet(rs.getString(duyet));
			shk.setTinhTrang(rs.getString(tinhTrang));
			shk.setNgayHen(rs.getString(ngayHen));
			return shk;
	}

}
