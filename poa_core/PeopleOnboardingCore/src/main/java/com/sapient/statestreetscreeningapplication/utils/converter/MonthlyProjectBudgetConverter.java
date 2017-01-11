package com.sapient.statestreetscreeningapplication.utils.converter;

import com.sapient.statestreetscreeningapplication.model.entity.MonthlyProjectBudgetEntity;
import com.sapient.statestreetscreeningapplication.ui.bean.MonthlyProjectBudgetBean;

public class MonthlyProjectBudgetConverter {
	
	public static MonthlyProjectBudgetBean MonthlyProjectBudgetEntityToBean(MonthlyProjectBudgetEntity entity){
		MonthlyProjectBudgetBean bean = new MonthlyProjectBudgetBean();
		bean.setMonthlyProjectBudgetId(entity.getMonthlyProjectBudgetId());
		bean.setProjectNewBean(ProjectNewConverter.convertNewProjectEntityToProjectNewBean(entity.getProject()));
		bean.setMonth(entity.getMonth());
		bean.setYear(entity.getYear());
		bean.setProjectMonthlyBudgetType(entity.getProjectMonthlyBudgetType());
		bean.setProjectMonthlyBudgetValue(entity.getProjectMonthlyBudgetValue());
		return bean;
	}
	
	public static MonthlyProjectBudgetEntity MonthlyProjectBudgetBeanToEntity(MonthlyProjectBudgetBean bean){
		MonthlyProjectBudgetEntity entity = new MonthlyProjectBudgetEntity();
		entity.setMonthlyProjectBudgetId(bean.getMonthlyProjectBudgetId());
		entity.setProject(ProjectNewConverter.convertNewProjectBeanToProjectNewEntity(bean.getProjectNewBean()));
		entity.setMonth(bean.getMonth());
		entity.setYear(bean.getYear());
		entity.setProjectMonthlyBudgetType(bean.getProjectMonthlyBudgetType());
		entity.setProjectMonthlyBudgetValue(bean.getProjectMonthlyBudgetValue());
		return entity;
	}

}
