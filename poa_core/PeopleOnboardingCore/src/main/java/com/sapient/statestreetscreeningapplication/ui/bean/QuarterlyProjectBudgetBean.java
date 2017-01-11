package com.sapient.statestreetscreeningapplication.ui.bean;

import com.sapient.statestreetscreeningapplication.utils.enums.Quarters;

public class QuarterlyProjectBudgetBean {
	
	private Long quarterlyProjectBudgetId;
	
	private ProjectNewBean projectNewBean;
	
	private Quarters quarter;
	
	private int year;
	
	private float projectQuarterlyBudgetValue;

	public Long getQuarterlyProjectBudgetId() {
		return quarterlyProjectBudgetId;
	}

	public void setQuarterlyProjectBudgetId(Long quarterlyProjectBudgetId) {
		this.quarterlyProjectBudgetId = quarterlyProjectBudgetId;
	}
	
	public ProjectNewBean getProjectNewBean() {
		return projectNewBean;
	}

	public void setProjectNewBean(ProjectNewBean projectNewBean) {
		this.projectNewBean = projectNewBean;
	}

	public Quarters getQuarter() {
		return quarter;
	}

	public void setQuarter(Quarters quarter) {
		this.quarter = quarter;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public float getProjectQuarterlyBudgetValue() {
		return projectQuarterlyBudgetValue;
	}

	public void setProjectQuarterlyBudgetValue(float projectQuarterlyBudgetValue) {
		this.projectQuarterlyBudgetValue = projectQuarterlyBudgetValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((projectNewBean == null) ? 0 : projectNewBean.getProjectId());
		result = prime * result + ((quarter == null) ? 0 : quarter.hashCode());
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
		QuarterlyProjectBudgetBean other = (QuarterlyProjectBudgetBean) obj;
		if (projectNewBean == null) {
			if (other.projectNewBean != null)
				return false;
		} else if (projectNewBean.getProjectId()!=other.projectNewBean.getProjectId())
			return false;
		if (quarter != other.quarter)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	
}
