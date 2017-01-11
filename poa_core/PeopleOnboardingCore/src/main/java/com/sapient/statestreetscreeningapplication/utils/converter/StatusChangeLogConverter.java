package com.sapient.statestreetscreeningapplication.utils.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.StatusChangeLogEntity;
import com.sapient.statestreetscreeningapplication.ui.bean.StatusChangeLogBean;

public class StatusChangeLogConverter {
	
	public static StatusChangeLogBean StatusChangeLogEntityToBean(StatusChangeLogEntity entity){
		SimpleDateFormat formatter ;
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		StatusChangeLogBean bean = new StatusChangeLogBean();
		bean.setStatusChangeLogId(entity.getStatusChangeLogId());
		bean.setCandidatePSD(PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(entity.getCandidatePSD()));
		bean.setStatusChangedFrom(StatusConverter.convertStatusEntityToStatusBean(entity.getStatusChangedFrom()));
		bean.setStatusChangedTo(StatusConverter.convertStatusEntityToStatusBean(entity.getStatusChangedTo()));
		bean.setStatusChangeDate(formatter.format(entity.getStatusChangeDate()));
		bean.setOperator(PersonNewConverter.personNewEntityToBean(entity.getOperator()));
		return bean;
	}
	
	public static StatusChangeLogEntity StatusChangeLogBeanToEntity(StatusChangeLogBean bean){
		SimpleDateFormat formatter ;
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		StatusChangeLogEntity entity = new StatusChangeLogEntity();
		entity.setStatusChangeLogId(bean.getStatusChangeLogId());
		entity.setCandidatePSD(PersonScreeningDetailsConvertor.PersonScreeningDetailsBeanToPersonScreeningDetailsEntity(bean.getCandidatePSD()));
		entity.setStatusChangedFrom(StatusConverter.convertStatusBeanToStatusEntity(bean.getStatusChangedFrom()));
		entity.setStatusChangedTo(StatusConverter.convertStatusBeanToStatusEntity(bean.getStatusChangedTo()));
		try {
			entity.setStatusChangeDate(formatter.parse(bean.getStatusChangeDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		entity.setOperator(PersonNewConverter.personBeanToEntity(bean.getOperator()));
		return entity;
	}
	
	public static List<StatusChangeLogBean> StatusChangeLogEntityToBeanList(List<StatusChangeLogEntity> entityList){
		List<StatusChangeLogBean> beanList = new ArrayList<StatusChangeLogBean>();
		for(StatusChangeLogEntity entity : entityList){
		SimpleDateFormat formatter ;
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		StatusChangeLogBean bean = new StatusChangeLogBean();
		bean.setStatusChangeLogId(entity.getStatusChangeLogId());
		bean.setCandidatePSD(PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(entity.getCandidatePSD()));
		bean.setStatusChangedFrom(StatusConverter.convertStatusEntityToStatusBean(entity.getStatusChangedFrom()));
		bean.setStatusChangedTo(StatusConverter.convertStatusEntityToStatusBean(entity.getStatusChangedTo()));
		bean.setStatusChangeDate(formatter.format(entity.getStatusChangeDate()));
		bean.setOperator(PersonNewConverter.personNewEntityToBean(entity.getOperator()));
		beanList.add(bean);
		}
		return beanList;
	}
	
}
