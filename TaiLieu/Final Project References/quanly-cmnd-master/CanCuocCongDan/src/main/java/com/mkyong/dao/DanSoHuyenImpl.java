package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.DanSoHuyenRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Chung.DanSoHuyen;

public class DanSoHuyenImpl implements DanSoHuyenDao {
	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;
	@Override
	public List<DanSoHuyen> getDanSoHuyen(String maTinh, int nam) {
		//SQL de create view DAN_SO_TINH
		//QUE_QUAN -> NOI_CAP
		String sql1 = "CREATE OR REPLACE VIEW dan_so_huyen AS "
					+ "SELECT H.MA_HUYEN, H.TEN_HUYEN, COUNT( * ) AS DAN_SO "
					+ "FROM khai_sinh K, xa X, huyen H, tinh T "
					+ "WHERE NOI_CAP = X.MA_XA "
							+ "AND X.MA_HUYEN = H.MA_HUYEN "
							+ "AND H.MA_TINH = T.MA_TINH "
							+ "AND T.MA_TINH = '" + maTinh + "' "
							+ "AND SUBSTRING(NGAY_SINH, 7, 10) < " + nam + " "
					+ "GROUP BY H.MA_HUYEN "
					+ "ORDER BY H.MA_HUYEN ASC "
					+ "LIMIT 0 , 30; "; //Ket thuc cau lenh creat view
		String sql2 = "SELECT "
						+ "MA_HUYEN, "
						+ "TEN_HUYEN, "
						+ "DAN_SO, "
						+ "((DAN_SO / (SELECT SUM( DAN_SO ) FROM dan_so_huyen)) * 100) AS TY_LE_PHAN_TRAM "
					+ "FROM dan_so_huyen; ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql1);
		List<DanSoHuyen> dsDanSoHuyen = jdbcTemplate.query(sql2, new DanSoHuyenRowMapper());
		return dsDanSoHuyen;
	}
	@Override
	public DanSoHuyen getDanSoHuyenBangMaHuyen(String maHuyen, int nam) {
		String sql1 = "CREATE OR REPLACE VIEW dan_so_huyen AS "
				+ "SELECT H.MA_HUYEN, H.TEN_HUYEN, COUNT( * ) AS DAN_SO "
				+ "FROM khai_sinh K, xa X, huyen H "
				+ "WHERE NOI_CAP = X.MA_XA "
						+ "AND X.MA_HUYEN = H.MA_HUYEN "
						+ "AND H.MA_HUYEN = '" + maHuyen + "' "
						+ "AND SUBSTRING(NGAY_SINH, 7, 10) <= " + nam + " "
				+ "GROUP BY H.MA_HUYEN "
				+ "ORDER BY H.MA_HUYEN ASC "
				+ "LIMIT 0 , 30; "; //Ket thuc cau lenh creat view
	String sql2 = "SELECT "
					+ "MA_HUYEN, "
					+ "TEN_HUYEN, "
					+ "DAN_SO, "
					+ "((DAN_SO / (SELECT SUM( DAN_SO ) FROM dan_so_huyen)) * 100) AS TY_LE_PHAN_TRAM "
				+ "FROM dan_so_huyen; ";
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	jdbcTemplate.update(sql1);
	List<DanSoHuyen> dsDanSoHuyen = jdbcTemplate.query(sql2, new DanSoHuyenRowMapper());
	if(dsDanSoHuyen.isEmpty()){
		return null;
	}
	return dsDanSoHuyen.get(0);
	}

}
