package jdbc.Config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Config.ConfigKetHon;

public class ConfigKetHonRowMapper implements RowMapper<ConfigKetHon>{
	@Override
	public ConfigKetHon mapRow(ResultSet rs, int rowNum) throws SQLException {
		ConfigKetHonExtractor ConfigKetHonExt = new ConfigKetHonExtractor();
		return ConfigKetHonExt.extractData(rs);
	}
}
