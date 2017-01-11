package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationBean.
 */
public class LocationBean {
	
	/** The location id. */
	private long locationId;
	
	/** The location name. */
	private String locationName;
	
	/** The is used. */
	private int isUsed;
	
	/** The dl list. */
	private List<DLBean> dlList=new ArrayList<DLBean>();
	
	/**
	 * Gets the location id.
	 *
	 * @return the location id
	 */
	public long getLocationId() {
		return locationId;
	}
	
	/**
	 * Gets the dl list.
	 *
	 * @return the dl list
	 */
	public List<DLBean> getDlList() {
		return dlList;
	}
	
	/**
	 * Sets the dl list.
	 *
	 * @param dlList the new dl list
	 */
	public void setDlList(List<DLBean> dlList) {
		this.dlList = dlList;
	}
	
	/**
	 * Sets the location id.
	 *
	 * @param locationId the new location id
	 */
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	
	/**
	 * Gets the location name.
	 *
	 * @return the location name
	 */
	public String getLocationName() {
		return locationName;
	}
	
	/**
	 * Sets the location name.
	 *
	 * @param locationName the new location name
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
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
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + isUsed;
		result = prime * result + (int) (locationId ^ (locationId >>> 32));
		result = prime * result
				+ ((locationName == null) ? 0 : locationName.hashCode());
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
		LocationBean other = (LocationBean) obj;
		if (isUsed != other.isUsed)
			return false;
		if (locationId != other.locationId)
			return false;
		if (locationName == null) {
			if (other.locationName != null)
				return false;
		} else if (!locationName.equals(other.locationName))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LocationBean [locationId=" + locationId + ", locationName="
				+ locationName + ", isUsed=" + isUsed + ", dlList=" + dlList
				+ "]";
	}
}
