package com.moneydonationpool.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.moneydonationpool.dao.CauseDao;
import com.moneydonationpool.entity.CauseEntity;
import com.moneydonationpool.exception.MoneyDonationPoolException;

@Repository(value = "CauseDao")
@Transactional
public class CauseDaoImpl implements CauseDao {
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<CauseEntity> getAllActiveCauses() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from CauseEntity c where c.isActive=true").list();
	}
	
	@Override
	public CauseEntity getCauseById(int causeId) throws MoneyDonationPoolException {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<CauseEntity> selectedCauseEntity = session.createQuery("from CauseEntity c where c.isActive=true and c.causeId="+causeId).list();
		if(selectedCauseEntity.isEmpty())
		{
			throw new MoneyDonationPoolException(com.moneydonationpool.exception.ErrorCodes.CAUSE_INACTIVE);
		}
		return selectedCauseEntity.get(0);
	}

	@Override
	public CauseEntity postCause(CauseEntity postCauseDetails) {
		Session session = sessionFactory.getCurrentSession();
		session.save(postCauseDetails);
		return postCauseDetails;
	}
	
	@Override
	public CauseEntity updateCause(CauseEntity updateCause) {
		Session session = sessionFactory.getCurrentSession();
		//CauseEntity updateEventById = session.get(CauseEntity.class, updateCause.getCauseId());
		session.update(updateCause);
		return updateCause;
	}
	
	@Override
	public ResponseEntity<String> deacticateCause(int causeId) {
		Session session = sessionFactory.getCurrentSession();
		CauseEntity deleteCause = session.get(CauseEntity.class, causeId);

		deleteCause.setIsActive(false);
		session.update(deleteCause);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CauseEntity> searchCause(String searchString, Integer categoryId) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from CauseEntity c where c.isActive=true and (lower(c.causeTitle) like lower('%"+searchString+"%') or c.categoryId="+categoryId+")").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CauseEntity> getTodaysCaused() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from CauseEntity c where c.isActive=true and c.causeExpirationDate<CURRENT_DATE").list();
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
