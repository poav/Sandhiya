package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.HashSet;
import java.util.Set;

public class MonthDetailsBean {
	
	private Set<StaffingMonthlyCostBean>  staffingMonthlyCostBeanSet  = new HashSet<StaffingMonthlyCostBean>();

	private Set<MonthlyProjectActualBean> monthlyProjectActualBeanSet = new HashSet<MonthlyProjectActualBean>();

	public Set<StaffingMonthlyCostBean> getStaffingMonthlyCostBeanSet() {
		return staffingMonthlyCostBeanSet;
	}

	public void setStaffingMonthlyCostBeanSet(
			Set<StaffingMonthlyCostBean> staffingMonthlyCostBeanSet) {
		this.staffingMonthlyCostBeanSet = staffingMonthlyCostBeanSet;
	}

	public Set<MonthlyProjectActualBean> getMonthlyProjectActualBeanSet() {
		return monthlyProjectActualBeanSet;
	}

	public void setMonthlyProjectActualBeanSet(
			Set<MonthlyProjectActualBean> monthlyProjectActualBeanSet) {
		this.monthlyProjectActualBeanSet = monthlyProjectActualBeanSet;
	}

}
