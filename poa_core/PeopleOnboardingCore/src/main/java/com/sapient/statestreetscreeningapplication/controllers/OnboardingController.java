package com.sapient.statestreetscreeningapplication.controllers;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.service.IntervieweeService;
import com.sapient.statestreetscreeningapplication.model.service.LocationService;
import com.sapient.statestreetscreeningapplication.model.service.OnboardingResourceService;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.model.service.SendEmailService;
import com.sapient.statestreetscreeningapplication.model.service.StatusService;
import com.sapient.statestreetscreeningapplication.ui.bean.LocationNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonStaffingBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

@RestController
public class OnboardingController {
	
	@Autowired
	private OnboardingResourceService onboardingResourceService;

	@Autowired
	private PersonLookupService personLookupService;
	
	@Autowired
	private IntervieweeService intervieweeService;

	@Autowired
	private SendEmailService sendEmailService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private StatusService statusService;
	
	@CrossOrigin
	@RequestMapping("/retrieveOnboardingChecklist")
	public List<OnboardingCheckListBean> retrieveOnboardingChecklist()
	{
		return onboardingResourceService.getOnboardingChecklistNew();
	}
	
	@CrossOrigin
	@RequestMapping("/getOnboardingBean")
	public OnboardingCheckListBean getOnboardingBean()
	{
		return new OnboardingCheckListBean();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/editOnboardingChecklist", method = RequestMethod.POST)
	public void editOnboardingChecklist(@RequestBody OnboardingCheckListBean onboardingCheckListBean)
	{
		onboardingResourceService.editOnboardingChecklistLead22(onboardingCheckListBean);
	}
	
	@CrossOrigin
	@RequestMapping("/getStatusList")
	public List<String> getStatusList()
	{
		return statusService.getAllUsedStatus();
	}
	
	@CrossOrigin
	@RequestMapping("/getStatusListForOnboardingInProgress")
	public List<String> getStatusListForOnboardingInProgress()
	{
		return statusService.getCheckListResults();
	}
	
	
	
	@CrossOrigin
	@RequestMapping("/getResultList")
	public List<String> getResultList()
	{
		return statusService.getCheckListResults();
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/requestdocumentResubmission")
	public void requestdocumentResubmission(@RequestParam("onboardingCheckList")  OnboardingCheckListBean onboardingCheckListBean,
			@RequestParam("details") List<String> resubmissionDetails ,
			@RequestParam("comments") String commentsForResubmission )
	{
		PersonBean personBean=new PersonBean();
		
		Person p= onboardingResourceService.fetchPersonByPersonID(onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonId());
		
				//onboardingResourceService.fetchPersonStaffingByPersonID(onboardingCheckList.getPersonStaffingBean().getPerson().getPersonId());
		if(p.getIsTemp()!=null && p.getIsTemp()){
			personBean.setEmail(p.getTempPerson().getTempPersonEmail());
			personBean.setName(p.getTempPerson().getTempPersonName());
			
		}else{
			PersonBean p2=personLookupService.getPersonByNTId(p.getPersonNtId());
			personBean.setEmail(p2.getEmail());
			personBean.setName(p2.getName());
			
		}
		
		intervieweeService.changeNewStatus(onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonId(),
				"Onboarding in progress", "Forms asked to resubmit",onboardingCheckListBean.getOnboardingCheckListId());
		/*
		 * intervieweeService.changeStatus(intervieweeService.
		 * fetchIntervieweeOnOracleId((int)
		 * oracleIdForResubmission).getIntervieweeId(),
		 * "Onboarding in progress", "Forms asked to resubmit");
		 */
		sendEmailService.sendResubmissionNotificationMail(personBean,
				resubmissionDetails, commentsForResubmission);
		
	}
			
			
	
	@CrossOrigin
	@RequestMapping(value = "/resendBGCDocumentsToCandidate", method = RequestMethod.POST)
	public void resendBGCDocumentsToCandidate(@RequestBody  OnboardingCheckListBean onboardingCheckListBean )/*String jsonObject*/
	{
		
//		Gson gson= new Gson();
//		OnboardingCheckListBean onboardingCheckListBean=gson.fromJson(jsonObject, OnboardingCheckListBean.class);
		 
		CustomLoggerUtils.INSTANCE.log.info("inside sendBGCdocuments() method and in sendBGCAction");

		PersonStaffingBean personStaffingBean = (onboardingResourceService
				.fetchNewOnboardingCheckList(onboardingCheckListBean.getOnboardingCheckListId())).getPersonStaffingBean();
		LocationNewBean location = locationService
				.getLocationByName(personStaffingBean.getLocation().getCity());
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
		else if (location.getCity().equalsIgnoreCase("Toronto"))
			selectedBGC = 5;
		CustomLoggerUtils.INSTANCE.log.info(personStaffingBean.toString());
		//PersonNewBean currentUser=(PersonNewBean) ServletActionContext.getRequest().getSession().getAttribute("user");
		
		//PersonNewBean currentUser=(PersonNewBean) ServletActionContext.getRequest().getSession().getAttribute("user");
		PersonNewBean currentUser=new PersonNewBean();
		PersonBean personDetails=new PersonBean();
		personDetails.setName("Suraj");
		currentUser.setPersonOracleId(115446);
		currentUser.setPersonDetails(personDetails);
		
		if(selectedBGC!=0){
		onboardingResourceService.sendBGCdocumentsToCandidate(selectedBGC,onboardingCheckListBean.getPersonStaffingBean().getPerson().getSupervisorId(), personStaffingBean, onboardingCheckListBean,currentUser);
		}
	}
	

}
