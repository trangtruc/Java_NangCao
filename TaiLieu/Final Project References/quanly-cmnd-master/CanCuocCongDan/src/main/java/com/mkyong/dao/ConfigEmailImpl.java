package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Config.ConfigEmailRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Config.ConfigEmail;

public class ConfigEmailImpl implements ConfigEmailDao{
	@Autowired
	private DataSource dataSource;
	
	public ConfigEmailImpl(){
		super();
	}
	@Override
	public List<ConfigEmail> getDSConfigEmail() {
		String sql = "SELECT * "
				+ "FROM config_email";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<ConfigEmail> ConfigEmail = jdbcTemplate.query(sql, new ConfigEmailRowMapper());
		return ConfigEmail;
	}

	@Override
	public ConfigEmail getConfigEmail(String maMail) {
		String sql = "SELECT * "
				+ "FROM config_email WHERE MA_EMAIL = '"+maMail+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<ConfigEmail> ConfigEmail = jdbcTemplate.query(sql, new ConfigEmailRowMapper());
		if(ConfigEmail.isEmpty()){
			return null;
		}
		return ConfigEmail.get(0);
	}

	@Override
	public Boolean insertConfigEmail(ConfigEmail cmail) {
		String sql = "INSERT INTO config_email(MA_EMAIL, EMAIL, MAT_KHAU, TIEU_DE_GUI, NOI_DUNG_GUI)"
				+ " values(?, ?, ?, ?, ?)";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {cmail.getMaEmail(),
						cmail.getEmail(),
						cmail.getMatKhau(),
						cmail.getTieuDeGui(),
						cmail.getNoiDungGui()});
		if (i > 0) {
			System.out.println("Insert 1 row into config_email");
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateConfigEmail(ConfigEmail cmail) {
		String sql = "UPDATE config_email SET"
				+ " EMAIL = ?,"
				+ " MAT_KHAU = ?,"
				+ " TIEU_DE_GUI = ?,"
				+ " NOI_DUNG_GUI = ?"
				+ " WHERE MA_EMAIL = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {
						cmail.getEmail(),
						cmail.getMatKhau(),
						cmail.getTieuDeGui(),
						cmail.getNoiDungGui(),
						cmail.getMaEmail()});
		if (i > 0) {
			System.out.println("Update 1 row from config_email");
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteConfigEmail(String maEmail) {
		String sql = "DELETE FROM config_email WHERE MA_EMAIL = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql,
							new Object[] {maEmail});
		if (i > 0) {
			System.out.println("delete 1 row from config_email");
			return true;
		}
		return false;
	}

}
