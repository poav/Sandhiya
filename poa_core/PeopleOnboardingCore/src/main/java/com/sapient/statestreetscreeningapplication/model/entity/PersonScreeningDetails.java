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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonScreeningDetails.
 */
@Entity
@Table(name = "PERSON_SCREENING_DETAILS")
public class PersonScreeningDetails {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result
				+ ((screener == null) ? 0 : screener.hashCode());
		result = prime
				* result
				+ ((screeningEndDate == null) ? 0 : screeningEndDate.hashCode());
		result = prime
				* result
				+ ((screeningStartDate == null) ? 0 : screeningStartDate
						.hashCode());
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
		PersonScreeningDetails other = (PersonScreeningDetails) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (screener == null) {
			if (other.screener != null)
				return false;
		} else if (!screener.equals(other.screener))
			return false;
		if (screeningEndDate == null) {
			if (other.screeningEndDate != null)
				return false;
		} else if (!screeningEndDate.equals(other.screeningEndDate))
			return false;
		if (screeningStartDate == null) {
			if (other.screeningStartDate != null)
				return false;
		} else if (!screeningStartDate.equals(other.screeningStartDate))
			return false;
		return true;
	}

	/** The person screening id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSON_SCREENING_ID")
	private Long personScreeningId;
	
	/** The person. */
	@OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name = "PERSON_ID")
	private Person person;
	
	/** The screening start date. */
	@Column(name = "SCREENING_START_DATE")
	private Date screeningStartDate;
	
	/** The screening end date. */
	@Column(name = "SCREENING_END_DATE")
	private Date screeningEndDate;
	
	/** The status. */
	@OneToOne
	@JoinColumn(name="STATUS_ID")
	private Status status;
	
	/** The score list. */
	@OneToMany(mappedBy = "personScreeningDetails", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Column(nullable = true)
	private Set<ScoresNew> scoreList = new HashSet<ScoresNew>();
	
	@Override
	public String toString() {
		return "PersonScreeningDetails [personScreeningId=" + personScreeningId
				+ ", person=" + person + ", screeningStartDate="
				+ screeningStartDate + ", screeningEndDate=" + screeningEndDate
				+ ", status=" + status + ", scoreList=" + scoreList
				+ ", screener=" + screener + ", projectNew=" + projectNew
				+ ", category=" + category + ", feedback=" + feedback + "]";
	}

	/** The screener. */
	@ManyToOne
	@JoinColumn(name = "SCREENER_ID")
	private Person screener;
/*	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
	@JoinTable(name="PERSON_SCREENING_PROJECT", 
			   joinColumns={@JoinColumn(name="PERSON_SCREENING_ID",updatable = true,nullable=true)}, 
	           inverseJoinColumns={@JoinColumn(name="PROJECT_ID" ,updatable = true,nullable=true)}
			   )
	private Set<ProjectNew> projectNew=new HashSet<ProjectNew>();*/
	/** The project new. */
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="PROJECT_ID")
	private ProjectNew projectNew;
	
	/** The category. */
	@OneToOne
	@JoinColumn(name="CATEGORY_ID")
	private Category category;
	
	/** The feedback. */
	@Column(length = 100000,name = "FEEDBACK")
	private String feedback;
	
	/**
	 * Gets the feedback.
	 *
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}
	
	/**
	 * Sets the feedback.
	 *
	 * @param feedback the new feedback
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	/**
	 * Gets the project new.
	 *
	 * @return the project new
	 */
	public ProjectNew getProjectNew() {
		return projectNew;
	}
	
	/**
	 * Sets the project new.
	 *
	 * @param projectNew the new project new
	 */
	public void setProjectNew(ProjectNew projectNew) {
		this.projectNew = projectNew;
	}
	
	/**
	 * Gets the person screening id.
	 *
	 * @return the person screening id
	 */
	public Long getPersonScreeningId() {
		return personScreeningId;
	}
	
	/**
	 * Sets the person screening id.
	 *
	 * @param personScreeningId the new person screening id
	 */
	public void setPersonScreeningId(Long personScreeningId) {
		this.personScreeningId = personScreeningId;
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
	 * Gets the screening start date.
	 *
	 * @return the screening start date
	 */
	public Date getScreeningStartDate() {
		return screeningStartDate;
	}
	
	/**
	 * Sets the screening start date.
	 *
	 * @param screeningStartDate the new screening start date
	 */
	public void setScreeningStartDate(Date screeningStartDate) {
		this.screeningStartDate = screeningStartDate;
	}
	
	/**
	 * Gets the screening end date.
	 *
	 * @return the screening end date
	 */
	public Date getScreeningEndDate() {
		return screeningEndDate;
	}
	
	/**
	 * Sets the screening end date.
	 *
	 * @param screeningEndDate the new screening end date
	 */
	public void setScreeningEndDate(Date screeningEndDate) {
		this.screeningEndDate = screeningEndDate;
	}
	
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * Gets the screener.
	 *
	 * @return the screener
	 */
	public Person getScreener() {
		return screener;
	}
	
	/**
	 * Sets the screener.
	 *
	 * @param screener the new screener
	 */
	public void setScreener(Person screener) {
		this.screener = screener;
	}
	
	/**
	 * Gets the score list.
	 *
	 * @return the score list
	 */
	public Set<ScoresNew> getScoreList() {
		return scoreList;
	}
	
	/**
	 * Sets the score list.
	 *
	 * @param scoreList the new score list
	 */
	public void setScoreList(Set<ScoresNew> scoreList) {
		this.scoreList = scoreList;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
}
