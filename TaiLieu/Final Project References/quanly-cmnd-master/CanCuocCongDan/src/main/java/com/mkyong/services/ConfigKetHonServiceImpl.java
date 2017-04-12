package com.mkyong.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkyong.dao.ConfigKetHonDao;


import bean.Config.ConfigKetHon;

public class ConfigKetHonServiceImpl implements ConfigKetHonService{
	@Autowired(required=true)
	private ConfigKetHonDao ckDao;
	@Override
	public ConfigKetHon getConfigKetHon() {
		// TODO Auto-generated method stub
		return ckDao.getConfigKetHon();
	}

	@Override
	public Boolean updateConfigKetHon(ConfigKetHon ck) {
		// TODO Auto-generated method stub
		return ckDao.updateConfigKetHon(ck);
	}

}
