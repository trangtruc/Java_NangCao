package com.mkyong.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkyong.dao.ConfigCCCDDao;

import bean.Config.ConfigCCCD;

public class ConfigCCCDServiceImpl implements ConfigCCCDService{
	@Autowired(required=true)
	private ConfigCCCDDao ccDao;
	@Override
	public ConfigCCCD getConfigCCCD() {
		// TODO Auto-generated method stub
		return ccDao.getConfigCCCD();
	}

	@Override
	public Boolean updateConfigCCCD(ConfigCCCD configCCCD) {
		// TODO Auto-generated method stub
		return ccDao.updateConfigCCCD(configCCCD);
	}

}
