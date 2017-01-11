package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.entity.OnboardingCheckList;
import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.entity.TeamLeadEntity;
import com.sapient.statestreetscreeningapplication.model.service.IntervieweeService;
import com.sapient.statestreetscreeningapplication.model.service.OnboardingResourceService;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.model.service.PersonService;
import com.sapient.statestreetscreeningapplication.model.service.ProjectLogService;
import com.sapient.statestreetscreeningapplication.model.service.ProjectService;
import com.sapient.statestreetscreeningapplication.model.service.SendEmailService;
import com.sapient.statestreetscreeningapplication.model.service.TeamService;
import com.sapient.statestreetscreeningapplication.ui.bean.Attachment;
import com.sapient.statestreetscreeningapplication.ui.bean.DLBean;
import com.sapient.statestreetscreeningapplication.ui.bean.Email;
import com.sapient.statestreetscreeningapplication.ui.bean.EmailDlBean;
import com.sapient.statestreetscreeningapplication.ui.bean.IntervieweeBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingResourceBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonScreeningDetailsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonStaffingBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.PropertyUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.OnboardingCheckListConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonNewConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonScreeningDetailsConvertor;
import com.sapient.statestreetscreeningapplication.utils.converter.TeamConverter;
import com.sapient.statestreetscreeningapplication.utils.email.ExcelFileCreator;

// TODO: Auto-generated Javadoc
/**
 * The Class SendEmailServiceImpl.
 */
@Service
public class SendEmailServiceImpl implements SendEmailService {
	
	@Autowired
	private PersonLookupService personLookupService;
	
	public PersonLookupService getPersonLookupService() {
		return personLookupService;
	}

	public void setPersonLookupService(PersonLookupService personLookupService) {
		this.personLookupService = personLookupService;
	}
	
	@Autowired
	private TeamService teamService;

	public TeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	/** The person service. */
	@Autowired
	private PersonService personService;

	/** The onboarding resource service. */
	@Autowired
	private OnboardingResourceService onboardingResourceService;
	
	/** The project service. */
	@Autowired
	private ProjectService projectService;
	
	/** The interviewee service. */
	@Autowired
	private IntervieweeService intervieweeService;
	
	/** The email service. */
	@Autowired
	private EmailService emailService;
	
	/** The person look service. */
	@Autowired
	private PersonLookupService personLookService;
	
	/** The project logservice. */
	@Autowired
	private ProjectLogService projectLogservice;
	
	
	@Autowired
	PersonLookupServiceImplementation personLookupServiceImplementation;

	/** The email. */
	private Email email;
	
	/** The reader. */
	private BufferedReader reader;
	
	/** The str. */
	private String str;
	
	/** The dl list1. */
	private String dlList1;
	
	public PersonLookupServiceImplementation getPersonLookupServiceImplementation() {
		return personLookupServiceImplementation;
	}

	public void setPersonLookupServiceImplementation(
			PersonLookupServiceImplementation personLookupServiceImplementation) {
		this.personLookupServiceImplementation = personLookupServiceImplementation;
	}

	/**
	 * Gets the dl list1.
	 *
	 * @return the dl list1
	 */
	public String getDlList1() {
		return dlList1;
	}
	
	/**
	 * Sets the dl list1.
	 *
	 * @param dlList1 the new dl list1
	 */
	public void setDlList1(String dlList1) {
		this.dlList1 = dlList1;
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.SendEmailService#fileRead(java.io.File)
	 */
	@Override
	public String fileRead(File file)
	{
		CustomLoggerUtils.INSTANCE.log.info("inside fileRead(File file) method of SendEmailServiceImpl class");
		String stp="";
	try {
		reader = new BufferedReader(new FileReader(file));
        
        int i=0;
		String str = reader.readLine();
		 str = reader.readLine();
		while (str!=null)
		{
			 stp=stp.concat(str);
          str = reader.readLine();
         }}
	catch (Exception e) {
  			throw new RuntimeException(e);
  		} finally {
  			if (reader != null) {
  				try {
  					reader.close();
  				} catch (Exception e) {
  					// ignore
  				}
  			}}
	return stp;
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.SendEmailService#fileSubjectRead(java.io.File)
	 */
	@Override
	public String fileSubjectRead(File file)
	{	
		CustomLoggerUtils.INSTANCE.log.info("inside fileSubjectRead(File file) method of SendEmailServiceImpl class");
		String stp="";
		try {
			reader = new BufferedReader(new FileReader(file));
		    int i=0;
			String str = reader.readLine();
			stp=stp.concat(str);
		}catch (Exception e) {
				throw new RuntimeException(e);
		}finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		return stp;
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.SendEmailService#sendEmail(com.sapient.statestreetscreeningapplication.ui.bean.IntervieweeBean, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean sendEmail(PersonScreeningDetailsBean bean, String subject, String body) {
		CustomLoggerUtils.INSTANCE.log.info("inside sendEmail(PersonScreeningDetailsBean bean, String subject, String body) method of SendEmailServiceImpl class");
		initializeMail();
		//singleInterviewee(bean);
		email.setSubject(subject);
		email.setText(body);
		List<PersonScreeningDetailsBean> newInterviewee = new ArrayList<PersonScreeningDetailsBean>();
		newInterviewee.add(bean);
		createAttachment(newInterviewee);
		return emailService.sendEmail(email);

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.SendEmailService#sendEmail(java.util.List, java.lang.String)
	 */
	@Override
	public void sendEmail(List<PersonScreeningDetailsBean> intervieweeList, String receiverEmailId) {
		CustomLoggerUtils.INSTANCE.log.info("inside  sendEmail(List<PersonScreeningDetailsBean> intervieweeList, String receiverEmailId) method of SendEmailServiceImpl class");
		email = new Email();
		email.setFrom(PropertyUtils.readProperty("screening.application.email"));
		email.setTo(receiverEmailId);
		String intervieweeNames="";
		if(intervieweeList.size()==1){
			if(intervieweeList.get(0).getPersonBean().getIsTemp()){
				intervieweeNames=intervieweeList.get(0).getPersonBean().getTempPersonBean().getTempPersonName();
			}else{
				intervieweeNames=intervieweeList.get(0).getPersonBean().getPersonName();
			}
			
		}else{
			if(intervieweeList.get(0).getPersonBean().getIsTemp()){
				intervieweeNames = intervieweeList.get(0).getPersonBean().getTempPersonBean().getTempPersonName();
			}else{
				intervieweeNames = intervieweeList.get(0).getPersonBean().getPersonName();
			}
			
			for(int i=1; i<intervieweeList.size(); i++){
				if(intervieweeList.get(i).getPersonBean().getIsTemp()){
					intervieweeNames = intervieweeNames + ", "+intervieweeList.get(i).getPersonBean().getTempPersonBean().getTempPersonName();
				}else{
					intervieweeNames = intervieweeNames + ", "+intervieweeList.get(i).getPersonBean().getPersonName();
				}
			}
		}
		
		String subject= "Screening Application| Updated interviewee details-" + intervieweeNames ;
		email.setSubject(subject);
		email.setText("PFA the updated interviewee details");
		createAttachment(intervieweeList);
		emailService.sendEmail(email);

	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.SendEmailService#sendEmailToStaffing(java.util.List, com.sapient.statestreetscreeningapplication.ui.bean.PersonBean)
	 */
	@Override
	public void sendEmailToStaffing(List<PersonScreeningDetailsBean> intervieweeList,PersonBean interviewer) {
		
		CustomLoggerUtils.INSTANCE.log.info("inside  sendEmailToStaffing(List<PersonScreeningDetailsBean> intervieweeList,PersonBean interviewer) method of SendEmailServiceImpl class");
		email = new Email();
		email.setFrom(PropertyUtils.readProperty("screening.application.email"));
		email.setTo(PropertyUtils.readProperty("staffing.email").split(","));
		
		
		File file = new File("D:/PeopleOnboarding3.0/MailTemplates/SendEmailToStaffing");	//This is for deplaoyed
		//File file = new File("D:/ScreeningApplication/MailTemplates/SendEmailToStaffing");
		String subject=fileSubjectRead(file);
		String[] subj=subject.split("---");
		email.setSubject(subj[0]);
		String stp=fileRead(file);
		/*"<html><head><title></title></head><body>"
				+ "<p>Hi "
				+ ",</br>"
				+ "</br>The following candidates have been selected for filling up the open needs at StateStreet:"
				+ "<br/><br/><table frame='box'><tr style='background-color: #535353 ;color: white;border-width: 0 0 1px 1px;border-spacing: 0;border-collapse: collapse;border-style: solid;'><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>Name</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>Oracle Id</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>Project Name</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>PID</td></tr>";*/
		
		String body=stp;
		String intervieweeDetails="<br/><table frame='box'><tr style='background-color: #535353 ;color: white;border-width: 0 0 1px 1px;border-spacing: 0;border-collapse: collapse;border-style: solid;'><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>Name</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>Oracle Id</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>Project Name</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>PID</td></tr>";
		for(PersonScreeningDetailsBean interviewee:intervieweeList){
			if(interviewee.getPersonBean().getIsTemp()!=null && interviewee.getPersonBean().getIsTemp()){
				intervieweeDetails=intervieweeDetails+"<tr><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+interviewee.getPersonBean().getTempPersonBean().getTempPersonName()+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+"NA"+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+interviewee.getProjectBean().getProjectName()+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+interviewee.getProjectBean().getClientProjectId()+"</td></tr>";
			}else
			intervieweeDetails=intervieweeDetails+"<tr><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+personLookService.getPersonByOracleId(interviewee.getPersonBean().getPersonOracleId()).getName()+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+interviewee.getPersonBean().getPersonId()+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+interviewee.getProjectBean().getProjectName()+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+interviewee.getProjectBean().getClientProjectId()+"</td></tr>";
		}
		/*body=body+"</table> Kindly confirm if the above resources are available to be onboarded onto StateStreet." 
				+ "<br/><br/>Warm regards,"
				+ "<br/>"+interviewer.getName()+"<br/>" + "</p></body></html>";*/
		intervieweeDetails+="</table>";
		
		String str=body.replace("$interviewerName$", interviewer.getName()).replace("$intervieweeDetails$", intervieweeDetails);
		email.setText(str);
		createAttachment(intervieweeList);
		emailService.sendEmail(email);
	}
	
	/**
	 * Initialize mail.
	 */
	private void initializeMail() {
		email = new Email();
		List<String> recList = new ArrayList<String>();
		recList = personService.getEmailsForImmediateUpdates();
		String[] emailList = new String[recList.size()];
		email.setFrom(PropertyUtils.readProperty("screening.application.email"));
		email.setTo(recList.toArray(emailList));
		//email.setCc(PropertyUtils.readProperty("lt.staffing.email").split(","));
	}
	
	
	private void singleInterviewee(PersonScreeningDetailsBean psdBean){
		email = new Email();
		List<String> recList = new ArrayList<String>();
		recList.add(personLookupService.getPersonByOracleId(psdBean.getScreenerBean().getPersonOracleId()).getEmail());
		String[] emailList = new String[recList.size()];
		email.setFrom(PropertyUtils.readProperty("screening.application.email"));
		email.setTo(recList.toArray(emailList));
		email.setCc(PropertyUtils.readProperty("lt.staffing.email").split(","));
	}

	/**
	 * Creates the attachment.
	 *
	 * @param interviewees the interviewees
	 */
	private void createAttachment(List<PersonScreeningDetailsBean> interviewees) {
		byte[] data = ExcelFileCreator.intervieweeDetailsWithScoresFileCreator(interviewees);
		String intervieweeNames="";
		if(interviewees.size()==1){
			if(interviewees.get(0).getPersonBean().getIsTemp()){
				intervieweeNames=interviewees.get(0).getPersonBean().getTempPersonBean().getTempPersonName();
			}else{
				intervieweeNames=interviewees.get(0).getPersonBean().getPersonName();
			}
			
		}else{
			/*for(PersonScreeningDetailsBean interviewee: interviewees){
				if(interviewee.getPersonBean().getIsTemp()){
					intervieweeNames = intervieweeNames + ", "+interviewee.getPersonBean().getTempPersonBean().getTempPersonName();
				}else{
					intervieweeNames = intervieweeNames + ", "+interviewee.getPersonBean().getPersonName();
				}
			}*/
			if(interviewees.get(0).getPersonBean().getIsTemp()){
				intervieweeNames = interviewees.get(0).getPersonBean().getTempPersonBean().getTempPersonName();
			}else{
				intervieweeNames = interviewees.get(0).getPersonBean().getPersonName();
			}
			
			for(int i=1; i<interviewees.size(); i++){
				if(interviewees.get(i).getPersonBean().getIsTemp()){
					intervieweeNames = intervieweeNames + ", "+interviewees.get(i).getPersonBean().getTempPersonBean().getTempPersonName();
				}else{
					intervieweeNames = intervieweeNames + ", "+interviewees.get(i).getPersonBean().getPersonName();
				}
			}
		}
		
		String filename= "Screening Details Feedback-" + intervieweeNames +".xls";
		Attachment attachment = new Attachment(data, filename,
				"application/vnd.ms-excel", false);
		email.addAttachment(attachment);
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public Email getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(Email email) {
		this.email = email;
	}

	

	/**
	 * Gets the person service.
	 *
	 * @return the person service
	 */
	public PersonService getPersonService() {
		return personService;
	}
	
	/**
	 * Sets the person service.
	 *
	 * @param personService the new person service
	 */
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	/**
	 * Gets the email service.
	 *
	 * @return the email service
	 */
	public EmailService getEmailService() {
		return emailService;
	}

	/**
	 * Sets the email service.
	 *
	 * @param emailService the new email service
	 */
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
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

	/**
	 * Gets the project service.
	 *
	 * @return the project service
	 */
	public ProjectService getProjectService() {
		return projectService;
	}

	/**
	 * Sets the project service.
	 *
	 * @param projectService the new project service
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	
	
	/**
	 * Gets the person look service.
	 *
	 * @return the person look service
	 */
	public PersonLookupService getPersonLookService() {
		return personLookService;
	}

	/**
	 * Sets the person look service.
	 *
	 * @param personLookService the new person look service
	 */
	public void setPersonLookService(PersonLookupService personLookService) {
		this.personLookService = personLookService;
	}

	/**
	 * Gets the interviewee service.
	 *
	 * @return the interviewee service
	 */
	public IntervieweeService getIntervieweeService() {
		return intervieweeService;
	}

	/**
	 * Sets the interviewee service.
	 *
	 * @param intervieweeService the new interviewee service
	 */
	public void setIntervieweeService(IntervieweeService intervieweeService) {
		this.intervieweeService = intervieweeService;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.SendEmailService#sendEmailToCandidate(com.sapient.statestreetscreeningapplication.ui.bean.PersonBean, int, com.sapient.statestreetscreeningapplication.ui.bean.ProjectBean, com.sapient.statestreetscreeningapplication.ui.bean.PersonBean, com.sapient.statestreetscreeningapplication.ui.bean.OnboardingResourceBean)
	 */
	@Override
	public boolean sendEmailToCandidate(int selectedBGC,int initiator,PersonStaffingBean personStaffingBean, OnboardingCheckListBean onboardingCheckListBean,PersonNewBean currentUser) {
		CustomLoggerUtils.INSTANCE.log.info("inside sendEmailToCandidate(int selectedBGC,PersonStaffingBean personStaffingBean, OnboardingCheckListBean onboardingCheckListBean) method of SendEmailServiceImpl class");
		/*PersonStaffing  ps=onboardingResourceService.fetchPersonStaffingByPersonID(personStaffingBean.getPerson().getPersonId());*/
		PersonNewBean candidate=PersonNewConverter.personNewEntityToBean(onboardingResourceService.fetchPersonByPersonID(personStaffingBean.getPerson().getPersonId()));
		ProjectNewBean project = personStaffingBean.getProject();	
		PersonBean pb = null;
		
		
		String leadName=(personLookService.getPersonByOracleId(currentUser.getPersonOracleId())).getName();
		if(candidate.getIsTemp()!=null && !candidate.getIsTemp()){
			pb=personLookService.getPersonByOracleId(candidate.getPersonOracleId());
		}
		String attachments = null;
		email=new Email();
		email.setFrom("StateStreetBackgroundChecks@sapient.com");
		//email.setTo(PropertyUtils.readProperty("operations.email").split(","));
		if(candidate.getIsTemp()!=null && candidate.getIsTemp()){
			email.setTo(candidate.getTempPersonBean().getTempPersonEmail());
		}else{
			email.setTo(pb.getEmail());
		}
		
		//email.setTo("spaul8@sapient.com");
		//email.setTo(candidate.getEmail());
		email.setCc(PropertyUtils.readProperty("operations.email"),(personLookService.getPersonByOracleId(currentUser.getPersonOracleId())).getEmail());
		
		//email.setTo("adanewalia@sapient.com");
		String dlList="";
		int i=0;		
		for(EmailDlBean a:personStaffingBean.getEmailDls())
		{ 
			dlList=dlList+a.getEmail();
			dlList=dlList+" "+","+" ";
		}		
		if(dlList!="")
		{StringBuilder b = new StringBuilder(dlList);
         		b.replace(dlList.lastIndexOf(","),dlList.lastIndexOf(",")+1,""); 
        		 dlList1=b.toString();
       	}
		 if(dlList1==null)
		 {
			 dlList1=" ";
		 }
		 if(personStaffingBean.getComments()==null){
			 personStaffingBean.setComments(" ");
		 }
		if(selectedBGC==1){//use SendEmailToCandidate1.txt
			File file = new File("D:/PeopleOnboarding3.0/MailTemplates/SendEmailToCandidateIndiaBGC");	//This is for deplaoyed
			//File file = new File("D:/ScreeningApplication/MailTemplates/SendEmailToCandidateIndiaBGC");
			String stp=fileRead(file);
			
			 //CustomLoggerUtils.INSTANCE.log.info("The data in send email service "+" "+person.toString()+" "+project.toString()+" "+interviwee.toString()+" "+personStaffingBean.toString());
			 //String st=stp.replace("$application.url$",PropertyUtils.readProperty("application.url")).replace("$personName$",person.getName()).replace("$personOracleId$",Integer.toString(person.getOracleId())).replace("$projectProjectName$", project.getProjectName()).replace("$projectIQNNumber$",Integer.toString(project.getIQNNumber())).replace("$HasPreviouslyAssignedAtSS$", (personStaffingBean.isHasPreviouslyAssignedAtSS()==true?"Yes":"No")).replace("$onboardingSsStartDate$",personStaffingBean.getStringSSStartDate()).replace("$LocationName$",interviwee.getLocation().getLocationName()).replace("$isOnsiteAtSS$",(personStaffingBean.isOnsiteAtSS()==true?"Yes":"No")).replace("$HasSapientLaptop$", (personStaffingBean.isHasSapientLaptop()==true?"Yes":"No")).replace("$DlName$", dlList1).replace("$onboardingComments$", personStaffingBean.getComments());
			String st;
			if(candidate.getIsTemp()!=null && candidate.getIsTemp()){
				 st=stp.replace("$candidateName$",candidate.getTempPersonBean().getTempPersonName()).replace("$projectIQNName$", project.getClientProjectName()).replace("$projectIQNNumber$", Integer.toString(project.getClientProjectId()))
						 .replace("$leadName$", leadName)	
						 .replace("$operations.poc.email$", PropertyUtils.readProperty("operations.poc.email")).replace("$personOracleId$","NA")
			        		.replace("$projectProjectName$", project.getProjectName()).replace("$projectIQNNumber$",Integer.toString(project.getClientProjectId()))
			        		.replace("$HasPreviouslyAssignedAtSS$", (onboardingCheckListBean.isHasPreviouslyWorkedForClient()==true?"Yes":"No")).
			        		replace("$onboardingSsStartDate$",personStaffingBean.getStartDate()).
			        		replace("$LocationName$",personStaffingBean.getLocation().getCity()).
			        		replace("$isOnsiteAtSS$",("No")).
			        		replace("$HasSapientLaptop$", (onboardingCheckListBean.isHasSapientLaptop()==true?"Yes":"No")).
			        		replace("$DlName$", dlList1).replace("$onboardingComments$", personStaffingBean.getComments());
			}else{
	       st=stp.replace("$candidateName$",pb.getName()).replace("$projectIQNName$", project.getClientProjectName()).replace("$projectIQNNumber$", Integer.toString(project.getClientProjectId()))
	    		   .replace("$leadName$", leadName)
	    		   .replace("$operations.poc.email$", PropertyUtils.readProperty("operations.poc.email")).replace("$personOracleId$",Integer.toString(candidate.getPersonOracleId()))
	        		.replace("$projectProjectName$", project.getProjectName()).replace("$projectIQNNumber$",Integer.toString(project.getClientProjectId()))
	        		.replace("$HasPreviouslyAssignedAtSS$", (onboardingCheckListBean.isHasPreviouslyWorkedForClient()==true?"Yes":"No")).
	        		replace("$onboardingSsStartDate$",personStaffingBean.getStartDate()).
	        		replace("$LocationName$",personStaffingBean.getLocation().getCity()).
	        		replace("$isOnsiteAtSS$",("No")).
	        		replace("$HasSapientLaptop$", (onboardingCheckListBean.isHasSapientLaptop()==true?"Yes":"No")).
	        		replace("$DlName$", dlList1).replace("$onboardingComments$", personStaffingBean.getComments());
			}
	        		/*replace("$leadName$", screener.getName());*/
			String subject=fileSubjectRead(file);
			String[] subj=subject.split("---");
			String sub;
			if(candidate.getIsTemp()!=null && candidate.getIsTemp()){
			sub=subj[0].replace("$candidateName$", candidate.getTempPersonBean().getTempPersonName());
			}else{
				sub=subj[0].replace("$candidateName$", pb.getName());
			}
			email.setSubject(sub);
		/*email.setSubject("State Street Background Check | India BGC "+candidate.getName());*/
		email.setText(st);
		attachments=subj[1];
		CustomLoggerUtils.INSTANCE.log.info("following are the attachemts "+attachments);
		
		}
		
		if(selectedBGC==2){//use SendEmailToCandidate2.txt
			File file2 = new File("D:/PeopleOnboarding3.0/MailTemplates/SendEmailToCandidateNYOnsiteBGC");	//This one is for deplaoyed
		//	File file2 = new File("D:/ScreeningApplication/MailTemplates/SendEmailToCandidateNYOnsiteBGC");
			String stp2=fileRead(file2);
			String st2;
			if(candidate.getIsTemp()!=null && candidate.getIsTemp()){
	               st2=stp2.replace("$candidateName$",candidate.getTempPersonBean().getTempPersonName()).replace("$projectIQNName$", project.getClientProjectName())
	            		  .replace("$projectIQNNumber$", Integer.toString(project.getClientProjectId())).
	            		  replace("$leadName$", leadName).
	            		  replace("$operations.poc.email$", PropertyUtils.readProperty("operations.poc.email"))
	            		  .replace("$personOracleId$","NA").replace("$projectProjectName$", project.getProjectName())
	            		  .replace("$projectIQNNumber$",Integer.toString(project.getClientProjectId()))
	            		  .replace("$HasPreviouslyAssignedAtSS$", (onboardingCheckListBean.isHasPreviouslyWorkedForClient()==true?"Yes":"No"))
	            		  .replace("$onboardingSsStartDate$",personStaffingBean.getStartDate())
	            		  .replace("$LocationName$",personStaffingBean.getLocation().getCity())
	            		  .replace("$isOnsiteAtSS$",("Yes"))
	            		  .replace("$HasSapientLaptop$", (onboardingCheckListBean.isHasSapientLaptop()==true?"Yes":"No"))
	            		  .replace("$DlName$", dlList1).replace("$onboardingComments$", personStaffingBean.getComments());
	            		/*  .replace("$leadName$", screener.getName());*/
			}else{
				st2=stp2.replace("$candidateName$",pb.getName()).replace("$projectIQNName$", project.getClientProjectName())
	            		  .replace("$projectIQNNumber$", Integer.toString(project.getClientProjectId())).
	            		  replace("$operations.poc.email$", PropertyUtils.readProperty("operations.poc.email"))
	            		  .replace("$leadName$", leadName)
	            		  .replace("$personOracleId$",Integer.toString(candidate.getPersonOracleId())).replace("$projectProjectName$", project.getProjectName())
	            		  .replace("$projectIQNNumber$",Integer.toString(project.getClientProjectId()))
	            		  .replace("$HasPreviouslyAssignedAtSS$", (onboardingCheckListBean.isHasPreviouslyWorkedForClient()==true?"Yes":"No"))
	            		  .replace("$onboardingSsStartDate$",personStaffingBean.getStartDate())
	            		  .replace("$LocationName$",personStaffingBean.getLocation().getCity())
	            		  .replace("$isOnsiteAtSS$",("Yes"))
	            		  .replace("$HasSapientLaptop$", (onboardingCheckListBean.isHasSapientLaptop()==true?"Yes":"No"))
	            		  .replace("$DlName$", dlList1).replace("$onboardingComments$", personStaffingBean.getComments());
			}
	              String subject=fileSubjectRead(file2);
	              String[] subj=subject.split("---");
	              String sub;
	              if(candidate.getIsTemp()!=null && candidate.getIsTemp()){
	  			 sub=subj[0].replace("$candidateName$", candidate.getTempPersonBean().getTempPersonName());
	              }else{
	            	   sub=subj[0].replace("$candidateName$", pb.getName());
	              }
	  			email.setSubject(sub);
	              /*email.setSubject("State Street Background Check | NY Onsite BGC "+candidate.getName());*/
			email.setText(st2);
			attachments=subj[1];
			}
		
		if(selectedBGC==3){//use SendEmailToCandidate3.txt
			File file3 = new File("D:/PeopleOnboarding3.0/MailTemplates/SendEmailToCandidateUSOnsiteBGC");	//This one is for deployed
			//File file3 = new File("D:/ScreeningApplication/MailTemplates/SendEmailToCandidateUSOnsiteBGC");
			String stp3=fileRead(file3);
			String st3;
			if(candidate.getIsTemp()!=null && candidate.getIsTemp()){
	              st3=stp3.replace("$candidateName$",candidate.getTempPersonBean().getTempPersonName()).replace("$projectIQNName$", project.getClientProjectName())
	            		  .replace("$projectIQNNumber$", Integer.toString(project.getClientProjectId()))
	            		  .replace("$leadName$", leadName)
	            		  .replace("$operations.poc.email$", PropertyUtils.readProperty("operations.poc.email"))
	            		  .replace("$personOracleId$","NA")
	            		  .replace("$projectProjectName$", project.getProjectName()).replace("$projectIQNNumber$",Integer.toString(project.getClientProjectId()))
	            		  .replace("$HasPreviouslyAssignedAtSS$", (onboardingCheckListBean.isHasPreviouslyWorkedForClient()==true?"Yes":"No"))
	            		  .replace("$onboardingSsStartDate$",personStaffingBean.getStartDate())
	            		  .replace("$LocationName$",personStaffingBean.getLocation().getCity())
	            		  .replace("$isOnsiteAtSS$",("Yes")).replace("$HasSapientLaptop$", (onboardingCheckListBean.isHasSapientLaptop()==true?"Yes":"No"))
	            		  .replace("$DlName$", dlList1).replace("$onboardingComments$", personStaffingBean.getComments())/*.replace("$leadName$", screener.getName());*/;
			}else{
				st3=stp3.replace("$candidateName$",pb.getName()).replace("$projectIQNName$", project.getClientProjectName())
          		  .replace("$projectIQNNumber$", Integer.toString(project.getClientProjectId()))
          		  .replace("$leadName$", leadName)
          		  .replace("$operations.poc.email$", PropertyUtils.readProperty("operations.poc.email"))
          		  .replace("$personOracleId$",Integer.toString(candidate.getPersonOracleId()))
          		  .replace("$projectProjectName$", project.getProjectName()).replace("$projectIQNNumber$",Integer.toString(project.getClientProjectId()))
          		  .replace("$HasPreviouslyAssignedAtSS$", (onboardingCheckListBean.isHasPreviouslyWorkedForClient()==true?"Yes":"No"))
          		  .replace("$onboardingSsStartDate$",personStaffingBean.getStartDate())
          		  .replace("$LocationName$",personStaffingBean.getLocation().getCity())
          		  .replace("$isOnsiteAtSS$",("Yes")).replace("$HasSapientLaptop$", (onboardingCheckListBean.isHasSapientLaptop()==true?"Yes":"No"))
          		  .replace("$DlName$", dlList1).replace("$onboardingComments$", personStaffingBean.getComments())/*.replace("$leadName$", screener.getName());*/;
		
				
			}
	              String subject=fileSubjectRead(file3);
	              String[] subj=subject.split("---");
	              String sub;
	              if(candidate.getIsTemp()!=null && candidate.getIsTemp()){
	  			 sub=subj[0].replace("$candidateName$", candidate.getTempPersonBean().getTempPersonName());
	              }else{
	            	  sub=subj[0].replace("$candidateName$", pb.getName());
	              }
	  			email.setSubject(sub);
	              /*email.setSubject("State Street Background Check | US Onsite BGC "+candidate.getName());*/
			email.setText(st3);
			attachments=subj[1];
			}
		
		if(selectedBGC==5){//use SendEmailToCandidate3.txt
			File file3 = new File("D:/PeopleOnboarding3.0/MailTemplates/SendEmailToCandidateNYOnsiteBGC");	//This one is for deployed
			//File file3 = new File("D:/ScreeningApplication/MailTemplates/SendEmailToCandidateUSOnsiteBGC");
			String stp3=fileRead(file3);
			String st3;
			if(candidate.getIsTemp()!=null && candidate.getIsTemp()){
	              st3=stp3.replace("$candidateName$",candidate.getTempPersonBean().getTempPersonName()).replace("$projectIQNName$", project.getClientProjectName())
	            		  .replace("$projectIQNNumber$", Integer.toString(project.getClientProjectId()))
	            		  .replace("$leadName$", leadName)
	            		  .replace("$operations.poc.email$", PropertyUtils.readProperty("operations.poc.email"))
	            		  .replace("$personOracleId$","NA")
	            		  .replace("$projectProjectName$", project.getProjectName()).replace("$projectIQNNumber$",Integer.toString(project.getClientProjectId()))
	            		  .replace("$HasPreviouslyAssignedAtSS$", (onboardingCheckListBean.isHasPreviouslyWorkedForClient()==true?"Yes":"No"))
	            		  .replace("$onboardingSsStartDate$",personStaffingBean.getStartDate())
	            		  .replace("$LocationName$",personStaffingBean.getLocation().getCity())
	            		  .replace("$isOnsiteAtSS$",("Yes")).replace("$HasSapientLaptop$", (onboardingCheckListBean.isHasSapientLaptop()==true?"Yes":"No"))
	            		  .replace("$DlName$", dlList1).replace("$onboardingComments$", personStaffingBean.getComments())/*.replace("$leadName$", screener.getName());*/;
			}else{
				st3=stp3.replace("$candidateName$",pb.getName()).replace("$projectIQNName$", project.getClientProjectName())
          		  .replace("$projectIQNNumber$", Integer.toString(project.getClientProjectId()))
          		  .replace("$leadName$", leadName)
          		  .replace("$operations.poc.email$", PropertyUtils.readProperty("operations.poc.email"))
          		  .replace("$personOracleId$",Integer.toString(candidate.getPersonOracleId()))
          		  .replace("$projectProjectName$", project.getProjectName()).replace("$projectIQNNumber$",Integer.toString(project.getClientProjectId()))
          		  .replace("$HasPreviouslyAssignedAtSS$", (onboardingCheckListBean.isHasPreviouslyWorkedForClient()==true?"Yes":"No"))
          		  .replace("$onboardingSsStartDate$",personStaffingBean.getStartDate())
          		  .replace("$LocationName$",personStaffingBean.getLocation().getCity())
          		  .replace("$isOnsiteAtSS$",("Yes")).replace("$HasSapientLaptop$", (onboardingCheckListBean.isHasSapientLaptop()==true?"Yes":"No"))
          		  .replace("$DlName$", dlList1).replace("$onboardingComments$", personStaffingBean.getComments())/*.replace("$leadName$", screener.getName());*/;
		
				
			}
	              String subject=fileSubjectRead(file3);
	              String[] subj=subject.split("---");
	              String sub;
	              if(candidate.getIsTemp()!=null && candidate.getIsTemp()){
	  			 sub=subj[0].replace("$candidateName$", candidate.getTempPersonBean().getTempPersonName());
	              }else{
	            	  sub=subj[0].replace("$candidateName$", pb.getName());
	              }
	  			email.setSubject(sub);
	              /*email.setSubject("State Street Background Check | US Onsite BGC "+candidate.getName());*/
			email.setText(st3);
			attachments=subj[1];
			}
		if(selectedBGC==4){//use SendEmailToCandidate3.txt
			File file3 = new File("D:/PeopleOnboarding3.0/MailTemplates/SendEmailToCandidateCostaRicaBGC");	//This one is for deployed
			//File file3 = new File("D:/ScreeningApplication/MailTemplates/SendEmailToCandidateUSOnsiteBGC");
			String stp3=fileRead(file3);
			String st3;
			if(candidate.getIsTemp()!=null && candidate.getIsTemp()){
	              st3=stp3.replace("$candidateName$",candidate.getTempPersonBean().getTempPersonName()).replace("$projectIQNName$", project.getClientProjectName())
	            		  .replace("$projectIQNNumber$", Integer.toString(project.getClientProjectId()))
	            		  .replace("$leadName$", leadName)
	            		  .replace("$operations.poc.email$", PropertyUtils.readProperty("operations.poc.email"))
	            		  .replace("$personOracleId$","NA")
	            		  .replace("$projectProjectName$", project.getProjectName()).replace("$projectIQNNumber$",Integer.toString(project.getClientProjectId()))
	            		  .replace("$HasPreviouslyAssignedAtSS$", (onboardingCheckListBean.isHasPreviouslyWorkedForClient()==true?"Yes":"No"))
	            		  .replace("$onboardingSsStartDate$",personStaffingBean.getStartDate())
	            		  .replace("$LocationName$",personStaffingBean.getLocation().getCity())
	            		  .replace("$isOnsiteAtSS$",("Yes")).replace("$HasSapientLaptop$", (onboardingCheckListBean.isHasSapientLaptop()==true?"Yes":"No"))
	            		  .replace("$DlName$", dlList1).replace("$onboardingComments$", personStaffingBean.getComments())/*.replace("$leadName$", screener.getName());*/;
			}else{
				st3=stp3.replace("$candidateName$",pb.getName()).replace("$projectIQNName$", project.getClientProjectName())
          		  .replace("$projectIQNNumber$", Integer.toString(project.getClientProjectId()))
          		  .replace("$leadName$", leadName)
          		  .replace("$operations.poc.email$", PropertyUtils.readProperty("operations.poc.email"))
          		  .replace("$personOracleId$",Integer.toString(candidate.getPersonOracleId()))
          		  .replace("$projectProjectName$", project.getProjectName()).replace("$projectIQNNumber$",Integer.toString(project.getClientProjectId()))
          		  .replace("$HasPreviouslyAssignedAtSS$", (onboardingCheckListBean.isHasPreviouslyWorkedForClient()==true?"Yes":"No"))
          		  .replace("$onboardingSsStartDate$",personStaffingBean.getStartDate())
          		  .replace("$LocationName$",personStaffingBean.getLocation().getCity())
          		  .replace("$isOnsiteAtSS$",("Yes")).replace("$HasSapientLaptop$", (onboardingCheckListBean.isHasSapientLaptop()==true?"Yes":"No"))
          		  .replace("$DlName$", dlList1).replace("$onboardingComments$", personStaffingBean.getComments())/*.replace("$leadName$", screener.getName());*/;
		
				
			}
	              String subject=fileSubjectRead(file3);
	              String[] subj=subject.split("---");
	              String sub;
	              if(candidate.getIsTemp()!=null && candidate.getIsTemp()){
	  			 sub=subj[0].replace("$candidateName$", candidate.getTempPersonBean().getTempPersonName());
	              }else{
	            	  sub=subj[0].replace("$candidateName$", pb.getName());
	              }
	  			email.setSubject(sub);
	              /*email.setSubject("State Street Background Check | US Onsite BGC "+candidate.getName());*/
			email.setText(st3);
			attachments=subj[1];
			}
		return emailService.sendEmailWithDefaultAttachments(email,selectedBGC,attachments);
		
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.SendEmailService#sendEmailToOperations(com.sapient.statestreetscreeningapplication.ui.bean.PersonBean, com.sapient.statestreetscreeningapplication.ui.bean.OnboardingResourceBean)
	 */
	@Override
	public void sendEmailToOperations(PersonBean person,OnboardingResourceBean onboarding) {
		CustomLoggerUtils.INSTANCE.log.info("inside sendEmailToOperations(PersonBean person,OnboardingResourceBean onboarding) method of SendEmailServiceImpl class");

		
		IntervieweeBean interviwee=intervieweeService.fetchIntervieweeOnOracleId(person.getOracleId());
		//OnboardingResourceBean onboarding=onboardingResourceService.fetchResourceOfInterviewee(interviwee.getIntervieweeId());
		ProjectNewBean project=projectService.getProjectById(interviwee.getProjectBean().getId());
		//ProjectBean project =projectService.getProjectById(interviwee.getProjectBean().getId());
		email=new Email();
		email.setFrom(PropertyUtils.readProperty("screening.application.email"));
		email.setTo(PropertyUtils.readProperty("operations.email").split(","));
	/*	email.setSubject("New StateStreet onboarding request");*/
		File file = new File("D:/PeopleOnboarding3.0/MailTemplates/SendEmailToOperations");	//This one is deplaoyed
		//File file = new File("D:/ScreeningApplication/MailTemplates/SendEmailToOperations");
		
		String stp=fileRead(file);
		String dlList="";
		int i=0;		
		for(DLBean a:onboarding.getDls())
		{ 
			dlList=dlList+a.getDlName();
		dlList=dlList+" "+","+" ";
		}		
		if(dlList!="")
		{StringBuilder b = new StringBuilder(dlList);
       
        		b.replace(dlList.lastIndexOf(","),dlList.lastIndexOf(",")+1,""); 
        		 dlList1=b.toString();
        		}
		 if(dlList1==null)
		 {
			 dlList1=" ";
		 }
		 if(onboarding.getComments()==null){
			 onboarding.setComments(" ");
		 }
		 //CustomLoggerUtils.INSTANCE.log.info("The data in send email service "+" "+person.toString()+" "+project.toString()+" "+interviwee.toString()+" "+onboarding.toString());
		 String st=stp.replace("$application.url$",PropertyUtils.readProperty("application.url")).replace("$personName$",person.getName()).replace("$personOracleId$",Integer.toString(person.getOracleId())).replace("$projectProjectName$", project.getProjectName()).replace("$projectIQNNumber$",project.getClientTimeTrackingId()).replace("$HasPreviouslyAssignedAtSS$", (onboarding.isHasPreviouslyAssignedAtSS()==true?"Yes":"No")).replace("$onboardingSsStartDate$",onboarding.getStringSSStartDate()).replace("$LocationName$",interviwee.getLocation().getLocationName()).replace("$isOnsiteAtSS$",(onboarding.isOnsiteAtSS()==true?"Yes":"No")).replace("$HasSapientLaptop$", (onboarding.isHasSapientLaptop()==true?"Yes":"No")).replace("$DlName$", dlList1).replace("$onboardingComments$", onboarding.getComments());
              String subject=fileSubjectRead(file);
              String[] subj=subject.split("---");
      		email.setSubject(subj[0]);
             email.setText(st);
              /*email.setText("<html><head><title></title></head><body>"
				+ "<p>Hi "
				+ ",</br>"
				+ "</br>A new request to onboard a person has been received by Screening Application. Please log into "
				+ PropertyUtils.readProperty("application.url")+" and view the onboarding resource details."
				+ "<br/><br/><table frame='box'><tr style='background-color: #535353 ;color: white;border-width: 0 0 1px 1px;border-spacing: 0;border-collapse: collapse;border-style: solid;'><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>Name</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>Oracle Id</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>Project Name</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>IQN</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>Has person previously been assigned at StateStreet?</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>StateStreet start date</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>Work Location</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>Onsite at StateStreet?</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>Sapient laptop?</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>DL to be added in</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>Comments</td></tr>"
				
				+ "<tr><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>" + person.getName()+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+person.getOracleId()+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+project.getProjectName()+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+project.getIQNNumber()+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+(onboarding.isHasPreviouslyAssignedAtSS()==true?"Yes":"No")+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+onboarding.getSsStartDate()+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+interviwee.getLocation().getLocationName()+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+(onboarding.isOnsiteAtSS()==true?"Yes":"No")+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+(onboarding.isHasSapientLaptop()==true?"Yes":"No")+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+onboarding.getDlBean().getDlName()+"</td><td style='margin: 0;padding: 4px;border-width: 1px 1px 0 0;border-style: solid;'>"+onboarding.getComments()+"</td></tr></table>"
				
				+ "<br/><br/>To access the application through VM, please follow the steps:"
				+ "<br/>1.	Log in to https://webapps.sapient.com/ using native client."
				+ "<br/>2.	Open any one of the applications such as ‘people portal’ given in the application box on the left."
				+ "<br/>3.	Replace the URL in the newly opened browser to "
				+ PropertyUtils.readProperty("application.url")
				+ " and click enter." + "<br/><br/>Warm regards,"
				+ "<br/>Screening Application Team<br/>" + "</p></body></html>");*/
		emailService.sendEmail(email);
		
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.SendEmailService#sendResubmissionNotificationMail(com.sapient.statestreetscreeningapplication.ui.bean.PersonBean, java.util.List, java.lang.String)
	 */
	@Override
	public boolean sendResubmissionNotificationMail(PersonBean personBean,List<String> details, String comments) {
		CustomLoggerUtils.INSTANCE.log.info("inside  sendResubmissionNotificationMail(PersonBean personBean,List<String> details, String comments) method of SendEmailServiceImpl class");
		String  summary="Your Onboarding documents could not be processed due to the following reasons:";
		for(String t:details)
		{
			summary=summary.concat("</br>"+t);
		}
		email=new Email();
		email.setFrom(PropertyUtils.readProperty("screening.application.email"));
		email.setTo(personBean.getEmail());
		/*email.setTo(PropertyUtils.readProperty("candidate.email"));*/
		//email.setTo(PropertyUtils.readProperty("operations.email").split(","));
	//	email.setSubject("Resubmission of documents for onboarding");
		/*email.setText("<html><head><title></title></head><body>"
				+ "<p>Hi "+personBean.getName()
				+ ",</br>"
				+ "</br>"+summary 
				+ "</br>"
				+ "</br>"+"Details: "+comments
				+ "<br/><br/>Please resubmit the onboarding documents with the following details mentioned above:"
				+  "<br/><br/>Warm regards,"
				+ "<br/>Screening Application Team<br/>" + "</p></body></html>");*/
		File file = new File("D:/PeopleOnboarding3.0/MailTemplates/SendResubmissionNotificationMail");	//This one is deplaoyed
		//File file = new File("D:/ScreeningApplication/MailTemplates/SendResubmissionNotificationMail");
		String stp=fileRead(file);
		String s=stp.replace("$personName$",personBean.getName()).replace("$summary$", summary).replace("$comments$", comments);
		String subject=fileSubjectRead(file);
		String[] subj=subject.split("---");
		email.setSubject(subj[0]);
		email.setText(s);
		emailService.sendEmail(email);
		return false;
	}

	

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.SendEmailService#sendPSIDEmailToCandidate(int)
	 */
	@Override
	public void sendPSIDEmailToCandidate(int onboardingID,String personClientId, String personName,PersonNewBean currentUser) {
		CustomLoggerUtils.INSTANCE.log.info("inside sendPSIDEmailToCandidate(int onboardingID) method of SendEmailServiceImpl class");
		String attachment=null;
		
		
		//this statement is changed by below statements
    	//OnboardingCheckListBean onboardingCheckListBean = onboardingResourceService.fetchNewOnboardingCheckList(onboardingID);
		OnboardingCheckList onboardingCheckList = onboardingResourceService.getOnboardingCheckListByStaffingId(new Long(onboardingID));
    
    	OnboardingCheckListBean onboardingCheckListBean=OnboardingCheckListConverter.convertOnboardingCheckListEntityToOnboardingCheckListBean(onboardingCheckList);
		
		PersonBean lead=personLookService.getPersonByNTId(onboardingCheckListBean.getPersonStaffingBean().getPerson().getSupervisorNtId());
		PersonNewBean person=onboardingCheckListBean.getPerson();/*personLookService.getPersonByOracleId(onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonId());*/
		PersonBean pb = null;
		if(person.getIsTemp()!=null && !person.getIsTemp()){
			pb=personLookService.getPersonByOracleId(person.getPersonOracleId());
		}
		email=new Email();
		email.setFrom(PropertyUtils.readProperty("screening.application.email"));
		/*if (onboardingResourceBean.getLeadOracleId()!=0) {
			//to add leads
			email.setTo(lead.getEmail(),
					PropertyUtils.readProperty("operations.email").split(","));
			email.setTo(PropertyUtils.readProperty("operations.email").split(","));
		}
		else
			email.setTo(PropertyUtils.readProperty("operations.email").split(","));*/
		if(person.getIsTemp()!=null &&  person.getIsTemp()){
			email.setTo(person.getTempPersonBean().getTempPersonEmail());
		}else{

		email.setTo(pb.getEmail());
		}
		
		if (onboardingCheckListBean.getPersonStaffingBean().getPerson().getSupervisorNtId()!=null) {
			String ntId=onboardingCheckListBean.getPersonStaffingBean().getPerson().getSupervisorNtId();
			if(onboardingCheckListBean.getInitiator()!=null){
				
				String cc[]={(personLookupService.getPersonByNTId(ntId)).getEmail(),currentUser.getPersonEmailId(),PropertyUtils.readProperty("operations.email"),personLookupService.getPersonByOracleId(onboardingCheckListBean.getInitiator().getPersonOracleId()).getEmail()};
				email.setCc(cc);
			}else{
				String cc[]={(personLookupService.getPersonByNTId(ntId)).getEmail(),currentUser.getPersonEmailId(),PropertyUtils.readProperty("operations.email")};
				email.setCc(cc);
			}
			
		}
		else
		{
			if(onboardingCheckListBean.getInitiator()!=null){
				String cc[]={currentUser.getPersonEmailId(),PropertyUtils.readProperty("operations.email"),personLookupService.getPersonByOracleId(onboardingCheckListBean.getInitiator().getPersonOracleId()).getEmail()};
				email.setCc(cc);
			}else{
				String cc[]={currentUser.getPersonEmailId(),PropertyUtils.readProperty("operations.email")};
				email.setCc(cc);
			}
			
		}
		
		
		List<String> emailStringList = new ArrayList<String>();
		
		List<TeamLeadEntity> teamleadEntityList = teamService.getTeamLeadByTeam(TeamConverter.convertTeamBeanToEntity(onboardingCheckListBean.getPersonStaffingBean().getProject().getTeamBean()));
		
		for(TeamLeadEntity teamleadEntity : teamleadEntityList){
			emailStringList.add((personLookupService.getPersonByOracleId(teamleadEntity.getLead().getPersonOracleId())).getEmail());
		}
		
		List<String> ccStringList = Arrays.asList(email.getCc());
		for(String ccString :ccStringList){
			emailStringList.add(ccString);
		}

		String[] emailList = new String[emailStringList.size()];
		
		email.setCc(emailStringList.toArray(emailList));
		
		
	    File file = new File("D:/PeopleOnboarding3.0/MailTemplates/SendPSIDEmailToCandidateIndia");//This one is deplaoyed
		//File file = new File("D:/ScreeningApplication/MailTemplates/SendPSIDEmailToCandidateIndia");
		File file2 = new File("D:/PeopleOnboarding3.0/MailTemplates/SendPSIDEmailToCandidateNewYork");//This one is deplaoyed
		//File file2 = new File("D:/ScreeningApplication/MailTemplates/SendPSIDEmailToCandidateNewYork");
		File file3 = new File("D:/PeopleOnboarding3.0/MailTemplates/SendPSIDEmailToCandidateBoston");//This one is deplaoyed
		//File file3 = new File("D:/ScreeningApplication/MailTemplates/SendPSIDEmailToCandidateBoston");
		File file4 = new File("D:/PeopleOnboarding3.0/MailTemplates/SendPSIDEmailToCandidateCostaRica");
		//email.setSubject("PSID #");
		
		
		String location=onboardingCheckListBean.getPersonStaffingBean().getPerson().getLocation().getCity();
		int selectedBGC=0;
		if(location.equalsIgnoreCase("Noida")||location.equalsIgnoreCase("Bangalore")||location.equalsIgnoreCase("Gurgaon"))
			{selectedBGC=1;
			String subject=fileSubjectRead(file);
			String[] subj=subject.split("---");
			email.setSubject("Name " +personName+","+subj[0]+" "+personClientId);}
		else if(location.equalsIgnoreCase("New York"))
			{selectedBGC=2;
			String subject=fileSubjectRead(file2);
			String[] subj=subject.split("---");
			email.setSubject("Name " +personName+","+subj[0]+" "+personClientId);}
			
		else if(location.equalsIgnoreCase("Boston"))
			{selectedBGC=3;
			String subject=fileSubjectRead(file3);
			String[] subj=subject.split("---");
			
			attachment=subj[1];
			email.setSubject("Name " +personName+","+subj[0]+" "+personClientId);
			}
		else if(location.equalsIgnoreCase("San Jose"))
		{selectedBGC=4;
		String subject=fileSubjectRead(file4);
		String[] subj=subject.split("---");
		
		attachment=subj[1];
		email.setSubject("Name " +personName+","+subj[0]+" "+personClientId);
		}
		String content = setPSIDMailContent(onboardingCheckListBean,selectedBGC);
		
		email.setText(content);
		
		if(selectedBGC==1||selectedBGC==2||selectedBGC==4)
			emailService.sendEmail(email);
		else if(selectedBGC==3)
			emailService.sendPSIDEmailWithBuildingAccessAttachment(email,attachment);
	}
	
	

	/**
	 * Sets the psid mail content.
	 *
	 * @param onboardingResourceBean the onboarding resource bean
	 * @param selectedBGC the selected bgc
	 * @return the string
	 */
	private String setPSIDMailContent(OnboardingCheckListBean onboardingCheckListBean, int selectedBGC) {
		String s=new String();
		if(selectedBGC==1){
			/*s=		"<html><head><title></title></head><body>"
					+ "<p>Hi "+personLookService.getPersonByOracleId(onboardingResourceBean.getInterviewee().getIntervieweeOracleID()).getName()+"," +
					"<br/><br/>Your PSID # is "+onboardingResourceBean.getPSID() + 
					"<br/><br/>Thanks," +
					"<br/>Statestreet Ops Team";*/
		    File file = new File("D:/PeopleOnboarding3.0/MailTemplates/SendPSIDEmailToCandidateIndia");//This one is for deplaoyed
			//File file = new File("D:/ScreeningApplication/MailTemplates/SendPSIDEmailToCandidateIndia");
			String stp=fileRead(file);
			if(onboardingCheckListBean.getPersonStaffingBean().getPerson().getIsTemp()!=null && onboardingCheckListBean.getPersonStaffingBean().getPerson().getIsTemp()){
	        s=stp.replace("$personName$", onboardingCheckListBean.getPersonStaffingBean().getPerson().getTempPersonBean().getTempPersonName()).
	        		replace("$onboardingResourceBeanPSID$", (onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonClientId()));
			}else{
				 s=stp.replace("$personName$", personLookService.getPersonByOracleId(onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonOracleId()).getName()).
			        		replace("$onboardingResourceBeanPSID$", (onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonClientId()));
					
			}
		}
		
		else if(selectedBGC==2){
			/*s=		"<html><head><title></title></head><body>"
					+ "<p>Hi "+personLookService.getPersonByOracleId(onboardingResourceBean.getInterviewee().getIntervieweeOracleID()).getName()+"," 
					+ "<br/><br/>Your PSID # is "+onboardingResourceBean.getPSID() + ". You need this to schedule fingerprinting to get SS office access. "
					+ "<br/><br/>The following steps to follow for fingerprinting :-"
					+ "<br/><br/>1.	Send email to Patrick Jennings PJennings2@StateStreet.com and request the “ID badge FORM.” With your PSID #. "
					+ "<br/><br/>2.	You can go for fingerprinting after Patrick’s confirmation mail and details."
					+ "<br/><br/>Contact "+PropertyUtils.readProperty("operations.poc.email")+" in case of any queries."
					+ "<br/><br/>Thanks," +
					"<br/>Statestreet Ops Team";*/
			File file2 = new File("D:/PeopleOnboarding3.0/MailTemplates/SendPSIDEmailToCandidateNewYork");	//This one is for deplaoyed
			//File file2 = new File("D:/ScreeningApplication/MailTemplates/SendPSIDEmailToCandidateNewYork");
			String stp=fileRead(file2);
			if(onboardingCheckListBean.getPersonStaffingBean().getPerson().getIsTemp()!=null && onboardingCheckListBean.getPersonStaffingBean().getPerson().getIsTemp()){
			 s=stp.replace("$personName$", onboardingCheckListBean.getPersonStaffingBean().getPerson().getTempPersonBean().getTempPersonName()).
					 replace("$onboardingResourceBean.getPSID$", (onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonClientId())).
					 replace("$operations.poc.email$",PropertyUtils.readProperty("operations.poc.email") );
			}else{
				s=stp.replace("$personName$", personLookService.getPersonByOracleId(onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonOracleId()).getName()).
						 replace("$onboardingResourceBean.getPSID$", (onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonClientId())).
						 replace("$operations.poc.email$",PropertyUtils.readProperty("operations.poc.email") );
				
			}
		}
		
		else if(selectedBGC==3){
			/*s=		"<html><head><title></title></head><body>"
					+ "<p>Hi "+personLookService.getPersonByOracleId(onboardingResourceBean.getInterviewee().getIntervieweeOracleID()).getName()+"," 
					+ "<br/><br/>Your PSID # is "+onboardingResourceBean.getPSID() + ". You need this to schedule fingerprinting to get SS office access. "
					+ "<br/><br/>The following steps to follow for fingerprinting :-"
					+ "<br/>	•	Employees in Boston will have to be fingerprinted to get their security badge. To schedule the finger-printing, please contact Vincent Brigance VBrigance@StateStreet.com   or call at  P +617 664 3110 | O +877-664-8080. You will need to bring a valid photo ID with you. You will need to provide your People Soft ID (PSID #)."
					+ "<br/>	•	Your project lead will get you your security badge from the SST manager."
					+ "<br/>	•	The instructions and locations of the fingerprinting and badge centers are attached in pdf file Boston Building Access. You need to go to one of the listed locations to get your badge and fingerprinting done."
					+ "<br/><br/>Contact "+PropertyUtils.readProperty("operations.poc.email")+" in case of any queries."
					+ "<br/><br/>Thanks," +
					"<br/>Statestreet Ops Team";*/
			File file3 = new File("D:/PeopleOnboarding3.0/MailTemplates/SendPSIDEmailToCandidateBoston");//This one is for deplaoyed	
			//File file3 = new File("D:/ScreeningApplication/MailTemplates/SendPSIDEmailToCandidateBoston");
			String stp=fileRead(file3);
			if(onboardingCheckListBean.getPersonStaffingBean().getPerson().getIsTemp()!=null && onboardingCheckListBean.getPersonStaffingBean().getPerson().getIsTemp()){
			 s=stp.replace("$personName$", onboardingCheckListBean.getPersonStaffingBean().getPerson().getTempPersonBean().getTempPersonName()).
					 replace("$onboardingResourceBeanPSID$", (onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonClientId())).
					 replace("$operations.poc.email$",PropertyUtils.readProperty("operations.poc.email") );
			}else{
				 s=stp.replace("$personName$", personLookService.getPersonByOracleId(onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonOracleId()).getName()).
						 replace("$onboardingResourceBeanPSID$", (onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonClientId())).
						 replace("$operations.poc.email$",PropertyUtils.readProperty("operations.poc.email") );
				
			}
		}
		
		else if(selectedBGC==4){
			/*s=		"<html><head><title></title></head><body>"
					+ "<p>Hi "+personLookService.getPersonByOracleId(onboardingResourceBean.getInterviewee().getIntervieweeOracleID()).getName()+"," 
					+ "<br/><br/>Your PSID # is "+onboardingResourceBean.getPSID() + ". You need this to schedule fingerprinting to get SS office access. "
					+ "<br/><br/>The following steps to follow for fingerprinting :-"
					+ "<br/>	•	Employees in Boston will have to be fingerprinted to get their security badge. To schedule the finger-printing, please contact Vincent Brigance VBrigance@StateStreet.com   or call at  P +617 664 3110 | O +877-664-8080. You will need to bring a valid photo ID with you. You will need to provide your People Soft ID (PSID #)."
					+ "<br/>	•	Your project lead will get you your security badge from the SST manager."
					+ "<br/>	•	The instructions and locations of the fingerprinting and badge centers are attached in pdf file Boston Building Access. You need to go to one of the listed locations to get your badge and fingerprinting done."
					+ "<br/><br/>Contact "+PropertyUtils.readProperty("operations.poc.email")+" in case of any queries."
					+ "<br/><br/>Thanks," +
					"<br/>Statestreet Ops Team";*/
			File file4 = new File("D:/PeopleOnboarding3.0/MailTemplates/SendPSIDEmailToCandidateCostaRica");//This one is for deplaoyed	
			//File file3 = new File("D:/ScreeningApplication/MailTemplates/SendPSIDEmailToCandidateBoston");
			String stp=fileRead(file4);
			if(onboardingCheckListBean.getPersonStaffingBean().getPerson().getIsTemp()!=null && onboardingCheckListBean.getPersonStaffingBean().getPerson().getIsTemp()){
			 s=stp.replace("$personName$", onboardingCheckListBean.getPersonStaffingBean().getPerson().getTempPersonBean().getTempPersonName()).
					 replace("$onboardingResourceBeanPSID$", (onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonClientId())).
					 replace("$operations.poc.email$",PropertyUtils.readProperty("operations.poc.email") );
			}else{
				 s=stp.replace("$personName$", personLookService.getPersonByOracleId(onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonOracleId()).getName()).
						 replace("$onboardingResourceBeanPSID$", (onboardingCheckListBean.getPersonStaffingBean().getPerson().getPersonClientId())).
						 replace("$operations.poc.email$",PropertyUtils.readProperty("operations.poc.email") );
				
			}
		}
		
		return s;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.SendEmailService#sendOnboardingTerminationEmail(int, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendOnboardingTerminationEmail(long leadOnboardID, String projectEndDate,String offboardingComments,PersonNewBean currentUser) {
		CustomLoggerUtils.INSTANCE.log.info("inside sendOnboardingTerminationEmail(int leadOnboardID, String projectEndDate,String offboardingComments) method of SendEmailServiceImpl class");
		//Use SendOnboardingTerminationEmail.txt
		OnboardingCheckListBean onboardingList=onboardingResourceService.fetchNewOnboardingCheckList(leadOnboardID);
		/*PersonStaffingBean personStaffingBean=onboardingResourceService.fetchpersonStaffingByPersonId(onboardingList.getPersonStaffingBean().getPerson().getPersonId());*/
		PersonStaffingBean personStaffingBean=onboardingList.getPersonStaffingBean();
		/*PersonScreeningDetails personScreeningDetailsBean=intervieweeService.fetchPersonScreeningDetailsOnPersonId
				(onboardingList.getPersonStaffingBean().getPerson().getPersonId());*/
		
		/*PersonScreeningDetails personScreeningDetailsBean=PersonScreeningDetailsConvertor.PersonScreeningDetailsBeanToEntity(bean, categoryService)onboardingList.getPersonScreeningDetails();*/
		
		
		DateFormat simpleformatter = new SimpleDateFormat("MM/dd/yyyy");
		Date staffingEndDateNew;
		try {
			staffingEndDateNew = simpleformatter.parse(projectEndDate);
			Date staffingStartDateOld = simpleformatter.parse(personStaffingBean.getStartDate());
			Date projectStartDateCurrent = simpleformatter.parse(personStaffingBean.getProject().getProjectStartDate());
			Date projectEndDateCurrent = simpleformatter.parse(personStaffingBean.getProject().getProjectEndDate());
			Date nextStaffingStartDate = null;
			Calendar calendar = Calendar.getInstance();
			
			if((personStaffingBean.getImmediateNextStaffing()!=null)&&(personStaffingBean.getImmediateNextStaffing().getStartDate()!=null)){
				nextStaffingStartDate = simpleformatter.parse(personStaffingBean.getImmediateNextStaffing().getStartDate());
				calendar.setTime(nextStaffingStartDate);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				nextStaffingStartDate = calendar.getTime();
			}
			
			calendar.setTime(staffingEndDateNew);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			staffingEndDateNew = calendar.getTime();
			
			calendar.setTime(staffingStartDateOld);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			staffingStartDateOld = calendar.getTime();
			
			calendar.setTime(projectStartDateCurrent);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			projectStartDateCurrent = calendar.getTime();
			
			calendar.setTime(projectEndDateCurrent);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			projectEndDateCurrent = calendar.getTime();
			
			boolean valid = true;
			
			if(staffingEndDateNew.before(staffingStartDateOld)){
				valid = false;
			}
			if(staffingEndDateNew.before(projectStartDateCurrent)){
				valid = false;
			}
			if(projectEndDateCurrent.before(staffingEndDateNew)){
				valid = false;
			}
			if((nextStaffingStartDate!=null)&&(!staffingEndDateNew.before(nextStaffingStartDate))){
				valid = false;
			}
			
			if(valid){
				personStaffingBean.setEndDate(projectEndDate);	
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		personStaffingBean.setOffboarded(true);
		onboardingResourceService.editPersonStaffing(personStaffingBean);
		/*OnboardingCheckListLeadsBean checkListLeadsBean=onboardingResourceService.fetchOnBoardingChecklistLead(leadOnboardID);
		OnboardingCheckListOpsBean opsBean=onboardingResourceService.fetchOnBoardingChecklist(checkListLeadsBean.getOnboardingCheckListOps().getOnboardingCheckListId());
		IntervieweeBean intervieweeBean=null;intervieweeService.fetchInterviewee(opsBean.getInterviewee().getIntervieweeId());
		OnboardingResourceBean resourceBean=onboardingResourceService.fetchResourceOfInterviewee(intervieweeBean.getIntervieweeId());*/
		//resourceBean.setIQNEndDate(projectEndDate);
		/*resourceBean.setStringIQNEndDate(projectEndDate);
		Date ssStartDate=null;
		Date iqnEndDate=null;
		DateFormat formatter ;  
		   formatter = new SimpleDateFormat("MM/dd/yyyy");
		   try {
			   if(resourceBean.getStringSSStartDate()!=null)
				   ssStartDate=formatter.parse(resourceBean.getStringSSStartDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   try {
			   if(resourceBean.getStringIQNEndDate()!=null)
				   iqnEndDate=formatter.parse(resourceBean.getStringIQNEndDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		   long diff = iqnEndDate.getTime() - ssStartDate.getTime();
			double diffInYears=(double)TimeUnit.MILLISECONDS.toDays(diff)/365.0;
			double finalValue = Math.round( diffInYears * 10.0 ) / 10.0;
			resourceBean.setAccountTenure(finalValue);
		boolean isSet=onboardingResourceService.updateOnboardingResourceInfo(resourceBean);
		
		projectLogservice.updateProjectLog(ssStartDate,iqnEndDate, intervieweeBean.getIntervieweeOracleID());*/
		/*CustomLoggerUtils.INSTANCE.log.info("the iqn end date has been set in onboarding resource bean"+isSet);*/
		PersonNewBean candidate=onboardingList.getPersonStaffingBean().getPerson();
		PersonBean lead=personLookService.getPersonByOracleId(currentUser.getPersonOracleId());
		File file = new File("D:/PeopleOnboarding3.0/MailTemplates/SendOnboardingTerminationEmail");	//This one is for deaplyed
		//File file = new File("D:/ScreeningApplication/MailTemplates/SendOnboardingTerminationEmail");
		String stp=fileRead(file);
		 String st;
		 PersonBean pb = null;
			if(candidate.getIsTemp()!=null && !candidate.getIsTemp()){
				pb=personLookService.getPersonByOracleId(candidate.getPersonOracleId());
			}
		if(candidate.getIsTemp()!=null && candidate.getIsTemp()){
               st=stp.replace("$candidateName$", candidate.getTempPersonBean().getTempPersonName()).replace("$candidateOracleId$","NA").replace("$intervieweeBean.getStatus().getResultName()$", onboardingList.getPersonScreeningDetails().getStatusBean().getResultName()).replace("$projectEndDate$", projectEndDate).replace("$offboardingComments$", offboardingComments).replace("$iqnProjectNumber$", Integer.toString(personStaffingBean.getProject().getClientProjectId())).replace("$leadName$",lead.getName());
		}else{
			st=stp.replace("$candidateName$", pb.getName()).replace("$candidateOracleId$",Integer.toString(pb.getOracleId())).replace("$intervieweeBean.getStatus().getResultName()$", onboardingList.getPersonScreeningDetails().getStatusBean().getResultName()).replace("$projectEndDate$", projectEndDate).replace("$offboardingComments$", offboardingComments).replace("$iqnProjectNumber$", Integer.toString(personStaffingBean.getProject().getClientProjectId())).replace("$leadName$",lead.getName());
		}
		email=new Email();
		email.setFrom(lead.getEmail());
		//to add leads
		/*email.setTo(lead.getEmail(),
				PropertyUtils.readProperty("operations.email").split(","));*/
		//email.setTo(PropertyUtils.readProperty("operations.email").split(","));
		email.setTo(PropertyUtils.readProperty("operations.email").split(","));
		String subject=fileSubjectRead(file);
		String[] subj=subject.split("---");
		email.setSubject(subj[0]);
		//email.setSubject("Onboarding Termination");
		email.setText(st);
		emailService.sendEmail(email);
	}
}
