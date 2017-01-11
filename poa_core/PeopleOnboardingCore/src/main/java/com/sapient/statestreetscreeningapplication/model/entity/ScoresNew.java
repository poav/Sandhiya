package com.sapient.statestreetscreeningapplication.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

// TODO: Auto-generated Javadoc
/**
 * The Class ScoresNew.
 */
@Entity
@Table(name = "SCORES_NEW")
public class ScoresNew {
	
	/** The score id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="SCORE_ID")
	private long scoreId;
	
	/** The person screening details. */
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "PERSON_SCREENING_ID")
	private PersonScreeningDetails personScreeningDetails;
	
	/** The criteria. */
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "CRITERIA_ID")
	private Criteria criteria;
	
	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	/** The score. */
	@Column(name = "SCORE")
	private double score;
	
	/** The comments. */
	@Column(name = "COMMENT")
	private String comments;
	
	/** The topic. */
	@OneToOne
	@JoinColumn(name = "TOPIC_ID")
	private Topics topic;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((criteria == null) ? 0 : criteria.hashCode());
		result = prime
				* result
				+ ((personScreeningDetails == null) ? 0
						: personScreeningDetails.hashCode());
		long temp;
		temp = Double.doubleToLongBits(score);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
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
		ScoresNew other = (ScoresNew) obj;
		if (criteria == null) {
			if (other.criteria != null)
				return false;
		} else if (!criteria.equals(other.criteria))
			return false;
		if (personScreeningDetails == null) {
			if (other.personScreeningDetails != null)
				return false;
		} else if (!personScreeningDetails.equals(other.personScreeningDetails))
			return false;
		if (Double.doubleToLongBits(score) != Double
				.doubleToLongBits(other.score))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}

	@Column(name = "IS_CHECKED")
	private int isChecked;
	
	public int getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(int isChecked) {
		this.isChecked = isChecked;
	}

	/**
	 * Gets the topic.
	 *
	 * @return the topic
	 */
	public Topics getTopic() {
		return topic;
	}
	
	/**
	 * Sets the topic.
	 *
	 * @param topic the new topic
	 */
	public void setTopic(Topics topic) {
		this.topic = topic;
	}
	
	/**
	 * Gets the score id.
	 *
	 * @return the score id
	 */
	public long getScoreId() {
		return scoreId;
	}
	
	/**
	 * Sets the score id.
	 *
	 * @param scoreId the new score id
	 */
	public void setScoreId(long scoreId) {
		this.scoreId = scoreId;
	}
	
	/**
	 * Gets the person screening details.
	 *
	 * @return the person screening details
	 */
	public PersonScreeningDetails getPersonScreeningDetails() {
		return personScreeningDetails;
	}
	
	/**
	 * Sets the person screening details.
	 *
	 * @param personScreeningDetails the new person screening details
	 */
	public void setPersonScreeningDetails(
			PersonScreeningDetails personScreeningDetails) {
		this.personScreeningDetails = personScreeningDetails;
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
	
	
	
}
