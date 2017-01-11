package com.sapient.statestreetscreeningapplication.ui.bean;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusBean.
 */
public class StatusBean {
	
	/** The status id. */
	private long statusId;
	
	/** The status name. */
	private String statusName;
	
	/** The result name. */
	private String resultName;
	
	/** The is used. */
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
		return statusName+"|"+resultName;
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
		StatusBean other = (StatusBean) obj;
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
