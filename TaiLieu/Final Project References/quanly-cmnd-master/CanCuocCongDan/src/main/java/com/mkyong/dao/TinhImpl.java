package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.TinhRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Chung.Tinh;

public class TinhImpl implements TinhDao {
	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;
	
	public TinhImpl() {
		super();
	}

	@Override
	public List<Tinh> getDSTinh() {
		String sql = "SELECT MA_TINH, TEN_TINH "
						+ "FROM tinh";
		List<Tinh> dsTinh;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsTinh = jdbcTemplate.query(sql, new TinhRowMapper());
		return dsTinh;
	}

	@Override
	public Tinh getTinhBangMa(String maTinh) {
		String sql = "SELECT MA_TINH, TEN_TINH "
						+ "FROM tinh "
						+ "WHERE MA_TINH = '" + maTinh + "'";
		List<Tinh> dsTinh;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsTinh = jdbcTemplate.query(sql, new TinhRowMapper());
		if(dsTinh.isEmpty()) {
			return null;
		}
		return dsTinh.get(0);
	}

	@Override
	public Boolean insertTinh(Tinh tinh) {
		String sql = "INSERT into tinh values(?, ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {tinh.getMaTinh(), tinh.getTenTinh()});
		if (i > 0) {
			System.out.println("Insert 1 row");
			return true;
		}
		return false;
	}

	@Override
	public List<Tinh> timKiemTinh(String tuKhoa) {
		String sql = "SELECT MA_TINH, TEN_TINH "
				+ "FROM tinh WHERE MA_TINH like '%"+tuKhoa+"%' or TEN_TINH  like '%"+tuKhoa+"%'";
		List<Tinh> dsTinh;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsTinh = jdbcTemplate.query(sql, new TinhRowMapper());
		return dsTinh;
	}

	@Override
	public Boolean updateTinh(Tinh tinh) {
		String sql = "UPDATE tinh SET"
				+ " TEN_TINH = ?"
				+ " WHERE MA_TINH = ?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			//insert a user
			int i = jdbcTemplate.update(
								sql,
								new Object[] {tinh.getTenTinh(), tinh.getMaTinh()});
			if (i > 0) {
				System.out.println("Update 1 row");
				return true;
			}
			return false;
	}
	
}
