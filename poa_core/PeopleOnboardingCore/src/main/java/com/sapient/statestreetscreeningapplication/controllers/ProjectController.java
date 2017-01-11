package com.sapient.statestreetscreeningapplication.controllers;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;
import com.sapient.statestreetscreeningapplication.model.entity.ScreeningDetailsEntity;
import com.sapient.statestreetscreeningapplication.model.service.CategoryService;
import com.sapient.statestreetscreeningapplication.model.service.IntervieweeService;
import com.sapient.statestreetscreeningapplication.model.service.LocationService;
import com.sapient.statestreetscreeningapplication.model.service.OnboardingResourceService;
import com.sapient.statestreetscreeningapplication.model.service.PersonService;
import com.sapient.statestreetscreeningapplication.model.service.PositionService;
import com.sapient.statestreetscreeningapplication.model.service.ProjectService;
import com.sapient.statestreetscreeningapplication.model.service.SendEmailService;
import com.sapient.statestreetscreeningapplication.model.service.StatusService;
import com.sapient.statestreetscreeningapplication.model.service.TeamService;
import com.sapient.statestreetscreeningapplication.ui.bean.LocationNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonScreeningDetailsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonStaffingBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PositionBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.RateBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ScoresNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.TeamBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonNewConverter;

@RestController
public class ProjectController {

	/** The team service. */
	@Autowired
	private TeamService teamService;

	@Autowired
	private LocationService locationService;

	/** The status service. */
	@Autowired
	private StatusService statusService;

	/** The project service. */
	@Autowired
	private ProjectService projectService;

	/** The position service. */
	@Autowired
	private PositionService positionService;

	/** The category service. */
	@Autowired
	private CategoryService categoryService;

	/** The interviewee service. */
	@Autowired
	private IntervieweeService intervieweeService;

	/** The person service. */
	@Autowired
	PersonService personService;

	/** The send email service. */
	@Autowired
	private SendEmailService sendEmailService;

	@Autowired
	private OnboardingResourceService onboardingResourceService;

	private List<ProjectNewBean> allProjects;
	private int interviewerOracleId;
	private String intervieweeName;
	private boolean email;

	@CrossOrigin
	@RequestMapping("/retrieveAllProjects")
	public List<ProjectNewBean> viewProjects() {
		CustomLoggerUtils.INSTANCE.log.info("Inside viewProjects() method");
		allProjects = projectService.getAllProjectBeans();
		return allProjects;
	}

	@CrossOrigin
	@RequestMapping("/retrieveAllProjectNames")
	public List<String> viewAllProjectNames() {
		return projectService.getAllUsedProjectNames();
	}

	@CrossOrigin
	@RequestMapping(value = "/retriveProjectsOfTeam", method = RequestMethod.GET)
	public String allProjectOfTeam(@RequestParam("teamName") String teamName) {

		List<String> specificTeamProjects = teamService.fetchProjectNamesForTeam(teamName);
		Gson gson = new Gson();
		String specificTeamProjectsJson = gson.toJson(specificTeamProjects);
		return "{\"records\": " + specificTeamProjectsJson + "}";
	}

	@CrossOrigin
	@RequestMapping("/retrieveAllProjectBeans")
	public List<ProjectNewBean> retrieveAllProjectBeans() {
		return projectService.getAllProjectBeans();
	}

	
	@CrossOrigin
	@RequestMapping(value = "/retrieveAllProjectBeansForTeam", method = RequestMethod.POST)
	public List<ProjectNewBean> retrieveAllProjectBeansForTeam(@RequestBody String teamName) {

		return projectService.getAllProjectBeansforTeamName(teamName);
	}

	@CrossOrigin
	@RequestMapping(value = "/editProject", method = RequestMethod.POST)
	public void editProject(@RequestBody String projectNewBean) throws ParseException {

		Gson gson = new Gson();
		System.out.println(projectNewBean.toString());
		ProjectNewBean project = gson.fromJson(projectNewBean, ProjectNewBean.class);
		// String endDate=project.getProjectEndDate();
		// System.out.println(endDate);
		// SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		// Date date=format.parse(endDate.toString());

		// System.out.println(date.toString());

		/*
		 * String string = "January 2, 2010"; DateTimeFormatter formatter =
		 * DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH); LocalDate
		 * date1 = LocalDate.parse(endDate, formatter);
		 * System.out.println(date1); // 2010-01-02
		 */

		// System.out.println(endDate);
		// System.out.println(project.toString());

		// projectService.editProject(project);
		project.getId();
	}

	@CrossOrigin
	@RequestMapping(value = "/addProject", method = RequestMethod.POST)
	public void addProject(@RequestBody String projectNewBean) {

		Gson gson = new Gson();
		ProjectNewBean project = gson.fromJson(projectNewBean, ProjectNewBean.class);
		projectService.saveProjectDetails(project);
	}
	

	@CrossOrigin
	@RequestMapping(value = "/retriveProjectsForTeam", method = RequestMethod.GET)
	public String allProjectNameOfTeamString(@RequestParam("teamName") String teamName) {

		List<String> specificTeamProjects = teamService.fetchProjectNamesForTeam(teamName);
		Gson gson = new Gson();
		String specificTeamProjectsJson = gson.toJson(specificTeamProjects);
		return "{\"records\": " + specificTeamProjectsJson + "}";
	}

	@CrossOrigin
	@RequestMapping(value = "/getProjectStartDate", method = RequestMethod.GET)
	public Date getStartDateofProjectName(@RequestParam("projectName") String projectName) {

		Date projectStartDate = projectService.getProjectNewEntityByName(projectName).getProjectStartDate();
		return projectStartDate;

	}

	@CrossOrigin
	@RequestMapping(value = "/getProjectEndDate", method = RequestMethod.GET)
	public Date getEndDateofProjectName(@RequestParam("projectName") String projectName) {

		Date projectEndDate = projectService.getProjectNewEntityByName(projectName).getProjectEndDate();
		return projectEndDate;

	}

	@CrossOrigin
	@RequestMapping(value = "/getEmptyPersonStaffingBean", method = RequestMethod.GET)
	public PersonStaffingBean returnEmptyPersonStaffingBean() {

		PersonStaffingBean personStaffingBean = new PersonStaffingBean();
		return personStaffingBean;
	}

	@CrossOrigin
	@RequestMapping(value = "/getEmptyPersonScreeningDetailsBean", method = RequestMethod.GET)
	public PersonScreeningDetailsBean returnEmptyPersonScreeningDetailsBean() {
		PersonScreeningDetailsBean personScreeningDetailsBean = new PersonScreeningDetailsBean();
		return personScreeningDetailsBean;
	}

	@CrossOrigin
	@RequestMapping(value = "/getEmptyPersonScreeningDetails", method = RequestMethod.GET)
	public PersonScreeningDetails returnEmptyPersonScreeningDetails() {
		PersonScreeningDetails personScreeningDetails = new PersonScreeningDetails();
		return personScreeningDetails;
	}

	@CrossOrigin
	@RequestMapping(value = "/getEmptyOnboardingChecklistBean", method = RequestMethod.GET)
	public OnboardingCheckListBean returnEmptyonboardingCheckListBean() {

		OnboardingCheckListBean onboardingCheckListBean = new OnboardingCheckListBean();
		return onboardingCheckListBean;
	}

	@CrossOrigin
	@RequestMapping(value = "/getEmptyRateBean", method = RequestMethod.GET)
	public RateBean returnEmptyRateBean() {
		RateBean rateBean = new RateBean();
		return rateBean;
	}

	@CrossOrigin
	@RequestMapping(value = "/sumitOnboardingWithoutScreeningForm", method = RequestMethod.POST)
	public void sumitOnboardingWithoutScreeningForm(@RequestBody String screeningDetailsEntity) throws ParseException {
		Gson gson = new Gson();
		ScreeningDetailsEntity bean = gson.fromJson(screeningDetailsEntity, ScreeningDetailsEntity.class);
		PersonScreeningDetailsBean personScreeningDetailsBean = bean.getOnboardingCheckListBean()
				.getPersonScreeningDetails();
		PersonStaffingBean personStaffingBean = bean.getOnboardingCheckListBean().getPersonStaffingBean();
		OnboardingCheckListBean onboardingCheckListBean = bean.getOnboardingCheckListBean();
		CustomLoggerUtils.INSTANCE.log
				.info("inside initiateWithoutScreening() method and in InitiateWithoutScreeningAction");

		// Converting Date to approriate String so that DAO layer can parse
		String startDate = personStaffingBean.getStartDate();
		personStaffingBean.setStartDate(customDateFormator(startDate));

		TeamBean teamBean = teamService
				.fetchTeamByName(personScreeningDetailsBean.getProjectBean().getTeamBean().getTeamName());
		ProjectNewBean project = projectService.fetchProjectByTeamAndProjectName(
				personScreeningDetailsBean.getProjectBean().getProjectName(), teamBean);

		boolean valid = true;

		if (valid) {

			CustomLoggerUtils.INSTANCE.log
					.info(personScreeningDetailsBean.getPersonBean().getPosition().getPositionName());

			String positionName = personScreeningDetailsBean.getPersonBean().getPosition().getPositionName();

			PositionBean position = positionService.getPositionByName(positionName);

			if (position == null) {
				String positionName1 = personScreeningDetailsBean.getPersonBean().getPosition().getPositionName()
						.split(",")[0].split("=")[1];

				position = positionService.getPositionByName(positionName1);

			}
			LocationNewBean location = locationService
					.getNewLocationByName(personScreeningDetailsBean.getPersonBean().getLocation().getCity());
			Person personEntityfromPersonId = null;
			PersonNewBean personfromPersonId = null;
			if (!personScreeningDetailsBean.getPersonBean().getIsTemp()) {
				personEntityfromPersonId = personService
						.getPersonByOracleId(personScreeningDetailsBean.getPersonBean().getPersonOracleId());
			}
			if (personEntityfromPersonId != null || personScreeningDetailsBean.getPersonBean().getIsTemp()) {
				if (!personScreeningDetailsBean.getPersonBean().getIsTemp()) {
					personfromPersonId = PersonNewConverter.personNewEntityToBean(personEntityfromPersonId);
					personfromPersonId.setPersonName(personScreeningDetailsBean.getPersonBean().getPersonName());
				} else {
					personfromPersonId = personScreeningDetailsBean.getPersonBean();
					personfromPersonId.setPersonName(
							personScreeningDetailsBean.getPersonBean().getTempPersonBean().getTempPersonName());
				}

				personfromPersonId.setPosition(position);
				personfromPersonId.setLocation(location);

			}

			/* personScreeningDetailsBean.getPersonBean().setIsTemp(false); */
			personScreeningDetailsBean.getPersonBean().setPosition(position);
			personScreeningDetailsBean.getPersonBean().setLocation(location);
			personScreeningDetailsBean.setProjectBean(
					project);
			
			personScreeningDetailsBean.setStatusBean(statusService.getStatusBeanOnStatusAndResultName(
					personScreeningDetailsBean.getStatusBean().getStatusName(),
					personScreeningDetailsBean.getStatusBean().getResultName()));

			int leadOracleId = personScreeningDetailsBean.getScreenerBean().getPersonOracleId();
			personScreeningDetailsBean.setScreenerBean(personService.getPersonNewBeanByPersonOracleId(leadOracleId));
			personScreeningDetailsBean.setStringScreeningStartDate(personStaffingBean.getStartDate());

			if (project.getProjectEndDate() != null) {
				personScreeningDetailsBean.setStringScreeningEndDate(project.getProjectEndDate());
			}
			if (personStaffingBean.getComments() != null)
				personScreeningDetailsBean.setFeedback(personStaffingBean.getComments());
			else
				personScreeningDetailsBean.setFeedback("");

			String intervieweeName;
			List<ScoresNewBean> scores;
			String body = null;
			String subject = null;

			if (personfromPersonId != null) {
				personScreeningDetailsBean.setPersonBean(personfromPersonId);
			}

			String duplicateOrUnique = null;
			if (!personScreeningDetailsBean.getPersonBean().getIsTemp()) {
				duplicateOrUnique = intervieweeService.checkIfDuplicateIntervieweeEntryExists(
						personScreeningDetailsBean.getPersonBean().getPersonOracleId(), leadOracleId,
						personScreeningDetailsBean.getStringScreeningStartDate());
			} else {
				duplicateOrUnique = intervieweeService.checkIfDuplicateIntervieweeEntryExistsForTemp(
						personScreeningDetailsBean.getPersonBean().getTempPersonBean().getTempPersonEmail(),
						leadOracleId, personScreeningDetailsBean.getStringScreeningStartDate());
			}
			if (duplicateOrUnique.equalsIgnoreCase("unique")) {
				// personScreeningDetailsBean.setPersonScreeningId(0L);

				if (!personScreeningDetailsBean.getPersonBean().getIsTemp()) {
					intervieweeName = personScreeningDetailsBean.getPersonBean().getPersonName();
				} else {
					intervieweeName = personScreeningDetailsBean.getPersonBean().getTempPersonBean()
							.getTempPersonName();
				}

				personScreeningDetailsBean = intervieweeService.saveInterviewee(personScreeningDetailsBean);
				onboardingCheckListBean.setPersonScreeningDetails(personScreeningDetailsBean);
				PersonNewBean currentUser = personScreeningDetailsBean.getScreenerBean();
				onboardingCheckListBean.setInitiator(currentUser);

				String[] dlArray;
				PersonNewBean personBean = personScreeningDetailsBean.getPersonBean();
				personStaffingBean
						.setCategoryBean(categoryService.getCategoryByName(personScreeningDetailsBean.getCategory()));

				personStaffingBean.setPosition(position);
				personStaffingBean.setLocation(location);
				personBean.setPosition(personStaffingBean.getPosition());
				personBean.setLocation(location);
				personStaffingBean.setProject(project);
				personStaffingBean.setPerson(personBean);
				personStaffingBean.setEndDate(project.getProjectEndDate());
				personStaffingBean.setRateBean(personStaffingBean.getRateBean());
				onboardingCheckListBean.setPersonStaffingBean(personStaffingBean);
				onboardingCheckListBean.setPerson(personBean);
				String onboardingStatus = "Forms requested";
				onboardingCheckListBean.setOnboardingStatus(personScreeningDetailsBean.getStatusBean().getResultName());

				/*
				 * //DL Handling if(!multipleDlvalues.equals("")) {
				 * dlArray=multipleDlvalues.split(","); for(String dlName:
				 * dlArray){ personStaffingBean.getEmailDls().add(dlService.
				 * getDlByDlNameAndProjectIdAndLocationId(dlName,project.getId()
				 * ,location.getLocationId())); } }
				 */
				int selectedBGC = 0;
				if (location.getCity().equalsIgnoreCase("Noida") || location.getCity().equalsIgnoreCase("Bangalore")
						|| location.getCity().equalsIgnoreCase("Gurgaon"))
					selectedBGC = 1;
				else if (location.getCity().equalsIgnoreCase("New York"))
					selectedBGC = 2;
				else if (location.getCity().equalsIgnoreCase("Boston"))
					selectedBGC = 3;
				else if (location.getCity().equalsIgnoreCase("San Jose"))
					selectedBGC = 4;
				else if (location.getCity().equalsIgnoreCase("Toronto"))
					selectedBGC = 5;
				CustomLoggerUtils.INSTANCE.log.info(personStaffingBean.toString());
				intervieweeService.onBoardWithoutScreening(personStaffingBean, onboardingCheckListBean);
				if (selectedBGC != 0) {
					onboardingResourceService.sendBGCdocumentsToCandidate(selectedBGC, leadOracleId, personStaffingBean,
							onboardingCheckListBean, currentUser);
				} else {
					// errorMsg="BGC Documents could not be sent to the
					// candidate as mail template is not available.";
					// addActionError("BGC Documents could not be sent to the
					// candidate as mail template is not available.");
				}
				// projectLogService.saveProjectLog(newProjectName,newIQNName,newAtrackName,
				// ssStartDate,iqnEndDate,oracleID);
				// addActionMessage("The person has been onboarded
				// successfully");
				// actionMsg="The person has been onboarded successfully";
			} else {
				// addActionMessage("The combination for interviewee,
				// interviewer and startDate already exists in the database.");
				// actionMsg="The combination for interviewee, interviewer and
				// startDate already exists in the database.";
			}
		}

	}

	@CrossOrigin
	@RequestMapping(value = "/submitScreeningForm", method = RequestMethod.POST)
	public void submitScreeningForm(@RequestBody String screeningDetailsEntity) {

		Gson gson = new Gson();
		ScreeningDetailsEntity bean = gson.fromJson(screeningDetailsEntity, ScreeningDetailsEntity.class);

		PersonScreeningDetailsBean personScreeningDetailsBean = bean.getOnboardingCheckListBean()
				.getPersonScreeningDetails();
		personScreeningDetailsBean.getPersonBean().setPosition(positionService
				.getPositionByName(personScreeningDetailsBean.getPersonBean().getPosition().getPositionName()));
		personScreeningDetailsBean.getPersonBean().setLocation(locationService
				.getNewLocationByName(personScreeningDetailsBean.getPersonBean().getLocation().getCity()));

		if (!personScreeningDetailsBean.getProjectBean().getTeamBean().getTeamName().equals("")) {

			TeamBean teamBean = teamService
					.fetchTeamByName(personScreeningDetailsBean.getProjectBean().getTeamBean().getTeamName());
			ProjectNewBean project = projectService.fetchProjectByTeamAndProjectName(
					personScreeningDetailsBean.getProjectBean().getProjectName(), teamBean);
			personScreeningDetailsBean.setProjectBean(project);
		} else {
			personScreeningDetailsBean.setProjectBean(null);
		}

		interviewerOracleId = personScreeningDetailsBean.getScreenerBean().getPersonOracleId();;
		personScreeningDetailsBean.setScreenerBean(personService.getPersonNewBeanByPersonOracleId(interviewerOracleId));
		
		personScreeningDetailsBean.setStatusBean(statusService.getStatusBeanOnStatusAndResultName(
				personScreeningDetailsBean.getStatusBean().getStatusName(),
				personScreeningDetailsBean.getStatusBean().getResultName()));

		List<ScoresNewBean> scoresFromUI = bean.getScoreListFromUI();
		Set<ScoresNewBean> scores = new HashSet<>();

		for (ScoresNewBean s : scoresFromUI) {

			if (isValidScoreNewBean(s)) {
				s.setCriteriaBean(s.getTopicBean().getCriteriaBean());
				scores.add(s);
			}
		}

		personScreeningDetailsBean.setScoreList(scores);

		// Converting Date to approriate String so that DAO layer can parse
		String endDate = personScreeningDetailsBean.getStringScreeningEndDate();
		String startDate = personScreeningDetailsBean.getStringScreeningStartDate();
		try {
			personScreeningDetailsBean.setStringScreeningEndDate(customDateFormator(endDate));
			personScreeningDetailsBean.setStringScreeningStartDate(customDateFormator(startDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String result = null;

		// he is permanent
		if (!personScreeningDetailsBean.getPersonBean().getIsTemp()) {
			result = intervieweeService.checkIfDuplicateIntervieweeEntryExists(
					personScreeningDetailsBean.getPersonBean().getPersonOracleId(), interviewerOracleId,
					personScreeningDetailsBean.getStringScreeningStartDate());

			intervieweeName = personScreeningDetailsBean.getPersonBean().getPersonName();
		} else {
			// temp person
			result = intervieweeService.checkIfDuplicateIntervieweeEntryExistsForTemp(
					personScreeningDetailsBean.getPersonBean().getTempPersonBean().getTempPersonEmail(),
					interviewerOracleId, personScreeningDetailsBean.getStringScreeningStartDate());

			intervieweeName = personScreeningDetailsBean.getPersonBean().getTempPersonBean().getTempPersonName();

		}

		if (result.equals("duplicate")) {
			// addActionMessage("The combination of interviewer-interviewee
			// already exists for the given start date.");
		} else {

			// Permanent Employee
			if (!personScreeningDetailsBean.getPersonBean().getIsTemp()) {

				personScreeningDetailsBean.getPersonBean().setPersonName(intervieweeName);
			}
			personScreeningDetailsBean = intervieweeService.saveInterviewee(personScreeningDetailsBean);
			personScreeningDetailsBean.setScoreList(scores);
			if (!personScreeningDetailsBean.getPersonBean().getIsTemp()) {
				personScreeningDetailsBean.getPersonBean().setPersonName(intervieweeName);
			} else {
				personScreeningDetailsBean.getPersonBean().getTempPersonBean().setTempPersonName(intervieweeName);
			}

			String header = "PFA details of the new candidate, " + intervieweeName;
			personScreeningDetailsBean.getPersonBean().setPersonName(intervieweeName);
			String content_PersonDetails = formEmailBody(personScreeningDetailsBean);

			String body = "<br>" + header + "<br><br>" + content_PersonDetails;
			String subject = "New interviewee added | " + intervieweeName;
			// successMsg = "The interviewee is added successfully";
			// addActionMessage("The interviewee is added successfully");
			if (email) {
				sendEmailService.sendEmail(personScreeningDetailsBean, subject, body);
				CustomLoggerUtils.INSTANCE.log.info("Email update sent");
			}

		}

	}

	public boolean isValidScoreNewBean(ScoresNewBean scoreNewBean) {

		if (scoreNewBean.getComments() == null) {
			return false;
		} else {
			
			return true;
		}

	}

	private String formEmailBody(PersonScreeningDetailsBean personScreeningDetailsBean) {
		String comments = "<br>";
		String[] commentList = personScreeningDetailsBean.getFeedback().split("\n");
		for (String c : commentList) {
			comments = comments + "<br>" + c;
		}

		String content_PersonDetails = "<table cellpadding='10' border='1'>" + "<tr>" + "<td>"
				+ "<b>INTERVIEWEE NAME</b>" + "</td>" + "<td> "
				+ personScreeningDetailsBean.getPersonBean().getPersonName() + " " + "</td>" + "</tr>" +

				"<tr>" + "<td>" + "<b>INTERVIEWEE ORACLE ID</b>" + "</td>" + "<td>"
				+ personScreeningDetailsBean.getPersonBean().getPersonOracleId() + " " + "</td>" + "</tr>" +

				"<tr>" + "<td>" + "<b>CATEGORY</b>" + "</td>" + "<td>" + personScreeningDetailsBean.getCategory() + " "
				+ "</td>" + "</tr>" +

				"<tr>" + "<td>" + "<b>START DATE</b>" + "</td>" + "<td>"
				+ personScreeningDetailsBean.getStringScreeningStartDate() + " " + "</td>" + "</tr>" +

				"<tr>" + "<td>" + "<b>END DATE</b>" + "</td>" + "<td>"
				+ personScreeningDetailsBean.getStringScreeningEndDate() + " " + "</td>" + "</tr>" +

				"<tr>" + "<td>" + "<b>STATUS</b>" + "</td>" + "<td>"
				+ personScreeningDetailsBean.getStatusBean().getStatusName() + " " + "</td>" + "</tr>" +

				"<tr>" + "<td>" + "<b>RESULT</b>" + "</td>" + "<td>"
				+ personScreeningDetailsBean.getStatusBean().getResultName() + " " + "</td>" + "</tr>" +

				"<tr>" + "<td>" + "<b>PROJECT</b>" + "</td>" + "<td>"
				+ personScreeningDetailsBean.getProjectBean().getProjectName() + " " + "</td>" + "</tr>" +

				"<tr>" + "<td>" + "<b>POSITION</b>" + "</td>" + "<td>"
				+ personScreeningDetailsBean.getPersonBean().getPosition().getPositionName() + " " + "</td>" + "</tr>" +

				"<tr>" + "<td>" + "<b>LOCATION</b>" + "</td>" + "<td>"
				+ personScreeningDetailsBean.getPersonBean().getLocation().getCity() + " " + "</td>" + "</tr>" +

				"<tr>" + "<td>" + "<b>COMMENT</b>" + "</td>" + "<td>" + comments + " " + "</td>" + "</tr>"
				+ "</table><br><br>";
		Set<ScoresNewBean> scoreList = personScreeningDetailsBean.getScoreList();
		content_PersonDetails = content_PersonDetails + "<table cellpadding='10' border='1'>" + "<tr>"
				+ "<td><b>CRITERIA</b></td>" + "<td><b>TOPIC</b></td>" + "<td><b>SCORE</b></td>"
				+ "<td><b>COMMENTS</b></td>" + "</tr>";

		for (ScoresNewBean scb : scoreList) {
			content_PersonDetails = content_PersonDetails + "<tr>" + "<td>" + scb.getCriteriaBean().getCriteriaName()
					+ " " + "</td>" + "<td>" + scb.getTopicBean().getTopicName() + " " + "</td>" + "<td>"
					+ scb.getScore() + " " + "</td>" + "<td>" + scb.getComments() + " " + "</td>" + "</tr>";

		}

		content_PersonDetails = content_PersonDetails + "</table>";

		return content_PersonDetails;
	}

	public String customDateFormator(String stringdate) throws ParseException {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
		Date date = format.parse(stringdate);
		Format formatter = new SimpleDateFormat("MM/dd/yyyy");
		String s = formatter.format(date);
		return s;
	}

}
