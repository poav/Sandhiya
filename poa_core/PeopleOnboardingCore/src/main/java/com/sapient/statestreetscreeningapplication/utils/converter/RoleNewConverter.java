package com.sapient.statestreetscreeningapplication.utils.converter;

import java.util.HashSet;
import java.util.Set;

import com.sapient.statestreetscreeningapplication.model.entity.Role;
import com.sapient.statestreetscreeningapplication.ui.bean.RoleBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class RoleNewConverter.
 */
public class RoleNewConverter {

	/**
	 * Role entity to bean.
	 *
	 * @param role the role
	 * @return the sets the
	 */
	public static Set<RoleBean> roleEntityToBean(Set<Role> role) {
		CustomLoggerUtils.INSTANCE.log.info("Inside roleEntityToBean method in RoleNewConverter");
		Set<RoleBean> roleBeanSet=new HashSet<RoleBean>();
		for (Role roleEntity : role) {
			roleBeanSet.add(convertEntityToBean(roleEntity));
		}
		CustomLoggerUtils.INSTANCE.log.info("Returing the following set from roleEntityToBean method in RoleNewConverter"+roleBeanSet);
return roleBeanSet;
	}

	/**
	 * Convert entity to bean.
	 *
	 * @param role the role
	 * @return the role bean
	 */
	public static RoleBean convertEntityToBean(Role role) {
		RoleBean roleBean=new RoleBean();
		roleBean.setIsActive(role.isActive());
		roleBean.setRoleId(role.getRoleId());
		roleBean.setRoleName(role.getRoleName());
		return roleBean;
	}

	/**
	 * Role bean to entity set.
	 *
	 * @param role the role
	 * @return the sets the
	 */
	public static Set<Role> roleBeanToEntitySet(Set<RoleBean> role) {
		CustomLoggerUtils.INSTANCE.log.info("Inside roleBeanToEntity method in RoleNewConverter");
		Set<Role> roleSet=new HashSet<Role>();
		for (RoleBean roleBean : role) {
			roleSet.add(convertBeanToEntity(roleBean));
		}
		CustomLoggerUtils.INSTANCE.log.info("Returing the following set from roleBeanToEntity method in RoleNewConverter"+roleSet);
return roleSet;
		
	}

	/**
	 * Convert bean to entity.
	 *
	 * @param roleBean the role bean
	 * @return the role
	 */
	public static Role convertBeanToEntity(RoleBean roleBean) {
		Role role=new Role();
		role.setIsActive(roleBean.getIsActive());
		role.setRoleId(roleBean.getRoleId());
		role.setRoleName(roleBean.getRoleName());
		return role;
	}

}
