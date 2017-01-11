package com.sapient.statestreetscreeningapplication.model.service;

import java.util.HashMap;
import java.util.List;

import com.sapient.statestreetscreeningapplication.ui.bean.CriteriaBean;
import com.sapient.statestreetscreeningapplication.ui.bean.TopicBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface CriteriaService.
 */
public interface CriteriaService {
	// public Map<Category, List<String>> getAllCriteriass();

	// List<String> getCriterias(Category category);

	/**
	 * Gets the criteria map.
	 *
	 * @return the criteria map
	 */
	HashMap<String, List<String>> getCriteriaMap();

	/**
	 * Gets the all topics.
	 *
	 * @param criteriaID the criteria id
	 * @return the all topics
	 */
	public List<TopicBean> getAllTopics(long criteriaID);
	
	/**
	 * Save criteria.
	 *
	 * @param criteriaBean the criteria bean
	 * @return the int
	 */
	public int saveCriteria(CriteriaBean criteriaBean);

	/**
	 * Change state.
	 *
	 * @param criteriaId the criteria id
	 * @param state the state
	 * @return true, if successful
	 */
	boolean changeState(long criteriaId, int state);
}
