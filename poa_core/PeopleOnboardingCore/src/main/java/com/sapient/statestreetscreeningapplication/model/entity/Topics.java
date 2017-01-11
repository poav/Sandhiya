package com.sapient.statestreetscreeningapplication.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class Topics.
 */
@Entity
public class Topics {
	
	/** The topic id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TOPIC_ID")
	private long topicId;
	
	/** The topic name. */
	@Column(name = "TOPIC_NAME")
	private String topicName;
	
	/** The criteria. */
	@ManyToOne
	@JoinColumn(name = "CRITERIA_ID")
	private Criteria criteria;
	
	/** The is used. */
	@Column(name = "IS_USED")
	private int isUsed;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	
	public Criteria getCriteria() {
		return criteria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((criteria == null) ? 0 : criteria.hashCode());
		result = prime * result
				+ ((topicName == null) ? 0 : topicName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topics other = (Topics) obj;
		if (criteria == null) {
			if (other.criteria != null)
				return false;
		} else if (!criteria.equals(other.criteria))
			return false;
		if (topicName == null) {
			if (other.topicName != null)
				return false;
		} else if (!topicName.equals(other.topicName))
			return false;
		return true;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the new criteria
	 */
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Topics [topic=" + topicName + "]";
	}
}
