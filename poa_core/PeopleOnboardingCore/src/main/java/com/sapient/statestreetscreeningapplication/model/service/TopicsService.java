package com.sapient.statestreetscreeningapplication.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.entity.Topics;
import com.sapient.statestreetscreeningapplication.ui.bean.TopicBean;
// TODO: Auto-generated Javadoc

/**
 * The Interface TopicsService.
 */
@Service
public interface TopicsService {

	/**
	 * Gets the topics.
	 *
	 * @param criteriaName the criteria name
	 * @param categoryName the category name
	 * @return the topics
	 */
	List<TopicBean> getTopics(String criteriaName, String categoryName);
	
	/**
	 * Gets the criteria.
	 *
	 * @param id the id
	 * @return the criteria
	 */
	String getCriteria(long id);
	
	/**
	 * Save topic.
	 *
	 * @param topicBean the topic bean
	 * @return the int
	 */
	int saveTopic(TopicBean topicBean);
	
	/**
	 * Change state.
	 *
	 * @param topicId the topic id
	 * @param state the state
	 */
	void changeState(long topicId, int state);
	
	/**
	 *Get list of Criteria
	 *
	 * @param criteriaName
	 * 
	 */
	public List<TopicBean> getTopicsFromCriteria(String criteriaName);

}
