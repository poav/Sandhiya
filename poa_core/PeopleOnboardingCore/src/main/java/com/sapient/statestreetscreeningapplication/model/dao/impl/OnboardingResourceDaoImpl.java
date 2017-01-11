package com.sapient.statestreetscreeningapplication.model.dao.impl ;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.OnboardingResourceDao;
import com.sapient.statestreetscreeningapplication.model.entity.LocationNew;
import com.sapient.statestreetscreeningapplication.model.entity.OnboardingCheckList;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.entity.ProjectNew;
import com.sapient.statestreetscreeningapplication.model.entity.Status;
import com.sapient.statestreetscreeningapplication.model.entity.StatusChangeLogEntity;
import com.sapient.statestreetscreeningapplication.model.service.StatusService;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonStaffingBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.StatusConverter;
import com.sapient.statestreetscreeningapplication.utils.enums.PSIDStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class OnboardingResourceDaoImpl.
 */
@Component
public class OnboardingResourceDaoImpl implements OnboardingResourceDao {
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	StatusService statusService;

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
	
	

	@Override
	@Transactional
	public List<OnboardingCheckList> getOnboardingCheckListNew() {
		CustomLoggerUtils.INSTANCE.log.info("inside getOnboardingCheckListNew() method of OnboardingResourceDaoImpl class");
		List<OnboardingCheckList> leadResourcesOnboarding = new ArrayList<OnboardingCheckList>();
		String query = "from OnboardingCheckList";
		TypedQuery<OnboardingCheckList> o = entityManager.createQuery(query,
				OnboardingCheckList.class);
		try {

			leadResourcesOnboarding = o.getResultList();
			CustomLoggerUtils.INSTANCE.log
					.info("The size of the list obtained is"
							+ leadResourcesOnboarding.size());
		} catch (NoResultException e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.info("Onboarding CheckList ops is null");
			return null;
		}

		return leadResourcesOnboarding;
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.OnboardingResourceDao#editOnboardingChecklistLead(com.sapient.statestreetscreeningapplication.model.entity.OnboardingCheckListLeads)
	 */
	@Override
	@Transactional
	public void editOnboardingChecklistLead(OnboardingCheckListBean checklist) {
		CustomLoggerUtils.INSTANCE.log.info("inside editOnboardingChecklistLead(OnboardingCheckListBean checklist) method of OnboardingResourceDaoImpl class");
		PersonScreeningDetails personScreeningDatails=entityManager.find(PersonScreeningDetails.class, checklist.getPersonScreeningDetails().getPersonScreeningId());
		Person person=entityManager.find(Person.class, personScreeningDatails.getPerson().getPersonId());
		personScreeningDatails.setPerson(person);
		OnboardingCheckList checklistToBeEdited=entityManager.find(OnboardingCheckList.class, checklist.getOnboardingCheckListId());
		SimpleDateFormat formatter ;  
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		checklistToBeEdited.setPersonScreeningDetails(personScreeningDatails);
		/*if(checklist.getHasPreviouslyWorkedForClient()!=null)
		{ if(checklist.getHasPreviouslyWorkedForClient().equalsIgnoreCase("yes"))
			checklistToBeEdited.setHasPreviouslyWorkedForClient(true);
		else
			checklistToBeEdited.setHasPreviouslyWorkedForClient(false);
		}*/


        /*if(checklist.getHasSapientLaptop()!=null)
		{ if(checklist.getHasSapientLaptop().equalsIgnoreCase("yes"))
			checklistToBeEdited.setHasSapientLaptop(true);
		else
			checklistToBeEdited.setHasSapientLaptop(false);
		}
		*/
		PersonStaffing personStaffing=entityManager.find(PersonStaffing.class,checklistToBeEdited.getPersonStaffing().getPersonStaffingId());
		/*checklist.getPersonStaffingBean().getPerson().setPersonClientId(checklist.getPersonScreeningDetails().getPersonBean().getPersonClientId());*/
		personStaffing.getPerson().setPersonClientId(checklist.getPersonStaffingBean().getPerson().getPersonClientId());
		checklistToBeEdited.setPersonStaffing(personStaffing);
		
		if(checklist.getAddedInDl()!=null)
		{if(checklist.getAddedInDl().equalsIgnoreCase("true"))
			checklistToBeEdited.setAddedInDl(true);
		else
			checklistToBeEdited.setAddedInDl(false);
		}
		if(checklist.getAddedInRS3()!=null)
		{if(checklist.getAddedInRS3().equalsIgnoreCase("true"))
			checklistToBeEdited.setAddedInRS3(true);
		else
			checklistToBeEdited.setAddedInRS3(false);
			
		}
		
		if(checklist.getIsOnsite()!=null)
		{ if(checklist.getIsOnsite().equalsIgnoreCase("true"))
			checklistToBeEdited.setIsOnsite(true);
		else
			checklistToBeEdited.setIsOnsite(false);
		}
		
		if(checklist.isHasPreviouslyWorkedForClient()){
			checklistToBeEdited.setHasPreviouslyWorkedForClient(true);
		}else{
			checklistToBeEdited.setHasPreviouslyWorkedForClient(false);
		}
		
		if(checklist.isHasSapientLaptop()){
			checklistToBeEdited.setHasSapientLaptop(true);
		}else{
			checklistToBeEdited.setHasSapientLaptop(false);
		}
		
		if(checklist.getOnboardingStatus()!=null){
			checklistToBeEdited.setOnboardingStatus(checklist.getOnboardingStatus());
			
			/*String statusName=checklist.getPersonScreeningDetails().getStatusBean().getStatusName();*/
			String resultName=checklist.getOnboardingStatus();
			
			/*if(checklist.getOnboardingStatus().equals("Onboarded")){
				statusName="Onboarding complete";
			}*/
			checklistToBeEdited.getPersonScreeningDetails().setStatus(StatusConverter.convertStatusBeanToStatusEntity(statusService.getStatusBeanOnResultName(resultName)));
		}
		if(checklist.getBackgroundCheckStatus()!=null)
		checklistToBeEdited.setBackgroundCheckStatus(checklist.getBackgroundCheckStatus());
		
		
		if(checklist.getBackgroundCheckStatus()!=null)
		{
			checklistToBeEdited.setBackgroundCheckStatus(checklist.getBackgroundCheckStatus());
		}
		
		   if(checklist.getBuildingAccessRequestDate()!=null)
			try {
				checklistToBeEdited.setBuildingAccessRequestDate(formatter.parse(checklist.getBuildingAccessRequestDate()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   try {
			   if(checklist.getDateBackgroundCheckDone()!=null)
				   checklistToBeEdited.setDateBackgroundCheckDone(formatter.parse(checklist.getDateBackgroundCheckDone()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   try {
			   if(checklist.getDateBackgroundCheckSubmitted()!=null)
				   checklistToBeEdited.setDateBackgroundCheckSubmitted(formatter.parse(checklist.getDateBackgroundCheckSubmitted()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		   try {
			   if(checklist.getDatePersonAddedInClientVendorManagementSystem()!=null)
				   checklistToBeEdited.setDatePersonAddedInClientVendorManagementSystem(formatter.parse(checklist.getDatePersonAddedInClientVendorManagementSystem()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   try {
			   if(checklist.getDatePersonApprovedInClientVendorManagementSystem()!=null)
				   checklistToBeEdited.setDatePersonApprovedInClientVendorManagementSystem(formatter.parse(checklist.getDatePersonApprovedInClientVendorManagementSystem()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   try {
			   if(checklist.getFingerPrintDate()!=null)
				   checklistToBeEdited.setFingerPrintDate(formatter.parse(checklist.getFingerPrintDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   try {
			   if(checklist.getDateOnboardingInitiatedDate()!=null)
			   {
				   checklistToBeEdited.setDateOnboardingInitiated(checklist.getDateOnboardingInitiatedDate());
			   }
			   else
			   {
				   if(checklist.getDateOnboardingInitiated()!=null)
					   checklistToBeEdited.setDateOnboardingInitiated(formatter.parse(checklist.getDateOnboardingInitiated()));
			   }
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		 
			   if(checklist.getMachineRequestId()!=0 && checklistToBeEdited.getMachineRequestId()==0)
			   {CustomLoggerUtils.INSTANCE.log.info("Machine request date ");
				   checklistToBeEdited.setMachineRequestDate(new Date());
			   }
			   
			   
		   try {
			   if(checklist.getOfficeAccessRequestDate()!=null)
				   checklistToBeEdited.setOfficeAccessRequestDate(formatter.parse(checklist.getOfficeAccessRequestDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		  try {
			   if(checklist.getRampupInitiatedDate()!=null)
				   checklistToBeEdited.setRampupInitiatedDate(formatter.parse(checklist.getRampupInitiatedDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			   if(checklist.getTimeOffToolRequestDate()!=null)
				   checklistToBeEdited.setTimeOffToolRequestDate(formatter.parse(checklist.getTimeOffToolRequestDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if(checklist.getUserAccessRequestId()!=0 && checklistToBeEdited.getUserAccessRequestId()==0)
				   checklistToBeEdited.setUserAccessRequestDate(new Date());
		 if(checklist.getMachineRequestId()!=0)
				checklistToBeEdited.setMachineRequestId(checklist.getMachineRequestId());
				if(checklist.getUserAccessRequestId()!=0)
				checklistToBeEdited.setUserAccessRequestId(checklist.getUserAccessRequestId());
		entityManager.merge(checklistToBeEdited);
	}
	@Transactional
	public void editOnboardingChecklistLead2(OnboardingCheckListBean checklist) {
		CustomLoggerUtils.INSTANCE.log.info("inside editOnboardingChecklistLead(OnboardingCheckListBean checklist) method of OnboardingResourceDaoImpl class");
		PersonScreeningDetails personScreeningDatails=entityManager.find(PersonScreeningDetails.class, checklist.getPersonScreeningDetails().getPersonScreeningId());
		Person person=entityManager.find(Person.class, personScreeningDatails.getPerson().getPersonId());
		personScreeningDatails.setPerson(person);
		OnboardingCheckList checklistToBeEdited=entityManager.find(OnboardingCheckList.class, checklist.getOnboardingCheckListId());
		SimpleDateFormat formatter ;  
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		checklistToBeEdited.setPersonScreeningDetails(personScreeningDatails);
		/*if(checklist.getHasPreviouslyWorkedForClient()!=null)
		{ if(checklist.getHasPreviouslyWorkedForClient().equalsIgnoreCase("yes"))
			checklistToBeEdited.setHasPreviouslyWorkedForClient(true);
		else
			checklistToBeEdited.setHasPreviouslyWorkedForClient(false);
		}*/


        /*if(checklist.getHasSapientLaptop()!=null)
		{ if(checklist.getHasSapientLaptop().equalsIgnoreCase("yes"))
			checklistToBeEdited.setHasSapientLaptop(true);
		else
			checklistToBeEdited.setHasSapientLaptop(false);
		}
		*/
		PersonStaffing personStaffing=entityManager.find(PersonStaffing.class,checklistToBeEdited.getPersonStaffing().getPersonStaffingId());
		/*checklist.getPersonStaffingBean().getPerson().setPersonClientId(checklist.getPersonScreeningDetails().getPersonBean().getPersonClientId());*/
		/*personStaffing.getPerson().setPersonClientId(checklist.getPersonStaffingBean().getPerson().getPersonClientId());*/
		checklistToBeEdited.setPersonStaffing(personStaffing);
		
		if(checklist.getAddedInDl()!=null)
		{if(checklist.getAddedInDl().equalsIgnoreCase("true"))
			checklistToBeEdited.setAddedInDl(true);
		else
			checklistToBeEdited.setAddedInDl(false);
		}
		if(checklist.getAddedInRS3()!=null)
		{if(checklist.getAddedInRS3().equalsIgnoreCase("true"))
			checklistToBeEdited.setAddedInRS3(true);
		else
			checklistToBeEdited.setAddedInRS3(false);
			
		}
		
		if(checklist.getIsOnsite()!=null)
		{ if(checklist.getIsOnsite().equalsIgnoreCase("true"))
			checklistToBeEdited.setIsOnsite(true);
		else
			checklistToBeEdited.setIsOnsite(false);
		}
		
		if(checklist.isHasPreviouslyWorkedForClient()){
			checklistToBeEdited.setHasPreviouslyWorkedForClient(true);
		}else{
			checklistToBeEdited.setHasPreviouslyWorkedForClient(false);
		}
		
		if(checklist.isHasSapientLaptop()){
			checklistToBeEdited.setHasSapientLaptop(true);
		}else{
			checklistToBeEdited.setHasSapientLaptop(false);
		}
		
		if(checklist.getOnboardingStatus()!=null){
			checklistToBeEdited.setOnboardingStatus(checklist.getOnboardingStatus());
			
			/*String statusName=checklist.getPersonScreeningDetails().getStatusBean().getStatusName();*/
			String resultName=checklist.getOnboardingStatus();
			
			/*if(checklist.getOnboardingStatus().equals("Onboarded")){
				statusName="Onboarding complete";
			}*/
			checklistToBeEdited.getPersonScreeningDetails().setStatus(StatusConverter.convertStatusBeanToStatusEntity(statusService.getStatusBeanOnResultName(resultName)));
		}
		if(checklist.getBackgroundCheckStatus()!=null)
		checklistToBeEdited.setBackgroundCheckStatus(checklist.getBackgroundCheckStatus());
		
		
		if(checklist.getBackgroundCheckStatus()!=null)
		{
			checklistToBeEdited.setBackgroundCheckStatus(checklist.getBackgroundCheckStatus());
		}
		
		   if(checklist.getBuildingAccessRequestDate()!=null)
			try {
				checklistToBeEdited.setBuildingAccessRequestDate(formatter.parse(checklist.getBuildingAccessRequestDate()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		 
			try {
				if(checklist.getBackgroundCheckDoneDate()!=null)
					checklistToBeEdited.setDateBackgroundCheckDone(checklist.getBackgroundCheckDoneDate());
				else
				if (checklist.getDateBackgroundCheckDone() != null)
					checklistToBeEdited.setDateBackgroundCheckDone(formatter.parse(checklist.getDateBackgroundCheckDone()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				if(checklist.getBackgroundCheckSubmittedDate()!=null)
					checklistToBeEdited.setDateBackgroundCheckSubmitted(checklist.getBackgroundCheckSubmittedDate());
				else
				if (checklist.getDateBackgroundCheckSubmitted() != null)
					checklistToBeEdited.setDateBackgroundCheckSubmitted(formatter.parse(checklist.getDateBackgroundCheckSubmitted()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				if(checklist.getDateOnboardingInitiatedDate()!=null)
					checklistToBeEdited.setDateOnboardingInitiated(checklist.getDateOnboardingInitiatedDate());
				else
				if (checklist.getDateOnboardingInitiated() != null)
					checklistToBeEdited.setDateOnboardingInitiated(formatter.parse(checklist.getDateOnboardingInitiated()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				if (checklist.getPersonAddedInClientVendorManagementSystemDate() != null)
					checklistToBeEdited.setDatePersonAddedInClientVendorManagementSystem(
							checklist.getPersonAddedInClientVendorManagementSystemDate());
				else
				if (checklist.getDatePersonAddedInClientVendorManagementSystem() != null)
					checklistToBeEdited.setDatePersonAddedInClientVendorManagementSystem(
							formatter.parse(checklist.getDatePersonAddedInClientVendorManagementSystem()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				if (checklist.getPersonApprovedInClientVendorManagementSystemDate() != null)
					checklistToBeEdited.setDatePersonApprovedInClientVendorManagementSystem(
							checklist.getPersonApprovedInClientVendorManagementSystemDate());
				else
				if (checklist.getDatePersonApprovedInClientVendorManagementSystem() != null)
					checklistToBeEdited.setDatePersonApprovedInClientVendorManagementSystem(
							formatter.parse(checklist.getDatePersonApprovedInClientVendorManagementSystem()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		   try {
			   if(checklist.getFingerPrintDate()!=null)
				   checklistToBeEdited.setFingerPrintDate(formatter.parse(checklist.getFingerPrintDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   try {
			   if(checklist.getDateOnboardingInitiated()!=null)
				   checklistToBeEdited.setDateOnboardingInitiated(formatter.parse(checklist.getDateOnboardingInitiated()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		 
			   if(checklist.getMachineRequestId()!=0 && checklistToBeEdited.getMachineRequestId()==0)
			   {CustomLoggerUtils.INSTANCE.log.info("Machine request date ");
				   checklistToBeEdited.setMachineRequestDate(new Date());
			   }
			   
			   
		   try {
			   if(checklist.getOfficeAccessRequestDate()!=null)
				   checklistToBeEdited.setOfficeAccessRequestDate(formatter.parse(checklist.getOfficeAccessRequestDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		  try {
			   if(checklist.getRampupInitiatedDate()!=null)
				   checklistToBeEdited.setRampupInitiatedDate(formatter.parse(checklist.getRampupInitiatedDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			   if(checklist.getTimeOffToolRequestDate()!=null)
				   checklistToBeEdited.setTimeOffToolRequestDate(formatter.parse(checklist.getTimeOffToolRequestDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if(checklist.getUserAccessRequestId()!=0 && checklistToBeEdited.getUserAccessRequestId()==0)
				   checklistToBeEdited.setUserAccessRequestDate(new Date());
		 if(checklist.getMachineRequestId()!=0)
				checklistToBeEdited.setMachineRequestId(checklist.getMachineRequestId());
				if(checklist.getUserAccessRequestId()!=0)
				checklistToBeEdited.setUserAccessRequestId(checklist.getUserAccessRequestId());
		entityManager.merge(checklistToBeEdited);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.OnboardingResourceDao#saveOnboardingResourceDetails(com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing)
	 */
	@Override
	@Transactional
	public void saveOnboardingResourceDetails(PersonStaffing personStaffing, OnboardingCheckList onboardingCheckList) {
	    CustomLoggerUtils.INSTANCE.log.info("inside saveOnboardingResourceDetails(PersonStaffing personStaffing, OnboardingCheckList onboardingCheckList)  method of OnboardingResourceDaoImpl class");
     
	    String statusName = onboardingCheckList.getPersonScreeningDetails().getStatus().getStatusName();
        String resultName = onboardingCheckList.getPersonScreeningDetails().getStatus().getResultName();
        String query2 = "from Status where statusName =:statusName and resultName=:resultName";
        TypedQuery<Status> statusTQ = entityManager.createQuery(query2,Status.class);
        statusTQ.setParameter("statusName",statusName);
        statusTQ.setParameter("resultName",resultName);
        Status status = statusTQ.getSingleResult();
        
//        String query = "from PersonScreeningDetails where person =:person and screeningStartDate =:screeningStartDate and screeningEndDate =:screeningEndDate";
//        TypedQuery<PersonScreeningDetails> psdTQ = entityManager.createQuery(query,PersonScreeningDetails.class);
//        psdTQ.setParameter("person",personStaffing.getPerson());
//        Date screeningStartDate = onboardingCheckList.getPersonScreeningDetails().getScreeningStartDate();
//        psdTQ.setParameter("screeningStartDate",screeningStartDate);
//        Date screeningEndDate = onboardingCheckList.getPersonScreeningDetails().getScreeningEndDate();
//        psdTQ.setParameter("screeningEndDate",screeningEndDate);
        
        String query = "from PersonScreeningDetails where personScreeningId =:personScreeningId";
        TypedQuery<PersonScreeningDetails> psdTQ = entityManager.createQuery(query,PersonScreeningDetails.class);
        Long personScreeningId = onboardingCheckList.getPersonScreeningDetails().getPersonScreeningId();
        psdTQ.setParameter("personScreeningId", personScreeningId);
      
        onboardingCheckList.setPersonScreeningDetails(psdTQ.getSingleResult());
	 
	  //  long statusId = 3;
      //  Status status=entityManager.find(Status.class,statusId);
     
        onboardingCheckList.getPersonScreeningDetails().setStatus(status);
        onboardingCheckList.setPersonStaffing(personStaffing);
	    
        entityManager.merge(onboardingCheckList);

	}
	@Transactional
	public OnboardingCheckList updateExistingOnboardingCheckList(
			OnboardingCheckList onboardingCheckList){
		return entityManager.merge(onboardingCheckList);
	}
	
	
	@Override
	@Transactional
	public void onBoardWithoutScreening(PersonStaffing personStaffing, OnboardingCheckList onboardingCheckList) {
	    CustomLoggerUtils.INSTANCE.log.info("inside saveOnboardingResourceDetails(PersonStaffing personStaffing, OnboardingCheckList onboardingCheckList)  method of OnboardingResourceDaoImpl class");
        onboardingCheckList.setPersonStaffing(personStaffing);
        entityManager.merge(onboardingCheckList);
	}
	


	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.OnboardingResourceDao#saveOnboardingChecklistEntry(com.sapient.statestreetscreeningapplication.model.entity.OnboardingCheckList)
	 */
	@Override
	@Transactional
	public OnboardingCheckList saveOnboardingChecklistEntry(OnboardingCheckList onboardingResourceCheckList) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveOnboardingChecklistEntry(OnboardingCheckList onboardingResourceCheckList)  method of OnboardingResourceDaoImpl class");
		entityManager.persist(onboardingResourceCheckList);
		return onboardingResourceCheckList;
	}

	@Override
	@Transactional
	public List<PersonStaffing> fetchPersonStaffingDetails() {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchPersonStaffingDetails() method of OnboardingResourceDaoImpl class");
		String query = "from PersonStaffing";
		TypedQuery<PersonStaffing> o = entityManager.createQuery(query,PersonStaffing.class);
		return o.getResultList();
	}

	@Override
	@Transactional
	public void editPersonStaffing(PersonStaffingBean personStaffingBean,LocationNew locationNew) {
		CustomLoggerUtils.INSTANCE.log.info("inside editPersonStaffing(PersonStaffingBean personStaffingBean,LocationNew locationNew) method of OnboardingResourceDaoImpl class");
		PersonStaffing personStaffing=entityManager.find(PersonStaffing.class, personStaffingBean.getPersonStaffingId());
		/*personStaffing.setLocation(LocationNewConverter.locationBeanToEntity(personStaffingBean.getLocation()));
		personStaffing.setPerson(PersonNewConverter.personBeanToEntity(personStaffingBean.getPerson()));*/
		personStaffing.getPerson().setSupervisorNtId(personStaffingBean.getPerson().getSupervisorNtId());
		personStaffing.setLocation(locationNew);
		personStaffing.setOffboarded(personStaffingBean.isOffboarded());
		SimpleDateFormat formatter ;  
		formatter = new SimpleDateFormat("MM/dd/yyyy");	
		try {
			if(personStaffingBean.getEndDate()!=null){
			personStaffing.setEndDate(formatter.parse(personStaffingBean.getEndDate()));
			}
	    } 
		catch (ParseException e) {
		e.printStackTrace();
	    }
		CustomLoggerUtils.INSTANCE.log.info("the staffing is "+personStaffing.getEndDate());
		
		entityManager.merge(personStaffing);
		
	}

	@Override
	@Transactional
	public PersonStaffing fetchPersonStaffingByOracleID(int personId) {
		PersonStaffing personStaffing;
		String query = "select * from person_staffing as I LEFT JOIN person as P ON I.PERSON_ID=P.PERSON_ID LEFT JOIN project_new ON I.PROJECT_ID=project_new.PROJECT_ID LEFT JOIN location_new ON I.LOCATION_ID=location_new.LOCATION_ID LEFT JOIN position ON I.POSITION_ID=position.POSITION_ID where P.PERSON_ID =:personId";
		Query p = entityManager.createNativeQuery(query,
				PersonStaffing.class);
		p.setParameter("personId",personId);
		
		try {
			personStaffing = (PersonStaffing) p.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return personStaffing;
	}
	public PersonStaffing fetchPersonStaffingByPersonID(int personId){
		PersonStaffing personStaffing;
		String query = "select * from person_staffing as I LEFT JOIN person as P ON I.PERSON_ID=P.PERSON_ID LEFT JOIN project_new ON I.PROJECT_ID=project_new.PROJECT_ID LEFT JOIN location_new ON I.LOCATION_ID=location_new.LOCATION_ID LEFT JOIN position ON I.POSITION_ID=position.POSITION_ID where P.PERSON_ID =:personId";
		Query p = entityManager.createNativeQuery(query,
				PersonStaffing.class);
		p.setParameter("personId",personId);
		
		try {
			personStaffing = (PersonStaffing) p.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return personStaffing;
	}
	
	
	@Override
	@Transactional
	public PersonStaffing fetchPersonStaffingByPersonStaffingId(Long staffingId) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchPersonStaffingByPersonStaffingId(Long staffingId) method of OnboardingResourceDaoImpl class");
		PersonStaffing personStaffing =  entityManager.find(PersonStaffing.class, staffingId);
		return personStaffing ;
	}
	
	

	@Override
	public OnboardingCheckList fetchNewOnbaordingCheckList(long leadOnboardID) {
		OnboardingCheckList onboardingCheckList;
		onboardingCheckList=entityManager.find(OnboardingCheckList.class, leadOnboardID);
		return onboardingCheckList;
	}

	@Override
	@Transactional
	public List<OnboardingCheckList> fetchAllResources() {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchAllResources() method of OnboardingResourceDaoImpl class");
		List<OnboardingCheckList> resourcesOnboarding = new ArrayList<OnboardingCheckList>();
		String query = "from OnboardingCheckList";
		TypedQuery<OnboardingCheckList> o = entityManager.createQuery(query,OnboardingCheckList.class);
		try {
			resourcesOnboarding = o.getResultList();
			CustomLoggerUtils.INSTANCE.log.info("The size of the list obtained is" + resourcesOnboarding.size());
		} catch (NoResultException e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.info("Onboarding CheckList is null");
			return null;
		}
		return resourcesOnboarding;
	}
	
	@Override
	@Transactional
	public void updatePersonClientId(PersonStaffingBean personStaffingBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside updatePersonClientId(PersonStaffingBean personStaffingBean) method of OnboardingResourceDaoImpl class");
		PersonStaffing personStaffing=entityManager.find(PersonStaffing.class, personStaffingBean.getPersonStaffingId());
		personStaffing.getPerson().setPersonClientId(personStaffingBean.getPerson().getPersonClientId());
		entityManager.merge(personStaffing);
	}

	@Override
	@Transactional
	public void updateStatusChangeLog(StatusChangeLogEntity entity){
		entityManager.merge(entity);
	}
	@Override
	public List<StatusChangeLogEntity> viewStatusChangeLogList(){
		String query = "from StatusChangeLogEntity";
		TypedQuery<StatusChangeLogEntity> o = entityManager.createQuery(query,StatusChangeLogEntity.class);
		return o.getResultList();
	}

	@Override
	public OnboardingCheckList getOnboardingCheckListByStaffingId(Long personStaffingId) {
		CustomLoggerUtils.INSTANCE.log.info("inside getOnboardingCheckListByStaffingId(Long personStaffingId)  method of OnboardingResourceDaoImpl class " );
		PersonStaffing personStaffing = entityManager.find(PersonStaffing.class, personStaffingId);
		String q = "from OnboardingCheckList where personStaffing=:personStaffing";
		TypedQuery<OnboardingCheckList> OnboardingCheckListTQ = entityManager.createQuery(q,OnboardingCheckList.class);
		OnboardingCheckListTQ.setParameter("personStaffing",personStaffing);
		OnboardingCheckList onboardingCheckList = OnboardingCheckListTQ.getSingleResult();
		return onboardingCheckList;
	}

	@Override
	@Transactional
	public OnboardingCheckList mergeOnboardingCheckList(OnboardingCheckList onboardingCheckList) {
		CustomLoggerUtils.INSTANCE.log.info("inside mergeOnboardingCheckList(OnboardingCheckList onboardingCheckList)  method of OnboardingResourceDaoImpl class " );
		return entityManager.merge(onboardingCheckList);
	}

	@Override
	@Transactional
	public boolean removeCurrentStaffing(Long personStaffingId) {
		PersonStaffing personStaffing = entityManager.find(PersonStaffing.class, personStaffingId);
		personStaffing.setEmailDls(null);
		personStaffing = entityManager.merge(personStaffing);
		String q = "from OnboardingCheckList where personStaffing=:personStaffing";
		TypedQuery<OnboardingCheckList> OnboardingCheckListTQ = entityManager.createQuery(q,OnboardingCheckList.class);
		OnboardingCheckListTQ.setParameter("personStaffing",personStaffing);
		OnboardingCheckList onboardingCheckList = OnboardingCheckListTQ.getSingleResult();
		boolean flag = false;
		entityManager.remove(onboardingCheckList);
		PersonStaffing psLast = personStaffing.getImmediateLastStaffing();
		personStaffing.setImmediateLastStaffing(null);
		PersonStaffing psNext = personStaffing.getImmediateNextStaffing();
		personStaffing.setImmediateNextStaffing(null);
		if(psLast!=null){
		   psLast.setImmediateNextStaffing(psNext);
		   psLast = entityManager.merge(psLast);
		}
		if(psNext!=null){
		   psNext.setImmediateLastStaffing(psLast);
		   psNext = entityManager.merge(psNext);
		}
		entityManager.remove(personStaffing);
		flag = true;
		return flag;
	}
}
