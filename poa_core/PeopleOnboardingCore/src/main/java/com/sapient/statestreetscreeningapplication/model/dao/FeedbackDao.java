package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.Feedback;


// TODO: Auto-generated Javadoc
/**
 * The Interface FeedbackDao.
 */
public interface FeedbackDao {

	/**
	 * Gets the all feedback.
	 *
	 * @return the all feedback
	 */
	List<Feedback> getAllFeedback();

	/**
	 * Save user feedback.
	 *
	 * @param convertBeanToEntity the convert bean to entity
	 * @return the boolean
	 */
	Boolean saveUserFeedback(Feedback convertBeanToEntity);

	/**
	 * Gets the feedback.
	 *
	 * @param feedbackId the feedback id
	 * @return the feedback
	 */
	Feedback getFeedback(long feedbackId);

}
