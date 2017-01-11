package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sapient.statestreetscreeningapplication.model.entity.OnboardingCheckList;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;
import com.sapient.statestreetscreeningapplication.model.entity.TempPerson;
import com.sapient.statestreetscreeningapplication.ui.bean.IntervieweeBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonScreeningDetailsBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IntervieweeDao.
 */
@Component
public interface IntervieweeDao {
	
	
	/**
	 * Save interviewee.
	 *
	 * @param personScreeningDetails the person screening details
	 * @return true, if successful
	 */
	public PersonScreeningDetails saveInterviewee(PersonScreeningDetails personScreeningDetails);
	
	/**fetches all rows from PersonScreeningDetails By interviewee OracleID
	 * 
	 * @param personId
	 * @return list of personScreeningDetails
	 */
	
	public List<PersonScreeningDetails> fetchMultiplePersonScreeningDetailsByOracleID(int personId);

	

	
	
	/**
	 * Gets the interviewee by oracle id.
	 *
	 * @param numericCellValue the numeric cell value
	 * @return the interviewee by oracle id
	 */
	public Person getIntervieweeByOracleId(int numericCellValue);

	

	// For interviewee datatable populate
	// Used by intervieweeService
	/**
	 * Populate interviewee.
	 *
	 * @param start the start
	 * @param amount the amount
	 * @param orderingColumn the ordering column
	 * @param orderingDirection the ordering direction
	 * @param searchKey the search key
	 * @return the map
	 */
	Map<String, Object> populateInterviewee(int start, int amount,
			String orderingColumn, String orderingDirection, String searchKey);

	/**
	 * Search interviewee by oracle id.
	 *
	 * @param start the start
	 * @param amount the amount
	 * @param orderingColumn the ordering column
	 * @param orderingDirection the ordering direction
	 * @param searchKey the search key
	 * @return the map
	 */
	Map<String, Object> searchIntervieweeByOracleId(int start, int amount,
			String orderingColumn, String orderingDirection, String searchKey);

	/**
	 * Search interviewee by oracle id or date.
	 *
	 * @param start the start
	 * @param amount the amount
	 * @param orderingColumn the ordering column
	 * @param orderingDirection the ordering direction
	 * @param searchKey the search key
	 * @return the map
	 */
	Map<String, Object> searchIntervieweeByOracleIdOrDate(int start, int amount,
			String orderingColumn, String orderingDirection, String searchKey);
	
	/**
	 * Search interviewee by date.
	 *
	 * @param start the start
	 * @param amount the amount
	 * @param orderingColumn the ordering column
	 * @param orderingDirection the ordering direction
	 * @param searchKey the search key
	 * @return the map
	 */
	Map<String, Object> searchIntervieweeByDate(int start, int amount,
			String orderingColumn, String orderingDirection, String searchKey);

	
	/**
	 * Delete.
	 *
	 * @param intervieweeNos the interviewee nos
	 * @return true, if successful
	 */
	public boolean delete(List<Long> intervieweeNos);
	
	/**
	 * Mark interviewee as exited.
	 *
	 * @param intervieweeId the interviewee id
	 */
	public void markIntervieweeAsExited(long intervieweeId);

	/**
	 * Change status.
	 *
	 * @param intervieweeNos the interviewee nos
	 * @param status the status
	 * @param result the result
	 */
	public void changeStatus(List<Long> intervieweeNos, String status,
			String result);

	/**
	 * Change status.
	 *
	 * @param intervieweeNo the interviewee no
	 * @param status the status
	 * @param result the result
	 */
	void changeStatus(long intervieweeNo, String status, String result);

	

	/**
	 * Fetch interviewee ids by status id.
	 *
	 * @param statusId the status id
	 * @return the list
	 */
	public List<Long> fetchIntervieweeIdsByStatusId(long statusId);

	

	
	/**
	 * Update existing interviewee.
	 *
	 * @param interviewee the interviewee
	 */
	public void updateExistingInterviewee(Person interviewee);

	/**
	 * Search interviewee by oracle id list.
	 *
	 * @param start the start
	 * @param amount the amount
	 * @param orderingColumn the ordering column
	 * @param orderingDirection the ordering direction
	 * @param oracleIdList the oracle id list
	 * @return the map
	 */
	public Map<String, Object> searchIntervieweeByOracleIdList(int start, int amount,
			String orderingColumn, String orderingDirection, List<String> oracleIdList);

	

	/**
	 * Fetch person screening details.
	 *
	 * @param Id the id
	 * @return the person screening details
	 */
	PersonScreeningDetails fetchPersonScreeningDetails(long Id);

	/**
	 * Update interviewee.
	 *
	 * @param personScreeningDetails the person screening details
	 */
	void updateInterviewee(PersonScreeningDetails personScreeningDetails);

	/**
	 * Fetch person screening details by oracle id.
	 *
	 * @param personId the person id
	 * @return the person screening details
	 */
	public PersonScreeningDetails fetchPersonScreeningDetailsByOracleID(
			int personId);

	/**
	 * Update existing person screening details.
	 *
	 * @param personScreeningDetails the person screening details
	 * @return 
	 */
	public PersonScreeningDetails updateExistingPersonScreeningDetails(
			PersonScreeningDetails personScreeningDetails);



	/**
	 *  checks If Duplicate Interviewee Entry Exists in the db for the combination of intervieweeid,screener id and start date.
	 * @param intervieweeOracleId
	 * @param screenerOracleId
	 * @param startDate
	 * @return
	 */

	public String checkIfDuplicateIntervieweeEntryExists(
			int intervieweeOracleId, int screenerOracleId, String startDate);

	public String checkIfDuplicateIntervieweeEntryExistsForTemp(
			String tempEmail, int screenerOracleId, String startDate);

	public Person findTempPersonDetailsFromScreeningId(
			long personScreeningDetailsId);

	public List<PersonScreeningDetails> fetchMultiplePersonScreeningDetailsByPersonID(
			int personId);

	public TempPerson findTempPersonDetailsFromTempId(long tempPersonId);

	public PersonScreeningDetails fetchPersonScreeningDetailsByOnboardID(
			Long onboardingCheckListId);

	public List<PersonScreeningDetails> retrieveTempInterviewees(int start, int amount,String orderingColumn, String orderingDirection,String searchKey);
	
	public int getTempPersonCount(int start, int amount, String orderingColumn,String orderingDirection, String searchQuery);

	//suraj
	public List<PersonScreeningDetails> getAllPersonScreeningDetails();

	public List<PersonScreeningDetailsBean> getIntervieweeList(int start, int rows, String columnName, String searchKey,
			String sortDirection);

	
}
