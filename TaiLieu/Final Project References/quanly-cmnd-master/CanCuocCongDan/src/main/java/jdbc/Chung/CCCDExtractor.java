package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import bean.Chung.DanToc;
import bean.Chung.Tinh;
import bean.Chung.Huyen;
import bean.Chung.Xa;

public class CCCDExtractor {
	public bean.Chung.CCCD extractData(ResultSet rs) throws SQLException,
			DataAccessException {
			final String soCC = "SO_CC";
			final String hoTen = "HO_TEN";
			final String ngaySinh = "NGAY_SINH";
			final String gioiTinh = "GIOI_TINH";
			final String quocTich = "QUOC_TICH";
			final String tenDT = "TEN_DT";
			
			final String tenTTXa = "TEN_TT_XA";
			final String tenTTHuyen = "TEN_TT_HUYEN";
			final String tenTTTinh = "TEN_TT_TINH";
			final String tenNoiCap = "TEN_NOI_CAP";
			
			bean.Chung.CCCD cccd = new bean.Chung.CCCD();
			DanToc dt = new DanToc();
			Xa ttXa = new Xa();
			Huyen ttHuyen = new Huyen();
			Tinh ttTinh = new Tinh();
			Tinh noiCapTinh = new Tinh();
			
			cccd.setSoCC(rs.getString(soCC));
			cccd.setHoTen(rs.getString(hoTen));
			//set dan toc
			dt.setTenDT(rs.getString(tenDT));
			cccd.setGioiTinh(rs.getString(gioiTinh));
			cccd.setDanToc(dt);
			cccd.setNgaySinh(rs.getString(ngaySinh));
			cccd.setQuocTich(rs.getString(quocTich));
			//set thuong tru xa
			ttTinh.setTenTinh(rs.getString(tenTTTinh));
			ttHuyen.setTinh(ttTinh);
			ttHuyen.setTenHuyen(rs.getString(tenTTHuyen));
			ttXa.setHuyen(ttHuyen);
			ttXa.setTenXa(rs.getString(tenTTXa));
			cccd.setThuongTru(ttXa);
			//set noi cap can cuoc
			noiCapTinh.setTenTinh(rs.getString(tenNoiCap));
			cccd.setNoiCap(noiCapTinh);
			return cccd;
	}
}
