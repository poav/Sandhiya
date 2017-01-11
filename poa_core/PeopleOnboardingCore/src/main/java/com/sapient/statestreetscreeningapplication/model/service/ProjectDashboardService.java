package com.sapient.statestreetscreeningapplication.model.service;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;


@Service
public interface ProjectDashboardService {

	public Map<String, Object> populateProjectDashboardTable(int sStart,
			int sAmount, String string, String sdir, String sSearch, int projectId, int year) ;
	
	public int getProjectDashBoardCountForSpecificProject(Date firstJanuaryOfSelectedYear1,
			Date thirtyFirstDecemberOfSelectedYear1, int projectId, int start,
			int amount, String orderingColumn, String orderingDirection, String searchKey);


}
