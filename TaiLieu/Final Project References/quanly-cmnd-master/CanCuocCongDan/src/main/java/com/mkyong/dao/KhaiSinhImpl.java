package com.mkyong.dao;

import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.KhaiSinhRowMapper;
import jdbc.Chung.TTDKKhaiSinhRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mkyong.services.function.FunctionService;

import Constant.Const;
import bean.Chung.KhaiSinh;
import bean.Chung.TTDKKhaiSinh;
import bean.Chung.TaiKhoan;

public class KhaiSinhImpl implements KhaiSinhDao {
	
	/**.
	 * {@dataSourcce} dataSource
	 */
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private FunctionService functionService;
	
	
	public KhaiSinhImpl() {
		super();
	}

	@Override
	public List<KhaiSinh> getDSKhaiSinh(TaiKhoan tk, String ngayHienTai) {
		String sql = "SELECT "
						+ "K.SO_KS, "
						+ "K.HO_TEN, "
						+ "K.NGAY_SINH, "
						+ "K.GIOI_TINH, "
						+ "K.QUOC_TICH, "
						+ "K.MA_DT, "
						+ "D.TEN_DT, "
						+ "K.BENH_VIEN, "
						+ "K.NOI_SINH, "
						+ "K.QUE_QUAN, "
						+ "K.SO_CC_NGUOI_YEU_CAU, "
						+ "K.QUAN_HE_VOI_NGUOI_KS, "
						+ "K.SO_CC_CHA, "
						+ "K.SO_CC_ME, "
						+ "K.NOI_CAP "
					+ "FROM "
						+ "khai_sinh K, "
						+ "dan_toc D, "
						+ "duyet_dkks E, "
						+ "tai_khoan T, "
						+ "quyen_tai_khoan Q, "
						+ "quyen Z "
					+ "WHERE "
						+ "K.MA_DT = D.MA_DT "
						+ "AND K.SO_KS = E.SO_KS "
						+ "AND E.NGAY_DUOC_DUYET = '" + ngayHienTai + "' "
						+ "AND K.NOI_CAP = '"  + tk.getCoQuan() + "' "
						+ "AND E.NGUOI_DUYET = T.USER_NAME "
						+ "AND T.USER_NAME = Q.USER_NAME "
						+ "AND Q.MA_QUYEN = Z.MA_QUYEN "
						+ "AND Z.TEN_QUYEN = '" + Const.DUYET_KHAI_SINH_2 + "' "
					+ "limit 0, 200; ";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<KhaiSinh> dsKhaiSinh;
		dsKhaiSinh = jdbcTemplate.query(sql, new KhaiSinhRowMapper());
		return dsKhaiSinh;
	}

	@Override
	public Boolean themGiayKhaiSinh(KhaiSinh ks) {
		String sql = "INSERT INTO  `khai_sinh` ("
						+ "`SO_KS` ,`HO_TEN` ,`NGAY_SINH` ,"
						+ "`GIOI_TINH` ,`QUOC_TICH` ,`MA_DT` ,"
						+ "`BENH_VIEN` ,`NOI_SINH` ,`QUE_QUAN` ,"
						+ "`SO_CC_NGUOI_YEU_CAU` ,`QUAN_HE_VOI_NGUOI_KS`, "
						+ "`SO_CC_CHA` ,`SO_CC_ME`, `NOI_CAP`) "
					+ "VALUES (?, ?, ?, "
							+ "?, ?, ?, "
							+ "?, ?, ?, "
							+ "?, ?, ?, "
							+ "?, ?);";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = 0;
		try {
			i = jdbcTemplate.update(
							sql,
							new Object[] {ks.getSoKS(), ks.getHoTen(), ks.getNgaySinh(), 
									ks.getGioiTinh(), ks.getQuocTich(), ks.getDanToc().getMaDT(), 
									ks.getBenhVien(), ks.getNoiSinh(), ks.getQueQuan(), 
									ks.getSoCCNguoiYeuCau(), ks.getQuanHeVoiNguoiKS(), 
									ks.getSoCCCha(), ks.getSoCCMe(), ks.getNoiCap()});
			
		} catch (InvalidResultSetAccessException e) {
			System.out.println("Loi exception.");
			throw new RuntimeException(e);
		} catch (DataAccessException e) {
			System.out.println("Loi exception.");
			throw new RuntimeException(e);
		}
		
		if (i > 0) {
			System.out.println("Insert 1 row");
			return true;
		}
		return false;
	}

	@Override
	public KhaiSinh getKhaiSinhBangSoKS(String soKS) {
		String sql = "SELECT "
					+ "K.SO_KS, "
					+ "K.HO_TEN, "
					+ "K.NGAY_SINH, "
					+ "K.GIOI_TINH, "
					+ "K.QUOC_TICH, "
					+ "K.MA_DT, "
					+ "D.TEN_DT, "
					+ "K.BENH_VIEN, "
					+ "K.NOI_SINH, "
					+ "K.QUE_QUAN, "
					+ "K.SO_CC_NGUOI_YEU_CAU, "
					+ "K.QUAN_HE_VOI_NGUOI_KS, "
					+ "K.SO_CC_CHA, "
					+ "K.SO_CC_ME, "
					+ "K.NOI_CAP "
				+ "FROM "
					+ "khai_sinh K, "
					+ "dan_toc D "
				+ "WHERE "
					+ "K.MA_DT = D.MA_DT "
					+ "AND K.SO_KS = '" + soKS + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<KhaiSinh> dsKhaiSinh;
		dsKhaiSinh = jdbcTemplate.query(sql, new KhaiSinhRowMapper());
		if(dsKhaiSinh.isEmpty()) {
			return null;
		}
		return dsKhaiSinh.get(0);
	}
	@Override
	public KhaiSinh layHangCuoi() {
		String sql = "SELECT * "
					+ "FROM "
						+ "khai_sinh A, dan_toc B "
					+ "WHERE A.MA_DT = B.MA_DT "
					+ "ORDER BY SO_KS DESC "
					+ "LIMIT 1";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<KhaiSinh> khaiSinh;
		khaiSinh = jdbcTemplate.query(sql, new KhaiSinhRowMapper());
		if (khaiSinh.isEmpty()) {
			return null;
		}
		return khaiSinh.get(0);
	}
	@Override
	public String taoSoKS() {
		KhaiSinh khaiSinh = layHangCuoi();
		String soKS =  "";
		if (khaiSinh == null) {
			soKS = "000000000001";
		} else {
			soKS = khaiSinh.getSoKS();
			Long num = Long.parseLong(soKS);
			num = num + 1;
			soKS = "" + num;
			if(soKS.length() < 12) {
				int sl = 12 - soKS.length();
				for(int i = 0 ; i < sl; i++){
					soKS = "0"+soKS;
				}
			}
		}
		return soKS;
	}

	@Override
	public Boolean capNhatKhaiSinh(KhaiSinh ks) {
		String sql = "UPDATE khai_sinh "
					+ "SET "
						+ "HO_TEN = ?, "
						+ "NGAY_SINH = ?, "
						+ "GIOI_TINH = ?,"
						+ "QUOC_TICH = ?,"
						+ "MA_DT = ?, "
						+ "BENH_VIEN = ?, "
						+ "NOI_SINH = ?, "
						+ "QUE_QUAN = ?, "
						+ "SO_CC_NGUOI_YEU_CAU = ?, "
						+ "QUAN_HE_VOI_NGUOI_KS = ?, "
						+ "SO_CC_CHA = ?, "
						+ "SO_CC_ME = ? "
						+ "NOI_CAP = ?"
					+ "WHERE SO_KS = '" + ks.getSoKS() + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int kt = jdbcTemplate.update(
				sql, 
				new Object[] {ks.getHoTen(), ks.getNgaySinh(), ks.getGioiTinh(), 
						ks.getQuocTich(), ks.getDanToc().getMaDT(), ks.getBenhVien(), 
						ks.getNoiSinh(), ks.getQueQuan(), ks.getSoCCNguoiYeuCau(), 
						ks.getQuanHeVoiNguoiKS(), ks.getSoCCCha(), ks.getSoCCMe(), 
						ks.getNoiCap()});
		if(kt == 1) {
			System.out.println("Da update mot row");
			return true;
		}
		return false;
	}

	@Override
	public List<KhaiSinh> tkKhaiSinhBangHoTen(TaiKhoan tk, String hoTen) {
		String sql = "SELECT * "
					+ "FROM "
						+ "khai_sinh K, "
						+ "dan_toc D "
					+ "WHERE "
						+ "K.MA_DT = D.MA_DT "
						+ "AND K.HO_TEN like '%" + hoTen + "%' "
						+ "AND K.NOI_CAP = '" + tk.getCoQuan() + "' ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<KhaiSinh> dsKhaiSinh;
		dsKhaiSinh = jdbcTemplate.query(sql, new KhaiSinhRowMapper());
		return dsKhaiSinh;
	}

	@Override
	public List<KhaiSinh> tkKhaiSinhBangSoKS(TaiKhoan tk, String soKS) {
		String sql = "SELECT * "
					+ "FROM "
						+ "khai_sinh K, "
						+ "dan_toc D "
					+ "WHERE "
						+ "K.MA_DT = D.MA_DT "
						+ "AND K.SO_KS = '" + soKS + "' "
						+ "AND K.NOI_CAP = '" + tk.getCoQuan() + "' ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<KhaiSinh> dsKhaiSinh;
		dsKhaiSinh = jdbcTemplate.query(sql, new KhaiSinhRowMapper());
		return dsKhaiSinh;
	}
	
	@Override
	public List<KhaiSinh> DSKhaiSinhBangMaHK(String soHK) {
		String sql = "SELECT * "			
				+ "FROM "
					+ "khai_sinh K, dan_toc D, chi_tiet_ho_khau C "
				+ "WHERE  "
					+ "K.MA_DT = D.MA_DT "
					+ "AND C.SO_KS = K.SO_KS "
					+ "AND C.SO_HK = '" + soHK + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<KhaiSinh> dsKhaiSinh = jdbcTemplate.query(sql, new KhaiSinhRowMapper());
		
		return dsKhaiSinh;
	}
	
	@Override
	public Boolean ktFullSoDonDKTrongNgay(Calendar ngay, String coQuan,
				int soDonTrongNgay) {
		String ngayHen = ngay.get(Calendar.DAY_OF_MONTH) + "-" + (ngay.get(Calendar.MONTH) + 1) + "-" + ngay.get(Calendar.YEAR);
		String sql = "SELECT * "
					+ "FROM ttdk_khai_sinh s, dan_toc d, yeu_cau y "
					+ "WHERE "
						+ "s.MA_DT = d.MA_DT "
						+ "AND s.MA_YEU_CAU = y.MA_YEU_CAU "
						+ "AND NGAY_HEN_LAM_VIEC = '" + ngayHen + "' "
						+ "AND QUE_QUAN = '" + coQuan + "' "
						+ "LIMIT 0 , 200";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKhaiSinh> dsKhaiSinh = jdbcTemplate.query(sql, new TTDKKhaiSinhRowMapper());
		if (dsKhaiSinh.size() > soDonTrongNgay) {
			return true;
		}
		return false;
	}

	@Override
	public String taoNgayHen(String ngayDangKy, String coQuan,
			int soDonTrongNgay) {
		
		String[] temp = ngayDangKy.split("-");
		Calendar ngayHen = Calendar.getInstance();
		ngayHen.set(Calendar.DAY_OF_MONTH, Integer.valueOf(temp[0]) + 5);
		ngayHen.set(Calendar.MONTH, Integer.valueOf(temp[1]) - 1);
		ngayHen.set(Calendar.YEAR, Integer.valueOf(temp[2]));
		
		while(ktFullSoDonDKTrongNgay(ngayHen, coQuan, soDonTrongNgay) || functionService.ktNgayNghi(ngayHen)) {
			ngayHen.set(Calendar.DAY_OF_MONTH, ngayHen.get(Calendar.DAY_OF_MONTH) + 1);
		}
		String ngay = String.valueOf(ngayHen.get(Calendar.DAY_OF_MONTH));
		if (ngayHen.get(Calendar.DAY_OF_MONTH) < 10) {
			ngay = "0" + ngayHen.get(Calendar.DAY_OF_MONTH);
		}
		return (ngay + "-" + (ngayHen.get(Calendar.MONTH) + 1) + "-" + ngayHen.get(Calendar.YEAR));
	}

	@Override
	public KhaiSinh getNoiCapBangSoKS(String soKS) {
		String sql = "SELECT * FROM"
				+ " khai_sinh A,"
				+ " dan_toc B"
				+ " WHERE"
				+ " SO_KS = '"+soKS+"'"
				+ " AND A.MA_DT = B.MA_DT";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<KhaiSinh> dsKhaiSinh = jdbcTemplate.query(sql, new KhaiSinhRowMapper());
		if (dsKhaiSinh.isEmpty()) {
			return null;
		}
		return dsKhaiSinh.get(0);
	}

	@Override
	public List<KhaiSinh> tkKhaiSinhBangNgayDuocDuyet(TaiKhoan tk, String ngayDuocDuyet) {
		String sql = "SELECT * "
				+ "FROM "
					+ "khai_sinh K, "
					+ "dan_toc D, "
					+ "duyet_dkks E "
				+ "WHERE "
					+ "K.MA_DT = D.MA_DT "
					+ "AND K.SO_KS = E.SO_KS "
					+ "AND E.NGAY_DUOC_DUYET = '" + ngayDuocDuyet + "' "
					+ "AND K.NOI_CAP = '" + tk.getCoQuan() + "' ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<KhaiSinh> dsKhaiSinh;
		dsKhaiSinh = jdbcTemplate.query(sql, new KhaiSinhRowMapper());
		return dsKhaiSinh;
	}

	@Override
	public Boolean ktTonTaiSoKS(String soKS) {
		String sql = "SELECT * "
				+ "FROM "
					+ "khai_sinh K, "
					+ "dan_toc D "
				+ "WHERE "
					+ "K.MA_DT = D.MA_DT "
					+ "AND K.SO_KS = '" + soKS + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<KhaiSinh> dsKhaiSinh;
		dsKhaiSinh = jdbcTemplate.query(sql, new KhaiSinhRowMapper());
		if (dsKhaiSinh.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean xoaKhaiSinh(String soKS) {
		String sql = "DELETE FROM khai_sinh WHERE so_ks = '" + soKS + "' ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(sql);
		if (i > 0) {
			System.out.println("Xoa duyet dkks");
			return true;
			
		}
		return false;
	}

}
