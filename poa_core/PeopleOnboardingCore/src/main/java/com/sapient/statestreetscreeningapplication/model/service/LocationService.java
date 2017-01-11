package com.sapient.statestreetscreeningapplication.model.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.ui.bean.LocationBean;
import com.sapient.statestreetscreeningapplication.ui.bean.LocationNewBean;

// TODO: Auto-generated Javadoc

/**
 * The Interface LocationService.
 */
@Service
public interface LocationService {

	/**
	 * Gets the all locations.
	 * 
	 * @return the all locations
	 */
	List<String> getAllLocations();

	/**
	 * Gets the all location beans.
	 * 
	 * @return the all location beans
	 */
	List<LocationBean> getAllLocationBeans();

	/**
	 * Save location details.
	 * 
	 * @param location
	 *            the location
	 * @return true, if successful
	 */
	boolean saveLocationDetails(LocationNewBean location);

	/**
	 * Save location batch details.
	 * 
	 * @param locationBatch
	 *            the location batch
	 */
	void saveLocationBatchDetails(File locationBatch);// to do test case

	/**
	 * Gets the all used locations.
	 * 
	 * @return the all used locations
	 */
	List<String> getAllUsedLocations();

	/**
	 * Change state.
	 * 
	 * @param locationId
	 *            the location id
	 * @param state
	 *            the state
	 * @return true, if successful
	 */
	boolean changeState(long locationId, int state);

	/**
	 * Gets the location by name.
	 * 
	 * @param locationName
	 *            the location name
	 * @return the location by name
	 */
	LocationNewBean getLocationByName(String locationName);

	/**
	 * Gets the all location new beans.
	 * 
	 * @return the all location new beans
	 */
	List<LocationNewBean> getAllLocationNewBeans();

	/**
	 * Gets the all used location names.
	 * 
	 * @return the all used location names
	 */
	List<String> getAllUsedLocationNames();

	/**
	 * Gets the new location by name.
	 * 
	 * @param locationName
	 *            the location name
	 * @return the new location by name
	 */
	LocationNewBean getNewLocationByName(String locationName);
}
