package com.sapient.statestreetscreeningapplication.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.ui.bean.AnnualDetailsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectSummaryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.QuarterDetailsBean;
import com.sapient.statestreetscreeningapplication.utils.enums.Quarters;

@Service
public interface DashboardService {
	
	public QuarterDetailsBean quarterlySummaryCalculation(Quarters quarter, int year);

	public AnnualDetailsBean annualSummaryCalculation(int year);

	public List<ProjectSummaryBean> projectSummaryCalculation(int projectId, int year);
	
	public int getWeekDays(Date fromDate, Date toDate);

}
