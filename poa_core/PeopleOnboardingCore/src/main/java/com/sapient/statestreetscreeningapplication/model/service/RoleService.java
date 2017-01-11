package com.sapient.statestreetscreeningapplication.model.service;

import com.sapient.statestreetscreeningapplication.ui.bean.RoleBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface RoleService.
 */
public interface RoleService {
	
	/**
	 * Gets the role by name.
	 *
	 * @param roleName the role name
	 * @return the role by name
	 */
	RoleBean getRoleByName(String roleName);
}
