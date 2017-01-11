package com.sapient.statestreetscreeningapplication.ui.bean;

import com.sapient.statestreetscreeningapplication.utils.enums.Months;
import com.sapient.statestreetscreeningapplication.utils.enums.ProjectBudgetType;

public class MonthlyProjectBudgetBean implements Comparable<MonthlyProjectBudgetBean>{
	
	private Long monthlyProjectBudgetId;
	private ProjectNewBean projectNewBean;
	private Months month;
	private int year;
	private float projectMonthlyBudgetValue;
	private ProjectBudgetType projectMonthlyBudgetType;
	public Long getMonthlyProjectBudgetId() {
		return monthlyProjectBudgetId;
	}
	public void setMonthlyProjectBudgetId(Long monthlyProjectBudgetId) {
		this.monthlyProjectBudgetId = monthlyProjectBudgetId;
	}
	public ProjectNewBean getProjectNewBean() {
		return projectNewBean;
	}
	public void setProjectNewBean(ProjectNewBean projectNewBean) {
		this.projectNewBean = projectNewBean;
	}
	public Months getMonth() {
		return month;
	}
	public void setMonth(Months month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public float getProjectMonthlyBudgetValue() {
		return projectMonthlyBudgetValue;
	}
	public void setProjectMonthlyBudgetValue(float projectMonthlyBudgetValue) {
		this.projectMonthlyBudgetValue = projectMonthlyBudgetValue;
	}
	public ProjectBudgetType getProjectMonthlyBudgetType() {
		return projectMonthlyBudgetType;
	}
	public void setProjectMonthlyBudgetType(ProjectBudgetType projectMonthlyBudgetType) {
		this.projectMonthlyBudgetType = projectMonthlyBudgetType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result
				+ ((projectNewBean == null) ? 0 : projectNewBean.hashCode());
		result = prime * result + year;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonthlyProjectBudgetBean other = (MonthlyProjectBudgetBean) obj;
		if (month != other.month)
			return false;
		if (projectNewBean == null) {
			if (other.projectNewBean != null)
				return false;
		} else if (!projectNewBean.equals(other.projectNewBean))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	@Override
	public int compareTo(MonthlyProjectBudgetBean o) {
		// TODO Auto-generated method stub
		return 1;
	}
	
	
	

}
