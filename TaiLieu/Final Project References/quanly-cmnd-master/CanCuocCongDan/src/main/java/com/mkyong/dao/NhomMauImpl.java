package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.NhomMauRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Chung.NhomMau;

public class NhomMauImpl implements NhomMauDao {
	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;

	public NhomMauImpl() {
		super();
	}
	
	public List<NhomMau> getDSNhomMau(){
		String sql = "SELECT * FROM nhom_mau ORDER BY MA_NHOM_MAU ASC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<NhomMau> nhomMau;
		nhomMau = jdbcTemplate.query(sql, new NhomMauRowMapper());
		return nhomMau;
	}
	public List<NhomMau> getNhomMauBangMa(String maNM){
		String sql = "SELECT * FROM nhom_mau WHERE MA_NHOM_MAU = "+maNM;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<NhomMau> nhomMau;
		nhomMau = jdbcTemplate.query(sql, new NhomMauRowMapper());
		return nhomMau;
	}
}
