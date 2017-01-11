package com.sapient.statestreetscreeningapplication.utils.converter;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sapient.statestreetscreeningapplication.model.entity.ScoresNew;
import com.sapient.statestreetscreeningapplication.ui.bean.ScoresNewBean;

public class ScoreNewConvertor {

	public static Set<ScoresNewBean> convertScoreListEntityToScoreListBean(
			Set<ScoresNew> entityList) {
		Set<ScoresNewBean> beanList = new HashSet<ScoresNewBean>();
		for (ScoresNew scoreEntity : entityList) {
			ScoresNewBean bean = new ScoresNewBean();
			bean.setScoreId(scoreEntity.getScoreId());
			//bean.setPersonScreeningDetails(scoreEntity.getPersonScreeningDetails());
			bean.setIsChecked(scoreEntity.getIsChecked());
			if (scoreEntity.getTopic().getTopicName() == null) {
				bean.setTopicBean(null);
			}
			else
				bean.setTopicBean(TopicConverter.convertTopicEntityToTopicBean(scoreEntity.getTopic()));
			bean.setScore(scoreEntity.getScore());
			bean.setComments(scoreEntity.getComments());
			bean.setCriteriaBean(CriteriaConverter.criteriaEntityToBeanConverter(scoreEntity.getCriteria()));
			beanList.add(bean);
		}
		return beanList;
	}

	public static Set<ScoresNew> convertScoreListBeanToScoreListEntity(
			Set<ScoresNewBean> beanList) {
		Set<ScoresNew> entityList = new HashSet<ScoresNew>();
		for (ScoresNewBean scoreBean : beanList) {
			ScoresNew entity = new ScoresNew();
			entity.setScoreId(scoreBean.getScoreId());
			entity.setPersonScreeningDetails(scoreBean.getPersonScreeningDetails());
			entity.setIsChecked(scoreBean.getIsChecked());
			if (scoreBean.getTopicBean().getTopicName() == null) {
				entity.setTopic(null);
			} else
				entity.setTopic(TopicConverter.convertTopicBeanToTopicEntity(scoreBean.getTopicBean()));
			entity.setScore(scoreBean.getScore());
			entity.setComments(scoreBean.getComments());
			entity.setCriteria(CriteriaConverter.criteriaBeanToEntityConverter(scoreBean.getCriteriaBean()));
			entityList.add(entity);
		}
		return entityList;
	}
	
	public static ScoresNew scoreBeanToEntity(ScoresNewBean bean) {
		ScoresNew entity = new ScoresNew();
		entity.setScoreId(bean.getScoreId());
		entity.setComments(bean.getComments());
		entity.setScore(bean.getScore());
		entity.setIsChecked(bean.getIsChecked());
		return entity;
	}
	
	public static ScoresNewBean scoreEntityToBean(ScoresNew entity) {
		ScoresNewBean bean = new ScoresNewBean();
		bean.setScoreId(entity.getScoreId());
		bean.setComments(entity.getComments());
		bean.setScore(entity.getScore());
		bean.setIsChecked(entity.getIsChecked());
		return bean;
	}

	
}
