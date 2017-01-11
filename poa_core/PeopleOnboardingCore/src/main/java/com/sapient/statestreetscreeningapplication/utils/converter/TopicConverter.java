package com.sapient.statestreetscreeningapplication.utils.converter;

import java.util.ArrayList;
import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.Topics;
import com.sapient.statestreetscreeningapplication.ui.bean.TopicBean;

// TODO: Auto-generated Javadoc
/**
 * The Class TopicConverter.
 */
public class TopicConverter {
	
	/**
	 * Convert topic bean to topic entity.
	 *
	 * @param bean the bean
	 * @return the topics
	 */
	public static Topics convertTopicBeanToTopicEntity(TopicBean bean) {
		Topics entity = new Topics();
		entity.setTopicId(bean.getTopicId());
		entity.setTopicName(bean.getTopicName());
		if (bean.getCriteriaBean() != null) {
			entity.setCriteria(CriteriaConverter
					.criteriaBeanToEntityConverter(bean.getCriteriaBean()));
		}
		entity.setIsUsed(bean.getIsUsed());
		return entity;
	}

	/**
	 * Convert topic entity to topic bean.
	 *
	 * @param entity the entity
	 * @return the topic bean
	 */
	public static TopicBean convertTopicEntityToTopicBean(Topics entity) {
		TopicBean bean = new TopicBean();
		bean.setTopicId(entity.getTopicId());
		bean.setTopicName(entity.getTopicName());
		bean.setCriteriaBean(CriteriaConverter
				.criteriaEntityToBeanConverter(entity.getCriteria()));
		bean.setIsUsed(entity.getIsUsed());
		return bean;
	}

	/**
	 * Convert topic entity list to topic bean list.
	 *
	 * @param entityList the entity list
	 * @return the list
	 */
	public static List<TopicBean> convertTopicEntityListToTopicBeanList(
			List<Topics> entityList) {
		List<TopicBean> beanList = new ArrayList<TopicBean>();
		for (Topics entity : entityList) {
			beanList.add(convertTopicEntityToTopicBean(entity));
		}
		return beanList;
	}
}
