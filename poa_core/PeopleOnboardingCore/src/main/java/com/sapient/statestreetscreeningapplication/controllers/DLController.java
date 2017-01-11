package com.sapient.statestreetscreeningapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sapient.statestreetscreeningapplication.model.service.DLService;
import com.sapient.statestreetscreeningapplication.model.service.LocationService;
import com.sapient.statestreetscreeningapplication.model.service.ProjectService;
import com.sapient.statestreetscreeningapplication.ui.bean.EmailDlBean;
import com.sapient.statestreetscreeningapplication.ui.bean.LocationNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean;

@RestController
public class DLController {
	
	@Autowired
	DLService dLService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	LocationService locationService;
	
	Gson gson = new Gson();
	
	@CrossOrigin
	@RequestMapping("/retrieveAllDls")
	public List<EmailDlBean> viewAllDLBeans()
	{
		return dLService.getCompleteDLList();
	}
	
	@CrossOrigin
	@RequestMapping("/getEmailDlBean")
	public EmailDlBean getEmailDlBean()
	{
		EmailDlBean emailDlBean= new EmailDlBean();
		emailDlBean.setLocationBean(new LocationNewBean());
		emailDlBean.setProjectBean(new ProjectNewBean());
		
		return emailDlBean;
	
	}
	
	@CrossOrigin
	@RequestMapping("/retrieveDlForLocation")
	public List<EmailDlBean> viewAllDLBeansForLocation(@RequestParam("projectId") long projectId,
			@RequestParam("locationId") long locationId)
	{
		return dLService.getActiveDlBeansByProjectIdAndLocationId(projectId, locationId);
	}
	
	
	@CrossOrigin
	@RequestMapping("/getDlByDlNameAndProjectIdAndLocationId")
	public EmailDlBean getDLBeanForDlName(@RequestParam("dlName") String dlName, @RequestParam("projectId") long projectId, @RequestParam("locationId") long locationId)
	{
		return dLService.getDlByDlNameAndProjectIdAndLocationId(dlName, projectId, locationId);
	}
	
	
	
	@CrossOrigin
	@RequestMapping(value = "/editDL", method = RequestMethod.POST)
	public void editDL(@RequestBody String str)
	{	
		EmailDlBean emailDlBean= gson.fromJson(str, EmailDlBean.class);
		dLService.editDl(emailDlBean, emailDlBean.getLocationBean().getCity(), emailDlBean.getProjectBean().getProjectName(), emailDlBean.getEmail());
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/saveDL", method = RequestMethod.POST)
	public void saveDL(@RequestBody String str)
	{	
		EmailDlBean emailDlBean= gson.fromJson(str, EmailDlBean.class);
		dLService.saveDl(emailDlBean.getLocationBean().getCity(), emailDlBean.getProjectBean().getProjectName(), emailDlBean.getEmail(),emailDlBean.getProjectBean().getTeamBean().getTeamName());
		
	}
	
	@CrossOrigin
	@RequestMapping("/getDlListByProjectNameAndLocation")
	public List<EmailDlBean> getDLBeanListForProjectNameAndLocation(@RequestParam("projectName") String projectName, @RequestParam("location") String location)
	{
		long projectId=projectService.getProjectNewEntityByName(projectName).getId();
		long locationId=locationService.getNewLocationByName(location).getLocationId();
		return dLService.getActiveDlBeansByProjectIdAndLocationId(projectId, locationId);
	}

}
