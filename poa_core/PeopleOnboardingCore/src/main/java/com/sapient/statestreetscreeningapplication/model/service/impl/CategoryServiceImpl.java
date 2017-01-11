package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.CategoryDao;
import com.sapient.statestreetscreeningapplication.model.dao.CriteriaDao;
import com.sapient.statestreetscreeningapplication.model.dao.TopicsDao;
import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.model.entity.Criteria;
import com.sapient.statestreetscreeningapplication.model.entity.Topics;
import com.sapient.statestreetscreeningapplication.model.service.CategoryService;
import com.sapient.statestreetscreeningapplication.ui.bean.CategoryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.CriteriaBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.CategoryConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.CriteriaConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoryServiceImpl.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	/** The category dao. */
	@Autowired
	CategoryDao categoryDao;

	/** The criteria dao. */
	@Autowired
	CriteriaDao criteriaDao;

	/** The topics dao. */
	@Autowired
	TopicsDao topicsDao;

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.CategoryService#getAllCategories()
	 */
	@Override
	public List<String> getAllCategories() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllCategories() method of CategoryServiceImpl class");
		List<CategoryBean> beanList = CategoryConverter
				.convertCategorytEntityListToCategoryBeanList(categoryDao
						.getAllCategories());
		List<String> categoryList = new ArrayList<String>();
		for (CategoryBean bean : beanList) {
			categoryList.add(bean.getCategoryName());
		}
		
		return categoryList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.CategoryService#getAllCategoryBeans()
	 */
	@Override
	public List<CategoryBean> getAllCategoryBeans() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllCategoryBeans() method of CategoryServiceImpl class");
		List<CategoryBean> beanList = CategoryConverter
				.convertCategorytEntityListToCategoryBeanList(categoryDao
						.getAllCategories());
		
		return beanList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.CategoryService#saveCategoryDetails(com.sapient.statestreetscreeningapplication.ui.bean.CategoryBean)
	 */
	@Override
	public boolean saveCategoryDetails(CategoryBean category) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveCategoryDetails(CategoryBean category) method of CategoryServiceImpl class");
		if (category != null) {
			if (categoryDao.saveCategory(CategoryConverter
					.convertCategoryBeanToCategoryEntity(category)) > 0) {
				
				return true;
			}
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.CategoryService#saveCategoryBatchDetails(java.io.File)
	 */
	@Override
	@Transactional
	public void saveCategoryBatchDetails(File categoryBatch) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveCategoryBatchDetails(File categoryBatch) method of CategoryServiceImpl class");

		String csvFile = categoryBatch.getPath();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] category = line.split(cvsSplitBy);
				String categoryName = category[0];
				String criteriaName = null;
				String topicName = null;
				try {
					criteriaName = category[1];
					topicName = category[2];
				} catch (ArrayIndexOutOfBoundsException e) {
					// TODO: handle exception
					CustomLoggerUtils.INSTANCE.log
							.error("ArrayIndexOutOfBounds. Category service implementation. Exception: invalid entry");
					continue;
				}
				Category existingCategory;
				if (!categoryName.equals("-")) {

					if (criteriaName.equals("-")) {
						if (topicName.equals("-")) {
							existingCategory = categoryDao
									.getCategory(categoryName);
							if (existingCategory == null) {
								Category newCategory = new Category();
								newCategory.setCategoryName(categoryName);
								newCategory.setIsUsed(1);
								categoryDao.saveCategory(newCategory);
							}
						}
					} else {
						if (topicName.equals("-")) {
							existingCategory = categoryDao
									.getCategory(categoryName);
							if (existingCategory == null) {
								Category newCategory = new Category();
								newCategory.setCategoryName(categoryName);
								newCategory.setIsUsed(1);
								categoryDao.saveCategory(newCategory);
								Criteria newCriteria = new Criteria();
								newCriteria.setCriteriaName(criteriaName);
								newCriteria.setIsUsed(1);
								newCriteria.setCategory(categoryDao
										.getCategory(categoryName));
								criteriaDao.saveCriteria(newCriteria);
							} else {
								List<Criteria> criteriaList = existingCategory
										.getCriteriaList();
								boolean existing = false;
								for (Criteria criteria : criteriaList) {
									if (criteriaName.equalsIgnoreCase(criteria
											.getCriteriaName())) {
										existing = true;
										break;
									}
								}
								if (!existing) {
									Criteria newCriteria = new Criteria();
									newCriteria.setCriteriaName(criteriaName);
									newCriteria.setIsUsed(1);
									newCriteria.setCategory(existingCategory);
									criteriaDao.saveCriteria(newCriteria);
								}
							}

						} else {
							existingCategory = categoryDao
									.getCategory(categoryName);
							if (existingCategory == null) {
								Category newCategory = new Category();
								newCategory.setCategoryName(categoryName);
								newCategory.setIsUsed(1);
								categoryDao.saveCategory(newCategory);
								Criteria newCriteria = new Criteria();
								newCriteria.setCriteriaName(criteriaName);
								newCriteria.setIsUsed(1);
								newCriteria.setCategory(categoryDao
										.getCategory(categoryName));
								criteriaDao.saveCriteria(newCriteria);
								Topics newTopic = new Topics();
								newTopic.setTopicName(topicName);
								newTopic.setIsUsed(1);
								newTopic.setCriteria(criteriaDao.getCriteria(
										criteriaName,
										categoryDao.getCategory(categoryName)));
								topicsDao.saveTopic(newTopic);
							} else {
								List<Criteria> criteriaList = existingCategory
										.getCriteriaList();
								boolean existing = false;
								Criteria existingCriteria = null;
								for (Criteria criteria : criteriaList) {
									if (criteriaName.equalsIgnoreCase(criteria
											.getCriteriaName())) {
										existing = true;
										existingCriteria = criteria;
										break;
									}
								}
								if (!existing) {
									Criteria newCriteria = new Criteria();
									newCriteria.setCriteriaName(criteriaName);
									newCriteria.setIsUsed(1);
									newCriteria.setCategory(existingCategory);
									criteriaDao.saveCriteria(newCriteria);
									Topics newTopic = new Topics();
									newTopic.setTopicName(topicName);
									newTopic.setIsUsed(1);
									newTopic.setCriteria(criteriaDao
											.getCriteria(
													criteriaName,
													categoryDao
															.getCategory(categoryName)));
									topicsDao.saveTopic(newTopic);
								} else {
									Set<Topics> topicList = existingCriteria
											.getTopicsList();
									boolean topicExisting = false;
									for (Topics topic : topicList) {
										if (topicName.equalsIgnoreCase(topic
												.getTopicName())) {
											topicExisting = true;
											break;
										}
									}
									if (!topicExisting) {
										Topics newTopic = new Topics();
										newTopic.setTopicName(topicName);
										newTopic.setIsUsed(1);
										newTopic.setCriteria(criteriaDao
												.getCriteria(
														criteriaName,
														categoryDao
																.getCategory(categoryName)));
										topicsDao.saveTopic(newTopic);
									}
								}
							}
						}
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.CategoryService#getCategoryByName(java.lang.String)
	 */
	@Override
	public CategoryBean getCategoryByName(String categoryName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getCategoryByName(String categoryName) method of CategoryServiceImpl class");
		Category category = categoryDao.getCategory(categoryName);
		if (category != null) {
			
			return CategoryConverter
					.convertCategoryEntityToCategoryBean(category);
		}
		
		return new CategoryBean();
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

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.CategoryService#getAllCriterias(long)
	 */
	@Override
	public List<CriteriaBean> getAllCriterias(long categoryID) {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllCriterias(long categoryID) method of CategoryServiceImpl class");
		if (categoryID > 0) {
			List<Criteria> criterias = categoryDao.getCriterias(categoryID);
			if (criterias != null) {
				List<CriteriaBean> criteriaList = CriteriaConverter
						.criteriaEntityListToBeanListConverter((criterias));
				return criteriaList;
			}
			return null;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.CategoryService#changeState(long, int)
	 */
	@Override
	public boolean changeState(long categoryId, int state) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeState(long categoryId, int state) method of CategoryServiceImpl class");
		if (categoryId > 0) {
			return categoryDao.changeState(categoryId, state);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.CategoryService#getAllUsedCategories()
	 */
	@Override
	public List<String> getAllUsedCategories() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllUsedCategories() method of CategoryServiceImpl class");
		List<CategoryBean> beanList = CategoryConverter
				.convertCategorytEntityListToCategoryBeanList(categoryDao
						.getAllUsedCategories());
		List<String> categoryList = new ArrayList<String>();
		for (CategoryBean bean : beanList) {
			categoryList.add(bean.getCategoryName());
		}
		return categoryList;
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
	 * Gets the topics dao.
	 *
	 * @return the topics dao
	 */
	public TopicsDao getTopicsDao() {
		return topicsDao;
	}

	/**
	 * Sets the topics dao.
	 *
	 * @param topicsDao the new topics dao
	 */
	public void setTopicsDao(TopicsDao topicsDao) {
		this.topicsDao = topicsDao;
	}
}
