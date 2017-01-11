package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.CategoryDao;
import com.sapient.statestreetscreeningapplication.model.dao.CriteriaDao;
import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.model.entity.Criteria;
import com.sapient.statestreetscreeningapplication.model.service.CriteriaService;
import com.sapient.statestreetscreeningapplication.ui.bean.CriteriaBean;
import com.sapient.statestreetscreeningapplication.ui.bean.TopicBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.CriteriaConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.TopicConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class CriteriaServiceImplementation.
 */
@Service
public class CriteriaServiceImplementation implements CriteriaService {

	/** The criteria dao. */
	@Autowired
	CriteriaDao criteriaDao;

	/** The category dao. */
	@Autowired
	CategoryDao categoryDao;

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.CriteriaService#getCriteriaMap()
	 */
	@Override
	@Transactional
	public HashMap<String, List<String>> getCriteriaMap() {
		CustomLoggerUtils.INSTANCE.log.info("inside getCriteriaMap() method of CriteriaServiceImplementation class");
		HashMap<String, List<String>> criteriaMap = new HashMap<String, List<String>>();
		List<Category> categoryList = categoryDao.getAllUsedCategories();
		List<CriteriaBean> criteriaList;
		List<String> tempCriteriaList;
		for (Category category : categoryList) {
			criteriaList = CriteriaConverter
					.criteriaEntityListToBeanListConverter(new ArrayList<Criteria>(
							category.getCriteriaList()));
			tempCriteriaList = new ArrayList<String>();
			for (CriteriaBean bean : criteriaList) {
				if (bean.getIsUsed() == 1) {
					tempCriteriaList.add(bean.getCriteriaName());
				}
			}
			criteriaMap.put(category.getCategoryName(), tempCriteriaList);
		}

		return criteriaMap;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.CriteriaService#getAllTopics(long)
	 */
	@Override
	public List<TopicBean> getAllTopics(long criteriaID) {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllTopics(long criteriaID) method of CriteriaServiceImplementation class");
		List<TopicBean> topicList = TopicConverter
				.convertTopicEntityListToTopicBeanList(criteriaDao
						.getAllCriterias(criteriaID));
		return topicList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.CriteriaService#saveCriteria(com.sapient.statestreetscreeningapplication.ui.bean.CriteriaBean)
	 */
	@Override
	public int saveCriteria(CriteriaBean criteriaBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveCriteria(CriteriaBean criteriaBean) method of CriteriaServiceImplementation class");
		if (criteriaBean != null) {
			Criteria criteria = CriteriaConverter
					.criteriaBeanToEntityConverter(criteriaBean);
			criteriaDao.saveCriteria(criteria);
			return 1;
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.CriteriaService#changeState(long, int)
	 */
	@Override
	public boolean changeState(long criteriaId, int state) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeState(long criteriaId, int state) method of CriteriaServiceImplementation class");
		if (criteriaId > 0) {
			return criteriaDao.changeState(criteriaId, state);
		}
		return false;
	}

	/**
	 * Gets the criteria dao.
	 *
	 * @return the criteria dao
	 */
	public CriteriaDao getCriteriaDao() {
		return criteriaDao;
	}

	/**
	 * Sets the criteria dao.
	 *
	 * @param criteriaDao the new criteria dao
	 */
	public void setCriteriaDao(CriteriaDao criteriaDao) {
		this.criteriaDao = criteriaDao;
	}

	/**
	 * Gets the category dao.
	 *
	 * @return the category dao
	 */
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	/**
	 * Sets the category dao.
	 *
	 * @param categoryDao the new category dao
	 */
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

}
