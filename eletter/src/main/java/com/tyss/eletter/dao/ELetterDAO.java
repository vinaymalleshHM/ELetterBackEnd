package com.tyss.eletter.dao;

import java.util.List;

import com.tyss.eletter.dto.HRInfoBean;

public interface ELetterDAO {

	boolean register(HRInfoBean hrInfoBean);
	HRInfoBean auth(String email, String password);
	
	boolean changePassword(int id,String password);
	
	List<HRInfoBean> search(String name);
	List<HRInfoBean> gethrInfo(int id);
	
	
	boolean deleteHRInfoBean(int id);
	
}
