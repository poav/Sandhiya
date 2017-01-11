package com.sapient.statestreetscreeningapplication.interceptors;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionSupport;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ExceptionHandler.
 */
@Action(value = "Exceptionhandler")
public class ExceptionHandler extends ActionSupport {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The message. */
	private String message;
	
	/** The exception. */
	private Exception exception;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		setMessage("Sorry. Looks like we encountered a problem. Please contact administrator.");
		CustomLoggerUtils.INSTANCE.log.error("Exception occurred:",exception);
		return "error";
	}

	/**
	 * Sets the exception.
	 *
	 * @param exceptionHolder the new exception
	 */
	public void setException(Exception exceptionHolder) {
		this.exception = exceptionHolder;
	}

	/**
	 * Gets the exception.
	 *
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	

}
