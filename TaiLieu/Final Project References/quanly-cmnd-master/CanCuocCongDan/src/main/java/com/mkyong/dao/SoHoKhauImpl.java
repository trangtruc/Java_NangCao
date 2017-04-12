package com.mkyong.dao;

import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import jdbc.An.ChiTietSoHoKhauRowMapper;
import jdbc.An.DSDangKyThemNhanKhauRowMapper;
import jdbc.An.DSLamHoKhauRowMapper;
import jdbc.An.DSQuanHeRowMapper;
import jdbc.An.DangKyThemNhanKhauRowMapper;
import jdbc.An.LienKetDuLieuRowMapper;
import jdbc.An.SoHoKhauRowMapper1;
import jdbc.Vu.SoHoKhauRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mkyong.dao.SoHoKhauDao;
import com.mkyong.services.function.FunctionService;

import bean.An.DSLamHoKhau;
import bean.An.DSQuanHe;
import bean.An.DSThemNhanKhau;
import bean.An.LienKetDuLieu;
import bean.An.SoHoKhau;

public class SoHoKhauImpl implements SoHoKhauDao {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private FunctionService functionService;
	
	public SoHoKhauImpl() {
		super();
	}
	
	@Override
	public List<DSQuanHe> dsQuanHe() {
		String sql = "SELECT * FROM config_quan_he" ;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<DSQuanHe> dsQuanHe = jdbcTemplate.query(sql, new DSQuanHeRowMapper());
		return dsQuanHe;
	}
	
	@Override
	public String layTenQuanHe(String quanHe) {
		String sql = "SELECT * FROM config_quan_he where quan_he='"+quanHe+"'" ;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<DSQuanHe> tenQuanHe = jdbcTemplate.query(sql, new DSQuanHeRowMapper());
		return tenQuanHe.get(0).getTenQuanHe();
	}
	
	@Override
	public List<SoHoKhau> getSoHoKhauBangMa(String soHK) {
		String sql = "SELECT * FROM chi_tiet_ho_khau A, so_ho_khau B WHERE A.so_hk = "+soHK+" AND B.so_hk = "+soHK;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<SoHoKhau> dsSoHoKhau = jdbcTemplate.query(sql, new ChiTietSoHoKhauRowMapper());
		if(dsSoHoKhau.isEmpty()){
			return null;
		}
		return dsSoHoKhau;
	}
	
	@Override
	public Boolean themSoHoKhau(String maSo, String diaChi, String coQuan) {
			String sql = "INSERT INTO so_ho_khau(so_hk, dia_chi, ma_tinh) VALUES(?, ?, ?)";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			int i = jdbcTemplate.update(
					sql,
					new Object[] {maSo, diaChi, coQuan});
			
			
			if (i > 0) {
				System.out.println("Insert 1 row into so_ho_khau");
				return true;
			}
			
			return false;
	}
	
	@Override
	public Boolean themChiTietSoHoKhau(String soHK, String soCC, String soKS, String quanHe, String ngayChuyenDen) {
		// TODO Auto-generated method stub
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql2 = "INSERT INTO chi_tiet_ho_khau(so_hk, so_cc, so_ks, quan_he, ngay_chuyen_den) VALUES(?, ?, ?, ?, ?)";
		int J = jdbcTemplate.update (sql2, new Object[] {soHK, soCC, soKS, quanHe, ngayChuyenDen});
		if (J>0 ) {
			System.out.println("Insert 1 row into chi_tiet_ho_khau ");
			return true;
		}
			return null;
	}
	
	
	@Override
	public Boolean xoaHoKhau(String soHK) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "DELETE FROM so_ho_khau WHERE so_hk = ?";
		int J = jdbcTemplate.update (sql, new Object[] {soHK});
		
		if (J>0 ) {
			return true;
		}
		return null;
	}
	
	
	@Override
	public List<SoHoKhau> dsSoHoKhau(String coQuan) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM so_ho_khau WHERE ma_tinh ='"+coQuan+"' AND so_hk != 000000000000 " ;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<SoHoKhau> dsSoHoKhau ;
		dsSoHoKhau = jdbcTemplate.query(sql, new SoHoKhauRowMapper1());
		
		return dsSoHoKhau;
	}
	
	@Override
	public SoHoKhau layHangCuoi() {
		String sql = "SELECT * from so_ho_khau ORDER BY so_hk DESC LIMIT 1";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<SoHoKhau> soHoKhau ;
		soHoKhau = jdbcTemplate.query(sql, new SoHoKhauRowMapper1());
		return soHoKhau.get(0);
	}
	@Override
	public String taoMaSoHK() {
		String soHK = layHangCuoi().getSoHK();
		//String soHK = "000000000001";
		Long num = Long.parseLong(soHK);
		num = num + 1;
		soHK = "" + num;
		if(soHK.length() < 12) {
			int sl = 12 - soHK.length();
			for(int i = 0 ; i < sl; i++){
				soHK = "0"+soHK;
			}
		}
		return soHK;
	}
	
	@Override
	public String taoNgayHen(String ngayDK) {
		String[] temp = ngayDK.split("-");
		Calendar ngayHen = Calendar.getInstance();
		ngayHen.set(Calendar.DAY_OF_MONTH, Integer.valueOf(temp[0]) + 5);
		ngayHen.set(Calendar.MONTH, Integer.valueOf(temp[1]) - 1);
		ngayHen.set(Calendar.YEAR, Integer.valueOf(temp[2]));
		while(functionService.ktNgayNghi(ngayHen)) {
			ngayHen.set(Calendar.DAY_OF_MONTH, ngayHen.get(Calendar.DAY_OF_MONTH) + 1);
		}
		String ngay = String.valueOf(ngayHen.get(Calendar.DAY_OF_MONTH));
		if (ngayHen.get(Calendar.DAY_OF_MONTH) < 10) {
			ngay = "0" + ngayHen.get(Calendar.DAY_OF_MONTH);
		}
		return ngay + "-" + (ngayHen.get(Calendar.MONTH)+1) + "-" + ngayHen.get(Calendar.YEAR);
	}
	
	public SoHoKhau getSoHoKhau(String where) {
		String sql = "SELECT * FROM chi_tiet_ho_khau A, so_ho_khau B WHERE A.so_hk = B.so_hk AND "+where;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<SoHoKhau> dsSoHoKhau = jdbcTemplate.query(sql, new ChiTietSoHoKhauRowMapper());
		if(dsSoHoKhau.isEmpty()){
			return null;
		}
		return dsSoHoKhau.get(0);
	}

	@Override
	public Boolean themDangKySoHoKhau(String soHKCu, String soCCNguoiDK, String diaChi,
			String soCC, String soKS, String quanHe, String noiDKLamViec, String duyet, String ngayDK, String ngayHen, String tinhTrang) {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "INSERT INTO dang_ky_tach_ho_khau(so_hk_cu, so_cc_nguoi_dk, dia_chi, so_ks_thanh_vien, so_cc_thanh_vien, quan_he, noi_dk_lam_viec, duyet, ngay_dk, ngay_hen, tinh_trang ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int J = jdbcTemplate.update (sql, new Object[] {soHKCu, soCCNguoiDK, diaChi, soKS, soCC, quanHe, noiDKLamViec, 0, ngayDK, ngayHen, tinhTrang});
			if(J>0){
				System.out.println("insert into SQL");
				return true;
			}
			return null;
	}

	@Override
	public List<DSLamHoKhau> dsLamHoKhauChuaDuyet(String noiDKLV) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String ngayHienTai = functionService.getNgayHienTai();
		String sql = "SELECT * FROM dang_ky_tach_ho_khau WHERE quan_he = 'chuho' and duyet = 0 and noi_dk_lam_viec = '"+noiDKLV+"' and ngay_hen = '"+ngayHienTai+"'";
		List<DSLamHoKhau> dsLamSoHoKhau;
		dsLamSoHoKhau = jdbcTemplate.query(sql, new DSLamHoKhauRowMapper());
		return dsLamSoHoKhau;
	}

	@Override
	public List<DSLamHoKhau> dsLamHoKhauKhongDuyet(String noiDKLV) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM dang_ky_tach_ho_khau WHERE quan_he = 'chuho' AND duyet = 4 and noi_dk_lam_viec ='"+noiDKLV+"'";
		List<DSLamHoKhau> dsLamSoHoKhau;
		dsLamSoHoKhau = jdbcTemplate.query(sql, new DSLamHoKhauRowMapper());
		return dsLamSoHoKhau;
	}
	
	@Override
	public List<DSLamHoKhau> dsLamHoKhauBangTuKhoa(String tuKhoa) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM dang_ky_tach_ho_khau WHERE (quan_he = 'chuho' AND so_hk_cu like '%"+tuKhoa+"%' AND duyet = '0') OR (quan_he = 'chuho' AND ma_so like '%"+tuKhoa+"%' AND duyet = '0')";
		List<DSLamHoKhau> dsLamSoHoKhau;
		dsLamSoHoKhau = jdbcTemplate.query(sql, new DSLamHoKhauRowMapper());
		return dsLamSoHoKhau;
	}
	
	@Override
	public List<DSLamHoKhau> dsLamHoKhauBangMa(String soCC) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM dang_ky_tach_ho_khau WHERE so_cc_nguoi_dk = '"+soCC+"'";
		List<DSLamHoKhau> dsLamSoHoKhau;
		dsLamSoHoKhau = jdbcTemplate.query(sql, new DSLamHoKhauRowMapper());
		return dsLamSoHoKhau;
	}

	@Override
	public Boolean duyetLamHoKhau(String soCCNguoiDK ) {
		String ngayDuyet = functionService.getNgayHienTai();
		String sql = "UPDATE dang_ky_tach_ho_khau SET duyet = 3, ngay_duyet = '"+ngayDuyet+"' WHERE so_cc_nguoi_dk = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {soCCNguoiDK});
		if (i > 0) {
			System.out.println("Update 1 row");
			return true;
		}
		return false;
	}

	@Override
	public Boolean chuyenHoKhau(String soHKMoi, String soKSThanhVien, String quanHe, String ngayChuyenDen, String tinhTrang) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql1 = "UPDATE chi_tiet_ho_khau SET so_hk = ?, quan_he = ?, ngay_chuyen_den = ?, tinh_trang = ? WHERE so_ks = ?";
		
		int i = jdbcTemplate.update(
				sql1,
				new Object[] {soHKMoi, quanHe, ngayChuyenDen, tinhTrang, soKSThanhVien});
		if (i > 0) {
			System.out.println("Update 1 row");
			return true;
		}
		return false;
	}

	// ham Vu handsome viet
	@Override
	public SoHoKhau getSoCCBangSoKS(String soKS) {
		String sql = "SELECT * FROM chi_tiet_ho_khau a,"
				+ " so_ho_khau b"
				+ " WHERE"
				+ " a.so_hk = b.so_hk"
				+ " and a.so_ks = "+soKS;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<SoHoKhau> dsSoHoKhau = jdbcTemplate.query(sql, new ChiTietSoHoKhauRowMapper());
		if(dsSoHoKhau.isEmpty()){
			return null;
		}
		return dsSoHoKhau.get(0);
	}
	@Override
	public SoHoKhau getSoKSBangSoCC(String soCC) {
		String sql = "SELECT * FROM chi_tiet_ho_khau a,"
				+ " so_ho_khau b"
				+ " WHERE"
				+ " a.so_hk = b.so_hk"
				+ " and a.so_cc = '"+soCC+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<SoHoKhau> dsSoHoKhau = jdbcTemplate.query(sql, new ChiTietSoHoKhauRowMapper());
		if(dsSoHoKhau.isEmpty()){
			return null;
		}
		return dsSoHoKhau.get(0);
	}
	@Override
	public Boolean updateSoCC(SoHoKhau shk) {
		String sql = "UPDATE chi_tiet_ho_khau SET"
				+ " so_cc = ?"
				+ " WHERE"
				+ " so_ks = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {shk.getSoCC(), shk.getSoKS()});
		
		
		if (i > 0) {
			System.out.println("cap nhat so can cuoc: "+shk.getSoCC());
			System.out.println("vao so khai sinh: "+shk.getSoKS());
			return true;
		}
		return false;
	}
	public Boolean khongDuyet(String soCCNguoiDK, String lydo) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql1 = "UPDATE dang_ky_tach_ho_khau SET DUYET = 4, LY_DO = ? WHERE SO_CC_NGUOI_DK = ?";
		
		int i = jdbcTemplate.update(
				sql1,
				new Object[] {lydo, soCCNguoiDK});
		if (i > 0) {
			System.out.println("Update 1 row");
			return true;
		}
		return false;
	}

	public Boolean dkThemThanhVien(String soHKCu, String soHKMoi,
			String soKS, String soCC, String quanHe, String ngayDK,String ngayHen, String noiDKLamViec, String tinhTrang) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 
		String sql = "INSERT INTO them_nhan_khau(so_hk_cu, so_hk_moi, so_ks, so_cc, quan_he, ngay_duyet, ngay_dang_ky, ngay_hen, noi_dk_lam_viec, nguoi_duyet, tinh_trang ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int J = jdbcTemplate.update (sql, new Object[] {soHKCu, soHKMoi, soKS, soCC, quanHe, "", ngayDK, ngayHen, noiDKLamViec,"", tinhTrang});
		if(J>0){
			System.out.println("insert into SQL");
			return true;
		}
		return false;
	}

	@Override
	public List<DSThemNhanKhau> dsDangKyThemNhanKhau(String noiDKLV) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT DISTINCT so_hk_moi, ly_do, duyet, ngay_hen "
				+ "FROM them_nhan_khau "
				+ "WHERE NOI_DK_LAM_VIEC ='"+noiDKLV+"'";
		List<DSThemNhanKhau> dsThemNhanKhau;
		dsThemNhanKhau = jdbcTemplate.query(sql, new DangKyThemNhanKhauRowMapper());
		return dsThemNhanKhau;
	}

	@Override
	public List<DSThemNhanKhau> dsNhanKhauDK(String soHKMoi) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM them_nhan_khau WHERE SO_HK_MOI ='"+soHKMoi+"'";
		List<DSThemNhanKhau> dsNhanKhauDK;
		dsNhanKhauDK = jdbcTemplate.query(sql, new DSDangKyThemNhanKhauRowMapper());
		return dsNhanKhauDK;
	}

	@Override
	public Boolean duyetThemNhanKhau(String duyet, String soHKMoi, String ngayDuyet, String nguoiDuyet, String lyDo) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql1 = "UPDATE them_nhan_khau SET DUYET = ?, NGAY_DUYET = ?, NGUOI_DUYET = ?, LY_DO = ? WHERE SO_HK_MOI = ?";
		
		int i = jdbcTemplate.update(
				sql1,
				new Object[] {duyet, ngayDuyet, nguoiDuyet, lyDo, soHKMoi});
		if (i > 0) {
			System.out.println("Update 1 row");
		}
		return null;
	}
			
	public List<SoHoKhau> getDSNhapKhaiSinh() {
		String sql = "SELECT * FROM chi_tiet_ho_khau WHERE (SO_CC != '' AND SO_CC != '000000000000')"
				+ "AND SO_KS = '' "
				+ "AND SO_HK = '000000000000'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<SoHoKhau> dsSoHoKhau = jdbcTemplate.query(sql, new SoHoKhauRowMapper());
		return dsSoHoKhau;
	}

	@Override
	public Boolean updateSoKS(SoHoKhau shk) {
		String sql = "UPDATE chi_tiet_ho_khau SET"
				+ " SO_KS = ?"
				+ " WHERE"
				+ " SO_CC = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {shk.getSoKS(), shk.getSoCC()});
		
		
		if (i > 0) {
			System.out.println("cap nhat so khai sinh: "+shk.getSoKS());
			System.out.println("vao so can cuoc: "+shk.getSoCC());
			return true;
		}
		return false;
	}

	@Override
	public List<DSThemNhanKhau> dsDangKyThemNhanKhauBangTuKhoa(String noiDKLV,
			String tuKhoa) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT DISTINCT SO_HK_MOI, LY_DO, DUYET FROM them_nhan_khau WHERE NOI_DK_LAM_VIEC = "+noiDKLV+" AND SO_HK_MOI like '%"+tuKhoa+"%'";
		List<DSThemNhanKhau> dsThemNhanKhau;
		dsThemNhanKhau = jdbcTemplate.query(sql, new DangKyThemNhanKhauRowMapper());
		return dsThemNhanKhau;
	}

	@Override
	public List<SoHoKhau> dsSoHoKhauBangTuKhoa(String noiDKLV, String tuKhoa) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM so_ho_khau WHERE ma_tinh = "+noiDKLV+" AND SO_HK like '%"+tuKhoa+"%'";
		List<SoHoKhau> dsHoKhau;
		dsHoKhau = jdbcTemplate.query(sql, new SoHoKhauRowMapper1());
		return dsHoKhau;
	}

	@Override
	public Boolean capNhatQuanHe(DSQuanHe dsQuanHe, String maQuanHe) {
		String ma = dsQuanHe.getQuanHe();
		String ten = dsQuanHe.getTenQuanHe();
		String sql = "UPDATE config_quan_he SET quan_he = ? , ten_quan_he = ? WHERE quan_he = ?" ;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {ma, ten, maQuanHe});
		// TODO Auto-generated method stub
		if (i > 0) {
			System.out.println("da cap nhat config_quan_he");
			return true;
		}
		return false;
	}

	@Override
	public Boolean themQuanHe(DSQuanHe dsQuanHe) {
		String ma = dsQuanHe.getQuanHe();
		String ten = dsQuanHe.getTenQuanHe();
		String sql = "INSERT INTO config_quan_he(quan_he, ten_quan_he) VALUES (?, ?)" ;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int i = jdbcTemplate.update(
				sql,
				new Object[] {ma, ten});
		// TODO Auto-generated method stub
		if (i > 0) {
			System.out.println("da cap nhat config_quan_he");
			return true;
		}
		return false;
	}

	@Override
	public Boolean xoaQuanHe(String maQuanHe) {
		String sql = "DELETE FROM config_quan_he WHERE quan_he = ?";
		JdbcTemplate J = new JdbcTemplate(dataSource);
		int i = J.update(
				sql,
				new Object[] {maQuanHe});
		if (i > 0) {
			System.out.println("da cap nhat config_quan_he");
			return true;
		}
		return false;
	}

	@Override
	public LienKetDuLieu thongTinCaNhan(String soKS) {
		String sql = "SELECT a.so_ks, c.so_cc, b.ma_so From khai_sinh a, ttdk_cccd b, cccd c where"
				+ " b.ma_so = c.ma_so"
				+ " AND a.so_ks = b.so_ks"
				+ " AND a.so_ks = "+soKS;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<LienKetDuLieu> lk = jdbcTemplate.query(sql, new LienKetDuLieuRowMapper());
		return lk.get(0);
	}

	@Override
	public Boolean capNhatTinhTrangSHK(String soKS, String tinhTrang) {
		String sql="Update chi_tiet_ho_khau SET tinh_trang = ? WHERE so_ks =?";
		JdbcTemplate J = new JdbcTemplate(dataSource);
		int i = J.update(
				sql,
				new Object[] {tinhTrang, soKS});
		if (i > 0) {
			System.out.println("da cap nhat");
			return true;
		}
		return false;
	}



	

	

	
}
