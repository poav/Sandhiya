package com.sapient.statestreetscreeningapplication.ui.bean;

public class AnnualProjectBudgetBean {
	
    private Long annualProjectBudgetId;
	
	private ProjectNewBean projectNewBean;
	
	private int year;
	
	private float projectAnnualBudgetValue;

	public Long getAnnualProjectBudgetId() {
		return annualProjectBudgetId;
	}

	public void setAnnualProjectBudgetId(Long annualProjectBudgetId) {
		this.annualProjectBudgetId = annualProjectBudgetId;
	}

	public ProjectNewBean getProjectNewBean() {
		return projectNewBean;
	}

	public void setProjectNewBean(ProjectNewBean projectNewBean) {
		this.projectNewBean = projectNewBean;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public float getProjectAnnualBudgetValue() {
		return projectAnnualBudgetValue;
	}

	public void setProjectAnnualBudgetValue(float projectAnnualBudgetValue) {
		this.projectAnnualBudgetValue = projectAnnualBudgetValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((projectNewBean == null) ? 0 : projectNewBean.getProjectId());
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
		AnnualProjectBudgetBean other = (AnnualProjectBudgetBean) obj;
		if (projectNewBean == null) {
			if (other.projectNewBean != null)
				return false;
		} else if (projectNewBean.getProjectId()!=other.projectNewBean.getProjectId())
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	
	
}
