package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.dao.TeamDao;
import com.sapient.statestreetscreeningapplication.model.entity.ProjectNew;
import com.sapient.statestreetscreeningapplication.model.entity.Team;
import com.sapient.statestreetscreeningapplication.model.entity.TeamLeadEntity;
import com.sapient.statestreetscreeningapplication.model.service.TeamService;
import com.sapient.statestreetscreeningapplication.ui.bean.TeamBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.TeamConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class TeamServiceImpl.
 */
@Service
public class TeamServiceImpl implements TeamService {

/** The team dao. */
@Autowired
TeamDao teamDao;
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.TeamService#saveTeams(com.sapient.statestreetscreeningapplication.ui.bean.TeamBean)
	 */
	@Override
	public boolean saveTeam(TeamBean teamBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveTeams(TeamBean teamBean) method of TeamServiceImpl class. Team name is: "+  teamBean.getTeamName());
		return teamDao.saveTeam(TeamConverter.convertTeamBeanToEntity(teamBean));
		
	}
	
	/**
	 * Gets the team dao.
	 *
	 * @return the team dao
	 */
	public TeamDao getTeamDao() {
		return teamDao;
	}
	
	/**
	 * Sets the team dao.
	 *
	 * @param teamDao the new team dao
	 */
	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.TeamService#getAllTeamList()
	 */
	@Override
	public List<TeamBean> getAllTeamList() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllTeamList() method of TeamServiceImpl class");
		return TeamConverter.convertTeamEntityListToTeamBeanList(teamDao.getAllTeamList());
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.TeamService#editTeam(com.sapient.statestreetscreeningapplication.ui.bean.TeamBean)
	 */
	@Override
	public void editTeam(TeamBean teamBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside editTeam(TeamBean teamBean) method of TeamServiceImpl class for Team bean , Id: "+ teamBean.getTeamId()+" name: "+ teamBean.getTeamName());
		teamDao.editTeam(TeamConverter.convertTeamBeanToEntity(teamBean));
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.TeamService#saveTeamBatchDetails(java.io.File)
	 */
	@Override
	public void saveTeamBatchDetails(File teamBatch) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveTeamBatchDetails(File teamBatch) method of TeamServiceImpl class");
	        readFile(teamBatch
				.getPath());
		
	}
	
	/**
	 * Read file.
	 *
	 * @param path the path
	 */
	private void readFile(String path) {
		CustomLoggerUtils.INSTANCE.log.info("inside readFile(String path) method of TeamServiceImpl class");
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {

			br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] team = line.split(cvsSplitBy);
				Team newTeam = new Team();
				
				newTeam.setIsActive(true);
				newTeam.setTeamName(team[0]);
				teamDao.saveTeam(newTeam);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.TeamService#getAllTeamNameList()
	 */
	@Override
	public List<String> getAllTeamNameList() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllTeamNameList() method of TeamServiceImpl class");
		return teamDao.getAllTeamNameList();
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.TeamService#fetchTeamByName(java.lang.String)
	 */
	@Override
	public TeamBean fetchTeamByName(String teamName) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchTeamByName(String teamName) method of TeamServiceImpl class. team name is : "+  teamName);
		return TeamConverter.convertTeamEntityToBean(teamDao.getTeamByName(teamName));
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.TeamService#fetchProjectNamesForTeam(java.lang.String)
	 */
	@Override
	public List<String> fetchProjectNamesForTeam(String accountDivision) {
		CustomLoggerUtils.INSTANCE.log.info("inside  fetchProjectNamesForTeam(String accountDivision) method of TeamServiceImpl class");
		List<String> projectNameList=new ArrayList<String>();
		Team team=teamDao.getTeamByName(accountDivision);
		for(ProjectNew project:team.getProjectList())
		{   if(project.getIsActive()) 
			projectNameList.add(project.getProjectName());
		}
		return projectNameList;
	}

	@Override
	public TeamLeadEntity mergeTeamLead(TeamLeadEntity teamleadEntity) {
		CustomLoggerUtils.INSTANCE.log.info("inside  mergeTeamLead(TeamLeadEntity teamleadEntity) method of TeamServiceImpl class");	
		return teamDao.mergeTeamLead(teamleadEntity);
	}

	@Override
	public List<TeamLeadEntity> getTeamLeadByTeam(Team team) {
		CustomLoggerUtils.INSTANCE.log.info("inside  getTeamLeadByTeam(Team team) method of TeamServiceImpl class");	
		return teamDao.getTeamLeadByTeam(team);
	}

	@Override
	public Boolean removeTeamLead(TeamLeadEntity teamleadEntity) {
		CustomLoggerUtils.INSTANCE.log.info("inside  removeTeamLead(TeamLeadEntity teamleadEntity) method of TeamServiceImpl class");
		return teamDao.removeTeamLead(teamleadEntity);
	}

	@Override
	public boolean ifDuplicateNameExists(String teamName) {
		// TODO Auto-generated method stub
		int count= teamDao.getCountOfteamName(teamName);
		if(count>0)
		{
			return true;
		}
		
		return false;
		
	}

	@Override
	public TeamBean getTeamForTeamName(String teamName) {
		// TODO Auto-generated method stub
		return TeamConverter.convertTeamEntityToBean(teamDao.getTeamForTeamName(teamName));
	}
	
}
