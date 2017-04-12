package jdbc.Vu;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import bean.Vu.CCCD;

public class CCCDExtractor {
	public CCCD extractData(ResultSet rs) throws SQLException,
	DataAccessException {
			final String soCC = "SO_CC";
			final String hinhThe = "HINH_THE";
			final String vanTayTroTrai = "VAN_TAY_TRO_TRAI";
			final String vanTayTroPhai = "VAN_TAY_TRO_PHAI";
			final String nhanDang = "NHAN_DANG";
			final String hoTen = "HO_TEN";
			final String hoTenKhac = "HO_TEN_KHAC";
			final String ngaySinh = "NGAY_SINH";
			final String gioiTinh = "GIOI_TINH";
			final String maDT = "MA_DT";
			final String quocTich = "QUOC_TICH";
			final String tonGiao = "TON_GIAO";
			final String nhomMau = "MA_NHOM_MAU";
			final String noiOHienTai = "NOI_O_HIEN_TAI";
			final String trinhDo = "TRINH_DO";
			final String ngheNghiep = "NGHE_NGHIEP";
			final String soCCDD = "SO_CC_DD";
			final String noiCap = "NOI_CAP";
			final String ngayCap = "NGAY_CAP";
			final String lanCap = "LAN_CAP";
			final String nguoiDuyet = "NGUOI_DUYET";
			final String nguoiNhap = "NGUOI_NHAP";
			final String maDuyet = "MA_DUYET";
			final String maNhap = "MA_NHAP";
			final String tinhTrang = "TINH_TRANG";
			CCCD cccd = new CCCD();
			cccd.setSoCC(rs.getString(soCC));
			cccd.setHinhThe(rs.getBinaryStream(hinhThe));
			cccd.setVanTayTroTrai(rs.getBinaryStream(vanTayTroTrai));
			cccd.setVanTayTroPhai(rs.getBinaryStream(vanTayTroPhai));
			cccd.setNhanDang(rs.getString(nhanDang));
			cccd.setHoTen(rs.getString(hoTen));
			cccd.setHoTenKhac(rs.getString(hoTenKhac));
			cccd.setNgaySinh(rs.getString(ngaySinh));
			cccd.setGioiTinh(rs.getString(gioiTinh));
			cccd.setMaDT(rs.getString(maDT));
			cccd.setQuocTich(rs.getString(quocTich));
			cccd.setTonGiao(rs.getString(tonGiao));
			cccd.setNhomMau(rs.getString(nhomMau));
			cccd.setNoiOHienTai(rs.getString(noiOHienTai));
			cccd.setNgheNghiep(rs.getString(ngheNghiep));
			cccd.setTrinhDo(rs.getString(trinhDo));
			cccd.setSoCCDD(rs.getString(soCCDD));
			cccd.setNoiCap(rs.getString(noiCap));
			cccd.setNgayCap(rs.getString(ngayCap));
			cccd.setLanCap(rs.getString(lanCap));
			cccd.setNguoiDuyet(rs.getString(nguoiDuyet));
			cccd.setMaDuyet(rs.getString(maDuyet));
			cccd.setTinhTrang(rs.getString(tinhTrang));
			return cccd;
}
}
