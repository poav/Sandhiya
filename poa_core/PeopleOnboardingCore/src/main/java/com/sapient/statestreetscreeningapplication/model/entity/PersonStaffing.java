package com.sapient.statestreetscreeningapplication.model.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonStaffing.
 */
@Entity
@Table(name = "PERSON_STAFFING")
public class PersonStaffing {
	
	/** The person staffing id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSON_STAFFING_ID")
	private Long personStaffingId;
	
	/** The person. */
	@ManyToOne
	@JoinColumn(name = "PERSON_ID")
	private Person person;
	
	/** The project. */
	@ManyToOne
	@JoinColumn(name = "PROJECT_ID")
	private ProjectNew project;
	
	/** The start date. */
	@Column(name = "START_DATE")
	private Date startDate;
	
	/** The end date. */
	@Column(name = "END_DATE")
	private Date endDate;
	
	/** The comments. */
	@Column(name = "COMMENTS")
	private String comments;
	
	/** The location. */
	@ManyToOne
	@JoinColumn(name = "LOCATION_ID")
	private LocationNew location;
	
	/** The position. */
	@ManyToOne
	@JoinColumn(name = "POSITION_ID")
	private Position position;
	
	/** The allocation. */
	@Column(name = "ALLOCATION")
	private float allocation;
	
	@ManyToOne
	@JoinColumn(name = "RATE_ID")
	private Rate rate;
	
	/** The rate. */
	/*@Column(name = "RATE_ID")
	private float rate;*/

	
	
	
	/*public String getRateCategory() {
		return rateCategory;
	}

	public void setRateCategory(String rateCategory) {
		this.rateCategory = rateCategory;
	}

	*//** The monthly rate. *//*
	@Column(name = "RATE_CATEGORY")
	private String rateCategory;*/
	
	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}

	/** The client lead. */
	@Column(name = "CLIENT_LEAD")
	private String clientLead;
	
	/** The sapient lead. */
	@ManyToOne
	@JoinColumn(name = "SAPIENT_LEAD")
	private Person sapientLead;
	
	/**
	 * Sets the sapient lead.
	 *
	 * @param sapientLead the new sapient lead
	 */
	public void setSapientLead(Person sapientLead) {
		this.sapientLead = sapientLead;
	}
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	
	/** The email dls. */
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="PERSON_STAFFING_EMAIL_DL", 
			   joinColumns={@JoinColumn(name="PERSON_STAFFING_ID",updatable = true,nullable=true)}, 
	           inverseJoinColumns={@JoinColumn(name="EMAIL_DL_ID" ,updatable = true,nullable=true)}
			   )
	private Set<EmailDl> emailDls = new HashSet<EmailDl>();
	
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
	public Person getPerson() {
		return person;
	}
	
	/**
	 * Sets the person.
	 *
	 * @param person the new person
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	
	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public ProjectNew getProject() {
		return project;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(ProjectNew project) {
		this.project = project;
	}
	
	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
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
	public LocationNew getLocation() {
		return location;
	}
	
	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(LocationNew location) {
		this.location = location;
	}
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	
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
	}
	
	*//**
	 * Sets the rate.
	 *
	 * @param rate the new rate
	 *//*
	public void setRate(float rate) {
		this.rate = rate;
	}*/
	
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
	public Person getSapientLead() {
		return sapientLead;
	}
	
	/**
	 * Gets the email dls.
	 *
	 * @return the email dls
	 */
	public Set<EmailDl> getEmailDls() {
		return emailDls;
	}
	
	/**
	 * Sets the email dls.
	 *
	 * @param emailDls the new email dls
	 */
	public void setEmailDls(Set<EmailDl> emailDls) {
		this.emailDls = emailDls;
	}
	
	@Column(name = "IS_OFFBOARDED")
	private boolean isOffboarded;

	public boolean isOffboarded() {
		return isOffboarded;
	}

	public void setOffboarded(boolean isOffboarded) {
		this.isOffboarded = isOffboarded;
	}
	
	
	@OneToOne
	@JoinColumn(name = "IMMEDIATE_LAST_STAFFING")
	private PersonStaffing immediateLastStaffing;
	
	@OneToOne
	@JoinColumn(name = "IMMEDIATE_NEXT_STAFFING")
	private PersonStaffing immediateNextStaffing;

	public PersonStaffing getImmediateLastStaffing() {
		return immediateLastStaffing;
	}

	public void setImmediateLastStaffing(PersonStaffing immediateLastStaffing) {
		this.immediateLastStaffing = immediateLastStaffing;
	}

	public PersonStaffing getImmediateNextStaffing() {
		return immediateNextStaffing;
	}

	public void setImmediateNextStaffing(PersonStaffing immediateNextStaffing) {
		this.immediateNextStaffing = immediateNextStaffing;
	}
	
}
