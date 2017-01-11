package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.DLDao;
import com.sapient.statestreetscreeningapplication.model.entity.EmailDl;
import com.sapient.statestreetscreeningapplication.model.entity.LocationNew;
import com.sapient.statestreetscreeningapplication.model.entity.ProjectNew;
import com.sapient.statestreetscreeningapplication.ui.bean.EmailDlBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class DLDaoImpl.
 */
@Component
public class DLDaoImpl implements DLDao{

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	/** The dl entity. */
	/*private DL dlEntity;*/

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
	
	/**
	 * Gets the dl entity.
	 *
	 * @return the dl entity
	 */

	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.DLDao#getAllDLs()
	 */

	@Override
    public List<String> getAllDLs() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllDLs() method of DLDaoImpl class");

            List<EmailDl> dlList;

            List<String> dlNames=new ArrayList<String>();

            String query = "from EmailDl ";

            TypedQuery<EmailDl> p = entityManager.createQuery(query,EmailDl.class);

            dlList = p.getResultList();

            for(EmailDl dl:dlList){

             dlNames.add(dl.getEmail());

            }

            return dlNames;

    }


	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.DLDao#getCompleteDLList()
	 */
	@Override
	public List<EmailDl> getCompleteDLList() {
		CustomLoggerUtils.INSTANCE.log.info("inside getCompleteDLList() method of DLDaoImpl class");
		List<EmailDl> dlList;
		String query = "from EmailDl";
		TypedQuery<EmailDl> p = entityManager.createQuery(query,EmailDl.class);
		dlList = p.getResultList();
		CustomLoggerUtils.INSTANCE.log.info("In ViewDLDAO "+dlList.toString());
		return dlList;
	}

	

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.DLDao#getDlByProjectIdAndLocationId(long, long)
	 */
	@Override
	public List<String> getDlByProjectIdAndLocationId(long projectId,long locationId) {
		CustomLoggerUtils.INSTANCE.log.info("inside getDlByProjectIdAndLocationId(long projectId,long locationId) method of DLDaoImpl class");
		List<String> dlNames=new ArrayList<String>();
		String query = "select Email from EmailDl where PROJECT_ID = :projectId and LOCATION_ID= :locationId";
		TypedQuery<String> p = entityManager.createQuery(query,
				String.class);
		p.setParameter("projectId", projectId);
		p.setParameter("locationId", locationId);
		try {
			dlNames = p.getResultList();
		} catch (NoResultException e) {
			CustomLoggerUtils.INSTANCE.log.error("dl not found: "
					+ projectId);
			return null;
		} catch (NonUniqueResultException e) {
			CustomLoggerUtils.INSTANCE.log.error("more than one record for the same dl name found in the database");
		}
		return dlNames;
	}
	
	
	@Override
	public List<String> getActiveDlByProjectIdAndLocationId(long projectId,long locationId){
		CustomLoggerUtils.INSTANCE.log.info("inside getActiveDlByProjectIdAndLocationId(long projectId,long locationId) method of DLDaoImpl class");
		List<String> dlNames=new ArrayList<String>();
		int isActive = 1;
		
		String q1 = "from ProjectNew where Id = :projectId";
		TypedQuery<ProjectNew> p1 = entityManager.createQuery(q1,ProjectNew.class);
		p1.setParameter("projectId", projectId);
		ProjectNew projectEntity = p1.getSingleResult();
		
		String q2 = "from LocationNew where locationId = :locationId";
		TypedQuery<LocationNew> p2 = entityManager.createQuery(q2,LocationNew.class);
		p2.setParameter("locationId", locationId);
		LocationNew locationNewEntity = p2.getSingleResult();
		
		String query = "select Email from EmailDl where project = :project and location= :location and isActive = :isActive";
		TypedQuery<String> p = entityManager.createQuery(query,String.class);
		
		p.setParameter("isActive", isActive);
		p.setParameter("project", projectEntity);
		p.setParameter("location", locationNewEntity);
		try {
			dlNames = p.getResultList();
		} catch (NoResultException e) {
			CustomLoggerUtils.INSTANCE.log.error("dl not found: "
					+ projectId);
			return null;
		} catch (NonUniqueResultException e) {
			CustomLoggerUtils.INSTANCE.log.error("more than one record for the same dl name found in the database");
		}
		return dlNames;
	}
	
	
	
	
	@Override
	public EmailDl getEmailDlByEmailDlId(Long emailDlId) {
		CustomLoggerUtils.INSTANCE.log.info("inside getEmailDlByEmailDlId(Long emailDlId) method of DLDaoImpl class");
		
		String query = "from EmailDl where emailDlId = :emailDlId";
		TypedQuery<EmailDl> p = entityManager.createQuery(query,EmailDl.class);
		p.setParameter("emailDlId", emailDlId);
		EmailDl emailDlentity =  p.getSingleResult();
		return emailDlentity;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.DLDao#editDl(com.sapient.statestreetscreeningapplication.ui.bean.EmailDlBean, com.sapient.statestreetscreeningapplication.model.entity.LocationNew, com.sapient.statestreetscreeningapplication.model.entity.ProjectNew, java.lang.String)
	 */
	@Override
	@Transactional
	public boolean editDl(EmailDlBean dlBean, LocationNew location, ProjectNew project,String dlName) {
		CustomLoggerUtils.INSTANCE.log.info("inside editDl(EmailDlBean dlBean, LocationNew location, ProjectNew project,String dlName) method of DLDaoImpl class");
		EmailDl dl=new EmailDl();
		String query = "from EmailDl where emailDlId = :dlId";
		TypedQuery<EmailDl> p = entityManager.createQuery(query,EmailDl.class);
		p.setParameter("dlId", dlBean.getEmailDlId());
		try {
			dl = p.getSingleResult();
		} catch (NoResultException e) {
			CustomLoggerUtils.INSTANCE.log.error("dl not found: "+ dlBean.getEmailDlId());
			return false;
		} catch (NonUniqueResultException e) {
			CustomLoggerUtils.INSTANCE.log.error("more than one record for the same dl name found in the database");
			return false;
		}
		dl.setLocation(location);
		dl.setProject(project);
		dl.setEmail(dlName);
		dl.setIsActive(dlBean.getIsActive());
		try{
			entityManager.merge(dl);
		}catch(HibernateException e){
			CustomLoggerUtils.INSTANCE.log.error("Merge Failed");
			return false;
		}
		CustomLoggerUtils.INSTANCE.log.error("Merge Success");
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.DLDao#saveDl(com.sapient.statestreetscreeningapplication.model.entity.LocationNew, com.sapient.statestreetscreeningapplication.model.entity.ProjectNew, java.lang.String)
	 */
	@Override
	@Transactional
	public boolean saveDl(LocationNew location, ProjectNew project, String dlName) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveDl(LocationNew location, ProjectNew project, String dlName) method of DLDaoImpl class");
		boolean isDuplicate;
		String query = "from EmailDl";
		TypedQuery<EmailDl> p = entityManager.createQuery(query,EmailDl.class);
		isDuplicate=false;
		for(EmailDl entity:p.getResultList()){
			if(entity.getLocation().getCity().equalsIgnoreCase(location.getCity())&&
				entity.getProject().getProjectName().equalsIgnoreCase(project.getProjectName())&&
				entity.getEmail().equalsIgnoreCase(dlName)){
				isDuplicate=true;
			}
		}
		if(isDuplicate){
			return isDuplicate;
		}
		else{
			CustomLoggerUtils.INSTANCE.log.info("location "+location.toString()+" project "+project.toString() +" dlName" + dlName);
			EmailDl dl=new EmailDl();
			dl.setLocation(location);
			dl.setProject(project);
			dl.setEmail(dlName);
			dl.setIsActive(1);
			entityManager.persist(dl);
			return isDuplicate;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.DLDao#saveDlBatch(java.util.List)
	 */
	@Override
	@Transactional
	public void saveDlBatch(List<EmailDl> list) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveDlBatch(List<EmailDl> list) method of DLDaoImpl class");
		for (EmailDl dl : list) {
			saveDl(dl.getLocation(),dl.getProject(),dl.getEmail());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.DLDao#getDLByDlNameAndProjectIdAndLocationId(java.lang.String, long, long)
	 */
	@Override
	public EmailDl getDLByDlNameAndProjectIdAndLocationId(String dlName,long projectId, long locationId) {
		CustomLoggerUtils.INSTANCE.log.info("inside getDLByDlNameAndProjectIdAndLocationId(String dlName,long projectId, long locationId) method of DLDaoImpl class");
		EmailDl emailDl=null;
		String query = "from EmailDl where email = :dlName and project = :project and location = :location ";
		TypedQuery<EmailDl> p = entityManager.createQuery(query,EmailDl.class);
		p.setParameter("dlName", dlName);
		p.setParameter("project", entityManager.find(ProjectNew.class, projectId));
		p.setParameter("location", entityManager.find(LocationNew.class, locationId));
		try {
			emailDl = p.getSingleResult();
		} catch (NoResultException e) {
			CustomLoggerUtils.INSTANCE.log.error("dl not found: "
					+ dlName);
			return null;
		} catch (NonUniqueResultException e) {
			CustomLoggerUtils.INSTANCE.log.error("more than one record for the same dl name found in the database");
		}
		return emailDl;
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.DLDao#getAllDlNames()
	 */
	@Override
	public List<String> getAllDlNames() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllDlNames() method of DLDaoImpl class");
		String query = "select Email from EmailDl";
		TypedQuery<String> p = entityManager.createQuery(query,
				String.class);
		return p.getResultList();
	}

	@Override
	public List<EmailDl> getActiveDlBeansByProjectIdAndLocationId(long projectId, long locationId) {
		// TODO Auto-generated method stub
		CustomLoggerUtils.INSTANCE.log.info("inside getActiveDlByProjectIdAndLocationId(long projectId,long locationId) method of DLDaoImpl class");
		List<EmailDl> dlEntityList=new ArrayList<>();
		int isActive = 1;
		
		String q1 = "from ProjectNew where Id = :projectId";
		TypedQuery<ProjectNew> p1 = entityManager.createQuery(q1,ProjectNew.class);
		p1.setParameter("projectId", projectId);
		ProjectNew projectEntity = p1.getSingleResult();
		
		String q2 = "from LocationNew where locationId = :locationId";
		TypedQuery<LocationNew> p2 = entityManager.createQuery(q2,LocationNew.class);
		p2.setParameter("locationId", locationId);
		LocationNew locationNewEntity = p2.getSingleResult();
		
		String query = "from EmailDl where project = :project and location= :location and isActive = :isActive";
		TypedQuery<EmailDl> p = entityManager.createQuery(query,EmailDl.class);
		
		p.setParameter("isActive", isActive);
		p.setParameter("project", projectEntity);
		p.setParameter("location", locationNewEntity);
		try {
			dlEntityList = p.getResultList();
		} catch (NoResultException e) {
			CustomLoggerUtils.INSTANCE.log.error("dl not found: "
					+ projectId);
			return null;
		} catch (NonUniqueResultException e) {
			CustomLoggerUtils.INSTANCE.log.error("more than one record for the same dl name found in the database");
		}
		
		
		return dlEntityList;
	}

	@Override
	public EmailDl getDLBeanForDlName(String dlName) {
		// TODO Auto-generated method stub
		CustomLoggerUtils.INSTANCE.log.info("inside getDLByDlNameAndProjectIdAndLocationId(String dlName,long projectId, long locationId) method of DLDaoImpl class");
		EmailDl emailDl=null;
		String query = "from EmailDl where email = :dlName";
		TypedQuery<EmailDl> p = entityManager.createQuery(query,EmailDl.class);
		p.setParameter("dlName", dlName);
		
		try {
			emailDl = p.getSingleResult();
		} catch (NoResultException e) {
			CustomLoggerUtils.INSTANCE.log.error("dl not found: "
					+ dlName);
			return null;
		} catch (NonUniqueResultException e) {
			CustomLoggerUtils.INSTANCE.log.error("more than one record for the same dl name found in the database");
		}
		return emailDl;
	}

	
	
}
