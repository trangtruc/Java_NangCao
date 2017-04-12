package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.TaiKhoanRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Chung.TaiKhoan;
import bean.Vu.TTDKCCCD;

public class TaiKhoanImpl implements TaiKhoanDao {
	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;

	public TaiKhoanImpl() {
		super();
	}
	@Override
	public Boolean addTaiKhoan(TTDKCCCD cccdTam, String password , String hoTen) {
		String sql = "INSERT INTO tai_khoan (USER_NAME, PASSWORD) "
				+ "VALUES (?, ?)";
		String sql_quyen = "INSERT INTO quyen_tai_khoan (MA_QUYEN, USER_NAME) "
				+ "VALUES (?, ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql,
							new Object[] {cccdTam.getSoCC(), password});
		if (i > 0) {
			int q = jdbcTemplate.update(
					sql_quyen,
					new Object[] {"0", cccdTam.getSoCC() });
			if(q > 0){
				return true;
			}
			return true;
		}
		return false;
	}
	
	public Boolean setQuyenTaiKhoan(String taiKhoan, int maQuyen){
		String sql = "UPDATE quyen_tai_khoan SET MA_QUYEN = ? WHERE USER_NAME = ? ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql,
							new Object[] {maQuyen, taiKhoan});
		if (i > 0) {
			System.out.println("Update 1 row");
			return true;
		}
		return false;
	}
	
	public TaiKhoan getTaiKhoan(String taiKhoan){
		String sql = "SELECT * FROM tai_khoan a, cccd b, ttdk_cccd c, khai_sinh d "
				+ " WHERE"
				+ " (a.user_name = b.so_cc and b.ma_so = c.ma_so and c.so_ks = d.so_ks and"
				+ " a.USER_NAME = '"+taiKhoan+"')";
		if(taiKhoan.equals("admin")){
			 sql = "SELECT * FROM tai_khoan"
					+ " WHERE"
					+ " USER_NAME = '"+taiKhoan+"'";
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TaiKhoan> tk;
		tk = jdbcTemplate.query(sql, new TaiKhoanRowMapper());
		if(tk.isEmpty()){
			return null;
		}
		return tk.get(0);
	}
	@Override
	public Boolean updateTaiKhoan(TaiKhoan taiKhoan) {
		String sql = "UPDATE tai_khoan SET"
				+ " PASSWORD = ?,"
				+ " EMAIL = ?,"
				+ " SO_DIEN_THOAI = ?,"
				+ " CO_QUAN = ?,"
				+ " TRANG_THAI = ?"
				+ " WHERE USER_NAME = ?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			//insert a user
			int i = jdbcTemplate.update(
								sql,
								new Object[] {taiKhoan.getPassword(),
										taiKhoan.getEmail(),
										taiKhoan.getSoDienThoai(),
										taiKhoan.getCoQuan(),
										taiKhoan.getTrangThai(),
										taiKhoan.getUsername()});
			if (i > 0) {
				System.out.println("Update 1 row");
				return true;
			}
			return false;
	}
	@Override
	public List<TaiKhoan> getDSTaiKhoan() {
		String sql = "SELECT * FROM tai_khoan a, cccd b, ttdk_cccd c, khai_sinh d WHERE a.user_name = b.so_cc and b.ma_so = c.ma_so and c.so_ks = d.so_ks";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TaiKhoan> tk;
		tk = jdbcTemplate.query(sql, new TaiKhoanRowMapper());
		return tk;
	}
	@Override
	public Boolean khoaUser(String taiKhoan) {
		String sql = "UPDATE tai_khoan SET"
				+ " TRANG_THAI = 0"
				+ " WHERE USER_NAME = ?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			//insert a user
			int i = jdbcTemplate.update(
								sql,
								new Object[] {taiKhoan});
			if (i > 0) {
				System.out.println("da khoa tai khoan: "+taiKhoan);
				return true;
			}
			return false;
	}
	@Override
	public Boolean moKhoaUser(String taiKhoan) {
		String sql = "UPDATE tai_khoan SET"
				+ " TRANG_THAI = 1"
				+ " WHERE USER_NAME = ?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			//insert a user
			int i = jdbcTemplate.update(
								sql,
								new Object[] {taiKhoan});
			if (i > 0) {
				System.out.println("da ma khoa tai khoan: "+taiKhoan);
				return true;
			}
			return false;
	}
	@Override
	public List<TaiKhoan> timKiemTaiKhoan(String tuKhoa) {
		String sql = "SELECT * FROM tai_khoan a, cccd b, ttdk_cccd c, khai_sinh d "
				+ " WHERE a.user_name = b.so_cc and b.ma_so = c.ma_so and c.so_ks = d.so_ks and"
				+ " (a.USER_NAME like '%"+tuKhoa+"%'"
						+ "or d.HO_TEN like '%"+tuKhoa+"%')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TaiKhoan> tk;
		tk = jdbcTemplate.query(sql, new TaiKhoanRowMapper());
		return tk;
	}
	@Override
	public TaiKhoan dangNhap(String taiKhoan, String password, String maQuyen) {
		String sql = "SELECT * "
				+ " FROM tai_khoan A, quyen_tai_khoan B, quyen C"
				+ " WHERE"
				+ " B.MA_QUYEN = C.MA_QUYEN"
				+ " AND B.MA_QUYEN = '"+maQuyen+"'"
				+ " AND B.USER_NAME = A.USER_NAME"
				+ " AND A.USER_NAME = '"+taiKhoan+"'"
				+ " AND A.PASSWORD = '"+password+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TaiKhoan> tk;
		tk = jdbcTemplate.query(sql, new TaiKhoanRowMapper());
		if(tk.isEmpty()){
			return null;
		}
		return tk.get(0);
	}
	@Override
	public Boolean themTaiKhoan(TaiKhoan taiKhoan) {
		String sql = "INSERT INTO tai_khoan (USER_NAME, PASSWORD , CO_QUAN) "
				+ "VALUES (?, ?, ?)";
		String sql_quyen = "INSERT INTO quyen_tai_khoan (MA_QUYEN, USER_NAME) "
				+ "VALUES (?, ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql,
							new Object[] {taiKhoan.getUsername(), taiKhoan.getPassword(), taiKhoan.getCoQuan() });
		if (i > 0) {
			int n = jdbcTemplate.update(
					sql_quyen,
					new Object[] {"0", taiKhoan.getUsername()});
			if(n < 0){
				return true;
			}
			return true;
		}
		return false;
	}
	@Override
	public List<TaiKhoan> getDSTaiKhoanHoatDong() {
		String sql = "SELECT * FROM tai_khoan a, cccd b, ttdk_cccd c, khai_sinh d "
				+ "WHERE a.user_name = b.so_cc and b.ma_so = c.ma_so and c.so_ks = d.so_ks and a.TRANG_THAI=1";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TaiKhoan> tk;
		tk = jdbcTemplate.query(sql, new TaiKhoanRowMapper());
		return tk;
	}
	@Override
	public List<TaiKhoan> timKiemTaiKhoanHoatDong(String tuKhoa) {
		String sql = "SELECT * FROM tai_khoan a, cccd b, ttdk_cccd c, khai_sinh d "
				+ " WHERE a.user_name = b.so_cc and b.ma_so = c.ma_so and c.so_ks = d.so_ks and"
				+ " (a.USER_NAME like '%"+tuKhoa+"%'"
						+ "or d.HO_TEN like '%"+tuKhoa+"%')"
				+ " AND a.TRANG_THAI=1";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TaiKhoan> tk;
		tk = jdbcTemplate.query(sql, new TaiKhoanRowMapper());
		return tk;
	}
	@Override
	public List<TaiKhoan> getDSTaiKhoanBiKhoa() {
		String sql = "SELECT * FROM tai_khoan a, cccd b, ttdk_cccd c, khai_sinh d "
				+ "WHERE a.user_name = b.so_cc and b.ma_so = c.ma_so and c.so_ks = d.so_ks and a.TRANG_THAI= 0";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TaiKhoan> tk;
		tk = jdbcTemplate.query(sql, new TaiKhoanRowMapper());
		return tk;
	}
	@Override
	public List<TaiKhoan> timKiemTaiKhoanBiKhoa(String tuKhoa) {
		String sql = " SELECT * FROM tai_khoan a, cccd b, ttdk_cccd c, khai_sinh d "
				+ " WHERE a.user_name = b.so_cc and b.ma_so = c.ma_so and c.so_ks = d.so_ks and"
				+ " (a.USER_NAME like '%"+tuKhoa+"%'"
						+ "or d.HO_TEN like '%"+tuKhoa+"%')"
				+ " AND a.TRANG_THAI=0";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TaiKhoan> tk;
		tk = jdbcTemplate.query(sql, new TaiKhoanRowMapper());
		return tk;
	}
	@Override
	public TaiKhoan getTaiKhoanBangEmail(String email) {
		String sql = " SELECT * FROM tai_khoan a, cccd d, ttdk_cccd e, khai_sinh f "
				+ " quyen b, "
				+ " quyen_tai_khoan c "
				+ " WHERE a.user_name = d.so_cc and d.ma_so = e.ma_so and e.so_ks = f.so_ks and "
				+ " c.MA_QUYEN = b.MA_QUYEN "
				+ " AND c.USER_NAME = a.USER_NAME "
				+ "and a.EMAIL = '"+email+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TaiKhoan> tk;
		tk = jdbcTemplate.query(sql, new TaiKhoanRowMapper());
		if(tk.isEmpty()){
			return null;
		}
		return tk.get(0);
	}
}
