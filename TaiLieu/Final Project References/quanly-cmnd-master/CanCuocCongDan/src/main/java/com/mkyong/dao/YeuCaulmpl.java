package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.YeuCauRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Chung.YeuCau;

public class YeuCaulmpl implements YeuCauDao {
	@Autowired
	private DataSource dataSource;

	public YeuCaulmpl() {
		super();
	}
	@Override
	public List<YeuCau> getDSYeuCau() {
		List<YeuCau> dsYeuCau;
		String sql = "SELECT * "
						+ "FROM yeu_cau ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsYeuCau = jdbcTemplate.query(sql, new YeuCauRowMapper());
		return dsYeuCau;
	}
	@Override
	public YeuCau getYeuCauID(int maYeuCau) {
		List<YeuCau> dsYeuCau;
		String sql = "SELECT * "
						+ "FROM yeu_cau "
						+ "WHERE MA_YEU_CAU ="+ maYeuCau;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsYeuCau = jdbcTemplate.query(sql, new YeuCauRowMapper());
		if(dsYeuCau.isEmpty()){
			return null;
		}
		return dsYeuCau.get(0);
	}
	@Override
	public List<YeuCau> getDSYeuCauHoatDong() {
		List<YeuCau> dsYeuCau;
		String sql = "SELECT * "
						+ "FROM yeu_cau WHERE TINH_TRANG = 1";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsYeuCau = jdbcTemplate.query(sql, new YeuCauRowMapper());
		return dsYeuCau;
	}
	@Override
	public List<YeuCau> getDSYeuCauTamDung() {
		List<YeuCau> dsYeuCau;
		String sql = "SELECT * "
						+ "FROM yeu_cau TRINH_TRANG = 0";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dsYeuCau = jdbcTemplate.query(sql, new YeuCauRowMapper());
		return dsYeuCau;
	}
	@Override
	public Boolean insertYeuCau(YeuCau yeuCau) {
		String sql = "INSERT INTO yeu_cau(TEN_YEU_CAU, MO_TA, GIAY_TO, LE_PHI) values(?, ?, ?, ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {yeuCau.getTenYeuCau(), yeuCau.getMoTa(), yeuCau.getGiayTo(), yeuCau.getLePhi()});
		if (i > 0) {
			System.out.println("Insert 1 row into yeu cau");
			return true;
		}
		return false;
	}
	@Override
	public Boolean updateYeuCau(YeuCau yeuCau) {
		String sql = "UPDATE yeu_cau SET"
				+ " TEN_YEU_CAU = ?,"
				+ " MO_TA = ?,"
				+ " GIAY_TO = ?,"
				+ " LE_PHI = ?"
				+ " WHERE"
				+ " MA_YEU_CAU = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {yeuCau.getTenYeuCau(), yeuCau.getMoTa(), yeuCau.getGiayTo(), yeuCau.getLePhi(), yeuCau.getMaYeuCau()});
		if (i > 0) {
			System.out.println("Update 1 row");
			return true;
		}
		return false;
	}
	@Override
	public Boolean tamDungYeuCau(YeuCau yeuCau) {
		String sql = "UPDATE yeu_cau SET"
				+ " TINH_TRANG = 0"
				+ " WHERE"
				+ " MA_YEU_CAU = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {yeuCau.getMaYeuCau()});
		if (i > 0) {
			System.out.println("Ä�Ã£ Stop 1 yÃªu cáº§u");
			return true;
		}
		return false;
	}
	@Override
	public Boolean tiepTucYeuCau(YeuCau yeuCau) {
		String sql = "UPDATE yeu_cau SET"
				+ " TINH_TRANG = 1"
				+ " WHERE"
				+ " MA_YEU_CAU = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {yeuCau.getMaYeuCau()});
		if (i > 0) {
			System.out.println("Ä�Ã£ tiáº¿p tá»¥c 1 yÃªu cáº§u");
			return true;
		}
		return false;
	}
}
