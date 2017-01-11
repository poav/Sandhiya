package com.sapient.statestreetscreeningapplication.model.service;

import java.io.File;
import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.Team;
import com.sapient.statestreetscreeningapplication.model.entity.TeamLeadEntity;
import com.sapient.statestreetscreeningapplication.ui.bean.TeamBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface TeamService.
 */
public interface TeamService {
	
	/**
	 * Save teams.
	 *
	 * @param teamBean the team bean
	 * @return true, if successful
	 */
	public boolean saveTeam(TeamBean teamBean);

	/**
	 * Gets the all team list.
	 *
	 * @return the all team list
	 */
	public List<TeamBean> getAllTeamList();

	/**
	 * Edits the team.
	 *
	 * @param teamBean the team bean
	 */
	public void editTeam(TeamBean teamBean);

	/**
	 * Save team batch details.
	 *
	 * @param teamBatch the team batch
	 */
	public void saveTeamBatchDetails(File teamBatch);

	/**
	 * Gets the all team name list.
	 *
	 * @return the all team name list
	 */
	public List<String> getAllTeamNameList();

	/**
	 * Fetch team by name.
	 *
	 * @param string the string
	 * @return the team bean
	 */
	public TeamBean fetchTeamByName(String string);

	/**
	 * Fetch project names for team.
	 *
	 * @param accountDivision the account division
	 * @return the list
	 */
	public List<String> fetchProjectNamesForTeam(String accountDivision);

	public TeamLeadEntity mergeTeamLead(TeamLeadEntity teamleadEntity);

	public List<TeamLeadEntity> getTeamLeadByTeam(Team team);

	public Boolean removeTeamLead(TeamLeadEntity teamleadEntity);

	public boolean ifDuplicateNameExists(String teamName);


	public TeamBean getTeamForTeamName(String teamName);

}
