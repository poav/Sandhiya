package com.sapient.statestreetscreeningapplication.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEMP_PERSON")
public class TempPerson {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TEMP_PERSON_ID")
	private long tempPersonId;
	
	@Column(name = "TEMP_PERSON_NAME")
	private String tempPersonName;
	
	
	@Column(name = "TEMP_PERSON_EMAIL")
	private String tempPersonEmail;
	
	@Column(name = "TEMP_PERSON_START_DATE")
	private Date tempPersonStartDate;
	
	public long getTempPersonId() {
		return tempPersonId;
	}

	public void setTempPersonId(long tempPersonId) {
		this.tempPersonId = tempPersonId;
	}

	public String getTempPersonName() {
		return tempPersonName;
	}

	public void setTempPersonName(String tempPersonName) {
		this.tempPersonName = tempPersonName;
	}

	public String getTempPersonEmail() {
		return tempPersonEmail;
	}

	public void setTempPersonEmail(String tempPersonEmail) {
		this.tempPersonEmail = tempPersonEmail;
	}

	public Date getTempPersonStartDate() {
		return tempPersonStartDate;
	}

	public void setTempPersonStartDate(Date tempPersonStartDate) {
		this.tempPersonStartDate = tempPersonStartDate;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tempPersonEmail == null) ? 0 : tempPersonEmail.hashCode());
		result = prime * result
				+ ((tempPersonName == null) ? 0 : tempPersonName.hashCode());
		result = prime
				* result
				+ ((tempPersonStartDate == null) ? 0 : tempPersonStartDate
						.hashCode());
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
		TempPerson other = (TempPerson) obj;
		if (tempPersonEmail == null) {
			if (other.tempPersonEmail != null)
				return false;
		} else if (!tempPersonEmail.equals(other.tempPersonEmail))
			return false;
		if (tempPersonName == null) {
			if (other.tempPersonName != null)
				return false;
		} else if (!tempPersonName.equals(other.tempPersonName))
			return false;
		if (tempPersonStartDate == null) {
			if (other.tempPersonStartDate != null)
				return false;
		} else if (!tempPersonStartDate.equals(other.tempPersonStartDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TempPerson [tempPersonId=" + tempPersonId + ", tempPersonName="
				+ tempPersonName + ", tempPersonEmail=" + tempPersonEmail
				+ ", tempPersonStartDate=" + tempPersonStartDate + "]";
	}

}
