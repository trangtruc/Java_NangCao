package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Config.ConfigCCCDRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Config.ConfigCCCD;

public class ConfigCCCDImpl implements ConfigCCCDDao{
	@Autowired
	private DataSource dataSource;
	
	public ConfigCCCDImpl(){
		super();
	}
	@Override
	public ConfigCCCD getConfigCCCD() {
		String sql = "SELECT * "
				+ "FROM config_cccd_thoi_han";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<ConfigCCCD> configCCCD = jdbcTemplate.query(sql, new ConfigCCCDRowMapper());
		return configCCCD.get(0);
	}

	@Override
	public Boolean updateConfigCCCD(ConfigCCCD cc) {
		String sql = "UPDATE config_cccd_thoi_han SET"
				+ " HAN_HO_SO = ?,"
				+ " HAN_SU_DUNG = ?,"
				+ " SO_HS_1_NGAY = ?,"
				+ " TUOI = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {
						cc.getHanHoSo(),
						cc.getHanSuDung(),
						cc.getSoHoSo1Ngay(),
						cc.getTuoi()});
		if (i > 0) {
			System.out.println("Update 1 row from CONFIG_CCCD");
			return true;
		}
		return false;
	}

}
