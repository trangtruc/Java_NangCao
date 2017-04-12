package jdbc.Config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Config.ConfigEmail;

public class ConfigEmailRowMapper implements RowMapper<ConfigEmail>{
	@Override
	public ConfigEmail mapRow(ResultSet rs, int rowNum) throws SQLException {
		ConfigEmailExtractor ConfigEmailExt = new ConfigEmailExtractor();
		return ConfigEmailExt.extractData(rs);
	}
}
