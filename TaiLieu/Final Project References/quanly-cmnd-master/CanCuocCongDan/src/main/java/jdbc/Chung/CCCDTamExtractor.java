package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import bean.Chung.CCCDTam;
import bean.Chung.DanToc;
import bean.Chung.Huyen;
import bean.Chung.Tinh;
import bean.Chung.Xa;

public class CCCDTamExtractor implements ResultSetExtractor<CCCDTam> {

	@Override
	public CCCDTam extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		final String maSo = "MA_SO";
		final String maYeuCau = "MA_YEU_CAU";
		final String hoTen = "HO_TEN";
		final String hoTenKhac = "HO_TEN_KHAC";
		final String ngaySinh = "NGAY_SINH";
		final String gioiTinh = "GIOI_TINH";
		final String quocTich = "QUOC_TICH";
		final String maDT = "MA_DT";
		final String tenDT = "TEN_DT";
		final String tonGiao = "TON_GIAO";
		final String nhomMau = "MA_NHOM_MAU";
		final String maKSXa = "MA_KS_XA";
		final String tenKSXa = "TEN_KS_XA";
		final String maKSHuyen = "MA_KS_HUYEN";
		final String tenKSHuyen = "TEN_KS_HUYEN";
		final String maKSTinh = "MA_KS_TINH";
		final String tenKSTinh = "TEN_KS_TINH";
		final String maQQXa = "MA_QQ_XA";
		final String tenQQXa = "TEN_QQ_XA";
		final String maQQHuyen = "MA_QQ_HUYEN";
		final String tenQQHuyen = "TEN_QQ_HUYEN";
		final String maQQTinh = "MA_QQ_TINH";
		final String tenQQTinh = "TEN_QQ_TINH";
		final String maTTXa = "MA_TT_XA";
		final String tenTTXa = "TEN_TT_XA";
		final String maNOHTXa = "MA_NOHT_XA";
		final String tenNOHTXa = "TEN_NOHT_XA";
		final String maNOHTHuyen = "MA_NOHT_HUYEN";
		final String tenNOHTHuyen = "TEN_NOHT_HUYEN";
		final String maNOHTTinh = "MA_NOHT_TINH";
		final String tenNOHTTinh = "TEN_NOHT_TINH";
		final String maTTTinh = "MA_TT_TINH";
		final String tenTTTinh = "TEN_TT_TINH";
		final String maTTHuyen = "MA_TT_HUYEN";
		final String tenTTHuyen = "TEN_TT_HUYEN";
		final String trinhDo = "TRINH_DO";
		final String soCCCha = "SO_CC_CHA";
		final String soCCMe = "SO_CC_ME";
		final String soCCDD = "SO_CC_DD";
		final String soCCChuHo = "SO_CC_CHU_HO";
		final String quanHeChuHo = "QUAN_HE_CHU_HO";
		final String chuyenPhat = "CHUYEN_PHAT";
		final String ngheNghiep = "NGHE_NGHIEP";
		final String lanCap = "LAN_CAP";
		
		CCCDTam cccdTamThoi = new CCCDTam();

		Tinh ksTinh = new Tinh();
		Tinh qqTinh = new Tinh();
		Tinh ttTinh = new Tinh();
		Tinh nohtTinh = new Tinh();
		Huyen ksHuyen = new Huyen();
		Huyen qqHuyen = new Huyen();
		Huyen ttHuyen = new Huyen();
		Huyen nohtHuyen = new Huyen();
		Xa ksXa = new Xa();
		Xa qqXa = new Xa();
		Xa ttXa = new Xa();
		Xa nohtXa = new Xa();
		DanToc danToc = new DanToc();
		
		cccdTamThoi.setMaSo(rs.getInt(maSo));
		try {
			cccdTamThoi.setMaYeuCau(Integer.valueOf(rs.getString(maYeuCau)));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		cccdTamThoi.setHoTen(rs.getString(hoTen));
		cccdTamThoi.setHoTenKhac(rs.getString(hoTenKhac));
		cccdTamThoi.setNgaySinh(rs.getString(ngaySinh));
		cccdTamThoi.setGioiTinh(rs.getString(gioiTinh));
		cccdTamThoi.setQuocTich(rs.getString(quocTich));
		//Set DanToc
		danToc.setMaDT(rs.getString(maDT));
		danToc.setTenDT(rs.getString(tenDT));
		cccdTamThoi.setDanToc(danToc);
		cccdTamThoi.setTonGiao(rs.getString(tonGiao));
		cccdTamThoi.setNhomMau(rs.getString(nhomMau));
		//Set NoiDKKhaiSinh
		ksTinh.setMaTinh(rs.getString(maKSTinh));
		ksTinh.setTenTinh(rs.getString(tenKSTinh));
		ksHuyen.setTinh(ksTinh);
		ksHuyen.setMaHuyen(rs.getString(maKSHuyen));
		ksHuyen.setTenHuyen(rs.getString(tenKSHuyen));
		ksXa.setMaXa(rs.getString(maKSXa));
		ksXa.setTenXa(rs.getString(tenKSXa));
		ksXa.setHuyen(ksHuyen);
		cccdTamThoi.setNoiDKKhaiSinh(ksXa);
		//Set queQuan
		qqTinh.setMaTinh(rs.getString(maQQTinh));
		qqTinh.setTenTinh(rs.getString(tenQQTinh));
		qqHuyen.setTinh(qqTinh);
		qqHuyen.setMaHuyen(rs.getString(maQQHuyen));
		qqHuyen.setTenHuyen(rs.getString(tenQQHuyen));
		qqXa.setHuyen(qqHuyen);
		qqXa.setMaXa(rs.getString(maQQXa));
		qqXa.setTenXa(rs.getString(tenQQXa));
		cccdTamThoi.setQueQuan(qqXa);
		//Set dia chi thuong tru
		ttTinh.setMaTinh(rs.getString(maTTTinh));
		ttTinh.setTenTinh(rs.getString(tenTTTinh));
		ttHuyen.setTinh(ttTinh);
		ttHuyen.setMaHuyen(rs.getString(maTTHuyen));
		ttHuyen.setTenHuyen(rs.getString(tenTTHuyen));
		ttXa.setHuyen(ttHuyen);
		ttXa.setMaXa(rs.getString(maTTXa));
		ttXa.setTenXa(rs.getString(tenTTXa));
		cccdTamThoi.setThuongTru(ttXa);
		//Set noi o hien tai
		nohtTinh.setMaTinh(rs.getString(maNOHTTinh));
		nohtTinh.setTenTinh(rs.getString(tenNOHTTinh));
		nohtHuyen.setTinh(nohtTinh);
		nohtHuyen.setMaHuyen(rs.getString(maNOHTHuyen));
		nohtHuyen.setTenHuyen(rs.getString(tenNOHTHuyen));
		nohtXa.setHuyen(nohtHuyen);
		nohtXa.setMaXa(rs.getString(maNOHTXa));
		nohtXa.setTenXa(rs.getString(tenNOHTXa));
		cccdTamThoi.setNoiOHienTai(nohtXa);
		
		cccdTamThoi.setTrinhDo(rs.getString(trinhDo));
		//Set so cc cha
		String soCha = rs.getString(soCCCha);
		if("000000000000".equals(soCha)) {
			soCha = "";
		}
		cccdTamThoi.setSoCCCha(rs.getString(soCha));
		//Set so cc me
		String soMe = rs.getString(soCCMe);
		if("000000000000".equals(soMe)) {
			soMe = "";
		}
		cccdTamThoi.setSoCCMe(soMe);
		//Set so cc dd
		String soDD = rs.getString(soCCDD);
		if("000000000000".equals(soDD)) {
			soDD = "";
		}
		cccdTamThoi.setSoCCDD(soDD);
		//Set so cc chu ho
		String soChuHo = rs.getString(soCCChuHo);
		if("000000000000".equals(soChuHo)) {
			soChuHo = "";
		}
		cccdTamThoi.setSoCCChuHo(soChuHo);
		//Set noi dk lam cccd
		//huyen.setMaHuyen(rs.getString(noiDKLamViec));
		cccdTamThoi.setQuanHeChuHo(rs.getString(quanHeChuHo));
		//cccdTamThoi.setNoiDKLamViec(huyen);
		
		cccdTamThoi.setChuyenPhat(rs.getInt(chuyenPhat));
		cccdTamThoi.setNgheNghiep(rs.getString(ngheNghiep));
		cccdTamThoi.setLanCap(rs.getString(lanCap));
		return cccdTamThoi;
	}

}
