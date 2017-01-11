package com.sapient.statestreetscreeningapplication.utils.converter;

import java.util.ArrayList;
import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.Feedback;
import com.sapient.statestreetscreeningapplication.ui.bean.FeedbackBean;
// TODO: Auto-generated Javadoc

/**
 * The Class FeedbackCaptureConverter.
 */
public class FeedbackCaptureConverter {
	
	/**
	 * Convert entity to bean.
	 *
	 * @param entity the entity
	 * @return the feedback bean
	 */
	public static FeedbackBean convertEntityToBean(Feedback entity)
	{
	FeedbackBean bean=new FeedbackBean();
	bean.setFeedbackId(entity.getFeedbackId());
	bean.setOracleId(entity.getOracleId());
	bean.setSummary(entity.getSummary());
	bean.setDetails(entity.getDetails());
	bean.setStatus(entity.getStatus());
	bean.setFeedbackType(entity.getFeedbackType());
	bean.setFixedBy(entity.getFixedBy());
	bean.setFixedDate(entity.getFixedDate());
	bean.setFixedComments(entity.getFixedComments());
	bean.setTargetRelease(entity.getTargetRelease());
		
		return bean;
		
	}
	
	/**
	 * Convert bean to entity.
	 *
	 * @param bean the bean
	 * @return the feedback
	 */
	public static Feedback convertBeanToEntity(FeedbackBean bean)
	{
		Feedback entity=new Feedback();
		entity.setFeedbackId(bean.getFeedbackId());
		entity.setOracleId(bean.getOracleId());
		entity.setSummary(bean.getSummary());
		entity.setDetails(bean.getDetails());
		entity.setStatus(bean.getStatus());
		entity.setFeedbackType(bean.getFeedbackType());
		entity.setFixedBy(bean.getFixedBy());
		entity.setFixedDate(bean.getFixedDate());
		entity.setFixedComments(bean.getFixedComments());
		entity.setTargetRelease(bean.getTargetRelease());
		return entity;
		
		
	}
	
	/**
	 * Convert to bean list.
	 *
	 * @param entityList the entity list
	 * @return the list
	 */
	public static List<FeedbackBean> convertToBeanList(List<Feedback> entityList){
		List<FeedbackBean> issueBeanList=new ArrayList<FeedbackBean>();
		
	for(Feedback entity:entityList)
	{
		issueBeanList.add(convertEntityToBean(entity));
	}
	
	return issueBeanList;
}
	
}
