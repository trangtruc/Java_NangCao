package com.mkyong.dao;

import java.util.List;

import bean.Config.ConfigEmail;

public interface ConfigEmailDao {
	List<ConfigEmail> getDSConfigEmail();
	ConfigEmail getConfigEmail(String maMail);
	Boolean insertConfigEmail(ConfigEmail cmail);
	Boolean updateConfigEmail(ConfigEmail cmail);
	Boolean deleteConfigEmail(String maMail);
}
