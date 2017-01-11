package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao;
import com.sapient.statestreetscreeningapplication.model.dao.LocationDao;
import com.sapient.statestreetscreeningapplication.model.dao.OnboardingResourceDao;
import com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew;
import com.sapient.statestreetscreeningapplication.model.dao.StatusDao;
import com.sapient.statestreetscreeningapplication.model.entity.OnboardingCheckList;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.entity.StatusChangeLogEntity;
import com.sapient.statestreetscreeningapplication.model.service.IntervieweeService;
import com.sapient.statestreetscreeningapplication.model.service.OnboardingResourceService;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.model.service.PersonService;
import com.sapient.statestreetscreeningapplication.model.service.SendEmailService;
import com.sapient.statestreetscreeningapplication.model.service.StatusService;
import com.sapient.statestreetscreeningapplication.ui.bean.DLBean;
import com.sapient.statestreetscreeningapplication.ui.bean.EmailDlBean;
import com.sapient.statestreetscreeningapplication.ui.bean.IntervieweeBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListLeadsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListOpsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingResourceBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PeopleInfoBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonStaffingBean;
import com.sapient.statestreetscreeningapplication.ui.bean.StatusChangeLogBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.OnboardingCheckListConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonNewConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonScreeningDetailsConvertor;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonStaffingConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.StatusChangeLogConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.StatusConverter;
import com.sapient.statestreetscreeningapplication.utils.email.ExcelFileCreator;
import com.sapient.statestreetscreeningapplication.utils.enums.PSIDStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class OnboardingResourceServiceImpl.
 */
@Service
public class OnboardingResourceServiceImpl implements OnboardingResourceService {

	/** The onboarding resource dao. */
	@Autowired
	OnboardingResourceDao onboardingResourceDao;
	@Autowired
	LocationDao locationDao;
	@Autowired
	IntervieweeDao intervieweeDao;
	/** The interviewee service. */
	@Autowired
	StatusDao statusDao;
	@Autowired
	IntervieweeService intervieweeService;

	/** The send email service. */
	@Autowired
	SendEmailService sendEmailService;

	/** The person lookup service implementation. */
	@Autowired
	PersonLookupServiceImplementation personLookupServiceImplementation;

	/** The person look service. */
	@Autowired
	private PersonLookupService personLookService;

	/** The person service. */
	@Autowired
	private PersonService personService;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	PersonDaoNew personDaoNewImpl;

	/** The checklist id. */
	private long checklistId;

	/**
	 * Gets the person lookup service implementation.
	 * 
	 * @return the person lookup service implementation
	 */
	public PersonLookupServiceImplementation getPersonLookupServiceImplementation() {
		return personLookupServiceImplementation;
	}

	/**
	 * Sets the person lookup service implementation.
	 * 
	 * @param personLookupServiceImplementation
	 *            the new person lookup service implementation
	 */
	public void setPersonLookupServiceImplementation(
			PersonLookupServiceImplementation personLookupServiceImplementation) {
		this.personLookupServiceImplementation = personLookupServiceImplementation;
	}

	/**
	 * Gets the send email service.
	 * 
	 * @return the send email service
	 */
	public SendEmailService getSendEmailService() {
		return sendEmailService;
	}

	/**
	 * Sets the send email service.
	 * 
	 * @param sendEmailService
	 *            the new send email service
	 */
	public void setSendEmailService(SendEmailService sendEmailService) {
		this.sendEmailService = sendEmailService;
	}

	/**
	 * Gets the interviewee service.
	 * 
	 * @return the interviewee service
	 */
	public IntervieweeService getIntervieweeService() {
		return intervieweeService;
	}

	/**
	 * Sets the interviewee service.
	 * 
	 * @param intervieweeService
	 *            the new interviewee service
	 */
	public void setIntervieweeService(IntervieweeService intervieweeService) {
		this.intervieweeService = intervieweeService;
	}

	/**
	 * Gets the onboarding resource dao.
	 * 
	 * @return the onboarding resource dao
	 */
	public OnboardingResourceDao getOnboardingResourceDao() {
		return onboardingResourceDao;
	}

	/**
	 * Sets the onboarding resource dao.
	 * 
	 * @param onboardingResourceDao
	 *            the new onboarding resource dao
	 */
	public void setOnboardingResourceDao(
			OnboardingResourceDao onboardingResourceDao) {
		this.onboardingResourceDao = onboardingResourceDao;
	}

	/**
	 * Gets the checklist id.
	 * 
	 * @return the checklist id
	 */
	public long getChecklistId() {
		return checklistId;
	}

	/**
	 * Sets the checklist id.
	 * 
	 * @param checklistId
	 *            the new checklist id
	 */
	public void setChecklistId(long checklistId) {
		this.checklistId = checklistId;
	}

	/**
	 * Gets the person service.
	 * 
	 * @return the person service
	 */
	public PersonService getPersonService() {
		return personService;
	}

	/**
	 * Sets the person service.
	 * 
	 * @param personService
	 *            the new person service
	 */
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapient.statestreetscreeningapplication.model.service.
	 * OnboardingResourceService
	 * #saveOnboardingResource(com.sapient.statestreetscreeningapplication
	 * .model.entity.PersonStaffing)
	 */
	@Override
	public void saveOnboardingResource(PersonStaffing personStaffing,OnboardingCheckList onboardingCheckList) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveOnboardingResource(PersonStaffing personStaffing,OnboardingCheckList onboardingCheckList) method of OnboardingResourceServiceImpl class");
		onboardingResourceDao.saveOnboardingResourceDetails(personStaffing,
				onboardingCheckList);
	}

	
	@Override
	public void onBoardWithoutScreening(PersonStaffing personStaffing, OnboardingCheckList onboardingCheckList){
		CustomLoggerUtils.INSTANCE.log.info("inside saveOnboardingResource(PersonStaffing personStaffing,OnboardingCheckList onboardingCheckList) method of OnboardingResourceServiceImpl class");
		/*onboardingCheckList.setPersonStaffing(personStaffing);*/
		onboardingResourceDao.onBoardWithoutScreening(personStaffing,
				onboardingCheckList);
	}
	
	
		/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapient.statestreetscreeningapplication.model.service.
	 * OnboardingResourceService#onboardInterviewee(int,
	 * com.sapient.statestreetscreeningapplication
	 * .ui.bean.OnboardingResourceBean)
	 */
	@Override
	public void onboardInterviewee(int oracleID,OnboardingResourceBean onboardingResourceBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside onboardInterviewee(int oracleID,OnboardingResourceBean onboardingResourceBean) method of OnboardingResourceServiceImpl class");
		long personId = intervieweeService.onboardInterviewee(oracleID,
				onboardingResourceBean);
		intervieweeService.changeStatus(personId,
				"Onboarding in progress", "Forms requested");
		createNewOnboardingCheckListEntry(oracleID);
		// notifyOperationsOfNewOnboardingRequest(personLookupServiceImplementation.getPersonByOracleId(oracleID),onboardingResourceBean);
	}

	@Override
	public void onboardPerson(PersonStaffingBean personStaffingBean,OnboardingCheckListBean onboardingCheckListBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside onboardPerson(PersonStaffingBean personStaffingBean,OnboardingCheckListBean onboardingCheckListBean) method of OnboardingResourceServiceImpl class");
		intervieweeService.onboardPerson(personStaffingBean,
				onboardingCheckListBean);
		// intervieweeService.changeStatus(personId,
		// "Onboarding in progress", "Forms requested");
		// createNewOnboardingCheckListEntry(oracleID);
		// notifyOperationsOfNewOnboardingRequest(personLookupServiceImplementation.getPersonByOracleId(oracleID),onboardingResourceBean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapient.statestreetscreeningapplication.model.service.
	 * OnboardingResourceService#reboardInterviewee(int,
	 * com.sapient.statestreetscreeningapplication
	 * .ui.bean.OnboardingResourceBean)
	 */
	/*@Override
	public void reboardInterviewee(int oracleID,OnboardingResourceBean onboardingResourceBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside reboardInterviewee(int oracleID,OnboardingResourceBean onboardingResourceBean) method of OnboardingResourceServiceImpl class");
		long personId = intervieweeService.reboardInterviewee(oracleID,
				onboardingResourceBean);
		intervieweeService.changeStatus(personId,
				"Onboarding in progress", "Forms requested");
		updateOnboardingCheckListEntry(personId);
		// notifyOperationsOfNewOnboardingRequest(personLookupServiceImplementation.getPersonByOracleId(oracleID),onboardingResourceBean);
	}
*/
	/**
	 * Update onboarding check list entry.
	 * 
	 * @param personId
	 *            the interviewee id
	 */
	/*private void updateOnboardingCheckListEntry(long personId) {
		CustomLoggerUtils.INSTANCE.log.info("inside updateOnboardingCheckListEntry(long personId) method of OnboardingResourceServiceImpl class");
		OnboardingCheckListOpsBean onboardingChecklistEntry = new OnboardingCheckListOpsBean();
		// onboardingChecklistEntry.setInterviewee(intervieweeService.fetchInterviewee(personId));
		String location = onboardingChecklistEntry.getInterviewee()
				.getLocation().getLocationName();
		if (location.equals("Noida") || location.equals("Bangalore")
				|| location.equals("Gurgaon"))
			onboardingChecklistEntry.setLocation("Offshore");
		else
			onboardingChecklistEntry.setLocation("Onshore");
		updateOnboardingCheckListEntry(OnboardingCheckListOpsConverter
				.convertOnboardingCheckListBeanToOnboardingCheckListEntity(onboardingChecklistEntry));
	}

	*//**
	 * Update onboarding check list entry.
	 * 
	 * @param onboardingResourceCheckList
	 *            the onboarding resource check list
	 *//*
	private void updateOnboardingCheckListEntry(OnboardingCheckListOps onboardingResourceCheckList) {
		CustomLoggerUtils.INSTANCE.log.info("inside updateOnboardingCheckListEntry(OnboardingCheckListOps onboardingResourceCheckList) method of OnboardingResourceServiceImpl class");
		OnboardingCheckListOps checklist = onboardingResourceDao
				.updateOnboardingChecklistEntry(onboardingResourceCheckList);
		OnboardingCheckListLeads checklistLeads = new OnboardingCheckListLeads();
		checklistLeads.setOnboardingCheckListOps(checklist);
	}
*/
	/**
	 * Creates the new onboarding check list entry.
	 * 
	 * @param personId
	 *            the interviewee id
	 */
	private void createNewOnboardingCheckListEntry(int personId) {
		CustomLoggerUtils.INSTANCE.log.info("inside createNewOnboardingCheckListEntry(int personId) method of OnboardingResourceServiceImpl class");
		// OnboardingCheckListOpsBean onboardingChecklistEntry=new
		// OnboardingCheckListOpsBean();

		OnboardingCheckListBean onboardingCheckListBean = new OnboardingCheckListBean();

		onboardingCheckListBean.setPersonStaffingBean(fetchpersonStaffingByOracleId(personId));
		// onboardingChecklistEntry.setIQNName(onboardingChecklistEntry.getInterviewee().getProjectBean().getIQNName());

		String location = onboardingCheckListBean.getPersonStaffingBean().getPerson().getLocation().getCity();
	//	onboardingCheckListBean.setPerson(onboardingCheckListBean.getPersonStaffingBean().getPerson());
		if (location.equals("Noida") || location.equals("Bangalore")
				|| location.equals("Gurgaon"))
			onboardingCheckListBean.setIsOnsite("No");
		else
			onboardingCheckListBean.setIsOnsite("Yes");
		saveOnboardingCheckListEntry(OnboardingCheckListConverter
				.convertOnboardingCheckListBeanToOnboardingCheckListEntity(onboardingCheckListBean));
	}

	/**
	 * Save onboarding check list entry.
	 * 
	 * @param onboardingResourceCheckList
	 *            the onboarding resource check list
	 */
	private void saveOnboardingCheckListEntry(OnboardingCheckList onboardingResourceCheckList) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveOnboardingCheckListEntry(OnboardingCheckList onboardingResourceCheckList) method of OnboardingResourceServiceImpl class");
		onboardingResourceCheckList.setDateOnboardingInitiated(new Date());
		OnboardingCheckList checklist = onboardingResourceDao
				.saveOnboardingChecklistEntry(onboardingResourceCheckList);
		/*
		 * OnboardingCheckListLeads checklistLeads=new
		 * OnboardingCheckListLeads();
		 * checklistLeads.setOnboardingCheckListOps(checklist);
		 * onboardingResourceDao
		 * .saveOnboardingChecklistLeadsEntry(checklistLeads);
		 */
	}

	/**
	 * Notify operations of new onboarding request.
	 * 
	 * @param person
	 *            the person
	 * @param onboardingResourceBean
	 *            the onboarding resource bean
	 */
	public void notifyOperationsOfNewOnboardingRequest(PersonBean person,OnboardingResourceBean onboardingResourceBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside notifyOperationsOfNewOnboardingRequest(PersonBean person,OnboardingResourceBean onboardingResourceBean) method of OnboardingResourceServiceImpl class");
		sendEmailService.sendEmailToOperations(person, onboardingResourceBean);
	}

	
	@Override
	public List<OnboardingCheckListBean> getOnboardingChecklistNew() {
		CustomLoggerUtils.INSTANCE.log.info("inside getOnboardingChecklistNew() method of OnboardingResourceServiceImpl class");
		List<OnboardingCheckListBean> list = OnboardingCheckListConverter
				.convertOnboardingCheckListEntityListToOnboardingCheckListBeanList(onboardingResourceDao
						.getOnboardingCheckListNew());
		List<OnboardingCheckListBean> exitedList = new ArrayList<OnboardingCheckListBean>();
		
		for (OnboardingCheckListBean bean : list) {

			/*
			 * String
			 * s=bean.getOnboardingCheckListOps().getInterviewee().getStatus
			 * ().getStatusName(); if(s.equals("Onboarding in progress"))
			 */
			if (!bean.getPersonStaffingBean().getPerson().getHasExited()) {
				bean.getPersonStaffingBean().setPerson((intervieweeService.setFieldsForPerson(bean.getPersonStaffingBean().getPerson())));
			} else {
				exitedList.add(bean);
			}
		}
		list.removeAll(exitedList);
		
		for (OnboardingCheckListBean onboardingCheckListBean : list) {
			
			List<PersonScreeningDetails> personScreeningDetailsList=intervieweeService.fetchMultiplePersonScreeningDetailsByPersonID(onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonId());

			
			Date currentDate=new Date();
			
			long minDiff = -1, currentTime = currentDate.getTime();
			 Date minDate = currentDate;
		
			for(PersonScreeningDetails personScreeningDetails:personScreeningDetailsList){
				if(personScreeningDetails.getStatus().equals("Screening complete")){
				long diff = Math.abs(currentTime - personScreeningDetails.getScreeningStartDate().getTime());
			    if ((minDiff == -1) || (diff < minDiff)) {
			      minDiff = diff;
			      minDate = personScreeningDetails.getScreeningStartDate();
			    }
				}
			}
			
			
			for(PersonScreeningDetails personScreeningDetails:personScreeningDetailsList){
				if((personScreeningDetails.getStatus().equals("Screening complete")) && (personScreeningDetails.getScreeningStartDate().equals(minDate))){
					onboardingCheckListBean.setStatus(personScreeningDetails.getStatus().getResultName());
				}
			}
			
		}
		return list;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapient.statestreetscreeningapplication.model.service.
	 * OnboardingResourceService
	 * #editOnboardingChecklistLead(com.sapient.statestreetscreeningapplication
	 * .ui.bean.OnboardingCheckListLeadsBean)
	 */
	@Override
	public void editOnboardingChecklistLead(OnboardingCheckListBean onboardingLeadBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside editOnboardingChecklistLead(OnboardingCheckListBean onboardingLeadBean) method of OnboardingResourceServiceImpl class");
		
		List<PersonScreeningDetails> personScreeningDetailsList=intervieweeService.fetchMultiplePersonScreeningDetailsByPersonID(onboardingLeadBean.getPersonStaffingBean().getPerson().getPersonId());

		Date currentDate=new Date();
		
		long minDiff = -1, currentTime = currentDate.getTime();
		 Date minDate = currentDate;
	
		for(PersonScreeningDetails personScreeningDetails:personScreeningDetailsList){
			if(personScreeningDetails.getStatus().equals("Screening complete")){
			long diff = Math.abs(currentTime - personScreeningDetails.getScreeningStartDate().getTime());
		    if ((minDiff == -1) || (diff < minDiff)) {
		      minDiff = diff;
		      minDate = personScreeningDetails.getScreeningStartDate();
		    }
			}
		}
		
		
		/*for(PersonScreeningDetails personScreeningDetails:personScreeningDetailsList){
			if((personScreeningDetails.getStatus().equals("Screening complete")) && (personScreeningDetails.getScreeningStartDate().equals(minDate))){
				onboardingCheckListBean.setStatus(personScreeningDetails.getStatus().getResultName());
			}
		}*/
		
		
		PersonScreeningDetails personScreeningDetails=null;
		for(PersonScreeningDetails pSDetails:personScreeningDetailsList){
			if((pSDetails.getStatus().equals("Screening complete")) && (pSDetails.getScreeningStartDate().equals(minDiff))){
				
				pSDetails.setStatus(statusDao
						.getStatusBeanOnResultName("Staffing approved"));
				
				personScreeningDetails=pSDetails;
			}
		}

		/*PersonScreeningDetails personScreeningDetails = intervieweeDao
				.fetchPersonScreeningDetailsByOracleID(onboardingLeadBean.getPersonStaffingBean().getPerson().getPersonId());
		personScreeningDetails.setStatus(statusDao
				.getStatusBeanOnResultName("Staffing approved"));*/
		/*PersonStaffing personstaffing=*/
		/*PersonScreeningDetails psd=intervieweeService.updatePersonScreeningDetails(personScreeningDetails);
		onboardingLeadBean.setPersonScreeningDetails(PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(psd));*/
		
		onboardingLeadBean.setPersonScreeningDetails(PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(personScreeningDetails));
		onboardingResourceDao.editOnboardingChecklistLead(onboardingLeadBean);

	}
	public void editOnboardingChecklistLead2(OnboardingCheckListBean onboardingLeadBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside editOnboardingChecklistLead(OnboardingCheckListBean onboardingLeadBean) method of OnboardingResourceServiceImpl class");
		
		PersonScreeningDetails personScreeningDetails=intervieweeService.fetchPersonScreeningDetailsByOnboardID(onboardingLeadBean.getOnboardingCheckListId());

		/*Date currentDate=new Date();
		
		long minDiff = -1, currentTime = currentDate.getTime();
		 Date minDate = currentDate;
	
		for(PersonScreeningDetails personScreeningDetails:personScreeningDetailsList){
			if(personScreeningDetails.getStatus().equals("Onboarding in progress")){
			long diff =currentTime - personScreeningDetails.getScreeningStartDate().getTime();
		    if ((minDiff == -1) || (diff < minDiff)) {
		      minDiff = diff;
		      minDate = personScreeningDetails.getScreeningStartDate();
		    }
			}
		}*/
		
		
		/*for(PersonScreeningDetails personScreeningDetails:personScreeningDetailsList){
			if((personScreeningDetails.getStatus().equals("Screening complete")) && (personScreeningDetails.getScreeningStartDate().equals(minDate))){
				onboardingCheckListBean.setStatus(personScreeningDetails.getStatus().getResultName());
			}
		}*/
		
		/*
		PersonScreeningDetails personScreeningDetails=null;
		for(PersonScreeningDetails pSDetails:personScreeningDetailsList){
			if((pSDetails.getStatus().equals("Onboarding in progress")) && (pSDetails.getScreeningStartDate().equals(minDiff))){
				
				pSDetails.setStatus(statusDao
						.getStatusBeanOnResultName("Forms requested"));
				
				personScreeningDetails=pSDetails;
			}
		}*/

		/*PersonScreeningDetails personScreeningDetails = intervieweeDao
				.fetchPersonScreeningDetailsByOracleID(onboardingLeadBean.getPersonStaffingBean().getPerson().getPersonId());
		personScreeningDetails.setStatus(statusDao
				.getStatusBeanOnResultName("Staffing approved"));*/
		/*PersonStaffing personstaffing=*/
		/*PersonScreeningDetails psd=intervieweeService.updatePersonScreeningDetails(personScreeningDetails);
		onboardingLeadBean.setPersonScreeningDetails(PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(psd));*/
		
		onboardingLeadBean.setPersonScreeningDetails(PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(personScreeningDetails));
		onboardingLeadBean.setPerson(onboardingLeadBean.getPersonScreeningDetails().getPersonBean());
		onboardingResourceDao.editOnboardingChecklistLead(onboardingLeadBean);

	}
	public void editOnboardingChecklistLead22(OnboardingCheckListBean onboardingLeadBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside editOnboardingChecklistLead(OnboardingCheckListBean onboardingLeadBean) method of OnboardingResourceServiceImpl class");
		
		PersonScreeningDetails personScreeningDetails=intervieweeService.fetchPersonScreeningDetailsByOnboardID(onboardingLeadBean.getOnboardingCheckListId());

		/*Date currentDate=new Date();
		
		long minDiff = -1, currentTime = currentDate.getTime();
		 Date minDate = currentDate;
	
		for(PersonScreeningDetails personScreeningDetails:personScreeningDetailsList){
			if(personScreeningDetails.getStatus().equals("Onboarding in progress")){
			long diff =currentTime - personScreeningDetails.getScreeningStartDate().getTime();
		    if ((minDiff == -1) || (diff < minDiff)) {
		      minDiff = diff;
		      minDate = personScreeningDetails.getScreeningStartDate();
		    }
			}
		}*/
		
		
		/*for(PersonScreeningDetails personScreeningDetails:personScreeningDetailsList){
			if((personScreeningDetails.getStatus().equals("Screening complete")) && (personScreeningDetails.getScreeningStartDate().equals(minDate))){
				onboardingCheckListBean.setStatus(personScreeningDetails.getStatus().getResultName());
			}
		}*/
		
		/*
		PersonScreeningDetails personScreeningDetails=null;
		for(PersonScreeningDetails pSDetails:personScreeningDetailsList){
			if((pSDetails.getStatus().equals("Onboarding in progress")) && (pSDetails.getScreeningStartDate().equals(minDiff))){
				
				pSDetails.setStatus(statusDao
						.getStatusBeanOnResultName("Forms requested"));
				
				personScreeningDetails=pSDetails;
			}
		}*/

		/*PersonScreeningDetails personScreeningDetails = intervieweeDao
				.fetchPersonScreeningDetailsByOracleID(onboardingLeadBean.getPersonStaffingBean().getPerson().getPersonId());
		personScreeningDetails.setStatus(statusDao
				.getStatusBeanOnResultName("Staffing approved"));*/
		/*PersonStaffing personstaffing=*/
		/*PersonScreeningDetails psd=intervieweeService.updatePersonScreeningDetails(personScreeningDetails);
		onboardingLeadBean.setPersonScreeningDetails(PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(psd));*/
		StatusChangeLogEntity sclEntity = new StatusChangeLogEntity();
		sclEntity.setCandidatePSD(personScreeningDetails);
		sclEntity.setStatusChangedFrom(personScreeningDetails.getStatus());
		sclEntity.setStatusChangedTo(StatusConverter.convertStatusBeanToStatusEntity(statusService.getStatusBeanOnResultName(onboardingLeadBean.getOnboardingStatus())));
		if(sclEntity.getStatusChangedFrom().getStatusId()!=sclEntity.getStatusChangedTo().getStatusId()){
		Date statusChangeDate = new Date();
		sclEntity.setStatusChangeDate(statusChangeDate);
		PersonNewBean operator= new PersonNewBean();
		operator.setPersonName("Suraj");
		operator.setPersonOracleId(115446);
		
		//PersonNewBean operator=(PersonNewBean) ServletActionContext.getRequest().getSession().getAttribute("user");
		sclEntity.setOperator(PersonNewConverter.personBeanToEntity(operator));
		onboardingResourceDao.updateStatusChangeLog(sclEntity);
		}
		onboardingLeadBean.setPersonScreeningDetails(PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(personScreeningDetails));
		onboardingLeadBean.setPerson(onboardingLeadBean.getPersonScreeningDetails().getPersonBean());
		onboardingResourceDao.editOnboardingChecklistLead2(onboardingLeadBean);

	}
	@Override
	public List<StatusChangeLogBean> viewStatusChangeLogList(){
		List<StatusChangeLogEntity> statusChangeLogEntityList = onboardingResourceDao.viewStatusChangeLogList();
		List<StatusChangeLogBean> statusChangeLogBeanList = StatusChangeLogConverter.StatusChangeLogEntityToBeanList(statusChangeLogEntityList);
		for(StatusChangeLogBean sclBean : statusChangeLogBeanList){
			PersonNewBean candidateBean = sclBean.getCandidatePSD().getPersonBean();
			       if(candidateBean.getIsTemp()){
			    	   sclBean.getCandidatePSD().getPersonBean().setPersonName(candidateBean.getTempPersonBean().getTempPersonName());
			       }
	          else if(!candidateBean.getIsTemp()){
	        	       sclBean.getCandidatePSD().getPersonBean().setPersonName(personLookupServiceImplementation.getPersonByOracleId(candidateBean.getPersonOracleId()).getName());
			       }
			       
			           sclBean.getOperator().setPersonName(personLookupServiceImplementation.getPersonByOracleId(sclBean.getOperator().getPersonOracleId()).getName());
		}
		return statusChangeLogBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapient.statestreetscreeningapplication.model.service.
	 * OnboardingResourceService#sendBGCdocumentsToCandidate(int, int, int,
	 * com.sapient
	 * .statestreetscreeningapplication.ui.bean.OnboardingResourceBean)
	 */
	@Override
	public void sendBGCdocumentsToCandidate(int selectedBGC, int initiator,PersonStaffingBean personStaffingBean,OnboardingCheckListBean onboardingCheckListBean,PersonNewBean currentUser) {
		CustomLoggerUtils.INSTANCE.log.info("inside sendBGCdocumentsToCandidate(int selectedBGC, int initiator,PersonStaffingBean personStaffingBean,OnboardingCheckListBean onboardingCheckListBean)  method of OnboardingResourceServiceImpl class");
		if (sendEmailService.sendEmailToCandidate(selectedBGC, initiator,
				personStaffingBean, onboardingCheckListBean,currentUser)) {
			// intervieweeService.changeStatus(intervieweeService.fetchIntervieweeOnOracleId(intervieweeOracleID).getIntervieweeId(),
			// "Onboarding in progress", "Forms requested");
		}

	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapient.statestreetscreeningapplication.model.service.
	 * OnboardingResourceService#sendPSIDToCandidate(int)
	 */
	@Override
	public void sendPSIDToCandidate(int onboardID) {
		CustomLoggerUtils.INSTANCE.log.info("inside sendPSIDToCandidate(int onboardID)  method of OnboardingResourceServiceImpl class");
		
	}
	
	@Override
	public void sendPSIDAndNameToCandidate(int onboardID,String personClientId, String personName, PersonNewBean currentUser) {
		CustomLoggerUtils.INSTANCE.log.info("inside sendPSIDAndNameToCandidate(int onboardID,String personClientId, String personName)  method of OnboardingResourceServiceImpl class");	
		sendEmailService.sendPSIDEmailToCandidate(onboardID,personClientId,personName,currentUser);
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapient.statestreetscreeningapplication.model.service.
	 * OnboardingResourceService#terminateOnboarding(int, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void terminateOnboarding(long leadOnboardID, String projectEndDate,String offboardingComments,PersonNewBean currentUser) {
		CustomLoggerUtils.INSTANCE.log.info("inside terminateOnboarding(long leadOnboardID, String projectEndDate,String offboardingComments) method of OnboardingResourceServiceImpl class");
		sendEmailService.sendOnboardingTerminationEmail(leadOnboardID,
				projectEndDate, offboardingComments,currentUser);
	}
	
	public OnboardingCheckList updateOnboardingCheckList(OnboardingCheckList onboardingCheckList) {
		
		CustomLoggerUtils.INSTANCE.log.info("inside updateOnboardingCheckList(OnboardingCheckList onboardingCheckList) method of IntervieweeServiceImpl class");
		
		return onboardingResourceDao.updateExistingOnboardingCheckList(onboardingCheckList);
		
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapient.statestreetscreeningapplication.model.service.
	 * OnboardingResourceService#excelImport(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	/*public boolean excelImport(String activityType, String accountDivision,String projectName) {*/
		public boolean excelImport(String activityType, String teamName,String projectName) {
		
		CustomLoggerUtils.INSTANCE.log.info("inside excelImport(String activityType, String teamName,String projectName) method of OnboardingResourceServiceImpl class");
		/*List<OnboardingCheckListLeadsBean> list = new ArrayList<OnboardingCheckListLeadsBean>();*/
		/*list = getOnboardingChecklistLeads();*/
		List<OnboardingCheckListBean> list= new ArrayList<OnboardingCheckListBean>();
		list=getOnboardingChecklistNew();
		
		
		List<OnboardingCheckListBean> list1 = new ArrayList<OnboardingCheckListBean>();
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date currentDate = new Date();
		String endDate=dateFormat.format(currentDate);
		
		/*for (OnboardingCheckListLeadsBean a : list)*/ 
		for (OnboardingCheckListBean a : list){
		
			
			/*	PeopleInfoBean peopleInfoBean = new PeopleInfoBean();*/
			
			/*OnboardingCheckListLeadsBean checkListLeadsBean = fetchOnBoardingChecklistLead(a
					.getOnboardingLeadsCheckListId());
			
			
			OnboardingCheckListOpsBean opsBean = fetchOnBoardingChecklist(checkListLeadsBean
					.getOnboardingCheckListOps().getOnboardingCheckListId());*/
			
			/*if (!opsBean.getInterviewee().isExited()) {*/
			
			try {
				if( !a.getPersonStaffingBean().getPerson().getHasExited()) {
					
					
					
					if((a.getPersonStaffingBean().getEndDate()!=null)){
						endDate=a.getPersonStaffingBean().getEndDate();
					}
					/*IntervieweeBean intervieweeBean = null;
					intervieweeService.fetchInterviewee(opsBean.getInterviewee()
							.getIntervieweeId());
					OnboardingResourceBean resourceBean = fetchResourceOfInterviewee(intervieweeBean
							.getIntervieweeId());
					PersonBean candidate = personLookService
							.getPersonByOracleId(checkListLeadsBean
									.getOnboardingCheckListOps().getInterviewee()
									.getIntervieweeOracleID());*/

					if (a.getPersonStaffingBean().getPerson().getPersonName() != null || a.getPersonStaffingBean().getPerson().getIsTemp()) {/*
						resourceBean.getInterviewee().setIntervieweeName(
								candidate.getName());
						resourceBean.getInterviewee().setIntervieweeEmailID(
								candidate.getEmail());
						resourceBean.getInterviewee().setIntervieweeOracleID(
								candidate.getOracleId());
						if (resourceBean.getSupervisorNtId() != null) {
							resourceBean.setSupervisor(personLookService
									.getPersonByNTId(
											resourceBean.getSupervisorNtId())
									.getName());
						}*/
						/*peopleInfoBean
								.setOnboardingCheckListLeadsBean(checkListLeadsBean);
						peopleInfoBean.setOnboardingCheckListOpsBean(opsBean);
						peopleInfoBean.setOnboardingResourceBean(resourceBean);
*/
						if (!teamName.equals("NA")) {
							if (!projectName.equals("NA")) {
								if (a.getPersonStaffingBean().getProject().getTeamBean().getTeamName().equals(teamName)
										&& a.getPersonStaffingBean().getProject().getProjectName().equals(projectName)
										) {
									if (activityType.equals("all"))
										list1.add(a);
									else if (activityType.equals("active")) {
										try {
											if ((dateFormat.parse(endDate).compareTo(currentDate)>0) || (dateFormat.parse(endDate).equals(currentDate)) || (a.getPersonStaffingBean().getEndDate()==null))
												list1.add(a);
										} catch (ParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									if (activityType.equals("inactive")) {
										try {
											if ((dateFormat.parse(endDate).compareTo(currentDate)<0) && (a.getPersonStaffingBean().getEndDate()!=null))
												list1.add(a);
										} catch (ParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
							} else {
								if (a.getPersonStaffingBean().getProject().getTeamBean().getTeamName().equals(teamName)) {
									if (activityType.equals("all"))
										list1.add(a);
									else if (activityType.equals("active")) {
										try {
											if ((dateFormat.parse(endDate).compareTo(currentDate)>0) || (dateFormat.parse(endDate).equals(currentDate))|| (a.getPersonStaffingBean().getEndDate()==null))
												list1.add(a);
										} catch (ParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									if (activityType.equals("inactive")) {
										try {
											if ((dateFormat.parse(endDate).compareTo(currentDate)<0)  && (a.getPersonStaffingBean().getEndDate()!=null))
												list1.add(a);
										} catch (ParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
							}
						} else {
							if (activityType.equals("all"))
								list1.add(a);
							else if (activityType.equals("active")) {
								if ((dateFormat.parse(endDate).compareTo(currentDate)>0) || (dateFormat.parse(endDate).equals(currentDate))|| (a.getPersonStaffingBean().getEndDate()==null))
									list1.add(a);
							}
							if (activityType.equals("inactive")) {
								if ((dateFormat.parse(endDate).compareTo(currentDate)<0)  && (a.getPersonStaffingBean().getEndDate()!=null))
									list1.add(a);
							}
						}
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (list1 != null && ExcelFileCreator.peopleInformation(list1) != null)
			return true;
		else
			return false;
	}

	@Override
	public List<PersonStaffingBean> fetchPersonStaffing() {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchPersonStaffing() method of OnboardingResourceServiceImpl class");
		
		List<PersonStaffingBean> personStaffingBeanList = PersonStaffingConverter
				.personStaffingEntityListToBeanList(onboardingResourceDao
						.fetchPersonStaffingDetails());
		
		List<PersonStaffingBean> personList = new ArrayList<PersonStaffingBean>();
		
		for (PersonStaffingBean personStaffingBean : personStaffingBeanList) {
			String dlName = "";
			
			if (!personStaffingBean.getPerson().getHasExited()) {
				PersonBean personBean = personLookService
						.getPersonByNTId(personStaffingBean.getPerson()
								.getPersonNtId());
				PersonBean supervisor = personLookService
						.getPersonByNTId(personStaffingBean.getPerson()
								.getSupervisorNtId());
				if (personBean != null || personStaffingBean.getPerson().getIsTemp()) {
					
					if(!personStaffingBean.getPerson().getIsTemp()){
						personStaffingBean.setPersonName(personBean.getName());
					personStaffingBean.setPersonEmail(personBean.getEmail());
					}else{
						personStaffingBean.setPersonName(personStaffingBean.getPerson().getTempPersonBean().getTempPersonName());
						personStaffingBean.setPersonEmail(personStaffingBean.getPerson().getTempPersonBean().getTempPersonEmail());
					}
					
					if (supervisor != null)
						personStaffingBean.setSupervisorName(supervisor
								.getName());
				
					
					if (!personStaffingBean.getEmailDls().isEmpty()) {
						
						for (EmailDlBean dl : personStaffingBean.getEmailDls()) {
							if (!dlName.equals("")) {
								dlName = dlName + "," + dl.getEmail();
							} else {
								dlName = dlName + dl.getEmail();
							}
						}
						// b.setDlNames(a.getDlName());}
						personStaffingBean.setDlNames(dlName);
					}

					personList.add(personStaffingBean);
				} else {
					personService
							.markPersonAsExited(PersonNewConverter
									.personBeanToEntity(personStaffingBean
											.getPerson()));
				}
			}
			
			
			
		}
		return personList;
	}

	@Override
	public void editPersonStaffing(PersonStaffingBean personStaffingBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside editPersonStaffing(PersonStaffingBean personStaffingBean) method of OnboardingResourceServiceImpl class");

		onboardingResourceDao.editPersonStaffing(personStaffingBean,
				locationDao.getNewLocationByName(personStaffingBean
						.getLocation().getCity()));

	}

	@Override
	public PersonStaffingBean fetchpersonStaffingByOracleId(int personId) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchpersonStaffingByOracleId(int personId) method of OnboardingResourceServiceImpl class");

		return PersonStaffingConverter
				.personStaffingEntityToBean(onboardingResourceDao
						.fetchPersonStaffingByOracleID(personId));
	}
	public PersonStaffingBean fetchpersonStaffingByPersonId(int personId){
		CustomLoggerUtils.INSTANCE.log.info("inside fetchpersonStaffingByPersonId(int personId) method of OnboardingResourceServiceImpl class");

		return PersonStaffingConverter
				.personStaffingEntityToBean(onboardingResourceDao
						.fetchPersonStaffingByPersonID(personId));
		
	}
	@Override
	public PersonStaffing fetchPersonStaffingByPersonID(int personId) {
		
		return onboardingResourceDao
				.fetchPersonStaffingByPersonID(personId);
	}
	
	@Override
	public Person fetchPersonByPersonID(int personId) {
		
		return personDaoNewImpl.getPersonByPersonId(personId);
				
	}
	
	@Override
	public PersonStaffingBean fetchPersonStaffingByPersonStaffingId(Long staffingId) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchPersonStaffingByPersonStaffingId(Long staffingId) method of OnboardingResourceServiceImpl class");
		return PersonStaffingConverter.personStaffingEntityToBean(onboardingResourceDao.fetchPersonStaffingByPersonStaffingId(staffingId));
	}
	
	

	@Override
	public OnboardingCheckListBean fetchNewOnboardingCheckList(long leadOnboardID) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchNewOnboardingCheckList(long leadOnboardID)  method of OnboardingResourceServiceImpl class for onboard id"+leadOnboardID);
		OnboardingCheckList onboardingCheckList=onboardingResourceDao.fetchNewOnbaordingCheckList(leadOnboardID);
		return OnboardingCheckListConverter
				.convertOnboardingCheckListEntityToOnboardingCheckListBean(onboardingCheckList);
	}

	@Override
	public List<OnboardingResourceBean> fetchResourcesOnboarded() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateOnboardingResourceInfo(
			OnboardingResourceBean onboardingResourceBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updatePersonClientId(PersonStaffingBean personStaffingBean) {
		onboardingResourceDao.updatePersonClientId(personStaffingBean);
		return true;
	}

	@Override
	public OnboardingResourceBean findResource(
			long onboardingResourceIdToBeUpdated) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OnboardingCheckListOpsBean> getOnboardingChecklistOps() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editOnboardingChecklist(
			OnboardingCheckListOpsBean onboardingBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OnboardingCheckListOpsBean fetchOnBoardingChecklist(long checklistId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OnboardingCheckListLeadsBean> getOnboardingChecklistLeads() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OnboardingCheckListLeadsBean fetchOnBoardingChecklistLead(
			long onboardingLeadsCheckListId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OnboardingResourceBean fetchResourceOfInterviewee(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OnboardingCheckListOpsBean> getOnboardingCandidatesBGCInprogress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OnboardingCheckListOpsBean> getOnboardingCandidatesBGCToBeSubmitted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OnboardingResourceBean> getcandidatesPSIDSent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OnboardingResourceBean> fetchAllResources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OnboardingCheckListOpsBean fetchOpsChecklist(long intervieweeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reboardInterviewee(int oracleID,
			OnboardingResourceBean onboardingResourceBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOnboardingResourceActivity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OnboardingResourceBean fetchOnboardingResourceByIntervieweeId(
			long intervieweeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OnboardingCheckList getOnboardingCheckListByStaffingId(Long personStaffingId) {
		CustomLoggerUtils.INSTANCE.log.info("inside getOnboardingCheckListByStaffingId(Long personStaffingId)  method of OnboardingResourceServiceImpl class");
		return onboardingResourceDao.getOnboardingCheckListByStaffingId(personStaffingId);
	}

	@Override
	public OnboardingCheckList mergeOnboardingCheckList(OnboardingCheckList onboardingCheckList) {
		CustomLoggerUtils.INSTANCE.log.info("inside mergeOnboardingCheckList(OnboardingCheckList onboardingCheckList)  method of OnboardingResourceServiceImpl class");
		return onboardingResourceDao.mergeOnboardingCheckList(onboardingCheckList);
		
	}

	@Override
	public boolean removeCurrentStaffing(Long personStaffingId) {
		CustomLoggerUtils.INSTANCE.log.info("inside removeCurrentStaffing(Long personStaffingId)  method of OnboardingResourceServiceImpl class");
		return onboardingResourceDao.removeCurrentStaffing(personStaffingId);
	}

	

	
}
