package com.sapient.statestreetscreeningapplication.utils.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;
import com.sapient.statestreetscreeningapplication.model.service.CategoryService;
import com.sapient.statestreetscreeningapplication.model.service.impl.CategoryServiceImpl;
import com.sapient.statestreetscreeningapplication.ui.bean.CategoryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonScreeningDetailsBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

public class PersonScreeningDetailsConvertor {

	public static PersonScreeningDetailsBean PersonScreeningDetailsEntityToBean(PersonScreeningDetails entity){
		PersonScreeningDetailsBean bean = new PersonScreeningDetailsBean();
		bean.setPersonScreeningId(entity.getPersonScreeningId());
		bean.setPersonBean(PersonNewConverter.personNewEntityToBean(entity.getPerson()));
		bean.setScreeningStartDate(entity.getScreeningStartDate());
		bean.setScreeningEndDate(entity.getScreeningEndDate());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if(entity.getScreeningStartDate()!=null)
			bean.setStringScreeningStartDate(formatter.format(entity.getScreeningStartDate()));
		if(entity.getScreeningEndDate()!=null)
			bean.setStringScreeningEndDate(formatter.format(entity.getScreeningEndDate()));
		bean.setStatusBean(StatusConverter.convertStatusEntityToStatusBean(entity.getStatus()));
		bean.setScreenerBean(PersonNewConverter.personNewEntityToBean(entity.getScreener()));
		bean.setScoreList(ScoreNewConvertor.convertScoreListEntityToScoreListBean(entity.getScoreList()));
		if(entity.getProjectNew()!=null){
			bean.setProjectBean(ProjectNewConverter.convertNewProjectEntityToProjectNewBean(entity.getProjectNew()));
		}
		
		bean.setCategory(entity.getCategory().getCategoryName());
		bean.setFeedback(entity.getFeedback());
		return bean;
	}	
	
	
	public static PersonScreeningDetails PersonScreeningDetailsBeanToPersonScreeningDetailsEntity(PersonScreeningDetailsBean bean){
		PersonScreeningDetails entity = new PersonScreeningDetails();
		entity.setPersonScreeningId(bean.getPersonScreeningId());
		
		if(bean.getPersonBean()!=null){
		entity.setPerson(PersonNewConverter.personBeanToEntity(bean.getPersonBean()));
		}
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		if(bean.getScreeningStartDate()!=null){
				entity.setScreeningStartDate(bean.getScreeningStartDate());
		}
		
		if(bean.getScreeningEndDate()!=null){
				entity.setScreeningEndDate(bean.getScreeningEndDate());
		}
		
		if(bean.getStatusBean()!=null){
		entity.setStatus(StatusConverter.convertStatusBeanToStatusEntity(bean.getStatusBean()));
		}
		if(bean.getScreenerBean()!=null)
			entity.setScreener(PersonNewConverter.personBeanToEntity(bean.getScreenerBean()));
		if(bean.getScoreList()!=null)
			entity.setScoreList(ScoreNewConvertor.convertScoreListBeanToScoreListEntity(bean.getScoreList()));
		if(bean.getProjectBean()!=null){
			entity.setProjectNew(ProjectNewConverter.convertNewProjectBeanToProjectNewEntity(bean.getProjectBean()));
		}
		
		if(bean.getFeedback()!=null){
		entity.setFeedback(bean.getFeedback());
		}
		return entity;
	}	
	
	
	public static PersonScreeningDetails PersonScreeningDetailsBeanToEntity(PersonScreeningDetailsBean bean, CategoryService categoryService){

		PersonScreeningDetails entity = new PersonScreeningDetails();
		entity.setPersonScreeningId(bean.getPersonScreeningId());
		entity.setPerson(PersonNewConverter.personBeanToEntity(bean.getPersonBean()));
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			if(bean.getScreeningStartDate()==null)
			{
				if(!bean.getStringScreeningStartDate().equals(null))
					entity.setScreeningStartDate(formatter.parse(bean.getStringScreeningStartDate()));
			}
			else
			{
				entity.setScreeningStartDate(bean.getScreeningStartDate());
			}
			
			if(bean.getScreeningEndDate()==null)
			{
				if(!bean.getStringScreeningEndDate().equals(null))
					entity.setScreeningEndDate(formatter.parse(bean.getStringScreeningEndDate()));

			}
			else
			{
				entity.setScreeningEndDate(bean.getScreeningEndDate());
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
				entity.setStatus(StatusConverter.convertStatusBeanToStatusEntity(bean.getStatusBean()));
		if(bean.getScreenerBean()!=null)
			entity.setScreener(PersonNewConverter.personBeanToEntity(bean.getScreenerBean()));
		if(bean.getScoreList()!=null)
			entity.setScoreList(ScoreNewConvertor.convertScoreListBeanToScoreListEntity(bean.getScoreList()));
		if(bean.getProjectBean()!=null){
			entity.setProjectNew(ProjectNewConverter.convertNewProjectBeanToProjectNewEntity(bean.getProjectBean()));
		}
		
		
		CategoryBean categoryBean = categoryService.getCategoryByName(bean.getCategory());
		entity.setCategory(CategoryConverter.convertCategoryBeanToCategoryEntity
				(categoryBean));
		entity.setFeedback(bean.getFeedback());
		return entity;
	}
	
}
