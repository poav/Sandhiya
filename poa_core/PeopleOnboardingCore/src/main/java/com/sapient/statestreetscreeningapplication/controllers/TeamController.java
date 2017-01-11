package com.sapient.statestreetscreeningapplication.controllers;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sapient.statestreetscreeningapplication.model.service.TeamService;
import com.sapient.statestreetscreeningapplication.ui.bean.TeamBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

@RestController
public class TeamController {
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	Gson gson;
	
	@CrossOrigin
	@RequestMapping("/displayAllteams")
	public List<TeamBean> viewAllTeamBeans()
	{
		CustomLoggerUtils.INSTANCE.log.info("inside method viewAllTeamBeans() to displayAllTeamsList");
		return teamService.getAllTeamList();
	}
	
	@CrossOrigin
	@RequestMapping("/getTeamBean")
	public TeamBean getTeamBean()
	{
		CustomLoggerUtils.INSTANCE.log.info("inside method getTeamBean() to get Empty TeamBean");
		return new TeamBean();
	}
	
	@CrossOrigin
	@RequestMapping("/retrieveAllTeamNames")
	public List<String> viewAllTeamNames()
	{
		CustomLoggerUtils.INSTANCE.log.info("inside method viewAllTeamNames() to get All List of Team Names");

		return teamService.getAllTeamNameList();
	}
	
	@CrossOrigin
	@RequestMapping("/checkDuplicateName")
	public boolean checkDuplicateName(@RequestParam("teamName") String teamName )
	{
		CustomLoggerUtils.INSTANCE.log.info("inside method viewAllTeamNames() to get All List of Team Names");
		return teamService.ifDuplicateNameExists(teamName);
	}
	
	@CrossOrigin
	@RequestMapping("/getTeamForTeamName")
	public TeamBean getTeamForTeamName(@RequestParam("teamName") String teamName )
	{
		CustomLoggerUtils.INSTANCE.log.info("inside method getTeamForTeamName() to get teamBean for Team Name: "+ teamName);
		return teamService.getTeamForTeamName(teamName);
	}

	
	@CrossOrigin
	@RequestMapping(value = "/editTeam", method = RequestMethod.POST)
	public boolean editTeam(@RequestBody TeamBean teamBean)
	{
		CustomLoggerUtils.INSTANCE.log.info("inside method editTeam() to get teamBean for Team Bean: id"+  teamBean.getTeamId()+" Name: "+teamBean.getTeamName());

		TeamBean teamBeanOld =teamService.getTeamForTeamName(teamBean.getTeamName());
		
		if(teamBeanOld.getTeamId()==teamBean.getTeamId())
		{
			CustomLoggerUtils.INSTANCE.log.info("inside method editTeam() and team name is not duplicate for Team Bean with Id"+  teamBean.getTeamId()+" Name: "+teamBean.getTeamName());

			teamService.editTeam(teamBean);
			CustomLoggerUtils.INSTANCE.log.info("inside method editTeam() and team Bean is edited successfully");

			return true;
		}
		
		return false;

	}
	
	@CrossOrigin
	@RequestMapping(value = "/addTeam", method = RequestMethod.POST)
	public void addTeam(@RequestBody TeamBean teamBean)
	{
		CustomLoggerUtils.INSTANCE.log.info("inside method addTeam() and team Bean : Id"+teamBean.getTeamId()+" Name: "+teamBean.getTeamName());

		if(!teamService.ifDuplicateNameExists(teamBean.getTeamName()))
		{
			CustomLoggerUtils.INSTANCE.log.info("inside method addTeam() and team Bean name is not duplicate for team Bean  : Id"+teamBean.getTeamId()+" Name: "+teamBean.getTeamName());
			teamService.saveTeam(teamBean);		
		}
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/addTeamBatch", method = RequestMethod.POST)
	public void saveTeamBatch(@RequestBody File teamBatch)
	{

		teamService.saveTeamBatchDetails(teamBatch);
	}

}
