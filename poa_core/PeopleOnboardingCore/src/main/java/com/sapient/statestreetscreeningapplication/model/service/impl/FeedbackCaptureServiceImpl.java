package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.dao.FeedbackDao;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.service.FeedbackCaptureService;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.model.service.PersonService;
import com.sapient.statestreetscreeningapplication.ui.bean.FeedbackBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.FeedbackCaptureConverter;
import com.sapient.statestreetscreeningapplication.utils.enums.FeedbackStatus;
import com.sapient.statestreetscreeningapplication.utils.enums.FeedbackType;

// TODO: Auto-generated Javadoc
/**
 * The Class FeedbackCaptureServiceImpl.
 */
@Service
public class FeedbackCaptureServiceImpl implements FeedbackCaptureService {

	/** The feedback dao. */
	@Autowired
	FeedbackDao feedbackDao;
	
	/** The person lookup service. */
	@Autowired
	PersonLookupService personLookupService;
	
	@Autowired
	PersonService PersonService;
	

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.FeedbackCaptureService#getAllFeedbacksAndIssues()
	 */
	@Override
	public List<FeedbackBean> getAllFeedbacksAndIssues() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllFeedbacksAndIssues() method of FeedbackCaptureServiceImpl class");
		List<FeedbackBean> feedbackList = new ArrayList<FeedbackBean>();
		feedbackList = FeedbackCaptureConverter.convertToBeanList(feedbackDao
				.getAllFeedback());
		for (FeedbackBean f : feedbackList) {
			
			Person tPerson= PersonService.getPersonByOracleId(f.getOracleId());
			PersonBean person = personLookupService.getPersonByNTId(tPerson.getPersonNtId());
			f.setFeedbackProvider(person.getName());
		}
		
		return feedbackList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.FeedbackCaptureService#saveFeedback(com.sapient.statestreetscreeningapplication.ui.bean.FeedbackBean)
	 */
	@Override
	public Boolean saveFeedback(FeedbackBean feedbackBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveFeedback(FeedbackBean feedbackBean) method of FeedbackCaptureServiceImpl class");
		Boolean isSaved = false;
		isSaved = feedbackDao.saveUserFeedback(FeedbackCaptureConverter
				.convertBeanToEntity(feedbackBean));
		return isSaved;
	}

	/**
	 * Gets the feedback dao.
	 *
	 * @return the feedback dao
	 */
	public FeedbackDao getFeedbackDao() {
		return feedbackDao;
	}

	/**
	 * Sets the feedback dao.
	 *
	 * @param feedbackDao the new feedback dao
	 */
	public void setFeedbackDao(FeedbackDao feedbackDao) {
		this.feedbackDao = feedbackDao;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.FeedbackCaptureService#updateFeedback(long)
	 */
	@Override
	public FeedbackBean updateFeedback(long feedbackId) {
		CustomLoggerUtils.INSTANCE.log.info("inside updateFeedback(long feedbackId) method of FeedbackCaptureServiceImpl class");
		FeedbackBean feedbackBean=FeedbackCaptureConverter.convertEntityToBean(feedbackDao.getFeedback(feedbackId));
		return feedbackBean;
	}

	@Override
	public List<String> getFeedBackStatusbyName() {
		// TODO Auto-generated method stub
		List<String> feedbackStatusList=new ArrayList<>();
				
		for(FeedbackStatus fs:FeedbackStatus.values())
			feedbackStatusList.add(fs.name());
		
		return feedbackStatusList;
			
	}

	@Override
	public List<String> getFeedBackTypeByName() {
		// TODO Auto-generated method stub
		List<String> feedbackTypeList=new ArrayList<>();
		
		for(FeedbackType fs:FeedbackType.values())
			feedbackTypeList.add(fs.name());
		
		return feedbackTypeList;
	}
}
