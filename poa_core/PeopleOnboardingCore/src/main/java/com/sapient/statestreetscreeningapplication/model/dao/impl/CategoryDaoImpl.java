package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.CategoryDao;
import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.model.entity.Criteria;
import com.sapient.statestreetscreeningapplication.model.entity.Position;
import com.sapient.statestreetscreeningapplication.model.entity.Topics;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoryDaoImpl.
 */
@Component
public class CategoryDaoImpl implements CategoryDao {
	
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
	 * @see com.sapient.statestreetscreeningapplication.model.dao.CategoryDao#getAllCategories()
	 */
	@Override
	public List<Category> getAllCategories() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllCategories() method of CategoryDaoImpl class");
		String query = "from Category";
		TypedQuery<Category> p = entityManager.createQuery(query,
				Category.class);
		return p.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.CategoryDao#saveCategory(com.sapient.statestreetscreeningapplication.model.entity.Category)
	 */
	@Override
	@Transactional
	public long saveCategory(Category category) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveCategory(Category "+ category +") method of CategoryDaoImpl class");
		String query = "from Category where categoryName = :categoryName";
		TypedQuery<Category> p = entityManager.createQuery(query,
				Category.class);
		p.setParameter("categoryName", category.getCategoryName());
		if (p.getResultList().size() <= 0) {
			CustomLoggerUtils.INSTANCE.log.info("category doesnt exist");
			category.setIsUsed(1);
			entityManager.persist(category);
			return category.getCategoryId();
		} else {
			CustomLoggerUtils.INSTANCE.log.info("category already exists");
			return 0;
		}

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.CategoryDao#getCategory(java.lang.String)
	 */
	@Override
	public Category getCategory(String categoryName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getCategory(-> "+ categoryName+" method of CategoryDaoImpl class");
		Category category;
		String query = "from Category where categoryName=:category_name";
		TypedQuery<Category> p = entityManager.createQuery(query,
				Category.class);
		p.setParameter("category_name", categoryName);
		try {
			category = p.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		CustomLoggerUtils.INSTANCE.log.info("Category returned is: "+category);

		return category;
	}
	
	@Override
	public List<Category> getAllCategoriesByName(String categoryName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getCategory( "+ categoryName+") method of CategoryDaoImpl class");
	
		List<Category> categoryList;
		String query = "from Category where categoryName like :category_name";
		TypedQuery<Category> p = entityManager.createQuery(query,
				Category.class);
		p.setParameter("category_name", categoryName);
		try {
			categoryList = p.getResultList();
			CustomLoggerUtils.INSTANCE.log.info("category QUERY "+query);
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		CustomLoggerUtils.INSTANCE.log.info("Category List returned is: "+ categoryList);

		return categoryList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.CategoryDao#saveCategoryBatch(java.util.List)
	 */
	@Override
	@Transactional
	public void saveCategoryBatch(List<Category> list) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveCategoryBatch-> "+ list.toString());
		List<Category> existingCategories = getAllCategories();
		for (Category category : list) {
			Boolean isUnique = true;
			for (Category c : existingCategories) {
				if (c.getCategoryName().equalsIgnoreCase(
						category.getCategoryName().toString())) {
					isUnique = false;
					break;
				}
			}
			if (isUnique) {
				entityManager.persist(category);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.CategoryDao#getCriterias(long)
	 */
	@Override
	public List<Criteria> getCriterias(long categoryID) {
		CustomLoggerUtils.INSTANCE.log.info("inside getCriterias "+ categoryID +" method of CategoryDaoImpl class");
		Category category = entityManager.find(Category.class, categoryID);
		if (category != null) {
			return Arrays.asList(category.getCriteriaList().toArray(
					new Criteria[0]));
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.CategoryDao#changeState(long, int)
	 */
	@Override
	@Transactional
	public boolean changeState(long categoryId, int state) {
		CustomLoggerUtils.INSTANCE.log.info("inside  changeState "+ categoryId+","+ state+"  method of CategoryDaoImpl class");
		Category category = entityManager.find(Category.class, categoryId);
		if (category != null) {
			category.setIsUsed(state);
			for (Criteria criteria : category.getCriteriaList()) {
				criteria.setIsUsed(state);
				for (Topics topic : criteria.getTopicsList()) {
					topic.setIsUsed(state);
				}
			}
			return true;
		}
		return false;

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.CategoryDao#getAllUsedCategories()
	 */
	@Override
	public List<Category> getAllUsedCategories() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllUsedCategories() method of CategoryDaoImpl class");
		String query = "from Category where isUsed =:Used";
		TypedQuery<Category> p = entityManager.createQuery(query,
				Category.class);
		p.setParameter("Used", 1);
		return p.getResultList();
	}
}
