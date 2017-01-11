package com.sapient.statestreetscreeningapplication.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.dao.RoleDao;
import com.sapient.statestreetscreeningapplication.model.service.RoleService;
import com.sapient.statestreetscreeningapplication.ui.bean.RoleBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.RoleNewConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class RoleServiceImpl.
 */
@Service
public class RoleServiceImpl implements RoleService{

/** The role dao. */
@Autowired
private RoleDao roleDao;
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.RoleService#getRoleByName(java.lang.String)
	 */
	@Override
	public RoleBean getRoleByName(String roleName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getRoleByName(String roleName) method of RoleServiceImpl class");
		RoleBean roleBean=RoleNewConverter.convertEntityToBean(roleDao.getRoleByName(roleName));
		return roleBean;
	}

}
