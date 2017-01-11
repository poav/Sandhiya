package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.ScreeningTrackerDao;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.ScoresNew;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ScreeningTackerDaoImpl.
 */
@Component
public class ScreeningTackerDaoImpl implements ScreeningTrackerDao {
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager the new entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.ScreeningTrackerDao#saveScreeningSummary(java.util.List)
	 */
	
	@Override
	@Transactional
	public void saveScreeningSummary(List<Person> screeningSummaryList) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveScreeningSummary(List<Interviewee> screeningSummaryList)  method of ScreeningTackerDaoImpl ");
		for (Person screeningSummary : screeningSummaryList) {
			entityManager.persist(screeningSummary);
		}
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.ScreeningTrackerDao#saveScoresBatch(java.util.List)
	 */
	@Override
	@Transactional
	public void saveScoresBatch(List<ScoresNew> scoreList) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveScoresBatch(List<Scores> scoreList)  method of ScreeningTackerDaoImpl ");
		for (ScoresNew score : scoreList) {
			entityManager.persist(score);
		}
	}

}
