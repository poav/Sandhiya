package com.sapient.statestreetscreeningapplication.ui.bean;

// TODO: Auto-generated Javadoc
/**
 * The Class TopicBean.
 */
public class TopicBean {

	@Override
	public String toString() {
		return "TopicBean [topicId=" + topicId + ", topicName=" + topicName
				+ ", criteriaBean=" + criteriaBean + ", isUsed=" + isUsed + "]";
	}

	/** The topic id. */
	private long topicId;
	
	/** The topic name. */
	private String topicName;
	
	/** The criteria bean. */
	private CriteriaBean criteriaBean;
	
	/** The is used. */
	private int isUsed;

	/**
	 * Instantiates a new topic bean.
	 */
	public TopicBean() {
		topicId = 0;
		topicName = "";
		criteriaBean = new CriteriaBean();
	}

	/**
	 * Gets the criteria bean.
	 *
	 * @return the criteria bean
	 */
	public CriteriaBean getCriteriaBean() {
		return criteriaBean;
	}

	/**
	 * Sets the criteria bean.
	 *
	 * @param criteriaBean the new criteria bean
	 */
	public void setCriteriaBean(CriteriaBean criteriaBean) {
		this.criteriaBean = criteriaBean;
	}

	/**
	 * Gets the topic id.
	 *
	 * @return the topic id
	 */
	public long getTopicId() {
		return topicId;
	}

	/**
	 * Sets the topic id.
	 *
	 * @param topicId the new topic id
	 */
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	/**
	 * Gets the topic name.
	 *
	 * @return the topic name
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * Sets the topic name.
	 *
	 * @param topicName the new topic name
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	/**
	 * Gets the checks if is used.
	 *
	 * @return the checks if is used
	 */
	public int getIsUsed() {
		return isUsed;
	}

	/**
	 * Sets the checks if is used.
	 *
	 * @param isUsed the new checks if is used
	 */
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
}
