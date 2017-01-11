package com.sapient.statestreetscreeningapplication.ui.bean;

// TODO: Auto-generated Javadoc
/**
 * The Class CriteriaBean.
 */
public class CriteriaBean {

	@Override
	public String toString() {
		return "CriteriaBean [criteriaName=" + criteriaName + ", categoryBean="
				+ categoryBean + ", criteriaId=" + criteriaId + ", isUsed="
				+ isUsed + "]";
	}

	/** The criteria name. */
	private String criteriaName;
	
	/** The category bean. */
	private CategoryBean categoryBean;
	
	/** The criteria id. */
	private long criteriaId;
	
	/** The is used. */
	private int isUsed;

	/**
	 * Instantiates a new criteria bean.
	 */
	public CriteriaBean() {
		criteriaId=0;
		criteriaName = "";
		categoryBean = new CategoryBean();
	}

	/**
	 * Gets the criteria name.
	 *
	 * @return the criteria name
	 */
	public String getCriteriaName() {
		return criteriaName;
	}

	/**
	 * Sets the criteria name.
	 *
	 * @param criteriaName the new criteria name
	 */
	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
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
	 * Gets the criteria id.
	 *
	 * @return the criteria id
	 */
	public long getCriteriaId() {
		return criteriaId;
	}

	/**
	 * Sets the criteria id.
	 *
	 * @param criteriaId the new criteria id
	 */
	public void setCriteriaId(long criteriaId) {
		this.criteriaId = criteriaId;
	}

	/**
	 * Gets the checks if is used.
	 *
	 * @return the checks if is used
	 */
	public int getIsUsed() {
		return isUsed;
	}

	/**
	 * Sets the checks if is used.
	 *
	 * @param isUsed the new checks if is used
	 */
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryBean == null) ? 0 : categoryBean.hashCode());
		result = prime * result + (int) (criteriaId ^ (criteriaId >>> 32));
		result = prime * result
				+ ((criteriaName == null) ? 0 : criteriaName.hashCode());
		result = prime * result + isUsed;
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
		CriteriaBean other = (CriteriaBean) obj;
		if (categoryBean == null) {
			if (other.categoryBean != null)
				return false;
		} else if (!categoryBean.equals(other.categoryBean))
			return false;
		if (criteriaId != other.criteriaId)
			return false;
		if (criteriaName == null) {
			if (other.criteriaName != null)
				return false;
		} else if (!criteriaName.equals(other.criteriaName))
			return false;
		if (isUsed != other.isUsed)
			return false;
		return true;
	}
	

}
