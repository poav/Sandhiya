package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.model.entity.Criteria;
import com.sapient.statestreetscreeningapplication.model.entity.Topics;

// TODO: Auto-generated Javadoc
/**
 * The Interface TopicsDao.
 */
@Component
public interface TopicsDao {
	
	/**
	 * Fetch topic.
	 *
	 * @param topic the topic
	 * @return the topics
	 */
	Topics fetchTopic(long topic);

	/**
	 * Gets the topics list.
	 *
	 * @param categoryName the category name
	 * @param criteriaName the criteria name
	 * @return the topics list
	 */
	List<Topics> getTopicsList(String categoryName, String criteriaName);

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
	 * @param topics the topics
	 * @return the int
	 */
	int saveTopic(Topics topics);

	/**
	 * Fetch topic.
	 *
	 * @param topicName the topic name
	 * @param criteria the criteria
	 * @return the topics
	 */
	Topics fetchTopic(String topicName, Criteria criteria);

	/**
	 * Gets the topic.
	 *
	 * @param topic the topic
	 * @param criteria the criteria
	 * @param category the category
	 * @return the topic
	 */
	Topics getTopic(String topic, String criteria, Category category);

	/**
	 * Change state.
	 *
	 * @param topicId the topic id
	 * @param state the state
	 */
	void changeState(long topicId, int state);

	/**
	 * Gets the all topics.
	 *
	 * @return the all topics
	 */
	List<Topics> getAllTopics();
	

	/**
	 * Gets the all topics from criteria.
	 *
	 * @return
	 */
	public List<Topics> getTopicsFromCriteria(String criteriaName);

}
