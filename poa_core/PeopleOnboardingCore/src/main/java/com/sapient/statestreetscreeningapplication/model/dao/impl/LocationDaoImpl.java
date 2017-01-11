package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.LocationDao;
import com.sapient.statestreetscreeningapplication.model.entity.LocationNew;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
// TODO: Auto-generated Javadoc

/**
 * The Class LocationDaoImpl.
 */
@Component
public class LocationDaoImpl implements LocationDao{
	
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
	 * @see com.sapient.statestreetscreeningapplication.model.dao.LocationDao#getLocationByName(java.lang.String)
	 */
	@Override
	@Transactional
	public LocationNew getLocationByName(String name) {
		CustomLoggerUtils.INSTANCE.log.info("inside getLocationByName(String name) method of LocationDaoImpl class");
		LocationNew location;
		String query = "from LocationNew where city=:locationName";
		TypedQuery<LocationNew> p = entityManager.createQuery(query,LocationNew.class);
		p.setParameter("locationName",name);
		
		try {
			location = p.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return location;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.LocationDao#getAllLocationByName(java.lang.String)
	 */
	@Override
	@Transactional
	public List<LocationNew> getAllLocationByName(String name) {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllLocationByName(String name) method of LocationDaoImpl class");
		List<LocationNew> locationList;
		String query = "from LocationNew where CITY like :locationName";
		CustomLoggerUtils.INSTANCE.log.info("LOCATION QUERY "+query);
		TypedQuery<LocationNew> p = entityManager.createQuery(query,
				LocationNew.class);
		p.setParameter("locationName",name + "%");
		CustomLoggerUtils.INSTANCE.log.info("LOCATION QUERY "+query +name );
		try {
			locationList = p.getResultList();
			CustomLoggerUtils.INSTANCE.log.info("location size"
					+ locationList.size());
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}	
		return locationList;
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.LocationDao#saveLocationDetails(com.sapient.statestreetscreeningapplication.model.entity.LocationNew)
	 */
	@Override
	@Transactional
	public void saveLocationDetails(LocationNew entity) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveLocationDetails(LocationNew entity) method of LocationDaoImpl class");
		// TODO Auto-generated method stub
		CustomLoggerUtils.INSTANCE.log.info("Inside saveLocationDetails");
		entity.setIsActive(1);
		Boolean isUnique = true;
		List<LocationNew> existingLocations = getAllNewLocations();
		String tempLocation = entity.getCity();
		for (LocationNew location : existingLocations) {
			if (location.getCity().equalsIgnoreCase(tempLocation)) {
				if(location.getIsActive()==1)
				{	isUnique = false;
				CustomLoggerUtils.INSTANCE.log.info("Location name is already in the database");
				}
				else{
					location.setIsActive(1);
					entityManager.merge(location);
					return;
				}
				break;
			}
		}
		if (isUnique) {
			CustomLoggerUtils.INSTANCE.log.info("Saving location");
			entityManager.merge(entity);
		}
	}
    
    /* (non-Javadoc)
     * @see com.sapient.statestreetscreeningapplication.model.dao.LocationDao#getAllNewLocations()
     */
    @Override
    @Transactional
	public List<LocationNew> getAllNewLocations() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllNewLocations() method of LocationDaoImpl class");
		String query = "from LocationNew";
		TypedQuery<LocationNew> p = entityManager.createQuery(query,
				LocationNew.class);
		return p.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.LocationDao#saveLocationBatch(java.util.List)
	 */
	@Override
	@Transactional
	public void saveLocationBatch(List<LocationNew> list) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveLocationBatch(List<LocationNew> list) method of LocationDaoImpl class");
	// TODO Auto-generated method stub
		for(LocationNew entity:list){
			saveLocationDetails(entity);
		}
		
	}


	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.LocationDao#changeState(long, int)
	 */
	@Override
	@Transactional
	public boolean changeState(long locationId, int state) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeState(long locationId, int state) method of LocationDaoImpl class");
		LocationNew location=entityManager.find(LocationNew.class, locationId);
		if(location!=null){
		location.setIsActive(state);
		return true;
		}
		return false;
	}


	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.LocationDao#getNewLocationByName(java.lang.String)
	 */
	@Override
	@Transactional
	public LocationNew getNewLocationByName(String locationName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getNewLocationByName(String locationName) method of LocationDaoImpl class");
		LocationNew location;
		String query = "from LocationNew where city=:locationName";
		TypedQuery<LocationNew> p = entityManager.createQuery(query,
				LocationNew.class);
		p.setParameter("locationName",locationName);
		
		try {
			location = p.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return location;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.LocationDao#getAllUsedLocationNames()
	 */
	@Override
	@Transactional
	public List<String> getAllUsedLocationNames() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllUsedLocationNames() method of LocationDaoImpl class");
		String query = "select city from LocationNew where isActive =:isActive";
		TypedQuery<String> p = entityManager.createQuery(query,	String.class);
		p.setParameter("isActive", 1);
		return p.getResultList();
	}

}
