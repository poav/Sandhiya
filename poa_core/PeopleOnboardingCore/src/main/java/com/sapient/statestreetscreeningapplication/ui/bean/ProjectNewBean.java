package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.Date;

import com.sapient.statestreetscreeningapplication.model.entity.Person;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectNewBean.
 */
public class ProjectNewBean {
	
	@Override
	public String toString() {
		return "ProjectNewBean [Id=" + Id + ", projectId=" + projectId
				+ ", projectName=" + projectName + ", clientProjectId="
				+ clientProjectId + ", clientTimeTrackingId="
				+ clientTimeTrackingId + ", clientTimeTrackingProjectName="
				+ clientTimeTrackingProjectName + ", clientProjectName="
				+ clientProjectName + ", projectStartDate=" + projectStartDate
				+ ", projectEndDate=" + projectEndDate + ", sapientLead="
				+ sapientLead + ", teamBean=" + teamBean + ", isActive="
				+ isActive + "]";
	}

	/** The Id. */
	private long Id;
	
	/** The project id. */
	private int projectId;
	
	/** The project name. */
	private String projectName;
	
	/** The client project id. */
	private int clientProjectId;
	
	/** The client time tracking id. */
	private String clientTimeTrackingId;
	
	/** The client time tracking project name. */
	private String clientTimeTrackingProjectName;
	
	/** The client project name. */
	private String clientProjectName;
	
	/** The project start date. */
	private String projectStartDate;
	
	/** The project end date. */
	private String projectEndDate;
	
	/** The sapient lead. */
	private int sapientLead;
	
	/** The team bean. */
	private TeamBean teamBean;
	
	/** The is active. */
	private Boolean isActive;
	
	private String costCenter;
	
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
	public String getProjectStartDate() {
		return projectStartDate;
	}
	
	/**
	 * Sets the project start date.
	 *
	 * @param projectStartDate the new project start date
	 */
	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	
	/**
	 * Gets the project end date.
	 *
	 * @return the project end date
	 */
	public String getProjectEndDate() {
		return projectEndDate;
	}
	
	/**
	 * Sets the project end date.
	 *
	 * @param projectEndDate the new project end date
	 */
	public void setProjectEndDate(String projectEndDate) {
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
	 * Gets the team bean.
	 *
	 * @return the team bean
	 */
	public TeamBean getTeamBean() {
		return teamBean;
	}
	
	/**
	 * Sets the team bean.
	 *
	 * @param teamBean the new team bean
	 */
	public void setTeamBean(TeamBean teamBean) {
		this.teamBean = teamBean;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + projectId;
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
		ProjectNewBean other = (ProjectNewBean) obj;
		if (projectId != other.projectId)
			return false;
		return true;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		Id = id;
	}
	
	private PersonNewBean projectLead;

	public PersonNewBean getProjectLead() {
		return projectLead;
	}

	public void setProjectLead(PersonNewBean projectLead) {
		this.projectLead = projectLead;
	}

}
