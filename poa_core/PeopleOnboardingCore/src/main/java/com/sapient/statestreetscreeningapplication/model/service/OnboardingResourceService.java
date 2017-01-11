package com.sapient.statestreetscreeningapplication.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.entity.OnboardingCheckList;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListLeadsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListOpsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingResourceBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonStaffingBean;
import com.sapient.statestreetscreeningapplication.ui.bean.StatusChangeLogBean;
// TODO: Auto-generated Javadoc
import com.sapient.statestreetscreeningapplication.ui.bean.PersonScreeningDetailsBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface OnboardingResourceService.
 */
@Service
public interface OnboardingResourceService  {
	
	/**
	 * Fetch resources onboarded.
	 *
	 * @return the list
	 */
	public List<OnboardingResourceBean>fetchResourcesOnboarded();
	
	/**
	 * Update onboarding resource info.
	 *
	 * @param onboardingResourceBean the onboarding resource bean
	 * @return the boolean
	 */
	public Boolean updateOnboardingResourceInfo(OnboardingResourceBean onboardingResourceBean);
	
	/**
	 * Find resource.
	 *
	 * @param onboardingResourceIdToBeUpdated the onboarding resource id to be updated
	 * @return the onboarding resource bean
	 */
	public OnboardingResourceBean findResource(long onboardingResourceIdToBeUpdated);
	
	/**
	 * Gets the onboarding checklist ops.
	 *
	 * @return the onboarding checklist ops
	 */
	public List<OnboardingCheckListOpsBean> getOnboardingChecklistOps();
	
	/**
	 * Edits the onboarding checklist.
	 *
	 * @param onboardingBean the onboarding bean
	 */
	public void editOnboardingChecklist(OnboardingCheckListOpsBean onboardingBean);
	
	/**
	 * Fetch on boarding checklist.
	 *
	 * @param checklistId the checklist id
	 * @return the onboarding check list ops bean
	 */
	public OnboardingCheckListOpsBean fetchOnBoardingChecklist(long checklistId);
	
	/**
	 * Gets the onboarding checklist leads.
	 *
	 * @return the onboarding checklist leads
	 */
	public List<OnboardingCheckListLeadsBean> getOnboardingChecklistLeads();
	
	/**
	 * Fetch on boarding checklist lead.
	 *
	 * @param onboardingLeadsCheckListId the onboarding leads check list id
	 * @return the onboarding check list leads bean
	 */
	public OnboardingCheckListLeadsBean fetchOnBoardingChecklistLead(
			long onboardingLeadsCheckListId);
	
	/**
	 * Edits the onboarding checklist lead.
	 *
	 * @param onboardingCheckList the onboarding lead bean
	 */
	public void editOnboardingChecklistLead(
			OnboardingCheckListBean onboardingCheckList);

	
	
	/**
	 * Onboard interviewee.
	 *
	 * @param oracleID the oracle id
	 * @param onboardingResourceBean the onboarding resource bean
	 */
	public void onboardInterviewee(int oracleID,
			OnboardingResourceBean onboardingResourceBean);

	/**
	 * Fetch resource of interviewee.
	 *
	 * @param id the id
	 * @return the onboarding resource bean
	 */
	public OnboardingResourceBean fetchResourceOfInterviewee(long id);
	
	/**
	 * Send psid to candidate.
	 *
	 * @param onboardID the onboard id
	 */
	public void sendPSIDToCandidate(int onboardID);
	
	/**
	 * Terminate onboarding.
	 *
	 * @param l the lead onboard id
	 * @param string the string
	 * @param offboardingComments the offboarding comments
	 */
	public void terminateOnboarding(long l, String string, String offboardingComments,PersonNewBean currentUser);
	
	/**
	 * Gets the onboarding candidates bgc inprogress.
	 *
	 * @return the onboarding candidates bgc inprogress
	 */
	public List<OnboardingCheckListOpsBean> getOnboardingCandidatesBGCInprogress();
	
	/**
	 * Gets the onboarding candidates bgc to be submitted.
	 *
	 * @return the onboarding candidates bgc to be submitted
	 */
	public List<OnboardingCheckListOpsBean> getOnboardingCandidatesBGCToBeSubmitted();
	
	/**
	 * Gets the candidates psid sent.
	 *
	 * @return the candidates psid sent
	 */
	public List<OnboardingResourceBean> getcandidatesPSIDSent();
	
	/**
	 * Fetch all resources.
	 *
	 * @return the list
	 */
	public List<OnboardingResourceBean> fetchAllResources();
	
	/**
	 * Fetch ops checklist.
	 *
	 * @param intervieweeId the interviewee id
	 * @return the onboarding check list ops bean
	 */
	public OnboardingCheckListOpsBean fetchOpsChecklist(long intervieweeId);
	
	/**
	 * Reboard interviewee.
	 *
	 * @param oracleID the oracle id
	 * @param onboardingResourceBean the onboarding resource bean
	 */
	public void reboardInterviewee(int oracleID,OnboardingResourceBean onboardingResourceBean);
	
		
	/**
	 * Update onboarding resource activity.
	 */
	public void updateOnboardingResourceActivity();
	
	
	/**
	 * Fetch onboarding resource by interviewee id.
	 *
	 * @param intervieweeId the interviewee id
	 * @return the onboarding resource bean
	 */
	public OnboardingResourceBean fetchOnboardingResourceByIntervieweeId(long intervieweeId);
	
	/**
	 * Excel import.
	 *
	 * @param activityType the activity type
	 * @param accountDivision the account division
	 * @param projectName the project name
	 * @return true, if successful
	 */
	public boolean excelImport(String activityType,String accountDivision,String projectName);

	/**
	 * Gets the onboarding checklist new.
	 *
	 * @return the onboarding checklist new
	 */
	List<OnboardingCheckListBean> getOnboardingChecklistNew();

	/**
	 * Fetch person staffing.
	 *
	 * @return the list
	 */
	public List<PersonStaffingBean> fetchPersonStaffing();

	/**
	 * Edits the person staffing.
	 *
	 * @param personStaffingBean the person staffing bean
	 */
	public void editPersonStaffing(PersonStaffingBean personStaffingBean);

	/**
	 * Onboard person.
	 *
	 * @param personStaffingBean the person staffing bean
	 * @param onboardingCheckListBean the onboarding check list bean
	 */
	void onboardPerson(PersonStaffingBean personStaffingBean,
			OnboardingCheckListBean onboardingCheckListBean);

	/**
	 * Save onboarding resource.
	 *
	 * @param personStaffing the person staffing
	 * @param onboardingCheckList the onboarding check list
	 */
	void saveOnboardingResource(PersonStaffing personStaffing,
			OnboardingCheckList onboardingCheckList);

	/**
	 * Fetchperson staffing by oracle id.
	 *
	 * @param personId the person id
	 * @return the person staffing bean
	 */
	public PersonStaffingBean fetchpersonStaffingByOracleId(int personId);
	public PersonStaffingBean fetchpersonStaffingByPersonId(int personId);

	/**
	 * Fetch new onboarding check list.
	 *
	 * @param leadOnboardID the lead onboard id
	 * @return the onboarding check list bean
	 */
	public OnboardingCheckListBean fetchNewOnboardingCheckList(long leadOnboardID);

	/**
	 * Send bg cdocuments to candidate.
	 *
	 * @param selectedBGC the selected bgc
	 * @param initiator the initiator
	 * @param personStaffingBean the person staffing bean
	 * @param onboardingCheckListBean the onboarding check list bean
	 */
	void sendBGCdocumentsToCandidate(int selectedBGC, int initiator,
			PersonStaffingBean personStaffingBean,
			OnboardingCheckListBean onboardingCheckListBean,PersonNewBean currentUser);

	public Boolean updatePersonClientId(PersonStaffingBean onboardingResourceBean);


	void sendPSIDAndNameToCandidate(int onboardID, String personClientId,
			String personName, PersonNewBean currentUser);

	public PersonStaffingBean fetchPersonStaffingByPersonStaffingId(Long staffingId);

	public void onBoardWithoutScreening(PersonStaffing personStaffing,
			OnboardingCheckList onboardingCheckList);
	
	public PersonStaffing fetchPersonStaffingByPersonID(int personId);

	public Person fetchPersonByPersonID(int personId);

	public void editOnboardingChecklistLead2(
			OnboardingCheckListBean onboardingCheckList);

	public OnboardingCheckList updateOnboardingCheckList(
			OnboardingCheckList onboardingCheckList);

	public void editOnboardingChecklistLead22(
			OnboardingCheckListBean onboardingCheckList);

	public List<StatusChangeLogBean> viewStatusChangeLogList();

	public OnboardingCheckList getOnboardingCheckListByStaffingId(Long personStaffingId);

	public OnboardingCheckList mergeOnboardingCheckList(OnboardingCheckList onboardingCheckList);

	public boolean removeCurrentStaffing(Long personStaffingId);

}

