package com.sapient.statestreetscreeningapplication.utils.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sapient.statestreetscreeningapplication.model.entity.EmailDl;
import com.sapient.statestreetscreeningapplication.ui.bean.EmailDlBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailDlConverter.
 */
public class EmailDlConverter {

	/**
	 * Convert email dl entity list to email dl bean list.
	 *
	 * @param completeDLList the complete dl list
	 * @return the list
	 */
	public static List<EmailDlBean> convertEmailDlEntityListToEmailDlBeanList(
			List<EmailDl> completeDLList) {
		CustomLoggerUtils.INSTANCE.log.info("convertEmailDlEntityListToEmailDlBeanList ");
		List<EmailDlBean> beanList = new ArrayList<EmailDlBean>();
		for (EmailDl entity : completeDLList) {
			beanList.add(convertEntityToBean(entity));
		}
		CustomLoggerUtils.INSTANCE.log.info("AFTER cconvertEmailDlEntityListToEmailDlBeanList ");
		return beanList;
	}

	/**
	 * Convert entity to bean.
	 *
	 * @param entity the entity
	 * @return the email dl bean
	 */
	public static EmailDlBean convertEntityToBean(EmailDl entity) {

		CustomLoggerUtils.INSTANCE.log.info("IN convertEmailDLEntityToEmailDLBean ");
		EmailDlBean bean=new EmailDlBean();
		bean.setIsActive(entity.getIsActive());
		bean.setEmailDlId(entity.getEmailDlId());
		bean.setEmail(entity.getEmail());
		bean.setLocationBean(LocationNewConverter.locationEntityToBean(entity.getLocation()));
	    bean.setProjectBean(ProjectNewConverter.convertNewProjectEntityToProjectNewBean(entity.getProject()));
		return bean;
	}

	public static Set<EmailDlBean> convertEmailDlEntitySetToEmailDlBeanSet(
			Set<EmailDl> emailDls) {
		Set<EmailDlBean> beanList = new HashSet<EmailDlBean>();
		for (EmailDl entity : emailDls) {
			beanList.add(convertEntityToBean(entity));
		}
		return beanList;
	}
	
	public static EmailDl convertBeanToEntity(EmailDlBean bean) {

		CustomLoggerUtils.INSTANCE.log.info("IN convertEmailDLEntityToEmailDLBean ");
		EmailDl entity=new EmailDl();
		entity.setIsActive(bean.getIsActive());
		entity.setEmailDlId(bean.getEmailDlId());
		entity.setEmail(bean.getEmail());
		entity.setLocation(LocationNewConverter.locationBeanToEntity(bean.getLocationBean()));
	    entity.setProject(ProjectNewConverter.convertNewProjectBeanToProjectNewEntity(bean.getProjectBean()));
		return entity;
	}
	
	public static Set<EmailDl> convertEmailDlBeanSetToEmailDlEntitySet(Set<EmailDlBean> emailDls) {
		Set<EmailDl> entityList = new HashSet<EmailDl>();
		for (EmailDlBean bean : emailDls) {
			entityList.add(convertBeanToEntity(bean));
		}
		return entityList;
	}
}
