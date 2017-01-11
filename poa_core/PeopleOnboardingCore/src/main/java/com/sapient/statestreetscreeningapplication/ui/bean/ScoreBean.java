package com.sapient.statestreetscreeningapplication.ui.bean;

// TODO: Auto-generated Javadoc
/**
 * The Class ScoreBean.
 */
public class ScoreBean {
	
	/** The Score id. */
	private long ScoreId;
	
	/** The score. */
	private double score;
	
	/** The interviewee id. */
	private long intervieweeId;
	
	/** The topic. */
	private String topic;
	
	/** The comments. */
	private String comments;
	
	/** The interviewee name. */
	private String intervieweeName;
	
	/** The criteria. */
	private String criteria;
	
	/** The topic id. */
	private long topicId;
	

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
	 * Gets the score id.
	 *
	 * @return the score id
	 */
	public long getScoreId() {
		return ScoreId;
	}
	
	/**
	 * Gets the interviewee id.
	 *
	 * @return the interviewee id
	 */
	public long getIntervieweeId() {
		return intervieweeId;
	}

	/**
	 * Sets the interviewee id.
	 *
	 * @param intervieweeId the new interviewee id
	 */
	public void setIntervieweeId(long intervieweeId) {
		this.intervieweeId = intervieweeId;
	}

	/**
	 * Gets the topic.
	 *
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * Sets the topic.
	 *
	 * @param topic the new topic
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}



	/**
	 * Sets the score id.
	 *
	 * @param scoreId the new score id
	 */
	public void setScoreId(long scoreId) {
		ScoreId = scoreId;
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public double getScore() {
		return score;
	}
	
	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(double score) {
		this.score = score;
	}
	
	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the interviewee name.
	 *
	 * @return the interviewee name
	 */
	public String getIntervieweeName() {
		return intervieweeName;
	}

	/**
	 * Sets the interviewee name.
	 *
	 * @param intervieweeName the new interviewee name
	 */
	public void setIntervieweeName(String intervieweeName) {
		this.intervieweeName = intervieweeName;
	}

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public String getCriteria() {
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the new criteria
	 */
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "" + score + "|" + topic + "|"
				+ comments + "|" + criteria + "";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ScoreId ^ (ScoreId >>> 32));
		result = prime * result
				+ ((comments == null) ? 0 : comments.hashCode());
		result = prime * result
				+ ((criteria == null) ? 0 : criteria.hashCode());
		result = prime * result
				+ (int) (intervieweeId ^ (intervieweeId >>> 32));
		result = prime * result
				+ ((intervieweeName == null) ? 0 : intervieweeName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(score);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		result = prime * result + (int) (topicId ^ (topicId >>> 32));
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreBean other = (ScoreBean) obj;
		if (ScoreId != other.ScoreId)
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (criteria == null) {
			if (other.criteria != null)
				return false;
		} else if (!criteria.equals(other.criteria))
			return false;
		if (intervieweeId != other.intervieweeId)
			return false;
		if (intervieweeName == null) {
			if (other.intervieweeName != null)
				return false;
		} else if (!intervieweeName.equals(other.intervieweeName))
			return false;
		if (Double.doubleToLongBits(score) != Double
				.doubleToLongBits(other.score))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		if (topicId != other.topicId)
			return false;
		return true;
	}
	
	

}
