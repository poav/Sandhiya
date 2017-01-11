package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusLogBean.
 */
public class StatusLogBean {

	/** The status log id. */
	private int statusLogId;
	
	/** The interviewee oracle id. */
	private int intervieweeOracleID;
	
	/** The status bean. */
	private StatusBean statusBean;
	
	/** The date. */
	private Date date;
	
	/** The modifier oracle id. */
	private int modifierOracleId;
	
	/**
	 * Gets the status log id.
	 *
	 * @return the status log id
	 */
	public int getStatusLogId() {
		return statusLogId;
	}
	
	/**
	 * Sets the status log id.
	 *
	 * @param statusLogId the new status log id
	 */
	public void setStatusLogId(int statusLogId) {
		this.statusLogId = statusLogId;
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
	 * Gets the status bean.
	 *
	 * @return the status bean
	 */
	public StatusBean getStatusBean() {
		return statusBean;
	}
	
	/**
	 * Sets the status bean.
	 *
	 * @param statusBean the new status bean
	 */
	public void setStatusBean(StatusBean statusBean) {
		this.statusBean = statusBean;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Gets the modifier oracle id.
	 *
	 * @return the modifier oracle id
	 */
	public int getModifierOracleId() {
		return modifierOracleId;
	}
	
	/**
	 * Sets the modifier oracle id.
	 *
	 * @param modifierOracleId the new modifier oracle id
	 */
	public void setModifierOracleId(int modifierOracleId) {
		this.modifierOracleId = modifierOracleId;
	}	
}
