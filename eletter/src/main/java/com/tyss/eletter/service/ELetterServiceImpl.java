package com.tyss.eletter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.eletter.dao.ELetterDAO;
import com.tyss.eletter.dto.HRInfoBean;

@Service

public class ELetterServiceImpl implements ELetterService{

	@Autowired
	private ELetterDAO dao;
	
	@Override
	public boolean register(HRInfoBean hrInfoBean) {
		return dao.register(hrInfoBean);
	}

	@Override
	public HRInfoBean auth(String email, String password) {
		return dao.auth(email, password);
	}

	@Override
	public boolean changePassword(int id, String password) {
		return dao.changePassword(id, password);
	}

	@Override
	public List<HRInfoBean> search(String name) {
		return dao.search(name);
	}

	@Override
	public List<HRInfoBean> gethrInfo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteHRInfoBean(int id) {
		return dao.deleteHRInfoBean(id);
	}

}
