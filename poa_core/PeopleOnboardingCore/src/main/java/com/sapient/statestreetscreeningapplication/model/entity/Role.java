package com.sapient.statestreetscreeningapplication.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

// TODO: Auto-generated Javadoc
/**
 * The Class Role.
 */
@Entity
@Table(name = "ROLE")
public class Role {
	
	/** The role id. */
	@Id
	@Column(name = "ROLE_ID")
	private int roleId;
	
	/** The role name. */
	@Column(name = "ROLE_NAME")
	private String roleName;
	/*@ManyToOne
	@JoinColumn(name="PERSON_ID")
	private Person person;*/
	/** The person. */
	@ManyToMany(mappedBy="role")
	@LazyCollection(LazyCollectionOption.FALSE)
    private Set<Person> person = new HashSet<Person>();
	
	
	/** The is active. */
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
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
	public Set<Person> getPerson() {
		return person;
	}
	
	/**
	 * Sets the person.
	 *
	 * @param person the new person
	 */
	public void setPerson(Set<Person> person) {
		this.person = person;
	}
	
	/**
	 * Sets the checks if is active.
	 *
	 * @param isActive the new checks if is active
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	/**
	 * Checks if is active.
	 *
	 * @return the boolean
	 */
	public Boolean isActive() {
		return isActive;
	}
	
}
