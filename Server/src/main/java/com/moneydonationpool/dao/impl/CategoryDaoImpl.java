package com.moneydonationpool.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moneydonationpool.dao.CategoryDao;
import com.moneydonationpool.entity.CategoryEntity;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryEntity> getAllCategories() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from CategoryEntity c ").list();
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
