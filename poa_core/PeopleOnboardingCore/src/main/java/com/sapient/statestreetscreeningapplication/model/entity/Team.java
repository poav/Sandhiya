package com.sapient.statestreetscreeningapplication.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


// TODO: Auto-generated Javadoc
/**
 * The Class Team.
 */
@Entity
@Table(name = "TEAM")
public class Team {

	/** The team id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TEAM_ID")
	private Long teamId;
	
	/** The team name. */
	@Column(name = "TEAM_NAME")
	private String teamName;
	
	/** The project list. */
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ProjectNew> projectList=new ArrayList<ProjectNew>();
	
	/** The is active. */
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	/**
	 * Gets the team id.
	 *
	 * @return the team id
	 */
	public Long getTeamId() {
		return teamId;
	}
	
	/**
	 * Sets the team id.
	 *
	 * @param teamId the new team id
	 */
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * Gets the team name.
	 *
	 * @return the team name
	 */
	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * Sets the team name.
	 *
	 * @param teamName the new team name
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	/**
	 * Gets the project list.
	 *
	 * @return the project list
	 */
	public List<ProjectNew> getProjectList() {
		return projectList;
	}
	
	/**
	 * Sets the project list.
	 *
	 * @param projectList the new project list
	 */
	public void setProjectList(List<ProjectNew> projectList) {
		this.projectList = projectList;
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
}
