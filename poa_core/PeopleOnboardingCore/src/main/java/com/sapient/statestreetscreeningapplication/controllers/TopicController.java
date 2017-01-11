package com.sapient.statestreetscreeningapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.statestreetscreeningapplication.model.service.ScoreService;
import com.sapient.statestreetscreeningapplication.model.service.TopicsService;
import com.sapient.statestreetscreeningapplication.ui.bean.CriteriaBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ScoreBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ScoresNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.TopicBean;

@RestController
public class TopicController {
	
	@Autowired
	TopicsService topicsService;

	@Autowired
	ScoreService scoreService;
	
	@CrossOrigin
	@RequestMapping(value = "/getAllTopicBeans", method = RequestMethod.POST)
	public List<TopicBean> retrieveAllTopicBeans(@RequestBody CriteriaBean criteriaBean)
	{
		return topicsService.getTopics(criteriaBean.getCriteriaName(), criteriaBean.getCategoryBean().getCategoryName());
	}
	
	@CrossOrigin
	@RequestMapping(value = "/addTopic", method = RequestMethod.POST)
	public void  saveTopicBean(@RequestBody TopicBean topicBean)
	{
		List<TopicBean> topicList= topicsService.getTopics(topicBean.getCriteriaBean().getCriteriaName(), topicBean.getCriteriaBean().getCategoryBean().getCategoryName());
		boolean duplicateCriteriaName=false;
			
		if(topicList!=null )	
		{
			for(TopicBean topicBeanOld: topicList)
			{
				if(topicBeanOld.getTopicName().equals(topicBean.getTopicName()))
					duplicateCriteriaName = true;
			}
			
		}
	
			if(duplicateCriteriaName==false)
				topicsService.saveTopic(topicBean);
			
		
		
	}
	
	
	@CrossOrigin
	@RequestMapping("/getTopicBean")
	public TopicBean getTopicBean()
	{
		return new TopicBean();
	}
	
	
	@CrossOrigin
	@RequestMapping("/getEmptyScoreBean")
	public ScoresNewBean getEmptyScoreNewBean(){
		
		return new ScoresNewBean();
	}
	
	
	
	
	
	
	
	
	
}
