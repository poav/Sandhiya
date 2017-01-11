package com.sapient.statestreetscreeningapplication.utils.converter;

import java.util.ArrayList;
import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.Status;
import com.sapient.statestreetscreeningapplication.ui.bean.StatusBean;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusConverter.
 */
public class StatusConverter {
	
	/**
	 * Convert status bean to status entity.
	 *
	 * @param bean the bean
	 * @return the status
	 */
	public static Status convertStatusBeanToStatusEntity(StatusBean bean) {
		Status entity = new Status();
		entity.setStatusId(bean.getStatusId());
		entity.setStatusName(bean.getStatusName());
		entity.setIsUsed(bean.getIsUsed());
		entity.setResultName(bean.getResultName());
		return entity;
	}

	/**
	 * Convert status entity to status bean.
	 *
	 * @param entity the entity
	 * @return the status bean
	 */
	public static StatusBean convertStatusEntityToStatusBean(Status entity) {
		StatusBean bean = new StatusBean();
		bean.setStatusId(entity.getStatusId());
		bean.setStatusName(entity.getStatusName());
		bean.setIsUsed(entity.getIsUsed());
		bean.setResultName(entity.getResultName());
		return bean;

	}

	/**
	 * Convert status entity list to status bean list.
	 *
	 * @param entityList the entity list
	 * @return the list
	 */
	public static List<StatusBean> convertStatusEntityListToStatusBeanList(
			List<Status> entityList) {
		List<StatusBean> beanList = new ArrayList<StatusBean>();
		for (Status entity : entityList) {
			beanList.add(convertStatusEntityToStatusBean(entity));
		}
		return beanList;

	}
}
