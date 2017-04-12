package jdbc.Config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Config.ConfigSoCC;

public class ConfigSoCCRowMapper implements RowMapper<ConfigSoCC>{

	@Override
	public ConfigSoCC mapRow(ResultSet rs, int rowNum) throws SQLException {
		ConfigSoCCExtractor ConfigSoCCExt = new ConfigSoCCExtractor();
		return ConfigSoCCExt.extractData(rs);
	}

}
