package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.HashSet;
import java.util.Set;

public class QuarterDetailsBean {
	
	private Set<QuarterlyProjectActualBean> quarterlyProjectActualBeanSet = new HashSet<QuarterlyProjectActualBean>();
	
	private Set<QuarterlyProjectBudgetBean> quarterlyProjectBudgetBeanSet = new HashSet<QuarterlyProjectBudgetBean>();
	
	public Set<QuarterlyProjectActualBean> getQuarterlyProjectActualBeanSet() {
		return quarterlyProjectActualBeanSet;
	}
	public void setQuarterlyProjectActualBeanSet(Set<QuarterlyProjectActualBean> quarterlyProjectActualBeanSet) {
		this.quarterlyProjectActualBeanSet = quarterlyProjectActualBeanSet;
	}
	public Set<QuarterlyProjectBudgetBean> getQuarterlyProjectBudgetBeanSet() {
		return quarterlyProjectBudgetBeanSet;
	}
	public void setQuarterlyProjectBudgetBeanSet(Set<QuarterlyProjectBudgetBean> quarterlyProjectBudgetBeanSet) {
		this.quarterlyProjectBudgetBeanSet = quarterlyProjectBudgetBeanSet;
	}
	
}
