package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.DanSoXaRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Chung.DanSoXa;

public class DanSoXaImpl implements DanSoXaDao {

	/**.
	 * {@dataSourcce} DataSource
	 */
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<DanSoXa> getDanSoXaBangMaTinh(String maTinh, int nam) {
		//SQL de create view DAN_SO_TINH
		String sql1 = "CREATE OR REPLACE VIEW dan_so_xa AS "
					+ "SELECT X.MA_XA, X.TEN_XA, COUNT( * ) AS DAN_SO "
					+ "FROM KHAI_SINH K, XA X, huyen H, TINH T "
					+ "WHERE NOI_CAP = X.MA_XA "
							+ "AND X.MA_HUYEN = H.MA_HUYEN "
							+ "AND H.MA_TINH = T.MA_TINH "
							+ "AND T.MA_TINH = '" + maTinh + "' "
							+ "AND SUBSTRING(NGAY_SINH, 7, 10) <= " + nam + " "
					+ "GROUP BY X.MA_XA "
					+ "ORDER BY X.MA_XA ASC "
					+ "LIMIT 0 , 30; "; //Ket thuc cau lenh creat view
		String sql2 = "SELECT "
						+ "MA_XA, "
						+ "TEN_XA, "
						+ "DAN_SO, "
						+ "((DAN_SO / (SELECT SUM( DAN_SO ) FROM dan_so_xa)) * 100) AS TY_LE_PHAN_TRAM "
					+ "FROM dan_so_xa; ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql1);
		List<DanSoXa> dsDanSoXa = jdbcTemplate.query(sql2, new DanSoXaRowMapper());
		return dsDanSoXa;
	}

	@Override
	public List<DanSoXa> getDanSoXaBangMaHuyen(String maHuyen, int nam) {
		//SQL de create view DAN_SO_TINH
		String sql1 = "CREATE OR REPLACE VIEW dan_so_xa AS "
					+ "SELECT X.MA_XA, X.TEN_XA, COUNT( * ) AS DAN_SO "
					+ "FROM khai_sinh K, xa X, huyen H, tinh T "
					+ "WHERE NOI_CAP = X.MA_XA "
							+ "AND X.MA_HUYEN = H.MA_HUYEN "
							+ "AND H.MA_HUYEN = '" + maHuyen + "' "
							+ "AND SUBSTRING(NGAY_SINH, 7, 10) <= " + nam + " "
					+ "GROUP BY X.MA_XA "
					+ "ORDER BY X.MA_XA ASC "
					+ "LIMIT 0 , 30; "; //Ket thuc cau lenh creat view
		String sql2 = "SELECT "
						+ "MA_XA, "
						+ "TEN_XA, "
						+ "DAN_SO, "
						+ "((DAN_SO / (SELECT SUM( DAN_SO ) FROM dan_so_xa)) * 100) AS TY_LE_PHAN_TRAM "
					+ "FROM dan_so_xa; ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql1);
		List<DanSoXa> dsDanSoXa = jdbcTemplate.query(sql2, new DanSoXaRowMapper());
		return dsDanSoXa;
	}

}
