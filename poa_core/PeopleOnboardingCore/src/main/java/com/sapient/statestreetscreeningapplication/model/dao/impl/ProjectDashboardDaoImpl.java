package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.CategoryDao;
import com.sapient.statestreetscreeningapplication.model.dao.LocationDao;
import com.sapient.statestreetscreeningapplication.model.dao.ProjectDashboardDao;
import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.model.entity.LocationNew;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

@Component
public class ProjectDashboardDaoImpl implements ProjectDashboardDao{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	LocationDao locationDao;
	

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	@Override
	@Transactional
	public List<PersonStaffing> getStaffingThisProject(
			Date firstJanuaryOfSelectedYear1,
			Date thirtyFirstDecemberOfSelectedYear1, int projectId, int start,
			int amount, String orderingColumn, String orderingDirection, String searchKey) {


		
		CustomLoggerUtils.INSTANCE.log.info("inside  List<PersonStaffing> getStaffingThisProject(Date firstJanuaryOfSelectedYear,Date thirtyFirstDecemberOfSelectedYear, int projectId, int start,int amount, String columnName, String direction, String searchKey) method of ProjectDashboardDaoImpl class" );
		// search by either (location domain ) or retrieve all
		String searchQuery = "", categorySearchKey="",locationSearchKey="";
		DateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String firstJanuaryOfSelectedYear=formatter.format(firstJanuaryOfSelectedYear1);
		String thirtyFirstDecemberOfSelectedYear=formatter.format(thirtyFirstDecemberOfSelectedYear1);
		
		List<Long> categoryIdList = new ArrayList<Long>();
		List<Long> locationIdList = new ArrayList<Long>();
		
		
		int count=0, check=0;

		if (searchKey != null && !searchKey.isEmpty()) {
			
			searchKey = "'%" + searchKey + "%'";
			if(!categoryDao.getAllCategoriesByName(searchKey).isEmpty()){
				for (Category category : categoryDao.getAllCategoriesByName(searchKey)){
					categoryIdList.add(category.getCategoryId());
				}
				CustomLoggerUtils.INSTANCE.log.info(categoryIdList.toString());
				for(Long temp : categoryIdList){
					if(count==0){
						categorySearchKey=""+temp+"";
					}
					else{
						categorySearchKey+=""+temp+"";
					}
					count++;
					if(count < categoryIdList.size()){
						categorySearchKey+=",";
					}
				}
				categorySearchKey = "(" + categorySearchKey + ")";
			}
			if(!locationDao.getAllLocationByName(searchKey).isEmpty()){
				for (LocationNew location : locationDao.getAllLocationByName(searchKey)){
					locationIdList.add(location.getLocationId());
				}
				CustomLoggerUtils.INSTANCE.log.info(locationIdList.toString());
				for(Long temp : locationIdList){
					if(count==0){
						locationSearchKey=""+temp+"";
					}
					else{
						locationSearchKey+=""+temp+"";
					}
					count++;
					if(count < locationIdList.size()){
						locationSearchKey+=",";
					}
				}
				locationSearchKey = "(" + locationSearchKey + ")";
			}
			
			searchQuery = " and (" ;
			if(categorySearchKey != ""){
				searchQuery += " P.CATEGORY_ID IN " + categorySearchKey;
				check = 1;
			}
			if(check == 1){
				searchQuery += " OR " ;
				check = 0;
			}
			if(locationSearchKey != ""){
				searchQuery += " P.LOCATION_ID IN " + locationSearchKey;
				check = 1;
			}
			if(check == 1){
				searchQuery += " OR " ;
				check = 0;
			}
			searchQuery +=  ") ";
			
			CustomLoggerUtils.INSTANCE.log.info("SEARCH QUERY "+ searchQuery);
		}
		String selectQuery = "select * from person_staffing as P LEFT JOIN person as Pe ON Pe.PERSON_ID=P.PERSON_ID LEFT JOIN location_new ON P.Location_ID=location_new.Location_ID LEFT JOIN position ON P.position_ID=position.position_ID LEFT JOIN project_new ON P.PROJECT_ID=project_new.ID LEFT JOIN rate ON P.rate_id=rate.rate_id where end_Date >'"+firstJanuaryOfSelectedYear+"' and start_Date <'"+thirtyFirstDecemberOfSelectedYear+"' and project_new.project_id ="
				+projectId
				+ " "
				+ searchQuery
				+ " order by "
				+ orderingColumn
				+ " "
				+ orderingDirection + " limit " + start + ", " + amount + "";
		Query p = entityManager.createNativeQuery(selectQuery,
				PersonStaffing.class);
		CustomLoggerUtils.INSTANCE.log.info("selectQuery "+ selectQuery);
		@SuppressWarnings("unchecked")
		List<PersonStaffing> list = p.getResultList();
		CustomLoggerUtils.INSTANCE.log.info("query: " + selectQuery);
		if (list != null) {
			CustomLoggerUtils.INSTANCE.log.info("Size of person staffing  retrived:"
							+ list.size());
		} else {
			CustomLoggerUtils.INSTANCE.log.info("No person staffing found");
		}
		return list; 
		
		
		
		
	}
	
	public int getProjectDashBoardCountForSpecificProject(Date firstJanuaryOfSelectedYear1,
			Date thirtyFirstDecemberOfSelectedYear1, int projectId, int start,
			int amount, String orderingColumn, String orderingDirection, String searchKey)
	{
		CustomLoggerUtils.INSTANCE.log.info("inside  List<PersonStaffing> getStaffingThisProject(Date firstJanuaryOfSelectedYear,Date thirtyFirstDecemberOfSelectedYear, int projectId, int start,int amount, String columnName, String direction, String searchKey) method of ProjectDashboardDaoImpl class" );
		// search by either (location domain ) or retrieve all
		DateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String firstJanuaryOfSelectedYear=formatter.format(firstJanuaryOfSelectedYear1);
		String thirtyFirstDecemberOfSelectedYear=formatter.format(thirtyFirstDecemberOfSelectedYear1);
		
		
		String selectQuery = "select count(*) from person_staffing as P JOIN person as Pe ON Pe.PERSON_ID=P.PERSON_ID  JOIN location_new ON P.Location_ID=location_new.Location_ID  JOIN position ON P.position_ID=position.position_ID  JOIN project_new ON P.PROJECT_ID=project_new.ID  JOIN rate ON P.rate_id=rate.rate_id where end_Date >'"+firstJanuaryOfSelectedYear+"' and start_Date <'"+thirtyFirstDecemberOfSelectedYear+"' and project_new.project_id ="
				+projectId ;
		Query p = entityManager.createNativeQuery(selectQuery,
				PersonStaffing.class);
		CustomLoggerUtils.INSTANCE.log.info("selectQuery "+ selectQuery);
//		BigInteger count= p.getFirstResult()
//		return count.intValue();
		
		return p.getFirstResult();
	}

	@Override
	@Transactional
	public List<PersonStaffing> getStaffingThisProjectForTempInterviewees(
			Date firstJanuaryOfSelectedYear1,
			Date thirtyFirstDecemberOfSelectedYear1, int projectId, int start,
			int amount, String orderingColumn, String orderingDirection, String searchKey) {
	

		CustomLoggerUtils.INSTANCE.log.info("inside  List<PersonStaffing> getStaffingThisProjectForTempInterviewees(Date firstJanuaryOfSelectedYear,Date thirtyFirstDecemberOfSelectedYear, int projectId, int start,int amount, String columnName, String direction, String searchKey) method of ProjectDashboardDaoImpl class" );
		DateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String firstJanuaryOfSelectedYear=formatter.format(firstJanuaryOfSelectedYear1);
		String thirtyFirstDecemberOfSelectedYear=formatter.format(thirtyFirstDecemberOfSelectedYear1);	
		String selectQuery ="select * from person_staffing as P LEFT JOIN (person as Pe inner join TEMP_PERSON as t on t.TEMP_PERSON_ID=Pe.TEMP_PERSON_ID)  ON Pe.PERSON_ID=P.PERSON_ID LEFT JOIN location_new ON P.Location_ID=location_new.Location_ID LEFT JOIN position ON P.position_ID=position.position_ID LEFT JOIN project_new ON P.PROJECT_ID=project_new.ID LEFT JOIN rate ON P.rate_id=rate.rate_id LEFT JOIN category ON P.category_id=category.category_id where end_Date >'"+firstJanuaryOfSelectedYear+"' and start_Date <'"+thirtyFirstDecemberOfSelectedYear+"' and project_new.project_id ="
				+projectId
				+ " "
				+ " and t.TEMP_PERSON_NAME like '"
				+searchKey +"%' "
				+ " order by "
				+ orderingColumn
				+ " "
				+ orderingDirection + " limit " + start + ", " + amount + "";
		Query p = entityManager.createNativeQuery(selectQuery,
			PersonStaffing.class);
		CustomLoggerUtils.INSTANCE.log.info("selectQuery "+ selectQuery);
		
		List<PersonStaffing> list ;
		try{
			list= p.getResultList();
		}catch(NoResultException e){
			CustomLoggerUtils.INSTANCE.log.info("No PersonStaffing found, returning null.");
			return null;
		}
		
		CustomLoggerUtils.INSTANCE.log.info("query: " + selectQuery);
		if (list != null) {
			CustomLoggerUtils.INSTANCE.log.info("Size of PersonStaffing retrived:"
							+ list.size());
		} else {
			CustomLoggerUtils.INSTANCE.log.info("No PersonStaffing found");
		}
		return list;
		
	}
	
	@Override
	@Transactional
	public List<PersonStaffing> getStaffingThisProjectForIntervieweesWithOracleId(
			Date firstJanuaryOfSelectedYear1,
			Date thirtyFirstDecemberOfSelectedYear1, int projectId, int start,
			int amount, String orderingColumn, String orderingDirection, String searchKey,List<String> oracleIdList) {
		
	String searchQuery;
	
	DateFormat formatter=new SimpleDateFormat("yyyyMMdd");
	String firstJanuaryOfSelectedYear=formatter.format(firstJanuaryOfSelectedYear1);
	String thirtyFirstDecemberOfSelectedYear=formatter.format(thirtyFirstDecemberOfSelectedYear1);
	
	int count=0;
	for(String temp : oracleIdList){
		if(count==0){
			searchKey="\'"+temp+"\'";
		}
		else{
			searchKey+="\'"+temp+"\'";
		}
		count++;
		if(count < oracleIdList.size()){
			searchKey+=",";
		}
	}
	CustomLoggerUtils.INSTANCE.log.info("oracleIdList "+oracleIdList+" searchKey "+searchKey);
	searchKey = "(" + searchKey + ")";
	searchQuery = " and (Pe.PERSON_ORACLE_ID IN " + searchKey + ") ";
	CustomLoggerUtils.INSTANCE.log.info("searchQuery  "+searchQuery);
	String selectQuery = "select * from person_staffing as P LEFT JOIN person as Pe ON Pe.PERSON_ID=P.PERSON_ID LEFT JOIN location_new ON P.Location_ID=location_new.Location_ID LEFT JOIN position ON P.position_ID=position.position_ID LEFT JOIN project_new ON P.PROJECT_ID=project_new.ID LEFT JOIN rate ON P.rate_id=rate.rate_id LEFT JOIN category ON P.category_id=category.category_id where end_Date >'"+firstJanuaryOfSelectedYear+"' and start_Date <'"+thirtyFirstDecemberOfSelectedYear+"' and project_new.project_id ="
			+projectId
			+ " "
			+ searchQuery
			+ " order by "
			+ orderingColumn
			+ " "
			+ orderingDirection + " limit " + start + ", " + amount + "";
	Query p = entityManager.createNativeQuery(selectQuery,
		PersonStaffing.class);
	CustomLoggerUtils.INSTANCE.log.info("selectQuery "+ selectQuery);
	@SuppressWarnings("unchecked")
	List<PersonStaffing> list = p.getResultList();
	CustomLoggerUtils.INSTANCE.log.info("query: " + selectQuery);
	if (list != null) {
		CustomLoggerUtils.INSTANCE.log.info("Size of person staffing  retrived:"
						+ list.size());
	} else {
		CustomLoggerUtils.INSTANCE.log.info("No person staffing found");
	}
	return list; 
	}

	@Override
	@Transactional
	public List<PersonStaffing> getStaffingThisProjectByDate(
			Date firstJanuaryOfSelectedYear1,
			Date thirtyFirstDecemberOfSelectedYear1, int projectId, int start,
			int amount, String orderingColumn, String orderingDirection, String searchKey) {
	
		CustomLoggerUtils.INSTANCE.log.info("inside  getStaffingThisProjectByDate(Date firstJanuaryOfSelectedYear,Date thirtyFirstDecemberOfSelectedYear, int projectId, int start,int amount, String columnName, String direction, String searchKey) method of ProjectDashboardDaoImpl class");
		String searchQuery;
		DateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String firstJanuaryOfSelectedYear=formatter.format(firstJanuaryOfSelectedYear1);
		String thirtyFirstDecemberOfSelectedYear=formatter.format(thirtyFirstDecemberOfSelectedYear1);

		searchKey = "'%" + searchKey + "%'";
		searchQuery = " and (P.START_DATE like " + searchKey + " OR P.END_DATE like "
				+ searchKey + " ) ";
		String selectQuery = "select * from person_staffing as P LEFT JOIN person as Pe ON Pe.PERSON_ID=P.PERSON_ID LEFT JOIN location_new ON P.Location_ID=location_new.Location_ID LEFT JOIN position ON P.position_ID=position.position_ID LEFT JOIN project_new ON P.PROJECT_ID=project_new.ID LEFT JOIN rate ON P.rate_id=rate.rate_id LEFT JOIN category ON P.category_id=category.category_id where end_Date >'"+firstJanuaryOfSelectedYear+"' and start_Date <'"+thirtyFirstDecemberOfSelectedYear+"' and project_new.project_id ="
				+projectId+" "
				+ searchQuery
				+ " order by "
				+ orderingColumn
				+ " "
				+ orderingDirection + " limit " + start + ", " + amount + "";
		
		Query p = entityManager.createNativeQuery(selectQuery,
				PersonStaffing.class);
		
		CustomLoggerUtils.INSTANCE.log.info("selectQuery "+ selectQuery);
		
		@SuppressWarnings("unchecked")
		List<PersonStaffing> list = p.getResultList();
		CustomLoggerUtils.INSTANCE.log.info("query: " + selectQuery);
		if (list != null) {
			CustomLoggerUtils.INSTANCE.log.info("getStaffingThisProjectByDate. Size of PersonStaffing retrived:"
							+ list.size());
		} else {
			CustomLoggerUtils.INSTANCE.log.info("No PersonStaffing found");
		}
		return list;
	}

	
	


}
