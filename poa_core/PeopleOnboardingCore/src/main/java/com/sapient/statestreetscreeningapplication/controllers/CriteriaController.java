package com.sapient.statestreetscreeningapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.statestreetscreeningapplication.model.entity.Criteria;
import com.sapient.statestreetscreeningapplication.model.service.CategoryService;
import com.sapient.statestreetscreeningapplication.model.service.CriteriaService;
import com.sapient.statestreetscreeningapplication.ui.bean.CategoryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.CriteriaBean;
import com.sapient.statestreetscreeningapplication.ui.bean.TopicBean;

@RestController
public class CriteriaController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CriteriaService criteriaService;
	
	@CrossOrigin
	@RequestMapping(value = "/getAllCriterias", method = RequestMethod.POST)
	public List<CriteriaBean> getAllCriterias(@RequestBody Long categoryID)
	{
		return categoryService.getAllCriterias(categoryID);
	}
	
	@CrossOrigin
	@RequestMapping("/getCriteriaBean")
	public CriteriaBean getCriteriaBean()
	{
		return new CriteriaBean();
	}

	
	@CrossOrigin
	@RequestMapping(value = "/changeStateCrieteria", method = RequestMethod.POST)
	public void changeState(@RequestBody Long criteriaId, int state)
	{
		
		criteriaService.changeState(criteriaId, state);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/addCriteria", method = RequestMethod.POST)
	public void  saveCriteriaBean(@RequestBody CriteriaBean criteriaBean)
	{
		List<CriteriaBean> criteriaList= categoryService.getAllCriterias(criteriaBean.getCriteriaId());
		boolean duplicateCriteriaName=false;
			
		if(criteriaList!=null)
		{
			
			for(CriteriaBean criteriaBeanOld: criteriaList)
			{
				if(criteriaBeanOld.getCriteriaName().equals(criteriaBean.getCriteriaName()))
					duplicateCriteriaName = true;
			}
			
			
		}
		if(duplicateCriteriaName==false)
			criteriaService.saveCriteria(criteriaBean);
		 
	}
	
	@CrossOrigin
	@RequestMapping("/retriveTopics")
	public List<TopicBean> getAllTopicsFromCriteria(@RequestParam("criteriaID") long criteriaID) {
			
			return criteriaService.getAllTopics(criteriaID);
		
	}
	
	
	
	
}
