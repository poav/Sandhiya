package com.sapient.statestreetscreeningapplication.model.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.ui.bean.CategoryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.CriteriaBean;
// TODO: Auto-generated Javadoc

/**
 * The Interface CategoryService.
 */
@Service
public interface CategoryService {

	/**
	 * Gets the all categories.
	 *
	 * @return the all categories
	 */
	List<String> getAllCategories();

	/**
	 * Gets the all category beans.
	 *
	 * @return the all category beans
	 */
	List<CategoryBean> getAllCategoryBeans();

	/**
	 * Save category details.
	 *
	 * @param category the category
	 * @return true, if successful
	 */
	boolean saveCategoryDetails(CategoryBean category);

	/**
	 * Save category batch details.
	 *
	 * @param categoryBatch the category batch
	 */
	void saveCategoryBatchDetails(File categoryBatch);

	/**
	 * Gets the category by name.
	 *
	 * @param categoryName the category name
	 * @return the category by name
	 */
	CategoryBean getCategoryByName(String categoryName);
	
	/**
	 * Gets the all criterias.
	 *
	 * @param categoryID the category id
	 * @return the all criterias
	 */
	List<CriteriaBean> getAllCriterias(long categoryID);

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
	List<String> getAllUsedCategories();

}
