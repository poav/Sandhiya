package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.dao.DLDao;
import com.sapient.statestreetscreeningapplication.model.dao.LocationDao;
import com.sapient.statestreetscreeningapplication.model.dao.ProjectDao;
import com.sapient.statestreetscreeningapplication.model.entity.EmailDl;
import com.sapient.statestreetscreeningapplication.model.entity.LocationNew;
import com.sapient.statestreetscreeningapplication.model.entity.ProjectNew;
import com.sapient.statestreetscreeningapplication.model.service.DLService;
import com.sapient.statestreetscreeningapplication.ui.bean.DLBean;
import com.sapient.statestreetscreeningapplication.ui.bean.EmailDlBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.EmailDlConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class DLServiceImpl.
 */
@Service
public class DLServiceImpl implements DLService{

	/** The dl dao. */
	@Autowired
	DLDao dlDao;
	
	/** The location dao. */
	@Autowired
	LocationDao locationDao;
	
	/** The project dao. */
	@Autowired
	ProjectDao projectDao;
	
	/** The location. */
	/*Location location;
	
	*//** The project. *//*
	Project project;*/
	
	
	/**
	 * Gets the dl dao.
	 *
	 * @return the dl dao
	 */
	public DLDao getDlDao() {
		return dlDao;
	}

	/**
	 * Sets the dl dao.
	 *
	 * @param dlDao the new dl dao
	 */
	public void setDlDao(DLDao dlDao) {
		this.dlDao = dlDao;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.DLService#getAllDLs()
	 */
	@Override
	public List<String> getAllDLs() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllDLs() method of DLServiceImpl class");
		return dlDao.getAllDLs();
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.DLService#getCompleteDLList()
	 */
	@Override
	public List<EmailDlBean> getCompleteDLList() {
		CustomLoggerUtils.INSTANCE.log.info("inside getCompleteDLList() method of DLServiceImpl class");
	
		List<EmailDlBean> dlBean = EmailDlConverter.convertEmailDlEntityListToEmailDlBeanList(dlDao.getCompleteDLList());
		return dlBean;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.DLService#getDLByProjectIdAndLocationId(long, long)
	 */
	@Override
	public List<String> getDLByProjectIdAndLocationId(long projectId,long locationId) {
		CustomLoggerUtils.INSTANCE.log.info("inside getDLByProjectIdAndLocationId(long projectId,long locationId) method of DLServiceImpl class");
		return dlDao.getDlByProjectIdAndLocationId(projectId,locationId);
		
	}
	
	@Override
	public List<String> getActiveDlByProjectIdAndLocationId(long projectId,long locationId){
		CustomLoggerUtils.INSTANCE.log.info("inside getActiveDlByProjectIdAndLocationId(long projectId,long locationId) method of DLServiceImpl class");
		return dlDao.getActiveDlByProjectIdAndLocationId(projectId, locationId);
	}
	
	
	

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.DLService#editDl(com.sapient.statestreetscreeningapplication.ui.bean.EmailDlBean, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean editDl(EmailDlBean dlBean,String locationName,String projectName, String dlName) {
		CustomLoggerUtils.INSTANCE.log.info("inside editDl(EmailDlBean dlBean,String locationName,String projectName, String dlName) method of DLServiceImpl class");
		LocationNew locationNew=locationDao.getNewLocationByName(locationName);
		ProjectNew projectNew=projectDao.getNewProjectByName(projectName);
		return dlDao.editDl(dlBean,locationNew,projectNew,dlName);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.DLService#saveDl(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void saveDl(String locationName, String projectName, String dlName, String teamName) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveDl(String locationName, String projectName, String dlName, String teamName) method of DLServiceImpl class");
		LocationNew locationNew=locationDao.getNewLocationByName(locationName);
		ProjectNew projectNew=projectDao.getNewProjectByName(projectName);
		dlDao.saveDl(locationNew,projectNew,dlName);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.DLService#saveDlBatchDetails(java.io.File)
	 */
	@Override
	public void saveDlBatchDetails(File dlBatch) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveDlBatchDetails(File dlBatch) method of DLServiceImpl class");
		dlDao.saveDlBatch(readFileAndCreateEntityList(dlBatch.getPath()));
	}

	/**
	 * Read file and create entity list.
	 *
	 * @param csvFile the csv file
	 * @return the list
	 */
	private List<EmailDl> readFileAndCreateEntityList(String csvFile) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<EmailDl> dlList = new ArrayList<EmailDl>();
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		String invalidEmail="";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] dl = line.split(cvsSplitBy);
				CustomLoggerUtils.INSTANCE.log.info("mst "+line);
				EmailDl newDL = new EmailDl();
				 Boolean b = dl[0].matches(EMAIL_REGEX);
				 if(b){
				newDL.setEmail(dl[0]);
				newDL.setLocation(locationDao.getNewLocationByName(dl[1]));
				newDL.setProject(projectDao.getNewProjectByName(dl[2]));
				dlList.add(newDL);}
				 else
				 {
					 invalidEmail="Some of the Dl names are inconsistent.Hence they are not saved";
					 CustomLoggerUtils.INSTANCE.log.info(invalidEmail);
					 
				 }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return dlList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.DLService#getDlByDlNameAndProjectIdAndLocationId(java.lang.String, long, long)
	 */
	@Override
	public EmailDlBean getDlByDlNameAndProjectIdAndLocationId(String dlName,long projectId, long locationId) {
		CustomLoggerUtils.INSTANCE.log.info("inside getDlByDlNameAndProjectIdAndLocationId(String dlName,long projectId, long locationId) method of DLServiceImpl class");
		EmailDl emailDl= new EmailDl();
		emailDl=dlDao.getDLByDlNameAndProjectIdAndLocationId(dlName,projectId,locationId);
		if(emailDl!=null)
		return  EmailDlConverter.convertEntityToBean(emailDl);
		
		return null;
	}

/* (non-Javadoc)
 * @see com.sapient.statestreetscreeningapplication.model.service.DLService#getAllEmailDlNames()
 */
@Override
	public List<String> getAllEmailDlNames() {
	CustomLoggerUtils.INSTANCE.log.info("inside getAllEmailDlNames() method of DLServiceImpl class");
		return dlDao.getAllDlNames();
	}

@Override
public int getEmailDlIsActiveByEmailDlId(Long emailDlId) {
	CustomLoggerUtils.INSTANCE.log.info("inside getEmailDlIsActiveByEmailDlId(Long emailDlId) method of DLServiceImpl class");
	return  dlDao.getEmailDlByEmailDlId(emailDlId).getIsActive();
}

@Override
public List<EmailDlBean> getActiveDlBeansByProjectIdAndLocationId(long projectId, long locationId) {
	// TODO Auto-generated method stub
	 List<EmailDl> emailDlEntityList= dlDao.getActiveDlBeansByProjectIdAndLocationId(projectId,locationId);
	 List<EmailDlBean> emailDlBeansList=new ArrayList<>();
	 for(EmailDl emailDl: emailDlEntityList)
	 {
		 emailDlBeansList.add(EmailDlConverter.convertEntityToBean(emailDl));
	 }
	 return emailDlBeansList;
}

@Override
public EmailDlBean getDLBeanForDlName(String dlName) {
	// TODO Auto-generated method stub
	return EmailDlConverter.convertEntityToBean(dlDao.getDLBeanForDlName(dlName));
}

}
