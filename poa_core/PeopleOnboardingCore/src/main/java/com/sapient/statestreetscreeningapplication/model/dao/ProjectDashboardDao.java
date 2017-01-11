package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Component;

import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;

@Component
public interface ProjectDashboardDao {
	
	

	public List<PersonStaffing> getStaffingThisProject(
			Date firstJanuaryOfSelectedYear,
			Date thirtyFirstDecemberOfSelectedYear, int projectId, int start,
			int amount, String columnName, String direction, String searchKey);

	public List<PersonStaffing> getStaffingThisProjectForTempInterviewees(
			Date firstJanuaryOfSelectedYear,
			Date thirtyFirstDecemberOfSelectedYear, int projectId, int start,
			int amount, String columnName, String direction, String searchKey);

	public List<PersonStaffing> getStaffingThisProjectForIntervieweesWithOracleId(Date firstJanuaryOfSelectedYear,
			Date thirtyFirstDecemberOfSelectedYear,int projectId, int start,
			int amount, String columnName, String direction, String searchKey, List<String> oracleIdList);

	public List<PersonStaffing> getStaffingThisProjectByDate(
			Date firstJanuaryOfSelectedYear,
			Date thirtyFirstDecemberOfSelectedYear, int projectId, int start,
			int amount, String columnName, String direction, String searchKey);
	
	public int getProjectDashBoardCountForSpecificProject(Date firstJanuaryOfSelectedYear1,
			Date thirtyFirstDecemberOfSelectedYear1, int projectId, int start,
			int amount, String orderingColumn, String orderingDirection, String searchKey);


}
