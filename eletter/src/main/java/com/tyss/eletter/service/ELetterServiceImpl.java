package com.tyss.eletter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.eletter.dao.ELetterDAO;
import com.tyss.eletter.dto.HRInfoBean;
import com.tyss.eletter.exceptions.PasswordInvalidException;
import com.tyss.eletter.util.FieldValidation;

@Service

public class ELetterServiceImpl implements ELetterService{

	@Autowired
	private ELetterDAO dao;
	
	
	
	@Override
	public boolean register(HRInfoBean hrInfoBean) {
		 FieldValidation validationForPassword = new FieldValidation();
		if (validationForPassword.passwordValidtion(hrInfoBean.getPassword())) {
			return dao.register(hrInfoBean);
		}
		else {
			return false;
		}
		
	}

	@Override
	public HRInfoBean auth(String email, String password) {
		FieldValidation validationForPassword = new FieldValidation();
		if (validationForPassword.passwordValidtion(password)) {
			return dao.auth(email, password);
		}
		else {
			return null;
		}
	}

	@Override
	public boolean changePassword(String email, String password) {
		FieldValidation validationForPassword = new FieldValidation();
		if (validationForPassword.passwordValidtion(password)) {
			return dao.changePassword(email, password);
		}
		else {
			return false;
		}
		
	}

	@Override
	public List<HRInfoBean> search(String name) {
		
		return dao.search(name);
	}

	@Override
	public List<HRInfoBean> gethrInfo(int id) {
		return null;
	}

	@Override
	public boolean deleteHRInfoBean(String email) {
		return dao.deleteHRInfoBean(email);
	}

}
