package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Config.ConfigNgayNghiRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Config.ConfigNgayNghi;

public class ConfigNgayNghiImpl implements ConfigNgayNghiDao {
	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;
	
	public ConfigNgayNghiImpl(){
		super();
	}
	@Override
	public Boolean insertNgayNghi(int ngay, int thang) {
		String sql = "INSERT INTO config_cccd_ngay_nghi(NGAY, THANG) values(?, ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {ngay, thang});
		if (i > 0) {
			System.out.println("Insert 1 row into config_cccd_ngay_nghi");
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteNgayNghi(int ngay, int thang) {
		String sql = "DELETE FROM config_cccd_ngay_nghi WHERE NGAY = ? AND THANG = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql,
							new Object[] {ngay, thang});
		if (i > 0) {
			System.out.println("delete 1 row from config_cccd_ngay_nghi");
			return true;
		}
		return false;
	}

	@Override
	public List<ConfigNgayNghi> getDSNgayNghi() {
		String sql = "SELECT * "
				+ "FROM config_cccd_ngay_nghi ORDER BY THANG ASC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<ConfigNgayNghi> ConfigNgayNghi = jdbcTemplate.query(sql, new ConfigNgayNghiRowMapper());
		return ConfigNgayNghi;
	}
	@Override
	public Boolean ktNgayNghi(ConfigNgayNghi ngayNghi) {
		String sql = "SELECT * "
					+ "FROM config_cccd_ngay_nghi "
					+ "WHERE "
						+ "NGAY = '" + ngayNghi.getNgay() + "' "
						+ "AND THANG = '" + ngayNghi.getThang() + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<ConfigNgayNghi> dsNgayNghi = jdbcTemplate.query(sql, new ConfigNgayNghiRowMapper());
		if (dsNgayNghi.isEmpty()) {
			return false;
		}
		return true;
	}
}
