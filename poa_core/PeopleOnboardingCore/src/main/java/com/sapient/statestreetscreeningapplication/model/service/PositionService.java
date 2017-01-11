package com.sapient.statestreetscreeningapplication.model.service;

import java.io.File;
import java.util.List;

import com.sapient.statestreetscreeningapplication.ui.bean.PositionBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PositionNewBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface PositionService.
 */
public interface PositionService {
	
	/**
	 * Gets the position by name.
	 *
	 * @param positionName the position name
	 * @return the position by name
	 */
	public PositionBean getPositionByName(String positionName);
	
	/**
	 * Gets the all positions.
	 *
	 * @return the all positions
	 */
	public List<PositionBean> getAllPositions();
	
	/**
	 * Save position details.
	 *
	 * @param position the position
	 * @return true, if successful
	 */
	public boolean savePositionDetails(PositionBean position);
	
	/**
	 * Save position batch details.
	 *
	 * @param positionBatch the position batch
	 */
	public void savePositionBatchDetails(File positionBatch);
	
	/**
	 * Change state.
	 *
	 * @param positionId the position id
	 * @param state the state
	 * @return true, if successful
	 */
	public boolean changeState(long positionId, int state);
	
	/**
	 * Gets the all used positions.
	 *
	 * @return the all used positions
	 */
	public List<PositionBean> getAllUsedPositions();
	
	/**
	 * Gets the all used positions.
	 *
	 * @return the all used positions names
	 */
	public List<String> getAllUsedPositionsByName();

	public List<String> getDesignationNamesList();

	/**
	 * Gets the all used positions of particular domain.
	 *
	 * @return the all used positions names of particular domain
	 */
	public List<PositionBean> getAllUsedPositionByDomain(String domainName);
	public List<String> getDesignationLevelNamesList();
}
