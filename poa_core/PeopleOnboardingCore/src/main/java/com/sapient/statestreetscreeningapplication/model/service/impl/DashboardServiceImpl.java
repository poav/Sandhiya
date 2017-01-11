package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew;
import com.sapient.statestreetscreeningapplication.model.entity.MonthlyProjectBudgetEntity;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.service.DashboardService;
import com.sapient.statestreetscreeningapplication.ui.bean.AnnualDetailsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.AnnualProjectActualBean;
import com.sapient.statestreetscreeningapplication.ui.bean.AnnualProjectBudgetBean;
import com.sapient.statestreetscreeningapplication.ui.bean.MonthDetailsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.MonthlyProjectActualBean;
import com.sapient.statestreetscreeningapplication.ui.bean.MonthlyProjectBudgetBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonStaffingBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectSummaryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.QuarterDetailsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.QuarterlyProjectActualBean;
import com.sapient.statestreetscreeningapplication.ui.bean.QuarterlyProjectBudgetBean;
import com.sapient.statestreetscreeningapplication.ui.bean.StaffingMonthlyCostBean;
import com.sapient.statestreetscreeningapplication.utils.converter.MonthlyProjectBudgetConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonStaffingConverter;
import com.sapient.statestreetscreeningapplication.utils.enums.Months;
import com.sapient.statestreetscreeningapplication.utils.enums.Quarters;
import com.sapient.statestreetscreeningapplication.virtual.dao.AnnualProjectActualDao;
import com.sapient.statestreetscreeningapplication.virtual.dao.AnnualProjectBudgetDao;
import com.sapient.statestreetscreeningapplication.virtual.dao.MonthlyProjectActualDao;
import com.sapient.statestreetscreeningapplication.virtual.dao.QuarterlyProjectActualDao;
import com.sapient.statestreetscreeningapplication.virtual.dao.QuarterlyProjectBudgetDao;
import com.sapient.statestreetscreeningapplication.virtual.dao.StaffingMonthlyCostDao;

@Service
public class DashboardServiceImpl implements DashboardService{
	
	@Autowired
	private EmailService emailService;

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@Autowired
	private PersonDaoNew personDaoNew;

	public PersonDaoNew getPersonDaoNew() {
		return personDaoNew;
	}

	public void setPersonDaoNew(PersonDaoNew personDaoNew) {
		this.personDaoNew = personDaoNew;
	}

	@Override
	public List<ProjectSummaryBean> projectSummaryCalculation(int projectId, int year){ // if todays date is before first date , check situation handled well or not
		
		Calendar selectedYearCalendar = Calendar.getInstance();
		selectedYearCalendar.set(year,0,1);
		Date firstJanuaryOfSelectedYear = selectedYearCalendar.getTime();
		selectedYearCalendar.set(year,11,31);
		Date thirtyFirstDecemberOfSelectedYear =  selectedYearCalendar.getTime();
		
		List<PersonStaffing> staffingThisProject = personDaoNew.getStaffingThisProject(firstJanuaryOfSelectedYear, thirtyFirstDecemberOfSelectedYear, projectId);
		if(staffingThisProject == null){
		   staffingThisProject =  new ArrayList<PersonStaffing>();
		}
		
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
					float totalWeekDaysOfTheIteratingMonth = getWeekDays(firstDateOfTheIteratingMonth,lastDateOfTheIteratingMonth);
					if(staffingStartDate.before(firstDateOfTheIteratingMonth)){
					   staffingStartDate = firstDateOfTheIteratingMonth;
					}
					if(staffingEndDate.after(lastDateOfTheIteratingMonth)){
					   staffingEndDate = lastDateOfTheIteratingMonth;
					}
					float staffedWeekDaysOfTheIteratingMonth = getWeekDays(staffingStartDate,staffingEndDate);
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
		
		return projectSummaryBeanList;
	}
	
	@Override
	public QuarterDetailsBean quarterlySummaryCalculation(Quarters quarter, int year){
		
		MonthDetailsBean monthDetailsBean = new MonthDetailsBean();
		QuarterDetailsBean quarterDetailsBean = new QuarterDetailsBean();
		
		int indexOfTheFirstMonthOfTheSelectedQuarter=0;
		
		Months month1 =null;
    	Months month2 =null;
    	Months month3 =null;

		switch (quarter) {
		case Q1:    indexOfTheFirstMonthOfTheSelectedQuarter = 0;
		            month1 = Months.JAN;
		            month2 = Months.FEB;
		            month3 = Months.MARCH;
		            break;
		case Q2:    indexOfTheFirstMonthOfTheSelectedQuarter = 3;
		            month1 = Months.APRIL;
		            month2 = Months.MAY;
		            month3 = Months.JUNE;
	                break;
		case Q3:    indexOfTheFirstMonthOfTheSelectedQuarter = 6;
		            month1 = Months.JULY;
		            month2 = Months.AUG;
		            month3 = Months.SEPT;
	                break;
		case Q4:    indexOfTheFirstMonthOfTheSelectedQuarter = 9;
		            month1 = Months.OCT;
		            month2 = Months.NOV;
		            month3 = Months.DEC;
	                break;
		default:    break;
		}
		int maximumIteration = 3;
		
		monthDetailsBean = populateMonthDetailsBean(year, monthDetailsBean, indexOfTheFirstMonthOfTheSelectedQuarter, maximumIteration);
		
		List<MonthlyProjectActualBean> mpaBeanList = MonthlyProjectActualDao.getMonthlyProjectActualThisQuarter(month1,month2,month3,year,monthDetailsBean.getMonthlyProjectActualBeanSet()); // vitualo dao
    	
    	Map<ProjectNewBean, Set<MonthlyProjectActualBean>> mpaProjectMap = getMapOfProjectNewBeanAsKeyAndSetOfMPABeanAsValue(mpaBeanList);
    	
    	for(ProjectNewBean projectNewBean : mpaProjectMap.keySet()){
    		
    		QuarterlyProjectActualBean qpaBean = new QuarterlyProjectActualBean();
    		qpaBean.setProjectNewBean(projectNewBean);
    		qpaBean.setYear(year);
    		qpaBean.setQuarter(quarter);
    		
    		float quarterlyProjectCost = 0;
    		
    		for(MonthlyProjectActualBean mpaBean: mpaProjectMap.get(projectNewBean)){
    			quarterlyProjectCost = quarterlyProjectCost + mpaBean.getProjectMonthlyCost();
    		}
    		
    		qpaBean.setProjectQuarterlyCost(quarterlyProjectCost);
    		
    		quarterDetailsBean.setQuarterlyProjectActualBeanSet(QuarterlyProjectActualDao.saveProjectQuarterlyCost(qpaBean,quarterDetailsBean.getQuarterlyProjectActualBeanSet())); // virtual dao
    		
    	}
    	
    	List<MonthlyProjectBudgetEntity> mpbEntityList = emailService.getMonthlyProjectBudgetThisQuarter(month1,month2,month3,year);
    	
    	Map<ProjectNewBean, Set<MonthlyProjectBudgetBean>> mpbProjectMap = getMapOfProjectNewBeanAsKeyAndSetOfMPBBeanAsValue(mpbEntityList);
    	
    	for(ProjectNewBean projectNewBean : mpbProjectMap.keySet()){
    		
    		QuarterlyProjectBudgetBean qpbBean = new QuarterlyProjectBudgetBean();
    		qpbBean.setProjectNewBean(projectNewBean);
    		qpbBean.setYear(year);
    		qpbBean.setQuarter(quarter);
    		
    		float quarterlyProjectBudget = 0;
    		
    		for(MonthlyProjectBudgetBean mpbBean: mpbProjectMap.get(projectNewBean)){
    			quarterlyProjectBudget = quarterlyProjectBudget + mpbBean.getProjectMonthlyBudgetValue();
    		}
    		
    		qpbBean.setProjectQuarterlyBudgetValue(quarterlyProjectBudget);
    		
    		quarterDetailsBean.setQuarterlyProjectBudgetBeanSet(QuarterlyProjectBudgetDao.saveProjectQuarterlyBudget(qpbBean,quarterDetailsBean.getQuarterlyProjectBudgetBeanSet())); // virtual dao
    		
    	}
    	
    	
    	return quarterDetailsBean;
		
	}

	
	
	@Override
	public AnnualDetailsBean annualSummaryCalculation(int year) {
		
		MonthDetailsBean monthDetailsBean = new MonthDetailsBean();
		AnnualDetailsBean annualDetailsBean = new AnnualDetailsBean();
		
		int indexOfTheFirstMonthOfTheSelectedYear=0;
		int maximumIteration = 12;
		
		monthDetailsBean = populateMonthDetailsBean(year, monthDetailsBean, indexOfTheFirstMonthOfTheSelectedYear, maximumIteration);
		
		List<MonthlyProjectActualBean> mpaBeanList = MonthlyProjectActualDao.getMonthlyProjectActualThisYear(year,monthDetailsBean.getMonthlyProjectActualBeanSet()); // vitualo dao
		
		Map<ProjectNewBean, Set<MonthlyProjectActualBean>> mpaProjectMap = getMapOfProjectNewBeanAsKeyAndSetOfMPABeanAsValue(mpaBeanList);
		
        for(ProjectNewBean projectNewBean : mpaProjectMap.keySet()){
    		
    		AnnualProjectActualBean apaBean = new AnnualProjectActualBean();
    		apaBean.setProjectNewBean(projectNewBean);
    		apaBean.setYear(year);
    		
    		float annualProjectCost = 0;
    		
    		for(MonthlyProjectActualBean mpaBean: mpaProjectMap.get(projectNewBean)){
    			annualProjectCost = annualProjectCost + mpaBean.getProjectMonthlyCost();
    		}
    		
    		apaBean.setProjectAnnualCost(annualProjectCost);
    		
    		annualDetailsBean.setAnnualProjectActualBeanSet(AnnualProjectActualDao.saveProjectAnnualCost(apaBean,annualDetailsBean.getAnnualProjectActualBeanSet())); // virtual dao
    		
    	}
        
        List<MonthlyProjectBudgetEntity> mpbEntityList = emailService.getMonthlyProjectBudgetThisYear(year);
        
        Map<ProjectNewBean, Set<MonthlyProjectBudgetBean>> mpbProjectMap = getMapOfProjectNewBeanAsKeyAndSetOfMPBBeanAsValue(mpbEntityList);
        
        for(ProjectNewBean projectNewBean : mpbProjectMap.keySet()){
    		
    		AnnualProjectBudgetBean apbBean = new AnnualProjectBudgetBean();
    		apbBean.setProjectNewBean(projectNewBean);
    		apbBean.setYear(year);
    		
    		float annualProjectBudget = 0;
    		
    		for(MonthlyProjectBudgetBean mpbBean: mpbProjectMap.get(projectNewBean)){
    			annualProjectBudget = annualProjectBudget + mpbBean.getProjectMonthlyBudgetValue();
    		}
    		
    		apbBean.setProjectAnnualBudgetValue(annualProjectBudget);
    		
    		annualDetailsBean.setAnnualProjectBudgetBeanSet(AnnualProjectBudgetDao.saveProjectAnnualBudget(apbBean,annualDetailsBean.getAnnualProjectBudgetBeanSet())); // virtual dao
    		
    	}
    	
		
		return annualDetailsBean;
	}

	public MonthDetailsBean populateMonthDetailsBean(int year, MonthDetailsBean monthDetailsBean, int indexOfTheFirstMonthOfTheSelectedDuration, int maximumIteration) {
		
		for(int a=0; a<maximumIteration ; a++){
			
			int indexOfTheIteratingMonthOfTheSelectedDuration = a + indexOfTheFirstMonthOfTheSelectedDuration;
			
			Calendar pastCalendar = Calendar.getInstance();
			pastCalendar.set(year,indexOfTheIteratingMonthOfTheSelectedDuration,1);
			Date firstDateOfTheIteratingMonth = pastCalendar.getTime();
			pastCalendar.set(Calendar.DAY_OF_MONTH, pastCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date lastDateOfTheIteratingMonth =  pastCalendar.getTime();
			
			float totalWeekDaysOfTheIteratingMonth = getWeekDays(firstDateOfTheIteratingMonth,lastDateOfTheIteratingMonth);
			
			List<PersonStaffing> staffingThisMonth = emailService.getStaffingThisMonth(firstDateOfTheIteratingMonth, lastDateOfTheIteratingMonth); // dates should be inclusive
			
			Map<ProjectNewBean,Set<StaffingMonthlyCostBean>> smcProjectMap = new HashMap<ProjectNewBean,Set<StaffingMonthlyCostBean>>();
			
			for(PersonStaffing personStaffingEntity :staffingThisMonth){
				
				PersonStaffingBean personStaffingBean = PersonStaffingConverter.personStaffingEntityToBean(personStaffingEntity);
				
				StaffingMonthlyCostBean smcBean = new StaffingMonthlyCostBean();
				
				smcBean.setPersonStaffingBean(personStaffingBean);
				smcBean.setYear(year);
				
				Months month =Months.getMonth(indexOfTheIteratingMonthOfTheSelectedDuration);
				smcBean.setMonth(month);
				
				Date staffingStartDate = personStaffingEntity.getStartDate();
				Date staffingEndDate   = personStaffingEntity.getEndDate();
				
				float individualMonthlyCost=0;
				
				if((staffingStartDate.before(firstDateOfTheIteratingMonth))&&(staffingEndDate.after(lastDateOfTheIteratingMonth))){
					individualMonthlyCost = (personStaffingEntity.getRate().getRate())*(personStaffingEntity.getAllocation()/100);
					individualMonthlyCost = Math.round(individualMonthlyCost);
				}
				else{
					if(staffingStartDate.before(firstDateOfTheIteratingMonth)){
					   staffingStartDate = firstDateOfTheIteratingMonth;
					}
					if(staffingEndDate.after(lastDateOfTheIteratingMonth)){
					   staffingEndDate = lastDateOfTheIteratingMonth;
					}
					float staffedWeekDaysOfTheIteratingMonth = getWeekDays(staffingStartDate,staffingEndDate);
					individualMonthlyCost = (personStaffingEntity.getRate().getRate())*(personStaffingEntity.getAllocation()/100)*(staffedWeekDaysOfTheIteratingMonth/totalWeekDaysOfTheIteratingMonth);
					individualMonthlyCost = Math.round(individualMonthlyCost);
				}
				
				smcBean.setIndividualMonthlyCost(individualMonthlyCost);
				
				monthDetailsBean.setStaffingMonthlyCostBeanSet(StaffingMonthlyCostDao.saveStaffingMonthlyCost(smcBean,monthDetailsBean.getStaffingMonthlyCostBeanSet())); // virtual dao
				
				Set<StaffingMonthlyCostBean> smcSet = smcProjectMap.get(personStaffingBean.getProject());
				if(smcSet==null){
				   smcSet = new HashSet<StaffingMonthlyCostBean>();
				}
				smcSet.add(smcBean);
				smcProjectMap.put(personStaffingBean.getProject(), smcSet);
				
			}
			
			for(ProjectNewBean projectNewBean : smcProjectMap.keySet()){
				
			    MonthlyProjectActualBean mpaBean = new MonthlyProjectActualBean();
			    mpaBean.setProject(projectNewBean);
			    mpaBean.setYear(year);
				
				Months month =Months.getMonth(indexOfTheIteratingMonthOfTheSelectedDuration);
				mpaBean.setMonth(month);
				
				float monthlyProjectCost = 0;
				
				for(StaffingMonthlyCostBean smcBean: smcProjectMap.get(projectNewBean)){
					monthlyProjectCost = monthlyProjectCost + smcBean.getIndividualMonthlyCost();
				}
				
				mpaBean.setProjectMonthlyCost(monthlyProjectCost);
				
				monthDetailsBean.setMonthlyProjectActualBeanSet(MonthlyProjectActualDao.saveProjectMonthlyCost(mpaBean,monthDetailsBean.getMonthlyProjectActualBeanSet())); // virtual dao
			
			}
			
		}
		
		return monthDetailsBean;
		
	}
	
    public Map<ProjectNewBean, Set<MonthlyProjectBudgetBean>> getMapOfProjectNewBeanAsKeyAndSetOfMPBBeanAsValue(List<MonthlyProjectBudgetEntity> mpbEntityList) {
		
		Map<ProjectNewBean,Set<MonthlyProjectBudgetBean>> mpbProjectMap = new HashMap<ProjectNewBean,Set<MonthlyProjectBudgetBean>>();
    	
    	for(MonthlyProjectBudgetEntity mpbEntity : mpbEntityList){
    		
    	MonthlyProjectBudgetBean mpbBean = MonthlyProjectBudgetConverter.MonthlyProjectBudgetEntityToBean(mpbEntity);
    	Set<MonthlyProjectBudgetBean> mpbBeanSet = mpbProjectMap.get(mpbBean.getProjectNewBean());
    	if(mpbBeanSet==null){
    	mpbBeanSet = new HashSet<MonthlyProjectBudgetBean>();
 		}
    	mpbBeanSet.add(mpbBean);
    	mpbProjectMap.put(mpbBean.getProjectNewBean(), mpbBeanSet);
    		
    	}
		return mpbProjectMap;
	}

	public Map<ProjectNewBean, Set<MonthlyProjectActualBean>> getMapOfProjectNewBeanAsKeyAndSetOfMPABeanAsValue(List<MonthlyProjectActualBean> mpaBeanList) {
		
		Map<ProjectNewBean,Set<MonthlyProjectActualBean>> mpaProjectMap = new HashMap<ProjectNewBean,Set<MonthlyProjectActualBean>>();
    	
    	for(MonthlyProjectActualBean mpaBean : mpaBeanList){
    		
	    	Set<MonthlyProjectActualBean> mpaBeanSet = mpaProjectMap.get(mpaBean.getProject());
	    	if(mpaBeanSet==null){
	    	   mpaBeanSet = new HashSet<MonthlyProjectActualBean>();
	 		}
	    	mpaBeanSet.add(mpaBean);
	    	mpaProjectMap.put(mpaBean.getProject(), mpaBeanSet);
	    		
	    	}
		return mpaProjectMap;
	}
	

	@Override
	public int getWeekDays(Date fromDate, Date toDate){
		 
		  Calendar iterationEndCalendar = Calendar.getInstance();
		  iterationEndCalendar.setTime(toDate);
		  iterationEndCalendar.set(Calendar.HOUR_OF_DAY, 0);
		  iterationEndCalendar.set(Calendar.MINUTE, 0);
		  iterationEndCalendar.set(Calendar.SECOND, 0);
		  iterationEndCalendar.set(Calendar.MILLISECOND, 0);
		  Date iterationEndDate = iterationEndCalendar.getTime();
		  
		  Calendar iterationCurrentCalendar = Calendar.getInstance();
		  iterationCurrentCalendar.setTime(fromDate);
		  iterationCurrentCalendar.set(Calendar.HOUR_OF_DAY, 0);
		  iterationCurrentCalendar.set(Calendar.MINUTE, 0);
		  iterationCurrentCalendar.set(Calendar.SECOND, 0);
		  iterationCurrentCalendar.set(Calendar.MILLISECOND, 0);
		  Date iterationCurrentDate = iterationCurrentCalendar.getTime();
		  
		  int weekDays = 0;
		  
		  for(;(!(iterationCurrentDate.after(iterationEndDate)));){
			 
			  iterationCurrentCalendar.setTime(iterationCurrentDate);
			  if((iterationCurrentCalendar.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY)&&(iterationCurrentCalendar.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY)){
			      weekDays = weekDays + 1;
			  }
			  iterationCurrentCalendar.add(Calendar.DAY_OF_MONTH, 1);
			  iterationCurrentCalendar.set(Calendar.HOUR_OF_DAY, 0);
			  iterationCurrentCalendar.set(Calendar.MINUTE, 0);
			  iterationCurrentCalendar.set(Calendar.SECOND, 0);
			  iterationCurrentCalendar.set(Calendar.MILLISECOND, 0);
			  iterationCurrentDate = iterationCurrentCalendar.getTime();
			  
		  }
		  
		  return weekDays;
	}

}
