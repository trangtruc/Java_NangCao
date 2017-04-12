package com.mkyong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkyong.dao.NhomMauDao;

import bean.Chung.NhomMau;

public class NhomMauServiceImpl implements NhomMauService{
	/**.
	 * {@NhomMauDao} NhomMauDao
	 */
	@Autowired(required=true)
	private NhomMauDao nhomMauDao;
	@Override
	public List<NhomMau> getDSNhomMau(){
		return nhomMauDao.getDSNhomMau();
	}
	@Override
	public List<NhomMau> getNhomMauBangMa(String maNM){
		return nhomMauDao.getNhomMauBangMa(maNM);
	}
}
