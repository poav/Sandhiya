package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.CriteriaDao;
import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.model.entity.Criteria;
import com.sapient.statestreetscreeningapplication.model.entity.Topics;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class CriteriaDaoImpl.
 */
@Component
public class CriteriaDaoImpl implements CriteriaDao {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager the new entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.CriteriaDao#getAllCriterias()
	 */
	@Override
	public List<Criteria> getAllCriterias() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllCriterias() method of CriteriaDaoImpl class");
		List<Criteria> criteriaList;
		String query = "from Criteria";
		TypedQuery<Criteria> p = entityManager.createQuery(query,
				Criteria.class);

		criteriaList = p.getResultList();
		return criteriaList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.CriteriaDao#getCriterias(com.sapient.statestreetscreeningapplication.model.entity.Category)
	 */
	@Override
	public List<Criteria> getCriterias(Category category) {
		CustomLoggerUtils.INSTANCE.log.info("inside getCriterias(Category category) method of CriteriaDaoImpl class");
		List<Criteria> criteriaList;
		Category categoryEntity;
		String query = "from Category where categoryName=:category";
		TypedQuery<Category> p = entityManager.createQuery(query,
				Category.class);
		p.setParameter("category", category.getCategoryName());

		categoryEntity = p.getSingleResult();

		query = "from Criteria where category=:category";
		TypedQuery<Criteria> p2 = entityManager.createQuery(query,
				Criteria.class);
		p2.setParameter("category", categoryEntity);

		criteriaList = p2.getResultList();
		return criteriaList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.CriteriaDao#getAllCriterias(long)
	 */
	@Override
	public List<Topics> getAllCriterias(long criteriaID) {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllCriterias(long criteriaID) method of CriteriaDaoImpl class");
		Criteria criteria = entityManager.find(Criteria.class, criteriaID);
		CustomLoggerUtils.INSTANCE.log
				.info("CriteriaDaoImpl.getAllCriterias criteriaName:"
						+ criteria.getCriteriaName());
		Set<Topics> topicsList = criteria.getTopicsList();
		CustomLoggerUtils.INSTANCE.log.info(topicsList);
		return Arrays.asList(topicsList.toArray(new Topics[0]));
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.CriteriaDao#getCriteria(java.lang.String, com.sapient.statestreetscreeningapplication.model.entity.Category)
	 */
	@Override
	public Criteria getCriteria(String criteria, Category category) {
		CustomLoggerUtils.INSTANCE.log.info("inside getCriteria(String criteria, Category category) method of CriteriaDaoImpl class");
		Criteria criteriaEntity;
		String query = "from Criteria where criteriaName=:criteria and category=:category";
		TypedQuery<Criteria> p = entityManager.createQuery(query,
				Criteria.class);
		p.setParameter("criteria", criteria);
		p.setParameter("category", category);
		CustomLoggerUtils.INSTANCE.log.info("criteria: " + criteria);
		criteriaEntity = p.getSingleResult();
		return criteriaEntity;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.CriteriaDao#saveCriteria(com.sapient.statestreetscreeningapplication.model.entity.Criteria)
	 */
	@Override
	@Transactional
	public long saveCriteria(Criteria criteria) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveCriteria(Criteria criteria) method of CriteriaDaoImpl class");
		criteria.setIsUsed(1);
		entityManager.persist(criteria);
		return criteria.getCriteriaId();
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.CriteriaDao#changeState(long, int)
	 */
	@Override
	@Transactional
	public boolean changeState(long criteriaId, int state) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeState(long criteriaId, int state) method of CriteriaDaoImpl class");
		Criteria criteria = entityManager.find(Criteria.class, criteriaId);
		if (criteria != null) {
			criteria.setIsUsed(state);
			for (Topics topic : criteria.getTopicsList()) {
				topic.setIsUsed(state);
			}
			return true;
		}
		return false;
	}
}
