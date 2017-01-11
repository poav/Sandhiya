package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.HashSet;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class RoleBean.
 */
public class RoleBean {
	
	/** The role id. */
	private int roleId;
	
	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public int getRoleId() {
		return roleId;
	}
	
	/**
	 * Sets the role id.
	 *
	 * @param roleId the new role id
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	/** The role name. */
	private String roleName;
	
	/** The is active. */
	private Boolean isActive;
    
	/** The person. */
	private Set<PersonNewBean> person = new HashSet<PersonNewBean>();
	
	/**
	 * Gets the role name.
	 *
	 * @return the role name
	 */
	public String getRoleName() {
		return roleName;
	}
	
	/**
	 * Sets the role name.
	 *
	 * @param roleName the new role name
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * Gets the person.
	 *
	 * @return the person
	 */
	public Set<PersonNewBean> getPerson() {
		return person;
	}
	
	/**
	 * Sets the person.
	 *
	 * @param person the new person
	 */
	public void setPerson(Set<PersonNewBean> person) {
		this.person = person;
	}
	
	/**
	 * Gets the checks if is active.
	 *
	 * @return the checks if is active
	 */
	public Boolean getIsActive() {
		return isActive;
	}
	
	/**
	 * Sets the checks if is active.
	 *
	 * @param isActive the new checks if is active
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
}
