package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.Date;

import com.sapient.statestreetscreeningapplication.utils.enums.FeedbackStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class FeedbackBean.
 */
public class FeedbackBean {
	
	
	/**
	 * Gets the oracle id.
	 *
	 * @return the oracle id
	 */
	public int getOracleId() {
		return oracleId;
	}
	
	/**
	 * Sets the oracle id.
	 *
	 * @param oracleId the new oracle id
	 */
	public void setOracleId(int oracleId) {
		this.oracleId = oracleId;
	}
	
	/**
	 * Gets the feedback type.
	 *
	 * @return the feedback type
	 */
	public String getFeedbackType() {
		return feedbackType;
	}
	
	/**
	 * Sets the feedback type.
	 *
	 * @param feedbackType the new feedback type
	 */
	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}
	
	/**
	 * Gets the summary.
	 *
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	
	/**
	 * Sets the summary.
	 *
	 * @param summary the new summary
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	/**
	 * Gets the details.
	 *
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}
	
	/**
	 * Sets the details.
	 *
	 * @param details the new details
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public FeedbackStatus getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(FeedbackStatus status) {
		this.status = status;
	}
	
	/**
	 * Gets the fixed date.
	 *
	 * @return the fixed date
	 */
	public Date getFixedDate() {
		return fixedDate;
	}
	
	/**
	 * Sets the fixed date.
	 *
	 * @param fixedDate the new fixed date
	 */
	public void setFixedDate(Date fixedDate) {
		this.fixedDate = fixedDate;
	}
	
	/**
	 * Gets the fixed by.
	 *
	 * @return the fixed by
	 */
	public String getFixedBy() {
		return fixedBy;
	}
	
	/**
	 * Sets the fixed by.
	 *
	 * @param fixedBy the new fixed by
	 */
	public void setFixedBy(String fixedBy) {
		this.fixedBy = fixedBy;
	}
	
	/**
	 * Gets the target release.
	 *
	 * @return the target release
	 */
	public Date getTargetRelease() {
		return targetRelease;
	}
	
	/**
	 * Sets the target release.
	 *
	 * @param targetRelease the new target release
	 */
	public void setTargetRelease(Date targetRelease) {
		this.targetRelease = targetRelease;
	}
	
	/**
	 * Gets the fixed comments.
	 *
	 * @return the fixed comments
	 */
	public String getFixedComments() {
		return fixedComments;
	}
	
	/**
	 * Sets the fixed comments.
	 *
	 * @param fixedComments the new fixed comments
	 */
	public void setFixedComments(String fixedComments) {
		this.fixedComments = fixedComments;
	}
	
	/**
	 * Gets the feedback provider.
	 *
	 * @return the feedback provider
	 */
	public String getFeedbackProvider() {
		return feedbackProvider;
	}
	
	/**
	 * Sets the feedback provider.
	 *
	 * @param feedbackProvider the new feedback provider
	 */
	public void setFeedbackProvider(String feedbackProvider) {
		this.feedbackProvider = feedbackProvider;
	}
	
	/**
	 * Gets the feedback id.
	 *
	 * @return the feedback id
	 */
	public long getFeedbackId() {
		return feedbackId;
	}
	
	/**
	 * Sets the feedback id.
	 *
	 * @param feedbackId the new feedback id
	 */
	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}
	
	/** The feedback id. */
	private long feedbackId;
	
	/** The oracle id. */
	private int oracleId;
	
	/** The feedback provider. */
	private String feedbackProvider;
	
	/** The feedback type. */
	private String feedbackType;
	
	/** The summary. */
	private String summary;
	
	/** The details. */
	private String details;
	
	/** The status. */
	private FeedbackStatus status;
	
	/** The fixed date. */
	private Date fixedDate;
	
	/** The fixed by. */
	private String fixedBy;
	
	/** The target release. */
	private Date targetRelease;
	
	/** The fixed comments. */
	private String fixedComments;

}
