package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.DuyetDKKSRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Chung.DuyetDKKS;

public class DuyetDKKSImpl implements DuyetDKKSDao {

	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;
	
	public DuyetDKKSImpl() {
		super();
	}

	@Override
	public Boolean themDuyetDKKS(DuyetDKKS duyetDKKS) {
		String sql = "INSERT INTO "
				+ "duyet_dkks(SO_DK, SO_KS, NGAY_DUOC_DUYET, NGUOI_DUYET, GHI_CHU) "
				+ "values (?, ?, ?, ?, ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(
							sql,
							new Object[] {duyetDKKS.getSoDK(), duyetDKKS.getSoKS(),
									duyetDKKS.getNgayDuocDuyet(), duyetDKKS.getNguoiDuyet(), 
									duyetDKKS.getGhiChu()}
							);
		if (i > 0) {
			System.out.println("Insert 1 row");
			return true;
		}
		return false;
	}

	@Override
	public Boolean capNhatSoKS(String soKS, String soDK, 
			String nguoiDuyet, String ngayDuyet) {
		String sql = "UPDATE duyet_dkks "
					+ "SET SO_KS = '" + soKS + "' "
					+ "	WHERE SO_DK = '" + soDK + "' "
							+ "AND NGUOI_DUYET = '" + nguoiDuyet + "' "
							+ "AND NGAY_DUOC_DUYET = '" + ngayDuyet + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//insert a user
		int i = jdbcTemplate.update(sql);
		if (i > 0) {
			System.out.println("Da update soKS trong duyet-dkks");
			return true;
		}
		return false;
	}

	@Override
	public DuyetDKKS getDuyetDKKS(String soKS) {
		String sql = "SELECT * "
					+ "FROM duyet_dkks "
					+ "WHERE SO_KS = '" + soKS + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<DuyetDKKS> dsDuyetDKKS = jdbcTemplate.query(sql, new DuyetDKKSRowMapper());
		return dsDuyetDKKS.get(0);
	}

	@Override
	public Boolean xoaDuyetDKKSBangSoKS(String soKS) {
		String sql = "DELETE FROM duyet_dkks WHERE so_ks = '" + soKS + "' ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(sql);
		if (i > 0) {
			System.out.println("Xoa duyet dkks");
			return true;
			
		}
		return false;
	}

	@Override
	public Boolean xoaDuyetDKKSBangSoKS1(String soKS, String nguoiDuyet) {
		String sql = "DELETE FROM duyet_dkks WHERE so_ks = '" + soKS + "' AND NGUOI_DUYET = '" + nguoiDuyet + "' ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(sql);
		if (i > 0) {
			System.out.println("Xoa duyet dkks 1");
			return true;
			
		}
		return false;
	}

}
