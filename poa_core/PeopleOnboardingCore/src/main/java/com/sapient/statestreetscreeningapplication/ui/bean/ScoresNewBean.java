package com.sapient.statestreetscreeningapplication.ui.bean;

import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;

public class ScoresNewBean {
	
	@Override
	public String toString() {
		return "ScoresNewBean [scoreId=" + scoreId
				+ ", personScreeningDetails=" + personScreeningDetails
				+ ", score=" + score + ", comments=" + comments
				+ ", topicBean=" + topicBean + ", criteriaBean=" + criteriaBean
				+ ", isChecked=" + isChecked + "]";
	}
	private long scoreId;
	private PersonScreeningDetails personScreeningDetails;
	private double score;
	private String comments;
	private TopicBean topicBean;
	private CriteriaBean criteriaBean;
	
	
	private int isChecked;
	
	public int getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(int isChecked) {
		this.isChecked = isChecked;
	}
	
	public CriteriaBean getCriteriaBean() {
		return criteriaBean;
	}
	public void setCriteriaBean(CriteriaBean criteriaBean) {
		this.criteriaBean = criteriaBean;
	}
	public long getScoreId() {
		return scoreId;
	}
	public void setScoreId(long scoreId) {
		this.scoreId = scoreId;
	}
	public PersonScreeningDetails getPersonScreeningDetails() {
		return personScreeningDetails;
	}
	public void setPersonScreeningDetails(
			PersonScreeningDetails personScreeningDetails) {
		this.personScreeningDetails = personScreeningDetails;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public TopicBean getTopicBean() {
		return topicBean;
	}
	public void setTopicBean(TopicBean topicBean) {
		this.topicBean = topicBean;
	}
}
