package com.sapient.statestreetscreeningapplication.ui.bean;

// TODO: Auto-generated Javadoc
/**
 * The Class CacheKey.
 */
public class CacheKey {

	/** The oracle id. */
	private long oracleId;
	
	/** The person name. */
	private String personName;
	
	/** The person email. */
	private String personEmail;
	
	/** The person user name. */
	private String personUserName;

	/**
	 * Gets the person user name.
	 *
	 * @return the person user name
	 */
	public String getPersonUserName() {
		return personUserName;
	}

	/**
	 * Sets the person user name.
	 *
	 * @param personUserName the new person user name
	 */
	public void setPersonUserName(String personUserName) {
		this.personUserName = personUserName;
	}

	/**
	 * Instantiates a new cache key.
	 */
	public CacheKey() {
		oracleId = 0;
		personName = null;
		personEmail = null;
		personUserName=null;

	}

	/**
	 * Gets the oracle id.
	 *
	 * @return the oracle id
	 */
	public long getOracleId() {
		return oracleId;
	}

	/**
	 * Sets the oracle id.
	 *
	 * @param oracleId the new oracle id
	 */
	public void setOracleId(long oracleId) {
		this.oracleId = oracleId;
	}

	/**
	 * Gets the person email.
	 *
	 * @return the person email
	 */
	public String getPersonEmail() {
		return personEmail;
	}

	/**
	 * Sets the person email.
	 *
	 * @param personEmail the new person email
	 */
	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	/**
	 * Gets the person name.
	 *
	 * @return the person name
	 */
	public String getPersonName() {
		return personName;
	}

	/**
	 * Sets the person name.
	 *
	 * @param personName the new person name
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (oracleId ^ (oracleId >>> 32));
		result = prime * result
				+ ((personEmail == null) ? 0 : personEmail.hashCode());
		result = prime * result
				+ ((personName == null) ? 0 : personName.hashCode());
		return result;
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
		CacheKey other = (CacheKey) obj;
		if (oracleId == other.oracleId)
			return true;
		else if (personEmail != null) {
			String string = personEmail.toLowerCase();
			String otherString = other.personEmail.toLowerCase();
			return otherString.startsWith(string);

		} else if (personName != null) {
			String string = personName.toLowerCase();
			String otherString = other.personName.toLowerCase();
			
			return otherString.startsWith(string);
		} else
			return false;
	}
}
