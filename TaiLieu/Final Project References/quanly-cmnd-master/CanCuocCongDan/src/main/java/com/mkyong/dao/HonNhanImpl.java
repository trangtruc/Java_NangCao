package com.mkyong.dao;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.HonNhanRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import Constant.Const;
import bean.Chung.HonNhan;
import bean.Chung.TaiKhoan;

public class HonNhanImpl implements HonNhanDao {
	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;
	
	public HonNhanImpl() {
		super();
	}
	
	@Override
	public Boolean ktGioiTinh(String gioiTinhA, String gioiTinhB) {
		return (!gioiTinhA.equals(gioiTinhB));
	}

	@Override
	public Boolean ktTuoiKH(String ngaySinh, int tuoiQuyDinh) {
		//Ngay hien tai
		Calendar ngayHienTai = Calendar.getInstance();
		//Lay ngay sinh
		String[] ngayThangNam = ngaySinh.split("-");
		String ngay = ngayThangNam[0];
		String thang = ngayThangNam[1];
		String nam = ngayThangNam[2];
		Calendar ngaySinhMoi = GregorianCalendar.getInstance();
		ngaySinhMoi.set(Integer.valueOf(nam), Integer.valueOf(thang), Integer.valueOf(ngay));
		int tuoi = ngayHienTai.get(Calendar.YEAR) - ngaySinhMoi.get(Calendar.YEAR);
		if(tuoi > tuoiQuyDinh) {
			return true;	
		} else if (tuoi == tuoiQuyDinh) {
			if(ngaySinhMoi.get(Calendar.MONTH) > 
					(ngayHienTai.get(Calendar.MONTH) + 1)) {
				return true;
			} else if (ngaySinhMoi.get(Calendar.MONTH) == 
						(ngayHienTai.get(Calendar.MONTH) + 1)) {
				if(ngaySinhMoi.get(Calendar.DAY_OF_MONTH) > 
					ngayHienTai.get(Calendar.DAY_OF_MONTH)) {
					return true;
				} else if (ngaySinhMoi.get(Calendar.DAY_OF_MONTH) == 
					ngayHienTai.get(Calendar.DAY_OF_MONTH)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Boolean ktDaKetHon(String soCC, String gioiTinh) {
		String sql = "";
		if ("Nam".equals(gioiTinh)) {
			sql = "SELECT * "
				+ "FROM hon_nhan a, tinh_trang_hn b "
				+ "WHERE "
					+ "a.TINH_TRANG_HN = b.MA_TINH_TRANG "
					+ "AND a.TINH_TRANG_HN = '1' "
					+ "AND a.SO_CC_CHONG = '" + soCC + "'";
		} else {
			sql = "SELECT * "
				+ "FROM hon_nhan a, tinh_trang_hn b "
				+ "WHERE "
					+ "a.TINH_TRANG_HN = b.MA_TINH_TRANG "
					+ "AND a.TINH_TRANG_HN = '1' "
					+ "AND SO_CC_VO = '" + soCC + "'";
		}
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<HonNhan> dsHonNhan;
		dsHonNhan = jdbcTemplate.query(sql, new HonNhanRowMapper());
		if (dsHonNhan.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public HonNhan getHonNhanBangSoCC(String soCC) {
		String sql = "SELECT * "
				+ "FROM "
					+ "hon_nhan A, "
					+ "tinh_trang_hn B "
				+ "WHERE "
				+ "A.TINH_TRANG_HN = 1 "
				+ "AND A.TINH_TRANG_HN = B.MA_TINH_TRANG "
				+ "AND (A.SO_CC_CHONG = '" + soCC + "' "
						+ "OR A.SO_CC_VO = '" + soCC + "')";
		List<HonNhan> honNhan;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		honNhan = jdbcTemplate.query(sql, new HonNhanRowMapper());
		if(honNhan.isEmpty()){
			return null;
		}
		return honNhan.get(0);
	}

	@Override
	public List<HonNhan> getDSHonNhan(TaiKhoan tk, String ngayDuyet, int soDonTrongNgay) {
		String sql = "SELECT * "
					+ "FROM "
						+ "hon_nhan h, "
						+ "duyet_dkkh  d, "
						+ "tai_khoan t, "
						+ "quyen_tai_khoan qt, "
						+ "quyen q, "
						+ "tinh_trang_hn tq "
					+ "WHERE "
						+ "h.TINH_TRANG_HN = 1 "
						+ "AND h.TINH_TRANG_HN = tq.MA_TINH_TRANG "
						+ "AND d.NGAY_DUOC_DUYET = '" + ngayDuyet + "' "
						+ "AND d.NGUOI_DUYET = t.USER_NAME "
						+ "AND t.CO_QUAN = '" + tk.getCoQuan() + "' "
						+ "AND t.USER_NAME = qt.USER_NAME "
						+ "AND qt.MA_QUYEN = q.MA_QUYEN "
						+ "AND q.TEN_QUYEN = '" + Const.DUYET_KET_HON_2 + "' "
					+ "LIMIT 0, " + soDonTrongNgay;
		List<HonNhan> dsHonNhan;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsHonNhan = jdbcTemplate.query(sql, new HonNhanRowMapper());
		return dsHonNhan;
	}

	@Override
	public Boolean themHonNhan(HonNhan hn) {
		String sql = "INSERT INTO hon_nhan ("
				+ "SO_DK, "
				+ "SO_CC_CHONG, "
				+ "SO_CC_VO, "
				+ "TINH_TRANG_HN, "
				+ "GHI_CHU "
				+ ")"
		+ "VALUES (?, ?, ?, ?, ?);";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(sql, 
								new Object[] {
											hn.getSoDK(), 
											hn.getSoCCChong(), hn.getSoCCVo(), 
											hn.getTinhTrangHN().getMaTinhTrang(), 
											hn.getGhiChu()
										
								});
		if (i > 0) {
			System.out.println("Them ket hon thanh cong");
			return true;
		}
		return false;
	}
	@Override
	public HonNhan layHangCuoi() {
		String sql = "SELECT * "
					+ "FROM "
						+ "hon_nhan A, "
						+ "tinh_trang_hn B "
					+ "WHERE "
						+ "A.TINH_TRANG_HN = 1 "
						+ "AND A.TINH_TRANG_HN = B.MA_TINH_TRANG "
					+ "ORDER BY SO_DK DESC "
					+ "LIMIT 1";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<HonNhan> dsHonNhan;
		dsHonNhan = jdbcTemplate.query(sql, new HonNhanRowMapper());
		if (dsHonNhan.isEmpty()) {
			return null;
		}
		return dsHonNhan.get(0);
	}
	@Override
	public int taoSoDK() {
		HonNhan hn = layHangCuoi();
		int soDK =  0;
		if (hn == null) {
			soDK = 1;
		} else {
			soDK = Integer.valueOf(hn.getSoDK()) + 1;
		}
		return soDK;
	}

	@Override
	public List<HonNhan> getHonNhan(String inputTimKiem) {
		String sql = "SELECT * "
					+ "FROM "
						+ "hon_nhan A, "
						+ "tinh_trang_hn B "
					+ "WHERE "
					+ "A.TINH_TRANG_HN = 1 "
					+ "AND A.TINH_TRANG_HN = B.MA_TINH_TRANG "
					+ "AND (A.SO_CC_CHONG = '" + inputTimKiem + "' "
							+ "OR A.SO_DK = '" + inputTimKiem + "' "
							+ "OR A.SO_CC_VO = '" + inputTimKiem + "')";
		List<HonNhan> dsHonNhan;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsHonNhan = jdbcTemplate.query(sql, new HonNhanRowMapper());
		return dsHonNhan;
	}

	@Override
	public HonNhan getHonNhanBangSoDK(int soDK) {
		String sql = "SELECT * "
				+ "FROM "
					+ "hon_nhan A, "
					+ "tinh_trang_hn B "
				+ "WHERE "
				+ "A.TINH_TRANG_HN = 1 "
				+ "AND A.TINH_TRANG_HN = B.MA_TINH_TRANG "
				+ "AND A.SO_DK = " + soDK;
		List<HonNhan> dsHonNhan;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsHonNhan = jdbcTemplate.query(sql, new HonNhanRowMapper());
		return dsHonNhan.get(0);
	}

	@Override
	public Boolean xoaHonNhan(String soCC) {
		String sql = "DELETE FROM hon_nhan "
				+ "WHERE (so_cc_chong =  '" + soCC + "' "
						+ "OR so_cc_vo = '" + soCC + "') "
						+"AND tinh_trang_hn = '1'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(sql);
		if (i > 0) {
			return true;
			
		}
		return false;
	}

}
