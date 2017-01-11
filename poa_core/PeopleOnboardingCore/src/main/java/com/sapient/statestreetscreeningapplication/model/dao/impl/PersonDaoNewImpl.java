package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew;
import com.sapient.statestreetscreeningapplication.model.entity.MonthlyProjectBudgetEntity;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.entity.ProjectNew;
import com.sapient.statestreetscreeningapplication.model.entity.Role;
import com.sapient.statestreetscreeningapplication.model.entity.Status;
import com.sapient.statestreetscreeningapplication.model.entity.TempPerson;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.comparator.MapComparator;
import com.sapient.statestreetscreeningapplication.utils.enums.Months;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonDaoNewImpl.
 */
@Component
public class PersonDaoNewImpl implements PersonDaoNew {
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

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

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew#getPersonByOracleId(int)
	 */
	@Override
	@Transactional
	public Person getPersonByOracleId(int oracleId) {
		CustomLoggerUtils.INSTANCE.log.info("inside getPersonByOracleId "+ oracleId);
		Person person = null;
		String query = "from Person where personOracleId=:oracleId";
		TypedQuery<Person> s = entityManager.createQuery(query,
				Person.class);
		s.setParameter("oracleId", oracleId);

		try {
			person = s.getSingleResult();
		} catch (NoResultException e) {
			CustomLoggerUtils.INSTANCE.log
					.warn("person with that oracleId not found in the database");
			e.printStackTrace();
			return null;
		}
		CustomLoggerUtils.INSTANCE.log
				.info("Returing the following person entity from the database"
						+ person);
		return person;
	}
	@Override
	public Person getPersonNewBeanByPersonOracleId(int personOracleId){
		CustomLoggerUtils.INSTANCE.log.info("inside getPersonNewBeanByPersonOracleId "+  personOracleId +" method of PersonDaoNewImpl class");
		Person person = null;
		String query = "from Person where personOracleId=?";
		TypedQuery<Person> s = entityManager.createQuery(query,
				Person.class);
		s.setParameter(1, personOracleId);
		person = s.getSingleResult();
		return person;
	}
	@Override
	public Person getPersonByPersonId(int personId){
		CustomLoggerUtils.INSTANCE.log.info("inside getPersonByPersonId -> "+ personId);
		Person person = null;
		String query = "from Person where person_Id=?";
		TypedQuery<Person> s = entityManager.createQuery(query,
				Person.class);
		s.setParameter(1, personId);
		person = s.getSingleResult();
		return person;
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew#getPersonByNtId(java.lang.String)
	 */
	@Override
	@Transactional
	public Person getPersonByNtId(String personNtId) {
		CustomLoggerUtils.INSTANCE.log.info("inside getPersonByNtId: "+personNtId);
		Person person = null;
		String personDetails = "from Person where PERSON_NT_ID=:personNtId";
		TypedQuery<Person> s = entityManager.createQuery(personDetails,
				Person.class);
		s.setParameter("personNtId", personNtId);

		try {
			person = s.getSingleResult();
		} catch (NoResultException e) {
			CustomLoggerUtils.INSTANCE.log
					.warn("person with that oracleId not found in the database");
			e.printStackTrace();
		}
		CustomLoggerUtils.INSTANCE.log
				.info("Returing the following person entity from the database"
						+ person);
		return person;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew#registerNewPerson(com.sapient.statestreetscreeningapplication.model.entity.Person)
	 */
	@Override
	@Transactional
	public boolean registerNewPerson(Person person) {
		CustomLoggerUtils.INSTANCE.log.info("inside registerNewPerson Person: "+ person.toString()+"  method of PersonDaoNewImpl class");
		person.setActive(true);
		person.setHasExited(false);
		person.setSubscribeDailyMail(true);
		person.setSubscribeImmediateMail(true);
		entityManager.persist(person);
		return true;
	}
	@Override
	public List<TempPerson> getAllTempPersons(){
		CustomLoggerUtils.INSTANCE.log.info("inside getAllTempPersons()  method of PersonDaoNewImpl class");
		List<TempPerson> tempPersonTablelist = null;
		List<TempPerson> tempPersonlist = new ArrayList<TempPerson>();
		String tempPeople = "from TempPerson";
		TypedQuery<TempPerson> p = entityManager.createQuery(tempPeople,
				TempPerson.class);
		
		tempPersonTablelist = p.getResultList();
		
		for(TempPerson tp:tempPersonTablelist){
			String tempPerson = "from Person where TEMP_PERSON_ID =?";
			TypedQuery<Person> per = entityManager.createQuery(tempPerson,
					Person.class);
			per.setParameter(1, tp.getTempPersonId());
			Person person = null;
			try{
				 person = per.getSingleResult();
			}catch(NoResultException e){
				
			}
		
			if(person!=null && person.getIsTemp()){
				tempPersonlist.add(tp);
			}
		}
		return tempPersonlist;
		
	}
	@Override
	@Transactional
	public void addOracleIdForTempPerson(PersonBean personBean,long tempPersonId, int newOracleId){
		CustomLoggerUtils.INSTANCE.log.info("inside addOracleIdForTempPerson tempPersonId: "+tempPersonId+"newOracleId: "+newOracleId+" method of PersonDaoNewImpl class");
		String tempPerson = "from Person where TEMP_PERSON_ID = :tempPersonId";
		TypedQuery<Person> p = entityManager.createQuery(tempPerson,
				Person.class);
		p.setParameter("tempPersonId", tempPersonId);
		Person person = p.getSingleResult();
		person.setPersonOracleId(newOracleId);
		person.setPersonNtId(personBean.getUsername());
		Boolean notTrue=false;
		person.setIsTemp(notTrue);
		entityManager.merge(person);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew#validatePersonId(int)
	 */
	
	/**
	 * validates on PersonOracleId
	 */
	@Override
	@Transactional
	public int validatePersonId(int personOracleId) {
		CustomLoggerUtils.INSTANCE.log.info("inside validatePersonId for Person Oracle ID: "+personOracleId+"  method of PersonDaoNewImpl class");
		List<Person> personlist = null;
		String searchOid = "from Person Se where Se.personOracleId = :personId";
		TypedQuery<Person> p = entityManager.createQuery(searchOid,
				Person.class);
		p.setParameter("personId", personOracleId);
		personlist = p.getResultList();
		CustomLoggerUtils.INSTANCE.log.info("the size" + personlist.size());
		if (personlist.size() == 0)
			return 1;
		else {
			CustomLoggerUtils.INSTANCE.log.info("the person id  is present");
			if(personlist.get(0).getRole().isEmpty()){
				return 2;
			}else{
				return 3;
			}
			
			
		}
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew#getAllNonExitedPersonDetails()
	 */
	@Override
	@Transactional
	public List<Person> getAllNonExitedPersonDetails() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllNonExitedPersonDetails() method of PersonDaoNewImpl class ");

		List<Person> activeAndNotExitedPersons = new ArrayList<Person>();
		String query = "from Person where hasExited = :hasExited";
		TypedQuery<Person> p = entityManager.createQuery(query,
				Person.class);
		p.setParameter("hasExited", false);
		activeAndNotExitedPersons = p.getResultList();
		return activeAndNotExitedPersons;

	}
	public List<Person> getAllNonExitedAndNonTempPersonDetails() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllNonExitedPersonDetails() method of PersonDaoNewImpl class ");

		List<Person> activeAndNotExitedPersons = new ArrayList<Person>();
		String query = " from Person as p where p.role is not empty and p.hasExited = :hasExited and p.isTemp=:isTemp";
		TypedQuery<Person> p = entityManager.createQuery(query,
				Person.class);
		p.setParameter("hasExited", false);
		p.setParameter("isTemp", false);
		activeAndNotExitedPersons = p.getResultList();
		return activeAndNotExitedPersons;

	}
	
	public List<Person> getAllNonExitedAndNonTempPersonDetailsJDBC() {
		
		List<Map<String,Object>> rows=jdbcTemplate.queryForList("select distinct p from Person p join PERSON_ROLE pr on pr.person_id=p.person_id and p.has_exited=false and p.is_temp=false ");
		return personMapper(rows);
	}
	
	private List<Person> personMapper(List<Map<String,Object>> rows)
	{
		List<Person> personList = new ArrayList<Person>();

		for (Map row : rows) {
			Person person = new Person();

			person.setBirthDay((Integer) row.get("birth_day"));
			person.setBirthMonth((String) row.get("birth_month"));
			person.setHasExited((Boolean) row.get("has_exited"));
			person.setIsActive((Boolean) row.get("is_active"));
			person.setIsContractor((Boolean) row.get("is_contractors"));
			person.setIsTemp((Boolean) row.get("is_temp"));
			person.setLocation(null);
			person.setPersonClientId((String) row.get("person_client_id"));
			person.setPersonId((Integer) row.get("person_id"));
			person.setPersonNtId((String) row.get("person_nt_id"));
			person.setPersonOracleId((Integer) row.get("person_oracle_id"));
			person.setPosition(null);
			person.setSubscribeDailyMail((Boolean) row.get("subscribe_daily_mail"));
			person.setSubscribeImmediateMail((Boolean) row.get("subscribe_immediate_mail"));
			person.setSupervisorId((Integer) row.get("supervisor_id"));
			person.setSupervisorNtId((String) row.get("supervisor_nt_id"));
			person.setTempPerson(null);
			personList.add(person);

		}

		return personList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew#markPersonAsExited(com.sapient.statestreetscreeningapplication.model.entity.Person)
	 */
	@Override
	@Transactional
	public void markPersonAsExited(Person person) {
		CustomLoggerUtils.INSTANCE.log.info("inside markPersonAsExited Person: "+ person.toString() + "method of PersonDaoNewImpl class "+person.getPersonNtId());
	
		String markAsExitedQuery = "from Person where personId = :personId";
		TypedQuery<Person> p = entityManager.createQuery(markAsExitedQuery,
				Person.class);
		p.setParameter("personId", person.getPersonId());
		Person exitedPerson = p.getSingleResult();
		exitedPerson.setActive(false);
		exitedPerson.setHasExited(true);
		entityManager.merge(exitedPerson);
		CustomLoggerUtils.INSTANCE.log
				.info("Exiting markPersonAsExited method in PersonDaoNewImpl"
						+ person.getPersonNtId());

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew#deletePerson(int)
	 */
	@Override
	@Transactional
	public boolean deletePerson(int personId) {
		CustomLoggerUtils.INSTANCE.log.info("inside deletePerson method of PersonDaoNewImpl class"+personId);
		Person person = null;
		

		String personDetails = "from Person where personOracleId=:personId";
		TypedQuery<Person> s = entityManager.createQuery(personDetails,
				Person.class);
		s.setParameter("personId", personId);
		person = s.getSingleResult();
		person.setHasExited(true);
		person.setActive(false);
		entityManager.merge(person);
		CustomLoggerUtils.INSTANCE.log.info("exting deletePerson method"+personId);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew#changePersonStatus(int, boolean)
	 */
	@Override
	@Transactional
	public boolean changePersonStatus(int personId, boolean b) {
		Person person = null;
		CustomLoggerUtils.INSTANCE.log.info("inside changePersonStatus(int personId, boolean b)  method of PersonDaoNewImpl class "+personId+" make active? "+b);
	
		String personDetails = "from Person where personOracleId=:personId";
		TypedQuery<Person> s = entityManager.createQuery(personDetails,
				Person.class);
		s.setParameter("personId", personId);
		person = s.getSingleResult();
		if(person.isActive()!=b){
		person.setActive(b);
		entityManager.merge(person);}
		CustomLoggerUtils.INSTANCE.log.info("exting changePersonStatus method"+personId);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew#modifyPersonRole(int, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public boolean modifyPersonRole(int personId, List<Role> userRolesList) {
		CustomLoggerUtils.INSTANCE.log.info("inside modifyPersonRole personId: "+ personId+" List Of Roles: "+userRolesList.toString() +" method of PersonDaoNewImpl class ");
		int containsAdmin=0;
		int userListContainsAdmin=0;
		CustomLoggerUtils.INSTANCE.log
		.info("Inside modifyPersonRole method in PersonDaoNewImpl");
         Person person = null;
         
          String searchOid = "from Person Se where Se.personOracleId = :personId";
       TypedQuery<Person> p = entityManager.createQuery(searchOid,
		Person.class);
       p.setParameter("personId", personId);
        person = p.getSingleResult();
        for(Role role:person.getRole())
        {
        	if(role.getRoleName().equalsIgnoreCase("ADMIN"))
        	{containsAdmin=1;
        		break;
        	}
        }
        for(Role role:userRolesList)
        {
        	if(role.getRoleName().equalsIgnoreCase("ADMIN"))
        	{
        		userListContainsAdmin=1;
        		break;
        	}
        }
        if(containsAdmin==1 && userListContainsAdmin==0)
        {CustomLoggerUtils.INSTANCE.log.info("Inside the method xyz");
        	Set<Person> adminList = null;
        	Role role=null;
			String selectQuery = " from Role r where r.roleName =:rolename";
			TypedQuery<Role> q = entityManager.createQuery(selectQuery,
					Role.class);
			q.setParameter("rolename", "ADMIN");
			role=q.getSingleResult();
			adminList=role.getPerson();
			if (adminList.size() < 3) {
				CustomLoggerUtils.INSTANCE.log.info("It will cause Less than 2 admins in database");
				// There has to be at least two admins in the application
				return false;
			}
        	
        }
		CustomLoggerUtils.INSTANCE.log.info("Changing the role of the person");
		Set<Role> roleSet=new HashSet<Role>(userRolesList);
		person.setRole(roleSet);
		entityManager.merge(person);
		CustomLoggerUtils.INSTANCE.log.info("Exiting modifyPersonRole method in PersonDaoNewImpl");
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew#updatePerson(com.sapient.statestreetscreeningapplication.model.entity.Person)
	 */
	@Override
	@Transactional
	public boolean updatePerson(Person personBeanToEntity) {
		CustomLoggerUtils.INSTANCE.log.info("inside  updatePerson(Person personBeanToEntity) method of PersonDaoNewImpl class "+ personBeanToEntity.toString());
	
		try {
			entityManager.merge(personBeanToEntity);
		} catch (IllegalArgumentException e) {
			CustomLoggerUtils.INSTANCE.log
					.error("Error updating person. Illegal argument");
			e.printStackTrace();
			return false;
		}
		CustomLoggerUtils.INSTANCE.log.info("Successfully updated");
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew#getInterviewersForDailyMail()
	 */
	@Override
	@Transactional
	public List<Person> getInterviewersForDailyMail() {
		CustomLoggerUtils.INSTANCE.log.info("inside getInterviewersForDailyMail() method of PersonDaoNewImpl class ");
		List<Person> interviewerList;
		String query = "from Person se where subscribeDailyMail=:true and isActive=:true";
		TypedQuery<Person> s = entityManager.createQuery(query,
				Person.class);
		s.setParameter("true", true);
		interviewerList = s.getResultList();
		CustomLoggerUtils.INSTANCE.log
				.info("Interviewers for daily mails retrieved. Is list null? "
						+ interviewerList == null);
		return interviewerList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew#getInterviewersForImmediateUpdates()
	 */
	@Override
	@Transactional
	public List<Person> getInterviewersForImmediateUpdates() {
		CustomLoggerUtils.INSTANCE.log.info("inside  getInterviewersForImmediateUpdates() method of PersonDaoNewImpl class " );
		List<Person> interviewerList;
		String query = "from Person se where subscribeImmediateMail=:true and isActive=:true";
		TypedQuery<Person> s = entityManager.createQuery(query,
				Person.class);
		s.setParameter("true", true);
		interviewerList = s.getResultList();
		CustomLoggerUtils.INSTANCE.log
				.info("Interviewers for daily mails retrieved. Is list null? "
						+ interviewerList == null);
		return interviewerList;
	}

	
	@Override
	@Transactional
	public List<Person> getActiveNonExitedDailyMailSubscribedPersonList(){
		CustomLoggerUtils.INSTANCE.log.info("inside  getActiveNonExitedDailyMailSubscribedPersonList() method of PersonDaoNewImpl class " );
		String query1 = "from Person where subscribeDailyMail=:subscribeDailyMail and isActive=:isActive and hasExited=:hasExited";
		TypedQuery<Person> s = entityManager.createQuery(query1,Person.class);
		boolean subscribeDailyMail = true;
		boolean isActive = true;
		boolean hasExited = false;
		s.setParameter("subscribeDailyMail", subscribeDailyMail);
		s.setParameter("isActive", isActive);
		s.setParameter("hasExited", hasExited);
		List<Person> personList = s.getResultList();
		return personList;
	}
	
	@Override
	public List<PersonScreeningDetails> getPSDListForAttachment(){
		CustomLoggerUtils.INSTANCE.log.info("inside  getPSDListForAttachment() method of PersonDaoNewImpl class " );
		String query1 = "from Status where statusName =:statusName and resultName=:resultName";
		TypedQuery<Status> s1 = entityManager.createQuery(query1,Status.class);
		String statusName1 = "Screening complete";
		String resultName1 = "Recommended";
		s1.setParameter("statusName", statusName1);
		s1.setParameter("resultName", resultName1);
		Status completeRecommended = s1.getSingleResult();
		
		String query2 = "from Status where statusName =:statusName and resultName=:resultName";
		TypedQuery<Status> s2 = entityManager.createQuery(query2,Status.class);
		String statusName2 = "Screening in progress";
		String resultName2 = "Pending";
		s2.setParameter("statusName", statusName2);
		s2.setParameter("resultName", resultName2);
		Status progressPending = s2.getSingleResult();
		
		String query3 = "from Status where statusName =:statusName and resultName=:resultName";
		TypedQuery<Status> s3 = entityManager.createQuery(query3,Status.class);
		String statusName3 = "NEW";
		String resultName3 = "NA";
		s3.setParameter("statusName", statusName3);
		s3.setParameter("resultName", resultName3);
		Status newNA = s3.getSingleResult();
		
		String query4 = "from Status where statusName =:statusName and resultName=:resultName ";
		TypedQuery<Status> s4 = entityManager.createQuery(query4,Status.class);
		String statusName4 = "Screening complete";
		String resultName4 = "Rejected";
		s4.setParameter("statusName", statusName4);
		s4.setParameter("resultName", resultName4);
		Status completeRejected = s4.getSingleResult();
		
	    String query = "from PersonScreeningDetails where status=:status1 or status=:status2 or status=:status3 or status=:status4 order by screeningStartDate desc";
	    TypedQuery<PersonScreeningDetails> psdTQ = entityManager.createQuery(query,PersonScreeningDetails.class);
	    psdTQ.setParameter("status1",newNA);
	    psdTQ.setParameter("status2",progressPending);
	    psdTQ.setParameter("status3",completeRecommended);
	    psdTQ.setParameter("status4",completeRejected);
	   
	    List<PersonScreeningDetails> psdResultList = psdTQ.getResultList();
	    return psdResultList;
	}

	@Override
	public TreeMap<Person,Long> getTopScreenersWithCount(Date fDate, Date lDate){
		CustomLoggerUtils.INSTANCE.log.info("inside  getTopScreenersWithCount(Date fDate, Date lDate) method of PersonDaoNewImpl class");
		
		String query = "from PersonScreeningDetails where screeningStartDate between :fDate and :lDate group by screener order by count(*) desc";
	    TypedQuery<PersonScreeningDetails> psdTQ = entityManager.createQuery(query,PersonScreeningDetails.class);
	    psdTQ.setParameter("fDate",fDate);
	    psdTQ.setParameter("lDate",lDate);
	    psdTQ.setMaxResults(3);
	    List<PersonScreeningDetails> psdResultList = psdTQ.getResultList();
	   
	    HashMap<Person,Long> topScreeners = new HashMap<Person,Long>();
		for(PersonScreeningDetails psd :psdResultList){
			Person screener = psd.getScreener();
			String q = "select count(*) from PersonScreeningDetails where screener =:screener and (screeningStartDate between :fDate and :lDate)";
			TypedQuery<Long> longTQ = entityManager.createQuery(q,Long.class);
			longTQ.setParameter("fDate",fDate);
			longTQ.setParameter("lDate",lDate);
			longTQ.setParameter("screener",screener);
			Long count = longTQ.getSingleResult();
			topScreeners.put(screener, count);
		}
		
		MapComparator mapComparator = new MapComparator(topScreeners);
		TreeMap<Person,Long> sortedScreenersMap = new TreeMap<Person,Long>(mapComparator);
		sortedScreenersMap.putAll(topScreeners);
		return sortedScreenersMap;
		
	}
	
	@Override
	public List<PersonStaffing> getStaffingThisMonth(Date firstDate, Date lastDate){
		CustomLoggerUtils.INSTANCE.log.info("inside  getStaffingThisMonth(Date firstDate, Date lastDate) method of PersonDaoNewImpl class" );
	    String query = "from PersonStaffing where endDate >:firstDate and startDate <:lastDate";
	    TypedQuery<PersonStaffing> personStaffingTQ = entityManager.createQuery(query,PersonStaffing.class);
	    personStaffingTQ.setParameter("firstDate",firstDate);
	    personStaffingTQ.setParameter("lastDate",lastDate);
	    List<PersonStaffing> psResultList = personStaffingTQ.getResultList();
	    return psResultList;
	}
	
	@Override
	public List<PersonStaffing> getStaffingThisProject(Date firstJanuaryOfSelectedYear, Date thirtyFirstDecemberOfSelectedYear, int projectId) {
		CustomLoggerUtils.INSTANCE.log.info("inside  getStaffingThisProject(Date firstJanuaryOfSelectedYear, Date thirtyFirstDecemberOfSelectedYear, int projectId) method of PersonDaoNewImpl class" );
	  
		String q = "from ProjectNew where projectId=:projectId";
		TypedQuery<ProjectNew> projectNewTQ = entityManager.createQuery(q,ProjectNew.class);
		projectNewTQ.setParameter("projectId",projectId);
		ProjectNew project = null;
		try {
			project = projectNewTQ.getSingleResult();
		} catch (NoResultException e) {
			CustomLoggerUtils.INSTANCE.log.info("No Project found for the ProjectId = "+" "+projectId);
		}
		if(project != null){
		String query = "from PersonStaffing where endDate >:firstJanuaryOfSelectedYear and startDate <:thirtyFirstDecemberOfSelectedYear and project =:project";
	    TypedQuery<PersonStaffing> personStaffingTQ = entityManager.createQuery(query,PersonStaffing.class);
	    personStaffingTQ.setParameter("firstJanuaryOfSelectedYear",firstJanuaryOfSelectedYear);
	    personStaffingTQ.setParameter("thirtyFirstDecemberOfSelectedYear",thirtyFirstDecemberOfSelectedYear);
	    personStaffingTQ.setParameter("project",project);
	    List<PersonStaffing> psResultList = personStaffingTQ.getResultList();
	    return psResultList;
		}
		return null;
	}


	@Override
	public List<MonthlyProjectBudgetEntity> getMonthlyProjectBudgetThisQuarter(Months month1, Months month2, Months month3, int year) {
		CustomLoggerUtils.INSTANCE.log.info("inside getMonthlyProjectBudgetThisQuarter Months :"+ month1+" "+month2+" "+month3+" Year: "+year);
		String query = "from MonthlyProjectBudgetEntity where year = :year and (month = :month1 or month =:month2 or month =:month3)";
	    TypedQuery<MonthlyProjectBudgetEntity> MonthlyProjectBudgetEntityTQ = entityManager.createQuery(query,MonthlyProjectBudgetEntity.class);
	    MonthlyProjectBudgetEntityTQ.setParameter("year",year);
	    MonthlyProjectBudgetEntityTQ.setParameter("month1",month1);
	    MonthlyProjectBudgetEntityTQ.setParameter("month2",month2);
	    MonthlyProjectBudgetEntityTQ.setParameter("month3",month3);
	    List<MonthlyProjectBudgetEntity> monthlyProjectBudgetEntityResultList = MonthlyProjectBudgetEntityTQ.getResultList();
		return monthlyProjectBudgetEntityResultList;
	}
	
	@Override
	public List<MonthlyProjectBudgetEntity> getMonthlyProjectBudgetThisYear(int year) {
		CustomLoggerUtils.INSTANCE.log.info("inside getMonthlyProjectBudgetThisYear: "+year);
		String query = "from MonthlyProjectBudgetEntity where year = :year";
	    TypedQuery<MonthlyProjectBudgetEntity> MonthlyProjectBudgetEntityTQ = entityManager.createQuery(query,MonthlyProjectBudgetEntity.class);
	    MonthlyProjectBudgetEntityTQ.setParameter("year",year);
	    List<MonthlyProjectBudgetEntity> monthlyProjectBudgetEntityResultList = MonthlyProjectBudgetEntityTQ.getResultList();
		return monthlyProjectBudgetEntityResultList;
	}
	
	@Override
	public List<MonthlyProjectBudgetEntity> getMonthlyProjectBudgetThisYearThisProject(int year, ProjectNew projectNew) {
		CustomLoggerUtils.INSTANCE.log.info("inside getMonthlyProjectBudgetThisYearThisProject(" +year+" Project: "+ projectNew.toString());
		String q = "from ProjectNew where projectId=:projectId";
		TypedQuery<ProjectNew> projectNewTQ = entityManager.createQuery(q,ProjectNew.class);
		projectNewTQ.setParameter("projectId",projectNew.getProjectId());
		ProjectNew project = projectNewTQ.getSingleResult();
		
		String query = "from MonthlyProjectBudgetEntity where year = :year and project = :project";
	    TypedQuery<MonthlyProjectBudgetEntity> MonthlyProjectBudgetEntityTQ = entityManager.createQuery(query,MonthlyProjectBudgetEntity.class);
	    MonthlyProjectBudgetEntityTQ.setParameter("year",year);
	    MonthlyProjectBudgetEntityTQ.setParameter("project",project);
	    List<MonthlyProjectBudgetEntity> monthlyProjectBudgetEntityResultList = MonthlyProjectBudgetEntityTQ.getResultList();
		return monthlyProjectBudgetEntityResultList;
	}


	@Override
	public List<MonthlyProjectBudgetEntity> getMonthlyProjectBudgetThisMonth(Months month, int year) {
		CustomLoggerUtils.INSTANCE.log.info("inside getMonthlyProjectBudgetThisMonth("+month+" Year: "+year);
		String query = "from MonthlyProjectBudgetEntity where year = :year and month = :month";
	    TypedQuery<MonthlyProjectBudgetEntity> MonthlyProjectBudgetEntityTQ = entityManager.createQuery(query,MonthlyProjectBudgetEntity.class);
	    MonthlyProjectBudgetEntityTQ.setParameter("year",year);
	    MonthlyProjectBudgetEntityTQ.setParameter("month",month);
	    List<MonthlyProjectBudgetEntity> monthlyProjectBudgetEntityResultList = MonthlyProjectBudgetEntityTQ.getResultList();
		return monthlyProjectBudgetEntityResultList;
	}

	@Override
	@Transactional
	public void newMonthlyProjectBudget(MonthlyProjectBudgetEntity mpbNew) {
		CustomLoggerUtils.INSTANCE.log.info("inside newMonthlyProjectBudget MonthlyProjectBudgetEntity: "+mpbNew.toString());
		String query = "from MonthlyProjectBudgetEntity where project = :project and month = :month and year = :year";
	    TypedQuery<MonthlyProjectBudgetEntity> monthlyProjectBudgetEntityTQ = entityManager.createQuery(query,MonthlyProjectBudgetEntity.class);
	    monthlyProjectBudgetEntityTQ.setParameter("project",mpbNew.getProject());
	    monthlyProjectBudgetEntityTQ.setParameter("month",mpbNew.getMonth());
	    monthlyProjectBudgetEntityTQ.setParameter("year",mpbNew.getYear());
	    List<MonthlyProjectBudgetEntity> monthlyProjectBudgetEntityResultList = monthlyProjectBudgetEntityTQ.getResultList();
	    if(monthlyProjectBudgetEntityResultList.size()==0){
	    	entityManager.merge(mpbNew);	
	    }
	}

}
