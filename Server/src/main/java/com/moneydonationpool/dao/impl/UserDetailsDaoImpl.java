package com.moneydonationpool.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moneydonationpool.dao.UserDetailsDao;
import com.moneydonationpool.entity.CauseEntity;
import com.moneydonationpool.entity.DonationEntity;
import com.moneydonationpool.entity.LoginEntity;
import com.moneydonationpool.entity.UserDetailsEntity;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {
	SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public UserDetailsEntity getUserDetails(int userId) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<UserDetailsEntity> userDetailsEntity = session
				.createQuery("from UserDetailsEntity u where u.isActive=true and userId=" + userId).list();
		return userDetailsEntity.get(0);
	}

	@Override
	public List<UserDetailsEntity> getUserIdByEmailId(String emailId) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<UserDetailsEntity> userDetailsEntity = session.createQuery("from UserDetailsEntity u where u.isActive=true and u.emailId='" + emailId+"'").list();
		 return userDetailsEntity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getExsistingEmails() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("select u.emailId from UserDetailsEntity u where u.isActive=true").list();
	}

	@Override
	public UserDetailsEntity registerUser(UserDetailsEntity userDetailsEntity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(userDetailsEntity);
		return userDetailsEntity;
	}

	@Override
	public UserDetailsEntity PromoteToAdmin(UserDetailsEntity userDetailsEntity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(userDetailsEntity);
		return userDetailsEntity;
	}

	@Override
	public List<DonationEntity> getUserDonations(int userId) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<DonationEntity> userDonation = session.createQuery(
				"from DonationEntity d join CauseEntity c on c.causeId = d.causeId where d.donatedBy =1")
				.list();
		return userDonation;
	}

	@Override
	public List<CauseEntity> getUserCreatedCause(int userId) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<CauseEntity> userCauses = session.createQuery("from CauseEntity c where c.createdBy =1").list();
		return userCauses;
	}

	@Override
	public String userTokenRegistery(LoginEntity loginEntity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(loginEntity);
		return "Success";
	}

	@Override
	public String userTokenDeRegistery(LoginEntity loginEntity) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(loginEntity);
		return "Success";
	}
	
	@Override
	public LoginEntity checkUserSessionDetails(String authorization) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<LoginEntity> loginEntity = session.createQuery("from LoginEntity l where l.accessToken ='"+authorization+"'").list();
		return loginEntity.get(0);
	}


}
