package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.TopicsDao;
import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.model.entity.Criteria;
import com.sapient.statestreetscreeningapplication.model.entity.Topics;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class TopicsDaoImpl.
 */
@Component
public class TopicsDaoImpl implements TopicsDao {

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
	 * @see com.sapient.statestreetscreeningapplication.model.dao.TopicsDao#fetchTopic(long)
	 */
	@Override
	@Transactional
	public Topics fetchTopic(long topicId) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchTopic(long topicId)  method of TopicsDaoImpl");
		return entityManager.find(Topics.class, topicId);

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.TopicsDao#fetchTopic(java.lang.String, com.sapient.statestreetscreeningapplication.model.entity.Criteria)
	 */
	@Override
	@Transactional
	public Topics fetchTopic(String topicName, Criteria criteria) {
		
		CustomLoggerUtils.INSTANCE.log.info("inside fetchTopic(String topicName, Criteria criteria)  method of TopicsDaoImpl");
		CustomLoggerUtils.INSTANCE.log.info(topicName+" "+criteria);
		String query = "from Topics where topicName = :topic and criteria= :criteria";
		TypedQuery<Topics> p = entityManager.createQuery(query, Topics.class);
		p.setParameter("topic", topicName);
		p.setParameter("criteria", criteria);
		
		Topics topicEntity = p.getSingleResult();
		
		return topicEntity;

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.TopicsDao#getCriteria(long)
	 */
	@Override
	@Transactional
	public String getCriteria(long id) {
		CustomLoggerUtils.INSTANCE.log.info("inside getCriteria(long id)  method of TopicsDaoImpl");
		Topics topic = entityManager.find(Topics.class, id);
		return topic.getCriteria().getCriteriaName();
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.TopicsDao#getTopicsList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Topics> getTopicsList(String categoryName, String criteriaName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getTopicsList(String categoryName, String criteriaName)  method of TopicsDaoImpl");
		String query = "from Category where categoryName = :categoryName";
		TypedQuery<Category> p = entityManager.createQuery(query,
				Category.class);
		p.setParameter("categoryName", categoryName);
		Category categoryEntity = p.getSingleResult();
		CustomLoggerUtils.INSTANCE.log.info("Category Name found:"
				+ categoryEntity.getCategoryName());
		query = "from Criteria where criteriaName= :criteriaName and category = :category";
		TypedQuery<Criteria> p2 = entityManager.createQuery(query,
				Criteria.class);
		p2.setParameter("criteriaName", criteriaName);
		p2.setParameter("category", categoryEntity);
		Criteria criteriaEntity = p2.getSingleResult();
		CustomLoggerUtils.INSTANCE.log.info("Criteria Name found: "
				+ criteriaEntity.getCriteriaName());
		List<Topics> topicsList;
		query = "from Topics where criteria= :criteria";
		TypedQuery<Topics> p3 = entityManager.createQuery(query, Topics.class);
		p3.setParameter("criteria", criteriaEntity);
		topicsList = p3.getResultList();
		CustomLoggerUtils.INSTANCE.log.info("List size of topics retrieved: "
				+ topicsList.size());
		return topicsList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.TopicsDao#getTopic(java.lang.String, java.lang.String, com.sapient.statestreetscreeningapplication.model.entity.Category)
	 */
	@Override
	public Topics getTopic(String topicName, String criteriaName, Category categoryEntity) {
		CustomLoggerUtils.INSTANCE.log.info("inside getTopic(String topicName, String criteriaName, Category categoryEntity)  method of TopicsDaoImpl");
		String query = "from Criteria where criteriaName= :criteriaName and category = :category";
		TypedQuery<Criteria> p2 = entityManager.createQuery(query,Criteria.class);
		p2.setParameter("criteriaName", criteriaName);
		p2.setParameter("category", categoryEntity);
		Criteria criteriaEntity = p2.getSingleResult();

		Topics topic;
		query = "from Topics where criteria= :criteria and topicName=:topicName";
		TypedQuery<Topics> p3 = entityManager.createQuery(query, Topics.class);
		p3.setParameter("criteria", criteriaEntity);
		p3.setParameter("topicName", topicName);
		topic = p3.getSingleResult();
		return topic;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.TopicsDao#saveTopic(com.sapient.statestreetscreeningapplication.model.entity.Topics)
	 */
	@Override
	@Transactional
	public int saveTopic(Topics topics) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveTopic(Topics topics)  method of TopicsDaoImpl");
		String query = "from Topics where criteria= :criteria and topicName=:topicName";
		TypedQuery<Topics> p3 = entityManager.createQuery(query, Topics.class);
		p3.setParameter("criteria", topics.getCriteria());
		p3.setParameter("topicName", topics.getTopicName());
		List<Topics> existingTopics = p3.getResultList();
		if (existingTopics.size() > 0) {
			if (existingTopics.size() == 1
					&& existingTopics.get(0).getIsUsed() == 0) {
				existingTopics.get(0).setIsUsed(1);
				return 1;
			}
			return 0;
		} else {
			topics.setIsUsed(1);
			entityManager.persist(topics);
			return 1;
		}
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.TopicsDao#changeState(long, int)
	 */
	@Override
	@Transactional
	public void changeState(long topicId, int state) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeState(long topicId, int state)  method of TopicsDaoImpl");
		Topics topic = entityManager.find(Topics.class, topicId);
		topic.setIsUsed(state);

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.TopicsDao#getAllTopics()
	 */
	@Override
	@Transactional
	public List<Topics> getAllTopics() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllTopics()  method of TopicsDaoImpl");
		String query = "from Topics";
		TypedQuery<Topics> p3 = entityManager.createQuery(query, Topics.class);
		return p3.getResultList();
	}
	
	public List<Topics> getTopicsFromCriteria(String criteriaName) {
		
		CustomLoggerUtils.INSTANCE.log.info("inside getTopicsFromCriteria");
		String query = "from Topics where criteria= :criteria";
		TypedQuery<Topics> p = entityManager.createQuery(query, Topics.class);
		p.setParameter("criteria", criteriaName);
		return p.getResultList();
	}
	
	

}
