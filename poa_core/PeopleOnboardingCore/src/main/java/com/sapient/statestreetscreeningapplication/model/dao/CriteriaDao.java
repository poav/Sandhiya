package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.model.entity.Criteria;
import com.sapient.statestreetscreeningapplication.model.entity.Topics;

// TODO: Auto-generated Javadoc
/**
 * The Interface CriteriaDao.
 */
@Component
public interface CriteriaDao {

/**
 * Gets the all criterias.
 *
 * @return the all criterias
 */
public List<Criteria> getAllCriterias();



/**
 * Gets the criterias.
 *
 * @param category the category
 * @return the criterias
 */
List<Criteria> getCriterias(Category category);

/**
 * Gets the all topic of criterias.
 *
 * @param criteriaID the criteria id
 * @return the all criterias
 */
public List<Topics> getAllCriterias(long criteriaID);

/**
 * Gets the criteria.
 *
 * @param criteria the criteria
 * @param category the category
 * @return the criteria
 */
public Criteria getCriteria(String criteria, Category category);

/**
 * Save criteria.
 *
 * @param criteria the criteria
 * @return the long
 */
public long saveCriteria(Criteria criteria);

/**
 * Change state.
 *
 * @param criteriaId the criteria id
 * @param state the state
 * @return true, if successful
 */
public boolean changeState(long criteriaId, int state);
}
