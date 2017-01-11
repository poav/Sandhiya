package com.sapient.statestreetscreeningapplication.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.sapient.statestreetscreeningapplication.utils.enums.Months;
import com.sapient.statestreetscreeningapplication.utils.enums.ProjectBudgetType;

@Entity
@Table(name="MONTHLY_PROJECT_BUDGET")
public class MonthlyProjectBudgetEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="MONTHLY_PROJECT_BUDGET_ID")
	private Long monthlyProjectBudgetId;
	
	@OneToOne
	@JoinColumn(name = "PROJECT_ID")
	private ProjectNew project;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "MONTH")
	private Months month;
	
	@Column(name = "YEAR")
	private int year;
	
	@Column(name = "PROJECT_MONTHLY_BUDGET_VALUE")
	private float projectMonthlyBudgetValue;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PROJECT_MONTHLY_BUDGET_TYPE")
	private ProjectBudgetType projectMonthlyBudgetType;
	
	
	public Long getMonthlyProjectBudgetId() {
		return monthlyProjectBudgetId;
	}

	public void setMonthlyProjectBudgetId(Long monthlyProjectBudgetId) {
		this.monthlyProjectBudgetId = monthlyProjectBudgetId;
	}

	public ProjectNew getProject() {
		return project;
	}

	public void setProject(ProjectNew project) {
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

}
