package com.sapient.statestreetscreeningapplication.model.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Person.
 */
@Entity
@Table(name = "PERSON")
public class Person {

	/** The person id. */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSON_ID")
	private int personId;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isTemp == null) ? 0 : isTemp.hashCode());
		result = prime * result
				+ ((personClientId == null) ? 0 : personClientId.hashCode());
		result = prime * result
				+ ((personNtId == null) ? 0 : personNtId.hashCode());
		result = prime * result + personOracleId;
		result = prime * result
				+ ((tempPerson == null) ? 0 : tempPerson.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (isTemp == null) {
			if (other.isTemp != null)
				return false;
		} else if (!isTemp.equals(other.isTemp))
			return false;
		if (personClientId == null) {
			if (other.personClientId != null)
				return false;
		} else if (!personClientId.equals(other.personClientId))
			return false;
		if (personNtId == null) {
			if (other.personNtId != null)
				return false;
		} else if (!personNtId.equals(other.personNtId))
			return false;
		if (personOracleId != other.personOracleId)
			return false;
		if (tempPerson == null) {
			if (other.tempPerson != null)
				return false;
		} else if (!tempPerson.equals(other.tempPerson))
			return false;
		return true;
	}

	@Column(name = "PERSON_ORACLE_ID")
	private int personOracleId;
	
	/** The person nt id. */
	@Column(name = "PERSON_NT_ID")
	private String personNtId;
	
	/** The birth day. */
	@Column(name = "BIRTH_DAY")
	private int birthDay;
	
	/** The birth month. */
	@Column(name = "BIRTH_MONTH")
	private String birthMonth;
	
	/** The person client id. */
	@Column(name = "PERSON_CLIENT_ID")
	private String personClientId;
	
	/** The supervisor id. */
	@Column(name = "SUPERVISOR_ID")
	private int supervisorId;
	
	/** The supervisor nt id. */
	@Column(name = "SUPERVISOR_NT_ID")
	private String supervisorNtId;
	
	/** The is active. */
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	/** The has exited. */
	@Column(name = "HAS_EXITED")
	private Boolean hasExited;
	
	@Column(name = "IS_TEMP")
	private Boolean isTemp;
	
	
	@Column(name = "IS_CONTRACTOR")
	private Boolean isContractor;
	

	public Boolean getIsContractor() {
		return isContractor;
	}

	public void setIsContractor(Boolean isContractor) {
		this.isContractor = isContractor;
	}

	public int getPersonOracleId() {
		return personOracleId;
	}

	public void setPersonOracleId(int personOracleId) {
		this.personOracleId = personOracleId;
	}

	public Boolean getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(Boolean isTemp) {
		this.isTemp = isTemp;
	}

	/** The subscribe daily mail. */
	@Column(name = "SUBSCRIBE_DAILY_MAIL")
	private Boolean subscribeDailyMail;
	
	/** The subscribe immediate mail. */
	@Column(name = "SUBSCRIBE_IMMEDIATE_MAIL")
	private Boolean subscribeImmediateMail;
	
	/*@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Role> roleList=new ArrayList<Role>();*/
	/** The role. */
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
	@JoinTable(name="PERSON_ROLE", 
			   joinColumns={@JoinColumn(name="PERSON_ID",updatable = true,nullable=true)}, 
	           inverseJoinColumns={@JoinColumn(name="ROLE_ID" ,updatable = true,nullable=true)}
			   )
	private Set<Role> role=new HashSet<Role>();
	
	@OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name="TEMP_PERSON_ID")
	private TempPerson tempPerson;
	
	public TempPerson getTempPerson() {
		return tempPerson;
	}

	public void setTempPerson(TempPerson tempPerson) {
		this.tempPerson = tempPerson;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Set<Role> getRole() {
		return role;
	}
	
	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	/** The position. */
	@ManyToOne//(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "POSITION_ID")
	private Position position;
	
	/** The location. */
	@ManyToOne//(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "LOCATION_ID")
	private LocationNew location;
	

	
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
	 * Checks if is active.
	 *
	 * @return the boolean
	 */
	public Boolean isActive() {
		return isActive;
	}
	
	/**
	 * Sets the active.
	 *
	 * @param isActive the new active
	 */
	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	/**
	 * Checks if is has exited.
	 *
	 * @return the boolean
	 */
	public Boolean isHasExited() {
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
	 * Checks if is subscribe daily mail.
	 *
	 * @return the boolean
	 */
	public Boolean isSubscribeDailyMail() {
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
	 * Checks if is subscribe immediate mail.
	 *
	 * @return the boolean
	 */
	public Boolean isSubscribeImmediateMail() {
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
	 * Gets the checks for exited.
	 *
	 * @return the checks for exited
	 */
	public Boolean getHasExited() {
		return hasExited;
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
	 * Gets the subscribe immediate mail.
	 *
	 * @return the subscribe immediate mail
	 */
	public Boolean getSubscribeImmediateMail() {
		return subscribeImmediateMail;
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
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public LocationNew getLocation() {
		return location;
	}
	
	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(LocationNew location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", personOracleId="
				+ personOracleId + ", personNtId=" + personNtId + ", birthDay="
				+ birthDay + ", birthMonth=" + birthMonth + ", personClientId="
				+ personClientId + ", supervisorId=" + supervisorId
				+ ", supervisorNtId=" + supervisorNtId + ", isActive="
				+ isActive + ", hasExited=" + hasExited + ", isTemp=" + isTemp
				+ ", subscribeDailyMail=" + subscribeDailyMail
				+ ", subscribeImmediateMail=" + subscribeImmediateMail
				+ ", role=" + role + ", tempPerson=" + tempPerson
				+ ", position=" + position + ", location=" + location + "]";
	}

	
}
