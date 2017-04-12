package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.DanSoVungRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Chung.DanSoVung;

public class DanSoVungImpl implements DanSoVungDao {
	/**.
	 * {@dataSourcce} dataSource
	 */
	@Autowired
	private DataSource dataSource;
	@Override
	public List<DanSoVung> getDSDanSoVung(int nam, int soVung) {
		//SQL de create view DAN_SO_TINH
		String sql1 = "CREATE OR REPLACE VIEW dan_so_vung AS "
					+ "SELECT V.MA_VUNG, V.TEN_VUNG, COUNT( * ) AS DAN_SO "
					+ "FROM khai_sinh K, xa X, huyen H, tinh T, vung V "
					+ "WHERE NOI_CAP = X.MA_XA "
							+ "AND X.MA_HUYEN = H.MA_HUYEN "
							+ "AND H.MA_TINH = T.MA_TINH "
							+ "AND T.MA_VUNG = V.MA_VUNG "
							+ "AND SUBSTRING(NGAY_SINH, 7, 10) < " + nam + " "
					+ "GROUP BY V.MA_VUNG "
					+ "ORDER BY V.MA_VUNG ASC "
					+ "LIMIT 0 , " + soVung ; //Ket thuc cau lenh creat view
		String sql2 = "SELECT "
						+ "MA_VUNG, "
						+ "TEN_VUNG, "
						+ "DAN_SO, "
						+ "((DAN_SO/(SELECT SUM( DAN_SO ) FROM dan_so_vung)) * 100) AS TY_LE_PHAN_TRAM "
					+ "FROM dan_so_vung; ";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql1);
		List<DanSoVung> dsDanSoVung = jdbcTemplate.query(sql2, new DanSoVungRowMapper());
		return dsDanSoVung;
	}

}
