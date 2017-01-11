package com.sapient.statestreetscreeningapplication.ui.bean;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;




public class PersonStaffingBean {
	/** The person staffing id. */
	private Long personStaffingId;
	
	/** The person. */
	private PersonNewBean person;
	
	/** The project. */
	private ProjectNewBean project;
	private String personName;
	private String personEmail;
    private String supervisorName;	
	private String dlNames;
	/** The start date. */
	private String startDate;
	
	/** The end date. */
	private String endDate;
	
	/** The rate Category. */
	/*private String rateCategory;*/
	
	/** The comments. */
	private String comments;
	
	/** The location. */
	private LocationNewBean location;
	
	/** The position. */
	private PositionBean position;
	
	/** The allocation. */
	private float allocation;
	
	/** The rate. */
	/*private float rate;*/
	private RateBean rateBean;
	
	private CategoryBean categoryBean;
	
	private Date staffingStartDate;
	
	private Date staffingEndDate;
	
	public Date getStaffingStartDate() {
		return staffingStartDate;
	}

	public void setStaffingStartDate(Date staffingStartDate) {
		this.staffingStartDate = staffingStartDate;
	}

	public Date getStaffingEndDate() {
		return staffingEndDate;
	}

	public void setStaffingEndDate(Date staffingEndDate) {
		this.staffingEndDate = staffingEndDate;
	}

	
	public CategoryBean getCategoryBean() {
		return categoryBean;
	}

	public void setCategoryBean(CategoryBean categoryBean) {
		this.categoryBean = categoryBean;
	}

	public RateBean getRateBean() {
		return rateBean;
	}

	public void setRateBean(RateBean rateBean) {
		this.rateBean = rateBean;
	}

	/** The client lead. */
	private String clientLead;
	
	/** The sapient lead. */
	private PersonNewBean sapientLead;
	
	private boolean isOffboarded;
	

	public boolean isOffboarded() {
		return isOffboarded;
	}

	public void setOffboarded(boolean isOffboarded) {
		this.isOffboarded = isOffboarded;
	}

	/** The email dls. */
	private Set<EmailDlBean> emailDls = new HashSet<EmailDlBean>();
	
	/**
	 * Gets the person staffing id.
	 *
	 * @return the person staffing id
	 */
	public Long getPersonStaffingId() {
		return personStaffingId;
	}
	
	/**
	 * Sets the person staffing id.
	 *
	 * @param personStaffingId the new person staffing id
	 */
	public void setPersonStaffingId(Long personStaffingId) {
		this.personStaffingId = personStaffingId;
	}
	
	/**
	 * Gets the person.
	 *
	 * @return the person
	 */
	public PersonNewBean getPerson() {
		return person;
	}
	
	/**
	 * Sets the person.
	 *
	 * @param person the new person
	 */
	public void setPerson(PersonNewBean person) {
		this.person = person;
	}
	
	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public ProjectNewBean getProject() {
		return project;
	}
	
	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(ProjectNewBean project) {
		this.project = project;
	}
	
	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public String getStartDate() {
		return startDate;
	}
	
	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	 * Gets the location.
	 *
	 * @return the location
	 */
	
	
	/**
	 * Gets the allocation.
	 *
	 * @return the allocation
	 */
	public float getAllocation() {
		return allocation;
	}
	
	/**
	 * Sets the allocation.
	 *
	 * @param allocation the new allocation
	 */
	public void setAllocation(float allocation) {
		this.allocation = allocation;
	}
	
	/**
	 * Gets the rate.
	 *
	 * @return the rate
	 */
	/*public float getRate() {
		return rate;
	}*/
	
	/**
	 * Sets the rate.
	 *
	 * @param rate the new rate
	 */
	/*public void setRate(float rate) {
		this.rate = rate;
	}
	*/
	/**
	 * Gets the client lead.
	 *
	 * @return the client lead
	 */
	public String getClientLead() {
		return clientLead;
	}
	
	/**
	 * Sets the client lead.
	 *
	 * @param clientLead the new client lead
	 */
	public void setClientLead(String clientLead) {
		this.clientLead = clientLead;
	}
	
	/**
	 * Gets the sapient lead.
	 *
	 * @return the sapient lead
	 */
	
	/**
	 * Gets the email dls.
	 *
	 * @return the email dls
	 */
	public Set<EmailDlBean> getEmailDls() {
		return emailDls;
	}
	
	public LocationNewBean getLocation() {
		return location;
	}

	public void setLocation(LocationNewBean location) {
		this.location = location;
	}

	public PositionBean getPosition() {
		return position;
	}

	public void setPosition(PositionBean position) {
		this.position = position;
	}

	public PersonNewBean getSapientLead() {
		return sapientLead;
	}

	public void setSapientLead(PersonNewBean sapientLead) {
		this.sapientLead = sapientLead;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	/**
	 * Sets the email dls.
	 *
	 * @param emailDls the new email dls
	 */
	public void setEmailDls(Set<EmailDlBean> emailDls) {
		this.emailDls = emailDls;
	}

	public String getDlNames() {
		return dlNames;
	}

	public void setDlNames(String dlNames) {
		this.dlNames = dlNames;
	}

	@Override
	public String toString() {
		return "PersonStaffingBean [personStaffingId=" + personStaffingId
				+ ", person=" + person + ", project=" + project
				+ ", personName=" + personName + ", personEmail=" + personEmail
				+ ", supervisorName=" + supervisorName + ", dlNames=" + dlNames
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", comments=" + comments + ", location=" + location
				+ ", position=" + position + ", allocation=" + allocation
				+ ", rateBean=" + rateBean + ", clientLead=" + clientLead
				+ ", sapientLead=" + sapientLead + ", emailDls=" + emailDls
				+ "]";
	}

	
	private PersonStaffingBean immediateLastStaffing;

	public PersonStaffingBean getImmediateLastStaffing() {
		return immediateLastStaffing;
	}

	public void setImmediateLastStaffing(PersonStaffingBean immediateLastStaffing) {
		this.immediateLastStaffing = immediateLastStaffing;
	} 
	
	private PersonStaffingBean immediateNextStaffing;

	public PersonStaffingBean getImmediateNextStaffing() {
		return immediateNextStaffing;
	}

	public void setImmediateNextStaffing(PersonStaffingBean immediateNextStaffing) {
		this.immediateNextStaffing = immediateNextStaffing;
	}
	
}
