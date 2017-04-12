package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkyong.dao.ConfigEmailDao;

import bean.Config.ConfigEmail;

public class ConfigEmailServiceImpl implements ConfigEmailService{

	@Autowired(required=true)
	private ConfigEmailDao emailDao;
	@Override
	public List<ConfigEmail> getDSConfigEmail() {
		// TODO Auto-generated method stub
		return emailDao.getDSConfigEmail();
	}

	@Override
	public ConfigEmail getConfigEmail(String maMail) {
		// TODO Auto-generated method stub
		return emailDao.getConfigEmail(maMail);
	}

	@Override
	public Boolean insertConfigEmail(ConfigEmail cmail) {
		// TODO Auto-generated method stub
		return emailDao.insertConfigEmail(cmail);
	}

	@Override
	public Boolean updateConfigEmail(ConfigEmail cmail) {
		// TODO Auto-generated method stub
		return emailDao.updateConfigEmail(cmail);
	}

	@Override
	public Boolean deleteConfigEmail(String maMail) {
		// TODO Auto-generated method stub
		return emailDao.deleteConfigEmail(maMail);
	}

}
