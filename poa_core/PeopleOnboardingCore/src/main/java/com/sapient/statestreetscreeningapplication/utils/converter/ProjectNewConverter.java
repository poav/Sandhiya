package com.sapient.statestreetscreeningapplication.utils.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.ProjectNew;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectNewConverter.
 */
public class ProjectNewConverter {

	/**
	 * Convert new project entity list to project new bean list.
	 *
	 * @param allProjects the all projects
	 * @return the list
	 */
	public static List<ProjectNewBean> convertNewProjectEntityListToProjectNewBeanList(
			List<ProjectNew> allProjects) {
		
			List<ProjectNewBean> beanList = new ArrayList<ProjectNewBean>();
			for (ProjectNew projectEntity : allProjects) {
				beanList.add(ProjectNewConverter
						.convertNewProjectEntityToProjectNewBean(projectEntity));
			}
			return beanList;
	}
	
	/**
	 * Convert new project entity to project new bean.
	 *
	 * @param projectEntity the project entity
	 * @return the project new bean
	 */
	public static ProjectNewBean convertNewProjectEntityToProjectNewBean(
			ProjectNew projectEntity) {
		ProjectNewBean projectNewBean = new ProjectNewBean();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		projectNewBean.setId(projectEntity.getId());
		projectNewBean.setClientProjectId(projectEntity.getClientProjectId());
		projectNewBean.setClientProjectName(projectEntity.getClientProjectName());
		projectNewBean.setClientTimeTrackingId(projectEntity.getClientTimeTrackingId());
		projectNewBean.setClientTimeTrackingProjectName(projectEntity.getClientTimeTrackingProjectName());
		projectNewBean.setIsActive(projectEntity.getIsActive());
		projectNewBean.setProjectId(projectEntity.getProjectId());
		projectNewBean.setProjectName(projectEntity.getProjectName());
		projectNewBean.setSapientLead(projectEntity.getSapientLead());
		projectNewBean.setTeamBean(TeamConverter.convertTeamEntityToBean(projectEntity.getTeam()));
		
		if(projectEntity.getProjectStartDate()!=null){
			projectNewBean.setProjectStartDate(formatter.format(projectEntity.getProjectStartDate()));
		}
		if(projectEntity.getProjectEndDate()!=null){
			projectNewBean.setProjectEndDate(formatter.format(projectEntity.getProjectEndDate()));
		}
		
		projectNewBean.setCostCenter(projectEntity.getCostCenter());
		
		if(projectEntity.getProjectLead()!=null){
		    projectNewBean.setProjectLead(PersonNewConverter.personNewEntityToBean(projectEntity.getProjectLead()));
		}
		return projectNewBean;
	}

	/**
	 * Convert new project bean to project new entity.
	 *
	 * @param projectNewBean the project new bean
	 * @return the project new
	 */
	public static ProjectNew convertNewProjectBeanToProjectNewEntity(
			ProjectNewBean projectNewBean) {
		ProjectNew projectNew = new ProjectNew();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		projectNew.setId(projectNewBean.getId());
		projectNew.setClientProjectId(projectNewBean.getClientProjectId());
		projectNew.setClientProjectName(projectNewBean.getClientProjectName());
		projectNew.setClientTimeTrackingId(projectNewBean.getClientTimeTrackingId());
		projectNew.setClientTimeTrackingProjectName(projectNewBean.getClientTimeTrackingProjectName());
		projectNew.setIsActive(projectNewBean.getIsActive());
		projectNew.setProjectId(projectNewBean.getProjectId());
		projectNew.setProjectName(projectNewBean.getProjectName());
		projectNew.setSapientLead(projectNewBean.getSapientLead());
		projectNew.setTeam(TeamConverter.convertTeamBeanToEntity(projectNewBean.getTeamBean()));
		
		if(projectNewBean.getProjectStartDate()!=null){
			try {
				projectNew.setProjectStartDate(formatter.parse(projectNewBean.getProjectStartDate()));
			} 
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(projectNewBean.getProjectEndDate()!=null){
			try {
				projectNew.setProjectEndDate(formatter.parse(projectNewBean.getProjectEndDate()));
			} 
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		projectNew.setCostCenter(projectNewBean.getCostCenter());
		
		if(projectNewBean.getProjectLead()!=null){
				projectNew.setProjectLead(PersonNewConverter.personBeanToEntity(projectNewBean.getProjectLead()));
		}
		
		return projectNew;
	}
}
