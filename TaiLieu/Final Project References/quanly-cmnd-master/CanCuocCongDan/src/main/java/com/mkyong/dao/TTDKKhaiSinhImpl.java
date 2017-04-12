package com.mkyong.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.Chung.TTDKKhaiSinhRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mkyong.services.KhaiSinhService;
import com.mkyong.services.QuyenService;

import Constant.Const;
import bean.Chung.TTDKKhaiSinh;
import bean.Chung.TaiKhoan;

public class TTDKKhaiSinhImpl implements TTDKKhaiSinhDao {

	/**.
	 * {@dataSourcce} dataSource
	 */
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private QuyenService quyenService;
	@Autowired
	private KhaiSinhService khaiSinhService;
	
	public TTDKKhaiSinhImpl() {
		super();
	}

	
	@Override
	public List<TTDKKhaiSinh> getDSTTDKKhaiSinh(String ngayHen, TaiKhoan tk, String tenQuyen, int soLuongTrongNgay) {
		int trangThai = 0;
		if (quyenService.kiemTraQuyenBangTen(tk.getUsername(), Const.DUYET_KHAI_SINH_2)) {
			trangThai = 1;
		}
		String sql = "SELECT * "
					+ "FROM "
						+ "ttdk_khai_sinh A, "
						+ "dan_toc B, "
						+ "yeu_cau C "
					+ "WHERE "
						+ "A.MA_DT = B.MA_DT "
						+ "AND A.MA_YEU_CAU = C.MA_YEU_CAU "
						+ "AND NOI_DKLV = '" + tk.getCoQuan() + "' "
						+ "AND NGAY_HEN_LAM_VIEC = '" + ngayHen + "' "
						+ "AND TRANG_THAI = " + trangThai + " "
						+ "LIMIT 0 , " + soLuongTrongNgay;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKhaiSinh> dsTTDKKhaiSinh;
		dsTTDKKhaiSinh = jdbcTemplate.query(sql, new TTDKKhaiSinhRowMapper());
		return dsTTDKKhaiSinh;
	}

	@Override
	public Boolean themTTDKKhaiSinh(TTDKKhaiSinh ttdkKhaiSinh) {
		String sql = "INSERT INTO "
				+ "`ttdk_khai_sinh` ("
									+ "`HO_TEN`, `NGAY_SINH`, "
									+ "`GIOI_TINH`, `QUOC_TICH`, "
									+ "`MA_DT`, `BENH_VIEN`, "
									+ "`NOI_SINH`, `QUE_QUAN`, "
									+ "`SO_CC_NGUOI_YEU_CAU`, `QUAN_HE_VOI_NGUOI_KS`, "
									+ "`SO_CC_CHA`, `SO_CC_ME`, "
									+ "`NGAY_DANG_KY`, `NGAY_HEN_LAM_VIEC`, "
									+ "`NOI_DKLV`, `MA_YEU_CAU`, `SO_KS`, `SO_KS_CU`) "
				+ "VALUES (?, ?, ?, ?, "
						+ "?, ?, ?, ?, "
						+ "?, ?, ?, ?, "
						+ "?, ?, ?, "
						+ "?, ?, ?);";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = 0;
		i = jdbcTemplate.update(
				sql,
				new Object[] {ttdkKhaiSinh.getHoTen(), ttdkKhaiSinh.getNgaySinh(), 
						ttdkKhaiSinh.getGioiTinh(), ttdkKhaiSinh.getQuocTich(), 
						ttdkKhaiSinh.getDanToc().getMaDT(), ttdkKhaiSinh.getBenhVien(), 
						ttdkKhaiSinh.getNoiSinh(), ttdkKhaiSinh.getQueQuan(), 
						ttdkKhaiSinh.getSoCCNguoiYeuCau(), ttdkKhaiSinh.getQuanHeVoiNguoiKS(), 
						ttdkKhaiSinh.getSoCCCha(), ttdkKhaiSinh.getSoCCMe(), 
						ttdkKhaiSinh.getNgayDangKy(), ttdkKhaiSinh.getNgayHenLamViec(), 
						ttdkKhaiSinh.getNoiDKLV(), ttdkKhaiSinh.getYeuCau().getMaYeuCau(), 
						ttdkKhaiSinh.getSoKS(), ttdkKhaiSinh.getSoKSCu()
					}
				);
		if (i > 0) {
			System.out.println("Insert 1 row");
			return true;
		}
		return false;
	}
	
	@Override
	public TTDKKhaiSinh getHangCuoi() {
		String sql = "SELECT * "
					+ "FROM "
						+ "ttdk_khai_sinh A, "
						+ "dan_toc B, "
						+ "yeu_cau C "
					+ "WHERE "
					+ "A.MA_DT = B.MA_DT "
					+ "AND A.MA_YEU_CAU = C.MA_YEU_CAU "
					+ "ORDER BY SO_KS DESC "
					+ "LIMIT 1";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKhaiSinh> dsTTDKKhaiSinh;
		dsTTDKKhaiSinh = jdbcTemplate.query(sql, new TTDKKhaiSinhRowMapper());
		return dsTTDKKhaiSinh.get(0);
	}

	@Override
	public TTDKKhaiSinh getTTDKKhaiSinhBangSoKS(String soKS) {
		String sql = "SELECT * "
					+ "FROM "
						+ "ttdk_khai_sinh A, "
						+ "dan_toc B, "
						+ "yeu_cau C "
					+ "WHERE "
						+ "A.MA_DT = B.MA_DT "
						+ "AND A.MA_YEU_CAU = C.MA_YEU_CAU "
						+ "AND A.SO_KS = '" + soKS + "'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKhaiSinh> dsTTDKKhaiSinh;
		dsTTDKKhaiSinh = jdbcTemplate.query(sql, new TTDKKhaiSinhRowMapper());
		if (dsTTDKKhaiSinh.isEmpty()) {
			System.out.println("Danh sach dk khai sinh null Impl");
			return null;
		}
		return dsTTDKKhaiSinh.get(0);
	}

	@Override
	public Boolean capNhatTTDKKhaiSinh(TTDKKhaiSinh ttdk) {
		String sql = "UPDATE ttdk_khai_sinh "
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
				+ "WHERE SO_KS = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int kt = jdbcTemplate.update(
				sql, 
				new Object[] {ttdk.getHoTen(), ttdk.getNgaySinh(), ttdk.getGioiTinh(), 
						ttdk.getQuocTich(), ttdk.getDanToc().getMaDT(), ttdk.getBenhVien(), 
						ttdk.getNoiSinh(), ttdk.getQueQuan(), ttdk.getSoCCNguoiYeuCau(), 
						ttdk.getQuanHeVoiNguoiKS(), ttdk.getSoCCCha(), ttdk.getSoCCMe(), 
						ttdk.getSoKS()});
		if(kt == 1) {
			System.out.println("Da update mot row");
			return true;
		}
		return false;
	}

	@Override
	public Boolean xoaTTDKKhaiSinh(String soKS) {
		String sql = "DELETE "
				+ "FROM ttdk_khai_sinh "
				+ "WHERE SO_KS = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
							sql,
							new Object[] {soKS});
		if (i > 0) {
			System.out.println("delete 1 row");
			return true;
		}
		return false;
	}

	@Override
	public Boolean capNhatTrangThai(String soKS, int trangThai, String ghiChu) {
		String sql = "UPDATE ttdk_khai_sinh "
					+ "SET TRANG_THAI = ?, GHI_CHU = ? "
					+ "WHERE SO_KS = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
							sql,
							new Object[] {trangThai, ghiChu, soKS});
		if (i > 0) {
			System.out.println("Cap nhat trang thai thanh cong.");
			return true;
		}
		return false;
	}

	@Override
	public List<TTDKKhaiSinh> getDSTTDKKhaiSinhBangHoTen(TaiKhoan tk, String hoTen) {
		String sql = "SELECT * "
				+ "FROM "
					+ "ttdk_khai_sinh A, "
					+ "dan_toc B, "
					+ "yeu_cau C "
				+ "WHERE "
					+ "A.MA_DT = B.MA_DT "
					+ "AND A.MA_YEU_CAU = C.MA_YEU_CAU "
					+ "AND A.HO_TEN like '%" + hoTen + "%' "
					+ "AND A.NOI_DKLV = '" + tk.getCoQuan() + "' ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKhaiSinh> dsTTDKKhaiSinh;
		dsTTDKKhaiSinh = jdbcTemplate.query(sql, new TTDKKhaiSinhRowMapper());
		if (dsTTDKKhaiSinh.isEmpty()) {
			System.out.println("Danh sach dk khai sinh null Impl");
			return null;
		}
		return dsTTDKKhaiSinh;
	}

	@Override
	public List<TTDKKhaiSinh> getDSTTDKKhaiSinhBangSoCC(TaiKhoan tk, String soCC) {
		String sql = "SELECT * "
				+ "FROM "
					+ "ttdk_khai_sinh A, "
					+ "dan_toc B, "
					+ "yeu_cau C "
				+ "WHERE "
					+ "A.MA_DT = B.MA_DT "
					+ "AND A.MA_YEU_CAU = C.MA_YEU_CAU "
					+ "AND (A.SO_KS = " + soCC + " "
							+ "OR A.SO_CC_NGUOI_YEU_CAU = '" + soCC + "' "
							+ "OR A.SO_CC_CHA = '" + soCC + "' "
							+ "OR A.SO_CC_ME = '" + soCC + "' "
							+ "OR A.SO_KS = " + soCC + ") "
					+ "AND A.NOI_DKLV = '" + tk.getCoQuan() + "' ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKhaiSinh> dsTTDKKhaiSinh;
		dsTTDKKhaiSinh = jdbcTemplate.query(sql, new TTDKKhaiSinhRowMapper());
		if (dsTTDKKhaiSinh.isEmpty()) {
			System.out.println("Danh sach dk khai sinh null Impl");
			return null;
		}
		return dsTTDKKhaiSinh;
	}

	@Override
	public List<TTDKKhaiSinh> getDSTTDKKhaiSinhBangSoKS(TaiKhoan tk, String soKS, int trangThai) {
		String sql = "SELECT * "
					+ "FROM "
						+ "ttdk_khai_sinh A, "
						+ "dan_toc B, "
						+ "yeu_cau C "
					+ "WHERE "
						+ "A.MA_DT = B.MA_DT "
						+ "AND A.MA_YEU_CAU = C.MA_YEU_CAU "
						+ "AND A.TRANG_THAI = " + trangThai + " "
						+ "AND A.SO_KS = '" + soKS + "' "
						+ "AND A.NOI_DKLV = '" + tk.getCoQuan() + "' ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKhaiSinh> dsTTDKKhaiSinh;
		dsTTDKKhaiSinh = jdbcTemplate.query(sql, new TTDKKhaiSinhRowMapper());
		return dsTTDKKhaiSinh;
	}

	@Override
	public List<TTDKKhaiSinh> getDSTTDKKhaiSinhDaDuyet(String ngayHen,
			TaiKhoan tk, int soLuongTrongNgay) {
		String sql = "SELECT * "
				+ "FROM "
					+ "ttdk_khai_sinh A, "
					+ "dan_toc B, "
					+ "yeu_cau C, "
					+ "duyet_dkks D "
				+ "WHERE "
					+ "A.MA_DT = B.MA_DT "
					+ "AND A.MA_YEU_CAU = C.MA_YEU_CAU "
					+ "AND NOI_DKLV = '" + tk.getCoQuan() + "' "
					+ "AND NGAY_HEN_LAM_VIEC = '" + ngayHen + "' "
					+ "AND A.SO_KS = D.SO_KS "
					+ "AND D.NGUOI_DUYET = '" + tk.getUsername() + "' "
					+ " LIMIT 0 , " + soLuongTrongNgay;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKhaiSinh> dsTTDKKhaiSinh;
		dsTTDKKhaiSinh = jdbcTemplate.query(sql, new TTDKKhaiSinhRowMapper());
		return dsTTDKKhaiSinh;
	}

	@Override
	public Boolean capNhatGhiChu(String soKS, String lyDo, TaiKhoan tk) {
		String sql = "UPDATE duyet_dkks "
					+ "SET GHI_CHU = '" + lyDo + "' "
					+ "WHERE SO_KS = '" + soKS + "' "
							+ "AND NGUOI_DUYET = '" + tk.getUsername() + "' ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(sql);
		if (i > 0) {
			return true;
		}
		return false;
	}
	@Override
	public TTDKKhaiSinh layHangCuoi() {
		String sql = "SELECT * "
					+ "FROM "
						+ "ttdk_khai_sinh A, "
						+ "dan_toc B,  "
						+ "yeu_cau C "
					+ "WHERE "
					+ "A.MA_DT = B.MA_DT "
					+ "AND A.MA_YEU_CAU = C.MA_YEU_CAU "
					+ "ORDER BY SO_KS DESC "
					+ "LIMIT 1";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKhaiSinh> ttdkKhaiSinh;
		ttdkKhaiSinh = jdbcTemplate.query(sql, new TTDKKhaiSinhRowMapper());
		if (ttdkKhaiSinh.isEmpty()) {
			return null;
		}
		return ttdkKhaiSinh.get(0);
	}
	@Override
	public String taoSoKS() {
		TTDKKhaiSinh ttdkKhaiSinh = layHangCuoi();
		String soKS =  "";
		if (ttdkKhaiSinh == null) {
			soKS = khaiSinhService.taoSoKS();
		} else {
			soKS = ttdkKhaiSinh.getSoKS();
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
	public List<TTDKKhaiSinh> getDSTTDKKhaiSinhBiTuChoi(TaiKhoan tk,
			int soLuongTrongNgay) {
		String sql = "SELECT * "
				+ "FROM "
					+ "ttdk_khai_sinh A, "
					+ "dan_toc B, "
					+ "yeu_cau C "
				+ "WHERE "
					+ "A.MA_DT = B.MA_DT "
					+ "AND A.MA_YEU_CAU = C.MA_YEU_CAU "
					+ "AND NOI_DKLV = '" + tk.getCoQuan() + "' "
					+ "AND (TRANG_THAI = 3 OR TRANG_THAI = 4) "
					+ "LIMIT 0 , " + soLuongTrongNgay;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<TTDKKhaiSinh> dsTTDKKhaiSinh;
		dsTTDKKhaiSinh = jdbcTemplate.query(sql, new TTDKKhaiSinhRowMapper());
		return dsTTDKKhaiSinh;
	}
	

}
