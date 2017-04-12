package com.mkyong.dao;

import bean.Config.ConfigCCCD;

public interface ConfigCCCDDao {
	ConfigCCCD getConfigCCCD();
	Boolean updateConfigCCCD(ConfigCCCD configCCCD);
}
