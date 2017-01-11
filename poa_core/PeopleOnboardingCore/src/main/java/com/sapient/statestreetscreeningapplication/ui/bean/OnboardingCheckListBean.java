package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.Date;

import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;

/**
 * The Class OnboardingCheckListBean.
 */
public class OnboardingCheckListBean {

/** The onboarding check list id. */
private Long onboardingCheckListId;


/** The person. */
private PersonStaffingBean personStaffingBean;

public PersonStaffingBean getPersonStaffingBean() {
	return personStaffingBean;
}

public void setPersonStaffingBean(PersonStaffingBean personStaffingBean) {
	this.personStaffingBean = personStaffingBean;
}

/** The has previously worked for client. */
private boolean hasPreviouslyWorkedForClient;

private String status;

/** The has sapient laptop. */
private boolean hasSapientLaptop;


/** The is onsite. */
private String isOnsite;


/** The onboarding status. */
private String onboardingStatus;


/** The date onboarding initiated. */
private String dateOnboardingInitiated;
private Date dateOnboardingInitiatedDate;





public Date getDateOnboardingInitiatedDate() {
	return dateOnboardingInitiatedDate;
}

public void setDateOnboardingInitiatedDate(Date dateOnboardingInitiatedDate) {
	this.dateOnboardingInitiatedDate = dateOnboardingInitiatedDate;
}

public Date getBackgroundCheckSubmittedDate() {
	return backgroundCheckSubmittedDate;
}

public void setBackgroundCheckSubmittedDate(Date backgroundCheckSubmittedDate) {
	this.backgroundCheckSubmittedDate = backgroundCheckSubmittedDate;
}

public Date getPersonAddedInClientVendorManagementSystemDate() {
	return personAddedInClientVendorManagementSystemDate;
}

public void setPersonAddedInClientVendorManagementSystemDate(Date personAddedInClientVendorManagementSystemDate) {
	this.personAddedInClientVendorManagementSystemDate = personAddedInClientVendorManagementSystemDate;
}

public Date getBackgroundCheckDoneDate() {
	return backgroundCheckDoneDate;
}

public void setBackgroundCheckDoneDate(Date backgroundCheckDoneDate) {
	this.backgroundCheckDoneDate = backgroundCheckDoneDate;
}

public Date getPersonApprovedInClientVendorManagementSystemDate() {
	return personApprovedInClientVendorManagementSystemDate;
}

public void setPersonApprovedInClientVendorManagementSystemDate(Date personApprovedInClientVendorManagementSystemDate) {
	this.personApprovedInClientVendorManagementSystemDate = personApprovedInClientVendorManagementSystemDate;
}

/** The date background check submitted. */
private String dateBackgroundCheckSubmitted;
private Date backgroundCheckSubmittedDate;


/** The date person added in client vendor management system. */
private String datePersonAddedInClientVendorManagementSystem;
private Date personAddedInClientVendorManagementSystemDate;

/** The date background check done. */
private String dateBackgroundCheckDone;

private Date backgroundCheckDoneDate;



/** The background check status. */
private String backgroundCheckStatus;


/** The date person approved in client vendor management system. */
private String datePersonApprovedInClientVendorManagementSystem;
private Date personApprovedInClientVendorManagementSystemDate;

/** The user access request id. */
private int userAccessRequestId;


/** The user access request date. */
private String userAccessRequestDate;


/** The machine request id. */
private int machineRequestId;


/** The machine request date. */
private String machineRequestDate;


/** The finger print date. */
private String fingerPrintDate;


/** The office access request date. */
private String officeAccessRequestDate;


/** The building access request date. */
private String buildingAccessRequestDate;


/** The time off tool request date. */
private String timeOffToolRequestDate;


/** The rampup initiated date. */
private String rampupInitiatedDate;


/** The added in dl. */
private String addedInDl;


/** The added in r s3. */
private String addedInRS3;

/** The added in r s3. */
private float accountTenure;

/** The machine name. */
private String machineName;

private PersonNewBean person;

private PersonScreeningDetailsBean personScreeningDetails;
private PersonNewBean initiator;

public PersonNewBean getInitiator() {
	return initiator;
}

public void setInitiator(PersonNewBean initiator) {
	this.initiator = initiator;
}
public PersonNewBean getPerson() {
	return person;
}

public void setPerson(PersonNewBean person) {
	this.person = person;
}

public PersonScreeningDetailsBean getPersonScreeningDetails() {
	return personScreeningDetails;
}

public void setPersonScreeningDetails(
		PersonScreeningDetailsBean personScreeningDetails) {
	this.personScreeningDetails = personScreeningDetails;
}

/**
 * Gets the onboarding check list id.
 *
 * @return the onboarding check list id
 */
public Long getOnboardingCheckListId() {
	return onboardingCheckListId;
}

/**
 * Sets the onboarding check list id.
 *
 * @param onboardingCheckListId the new onboarding check list id
 */
public void setOnboardingCheckListId(Long onboardingCheckListId) {
	this.onboardingCheckListId = onboardingCheckListId;
}


/**
 * Gets the checks if is onsite.
 *
 * @return the checks if is onsite
 */
public String getIsOnsite() {
	return isOnsite;
}

public boolean isHasPreviouslyWorkedForClient() {
	return hasPreviouslyWorkedForClient;
}

public void setHasPreviouslyWorkedForClient(boolean hasPreviouslyWorkedForClient) {
	this.hasPreviouslyWorkedForClient = hasPreviouslyWorkedForClient;
}

public boolean isHasSapientLaptop() {
	return hasSapientLaptop;
}

public void setHasSapientLaptop(boolean hasSapientLaptop) {
	this.hasSapientLaptop = hasSapientLaptop;
}

/**
 * Sets the checks if is onsite.
 *
 * @param isOnsite the new checks if is onsite
 */
public void setIsOnsite(String isOnsite) {
	this.isOnsite = isOnsite;
}

/**
 * Gets the onboarding status.
 *
 * @return the onboarding status
 */
public String getOnboardingStatus() {
	return onboardingStatus;
}

/**
 * Sets the onboarding status.
 *
 * @param onboardingStatus the new onboarding status
 */
public void setOnboardingStatus(String onboardingStatus) {
	this.onboardingStatus = onboardingStatus;
}

/**
 * Gets the date onboarding initiated.
 *
 * @return the date onboarding initiated
 */
public String getDateOnboardingInitiated() {
	return dateOnboardingInitiated;
}

/**
 * Sets the date onboarding initiated.
 *
 * @param dateOnboardingInitiated the new date onboarding initiated
 */
public void setDateOnboardingInitiated(String dateOnboardingInitiated) {
	this.dateOnboardingInitiated = dateOnboardingInitiated;
}

/**
 * Gets the date background check submitted.
 *
 * @return the date background check submitted
 */
public String getDateBackgroundCheckSubmitted() {
	return dateBackgroundCheckSubmitted;
}

/**
 * Sets the date background check submitted.
 *
 * @param dateBackgroundCheckSubmitted the new date background check submitted
 */
public void setDateBackgroundCheckSubmitted(String dateBackgroundCheckSubmitted) {
	this.dateBackgroundCheckSubmitted = dateBackgroundCheckSubmitted;
}

/**
 * Gets the date person added in client vendor management system.
 *
 * @return the date person added in client vendor management system
 */
public String getDatePersonAddedInClientVendorManagementSystem() {
	return datePersonAddedInClientVendorManagementSystem;
}

/**
 * Sets the date person added in client vendor management system.
 *
 * @param datePersonAddedInClientVendorManagementSystem the new date person added in client vendor management system
 */
public void setDatePersonAddedInClientVendorManagementSystem(
		String datePersonAddedInClientVendorManagementSystem) {
	this.datePersonAddedInClientVendorManagementSystem = datePersonAddedInClientVendorManagementSystem;
}

/**
 * Gets the date background check done.
 *
 * @return the date background check done
 */
public String getDateBackgroundCheckDone() {
	return dateBackgroundCheckDone;
}

/**
 * Sets the date background check done.
 *
 * @param dateBackgroundCheckDone the new date background check done
 */
public void setDateBackgroundCheckDone(String dateBackgroundCheckDone) {
	this.dateBackgroundCheckDone = dateBackgroundCheckDone;
}

/**
 * Gets the background check status.
 *
 * @return the background check status
 */
public String getBackgroundCheckStatus() {
	return backgroundCheckStatus;
}

/**
 * Sets the background check status.
 *
 * @param backgroundCheckStatus the new background check status
 */
public void setBackgroundCheckStatus(String backgroundCheckStatus) {
	this.backgroundCheckStatus = backgroundCheckStatus;
}

/**
 * Gets the date person approved in client vendor management system.
 *
 * @return the date person approved in client vendor management system
 */
public String getDatePersonApprovedInClientVendorManagementSystem() {
	return datePersonApprovedInClientVendorManagementSystem;
}

/**
 * Sets the date person approved in client vendor management system.
 *
 * @param datePersonApprovedInClientVendorManagementSystem the new date person approved in client vendor management system
 */
public void setDatePersonApprovedInClientVendorManagementSystem(
		String datePersonApprovedInClientVendorManagementSystem) {
	this.datePersonApprovedInClientVendorManagementSystem = datePersonApprovedInClientVendorManagementSystem;
}

/**
 * Gets the user access request id.
 *
 * @return the user access request id
 */
public int getUserAccessRequestId() {
	return userAccessRequestId;
}

/**
 * Sets the user access request id.
 *
 * @param userAccessRequestId the new user access request id
 */
public void setUserAccessRequestId(int userAccessRequestId) {
	this.userAccessRequestId = userAccessRequestId;
}

/**
 * Gets the user access request date.
 *
 * @return the user access request date
 */
public String getUserAccessRequestDate() {
	return userAccessRequestDate;
}

/**
 * Sets the user access request date.
 *
 * @param userAccessRequestDate the new user access request date
 */
public void setUserAccessRequestDate(String userAccessRequestDate) {
	this.userAccessRequestDate = userAccessRequestDate;
}

/**
 * Gets the machine request id.
 *
 * @return the machine request id
 */
public int getMachineRequestId() {
	return machineRequestId;
}

/**
 * Sets the machine request id.
 *
 * @param machineRequestId the new machine request id
 */
public void setMachineRequestId(int machineRequestId) {
	this.machineRequestId = machineRequestId;
}

/**
 * Gets the machine request date.
 *
 * @return the machine request date
 */
public String getMachineRequestDate() {
	return machineRequestDate;
}

/**
 * Sets the machine request date.
 *
 * @param machineRequestDate the new machine request date
 */
public void setMachineRequestDate(String machineRequestDate) {
	this.machineRequestDate = machineRequestDate;
}

/**
 * Gets the finger print date.
 *
 * @return the finger print date
 */
public String getFingerPrintDate() {
	return fingerPrintDate;
}

/**
 * Sets the finger print date.
 *
 * @param fingerPrintDate the new finger print date
 */
public void setFingerPrintDate(String fingerPrintDate) {
	this.fingerPrintDate = fingerPrintDate;
}

/**
 * Gets the office access request date.
 *
 * @return the office access request date
 */
public String getOfficeAccessRequestDate() {
	return officeAccessRequestDate;
}

/**
 * Sets the office access request date.
 *
 * @param officeAccessRequestDate the new office access request date
 */
public void setOfficeAccessRequestDate(String officeAccessRequestDate) {
	this.officeAccessRequestDate = officeAccessRequestDate;
}

/**
 * Gets the building access request date.
 *
 * @return the building access request date
 */
public String getBuildingAccessRequestDate() {
	return buildingAccessRequestDate;
}

/**
 * Sets the building access request date.
 *
 * @param buildingAccessRequestDate the new building access request date
 */
public void setBuildingAccessRequestDate(String buildingAccessRequestDate) {
	this.buildingAccessRequestDate = buildingAccessRequestDate;
}

/**
 * Gets the time off tool request date.
 *
 * @return the time off tool request date
 */
public String getTimeOffToolRequestDate() {
	return timeOffToolRequestDate;
}

/**
 * Sets the time off tool request date.
 *
 * @param timeOffToolRequestDate the new time off tool request date
 */
public void setTimeOffToolRequestDate(String timeOffToolRequestDate) {
	this.timeOffToolRequestDate = timeOffToolRequestDate;
}

/**
 * Gets the rampup initiated date.
 *
 * @return the rampup initiated date
 */
public String getRampupInitiatedDate() {
	return rampupInitiatedDate;
}

/**
 * Sets the rampup initiated date.
 *
 * @param rampupInitiatedDate the new rampup initiated date
 */
public void setRampupInitiatedDate(String rampupInitiatedDate) {
	this.rampupInitiatedDate = rampupInitiatedDate;
}

/**
 * Gets the added in dl.
 *
 * @return the added in dl
 */
public String getAddedInDl() {
	return addedInDl;
}

/**
 * Sets the added in dl.
 *
 * @param addedInDl the new added in dl
 */
public void setAddedInDl(String addedInDl) {
	this.addedInDl = addedInDl;
}

/**
 * Gets the added in r s3.
 *
 * @return the added in r s3
 */
public String getAddedInRS3() {
	return addedInRS3;
}

/**
 * Sets the added in r s3.
 *
 * @param addedInRS3 the new added in r s3
 */
public void setAddedInRS3(String addedInRS3) {
	this.addedInRS3 = addedInRS3;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public float getAccountTenure() {
	return accountTenure;
}

public void setAccountTenure(float accountTenure) {
	this.accountTenure = accountTenure;
}

/**
 * Gets the machine name.
 *
 * @return the machine name
 */
public String getMachineName() {
	return machineName;
}

/**
 * Sets the machine name.
 *
 * @param machineName the new machine name
 */
public void setMachineName(String machineName) {
	this.machineName = machineName;
}

@Override
public String toString() {
	return "OnboardingCheckListBean [onboardingCheckListId="
			+ onboardingCheckListId + ", personStaffingBean="
			+ personStaffingBean + ", hasPreviouslyWorkedForClient="
			+ hasPreviouslyWorkedForClient + ", status=" + status
			+ ", hasSapientLaptop=" + hasSapientLaptop + ", isOnsite="
			+ isOnsite + ", onboardingStatus=" + onboardingStatus
			+ ", dateOnboardingInitiated=" + dateOnboardingInitiated
			+ ", dateBackgroundCheckSubmitted=" + dateBackgroundCheckSubmitted
			+ ", datePersonAddedInClientVendorManagementSystem="
			+ datePersonAddedInClientVendorManagementSystem
			+ ", dateBackgroundCheckDone=" + dateBackgroundCheckDone
			+ ", backgroundCheckStatus=" + backgroundCheckStatus
			+ ", datePersonApprovedInClientVendorManagementSystem="
			+ datePersonApprovedInClientVendorManagementSystem
			+ ", userAccessRequestId=" + userAccessRequestId
			+ ", userAccessRequestDate=" + userAccessRequestDate
			+ ", machineRequestId=" + machineRequestId
			+ ", machineRequestDate=" + machineRequestDate
			+ ", fingerPrintDate=" + fingerPrintDate
			+ ", officeAccessRequestDate=" + officeAccessRequestDate
			+ ", buildingAccessRequestDate=" + buildingAccessRequestDate
			+ ", timeOffToolRequestDate=" + timeOffToolRequestDate
			+ ", rampupInitiatedDate=" + rampupInitiatedDate + ", addedInDl="
			+ addedInDl + ", addedInRS3=" + addedInRS3 + ", accountTenure="
			+ accountTenure + "]";
}
}
