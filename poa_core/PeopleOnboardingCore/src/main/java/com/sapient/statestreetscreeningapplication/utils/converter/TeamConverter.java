package com.sapient.statestreetscreeningapplication.utils.converter;

import java.util.ArrayList;
import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.Team;
import com.sapient.statestreetscreeningapplication.ui.bean.TeamBean;

// TODO: Auto-generated Javadoc
/**
 * The Class TeamConverter.
 */
public class TeamConverter {
	
	/**
	 * Convert team entity to bean.
	 *
	 * @param team the team
	 * @return the team bean
	 */
	public static TeamBean convertTeamEntityToBean(Team team) {
		TeamBean teamBean = new TeamBean();
		teamBean.setIsActive(team.getIsActive());
		teamBean.setTeamId(team.getTeamId());
		teamBean.setTeamName(team.getTeamName());
		return teamBean;

	}

	/**
	 * Convert team bean to entity.
	 *
	 * @param teamBean the team bean
	 * @return the team
	 */
	public static Team convertTeamBeanToEntity(TeamBean teamBean) {
		Team team = new Team();
		team.setIsActive(teamBean.getIsActive());
		team.setTeamId(teamBean.getTeamId());
		team.setTeamName(teamBean.getTeamName());
		return team;

	}

	/**
	 * Convert team entity list to team bean list.
	 *
	 * @param allTeamList the all team list
	 * @return the list
	 */
	public static List<TeamBean> convertTeamEntityListToTeamBeanList(
			List<Team> allTeamList) {
		List<TeamBean> teamBeanlist = new ArrayList<TeamBean>();
		for (Team team : allTeamList) {
			teamBeanlist.add(convertTeamEntityToBean(team));
		}
		return teamBeanlist;
	}
}
