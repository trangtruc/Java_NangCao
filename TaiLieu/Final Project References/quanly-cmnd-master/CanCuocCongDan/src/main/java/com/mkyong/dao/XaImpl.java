package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.XaRowMapper;
import jdbc.Chung.XaTinhHuyenRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Chung.Xa;

public class XaImpl implements XaDao {
	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;
	
	public XaImpl() {
		super();
	}

	@Override
	public List<Xa> getDSXa(String maHuyen) {
		String sql = "SELECT MA_XA, TEN_XA, MA_HUYEN "
				+ "FROM xa "
				+ "WHERE MA_HUYEN = '" + maHuyen + "'";
		List<Xa> dsXa;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsXa = jdbcTemplate.query(sql, new XaRowMapper());
		return dsXa;
	}

	@Override
	public Xa getXaBangMa(String maXa) {
		String sql = "SELECT MA_XA, TEN_XA, MA_HUYEN "
				+ "FROM xa "
				+ "WHERE MA_XA = '" + maXa + "'";
		List<Xa> dsXa;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsXa = jdbcTemplate.query(sql, new XaRowMapper());
		if(dsXa.isEmpty()) {
			return null;
		}
		return dsXa.get(0);
	}

	@Override
	public Xa getXaHuyenTinh(String maXa) {
		String sql = "SELECT A.MA_XA, A.TEN_XA, A.MA_HUYEN, B.TEN_HUYEN, B.MA_TINH, C.TEN_TINH "
				+ "FROM xa A, huyen B, tinh C "
				+ "WHERE A.MA_HUYEN = B.MA_HUYEN AND B.MA_TINH = C.MA_TINH AND MA_XA = '" + maXa + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Xa> dsXa;
		dsXa = jdbcTemplate.query(sql, new XaTinhHuyenRowMapper());
		if(dsXa.isEmpty()) {
			return null;
		}
		return dsXa.get(0);
	}

	@Override
	public List<Xa> getDSXaAll() {
		String sql = "SELECT * "
				+ "FROM xa ";
		List<Xa> dsXa;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsXa = jdbcTemplate.query(sql, new XaRowMapper());
		return dsXa;
	}

	@Override
	public Boolean insertXa(Xa xa) {
		String sql = "INSERT into xa values(?, ?, ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {xa.getMaXa(), xa.getTenXa(), xa.getHuyen().getMaHuyen()});
		if (i > 0) {
			System.out.println("Insert 1 row");
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateXa(Xa xa) {
		String sql = "UPDATE xa SET"
				+ " TEN_XA = ?,"
				+ " MA_HUYEN = ?"
				+ " WHERE"
				+ " MA_XA = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {xa.getTenXa(), xa.getHuyen().getMaHuyen(), xa.getMaXa()});
		if (i > 0) {
			System.out.println("Update 1 row");
			return true;
		}
		return false;
	}

	@Override
	public List<Xa> timKiemXa(String tuKhoa) {
		
		String sql = "SELECT MA_XA, TEN_XA, MA_HUYEN "
				+ "FROM xa "
				+ "WHERE TEN_XA like '%" + tuKhoa + "%'";
		List<Xa> dsXa;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsXa = jdbcTemplate.query(sql, new XaRowMapper());
		return dsXa;
	}

}
