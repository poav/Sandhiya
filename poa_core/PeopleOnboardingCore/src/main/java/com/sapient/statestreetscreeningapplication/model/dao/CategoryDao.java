package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.model.entity.Criteria;

// TODO: Auto-generated Javadoc
/**
 * The Interface CategoryDao.
 */
@Component
public interface CategoryDao {

	/**
	 * Gets the all categories.
	 *
	 * @return the all categories
	 */
	List<Category> getAllCategories();

	/**
	 * Save category.
	 *
	 * @param category the category
	 * @return the long
	 */
	long saveCategory(Category category);

	/**
	 * Gets the category.
	 *
	 * @param categoryName the category name
	 * @return the category
	 */
	Category getCategory(String categoryName);

	/**
	 * Save category batch.
	 *
	 * @param list the list
	 */
	void saveCategoryBatch(List<Category> list);

	/**
	 * Gets the criterias.
	 *
	 * @param categoryID the category id
	 * @return the criterias
	 */
	List<Criteria> getCriterias(long categoryID);

	/**
	 * Change state.
	 *
	 * @param categoryId the category id
	 * @param state the state
	 * @return true, if successful
	 */
	boolean changeState(long categoryId, int state);

	/**
	 * Gets the all used categories.
	 *
	 * @return the all used categories
	 */
	List<Category> getAllUsedCategories();

	List<Category> getAllCategoriesByName(String categoryName);

}
