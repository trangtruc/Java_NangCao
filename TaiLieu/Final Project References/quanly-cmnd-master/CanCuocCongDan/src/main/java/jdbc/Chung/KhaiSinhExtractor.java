
package jdbc.Chung;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import Constant.Const;
import bean.Chung.DanToc;
import bean.Chung.KhaiSinh;

public class KhaiSinhExtractor implements ResultSetExtractor<KhaiSinh> {

	@Override
	public KhaiSinh extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		
		KhaiSinh ks = new KhaiSinh();
		DanToc dt = new DanToc();
		
		ks.setSoKS(rs.getString(Const.SO_KS));
		ks.setHoTen(rs.getString(Const.HO_TEN));
		ks.setNgaySinh(rs.getString(Const.NGAY_SINH));
		ks.setGioiTinh(rs.getString(Const.GIOI_TINH));
		ks.setQuocTich(rs.getString(Const.QUOC_TINH));
		//Set dan toc
		dt.setMaDT(rs.getString(Const.MA_DT));
		dt.setTenDT(rs.getString(Const.TEN_DT));
		ks.setDanToc(dt);
		ks.setBenhVien(rs.getString(Const.BENH_VIEN));
		//set noi cap
		ks.setNoiCap(rs.getString(Const.NOI_CAP));
		//Set noi sinh
		ks.setNoiSinh(rs.getString(Const.NOI_SINH));
		ks.setQueQuan(rs.getString(Const.QUE_QUAN));
		//Set so can cuoc nguoi yeu cau
		ks.setSoCCNguoiYeuCau(rs.getString(Const.SO_CC_NGUOI_YEU_CAU));
		ks.setQuanHeVoiNguoiKS(rs.getString(Const.QUAN_HE_VOI_NGUOI_KS));
		//Set so can cuoc cua cha
		ks.setSoCCCha(rs.getString(Const.SO_CC_CHA));
		//Set so can cuoc me
		ks.setSoCCMe(rs.getString(Const.SO_CC_ME));
		
		return ks;
	}

}
