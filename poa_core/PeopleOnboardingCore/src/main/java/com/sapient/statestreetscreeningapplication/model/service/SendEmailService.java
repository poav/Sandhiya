package com.sapient.statestreetscreeningapplication.model.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.ui.bean.IntervieweeBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingResourceBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonScreeningDetailsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonStaffingBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface SendEmailService.
 */
@Service
public interface SendEmailService {
	
	/**
	 * Send resubmission notification mail.
	 *
	 * @param personBean the person bean
	 * @param details the details
	 * @param comments the comments
	 * @return true, if successful
	 */
	public boolean sendResubmissionNotificationMail(PersonBean personBean,List<String> details,String comments);

	/**
	 * Send email to operations.
	 *
	 * @param person the person
	 * @param onboardingResourceBean the onboarding resource bean
	 */
	public void sendEmailToOperations(PersonBean person, OnboardingResourceBean onboardingResourceBean);
	
	/**
	 * Send psid email to candidate.
	 *
	 * @param onboardingID the onboarding id
	 */
	void sendPSIDEmailToCandidate(int onboardingID, String personClientId,
			String personName, PersonNewBean currentUser);
	/**
	 * File read.
	 *
	 * @param file the file
	 * @return the string
	 */
	public  String fileRead(File file);

	/**
	 * File subject read.
	 *
	 * @param file the file
	 * @return the string
	 */
	public  String fileSubjectRead(File file);

	/**
	 * Send email to candidate.
	 *
	 * @param candidate the candidate
	 * @param selectedBGC the selected bgc
	 * @param project the project
	 * @param initiator the initiator
	 * @param onboradingResourceBean the onborading resource bean
	 * @return true, if successful
	 */
	/*public boolean sendEmailToCandidate(PersonBean candidate, int selectedBGC,
			ProjectBean project, PersonBean initiator, OnboardingResourceBean onboradingResourceBean);*/

	/**
	 * Send onboarding termination email.
	 *
	 * @param leadOnboardID the lead onboard id
	 * @param projectEndDate the project end date
	 * @param offboardingComments the offboarding comments
	 */
	public void sendOnboardingTerminationEmail(long leadOnboardID, String projectEndDate,
			String offboardingComments,PersonNewBean currentUser);

	boolean sendEmail(PersonScreeningDetailsBean bean, String subject,
			String body);

	void sendEmail(List<PersonScreeningDetailsBean> intervieweeList,
			String receiverEmailId);

	void sendEmailToStaffing(List<PersonScreeningDetailsBean> intervieweeList,
			PersonBean interviewer);

	boolean sendEmailToCandidate(int selectedBGC, int initiator,
			PersonStaffingBean personStaffingBean,
			OnboardingCheckListBean onboardingCheckListBean,PersonNewBean currentUser);

	

	


}
