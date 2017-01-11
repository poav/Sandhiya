package com.sapient.statestreetscreeningapplication.ui.bean;

// TODO: Auto-generated Javadoc
/**
 * The Class DLBean.
 */
public class DLBean {
	
	/** The dl id. */
	private long dlId;
	
	/** The dl name. */
	private String dlName;
	
	/** The location. */
	private LocationBean location;
	
	/** The project. */
	private ProjectBean project;
	
	/**
	 * Gets the dl id.
	 *
	 * @return the dl id
	 */
	public long getDlId() {
		return dlId;
	}
	
	/**
	 * Sets the dl id.
	 *
	 * @param dlId the new dl id
	 */
	public void setDlId(long dlId) {
		this.dlId = dlId;
	}
	
	/**
	 * Gets the dl name.
	 *
	 * @return the dl name
	 */
	public String getDlName() {
		return dlName;
	}
	
	/**
	 * Sets the dl name.
	 *
	 * @param dlName the new dl name
	 */
	public void setDlName(String dlName) {
		this.dlName = dlName;
	}
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public LocationBean getLocation() {
		return location;
	}
	
	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(LocationBean location) {
		this.location = location;
	}
	
	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public ProjectBean getProject() {
		return project;
	}
	
	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(ProjectBean project) {
		this.project = project;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DLBean [dlId=" + dlId + ", dlName=" + dlName + ", location="
				+ location + ", project=" + project + "]";
	}
}
