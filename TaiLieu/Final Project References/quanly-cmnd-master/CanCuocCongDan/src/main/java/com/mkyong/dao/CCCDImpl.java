package com.mkyong.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mkyong.services.CCCDService;
import com.mkyong.services.ConfigSoCCService;
import com.mkyong.services.HuyenService;
import com.mkyong.services.KhaiSinhService;
import com.mkyong.services.SoHoKhauService;
import com.mkyong.services.TinhService;
import com.mkyong.services.XaService;
import com.mkyong.services.function.FunctionService;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import jdbc.Vu.CCCDRowMapper;
import bean.An.SoHoKhau;
import bean.Chung.KhaiSinh;
import bean.Config.ConfigSoCC;
import bean.Vu.CCCD;
import bean.Vu.TTDKCCCD;

public class CCCDImpl implements CCCDDao {
	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;
	@Autowired
	HttpSession session;
	@Autowired
	private FunctionService functionService; 
	public CCCDImpl() {
		super();
	}

	@Override
	public Boolean insertCCCD(TTDKCCCD cccd) {
		if(cccd.getHoTenKhac() == null){
			cccd.setHoTenKhac("");
		}
		cccd.setNoiCap(session.getAttribute("ssCoQuan").toString());
		String sql = "INSERT INTO cccd (SO_CC,"
						+ " MA_SO,"
						+ " NOI_CAP, NGAY_CAP, LAN_CAP, NGUOI_DUYET) "
						+ "VALUES (?, ?, ?, ?, ?, ?"
						+ ")";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql,
							new Object[] {  cccd.getSoCC(),
											cccd.getMaSo(), 
											cccd.getNoiCap(),
											cccd.getNgayCap(), cccd.getLanCap(),
											session.getAttribute("ssHoTen")});
		if (i > 0) {
			System.out.println("Insert 1 row");
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean updateCCCD(TTDKCCCD cccd, String soCCCu) {
		if(cccd.getHoTenKhac() == null){
			cccd.setHoTenKhac("");
		}
		String sql = "UPDATE cccd SET SO_CC = ? "
				+ ",MA_SO = ?, NGAY_CAP = ?, "
				+ "NGUOI_DUYET = ? WHERE SO_CC = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql,
							new Object[] {cccd.getSoCC(), cccd.getMaSo(), functionService.getNgayHienTai(),
									session.getAttribute("ssHoTen"), soCCCu,});
		if (i > 0) {
			System.out.println("Update 1 row");
			if(soCCCu.length() != 12 && cccd.getMaYeuCau() == 2){
				String _12So = cccd.getSoCC();
				String _9So = soCCCu;
				String updateKhaiSinhCha = "UPDATE khai_sinh SET"
						+ " SO_CC_CHA = ?"
						+ " WHERE SO_CC_CHA = ?";
				String updateKhaiSinhMe = "UPDATE khai_sinh SET"
						+ " SO_CC_ME = ?"
						+ " WHERE SO_CC_ME = ?";
				String updateKhaiSinhNYC = "UPDATE khai_sinh SET"
						+ " SO_CC_NGUOI_YEU_CAU = ?"
						+ " WHERE SO_CC_NGUOI_YEU_CAU = ?";
				String updateHoKhau = "UPDATE chi_tiet_ho_khau SET"
						+ " SO_CC = ?"
						+ " WHERE SO_CC = ?";
				String updateKetHonChong = "UPDATE hon_nhan SET"
						+ " SO_CC_CHONG = ?"
						+ " WHERE SO_CC_CHONG = ?";
				String updateKetHonVo = "UPDATE hon_nhan SET"
						+ " SO_CC_Vo = ?"
						+ " WHERE SO_CC_Vo = ?";
				changeSoCC9To12(updateKhaiSinhCha, _12So, _9So);
				changeSoCC9To12(updateKhaiSinhMe, _12So, _9So);
				changeSoCC9To12(updateKhaiSinhNYC, _12So, _9So);
				changeSoCC9To12(updateHoKhau, _12So, _9So);
				changeSoCC9To12(updateKetHonChong, _12So, _9So);
				changeSoCC9To12(updateKetHonVo, _12So, _9So);
			}
			return true;
		}
		return false;
	}
	@Override
	public Boolean changeSoCC9To12(String sql, String _12So, String _9So) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int kq = jdbcTemplate.update(
				sql,new Object[] {_12So, _9So});
		if(kq > 0){
			System.out.println("cập nhật 9 số sang 12 số");
			return true;
		}
		return false;
	}
	
	
	public List<CCCD> getCCCD(String soCC){
		String sql = "SELECT * FROM cccd a, nhom_mau b, ttdk_cccd c, khai_sinh d WHERE a.ma_so = c.ma_so and"
				+ " c.so_ks = d.so_ks and"
				+ " c.MA_NHOM_MAU = b.MA_NHOM_MAU and"
				+ " a.SO_CC = '"+soCC+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CCCD> cccd;
		cccd = jdbcTemplate.query(sql, new CCCDRowMapper());
		return cccd;
	}
	@Override
	public Boolean deleteCCCD(String soCC) {
		String sql = "DELETE FROM cccd WHERE  SO_CC = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql,
							new Object[] {soCC});
		if (i > 0) {
			System.out.println("delete 1 row");
			return true;
		}
		return false;
	}

	@Override
	public CCCD getCCCDBangMa(String soCC) {
		String sql = "SELECT * "
			+ "FROM cccd a, ttdk_cccd b, khai_sinh c "
			+ "WHERE a.ma_so = b.ma_so and b.so_ks = c.so_ks and a.SO_CC = '" + soCC + "'";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List<CCCD> dsCCCD;
			dsCCCD = jdbcTemplate.query(sql, new CCCDRowMapper());
			if(dsCCCD.isEmpty()) {
				return null;
			}
		return dsCCCD.get(0);
	}
	//Ham get CCCD cua Tin
	@Override
	public bean.Chung.CCCD getCCCD1(String soCC) {
		String sql = "SELECT "
					+ "A.SO_CC, "
					+ "c.GIOI_TINH, "
					+ "c.HO_TEN, "
					+ "c.NGAY_SINH, "
					+ "D.TEN_DT, "
					+ "c.QUOC_TICH, "
					+ "X1.TEN_XA AS TEN_TT_XA, "
					+ "H1.TEN_HUYEN AS TEN_TT_HUYEN, "
					+ "T1.TEN_TINH AS TEN_TT_TINH, "
					+ "T2.TEN_TINH AS TEN_NOI_CAP "
				+ "FROM "
					+ "cccd A, "
					+ "ttdk_cccd b, "
					+ "khai_sinh c, "
					+ "xa X1, "
					+ "huyen H1, "
					+ "tinh T1, "
					+ "tinh T2, "
					+ "dan_toc D "
				+ "WHERE A.ma_so = b.ma_so and b.so_ks = c.so_ks and "
					+ "c.que_quan = X1.MA_XA "
					+ "AND X1.MA_HUYEN = H1.MA_HUYEN "
					+ "AND H1.MA_TINH = T1.MA_TINH "
					+ "AND c.MA_DT = D.MA_DT "
					+ "AND A.NOI_CAP = T2.MA_TINH "
					+ "AND A.SO_CC = '" + soCC + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<bean.Chung.CCCD> dsCCCD;
		dsCCCD = jdbcTemplate.query(sql, new jdbc.Chung.CCCDRowMapper());
		if(dsCCCD.isEmpty()) {
			return null;
		}
		return dsCCCD.get(0);
	}
	@Override
	public CCCD layHangCuoi(String noiCap) {
		String sql = "SELECT * FROM cccd a, ttdk_cccd b, khai_sinh c WHERE a.ma_so = b.ma_so and b.so_ks = c.so_ks and "
				+ " a.NOI_CAP = '"+noiCap+"' AND  CHAR_LENGTH(a.SO_CC) > 9 ORDER BY SUBSTRING(a.SO_CC,7) DESC LIMIT 1";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CCCD> cccd;
		cccd = jdbcTemplate.query(sql, new CCCDRowMapper());
		if(cccd.isEmpty()){
			return null;
		}
		return cccd.get(0);
	}
	@Override
	// sinh mã ngẫu nhiên 6 số cho căn cước
	public String maNgauNhien(String noiCap) {
		// TODO Auto-generated method stub
		String maCC = "";
		if(layHangCuoi(noiCap) == null){
			maCC = "000000000000";
		} else {
			maCC = layHangCuoi(noiCap).getSoCC();
		}
		maCC = maCC.substring(6);
		System.out.println(maCC);
		Long num = Long.parseLong(maCC);
		num ++;
		maCC = "" + num;
		if(maCC.length() < 6) {
			int sl = 6 - maCC.length();
			for(int i = 0 ; i < sl; i++){
				maCC = "0"+maCC;
			}
		}
		return maCC;
	}
	@Autowired
	private SoHoKhauService shkService;
	@Autowired
	private CCCDService cccdService;
	@Autowired
	private KhaiSinhService ksService;
	@Autowired
	private XaService xaService;
	@Autowired
	private HuyenService huyenService;
	@Autowired
	private TinhService tinhService;
	@Autowired
	private ConfigSoCCService cscService;
	@Override
	// sinh mã ngẫu nhiên 12 số cho căn cước
	public String sinhMaCanCuoc(TTDKCCCD cccdTam) {
		String SoCC ;
		//Ho Xuan Tin bo noiCap trong KhaiSinh
		//Lay noi cap trong bang Khai Sinh
		KhaiSinh ks = ksService.getNoiCapBangSoKS(cccdTam.getSoKhaiSinh());
		//Noi cap khai sinh 
		String noiCap = ks.getNoiCap();
		if(ks.getNoiCap().length()>2){
			noiCap = xaService.getXaHuyenTinh(ks.getNoiCap()).getHuyen().getTinh().getMaTinh();
		}
		
		noiCap = "0"+noiCap;
		//sinh mã năm sinh: chiếm 2 chữ số
		String ngaySinh = cccdTam.getNgaySinh();
		String[] year = ngaySinh.split("-");
		String nam = year[2];
		nam = nam.substring(2);
		
		//sinh mã giới tính: chiếm 1 chữ số
		ConfigSoCC csc = cscService.getConfigSoCC();
		String gioiTinh = cccdTam.getGioiTinh();
		if(Integer.parseInt(year[2]) < csc.getNam()){
			if(gioiTinh.equals("Nam")){
				gioiTinh = ""+csc.getGiaTriNamTruoc();
			}else{
				gioiTinh = ""+csc.getGiaTriNuTruoc();
			}
		}else{
			if(gioiTinh.equals("Nam")){
				gioiTinh = ""+csc.getGiaTriNamSau();
			}else{
				gioiTinh = ""+csc.getGiaTriNuSau();
			}
		}
		//sinh mã ngẫu nhiên theo thứ tự tăng dần: chiếm 6 chữ số
		String maNgauNhien = cccdService.maNgauNhien(cccdTam.getNoiCap());
		//List<CCCD> dsCCCD = cccdService.getCCCD(soCC);
		SoCC = noiCap + gioiTinh + nam + maNgauNhien;
		
		
		return SoCC;
	}

	@Override
	public List<CCCD> getDSCCCD() {
		String sql = "SELECT * FROM cccd a, ttdk_cccd b, khai_sinh c WHERE a.ma_so = b.ma_so and b.so_ks = c.so_ks and a.SO_CC != '000000000000'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CCCD> cccd;
		cccd = jdbcTemplate.query(sql, new CCCDRowMapper());
		return cccd;
	}
	
	@Override
	public List<bean.Vu.CCCD> getDSNguoiThan(String soCC) {
		List<bean.Vu.CCCD> dsNguoiThan = new ArrayList<bean.Vu.CCCD>();
		bean.Vu.CCCD cccd = getCCCDBangMa(soCC);
		// Vu lay so CC CHA CC Me tu khai sinh
		SoHoKhau shk = shkService.getSoKSBangSoCC(soCC);
		String soKS = "";
		try {
			soKS = shk.getSoKS();
		} catch (NullPointerException e) {
			return null;
		}
		KhaiSinh khaiSinh = ksService.getKhaiSinhBangSoKS(soKS); 
		dsNguoiThan.add(cccd);
		if (khaiSinh != null) {
			String soCCCha = khaiSinh.getSoCCCha();
			String soCCMe = khaiSinh.getSoCCMe();
			if(!functionService.checkEmpty(soCCCha)) {
				String soCCOngNoi = "";
				String soCCBaNoi = "";
				bean.Vu.CCCD cccdCha = getCCCDBangMa(soCCCha);
				// Vu lay so CC CHA CC Me tu khai sinh
				SoHoKhau shkCha = shkService.getSoKSBangSoCC(soCCCha);
				KhaiSinh khaiSinhCha = ksService.getKhaiSinhBangSoKS(shkCha.getSoKS()); 
				//Them cccdCha vao danh sach
				dsNguoiThan.add(cccdCha);
				//Lay so cc ong ba noi
				soCCOngNoi = khaiSinhCha.getSoCCCha();
				soCCBaNoi = khaiSinhCha.getSoCCMe();
				if(!functionService.checkEmpty(soCCOngNoi)) {
					bean.Vu.CCCD cccdOngNoi = getCCCDBangMa(soCCOngNoi);
					bean.Vu.CCCD cccdBaNoi = getCCCDBangMa(soCCBaNoi);
					//Them so cc ong ba noi vao danh sach
					if(!functionService.checkEmpty(cccdOngNoi.getSoCC())) {
						dsNguoiThan.add(cccdOngNoi);
					}
					if(!functionService.checkEmpty(cccdBaNoi.getSoCC())) {
						dsNguoiThan.add(cccdBaNoi);
					}
				}
			}
			if(!functionService.checkEmpty(soCCMe)) {
				String soCCOngNgoai = "";
				String soCCBaNgoai = "";
				bean.Vu.CCCD cccdMe = getCCCDBangMa(soCCMe);
				// Vu lay so CC CHA CC Me tu khai sinh
				SoHoKhau shkMe = shkService.getSoKSBangSoCC(soCCMe);
				KhaiSinh khaiSinhMe = ksService.getKhaiSinhBangSoKS(shkMe.getSoKS()); 
				dsNguoiThan.add(cccdMe);
				//So cc ong ngoai, ba ngoai
				soCCOngNgoai = khaiSinhMe.getSoCCCha();
				soCCBaNgoai = khaiSinhMe.getSoCCMe();
				if(!functionService.checkEmpty(soCCOngNgoai)) {
					bean.Vu.CCCD cccdOngNgoai = getCCCDBangMa(soCCOngNgoai);
					bean.Vu.CCCD cccdBaNgoai = getCCCDBangMa(soCCBaNgoai);
					//Them so cc ong ba ngoai vao danh sach
					if(!functionService.checkEmpty(cccdOngNgoai.getSoCC())) {
						dsNguoiThan.add(cccdOngNgoai);
					}
					if(!functionService.checkEmpty(cccdBaNgoai.getSoCC())){
						dsNguoiThan.add(cccdBaNgoai);
					}	
				}
			}
		}
		return dsNguoiThan;
	}

	@Override
	public Boolean ktHuyetThong(String soCCA, String soCCB) {
		List<bean.Vu.CCCD> dsHoHangA = getDSNguoiThan(soCCA);
		List<bean.Vu.CCCD> dsHoHangB = getDSNguoiThan(soCCB);
		if ((dsHoHangA != null) && (dsHoHangB != null)) {
			int sizeDSA = dsHoHangA.size();
			int sizeDSB = dsHoHangB.size();
			String soCC1 = "";
			String soCC2 = "";
			for(int i = 0; i < sizeDSA; i++) {
				soCC1 = dsHoHangA.get(i).getSoCC();
				for(int j = 0; j < sizeDSB; j++) {
					soCC2 = dsHoHangB.get(j).getSoCC();
					if(soCC1.equals(soCC2)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public List<CCCD> getDSCCCDTheoTinh() {
		String sql = "SELECT *"
				+ " FROM cccd a, ttdk_cccd b, khai_sinh c"
				+ " WHERE a.ma_so = b.ma_so and b.so_ks = c.so_ks and a.SO_CC != '000000000000'"
				+ " AND a.NOI_CAP = '"+session.getAttribute("ssCoQuan")+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CCCD> cccd;
		cccd = jdbcTemplate.query(sql, new CCCDRowMapper());
		return cccd;
	}

	@Override
	public List<CCCD> timKiemCCCD(String tuKhoa) {
		String sql = "SELECT *"
				+ " FROM cccd a, ttdk_cccd b, khai_sinh c"
				+ " WHERE a.ma_so = b.ma_so and b.so_ks = c.so_ks and a.SO_CC != '000000000000'"
				+ " AND a.NOI_CAP = '"+session.getAttribute("ssCoQuan")+"'"
				+ " AND (a.SO_CC like '%"+tuKhoa+"%' OR c.HO_TEN like '%"+tuKhoa+"%')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CCCD> cccd;
		cccd = jdbcTemplate.query(sql, new CCCDRowMapper());
		return cccd;
	}

	@Override
	public List<CCCD> getDSCCCDTheoNguoiCap(String taiKhoan) {
		String sql = "SELECT *"
				+ " FROM cccd a, ttdk_cccd b, khai_sinh c"
				+ " WHERE a.ma_so = b.ma_so and b.so_ks = c.so_ks and SO_CC != '000000000000'"
				+ " AND NGUOI_DUYET = '"+taiKhoan+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CCCD> cccd;
		cccd = jdbcTemplate.query(sql, new CCCDRowMapper());
		return cccd;
	}

	@Override
	public Boolean updateCCCDSW(String set, String where) {
		String sql = "UPDATE cccd " + set + where;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(sql);
		if (i > 0) {
			System.out.println("Update 1 row");
			return true;
		}
		return false;
	}

	@Override
	public Boolean nhapCCCD(TTDKCCCD cccd) {
		cccd.setNgayDK(functionService.getNgayHienTai());
		cccd.setNgayXacNhan(functionService.getNgayHienTai());
		cccd.setNgayHen(functionService.getNgayHienTai());
		cccd.setNoiDKLamViec(session.getAttribute("ssCoQuan").toString());
		cccd.setMaYeuCau(2);
		cccd.setKetQuaXacMinh("Đơn được cán bộ nhập vào hệ thống");
		cccd.setDuyet(3);
		cccd.setNguoiKiemTra(session.getAttribute("ssHoTen").toString());
		String maDuyetTTDK = functionService.taoMaDuyetTTDKCCCD(cccd, session.getAttribute("ssMatKhau").toString());
		String sql = "INSERT INTO ttdk_cccd (MA_SO, SO_KS, MA_YEU_CAU, SO_CC_CU, HINH_THE, VAN_TAY_TRO_TRAI, VAN_TAY_TRO_PHAI, NHAN_DANG, HO_TEN_KHAC, "
				+ "TON_GIAO, MA_NHOM_MAU,"
				+ "NOI_O_HIEN_TAI, NGHE_NGHIEP, TRINH_DO,"
				+ "NOI_DK_LAM_VIEC, NGAY_DK, NGAY_HEN, NGAY_XAC_NHAN, KET_QUA_XAC_MINH, DUYET, "
				+ "NGUOI_KIEM_TRA, NGUOI_DUYET, MA_DUYET) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql1 = "INSERT INTO cccd (SO_CC, MA_SO,"
				+ "NGAY_CAP, NOI_CAP,"
				+ "LAN_CAP, "
				+ "NGUOI_DUYET) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql,
							new Object[] {  cccd.getMaSo(), 
											cccd.getSoKhaiSinh(),
											cccd.getMaYeuCau(),
											cccd.getSoCC(),
											cccd.getHinhThe(), 
											cccd.getVanTayTroTrai(),
											cccd.getVanTayTroPhai(),
											cccd.getNhanDang(), 
											cccd.getHoTenKhac(),
											cccd.getTonGiao(), 
											cccd.getNhomMau(), 
											cccd.getNoiOHienTai(), 
											cccd.getNgheNghiep(), 
											cccd.getTrinhDo(),
											cccd.getNoiDKLamViec(),
											cccd.getNgayDK(),
											cccd.getNgayHen(),
											cccd.getNgayXacNhan(),
											cccd.getKetQuaXacMinh(),
											cccd.getDuyet(),
											cccd.getNguoiKiemTra(),
											cccd.getNguoiDuyet(),
											maDuyetTTDK});
		int j = jdbcTemplate.update(
				sql1,
				new Object[] {  cccd.getSoCC(),
								cccd.getMaSo(),
								cccd.getNgayCap(),
								cccd.getNoiCap(),
								cccd.getLanCap(),
								cccd.getNguoiDuyet()});
		if (i > 0 && j > 0) {
			System.out.println("Da nhap 1 dong vao CCCD");
			return true;
		}
		return false;
	}

	

	
}
