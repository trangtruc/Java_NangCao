package jdbc.Config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Config.ConfigCCCD;

public class ConfigCCCDRowMapper implements RowMapper<ConfigCCCD>{
	@Override
	public ConfigCCCD mapRow(ResultSet rs, int rowNum) throws SQLException {
		ConfigCCCDExtractor ConfigCCCDExt = new ConfigCCCDExtractor();
		return ConfigCCCDExt.extractData(rs);
	}
}
