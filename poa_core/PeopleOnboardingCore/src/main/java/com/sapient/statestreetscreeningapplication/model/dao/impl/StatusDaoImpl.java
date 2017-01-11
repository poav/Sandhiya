package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.StatusDao;
import com.sapient.statestreetscreeningapplication.model.entity.Status;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusDaoImpl.
 */
@Component
public class StatusDaoImpl implements StatusDao {
	
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
	 * @see com.sapient.statestreetscreeningapplication.model.dao.StatusDao#getAllStatus()
	 */
	@Override
	@Transactional
	public List<Status> getAllStatus() {
		String query = "from Status";
		TypedQuery<Status> p = entityManager.createQuery(query, Status.class);
		return p.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.StatusDao#getStatusByName(java.lang.String)
	 */
	@Override
	@Transactional
	public Status getStatusByName(String name) {
		CustomLoggerUtils.INSTANCE.log.info("inside getStatusByName(String name)  method of StatusDaoImpl ");
		List<Status> statusList;
		String query = "from Status where statusName=:statusName";
		TypedQuery<Status> p = entityManager.createQuery(query, Status.class);
		p.setParameter("statusName", name);
		statusList = p.getResultList();
		if (!statusList.isEmpty()) {
			return statusList.get(0);
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.StatusDao#getStatusByNames(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public Status getStatusByNames(String statusName,String resultName){
		CustomLoggerUtils.INSTANCE.log.info("inside getStatusByNames(String statusName,String resultName)  method of StatusDaoImpl ");
		Status status=null;
		String query = "from Status where statusName=:statusName and resultName=:resultName";
		TypedQuery<Status> p = entityManager.createQuery(query, Status.class);
		p.setParameter("statusName", statusName);
		p.setParameter("resultName", resultName);
		try
		{
		status = p.getSingleResult();
		}
		catch(NoResultException e)
		{
			CustomLoggerUtils.INSTANCE.log.warn("No result found for status: "+statusName+" and result: "+resultName);
		}
		
			return status;
		
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.StatusDao#saveStatusDetails(com.sapient.statestreetscreeningapplication.model.entity.Status)
	 */
	@Override
	@Transactional
	public void saveStatusDetails(Status entity) {
		CustomLoggerUtils.INSTANCE.log.info("inside  saveStatusDetails(Status entity) method of StatusDaoImpl ");
		Status status=null;
		String query = "from Status where statusName=:statusName and resultName=:resultName";
		TypedQuery<Status> p = entityManager.createQuery(query, Status.class);
		p.setParameter("statusName", entity.getStatusName());
		p.setParameter("resultName", "NA");
		try {
			status = p.getSingleResult();
		} catch (NoResultException e) {
			CustomLoggerUtils.INSTANCE.log.info("The status has results.");
		}
		if(status!=null){
			status.setIsUsed(0);
		}
		entity.setIsUsed(1);
		entityManager.merge(entity);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.StatusDao#saveStatusBatch(java.util.List)
	 */
	@Override
	@Transactional
	public void saveStatusBatch(List<Status> list) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveStatusBatch(List<Status> list)  method of StatusDaoImpl ");
		for (Status entity : list) {
			saveStatusDetails(entity);
		}
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.StatusDao#getAllUsedStatus()
	 */
	@Override
	public List<Status> getAllUsedStatus() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllUsedStatus()  method of StatusDaoImpl ");
		String query = "from Status where isUsed=:Used";
		TypedQuery<Status> p = entityManager.createQuery(query, Status.class);
		p.setParameter("Used", 1);
		return p.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.StatusDao#changeState(long, int)
	 */
	@Override
	@Transactional
	public boolean changeState(long statusId, int state) {
		CustomLoggerUtils.INSTANCE.log.info("inside  changeState(long statusId, int state) method of StatusDaoImpl ");
		Status status = entityManager.find(Status.class, statusId);
		if(status!=null){
		if(state==0){
		List<Status> existingStatuses=null;
		String query = "from Status where statusName=:statusName and isUsed=:Used";
		TypedQuery<Status> p = entityManager.createQuery(query, Status.class);
		p.setParameter("statusName", status.getStatusName());
		p.setParameter("Used", 1);
		try {
			existingStatuses = p.getResultList();
		} catch (NoResultException e) {
			
			CustomLoggerUtils.INSTANCE.log.info("The status has results.");
		}
		if(existingStatuses.size()==1){
			Status defaultStatus=null;
			String query1 = "from Status where statusName=:statusName and resultName=:default";
			TypedQuery<Status> p1 = entityManager.createQuery(query1, Status.class);
			p1.setParameter("statusName", status.getStatusName());
			p1.setParameter("default", "NA");
			try {
			defaultStatus=p1.getSingleResult();
			} catch (NoResultException e) {
				CustomLoggerUtils.INSTANCE.log.info("The status has no default results.");
			}
			if (defaultStatus!=null) {
				defaultStatus.setIsUsed(1);
			}else{
				defaultStatus=new Status();
				defaultStatus.setIsUsed(1);
				defaultStatus.setResultName("NA");
				defaultStatus.setStatusName(status.getStatusName());
				entityManager.persist(defaultStatus);
			}
		}
		}else{
			String query2 = "from Status where statusName=:statusName and resultName=:default";
			TypedQuery<Status> p2 = entityManager.createQuery(query2, Status.class);
			p2.setParameter("statusName", status.getStatusName());
			p2.setParameter("default", "NA");
			Status defaultStatus=p2.getSingleResult();
			defaultStatus.setIsUsed(0);
		}
		status.setIsUsed(state);
		return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.StatusDao#getStatusBeanOnResultName(java.lang.String)
	 */
	@Override
	@Transactional
	public Status getStatusBeanOnResultName(String resultName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getStatusBeanOnResultName(String resultName)  method of StatusDaoImpl ");
		Status status=null;
		String query = "from Status where resultName=:resultName";
		TypedQuery<Status> p = entityManager.createQuery(query, Status.class);
		p.setParameter("resultName", resultName);
		try {
			status = p.getSingleResult();
		} catch (NoResultException e) {
			CustomLoggerUtils.INSTANCE.log.info("The status has results.");
		}
		return status;
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.StatusDao#getStatusBeanOnStatusAndResultName(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public Status getStatusBeanOnStatusAndResultName(String statusName, String resultName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getStatusBeanOnStatusAndResultName(String statusName, String resultName)  method of StatusDaoImpl "+statusName+" "+resultName);
		Status status=null;
		if(statusName.equals("NEW")){
			resultName = "NA";
		}
		String query = "from Status where resultName=:resultName and statusName=:statusName";
		TypedQuery<Status> p = entityManager.createQuery(query, Status.class);
		p.setParameter("resultName", resultName);
		p.setParameter("statusName", statusName);
		try {
			status = p.getSingleResult();
		} catch (NoResultException e) {
			CustomLoggerUtils.INSTANCE.log.info("The status has results.");
		}
		return status;
	}
}
