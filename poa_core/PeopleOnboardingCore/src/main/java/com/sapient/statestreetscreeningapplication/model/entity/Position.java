package com.sapient.statestreetscreeningapplication.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Position.
 */
@Entity
@Table(name = "POSITION")
public class Position {
	
	/** The position id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "POSITION_ID")
	private Long positionId;
	
	/** The position name. */
	@Column(name = "POSITION_NAME")
	private String positionName;
	
	/** The domain. */
	@Column(name = "DOMAIN")
	private String domain;
	
	/** The level. */
	@Column(name = "LEVEL")
	private String level;
	
	/** The is used. */
	@Column(name = "IS_ACTIVE")
	private int isUsed;
	
	/**
	 * Gets the position id.
	 *
	 * @return the position id
	 */
	public Long getPositionId() {
		return positionId;
	}
	
	/**
	 * Sets the position id.
	 *
	 * @param positionId the new position id
	 */
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	
	/**
	 * Gets the domain.
	 *
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}
	
	/**
	 * Sets the domain.
	 *
	 * @param domain the new domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	
	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(String level) {
		this.level = level;
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
	
	/**
	 * Gets the position name.
	 *
	 * @return the position name
	 */
	public String getPositionName() {
		return positionName;
	}
	
	/**
	 * Sets the position name.
	 *
	 * @param positionName the new position name
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
}
