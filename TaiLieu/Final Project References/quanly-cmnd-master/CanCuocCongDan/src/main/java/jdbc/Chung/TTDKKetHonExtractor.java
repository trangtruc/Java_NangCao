package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import Constant.Const;
import bean.Chung.TTDKKetHon;

public class TTDKKetHonExtractor implements ResultSetExtractor<TTDKKetHon>{

	@Override
	public TTDKKetHon extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		//commit
		final String soDK = "SO_DK";
		final String soCCNguoiDK = "SO_CC_NGUOI_DK";
		final String soCCVoHoacChong = "SO_CC_VO_HOAC_CHONG";
		final String ngayDangKy = "NGAY_DANG_KY";
		final String ngayHenLamViec = "NGAY_HEN_LAM_VIEC";
		final String trangThai = "TRANG_THAI";
		final String noiDKLV = "NOI_DKLV";
		final String maXacNhan = "MA_XAC_NHAN";
		TTDKKetHon ttdk = new TTDKKetHon();
		ttdk.setSoDK(rs.getString(soDK));
		ttdk.setSoCCNguoiDK(rs.getString(soCCNguoiDK));
		ttdk.setSoCCVoHoacChong(rs.getString(soCCVoHoacChong));
		ttdk.setNgayDangKy(rs.getString(ngayDangKy));
		ttdk.setNgayHenLamViec(rs.getString(ngayHenLamViec));
		ttdk.setTrangThai(rs.getInt(trangThai));
		ttdk.setNoiDKLV(rs.getString(noiDKLV));
		ttdk.setMaXacNhan(rs.getLong(maXacNhan));
		ttdk.setGhiChu(rs.getString(Const.GHI_CHU));
		return ttdk;
	}

}
