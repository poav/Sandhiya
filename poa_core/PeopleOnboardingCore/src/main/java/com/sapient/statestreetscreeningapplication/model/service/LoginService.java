package com.sapient.statestreetscreeningapplication.model.service;

import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface LoginService.
 */
public interface LoginService {
	
	/**
	 * Validate and get user.
	 *
	 * @param username the username
	 * @return the person new bean
	 */
	public PersonNewBean validateAndGetUser(String username);
}
