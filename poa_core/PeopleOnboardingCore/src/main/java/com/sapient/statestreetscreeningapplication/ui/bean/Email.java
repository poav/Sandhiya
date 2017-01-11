package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Email.
 */
public class Email {

	/** The from. */
	private String from;
	
	/** The to. */
	private String[] to;
	
	/** The cc. */
	private String[] cc;
	
	/** The bcc. */
	private String[] bcc;
	
	/** The subject. */
	private String subject;
	
	/** The text. */
	private String text;
	
	/** The mime type. */
	private String mimeType;
	
	/** The attachments. */
	private List<Attachment> attachments = new ArrayList<Attachment>();

	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Sets the from.
	 *
	 * @param from the new from
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Gets the to.
	 *
	 * @return the to
	 */
	public String[] getTo() {
		return to;
	}

	/**
	 * Sets the to.
	 *
	 * @param to the new to
	 */
	public void setTo(String... to) {
		this.to = to;
	}

	/**
	 * Gets the cc.
	 *
	 * @return the cc
	 */
	public String[] getCc() {
		return cc;
	}

	/**
	 * Sets the cc.
	 *
	 * @param cc the new cc
	 */
	public void setCc(String... cc) {
		this.cc = cc;
	}

	/**
	 * Gets the bcc.
	 *
	 * @return the bcc
	 */
	public String[] getBcc() {
		return bcc;
	}

	/**
	 * Sets the bcc.
	 *
	 * @param bcc the new bcc
	 */
	public void setBcc(String... bcc) {
		this.bcc = bcc;
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
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the mime type.
	 *
	 * @return the mime type
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * Sets the mime type.
	 *
	 * @param mimeType the new mime type
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * Gets the attachments.
	 *
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * Adds the attachments.
	 *
	 * @param attachments the attachments
	 */
	public void addAttachments(List<Attachment> attachments) {
		this.attachments.addAll(attachments);
	}

	/**
	 * Adds the attachment.
	 *
	 * @param attachment the attachment
	 */
	public void addAttachment(Attachment attachment) {
		this.attachments.add(attachment);
	}

	/**
	 * Removes the attachment.
	 *
	 * @param index the index
	 */
	public void removeAttachment(int index) {
		this.attachments.remove(index);
	}

	/**
	 * Removes the all attachments.
	 */
	public void removeAllAttachments() {
		this.attachments.clear();
	}
}
