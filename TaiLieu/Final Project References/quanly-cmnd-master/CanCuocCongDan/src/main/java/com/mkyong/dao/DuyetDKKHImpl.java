package com.mkyong.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Chung.DuyetDKKH;

public class DuyetDKKHImpl implements DuyetDKKHDao {
	
	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;

	public DuyetDKKHImpl() {
		super();
	}


	@Override
	public Boolean themDuyetDKKH(DuyetDKKH duyetDKKH) {
		String sql = "INSERT INTO  `duyet_dkkh` ("
						+ "`SO_DK` , "
						+ "`NGAY_DUOC_DUYET` ,"
						+ "`NGUOI_DUYET` ,"
						+ "`GHI_CHU`) "
					+ "VALUES (?,  ?,  ?,  ?);";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {
						duyetDKKH.getSoDK(), duyetDKKH.getNgayDuocDuyet(), 
						duyetDKKH.getNguoiDuyet(), duyetDKKH.getGhiChu()
						});
		if (i > 0) {
			System.out.println("Insert 1 row");
			return true;
		}
		return false;
	}


	@Override
	public Boolean xoaDuyetDKKHBangSoDK(String soDK) {
		String sql = "DELETE FROM duyet_dkkh WHERE so_dk = '" + soDK + "' ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(sql);
		if (i > 0) {
			System.out.println("Xoa duyet dkkh 1");
			return true;
			
		}
		return false;
	}


	@Override
	public Boolean xoaDuyetDKKHBangSoDK(String soDK, String nguoiDuyet) {
		String sql = "DELETE FROM duyet_dkkh "
					+ "WHERE so_dk = '" + soDK + "' "
						+ "AND NGUOI_DUYET = '" + nguoiDuyet + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(sql);
		if (i > 0) {
			System.out.println("Xoa duyet dkkh 1");
			return true;
			
		}
		return false;
	}

}
