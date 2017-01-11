package com.sapient.statestreetscreeningapplication.model.dao;

import com.sapient.statestreetscreeningapplication.model.entity.Role;

// TODO: Auto-generated Javadoc
/**
 * The Interface RoleDao.
 */
public interface RoleDao {

/**
 * Gets the role by name.
 *
 * @param roleName the role name
 * @return the role by name
 */
public Role getRoleByName(String roleName);
}
