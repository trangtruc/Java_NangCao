package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.DanTocRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Chung.DanToc;

public class DanTocImpl implements DanTocDao {
	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;
	
	public DanTocImpl() {
		super();
	}

	@Override
	public List<DanToc> getDSDanToc() {
		String sql = "SELECT * FROM dan_toc";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<DanToc> dsDanToc = jdbcTemplate.query(sql, new DanTocRowMapper());
		return dsDanToc;
	}
	@Override
	public DanToc getDanTocID(String maDanToc) {
		String sql = "SELECT * FROM dan_toc WHERE MA_DT = '"+maDanToc+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<DanToc> danToc = jdbcTemplate.query(sql, new DanTocRowMapper());
		if(danToc.isEmpty()){
			return null;
		}
		return danToc.get(0);
	}

	@Override
	public Boolean insertDanToc(DanToc danToc) {
		String sql = "INSERT into dan_toc values(?, ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {danToc.getMaDT(), danToc.getTenDT()});
		if (i > 0) {
			System.out.println("Insert 1 row");
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateDanToc(DanToc danToc) {
		String sql = "UPDATE dan_toc SET"
				+ " TEN_DT = ?"
				+ " WHERE MA_DT = ?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			//insert a user
			int i = jdbcTemplate.update(
								sql,
								new Object[] {danToc.getTenDT(), danToc.getMaDT()});
			if (i > 0) {
				System.out.println("Update 1 row");
				return true;
			}
			return false;
	}

	@Override
	public List<DanToc> timKiemDanToc(String tuKhoa) {
		String sql = "SELECT * FROM dan_toc WHERE TEN_DT like '%"+tuKhoa+"%'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<DanToc> dsDanToc = jdbcTemplate.query(sql, new DanTocRowMapper());
		return dsDanToc;
	}
}
