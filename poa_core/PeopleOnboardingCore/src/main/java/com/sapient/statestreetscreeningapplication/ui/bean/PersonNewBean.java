package com.sapient.statestreetscreeningapplication.ui.bean;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Set;

import com.sapient.statestreetscreeningapplication.model.entity.TempPerson;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonNewBean.
 */
public class PersonNewBean {
	
	/** The person id. */
	private int personId;
	
	/** The person nt id. */
	private String personNtId;
	
	private String personName;
	
	private String personEmailId;
	
	private int personOracleId;
	
	private Boolean isTemp;
	
	private Boolean isContractor;
	
	/** The birth day. */
	private int birthDay;
	
	/** The birth month. */
	private String birthMonth;
	
	/** The person client id. */
	private String personClientId;
	
	/** The supervisor id. */
	private int supervisorId;
	
	/** The supervisor nt id. */
	private String supervisorNtId;
	
	/** The machine name. */
	private String machineName;
	
	/** The is active. */
	private Boolean isActive;
	
	/** The has exited. */
	private Boolean hasExited;
	
	/** The subscribe daily mail. */
	private Boolean subscribeDailyMail;
	
	/** The subscribe immediate mail. */
	private Boolean subscribeImmediateMail;
	
	/** The role. */
	private Set<RoleBean> role = new HashSet<RoleBean>();
	
	/** The position. */
	private PositionBean position;
	
	/** The location. */
	private LocationNewBean location;
	
	/** The person details. */
	private PersonBean personDetails;
	
	/** The user role. */
	private int userRole;

	private TempPersonBean tempPersonBean;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	

	/**
	 * Gets the user role.
	 *
	 * @return the user role
	 */
	public int getUserRole() {
		return userRole;
	}

	@Override
	public String toString() {
		return "PersonNewBean [personId=" + personId + ", personNtId="
				+ personNtId + ", personName=" + personName
				+ ", personEmailId=" + personEmailId + ", personOracleId="
				+ personOracleId + ", isTemp=" + isTemp + ", birthDay="
				+ birthDay + ", birthMonth=" + birthMonth + ", personClientId="
				+ personClientId + ", supervisorId=" + supervisorId
				+ ", supervisorNtId=" + supervisorNtId + ", machineName="
				+ machineName + ", isActive=" + isActive + ", hasExited="
				+ hasExited + ", subscribeDailyMail=" + subscribeDailyMail
				+ ", subscribeImmediateMail=" + subscribeImmediateMail
				+ ", role=" + role + ", position=" + position + ", location="
				+ location + ", personDetails=" + personDetails + ", userRole="
				+ userRole + ", tempPersonBean=" + tempPersonBean + "]";
	}

	/**
	 * Sets the user role.
	 *
	 * @param userRole the new user role
	 */
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	/**
	 * Gets the person id.
	 *
	 * @return the person id
	 */
	public int getPersonId() {
		return personId;
	}

	/**
	 * Sets the person id.
	 *
	 * @param personId the new person id
	 */
	public void setPersonId(int personId) {
		this.personId = personId;
	}

	/**
	 * Gets the person nt id.
	 *
	 * @return the person nt id
	 */
	public String getPersonNtId() {
		return personNtId;
	}

	/**
	 * Sets the person nt id.
	 *
	 * @param personNtId the new person nt id
	 */
	public void setPersonNtId(String personNtId) {
		this.personNtId = personNtId;
	}

	/**
	 * Gets the birth day.
	 *
	 * @return the birth day
	 */
	public int getBirthDay() {
		return birthDay;
	}

	/**
	 * Sets the birth day.
	 *
	 * @param birthDay the new birth day
	 */
	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * Gets the birth month.
	 *
	 * @return the birth month
	 */
	public String getBirthMonth() {
		return birthMonth;
	}

	/**
	 * Sets the birth month.
	 *
	 * @param birthMonth the new birth month
	 */
	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	

	/**
	 * Gets the person client id.
	 *
	 * @return the person client id
	 */
	public String getPersonClientId() {
		return personClientId;
	}

	/**
	 * Sets the person client id.
	 *
	 * @param personClientId the new person client id
	 */
	public void setPersonClientId(String personClientId) {
		this.personClientId = personClientId;
	}

	/**
	 * Gets the supervisor id.
	 *
	 * @return the supervisor id
	 */
	public int getSupervisorId() {
		return supervisorId;
	}

	/**
	 * Sets the supervisor id.
	 *
	 * @param supervisorId the new supervisor id
	 */
	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	/**
	 * Gets the supervisor nt id.
	 *
	 * @return the supervisor nt id
	 */
	public String getSupervisorNtId() {
		return supervisorNtId;
	}

	/**
	 * Sets the supervisor nt id.
	 *
	 * @param supervisorNtId the new supervisor nt id
	 */
	public void setSupervisorNtId(String supervisorNtId) {
		this.supervisorNtId = supervisorNtId;
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

	/**
	 * Gets the checks if is active.
	 *
	 * @return the checks if is active
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * Sets the checks if is active.
	 *
	 * @param isActive the new checks if is active
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the checks for exited.
	 *
	 * @return the checks for exited
	 */
	public Boolean getHasExited() {
		return hasExited;
	}

	/**
	 * Sets the checks for exited.
	 *
	 * @param hasExited the new checks for exited
	 */
	public void setHasExited(Boolean hasExited) {
		this.hasExited = hasExited;
	}

	/**
	 * Gets the subscribe daily mail.
	 *
	 * @return the subscribe daily mail
	 */
	public Boolean getSubscribeDailyMail() {
		return subscribeDailyMail;
	}

	/**
	 * Sets the subscribe daily mail.
	 *
	 * @param subscribeDailyMail the new subscribe daily mail
	 */
	public void setSubscribeDailyMail(Boolean subscribeDailyMail) {
		this.subscribeDailyMail = subscribeDailyMail;
	}

	/**
	 * Gets the subscribe immediate mail.
	 *
	 * @return the subscribe immediate mail
	 */
	public Boolean getSubscribeImmediateMail() {
		return subscribeImmediateMail;
	}

	/**
	 * Sets the subscribe immediate mail.
	 *
	 * @param subscribeImmediateMail the new subscribe immediate mail
	 */
	public void setSubscribeImmediateMail(Boolean subscribeImmediateMail) {
		this.subscribeImmediateMail = subscribeImmediateMail;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Set<RoleBean> getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(Set<RoleBean> role) {
		this.role = role;
	}

	

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public PositionBean getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(PositionBean position) {
		this.position = position;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public LocationNewBean getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(LocationNewBean location) {
		this.location = location;
	}

	/**
	 * Gets the person details.
	 *
	 * @return the person details
	 */
	public PersonBean getPersonDetails() {
		return personDetails;
	}

	/**
	 * Sets the person details.
	 *
	 * @param personDetails the new person details
	 */
	public void setPersonDetails(PersonBean personDetails) {
		this.personDetails = personDetails;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonEmailId() {
		return personEmailId;
	}

	public void setPersonEmailId(String personEmailId) {
		this.personEmailId = personEmailId;
	}

	public int getPersonOracleId() {
		return personOracleId;
	}
	
	/*public Object getPersonOracleId() {
		return personOracleId;
	}
	
	public void setPersonOracleId(Object personOracleId) {
		this.personOracleId = (Integer)personOracleId;
	}*/
	

	public void setPersonOracleId(int personOracleId) {
		this.personOracleId = personOracleId;
	}

	public Boolean getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(Boolean isTemp) {
		this.isTemp = isTemp;
	}
	
	public TempPersonBean getTempPersonBean() {
		return tempPersonBean;
	}

	public void setTempPersonBean(TempPersonBean tempPersonBean) {
		this.tempPersonBean = tempPersonBean;
	}

	public Boolean getIsContractor() {
		return isContractor;
	}

	public void setIsContractor(Boolean isContractor) {
		this.isContractor = isContractor;
	}

	

}
