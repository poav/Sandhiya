package com.sapient.statestreetscreeningapplication.ui.bean;

import com.sapient.statestreetscreeningapplication.utils.enums.Months;

public class MonthlyProjectActualBean {
	
    private Long monthlyProjectActualId;

    private ProjectNewBean project;
	
	private Months month;
	
	private int year;
	
	private float projectMonthlyCost;

	public Long getMonthlyProjectActualId() {
		return monthlyProjectActualId;
	}

	public void setMonthlyProjectActualId(Long monthlyProjectActualId) {
		this.monthlyProjectActualId = monthlyProjectActualId;
	}

	public ProjectNewBean getProject() {
		return project;
	}

	public void setProject(ProjectNewBean project) {
		this.project = project;
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

	public float getProjectMonthlyCost() {
		return projectMonthlyCost;
	}

	public void setProjectMonthlyCost(float projectMonthlyCost) {
		this.projectMonthlyCost = projectMonthlyCost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((project == null) ? 0 : project.getProjectId());
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
		MonthlyProjectActualBean other = (MonthlyProjectActualBean) obj;
		if (month != other.month)
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (project.getProjectId()!=other.project.getProjectId())
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	

}
