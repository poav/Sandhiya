package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class OnboardingCheckListLeadsBean.
 */
public class OnboardingCheckListLeadsBean {
	
	@Override
	public String toString() {
		return "OnboardingCheckListLeadsBean [onboardingLeadsCheckListId="
				+ onboardingLeadsCheckListId + ", onboardingCheckListOps="
				+ onboardingCheckListOps
				+ ", onboardingTemplateCompletedReceived="
				+ onboardingTemplateCompletedReceived
				+ ", leadInitiatesAccountRampUp=" + leadInitiatesAccountRampUp
				+ ", fingerPrintingComplete=" + fingerPrintingComplete
				+ ", UARRequested=" + UARRequested + ", machineRequested="
				+ machineRequested + ", timeOffManagementAccessRequested="
				+ timeOffManagementAccessRequested
				+ ", ssOfficeAccessRequested=" + ssOfficeAccessRequested
				+ ", ssBuildingAccessRequested=" + ssBuildingAccessRequested
				+ ", UARNumber=" + UARNumber + ", TTMachineSetup="
				+ TTMachineSetup + ", TTNumber=" + TTNumber
				+ ", dateUARRequested=" + dateUARRequested
				+ ", dateStringUARRequested=" + dateStringUARRequested + "]";
	}

	/** The onboarding leads check list id. */
	private long onboardingLeadsCheckListId;
	
	/** The onboarding check list ops. */
	private OnboardingCheckListOpsBean onboardingCheckListOps;
	
	/** The onboarding template completed received. */
	private String onboardingTemplateCompletedReceived;
	
	/** The lead initiates account ramp up. */
	private String leadInitiatesAccountRampUp;
	
	/** The finger printing complete. */
	private String fingerPrintingComplete;
	
	/** The UAR requested. */
	private String UARRequested;
	
	/** The machine requested. */
	private String machineRequested;
	
	/** The time off management access requested. */
	private String timeOffManagementAccessRequested;
	
	/** The ss office access requested. */
	private String ssOfficeAccessRequested;
	
	/** The ss building access requested. */
	private String ssBuildingAccessRequested;
	
	/** The UAR number. */
	private int UARNumber;
	
	/** The TT machine setup. */
	private String TTMachineSetup;
	
	/** The TT number. */
	private int TTNumber;
	
	/** The date uar requested. */
	private Date dateUARRequested;
	
	/** The date string uar requested. */
	private String dateStringUARRequested;
	
	/**
	 * Gets the onboarding leads check list id.
	 *
	 * @return the onboarding leads check list id
	 */
	public long getOnboardingLeadsCheckListId() {
		return onboardingLeadsCheckListId;
	}
	
	/**
	 * Sets the onboarding leads check list id.
	 *
	 * @param onboardingLeadsCheckListId the new onboarding leads check list id
	 */
	public void setOnboardingLeadsCheckListId(long onboardingLeadsCheckListId) {
		this.onboardingLeadsCheckListId = onboardingLeadsCheckListId;
	}
	
	/**
	 * Gets the onboarding check list ops.
	 *
	 * @return the onboarding check list ops
	 */
	public OnboardingCheckListOpsBean getOnboardingCheckListOps() {
		return onboardingCheckListOps;
	}
	
	/**
	 * Sets the onboarding check list ops.
	 *
	 * @param onboardingCheckListOps the new onboarding check list ops
	 */
	public void setOnboardingCheckListOps(
			OnboardingCheckListOpsBean onboardingCheckListOps) {
		this.onboardingCheckListOps = onboardingCheckListOps;
	}	
	
	/**
	 * Gets the onboarding template completed received.
	 *
	 * @return the onboarding template completed received
	 */
	public String getOnboardingTemplateCompletedReceived() {
		return onboardingTemplateCompletedReceived;
	}
	
	/**
	 * Sets the onboarding template completed received.
	 *
	 * @param onboardingTemplateCompletedReceived the new onboarding template completed received
	 */
	public void setOnboardingTemplateCompletedReceived(
			String onboardingTemplateCompletedReceived) {
		this.onboardingTemplateCompletedReceived = onboardingTemplateCompletedReceived;
	}
	
	/**
	 * Gets the lead initiates account ramp up.
	 *
	 * @return the lead initiates account ramp up
	 */
	public String getLeadInitiatesAccountRampUp() {
		return leadInitiatesAccountRampUp;
	}
	
	/**
	 * Sets the lead initiates account ramp up.
	 *
	 * @param leadInitiatesAccountRampUp the new lead initiates account ramp up
	 */
	public void setLeadInitiatesAccountRampUp(String leadInitiatesAccountRampUp) {
		this.leadInitiatesAccountRampUp = leadInitiatesAccountRampUp;
	}
	
	/**
	 * Gets the UAR requested.
	 *
	 * @return the UAR requested
	 */
	public String getUARRequested() {
		return UARRequested;
	}
	
	/**
	 * Sets the UAR requested.
	 *
	 * @param uARRequested the new UAR requested
	 */
	public void setUARRequested(String uARRequested) {
		UARRequested = uARRequested;
	}
	
	/**
	 * Gets the machine requested.
	 *
	 * @return the machine requested
	 */
	public String getMachineRequested() {
		return machineRequested;
	}
	
	/**
	 * Sets the machine requested.
	 *
	 * @param machineRequested the new machine requested
	 */
	public void setMachineRequested(String machineRequested) {
		this.machineRequested = machineRequested;
	}
	
	/**
	 * Gets the time off management access requested.
	 *
	 * @return the time off management access requested
	 */
	public String getTimeOffManagementAccessRequested() {
		return timeOffManagementAccessRequested;
	}
	
	/**
	 * Sets the time off management access requested.
	 *
	 * @param timeOffManagementAccessRequested the new time off management access requested
	 */
	public void setTimeOffManagementAccessRequested(
			String timeOffManagementAccessRequested) {
		this.timeOffManagementAccessRequested = timeOffManagementAccessRequested;
	}
	
	/**
	 * Gets the finger printing complete.
	 *
	 * @return the finger printing complete
	 */
	public String getFingerPrintingComplete() {
		return fingerPrintingComplete;
	}
	
	/**
	 * Sets the finger printing complete.
	 *
	 * @param fingerPrintingComplete the new finger printing complete
	 */
	public void setFingerPrintingComplete(String fingerPrintingComplete) {
		this.fingerPrintingComplete = fingerPrintingComplete;
	}
	
	/**
	 * Gets the ss office access requested.
	 *
	 * @return the ss office access requested
	 */
	public String getSsOfficeAccessRequested() {
		return ssOfficeAccessRequested;
	}
	
	/**
	 * Sets the ss office access requested.
	 *
	 * @param ssOfficeAccessRequested the new ss office access requested
	 */
	public void setSsOfficeAccessRequested(String ssOfficeAccessRequested) {
		this.ssOfficeAccessRequested = ssOfficeAccessRequested;
	}
	
	/**
	 * Gets the ss building access requested.
	 *
	 * @return the ss building access requested
	 */
	public String getSsBuildingAccessRequested() {
		return ssBuildingAccessRequested;
	}
	
	/**
	 * Sets the ss building access requested.
	 *
	 * @param ssBuildingAccessRequested the new ss building access requested
	 */
	public void setSsBuildingAccessRequested(String ssBuildingAccessRequested) {
		this.ssBuildingAccessRequested = ssBuildingAccessRequested;
	}
	
	/**
	 * Gets the UAR number.
	 *
	 * @return the UAR number
	 */
	public int getUARNumber() {
		return UARNumber;
	}
	
	/**
	 * Sets the UAR number.
	 *
	 * @param uARNumber the new UAR number
	 */
	public void setUARNumber(int uARNumber) {
		UARNumber = uARNumber;
	}
	
	/**
	 * Gets the TT machine setup.
	 *
	 * @return the TT machine setup
	 */
	public String getTTMachineSetup() {
		return TTMachineSetup;
	}
	
	/**
	 * Sets the TT machine setup.
	 *
	 * @param tTMachineSetup the new TT machine setup
	 */
	public void setTTMachineSetup(String tTMachineSetup) {
		TTMachineSetup = tTMachineSetup;
	}
	
	/**
	 * Gets the date uar requested.
	 *
	 * @return the date uar requested
	 */
	public Date getDateUARRequested() {
		return dateUARRequested;
	}
	
	/**
	 * Sets the date uar requested.
	 *
	 * @param dateUARRequested the new date uar requested
	 */
	public void setDateUARRequested(Date dateUARRequested) {
		this.dateUARRequested = dateUARRequested;
	}
	
	/**
	 * Gets the date string uar requested.
	 *
	 * @return the date string uar requested
	 */
	public String getDateStringUARRequested() {
		return dateStringUARRequested;
	}
	
	/**
	 * Sets the date string uar requested.
	 *
	 * @param dateStringUARRequested the new date string uar requested
	 */
	public void setDateStringUARRequested(String dateStringUARRequested) {
		this.dateStringUARRequested = dateStringUARRequested;
	}
	
	/**
	 * Gets the TT number.
	 *
	 * @return the TT number
	 */
	public int getTTNumber() {
		return TTNumber;
	}
	
	/**
	 * Sets the TT number.
	 *
	 * @param tTNumber the new TT number
	 */
	public void setTTNumber(int tTNumber) {
		TTNumber = tTNumber;
	}	
}
