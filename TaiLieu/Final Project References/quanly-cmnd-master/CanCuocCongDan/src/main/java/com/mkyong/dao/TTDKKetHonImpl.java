package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;





















import jdbc.Chung.TTDKKetHonRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Chung.TTDKKetHon;
import bean.Chung.TaiKhoan;

public class TTDKKetHonImpl implements TTDKKetHonDao {

	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;
	
	public TTDKKetHonImpl() {
		super();
	}

	/**
	 * Lay danh sach TTDKKetHon
	 */
	// commit
	@Override
	public List<TTDKKetHon> getDSTTDKKetHon(int trangThai, String noiDKLV, String ngayHen, int soDonGioiHan) {
		String sql = "SELECT * "
					+ "FROM ttdk_hon_nhan "
					+ "WHERE TRANG_THAI = " + trangThai + " "
					+ "AND NOI_DKLV = '" + noiDKLV + "' "
					+ "AND NGAY_HEN_LAM_VIEC = '" + ngayHen + "' "
					+ "LIMIT 0, " + soDonGioiHan;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKetHon> dsTTDKKetHon;
		dsTTDKKetHon = jdbcTemplate.query(sql, new TTDKKetHonRowMapper());
		return dsTTDKKetHon;
	}

	@Override
	public Boolean capNhatTTDKKetHon(TTDKKetHon ttdk) {
		String sql = "UPDATE "
						+ "ttdk_hon_nhan "
					+ "SET "
						+ "SO_CC_NGUOI_DK = ?, "
						+ "SO_CC_VO_HOAC_CHONG = ? "
					+ "WHERE SO_DK = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
									sql,
									new Object[] {ttdk.getSoCCNguoiDK(), ttdk.getSoCCVoHoacChong(),
												  ttdk.getSoDK()});
		if (i > 0) {
			System.out.println("Update HON_NHAN thanh cong.");
			return true;
		}
		return false;
	}

	@Override
	public TTDKKetHon getTTTDKKetHon(String soDK) {
		String sql = "SELECT * "
					+ "FROM "
						+ "ttdk_hon_nhan "
					+ "WHERE SO_DK = '" + soDK + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKetHon> dsTTDKKetHon;
		dsTTDKKetHon = jdbcTemplate.query(sql, new TTDKKetHonRowMapper());
		if (dsTTDKKetHon.isEmpty()) {
			return null;
		}
		return dsTTDKKetHon.get(0);
	}

	@Override
	public List<TTDKKetHon> getDSTTTDKKetHon(String soDK) {
		String sql = "SELECT * "
				+ "FROM "
					+ "ttdk_hon_nhan "
				+ "WHERE SO_DK = '" + soDK + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKetHon> dsTTDKKetHon;
		dsTTDKKetHon = jdbcTemplate.query(sql, new TTDKKetHonRowMapper());
		return dsTTDKKetHon;
	}

	@Override
	public List<TTDKKetHon> getDSTTDKKetHonBangSoCC(String soCC) {
		String sql = "SELECT * "
				+ "FROM "
					+ "ttdk_hon_nhan "
				+ "WHERE "
					+ "SO_CC_NGUOI_DK = '" + soCC + "' "
					+ "OR SO_CC_VO_HOAC_CHONG = '" + soCC + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKetHon> dsTTDKKetHon;
		dsTTDKKetHon = jdbcTemplate.query(sql, new TTDKKetHonRowMapper());
		if (dsTTDKKetHon.isEmpty()) {
			return null;
		}
		return dsTTDKKetHon;
	}

	@Override
	public List<TTDKKetHon> getDSDKKHTheoNgayDK(String ngay) {
		String sql = "SELECT * "
					+ "FROM "
						+ "ttdk_hon_nhan "
					+ "WHERE "
						+ "NGAY_DANG_KY LIKE '%" + ngay + "%'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKetHon> dsTTDKKetHon;
		dsTTDKKetHon = jdbcTemplate.query(sql, new TTDKKetHonRowMapper());
		return dsTTDKKetHon;
	}

	@Override
	public List<TTDKKetHon> getDSDKKHTheoNgayHen(String ngay) {
		String sql = "SELECT * "
				+ "FROM "
					+ "ttdk_hon_nhan "
				+ "WHERE "
					+ "NGAY_HEN_LAM_VIEC LIKE '%" + ngay + "%'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKetHon> dsTTDKKetHon;
		dsTTDKKetHon = jdbcTemplate.query(sql, new TTDKKetHonRowMapper());
		return dsTTDKKetHon;
	}
	@Override
	public List<TTDKKetHon> getDSDKKHTheoNgayNhanGiay(String ngay) {
		String sql = "SELECT * "
				+ "FROM "
					+ "ttdk_hon_nhan "
				+ "WHERE "
					+ "NGAY_NHAN_GIAY LIKE '%" + ngay + "%'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKetHon> dsTTDKKetHon;
		dsTTDKKetHon = jdbcTemplate.query(sql, new TTDKKetHonRowMapper());
		return dsTTDKKetHon;
	}

	@Override
	public Boolean themTTDKKetHon(TTDKKetHon ttdk) {
		String sql = "INSERT INTO ttdk_hon_nhan ("
							+ "SO_CC_NGUOI_DK, "
							+ "SO_CC_VO_HOAC_CHONG, "
							+ "NGAY_DANG_KY, "
							+ "NGAY_HEN_LAM_VIEC, "
							+ "NOI_DKLV, "
							+ "MA_XAC_NHAN) "
					+ "VALUES (?, ?, ?, ?, ?, ?);";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(sql, 
									new Object[] {
												ttdk.getSoCCNguoiDK(), ttdk.getSoCCVoHoacChong(), 
												ttdk.getNgayDangKy(), ttdk.getNgayHenLamViec(), 
												ttdk.getNoiDKLV(), ttdk.getMaXacNhan()
									});
		if (i > 0) {
			System.out.println("Them ttdk ket hon thanh cong");
			return true;
		}
		return false;
	}

	@Override
	public Boolean xacNhanDK(String soCCA, String soCCB, String maXacNhan) {
		String sql = "SELECT * "
					+ "FROM ttdk_hon_nhan "
					+ "WHERE SO_CC_NGUOI_DK = '" + soCCA + "' "
							+ "AND  SO_CC_VO_HOAC_CHONG = '" + soCCB + "' "
							+ "AND MA_XAC_NHAN = '" + maXacNhan + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKetHon> dsTTDKKetHon = jdbcTemplate.query(sql, new TTDKKetHonRowMapper());
		if (dsTTDKKetHon.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean xacNhanDK(String soCC, String maXacNhan) {
		String sql = "SELECT * "
					+ "FROM ttdk_hon_nhan "
					+ "WHERE (SO_CC_NGUOI_DK = '" + soCC + "' "
							+ "OR SO_CC_VO_HOAC_CHONG = '" + soCC + "') "
							+ "AND MA_XAC_NHAN = '" + maXacNhan + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKetHon> dsTTDKKetHon = jdbcTemplate.query(sql, new TTDKKetHonRowMapper());
		if (dsTTDKKetHon.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean capNhatTrangThai(TTDKKetHon ttdk, int trangThai) {
		String sql = "UPDATE "
				+ "ttdk_hon_nhan "
			+ "SET "
				+ "TRANG_THAI = ? "
			+ "WHERE "
				+  "SO_CC_NGUOI_DK = ? "
				+ "AND SO_CC_VO_HOAC_CHONG = ? "
				+ "AND MA_XAC_NHAN = ?;";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
									sql,
									new Object[] {trangThai, ttdk.getSoCCNguoiDK(), ttdk.getSoCCVoHoacChong(), ttdk.getMaXacNhan()});
		if (i > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean capNhatTrangThai(String soDK, int trangThai, String lyDo) {
		String sql = "UPDATE "
				+ "ttdk_hon_nhan "
			+ "SET "
				+ "TRANG_THAI = ? , GHI_CHU = ? "
			+ "WHERE "
				+  "SO_DK = ?;";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
									sql,
									new Object[] {trangThai, lyDo, soDK});
		if (i > 0) {
			System.out.println("Update HON_NHAN thanh cong.");
			return true;
		}
		return false;
	}

	@Override
	public TTDKKetHon getDKKetHonBangSoCCNguoiDK(String soCC) {
		String sql = "SELECT * "
				+ "FROM "
					+ "ttdk_hon_nhan "
				+ "WHERE "
					+ "SO_CC_NGUOI_DK = '" + soCC + "' ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKetHon> dsTTDKKetHon;
		dsTTDKKetHon = jdbcTemplate.query(sql, new TTDKKetHonRowMapper());
		if (dsTTDKKetHon.isEmpty()) {
			return null;
		}
		return dsTTDKKetHon.get(dsTTDKKetHon.size() - 1);
	}

	@Override
	public List<TTDKKetHon> getDKKetHonBangSoCCVoHoacChong(String soCC) {
		String sql = "SELECT * "
				+ "FROM "
					+ "ttdk_hon_nhan "
				+ "WHERE "
					+ "SO_CC_VO_HOAC_CHONG = '" + soCC + "' ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKetHon> dsTTDKKetHon;
		dsTTDKKetHon = jdbcTemplate.query(sql, new TTDKKetHonRowMapper());
		if (dsTTDKKetHon.isEmpty()) {
			return null;
		}
		return dsTTDKKetHon;
	}

	@Override
	public Boolean ktXacNhan(String soCC, int trangThai) {
		String sql = "SELECT * "
				+ "FROM "
					+ "ttdk_hon_nhan "
				+ "WHERE "
					+ "SO_CC_VO_HOAC_CHONG = '" + soCC + "' "
					+ "AND TRANG_THAI = " + trangThai;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKetHon> dsTTDKKetHon;
		dsTTDKKetHon = jdbcTemplate.query(sql, new TTDKKetHonRowMapper());
		if (dsTTDKKetHon.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public List<TTDKKetHon> getDSTTDKKetHonDaDuyet(String ngayHen, TaiKhoan tk,
			int soLuongTrongNgay) {
		String sql = "SELECT * "
					+ "FROM ttdk_hon_nhan a, duyet_dkkh b "
					+ "WHERE "
						+ "a.SO_DK = b.SO_DK "
						+ "AND NOI_DKLV = '" + tk.getCoQuan() + "' "
						+ "AND b.NGUOI_DUYET = '" + tk.getUsername() + "' "
					+ "LIMIT 0, " + soLuongTrongNgay;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKetHon> dsTTDKKetHon;
		dsTTDKKetHon = jdbcTemplate.query(sql, new TTDKKetHonRowMapper());
		return dsTTDKKetHon;
	}

	@Override
	public List<TTDKKetHon> getDSTTDKKetHonBiTuChoi(TaiKhoan tk, int soLuongTrongNgay) {
		String sql = "SELECT * "
				+ "FROM ttdk_hon_nhan "
				+ "WHERE "
					+ "NOI_DKLV = '" + tk.getCoQuan() + "' "
					+ "AND (TRANG_THAI = 3 OR TRANG_THAI = 4) "
				+ "LIMIT 0, " + soLuongTrongNgay;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKetHon> dsTTDKKetHon;
		dsTTDKKetHon = jdbcTemplate.query(sql, new TTDKKetHonRowMapper());
		return dsTTDKKetHon;
	}

	

}
