package com.sapient.statestreetscreeningapplication.model.service;

import java.io.File;
import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.EmailDl;
import com.sapient.statestreetscreeningapplication.ui.bean.DLBean;
import com.sapient.statestreetscreeningapplication.ui.bean.EmailDlBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface DLService.
 */
public interface DLService {
	
	/**
	 * Gets the all d ls.
	 *
	 * @return the all d ls
	 */
	public List<String> getAllDLs();

	
	
	/**
	 * Gets the DL by project id and location id.
	 *
	 * @param projectId the project id
	 * @param locationId the location id
	 * @return the DL by project id and location id
	 */
	public List<String> getDLByProjectIdAndLocationId(long projectId,long locationId);
	
	public int getEmailDlIsActiveByEmailDlId(Long emailDlId);

	/**
	 * Gets the complete dl list.
	 *
	 * @return the complete dl list
	 */
	List<EmailDlBean> getCompleteDLList();


	/**
	 * Save dl.
	 *
	 * @param locationName the location name
	 * @param projectName the project name
	 * @param dlName the dl name
	 * @param teamName the team name
	 */
	void saveDl(String locationName, String projectName, String dlName,String teamName);

	/**
	 * Save dl batch details.
	 *
	 * @param dlBatch the dl batch
	 */
	void saveDlBatchDetails(File dlBatch);

	/**
	 * Gets the dl by dl name and project id and location id.
	 *
	 * @param dlName the dl name
	 * @param projectId the project id
	 * @param locationId the location id
	 * @return the dl by dl name and project id and location id
	 */
	public EmailDlBean getDlByDlNameAndProjectIdAndLocationId(String dlName,
			long projectId, long locationId);

	/**
	 * Gets the all email dl names.
	 *
	 * @return the all email dl names
	 */
	List<String> getAllEmailDlNames();

	/**
	 * Edits the dl.
	 *
	 * @param dlBean the dl bean
	 * @param locationName the location name
	 * @param projectName the project name
	 * @param dlName the dl name
	 * @return true, if successful
	 */
	boolean editDl(EmailDlBean dlBean, String locationName, String projectName,
			String dlName);



	public List<String> getActiveDlByProjectIdAndLocationId(long projectId,
			long locationId);



	public List<EmailDlBean> getActiveDlBeansByProjectIdAndLocationId(long projectId, long locationId);



	public EmailDlBean getDLBeanForDlName(String dlName);

}
