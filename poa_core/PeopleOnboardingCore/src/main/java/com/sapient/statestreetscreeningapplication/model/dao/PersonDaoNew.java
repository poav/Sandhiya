package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.sapient.statestreetscreeningapplication.model.entity.MonthlyProjectBudgetEntity;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.entity.ProjectNew;
import com.sapient.statestreetscreeningapplication.model.entity.Role;
import com.sapient.statestreetscreeningapplication.model.entity.TempPerson;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.utils.enums.Months;

// TODO: Auto-generated Javadoc
/**
 * The Interface PersonDaoNew.
 */
@Component
public interface PersonDaoNew {

/**
 * Gets the person by oracle id.
 *
 * @param oracleId the oracle id
 * @return the person by oracle id
 */
public Person getPersonByOracleId(int oracleId);

/**
 * Register new person.
 *
 * @param person the person
 * @return true, if successful
 */
public boolean registerNewPerson(Person person);

/**
 * Validate person id.
 *
 * @param personId the person id
 * @return true, if successful
 */
public int validatePersonId(int personId);

/**
 * Mark person as exited.
 *
 * @param person the person
 */
public void markPersonAsExited(Person person);

/**
 * Gets the all non exited person details.
 *
 * @return the all non exited person details
 */
List<Person> getAllNonExitedPersonDetails();

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
boolean modifyPersonRole(int personId, List<Role> userRolesList);

/**
 * Update person.
 *
 * @param personBeanToEntity the person bean to entity
 * @return true, if successful
 */
public boolean updatePerson(Person personBeanToEntity);

/**
 * Gets the interviewers for daily mail.
 *
 * @return the interviewers for daily mail
 */
public List<Person> getInterviewersForDailyMail();

/**
 * Gets the interviewers for immediate updates.
 *
 * @return the interviewers for immediate updates
 */
public List<Person> getInterviewersForImmediateUpdates();

public Person getPersonByNtId(String personNtId);

public List<TempPerson> getAllTempPersons();

public void addOracleIdForTempPerson(PersonBean person, long tempPersonId, int newOracleId);

public List<Person> getActiveNonExitedDailyMailSubscribedPersonList();

public List<PersonScreeningDetails> getPSDListForAttachment();

public Person getPersonByPersonId(int personId);

public TreeMap<Person, Long> getTopScreenersWithCount(Date fDate, Date lDate);

public Person getPersonNewBeanByPersonOracleId(int personOracleId);

public List<Person> getAllNonExitedAndNonTempPersonDetails();

public List<PersonStaffing> getStaffingThisMonth(Date firstDate, Date lastDate);

public List<MonthlyProjectBudgetEntity> getMonthlyProjectBudgetThisQuarter(Months month1, Months month2, Months month3, int year);

public List<MonthlyProjectBudgetEntity> getMonthlyProjectBudgetThisMonth(Months month, int year);

public void newMonthlyProjectBudget(MonthlyProjectBudgetEntity mpbNew);

public List<MonthlyProjectBudgetEntity> getMonthlyProjectBudgetThisYear(int year);

public List<PersonStaffing> getStaffingThisProject(Date firstJanuaryOfSelectedYear,Date thirtyFirstDecemberOfSelectedYear, int projectId);

public List<MonthlyProjectBudgetEntity> getMonthlyProjectBudgetThisYearThisProject(int year, ProjectNew projectNew);

public List<Person> getAllNonExitedAndNonTempPersonDetailsJDBC();

}
