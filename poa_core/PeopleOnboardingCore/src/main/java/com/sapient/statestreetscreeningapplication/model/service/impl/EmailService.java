package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.dao.PersonDaoNew;
import com.sapient.statestreetscreeningapplication.model.entity.MonthlyProjectBudgetEntity;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.service.IntervieweeService;
import com.sapient.statestreetscreeningapplication.model.service.OnboardingResourceService;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.model.service.PersonService;
import com.sapient.statestreetscreeningapplication.ui.bean.Attachment;
import com.sapient.statestreetscreeningapplication.ui.bean.Email;
import com.sapient.statestreetscreeningapplication.ui.bean.IntervieweeBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListOpsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingResourceBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonScreeningDetailsBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonScreeningDetailsConvertor;
import com.sapient.statestreetscreeningapplication.utils.enums.Months;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailService.
 */
@Service
public class EmailService {

	/** The interviewee service. */
	@Autowired
	private IntervieweeService intervieweeService;

	/** The person service. */
	@Autowired
	private PersonService personService;

	/** The mail sender. */
	@Autowired
	private JavaMailSenderImpl mailSender;

	/** The onboarding service. */
	@Autowired
	private OnboardingResourceService onboardingService;

	/** The person lookup service. */
	@Autowired
	private PersonLookupService personLookupService;
	
	@Autowired
	private PersonDaoNew personDaoNew;

	public PersonDaoNew getPersonDaoNew() {
		return personDaoNew;
	}

	public void setPersonDaoNew(PersonDaoNew personDaoNew) {
		this.personDaoNew = personDaoNew;
	}

	/**
	 * Send email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	public boolean sendEmail(Email email) {
		CustomLoggerUtils.INSTANCE.log.info("inside sendEmail(Email email) method of EmailService class");
	
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		boolean hasAttachments = (email.getAttachments() != null && email
				.getAttachments().size() > 0);
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					hasAttachments);
			helper.setTo(email.getTo());
			if (email.getCc() != null)
				helper.setCc(email.getCc());
			helper.setFrom(email.getFrom());
			helper.setSubject(email.getSubject());
			helper.setText(email.getText(), true);
			CustomLoggerUtils.INSTANCE.log.info("email initialized");
			List<Attachment> attachments = email.getAttachments();
			if (attachments != null && attachments.size() > 0) {
				for (Attachment attachment : attachments) {
					String filename = attachment.getFilename();
					DataSource dataSource = new ByteArrayDataSource(
							attachment.getData(), attachment.getMimeType());
					if (attachment.isInline()) {
						helper.addInline(filename, dataSource);
					} else {
						helper.addAttachment(filename, dataSource);
					}
				}
			}
		} catch (MessagingException e1) {
			CustomLoggerUtils.INSTANCE.log.error("MessagingException");
			return false;
		}
		CustomLoggerUtils.INSTANCE.log.info("Attachments added");
		try {
			mailSender.send(mimeMessage);
			CustomLoggerUtils.INSTANCE.log.info("Email sent successfully");
			return true;
		} catch (MailException e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.error("Mail could not be sent");
			return false;
		}

	}

	/**
	 * Send psid email with building access attachment.
	 *
	 * @param email the email
	 * @param attachment the attachment
	 * @return true, if successful
	 */
	public boolean sendPSIDEmailWithBuildingAccessAttachment(Email email,String attachment) {
		CustomLoggerUtils.INSTANCE.log.info("inside sendPSIDEmailWithBuildingAccessAttachment(Email email,String attachment) method of EmailService class");
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		URI path;
		DataSource source;
		String attachmentToBeSent[] = new String[100];
		if (attachment != null) {
			attachmentToBeSent = attachment.split(",");
		}
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setTo(email.getTo());
			if (email.getCc() != null)
				helper.setCc(email.getCc());
			helper.setFrom(email.getFrom());
			helper.setSubject(email.getSubject());
			helper.setText(email.getText(), true);
			CustomLoggerUtils.INSTANCE.log.info("email initialized");

			// attaching common documents
			if (attachment != null) {
				// String directoryLocation =
				// "D:/wtpwebapps/ApplicationScreening/WEB-INF/classes/PSIDEmailWithBuildingAccessAttachment";
				// String
				// directoryLocation="D:/ScreeningApplication/Attachments/SendPSIDEmailToCandidateBoston";//This
				// one is for developers
				String directoryLocation = "D:/PeopleOnboarding3.0/Attachments/SendPSIDEmailToCandidateBoston";

				File directory = new File(directoryLocation);
				File[] files = directory.listFiles();
				for (File file : files) {
					for (String attachments1 : attachmentToBeSent) {
						if (attachments1.equals(file.getName())) {
							source = new FileDataSource(new File(
									file.getAbsolutePath()));
							helper.addAttachment(file.getName(), source);

							/*
							 * path = this.getClass().getClassLoader()
							 * .getResource("BostonBuildingAccess.pdf").toURI();
							 * source = new FileDataSource(new
							 * File(path.toString().substring(6)));
							 * helper.addAttachment("BostonBuildingAccess.pdf",
							 * source);
							 */}
					}
				}
			}
		} catch (MessagingException e1) {
			CustomLoggerUtils.INSTANCE.log.error("MessagingException");
			return false;
		} /*
		 * catch (URISyntaxException e2) { CustomLoggerUtils.INSTANCE.log
		 * .error("URISyntaxException while accessing BGC documents"); return
		 * false; }
		 */
		CustomLoggerUtils.INSTANCE.log.info("Attachment added");
		try {
			mailSender.send(mimeMessage);
			CustomLoggerUtils.INSTANCE.log.info("Email sent successfully");
			return true;
		} catch (MailException e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.error("Mail could not be sent");
			return false;
		}
	}

	/**
	 * Send email with default attachments.
	 *
	 * @param email the email
	 * @param selectedBGC the selected bgc
	 * @param attachments the attachments
	 * @return true, if successful
	 */
	public boolean sendEmailWithDefaultAttachments(Email email,int selectedBGC, String attachments) {
		CustomLoggerUtils.INSTANCE.log.info("inside sendEmailWithDefaultAttachments(Email email,int selectedBGC, String attachments) method of EmailService class");
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		URI path;
		DataSource source;
		String attachmentToBeSent[] = new String[100];
		
		if (attachments != null) {
			attachmentToBeSent = attachments.split(",");
		}
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setTo(email.getTo());
			helper.setFrom(email.getFrom());
			helper.setCc(email.getCc());
			helper.setSubject(email.getSubject());
			helper.setText(email.getText(), true);
			
			CustomLoggerUtils.INSTANCE.log.info("email initialized");

			// attaching common documents
			/*
			 * path = this.getClass().getClassLoader()
			 * .getResource("ConfidentialityAgreement.doc").toURI();
			 * 
			 * .println("ConfidentialityAgreement path"+path.toString
			 * ().substring(6));
			 */
			/*
			 * String filePath=
			 * "D:/MyWork/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ApplicationScreening/WEB-INF/classes/ConfidentialityAgreement.doc"
			 * ; //source = new FileDataSource(new
			 * File(path.toString().substring(6))); source = new
			 * FileDataSource(new File(filePath));
			 * 
			 * helper.addAttachment("ConfidentialityAgreement.doc", source);
			 * path = this.getClass().getClassLoader()
			 * .getResource("StateStAccountWelcomePacket.pdf").toURI(); source =
			 * new FileDataSource(new File(path.toString().substring(6)));
			 * helper.addAttachment("StateStAccountWelcomePacket.pdf", source);
			 */

			// attaching location specific documents and setting appropriate
			// message subject and message body
			if (selectedBGC == 1 && attachments != null) {
				// String directoryLocation =
				// "D:/wtpwebapps/ApplicationScreening/WEB-INF/classes/SendEmailToCandidateIndiaBGC";
				// String
				// directoryLocation="D:/ScreeningApplication/Attachments/SendEmailToCandidateIndiaBGC";//This
				// one is for developers
				String directoryLocation = "D:/PeopleOnboarding3.0/Attachments/SendEmailToCandidateIndiaBGC";
				File directory = new File(directoryLocation);
				File[] files = directory.listFiles();
				for (File file : files) {
					for (String attachments1 : attachmentToBeSent) {
						if (attachments1.equals(file.getName())) {
							source = new FileDataSource(new File(
									file.getAbsolutePath()));
							helper.addAttachment(file.getName(), source);
							
							/*
							 * path = this.getClass().getClassLoader()
							 * .getResource
							 * ("STATESTALLEGISEW.PRT-INDIA.XLS").toURI();
							 * source = new FileDataSource(new
							 * File(path.toString().substring( 6)));
							 * helper.addAttachment
							 * ("STATESTALLEGISEW.PRT–INDIA.XLS", source); path
							 * = this.getClass().getClassLoader()
							 * .getResource("SS.Final.India.Cont.07.08.pdf"
							 * ).toURI(); source = new FileDataSource(new
							 * File(path.toString().substring( 6)));
							 * helper.addAttachment
							 * ("SS.Final.India.Cont.07.08.pdf", source);
							 */
						}
					}
				}
			}
			if (selectedBGC == 2 && attachments != null) {
				// String directoryLocation =
				// "D:/wtpwebapps/ApplicationScreening/WEB-INF/classes/SendEmailToCandidateNYOnsiteBGC";
				// String
				// directoryLocation="D:/ScreeningApplication/Attachments/SendEmailToCandidateNYOnsiteBGC";//This
				// one is for developers
				String directoryLocation = "D:/PeopleOnboarding3.0/Attachments/SendEmailToCandidateNYOnsiteBGC";
				File directory = new File(directoryLocation);
				File[] files = directory.listFiles();
				for (File file : files) {
					for (String attachments1 : attachmentToBeSent) {
						if (attachments1.equals(file.getName())) {
							source = new FileDataSource(new File(
									file.getAbsolutePath()));
							helper.addAttachment(file.getName(), source);

							/*
							 * path = this.getClass().getClassLoader()
							 * .getResource
							 * ("STATESTALLEGISEW.PRT-US.XLS").toURI(); source =
							 * new FileDataSource(new
							 * File(path.toString().substring( 6)));
							 * helper.addAttachment
							 * ("STATESTALLEGISEW.PRT–US.XLS", source); path =
							 * this.getClass().getClassLoader()
							 * .getResource("SS.Final.US.NY.Cont.08.09.pdf"
							 * ).toURI(); source = new FileDataSource(new
							 * File(path.toString().substring( 6)));
							 * helper.addAttachment
							 * ("SS.Final.US.NY.Cont.08.09.pdf", source);
							 */
						}
					}
				}
			}
			if (selectedBGC == 3 && attachments != null) {
				// String directoryLocation =
				// "D:/wtpwebapps/ApplicationScreening/WEB-INF/classes/SendEmailToCandidateUSOnsiteBGC";
				// String
				// directoryLocation="D:/ScreeningApplication/Attachments/SendEmailToCandidateUSOnsiteBGC";//This
				// one is for developers
				String directoryLocation = "D:/PeopleOnboarding3.0/Attachments/SendEmailToCandidateUSOnsiteBGC";
				File directory = new File(directoryLocation);
				File[] files = directory.listFiles();
				for (File file : files) {
					for (String attachments1 : attachmentToBeSent) {
						if (attachments1.equals(file.getName())) {
							source = new FileDataSource(new File(
									file.getAbsolutePath()));
							helper.addAttachment(file.getName(), source);

							/*
							 * path = this.getClass().getClassLoader()
							 * .getResource
							 * ("STATESTALLEGISEW.PRT-US.XLS").toURI(); source =
							 * new FileDataSource(new
							 * File(path.toString().substring( 6)));
							 * helper.addAttachment
							 * ("STATESTALLEGISEW.PRT–US.XLS", source); path =
							 * this.getClass().getClassLoader()
							 * .getResource("SS.Final.US.Cont.08.09.pdf"
							 * ).toURI(); source = new FileDataSource(new
							 * File(path.toString().substring( 6)));
							 * helper.addAttachment
							 * ("SS.Final.US.Cont.08.09.pdf", source); path =
							 * this.getClass().getClassLoader()
							 * .getResource("BostonBuildingAccess.pdf").toURI();
							 * source = new FileDataSource(new
							 * File(path.toString().substring( 6)));
							 * helper.addAttachment("BostonBuildingAccess.pdf",
							 * source);
							 */
						}
					}
				}
			}if (selectedBGC == 4 && attachments != null) {
				// String directoryLocation =
				// "D:/wtpwebapps/ApplicationScreening/WEB-INF/classes/SendEmailToCandidateUSOnsiteBGC";
				// String
				// directoryLocation="D:/ScreeningApplication/Attachments/SendEmailToCandidateUSOnsiteBGC";//This
				// one is for developers
				String directoryLocation = "D:/PeopleOnboarding3.0/Attachments/SendEmailToCandidateCostaRicaBGC";
				File directory = new File(directoryLocation);
				File[] files = directory.listFiles();
				for (File file : files) {
					for (String attachments1 : attachmentToBeSent) {
						if (attachments1.equals(file.getName())) {
							source = new FileDataSource(new File(
									file.getAbsolutePath()));
							helper.addAttachment(file.getName(), source);

							/*
							 * path = this.getClass().getClassLoader()
							 * .getResource
							 * ("STATESTALLEGISEW.PRT-US.XLS").toURI(); source =
							 * new FileDataSource(new
							 * File(path.toString().substring( 6)));
							 * helper.addAttachment
							 * ("STATESTALLEGISEW.PRT–US.XLS", source); path =
							 * this.getClass().getClassLoader()
							 * .getResource("SS.Final.US.Cont.08.09.pdf"
							 * ).toURI(); source = new FileDataSource(new
							 * File(path.toString().substring( 6)));
							 * helper.addAttachment
							 * ("SS.Final.US.Cont.08.09.pdf", source); path =
							 * this.getClass().getClassLoader()
							 * .getResource("BostonBuildingAccess.pdf").toURI();
							 * source = new FileDataSource(new
							 * File(path.toString().substring( 6)));
							 * helper.addAttachment("BostonBuildingAccess.pdf",
							 * source);
							 */
						}
					}
				}
			}if (selectedBGC == 5 && attachments != null) {
				// String directoryLocation =
				// "D:/wtpwebapps/ApplicationScreening/WEB-INF/classes/SendEmailToCandidateUSOnsiteBGC";
				// String
				// directoryLocation="D:/ScreeningApplication/Attachments/SendEmailToCandidateUSOnsiteBGC";//This
				// one is for developers
				String directoryLocation = "D:/PeopleOnboarding3.0/Attachments/SendEmailToCandidateNYOnsiteBGC";
				File directory = new File(directoryLocation);
				File[] files = directory.listFiles();
				for (File file : files) {
					for (String attachments1 : attachmentToBeSent) {
						if (attachments1.equals(file.getName())) {
							source = new FileDataSource(new File(
									file.getAbsolutePath()));
							helper.addAttachment(file.getName(), source);

							/*
							 * path = this.getClass().getClassLoader()
							 * .getResource
							 * ("STATESTALLEGISEW.PRT-US.XLS").toURI(); source =
							 * new FileDataSource(new
							 * File(path.toString().substring( 6)));
							 * helper.addAttachment
							 * ("STATESTALLEGISEW.PRT–US.XLS", source); path =
							 * this.getClass().getClassLoader()
							 * .getResource("SS.Final.US.Cont.08.09.pdf"
							 * ).toURI(); source = new FileDataSource(new
							 * File(path.toString().substring( 6)));
							 * helper.addAttachment
							 * ("SS.Final.US.Cont.08.09.pdf", source); path =
							 * this.getClass().getClassLoader()
							 * .getResource("BostonBuildingAccess.pdf").toURI();
							 * source = new FileDataSource(new
							 * File(path.toString().substring( 6)));
							 * helper.addAttachment("BostonBuildingAccess.pdf",
							 * source);
							 */
						}
					}
				}
			}

		} catch (MessagingException e1) {
			CustomLoggerUtils.INSTANCE.log.error("MessagingException");
			return false;
		} /*
		 * catch (URISyntaxException e2) { CustomLoggerUtils.INSTANCE.log
		 * .error("URISyntaxException while accessing BGC documents"); return
		 * false; }
		 */
		CustomLoggerUtils.INSTANCE.log.info("Attachments added");
		try {
			mailSender.send(mimeMessage);
			CustomLoggerUtils.INSTANCE.log.info("Email sent successfully");
			return true;
		} catch (MailException e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.error("Mail could not be sent");
			return false;
		}
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
	 * Gets the mail sender.
	 *
	 * @return the mail sender
	 */
	public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	/**
	 * Sets the mail sender.
	 *
	 * @param mailSender the new mail sender
	 */
	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * Gets the onboarding service.
	 *
	 * @return the onboarding service
	 */
	public OnboardingResourceService getOnboardingService() {
		return onboardingService;
	}

	/**
	 * Sets the onboarding service.
	 *
	 * @param onboardingService the new onboarding service
	 */
	public void setOnboardingService(OnboardingResourceService onboardingService) {
		this.onboardingService = onboardingService;
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

	// Used by emailsenderJob
	/**
	 * Gets the emails for daily updates.
	 *
	 * @return the emails for daily updates
	 */
	public List<String> getEmailsForDailyUpdates() {
		return personService.getEmailsForDailyUpdate();
	}

	// Used by emailsenderJob
	/**
	 * Gets the interviewee for daily email.
	 *
	 * @return the interviewee for daily email
	 */
	public List<IntervieweeBean> getIntervieweeForDailyEmail() {
		return intervieweeService.getIntervieweeForDailyEmail();
	}

	/**
	 * Gets the onboarding candidates bgc inprogress.
	 *
	 * @return the onboarding candidates bgc inprogress
	 */
	public List<OnboardingCheckListOpsBean> getOnboardingCandidatesBGCInprogress() {
		CustomLoggerUtils.INSTANCE.log.info("inside getOnboardingCandidatesBGCInprogress() method of EmailService class");
		List<OnboardingCheckListOpsBean> list = new ArrayList<OnboardingCheckListOpsBean>();
		List<Long> intervieweeIdsList = new ArrayList<Long>();
		/*
		 * intervieweeList=intervieweeService.fetchIntervieweeByStatusId(14);
		 * for(IntervieweeBean bean:intervieweeList){
		 * onboardingService.fetchResourceOfInterviewee
		 * (bean.getIntervieweeId()); }
		 */

		intervieweeIdsList = intervieweeService
				.fetchIntervieweeIdsByStatusId(10);
		for (Long l : intervieweeIdsList) {
			list.add(onboardingService.fetchOpsChecklist(l));
		}

		/* list= onboardingService.getOnboardingCandidatesBGCInprogress(); */
		for (OnboardingCheckListOpsBean bean : list) {
			bean.getInterviewee().setIntervieweeName(
					personLookupService.getPersonByOracleId(
							bean.getInterviewee().getIntervieweeOracleID())
							.getName());
		}
		return list;
	}

	/**
	 * Gets the onboarding candidates bgc to be submitted.
	 *
	 * @return the onboarding candidates bgc to be submitted
	 */
	public List<OnboardingCheckListOpsBean> getOnboardingCandidatesBGCToBeSubmitted() {
		CustomLoggerUtils.INSTANCE.log.info("inside getOnboardingCandidatesBGCToBeSubmitted() method of EmailService class");
		List<OnboardingCheckListOpsBean> list = new ArrayList<OnboardingCheckListOpsBean>();
		List<OnboardingCheckListOpsBean> exited = new ArrayList<OnboardingCheckListOpsBean>();
		List<Long> intervieweeIdsList = new ArrayList<Long>();

		intervieweeIdsList = intervieweeService
				.fetchIntervieweeIdsByStatusId(7);
		intervieweeIdsList.addAll(intervieweeService
				.fetchIntervieweeIdsByStatusId(9));

		/*
		 * list=onboardingService.fetchOnboardingOpsChecklist(intervieweeIdsList)
		 * ;
		 */

		for (Long l : intervieweeIdsList) {
			list.add(onboardingService.fetchOpsChecklist(l));
		}

		for (OnboardingCheckListOpsBean bean : list) {
			if (bean.getInterviewee().getIntervieweeOracleID() != 0
					&& !bean.getInterviewee().isExited()) {
				bean.getInterviewee().setIntervieweeName(
						personLookupService.getPersonByOracleId(
								bean.getInterviewee().getIntervieweeOracleID())
								.getName());
			} else {
				exited.add(bean);
			}
		}
		list.removeAll(exited);
		return list;
	}

	/**
	 * Gets the candidates psid sent.
	 *
	 * @return the candidates psid sent
	 */
	public List<OnboardingResourceBean> getcandidatesPSIDSent() {
		CustomLoggerUtils.INSTANCE.log.info("inside getcandidatesPSIDSent() method of EmailService class");
		List<OnboardingResourceBean> list = new ArrayList<OnboardingResourceBean>();
		List<OnboardingResourceBean> exited = new ArrayList<OnboardingResourceBean>();
		list = onboardingService.getcandidatesPSIDSent();
		for (OnboardingResourceBean bean : list) {
			if (bean.getInterviewee().isExited()) {
				exited.add(bean);
			} else {
				bean.getInterviewee().setIntervieweeName(
						personLookupService.getPersonByOracleId(
								bean.getInterviewee().getIntervieweeOracleID())
								.getName());
			}
		}
		list.removeAll(exited);
		return list;
	}
	
@SuppressWarnings("deprecation")
public TreeMap<Person,Long> getTopScreeners(){
	    CustomLoggerUtils.INSTANCE.log.info("inside TreeMap<Person,Long> getTopScreeners() method of EmailService class");
		Date lDate = new Date();
		Date fDate = new Date();
		fDate.setMonth(lDate .getMonth()-1);
		return personDaoNew.getTopScreenersWithCount(fDate, lDate);
	}
	
	public List<PersonScreeningDetailsBean> getPSDForDailyMail(){
		CustomLoggerUtils.INSTANCE.log.info("inside getPSDForDailyMail() method of EmailService class");
		List<PersonScreeningDetails> psdListDailyMail = personDaoNew.getPSDListForAttachment();
		List<PersonScreeningDetailsBean> psdBeanList = new ArrayList<PersonScreeningDetailsBean>();
		if(psdListDailyMail!=null){
			for(PersonScreeningDetails psd: psdListDailyMail){
				PersonScreeningDetailsBean psdbean = PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(psd);
				psdBeanList.add(psdbean);
			}
			return psdBeanList;
			}else{
				return null;
			}
	}
	
	
public List<String> getEmailListForInterviewers(){
	CustomLoggerUtils.INSTANCE.log.info("inside getEmailListForInterviewers() method of EmailService class");
	return personService.getEmailListForInterviewers();
}

public List<PersonStaffing> getStaffingThisMonth(Date firstDate, Date lastDate){
	CustomLoggerUtils.INSTANCE.log.info("inside getStaffingThisMonth(Date firstDate, Date lastDate) method of EmailService class");
	List<PersonStaffing> p = personDaoNew.getStaffingThisMonth(firstDate, lastDate);
    return	p;
}


public List<MonthlyProjectBudgetEntity> getMonthlyProjectBudgetThisQuarter(Months month1, Months month2, Months month3, int year) {
	CustomLoggerUtils.INSTANCE.log.info("inside getMonthlyProjectBudgetThisQuarter(Months month1, Months month2, Months month3, int year) method of EmailService class");
	List<MonthlyProjectBudgetEntity> mpbList = personDaoNew.getMonthlyProjectBudgetThisQuarter(month1,month2,month3,year);
	return mpbList;
}

public List<MonthlyProjectBudgetEntity> getMonthlyProjectBudgetThisYear(int year) {
	CustomLoggerUtils.INSTANCE.log.info("inside getMonthlyProjectBudgetThisYear(int year) method of EmailService class");
	List<MonthlyProjectBudgetEntity> mpbList = personDaoNew.getMonthlyProjectBudgetThisYear(year);
	return mpbList;
}


public List<MonthlyProjectBudgetEntity> getMonthlyProjectBudgetThisMonth(Months month, int year) {
	CustomLoggerUtils.INSTANCE.log.info("inside getMonthlyProjectBudgetThisMonth(Months month, int year) method of EmailService class");
	List<MonthlyProjectBudgetEntity> allMPB = personDaoNew.getMonthlyProjectBudgetThisMonth(month, year);
	return allMPB;
}

public void newMonthlyProjectBudget(MonthlyProjectBudgetEntity mpbNew) {
	CustomLoggerUtils.INSTANCE.log.info("inside newMonthlyProjectBudget(MonthlyProjectBudgetEntity mpbNew) method of EmailService class");
	personDaoNew.newMonthlyProjectBudget(mpbNew);
}

}