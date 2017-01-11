package com.sapient.statestreetscreeningapplication.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Status.
 */
@Entity
@Table(name = "STATUS")
public class Status {
	
	/** The status id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="STATUS_ID")
	private long statusId;
	
	/** The status name. */
	@Column(name ="STATUS_NAME")
	private String statusName;
	
	/** The result name. */
	@Column(name ="RESULT_NAME")
	private String resultName;
	
	/** The is used. */
	@Column(name ="IS_USED")
	private int isUsed;
	
	/**
	 * Gets the status id.
	 *
	 * @return the status id
	 */
	public long getStatusId() {
		return statusId;
	}
	
	/**
	 * Sets the status id.
	 *
	 * @param statusId the new status id
	 */
	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}
	
	/**
	 * Gets the status name.
	 *
	 * @return the status name
	 */
	public String getStatusName() {
		return statusName;
	}
	
	/**
	 * Sets the status name.
	 *
	 * @param statusName the new status name
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	/**
	 * Gets the checks if is used.
	 *
	 * @return the checks if is used
	 */
	public int getIsUsed() {
		return isUsed;
	}
	
	/**
	 * Sets the checks if is used.
	 *
	 * @param isUsed the new checks if is used
	 */
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	
	/**
	 * Gets the result name.
	 *
	 * @return the result name
	 */
	public String getResultName() {
		return resultName;
	}
	
	/**
	 * Sets the result name.
	 *
	 * @param resultName the new result name
	 */
	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Status [statusName=" + statusName + ", resultName="
				+ resultName + "]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + isUsed;
		result = prime * result
				+ ((resultName == null) ? 0 : resultName.hashCode());
		result = prime * result + (int) (statusId ^ (statusId >>> 32));
		result = prime * result
				+ ((statusName == null) ? 0 : statusName.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		if (isUsed != other.isUsed)
			return false;
		if (resultName == null) {
			if (other.resultName != null)
				return false;
		} else if (!resultName.equals(other.resultName))
			return false;
		if (statusId != other.statusId)
			return false;
		if (statusName == null) {
			if (other.statusName != null)
				return false;
		} else if (!statusName.equals(other.statusName))
			return false;
		return true;
	}
	
	
	
}
