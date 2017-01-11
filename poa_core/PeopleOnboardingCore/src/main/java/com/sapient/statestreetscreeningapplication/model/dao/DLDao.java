package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.EmailDl;
import com.sapient.statestreetscreeningapplication.model.entity.LocationNew;
import com.sapient.statestreetscreeningapplication.model.entity.ProjectNew;
import com.sapient.statestreetscreeningapplication.ui.bean.EmailDlBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface DLDao.
 */
public interface DLDao {

	/**
	 * Gets the all d ls.
	 *
	 * @return the all d ls
	 */
	List<String> getAllDLs();

	/**
	 * Gets the DL by name.
	 *
	 * @param dlName the dl name
	 * @return the DL by name
	 */
	
	/**
	 * Gets the dl by project id and location id.
	 *
	 * @param projectId the project id
	 * @param locationId the location id
	 * @return the dl by project id and location id
	 */
	List<String> getDlByProjectIdAndLocationId(long projectId,long locationId);

	/**
	 * Gets the complete dl list.
	 *
	 * @return the complete dl list
	 */
	List<EmailDl> getCompleteDLList();



	/**
	 * Save dl batch.
	 *
	 * @param list the list
	 */
	void saveDlBatch(List<EmailDl> list);

	/**
	 * Gets the DL by dl name and project id and location id.
	 *
	 * @param dlName the dl name
	 * @param projectId the project id
	 * @param locationId the location id
	 * @return the DL by dl name and project id and location id
	 */
	EmailDl getDLByDlNameAndProjectIdAndLocationId(String dlName, long projectId,
			long locationId);

	/**
	 * Gets the all dl names.
	 *
	 * @return the all dl names
	 */
	List<String> getAllDlNames();

	/**
	 * Save dl.
	 *
	 * @param location the location
	 * @param project the project
	 * @param dlName the dl name
	 * @return true, if successful
	 */
	boolean saveDl(LocationNew location, ProjectNew project, String dlName);

	/**
	 * Edits the dl.
	 *
	 * @param dlBean the dl bean
	 * @param locationNew the location new
	 * @param projectNew the project new
	 * @param dlName the dl name
	 * @return true, if successful
	 */
	boolean editDl(EmailDlBean dlBean, LocationNew locationNew, ProjectNew projectNew,
			String dlName);

	public EmailDl getEmailDlByEmailDlId(Long emailDlId);

	public List<String> getActiveDlByProjectIdAndLocationId(long projectId,
			long locationId);

	List<EmailDl> getActiveDlBeansByProjectIdAndLocationId(long projectId, long locationId);

	EmailDl getDLBeanForDlName(String dlName);
	
	
}
