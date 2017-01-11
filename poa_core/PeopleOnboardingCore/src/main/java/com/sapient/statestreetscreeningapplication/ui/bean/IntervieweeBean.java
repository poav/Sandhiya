package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class IntervieweeBean.
 */
public class IntervieweeBean {
	
	/** The log. */
	private final Logger log = Logger
			.getLogger(this.getClass().getSimpleName());
	
	/** The interviewee oracle id. */
	private int intervieweeOracleID;
	
	/** The interviewee user name. */
	private String intervieweeUserName;
	
	/**
	 * Gets the interviewee user name.
	 *
	 * @return the interviewee user name
	 */
	public String getIntervieweeUserName() {
		return intervieweeUserName;
	}

	/**
	 * Sets the interviewee user name.
	 *
	 * @param intervieweeUserName the new interviewee user name
	 */
	public void setIntervieweeUserName(String intervieweeUserName) {
		this.intervieweeUserName = intervieweeUserName;
	}

	/** The Interviewee id. */
	private long IntervieweeId;
	
	/** The interviewer name. */
	private String interviewerName;
	
	/** The interviewee name. */
	private String intervieweeName;
	
	/** The start date. */
	private Date startDate;
	
	/** The end date. */
	private Date endDate;
	
	/** The category bean. */
	private CategoryBean categoryBean;
	
	/** The status. */
	private StatusBean status;
	
	/** The location. */
	private LocationBean location;
	
	/** The comments. */
	private String comments;
	
	/** The interviewee email id. */
	private String intervieweeEmailID;
	
	/** The project bean. */
	private ProjectBean projectBean;
	
	/** The position bean. */
	private PositionBean positionBean;
	
	/** The string start date. */
	private String stringStartDate;
	
	/** The string end date. */
	private String stringEndDate;
	
	/** The exited. */
	private boolean exited;
	
	/** The scores list. */
	private List<ScoreBean> scoresList = new ArrayList<ScoreBean>();

	/**
	 * Instantiates a new interviewee bean.
	 */
	public IntervieweeBean() {
		positionBean = new PositionBean();
		projectBean = new ProjectBean();
		status = new StatusBean();
		categoryBean = new CategoryBean();
		location = new LocationBean();
		IntervieweeId = 0;
	}

	/**
	 * Gets the string start date.
	 *
	 * @return the string start date
	 */
	public String getStringStartDate() {
		return stringStartDate;
	}

	/**
	 * Gets the string end date.
	 *
	 * @return the string end date
	 */
	public String getStringEndDate() {
		return stringEndDate;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the interviewee name.
	 *
	 * @return the interviewee name
	 */
	public String getIntervieweeName() {
		return intervieweeName;
	}

	/**
	 * Sets the interviewee name.
	 *
	 * @param intervieweeName the new interviewee name
	 */
	public void setIntervieweeName(String intervieweeName) {
		this.intervieweeName = intervieweeName;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Sets the string start date.
	 *
	 * @param stringStartDate the new string start date
	 */
	public void setStringStartDate(String stringStartDate) {
		this.stringStartDate = stringStartDate;
	}

	/**
	 * Checks if is exited.
	 *
	 * @return true, if is exited
	 */
	public boolean isExited() {
		return exited;
	}

	/**
	 * Sets the exited.
	 *
	 * @param exited the new exited
	 */
	public void setExited(boolean exited) {
		this.exited = exited;
	}

	/**
	 * Sets the string end date.
	 *
	 * @param stringEndDate the new string end date
	 */
	public void setStringEndDate(String stringEndDate) {
		this.stringEndDate = stringEndDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the category bean.
	 *
	 * @return the category bean
	 */
	public CategoryBean getCategoryBean() {
		return categoryBean;
	}

	/**
	 * Sets the category bean.
	 *
	 * @param categoryBean the new category bean
	 */
	public void setCategoryBean(CategoryBean categoryBean) {
		this.categoryBean = categoryBean;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public StatusBean getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(StatusBean status) {
		this.status = status;
	}

	/**
	 * Gets the interviewer name.
	 *
	 * @return the interviewer name
	 */
	public String getInterviewerName() {
		return interviewerName;
	}

	/**
	 * Sets the interviewer name.
	 *
	 * @param interviewerName the new interviewer name
	 */
	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	/**
	 * Gets the interviewee id.
	 *
	 * @return the interviewee id
	 */
	public long getIntervieweeId() {
		return IntervieweeId;
	}

	/**
	 * Sets the interviewee id.
	 *
	 * @param intervieweeId the new interviewee id
	 */
	public void setIntervieweeId(long intervieweeId) {
		IntervieweeId = intervieweeId;
	}

	/**
	 * Sets the interviewee id.
	 *
	 * @param intervieweeId the new interviewee id
	 */
	public void setIntervieweeId(String intervieweeId) {
		try {
			IntervieweeId = Long.valueOf(intervieweeId);
		} catch (NumberFormatException e) {
			this.IntervieweeId = 0;
			log.warn("Cannot convert from " + intervieweeId + " to "
					+ this.IntervieweeId);
		}

	}

	/**
	 * Gets the scores list.
	 *
	 * @return the scores list
	 */
	public List<ScoreBean> getScoresList() {
		return scoresList;
	}

	/**
	 * Sets the scores list.
	 *
	 * @param scoresList the new scores list
	 */
	public void setScoresList(List<ScoreBean> scoresList) {
		this.scoresList = scoresList;
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
	 * Gets the position bean.
	 *
	 * @return the position bean
	 */
	public PositionBean getPositionBean() {
		return positionBean;
	}

	/**
	 * Sets the position bean.
	 *
	 * @param positionBean the new position bean
	 */
	public void setPositionBean(PositionBean positionBean) {
		this.positionBean = positionBean;
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
	 * Gets the interviewee email id.
	 *
	 * @return the interviewee email id
	 */
	public String getIntervieweeEmailID() {
		return intervieweeEmailID;
	}

	/**
	 * Sets the interviewee email id.
	 *
	 * @param intervieweeEmailID the new interviewee email id
	 */
	public void setIntervieweeEmailID(String intervieweeEmailID) {
		this.intervieweeEmailID = intervieweeEmailID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + intervieweeOracleID;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IntervieweeBean [log=" + log + ", intervieweeOracleID="
				+ intervieweeOracleID + ", intervieweeUserName="
				+ intervieweeUserName + ", IntervieweeId=" + IntervieweeId
				+ ", interviewerName=" + interviewerName + ", intervieweeName="
				+ intervieweeName + ", startDate=" + startDate + ", endDate="
				+ endDate + ", categoryBean=" + categoryBean + ", status="
				+ status + ", location=" + location + ", comments=" + comments
				+ ", intervieweeEmailID=" + intervieweeEmailID
				+ ", projectBean=" + projectBean + ", positionBean="
				+ positionBean + ", stringStartDate=" + stringStartDate
				+ ", stringEndDate=" + stringEndDate + ", exited=" + exited
				+ ", scoresList=" + scoresList + "]";
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
		IntervieweeBean other = (IntervieweeBean) obj;
		if (intervieweeOracleID != other.intervieweeOracleID)
			return false;
		return true;
	}
}
