package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.FeedbackDao;
import com.sapient.statestreetscreeningapplication.model.entity.Feedback;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class FeedbackDaoImpl.
 */
@Component
public class FeedbackDaoImpl implements FeedbackDao {
	
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

	/** The entity manager. */
	@PersistenceContext
	EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.FeedbackDao#getAllFeedback()
	 */
	@Override
	public List<Feedback> getAllFeedback() {
//		Fetching the available Feedbacks
		CustomLoggerUtils.INSTANCE.log.info("inside getAllFeedback() method of FeedbackDaoImpl class");
		String query = "from Feedback";
		TypedQuery<Feedback> t = entityManager.createQuery(query,
				Feedback.class);
		return t.getResultList();

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.FeedbackDao#saveUserFeedback(com.sapient.statestreetscreeningapplication.model.entity.Feedback)
	 */
	@Override
	@Transactional
	public Boolean saveUserFeedback(Feedback entity) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveUserFeedback(Feedback entity) method of FeedbackDaoImpl class");
		entityManager.merge(entity);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.FeedbackDao#getFeedback(long)
	 */
	@Override
	public Feedback getFeedback(long feedbackId) {
		CustomLoggerUtils.INSTANCE.log.info("inside getFeedback(long feedbackId) method of FeedbackDaoImpl class");
		Feedback feedback = entityManager.find(Feedback.class, feedbackId);
		return feedback;
	}

}
