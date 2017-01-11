package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew;
import com.sapient.statestreetscreeningapplication.model.dao.impl.PersonDaoNewImpl;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.Role;
import com.sapient.statestreetscreeningapplication.model.entity.TempPerson;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.model.service.PersonService;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.RoleBean;
import com.sapient.statestreetscreeningapplication.ui.bean.TempPersonBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonNewConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.TempPersonConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonServiceImpl.
 */
@Service
public class PersonServiceImpl implements PersonService {
	
	/** The person dao new. */
	@Autowired
	private PersonDaoNew personDaoNew;
	
	/** The person lookup service. */
	@Autowired
	private PersonLookupService personLookupService;

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonService#registerNewPerson(com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean)
	 */
	@Override
	public boolean registerNewPerson(PersonNewBean personNewBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside registerNewPerson(PersonNewBean personNewBean) method of PersonServiceImpl class");
		boolean created;
		Person personEntity = PersonNewConverter
				.personBeanToEntity(personNewBean);
		created = personDaoNew.registerNewPerson(personEntity);
		return created;

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonService#markPersonAsExited(com.sapient.statestreetscreeningapplication.model.entity.Person)
	 */
	@Override
	public void markPersonAsExited(Person person) {
		CustomLoggerUtils.INSTANCE.log.info("inside markPersonAsExited(Person person) method of PersonServiceImpl class");
		personDaoNew.markPersonAsExited(person);
	}

	/**
	 * Gets the person dao new.
	 *
	 * @return the person dao new
	 */
	public PersonDaoNew getPersonDaoNew() {
		return personDaoNew;
	}

	/**
	 * Sets the person dao new.
	 *
	 * @param personDaoNew the new person dao new
	 */
	public void setPersonDaoNew(PersonDaoNew personDaoNew) {
		this.personDaoNew = personDaoNew;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonService#validatePersonId(int)
	 */
	@Override
	public int validatePersonId(int personId) {
		CustomLoggerUtils.INSTANCE.log.info("inside validatePersonId(int personId) method of PersonServiceImpl class");
		int validated;
		validated = personDaoNew.validatePersonId(personId);
		return validated;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonService#getAllPersons()
	 */
	@Override
	public List<PersonNewBean> getAllPersons() {
		
		//returns all persons with roles
		
		CustomLoggerUtils.INSTANCE.log.info("inside  method of PersonServiceImpl class");
		List<Person> availablePersons = personDaoNew
				.getAllNonExitedAndNonTempPersonDetails();
		List<PersonNewBean> allPersons = new ArrayList<PersonNewBean>();
		List<Person> exitedPersons = new ArrayList<Person>();
		List<PersonNewBean> allPersonsWithRoles = new ArrayList<PersonNewBean>();
		/*
		 * List<String> personNtIdList = new ArrayList<String>(); for (Person
		 * personEntity : availablePersons) {
		 * personNtIdList.add(personEntity.getPersonNtId()); }
		 * personLookupService.getPersonsByOracleIds(oracleIdList, true);
		 */
		PersonBean personBean = null;
		for (Person s : availablePersons) {
			PersonNewBean bean = PersonNewConverter.personNewEntityToBean(s);
			int combinedRoles = 1;
			for (RoleBean roleBean : bean.getRole()) {
				combinedRoles = combinedRoles * roleBean.getRoleId();
			}
			bean.setUserRole(combinedRoles);
			CustomLoggerUtils.INSTANCE.log.info("The user is active?"
					+ bean.getIsActive());
			if(combinedRoles>1)
			personBean = personLookupService
					.getPersonByNTId((s.getPersonNtId()));
			
			if (personBean == null) {
				exitedPersons.add(s);
			} else {
				bean.setPersonDetails(personBean);
				allPersons.add(bean);
			}
		}
		
		if (!exitedPersons.isEmpty()) {
			CustomLoggerUtils.INSTANCE.log
					.info("Person(s) have exited sapient");
			for (Person exitedPerson : exitedPersons) {
				markPersonAsExited(exitedPerson);
			}

		}
		for(PersonNewBean p: allPersons){
			if(p.getUserRole()>1){
				allPersonsWithRoles.add(p);
			}
		}

		return allPersonsWithRoles;
	}

	@Override
	public List<TempPersonBean> getAllTempPersons(){
		CustomLoggerUtils.INSTANCE.log.info("inside getAllTempPersons() method of PersonServiceImpl class");
		List<TempPersonBean> tempPersonList=new ArrayList<TempPersonBean>();
		List<TempPerson> tempPersonEntityList=personDaoNew.getAllTempPersons();
		for(TempPerson tp:tempPersonEntityList){
			tempPersonList.add(TempPersonConverter.tempPersonEntityToBean(tp));
		}
		
		return tempPersonList;
		
	}
	@Override
	public void addOracleIdForTempPerson(long tempPersonId, int newOracleId){
		CustomLoggerUtils.INSTANCE.log.info("inside addOracleIdForTempPerson(long tempPersonId, long newOracleId) method of PersonServiceImpl class");
		PersonBean person=personLookupService.getPersonByOracleId(newOracleId);
		personDaoNew.addOracleIdForTempPerson(person,tempPersonId, newOracleId);
		
	}
	@Override
	public Person getPersonByOracleId(int personOracleId){
		CustomLoggerUtils.INSTANCE.log.info("inside getPersonByOracleId(int personOracleId) method of PersonServiceImpl class");
		
		return (personDaoNew.getPersonByOracleId(personOracleId));
	}

	
	@Override
	public List<String> getEmailListForInterviewers(){
		CustomLoggerUtils.INSTANCE.log.info("inside getEmailListForInterviewers() method of PersonServiceImpl class");
		List<Person> fetchedPersonList = new ArrayList<Person>();
	    fetchedPersonList =	personDaoNew.getActiveNonExitedDailyMailSubscribedPersonList();
		List<String> interviewerEmailList = new ArrayList<String>();
		PersonBean personBean;
		for (Person person : fetchedPersonList){
			PersonNewBean pnewbean = PersonNewConverter.personNewEntityToBean(person);
			personBean = personLookupService.getPersonByNTId((person.getPersonNtId()));
			if (personBean == null) {
				markPersonAsExited(person);
			} 
			else{
				int combinedRoles = 1;
				for (RoleBean roleBean : pnewbean.getRole()) {
					combinedRoles = combinedRoles * roleBean.getRoleId();
				}
				if(combinedRoles>1){
					interviewerEmailList.add(personBean.getEmail());
				}
			}
		}
		return interviewerEmailList;
	}


	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonService#deletePerson(int)
	 */
	@Override
	public boolean deletePerson(int personId) {
		CustomLoggerUtils.INSTANCE.log.info("inside deletePerson(int personId) method of PersonServiceImpl class");
		return personDaoNew.deletePerson(personId);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonService#changePersonStatus(int, boolean)
	 */
	@Override
	public boolean changePersonStatus(int personId, boolean b) {
		CustomLoggerUtils.INSTANCE.log.info("inside changePersonStatus(int personId, boolean b) method of PersonServiceImpl class");
		return personDaoNew.changePersonStatus(personId, b);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonService#modifyPersonRole(int, java.util.List)
	 */
	@Override
	public boolean modifyPersonRole(int personId, List<Role> userRolesList) {
		CustomLoggerUtils.INSTANCE.log.info("inside modifyPersonRole(int personId, List<Role> userRolesList) method of PersonServiceImpl class");
		return personDaoNew.modifyPersonRole(personId, userRolesList);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonService#updatePerson(com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean)
	 */
	@Override
	public boolean updatePerson(PersonNewBean currentPerson) {
		CustomLoggerUtils.INSTANCE.log.info("inside updatePerson(PersonNewBean currentPerson) method of PersonServiceImpl class");
		return personDaoNew.updatePerson(PersonNewConverter
				.personBeanToEntity(currentPerson));
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonService#getEmailsForDailyUpdate()
	 */
	@Override
	public List<String> getEmailsForDailyUpdate() {
		CustomLoggerUtils.INSTANCE.log.info("inside getEmailsForDailyUpdate() method of PersonServiceImpl class");

		List<String> interviewerEmailList = new ArrayList<String>();
		List<Person> interviewersList = personDaoNew
				.getInterviewersForDailyMail();
		List<Person> exitedInterviewersList = new ArrayList<Person>();
		PersonBean personBean = null;
		for (Person interviewer : interviewersList) {
			personBean = personLookupService.getPersonByOracleId(interviewer
					.getPersonId());
			if (personBean != null) {
				interviewerEmailList.add(personBean.getEmail());
			} else {
				exitedInterviewersList.add(interviewer);
			}
		}
		for (Person person : exitedInterviewersList) {
			CustomLoggerUtils.INSTANCE.log
					.info("The following interviewer has left sapient and is removed from the mail list"
							+ person.getPersonId());
			personDaoNew.markPersonAsExited(person);
		}
		CustomLoggerUtils.INSTANCE.log.info("The following will be mailed: "
				+ interviewerEmailList);
		return interviewerEmailList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonService#getInterviewersByName(java.lang.String)
	 */
	@Override
	public List<PersonBean> getInterviewersByName(String partialInitialName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getInterviewersByName(String partialInitialName) method of PersonServiceImpl class");
		List<Person> interviewerList = personDaoNew
				.getAllNonExitedPersonDetails();
		if (interviewerList == null) {
			return null;
		}

		List<Integer> oracleIdList = new ArrayList<Integer>();
		for (Person interviewerEntity : interviewerList) {
			oracleIdList.add(Integer.valueOf(interviewerEntity.getPersonId()));
		}
		return personLookupService.getInterviewersByName(partialInitialName,
				oracleIdList);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonService#getEmailsForImmediateUpdates()
	 */
	@Override
	public List<String> getEmailsForImmediateUpdates() {
		CustomLoggerUtils.INSTANCE.log.info("inside getEmailsForImmediateUpdates() method of PersonServiceImpl class");
		List<String> interviewerEmailList = new ArrayList<String>();
		List<Person> interviewersList = personDaoNew
				.getInterviewersForImmediateUpdates();
		List<Person> exitedInterviewersList = new ArrayList<Person>();
		PersonBean personBean = null;
		for (Person interviewer : interviewersList) {
			
			if(!interviewer.getIsTemp()){
			personBean = personLookupService.getPersonByOracleId(interviewer
					.getPersonOracleId());
			
			if (personBean != null) {
				interviewerEmailList.add(personBean.getEmail());
			} else {
				exitedInterviewersList.add(interviewer);
			}
		}
		}
		for (Person person : exitedInterviewersList) {
			CustomLoggerUtils.INSTANCE.log
					.info("The following interviewer has left sapient and is removed from the mail list"
							+ person.getPersonId());
			personDaoNew.markPersonAsExited(person);
		}
		CustomLoggerUtils.INSTANCE.log.info("The following will be mailed: "
				+ interviewerEmailList);
		return interviewerEmailList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonService#getPersonByPersonId(int)
	 */
	@Override
	public PersonNewBean getPersonByPersonId(int personId) {
		CustomLoggerUtils.INSTANCE.log.info("inside getPersonByPersonId(int personId) method of PersonServiceImpl class");
		return PersonNewConverter.personNewEntityToBean(personDaoNew.getPersonByPersonId(personId));
	}
	@Override
	public PersonNewBean getPersonByPersonNtId(String personNtId) {
		CustomLoggerUtils.INSTANCE.log.info("inside getPersonByPersonNtId(String personNtId) method of PersonServiceImpl class");
		return PersonNewConverter.personNewEntityToBean(personDaoNew.getPersonByNtId(personNtId));
	}
	public PersonNewBean getPersonNewBeanByPersonOracleId(int personOracleId) {
		CustomLoggerUtils.INSTANCE.log.info("inside getPersonNewBeanByPersonOracleId(int personOracleId) method of PersonServiceImpl class");
		return PersonNewConverter.personNewEntityToBean(personDaoNew.getPersonNewBeanByPersonOracleId(personOracleId));
	}
}
