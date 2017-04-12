package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.DanSoTinhRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Chung.DanSoTinh;

public class DanSoTinhImpl implements DanSoTinhDao {

	/**.
	 * {@dataSourcce} dataSource
	 */
	
	@Autowired
	private DataSource dataSource;
	
	public DanSoTinhImpl() {
		super();
	}


	@Override
	public List<DanSoTinh> getDanSoTinh(int nam) {
		//SQL de create view DAN_SO_TINH
		String sql1 = "CREATE OR REPLACE VIEW dan_so_tinh AS "
					+ "SELECT T.MA_TINH, T.TEN_TINH, COUNT( * ) AS DAN_SO "
					+ "FROM khai_sinh K, xa X, huyen H, tinh T "
					+ "WHERE NOI_CAP = X.MA_XA "
							+ "AND X.MA_HUYEN = H.MA_HUYEN "
							+ "AND H.MA_TINH = T.MA_TINH "
							+ "AND SUBSTRING(NGAY_SINH, 7, 10) <= " + nam + " "
					+ "GROUP BY T.MA_TINH "
					+ "ORDER BY T.MA_TINH ASC "
					+ "LIMIT 0 , 64; "; //Ket thuc cau lenh creat view
		String sql2 = "SELECT "
						+ "MA_TINH, "
						+ "TEN_TINH, "
						+ "DAN_SO, "
						+ "((DAN_SO / (SELECT SUM( DAN_SO ) FROM dan_so_tinh)) * 100) AS TY_LE_PHAN_TRAM "
					+ "FROM dan_so_tinh; ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql1);
		List<DanSoTinh> dsDanSoTinh = jdbcTemplate.query(sql2, new DanSoTinhRowMapper());
		return dsDanSoTinh;
	}


	@Override
	public long getTongDanSo(int nam) {
		List<DanSoTinh> dsDanSoTinh = getDanSoTinh(nam);
		int soTinh = dsDanSoTinh.size();
		long tongDanSo = 0;
		for (int i = 0; i < soTinh; i++) {
			tongDanSo += dsDanSoTinh.get(i).getDanSo();
		}
		return tongDanSo;
	}

}
