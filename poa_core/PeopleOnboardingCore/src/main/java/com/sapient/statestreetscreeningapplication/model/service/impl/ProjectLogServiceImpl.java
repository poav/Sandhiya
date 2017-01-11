package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.dao.ProjectDao;
import com.sapient.statestreetscreeningapplication.model.service.ProjectLogService;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectLogBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectLogServiceImpl.
 */
@Service
public class ProjectLogServiceImpl implements ProjectLogService{

	
	/** The project log dao. */
	/*@Autowired
	private ProjectLogDao projectLogDao;*/
	
	/** The project dao. */
	@Autowired
	private ProjectDao projectDao;
	
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

	@Override
	public Boolean saveProjectLog(String newProjectName, String newIQNName,
			String newAtrackName, Date ssStartDate, Date iqnEndDate,
			int oracleID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateProjectLog(Date ssStartDate, Date iqnEndDate,
			int oracleID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectLogBean> fetchProjectLog(int oracleID) {
		// TODO Auto-generated method stub
		return null;
	}

}
