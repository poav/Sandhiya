package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectLogBean.
 */
public class ProjectLogBean {
	
	/** The log id. */
	private long logId;
	
	/** The interviewee oracle id. */
	private int intervieweeOracleID;
	
	/** The project bean. */
	private ProjectBean projectBean;
	
	/** The sape start date. */
	private Date sapeStartDate;
	
	/** The sape end date. */
	private Date sapeEndDate;
	
	/** The last log. */
	private int lastLog;
	
	/**
	 * Instantiates a new project log bean.
	 */
	public ProjectLogBean() {
		logId=0;
		projectBean=new ProjectBean();
		lastLog=0;
	}
	
	/**
	 * Gets the log id.
	 *
	 * @return the log id
	 */
	public long getLogId() {
		return logId;
	}
	
	/**
	 * Sets the log id.
	 *
	 * @param logId the new log id
	 */
	public void setLogId(long logId) {
		this.logId = logId;
	}
	
	/**
	 * Gets the interviewee oracle id.
	 *
	 * @return the interviewee oracle id
	 */
	public int getIntervieweeOracleID() {
		return intervieweeOracleID;
	}
	
	/**
	 * Sets the interviewee oracle id.
	 *
	 * @param intervieweeOracleID the new interviewee oracle id
	 */
	public void setIntervieweeOracleID(int intervieweeOracleID) {
		this.intervieweeOracleID = intervieweeOracleID;
	}
	
	/**
	 * Gets the project bean.
	 *
	 * @return the project bean
	 */
	public ProjectBean getProjectBean() {
		return projectBean;
	}
	
	/**
	 * Sets the project bean.
	 *
	 * @param projectBean the new project bean
	 */
	public void setProjectBean(ProjectBean projectBean) {
		this.projectBean = projectBean;
	}
	
	/**
	 * Gets the sape start date.
	 *
	 * @return the sape start date
	 */
	public Date getSapeStartDate() {
		return sapeStartDate;
	}
	
	/**
	 * Sets the sape start date.
	 *
	 * @param sapeStartDate the new sape start date
	 */
	public void setSapeStartDate(Date sapeStartDate) {
		this.sapeStartDate = sapeStartDate;
	}
	
	/**
	 * Gets the sape end date.
	 *
	 * @return the sape end date
	 */
	public Date getSapeEndDate() {
		return sapeEndDate;
	}
	
	/**
	 * Sets the sape end date.
	 *
	 * @param sapeEndDate the new sape end date
	 */
	public void setSapeEndDate(Date sapeEndDate) {
		this.sapeEndDate = sapeEndDate;
	}
	
	/**
	 * Gets the last log.
	 *
	 * @return the last log
	 */
	public int getLastLog() {
		return lastLog;
	}
	
	/**
	 * Sets the last log.
	 *
	 * @param lastLog the new last log
	 */
	public void setLastLog(int lastLog) {
		this.lastLog = lastLog;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProjectLogBean [logId=" + logId + ", intervieweeOracleID="
				+ intervieweeOracleID + ", projectBean=" + projectBean
				+ ", sapeStartDate=" + sapeStartDate + ", sapeEndDate="
				+ sapeEndDate + "]";
	}
}
