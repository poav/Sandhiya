package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.HashSet;
import java.util.Set;

public class AnnualDetailsBean {
	
	private Set<AnnualProjectActualBean> annualProjectActualBeanSet = new HashSet<AnnualProjectActualBean>();

	private Set<AnnualProjectBudgetBean> annualProjectBudgetBeanSet = new HashSet<AnnualProjectBudgetBean>();

	public Set<AnnualProjectActualBean> getAnnualProjectActualBeanSet() {
		return annualProjectActualBeanSet;
	}

	public void setAnnualProjectActualBeanSet(
			Set<AnnualProjectActualBean> annualProjectActualBeanSet) {
		this.annualProjectActualBeanSet = annualProjectActualBeanSet;
	}

	public Set<AnnualProjectBudgetBean> getAnnualProjectBudgetBeanSet() {
		return annualProjectBudgetBeanSet;
	}

	public void setAnnualProjectBudgetBeanSet(
			Set<AnnualProjectBudgetBean> annualProjectBudgetBeanSet) {
		this.annualProjectBudgetBeanSet = annualProjectBudgetBeanSet;
	}
	
	

}
