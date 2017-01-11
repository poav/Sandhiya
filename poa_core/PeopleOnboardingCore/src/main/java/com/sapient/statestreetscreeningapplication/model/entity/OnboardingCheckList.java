package com.sapient.statestreetscreeningapplication.model.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

// TODO: Auto-generated Javadoc
/**
 * The Class OnboardingCheckList.
 */
@Entity
@Table(name="ONBOARDING_CHECKLIST_NEW")
public class OnboardingCheckList {

/** The onboarding check list id. */
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="ONBOARDING_CHECKLIST_ID")
private Long onboardingCheckListId;

/** The person. */
@OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
@JoinColumn(name="PERSON_STAFFING_ID")
private PersonStaffing personStaffing;

/** The has previously worked for client. */
@Column(name="HAS_PREVIOUSLY_WORKED_FOR_CLIENT")
private Boolean hasPreviouslyWorkedForClient;

/** The has sapient laptop. */
@Column(name="HAS_SAPIENT_LAPTOP")
private Boolean hasSapientLaptop;

/** The is onsite. */
@Column(name="IS_ONSITE")
private Boolean isOnsite;

/** The onboarding status. */
@Column(name="ONBOARDING_STATUS")
private String onboardingStatus;

/** The date onboarding initiated. */
@Column(name="DATE_ONBOARDING_INITIATED")
private Date dateOnboardingInitiated;

/** The date background check submitted. */
@Column(name="DATE_BACKGROUND_CHECK_SUBMITTED")
private Date dateBackgroundCheckSubmitted;

/** The date person added in client vendor management system. */
@Column(name="DATE_PERSON_ADDED_IN_CLIENT_VENDOR_MGMT_SYSTEM")
private Date datePersonAddedInClientVendorManagementSystem;

/** The date background check done. */
@Column(name="DATE_BACKGROUND_CHECK_DONE")
private Date dateBackgroundCheckDone;

/** The background check status. */
@Column(name="BACKGROUND_CHECK_STATUS")
private String backgroundCheckStatus;

/** The date person approved in client vendor management system. */
@Column(name="DATE_PERSON_APPROVED_IN_CLIENT_VENDOR_MGMT_SYSTEM")
private Date datePersonApprovedInClientVendorManagementSystem;

/** The user access request id. */
@Column(name="USER_ACCESS_REQUEST_ID")
private int userAccessRequestId;

/** The user access request date. */
@Column(name="USER_ACCESS_REQUEST_DATE")
private Date userAccessRequestDate;

/** The machine name. */
@Column(name = "MACHINE_NAME")
private String machineName;

/** The machine request id. */
@Column(name="MACHINE_REQUEST_ID")
private int machineRequestId;

/** The machine request date. */
@Column(name="MACHINE_REQUEST_DATE")
private Date machineRequestDate;

/** The finger print date. */
@Column(name="FINGER_PRINT_DATE")
private Date fingerPrintDate;

/** The office access request date. */
@Column(name="OFFICE_ACCESS_REQUEST_DATE")
private Date officeAccessRequestDate;

/** The building access request date. */
@Column(name="BUILDING_ACCESS_REQUEST_DATE")
private Date buildingAccessRequestDate;

/** The time off tool request date. */
@Column(name="TIME_OFF_TOOL_REQUEST_DATE")
private Date timeOffToolRequestDate;

/** The rampup initiated date. */
@Column(name="RAMPUP_INITIATED_DATE")
private Date rampupInitiatedDate;

/** The added in dl. */
@Column(name = "ADDED_IN_DL")
private Boolean addedInDl;

/** The added in r s3. */
@Column(name = "ADDED_IN_RS3")
private Boolean addedInRS3;

@OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
@JoinColumn(name = "PERSON_ID")
private Person person;


@ManyToOne
@OnDelete(action = OnDeleteAction.CASCADE)
@JoinColumn(name = "PERSON_SCREENING_ID")
private PersonScreeningDetails personScreeningDetails;

@ManyToOne
@OnDelete(action = OnDeleteAction.CASCADE)
@JoinColumn(name = "INITIATOR_ID")
private Person initiator;


public Person getInitiator() {
	return initiator;
}

public void setInitiator(Person initiator) {
	this.initiator = initiator;
}

public Person getPerson() {
	return person;
}

public void setPerson(Person person) {
	this.person = person;
}

public PersonScreeningDetails getPersonScreeningDetails() {
	return personScreeningDetails;
}

public void setPersonScreeningDetails(
		PersonScreeningDetails personScreeningDetails) {
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
 * Gets the checks for previously worked for client.
 *
 * @return the checks for previously worked for client
 */
public Boolean getHasPreviouslyWorkedForClient() {
	return hasPreviouslyWorkedForClient;
}

/**
 * Sets the checks for previously worked for client.
 *
 * @param hasPreviouslyWorkedForClient the new checks for previously worked for client
 */
public void setHasPreviouslyWorkedForClient(Boolean hasPreviouslyWorkedForClient) {
	this.hasPreviouslyWorkedForClient = hasPreviouslyWorkedForClient;
}

/**
 * Gets the checks for sapient laptop.
 *
 * @return the checks for sapient laptop
 */
public Boolean getHasSapientLaptop() {
	return hasSapientLaptop;
}

/**
 * Sets the checks for sapient laptop.
 *
 * @param hasSapientLaptop the new checks for sapient laptop
 */
public void setHasSapientLaptop(Boolean hasSapientLaptop) {
	this.hasSapientLaptop = hasSapientLaptop;
}

/**
 * Gets the checks if is onsite.
 *
 * @return the checks if is onsite
 */
public Boolean getIsOnsite() {
	return isOnsite;
}

/**
 * Sets the checks if is onsite.
 *
 * @param isOnsite the new checks if is onsite
 */
public void setIsOnsite(Boolean isOnsite) {
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
public Date getDateOnboardingInitiated() {
	return dateOnboardingInitiated;
}

/**
 * Sets the date onboarding initiated.
 *
 * @param dateOnboardingInitiated the new date onboarding initiated
 */
public void setDateOnboardingInitiated(Date dateOnboardingInitiated) {
	this.dateOnboardingInitiated = dateOnboardingInitiated;
}

/**
 * Gets the date background check submitted.
 *
 * @return the date background check submitted
 */
public Date getDateBackgroundCheckSubmitted() {
	return dateBackgroundCheckSubmitted;
}

/**
 * Sets the date background check submitted.
 *
 * @param dateBackgroundCheckSubmitted the new date background check submitted
 */
public void setDateBackgroundCheckSubmitted(Date dateBackgroundCheckSubmitted) {
	this.dateBackgroundCheckSubmitted = dateBackgroundCheckSubmitted;
}

/**
 * Gets the date person added in client vendor management system.
 *
 * @return the date person added in client vendor management system
 */
public Date getDatePersonAddedInClientVendorManagementSystem() {
	return datePersonAddedInClientVendorManagementSystem;
}

/**
 * Sets the date person added in client vendor management system.
 *
 * @param datePersonAddedInClientVendorManagementSystem the new date person added in client vendor management system
 */
public void setDatePersonAddedInClientVendorManagementSystem(
		Date datePersonAddedInClientVendorManagementSystem) {
	this.datePersonAddedInClientVendorManagementSystem = datePersonAddedInClientVendorManagementSystem;
}

/**
 * Gets the date background check done.
 *
 * @return the date background check done
 */
public Date getDateBackgroundCheckDone() {
	return dateBackgroundCheckDone;
}

/**
 * Sets the date background check done.
 *
 * @param dateBackgroundCheckDone the new date background check done
 */
public void setDateBackgroundCheckDone(Date dateBackgroundCheckDone) {
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
public Date getDatePersonApprovedInClientVendorManagementSystem() {
	return datePersonApprovedInClientVendorManagementSystem;
}

/**
 * Sets the date person approved in client vendor management system.
 *
 * @param datePersonApprovedInClientVendorManagementSystem the new date person approved in client vendor management system
 */
public void setDatePersonApprovedInClientVendorManagementSystem(
		Date datePersonApprovedInClientVendorManagementSystem) {
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
public Date getUserAccessRequestDate() {
	return userAccessRequestDate;
}

/**
 * Sets the user access request date.
 *
 * @param userAccessRequestDate the new user access request date
 */
public void setUserAccessRequestDate(Date userAccessRequestDate) {
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
public Date getMachineRequestDate() {
	return machineRequestDate;
}

/**
 * Sets the machine request date.
 *
 * @param machineRequestDate the new machine request date
 */
public void setMachineRequestDate(Date machineRequestDate) {
	this.machineRequestDate = machineRequestDate;
}

/**
 * Gets the finger print date.
 *
 * @return the finger print date
 */
public Date getFingerPrintDate() {
	return fingerPrintDate;
}

/**
 * Sets the finger print date.
 *
 * @param fingerPrintDate the new finger print date
 */
public void setFingerPrintDate(Date fingerPrintDate) {
	this.fingerPrintDate = fingerPrintDate;
}

/**
 * Gets the office access request date.
 *
 * @return the office access request date
 */
public Date getOfficeAccessRequestDate() {
	return officeAccessRequestDate;
}

/**
 * Sets the office access request date.
 *
 * @param officeAccessRequestDate the new office access request date
 */
public void setOfficeAccessRequestDate(Date officeAccessRequestDate) {
	this.officeAccessRequestDate = officeAccessRequestDate;
}

/**
 * Gets the building access request date.
 *
 * @return the building access request date
 */
public Date getBuildingAccessRequestDate() {
	return buildingAccessRequestDate;
}

/**
 * Sets the building access request date.
 *
 * @param buildingAccessRequestDate the new building access request date
 */
public void setBuildingAccessRequestDate(Date buildingAccessRequestDate) {
	this.buildingAccessRequestDate = buildingAccessRequestDate;
}

/**
 * Gets the time off tool request date.
 *
 * @return the time off tool request date
 */
public Date getTimeOffToolRequestDate() {
	return timeOffToolRequestDate;
}

/**
 * Sets the time off tool request date.
 *
 * @param timeOffToolRequestDate the new time off tool request date
 */
public void setTimeOffToolRequestDate(Date timeOffToolRequestDate) {
	this.timeOffToolRequestDate = timeOffToolRequestDate;
}

/**
 * Gets the rampup initiated date.
 *
 * @return the rampup initiated date
 */
public Date getRampupInitiatedDate() {
	return rampupInitiatedDate;
}

/**
 * Sets the rampup initiated date.
 *
 * @param rampupInitiatedDate the new rampup initiated date
 */
public void setRampupInitiatedDate(Date rampupInitiatedDate) {
	this.rampupInitiatedDate = rampupInitiatedDate;
}

/**
 * Gets the added in dl.
 *
 * @return the added in dl
 */
public Boolean getAddedInDl() {
	return addedInDl;
}

/**
 * Sets the added in dl.
 *
 * @param addedInDl the new added in dl
 */
public void setAddedInDl(Boolean addedInDl) {
	this.addedInDl = addedInDl;
}

/**
 * Gets the added in r s3.
 *
 * @return the added in r s3
 */
public Boolean getAddedInRS3() {
	return addedInRS3;
}

/**
 * Sets the added in r s3.
 *
 * @param addedInRS3 the new added in r s3
 */
public void setAddedInRS3(Boolean addedInRS3) {
	this.addedInRS3 = addedInRS3;
}

public PersonStaffing getPersonStaffing() {
	return personStaffing;
}

public void setPersonStaffing(PersonStaffing personStaffing) {
	this.personStaffing = personStaffing;
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
}
