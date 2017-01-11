package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonScreeningDetailsBean {

	private Long personScreeningId;
	private PersonNewBean personBean;
	private Date screeningStartDate;
	private Date screeningEndDate;
	private String stringScreeningStartDate;
	private String stringScreeningEndDate;
	private StatusBean statusBean;
	private Set<ScoresNewBean> scoreList = new HashSet<ScoresNewBean>();
	private List<ScoresNewBean> scoreList2 = new ArrayList<>();
	
	private PersonNewBean screenerBean;
	private ProjectNewBean projectBean;
	private String category;
	private String feedback;
	public Long getPersonScreeningId() {
		return personScreeningId;
	}
	public void setPersonScreeningId(Long personScreeningId) {
		this.personScreeningId = personScreeningId;
	}
	public PersonNewBean getPersonBean() {
		return personBean;
	}
	public void setPersonBean(PersonNewBean personBean) {
		this.personBean = personBean;
	}
	public Date getScreeningStartDate() {
		return screeningStartDate;
	}
	public void setScreeningStartDate(Date screeningStartDate) {
		this.screeningStartDate = screeningStartDate;
	}
	public Date getScreeningEndDate() {
		return screeningEndDate;
	}
	public void setScreeningEndDate(Date screeningEndDate) {
		this.screeningEndDate = screeningEndDate;
	}
	public String getStringScreeningStartDate() {
		return stringScreeningStartDate;
	}
	public void setStringScreeningStartDate(String stringScreeningStartDate) {
		this.stringScreeningStartDate = stringScreeningStartDate;
	}
	public String getStringScreeningEndDate() {
		return stringScreeningEndDate;
	}
	public void setStringScreeningEndDate(String stringScreeningEndDate) {
		this.stringScreeningEndDate = stringScreeningEndDate;
	}
	public StatusBean getStatusBean() {
		return statusBean;
	}
	public void setStatusBean(StatusBean statusBean) {
		this.statusBean = statusBean;
	}
	public Set<ScoresNewBean> getScoreList() {
		return scoreList;
	}
	public void setScoreList(Set<ScoresNewBean> scoreList) {
		this.scoreList = scoreList;
	}
	public PersonNewBean getScreenerBean() {
		return screenerBean;
	}
	public void setScreenerBean(PersonNewBean screenerBean) {
		this.screenerBean = screenerBean;
	}
	public ProjectNewBean getProjectBean() {
		return projectBean;
	}
	public void setProjectBean(ProjectNewBean projectBean) {
		this.projectBean = projectBean;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	@Override
	public String toString() {
		return "PersonScreeningDetailsBean [personScreeningId="
				+ personScreeningId + ", personBean=" + personBean
				+ ", screeningStartDate=" + screeningStartDate
				+ ", screeningEndDate=" + screeningEndDate
				+ ", stringScreeningStartDate=" + stringScreeningStartDate
				+ ", stringScreeningEndDate=" + stringScreeningEndDate
				+ ", statusBean=" + statusBean + ", scoreList=" + scoreList
				+ ", screenerBean=" + screenerBean + ", projectBean="
				+ projectBean + ", categoryBean=" + category
				+ ", feedback=" + feedback + "]";
	}
}
