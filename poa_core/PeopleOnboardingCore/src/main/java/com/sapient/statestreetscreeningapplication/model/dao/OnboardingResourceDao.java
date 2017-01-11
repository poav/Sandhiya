package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sapient.statestreetscreeningapplication.model.entity.LocationNew;
import com.sapient.statestreetscreeningapplication.model.entity.OnboardingCheckList;
import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.entity.StatusChangeLogEntity;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonScreeningDetailsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonStaffingBean;
import com.sapient.statestreetscreeningapplication.utils.enums.PSIDStatus;
// TODO: Auto-generated Javadoc

/**
 * The Interface OnboardingResourceDao.
 */
@Component
public interface OnboardingResourceDao {
	

	
	/**
	 * Edits the onboarding checklist lead.
	 *
	 * @param onboardingLeadBean the checklist
	 */
	public void editOnboardingChecklistLead(
			OnboardingCheckListBean onboardingLeadBean);
	
	
	/**
	 * Save onboarding checklist entry.
	 *
	 * @param onboardingResourceCheckList the onboarding resource check list
	 * @return the onboarding check list
	 */
	OnboardingCheckList saveOnboardingChecklistEntry(
			OnboardingCheckList onboardingResourceCheckList);

	/**
	 * Gets the onboarding check list new.
	 *
	 * @return the onboarding check list new
	 */
	List<OnboardingCheckList> getOnboardingCheckListNew();


	/**
	 * Fetch person staffing details.
	 *
	 * @return the list
	 */
	public List<PersonStaffing> fetchPersonStaffingDetails();

	/**
	 * Edits the person staffing.
	 *
	 * @param personStaffingBean the person staffing bean
	 * @param locationNew the location new
	 */
	public void editPersonStaffing(PersonStaffingBean personStaffingBean, LocationNew locationNew);

	/**
	 * Save onboarding resource details.
	 *
	 * @param personStaffing the person staffing
	 * @param onboardingCheckList the onboarding check list
	 */
	void saveOnboardingResourceDetails(PersonStaffing personStaffing,
			OnboardingCheckList onboardingCheckList);

	/**
	 * Fetch person staffing by oracle id.
	 *
	 * @param personId the person id
	 * @return the person staffing
	 */
	public PersonStaffing fetchPersonStaffingByOracleID(int personId);

	/**
	 * Fetch new onbaording check list.
	 *
	 * @param leadOnboardID the lead onboard id
	 * @return the onboarding check list
	 */
	public OnboardingCheckList fetchNewOnbaordingCheckList(long leadOnboardID);

	List<OnboardingCheckList> fetchAllResources();

	void updatePersonClientId(PersonStaffingBean personStaffingBean);


	public PersonStaffing fetchPersonStaffingByPersonStaffingId(Long staffingId);


	public void onBoardWithoutScreening(PersonStaffing personStaffing,
			OnboardingCheckList onboardingCheckList);


	public PersonStaffing fetchPersonStaffingByPersonID(int personId);


	public OnboardingCheckList updateExistingOnboardingCheckList(
			OnboardingCheckList onboardingCheckList);


	public void editOnboardingChecklistLead2(OnboardingCheckListBean checklist);


	public void updateStatusChangeLog(StatusChangeLogEntity entity);


	public List<StatusChangeLogEntity> viewStatusChangeLogList();


	public OnboardingCheckList getOnboardingCheckListByStaffingId(Long personStaffingId);


	public OnboardingCheckList mergeOnboardingCheckList(OnboardingCheckList onboardingCheckList);


	public boolean removeCurrentStaffing(Long personStaffingId);

}
