package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.ProjectDao;
import com.sapient.statestreetscreeningapplication.model.entity.MonthlyProjectBudgetEntity;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.entity.ProjectNew;
import com.sapient.statestreetscreeningapplication.model.entity.Team;
import com.sapient.statestreetscreeningapplication.ui.bean.MonthlyProjectBudgetBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.ProjectNewConverter;
import com.sapient.statestreetscreeningapplication.utils.enums.Months;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectDaoImpl.
 */
@Component
public class ProjectDaoImpl implements ProjectDao {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager the new entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.ProjectDao#getAllProjects()
	 */
	@Override
	@Transactional
	public List<ProjectNew> getAllProjects() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllProjects()  method of ProjectDaoImpl class " );
		String query = "from ProjectNew";
		TypedQuery<ProjectNew> p = entityManager.createQuery(query, ProjectNew.class);
		CustomLoggerUtils.INSTANCE.log.info("retrieving the list with size "+p.getResultList().size());
		return p.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.ProjectDao#saveProject(com.sapient.statestreetscreeningapplication.model.entity.ProjectNew)
	 */
	@Override
	@Transactional
	public boolean saveProject(ProjectNew project) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveProject(ProjectNew project)  method of ProjectDaoImpl class " );
		project.setIsActive(true);
		Boolean isUnique = true;
		List<ProjectNew> existingProjects = getAllProjects();
		for (ProjectNew p : existingProjects) {
			if (p.getProjectName().equalsIgnoreCase(project.getProjectName())&&p.getClientProjectName().equalsIgnoreCase(project.getClientProjectName())&&p.getClientTimeTrackingProjectName().equalsIgnoreCase(project.getClientTimeTrackingProjectName())) {
				CustomLoggerUtils.INSTANCE.log.info("The project "+project+" already exists hence not saving it");
				isUnique = false;
				break;
			}
		}
		if (isUnique) {
			
			if(project.getProjectLead()!=null){
			String q1 = "from Person where personOracleId=:personOracleId";
			TypedQuery<Person> personTQ = entityManager.createQuery(q1,Person.class);
			personTQ.setParameter("personOracleId",project.getProjectLead().getPersonOracleId());
			List<Person> personList = personTQ.getResultList();
			Person lead = new Person();
				if(personList.size()>0){
				   lead = personList.get(0);
				}
				if(personList.size()==0){
				   lead = entityManager.merge(project.getProjectLead());
				}
			project.setProjectLead(lead);
			}
			
			entityManager.merge(project);
			
		}
		return isUnique;
	}

	
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.ProjectDao#getIQNNames(java.lang.String)
	 */
	@Override
	@Transactional
	public List<String> getIQNNames(String projectName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getIQNNames(String projectName)  method of ProjectDaoImpl class " );
		List<ProjectNew> projectList=new ArrayList<ProjectNew>();
		List<String> IQNList=new ArrayList<String>();
		String query = "from ProjectNew where projectName=:projectName";
		TypedQuery<ProjectNew> p = entityManager.createQuery(query, ProjectNew.class);
		p.setParameter("projectName", projectName);
		try {
			projectList = p.getResultList();
			for(ProjectNew project:projectList){
				if(!IQNList.contains(project.getClientProjectName()))
				IQNList.add(project.getClientProjectName());
			}
		} catch (NoResultException e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.error("Single result not found");
			return null;
		}
		
		return IQNList;
	}
	

	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.ProjectDao#getAtrackNamesByIQN(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public List<String> getAtrackNamesByIQN(String s, String teamName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getAtrackNamesByIQN(String s, String teamName)  method of ProjectDaoImpl class " );
		List<ProjectNew> projectList=new ArrayList<ProjectNew>();
		List<String> AtrackList=new ArrayList<String>();
		String query = "from ProjectNew where clientProjectName=:IQNName and projectName=:projectName";
		TypedQuery<ProjectNew> p = entityManager.createQuery(query, ProjectNew.class);
		p.setParameter("IQNName", s);
		p.setParameter("projectName", teamName);
		try {
			projectList = p.getResultList();
			for(ProjectNew project:projectList){
				AtrackList.add(project.getClientTimeTrackingProjectName());
			}
		} catch (NoResultException e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.error("Single result not found");
			return null;
		}
		
		return AtrackList;
	}

	
	@Override
	public PersonStaffing staffingOfMinimumStartDate(ProjectNew newProject) {
		CustomLoggerUtils.INSTANCE.log.info("inside validateProjectStartDate(ProjectNew newProject)  method of ProjectDaoImpl class " );
		ProjectNew project = getProjectByProjectId(newProject.getId());
		String query = "from PersonStaffing where project =:project";
	    TypedQuery<PersonStaffing> personStaffingTQ = entityManager.createQuery(query,PersonStaffing.class);
	    personStaffingTQ.setParameter("project",project);
	    List<PersonStaffing> psResultList = personStaffingTQ.getResultList();
	    if(psResultList.size()==0){
	    	return null;
	    }
	    PersonStaffing personStaffing = psResultList.get(0);
	    for(PersonStaffing ps : psResultList){
	    	if(ps.getStartDate().before(personStaffing.getStartDate())){
	    	   personStaffing = ps;
	    	}
	    }
	    	return personStaffing;
	}
	
	
	@Override
	public PersonStaffing staffingOfMaximumStartDate(ProjectNew newProject) {
		CustomLoggerUtils.INSTANCE.log.info("inside validateProjectEndDate(ProjectNew newProject)  method of ProjectDaoImpl class " );
		ProjectNew project = getProjectByProjectId(newProject.getId());
		String query = "from PersonStaffing where project =:project";
	    TypedQuery<PersonStaffing> personStaffingTQ = entityManager.createQuery(query,PersonStaffing.class);
	    personStaffingTQ.setParameter("project",project);
	    List<PersonStaffing> psResultList = personStaffingTQ.getResultList();
	    if(psResultList.size()==0){
	    	return null;
	    }
	    PersonStaffing personStaffing = psResultList.get(0);
	    for(PersonStaffing ps : psResultList){
	    	if(personStaffing.getStartDate().before(ps.getStartDate())){
	    	   personStaffing = ps;
	    	}
	    }
	    	return personStaffing;
	}
	
	
	@Override
	@Transactional
	public void editProject(ProjectNew newProject) {
		CustomLoggerUtils.INSTANCE.log.info("inside editProject(ProjectNew newProject)  method of ProjectDaoImpl class " );
		ProjectNew oldProject=getProjectByProjectId(newProject.getId());
		
		if(newProject.getProjectEndDate()!=null){
			
			if(newProject.getProjectEndDate()!=oldProject.getProjectEndDate()){
				String query = "from PersonStaffing where project =:project";
			    TypedQuery<PersonStaffing> personStaffingTQ = entityManager.createQuery(query,PersonStaffing.class);
			    personStaffingTQ.setParameter("project",oldProject);
			    List<PersonStaffing> psResultList = personStaffingTQ.getResultList();
			    for(PersonStaffing personStaffing : psResultList){
			    	Date psEndDate = personStaffing.getEndDate();
			    	Date newProjectEndDate = newProject.getProjectEndDate();
			    	boolean reset = false;
			    	if(newProjectEndDate.before(psEndDate)){
			    		    reset = true;
			    	}
			    	if(psEndDate.before(newProjectEndDate)){
			    		if((!personStaffing.isOffboarded())&&(personStaffing.getImmediateNextStaffing()==null)){
			    			reset = true;
			    		}
			    	}
			    	if(reset){
			    		personStaffing.setEndDate(newProjectEndDate);
			    		entityManager.merge(personStaffing);
			    	}
			    }
			}
			
			oldProject.setProjectEndDate(newProject.getProjectEndDate());
		}
		
		
		if(newProject.getProjectStartDate()!=null){
			oldProject.setProjectStartDate(newProject.getProjectStartDate());
		}
		
		if(newProject.getCostCenter()!=null){
			oldProject.setCostCenter(newProject.getCostCenter());
		}
		
		oldProject.setClientProjectId(newProject.getClientProjectId());
		oldProject.setClientProjectName(newProject.getClientProjectName());
		oldProject.setIsActive(newProject.getIsActive());
		oldProject.setProjectName(newProject.getProjectName());
		oldProject.setProjectId(newProject.getProjectId());
		if(newProject.getClientTimeTrackingId()!=null){
		oldProject.setClientTimeTrackingId(newProject.getClientTimeTrackingId());
		}
		if(newProject.getClientTimeTrackingProjectName()!=null){
		oldProject.setClientTimeTrackingProjectName(newProject.getClientTimeTrackingProjectName());
		}
		oldProject.setTeam(newProject.getTeam());
		
		entityManager.merge(oldProject);
	}
	
    /* (non-Javadoc)
     * @see com.sapient.statestreetscreeningapplication.model.dao.ProjectDao#getProjectByProjectId(long)
     */
    @Override
    @Transactional
	public ProjectNew getProjectByProjectId(long projectId) {
    	CustomLoggerUtils.INSTANCE.log.info("inside getProjectByProjectId(long projectId)  method of ProjectDaoImpl class " );
    	return entityManager.find(ProjectNew.class, projectId);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.ProjectDao#getAllUsedProjectNames()
	 */
	@Override
	@Transactional
	public List<String> getAllUsedProjectNames() {
		CustomLoggerUtils.INSTANCE.log.info("inside  getAllUsedProjectNames()  method of ProjectDaoImpl class " );

		String query = "select projectName from ProjectNew where isActive =:isActive";
		TypedQuery<String> p = entityManager.createQuery(query,String.class);
		p.setParameter("isActive", true);
		return p.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.ProjectDao#getNewProjectByName(java.lang.String)
	 */
	@Override
	@Transactional
	public ProjectNew getNewProjectByName(String projectName) {
		CustomLoggerUtils.INSTANCE.log.info("inside   getNewProjectByName(String projectName) method of ProjectDaoImpl class " );
		ProjectNew project;
		String query = "from ProjectNew where projectName=:project_name";
		TypedQuery<ProjectNew> p = entityManager.createQuery(query, ProjectNew.class);
		p.setParameter("project_name", projectName);
		try {
			System.out.println(projectName+"=projectName");
			project = p.getResultList().get(0);
		} catch (NoResultException e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.error("Single result not found");
			return null;
		}
		catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.error("Single result not found");
			return null;
		}
		
		return project;
	}
	
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.ProjectDao#fetchProjectByTeamAndProjectName(java.lang.String, com.sapient.statestreetscreeningapplication.model.entity.Team)
	 */
	@Override
	@Transactional
	public ProjectNew fetchProjectByTeamAndProjectName(String projectName, Team team) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchProjectByTeamAndProjectName(String projectName, Team team)  method of ProjectDaoImpl class " );
		ProjectNew project;
		String query = "from ProjectNew where projectName=:project_name and team=:team";
		TypedQuery<ProjectNew> p = entityManager.createQuery(query, ProjectNew.class);
		p.setParameter("project_name", projectName);
		p.setParameter("team", team);
		try {
			project = p.getResultList().get(0);
		} catch (NoResultException e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.error("Single result not found");
			return null;
		}
		catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.error("Single result not found");
			return null;
		}
		return project;
	}

	@Override
	@Transactional
	public void saveProjectList(List<ProjectNew> projectList) {
		
		for(ProjectNew p:projectList){
			entityManager.merge(p);
		}
	}

	@Override
	@Transactional
	public void saveMonthlyProjectBudget(MonthlyProjectBudgetEntity monthlyProjectBudgetEntity) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveMonthlyProjectBudget(MonthlyProjectBudgetEntity monthlyProjectBudgetEntity)  method of ProjectDaoImpl class " );
		
		String q = "from ProjectNew where projectId=:projectId";
		TypedQuery<ProjectNew> projectNewTQ = entityManager.createQuery(q,ProjectNew.class);
		projectNewTQ.setParameter("projectId",monthlyProjectBudgetEntity.getProject().getProjectId());
		ProjectNew project = projectNewTQ.getSingleResult();
		
		monthlyProjectBudgetEntity.setProject(project);
		
		String query = "from MonthlyProjectBudgetEntity where project = :project and month = :month and year = :year";
	    TypedQuery<MonthlyProjectBudgetEntity> monthlyProjectBudgetEntityTQ = entityManager.createQuery(query,MonthlyProjectBudgetEntity.class);
	    monthlyProjectBudgetEntityTQ.setParameter("project",monthlyProjectBudgetEntity.getProject());
	    monthlyProjectBudgetEntityTQ.setParameter("month",monthlyProjectBudgetEntity.getMonth());
	    monthlyProjectBudgetEntityTQ.setParameter("year",monthlyProjectBudgetEntity.getYear());
	    List<MonthlyProjectBudgetEntity> monthlyProjectBudgetEntityResultList = monthlyProjectBudgetEntityTQ.getResultList();
	    if(monthlyProjectBudgetEntityResultList.size()==0){
	    	entityManager.merge(monthlyProjectBudgetEntity);	
	    }
	    if(monthlyProjectBudgetEntityResultList.size()==1){
	    	MonthlyProjectBudgetEntity monthlyProjectBudgetEntity0 = monthlyProjectBudgetEntityResultList.get(0);
	    	monthlyProjectBudgetEntity0.setProjectMonthlyBudgetType(monthlyProjectBudgetEntity.getProjectMonthlyBudgetType());
	    	monthlyProjectBudgetEntity0.setProjectMonthlyBudgetValue(monthlyProjectBudgetEntity.getProjectMonthlyBudgetValue());
	    	entityManager.merge(monthlyProjectBudgetEntity0);

	    }
		
	}
	
	
	@Override
	@Transactional
	public void editMonthlyProjectBudget(MonthlyProjectBudgetBean monthlyProjectBudgetBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside editMonthlyProjectBudget(MonthlyProjectBudgetBean monthlyProjectBudgetBean)  method of ProjectDaoImpl class " );
		MonthlyProjectBudgetEntity monthlyProjectBudgetEntity = entityManager.find(MonthlyProjectBudgetEntity.class, monthlyProjectBudgetBean.getMonthlyProjectBudgetId());
		if(monthlyProjectBudgetEntity != null){
		monthlyProjectBudgetEntity.setProjectMonthlyBudgetValue(monthlyProjectBudgetBean.getProjectMonthlyBudgetValue());
		monthlyProjectBudgetEntity.setProjectMonthlyBudgetType(monthlyProjectBudgetBean.getProjectMonthlyBudgetType());
		entityManager.merge(monthlyProjectBudgetEntity);
		}
	}

	@Override
	@Transactional
	public void deleteMonthlyProjectBudget(MonthlyProjectBudgetBean monthlyProjectBudgetBean) {
		
		String q = "from ProjectNew where projectId=:projectId";
		TypedQuery<ProjectNew> projectNewTQ = entityManager.createQuery(q,ProjectNew.class);
		projectNewTQ.setParameter("projectId",monthlyProjectBudgetBean.getProjectNewBean().getProjectId());
		ProjectNew project = projectNewTQ.getSingleResult();
		
		String query = "from MonthlyProjectBudgetEntity where project = :project and month = :month and year = :year";
	    TypedQuery<MonthlyProjectBudgetEntity> monthlyProjectBudgetEntityTQ = entityManager.createQuery(query,MonthlyProjectBudgetEntity.class);
	    monthlyProjectBudgetEntityTQ.setParameter("project",project);
	    monthlyProjectBudgetEntityTQ.setParameter("month",monthlyProjectBudgetBean.getMonth());
	    monthlyProjectBudgetEntityTQ.setParameter("year",monthlyProjectBudgetBean.getYear());
	    List<MonthlyProjectBudgetEntity> monthlyProjectBudgetEntityResultList = monthlyProjectBudgetEntityTQ.getResultList();
	    
	    if(monthlyProjectBudgetEntityResultList.size()>0){
	    	for(MonthlyProjectBudgetEntity monthlyProjectBudgetEntity : monthlyProjectBudgetEntityResultList){
	    		entityManager.remove(monthlyProjectBudgetEntity);
	    	}
	    }
	}
	
	@Override
	public MonthlyProjectBudgetEntity getProjectMonthlyBudget(int projectIdentity, Months month, int year) {
		CustomLoggerUtils.INSTANCE.log.info("inside getProjectMonthlyBudget(int projectIdentity, Months month, int year)  method of ProjectDaoImpl class " );
		
		String q = "from ProjectNew where projectId=:projectId";
		TypedQuery<ProjectNew> projectNewTQ = entityManager.createQuery(q,ProjectNew.class);
		projectNewTQ.setParameter("projectId",projectIdentity);
		ProjectNew project = projectNewTQ.getSingleResult();
		
		String query = "from MonthlyProjectBudgetEntity where project = :project and month = :month and year = :year";
	    TypedQuery<MonthlyProjectBudgetEntity> monthlyProjectBudgetEntityTQ = entityManager.createQuery(query,MonthlyProjectBudgetEntity.class);
	    monthlyProjectBudgetEntityTQ.setParameter("project",project);
	    monthlyProjectBudgetEntityTQ.setParameter("month",month);
	    monthlyProjectBudgetEntityTQ.setParameter("year",year);
	    MonthlyProjectBudgetEntity mpb = monthlyProjectBudgetEntityTQ.getSingleResult();
	    return mpb;

	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Integer> getYearList() {
		TreeSet<Integer> yrLst=new TreeSet<Integer>();
		String q = "from ProjectNew";
		TypedQuery<ProjectNew> projectNewTQ = entityManager.createQuery(q,ProjectNew.class);
		
		List<ProjectNew> projectList=projectNewTQ.getResultList();
		Calendar cal = Calendar.getInstance();
	    
		for(ProjectNew pr:projectList){
			 cal.setTime(pr.getProjectStartDate()); 
			yrLst.add(cal.get(Calendar.YEAR));
			int startyr=cal.get(Calendar.YEAR);
			cal.setTime(pr.getProjectEndDate()); 
			yrLst.add(cal.get(Calendar.YEAR));
			int endyr=cal.get(Calendar.YEAR);
			for(int i=startyr+1;i<endyr;i++){
				yrLst.add(i);
			}
			
		}
		List<Integer> yearList=new ArrayList<Integer>() ;
		yearList.addAll(yrLst);
		
		return yearList;
	}

	@Override
	@Transactional
	public void changeStaffingDate(PersonStaffing personStaffing) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeStaffingDate(PersonStaffing personStaffing)  method of ProjectDaoImpl class " );
		PersonStaffing personStaffingOld = entityManager.find(PersonStaffing.class, personStaffing.getPersonStaffingId());
		personStaffingOld.setStartDate(personStaffing.getStartDate());
		personStaffingOld.setEndDate(personStaffing.getEndDate());
		entityManager.merge(personStaffingOld);
	}
	
	@Override
	@Transactional
	public PersonStaffing mergeStaffingNew(PersonStaffing personStaffing) {
		CustomLoggerUtils.INSTANCE.log.info("inside createStaffingNew(PersonStaffing personStaffing)  method of ProjectDaoImpl class " );
		return entityManager.merge(personStaffing);
	}	

	@Override
	public ProjectNew getProjectNewEntityByName(String projectName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getProjectNewEntityByName(String projectName)  method of ProjectDaoImpl class " );
		String q = "from ProjectNew where projectName=:projectName";
		TypedQuery<ProjectNew> projectNewTQ = entityManager.createQuery(q,ProjectNew.class);
		projectNewTQ.setParameter("projectName",projectName);
		ProjectNew projectNew = projectNewTQ.getSingleResult();
		return projectNew;
	}

	@Override
	public List<ProjectNewBean> getAllProjectBeansforTeamNameDB(Team team) {
		// TODO Auto-generated method stub
		CustomLoggerUtils.INSTANCE.log.info("inside fetchProjectByTeamAndProjectName(String projectName, Team team)  method of ProjectDaoImpl class " );
		List<ProjectNew> projectList;
		String query = "from ProjectNew where team=:team";
		TypedQuery<ProjectNew> p = entityManager.createQuery(query, ProjectNew.class);
		p.setParameter("team", team);
		try {
			projectList = p.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.error("Single result not found");
			return null;
		}
		catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.error("Single result not found");
			return null;
		}
		
		List<ProjectNewBean> projectBeanList=new ArrayList<ProjectNewBean>(); 
		for(ProjectNew projectEntity: projectList)
		{
			
			projectBeanList.add(ProjectNewConverter.convertNewProjectEntityToProjectNewBean(projectEntity));
		}
			
		return projectBeanList;
	}
	
}


