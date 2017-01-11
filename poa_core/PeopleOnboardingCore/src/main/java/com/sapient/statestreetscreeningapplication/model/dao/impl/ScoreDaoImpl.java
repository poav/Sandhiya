package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.ScoreDao;
import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.model.entity.Criteria;
import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;
import com.sapient.statestreetscreeningapplication.model.entity.ScoresNew;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ScoreDaoImpl.
 */
@Component
public class ScoreDaoImpl implements ScoreDao{

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
	 * @see com.sapient.statestreetscreeningapplication.model.dao.ScoreDao#fetchScores(long)
	 */
	@Override
	@Transactional
	public Set<ScoresNew> fetchScores(long id) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchScores(long id)  method of ScoreDaoImpl class ");
		PersonScreeningDetails person=entityManager.find(PersonScreeningDetails.class, id);
		Set<ScoresNew> scoreList=person.getScoreList();
		return scoreList;
	}

	@Override
	public Set<ScoresNew> getScoreBean(String criteria, String categoryName,
			long personScreeningDetailsId) {
		CustomLoggerUtils.INSTANCE.log.info("inside getScoreBean(String criteria, String categoryName,long personScreeningDetailsId)  method of ScoreDaoImpl class ");
		
		String q1="from Category where categoryName=:categoryName";
		TypedQuery<Category> p1 = entityManager.createQuery(q1,
				Category.class);
		p1.setParameter("categoryName", categoryName);
		Category categoryEntity=p1.getSingleResult();
		
		
		 String query = "from Criteria where criteriaName=:criteriaName and category=:category";
		TypedQuery<Criteria> p2 = entityManager.createQuery(query,
				Criteria.class);
		p2.setParameter("criteriaName", criteria);
		p2.setParameter("category", categoryEntity);
		Criteria criteriaEntity = p2.getSingleResult();
		
		String q2 = "from ScoresNew where personScreeningDetails=:personScreeningDetails and criteria=:criteria";
		TypedQuery<ScoresNew> p3 = entityManager.createQuery(q2,
				ScoresNew.class);
		PersonScreeningDetails personScreeningDetails = entityManager.find(PersonScreeningDetails.class, personScreeningDetailsId);
		p3.setParameter("personScreeningDetails", personScreeningDetails);
		p3.setParameter("criteria", criteriaEntity);
		List<ScoresNew> scoresNewList = p3.getResultList();
		Set<ScoresNew> scoresNewSet=new HashSet<ScoresNew>();
		scoresNewSet.addAll(scoresNewList);
		return scoresNewSet;
	}

	@Override
	@Transactional
	public boolean deleteScoresRows(String criteria, String categoryName,long personScreeningDetailsId) {
		CustomLoggerUtils.INSTANCE.log.info("inside deleteScoresRows(String criteria, String categoryName,long personScreeningDetailsId)  method of ScoreDaoImpl class ");
	
		boolean deleteFlag = false;
//		String q1="from Category where categoryName=:categoryName";
//		TypedQuery<Category> p1 = entityManager.createQuery(q1,Category.class);
//		p1.setParameter("categoryName", categoryName);
//		Category categoryEntity=p1.getSingleResult();
//		
//		 String q2 = "from Criteria where criteriaName=:criteriaName and category=:category";
//		TypedQuery<Criteria> p2 = entityManager.createQuery(q2,Criteria.class);
//		p2.setParameter("criteriaName", criteria);
//		p2.setParameter("category", categoryEntity);
//		Criteria criteriaEntity = p2.getSingleResult();
//		
    	PersonScreeningDetails personScreeningDetails = entityManager.find(PersonScreeningDetails.class, personScreeningDetailsId);
//		
		

//		String q4 = "from ScoresNew where personScreeningDetails=:personScreeningDetails and criteria=:criteria";
		String q4 = "from ScoresNew where personScreeningDetails=:personScreeningDetails";
		TypedQuery<ScoresNew> p4 = entityManager.createQuery(q4,
				ScoresNew.class);
		
		p4.setParameter("personScreeningDetails", personScreeningDetails);
	//	p4.setParameter("criteria", criteriaEntity);
		List<ScoresNew> scoresNewList = p4.getResultList();
		
	
			
		for(ScoresNew sn : scoresNewList){
			deleteFlag = false ;
			ScoresNew entity = entityManager.getReference(ScoresNew.class, sn.getScoreId());
			 
			entityManager.remove(entity);
			deleteFlag = true;

		}
		
		
		
		
		
		
		
		return deleteFlag;
	}

}
