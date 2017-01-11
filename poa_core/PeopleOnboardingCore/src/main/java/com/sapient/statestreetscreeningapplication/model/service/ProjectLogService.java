package com.sapient.statestreetscreeningapplication.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.ui.bean.ProjectLogBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface ProjectLogService.
 */
@Service
public interface ProjectLogService {
	
	/**
	 * Save project log.
	 *
	 * @param newProjectName the new project name
	 * @param newIQNName the new iqn name
	 * @param newAtrackName the new atrack name
	 * @param ssStartDate the ss start date
	 * @param iqnEndDate the iqn end date
	 * @param oracleID the oracle id
	 * @return the boolean
	 */
	public Boolean saveProjectLog(String newProjectName, String newIQNName,
			String newAtrackName,Date ssStartDate,Date iqnEndDate, int oracleID);

	/**
	 * Update project log.
	 *
	 * @param ssStartDate the ss start date
	 * @param iqnEndDate the iqn end date
	 * @param oracleID the oracle id
	 * @return the boolean
	 */
	public Boolean updateProjectLog(Date ssStartDate, Date iqnEndDate, int oracleID);
	
	/**
	 * Fetch project log.
	 *
	 * @param oracleID the oracle id
	 * @return the list
	 */
	public List<ProjectLogBean> fetchProjectLog(int oracleID);
}
