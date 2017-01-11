package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.List;

import org.springframework.stereotype.Component;


import com.sapient.statestreetscreeningapplication.model.entity.MonthlyProjectBudgetEntity;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.entity.ProjectNew;
import com.sapient.statestreetscreeningapplication.model.entity.Team;
import com.sapient.statestreetscreeningapplication.ui.bean.MonthlyProjectBudgetBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean;
// TODO: Auto-generated Javadoc
import com.sapient.statestreetscreeningapplication.utils.enums.Months;

/**
 * The Interface ProjectDao.
 */
@Component
public interface ProjectDao {

	/**
	 * Gets the all projects.
	 *
	 * @return the all projects
	 */
	public List<ProjectNew> getAllProjects();


	
	/**
	 * Gets the IQN names.
	 *
	 * @param projectName the project name
	 * @return the IQN names
	 */
	public List<String> getIQNNames(String projectName);

	/**
	 * Gets the atrack names by iqn.
	 *
	 * @param s the s
	 * @param projectName the project name
	 * @return the atrack names by iqn
	 */
	public List<String> getAtrackNamesByIQN(String s, String projectName);

	

	/**
	 * Save project.
	 *
	 * @param project the project
	 * @return true, if successful
	 */
	public boolean saveProject(ProjectNew project);


	/**
	 * Edits the project.
	 *
	 * @param newProject the new project
	 */
	public void editProject(ProjectNew newProject);


	/**
	 * Gets the project by project id.
	 *
	 * @param projectId the project id
	 * @return the project by project id
	 */
	public ProjectNew getProjectByProjectId(long projectId);


	/**
	 * Gets the all used project names.
	 *
	 * @return the all used project names
	 */
	public List<String> getAllUsedProjectNames();


	/**
	 * Gets the new project by name.
	 *
	 * @param projectName the project name
	 * @return the new project by name
	 */
	public ProjectNew getNewProjectByName(String projectName);


	/**
	 * Fetch project by team and project name.
	 *
	 * @param projectName the project name
	 * @param team the team
	 * @return the project new
	 */
	public ProjectNew fetchProjectByTeamAndProjectName(String projectName, Team team);

	public void saveProjectList(List<ProjectNew> projectList);

	public void saveMonthlyProjectBudget(MonthlyProjectBudgetEntity monthlyProjectBudgetEntity);
	
	public void editMonthlyProjectBudget(MonthlyProjectBudgetBean monthlyProjectBudgetBean);
	
	public void deleteMonthlyProjectBudget(MonthlyProjectBudgetBean monthlyProjectBudgetBean);

	public MonthlyProjectBudgetEntity getProjectMonthlyBudget(int projectIdentity, Months month, int year);

	public PersonStaffing staffingOfMaximumStartDate(ProjectNew newProject);

	public PersonStaffing staffingOfMinimumStartDate(ProjectNew newProject);

	public List<Integer> getYearList();

	public void changeStaffingDate(PersonStaffing personStaffing);
	
	public PersonStaffing mergeStaffingNew(PersonStaffing personStaffing);

	public ProjectNew getProjectNewEntityByName(String projectName);

	public List<ProjectNewBean> getAllProjectBeansforTeamNameDB(Team team);

}
