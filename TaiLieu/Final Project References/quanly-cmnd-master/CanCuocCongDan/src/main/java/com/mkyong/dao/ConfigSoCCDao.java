package com.mkyong.dao;

import java.util.List;

import bean.Config.ConfigSoCC;

public interface ConfigSoCCDao {
	Boolean updateConfigSoCC(ConfigSoCC csc);
	Boolean insertConfigSoCC(ConfigSoCC csc);
	Boolean deleteConfigSoCC(ConfigSoCC csc);
	List<ConfigSoCC> getDSConfigSoCC();
	ConfigSoCC getConfigSoCC();
}
