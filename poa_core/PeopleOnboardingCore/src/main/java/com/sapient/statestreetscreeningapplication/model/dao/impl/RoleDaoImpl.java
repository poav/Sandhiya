package com.sapient.statestreetscreeningapplication.model.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.RoleDao;
import com.sapient.statestreetscreeningapplication.model.entity.Role;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class RoleDaoImpl.
 */
@Component
public class RoleDaoImpl implements RoleDao {
	
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
	 * @see com.sapient.statestreetscreeningapplication.model.dao.RoleDao#getRoleByName(java.lang.String)
	 */
	@Override
	@Transactional
	public Role getRoleByName(String roleName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getRoleByName "+ roleName +"  method of RoleDaoImpl class ");
		Role role=null;
		String query = "from Role where roleName = :roleName";
		TypedQuery<Role> p = entityManager.createQuery(query,
				Role.class);
		p.setParameter("roleName", roleName);
		try {
			role = p.getSingleResult();
		} catch (NoResultException e) {
			CustomLoggerUtils.INSTANCE.log.error("role not found: "
					+ roleName);
			return null;
		} catch (NonUniqueResultException e) {
			CustomLoggerUtils.INSTANCE.log.error("more than one record for the same roleName name found in the database");
		}
		return role;
	}

}
