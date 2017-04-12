package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Config.ConfigKetHonRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Config.ConfigKetHon;

public class ConfigKetHonImpl implements ConfigKetHonDao{
	@Autowired
	private DataSource dataSource;
	
	public ConfigKetHonImpl(){
		super();
	}
	@Override
	public ConfigKetHon getConfigKetHon() {
		String sql = "SELECT * "
				+ "FROM config_ket_hon";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<ConfigKetHon> ck = jdbcTemplate.query(sql, new ConfigKetHonRowMapper());
		return ck.get(0);
	}

	@Override
	public Boolean updateConfigKetHon(ConfigKetHon ck) {
		String sql = "UPDATE config_ket_hon SET"
				+ " TUOI_NAM = ?,"
				+ " TUOI_NU = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {
						ck.getTuoiNam(),
						ck.getTuoiNu()});
		if (i > 0) {
			System.out.println("Update 1 row from config_ket_hon");
			return true;
		}
		return false;
	}

}
