package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import Constant.Const;
import bean.Chung.DanToc;
import bean.Chung.TTDKKhaiSinh;
import bean.Chung.YeuCau;

public class TTDKKhaiSinhExtractor implements ResultSetExtractor<TTDKKhaiSinh> {

	@Override
	public TTDKKhaiSinh extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		
		TTDKKhaiSinh ttdk = new TTDKKhaiSinh();
		DanToc danToc = new DanToc();
		
		ttdk.setSoKSCu(rs.getString(Const.SO_KS_CU));
		ttdk.setSoKS(rs.getString(Const.SO_KS));
		ttdk.setHoTen(rs.getString(Const.HO_TEN));
		ttdk.setNgaySinh(rs.getString(Const.NGAY_SINH));
		ttdk.setGioiTinh(rs.getString(Const.GIOI_TINH));
		ttdk.setQuocTich(rs.getString(Const.QUOC_TINH));
		//Set DanToc
		danToc.setMaDT(rs.getString(Const.MA_DT));
		danToc.setTenDT(rs.getString(Const.TEN_DT));
		ttdk.setDanToc(danToc);
		ttdk.setBenhVien(rs.getString(Const.BENH_VIEN));
		ttdk.setNoiSinh(rs.getString(Const.NOI_SINH));
		ttdk.setQueQuan(rs.getString(Const.QUE_QUAN));
		ttdk.setSoCCNguoiYeuCau(rs.getString(Const.SO_CC_NGUOI_YEU_CAU));
		ttdk.setQuanHeVoiNguoiKS(rs.getString(Const.QUAN_HE_VOI_NGUOI_KS));
		ttdk.setSoCCCha(rs.getString(Const.SO_CC_CHA));
		ttdk.setSoCCMe(rs.getString(Const.SO_CC_ME));
		ttdk.setNgayDangKy(rs.getString(Const.NGAY_DANG_KY));
		ttdk.setNgayHenLamViec(rs.getString(Const.NGAY_HEN_LAM_VIEC));
		ttdk.setNoiDKLV(rs.getString(Const.NOI_DKLV));
		//Set yeuCau
		YeuCau yeuCau = new YeuCau();
		yeuCau.setMaYeuCau(rs.getInt(Const.MA_YEU_CAU));
		yeuCau.setTenYeuCau(rs.getString(Const.TEN_YEU_CAU));
		ttdk.setYeuCau(yeuCau);
		ttdk.setTrangThai(rs.getInt(Const.TRANG_THAI));
		ttdk.setGhiChu(rs.getString(Const.GHI_CHU));
		
		return ttdk;
		
	}

}
