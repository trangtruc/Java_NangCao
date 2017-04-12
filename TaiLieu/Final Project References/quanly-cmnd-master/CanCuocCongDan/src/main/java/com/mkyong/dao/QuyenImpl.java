package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.QuyenRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Chung.Quyen;

public class QuyenImpl implements QuyenDao {
	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;
	public QuyenImpl(){
		super();
	}
	@Override
	public List<Quyen> getDSQuyen() {
		String sql = "SELECT * "
				+ "FROM quyen ORDER BY MO_TA ASC";
		List<Quyen> dsQuyen;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsQuyen = jdbcTemplate.query(sql, new QuyenRowMapper());
		return dsQuyen;
	}
	@Override
	public Quyen getQuyen(int maQuyen) {
		String sql = "SELECT * "
				+ "FROM quyen "
				+ "WHERE MA_QUYEN = "+maQuyen + " OR TEN_QUYEN = '"+maQuyen+"'";
		List<Quyen> dsQuyen;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsQuyen = jdbcTemplate.query(sql, new QuyenRowMapper());
		return dsQuyen.get(0);
	}
	@Override
	public Quyen getQuyenBangTen(String tenQuyen) {
		String sql = "SELECT * "
				+ "FROM quyen "
				+ "WHERE TEN_QUYEN = '"+tenQuyen+"'";
		List<Quyen> dsQuyen;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsQuyen = jdbcTemplate.query(sql, new QuyenRowMapper());
		return dsQuyen.get(0);
	}
	@Override
	public List<Quyen> getQuyenTaiKhoan(String taiKhoan) {
		String sql = "SELECT * "
				+ "FROM quyen A,"
				+ " quyen_tai_khoan B "
				+ "WHERE A.MA_QUYEN = B.MA_QUYEN "
				+ "AND B.USER_NAME = '"+taiKhoan+"'";
		List<Quyen> dsQuyen;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsQuyen = jdbcTemplate.query(sql, new QuyenRowMapper());
		return dsQuyen;
	}
	@Override
	public Boolean kiemTraQuyen(String taiKhoan, int maQuyen) {
		String sql = "SELECT * "
				+ "FROM quyen A,"
				+ " quyen_tai_khoan B "
				+ "WHERE A.MA_QUYEN = B.MA_QUYEN "
				+ "AND B.USER_NAME = '"+taiKhoan+"' "
				+ " AND B.MA_QUYEN = '"+maQuyen+"' ";
		List<Quyen> dsQuyen;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsQuyen = jdbcTemplate.query(sql, new QuyenRowMapper());
		if(dsQuyen.isEmpty()){
			return false;
		}
		return true;
	}
	@Override
	public Boolean addQuyen(String taiKhoan, int maQuyen) {
		String sql = "INSERT INTO quyen_tai_khoan(MA_QUYEN, USER_NAME)"
				+ "VALUES(?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql,
							new Object[] {maQuyen, taiKhoan});
		if (i > 0) {
			System.out.println("add 1 quyen "+maQuyen+" cho tai khoan "+taiKhoan+"");
			return true;
		}
		return false;
	}
	@Override
	public Boolean deleteQuyen(String taiKhoan, int maQuyen) {
		String sql = "DELETE FROM quyen_tai_khoan"
				+ " WHERE "
				+ " USER_NAME = '"+taiKhoan+"'"
				+ " AND MA_QUYEN = "+maQuyen;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql);
		if (i > 0) {
			System.out.println("delete 1 quyen "+maQuyen+" cua tai khoan "+taiKhoan+"");
			return true;
		}
		return false;
	}
	@Override
	public Boolean kiemTraQuyenBangTen(String taiKhoan, String tenQuyen) {
		String sql = "SELECT * "
				+ "FROM quyen A,"
				+ " quyen_tai_khoan B "
				+ "WHERE A.MA_QUYEN = B.MA_QUYEN "
				+ "AND B.USER_NAME = '"+taiKhoan+"' "
				+ " AND A.TEN_QUYEN = '"+tenQuyen+"' ";
		List<Quyen> dsQuyen;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsQuyen = jdbcTemplate.query(sql, new QuyenRowMapper());
		if(dsQuyen.isEmpty()){
			return false;
		}
		return true;
	}

}
