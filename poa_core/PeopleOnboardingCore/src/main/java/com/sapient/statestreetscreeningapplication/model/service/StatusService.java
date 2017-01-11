package com.sapient.statestreetscreeningapplication.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.ui.bean.StatusBean;
// TODO: Auto-generated Javadoc

/**
 * The Interface StatusService.
 */
@Service
public interface StatusService {

	/**
	 * Gets the all status.
	 *
	 * @return the all status
	 */
	List<String> getAllStatus();

	/**
	 * Gets the all status beans.
	 *
	 * @return the all status beans
	 */
	List<StatusBean> getAllStatusBeans();

	/**
	 * Save status details.
	 *
	 * @param status the status
	 * @return true, if successful
	 */
	boolean saveStatusDetails(StatusBean status);

	/**
	 * Save status batch details.
	 *
	 * @param statusBatch the status batch
	 */
	void saveStatusBatchDetails(File statusBatch);

	/**
	 * Gets the all used status.
	 *
	 * @return the all used status
	 */
	List<String> getAllUsedStatus();

	/**
	 * Change state.
	 *
	 * @param statusId the status id
	 * @param state the state
	 * @return true, if successful
	 */
	boolean changeState(long statusId, int state);

	/**
	 * Gets the status map.
	 *
	 * @return the status map
	 */
	HashMap<String, List<String>> getStatusMap();
	
	/**
	 * Gets the status bean on result name.
	 *
	 * @param statusName the status name
	 * @return the status bean on result name
	 */
	StatusBean getStatusBeanOnResultName(String statusName);

	/**
	 * Gets the check list results.
	 *
	 * @return the check list results
	 */
	List<String> getCheckListResults();

	StatusBean getStatusBeanOnStatusAndResultName(String statusName,
			String resultName);

}
