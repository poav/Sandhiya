package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew;
import com.sapient.statestreetscreeningapplication.model.dao.ProjectDao;
import com.sapient.statestreetscreeningapplication.model.dao.TeamDao;
import com.sapient.statestreetscreeningapplication.model.entity.MonthlyProjectBudgetEntity;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.entity.ProjectNew;
import com.sapient.statestreetscreeningapplication.model.service.ProjectService;
import com.sapient.statestreetscreeningapplication.model.service.TeamService;
import com.sapient.statestreetscreeningapplication.ui.bean.MonthlyProjectBudgetBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.TeamBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.MonthlyProjectBudgetConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.ProjectNewConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.TeamConverter;
import com.sapient.statestreetscreeningapplication.utils.enums.Months;
import com.sapient.statestreetscreeningapplication.utils.enums.ProjectBudgetType;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectServiceImpl.
 */
@Service
public class ProjectServiceImpl implements ProjectService {
	
	/** The project dao. */
	@Autowired
	private ProjectDao projectDao;
	
	/** The team service. */
	@Autowired
	private TeamService teamService;
	
	public TeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}
	
	@Autowired
	private TeamDao teamDao;

	public TeamDao getTeamDao() {
		return teamDao;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}
	
	@Autowired
	private PersonDaoNew personDaoNew;
	
	public PersonDaoNew getPersonDaoNew() {
		return personDaoNew;
	}

	public void setPersonDaoNew(PersonDaoNew personDaoNew) {
		this.personDaoNew = personDaoNew;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.ProjectService#getAllProjects()
	 */
	@Override
	public List<String> getAllProjects() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllProjects() method of ProjectServiceImpl class");
		List<ProjectNewBean> beanList = ProjectNewConverter
				.convertNewProjectEntityListToProjectNewBeanList(projectDao
						.getAllProjects());
		List<String> projectList = new ArrayList<String>();
		for (ProjectNewBean bean : beanList) {
			projectList.add(bean.getProjectName());
		}
		return projectList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.ProjectService#getAllProjectBeans()
	 */
	@Override
	public List<ProjectNewBean> getAllProjectBeans() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllProjectBeans() method of ProjectServiceImpl class");
		return ProjectNewConverter
				.convertNewProjectEntityListToProjectNewBeanList(projectDao
						.getAllProjects());
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.ProjectService#saveProjectDetails(com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean)
	 */
	@Override
	public boolean saveProjectDetails(ProjectNewBean project) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveProjectDetails(ProjectNewBean project) method of ProjectServiceImpl class");
		if (project != null) {
			return projectDao.saveProject(getProject(project));
		}
		return false;
	}

	/**
	 * Gets the project.
	 *
	 * @param project the project
	 * @return the project
	 */
	ProjectNew getProject(ProjectNewBean project) {
		CustomLoggerUtils.INSTANCE.log.info("inside getProject(ProjectNewBean project) method of ProjectServiceImpl class");
		return ProjectNewConverter.convertNewProjectBeanToProjectNewEntity(project);
	}

	

	/**
	 * Read file and create entity list.
	 *
	 * @param csvFile the csv file
	 * @return the list
	 */
	private List<ProjectNew> readFileAndCreateEntityList(String csvFile) {
		
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<ProjectNew> projectList = new ArrayList<ProjectNew>();

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] project = line.split(cvsSplitBy);
				ProjectNew  newProject = new ProjectNew();
				newProject.setProjectId(Integer.parseInt(project[0]));
				if(project[1]!=null)
				newProject.setTeam(TeamConverter.convertTeamBeanToEntity(teamService.fetchTeamByName(project[1])));
				/*newProject.setPID(Integer.parseInt(project[0]));
				newProject.setAccountDivision(project[1]);*/
				newProject.setProjectName(project[2]);
				newProject.setClientProjectId(Integer.parseInt(project[3]));
				newProject.setClientProjectName(project[4]);
				newProject.setClientTimeTrackingId(project[5]);
				newProject.setClientTimeTrackingProjectName(project[6]);
				projectList.add(newProject);
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
		return projectList;
	}

	

	/**
	 * Gets the project dao.
	 *
	 * @return the project dao
	 */
	public ProjectDao getProjectDao() {
		return projectDao;
	}

	/**
	 * Sets the project dao.
	 *
	 * @param projectDao the new project dao
	 */
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.ProjectService#fetchIQNNames(java.lang.String)
	 */
	@Override
	public List<String> fetchIQNNames(String projectName) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchIQNNames(String projectName) method of ProjectServiceImpl class");
		return projectDao.getIQNNames(projectName);
	}
	
	

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.ProjectService#fetchAtrackNamesForIQN(java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> fetchAtrackNamesForIQN(String iQNName,String projectName) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchAtrackNamesForIQN(String iQNName,String projectName) method of ProjectServiceImpl class");
		return projectDao.getAtrackNamesByIQN(iQNName,projectName);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.ProjectService#editProject(com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean)
	 */
	@Override
	public boolean editProject(ProjectNewBean projectBean) {
		boolean edited = false;
		CustomLoggerUtils.INSTANCE.log.info("inside editProject(ProjectNewBean projectBean) method of ProjectServiceImpl class");
		ProjectNew newProject = ProjectNewConverter.convertNewProjectBeanToProjectNewEntity(projectBean);
		Date minimumProjectEndDate = minimumProjectEndDate(newProject);
		Date maximumProjectStartDate = maximumProjectStartDate(newProject);
		if(minimumProjectEndDate==null && maximumProjectStartDate==null ){
		projectDao.editProject(newProject);
		edited = true;
		return edited;
		}
		if((!newProject.getProjectStartDate().after(maximumProjectStartDate))&&(!minimumProjectEndDate.after(newProject.getProjectEndDate()))){
		projectDao.editProject(newProject);
		edited = true;
		return edited;
		}
		return edited;
	}

	@Override
	public Date minimumProjectEndDate(ProjectNew newProject) {
		PersonStaffing personStaffing = projectDao.staffingOfMaximumStartDate(newProject);
		if(personStaffing==null){
		return null;
		}
		return personStaffing.getStartDate();
	}
	
	@Override
	public Date maximumProjectStartDate(ProjectNew newProject) {
		PersonStaffing personStaffing = projectDao.staffingOfMinimumStartDate(newProject);
		if(personStaffing==null){
		return null;
		}
		return personStaffing.getStartDate();
	}
	
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.ProjectService#getAllUsedProjectNames()
	 */
	@Override
	public List<String> getAllUsedProjectNames() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllUsedProjectNames() method of ProjectServiceImpl class");
	
		return projectDao.getAllUsedProjectNames();
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.ProjectService#getNewProjectName(java.lang.String)
	 */
	@Override
	public ProjectNewBean getNewProjectName(String teamName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getNewProjectName(String teamName) method of ProjectServiceImpl class");
		
		return ProjectNewConverter.convertNewProjectEntityToProjectNewBean(projectDao.getNewProjectByName(teamName));
	}
	@Override
	public ProjectNewBean fetchProjectByTeamAndProjectName( String projectName, TeamBean team) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchProjectByTeamAndProjectName( String projectName, TeamBean team) method of ProjectServiceImpl class");

		return ProjectNewConverter.convertNewProjectEntityToProjectNewBean
				(projectDao.fetchProjectByTeamAndProjectName(projectName, TeamConverter.convertTeamBeanToEntity(team)));
	}

	@Override
	@Transactional
	public void saveProjectBatchDetails(File projectBatch) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveProjectBatchDetails(File projectBatch) method of ProjectServiceImpl class");

		// for csv file
		String csvFile = projectBatch.getPath();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		ProjectNew projectExisting;
		List<ProjectNew> projectList = new ArrayList<ProjectNew>();

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				
				int startYear=0;
				int startMonth=0;
				int endYear=0;
				int endMonth=0;
				boolean unique = false;
				ProjectNewBean projectNewBean = new ProjectNewBean();
				
				SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
				// use comma as separator
				String[] project = line.split(cvsSplitBy);
				projectExisting = projectDao.getProjectByProjectId(Integer.parseInt(project[0]));
				if (projectExisting == null && teamDao.getTeamByName(project[1])!=null) {
					ProjectNew newProject = new ProjectNew();
					newProject.setProjectId(Integer.parseInt(project[0]));
					newProject.setTeam(teamDao.getTeamByName(project[1]));
					newProject.setProjectName(project[2]);
					newProject.setClientProjectId(Integer.parseInt(project[3]));
					newProject.setClientProjectName(project[4]);
					newProject.setClientTimeTrackingId(project[5]);
					newProject.setClientTimeTrackingProjectName(project[6]);
					newProject.setCostCenter(project[7]);
					if(project.length>9){
					try {
						newProject.setProjectStartDate(formatter1.parse(project[8]));
						newProject.setProjectEndDate(formatter1.parse(project[9]));
						
						projectNewBean = ProjectNewConverter.convertNewProjectEntityToProjectNewBean(newProject);
						
						DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
						Calendar calendar = Calendar.getInstance(); 
						
						calendar.setTime(formatter.parse(projectNewBean.getProjectStartDate()));
				    	startYear = calendar.get(Calendar.YEAR);
				    	startMonth = calendar.get(Calendar.MONTH);
						
				    	calendar.setTime(formatter.parse(projectNewBean.getProjectEndDate()));
				    	endYear = calendar.get(Calendar.YEAR);
				    	endMonth = calendar.get(Calendar.MONTH);
				    	if((!projectNewBean.getProjectName().equals(""))&&(!projectNewBean.getClientProjectName().equals(""))&&(newProject.getProjectStartDate().before(newProject.getProjectEndDate()))){
				    		unique = saveProjectDetails(projectNewBean);	
				    	}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 if(unique){
				        	
				    		int indexYear = startYear;
				    		int indexMonth=startMonth;
				    		
				    		for(;((indexYear<endYear)||((indexYear==endYear)&&(indexMonth<=endMonth)));){
				    			
				    			{
				    			System.out.println("month= "+indexMonth+" year= "+indexYear );
				    			Months enumMonth = Months.getMonth(indexMonth);
				    			MonthlyProjectBudgetBean monthlyProjectBudgetBean = new MonthlyProjectBudgetBean();
				    			monthlyProjectBudgetBean.setProjectNewBean(projectNewBean);
				    			monthlyProjectBudgetBean.setMonth(enumMonth);
				    			monthlyProjectBudgetBean.setYear(indexYear);
				    			monthlyProjectBudgetBean.setProjectMonthlyBudgetType(ProjectBudgetType.Labour);
				    			monthlyProjectBudgetBean.setProjectMonthlyBudgetValue(0);
				    			saveMonthlyProjectBudget(MonthlyProjectBudgetConverter.MonthlyProjectBudgetBeanToEntity(monthlyProjectBudgetBean));
				    			}
				    			
				    			indexMonth = indexMonth+1;
				    			if(indexMonth==12){
				    			   indexYear=indexYear+1;
				    			   indexMonth=0;	
				    			}
				    		}
				    	
				        }
					
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
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

	@Override
	public boolean changeState(long projectId, int state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getAllUsedProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectNewBean getProjectById(long id) {
		CustomLoggerUtils.INSTANCE.log.info("inside getProjectById(long id) method of ProjectServiceImpl class");
		return ProjectNewConverter.convertNewProjectEntityToProjectNewBean(projectDao.getProjectByProjectId(id));
	}

	@Override
	public ProjectBean getProjectByName(String teamName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> fetchTeamNames(String accountDivision) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveMonthlyProjectBudget(MonthlyProjectBudgetEntity monthlyProjectBudgetEntity) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveMonthlyProjectBudget(MonthlyProjectBudgetEntity monthlyProjectBudgetEntity) method of ProjectServiceImpl class");
		projectDao.saveMonthlyProjectBudget(monthlyProjectBudgetEntity);
	}
	
	@Override
	public void editMonthlyProjectBudget(MonthlyProjectBudgetBean monthlyProjectBudgetBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside editMonthlyProjectBudget(MonthlyProjectBudgetBean monthlyProjectBudgetBean) method of ProjectServiceImpl class");
		projectDao.editMonthlyProjectBudget(monthlyProjectBudgetBean);
	}
	
	@Override
	public void deleteMonthlyProjectBudget(MonthlyProjectBudgetBean monthlyProjectBudgetBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside deleteMonthlyProjectBudget(MonthlyProjectBudgetBean monthlyProjectBudgetBean) method of ProjectServiceImpl class");
		projectDao.deleteMonthlyProjectBudget(monthlyProjectBudgetBean);
	}

	@Override
	public MonthlyProjectBudgetEntity getProjectMonthlyBudget(int projectIdentity, Months month, int year) {
		CustomLoggerUtils.INSTANCE.log.info("inside getProjectMonthlyBudget(int projectIdentity, Months month, int year) method of ProjectServiceImpl class");// TODO Auto-generated method stub
		return projectDao.getProjectMonthlyBudget(projectIdentity,month,year);
	}

	@Override
	public List<Integer> getYearList() {
			CustomLoggerUtils.INSTANCE.log.info("inside getYearList() method of ProjectServiceImpl class");
			return projectDao.getYearList();
	}

	@Override
	public List<MonthlyProjectBudgetEntity> getMonthlyProjectBudgetThisYearThisProject(int year, ProjectNew projectNew) {
		CustomLoggerUtils.INSTANCE.log.info("inside getMonthlyProjectBudgetThisYearThisProject(int year, ProjectNew projectNew) method of ProjectServiceImpl class");
		List<MonthlyProjectBudgetEntity> mpbEntityList = personDaoNew.getMonthlyProjectBudgetThisYearThisProject(year, projectNew);
		return mpbEntityList ;
	}

	@Override
	public void changeStaffingDate(PersonStaffing personStaffing) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeStaffingDate(PersonStaffing personStaffing) method of ProjectServiceImpl class");
		projectDao.changeStaffingDate(personStaffing);
	}
	
	@Override
	public PersonStaffing mergeStaffingNew(PersonStaffing personStaffing) {
		CustomLoggerUtils.INSTANCE.log.info("inside createStaffingNew(PersonStaffing personStaffing) method of ProjectServiceImpl class");
		return projectDao.mergeStaffingNew(personStaffing);
	}	

	@Override
	public ProjectNew getProjectNewEntityByName(String projectName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getProjectNewEntityByName(String projectName) method of ProjectServiceImpl class");
		return projectDao.getProjectNewEntityByName(projectName);
	}

	@Override
	public List<ProjectNewBean> getAllProjectBeansforTeamName(String teamName) {
		// TODO Auto-generated method stub
		TeamBean teamBean = teamService.fetchTeamByName(teamName);
		return projectDao.getAllProjectBeansforTeamNameDB(TeamConverter.convertTeamBeanToEntity(teamBean));
	}

}
