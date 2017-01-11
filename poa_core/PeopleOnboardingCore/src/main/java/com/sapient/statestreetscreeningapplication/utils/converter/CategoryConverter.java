package com.sapient.statestreetscreeningapplication.utils.converter;

import java.util.ArrayList;
import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.ui.bean.CategoryBean;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoryConverter.
 */
public class CategoryConverter {

	/**
	 * Convert categoryt entity list to category bean list.
	 *
	 * @param allCategories the all categories
	 * @return the list
	 */
	public static List<CategoryBean> convertCategorytEntityListToCategoryBeanList(
			List<Category> allCategories) {
		List<CategoryBean> beanList = new ArrayList<CategoryBean>();
		for (Category categoryEntity : allCategories) {
			beanList.add(CategoryConverter
					.convertCategoryEntityToCategoryBean(categoryEntity));
		}
		return beanList;

	}

	/**
	 * Convert category entity to category bean.
	 *
	 * @param categoryEntity the category entity
	 * @return the category bean
	 */
	public static CategoryBean convertCategoryEntityToCategoryBean(
			Category categoryEntity) {
		CategoryBean bean = new CategoryBean();
		bean.setCategoryName(categoryEntity.getCategoryName());
		
		bean.setCategoryId(categoryEntity.getCategoryId());
		bean.setIsUsed(categoryEntity.getIsUsed());
		return bean;
	}

	/**
	 * Convert category bean to category entity.
	 *
	 * @param category the category
	 * @return the category
	 */
	public static Category convertCategoryBeanToCategoryEntity(
			CategoryBean category) {
		Category entity = new Category();
		entity.setCategoryName(category.getCategoryName());
		entity.setCategoryId(category.getCategoryId());
		entity.setIsUsed(category.getIsUsed());
		return entity;
	}

}
