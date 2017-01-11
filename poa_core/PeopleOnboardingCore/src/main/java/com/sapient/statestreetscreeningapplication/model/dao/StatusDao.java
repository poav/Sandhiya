package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sapient.statestreetscreeningapplication.model.entity.Status;

// TODO: Auto-generated Javadoc
/**
 * The Interface StatusDao.
 */
@Component
public interface StatusDao {

	/**
	 * Gets the all status.
	 *
	 * @return the all status
	 */
	List<Status> getAllStatus();

	/**
	 * Gets the status by name.
	 *
	 * @param trim the trim
	 * @return the status by name
	 */
	Status getStatusByName(String trim);

	/**
	 * Save status details.
	 *
	 * @param status the status
	 */
	void saveStatusDetails(Status status);

	/**
	 * Save status batch.
	 *
	 * @param list the list
	 */
	void saveStatusBatch(List<Status> list);

	/**
	 * Gets the all used status.
	 *
	 * @return the all used status
	 */
	List<Status> getAllUsedStatus();

	/**
	 * Change state.
	 *
	 * @param statusId the status id
	 * @param state the state
	 * @return true, if successful
	 */
	boolean changeState(long statusId, int state);

	/**
	 * Gets the status by names.
	 *
	 * @param statusName the status name
	 * @param resultName the result name
	 * @return the status by names
	 */
	Status getStatusByNames(String statusName, String resultName);

	/**
	 * Gets the status bean on result name.
	 *
	 * @param statusName the status name
	 * @return the status bean on result name
	 */
	Status getStatusBeanOnResultName(String statusName);

	Status getStatusBeanOnStatusAndResultName(String statysName,
			String resultName);

}
