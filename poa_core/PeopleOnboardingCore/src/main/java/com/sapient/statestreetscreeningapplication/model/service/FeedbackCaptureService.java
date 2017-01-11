package com.sapient.statestreetscreeningapplication.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.ui.bean.FeedbackBean;
// TODO: Auto-generated Javadoc

/**
 * The Interface FeedbackCaptureService.
 */
@Service
public interface FeedbackCaptureService {

	/**
	 * Gets the all feedbacks and issues.
	 *
	 * @return the all feedbacks and issues
	 */
	List<FeedbackBean> getAllFeedbacksAndIssues();

	/**
	 * Save feedback.
	 *
	 * @param feedbackBean the feedback bean
	 * @return the boolean
	 */
	Boolean saveFeedback(FeedbackBean feedbackBean);

	/**
	 * Update feedback.
	 *
	 * @param feedbackId the feedback id
	 * @return the feedback bean
	 */
	FeedbackBean updateFeedback(long feedbackId);

	List<String> getFeedBackStatusbyName();

	List<String> getFeedBackTypeByName();
	

}
