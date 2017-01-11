package com.sapient.statestreetscreeningapplication.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectNew.
 */
@Entity
@Table(name = "PROJECT_NEW")
public class ProjectNew {
	
	/** The Id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long Id;
	
	/** The project id. */
	@Column(name = "PROJECT_ID")
	private int projectId;
	
	/** The project name. */
	@Column(name = "PROJECT_NAME")
	private String projectName;
	
	/** The client project id. */
	@Column(name = "CLIENT_PROJECT_ID")
	private int clientProjectId;
	
	/** The client time tracking id. */
	@Column(name = "CLIENT_TIME_TRACKING_ID")
	private String clientTimeTrackingId;
	
	/** The client time tracking project name. */
	@Column(name = "CLIENT_TIME_TRACKING_PROJECT_NAME")
	private String clientTimeTrackingProjectName;
	
	/** The client project name. */
	@Column(name = "CLIENT_PROJECT_NAME")
	private String clientProjectName;
	
	/** The project start date. */
	@Column(name = "PROJECT_START_DATE")
	private Date projectStartDate;
	
	/** The project end date. */
	@Column(name = "PROJECT_END_DATE")
	private Date projectEndDate;
	
	/** The sapient lead. */
	@Column(name = "SAPIENT_LEAD")
	private int sapientLead;
	
	/** The team. */
	@ManyToOne
	@JoinColumn(name="TEAM_ID")
	private Team team;
	
	@OneToOne
	@JoinColumn(name="PROJECT_LEAD")
	private Person projectLead;
	
	public Person getProjectLead() {
		return projectLead;
	}

	public void setProjectLead(Person projectLead) {
		this.projectLead = projectLead;
	}

	/** The is active. */
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name = "COST_CENTER")
	private String costCenter;
	/*@ManyToMany(mappedBy="projectNew")
    private Set<PersonScreeningDetails> personScreeningDetails = new HashSet<PersonScreeningDetails>();*/
	
	
	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	/**
	 * Gets the project id.
	 *
	 * @return the project id
	 */
	public int getProjectId() {
		return projectId;
	}
	
	/**
	 * Sets the project id.
	 *
	 * @param projectId the new project id
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	/**
	 * Gets the project name.
	 *
	 * @return the project name
	 */
	public String getProjectName() {
		return projectName;
	}
	
	/**
	 * Sets the project name.
	 *
	 * @param projectName the new project name
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	/**
	 * Gets the client project id.
	 *
	 * @return the client project id
	 */
	public int getClientProjectId() {
		return clientProjectId;
	}
	
	/**
	 * Sets the client project id.
	 *
	 * @param clientProjectId the new client project id
	 */
	public void setClientProjectId(int clientProjectId) {
		this.clientProjectId = clientProjectId;
	}
	
	/**
	 * Gets the client time tracking id.
	 *
	 * @return the client time tracking id
	 */
	public String getClientTimeTrackingId() {
		return clientTimeTrackingId;
	}
	
	/**
	 * Sets the client time tracking id.
	 *
	 * @param clientTimeTrackingId the new client time tracking id
	 */
	public void setClientTimeTrackingId(String clientTimeTrackingId) {
		this.clientTimeTrackingId = clientTimeTrackingId;
	}
	
	/**
	 * Gets the client project name.
	 *
	 * @return the client project name
	 */
	public String getClientProjectName() {
		return clientProjectName;
	}
	
	/**
	 * Sets the client project name.
	 *
	 * @param clientProjectName the new client project name
	 */
	public void setClientProjectName(String clientProjectName) {
		this.clientProjectName = clientProjectName;
	}
	
	/**
	 * Gets the project start date.
	 *
	 * @return the project start date
	 */
	public Date getProjectStartDate() {
		return projectStartDate;
	}
	
	/**
	 * Sets the project start date.
	 *
	 * @param projectStartDate the new project start date
	 */
	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	
	/**
	 * Gets the project end date.
	 *
	 * @return the project end date
	 */
	public Date getProjectEndDate() {
		return projectEndDate;
	}
	
	/**
	 * Sets the project end date.
	 *
	 * @param projectEndDate the new project end date
	 */
	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
	}
	
	/**
	 * Gets the sapient lead.
	 *
	 * @return the sapient lead
	 */
	public int getSapientLead() {
		return sapientLead;
	}
	
	/**
	 * Sets the sapient lead.
	 *
	 * @param sapientLead the new sapient lead
	 */
	public void setSapientLead(int sapientLead) {
		this.sapientLead = sapientLead;
	}
	
	/**
	 * Gets the team.
	 *
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}
	
	/**
	 * Sets the team.
	 *
	 * @param team the new team
	 */
	public void setTeam(Team team) {
		this.team = team;
	}
	
	/**
	 * Gets the checks if is active.
	 *
	 * @return the checks if is active
	 */
	public Boolean getIsActive() {
		return isActive;
	}
	
	/**
	 * Sets the checks if is active.
	 *
	 * @param isActive the new checks if is active
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	/**
	 * Gets the client time tracking project name.
	 *
	 * @return the client time tracking project name
	 */
	public String getClientTimeTrackingProjectName() {
		return clientTimeTrackingProjectName;
	}
	
	/**
	 * Sets the client time tracking project name.
	 *
	 * @param clientTimeTrackingProjectName the new client time tracking project name
	 */
	public void setClientTimeTrackingProjectName(
			String clientTimeTrackingProjectName) {
		this.clientTimeTrackingProjectName = clientTimeTrackingProjectName;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return Id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		Id = id;
	}
	
}
