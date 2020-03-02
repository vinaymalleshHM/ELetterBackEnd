package com.tyss.eletter.dao;

import java.time.temporal.TemporalQuery;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
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
public class ELetterDAOImpl implements ELetterDAO {

	@PersistenceUnit
	private EntityManagerFactory factory;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public boolean register(HRInfoBean hrInfoBean) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		try {
			String jpql = "from HRInfoBean emp where emp.email=:email";
			Query query = manager.createQuery(jpql);
			query.setParameter("email", hrInfoBean.getEmail());
			HRInfoBean bean = (HRInfoBean) query.getSingleResult();
			List<RecieverInfoBean> list = bean.getRecieverInfoBean();
			list.addAll(hrInfoBean.getRecieverInfoBean());
			bean.setRecieverInfoBean(list);
			manager.persist(bean);
			}catch(Exception e) {
				hrInfoBean.setPassword(encoder.encode(hrInfoBean.getPassword()));
				hrInfoBean.setActive(true);
				manager.persist(hrInfoBean);
			}
			transaction.commit();
			return true;
 

	}

	@Override
	public HRInfoBean auth(String email, String password) {
		EntityManager manager = factory.createEntityManager();
		String jpql ="from HRInfoBean hr where hr.email=:email";
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
	public boolean changePassword(String email, String password) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		String jpql ="from HRInfoBean hr where hr.email=:email";
		TypedQuery<HRInfoBean> beanQuery = manager.createQuery(jpql,HRInfoBean.class);
		beanQuery.setParameter("email", email);
		HRInfoBean record = beanQuery.getSingleResult();
		HRInfoBean hrInfoBean = manager.find(HRInfoBean.class, record.getHId());
		transaction.begin();
		try {
			hrInfoBean.setPassword(encoder.encode(password));
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
	public List<HRInfoBean> search(String name) {
		EntityManager manager = factory.createEntityManager();
		String jpql ="from HRInfoBean hr where (hr.name=:name AND hr.isActive=true ) OR (tyId=:name and hr.isActive=true)";
		TypedQuery< HRInfoBean>  query = manager.createQuery(jpql, HRInfoBean.class);
		query.setParameter("name", name);
		return query.getResultList();
	}

	@Override
	public List<HRInfoBean> gethrInfo(int id) {
		return null;
	}

	@Override
	public boolean deleteHRInfoBean(String email) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		String jpql ="from HRInfoBean hr where hr.email=:email";
		TypedQuery<HRInfoBean> beanQuery = manager.createQuery(jpql,HRInfoBean.class);
		beanQuery.setParameter("email", email);
		HRInfoBean record = beanQuery.getSingleResult();
		transaction.begin();
		try {
			HRInfoBean hrInfoBean = manager.find(HRInfoBean.class, record.getHId());
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

	
}
