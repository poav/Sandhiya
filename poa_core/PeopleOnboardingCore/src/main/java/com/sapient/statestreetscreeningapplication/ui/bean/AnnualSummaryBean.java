package com.sapient.statestreetscreeningapplication.ui.bean;

public class AnnualSummaryBean {

    private ProjectNewBean projectNewBean;
	
	private AnnualProjectActualBean annualProjectActualBean;
	
	private AnnualProjectBudgetBean annualProjectBudgetBean;
	
	private float totalCreditThisYear;
	
	private String comments;

	public ProjectNewBean getProjectNewBean() {
		return projectNewBean;
	}

	public void setProjectNewBean(ProjectNewBean projectNewBean) {
		this.projectNewBean = projectNewBean;
	}

	public AnnualProjectActualBean getAnnualProjectActualBean() {
		return annualProjectActualBean;
	}

	public void setAnnualProjectActualBean(
			AnnualProjectActualBean annualProjectActualBean) {
		this.annualProjectActualBean = annualProjectActualBean;
	}

	public AnnualProjectBudgetBean getAnnualProjectBudgetBean() {
		return annualProjectBudgetBean;
	}

	public void setAnnualProjectBudgetBean(
			AnnualProjectBudgetBean annualProjectBudgetBean) {
		this.annualProjectBudgetBean = annualProjectBudgetBean;
	}

	public float getTotalCreditThisYear() {
		return totalCreditThisYear;
	}

	public void setTotalCreditThisYear(float totalCreditThisYear) {
		this.totalCreditThisYear = totalCreditThisYear;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
