package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.dao.ProjectDashboardDao;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.service.DashboardService;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.model.service.ProjectDashboardService;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonStaffingBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectSummaryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.StaffingMonthlyCostBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonStaffingConverter;
import com.sapient.statestreetscreeningapplication.utils.enums.Months;


@Service
public class ProjectDashboardServiceImpl implements ProjectDashboardService{
	
	
	@Autowired
	DashboardService dashboardService;
	
	@Autowired
	ProjectDashboardDao projectDashboardDao;
	
	@Autowired
	PersonLookupService personLookupService;
	
	
	public DashboardService getDashboardService() {
		return dashboardService;
	}


	public void setDashboardService(DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}


	public ProjectDashboardDao getProjectDashboardDao() {
		return projectDashboardDao;
	}


	public void setProjectDashboardDao(ProjectDashboardDao projectDashboardDao) {
		this.projectDashboardDao = projectDashboardDao;
	}


	public PersonLookupService getPersonLookupService() {
		return personLookupService;
	}


	public void setPersonLookupService(PersonLookupService personLookupService) {
		this.personLookupService = personLookupService;
	}


	public Map<String, Object> fetchProjectSummaryBeanMap(int start,
			int amount, String columnName, String direction, String searchKey,int projectId, int year) {
		
	Calendar selectedYearCalendar = Calendar.getInstance();
	selectedYearCalendar.set(year,0,1);
	Date firstJanuaryOfSelectedYear = selectedYearCalendar.getTime();
	selectedYearCalendar.set(year,11,31);
	Date thirtyFirstDecemberOfSelectedYear =  selectedYearCalendar.getTime();
	
	List<PersonStaffing> staffingThisProject = projectDashboardDao.getStaffingThisProject(firstJanuaryOfSelectedYear, thirtyFirstDecemberOfSelectedYear, projectId, start,
			 amount,  columnName,  direction,  searchKey);
	
	int listCount=getProjectDashBoardCountForSpecificProject(firstJanuaryOfSelectedYear, thirtyFirstDecemberOfSelectedYear, projectId, start, amount, columnName, direction, searchKey);
	
	List<ProjectSummaryBean> projectSummaryBeanList = new ArrayList<ProjectSummaryBean>();
	
	for(PersonStaffing personStaffingEntity : staffingThisProject){
		ProjectSummaryBean projectSummaryBean = new ProjectSummaryBean();
		PersonStaffingBean personStaffingBean = PersonStaffingConverter.personStaffingEntityToBean(personStaffingEntity);
		projectSummaryBean.setPersonStaffingBean(personStaffingBean);
		
		for(int indexOfIteratingMonth=0; indexOfIteratingMonth<12; indexOfIteratingMonth++){
			
			Calendar iteratingCalendar = Calendar.getInstance();
			iteratingCalendar.set(year,indexOfIteratingMonth,1);
			Date firstDateOfTheIteratingMonth = iteratingCalendar.getTime();
			iteratingCalendar.set(Calendar.DAY_OF_MONTH, iteratingCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date lastDateOfTheIteratingMonth =  iteratingCalendar.getTime();
			
			Date staffingStartDate = personStaffingEntity.getStartDate();
			Date staffingEndDate   = personStaffingEntity.getEndDate();
			
			float individualMonthlyCost=0;
			
			if((staffingStartDate.before(firstDateOfTheIteratingMonth))&&(staffingEndDate.after(lastDateOfTheIteratingMonth))){
				individualMonthlyCost = (personStaffingEntity.getRate().getRate())*(personStaffingEntity.getAllocation()/100);
				individualMonthlyCost = Math.round(individualMonthlyCost);
			}
			else{
				float totalWeekDaysOfTheIteratingMonth = dashboardService.getWeekDays(firstDateOfTheIteratingMonth,lastDateOfTheIteratingMonth);
				if(staffingStartDate.before(firstDateOfTheIteratingMonth)){
				   staffingStartDate = firstDateOfTheIteratingMonth;
				}
				if(staffingEndDate.after(lastDateOfTheIteratingMonth)){
				   staffingEndDate = lastDateOfTheIteratingMonth;
				}
				float staffedWeekDaysOfTheIteratingMonth = dashboardService.getWeekDays(staffingStartDate,staffingEndDate);
				individualMonthlyCost = (personStaffingEntity.getRate().getRate())*(personStaffingEntity.getAllocation()/100)*(staffedWeekDaysOfTheIteratingMonth/totalWeekDaysOfTheIteratingMonth);
				individualMonthlyCost = Math.round(individualMonthlyCost);
			}
			
            StaffingMonthlyCostBean smcBean = new StaffingMonthlyCostBean();
			
			smcBean.setPersonStaffingBean(personStaffingBean);
			smcBean.setYear(year);
			
			Months month =Months.getMonth(indexOfIteratingMonth);
			smcBean.setMonth(month);
			
			smcBean.setIndividualMonthlyCost(individualMonthlyCost);
			
			projectSummaryBean.setStaffingMonthlyCostBean(smcBean);
			
		}
		
		projectSummaryBean.setStaffingQuarterlyCost();
		projectSummaryBeanList.add(projectSummaryBean);
	}
	 Map<String, Object> projectSummaryBeanMap=new HashMap<>();
	 
	 projectSummaryBeanMap.put("projectSummaryBeanList", projectSummaryBeanList);
	 projectSummaryBeanMap.put("count", projectSummaryBeanList.size());
	 projectSummaryBeanMap.put("listCount", listCount);

	return projectSummaryBeanMap;
	}
	
	
	public Map<String, Object> fetchProjectSummaryBeanMapforIntervieweesWithOracleId(int start,
			int amount, String columnName, String direction, String searchKey,int projectId, int year, List<String> oracleIdList) {
		
	Calendar selectedYearCalendar = Calendar.getInstance();
	selectedYearCalendar.set(year,0,1);
	Date firstJanuaryOfSelectedYear = selectedYearCalendar.getTime();
	selectedYearCalendar.set(year,11,31);
	Date thirtyFirstDecemberOfSelectedYear =  selectedYearCalendar.getTime();
	
	List<PersonStaffing> staffingThisProject = projectDashboardDao.getStaffingThisProjectForIntervieweesWithOracleId(firstJanuaryOfSelectedYear, thirtyFirstDecemberOfSelectedYear, projectId, start,
			 amount,  columnName,  direction,  null,oracleIdList);
	
	List<ProjectSummaryBean> projectSummaryBeanList = new ArrayList<ProjectSummaryBean>();
	
	for(PersonStaffing personStaffingEntity : staffingThisProject){
		ProjectSummaryBean projectSummaryBean = new ProjectSummaryBean();
		PersonStaffingBean personStaffingBean = PersonStaffingConverter.personStaffingEntityToBean(personStaffingEntity);
		projectSummaryBean.setPersonStaffingBean(personStaffingBean);
		
		for(int indexOfIteratingMonth=0; indexOfIteratingMonth<12; indexOfIteratingMonth++){
			
			Calendar iteratingCalendar = Calendar.getInstance();
			iteratingCalendar.set(year,indexOfIteratingMonth,1);
			Date firstDateOfTheIteratingMonth = iteratingCalendar.getTime();
			iteratingCalendar.set(Calendar.DAY_OF_MONTH, iteratingCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date lastDateOfTheIteratingMonth =  iteratingCalendar.getTime();
			
			Date staffingStartDate = personStaffingEntity.getStartDate();
			Date staffingEndDate   = personStaffingEntity.getEndDate();
			
			float individualMonthlyCost=0;
			
			if((staffingStartDate.before(firstDateOfTheIteratingMonth))&&(staffingEndDate.after(lastDateOfTheIteratingMonth))){
				individualMonthlyCost = (personStaffingEntity.getRate().getRate())*(personStaffingEntity.getAllocation()/100);
				individualMonthlyCost = Math.round(individualMonthlyCost);
			}
			else{
				float totalWeekDaysOfTheIteratingMonth = dashboardService.getWeekDays(firstDateOfTheIteratingMonth,lastDateOfTheIteratingMonth);
				if(staffingStartDate.before(firstDateOfTheIteratingMonth)){
				   staffingStartDate = firstDateOfTheIteratingMonth;
				}
				if(staffingEndDate.after(lastDateOfTheIteratingMonth)){
				   staffingEndDate = lastDateOfTheIteratingMonth;
				}
				float staffedWeekDaysOfTheIteratingMonth = dashboardService.getWeekDays(staffingStartDate,staffingEndDate);
				individualMonthlyCost = (personStaffingEntity.getRate().getRate())*(personStaffingEntity.getAllocation()/100)*(staffedWeekDaysOfTheIteratingMonth/totalWeekDaysOfTheIteratingMonth);
				individualMonthlyCost = Math.round(individualMonthlyCost);
			}
			
            StaffingMonthlyCostBean smcBean = new StaffingMonthlyCostBean();
			
			smcBean.setPersonStaffingBean(personStaffingBean);
			smcBean.setYear(year);
			
			Months month =Months.getMonth(indexOfIteratingMonth);
			smcBean.setMonth(month);
			
			smcBean.setIndividualMonthlyCost(individualMonthlyCost);
			
			projectSummaryBean.setStaffingMonthlyCostBean(smcBean);
			
		}
		
		projectSummaryBean.setStaffingQuarterlyCost();
		projectSummaryBeanList.add(projectSummaryBean);
	}
	 Map<String, Object> projectSummaryBeanMap=new HashMap<>();
	 
	 projectSummaryBeanMap.put("projectSummaryBeanList", projectSummaryBeanList);
	 projectSummaryBeanMap.put("count", projectSummaryBeanList.size());
	return projectSummaryBeanMap;
	}
	
	public Map<String, Object> fetchProjectSummaryBeanMapForTempInterviewees(int start,
			int amount, String columnName, String direction, String searchKey,int projectId, int year) {
		
	Calendar selectedYearCalendar = Calendar.getInstance();
	selectedYearCalendar.set(year,0,1);
	Date firstJanuaryOfSelectedYear = selectedYearCalendar.getTime();
	selectedYearCalendar.set(year,11,31);
	Date thirtyFirstDecemberOfSelectedYear =  selectedYearCalendar.getTime();
	
	List<PersonStaffing> staffingThisProject = projectDashboardDao.getStaffingThisProjectForTempInterviewees(firstJanuaryOfSelectedYear, thirtyFirstDecemberOfSelectedYear, projectId, start,
			 amount,  columnName,  direction,  searchKey);
	
	List<ProjectSummaryBean> projectSummaryBeanList = new ArrayList<ProjectSummaryBean>();
	
	for(PersonStaffing personStaffingEntity : staffingThisProject){
		ProjectSummaryBean projectSummaryBean = new ProjectSummaryBean();
		PersonStaffingBean personStaffingBean = PersonStaffingConverter.personStaffingEntityToBean(personStaffingEntity);
		projectSummaryBean.setPersonStaffingBean(personStaffingBean);
		
		for(int indexOfIteratingMonth=0; indexOfIteratingMonth<12; indexOfIteratingMonth++){
			
			Calendar iteratingCalendar = Calendar.getInstance();
			iteratingCalendar.set(year,indexOfIteratingMonth,1);
			Date firstDateOfTheIteratingMonth = iteratingCalendar.getTime();
			iteratingCalendar.set(Calendar.DAY_OF_MONTH, iteratingCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date lastDateOfTheIteratingMonth =  iteratingCalendar.getTime();
			
			Date staffingStartDate = personStaffingEntity.getStartDate();
			Date staffingEndDate   = personStaffingEntity.getEndDate();
			
			float individualMonthlyCost=0;
			
			if((staffingStartDate.before(firstDateOfTheIteratingMonth))&&(staffingEndDate.after(lastDateOfTheIteratingMonth))){
				individualMonthlyCost = (personStaffingEntity.getRate().getRate())*(personStaffingEntity.getAllocation()/100);
				individualMonthlyCost = Math.round(individualMonthlyCost);
			}
			else{
				float totalWeekDaysOfTheIteratingMonth = dashboardService.getWeekDays(firstDateOfTheIteratingMonth,lastDateOfTheIteratingMonth);
				if(staffingStartDate.before(firstDateOfTheIteratingMonth)){
				   staffingStartDate = firstDateOfTheIteratingMonth;
				}
				if(staffingEndDate.after(lastDateOfTheIteratingMonth)){
				   staffingEndDate = lastDateOfTheIteratingMonth;
				}
				float staffedWeekDaysOfTheIteratingMonth = dashboardService.getWeekDays(staffingStartDate,staffingEndDate);
				individualMonthlyCost = (personStaffingEntity.getRate().getRate())*(personStaffingEntity.getAllocation()/100)*(staffedWeekDaysOfTheIteratingMonth/totalWeekDaysOfTheIteratingMonth);
				individualMonthlyCost = Math.round(individualMonthlyCost);
			}
			
            StaffingMonthlyCostBean smcBean = new StaffingMonthlyCostBean();
			
			smcBean.setPersonStaffingBean(personStaffingBean);
			smcBean.setYear(year);
			
			Months month =Months.getMonth(indexOfIteratingMonth);
			smcBean.setMonth(month);
			
			smcBean.setIndividualMonthlyCost(individualMonthlyCost);
			
			projectSummaryBean.setStaffingMonthlyCostBean(smcBean);
			
		}
		
		projectSummaryBean.setStaffingQuarterlyCost();
		projectSummaryBeanList.add(projectSummaryBean);
	}
	 Map<String, Object> projectSummaryBeanMap=new HashMap<>();
	 
	 projectSummaryBeanMap.put("projectSummaryBeanList", projectSummaryBeanList);
	 projectSummaryBeanMap.put("count", projectSummaryBeanList.size());
	return projectSummaryBeanMap;
	}
	
	public Map<String, Object> fetchProjectSummaryBeanMapByDate(int start,
			int amount, String columnName, String direction, String searchKey,int projectId, int year){
		
		Calendar selectedYearCalendar = Calendar.getInstance();
		selectedYearCalendar.set(year,0,1);
		Date firstJanuaryOfSelectedYear = selectedYearCalendar.getTime();
		selectedYearCalendar.set(year,11,31);
		Date thirtyFirstDecemberOfSelectedYear =  selectedYearCalendar.getTime();
		
		List<PersonStaffing> staffingThisProject = projectDashboardDao.getStaffingThisProjectByDate(firstJanuaryOfSelectedYear, thirtyFirstDecemberOfSelectedYear, projectId, start,
				 amount,  columnName,  direction,  searchKey);
		
		List<ProjectSummaryBean> projectSummaryBeanList = new ArrayList<ProjectSummaryBean>();
		
		for(PersonStaffing personStaffingEntity : staffingThisProject){
			ProjectSummaryBean projectSummaryBean = new ProjectSummaryBean();
			PersonStaffingBean personStaffingBean = PersonStaffingConverter.personStaffingEntityToBean(personStaffingEntity);
			projectSummaryBean.setPersonStaffingBean(personStaffingBean);
			
			for(int indexOfIteratingMonth=0; indexOfIteratingMonth<12; indexOfIteratingMonth++){
				
				Calendar iteratingCalendar = Calendar.getInstance();
				iteratingCalendar.set(year,indexOfIteratingMonth,1);
				Date firstDateOfTheIteratingMonth = iteratingCalendar.getTime();
				iteratingCalendar.set(Calendar.DAY_OF_MONTH, iteratingCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				Date lastDateOfTheIteratingMonth =  iteratingCalendar.getTime();
				
				Date staffingStartDate = personStaffingEntity.getStartDate();
				Date staffingEndDate   = personStaffingEntity.getEndDate();
				
				float individualMonthlyCost=0;
				
				if((staffingStartDate.before(firstDateOfTheIteratingMonth))&&(staffingEndDate.after(lastDateOfTheIteratingMonth))){
					individualMonthlyCost = (personStaffingEntity.getRate().getRate())*(personStaffingEntity.getAllocation()/100);
					individualMonthlyCost = Math.round(individualMonthlyCost);
				}
				else{
					float totalWeekDaysOfTheIteratingMonth = dashboardService.getWeekDays(firstDateOfTheIteratingMonth,lastDateOfTheIteratingMonth);
					if(staffingStartDate.before(firstDateOfTheIteratingMonth)){
					   staffingStartDate = firstDateOfTheIteratingMonth;
					}
					if(staffingEndDate.after(lastDateOfTheIteratingMonth)){
					   staffingEndDate = lastDateOfTheIteratingMonth;
					}
					float staffedWeekDaysOfTheIteratingMonth = dashboardService.getWeekDays(staffingStartDate,staffingEndDate);
					individualMonthlyCost = (personStaffingEntity.getRate().getRate())*(personStaffingEntity.getAllocation()/100)*(staffedWeekDaysOfTheIteratingMonth/totalWeekDaysOfTheIteratingMonth);
					individualMonthlyCost = Math.round(individualMonthlyCost);
				}
				
	            StaffingMonthlyCostBean smcBean = new StaffingMonthlyCostBean();
				
				smcBean.setPersonStaffingBean(personStaffingBean);
				smcBean.setYear(year);
				
				Months month =Months.getMonth(indexOfIteratingMonth);
				smcBean.setMonth(month);
				
				smcBean.setIndividualMonthlyCost(individualMonthlyCost);
				
				projectSummaryBean.setStaffingMonthlyCostBean(smcBean);
				
			}
			
			projectSummaryBean.setStaffingQuarterlyCost();
			projectSummaryBeanList.add(projectSummaryBean);
		}
		 Map<String, Object> projectSummaryBeanMap=new HashMap<>();
		 
		 projectSummaryBeanMap.put("projectSummaryBeanList", projectSummaryBeanList);
		 projectSummaryBeanMap.put("count", projectSummaryBeanList.size());
		return projectSummaryBeanMap;
		
	}
	
	@Override
	public Map<String, Object> populateProjectDashboardTable(int start,
			int amount, String columnName, String direction, String searchKey,int projectId, int year) {
		CustomLoggerUtils.INSTANCE.log.info("Map<String, Object> populateProjectDashboardTable(int sStart,int sAmount, String columnName, String sdir, String sSearch,int projectId, int year) method of ProjectDashboardServiceImpl class");
		
		List<ProjectSummaryBean> projectSummaryBeanList = null;
		Map<String, Object> projectSummaryBeanMap = new HashMap<String, Object>();
		Map<String, Object> personMap = new HashMap<String, Object>();
		Map<String, Object> TempPersonMap = new HashMap<String, Object>();
		
		
		projectSummaryBeanMap.put("count", 0);
		if (searchKey == null || searchKey.isEmpty()) {
			projectSummaryBeanMap =fetchProjectSummaryBeanMap(start, amount,
					columnName, direction, searchKey,projectId, year);
		} 
		
		else if (searchKey.matches("[a-zA-Z][a-zA-Z]*")){
			
			CustomLoggerUtils.INSTANCE.log.info("Text Input");
			List<PersonBean> persons = null;
			List<ProjectSummaryBean> tempPersonProjectSummaryBeanList=null;
			if (searchKey.length()>=3) {
				persons=personLookupService.getPersonByNameSearch(searchKey);
				TempPersonMap=fetchProjectSummaryBeanMapForTempInterviewees(start, amount, columnName, direction,searchKey,projectId, year);
				if(persons != null || TempPersonMap!=null){
					
					
					if(persons != null){
						List<String> oracleIdList=new ArrayList<String>();
						CustomLoggerUtils.INSTANCE.log.info("FETCHED FROM AD "+persons);
						for(PersonBean person : persons){
						oracleIdList.add(String.valueOf(person.getOracleId()));
						}
						personMap = fetchProjectSummaryBeanMapforIntervieweesWithOracleId(
							start, amount, columnName, direction,searchKey, projectId, year, oracleIdList);
					}
					
					
					List<ProjectSummaryBean> allProjectSummaryBean=new ArrayList<>();
					int allCount=0;
					
					if(personMap!=null){
						allProjectSummaryBean.addAll((Collection<? extends ProjectSummaryBean>) personMap.get("projectSummaryBeanList"));
						allCount=(int) personMap.get("count");
						if(TempPersonMap!=null){
							allProjectSummaryBean.addAll((Collection<? extends ProjectSummaryBean>) TempPersonMap.get("projectSummaryBeanList"));
							allCount=allCount+(int) TempPersonMap.get("count");
						}
						projectSummaryBeanMap.put("projectSummaryBeanList", allProjectSummaryBean);
						projectSummaryBeanMap.put("count", allCount);
					}else{
						if(TempPersonMap!=null){
							projectSummaryBeanMap.putAll(TempPersonMap);
						}
						
					}
					
				} 
				else{
					CustomLoggerUtils.INSTANCE.log.info("Text Input Except Name");
					projectSummaryBeanMap =fetchProjectSummaryBeanMap(start, amount,
							columnName, direction, searchKey,projectId, year);
				}
			}
			else{
			CustomLoggerUtils.INSTANCE.log.info("Text Input Except Name (in else)");
			projectSummaryBeanMap =fetchProjectSummaryBeanMap(start, amount,
					columnName, direction, searchKey,projectId, year);
			}
		} /*else if (searchKey.matches("^[\\d]{1,2}$"))*/ 
		
		
		else if (searchKey.matches("[0-9][0-9]*")){
			CustomLoggerUtils.INSTANCE.log
					.info("searching 1 or 2 digits. date ");
			projectSummaryBeanMap =fetchProjectSummaryBeanMapByDate(start, amount,
					columnName, direction, searchKey,projectId, year);
		} /*else if (searchKey.matches("^[\\d]{1,10}$")) {
			CustomLoggerUtils.INSTANCE.log
					.info("more than 2 consecutive digits. oid");
			projectSummaryBeanMap =fetchProjectSummaryBeanMapByOracleId(start, amount,
					columnName, direction, searchKey,projectId, year);
		} else if (searchKey
				.matches("^[\\d]{1,2}[\\\\|\\/|\\-]((([\\d]{1,2})?[\\\\|\\/|\\-])?[\\d]{0,2})?")) {
			CustomLoggerUtils.INSTANCE.log.info("date");
			String[] dateArray = searchKey.split("[\\\\|\\/|\\-]");
			String searchString = dateArray[0];
			for (int i = 1; i < dateArray.length; i++) {
				searchString = dateArray[i] + "-" + searchString;
			}
			CustomLoggerUtils.INSTANCE.log.info("Date search string: "
					+ searchString);
			projectSummaryBeanMap =fetchProjectSummaryBeanMapByDate(start, amount,
					columnName, direction, searchKey,projectId, year);
		}*/
		
		return projectSummaryBeanMap;
		
		
		
	}


	@Override
	public int getProjectDashBoardCountForSpecificProject(Date firstJanuaryOfSelectedYear1,
			Date thirtyFirstDecemberOfSelectedYear1, int projectId, int start, int amount, String orderingColumn,
			String orderingDirection, String searchKey) {
		// TODO Auto-generated method stub
		return projectDashboardDao.getProjectDashBoardCountForSpecificProject(firstJanuaryOfSelectedYear1, thirtyFirstDecemberOfSelectedYear1, projectId, start, amount, orderingColumn, orderingDirection, searchKey);
	}

}
