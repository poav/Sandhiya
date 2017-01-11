package com.sapient.statestreetscreeningapplication.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sapient.statestreetscreeningapplication.model.entity.OnboardingCheckList;
import com.sapient.statestreetscreeningapplication.model.service.OnboardingResourceService;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.model.service.PersonService;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonStaffingBean;
import com.sapient.statestreetscreeningapplication.ui.bean.StatusChangeLogBean;
import com.sapient.statestreetscreeningapplication.ui.bean.TempPersonBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.OnboardingCheckListConverter;

@RestController
public class OperationController {
	
	@Autowired
	private OnboardingResourceService onboardingResourceService;
	
	@Autowired
	private PersonLookupService personLookService;
	
	@Autowired
	private PersonService personService;


	@CrossOrigin
	@RequestMapping("/displayAllResourcesToBeOnBoarded")
	public List<PersonStaffingBean> fetchResourcesToBeOnboardedLeads() {
		
		return  onboardingResourceService.fetchPersonStaffing();
		
	}
	
	@CrossOrigin
	@RequestMapping(value="/displayPersonBeanForName",method=RequestMethod.POST)
	public List<PersonBean> displayPersonNames(@RequestBody String name) {
		
		return personLookService.getPersonByName(name);
	}
	
	@CrossOrigin
	@RequestMapping("/displayProjectInfo")
	public List<OnboardingCheckListBean> viewOnboardingResourceProjectInfo() {
		
		return onboardingResourceService.getOnboardingChecklistNew();
		
	}

	
	@CrossOrigin
	@RequestMapping(value = "/editPersonInfo", method = RequestMethod.POST)
	public void updateIssueTracker(@RequestBody PersonStaffingBean personStaffingBean )
	{
		onboardingResourceService.editPersonStaffing(personStaffingBean);
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/editProjectInfo", method = RequestMethod.POST)
	public void updateOnboardingResourceProjectInfo(@RequestBody OnboardingCheckListBean onboardingCheckListBean) {
		
		CustomLoggerUtils.INSTANCE.log.info("inside updateOnboardingResourceProjectInfo() method and in updateOnboardingResourceProjectInfoAction");
		
	
		onboardingResourceService.editOnboardingChecklistLead2(onboardingCheckListBean);

	}
	
	@CrossOrigin
	@RequestMapping(value = "/sendPSIDToCandidate", method = RequestMethod.POST)
	public void sendPSIDToCandidate(@RequestBody String[] obid) {
		CustomLoggerUtils.INSTANCE.log.info("inside sendPSIDToCandidateActionOps() method and in SendPSIDToCandidateAction" );
		PersonNewBean currentUser= new PersonNewBean();
		currentUser.setPersonNtId("srawa5");
		currentUser.setPersonOracleId(115446);
		
//		
//		String onboardingIDString = onBoardingId;
//        String sentId = ":";
//        String notSentId =":";
//        String[] toReplace = {"\r", "\n", "\t"," "};
//        for (String replaceString : toReplace) {
//        	onboardingIDString = onboardingIDString.replace(replaceString, "").trim();
//        }
        
        //String[] obid = onboardingIDString.split("-");

		//        PersonNewBean currentUser=(PersonNewBean) ServletActionContext.getRequest().getSession().getAttribute("user");
        
		String sentId = ":";
		String notSentId =":";
		PersonBean lead=personLookService.getPersonByOracleId(currentUser.getPersonOracleId());
        currentUser.setPersonEmailId(lead.getEmail());
        for(int i=0;i<obid.length;i++){
        	String a = obid[i];
        	int onboardingID =Integer.parseInt(a);
        	String personName;
        	
        	//this statement is changed by below statements
        	//OnboardingCheckListBean onboardingCheckListBean = onboardingResourceService.fetchNewOnboardingCheckList(onboardingID);
        	
        	OnboardingCheckList onboardingCheckList = onboardingResourceService.getOnboardingCheckListByStaffingId(new Long(onboardingID));
        	
        	OnboardingCheckListBean onboardingCheckListBean=OnboardingCheckListConverter.convertOnboardingCheckListEntityToOnboardingCheckListBean(onboardingCheckList);
        	
        	PersonNewBean person = onboardingCheckListBean.getPerson();
        	
        	if(person.getIsTemp()!=null && person.getIsTemp()){
				personName=person.getTempPersonBean().getTempPersonName();
			}else{
				personName = personLookService.getPersonByNTId(person.getPersonNtId()).getName();
			}
    		if(onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonClientId()!=null && onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonClientId().length()!=0){
    			String personClientId =onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonClientId();
   
    			onboardingResourceService.sendPSIDAndNameToCandidate(onboardingID,personClientId , personName,currentUser);
    			sentId = sentId +" " + personName + "," ;
    		}else{
    			notSentId = notSentId +" " + personName + "," ;   //addActionMessage("Please add candidate's client id to send PSID");
    		}
        }
        
        
	}
	
	@CrossOrigin
	@RequestMapping("/displayAllTempPerson")
	public List<TempPersonBean> viewAllTempPerson() {
		CustomLoggerUtils.INSTANCE.log.info("inside updateOracleId() method and in UpdateOracleIdAction");
		
		return personService.getAllTempPersons();
		
	}
	
	@CrossOrigin
	@RequestMapping(value="/updateOracleId" , method= RequestMethod.POST)
	public String updateOracleId(@RequestBody Map<String,Object> map )
	{
//		Gson gson = new Gson();
//		Map<String,Integer> map = gson.fromJson(str, Map.class);
//		
		
		Integer tempid= (Integer) map.get("tempPersonId");
		
		long tempPersonId=  tempid.longValue();
		
		Integer tempOracleID=(Integer) map.get("tempPersonOracleID");
		int tempPersonOracleID= tempOracleID.intValue();
		
		CustomLoggerUtils.INSTANCE.log.info("inside addOracleId() method and in AddOracleIdAction");
		personService.addOracleIdForTempPerson(tempPersonId,tempPersonOracleID);
		return "success";
	}
	
	@CrossOrigin
	@RequestMapping("/displayStatusChangeLog")
	public List<StatusChangeLogBean> viewStatusChangeLog() {
		CustomLoggerUtils.INSTANCE.log.info("inside ViewStatusChangeLogAction method and in ViewStatusChangeLogAction");
		return onboardingResourceService.viewStatusChangeLogList();
	}
	
	
}
