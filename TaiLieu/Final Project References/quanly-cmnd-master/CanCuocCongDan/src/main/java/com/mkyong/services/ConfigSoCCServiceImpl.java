package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkyong.dao.ConfigSoCCDao;

import bean.Config.ConfigSoCC;

public class ConfigSoCCServiceImpl implements ConfigSoCCService{
	@Autowired(required=true)
	ConfigSoCCDao cscDao;
	@Override
	public Boolean updateConfigSoCC(ConfigSoCC csc) {
		// TODO Auto-generated method stub
		return cscDao.updateConfigSoCC(csc);
	}

	@Override
	public Boolean insertConfigSoCC(ConfigSoCC csc) {
		// TODO Auto-generated method stub
		return cscDao.insertConfigSoCC(csc);
	}

	@Override
	public Boolean deleteConfigSoCC(ConfigSoCC csc) {
		// TODO Auto-generated method stub
		return cscDao.deleteConfigSoCC(csc);
	}

	@Override
	public List<ConfigSoCC> getDSConfigSoCC() {
		// TODO Auto-generated method stub
		return cscDao.getDSConfigSoCC();
	}

	@Override
	public ConfigSoCC getConfigSoCC() {
		// TODO Auto-generated method stub
		return cscDao.getConfigSoCC();
	}

}
