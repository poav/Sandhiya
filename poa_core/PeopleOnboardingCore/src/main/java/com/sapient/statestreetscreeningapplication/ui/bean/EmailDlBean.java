package com.sapient.statestreetscreeningapplication.ui.bean;



// TODO: Auto-generated Javadoc
/**
 * The Class EmailDlBean.
 */
public class EmailDlBean {
	
	/** The email dl id. */
	private Long emailDlId;
	
	/** The Email. */
	private String email;
	
	/** The project bean. */
	private ProjectNewBean projectBean;
	
	/** The location bean. */
	private LocationNewBean locationBean;
	
    private int isActive ;

	public Long getEmailDlId() {
		return emailDlId;
	}

	public void setEmailDlId(Long emailDlId) {
		this.emailDlId = emailDlId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ProjectNewBean getProjectBean() {
		return projectBean;
	}

	public void setProjectBean(ProjectNewBean projectBean) {
		this.projectBean = projectBean;
	}

	public LocationNewBean getLocationBean() {
		return locationBean;
	}

	public void setLocationBean(LocationNewBean locationBean) {
		this.locationBean = locationBean;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	

	

	
	
}
