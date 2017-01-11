package com.sapient.statestreetscreeningapplication.utils.converter;


import com.sapient.statestreetscreeningapplication.model.entity.StatusLog;
import com.sapient.statestreetscreeningapplication.ui.bean.StatusLogBean;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusLogConvertor.
 */
public class StatusLogConvertor {

	/**
	 * Convert status log entity to status log bean.
	 *
	 * @param statusLogEntity the status log entity
	 * @return the status log bean
	 */
	public static StatusLogBean convertStatusLogEntityToStatusLogBean(StatusLog statusLogEntity) {
		StatusLogBean bean = new StatusLogBean();
		bean.setStatusLogId(statusLogEntity.getStatusLogId());
		bean.setIntervieweeOracleID(statusLogEntity.getIntervieweeOracleID());
		bean.setModifierOracleId(statusLogEntity.getModifierOracleId());
		bean.setDate(statusLogEntity.getDate());
		bean.setStatusBean(StatusConverter.convertStatusEntityToStatusBean(statusLogEntity.getStatus()));
		return bean;
	}
	
	/**
	 * Convert status log bean to status log entity.
	 *
	 * @param statusLogBean the status log bean
	 * @return the status log
	 */
	public static StatusLog convertStatusLogBeanToStatusLogEntity(StatusLogBean statusLogBean) {
		StatusLog entity = new StatusLog();
		entity.setStatusLogId(statusLogBean.getStatusLogId());
		entity.setIntervieweeOracleID(statusLogBean.getIntervieweeOracleID());
		entity.setModifierOracleId(statusLogBean.getModifierOracleId());
		entity.setDate(statusLogBean.getDate());
		entity.setStatus(StatusConverter.convertStatusBeanToStatusEntity(statusLogBean.getStatusBean()));
		return entity;
	}
}
