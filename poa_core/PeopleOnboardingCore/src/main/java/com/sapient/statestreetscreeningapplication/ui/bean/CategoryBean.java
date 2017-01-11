package com.sapient.statestreetscreeningapplication.ui.bean;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoryBean.
 */
public class CategoryBean {
	
	/** The category name. */
	private String categoryName;
	
	/** The category id. */
	private long categoryId;
	
	/** The is used. */
	private int isUsed;

	/**
	 * Instantiates a new category bean.
	 */
	public CategoryBean() {
		categoryName = "";
		categoryId = 0;
	}

	/**
	 * Gets the category name.
	 *
	 * @return the category name
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Sets the category name.
	 *
	 * @param categoryName the new category name
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * Gets the category id.
	 *
	 * @return the category id
	 */
	public long getCategoryId() {
		return categoryId;
	}

	/**
	 * Sets the category id.
	 *
	 * @param categoryId the new category id
	 */
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CategoryBean [categoryName=" + categoryName
				+ ", getCategoryName()=" + getCategoryName() + ", toString()="
				+ super.toString() + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (categoryId ^ (categoryId >>> 32));
		result = prime * result
				+ ((categoryName == null) ? 0 : categoryName.hashCode());
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
		CategoryBean other = (CategoryBean) obj;
		if (categoryId != other.categoryId)
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (isUsed != other.isUsed)
			return false;
		return true;
	}
}
