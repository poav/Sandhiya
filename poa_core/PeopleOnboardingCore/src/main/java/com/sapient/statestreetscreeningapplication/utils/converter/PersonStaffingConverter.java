package com.sapient.statestreetscreeningapplication.utils.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonStaffingBean;

public class PersonStaffingConverter {
	
	public static PersonStaffingBean personStaffingEntityToBean(PersonStaffing entity) {
		
		PersonStaffingBean bean = new PersonStaffingBean();
		
		if(entity==null){
		   bean = null;
		}
		
		if(entity!=null){
		
		bean = PersonStaffingConverter.partiallyConvertEntityToBean(entity);
		bean.setImmediateLastStaffing(PersonStaffingConverter.partiallyConvertEntityToBean(entity.getImmediateLastStaffing()));
		bean.setImmediateNextStaffing(PersonStaffingConverter.partiallyConvertEntityToBean(entity.getImmediateNextStaffing()));
		
		}
		   
		return bean;
    }
	
	
	public static PersonStaffing personStaffingBeanToEntity(PersonStaffingBean bean) {
		
		PersonStaffing entity = new PersonStaffing();
		
		if(bean==null){
		   entity = null;
		}
		
		if(bean!=null){
		
		entity = PersonStaffingConverter.partiallyConvertBeanToEntity(bean);
		entity.setImmediateLastStaffing(PersonStaffingConverter.partiallyConvertBeanToEntity(bean.getImmediateLastStaffing()));
		entity.setImmediateNextStaffing(PersonStaffingConverter.partiallyConvertBeanToEntity(bean.getImmediateNextStaffing()));
		
		}
		   
		return entity;
    }

	public static List<PersonStaffingBean> personStaffingEntityListToBeanList(List<PersonStaffing> fetchPersonStaffingDetails) {
		List<PersonStaffingBean> beanList=new ArrayList<PersonStaffingBean>();
		for(PersonStaffing entity:fetchPersonStaffingDetails){
			beanList.add(personStaffingEntityToBean(entity));
		}
		return beanList;
	}
	

	
	private static PersonStaffingBean partiallyConvertEntityToBean(PersonStaffing entity) {
		
		PersonStaffingBean bean = new PersonStaffingBean();
		
		if(entity==null){
			bean = null;
		}
		
		if(entity!=null){
			
			bean.setAllocation(entity.getAllocation());
			bean.setClientLead(entity.getClientLead());
			bean.setComments(entity.getComments());
			bean.setCategoryBean(CategoryConverter.convertCategoryEntityToCategoryBean(entity.getCategory()));
			bean.setPersonStaffingId(entity.getPersonStaffingId());
			bean.setEmailDls(EmailDlConverter.convertEmailDlEntitySetToEmailDlBeanSet(entity.getEmailDls()));
			
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			
			if(entity.getStartDate()!=null){
				bean.setStaffingStartDate(entity.getStartDate());
				bean.setStartDate(formatter.format(entity.getStartDate()));	
			}
			
			if(entity.getEndDate()!=null){
				bean.setStaffingEndDate(entity.getEndDate());
				bean.setEndDate(formatter.format(entity.getEndDate()));
			}
			
			bean.setLocation(LocationNewConverter.locationEntityToBean(entity.getLocation()));
			bean.setPerson(PersonNewConverter.personNewEntityToBean(entity.getPerson()));
			bean.setPosition(PositionConverter.convertPositionEntityToBean(entity.getPosition()));
			bean.setProject(ProjectNewConverter.convertNewProjectEntityToProjectNewBean(entity.getProject()));
			
			if(entity.getSapientLead()!=null){
				bean.setSapientLead(PersonNewConverter.personNewEntityToBean(entity.getSapientLead()));
			}
			
			bean.setRateBean(RateConverter.rateEntityToBean(entity.getRate()));
			bean.setOffboarded(entity.isOffboarded());

		}
		   
		return bean;		
	}
	
	
	private static PersonStaffing partiallyConvertBeanToEntity(PersonStaffingBean bean) {
		
		PersonStaffing entity = new PersonStaffing();
		
		if(bean==null){
			entity = null;
		}
		
		if(bean!=null){
		
			entity.setAllocation(bean.getAllocation());
			entity.setClientLead(bean.getClientLead());
			entity.setComments(bean.getComments());
			entity.setCategory(CategoryConverter.convertCategoryBeanToCategoryEntity(bean.getCategoryBean()));
			entity.setPersonStaffingId(bean.getPersonStaffingId());
			entity.setEmailDls(EmailDlConverter.convertEmailDlBeanSetToEmailDlEntitySet(bean.getEmailDls()));
			
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			
			if(bean.getStartDate()!=null){
			try{entity.setStartDate(formatter.parse(bean.getStartDate()));} catch(ParseException e){e.printStackTrace();}
			}
			
			if(bean.getEndDate()!=null){
			try{entity.setEndDate(formatter.parse(bean.getEndDate()));} catch(ParseException e){e.printStackTrace();}
			}
			
			entity.setLocation(LocationNewConverter.locationBeanToEntity(bean.getLocation()));
			entity.setPerson(PersonNewConverter.personBeanToEntity(bean.getPerson()));
			
			if(bean.getPosition()!=null){
				entity.setPosition(PositionConverter.convertPositionBeanToEntity(bean.getPosition()));
			}
			
			entity.setProject(ProjectNewConverter.convertNewProjectBeanToProjectNewEntity(bean.getProject()));
			
			if(bean.getSapientLead() != null){
				entity.setSapientLead(PersonNewConverter.personBeanToEntity(bean.getSapientLead()));
			}
			 
			entity.setRate(RateConverter.rateBeanToEntity(bean.getRateBean()));
			entity.setOffboarded(bean.isOffboarded());
			
		}
		 
		return entity;
    }

	
	
}