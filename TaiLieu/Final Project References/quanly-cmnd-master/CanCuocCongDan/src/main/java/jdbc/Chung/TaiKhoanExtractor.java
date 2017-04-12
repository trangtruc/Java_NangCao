package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import bean.Chung.TaiKhoan;
public class TaiKhoanExtractor {
	public TaiKhoan extractData(ResultSet rs) throws SQLException,
	DataAccessException {
		final String username = "USER_NAME";
		final String password = "PASSWORD";
		final String email = "EMAIL";
		final String soDienThoai = "SO_DIEN_THOAI";
		final String trangThai= "TRANG_THAI";
		final String coQuan = "CO_QUAN";
		final String hoTen = "HO_TEN";
		TaiKhoan taiKhoan = new TaiKhoan();
		taiKhoan.setUsername(rs.getString(username));
		taiKhoan.setPassword(rs.getString(password));
		taiKhoan.setEmail(rs.getString(email));
		taiKhoan.setSoDienThoai(rs.getString(soDienThoai));
		taiKhoan.setTrangThai(rs.getString(trangThai));
		taiKhoan.setCoQuan(rs.getString(coQuan));
		try{
		taiKhoan.setHoTen(rs.getString(hoTen));
		} catch(Exception e){
			taiKhoan.setHoTen("Administrator");
		}
		return  taiKhoan;
	}
	public TaiKhoan extractDataTimKiem(ResultSet rs) throws SQLException,
	DataAccessException {
		final String username = "USER_NAME";
		final String password = "PASSWORD";
		final String email = "EMAIL";
		final String soDienThoai = "SO_DIEN_THOAI";
		final String trangThai= "TRANG_THAI";
		final String coQuan = "CO_QUAN";
		final String hoTen = "HO_TEN";
		TaiKhoan taiKhoan = new TaiKhoan();
		taiKhoan.setUsername(rs.getString(username));
		taiKhoan.setPassword(rs.getString(password));
		taiKhoan.setEmail(rs.getString(email));
		taiKhoan.setSoDienThoai(rs.getString(soDienThoai));
		taiKhoan.setTrangThai(rs.getString(trangThai));
		taiKhoan.setCoQuan(rs.getString(coQuan));
		taiKhoan.setHoTen(rs.getString(hoTen));
		while(rs.next()){
			rs.getString(hoTen);
		}
		return  taiKhoan;
	}
}
