package com.mkyong.dao;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import jdbc.Vu.TheCMTRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Vu.TheCMT;

public class TheCMTImpl implements TheCMTDao {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private HttpSession session;
	public TheCMTImpl(){
		super();
	}
	@Override
	public List<TheCMT> getDSThe() {
		String sql = "SELECT * FROM danh_sach_lam_the a, cccd b, chi_tiet_ho_khau c, khai_sinh d"
				+ " WHERE"
				+ " b.SO_CC = c.SO_CC and c.SO_KS = d.SO_KS"
				+ " and a.DA_LAM = '1'"
				+ " and a.DA_TRA = '1'"
				+ " and a.SO_CC = b.SO_CC"
				+ " and a.NOI_TRA = '"+session.getAttribute("ssCoQuan")+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TheCMT> dsLamThe = jdbcTemplate.query(sql, new TheCMTRowMapper());
		return dsLamThe;
	}
	@Override
	public List<TheCMT> getDSChuaLamThe() {
		String sql = "SELECT * FROM danh_sach_lam_the a, cccd b, chi_tiet_ho_khau c, khai_sinh d"
				+ " WHERE"
				+ " b.SO_CC = c.SO_CC and c.SO_KS = d.SO_KS"
				+ " and a.DA_LAM = '0'"
				+ " and a.SO_CC = b.SO_CC"
				+ " and a.NOI_TRA = '"+session.getAttribute("ssCoQuan")+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TheCMT> dsLamThe = jdbcTemplate.query(sql, new TheCMTRowMapper());
		return dsLamThe;
	}
	@Override
	public Boolean insertThe(TheCMT the) {
		String sql = "INSERT INTO danh_sach_lam_the (MA_SO, SO_CC, PASSWORD , HAN_SD, NOI_TRA) "
				+ "VALUES (?, ?, ?, ?, ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql,
							new Object[] {the.getMaSo(), the.getSoCC(), the.getPassword(), the.getHanSD(), the.getNoiTra() });
		if (i > 0) {
			System.out.println("Insert 1 row");
			return true;
		}
		return false;
	}
	@Override
	public List<TheCMT> getDSTheChuaTra() {
		String sql = "SELECT * FROM danh_sach_lam_the a, cccd b, chi_tiet_ho_khau c, khai_sinh d"
				+ " WHERE"
				+ " b.SO_CC = c.SO_CC and c.SO_KS = d.SO_KS"
				+ " and a.DA_LAM = '1'"
				+ " and a.DA_TRA = '0'"
				+ " and a.SO_CC = b.SO_CC"
				+ " and a.NOI_TRA = '"+session.getAttribute("ssCoQuan")+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TheCMT> dsLamThe = jdbcTemplate.query(sql, new TheCMTRowMapper());
		return dsLamThe;
	}
	@Override
	public List<TheCMT> getDSTheDaTra() {
		String sql = "SELECT * FROM danh_sach_lam_the a, cccd b, chi_tiet_ho_khau c, khai_sinh d"
				+ " WHERE"
				+ " b.SO_CC = c.SO_CC and c.SO_KS = d.SO_KS"
				+ " and a.DA_LAM = '1'"
				+ " and a.DA_TRA = '1'"
				+ " and a.SO_CC = b.SO_CC"
				+ " and a.NOI_TRA = '"+session.getAttribute("ssCoQuan")+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TheCMT> dsLamThe = jdbcTemplate.query(sql, new TheCMTRowMapper());
		return dsLamThe;
	}
	@Override
	public Boolean updateThe(String set, String where) {
		String sql = "UPDATE `danh_sach_lam_the` " + set + where;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(sql);
		if (i > 0) {
			System.out.println("update 1 row");
			return true;
		}
		return false;
	}
	@Override
	public List<TheCMT> getDSTheBangMa(String maSo) {
		String sql = "SELECT * FROM danh_sach_lam_the a, cccd b, chi_tiet_ho_khau c, khai_sinh d"
				+ " WHERE"
				+ " b.SO_CC = c.SO_CC and c.SO_KS = d.SO_KS"
				+ " and a.DA_LAM = '1'"
				+ " and a.SO_CC = b.SO_CC"
				+ " and a.MA_SO like '%"+maSo+"%'"
				+ " and a.NOI_TRA = '"+session.getAttribute("ssCoQuan")+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TheCMT> dsLamThe = jdbcTemplate.query(sql, new TheCMTRowMapper());
		return dsLamThe;
	}
	@Override
	public Boolean deleteThe(TheCMT the) {
		String sql = "DELETE danh_sach_lam_the WHERE STT = " +the.getStt();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(sql);
		if (i > 0) {
			System.out.println("delete danh_sach_lam_the");
			return true;
		}
		return false;
	}
	@Override
	public TheCMT getTheBangSoCC(String soCC) {
		String sql = "SELECT * FROM danh_sach_lam_the a, cccd b, chi_tiet_ho_khau c, khai_sinh d"
				+ " WHERE"
				+ " b.SO_CC = c.SO_CC and c.SO_KS = d.SO_KS"
				+ " and a.SO_CC = b.SO_CC"
				+ " and a.SO_CC = '"+soCC+"' ORDER BY a.STT DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TheCMT> dsLamThe = jdbcTemplate.query(sql, new TheCMTRowMapper());
		if(dsLamThe.isEmpty()){
			return null;
		}
		return dsLamThe.get(0);
	}
	@Override
	public List<TheCMT> timKiemTheChuaLam(String tuKhoa) {
		String sql = "SELECT * FROM danh_sach_lam_the a, cccd b, chi_tiet_ho_khau c, khai_sinh d"
				+ " WHERE"
				+ " b.SO_CC = c.SO_CC and c.SO_KS = d.SO_KS"
				+ " and a.DA_LAM = '0'"
				+ " and a.SO_CC = b.SO_CC"
				+ " and (a.MA_SO like '%"+tuKhoa+"%' OR a.SO_CC like '%"+tuKhoa+"%')"
				+ " and a.NOI_TRA = '"+session.getAttribute("ssCoQuan")+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TheCMT> dsLamThe = jdbcTemplate.query(sql, new TheCMTRowMapper());
		return dsLamThe;
	}
	@Override
	public List<TheCMT> timKiemTheChuaTra(String tuKhoa) {
		String sql = "SELECT * FROM danh_sach_lam_the a, cccd b, chi_tiet_ho_khau c, khai_sinh d"
				+ " WHERE"
				+ " b.SO_CC = c.SO_CC and c.SO_KS = d.SO_KS"
				+ " and a.DA_LAM = '1'"
				+ " and a.DA_TRA = '0'"
				+ " and a.SO_CC = b.SO_CC"
				+ " and (a.MA_SO like '%"+tuKhoa+"%' OR a.SO_CC like '%"+tuKhoa+"%')"
				+ " and a.NOI_TRA = '"+session.getAttribute("ssCoQuan")+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TheCMT> dsLamThe = jdbcTemplate.query(sql, new TheCMTRowMapper());
		return dsLamThe;
	}
	@Override
	public List<TheCMT> timKiemThe(String tuKhoa) {
		String sql = "SELECT * FROM danh_sach_lam_the a, cccd b, chi_tiet_ho_khau c, khai_sinh d"
				+ " WHERE"
				+ " b.SO_CC = c.SO_CC and c.SO_KS = d.SO_KS"
				+ " and a.DA_LAM = '1'"
				+ " and a.DA_TRA = '1'"
				+ " and a.SO_CC = b.SO_CC"
				+ " and (a.MA_SO like '%"+tuKhoa+"%' OR a.SO_CC like '%"+tuKhoa+"%')"
				+ " and a.NOI_TRA = '"+session.getAttribute("ssCoQuan")+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TheCMT> dsLamThe = jdbcTemplate.query(sql, new TheCMTRowMapper());
		return dsLamThe;
	}
}
