package jdbc.Vu;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import bean.Vu.TTDKCCCD;

public class TTDKCCCDExtractor {
	
	public TTDKCCCD extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		final String maSo = "MA_SO";
		final String soKS = "SO_KS";
		final String yeuCau = "MA_YEU_CAU";
		final String hinhThe = "HINH_THE";
		final String vanTayTroTrai = "VAN_TAY_TRO_TRAI";
		final String vanTayTroPhai = "VAN_TAY_TRO_PHAI";
		final String nhanDang = "NHAN_DANG";
		final String hoTen = "HO_TEN";
		final String hoTenKhac = "HO_TEN_KHAC";
		final String ngaySinh = "NGAY_SINH";
		final String soCC = "SO_CC_CU";
		final String gioiTinh = "GIOI_TINH";
		final String maDT = "MA_DT";
		final String quocTich = "QUOC_TICH";
		final String tonGiao = "TON_GIAO";
		final String nhomMau = "MA_NHOM_MAU";
		final String noiOHienTai = "NOI_O_HIEN_TAI";
		final String trinhDo = "TRINH_DO";
		final String ngheNghiep = "NGHE_NGHIEP";
		final String soCCDD = "SO_CC_DD";
		final String noiDKLV = "NOI_DK_LAM_VIEC";
		final String chuyenPhat = "CHUYEN_PHAT";
		final String ketQuaXacMinh = "KET_QUA_XAC_MINH";
		final String lanCap = "LAN_CAP";
		final String ngayDK = "NGAY_DK";
		final String ngayHen = "NGAY_HEN";
		final String ngayXacNhan = "NGAY_XAC_NHAN";
		final String nguoiKiemTra = "NGUOI_KIEM_TRA";
		final String duyet = "DUYET";
		final String maDuyet = "MA_DUYET";
		final String lyDo = "LY_DO";	
		TTDKCCCD ttdkCCCD = new TTDKCCCD();
		ttdkCCCD.setMaSo(rs.getInt(maSo));
		ttdkCCCD.setSoKhaiSinh(rs.getString(soKS));
		ttdkCCCD.setMaYeuCau(rs.getInt(yeuCau));
		ttdkCCCD.setHinhThe(rs.getBinaryStream(hinhThe));
		ttdkCCCD.setVanTayTroTrai(rs.getBinaryStream(vanTayTroTrai));
		ttdkCCCD.setVanTayTroPhai(rs.getBinaryStream(vanTayTroPhai));
		ttdkCCCD.setNhanDang(rs.getString(nhanDang));
		ttdkCCCD.setHoTen(rs.getString(hoTen));
		ttdkCCCD.setHoTenKhac(rs.getString(hoTenKhac));
		ttdkCCCD.setNgaySinh(rs.getString(ngaySinh));
		ttdkCCCD.setSoCC(rs.getString(soCC));
		ttdkCCCD.setGioiTinh(rs.getString(gioiTinh));
		ttdkCCCD.setMaDT(rs.getString(maDT));
		ttdkCCCD.setQuocTich(rs.getString(quocTich));
		ttdkCCCD.setTonGiao(rs.getString(tonGiao));
		ttdkCCCD.setNhomMau(rs.getString(nhomMau));
		ttdkCCCD.setNoiOHienTai(rs.getString(noiOHienTai));
		ttdkCCCD.setNgheNghiep(rs.getString(ngheNghiep));
		ttdkCCCD.setTrinhDo(rs.getString(trinhDo));
		ttdkCCCD.setSoCCDD(rs.getString(soCCDD));
		ttdkCCCD.setLanCap(rs.getString(lanCap));
		ttdkCCCD.setNoiDKLamViec(rs.getString(noiDKLV));
		ttdkCCCD.setChuyenPhat(rs.getInt(chuyenPhat));
		ttdkCCCD.setKetQuaXacMinh(rs.getString(ketQuaXacMinh));
		// doi noi dang ky lam viec thanh noi cap
		if(ttdkCCCD.getNoiDKLamViec().length() > 2){
			String[] tinh = ttdkCCCD.getNoiDKLamViec().split("-");
			String noiCap = tinh[0];
			ttdkCCCD.setNoiCap(noiCap);
		} else {
			ttdkCCCD.setNoiCap(ttdkCCCD.getNoiDKLamViec());
		}
		
		ttdkCCCD.setNgayDK(rs.getString(ngayDK));
		ttdkCCCD.setNgayHen(rs.getString(ngayHen));
		ttdkCCCD.setNgayXacNhan(rs.getString(ngayXacNhan));
		ttdkCCCD.setNguoiKiemTra(rs.getString(nguoiKiemTra));
		ttdkCCCD.setDuyet(rs.getInt(duyet));
		ttdkCCCD.setMaDuyet(rs.getString(maDuyet));
		ttdkCCCD.setLyDo(rs.getString(lyDo));
		return ttdkCCCD;
	}

}
