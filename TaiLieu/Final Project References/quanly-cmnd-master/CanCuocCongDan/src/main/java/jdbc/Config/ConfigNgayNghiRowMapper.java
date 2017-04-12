package jdbc.Config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Config.ConfigNgayNghi;


public class ConfigNgayNghiRowMapper implements RowMapper<ConfigNgayNghi> {
	@Override
	public ConfigNgayNghi mapRow(ResultSet rs, int rowNum) throws SQLException {
		ConfigNgayNghiExtractor ConfigNgayNghiExt = new ConfigNgayNghiExtractor();
		return ConfigNgayNghiExt.extractData(rs);
	}
}
