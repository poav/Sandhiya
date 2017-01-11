package com.sapient.statestreetscreeningapplication.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

// TODO: Auto-generated Javadoc
/**
 * The Class Criteria.
 */
@Entity
public class Criteria {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((criteriaName == null) ? 0 : criteriaName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Criteria other = (Criteria) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (criteriaName == null) {
			if (other.criteriaName != null)
				return false;
		} else if (!criteriaName.equals(other.criteriaName))
			return false;
		return true;
	}

	/** The criteria id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CRITERIA_ID")
	private long criteriaId;
	
	/** The criteria name. */
	@Column(name = "CRITERIA_NAME")
	private String criteriaName;
	
	/** The category. */
	@ManyToOne(cascade = CascadeType.DETACH)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	/** The is used. */
	@Column(name = "IS_USED")
	private int isUsed;
	
	//Topics within a criteria have to be unique. TopicsDao.getTopic(String topicName, String criteriaName,Category categoryEntity)  
	/** The topics list. */
	@OneToMany(mappedBy="criteria",cascade=CascadeType.ALL, orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Topics> topicsList = new HashSet<Topics>();

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
	 * Gets the category.
	 *
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Gets the topics list.
	 *
	 * @return the topics list
	 */
	public Set<Topics> getTopicsList() {
		return topicsList;
	}

	/**
	 * Sets the topics list.
	 *
	 * @param topicsList the new topics list
	 */
	public void setTopicsList(Set<Topics> topicsList) {
		this.topicsList = topicsList;
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

}
