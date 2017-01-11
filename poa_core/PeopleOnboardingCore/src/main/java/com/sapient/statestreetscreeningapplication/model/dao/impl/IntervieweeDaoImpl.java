package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao;
import com.sapient.statestreetscreeningapplication.model.dao.LocationDao;
import com.sapient.statestreetscreeningapplication.model.dao.PositionDao;
import com.sapient.statestreetscreeningapplication.model.dao.StatusDao;
import com.sapient.statestreetscreeningapplication.model.entity.LocationNew;
import com.sapient.statestreetscreeningapplication.model.entity.OnboardingCheckList;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;
import com.sapient.statestreetscreeningapplication.model.entity.Position;
import com.sapient.statestreetscreeningapplication.model.entity.ScoresNew;
import com.sapient.statestreetscreeningapplication.model.entity.TempPerson;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonScreeningDetailsBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonScreeningDetailsConvertor;

// TODO: Auto-generated Javadoc
/**
 * The Class IntervieweeDaoImpl.
 */
@Component
public class IntervieweeDaoImpl implements IntervieweeDao {

	

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/** The status dao. */
	@Autowired
	private StatusDao statusDao;
	
	/** The position dao. */
	@Autowired
	private PositionDao positionDao;

	/** The location dao. */
	@Autowired
	private LocationDao locationDao;
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao#markIntervieweeAsExited(long)
	 */
	@Override
	@Transactional
	public void markIntervieweeAsExited(long id) {
		CustomLoggerUtils.INSTANCE.log.info("inside markIntervieweeAsExited(long id) method of IntervieweeDaoImpl class");
		PersonScreeningDetails personScreeningDetails = entityManager.find(PersonScreeningDetails.class,id);
		if(!personScreeningDetails.getPerson().getIsTemp()){
			personScreeningDetails.getPerson().setHasExited(true);
		}
		
		entityManager.merge(personScreeningDetails);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao#searchIntervieweeByOracleIdOrDate(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> searchIntervieweeByOracleIdOrDate(int start,int amount, String orderingColumn, String orderingDirection,String searchKey) {
		CustomLoggerUtils.INSTANCE.log.info("inside searchIntervieweeByOracleIdOrDate(int start,int amount, String orderingColumn, String orderingDirection,String searchKey) method of IntervieweeDaoImpl class");
		String searchQuery;
		List<PersonScreeningDetails> list;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		int count;

		searchKey = "'%" + searchKey + "%'";
		/*searchQuery = " and (I.PERSON_ID like " + searchKey
				+ " OR I.SCREENING_START_DATE like " + searchKey + " OR I.SCREENING_END_DATE like "
				+ searchKey + ") ";*/
		searchQuery = " and (P.PERSON_ORACLE_ID like " + searchKey
				+ " OR I.SCREENING_START_DATE like " + searchKey + " OR I.SCREENING_END_DATE like "
				+ searchKey + ") ";
		list = retrieveInterviewees(start, amount, orderingColumn,
				orderingDirection, searchQuery);
		count = getCount(start, amount, orderingColumn, orderingDirection,
				searchQuery);
		dataMap.put("intervieweeList", list);
		dataMap.put("count", count);
		return dataMap;
	}
	
	@Override
	public Person findTempPersonDetailsFromScreeningId(
			long personScreeningDetailsId){
		CustomLoggerUtils.INSTANCE.log.info("inside sfindTempPersonDetailsFromScreeningId(long personScreeningDetailsId method of IntervieweeDaoImpl class");
		
		String q1="from PersonScreeningDetails where PERSON_SCREENING_ID=?";
		TypedQuery<PersonScreeningDetails> t1 = entityManager.createQuery(q1,
				PersonScreeningDetails.class);

		t1.setParameter(1, personScreeningDetailsId);
		PersonScreeningDetails psd=t1.getSingleResult();
		
		
		return psd.getPerson();
		
	}
	public TempPerson findTempPersonDetailsFromTempId(long tempPersonId){
		CustomLoggerUtils.INSTANCE.log.info("inside findTempPersonDetailsFromTempId(long tempPersonId method of IntervieweeDaoImpl class");
		
		String q1="from TempPerson where Temp_Person_ID=?";
		TypedQuery<TempPerson> t1 = entityManager.createQuery(q1,
				TempPerson.class);

		t1.setParameter(1, tempPersonId);
		TempPerson tempPerson=t1.getSingleResult();
		
		
		return tempPerson;
		
	}
	/*public Person findTempPersonDetailsFromStaffingId(
			long personStaffingId){
		CustomLoggerUtils.INSTANCE.log.info("inside sfindTempPersonDetailsFromScreeningId(long personScreeningDetailsId method of IntervieweeDaoImpl class");
		
		String q1="from PersonScreeningDetails where PERSON_SCREENING_ID=?";
		TypedQuery<PersonScreeningDetails> t1 = entityManager.createQuery(q1,
				PersonScreeningDetails.class);

		t1.setParameter(1, personScreeningDetailsId);
		PersonScreeningDetails psd=t1.getSingleResult();
		
		
		return psd.getPerson();
		
	}*/

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao#searchIntervieweeByOracleId(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> searchIntervieweeByOracleId(int start,int amount, String orderingColumn,String orderingDirection,String searchKey) {
		CustomLoggerUtils.INSTANCE.log.info("inside searchIntervieweeByOracleId(int start,int amount, String orderingColumn,String orderingDirection,String searchKey) method of IntervieweeDaoImpl class");
		String searchQuery;
		List<PersonScreeningDetails> list;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		int count;

		searchKey = "'%" + searchKey + "%'";
		searchQuery = " and (I.PERSON_ID like " + searchKey + ") ";
		list = retrieveInterviewees(start, amount, orderingColumn,
				orderingDirection, searchQuery);
		count = getCount(start, amount, orderingColumn, orderingDirection,
				searchQuery);
		dataMap.put("intervieweeList", list);
		dataMap.put("count", count);
		return dataMap;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao#searchIntervieweeByOracleIdList(int, int, java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public Map<String, Object> searchIntervieweeByOracleIdList(int start,int amount, String orderingColumn, String orderingDirection,List<String> oracleIdList) {
		CustomLoggerUtils.INSTANCE.log.info("inside searchIntervieweeByOracleIdList(int start,int amount, String orderingColumn, String orderingDirection,List<String> oracleIdList) method of IntervieweeDaoImpl class");
		String searchQuery,searchKey=null;
		List<PersonScreeningDetails> list;
		Map<String, Object> dataMap = new HashMap<String, Object>();
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
		searchQuery = " and (P.PERSON_ORACLE_ID IN " + searchKey + ") ";
		CustomLoggerUtils.INSTANCE.log.info("searchQuery  "+searchQuery);
		list = retrieveIntervieweesByOracleIdList(start, amount, orderingColumn,
				orderingDirection, searchQuery);
		count = getCount(start, amount, orderingColumn, orderingDirection,
				searchQuery);
		dataMap.put("intervieweeList", list);
		dataMap.put("count", count);
		return dataMap;
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao#searchIntervieweeByDate(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> searchIntervieweeByDate(int start, int amount,String orderingColumn, String orderingDirection, String searchKey) {
		CustomLoggerUtils.INSTANCE.log.info("inside  searchIntervieweeByDate(int start, int amount,String orderingColumn, String orderingDirection, String searchKey) method of IntervieweeDaoImpl class");
		String searchQuery;
		List<PersonScreeningDetails> list;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		int count;

		searchKey = "'%" + searchKey + "%'";
		searchQuery = " and (I.SCREENING_START_DATE like " + searchKey + " OR I.SCREENING_END_DATE like "
				+ searchKey + " ) ";
		list = retrieveInterviewees(start, amount, orderingColumn,
				orderingDirection, searchQuery);
		count = getCount(start, amount, orderingColumn, orderingDirection,
				searchQuery);
		dataMap.put("intervieweeList", list);
		dataMap.put("count", count);
		return dataMap;
	}

	/**
	 * Retrieve interviewees.
	 *
	 * @param start the start
	 * @param amount the amount
	 * @param orderingColumn the ordering column
	 * @param orderingDirection the ordering direction
	 * @param searchQuery the search query
	 * @return the list
	 */
	
	// IMP: This function uses native SQL. Table name has to be updated when
	// changed. Ditto for columns.
	List<PersonScreeningDetails> retrieveInterviewees(int start, int amount,String orderingColumn, String orderingDirection, String searchQuery) {
		CustomLoggerUtils.INSTANCE.log.info("inside retrieveInterviewees(int start, int amount,String orderingColumn, String orderingDirection, String searchQuery) method of IntervieweeDaoImpl class");
		
		//String selectQuery = "select * from Person_Screening_Details I LEFT JOIN person as P ON I.person_Id=P.PERSON_ID LEFT JOIN project_new ON I.PROJECT_ID=project_new.PROJECT_ID LEFT JOIN status ON I.STATUS_ID=status.STATUS_ID LEFT JOIN category ON I.CATEGORY_ID=category.CATEGORY_ID where P.HAS_EXITED = 0";
			
				String selectQuery = "select * from Person_Screening_Details I LEFT JOIN person as P ON I.person_Id=P.PERSON_ID LEFT JOIN project_new ON I.PROJECT_ID=project_new.PROJECT_ID LEFT JOIN status ON I.STATUS_ID=status.STATUS_ID inner JOIN category ON I.CATEGORY_ID=category.CATEGORY_ID  where P.HAS_EXITED = 0"
				+ searchQuery
				+ " order by "
				+ orderingColumn
				+ " "
				+ orderingDirection + " limit " + start + ", " + amount + "";
		Query p = entityManager.createNativeQuery(selectQuery,
			PersonScreeningDetails.class);
		CustomLoggerUtils.INSTANCE.log.info("selectQuery "+ selectQuery);
		@SuppressWarnings("unchecked")
		List<PersonScreeningDetails> list = p.getResultList();
		CustomLoggerUtils.INSTANCE.log.info("query: " + selectQuery);
		if (list != null) {
			CustomLoggerUtils.INSTANCE.log.info("IntervieweeDao.popInterviewee. Size of interviewees retrived:"
							+ list.size());
		} else {
			CustomLoggerUtils.INSTANCE.log.info("No interviewees found");
		}
		return list;
	}
	
	/**
	 * Retrieve interviewees by oracle id list.
	 *
	 * @param start the start
	 * @param amount the amount
	 * @param orderingColumn the ordering column
	 * @param orderingDirection the ordering direction
	 * @param searchQuery the search query
	 * @return the list
	 */
	// IMP: This function uses native SQL. Table name has to be updated when
	// changed. Ditto for columns.
	List<PersonScreeningDetails> retrieveIntervieweesByOracleIdList(int start, int amount,String orderingColumn, String orderingDirection, String searchQuery) {
		CustomLoggerUtils.INSTANCE.log.info("inside  retrieveIntervieweesByOracleIdList(int start, int amount,String orderingColumn, String orderingDirection, String searchQuery) method of IntervieweeDaoImpl class");
		String selectQuery = "select * from person_screening_details as I LEFT JOIN person as P ON I.PERSON_ID=P.PERSON_ID LEFT JOIN project_new ON I.PROJECT_ID=project_new.PROJECT_ID LEFT JOIN status ON I.STATUS_ID=status.STATUS_ID where P.HAS_EXITED = 0"
				+ searchQuery
				+ " order by "
				+ orderingColumn
				+ " "
				+ orderingDirection + " limit " + start + ", " + amount + "";
		CustomLoggerUtils.INSTANCE.log.info("selectQuery "+ selectQuery);
		Query p = entityManager.createNativeQuery(selectQuery,
				PersonScreeningDetails.class);
		@SuppressWarnings("unchecked")
		List<PersonScreeningDetails> list = p.getResultList();
		CustomLoggerUtils.INSTANCE.log.info("query: " + selectQuery);
		if (list != null) {
			CustomLoggerUtils.INSTANCE.log.info("IntervieweeDao.popInterviewee. Size of interviewees retrived:"
							+ list.size());
		} else {
			CustomLoggerUtils.INSTANCE.log.info("No interviewees found");
		}
		return list;
	}

	/**
	 * Gets the count.
	 *
	 * @param start the start
	 * @param amount the amount
	 * @param orderingColumn the ordering column
	 * @param orderingDirection the ordering direction
	 * @param searchQuery the search query
	 * @return the count
	 */
	// IMP: This function uses native SQL. Table name has to be updated when
	// changed. Ditto for columns.
	public int getCount(int start, int amount, String orderingColumn,String orderingDirection, String searchQuery) {
		CustomLoggerUtils.INSTANCE.log.info("inside getCount(int start, int amount, String orderingColumn,String orderingDirection, String searchQuery) method of IntervieweeDaoImpl class");
		String getCountQuery = "select count(*) from person_screening_details as I LEFT JOIN person as P ON I.PERSON_ID=P.PERSON_ID LEFT JOIN project_new ON I.PROJECT_ID=project_new.PROJECT_ID LEFT JOIN status ON I.STATUS_ID=status.STATUS_ID "
				+ searchQuery
				+ " order by "
				+ orderingColumn
				+ " "
				+ orderingDirection;
		Query p = entityManager.createNativeQuery(getCountQuery);
		BigInteger count = (BigInteger) p.getSingleResult();
		CustomLoggerUtils.INSTANCE.log.info("count of interviewees retrieved: "
				+ count);
		return count.intValue();
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao#populateInterviewee(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	// IMP: This function uses native SQL. Table name has to be updated when
	// changed. Ditto for columns.
	public Map<String, Object> populateInterviewee(int start, int amount,String orderingColumn, String orderingDirection, String searchKey) {
		CustomLoggerUtils.INSTANCE.log.info("inside populateInterviewee(int start, int amount,String orderingColumn, String orderingDirection, String searchKey) method of IntervieweeDaoImpl class");
		// search by either (location project position status) or retrieve all
		String searchQuery = "", positionSearchKey="",locationSearchKey="";
		List<PersonScreeningDetails> list;
		List<Long> positionIdList = new ArrayList<Long>();
		List<Long> locationIdList = new ArrayList<Long>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		int count=0, check=0;

		if (searchKey != null && !searchKey.isEmpty()) {
			
			searchKey = "'%" + searchKey + "%'";
			if(!positionDao.getAllPositionsByName(searchKey).isEmpty()){
				for (Position position : positionDao.getAllPositionsByName(searchKey)){
					positionIdList.add(position.getPositionId());
				}
				CustomLoggerUtils.INSTANCE.log.info(positionIdList.toString());
				for(Long temp : positionIdList){
					if(count==0){
						positionSearchKey=""+temp+"";
					}
					else{
						positionSearchKey+=""+temp+"";
					}
					count++;
					if(count < positionIdList.size()){
						positionSearchKey+=",";
					}
				}
				positionSearchKey = "(" + positionSearchKey + ")";
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
			if(positionSearchKey != ""){
				searchQuery += " P.POSITION_ID IN " + positionSearchKey;
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
			searchQuery += " project_new.PROJECT_NAME like " + searchKey
					+ " OR status.RESULT_NAME like " + searchKey
					+ " OR status.STATUS_NAME like " + searchKey + ") ";
			
			CustomLoggerUtils.INSTANCE.log.info("SEARCH QUERY "+ searchQuery);
		}
		list = retrieveInterviewees(start, amount, orderingColumn,
				orderingDirection, searchQuery);
		count = getCount(start, amount, orderingColumn, orderingDirection,
				searchQuery);
		dataMap.put("intervieweeList", list);
		dataMap.put("count", count);
		return dataMap;
	}

	@Override
	public List<PersonScreeningDetailsBean> getIntervieweeList(int start, int rows, String columnName, String searchKey,
			String sortDirection) {
		
		String searchQuery = "", positionSearchKey="",locationSearchKey="";
		
		List<PersonScreeningDetails> personScreeningDetailsList;
		List<Long> positionIdList = new ArrayList<Long>();
		List<Long> locationIdList = new ArrayList<Long>();
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		int count=0, check=0;

		if (searchKey != null && !searchKey.isEmpty()) {
			
			searchKey = "'%" + searchKey + "%'";
			if(!positionDao.getAllPositionsByName(searchKey).isEmpty()){
				for (Position position : positionDao.getAllPositionsByName(searchKey)){
					positionIdList.add(position.getPositionId());
				}
				CustomLoggerUtils.INSTANCE.log.info(positionIdList.toString());
				for(Long temp : positionIdList){
					if(count==0){
						positionSearchKey=""+temp+"";
					}
					else{
						positionSearchKey+=""+temp+"";
					}
					count++;
					if(count < positionIdList.size()){
						positionSearchKey+=",";
					}
				}
				positionSearchKey = "(" + positionSearchKey + ")";
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
			if(positionSearchKey != ""){
				searchQuery += " P.POSITION_ID IN " + positionSearchKey;
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
			searchQuery += " project_new.PROJECT_NAME like " + searchKey
					+ " OR status.RESULT_NAME like " + searchKey
					+ " OR status.STATUS_NAME like " + searchKey + ") ";
			
			CustomLoggerUtils.INSTANCE.log.info("SEARCH QUERY "+ searchQuery);
		}
		
		personScreeningDetailsList = retrieveInterviewees(start, rows, columnName,
				sortDirection, searchQuery);
		List<PersonScreeningDetailsBean> personScreeningDetailsBeansList= new ArrayList<>(3);
		for(PersonScreeningDetails personScreeningDetails:personScreeningDetailsList)
		{
			personScreeningDetailsBeansList.add(PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(personScreeningDetails));
		}
		
		return personScreeningDetailsBeansList;
		
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao#saveInterviewee(com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails)
	 */
	@Override
	@Transactional
	public PersonScreeningDetails saveInterviewee(PersonScreeningDetails personScreeningDetails) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveInterviewee(PersonScreeningDetails personScreeningDetails) method of IntervieweeDaoImpl class");
		PersonScreeningDetails pScreeningDetails=null;
		Person personFromOracleId = null,tempPersonFromOracleId = null;
		Person person;
		TempPerson temp = null;
		if( personScreeningDetails.getPerson().getIsTemp()!=null && !personScreeningDetails.getPerson().getIsTemp()){
			String q1="from Person where personOracleId=?";
			TypedQuery<Person> tp1=entityManager.createQuery(q1, Person.class);
			tp1.setParameter(1, personScreeningDetails.getPerson().getPersonOracleId());
			try{
				personFromOracleId=tp1.getSingleResult();
			}catch(NoResultException e){
				
			}
			if(personFromOracleId!=null){
				personScreeningDetails.getPerson().setPersonId(personFromOracleId.getPersonId());
				personScreeningDetails.getPerson().setHasExited(personFromOracleId.getHasExited());
				personScreeningDetails.getPerson().setIsActive(personFromOracleId.getIsActive());
				personScreeningDetails.getPerson().setRole(personFromOracleId.getRole());
			}
		}else if(personScreeningDetails.getPerson().getIsTemp()!=null && personScreeningDetails.getPerson().getIsTemp()){
			String q2="from TempPerson where tempPersonEmail=?";
			TypedQuery<TempPerson> tp2=entityManager.createQuery(q2, TempPerson.class);
			tp2.setParameter(1, personScreeningDetails.getPerson().getTempPerson().getTempPersonEmail());
			try{
				temp=tp2.getSingleResult();
			}catch(NoResultException e){
				
			}
			if(temp!=null){
				String str="from Person where tempPerson=?";
				TypedQuery<Person> tp3=entityManager.createQuery(str, Person.class);
				tp3.setParameter(1, temp);
				try{
					tempPersonFromOracleId=tp3.getSingleResult();
				}catch(NoResultException e){
					
				}
				if(tempPersonFromOracleId!=null){
					personScreeningDetails.getPerson().setPersonId(tempPersonFromOracleId.getPersonId());
					personScreeningDetails.getPerson().getTempPerson().setTempPersonId(temp.getTempPersonId());
					personScreeningDetails.getPerson().setHasExited(tempPersonFromOracleId.getHasExited());
					personScreeningDetails.getPerson().setIsActive(tempPersonFromOracleId.getIsActive());
					personScreeningDetails.getPerson().setRole(tempPersonFromOracleId.getRole());
				}
			}
		}
		
		if(personScreeningDetails.getPerson().getHasExited()==null){
			personScreeningDetails.getPerson().setHasExited(false);
		}
		
		if(personScreeningDetails.getPerson().getIsActive()==null){
			personScreeningDetails.getPerson().setIsActive(false);
		}
		try{
			 person=entityManager.merge(personScreeningDetails.getPerson());
			personScreeningDetails.setPerson(person);
			 pScreeningDetails=entityManager.merge(personScreeningDetails);
		}catch(javax.persistence.PersistenceException e){

			e.printStackTrace();
			


		}
		
		//entityManager.persist(personScreeningDetails);
		return pScreeningDetails;
	}

	
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao#fetchPersonScreeningDetails(long)
	 */
	@Override
	public PersonScreeningDetails fetchPersonScreeningDetails(long Id) {
		CustomLoggerUtils.INSTANCE.log.info("inside  fetchPersonScreeningDetails(long Id) method of IntervieweeDaoImpl class");
		CustomLoggerUtils.INSTANCE.log.info("Fetching PersonScreeningDetails ");
		PersonScreeningDetails personScreeningDetails = entityManager.find(PersonScreeningDetails.class,Id);
		return personScreeningDetails;
	}

	

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao#getIntervieweeByOracleId(int)
	 */
	@Override
	public Person getIntervieweeByOracleId(int numericCellValue) {
		CustomLoggerUtils.INSTANCE.log.info("inside getIntervieweeByOracleId(int numericCellValue) method of IntervieweeDaoImpl class");
	List<Person> list=null;
	List<PersonScreeningDetails> list2=null;
	Person interviewee = null;
	PersonScreeningDetails personScreeningDetails = null;
	String query = "from Person where person_id=:oracleID";
	String query2 = "from PersonScreeningDetails where person_id=:oracleID";
	
	/*TypedQuery<Interviewee> p = entityManager.createQuery(query,
	Interviewee.class);*/
	
	TypedQuery<Person> p = entityManager.createQuery(query,
			Person.class);
	
	TypedQuery<PersonScreeningDetails> p2 = entityManager.createQuery(query2,
			PersonScreeningDetails.class);

	p.setParameter("person_id", numericCellValue);
	p2.setParameter("person_id", numericCellValue);

	try {
		list =p.getResultList();
		list2= p2.getResultList();
	} catch (NoResultException e) {
		CustomLoggerUtils.INSTANCE.log.error("interviewee not found: "
				+ numericCellValue);
		return null;
	} catch (NonUniqueResultException e) {
		CustomLoggerUtils.INSTANCE.log.error("more than one record for the same interviewee found in the database");
	}
	if(list.size()==1)
	{
		interviewee=list.get(0);
		personScreeningDetails = list2.get(0);
	}
	else
	{
		for(Person inter:list)
		{
			if(!personScreeningDetails.getStatus().equals("Rejected")){
				interviewee=inter;
			}
		}
	}
	CustomLoggerUtils.INSTANCE.log.info("return interviwee "+interviewee.toString());
	return interviewee ;
	}
	
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao#updateInterviewee(com.sapient.statestreetscreeningapplication.model.entity.Interviewee)
	 */
	@Override
	@Transactional
	public void updateInterviewee(PersonScreeningDetails personScreeningDetails) {
		CustomLoggerUtils.INSTANCE.log.info("inside updateInterviewee(PersonScreeningDetails personScreeningDetails) method of IntervieweeDaoImpl class");
		if(personScreeningDetails.getPerson().getTempPerson()!=null){
			TempPerson tempPerson=entityManager.merge(personScreeningDetails.getPerson().getTempPerson());
			personScreeningDetails.getPerson().setTempPerson(tempPerson);
		}
		
		Person person=entityManager.merge(personScreeningDetails.getPerson());
		personScreeningDetails.setPerson(person);
	
		PersonScreeningDetails temp = entityManager.find(PersonScreeningDetails.class,personScreeningDetails.getPersonScreeningId());
		String currentComments = temp.getFeedback();
		/*entityManager.remove(temp);
		personScreeningDetails.setPersonScreeningId(0l);*/
		
		Set<ScoresNew> existingList = temp.getScoreList();
		Set<ScoresNew> newScoreList = personScreeningDetails.getScoreList();
		Iterator<ScoresNew> exitingItr = existingList.iterator();
		while(exitingItr.hasNext()){
			ScoresNew score = exitingItr.next();
			if(!newScoreList.contains(score)){
				entityManager.remove(score);
			}
		}
		if (!personScreeningDetails.getFeedback().equals("")) {
			personScreeningDetails.setFeedback(personScreeningDetails.getFeedback() + "\n\n" + currentComments);
		}
		else{
			personScreeningDetails.setFeedback(currentComments);
		}
		personScreeningDetails.setScoreList(personScreeningDetails.getScoreList());
		temp.setCategory(personScreeningDetails.getCategory());
		temp.setFeedback(personScreeningDetails.getFeedback());
		
		Boolean is_active=false,has_exited=false;
		if(personScreeningDetails.getPerson().getHasExited()==null){
			personScreeningDetails.getPerson().setHasExited(has_exited);
		}
		if(personScreeningDetails.getPerson().getIsActive()==null){
			personScreeningDetails.getPerson().setActive(is_active);
		}
		temp.setPerson(personScreeningDetails.getPerson());
		temp.setPersonScreeningId(personScreeningDetails.getPersonScreeningId());
		temp.setProjectNew(personScreeningDetails.getProjectNew());
		temp.setScoreList(personScreeningDetails.getScoreList());
		temp.setScreener(personScreeningDetails.getScreener());
		temp.setScreeningEndDate(personScreeningDetails.getScreeningEndDate());
		temp.setScreeningStartDate(personScreeningDetails.getScreeningStartDate());
		temp.setStatus(personScreeningDetails.getStatus());
		
		
		

		entityManager.merge(temp);
		
	}

	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao#changeStatus(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public void changeStatus(List<Long> intervieweeNos, String status,String result) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeStatus(List<Long> intervieweeNos, String status,String result)  method of IntervieweeDaoImpl class");
		for(long id:intervieweeNos){
			changeStatus(id, status, result);
		}
	}
	


	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager the new entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Gets the status dao.
	 *
	 * @return the status dao
	 */
	public StatusDao getStatusDao() {
		return statusDao;
	}

	/**
	 * Sets the status dao.
	 *
	 * @param statusDao the new status dao
	 */
	public void setStatusDao(StatusDao statusDao) {
		this.statusDao = statusDao;
	}
	
	/**
	 * Gets the position dao.
	 *
	 * @return the position dao
	 */
	public PositionDao getPositionDao() {
		return positionDao;
	}

	/**
	 * Sets the position dao.
	 *
	 * @param positionDao the new position dao
	 */
	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}
	

	/**
	 * Gets the location dao.
	 *
	 * @return the location dao
	 */
	public LocationDao getLocationDao() {
		return locationDao;
	}

	/**
	 * Sets the location dao.
	 *
	 * @param locationDao the new location dao
	 */
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao#updateExistingInterviewee(com.sapient.statestreetscreeningapplication.model.entity.Person)
	 */
	@Override
	@Transactional
	public void updateExistingInterviewee(Person interviewee) {
		CustomLoggerUtils.INSTANCE.log.info("inside updateExistingInterviewee(Person interviewee) method of IntervieweeDaoImpl class");
		Person interviewee1=getIntervieweeByOracleId(interviewee.getPersonId());
		interviewee1.setPersonNtId((interviewee.getPersonNtId()));
		 entityManager.merge(interviewee1);	
	}


	@Override
	@Transactional
	public PersonScreeningDetails fetchPersonScreeningDetailsByOracleID(int personId) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchPersonScreeningDetailsByOracleID(int personId) method of IntervieweeDaoImpl class");
		PersonScreeningDetails personScreeningDetails;
		String query = "select * from person_screening_details as I LEFT JOIN person as P ON I.PERSON_ID=P.PERSON_ID LEFT JOIN category ON I.CATEGORY_ID=category.CATEGORY_ID LEFT JOIN project_new ON I.PROJECT_ID=project_new.PROJECT_ID LEFT JOIN status ON I.STATUS_ID=status.STATUS_ID where P.PERSON_ID =:personId";
		Query p = entityManager.createNativeQuery(query,
				PersonScreeningDetails.class);
		p.setParameter("personId",personId);
		
		try {
			personScreeningDetails = (PersonScreeningDetails) p.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return personScreeningDetails;
	}
	
	@Override
	public List<PersonScreeningDetails> fetchMultiplePersonScreeningDetailsByOracleID(int personOracleId){
		CustomLoggerUtils.INSTANCE.log.info("inside fetchMultiplePersonScreeningDetailsByOracleID(int personId) method of IntervieweeDaoImpl class");
		List<PersonScreeningDetails> personScreeningDetailslist;
		String query = "select * from person_screening_details as I LEFT JOIN person as P ON I.PERSON_ID=P.PERSON_ID LEFT JOIN category ON I.CATEGORY_ID=category.CATEGORY_ID LEFT JOIN project_new ON I.PROJECT_ID=project_new.PROJECT_ID LEFT JOIN status ON I.STATUS_ID=status.STATUS_ID where P.PERSON_ORACLE_ID =:personId";
		Query p = entityManager.createNativeQuery(query,
				PersonScreeningDetails.class);
		p.setParameter("personId",personOracleId);
		
		try {
			personScreeningDetailslist =  p.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return personScreeningDetailslist;
	}
	public List<PersonScreeningDetails> fetchMultiplePersonScreeningDetailsByPersonID(int personId){
		CustomLoggerUtils.INSTANCE.log.info("inside fetchMultiplePersonScreeningDetailsByOracleID(int personId) method of IntervieweeDaoImpl class");
		List<PersonScreeningDetails> personScreeningDetailslist;
		String query = "select * from person_screening_details as I LEFT JOIN person as P ON I.PERSON_ID=P.PERSON_ID LEFT JOIN category ON I.CATEGORY_ID=category.CATEGORY_ID LEFT JOIN project_new ON I.PROJECT_ID=project_new.PROJECT_ID LEFT JOIN status ON I.STATUS_ID=status.STATUS_ID where P.PERSON_ID =:personId";
		Query p = entityManager.createNativeQuery(query,
				PersonScreeningDetails.class);
		p.setParameter("personId",personId);
		
		try {
			personScreeningDetailslist =  p.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return personScreeningDetailslist;
	}
	
	@Override
	public String  checkIfDuplicateIntervieweeEntryExists(int intervieweePersonId,int screenerPersonId,String startDate){
		CustomLoggerUtils.INSTANCE.log.info("inside checkIfDuplicateIntervieweeEntryExists(int intervieweeOracleId,int screenerOracleId,String startDate) method of IntervieweeDaoImpl class");
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		
		String s1="from Person where personOracleId=?";
		TypedQuery<Person> t1=entityManager.createQuery(s1, Person.class);
		t1.setParameter(1, intervieweePersonId);
		Person interviewee;
		try{
			interviewee = t1.getSingleResult();
		}catch(NoResultException e){
			return "unique";
		}
		
		String s2="from Person where personOracleId=?";
		TypedQuery<Person> t2=entityManager.createQuery(s2, Person.class);
		
		t2.setParameter(1, screenerPersonId);
		Person interviewer = t2.getSingleResult();
		
//		String q1="from Person where PERSON_ID=?";
//		TypedQuery<Person> tq = entityManager.createQuery(q1,
//				Person.class);
//		tq.setParameter(1, screenerPersonId);
//		Person screener=tq.getSingleResult();
//		
//		String q2="from Person where PERSON_ID=?";
//		TypedQuery<Person> tq1 = entityManager.createQuery(q2,
//				Person.class);
//		tq1.setParameter(1, intervieweePersonId);
//		Person interviewer=tq1.getSingleResult();
		
		String query = "from PersonScreeningDetails where screeningStartDate=:screeningStartDate and screener=:screener and person=:person";
		
		List<PersonScreeningDetails> list;
		TypedQuery<PersonScreeningDetails> p = entityManager.createQuery(query,
				PersonScreeningDetails.class);
		
	
		try {
			p.setParameter("screeningStartDate",formatter.parse(startDate));
			p.setParameter("screener", interviewer);
			p.setParameter("person", interviewee);
		
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		

		try {
			list =p.getResultList();
			if(list != null && list.size()==0){
				return "unique";
			}
		} catch (NoResultException e) {
			return "unique";
		} 
		return "duplicate";
		
	}
	@Override
	public String  checkIfDuplicateIntervieweeEntryExistsForTemp(String tempEmail,int screenerOracleId,String startDate){
		CustomLoggerUtils.INSTANCE.log.info("inside checkIfDuplicateIntervieweeEntryExistsForTemp(String tempEmail,int screenerOracleId,String startDate) method of IntervieweeDaoImpl class");
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		String q1="from TempPerson where TEMP_PERSON_EMAIL=?";
		
		TypedQuery<TempPerson> p1 = entityManager.createQuery(q1,
				TempPerson.class);
		
	
		p1.setParameter(1,tempEmail);
		TempPerson tempPerson;
		

		try{
		tempPerson =p1.getSingleResult();
		if(tempPerson== null ){
			return "unique";
		}
	} catch (NoResultException e) {
		return "unique";
	} 
		
		String q2="from Person where tempPerson=?";
		
		TypedQuery<Person> p2 = entityManager.createQuery(q2,
				Person.class);
		
	
		p2.setParameter(1,tempPerson);
		

		Person person;
		try{
			person =p2.getSingleResult();
		}catch (NoResultException e) {
			return "unique";
		} 
		
		
		String q3="from Person where personOracleId=?";
		
		TypedQuery<Person> p3 = entityManager.createQuery(q3,
				Person.class);
		
	
		p3.setParameter(1,screenerOracleId);
		

		
		Person screener =p3.getSingleResult();
		
		
		String query = "from PersonScreeningDetails where screeningStartDate=? and screener=? and person=?";
		
		List<PersonScreeningDetails> list;
		TypedQuery<PersonScreeningDetails> p = entityManager.createQuery(query,
				PersonScreeningDetails.class);
		
	
		try {
			p.setParameter(1,formatter.parse(startDate));
			p.setParameter(2, screener);
			p.setParameter(3, person);
		
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		

		try {
			list =p.getResultList();
			if(list != null && list.size()==0){
				return "unique";
			}
		} catch (NoResultException e) {
			return "unique";
		} 
		return "duplicate";
		
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<PersonScreeningDetails> retrieveTempInterviewees(int start, int amount,String orderingColumn, String orderingDirection,String searchKey) {
		CustomLoggerUtils.INSTANCE.log.info("inside retrieveTempInterviewees(int start, int amount,String orderingColumn, String orderingDirection, String searchQuery) method of IntervieweeDaoImpl class");
		String selectQuery = "select * from person_screening_details as I LEFT JOIN (person as P inner join TEMP_PERSON as t on t.TEMP_PERSON_ID=P.TEMP_PERSON_ID)   ON I.PERSON_ID=P.PERSON_ID LEFT JOIN project_new ON I.PROJECT_ID=project_new.PROJECT_ID LEFT JOIN status ON I.STATUS_ID=status.STATUS_ID where P.HAS_EXITED = 0"
				+ " and t.TEMP_PERSON_NAME like '"
				+searchKey +"%' "
				+ " order by "
				+ orderingColumn
				+ " "
				+ orderingDirection + " limit " + start + ", " + amount + "";
		Query p = entityManager.createNativeQuery(selectQuery,
			PersonScreeningDetails.class);
		CustomLoggerUtils.INSTANCE.log.info("selectQuery "+ selectQuery);
		
		List<PersonScreeningDetails> list ;
		try{
			list= p.getResultList();
		}catch(NoResultException e){
			CustomLoggerUtils.INSTANCE.log.info("No interviewees found, returning null.");
			return null;
		}
		
		CustomLoggerUtils.INSTANCE.log.info("query: " + selectQuery);
		if (list != null) {
			CustomLoggerUtils.INSTANCE.log.info("IntervieweeDao.popInterviewee. Size of interviewees retrived:"
							+ list.size());
		} else {
			CustomLoggerUtils.INSTANCE.log.info("No interviewees found");
		}
		return list;
	}
	public int getTempPersonCount(int start, int amount, String orderingColumn,String orderingDirection, String searchQuery) {
		CustomLoggerUtils.INSTANCE.log.info("inside getCount(int start, int amount, String orderingColumn,String orderingDirection, String searchQuery) method of IntervieweeDaoImpl class");
		String getCountQuery = "select * from person_screening_details as I LEFT JOIN (person as P inner join TEMP_PERSON as t on t.TEMP_PERSON_ID=P.TEMP_PERSON_ID)   ON I.PERSON_ID=P.PERSON_ID LEFT JOIN project_new ON I.PROJECT_ID=project_new.PROJECT_ID LEFT JOIN status ON I.STATUS_ID=status.STATUS_ID where P.HAS_EXITED = 0"
				+ " and t.TEMP_PERSON_NAME like '"
				+ searchQuery +"%' "
				+ " order by "
				+ orderingColumn
				+ " "
				+ orderingDirection;
		/*Query p = entityManager.createNativeQuery(getCountQuery);
		BigInteger count = (BigInteger) p.getSingleResult();
		CustomLoggerUtils.INSTANCE.log.info("count of interviewees retrieved: "
				+ count);*/
		Query p = entityManager.createNativeQuery(getCountQuery,
				PersonScreeningDetails.class);
		List<PersonScreeningDetails> list ;
		try{
			list= p.getResultList();
		}catch(NoResultException e){
			CustomLoggerUtils.INSTANCE.log.info("No interviewees found, returning null.");
			return 0;
		}
		return list.size();
	}

	@Override
	@Transactional
	public PersonScreeningDetails updateExistingPersonScreeningDetails(PersonScreeningDetails personScreeningDetails) {
		CustomLoggerUtils.INSTANCE.log.info("inside updateExistingPersonScreeningDetails(PersonScreeningDetails personScreeningDetails) method of IntervieweeDaoImpl class " );
		/*entityManager.flush();
		entityManager.clear();*/
		PersonScreeningDetails psd=entityManager.merge(personScreeningDetails);
		return psd;
		
	}

	@Override
	public boolean delete(List<Long> intervieweeNos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void changeStatus(long intervieweeNo, String status, String result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Long> fetchIntervieweeIdsByStatusId(long statusId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PersonScreeningDetails fetchPersonScreeningDetailsByOnboardID(
			Long onboardingCheckListId){
		
		OnboardingCheckList onboardingCheckList=entityManager.find(OnboardingCheckList.class, onboardingCheckListId);
		return onboardingCheckList.getPersonScreeningDetails();
	}

	@Override
	public List<PersonScreeningDetails> getAllPersonScreeningDetails() {
		// TODO Auto-generated method stub
		String sqlQuery= "select * from person_screening_details as I LEFT JOIN (person as P inner join TEMP_PERSON as t on t.TEMP_PERSON_ID=P.TEMP_PERSON_ID)   ON I.PERSON_ID=P.PERSON_ID LEFT JOIN project_new ON I.PROJECT_ID=project_new.PROJECT_ID LEFT JOIN status ON I.STATUS_ID=status.STATUS_ID where P.HAS_EXITED = 0"; 
		Query query = entityManager.createNativeQuery(sqlQuery,
				PersonScreeningDetails.class);
		List<PersonScreeningDetails> personScreeningDetailsList ;
		try{
			personScreeningDetailsList= query.getResultList();
		}catch(NoResultException e){
			CustomLoggerUtils.INSTANCE.log.info("No interviewees found, returning null.");
			return null;
		}
		return personScreeningDetailsList;
	}



	
}