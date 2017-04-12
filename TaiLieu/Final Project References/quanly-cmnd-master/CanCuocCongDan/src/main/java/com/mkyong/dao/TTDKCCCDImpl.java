package com.mkyong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import jdbc.Vu.TTDKCCCDRowMapper;
import bean.Vu.TTDKCCCD;

public class TTDKCCCDImpl implements TTDKCCCDDao {
	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;
	@Autowired
	private HttpSession session;
	public TTDKCCCDImpl() {
		super();
	}

	@Override
	public Boolean insertTTDKCCCD(TTDKCCCD cccd) {
		String sql = "INSERT INTO ttdk_cccd (MA_SO, SO_KS, MA_YEU_CAU, HINH_THE, VAN_TAY_TRO_TRAI, VAN_TAY_TRO_PHAI, NHAN_DANG, HO_TEN_KHAC, SO_CC_CU, "
						+ "NGHE_NGHIEP, TON_GIAO, MA_NHOM_MAU,"
						+ "NOI_O_HIEN_TAI, TRINH_DO, "
						+ "LAN_CAP, NOI_DK_LAM_VIEC, NGAY_DK, NGAY_HEN) "
						+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, "
						+ "?, ?, ?, ?, ?, ?, ?, ?, ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql,
							new Object[] {  cccd.getMaSo(), cccd.getSoKhaiSinh(), cccd.getMaYeuCau(),
											cccd.getHinhAnh(), cccd.getVanTayTroTrai(), cccd.getVanTayTroPhai(), cccd.getNhanDang() ,
											cccd.getHoTenKhac(), 
											cccd.getSoCC(),
											cccd.getNgheNghiep(),
											cccd.getTonGiao(), cccd.getNhomMau(), 
											cccd.getNoiOHienTai(), cccd.getTrinhDo(),
											cccd.getLanCap(),
											cccd.getNoiDKLamViec(),
											cccd.getNgayDK(), cccd.getNgayHen()});
		if (i > 0) {
			System.out.println("insert 1 row into ttdk_cccd");
			return true;
		}
		return false;
	}
	@Override
	public Boolean updateTTDKCCCD(String set, String where) {
		String sql = "UPDATE `ttdk_cccd` " + set + where;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(sql);
		if (i > 0) {
			System.out.println("update 1 row from ttdk_cccd");
			return true;
		}
		return false;
	}
	@Override
	public Boolean deleteTTDKCCCD(int maSo) {
		String sql = "DELETE FROM ttdk_cccd WHERE MA_SO = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql,
							new Object[] {maSo});
		if (i > 0) {
			System.out.println("delete from ttdk_cccd");
			return true;
		}
		return false;
	}
	public List<TTDKCCCD> getDSTTDKCCCD(){
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b"
				+ " WHERE a.so_ks = b.so_ks"
				+ " ORDER BY a.MA_SO DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		return dsTTDKCCCD;
	}
	public List<TTDKCCCD> getDSNgayHen(String noiDangKy, String date){
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b WHERE a.so_ks = b.so_ks and a.NOI_DK_LAM_VIEC = '"+noiDangKy+"' and NGAY_HEN = '"+date+"'"
				+ " ORDER BY a.MA_SO DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		return dsTTDKCCCD;
	}
	public List<TTDKCCCD> getDSCanBoXacNhan(){
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b WHERE a.so_ks = b.so_ks and a.duyet < 2"
				+ " AND a.NOI_DK_LAM_VIEC = '"+session.getAttribute("ssCoQuan")+"'"
				+ " ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		return dsTTDKCCCD;
	}
	public List<TTDKCCCD> getDSCanBoDaXacNhan(){
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b WHERE a.so_ks = b.so_ks and a.duyet >= 2"
				+ " AND a.NOI_DK_LAM_VIEC = '"+session.getAttribute("ssCoQuan")+"'"
				+ " ORDER BY a.MA_SO DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		return dsTTDKCCCD;
	}
	public List<TTDKCCCD> getDSGiamDocDuyet(){
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b "
				+ " WHERE a.so_ks = b.so_ks and"
				+ " a.MA_SO IN (SELECT DISTINCT ma_so"
				+ " FROM ttdk_cccd a, huyen b, khai_sinh c "
				+ " WHERE a.so_ks = c.so_ks and "
				+ " ( DUYET = 2 OR DUYET = 10 )"
				+ " AND b.MA_TINH = '"+session.getAttribute("ssCoQuan")+"'"
				+ " AND (noi_dk_lam_viec = '"+session.getAttribute("ssCoQuan")+"'"
						+ " or noi_dk_lam_viec = b.MA_HUYEN))"
				+ " ORDER BY MA_SO DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		return dsTTDKCCCD;
	}
	public TTDKCCCD getTTDKCCCDBangMa(String maSo){
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b WHERE a.so_ks = b.so_ks and a.MA_SO = "+maSo;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		if(dsTTDKCCCD.isEmpty()){
			return null;
		}
		return dsTTDKCCCD.get(0);
	}

	@Override
	public List<bean.Vu.TTDKCCCD> timkiemTTDKCCCD(String tuKhoa) {
		String sql = "SELECT * "
				+ "FROM ttdk_cccd a, khai_sinh b "
				+ "WHERE a.so_ks = b.so_ks and DUYET < 2 "
				+ "AND (a.MA_SO like '%"+tuKhoa+"%' OR b.HO_TEN like '%"+tuKhoa+"%')"
				+ "AND a.NOI_DK_LAM_VIEC = '"+session.getAttribute("ssCoQuan")+"'"
				+ " ORDER BY a.DUYET ASC";
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				List<bean.Vu.TTDKCCCD> dsTam;
				dsTam = jdbcTemplate.query(sql, new jdbc.Vu.TTDKCCCDRowMapper());
				if(dsTam.isEmpty()) {
					return null;
				}
				return dsTam;
	}

	@Override
	public Boolean capNhatTTDKCCCD(bean.Vu.TTDKCCCD cccd) {
		String sql = "UPDATE  ttdk_cccd "
				+ "SET  "
						+ "SO_KS = ?, MA_YEU_CAU = ?, HINH_THE = ?, "
						+ "VAN_TAY_TRO_TRAI = ?, VAN_TAY_TRO_PHAI = ?, NHAN_DANG = ?,"
						+ "HO_TEN_KHAC = ?, SO_CC_CU = ?, "
						+ "NGHE_NGHIEP = ?, TON_GIAO = ?, MA_NHOM_MAU = ?, "
						+ "NOI_O_HIEN_TAI = ?, TRINH_DO = ?, "
						+ "LAN_CAP = ?, CHUYEN_PHAT = ?, KET_QUA_XAC_MINH = ?, NOI_DK_LAM_VIEC = ?, NGAY_DK = ?,"
						+ " NGAY_HEN = ? "
						+ "WHERE  MA_SO = " + cccd.getMaSo();
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			int kt = jdbcTemplate.update(
			sql, 
			new Object[] { cccd.getSoKhaiSinh(),
					cccd.getMaYeuCau(),
					cccd.getHinhThe(),
					cccd.getVanTayTroTrai(),
					cccd.getVanTayTroPhai(),
					cccd.getNhanDang(),
					cccd.getHoTenKhac(),
					cccd.getSoCC(),
					cccd.getNgheNghiep(),
					cccd.getTonGiao(),
					cccd.getNhomMau(), 
					cccd.getNoiOHienTai(),
					cccd.getTrinhDo(),
					cccd.getLanCap(),
					cccd.getChuyenPhat(),
					cccd.getKetQuaXacMinh(),
					cccd.getNoiDKLamViec(),
					cccd.getNgayDK(),
					cccd.getNgayHen()});
		if(kt == 1) {
		System.out.println("Da update mot row");
		return true;
		}
		return false;
	}

	@Override
	public int getMaxMaSo() {
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b WHERE a.so_ks = b.so_ks ORDER BY a.MA_SO DESC LIMIT 1";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		if(dsTTDKCCCD.isEmpty()){
			return 0;
		}
		return dsTTDKCCCD.get(0).getMaSo();
	}

	@Override
	public List<TTDKCCCD> getThongTinDangKy() {
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b WHERE a.so_ks = b.so_ks and duyet < 2 "
				+ " ORDER BY a.MA_SO DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		return dsTTDKCCCD;
	}

	@Override
	public List<TTDKCCCD> getDSQuaHan() {
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b WHERE a.so_ks = b.so_ks and duyet < 2";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		return dsTTDKCCCD;
	}

	@Override
	public List<TTDKCCCD> getDSGiamDocDaDuyet() {
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b "
				+ " WHERE a.so_ks = b.so_ks and "
				+ " a.MA_SO IN (SELECT DISTINCT a.ma_so"
				+ " FROM ttdk_cccd a, huyen b, khai_sinh c "
				+ " WHERE a.so_ks = c.so_ks and"
				+ " a.DUYET = 3"
				+ " AND b.MA_TINH = '"+session.getAttribute("ssCoQuan")+"'"
				+ " AND (a.noi_dk_lam_viec = '"+session.getAttribute("ssCoQuan")+"'"
						+ " or a.noi_dk_lam_viec = b.MA_HUYEN))"
				+ " AND a.NGUOI_DUYET = '"+session.getAttribute("ssHoTen")+"'"
				+ " ORDER BY a.MA_SO DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		return dsTTDKCCCD;
	}

	@Override
	public List<TTDKCCCD> getDSGiamDocTuChoi() {
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b "
				+ " WHERE a.so_ks = b.so_ks and"
				+ " a.MA_SO IN (SELECT DISTINCT a.ma_so"
				+ " FROM ttdk_cccd a, huyen b, khai_sinh c "
				+ " WHERE a.so_ks = c.so_ks and "
				+ " a.DUYET = 4"
				+ " AND b.MA_TINH = '"+session.getAttribute("ssCoQuan")+"'"
				+ " AND (a.noi_dk_lam_viec = '"+session.getAttribute("ssCoQuan")+"'"
						+ " or a.noi_dk_lam_viec = b.MA_HUYEN))"
				+ " AND a.NGUOI_DUYET = '"+session.getAttribute("ssHoTen")+"'"
				+ " ORDER BY a.MA_SO DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		return dsTTDKCCCD;
	}

	@Override
	public List<TTDKCCCD> timKiemTTDKChuaDuyet(String key) {
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b "
				+ " WHERE a.so_ks = b.so_ks and"
				+ " a.MA_SO IN (SELECT DISTINCT a.ma_so"
				+ " FROM ttdk_cccd a, huyen b, khai_sinh c "
				+ " WHERE a.so_ks = c.so_ks and"
				+ " ( a.DUYET = 2 OR a.DUYET = 10 )"
				+ " AND (c.HO_TEN like '%"+key+"%' OR a.MA_SO like '%"+key+"%')"
				+ " AND b.MA_TINH = '"+session.getAttribute("ssCoQuan")+"'"
				+ " AND (a.noi_dk_lam_viec = '"+session.getAttribute("ssCoQuan")+"'"
						+ " or a.noi_dk_lam_viec = b.MA_HUYEN))"
				+ " ORDER BY a.MA_SO DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<bean.Vu.TTDKCCCD> dsTam;
		dsTam = jdbcTemplate.query(sql, new jdbc.Vu.TTDKCCCDRowMapper());
		if(dsTam.isEmpty()) {
			return null;
		}
		return dsTam;
	}

	@Override
	public List<TTDKCCCD> timKiemTTDKDaDuyet(String key) {
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b "
				+ " WHERE a.so_ks = b.so_ks and"
				+ " a.MA_SO IN (SELECT DISTINCT a.ma_so"
				+ " FROM ttdk_cccd a, huyen b, khai_sinh c "
				+ " WHERE a.so_ks = c.so_ks and"
				+ " a.DUYET = 3"
				+ " AND (c.HO_TEN like '%"+key+"%' OR a.MA_SO like '%"+key+"%')"
				+ " AND b.MA_TINH = '"+session.getAttribute("ssCoQuan")+"'"
				+ " AND (a.noi_dk_lam_viec = '"+session.getAttribute("ssCoQuan")+"'"
						+ " or a.noi_dk_lam_viec = b.MA_HUYEN))"
				+ " AND a.NGUOI_DUYET = '"+session.getAttribute("ssHoTen")+"'"
				+ " ORDER BY a.MA_SO DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<bean.Vu.TTDKCCCD> dsTam;
		dsTam = jdbcTemplate.query(sql, new jdbc.Vu.TTDKCCCDRowMapper());
		if(dsTam.isEmpty()) {
			return null;
		}
		return dsTam;
	}

	@Override
	public List<TTDKCCCD> timKiemTTDKBiTuChoi(String key) {
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b "
				+ " WHERE a.so_ks = b.so_ks and"
				+ " a.MA_SO IN (SELECT DISTINCT a.ma_so"
				+ " FROM ttdk_cccd a, huyen b, khai_sinh c "
				+ " WHERE a.so_ks = c.so_ks and"
				+ " a.DUYET = 4"
				+ " AND (c.HO_TEN like '%"+key+"%' OR a.MA_SO like '%"+key+"%')"
				+ " AND b.MA_TINH = '"+session.getAttribute("ssCoQuan")+"'"
				+ " AND (a.noi_dk_lam_viec = '"+session.getAttribute("ssCoQuan")+"'"
						+ " or a.noi_dk_lam_viec = b.MA_HUYEN))"
				+ " AND a.NGUOI_DUYET = '"+session.getAttribute("ssHoTen")+"'"
				+ " ORDER BY a.MA_SO DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<bean.Vu.TTDKCCCD> dsTam;
		dsTam = jdbcTemplate.query(sql, new jdbc.Vu.TTDKCCCDRowMapper());
		if(dsTam.isEmpty()) {
			return null;
		}
		return dsTam;
	}

	@Override
	public List<TTDKCCCD> thongKeDonDangKy(String thangNam) {
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b "
				+ " WHERE a.so_ks = b.so_ks and "
				+ " a.MA_SO IN (SELECT DISTINCT a.ma_so"
				+ " FROM ttdk_cccd a, huyen b, khai_sinh c "
				+ " WHERE a.so_ks = c.so_ks and"
				+ " b.MA_TINH = '"+session.getAttribute("ssCoQuan")+"'"
				+ " AND (a.noi_dk_lam_viec = '"+session.getAttribute("ssCoQuan")+"'"
						+ " or a.noi_dk_lam_viec = b.MA_HUYEN) and a.ngay_hen like '%"+thangNam+"')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		return dsTTDKCCCD;
	}

	@Override
	public List<TTDKCCCD> thongKeDonDangKyDV(String dv, String thangNam) {
		String sql = "SELECT * FROM ttdk_cccd a, khai_sinh b "
				+ " WHERE a.so_ks = b.so_ks and"
				+ " a.NOI_DK_LAM_VIEC = '"+dv+"'"
				+ " AND a.MA_SO IN (SELECT DISTINCT a.ma_so"
				+ " FROM ttdk_cccd a, huyen b "
				+ " WHERE"
				+ " b.MA_TINH = '"+session.getAttribute("ssCoQuan")+"'"
				+ " AND (noi_dk_lam_viec = '"+session.getAttribute("ssCoQuan")+"'"
						+ " or noi_dk_lam_viec = b.MA_HUYEN) and ngay_hen like '%"+thangNam+"')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		return dsTTDKCCCD;
	}

	@Override
	public List<TTDKCCCD> timkiemTTDKCCCDDaXacNhan(String tuKhoa) {
		String sql = "SELECT * "
				+ "FROM ttdk_cccd a, khai_sinh b "
				+ "WHERE a.so_ks = b.so_ks and DUYET >= 2 "
				+ "AND (a.MA_SO like '%"+tuKhoa+"%' OR b.HO_TEN like '%"+tuKhoa+"%')"
				+ "AND a.NOI_DK_LAM_VIEC = '"+session.getAttribute("ssCoQuan")+"'"
				+ " ORDER BY a.MA_SO DESC";
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				List<bean.Vu.TTDKCCCD> dsTam;
				dsTam = jdbcTemplate.query(sql, new jdbc.Vu.TTDKCCCDRowMapper());
				if(dsTam.isEmpty()) {
					return null;
				}
				return dsTam;
	}

	@Override
	public List<TTDKCCCD> getDSCanBoXacNhanTheoNgay(String ngay) {
		String sql = "SELECT * "
				+ "FROM ttdk_cccd a, khai_sinh b "
				+ "WHERE a.so_ks = b.so_ks and"
				+ " a.duyet < 2 "
				+ " AND a.NGAY_HEN = '"+ngay+"'"
				+ " AND a.NOI_DK_LAM_VIEC = '"+session.getAttribute("ssCoQuan")+"'"
				+ " ORDER BY a.DUYET ASC";
		String sql2 = "SELECT * "
				+ "FROM ttdk_cccd a, khai_sinh b "
				+ "WHERE a.so_ks = b.so_ks and"
				+ " a.duyet < 2 "
				+ " AND a.NGAY_HEN != '"+ngay+"'"
				+ " AND a.NOI_DK_LAM_VIEC = '"+session.getAttribute("ssCoQuan")+"'"
				+ " ORDER BY a.DUYET ASC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		List<TTDKCCCD> ds2;
		ds2 = jdbcTemplate.query(sql2, new TTDKCCCDRowMapper());
		for(int i = 0; i < ds2.size(); i++){
			dsTTDKCCCD.add(ds2.get(i));
		}
		return dsTTDKCCCD;
	}

	@Override
	public List<TTDKCCCD> getDSCanBoDaXacNhanTheoNgay(String ngay) {
		String sql = "SELECT * "
				+ "FROM ttdk_cccd a, khai_sinh b"
				+ "WHERE a.so_ks = b.so_ks and"
				+ " a.duyet >= 2 "
				+ "AND a.NGAY_XAC_NHAN = '"+ngay+"'"
				+ " AND a.NOI_DK_LAM_VIEC = '"+session.getAttribute("ssCoQuan")+"'"
				+ " ORDER BY a.MA_SO DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		return dsTTDKCCCD;
	}

	@Override
	public Boolean xemTTDKCCCD(int maSo, String taiKhoan) {
		String sql = "SELECT * FROM xem_ttdk_cccd a, ttdk_cccd b, khai_sinh c WHERE"
				+ " a.MA_SO = " + maSo
				+ " AND a.NGUOI_XEM = '" +taiKhoan+"'"
				+ " AND a.MA_SO = b.MA_SO"
				+ " and b.so_ks = c.so_ks";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKCCCD> dsTTDKCCCD;
		dsTTDKCCCD = jdbcTemplate.query(sql, new TTDKCCCDRowMapper());
		if (!dsTTDKCCCD.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean daXemTTDKCCCD(int maSo, String taiKhoan) {
		String sql = "INSERT INTO xem_ttdk_cccd(MA_SO, NGUOI_XEM) VALUES(?, ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql,
							new Object[] {maSo, taiKhoan});
		if (i > 0) {
			System.out.println("insert  xem_ttdk_cccd");
			return true;
		}
		return false;
	}
}
