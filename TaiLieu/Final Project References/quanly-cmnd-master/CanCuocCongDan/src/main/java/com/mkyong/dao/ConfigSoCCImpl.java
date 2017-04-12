package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Config.ConfigSoCCRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Config.ConfigSoCC;

public class ConfigSoCCImpl implements ConfigSoCCDao{
	@Autowired
	private DataSource dataSource;
	
	public ConfigSoCCImpl(){
		super();
	}
	@Override
	public Boolean updateConfigSoCC(ConfigSoCC csc) {
		String sql = "UPDATE config_cccd_so_cc SET"
				+ " NAM = ?,"
				+ " MO_TA = ?,"
				+ " GIA_TRI_NAM_TRUOC = ?,"
				+ " GIA_TRI_NU_TRUOC = ?,"
				+ " GIA_TRI_NAM_SAU = ?,"
				+ " GIA_TRI_NU_SAU = ?"
				+ " WHERE NAM = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {
						csc.getNam(),
						csc.getMoTa(),
						csc.getGiaTriNamTruoc(),
						csc.getGiaTriNuTruoc(),
						csc.getGiaTriNamSau(),
						csc.getGiaTriNuSau(),
						csc.getNam()
				});
		if (i > 0) {
			System.out.println("Update 1 row from CONFIG_CCCD");
			return true;
		}
		return false;
	}

	@Override
	public Boolean insertConfigSoCC(ConfigSoCC csc) {
		String sql = "INSERT INTO config_cccd_so_cc(NAM, MO_TA, "
				+ " GIA_TRI_NAM_TRUOC, GIA_TRI_NU_TRUOC, "
				+ " GIA_TRI_NAM_SAU, GIA_TRI_NU_SAU) "
				+ " VALUES(?, ?,"
				+ " ?, ?,"
				+ " ?, ?)";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {
						csc.getNam(),
						csc.getMoTa(),
						csc.getGiaTriNamTruoc(),
						csc.getGiaTriNuTruoc(),
						csc.getGiaTriNamSau(),
						csc.getGiaTriNuSau()
				});
		if (i > 0) {
			System.out.println("insert 1 row from config_cccd_so_cc");
			return true;
		}
		return false;
	}

	@Override
	public List<ConfigSoCC> getDSConfigSoCC() {
		String sql = "SELECT * "
				+ " FROM config_cccd_so_cc ORDER BY NAM DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<ConfigSoCC> ConfigSoCC = jdbcTemplate.query(sql, new ConfigSoCCRowMapper());
		if(ConfigSoCC.isEmpty()){
			return null;
		}
		return ConfigSoCC;
	}

	@Override
	public ConfigSoCC getConfigSoCC() {
		String sql = "SELECT * "
				+ " FROM config_cccd_so_cc";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<ConfigSoCC> ConfigSoCC = jdbcTemplate.query(sql, new ConfigSoCCRowMapper());
		if(ConfigSoCC.isEmpty()){
			return null;
		}
		return ConfigSoCC.get(0);
	}
	@Override
	public Boolean deleteConfigSoCC(ConfigSoCC csc) {
		String sql = "DELETE FROM config_cccd_so_cc WHERE NAM = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {
						csc.getNam()
				});
		if (i > 0) {
			System.out.println("delete 1 row from config_cccd_so_cc");
			return true;
		}
		return false;
	}

}
