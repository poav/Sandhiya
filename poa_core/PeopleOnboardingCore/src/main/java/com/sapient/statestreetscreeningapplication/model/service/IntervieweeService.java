package com.sapient.statestreetscreeningapplication.model.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;
import com.sapient.statestreetscreeningapplication.ui.bean.IntervieweeBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingResourceBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonScreeningDetailsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonStaffingBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ScoreBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ScoresNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.TempPersonBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IntervieweeService.
 */
@Service
public interface IntervieweeService {
	
	/**
	 * Fetch interviewee.
	 *
	 * @param id the id
	 * @return the interviewee bean
	 */
	public PersonScreeningDetailsBean fetchInterviewee(long id); //Used to populate the screening page on edit
	
	/**fetches all rows from PersonScreeningDetails By interviewee OracleID
	 * 
	 * @param personId
	 * @return list of personScreeningDetails
	 */
	
	public List<PersonScreeningDetails> fetchMultiplePersonScreeningDetailsByOracleID(int personId);

	
	/**
	 * Gets the interviewee for daily email.
	 *
	 * @return the interviewee for daily email
	 */
	public List<IntervieweeBean> getIntervieweeForDailyEmail();
	
	/**
	 * Sets the fields for interviewee.
	 *
	 * @param intervieweeBean the interviewee bean
	 * @return the interviewee bean
	 */
	public IntervieweeBean setFieldsForInterviewee(IntervieweeBean intervieweeBean);

	/**
	 * Gets the scores.
	 *
	 * @param intervieweeId the interviewee id
	 * @return the scores
	 */
	public Set<ScoresNewBean> getScores(long intervieweeId);

	
	// For interviewee datatable populate. Used by interviewee action
	/**
	 * Populate interviewee data.
	 *
	 * @param sStart the s start
	 * @param sAmount the s amount
	 * @param columnName the column name
	 * @param sdir the sdir
	 * @param sSearch the s search
	 * @return the map
	 */
	public Map<String, Object> populateIntervieweeData(int sStart, int sAmount,
			String columnName, String sdir, String sSearch);

	/**
	 * Gets the exited interviewees.
	 *
	 * @return the exited interviewees
	 */
	public List<IntervieweeBean> getExitedInterviewees();

	//for deleting exited interviewees
	/**
	 * Delete.
	 *
	 * @param intervieweeNos the interviewee nos
	 * @return true, if successful
	 */
	public boolean delete(List<Long> intervieweeNos);

	//for checking duplicate interviewee records while entering new interviewee.
	/**
	 * Check if interviewee exists.
	 *
	 * @param intervieweeOracleID the interviewee oracle id
	 * @param interviewerOracleId the interviewer oracle id
	 * @return the string
	 */
	public String checkIfIntervieweeExists(int intervieweeOracleID,int interviewerOracleId);

	/**
	 * Change status.
	 *
	 * @param intervieweeNos the interviewee nos
	 * @param status the status
	 * @param result the result
	 */
	public void changeStatus(List<Long> intervieweeNos, String status, String result);

	/**
	 * Onboard interviewee.
	 *
	 * @param oracleID the oracle id
	 * @param onboardingResourceBean the onboarding resource bean
	 * @return the long
	 */
	public long onboardInterviewee(int oracleID,
			OnboardingResourceBean onboardingResourceBean);

	/**
	 * Change status.
	 *
	 * @param intervieweeNo the interviewee no
	 * @param status the status
	 * @param result the result
	 */
	public void changeStatus(long intervieweeNo, String status, String result);

	/**
	 * Gets the project of interviewee.
	 *
	 * @param intervieweeOracleID the interviewee oracle id
	 * @return the project of interviewee
	 */
	public ProjectBean getProjectOfInterviewee(int intervieweeOracleID);

	/**
	 * Fetch interviewee on oracle id.
	 *
	 * @param oracleId the oracle id
	 * @return the interviewee bean
	 */
	public IntervieweeBean fetchIntervieweeOnOracleId(int oracleId);
	
	/**
	 * Fetch interviewee ids by status id.
	 *
	 * @param statusId the status id
	 * @return the list
	 */
	public List<Long> fetchIntervieweeIdsByStatusId(long statusId);
	
	
	/**
	 * Checks in db to see if duplicate entry has been made for the combination -interviewee id,screener id and start date.
	 * @param intervieweeOracleId
	 * @param screenerOracleId
	 * @param startDate
	 * @return
	 */
	public String checkIfDuplicateIntervieweeEntryExists(int intervieweeOracleId,int screenerOracleId,String startDate);

	/**
	 * Change project.
	 *
	 * @param oracleId the oracle id
	 * @param newProjectName the new project name
	 * @param newIQNName the new iqn name
	 * @param newAtrackName the new atrack name
	 */
	public void changeProject(int oracleId, String newProjectName,
			String newIQNName, String newAtrackName);

	/**
	 * Change location.
	 *
	 * @param oracleID the oracle id
	 * @param location the location
	 */
	public void changeLocation(int oracleID, String location);

	/**
	 * Adds the new project.
	 *
	 * @param oracleId the oracle id
	 * @param newProjectName the new project name
	 * @param newIQNName the new iqn name
	 * @param newAtrackName the new atrack name
	 */
	void addNewProject(int oracleId, String newProjectName, String newIQNName,
			String newAtrackName);

	/**
	 * Reboard interviewee.
	 *
	 * @param oracleID the oracle id
	 * @param onboardingResourceBean the onboarding resource bean
	 * @return the long
	 */
	long reboardInterviewee(int oracleID,
			OnboardingResourceBean onboardingResourceBean);

	PersonScreeningDetailsBean saveInterviewee(
			PersonScreeningDetailsBean personScreeningDetailsBean);

	PersonScreeningDetailsBean updateInterviewee(
			PersonScreeningDetailsBean personScreeningDetailsBean);

	Set<ScoresNewBean> convertStringToList(String scores, String categoryName,
			PersonScreeningDetailsBean personScreeningDetailsBean);

	public PersonNewBean setFieldsForPerson(PersonNewBean person);

	public PersonScreeningDetails fetchPersonScreeningDetailsOnPersonId(int personId);

	public PersonScreeningDetails updatePersonScreeningDetails(
			PersonScreeningDetails personScreeningDetails);

	void onboardPerson(PersonStaffingBean personStaffingBean,
			OnboardingCheckListBean onboardingCheckListBean);

	public void changeNewStatus(int oracleId, String string, String string2,long checkListId);


	public String checkIfIntervieweeSameAsScreener(int intervieweeOracleId,
			int screenerOracleId);
			
	public void onBoardWithoutScreening(PersonStaffingBean personStaffingBean,
			OnboardingCheckListBean onboardingCheckListBean);

	public String checkIfDuplicateIntervieweeEntryExistsForTemp(
			String tempPersonEmail, int interviewerOracleId,
			String stringScreeningStartDate);

	public PersonScreeningDetailsBean saveTempInterviewee(
			PersonScreeningDetailsBean personScreeningDetailsBean);

	public PersonNewBean findTempPersonDetailsFromScreeningId(
			long personScreeningDetailsId);

	public List<PersonScreeningDetails> fetchMultiplePersonScreeningDetailsByPersonID(
			int personId);
	public TempPersonBean findTempPersonDetailsFromTempId(
			long tempPersonId);

	public PersonScreeningDetails fetchPersonScreeningDetailsByOnboardID(
			Long onboardingCheckListId);

	public PersonScreeningDetailsBean fetchPersonScreeningDetails(long id);

	public List<PersonScreeningDetailsBean> retrieveAllInterviewees();

	public Map<String, Object> populateServerInterviewee(int currentPage, int numberOfRows, String columnName,
			String sortDirection, String searchKey);

	public List<PersonScreeningDetailsBean> getAllIntervieweeList(int start, int rows, int colNum, String searchKey,
			String sortDirection);

	public Map<String, Object> getIntervieweesCountAndList(int start, int rows, int colNum, String searchKey, String sortDirection);

	}
