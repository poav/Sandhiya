package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailConfigBean.
 */
public class EmailConfigBean {
	
	/** The content. */
	private String content;
	
	/** The name. */
	private String name;
	
	/** The subject. */
	private String subject;
	
	/** The attachments. */
	private List<String> attachments;
	
	/** The selected attachments. */
	private List<String> selectedAttachments;
	
	/**
	 * Gets the selected attachments.
	 *
	 * @return the selected attachments
	 */
	public List<String> getSelectedAttachments() {
		return selectedAttachments;
	}
	
	/**
	 * Sets the selected attachments.
	 *
	 * @param selectedAttachments the new selected attachments
	 */
	public void setSelectedAttachments(List<String> selectedAttachments) {
		this.selectedAttachments = selectedAttachments;
	}
	
	/**
	 * Gets the attachments.
	 *
	 * @return the attachments
	 */
	public List<String> getAttachments() {
		return attachments;
	}
	
	/**
	 * Sets the attachments.
	 *
	 * @param attachments the new attachments
	 */
	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}
	
	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	
	/**
	 * Sets the subject.
	 *
	 * @param subject the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
