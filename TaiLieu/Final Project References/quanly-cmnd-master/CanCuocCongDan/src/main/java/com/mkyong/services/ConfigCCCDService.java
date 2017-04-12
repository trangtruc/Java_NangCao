package com.mkyong.services;

import bean.Config.ConfigCCCD;

public interface ConfigCCCDService {
	ConfigCCCD getConfigCCCD();
	Boolean updateConfigCCCD(ConfigCCCD configCCCD);
}
