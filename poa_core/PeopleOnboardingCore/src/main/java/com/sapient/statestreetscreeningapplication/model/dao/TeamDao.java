package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.Team;
import com.sapient.statestreetscreeningapplication.model.entity.TeamLeadEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface TeamDao.
 */
public interface TeamDao {

/**
 * Save team.
 *
 * @param team the team
 * @return true, if successful
 */
public boolean saveTeam(Team team);

/**
 * Gets the all team list.
 *
 * @return the all team list
 */
public List<Team> getAllTeamList();

/**
 * Edits the team.
 *
 * @param team the team
 */
public void editTeam(Team team);

/**
 * Gets the all team name list.
 *
 * @return the all team name list
 */
public List<String> getAllTeamNameList();

/**
 * Gets the team by name.
 *
 * @param teamName the team name
 * @return the team by name
 */
public Team getTeamByName(String teamName);

public TeamLeadEntity mergeTeamLead(TeamLeadEntity teamleadEntity);

public List<TeamLeadEntity> getTeamLeadByTeam(Team team);

public Boolean removeTeamLead(TeamLeadEntity teamleadEntity);

public int getCountOfteamName(String teamName);

public Team getTeamForTeamName(String teamName);

}
