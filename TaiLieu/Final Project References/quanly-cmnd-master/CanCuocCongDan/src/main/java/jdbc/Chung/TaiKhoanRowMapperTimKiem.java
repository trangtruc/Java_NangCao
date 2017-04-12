package jdbc.Chung;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Chung.TaiKhoan;

public class TaiKhoanRowMapperTimKiem implements RowMapper<TaiKhoan> {
	public TaiKhoan mapRow(ResultSet rs, int rowNum) throws SQLException {
		TaiKhoanExtractor taikhoan = new TaiKhoanExtractor();
		return taikhoan.extractDataTimKiem(rs);
	}
}
