package com.sapient.statestreetscreeningapplication.ui.bean;

public class TeamLeadBean {
	
	private Long defaultTeamLeadId;
	
	private PersonNewBean lead;
	
	private TeamBean team;

	public Long getDefaultTeamLeadId() {
		return defaultTeamLeadId;
	}

	public void setDefaultTeamLeadId(Long defaultTeamLeadId) {
		this.defaultTeamLeadId = defaultTeamLeadId;
	}

	public PersonNewBean getLead() {
		return lead;
	}

	public void setLead(PersonNewBean lead) {
		this.lead = lead;
	}

	public TeamBean getTeam() {
		return team;
	}

	public void setTeam(TeamBean team) {
		this.team = team;
	}
}
