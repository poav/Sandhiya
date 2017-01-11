package com.sapient.statestreetscreeningapplication.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusLog.
 */
@Entity
public class StatusLog {
	
	/** The status log id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int statusLogId;
	
	/** The interviewee oracle id. */
	private int intervieweeOracleID;
	
	/** The status. */
	@ManyToOne
	private Status status;
	
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
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Status status) {
		this.status = status;
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
