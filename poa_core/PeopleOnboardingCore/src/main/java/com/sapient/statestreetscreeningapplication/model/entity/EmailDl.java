package com.sapient.statestreetscreeningapplication.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailDl.
 */
@Entity
@Table( name = "EMAIL_DL")
public class EmailDl {
	
	/** The email dl id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMAIL_DL_ID")
	private Long emailDlId;
	
	/** The Email. */
	@Column(name = "EMAIL")
	private String Email;
	
	/** The project. */
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "PROJECT_ID")
	private ProjectNew project;
	
	/** The location. */
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "LOCATION_ID")
	private LocationNew location;
	
	/** The person staffing. */
	@ManyToMany(mappedBy="emailDls")
    private Set<PersonStaffing> personStaffing = new HashSet<PersonStaffing>();
	
	@Column(name = "IS_ACTIVE")
	private int isActive ;
	
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the email dl id.
	 *
	 * @return the email dl id
	 */
	public Long getEmailDlId() {
		return emailDlId;
	}
	
	/**
	 * Sets the email dl id.
	 *
	 * @param emailDlId the new email dl id
	 */
	public void setEmailDlId(Long emailDlId) {
		this.emailDlId = emailDlId;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		Email = email;
	}
	
	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public ProjectNew getProject() {
		return project;
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
	 * Gets the person staffing.
	 *
	 * @return the person staffing
	 */
	public Set<PersonStaffing> getPersonStaffing() {
		return personStaffing;
	}
	
	/**
	 * Sets the person staffing.
	 *
	 * @param personStaffing the new person staffing
	 */
	public void setPersonStaffing(Set<PersonStaffing> personStaffing) {
		this.personStaffing = personStaffing;
	}
}
