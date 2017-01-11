package com.sapient.statestreetscreeningapplication.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.statestreetscreeningapplication.model.entity.EmailDl;
import com.sapient.statestreetscreeningapplication.model.entity.MonthlyProjectBudgetEntity;
import com.sapient.statestreetscreeningapplication.model.entity.OnboardingCheckList;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.entity.ProjectNew;
import com.sapient.statestreetscreeningapplication.model.service.DLService;
import com.sapient.statestreetscreeningapplication.model.service.DashboardService;
import com.sapient.statestreetscreeningapplication.model.service.LocationService;
import com.sapient.statestreetscreeningapplication.model.service.OnboardingResourceService;
import com.sapient.statestreetscreeningapplication.model.service.PositionService;
import com.sapient.statestreetscreeningapplication.model.service.ProjectService;
import com.sapient.statestreetscreeningapplication.model.service.RateService;
import com.sapient.statestreetscreeningapplication.ui.bean.ActualSummaryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.BudgetSummaryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.EmailDlBean;
import com.sapient.statestreetscreeningapplication.ui.bean.LocationNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.MonthlyProjectBudgetBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectSummaryBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.EmailDlConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.LocationNewConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.MonthlyProjectBudgetConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonNewConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonStaffingConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.PositionConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.ProjectNewConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.RateConverter;

@RestController
public class EditBudgetController {
	
	@Autowired
	private ProjectService projectService;
	

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private DLService dlService;
	
	@Autowired
	private OnboardingResourceService onboardingResourceService;
	
	@Autowired
	private RateService rateService;
	
	@Autowired
	private PositionService positionService;
	
	@CrossOrigin
	@RequestMapping("/getBudgetSummary")
	public BudgetSummaryBean getBudgetSummaryBeanAction(@RequestParam("year") int  year,
			@RequestParam("projectName") String  projectName) {
		CustomLoggerUtils.INSTANCE.log.info("inside  getMonthlyProjectBudgetAction() method of EditBudgetAction");
		ProjectNew pn = ProjectNewConverter.convertNewProjectBeanToProjectNewEntity(projectService.getNewProjectName(projectName));
		List<MonthlyProjectBudgetEntity> mpbEntityList = projectService.getMonthlyProjectBudgetThisYearThisProject(year, pn);
		BudgetSummaryBean bSummaryBean = new BudgetSummaryBean();
		if(mpbEntityList != null){
			for(MonthlyProjectBudgetEntity mpbEntity : mpbEntityList){
				MonthlyProjectBudgetBean mpbBean = MonthlyProjectBudgetConverter.MonthlyProjectBudgetEntityToBean(mpbEntity);
				bSummaryBean.setMonthlyProjectBudgetBean(mpbBean);
			}
		}
		bSummaryBean.setQuarterlyProjectBudget();
		return bSummaryBean;
	}
	
	@CrossOrigin
	@RequestMapping("/getActualSummary")
	public ActualSummaryBean getActualSummaryBeanAction(@RequestParam("year") int  year,
			@RequestParam("projectName") String  projectName) {

		float jan = 0;
		float feb = 0;
		float march = 0;
		float april = 0;
		float may = 0;
		float june = 0;
		float july = 0;
		float aug = 0;
		float sept = 0;
		float oct = 0;
		float nov = 0;
		float dec = 0;
		List<ProjectSummaryBean> pSummaryBeanList = dashboardService.projectSummaryCalculation((projectService.getNewProjectName(projectName)).getProjectId(), year);
		ActualSummaryBean aSummaryBean = new ActualSummaryBean();
		if(pSummaryBeanList.size()>0){
			for(ProjectSummaryBean projectSummaryBean : pSummaryBeanList) {
				jan = jan + projectSummaryBean.getJan().getIndividualMonthlyCost();
				feb = feb + projectSummaryBean.getFeb().getIndividualMonthlyCost();	
				march = march + projectSummaryBean.getMarch().getIndividualMonthlyCost();
				april = april + projectSummaryBean.getApril().getIndividualMonthlyCost();
				may = may + projectSummaryBean.getMay().getIndividualMonthlyCost();
				june = june + projectSummaryBean.getJune().getIndividualMonthlyCost();
				july = july + projectSummaryBean.getJuly().getIndividualMonthlyCost();
				aug = aug + projectSummaryBean.getAug().getIndividualMonthlyCost();
				sept = sept + projectSummaryBean.getSept().getIndividualMonthlyCost();
				oct = oct + projectSummaryBean.getOct().getIndividualMonthlyCost();
				nov = nov + projectSummaryBean.getNov().getIndividualMonthlyCost();
				dec = dec + projectSummaryBean.getDec().getIndividualMonthlyCost();
		}
		aSummaryBean.setJan(jan);
		aSummaryBean.setFeb(feb);
		aSummaryBean.setMarch(march);
		aSummaryBean.setApril(april);
		aSummaryBean.setMay(may);
		aSummaryBean.setJune(june);
		aSummaryBean.setJuly(july);
		aSummaryBean.setAug(aug);
		aSummaryBean.setSept(sept);
		aSummaryBean.setOct(oct);
		aSummaryBean.setNov(nov);
		aSummaryBean.setDec(dec);
		aSummaryBean.setQuarterlyProjectActual();
		}
		return aSummaryBean;
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/editMonthlyProjectBudget", method = RequestMethod.POST)
	public void EditMonthlyProjectBudgetAction(@RequestBody BudgetSummaryBean bSummaryBean){
		
		CustomLoggerUtils.INSTANCE.log.info("inside EditMonthlyProjectBudgetAction() method of EditBudgetAction");
		
		if( (bSummaryBean.getJan().getMonthlyProjectBudgetId() != null) && (bSummaryBean.getJan().getMonthlyProjectBudgetId() != 0) && (!(bSummaryBean.getJan().getProjectMonthlyBudgetValue() < 0)) ) {
			 projectService.editMonthlyProjectBudget(bSummaryBean.getJan());
		}
		
		if( (bSummaryBean.getFeb().getMonthlyProjectBudgetId() != null) && (bSummaryBean.getFeb().getMonthlyProjectBudgetId() != 0) && (!(bSummaryBean.getFeb().getProjectMonthlyBudgetValue() < 0)) ) {
			 projectService.editMonthlyProjectBudget(bSummaryBean.getFeb());
		}
		if( (bSummaryBean.getMarch().getMonthlyProjectBudgetId() != null) && (bSummaryBean.getMarch().getMonthlyProjectBudgetId() != 0) && (!(bSummaryBean.getMarch().getProjectMonthlyBudgetValue() < 0)) ) {
			 projectService.editMonthlyProjectBudget(bSummaryBean.getMarch());
		}
		if( (bSummaryBean.getApril().getMonthlyProjectBudgetId() != null) && (bSummaryBean.getApril().getMonthlyProjectBudgetId() != 0) && (!(bSummaryBean.getApril().getProjectMonthlyBudgetValue() < 0)) ) {
			 projectService.editMonthlyProjectBudget(bSummaryBean.getApril());
		}
		if( (bSummaryBean.getMay().getMonthlyProjectBudgetId() != null) && (bSummaryBean.getMay().getMonthlyProjectBudgetId() != 0) && (!(bSummaryBean.getMay().getProjectMonthlyBudgetValue() < 0)) ) {
			 projectService.editMonthlyProjectBudget(bSummaryBean.getMay());
		}
		if( (bSummaryBean.getJune().getMonthlyProjectBudgetId() != null) && (bSummaryBean.getJune().getMonthlyProjectBudgetId() != 0) && (!(bSummaryBean.getJune().getProjectMonthlyBudgetValue() < 0)) ) {
			 projectService.editMonthlyProjectBudget(bSummaryBean.getJune());
		}
		if( (bSummaryBean.getJuly().getMonthlyProjectBudgetId() != null) && (bSummaryBean.getJuly().getMonthlyProjectBudgetId() != 0) && (!(bSummaryBean.getJuly().getProjectMonthlyBudgetValue() < 0)) ) {
			 projectService.editMonthlyProjectBudget(bSummaryBean.getJuly());
		}
		if( (bSummaryBean.getAug().getMonthlyProjectBudgetId() != null) && (bSummaryBean.getAug().getMonthlyProjectBudgetId() != 0) && (!(bSummaryBean.getAug().getProjectMonthlyBudgetValue() < 0)) ) {
			 projectService.editMonthlyProjectBudget(bSummaryBean.getAug());
		}
		if( (bSummaryBean.getSept().getMonthlyProjectBudgetId() != null) && (bSummaryBean.getSept().getMonthlyProjectBudgetId() != 0) && (!(bSummaryBean.getSept().getProjectMonthlyBudgetValue() < 0)) ) {
			 projectService.editMonthlyProjectBudget(bSummaryBean.getSept());
		}
		if( (bSummaryBean.getOct().getMonthlyProjectBudgetId() != null) && (bSummaryBean.getOct().getMonthlyProjectBudgetId() != 0) && (!(bSummaryBean.getOct().getProjectMonthlyBudgetValue() < 0)) ) {
			 projectService.editMonthlyProjectBudget(bSummaryBean.getOct());
		}
		if( (bSummaryBean.getNov().getMonthlyProjectBudgetId() != null) && (bSummaryBean.getNov().getMonthlyProjectBudgetId() != 0) && (!(bSummaryBean.getNov().getProjectMonthlyBudgetValue() < 0)) ) {
			 projectService.editMonthlyProjectBudget(bSummaryBean.getNov());
		}
		if( (bSummaryBean.getDec().getMonthlyProjectBudgetId() != null) && (bSummaryBean.getDec().getMonthlyProjectBudgetId() != 0) && (!(bSummaryBean.getDec().getProjectMonthlyBudgetValue() < 0)) ) {
			 projectService.editMonthlyProjectBudget(bSummaryBean.getDec());
		}

		
	}
	
	
	@CrossOrigin
	@RequestMapping("/editStaffingAssignment")
	public void EditStaffingAssignmentAction(
			@RequestParam("assignmentCode") int assignmentCode,
			@RequestParam("personStaffingId") Integer personStaffingId,
			@RequestParam("projectNameNew") String projectNameNew,
			@RequestParam("locationNameNew") String locationNameNew,
			@RequestParam("positionNameNew") String positionNameNew,
			@RequestParam("rateTypeNew") String rateTypeNew,
			@RequestParam("rateCategoryNew") String rateCategoryNew,
			@RequestParam("dlStringArray") String[] dlStringArray,
			@RequestParam("offboardingComments") String offboardingComments,
			@RequestParam("staffingStartDateNew") long staffingStartDate,
			@RequestParam("staffingEndDateNew") long staffingEndDate){
		
		CustomLoggerUtils.INSTANCE.log.info("inside EditStaffingAssignmentAction() method of EditBudgetAction");
		
		
		
		ProjectNew projectNew = projectService.getProjectNewEntityByName(projectNameNew);
		Date projectStartDate = projectNew.getProjectStartDate();
		Date projectEndDate = projectNew.getProjectEndDate();
//		PersonNewBean currentUser = (PersonNewBean)ServletActionContext.getRequest().getSession().getAttribute("user");
		PersonNewBean currentUser= new PersonNewBean();
		currentUser.setPersonOracleId(115446);
		
		Date staffingStartDateNew= new Date(staffingStartDate);
		Date staffingEndDateNew= new Date(staffingEndDate);
		if((assignmentCode==1)&&(!(staffingStartDateNew.before(projectStartDate)||projectEndDate.before(staffingEndDateNew)||staffingEndDateNew.before(staffingStartDateNew)))){
			 PersonStaffing psOld = PersonStaffingConverter.personStaffingBeanToEntity(onboardingResourceService.fetchPersonStaffingByPersonStaffingId((long) personStaffingId));
			 psOld.setStartDate(staffingStartDateNew);
			 psOld.setEndDate(staffingEndDateNew);
			 psOld.setLocation(LocationNewConverter.locationBeanToEntity(locationService.getLocationByName(locationNameNew)));
			 psOld.setPosition(PositionConverter.convertPositionBeanToEntity(positionService.getPositionByName(positionNameNew)));
			 psOld.setRate(RateConverter.rateBeanToEntity((rateService.findRate(locationNameNew, positionNameNew, psOld.getCategory().getCategoryName(), rateCategoryNew,  rateTypeNew))));
			 
				
				//dlStringArray = dlString.split(",");
				Set<EmailDl> emailDlHashSet = new HashSet<EmailDl>();
				for(String dlName : dlStringArray){
					emailDlHashSet.add(EmailDlConverter.convertBeanToEntity(dlService.getDlByDlNameAndProjectIdAndLocationId(dlName, psOld.getProject().getId(), psOld.getLocation().getLocationId())));
				}
				psOld.setEmailDls(emailDlHashSet);
			 
			 psOld = projectService.mergeStaffingNew(psOld);
			 //addActionMessage("staffing startdate/enddate has been changed successfully");
		 }
		
		if((assignmentCode==2)&&(!(staffingStartDateNew.before(projectStartDate)))){
			 PersonStaffing psOld = PersonStaffingConverter.personStaffingBeanToEntity(onboardingResourceService.fetchPersonStaffingByPersonStaffingId((long)personStaffingId));
			 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(staffingStartDateNew);
			 calendar.add(Calendar.DATE, -1);
			 if((calendar.getTime()).before(psOld.getEndDate())){
			 psOld.setEndDate(calendar.getTime()); 
			 }
			 psOld = projectService.mergeStaffingNew(psOld);
			 PersonStaffing psCopy = new PersonStaffing();
			 psCopy.setPersonStaffingId(null);
			 psCopy.setPerson(psOld.getPerson());
			 psCopy.setAllocation(psOld.getAllocation());
			 psCopy.setCategory(psOld.getCategory());
			 psCopy.setClientLead(psOld.getClientLead());
			 psCopy.setSapientLead(psOld.getSapientLead());
			 psCopy.setComments(psOld.getComments());
			 psCopy.setOffboarded(psOld.isOffboarded());
			 psCopy.setImmediateLastStaffing(psOld);
			 psCopy.setProject(projectNew);
			 psCopy.setLocation(LocationNewConverter.locationBeanToEntity(locationService.getLocationByName(locationNameNew)));
			 psCopy.setPosition(PositionConverter.convertPositionBeanToEntity(positionService.getPositionByName(positionNameNew)));
			 psCopy.setRate(RateConverter.rateBeanToEntity((rateService.findRate(locationNameNew, positionNameNew, psCopy.getCategory().getCategoryName(), rateCategoryNew,  rateTypeNew))));
			 psCopy.setStartDate(staffingStartDateNew);
			 psCopy.setEndDate(projectNew.getProjectEndDate());
			 
			 Set<EmailDl> emailDlHashSet = new HashSet<EmailDl>();
				for(String dlName : dlStringArray){
					emailDlHashSet.add(EmailDlConverter.convertBeanToEntity(dlService.getDlByDlNameAndProjectIdAndLocationId(dlName, psCopy.getProject().getId(), psCopy.getLocation().getLocationId())));
				}
				psCopy.setEmailDls(emailDlHashSet);
			 
			 PersonStaffing psNew = projectService.mergeStaffingNew(psCopy);
			 psOld.setImmediateNextStaffing(psNew);
			 psOld = projectService.mergeStaffingNew(psOld);
			 OnboardingCheckList oCheckListOld = onboardingResourceService.getOnboardingCheckListByStaffingId((long)personStaffingId);
			 oCheckListOld.setOnboardingCheckListId(null);
			 oCheckListOld.setPersonStaffing(psNew);
			 oCheckListOld.setInitiator(PersonNewConverter.personBeanToEntity(currentUser));
			 oCheckListOld.setDateOnboardingInitiated(new Date());
			 oCheckListOld = onboardingResourceService.mergeOnboardingCheckList(oCheckListOld);
			 //addActionMessage("assignment has been changed successfully");
		 }
		
		if(assignmentCode==3){
		     onboardingResourceService.removeCurrentStaffing((long) personStaffingId);
		     //addActionMessage("assignment has been removed successfully");
		}
		
		if(assignmentCode==4){
		     OnboardingCheckList oCheckListOld = onboardingResourceService.getOnboardingCheckListByStaffingId((long)personStaffingId);
		     onboardingResourceService.terminateOnboarding(oCheckListOld.getOnboardingCheckListId(), staffingStartDateNew.toString() , offboardingComments, currentUser);
		     //addActionMessage("offboarding has been initiated successfully");
		}
		
		
	}	
	
	
	@CrossOrigin
	@RequestMapping("/getDlForLocationAndProject")
	public List<EmailDlBean> mappingIntervieweeActiveDlAction(@RequestParam("locationName") String locationName,
			@RequestParam("teamName") String teamName) {
		CustomLoggerUtils.INSTANCE.log.info("inside  mappingDl() method of IntervieweeAction");
	LocationNewBean locationBean=locationService.getNewLocationByName(locationName);
	ProjectNewBean projectBean=projectService.getNewProjectName(teamName);
	return dlService.getActiveDlBeansByProjectIdAndLocationId(projectBean.getId(),locationBean.getLocationId());
	}

}
