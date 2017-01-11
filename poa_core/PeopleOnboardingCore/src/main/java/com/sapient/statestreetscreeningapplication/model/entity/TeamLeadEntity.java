package com.sapient.statestreetscreeningapplication.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TEAM_LEAD")
public class TeamLeadEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TEAM_LEAD_ID")
	private Long defaultTeamLeadId;
	
	@OneToOne
	@JoinColumn(name="PERSON_ID")
	private Person lead;
	
	@OneToOne
	@JoinColumn(name="TEAM_ID")
	private Team team;

	public Long getDefaultTeamLeadId() {
		return defaultTeamLeadId;
	}

	public void setDefaultTeamLeadId(Long defaultTeamLeadId) {
		this.defaultTeamLeadId = defaultTeamLeadId;
	}

	public Person getLead() {
		return lead;
	}

	public void setLead(Person lead) {
		this.lead = lead;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
}
