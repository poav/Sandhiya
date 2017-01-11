package com.sapient.statestreetscreeningapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.statestreetscreeningapplication.model.service.FeedbackCaptureService;
import com.sapient.statestreetscreeningapplication.ui.bean.FeedbackBean;

@RestController
public class IssueTrackerController {
	
	@Autowired
	private FeedbackCaptureService feedbackCaptureService;
	
	@CrossOrigin
	@RequestMapping("/retrieveAllFeedBacks")
	public List<FeedbackBean> getAllFeedbacks() {
		
		return feedbackCaptureService.getAllFeedbacksAndIssues();
		
	}
	
	@CrossOrigin
	@RequestMapping("/getIssueTrackerBean")
	public FeedbackBean getFeedBackBean() {
		
		return new FeedbackBean();
		
	}
	
	@CrossOrigin
	@RequestMapping("/getIssueTrackerStatusByName")
	public List<String> getIssueTrackerStatusByName() {
		
		 
		return feedbackCaptureService.getFeedBackStatusbyName();
		
	}
	
	@CrossOrigin
	@RequestMapping("/getIssueTrackerTypeByName")
	public List<String> getIssueTrackerTypeByName() {
		
		 
		return feedbackCaptureService.getFeedBackTypeByName();
		
	}
	
	
	
	@CrossOrigin
	@RequestMapping(value = "/updateIssueTracker", method = RequestMethod.POST)
	public void updateIssueTracker(@RequestBody FeedbackBean feedbackBean )
	{
		
		feedbackCaptureService.saveFeedback(feedbackBean);
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/saveIssueTracker", method = RequestMethod.POST)
	public void saveIssueTracker(@RequestBody FeedbackBean feedbackBean )
	{
		
		feedbackCaptureService.saveFeedback(feedbackBean);
		
	}
	

}
