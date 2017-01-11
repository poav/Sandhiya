package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.PositionDao;
import com.sapient.statestreetscreeningapplication.model.entity.Position;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class PositionDaoImpl.
 */
@Component
public class PositionDaoImpl implements PositionDao {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PositionDao#getPosition(java.lang.String)
	 */
	@Override
	public Position getPosition(String positionName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getPosition(String positionName)  method of PositionDaoImpl class " );

		Position position;
		String query = "from Position where positionName=:positionName";
		TypedQuery<Position> p = entityManager.createQuery(query,
				Position.class);
		p.setParameter("positionName", positionName);
		try {
			position = p.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return position;
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PositionDao#getPositionByNameDomainLevel(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Position getPositionByNameDomainLevel(String positionName, String domainName, String levelName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getPositionByNameDomainLevel(String positionName, String domainName, String levelName)  method of PositionDaoImpl class " );
		Position position;
		String query = "from Position where positionName=:positionName and domain =:domainName and level=:levelName";
		TypedQuery<Position> p = entityManager.createQuery(query,
				Position.class);
		p.setParameter("positionName", positionName);
		p.setParameter("domainName", domainName);
		p.setParameter("levelName", levelName);
		try {
			position = p.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return position;
	}

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
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PositionDao#getAllPositions()
	 */
	@Override
	public List<Position> getAllPositions() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllPositions()  method of PositionDaoImpl class " );
		List<Position> positionList;
		String query = "from Position ORDER BY positionName asc";
		TypedQuery<Position> p = entityManager.createQuery(query,
				Position.class);
		try {

			positionList = p.getResultList();
			CustomLoggerUtils.INSTANCE.log.info("position size"
					+ positionList.size());
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return positionList;
	}	
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PositionDao#getAllPositionsByName(java.lang.String)
	 */
	@Override
	public List<Position> getAllPositionsByName(String positionName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllPositionsByName(String positionName)  method of PositionDaoImpl class " );
		List<Position> positionList;
		String query = "from Position where POSITION_NAME like :positionName";
		CustomLoggerUtils.INSTANCE.log.info("POSITION QUERY "+query);
		TypedQuery<Position> p = entityManager.createQuery(query,
				Position.class);
		p.setParameter("positionName", positionName + "%");
		CustomLoggerUtils.INSTANCE.log.info("POSITION QUERY "+query);
		try {

			positionList = p.getResultList();
			CustomLoggerUtils.INSTANCE.log.info("position size"
					+ positionList.size());
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}		
		return positionList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PositionDao#savePositionDetails(com.sapient.statestreetscreeningapplication.model.entity.Position)
	 */
	@Override
	@Transactional
	public void savePositionDetails(Position entity) {
		CustomLoggerUtils.INSTANCE.log.info("inside savePositionDetails(Position entity)  method of PositionDaoImpl class " );
		entity.setIsUsed(1);
		
		entityManager.merge(entity);

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PositionDao#savePositionBatch(java.util.List)
	 */
	@Override
	@Transactional
	public void savePositionBatch(List<Position> list) {
		CustomLoggerUtils.INSTANCE.log.info("inside savePositionBatch(List<Position> list)  method of PositionDaoImpl class " );
		for (Position entity : list) {
			savePositionDetails(entity);
		}
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PositionDao#changeState(long, int)
	 */
	@Override
	@Transactional
	public boolean changeState(long positionId, int state) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeState(long positionId, int state)  method of PositionDaoImpl class " );
		Position position = entityManager.find(Position.class, positionId);
		if (position != null) {
			position.setIsUsed(state);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PositionDao#getAllUsedPositions()
	 */
	@Override
	public List<Position> getAllUsedPositions() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllUsedPositions()  method of PositionDaoImpl class " );
		List<Position> positionList;
		String query = "from Position where isUsed=:Used ORDER BY positionName asc";
		TypedQuery<Position> p = entityManager.createQuery(query,Position.class);
		p.setParameter("Used", 1);
		try {
			positionList = p.getResultList();
			CustomLoggerUtils.INSTANCE.log.info("position size"+ positionList.size());
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return positionList;
	}

	@Override
	public List<String> getAllUsedPositionsByName() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllUsedPositionsByName()  method of PositionDaoImpl class " );
		List<Position> positionList;
		String query = "from Position where isUsed=:Used ORDER BY positionName asc";
		TypedQuery<Position> p = entityManager.createQuery(query,Position.class);
		p.setParameter("Used", 1);
		try {
			positionList = p.getResultList();
			CustomLoggerUtils.INSTANCE.log.info("position size"+ positionList.size());
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		List<String> positionNameList=new ArrayList<String>();
		for(Position pos:positionList){
			positionNameList.add(pos.getPositionName());
		}
		return positionNameList;
	}
	@Override
	public List<Position> getAllUsedPositionByDomain(String domainName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllUsedPositionsByDomain: "+domainName );
		List<Position> positionsList;
		String query="from Position where DOMAIN like :domainName AND isUsed=:Used ORDER BY positionName asc";
		TypedQuery<Position> p = entityManager.createQuery(query,Position.class);
		p.setParameter("domainName",domainName);
		p.setParameter("Used", 1);
		CustomLoggerUtils.INSTANCE.log.info("POSITION QUERY "+query);
		try {

			positionsList = p.getResultList();
			CustomLoggerUtils.INSTANCE.log.info("position size"
					+ positionsList.size());
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}		
		return positionsList;
		
	}
}
