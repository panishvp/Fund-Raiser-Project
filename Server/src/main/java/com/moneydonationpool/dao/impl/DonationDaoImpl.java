package com.moneydonationpool.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moneydonationpool.dao.DonationDao;
import com.moneydonationpool.entity.DonationEntity;
@Repository
public class DonationDaoImpl implements DonationDao {
	SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public DonationEntity postDonationDetails(DonationEntity donationDetails) {
		Session session = sessionFactory.getCurrentSession();
		session.save(donationDetails);
		return donationDetails;
	}

}
