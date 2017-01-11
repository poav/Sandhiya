package com.sapient.statestreetscreeningapplication.model.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.Role;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.TempPersonBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface PersonService.
 */

@Component
public interface PersonService {

/**
 * Register new person.
 *
 * @param personNewBean the person new bean
 * @return true, if successful
 */
public boolean registerNewPerson(PersonNewBean personNewBean);

/**
 * Validate person id.
 *
 * @param personId the person id
 * @return true, if successful
 */
public int validatePersonId(int personId);

/**
 * Gets the all persons.
 *
 * @return the all persons
 */
public List<PersonNewBean> getAllPersons();

/**
 * Mark person as exited.
 *
 * @param person the person
 */
void markPersonAsExited(Person person);

/**
 * Delete person.
 *
 * @param personId the person id
 * @return true, if successful
 */
public boolean deletePerson(int personId);



/**
 * Change person status.
 *
 * @param personId the person id
 * @param b the b
 * @return true, if successful
 */
public boolean changePersonStatus(int personId, boolean b);

/**
 * Modify person role.
 *
 * @param personId the person id
 * @param userRolesList the user roles list
 * @return true, if successful
 */
public boolean modifyPersonRole(int personId, List<Role> userRolesList);

/**
 * Update person.
 *
 * @param currentPerson the current person
 * @return true, if successful
 */
public boolean updatePerson(PersonNewBean currentPerson);

/**
 * Gets the emails for daily update.
 *
 * @return the emails for daily update
 */
public List<String> getEmailsForDailyUpdate();

/**
 * Gets the interviewers by name.
 *
 * @param name the name
 * @return the interviewers by name
 */
public List<PersonBean> getInterviewersByName(String name);

/**
 * Gets the person by person id.
 *
 * @param personId the person id
 * @return the person by person id
 */
public PersonNewBean getPersonByPersonId(int personId);

/**
 * Gets the emails for immediate updates.
 *
 * @return the emails for immediate updates
 */
public List<String> getEmailsForImmediateUpdates();


public PersonNewBean getPersonByPersonNtId(String personNtId);

public List<String> getEmailListForInterviewers();

public List<TempPersonBean> getAllTempPersons();

public void addOracleIdForTempPerson(long tempPersonId, int newOracleId);



public Person getPersonByOracleId(int personOracleId);

public PersonNewBean getPersonNewBeanByPersonOracleId(int personOracleId);


}
