package com.sapient.statestreetscreeningapplication.ui.bean;

public class QuarterlySummaryBean {
	
	private ProjectNewBean projectNewBean;
	
	private QuarterlyProjectActualBean quarterlyProjectActualBean;
	
	private QuarterlyProjectBudgetBean quarterlyProjectBudgetBean;
	
	private float totalCreditThisQuarter;
	
	private String comments;

	public ProjectNewBean getProjectNewBean() {
		return projectNewBean;
	}

	public void setProjectNewBean(ProjectNewBean projectNewBean) {
		this.projectNewBean = projectNewBean;
	}

	public QuarterlyProjectActualBean getQuarterlyProjectActualBean() {
		return quarterlyProjectActualBean;
	}

	public void setQuarterlyProjectActualBean(QuarterlyProjectActualBean quarterlyProjectActualBean) {
		this.quarterlyProjectActualBean = quarterlyProjectActualBean;
	}

	public QuarterlyProjectBudgetBean getQuarterlyProjectBudgetBean() {
		return quarterlyProjectBudgetBean;
	}

	public void setQuarterlyProjectBudgetBean(QuarterlyProjectBudgetBean quarterlyProjectBudgetBean) {
		this.quarterlyProjectBudgetBean = quarterlyProjectBudgetBean;
	}

	public float getTotalCreditThisQuarter() {
		return totalCreditThisQuarter;
	}

	public void setTotalCreditThisQuarter(float totalCreditThisQuarter) {
		this.totalCreditThisQuarter = totalCreditThisQuarter;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	

}
