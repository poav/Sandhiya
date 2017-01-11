package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.dao.StatusDao;
import com.sapient.statestreetscreeningapplication.model.entity.Status;
import com.sapient.statestreetscreeningapplication.model.service.StatusService;
import com.sapient.statestreetscreeningapplication.ui.bean.StatusBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.StatusConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusServiceImpl.
 */
@Service
public class StatusServiceImpl implements StatusService {

	/** The status dao. */
	@Autowired
	StatusDao statusDao;

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.StatusService#getAllStatus()
	 */
	@Override
	public List<String> getAllStatus() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllStatus() method of StatusServiceImpl class");
		List<Status> statusEntityList = statusDao.getAllStatus();
		List<String> statusStringList = new ArrayList<String>();
		for (Status entity : statusEntityList) {
			statusStringList.add(entity.getStatusName());
		}
		return statusStringList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.StatusService#getAllStatusBeans()
	 */
	@Override
	public List<StatusBean> getAllStatusBeans() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllStatusBeans() method of StatusServiceImpl class");
		List<StatusBean> beanList = StatusConverter
				.convertStatusEntityListToStatusBeanList(statusDao
						.getAllStatus());
		return beanList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.StatusService#saveStatusDetails(com.sapient.statestreetscreeningapplication.ui.bean.StatusBean)
	 */
	@Override
	public boolean saveStatusDetails(StatusBean status) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveStatusDetails(StatusBean status) method of StatusServiceImpl class");
		if(status!=null){
		statusDao.saveStatusDetails(StatusConverter
				.convertStatusBeanToStatusEntity(status));
		return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.StatusService#saveStatusBatchDetails(java.io.File)
	 */
	@Override
	public void saveStatusBatchDetails(File statusBatch) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveStatusBatchDetails(File statusBatch) method of StatusServiceImpl class");
		// for csv file
		String csvFile = statusBatch.getPath();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		Status statusExisting;
		List<Status> statusList = new ArrayList<Status>();

		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] status = line.split(cvsSplitBy);
				statusExisting = statusDao.getStatusByNames(status[0],
						status[1]);
				if (statusExisting == null) {
					Status newStatus = new Status();
					newStatus.setStatusName(status[0]);
					newStatus.setResultName(status[1]);
					statusList.add(newStatus);
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
		statusDao.saveStatusBatch(statusList);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.StatusService#getAllUsedStatus()
	 */
	@Override
	public List<String> getAllUsedStatus() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllUsedStatus() method of StatusServiceImpl class");
		List<Status> statusEntityList = statusDao.getAllUsedStatus();
		List<String> statusStringList = new ArrayList<String>();
		
		for (Status entity : statusEntityList) {
			if (entity != null
					&& statusStringList.contains(entity.getStatusName()) == false) {
				statusStringList.add(entity.getStatusName());
			}
		}
		return statusStringList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.StatusService#changeState(long, int)
	 */
	@Override
	public boolean changeState(long statusId, int state) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeState(long statusId, int state) method of StatusServiceImpl class");
		
		if (statusId>0) {
			return statusDao.changeState(statusId, state);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.StatusService#getStatusMap()
	 */
	@Override
	public HashMap<String, List<String>> getStatusMap() {
		CustomLoggerUtils.INSTANCE.log.info("inside getStatusMap() method of StatusServiceImpl class");
		HashMap<String, List<String>> statusMap = new HashMap<String, List<String>>();
		List<Status> statusList = statusDao.getAllUsedStatus();
		List<String> valueList;
		for (Status status : statusList) {
			if (statusMap.containsKey(status.getStatusName())) {
				valueList = statusMap.get(status.getStatusName());
				valueList.add(status.getResultName());
				statusMap.put(status.getStatusName(), valueList);
			} else {
				valueList = new ArrayList<String>();
				valueList.add(status.getResultName());
				statusMap.put(status.getStatusName(), valueList);
			}
		}
		return statusMap;
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

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.StatusService#getStatusBeanOnResultName(java.lang.String)
	 */
	@Override
	public StatusBean getStatusBeanOnResultName(String statusName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getStatusBeanOnResultName(String statusName) method of StatusServiceImpl class");
		return StatusConverter.convertStatusEntityToStatusBean(statusDao.getStatusBeanOnResultName(statusName));
	}
	
	@Override
	public StatusBean getStatusBeanOnStatusAndResultName(String statusName, String resultName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getStatusBeanOnStatusAndResultName(String statusName, String resultName) method of StatusServiceImpl class");
		return StatusConverter.convertStatusEntityToStatusBean(statusDao.getStatusBeanOnStatusAndResultName(statusName, resultName));
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.StatusService#getCheckListResults()
	 */
	@Override
	public List<String> getCheckListResults() {
		CustomLoggerUtils.INSTANCE.log.info("inside getCheckListResults() method of StatusServiceImpl class");
		List<Status> statusEntityList = statusDao.getAllStatus();
		List<String> resultStringList = new ArrayList<String>();
		for (Status entity : statusEntityList) {
			if(entity.getStatusName().equals("Onboarding in progress") || entity.getStatusName().equals("Onboarding complete") 
			||entity.getStatusName().equals("Offboarding initiated"))				
				resultStringList.add(entity.getResultName());
		}
		return resultStringList;
	}
}
