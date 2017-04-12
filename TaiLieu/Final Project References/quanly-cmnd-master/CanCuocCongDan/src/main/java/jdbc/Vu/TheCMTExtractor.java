package jdbc.Vu;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import bean.Vu.TheCMT;

public class TheCMTExtractor {
	public TheCMT extractData(ResultSet rs) throws SQLException,
	DataAccessException {
		final String stt = "STT";
		final String maSo = "MA_SO";
		final String hoTen = "HO_TEN";
		final String soCC = "SO_CC";
		final String password = "PASSWORD";
		final String gioiTinh = "GIOI_TINH";
		final String quocTich = "QUOC_TICH";
		final String queQuan = "QUE_QUAN";
		final String hanSD = "HAN_SD";
		final String nguoiNhan = "NGUOI_NHAN";
		final String daLam = "DA_LAM";
		final String daTra = "DA_TRA";
		final String ngayTra = "NGAY_TRA";
		final String nguoiTra = "NGUOI_TRA";
		TheCMT dsLamThe = new TheCMT();
		dsLamThe.setStt(rs.getString(stt));
		dsLamThe.setMaSo(rs.getString(maSo));
		dsLamThe.setSoCC(rs.getString(soCC));
		dsLamThe.setHoTen(rs.getString(hoTen));
		dsLamThe.setPassword(rs.getString(password));
		dsLamThe.setGioiTinh(rs.getString(gioiTinh));
		dsLamThe.setQuocTich(rs.getString(quocTich));
		dsLamThe.setQueQuan(rs.getString(queQuan));
		dsLamThe.setHanSD(rs.getString(hanSD));
		dsLamThe.setNguoiNhan(rs.getString(nguoiNhan));
		dsLamThe.setDaLam(rs.getInt(daLam));
		dsLamThe.setDaTra(rs.getInt(daTra));
		dsLamThe.setNgayTra(rs.getString(ngayTra));
		dsLamThe.setNguoiTra(rs.getString(nguoiTra));
		return dsLamThe;
	}
}
