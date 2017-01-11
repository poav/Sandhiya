package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.CategoryDao;
import com.sapient.statestreetscreeningapplication.model.dao.CriteriaDao;
import com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao;
import com.sapient.statestreetscreeningapplication.model.dao.LocationDao;
import com.sapient.statestreetscreeningapplication.model.dao.OnboardingResourceDao;
import com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew;
import com.sapient.statestreetscreeningapplication.model.dao.PositionDao;
import com.sapient.statestreetscreeningapplication.model.dao.ProjectDao;
import com.sapient.statestreetscreeningapplication.model.dao.StatusDao;
import com.sapient.statestreetscreeningapplication.model.dao.TopicsDao;
import com.sapient.statestreetscreeningapplication.model.entity.Criteria;
import com.sapient.statestreetscreeningapplication.model.entity.OnboardingCheckList;
import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.entity.ScoresNew;
import com.sapient.statestreetscreeningapplication.model.entity.StatusChangeLogEntity;
import com.sapient.statestreetscreeningapplication.model.entity.Topics;
import com.sapient.statestreetscreeningapplication.model.service.CategoryService;
import com.sapient.statestreetscreeningapplication.model.service.IntervieweeService;
import com.sapient.statestreetscreeningapplication.model.service.OnboardingResourceService;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.model.service.PersonService;
import com.sapient.statestreetscreeningapplication.model.service.StatusService;
import com.sapient.statestreetscreeningapplication.ui.bean.IntervieweeBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingResourceBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonScreeningDetailsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonStaffingBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ScoresNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.TempPersonBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.CriteriaConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.OnboardingCheckListConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonNewConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonScreeningDetailsConvertor;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonStaffingConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.ScoreNewConvertor;
import com.sapient.statestreetscreeningapplication.utils.converter.StatusConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.TempPersonConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.TopicConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class IntervieweeServiceImpl.
 */
@Service
public class IntervieweeServiceImpl implements IntervieweeService {

	/** The interviewee dao. */
	@Autowired
	IntervieweeDao intervieweeDao;
	@Autowired
	PersonService personService ;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	CategoryService categoryService;
	
	/** The topics dao. */
	@Autowired
	TopicsDao topicsDao;
	
	/** The criteria dao. */
	@Autowired
	CriteriaDao criteriaDao;
	
	/** The category dao. */
	@Autowired
	CategoryDao categoryDao;
	
	/** The location dao. */
	@Autowired
	LocationDao locationDao;
	
	@Autowired
	OnboardingResourceDao onboardingResourceDao;
	
	/** The status dao. */
	@Autowired
	StatusDao statusDao;
	
	/** The position dao. */
	@Autowired
	PositionDao positionDao;
	
	/** The project dao. */
	@Autowired
	ProjectDao projectDao;
	
	/** The person lookup service. */
	@Autowired
	PersonLookupService personLookupService;
	@Autowired
	PersonDaoNew personDaoNew;
	
	/** The onboarding resource service. */
	@Autowired
	OnboardingResourceService onboardingResourceService;

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonScreeningDetailsBean> getAllIntervieweeList(int start, int rows, int colNum, String searchKey,
			String sortDirection) {
		// TODO Auto-generated method stub
		
		String columnName = "";
		String[] columnNames = { "psd.person_Screening_Id", "p.person_Oracle_Id",
				 "p.position.domain", "psd.screening_Start_Date",
					"psd.screening_End_Date", "p.location.location_Name", "s.status_Name",
					"s.result_Name", "proj.project_Name", "c.categoryName",
					"psd.feedback" ,"p.is_Contractor"};

		columnName = columnNames[colNum]; 
				
		if (colNum < 0 || colNum > columnNames.length) {
			colNum = 0;
		}
		
		columnName = columnNames[colNum];
		if (start < 0) {
			start = 0;
		}
		if (rows < 0) {
			rows = 0;
		}
		
		if (!sortDirection.equals("asc")) {
			sortDirection = "desc";
		}
		
		List<PersonScreeningDetailsBean> intervieweeList; 
		Map<String, Object> intervieweeMap = new HashMap<String, Object>();
		if (searchKey == null || searchKey.isEmpty()) {
			intervieweeList= intervieweeDao.getIntervieweeList(start, rows,
					columnName, searchKey, sortDirection);
			
		}
		else if (searchKey.matches("[a-zA-Z][a-zA-Z]*")){
			
			CustomLoggerUtils.INSTANCE.log.info("Text Input");
			List<PersonBean> persons = null;
			List<PersonScreeningDetails> tempPersonScreeningDetailsList=null;
			if (searchKey.length()>=3) {
				persons=personLookupService.getPersonByNameSearch(searchKey);
				tempPersonScreeningDetailsList=intervieweeDao.retrieveTempInterviewees(start, rows, columnName, sortDirection,searchKey);
				if(persons != null || tempPersonScreeningDetailsList!=null){
					
					Map<String, Object> personMap = new HashMap<String, Object>();
					Map<String, Object> TempPersonMap = new HashMap<String, Object>();
					
					if(persons != null){
						List<String> oracleIdList=new ArrayList<String>();
						CustomLoggerUtils.INSTANCE.log.info("FETCHED FROM AD "+persons);
						
						for(PersonBean person : persons){
						oracleIdList.add(String.valueOf(person.getOracleId()));
						}
						personMap = intervieweeDao.searchIntervieweeByOracleIdList(
							start, rows, columnName, sortDirection,oracleIdList);
					}
					
					if(tempPersonScreeningDetailsList!=null){
						
						int count = intervieweeDao.getTempPersonCount(start, rows, columnName, sortDirection,
								searchKey);
						TempPersonMap.put("intervieweeList", tempPersonScreeningDetailsList);
						TempPersonMap.put("count", count);
					}
					
					List<PersonScreeningDetails> allPersonScreeningDetails=new ArrayList<>();
					int allCount=0;
					
					if(personMap!=null){
						allPersonScreeningDetails.addAll((Collection<? extends PersonScreeningDetails>) personMap.get("intervieweeList"));
						allCount=(int) personMap.get("count");
						if(TempPersonMap!=null){
							allPersonScreeningDetails.addAll((Collection<? extends PersonScreeningDetails>) TempPersonMap.get("intervieweeList"));
							allCount=allCount+(int) TempPersonMap.get("count");
						}
						intervieweeMap .put("intervieweeList", allPersonScreeningDetails);
						intervieweeMap.put("count", allCount);
					}else{
						if(TempPersonMap!=null){
							allPersonScreeningDetails.addAll((Collection<? extends PersonScreeningDetails>) TempPersonMap.get("intervieweeList"));
							allCount=allCount+(int) TempPersonMap.get("count");
							intervieweeMap.put("intervieweeList", allPersonScreeningDetails);
							intervieweeMap.put("count", allCount);
						}
						
					}
					
				} 
				else{
					CustomLoggerUtils.INSTANCE.log.info("Text Input Except Name");
					/*intervieweeMap = intervieweeDao.populateInterviewee(start, rows,
							columnName, sortDirection, searchKey);*/
					
					intervieweeList= intervieweeDao.getIntervieweeList(start, rows,
							columnName, searchKey, sortDirection);
				}
			}
			else{
			CustomLoggerUtils.INSTANCE.log.info("Text Input Except Name (in else)");
			intervieweeList= intervieweeDao.getIntervieweeList(start, rows,
					columnName, searchKey, sortDirection);
			}
		} /*else if (searchKey.matches("^[\\d]{1,2}$"))*/ 
		else if (searchKey.matches("[0-9][0-9]*")){
			CustomLoggerUtils.INSTANCE.log
					.info("searching 1 or 2 digits. date or oracle id");
			intervieweeMap = intervieweeDao.searchIntervieweeByOracleIdOrDate(
					start, rows, columnName, sortDirection, searchKey);
		} else if (searchKey.matches("^[\\d]{1,10}$")) {
			CustomLoggerUtils.INSTANCE.log
					.info("more than 2 consecutive digits. oid");
			intervieweeMap = intervieweeDao.searchIntervieweeByOracleId(start,
					rows, columnName, sortDirection, searchKey);
		} else if (searchKey
				.matches("^[\\d]{1,2}[\\\\|\\/|\\-]((([\\d]{1,2})?[\\\\|\\/|\\-])?[\\d]{0,2})?")) {
			CustomLoggerUtils.INSTANCE.log.info("date");
			String[] dateArray = searchKey.split("[\\\\|\\/|\\-]");
			String searchString = dateArray[0];
			for (int i = 1; i < dateArray.length; i++) {
				searchString = dateArray[i] + "-" + searchString;
			}
			CustomLoggerUtils.INSTANCE.log.info("Date search string: "
					+ searchString);
			intervieweeMap = intervieweeDao.searchIntervieweeByDate(start,
					rows, columnName, sortDirection, searchString);
		}
		intervieweeList=(List<PersonScreeningDetailsBean>) intervieweeMap.get("intervieweeList");
		return intervieweeList;
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.IntervieweeService#populateIntervieweeData(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> populateIntervieweeData(int start, int amount,
			String columnName, String direction, String searchKey) {
		CustomLoggerUtils.INSTANCE.log.info("inside Map<String, Object> populateIntervieweeData(int start, int amount,String columnName, String direction, String searchKey) method of IntervieweeServiceImpl class");
		// TODO: Cleanup
		List<IntervieweeBean> intervieweeBeanList = null;
		Map<String, Object> intervieweeMap = new HashMap<String, Object>();
		intervieweeMap.put("count", 0);
		if (searchKey == null || searchKey.isEmpty()) {
			intervieweeMap = intervieweeDao.populateInterviewee(start, amount,
					columnName, direction, searchKey);
		} /*else if (searchKey.startsWith("SearchByName:")) {
			CustomLoggerUtils.INSTANCE.log.info("search by name!");
			searchKey = searchKey.replace("SearchByName:", "");
			CustomLoggerUtils.INSTANCE.log.info(searchKey);
			List<PersonBean> persons = personLookupService
					.getPersonByName(searchKey);
			if (persons != null) {
				intervieweeMap = intervieweeDao.searchIntervieweeByOracleId(
						start, amount, columnName, direction,
						String.valueOf(persons.get(0).getOracleId()));
			}

		}*/ /*else if (searchKey.matches("^[\\D]+"))*/ 
		else if (searchKey.matches("[a-zA-Z][a-zA-Z]*")){
			
			CustomLoggerUtils.INSTANCE.log.info("Text Input");
			List<PersonBean> persons = null;
			List<PersonScreeningDetails> tempPersonScreeningDetailsList=null;
			if (searchKey.length()>=3) {
				persons=personLookupService.getPersonByNameSearch(searchKey);
				tempPersonScreeningDetailsList=intervieweeDao.retrieveTempInterviewees(start, amount, columnName, direction,searchKey);
				if(persons != null || tempPersonScreeningDetailsList!=null){
					
					Map<String, Object> personMap = new HashMap<String, Object>();
					Map<String, Object> TempPersonMap = new HashMap<String, Object>();
					
					if(persons != null){
						List<String> oracleIdList=new ArrayList<String>();
						CustomLoggerUtils.INSTANCE.log.info("FETCHED FROM AD "+persons);
						for(PersonBean person : persons){
						oracleIdList.add(String.valueOf(person.getOracleId()));
						}
						personMap = intervieweeDao.searchIntervieweeByOracleIdList(
							start, amount, columnName, direction,oracleIdList);
					}
					if(tempPersonScreeningDetailsList!=null){
						
						int count = intervieweeDao.getTempPersonCount(start, amount, columnName, direction,
								searchKey);
						TempPersonMap.put("intervieweeList", tempPersonScreeningDetailsList);
						TempPersonMap.put("count", count);
					}
					
					List<PersonScreeningDetails> allPersonScreeningDetails=new ArrayList<>();
					int allCount=0;
					
					if(personMap!=null){
						allPersonScreeningDetails.addAll((Collection<? extends PersonScreeningDetails>) personMap.get("intervieweeList"));
						allCount=(int) personMap.get("count");
						if(TempPersonMap!=null){
							allPersonScreeningDetails.addAll((Collection<? extends PersonScreeningDetails>) TempPersonMap.get("intervieweeList"));
							allCount=allCount+(int) TempPersonMap.get("count");
						}
						intervieweeMap.put("intervieweeList", allPersonScreeningDetails);
						intervieweeMap.put("count", allCount);
					}else{
						if(TempPersonMap!=null){
							allPersonScreeningDetails.addAll((Collection<? extends PersonScreeningDetails>) TempPersonMap.get("intervieweeList"));
							allCount=allCount+(int) TempPersonMap.get("count");
							intervieweeMap.put("intervieweeList", allPersonScreeningDetails);
							intervieweeMap.put("count", allCount);
						}
						
					}
					
				} 
				else{
					CustomLoggerUtils.INSTANCE.log.info("Text Input Except Name");
					intervieweeMap = intervieweeDao.populateInterviewee(start, amount,
							columnName, direction, searchKey);
				}
			}
			else{
			CustomLoggerUtils.INSTANCE.log.info("Text Input Except Name (in else)");
			intervieweeMap = intervieweeDao.populateInterviewee(start, amount,
					columnName, direction, searchKey);
			}
		} /*else if (searchKey.matches("^[\\d]{1,2}$"))*/ 
		else if (searchKey.matches("[0-9][0-9]*")){
			CustomLoggerUtils.INSTANCE.log
					.info("searching 1 or 2 digits. date or oracle id");
			intervieweeMap = intervieweeDao.searchIntervieweeByOracleIdOrDate(
					start, amount, columnName, direction, searchKey);
		} else if (searchKey.matches("^[\\d]{1,10}$")) {
			CustomLoggerUtils.INSTANCE.log
					.info("more than 2 consecutive digits. oid");
			intervieweeMap = intervieweeDao.searchIntervieweeByOracleId(start,
					amount, columnName, direction, searchKey);
		} else if (searchKey
				.matches("^[\\d]{1,2}[\\\\|\\/|\\-]((([\\d]{1,2})?[\\\\|\\/|\\-])?[\\d]{0,2})?")) {
			CustomLoggerUtils.INSTANCE.log.info("date");
			String[] dateArray = searchKey.split("[\\\\|\\/|\\-]");
			String searchString = dateArray[0];
			for (int i = 1; i < dateArray.length; i++) {
				searchString = dateArray[i] + "-" + searchString;
			}
			CustomLoggerUtils.INSTANCE.log.info("Date search string: "
					+ searchString);
			intervieweeMap = intervieweeDao.searchIntervieweeByDate(start,
					amount, columnName, direction, searchString);
		}
		/*intervieweeBeanList = IntervieweeConverter
				.intervieweeEntityListToBeanListConverter((List<Interviewee>) intervieweeMap
						.get("intervieweeList"));*/
		/*List<PersonScreeningDetails> personScreeningList=new ArrayList<PersonScreeningDetails>();
		personScreeningList=(List<PersonScreeningDetails>) intervieweeMap
				.get("intervieweeList");*/
		/*intervieweeBeanList = populateIntervieweeNames(intervieweeBeanList);*/
		//personScreeningList=populateIntervieweeNames(personScreeningList);
		/*intervieweeMap.put("intervieweeList", intervieweeBeanList);*/
		//intervieweeMap.put("intervieweeList", personScreeningList);
		return intervieweeMap;

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.IntervieweeService#saveInterviewee(com.sapient.statestreetscreeningapplication.ui.bean.IntervieweeBean)
	 */
	@Override
	@Transactional
	public PersonScreeningDetailsBean saveInterviewee(PersonScreeningDetailsBean personScreeningDetailsBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveInterviewee(PersonScreeningDetailsBean personScreeningDetailsBean) method of IntervieweeServiceImpl class");
		Set<ScoresNew> scoresList=new HashSet<ScoresNew>();
		PersonScreeningDetails personScreeningDetails = PersonScreeningDetailsConvertor.PersonScreeningDetailsBeanToEntity(personScreeningDetailsBean,categoryService);
		
		//Interviewee  interviewee = IntervieweeConverter.intervieweeBeanToEntityConverter(intervieweeBean);
		Date today = new Date();
		
		if (!personScreeningDetailsBean.getFeedback().equals(""))
			personScreeningDetails.setFeedback(DateFormatUtils.format(today, "dd/MM/yy")
					+ ": Per " + personLookupService.getPersonByOracleId((Integer)personScreeningDetailsBean.getScreenerBean().getPersonOracleId()).getName() + ", " + personScreeningDetailsBean.getFeedback());
		/*if(personScreeningDetails.getPerson().getHasExited()==null){
			personScreeningDetails.getPerson().setHasExited(false);
		}
		if(personScreeningDetails.getPerson().getIsActive()==null){
			personScreeningDetails.getPerson().setIsActive(false);
		}*/
		
		
		if(personScreeningDetailsBean.getScoreList()!=null){
			for (ScoresNewBean scoreBean : personScreeningDetailsBean.getScoreList()) {
				ScoresNew scoresEntity = new ScoresNew();
				scoresEntity.setComments(scoreBean.getComments());
				scoresEntity.setScore(scoreBean.getScore());
				scoresEntity.setTopic(topicsDao.fetchTopic(
						scoreBean.getTopicBean().getTopicName(),CriteriaConverter.criteriaBeanToEntityConverter(scoreBean.getTopicBean().getCriteriaBean())));
				scoresEntity.setPersonScreeningDetails(personScreeningDetails);
				scoresEntity.setCriteria(CriteriaConverter.criteriaBeanToEntityConverter(scoreBean.getCriteriaBean()));
				scoresEntity.setIsChecked(scoreBean.getIsChecked());
				scoresEntity.setScoreId(scoreBean.getScoreId());
				scoresList.add(scoresEntity);
			}
			personScreeningDetails.setScoreList(scoresList);
		}
		PersonScreeningDetails psd=intervieweeDao.saveInterviewee(personScreeningDetails);
		return PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(psd);
	}
	
	@Override
	@Transactional
	public PersonScreeningDetailsBean saveTempInterviewee(PersonScreeningDetailsBean personScreeningDetailsBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveTempInterviewee(PersonScreeningDetailsBean personScreeningDetailsBean) method of IntervieweeServiceImpl class");
		Set<ScoresNew> scoresList=new HashSet<ScoresNew>();
		PersonScreeningDetails personScreeningDetails = PersonScreeningDetailsConvertor.PersonScreeningDetailsBeanToEntity(personScreeningDetailsBean,categoryService);
		
		//Interviewee  interviewee = IntervieweeConverter.intervieweeBeanToEntityConverter(intervieweeBean);
		Date today = new Date();
		
		if (!personScreeningDetailsBean.getFeedback().equals(""))
			personScreeningDetails.setFeedback(DateFormatUtils.format(today, "dd/MM/yy")
					+ ": Per " + personLookupService.getPersonByOracleId(personScreeningDetailsBean.getScreenerBean().getPersonId()).getName() + ", " + personScreeningDetailsBean.getFeedback());
		if(personScreeningDetails.getPerson().getHasExited()==null){
			personScreeningDetails.getPerson().setHasExited(false);
		}
		if(personScreeningDetails.getPerson().getIsActive()==null){
			personScreeningDetails.getPerson().setIsActive(false);
		}
		
		
		if(personScreeningDetailsBean.getScoreList()!=null){
			for (ScoresNewBean scoreBean : personScreeningDetailsBean.getScoreList()) {
				ScoresNew scoresEntity = new ScoresNew();
				scoresEntity.setComments(scoreBean.getComments());
				scoresEntity.setScore(scoreBean.getScore());
				scoresEntity.setTopic(topicsDao.fetchTopic(
						scoreBean.getTopicBean().getTopicName(),CriteriaConverter.criteriaBeanToEntityConverter(scoreBean.getTopicBean().getCriteriaBean())));
				scoresEntity.setPersonScreeningDetails(personScreeningDetails);
				scoresEntity.setCriteria(CriteriaConverter.criteriaBeanToEntityConverter(scoreBean.getCriteriaBean()));
				scoresEntity.setIsChecked(scoreBean.getIsChecked());
				scoresEntity.setScoreId(scoreBean.getScoreId());
				scoresList.add(scoresEntity);
			}
			personScreeningDetails.setScoreList(scoresList);
		}
		intervieweeDao.saveInterviewee(personScreeningDetails);
		return PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(personScreeningDetails);
	}
	@Override
	public PersonNewBean findTempPersonDetailsFromScreeningId(
			long personScreeningDetailsId){
		return PersonNewConverter.personNewEntityToBean(intervieweeDao.findTempPersonDetailsFromScreeningId(personScreeningDetailsId));
	}
	@Override
	public TempPersonBean findTempPersonDetailsFromTempId(
			long tempPersonId){
		return TempPersonConverter.tempPersonEntityToBean(intervieweeDao.findTempPersonDetailsFromTempId(tempPersonId));
		
	}

	/**
	 * Populate interviewee names.
	 *
	 * @param intervieweeBeanList the interviewee bean list
	 * @return the list
	 */
	List<IntervieweeBean> populateIntervieweeNames(List<IntervieweeBean> intervieweeBeanList) {
		CustomLoggerUtils.INSTANCE.log.info("inside populateIntervieweeNames(List<IntervieweeBean> intervieweeBeanList) method of IntervieweeServiceImpl class");
		PersonBean person;
		List<IntervieweeBean> updatedIntervieweeBeanList = new ArrayList<IntervieweeBean>();
		if (intervieweeBeanList != null && !intervieweeBeanList.isEmpty()) {
			for (IntervieweeBean bean : intervieweeBeanList) {
				if(bean.getIntervieweeUserName()==null)
				{person = personLookupService.getPersonByOracleId(bean
						.getIntervieweeOracleID());
				}
				
				else
				{
					person = personLookupService.getPersonByUserName(bean);
				}
				if (person != null) {
					CustomLoggerUtils.INSTANCE.log.info("person is not null "
							+ person);
					bean.setIntervieweeName(person.getName());
				    if(bean.getIntervieweeUserName()==null)
					{bean.setIntervieweeUserName(person.getUsername());
					//intervieweeDao.updateExistingInterviewee(IntervieweeConverter.intervieweeBeanToEntityConverter(bean));
					}
					updatedIntervieweeBeanList.add(bean);
				} else {
					markIntervieweeAsExited(bean.getIntervieweeId());
				}

			}
		}
		return updatedIntervieweeBeanList;
	}

	/**
	 * Mark interviewee as exited.
	 *
	 * @param intervieweeId the interviewee id
	 */
	public void markIntervieweeAsExited(long intervieweeId) {
		CustomLoggerUtils.INSTANCE.log.info("inside markIntervieweeAsExited(long intervieweeId) method of IntervieweeServiceImpl class");
		intervieweeDao.markIntervieweeAsExited(intervieweeId);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.IntervieweeService#convertStringToList(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Set<ScoresNewBean> convertStringToList(String scores, String categoryName, PersonScreeningDetailsBean personScreeningDetailsBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside convertStringToList(String scores, String categoryName, PersonScreeningDetailsBean personScreeningDetailsBean) method of IntervieweeServiceImpl class");
		Set<ScoresNewBean> scoresList = new HashSet<ScoresNewBean>();
		String values[] = scores.split("\\|");
		for (String value : values) {
			ScoresNewBean scorebean = new ScoresNewBean();
			CustomLoggerUtils.INSTANCE.log.info("value   "+ value);
			Criteria criteria = criteriaDao.getCriteria(value.split("\\^")[0],categoryDao.getCategory(categoryName));
			scorebean.setCriteriaBean(CriteriaConverter.criteriaEntityToBeanConverter(criteria));
			CustomLoggerUtils.INSTANCE.log.info(criteria.toString());
			//scorebean.getTopicBean().setCriteriaBean(CriteriaConverter.criteriaEntityToBeanConverter(criteria));
			if (value.split("\\^")[1].startsWith("++")) {
				Topics topics = new Topics();
				topics.setTopicName(value.split("\\^")[1].substring(2));
				topics.setCriteria(criteria);
				CustomLoggerUtils.INSTANCE.log.info("New topic detected");
				if (topicsDao.saveTopic(topics) == 1) {
					CustomLoggerUtils.INSTANCE.log.info("New topic created: "+ value.split("\\^")[1].substring(2));
					scorebean.setTopicBean(TopicConverter.convertTopicEntityToTopicBean(topicsDao.fetchTopic(topics.getTopicName(),topics.getCriteria())));
				} else {
					CustomLoggerUtils.INSTANCE.log.error("Failed to create new topic: "	+ value.split("\\^")[1].substring(2));
				}
			} else {
				//scorebean.getTopicBean().setTopicName(value.split("\\^")[1]);
				scorebean.setTopicBean(TopicConverter.convertTopicEntityToTopicBean(topicsDao.fetchTopic(value.split("\\^")[1], criteria)));
			}
			scorebean.setScore(Double.valueOf(value.split("\\^")[2]));
			if (!value.equals(" ")) {
				scorebean.setComments(value.split("\\^")[3]);
			} else {
				scorebean.setComments(" ");
			}
			scorebean.setIsChecked(Integer.valueOf(value.split("\\^")[4]));
			String[] scoreValues = value.split("\\^");
			if(scoreValues.length>5){
			scorebean.setScoreId(Long.valueOf(value.split("\\^")[5]));
			}
			
			scorebean.setPersonScreeningDetails(PersonScreeningDetailsConvertor.PersonScreeningDetailsBeanToEntity(personScreeningDetailsBean,categoryService));
			scoresList.add(scorebean);
		}
		CustomLoggerUtils.INSTANCE.log.info("scoresList: " + scoresList);
		return scoresList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.IntervieweeService#fetchInterviewee(long)
	 */
	@Override
	public PersonScreeningDetailsBean fetchInterviewee(long id) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchInterviewee(long id) method of IntervieweeServiceImpl class");
		PersonScreeningDetailsBean personScreeningDetailsBean = PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(intervieweeDao.fetchPersonScreeningDetails(id));
		PersonBean person = personLookupService.getPersonByOracleId((Integer)personScreeningDetailsBean.getPersonBean().getPersonOracleId());
		if (person == null && !personScreeningDetailsBean.getPersonBean().getIsTemp()) {
			// Reset the interviewee as its not available in the AD
			intervieweeDao.markIntervieweeAsExited(id);
		} else {
			if(person!=null){
			personScreeningDetailsBean.getPersonBean().setPersonName(person.getName());
			}
		}
		return personScreeningDetailsBean;
	}

	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.IntervieweeService#getScores(long)
	 */
	@Override
	@Transactional
	public Set<ScoresNewBean> getScores(long id) {
		CustomLoggerUtils.INSTANCE.log.info("inside getScores(long id) method of IntervieweeServiceImpl class");
		PersonScreeningDetails personScreeningDetails = intervieweeDao.fetchPersonScreeningDetails(id);
		Set<ScoresNewBean> scoresList = ScoreNewConvertor.convertScoreListEntityToScoreListBean(personScreeningDetails.getScoreList());
		return scoresList;
	}
	
	

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.IntervieweeService#updateInterviewee(com.sapient.statestreetscreeningapplication.ui.bean.IntervieweeBean)
	 */
	@Override
	@Transactional
	public PersonScreeningDetailsBean updateInterviewee(PersonScreeningDetailsBean personScreeningDetailsBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside updateInterviewee(PersonScreeningDetailsBean personScreeningDetailsBean) method of IntervieweeServiceImpl class");
		PersonScreeningDetails personScreeningDetails = PersonScreeningDetailsConvertor.PersonScreeningDetailsBeanToEntity(personScreeningDetailsBean,categoryService);
		if (!personScreeningDetailsBean.getFeedback().equals("")) {
			personScreeningDetails.setFeedback(DateFormatUtils.format(new Date(),"dd/MM/yy")
					+ ": Per "
					+ personScreeningDetailsBean.getPersonBean().getPersonName()					
					+ ", "
					+ personScreeningDetailsBean.getFeedback());
		}
		Set<ScoresNew> scoresList = new HashSet<ScoresNew>();
		for (ScoresNewBean scoreBean : personScreeningDetailsBean.getScoreList()) {
			ScoresNew scoresEntity = new ScoresNew();
			scoresEntity.setComments(scoreBean.getComments());
			scoresEntity.setScore(scoreBean.getScore());
			scoresEntity.setTopic(topicsDao.fetchTopic(
					scoreBean.getTopicBean().getTopicName(),CriteriaConverter.criteriaBeanToEntityConverter(scoreBean.getTopicBean().getCriteriaBean())));
			scoresEntity.setPersonScreeningDetails(personScreeningDetails);
			scoresEntity.setCriteria(CriteriaConverter.criteriaBeanToEntityConverter(scoreBean.getCriteriaBean()));
			scoresEntity.setScoreId(scoreBean.getScoreId());
			scoresEntity.setIsChecked(scoreBean.getIsChecked());
			scoresList.add(scoresEntity);
			CustomLoggerUtils.INSTANCE.log.info("Score entity: "+ scoresEntity.toString());
		}
		personScreeningDetails.setScoreList(scoresList);
		intervieweeDao.updateInterviewee(personScreeningDetails);
		return PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(personScreeningDetails);
	}

	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.IntervieweeService#delete(java.util.List)
	 */
	@Override
	public boolean delete(List<Long> intervieweeNos) {
		CustomLoggerUtils.INSTANCE.log.info("inside delete(List<Long> intervieweeNos) method of IntervieweeServiceImpl class");
		return intervieweeDao.delete(intervieweeNos);
	}

	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.IntervieweeService#changeStatus(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void changeStatus(List<Long> intervieweeNos,String status, String result) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeStatus(List<Long> intervieweeNos,String status, String result) method of IntervieweeServiceImpl class");
		intervieweeDao.changeStatus(intervieweeNos,status,result);
	}
	
	/**
	 * Gets the category dao.
	 *
	 * @return the category dao
	 */
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	/**
	 * Sets the category dao.
	 *
	 * @param categoryDao the new category dao
	 */
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * Gets the project dao.
	 *
	 * @return the project dao
	 */
	public ProjectDao getProjectDao() {
		return projectDao;
	}

	/**
	 * Sets the project dao.
	 *
	 * @param projectDao the new project dao
	 */
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
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
	 * Gets the topics dao.
	 *
	 * @return the topics dao
	 */
	public TopicsDao getTopicsDao() {
		return topicsDao;
	}

	/**
	 * Sets the topics dao.
	 *
	 * @param topicsDao the new topics dao
	 */
	public void setTopicsDao(TopicsDao topicsDao) {
		this.topicsDao = topicsDao;
	}

	/**
	 * Gets the criteria dao.
	 *
	 * @return the criteria dao
	 */
	public CriteriaDao getCriteriaDao() {
		return criteriaDao;
	}

	/**
	 * Sets the criteria dao.
	 *
	 * @param criteriaDao the new criteria dao
	 */
	public void setCriteriaDao(CriteriaDao criteriaDao) {
		this.criteriaDao = criteriaDao;
	}

	/**
	 * Gets the interviewee dao.
	 *
	 * @return the interviewee dao
	 */
	public IntervieweeDao getIntervieweeDao() {
		return intervieweeDao;
	}

	/**
	 * Sets the interviewee dao.
	 *
	 * @param intervieweeDao the new interviewee dao
	 */
	public void setIntervieweeDao(IntervieweeDao intervieweeDao) {
		this.intervieweeDao = intervieweeDao;
	}

	/**
	 * Gets the person lookup service.
	 *
	 * @return the person lookup service
	 */
	public PersonLookupService getPersonLookupService() {
		return personLookupService;
	}

	/**
	 * Sets the person lookup service.
	 *
	 * @param personLookupService the new person lookup service
	 */
	public void setPersonLookupService(PersonLookupService personLookupService) {
		this.personLookupService = personLookupService;
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
	 * Gets the onboarding resource service.
	 *
	 * @return the onboarding resource service
	 */
	public OnboardingResourceService getOnboardingResourceService() {
		return onboardingResourceService;
	}

	/**
	 * Sets the onboarding resource service.
	 *
	 * @param onboardingResourceService the new onboarding resource service
	 */
	public void setOnboardingResourceService(
			OnboardingResourceService onboardingResourceService) {
		this.onboardingResourceService = onboardingResourceService;
	}


	
	@Override
	public void onboardPerson(PersonStaffingBean personStaffingBean, OnboardingCheckListBean onboardingCheckListBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside onboardPerson(PersonStaffingBean personStaffingBean, OnboardingCheckListBean onboardingCheckListBean) method of IntervieweeServiceImpl class");
		PersonStaffing personStaffing = PersonStaffingConverter.personStaffingBeanToEntity(personStaffingBean);
		OnboardingCheckList onboardingCheckList = OnboardingCheckListConverter.convertOnboardingCheckListBeanToOnboardingCheckListEntity(onboardingCheckListBean);
		onboardingResourceService.saveOnboardingResource(personStaffing, onboardingCheckList);
	}
	
	@Override
	public void onBoardWithoutScreening(PersonStaffingBean personStaffingBean, OnboardingCheckListBean onboardingCheckListBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside onboardPerson(PersonStaffingBean personStaffingBean, OnboardingCheckListBean onboardingCheckListBean) method of IntervieweeServiceImpl class");
		PersonStaffing personStaffing = PersonStaffingConverter.personStaffingBeanToEntity(personStaffingBean);
		OnboardingCheckList onboardingCheckList = OnboardingCheckListConverter.convertOnboardingCheckListBeanToOnboardingCheckListEntity(onboardingCheckListBean);
		onboardingResourceService.onBoardWithoutScreening(personStaffing, onboardingCheckList);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.IntervieweeService#changeStatus(long, java.lang.String, java.lang.String)
	 */
	@Override
	public void changeStatus(long intervieweeNo, String status, String result) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeStatus(long intervieweeNo, String status, String result) method of IntervieweeServiceImpl class");
		intervieweeDao.changeStatus(intervieweeNo,status,result);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.IntervieweeService#getProjectOfInterviewee(int)
	 */
	@Override
	public ProjectBean getProjectOfInterviewee(int intervieweeOracleID) {
		CustomLoggerUtils.INSTANCE.log.info("inside getProjectOfInterviewee(int intervieweeOracleID) method of IntervieweeServiceImpl class");
		
		//return ProjectConverter.convertProjectEntityToProjectBean(intervieweeDao.getIntervieweeByOracleId(intervieweeOracleID).getProjectI());
		return null;
	}

	

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.IntervieweeService#fetchIntervieweeIdsByStatusId(long)
	 */
	@Override
	public List<Long> fetchIntervieweeIdsByStatusId(long statusId) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchIntervieweeIdsByStatusId(long statusId) method of IntervieweeServiceImpl class");
		return intervieweeDao.fetchIntervieweeIdsByStatusId(statusId);
	}
	
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.IntervieweeService#changeLocation(int, java.lang.String)
	 */
	@Override
	public void changeLocation(int oracleID, String location) {
		//intervieweeDao.changeLocation(oracleID,locationDao.getLocationByName(location));
	}


	@Override
	public PersonNewBean setFieldsForPerson(PersonNewBean person) {
		CustomLoggerUtils.INSTANCE.log.info("inside setFieldsForPerson(PersonNewBean person) method of IntervieweeServiceImpl class");
		PersonBean personFromActiveDirectory;
		
		
		personFromActiveDirectory = personLookupService.getPersonByNTId(person.getPersonNtId());
		
		
		if(personFromActiveDirectory!=null){
		person.setPersonName(personFromActiveDirectory.getName());
		person.setPersonEmailId(personFromActiveDirectory.getEmail());
		
		}else if(person.getIsTemp()){
			person.setPersonName(person.getTempPersonBean().getTempPersonName());
			person.setPersonEmailId(person.getTempPersonBean().getTempPersonEmail());
		}
		else
		{personService.markPersonAsExited(PersonNewConverter.personBeanToEntity(person));
			
		}
		
		return person;
		
	}
	@Override
	public PersonScreeningDetails fetchPersonScreeningDetailsByOnboardID(Long onboardingCheckListId){
		CustomLoggerUtils.INSTANCE.log.info("inside PersonScreeningDetails fetchPersonScreeningDetailsByOnboardID(Long onboardingCheckListId) method of IntervieweeServiceImpl class");
		
		return intervieweeDao.fetchPersonScreeningDetailsByOnboardID(onboardingCheckListId);
	}

	@Override
	public PersonScreeningDetails fetchPersonScreeningDetailsOnPersonId(int personId) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchPersonScreeningDetailsOnPersonId(int personId) method of IntervieweeServiceImpl class");
		
		return intervieweeDao.fetchPersonScreeningDetailsByOracleID(personId);
	}
	@Override
	public List<PersonScreeningDetails> fetchMultiplePersonScreeningDetailsByOracleID(int personId){
		CustomLoggerUtils.INSTANCE.log.info("inside fetchMultiplePersonScreeningDetailsByOracleID(int personId) method of IntervieweeServiceImpl class");
		
		return intervieweeDao.fetchMultiplePersonScreeningDetailsByOracleID(personId);
	}
	@Override
	public List<PersonScreeningDetails> fetchMultiplePersonScreeningDetailsByPersonID(int personId){
		CustomLoggerUtils.INSTANCE.log.info("inside fetchMultiplePersonScreeningDetailsByOracleID(int personId) method of IntervieweeServiceImpl class");
		
		return intervieweeDao.fetchMultiplePersonScreeningDetailsByPersonID(personId);
	}
	
	

	@Override
	public PersonScreeningDetails updatePersonScreeningDetails(PersonScreeningDetails personScreeningDetails) {
	
		CustomLoggerUtils.INSTANCE.log.info("inside updatePersonScreeningDetails(PersonScreeningDetails personScreeningDetails) method of IntervieweeServiceImpl class");
		
		return intervieweeDao.updateExistingPersonScreeningDetails(personScreeningDetails);
		
	}
	
	

	@Override
	public void changeNewStatus(int oracleId, String string, String string2,long checkListId) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeNewStatus(int oracleId, String string, String string2) method of IntervieweeServiceImpl class");
		/*PersonScreeningDetails personScreeningDetails=intervieweeDao.fetchPersonScreeningDetailsByOracleID(oracleId);*/
		
		PersonScreeningDetails personScreeningDetails=PersonScreeningDetailsConvertor.PersonScreeningDetailsBeanToEntity((onboardingResourceService.fetchNewOnboardingCheckList(checkListId)).getPersonScreeningDetails(), categoryService);
		StatusChangeLogEntity sclEntity = new StatusChangeLogEntity();
		sclEntity.setCandidatePSD(personScreeningDetails);
		sclEntity.setStatusChangedFrom(personScreeningDetails.getStatus());
		sclEntity.setStatusChangedTo(StatusConverter.convertStatusBeanToStatusEntity(statusService.getStatusBeanOnResultName(string2)));
		if(sclEntity.getStatusChangedFrom().getStatusId()!=sclEntity.getStatusChangedTo().getStatusId()){
		Date statusChangeDate = new Date();
		sclEntity.setStatusChangeDate(statusChangeDate);
		PersonNewBean operator=(PersonNewBean) ServletActionContext.getRequest().getSession().getAttribute("user");
		sclEntity.setOperator(PersonNewConverter.personBeanToEntity(operator));
		onboardingResourceDao.updateStatusChangeLog(sclEntity);
		}
		personScreeningDetails.setStatus(statusDao.getStatusBeanOnResultName(string2));
		updatePersonScreeningDetails(personScreeningDetails);
		
		OnboardingCheckListBean onboardingCheckListBean= onboardingResourceService.fetchNewOnboardingCheckList(checkListId);
		onboardingCheckListBean.setOnboardingStatus(string2);
		OnboardingCheckList onboardingCheckList=OnboardingCheckListConverter.convertOnboardingCheckListBeanToOnboardingCheckListEntity(onboardingCheckListBean);
		onboardingResourceService.updateOnboardingCheckList(onboardingCheckList);
		
		/*OnboardingCheckListBean onboardingCheckListBean= onboardingResourceService.fetchNewOnboardingCheckList(checkListId);
		PersonScreeningDetails personScreeningDetails=PersonScreeningDetailsConvertor.PersonScreeningDetailsBeanToEntity(onboardingCheckListBean.getPersonScreeningDetails(), categoryService);
		personScreeningDetails.setStatus(statusDao.getStatusBeanOnResultName(string2));
		onboardingCheckListBean.setOnboardingStatus(personScreeningDetails.getStatus().getResultName());
		
		OnboardingCheckList onboardingCheckList=OnboardingCheckListConverter.convertOnboardingCheckListBeanToOnboardingCheckListEntity(onboardingCheckListBean);
		onboardingCheckList.setPersonScreeningDetails(personScreeningDetails);
		onboardingResourceService.updateOnboardingCheckList(onboardingCheckList);*/
		
		
	}
	

	@Override
	public List<IntervieweeBean> getIntervieweeForDailyEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IntervieweeBean> getExitedInterviewees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkIfIntervieweeExists(int intervieweeOracleID,
			int interviewerOracleId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String checkIfIntervieweeSameAsScreener(int intervieweeOracleID,
			int interviewerOracleId) {
		
		if(intervieweeOracleID==interviewerOracleId){
			return "sameAsInterviewee";
		}else 
		return "unique";
	}
	
	
	
	@Override
	public String  checkIfDuplicateIntervieweeEntryExists(int intervieweeOracleId,int screenerOracleId,String startDate) {
		CustomLoggerUtils.INSTANCE.log.info("inside u checkIfDuplicateIntervieweeEntryExists(int intervieweeOracleId,int screenerOracleId,String startDate) method of IntervieweeServiceImpl class");
		return intervieweeDao.checkIfDuplicateIntervieweeEntryExists(intervieweeOracleId,screenerOracleId,startDate);
	}
	@Override
	public String  checkIfDuplicateIntervieweeEntryExistsForTemp(String tempEmail,int screenerOracleId,String startDate) {
		CustomLoggerUtils.INSTANCE.log.info("inside u checkIfDuplicateIntervieweeEntryExists(int intervieweeOracleId,int screenerOracleId,String startDate) method of IntervieweeServiceImpl class");
		return intervieweeDao.checkIfDuplicateIntervieweeEntryExistsForTemp(tempEmail,screenerOracleId,startDate);
	}
	
	public PersonScreeningDetailsBean fetchPersonScreeningDetails(long Id){
		return PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(intervieweeDao.fetchPersonScreeningDetails(Id));
		
	}

	@Override
	public IntervieweeBean fetchIntervieweeOnOracleId(int oracleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeProject(int oracleId, String newProjectName,
			String newIQNName, String newAtrackName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewProject(int oracleId, String newProjectName,
			String newIQNName, String newAtrackName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long reboardInterviewee(int oracleID,
			OnboardingResourceBean onboardingResourceBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IntervieweeBean setFieldsForInterviewee(
			IntervieweeBean intervieweeBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long onboardInterviewee(int oracleID,
			OnboardingResourceBean onboardingResourceBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PersonScreeningDetailsBean> retrieveAllInterviewees() {
		// TODO Auto-generated method stub
		List<PersonScreeningDetails> personScreeningDetailsList = intervieweeDao.getAllPersonScreeningDetails();
		List<PersonScreeningDetailsBean> personScreeningDetailsBeanList= new ArrayList<>();
		
		for(PersonScreeningDetails personScreeningDetails: personScreeningDetailsList)
		{	
			PersonScreeningDetailsBean personScreeningDetailsBean=PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(personScreeningDetails);
			PersonBean personBean= personLookupService.getPersonByNTId(personScreeningDetailsBean.getPersonBean().getPersonNtId());
			
			personScreeningDetailsBean.getPersonBean().setPersonDetails(personBean);
			
			personScreeningDetailsBeanList.add(personScreeningDetailsBean);
		}
		
		return personScreeningDetailsBeanList;
	}

	

	@Override
	public Map<String, Object> populateServerInterviewee(int currentPage, int numberOfRows, String columnName,
			String sortDirection, String searchKey) {
		// TODO Auto-generated method stub
		return intervieweeDao.populateInterviewee(currentPage, numberOfRows, columnName, sortDirection, searchKey);
	}

	@Override
	public Map<String, Object> getIntervieweesCountAndList(int start, int rows, int colNum, String searchKey, String sortDirection) {
		 String  columnName = "";
		 String[] columnNamess = { "I.person_Screening_Id", "P.person_Oracle_Id",
				 "position.domain", "I.screening_Start_Date",
					"I.screening_End_Date", "location_new.location_Name", "status.status_Name",
					"status.result_Name", "project_new.project_Name", "category.category_name",
					"I.feedback" ,"P.is_Contractor"};
         int   sEcho = 0, total = 0;
       
         	
         	if (colNum < 0 || colNum > columnNamess.length) {
         		colNum = 0;
         	}
         	columnName = columnNamess[colNum];
         	if (start < 0) {
         		start = 0;
         	}
         	if (rows < 0) {
         		rows = 0;
         	}
         	if (sortDirection.equals("none")) {
         		sortDirection = "";
         	}
         	/*
         	 * CustomLoggerUtils.INSTANCE.log.info("Interviewee Datatable. start:"
         	 * + sStart + " amount:" + sAmount + " sortingColumn:" + columnName
         	 * + " dir:" + sdir + " search:" + sSearch);
         	 */
         
        
         
         Map<String, Object> intervieweeMap;
         intervieweeMap= populateIntervieweeData(start,
         		rows, columnName, sortDirection, searchKey);
         
		
		List<PersonScreeningDetails> intervieweeList = (List<PersonScreeningDetails>) intervieweeMap.get("intervieweeList");
		List<PersonScreeningDetailsBean> personScreeningDetailsBeansList= new ArrayList<>(intervieweeList.size());
		
		int count = 0;
		for(PersonScreeningDetails personScreeningDetails : intervieweeList)
		{
			
			PersonScreeningDetailsBean personScreeningDetailsBean= PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(personScreeningDetails); 
			personScreeningDetailsBean.setStringScreeningStartDate(DateFormatUtils.format(personScreeningDetails.getScreeningStartDate(), "MM/dd/yy"));
			personScreeningDetailsBean.setStringScreeningEndDate(DateFormatUtils.format(personScreeningDetails.getScreeningEndDate(), "MM/dd/yy"));
			personScreeningDetailsBean.getPersonBean().setPersonDetails(personLookupService.getPersonByNTId(personScreeningDetails.getPerson().getPersonNtId()));
			personScreeningDetailsBeansList.add(personScreeningDetailsBean);
			count++;
		}
		
		total = (int) intervieweeMap.get("count");
		 Map<String,Object> personScreeningDetailsBeanListMap = new HashMap<String,Object>(2);
		 personScreeningDetailsBeanListMap.put("list",personScreeningDetailsBeansList);
		 personScreeningDetailsBeanListMap.put("totalListSize", total);
		
		 return personScreeningDetailsBeanListMap;
		
		//return personScreeningDetailsBeansList;
				
		
		
//		rows = (int) intervieweeMap.get("count");
//		String data = "";
//		int i = 0;
//		String aaData;
//		if (intervieweeList == null || intervieweeList.isEmpty()) {
//			aaData = "{\"sEcho\":\"" + sEcho + "\",\"iTotalRecords\":\""
//					+ total + "\",\"iTotalDisplayRecords\":\""
//					+ rows + "\",\"aaData\":[]}";
//			aaData = "{\"totalListSize\":\"" + total
//					+ "\",\"list\":[" + data + "]}";
//			
//		}
//		String positionName;
//		String locationName;
//		String personName;
//		String isPersonContractor;
//		
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		
//		do {
//			if (i != 0) {
//				data += ",";
//			}
//			
//			
//			
//			if(intervieweeList.get(i).getPerson().getPosition()!=null)
//			 positionName=intervieweeList.get(i).getPerson().getPosition().getPositionName();
//			else
//			positionName="";
//			
//			if(intervieweeList.get(i).getPerson().getLocation()!=null)
//				locationName=intervieweeList.get(i).getPerson().getLocation().getCity();
//			else
//				locationName="";
//			
//			if(intervieweeList.get(i).getPerson().getLocation()!=null)
//				locationName=intervieweeList.get(i).getPerson().getLocation().getCity();
//			else
//				locationName="";
//			
//			if(personLookupService.getPersonByNTId(intervieweeList.get(i).getPerson().getPersonNtId())!=null)
//				personName=personLookupService.getPersonByNTId(intervieweeList.get(i).getPerson().getPersonNtId()).getName();
//			else
//				personName="";
//			
//			if(intervieweeList.get(i).getPerson().getIsContractor()!=null)
//				isPersonContractor=intervieweeList.get(i).getPerson().getIsContractor().toString();
//			else
//				isPersonContractor="";
//			
//			if (intervieweeList.get(i).getProjectNew() != null) {
//				if(intervieweeList.get(i).getPerson().getPersonOracleId()==0){
//					data += "{\"intervieweeId\":\""
//							+ intervieweeList.get(i).getPersonScreeningId()
//							+ "\",\"intervieweeName\":\""
//							+ intervieweeList.get(i).getPerson().getTempPerson().getTempPersonName()
//							+ "\",\"intervieweeOracleId\":\""
//							+ "0"
//							+ "\",\"positionAppliedFor\":\""
//							+ positionName
//							+ "\",\"startDate\":\""
//							+ formatter.format(intervieweeList.get(i).getScreeningStartDate())
//							+ "\",\"endDate\":\""
//							+ formatter.format(intervieweeList.get(i).getScreeningEndDate())
//							+ "\",\"location\":\""
//							+ locationName
//							+ "\",\"status\":\""
//							+ intervieweeList.get(i).getStatus().getStatusName()
//							+ "\",\"result\":\""
//							+ intervieweeList.get(i).getStatus().getResultName()
//							+ "\",\"project\":\""
//							+ intervieweeList.get(i).getProjectNew()
//									.getProjectName()
//							+ "\",\"domain\":\""
//							+ intervieweeList.get(i).getCategory()
//									.getCategoryName() + "\",\"comments\":\""
//							+ intervieweeList.get(i).getFeedback()
//							+ "\",\"isContractor\":\""+ isPersonContractor
//									+ "\"}";
//					
//					}else{
//						
//						data += "{\"intervieweeId\":\""
//						+ intervieweeList.get(i).getPersonScreeningId()
//						+ "\",\"intervieweeName\":\""
//						+ personName
//						+ "\",\"intervieweeOracleId\":\""
//						+ intervieweeList.get(i).getPerson().getPersonOracleId()
//						+ "\",\"positionAppliedFor\":\""
//						+ positionName
//						+ "\",\"startDate\":\""
//						+ formatter.format(intervieweeList.get(i).getScreeningStartDate())
//						+ "\",\"endDate\":\""
//						+ formatter.format(intervieweeList.get(i).getScreeningEndDate())
//						+ "\",\"location\":\""
//						+ locationName
//						+ "\",\"status\":\""
//						+ intervieweeList.get(i).getStatus().getStatusName()
//						+ "\",\"result\":\""
//						+ intervieweeList.get(i).getStatus().getResultName()
//						+ "\",\"project\":\""
//						+ intervieweeList.get(i).getProjectNew()
//								.getProjectName()
//						+ "\",\"domain\":\""
//						+ intervieweeList.get(i).getCategory()
//								.getCategoryName() + "\",\"comments\":\""
//						+ intervieweeList.get(i).getFeedback() 
//						+ "\",\"isContractor\":\""+ isPersonContractor
//								+ "\"}";
//				}
//			} else {
//				if(intervieweeList.get(i).getPerson().getPersonOracleId()==0){
//					data += "{\"intervieweeId\":\""
//							+ intervieweeList.get(i).getPersonScreeningId()
//							+ "\",\"intervieweeName\":\""
//							+intervieweeList.get(i).getPerson().getTempPerson().getTempPersonName()
//							+ "\",\"intervieweeOracleId\":\""
//							+ "0"
//							+ "\",\"positionAppliedFor\":\""
//							+ positionName 
//							+ "\",\"startDate\":\""
//							+ formatter.format(intervieweeList.get(i).getScreeningStartDate())
//							+ "\",\"endDate\":\""
//							+ formatter.format(intervieweeList.get(i).getScreeningEndDate())
//							+ "\",\"location\":\""
//							+ locationName
//							+ "\",\"status\":\""
//							+ intervieweeList.get(i).getStatus().getStatusName()
//							+ "\",\"result\":\""
//							+ intervieweeList.get(i).getStatus().getResultName()
//							+ "\",\"project\":\""
//							+ ""
//							+ "\",\"domain\":\""
//							+ intervieweeList.get(i).getCategory()
//									.getCategoryName() + "\",\"comments\":\""
//							+ intervieweeList.get(i).getFeedback() 
//							+ "\",\"isContractor\":\""+ isPersonContractor
//									+ "\"}";
//					
//				}else{
//				data += "{\"intervieweeId\":\""
//						+ intervieweeList.get(i).getPersonScreeningId()
//						+ "\",\"intervieweeName\":\""
//						+ personName
//						+ "\",\"intervieweeOracleId\":\""
//						+ intervieweeList.get(i).getPerson().getPersonOracleId()
//						+ "\",\"positionAppliedFor\":\""
//						+ positionName
//						+ "\",\"startDate\":\""
//						+ formatter.format(intervieweeList.get(i).getScreeningStartDate())
//						+ "\",\"endDate\":\""
//						+ formatter.format(intervieweeList.get(i).getScreeningEndDate())
//						+ "\",\"location\":\""
//						+ locationName
//								
//						+ "\",\"status\":\""
//						+ intervieweeList.get(i).getStatus().getStatusName()
//						+ "\",\"result\":\""
//						+ intervieweeList.get(i).getStatus().getResultName()
//						+ "\",\"project\":\""
//						+ ""
//						+ "\",\"domain\":\""
//						+ intervieweeList.get(i).getCategory()
//								.getCategoryName() + "\",\"comments\":\""
//						+ intervieweeList.get(i).getFeedback() 
//						+ "\",\"isContractor\":\""+ isPersonContractor
//								+ "\"}";
//				}
//			}
//
//			i++;
//		} while (i < intervieweeList.size());
//		aaData = "{\"totalListSize\":\"" + total
//				+ "\",\"list\":[" + data + "]}";
//		
//		return aaData;
	}

	
	
}