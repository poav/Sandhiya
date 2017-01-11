package com.sapient.statestreetscreeningapplication.ui.bean;

// TODO: Auto-generated Javadoc
/**
 * The Class Attachment.
 */
public class Attachment {

	/** The data. */
	private byte[] data;
	
	/** The filename. */
	private String filename;
	
	/** The mime type. */
	private String mimeType;
	
	/** The inline. */
	private boolean inline;

	/**
	 * Instantiates a new attachment.
	 */
	public Attachment() {
	}

	/**
	 * Instantiates a new attachment.
	 *
	 * @param data the data
	 * @param filename the filename
	 * @param mimeType the mime type
	 */
	public Attachment(byte[] data, String filename, String mimeType) {
		this.data = data;
		this.filename = filename;
		this.mimeType = mimeType;
	}

	/**
	 * Instantiates a new attachment.
	 *
	 * @param data the data
	 * @param filename the filename
	 * @param mimeType the mime type
	 * @param inline the inline
	 */
	public Attachment(byte[] data, String filename, String mimeType,
			boolean inline) {
		this.data = data;
		this.filename = filename;
		this.mimeType = mimeType;
		this.inline = inline;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

	/**
	 * Gets the filename.
	 *
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the filename.
	 *
	 * @param filename the new filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
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
	 * Checks if is inline.
	 *
	 * @return true, if is inline
	 */
	public boolean isInline() {
		return inline;
	}

	/**
	 * Sets the inline.
	 *
	 * @param inline the new inline
	 */
	public void setInline(boolean inline) {
		this.inline = inline;
	}
}
