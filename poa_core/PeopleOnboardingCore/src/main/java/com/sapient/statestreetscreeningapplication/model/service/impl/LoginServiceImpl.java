package com.sapient.statestreetscreeningapplication.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.service.LoginService;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonNewConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginServiceImpl.
 */
@Service
public class LoginServiceImpl implements LoginService {

	/*@Autowired
	StakeholderDao stakeholderDao;*/
	/** The person dao new. */
	@Autowired
	PersonDaoNew personDaoNew;
	
	/** The person lookup service. */
	@Autowired
	PersonLookupService personLookupService;

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.LoginService#validateAndGetUser(java.lang.String)
	 */
	@Override
	public PersonNewBean validateAndGetUser(String username) {
		CustomLoggerUtils.INSTANCE.log.info("inside validateAndGetUser(String username) method of LoginServiceImpl class");
		PersonBean person = personLookupService.getPersonByNTId(username);
		if(person!=null){
		Person personNew = personDaoNew.getPersonByOracleId(person.getOracleId());
		return (personNew == null) ? null : PersonNewConverter
				.personNewEntityToBean(personNew);
		}
		return null;
	}

	/**
	 * Gets the person lookup service.
	 *
	 * @return the person lookup service
	 */
	public PersonLookupService getPersonLookupService() {
		return personLookupService;
	}

	/**
	 * Sets the person lookup service.
	 *
	 * @param personLookupService the new person lookup service
	 */
	public void setPersonLookupService(PersonLookupService personLookupService) {
		this.personLookupService = personLookupService;
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

	

}
