package com.sapient.statestreetscreeningapplication.model.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.entity.MonthlyProjectBudgetEntity;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.entity.ProjectNew;
import com.sapient.statestreetscreeningapplication.ui.bean.MonthlyProjectBudgetBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.TeamBean;
// TODO: Auto-generated Javadoc
import com.sapient.statestreetscreeningapplication.utils.enums.Months;

/**
 * The Interface ProjectService.
 */
@Service
public interface ProjectService {
	
	/**
	 * Gets the all projects.
	 *
	 * @return the all projects
	 */
	public List<String> getAllProjects();

	/**
	 * Gets the all project beans.
	 *
	 * @return the all project beans
	 */
	public List<ProjectNewBean> getAllProjectBeans();

	/**
	 * Save project batch details.
	 *
	 * @param projectBatch the project batch
	 */
	public void saveProjectBatchDetails(File projectBatch);//to do test case.

	/**
	 * Change state.
	 *
	 * @param projectId the project id
	 * @param state the state
	 * @return true, if successful
	 */
	public boolean changeState(long projectId, int state);

	/**
	 * Gets the all used projects.
	 *
	 * @return the all used projects
	 */
	public List<String> getAllUsedProjects();
	
	/**
	 * Gets the project by id.
	 *
	 * @param id the id
	 * @return the project by id
	 */
	public ProjectNewBean getProjectById(long id);

	/**
	 * Fetch iqn names.
	 *
	 * @param projectName the project name
	 * @return the list
	 */
	public List<String> fetchIQNNames(String projectName);

	/**
	 * Fetch atrack names for iqn.
	 *
	 * @param iQNName the i qn name
	 * @param projectName the project name
	 * @return the list
	 */
	public List<String> fetchAtrackNamesForIQN(String iQNName, String projectName);


	/**
	 * Gets the project by name.
	 *
	 * @param teamName the team name
	 * @return the project by name
	 */
	public ProjectBean getProjectByName(String teamName);

	/**
	 * Gets the all accounts.
	 *
	 * @return the all accounts
	 */
	public List<String> getAllAccounts();

	/**
	 * Fetch team names.
	 *
	 * @param accountDivision the account division
	 * @return the list
	 */
	public List<String> fetchTeamNames(String accountDivision);

	/**
	 * Save project details.
	 *
	 * @param project the project
	 * @return true, if successful
	 */
	public boolean saveProjectDetails(ProjectNewBean project);

	/**
	 * Edits the project.
	 *
	 * @param projectBean the project bean
	 * @return 
	 */
	public boolean editProject(ProjectNewBean projectBean);

	/**
	 * Gets the all used project names.
	 *
	 * @return the all used project names
	 */
	public List<String> getAllUsedProjectNames();

	/**
	 * Gets the new project name.
	 *
	 * @param teamName the team name
	 * @return the new project name
	 */
	public ProjectNewBean getNewProjectName(String teamName);

	public ProjectNewBean fetchProjectByTeamAndProjectName(String projectName, TeamBean team);

	public void saveMonthlyProjectBudget(MonthlyProjectBudgetEntity monthlyProjectBudgetEntity);
	
	public void editMonthlyProjectBudget(MonthlyProjectBudgetBean monthlyProjectBudgetBean);
	
	public void deleteMonthlyProjectBudget(MonthlyProjectBudgetBean monthlyProjectBudgetBean);

	public MonthlyProjectBudgetEntity getProjectMonthlyBudget(int projectIdentity, Months month, int year);

	public Date minimumProjectEndDate(ProjectNew newProject);
	
	public Date maximumProjectStartDate(ProjectNew newProject);

	public List<Integer> getYearList();

	public List<MonthlyProjectBudgetEntity> getMonthlyProjectBudgetThisYearThisProject(int year, ProjectNew projectNew);

	public void changeStaffingDate(PersonStaffing personStaffing);
	
	public PersonStaffing mergeStaffingNew(PersonStaffing personStaffing);

	public ProjectNew getProjectNewEntityByName(String projectName);

	public List<ProjectNewBean> getAllProjectBeansforTeamName(String teamName);
	
}
