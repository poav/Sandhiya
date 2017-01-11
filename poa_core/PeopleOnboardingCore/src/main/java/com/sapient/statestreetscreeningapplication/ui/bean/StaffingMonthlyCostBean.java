package com.sapient.statestreetscreeningapplication.ui.bean;

import com.sapient.statestreetscreeningapplication.utils.enums.Months;

public class StaffingMonthlyCostBean {
	
	
	private Long staffingMonthlyCostId;
	
	private PersonStaffingBean personStaffingBean;
	
	private Months month;
	
	private int year;
	
	private float individualMonthlyCost;

	public Long getStaffingMonthlyCostId() {
		return staffingMonthlyCostId;
	}

	public void setStaffingMonthlyCostId(Long staffingMonthlyCostId) {
		this.staffingMonthlyCostId = staffingMonthlyCostId;
	}

	public PersonStaffingBean getPersonStaffingBean() {
		return personStaffingBean;
	}

	public void setPersonStaffingBean(PersonStaffingBean personStaffingBean) {
		this.personStaffingBean = personStaffingBean;
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

	public float getIndividualMonthlyCost() {
		return individualMonthlyCost;
	}

	public void setIndividualMonthlyCost(float individualMonthlyCost) {
		this.individualMonthlyCost = individualMonthlyCost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime
				* result
				+ ((personStaffingBean == null) ? 0 : personStaffingBean.getPersonStaffingId().intValue());
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
		StaffingMonthlyCostBean other = (StaffingMonthlyCostBean) obj;
		if (month != other.month)
			return false;
		if (personStaffingBean == null) {
			if (other.personStaffingBean != null)
				return false;
		} else if (personStaffingBean.getPersonStaffingId()!=other.personStaffingBean.getPersonStaffingId())
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	
	
}
