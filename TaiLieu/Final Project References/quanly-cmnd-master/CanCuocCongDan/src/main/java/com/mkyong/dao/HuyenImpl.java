package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.HuyenRowMapper;
import jdbc.Chung.HuyenTinhRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Chung.Huyen;

public class HuyenImpl implements HuyenDao {
	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;

	public HuyenImpl() {
		super();
	}
	@Override
	public List<Huyen> getDSHuyen(String maTinh) {
		List<Huyen> dsHuyen;
		String sql = "SELECT MA_HUYEN, TEN_HUYEN, MA_TINH "
						+ "FROM huyen "
						+ "WHERE MA_TINH = '" + maTinh + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsHuyen = jdbcTemplate.query(sql, new HuyenRowMapper());
		return dsHuyen;
	}
	@Override
	public Huyen getHuyenBangMa(String maHuyen) {
		List<Huyen> dsHuyen;
		String sql = "SELECT MA_HUYEN, TEN_HUYEN, MA_TINH "
						+ "FROM huyen "
						+ "WHERE MA_HUYEN = '" + maHuyen + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsHuyen = jdbcTemplate.query(sql, new HuyenRowMapper());
		if(dsHuyen.isEmpty()) {
			return null;
		}
		return dsHuyen.get(0);
	}
	
	@Override
	public Huyen getTinhHuyenBangMa(String maHuyen) {
		String sql = "SELECT "
						+ "H.MA_HUYEN, "
						+ "H.TEN_HUYEN, "
						+ "H.MA_TINH, "
						+ "T.TEN_TINH "
					+ "FROM "
						+ "huyen H, "
						+ "tinh T "
					+ "WHERE "
						+ "H.MA_TINH = T.MA_TINH "
						+ "AND H.MA_HUYEN = " + maHuyen;
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Huyen> dsHuyen;
		dsHuyen = jdbcTemplate.query(sql, new HuyenTinhRowMapper());
		if(dsHuyen.isEmpty()) {
			return null;
		}
		return dsHuyen.get(0);
	}
	@Override
	public List<Huyen> timKiemHuyen(String tuKhoa) {
		List<Huyen> dsHuyen;
		String sql = "SELECT MA_HUYEN, TEN_HUYEN, MA_TINH "
						+ "FROM huyen "
						+ "WHERE TEN_HUYEN like '%"+ tuKhoa +"%'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsHuyen = jdbcTemplate.query(sql, new HuyenRowMapper());
		return dsHuyen;
	}
	@Override
	public List<Huyen> getDSHuyenAll() {
		List<Huyen> dsHuyen;
		String sql = "SELECT MA_HUYEN, TEN_HUYEN, MA_TINH "
						+ "FROM huyen ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsHuyen = jdbcTemplate.query(sql, new HuyenRowMapper());
		return dsHuyen;
	}
	@Override
	public Boolean insertHuyen(Huyen huyen) {
		String sql = "INSERT into huyen values(?, ?, ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {huyen.getMaHuyen(), huyen.getTenHuyen(), huyen.getTinh().getMaTinh()});
		if (i > 0) {
			System.out.println("Insert 1 row");
			return true;
		}
		return false;
	}
	@Override
	public Boolean updateHuyen(Huyen huyen) {
		String sql = "UPDATE huyen SET"
				+ " TEN_HUYEN = ?,"
				+ " MA_TINH = ?"
				+ " WHERE"
				+ " MA_HUYEN = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {huyen.getTenHuyen(), huyen.getTinh().getMaTinh(), huyen.getMaHuyen()});
		if (i > 0) {
			System.out.println("Update 1 row");
			return true;
		}
		return false;
	} 
}
