package com.sapient.statestreetscreeningapplication.ui.bean;

// TODO: Auto-generated Javadoc
/**
 * The Class PositionBean.
 */
public class PositionBean {
	
	/** The position name. */
	private String positionName;
	
	/** The position id. */
	private long positionId;
	
	/** The is used. */
	private int isUsed;
	
	/** The level. */
	private String level;
	
	/** The domain. */
	private String domain;

	/**
	 * Instantiates a new position bean.
	 */
	public PositionBean() {
		positionId = 0;
		positionName = "Associate";
		isUsed = 0;
		level = "";
		domain = "";
	}

	/**
	 * Gets the position name.
	 *
	 * @return the position name
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * Sets the position name.
	 *
	 * @param positionName the new position name
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * Gets the position id.
	 *
	 * @return the position id
	 */
	public long getPositionId() {
		return positionId;
	}

	/**
	 * Sets the position id.
	 *
	 * @param positionId the new position id
	 */
	public void setPositionId(long positionId) {
		this.positionId = positionId;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * Gets the domain.
	 *
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * Sets the domain.
	 *
	 * @param domain the new domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PositionBean [positionName=" + positionName + ", positionId="
				+ positionId + ", isUsed=" + isUsed + ", level=" + level
				+ ", domain=" + domain + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result + isUsed;
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + (int) (positionId ^ (positionId >>> 32));
		result = prime * result
				+ ((positionName == null) ? 0 : positionName.hashCode());
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
		PositionBean other = (PositionBean) obj;
		if (domain == null) {
			if (other.domain != null)
				return false;
		} else if (!domain.equals(other.domain))
			return false;
		if (isUsed != other.isUsed)
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (positionId != other.positionId)
			return false;
		if (positionName == null) {
			if (other.positionName != null)
				return false;
		} else if (!positionName.equals(other.positionName))
			return false;
		return true;
	}
}
