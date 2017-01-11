package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.dao.TopicsDao;
import com.sapient.statestreetscreeningapplication.model.entity.Topics;
import com.sapient.statestreetscreeningapplication.model.service.TopicsService;
import com.sapient.statestreetscreeningapplication.ui.bean.TopicBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.TopicConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class TopicsServiceImpl.
 */
@Service
public class TopicsServiceImpl implements TopicsService {

	/** The topics dao. */
	@Autowired
	TopicsDao topicsDao;

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.TopicsService#getTopics(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unused")
	@Override
	public List<TopicBean> getTopics(String criteriaName, String categoryName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getTopics(String criteriaName, String categoryName) method of TopicsServiceImpl class");
		List<Topics> topicsList = topicsDao.getTopicsList(categoryName,
				criteriaName);
		List<Topics> usedTopicList = new ArrayList<Topics>();
		for (Topics topic : topicsList) {
			if (topic.getIsUsed() == 1) {
				usedTopicList.add(topic);
			}
		}
		CustomLoggerUtils.INSTANCE.log
				.info("is topics list received from dao null:" + topicsList == null);
		if (topicsList != null) {
			return TopicConverter
					.convertTopicEntityListToTopicBeanList(usedTopicList);
		}
		return null;

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.TopicsService#getCriteria(long)
	 */
	@Override
	public String getCriteria(long id) {
		CustomLoggerUtils.INSTANCE.log.info("inside getCriteria(long id) method of TopicsServiceImpl class");
		return topicsDao.getCriteria(id);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.TopicsService#saveTopic(com.sapient.statestreetscreeningapplication.ui.bean.TopicBean)
	 */
	@Override
	public int saveTopic(TopicBean topicBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveTopic(TopicBean topicBean) method of TopicsServiceImpl class");
		Topics topic = TopicConverter.convertTopicBeanToTopicEntity(topicBean);
		return topicsDao.saveTopic(topic);
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

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.TopicsService#changeState(long, int)
	 */
	@Override
	public void changeState(long topicId, int state) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeState(long topicId, int state) method of TopicsServiceImpl class");
		topicsDao.changeState(topicId, state);
	}

	@Override
	public List<TopicBean> getTopicsFromCriteria(String criteriaName) {
		// TODO Auto-generated method stub
		
		List<Topics> usedTopicList = new ArrayList<Topics>();
		List<Topics> topicsList=topicsDao.getTopicsFromCriteria(criteriaName);
		for (Topics topic : topicsList) {
			if (topic.getIsUsed() == 1) {
				usedTopicList.add(topic);
			}
		}
		CustomLoggerUtils.INSTANCE.log
				.info("is topics list received from dao null:" + topicsList == null);
		if (topicsList != null) {
			
			return TopicConverter.convertTopicEntityListToTopicBeanList(topicsList);
		}
		return null;
		
	}
}
