package com.fuwa.lhh.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


public class BaseService<T> implements BaseDao<T> {
	@Resource()
	SessionFactory sessionFactory;	
	private Class<T> clazz;
	public BaseService() {
		ParameterizedType pt=(ParameterizedType) this.getClass().getGenericSuperclass();
		clazz=(Class<T>) pt.getActualTypeArguments()[0];
		System.out.println(clazz);	 	
	}

	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		getCurrentSession().save(t);
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		getCurrentSession().update(t);

	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		getCurrentSession().delete(t);

	}

	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from "+clazz.getSimpleName()).list();
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getObjectById(Long id) {
		// TODO Auto-generated method stub
		return (T) getCurrentSession().get(clazz, id);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		String hql="delete from "+clazz.getSimpleName();
		System.out.println("hql--->"+hql);
		getCurrentSession().createQuery(hql).executeUpdate();
		
	}
	

}
