package com.tyss.eletter.dao;

import java.time.temporal.TemporalQuery;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.tyss.eletter.dto.HRInfoBean;
import com.tyss.eletter.dto.HRRecieverPK;
import com.tyss.eletter.dto.RecieverInfoBean;
import com.tyss.eletter.exceptions.EmailAlreadyExistExeception;

@Repository
public class ELetterDAOImpl implements ELetterDAO{
	
	@PersistenceUnit
	private EntityManagerFactory factory;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public boolean register(HRInfoBean hrInfoBean) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		String jpql = "select hr from HRInfoBean hr where hr.email=:email";
		TypedQuery<HRInfoBean> beanQuery = manager.createQuery(jpql,HRInfoBean.class);
		beanQuery.setParameter("email", hrInfoBean.getEmail());
		
		try {
			
			HRInfoBean infoBean = beanQuery.getSingleResult();

			if (infoBean!=null) {
				HRInfoBean bean = manager.find(HRInfoBean.class, infoBean.getHId());
				bean.setHId(bean.getHId());
				bean.setRecieverInfoBean(hrInfoBean.getRecieverInfoBean());
				manager.persist(bean);
				transaction.commit();
				return true;
			} else {
				hrInfoBean.setPassword(encoder.encode(hrInfoBean.getPassword()));
				hrInfoBean.setActive(true);
				manager.persist(hrInfoBean);
				transaction.commit();
				return true;
			}
			
		} catch (Exception e) {
			for (StackTraceElement element : e.getStackTrace()) {
				System.out.println(element.toString());
			}
			throw new EmailAlreadyExistExeception("Email Already Exist");
		}
	}

	@Override
	public HRInfoBean auth(String email, String password) {
		EntityManager manager = factory.createEntityManager();
		String jpql ="select hr from HRInfoBean hr where hr.email=:email";
		TypedQuery<HRInfoBean> beanQuery = manager.createQuery(jpql,HRInfoBean.class);
		beanQuery.setParameter("email", email);
		try {
			HRInfoBean record = beanQuery.getSingleResult();
			if (encoder.matches(password, record.getPassword())) {
				return record;
			} else {
				return null;
			}
		} catch (Exception e) {
			for (StackTraceElement element : e.getStackTrace()) {
				System.out.println(element.toString());
			}
			return null;
		}
	}

	@Override
	public boolean changePassword(int id, String password) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		HRInfoBean hrInfoBean = manager.find(HRInfoBean.class, id);
		transaction.begin();
		try {
			hrInfoBean.setPassword(encoder.encode(password));
			manager.persist(hrInfoBean); 
			return true;
		} catch (Exception e) {
			for (StackTraceElement element : e.getStackTrace()) {
				System.out.println(element.toString());
			}
			return false;
		}
	}

	@Override
	public List<HRInfoBean> search(String name) {
		EntityManager manager = factory.createEntityManager();
		String jpql ="select hr from HRInfoBean hr where (hr.name=:name AND hr.isActive=true )OR (tyId=:name and hr.isActive=true)";
		TypedQuery< HRInfoBean>  query = manager.createQuery(jpql, HRInfoBean.class);
		query.setParameter("name", name);
		return query.getResultList();
	}

	@Override
	public List<HRInfoBean> gethrInfo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteHRInfoBean(int id) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		try {
			HRInfoBean hrInfoBean = manager.find(HRInfoBean.class, id);
			hrInfoBean.setActive(false);
			manager.persist(hrInfoBean);
			transaction.commit();
			return true;
		} catch (Exception e) {
			for (StackTraceElement element : e.getStackTrace()) {
				System.out.println(element.toString());
			}
			return false;
		}
	}

	@Override
	public boolean reg(HRInfoBean hrInfoBean) {
////		Hibernate: insert into hr_info_bean_reciever_info_bean (HRInfoBean_h_id, recieverInfoBean_r_id) values (?, ?)
//		EntityManager manager = factory.createEntityManager();
//		EntityTransaction transaction = manager.getTransaction();
//		transaction.begin();
//		String jpql = "select hr from HRInfoBean hr where hr.email=:email";
//		TypedQuery<HRInfoBean> query = manager.createQuery(jpql, HRInfoBean.class);
//		query.setParameter("email", hrInfoBean.getEmail());
//		HRInfoBean infoBean = query.getSingleResult();
//		
//		if (infoBean !=null ) {
//			
//			for (RecieverInfoBean element : infoBean.getRecieverInfoBeans()) {
//				manager.persist(element);
//				String jpqlq = "insert into hr_info_bean_reciever_info_bean (HRInfoBean_h_id, RecieverInfoBean_r_id) values (:=a, :=b)";
//				TypedQuery<HRRecieverPK> queryForUpdate = manager.createQuery(jpqlq, HRRecieverPK.class);
//				queryForUpdate.setParameter("a", infoBean.getHId());
//				queryForUpdate.setParameter("a", element.get);
//			}
//			
//			return true;
//		} else {
//			
//			try {
//				hrInfoBean.setPassword(encoder.encode(hrInfoBean.getPassword()));
//				hrInfoBean.setActive(true);
//				manager.persist(hrInfoBean);
//				transaction.commit();
//				return true;
//				
//			} catch (Exception e) {
//				for (StackTraceElement element : e.getStackTrace()) {
//					System.out.println(element.toString());
//				}
				return false;
//			}
//		}
		
	}
}
