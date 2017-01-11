package com.sapient.statestreetscreeningapplication.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sapient.statestreetscreeningapplication.model.service.CategoryService;
import com.sapient.statestreetscreeningapplication.model.service.DLService;
import com.sapient.statestreetscreeningapplication.model.service.IntervieweeService;
import com.sapient.statestreetscreeningapplication.model.service.LocationService;
import com.sapient.statestreetscreeningapplication.model.service.OnboardingResourceService;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.model.service.PersonService;
import com.sapient.statestreetscreeningapplication.model.service.PositionService;
import com.sapient.statestreetscreeningapplication.model.service.ProjectService;
import com.sapient.statestreetscreeningapplication.model.service.RateService;
import com.sapient.statestreetscreeningapplication.model.service.ScoreService;
import com.sapient.statestreetscreeningapplication.model.service.SendEmailService;
import com.sapient.statestreetscreeningapplication.ui.bean.CategoryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.LocationNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonScreeningDetailsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonStaffingBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PositionBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.RateBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ScoresNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.StatusBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

@RestController
public class IntervieweeController {
	
	@Autowired
	private IntervieweeService intervieweeService;
	
	@Autowired
	private ScoreService scoreService;
	
	private Gson gson = new Gson();

	@Autowired
	private SendEmailService sendEmailService;
	
	@Autowired
	private PersonLookupService personLookupService;
	
	@Autowired
	private PersonLookupService personLookUpService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OnboardingResourceService onboardingResourceService;
	
	@Autowired
	private RateService rateService;
	
	@Autowired
	private DLService dlService;
	
	@Autowired
	private PersonService personService;

	@CrossOrigin
	@RequestMapping("/displayAllInterviewee")
	public Map<String, Object> retrieveInterviweeServer(@RequestParam("start") int start,
			@RequestParam("rows") int rows,
			@RequestParam("colNum") int colNum,
			@RequestParam("searchKey") String searchKey,
			@RequestParam("sortDirection") String  sortDirection)
	{
		   
		return intervieweeService.getIntervieweesCountAndList(start,rows,colNum,searchKey,sortDirection);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/getScoresList")
	public Set<ScoresNewBean> getScoresList(@RequestParam("personScreeningId") long personScreeningId)
	{
		
		return scoreService.fetchScores(personScreeningId);
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/getPersonStaffingBean")
	public PersonStaffingBean getPersonStaffingBean()
	{
		
		PersonStaffingBean personStaffingBean= new PersonStaffingBean();
		personStaffingBean.setCategoryBean(new CategoryBean());
		personStaffingBean.setRateBean(new RateBean());
		personStaffingBean.setLocation(new LocationNewBean());
		personStaffingBean.setPosition(new PositionBean());
		personStaffingBean.setProject(new ProjectNewBean());
		
		return personStaffingBean;
		
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/getOnboardingCheckListBean")
	public OnboardingCheckListBean getOnboardingCheckListBean()
	{
		OnboardingCheckListBean onboardingCheckListBean= new OnboardingCheckListBean();
		onboardingCheckListBean.setInitiator(new PersonNewBean());
		onboardingCheckListBean.setPersonStaffingBean(new PersonStaffingBean());
						
		return onboardingCheckListBean;
		
		
	}

	
	@CrossOrigin
	@RequestMapping(value = "/getInterviewee")
	public PersonScreeningDetailsBean getInterviewee(@RequestParam("personScreeningId") long personScreeningId)
	{
		
		return intervieweeService.fetchInterviewee(personScreeningId);
		
	}
	

	@CrossOrigin
	@RequestMapping(value = "/getAsEmail", method = RequestMethod.POST)
	public void getAsEmail(@RequestBody String[] personScreeningIdList ) {

		List<Long> intervieweeNos = new ArrayList<Long>();
		for (String s : personScreeningIdList) {
			intervieweeNos.add(Long.valueOf(s));
		}
		
		List<PersonScreeningDetailsBean> intervieweeList = new ArrayList<PersonScreeningDetailsBean>();
		for (Long val : intervieweeNos) {
			PersonScreeningDetailsBean bean = intervieweeService
					.fetchInterviewee(val);
			Set<ScoresNewBean> list = scoreService.fetchScores(val);
			bean.setScoreList(list);
			intervieweeList.add(bean);
		}
		String interviewerEmailId="srawat7@sapient.com";
		CustomLoggerUtils.INSTANCE.log.info("sending mail to "+ interviewerEmailId);
		
		sendEmailService.sendEmail(intervieweeList, interviewerEmailId);
	
	}

	@CrossOrigin
	@RequestMapping(value = "/notifyStaffing", method = RequestMethod.POST)
	public void notifyStaffing(@RequestBody String[] personScreeningIdList) {

		CustomLoggerUtils.INSTANCE.log.info("inside  notifyStaffing() method and in NotifyStaffingAction");
		List<Long> intervieweeNos = new ArrayList<Long>();
		
		for (String s : personScreeningIdList) {
			intervieweeNos.add(Long.valueOf(s));
		}
		
		List<PersonScreeningDetailsBean> intervieweeList = new ArrayList<PersonScreeningDetailsBean>();
		for (long val : intervieweeNos) {
			PersonScreeningDetailsBean bean = intervieweeService
					.fetchInterviewee(val);
			bean.setScoreList(scoreService.fetchScores(val));
			intervieweeList.add(bean);
		}
		CustomLoggerUtils.INSTANCE.log.info("sending mail to Staffing");

		String interviewerEmailId="srawat7@sapient.com";
		sendEmailService.sendEmailToStaffing(intervieweeList,
				personLookupService.getPersonByEmail(interviewerEmailId));
		// intervieweeService.changeStatus(intervieweeNos,"Screening complete","Staffing prioritization");
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/updateScreeningDetails", method = RequestMethod.POST)
	public void updateScreeningDetails(@RequestBody PersonScreeningDetailsBean personScreeningDetailsBean) {
	
		intervieweeService.updateInterviewee(personScreeningDetailsBean);
	}

	@CrossOrigin
	@RequestMapping("/retrievePersonByName")
	public List<PersonBean> retrieveInterviweeServer(@RequestParam("name") String name)
	{ 
		return personLookUpService.getPersonByName(name);
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/initiateOnboarding", method = RequestMethod.POST)
	public void initiateOnboarding(@RequestBody OnboardingCheckListBean onboardingCheckListBean
			) {
		
		CustomLoggerUtils.INSTANCE.log.info("inside initiateOnboarding() method and in InitiateOnboardingAction");
		//Map<String,Object> map= gson.fromJson(string, HashMap.class);

		//OnboardingCheckListBean onboardingCheckListBean=(OnboardingCheckListBean) map.get("onboardingCheckListBean");
		PersonScreeningDetailsBean personScreeningDetailsBean = (PersonScreeningDetailsBean) onboardingCheckListBean.getPersonScreeningDetails();
		PersonStaffingBean personStaffingBean= onboardingCheckListBean.getPersonStaffingBean();
		
		String multipleDlvalues= onboardingCheckListBean.getPersonStaffingBean().getDlNames();
		
		PersonBean personBean=personLookUpService.getPersonByNTId(onboardingCheckListBean.getInitiator().getPersonNtId());
		PersonNewBean currentUser= personService.getPersonNewBeanByPersonOracleId(personBean.getOracleId());
		
		
		try{
			
			ProjectNewBean project = projectService.getNewProjectName(personStaffingBean.getProject().getProjectName());
			personStaffingBean.setProject(project);			
			
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			Date psStartDate = personStaffingBean.getStaffingStartDate();
			Date projectNewStartDate = formatter.parse(personStaffingBean.getProject().getProjectStartDate());
			Date projectNewEndDate = formatter.parse(personStaffingBean.getProject().getProjectEndDate());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(psStartDate);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			psStartDate = calendar.getTime();
			calendar.setTime(projectNewStartDate);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			projectNewStartDate = calendar.getTime();
			calendar.setTime(projectNewEndDate);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			projectNewEndDate = calendar.getTime();
			boolean valid = true;
			
			if(psStartDate.before(projectNewStartDate)){
				valid = false;
	
			}
			
	        if(projectNewEndDate.before(psStartDate)){
	        	valid = false;

			}
	        
	        if(valid){
	        	//addActionError("");
	    		PersonNewBean initiator=currentUser;
	    				//(PersonNewBean) map.get("currentUser");
	    		
	    		PersonNewBean personNewBean = intervieweeService.findTempPersonDetailsFromScreeningId(personScreeningDetailsBean.getPersonScreeningId());
	    		LocationNewBean location = locationService.getLocationByName(personStaffingBean.getLocation().getCity());
	    		
	    		
	    		CustomLoggerUtils.INSTANCE.log.info(personStaffingBean.getPosition()
	    				.getPositionName());

	    		 
	    		
	    		personStaffingBean.setCategoryBean(categoryService.getCategoryByName(intervieweeService.fetchPersonScreeningDetails(personScreeningDetailsBean.getPersonScreeningId()).getCategory()));
	    		String positionName = personStaffingBean.getPosition()
	    				.getPositionName();
	    		personStaffingBean.setPosition(positionService
	    				.getPositionByName(positionName));
	    		personStaffingBean.setLocation(location);
	    		personNewBean.setPosition(personStaffingBean.getPosition());
	    		personNewBean.setLocation(location);
	    		personStaffingBean.setPerson(personNewBean);
	    		// personStaffingBean.setSapientLead(supervisorBean);
	    		
	    		personStaffingBean.setEndDate(project.getProjectEndDate());

	    		
	    	//	SimpleDateFormat formatter=new SimpleDateFormat("MM/dd/yyyy");
	    	//	personStaffingBean.setEndDate(formatter.format(project.getProjectEndDate()));
	    		
	    		StatusBean statusBean = new StatusBean();
	    		statusBean.setStatusName(personScreeningDetailsBean.getStatusBean().getStatusName());
	    		statusBean.setResultName(personScreeningDetailsBean.getStatusBean().getResultName());
	    		PersonScreeningDetailsBean psdBean = new PersonScreeningDetailsBean();
	    		psdBean.setPersonScreeningId(personScreeningDetailsBean.getPersonScreeningId());
	    		psdBean.setStatusBean(statusBean);
				// psdBean.setScreeningStartDate(getScreeningStartDate());
	         // psdBean.setScreeningEndDate(getScreeningEndDate());
	    		onboardingCheckListBean.setInitiator(initiator);
	    		onboardingCheckListBean.setPersonScreeningDetails(psdBean);
	    		String categoryName1=intervieweeService.fetchPersonScreeningDetails(personScreeningDetailsBean.getPersonScreeningId()).getCategory();
	    		RateBean rateBean=rateService.findRate(location.getCity(), positionName, categoryName1,  personStaffingBean.getRateBean().getRateCategory(), personStaffingBean.getRateBean().getRateType());
	    		personStaffingBean.setRateBean(rateBean);
	    		personStaffingBean.setCategoryBean(categoryService.getCategoryByName(categoryName1));
	    		onboardingCheckListBean.setPersonStaffingBean(personStaffingBean);
	    		onboardingCheckListBean.setPerson(personNewBean);
	    		onboardingCheckListBean.setOnboardingStatus(psdBean.getStatusBean().getResultName());
	    		
	    		String[] dlArray;
	    		if (!multipleDlvalues.equals("")) {
	    			dlArray = multipleDlvalues.split(",");
	    			for (String dlName : dlArray) {
	    				personStaffingBean.getEmailDls().add(
	    						dlService.getDlByDlNameAndProjectIdAndLocationId(
	    								dlName, project.getId(),
	    								location.getLocationId()));
	    			}
	    		}
	    		
	    		int selectedBGC = 0;
	    		if (location.getCity().equalsIgnoreCase("Noida")
	    				|| location.getCity().equalsIgnoreCase("Bangalore")
	    				|| location.getCity().equalsIgnoreCase("Gurgaon"))
	    			selectedBGC = 1;
	    		else if (location.getCity().equalsIgnoreCase("New York"))
	    			selectedBGC = 2;
	    		else if (location.getCity().equalsIgnoreCase("Boston"))
	    			selectedBGC = 3;
	    		else if (location.getCity().equalsIgnoreCase("San Jose"))
	    			selectedBGC = 4;
	    		CustomLoggerUtils.INSTANCE.log.info(personStaffingBean.toString());
	    		onboardingResourceService.onboardPerson(personStaffingBean,
	    				onboardingCheckListBean);
//	    		PersonNewBean currentUser=(PersonNewBean) ServletActionContext.getRequest().getSession().getAttribute("user");
	    		
	    		
	    		if(selectedBGC!=0){
				onboardingResourceService.sendBGCdocumentsToCandidate(selectedBGC,
						currentUser.getPersonOracleId(), personStaffingBean, onboardingCheckListBean, currentUser);
	    		}else{
	    			//addActionError("Email could not be sent as no mail template is available for the selected location");
	    		}
	    		// projectLogService.saveProjectLog(newProjectName,newIQNName,newAtrackName,
	    		// ssStartDate,iqnEndDate,oracleID);	        	
	        	
	        }
		} 
		catch(ParseException e){
			e.printStackTrace();
		}

		//addActionMessage("The person has been onboarded successfully");
//		if (redirection.equals("leads-menus.jsp")){
//			return "redirection";
//		}
//		else{
//			return "success";
//		}
	}

}
