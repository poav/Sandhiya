package com.sapient.statestreetscreeningapplication.utils.converter;

import java.util.ArrayList;
import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.Criteria;
import com.sapient.statestreetscreeningapplication.ui.bean.CriteriaBean;

// TODO: Auto-generated Javadoc
/**
 * The Class CriteriaConverter.
 */
public class CriteriaConverter {
	
	/**
	 * Criteria entity to bean converter.
	 *
	 * @param entity the entity
	 * @return the criteria bean
	 */
	public static CriteriaBean criteriaEntityToBeanConverter(Criteria entity) {
		CriteriaBean bean = new CriteriaBean();
		if (entity.getCategory() != null) {
			bean.setCategoryBean(CategoryConverter
					.convertCategoryEntityToCategoryBean(entity.getCategory()));
		}
		bean.setCriteriaName(entity.getCriteriaName());
		bean.setCriteriaId(entity.getCriteriaId());
		bean.setIsUsed(entity.getIsUsed());
		return bean;
	}

	/**
	 * Criteria bean to entity converter.
	 *
	 * @param bean the bean
	 * @return the criteria
	 */
	public static Criteria criteriaBeanToEntityConverter(CriteriaBean bean) {
		Criteria entity = new Criteria();
		if (bean.getCategoryBean() != null) {
			entity.setCategory(CategoryConverter
					.convertCategoryBeanToCategoryEntity(bean.getCategoryBean()));
		}
		entity.setCriteriaName(bean.getCriteriaName());
		entity.setCriteriaId(bean.getCriteriaId());
		entity.setIsUsed(bean.getIsUsed());
		return entity;
	}

	/**
	 * Criteria entity list to bean list converter.
	 *
	 * @param entityList the entity list
	 * @return the list
	 */
	public static List<CriteriaBean> criteriaEntityListToBeanListConverter(
			List<Criteria> entityList) {
		List<CriteriaBean> beanList = new ArrayList<CriteriaBean>();
		if (entityList != null) {
			for (Criteria entity : entityList) {
				beanList.add(criteriaEntityToBeanConverter(entity));
			}
		}
		return beanList;
	}
}
