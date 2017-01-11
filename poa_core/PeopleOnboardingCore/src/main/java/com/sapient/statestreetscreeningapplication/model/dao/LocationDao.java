package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.List;

import org.springframework.stereotype.Component;


import com.sapient.statestreetscreeningapplication.model.entity.LocationNew;


// TODO: Auto-generated Javadoc
/**
 * The Interface LocationDao.
 */
@Component
public interface LocationDao {



	/**
	 * Gets the location by name.
	 *
	 * @param locationName the location name
	 * @return the location by name
	 */
	LocationNew getLocationByName(String locationName);

	/**
	 * Save location details.
	 *
	 * @param locationNew the location new
	 */
	void saveLocationDetails(LocationNew locationNew);

	/**
	 * Save location batch.
	 *
	 * @param locationList the location list
	 */
	void saveLocationBatch(List<LocationNew> locationList);



	/**
	 * Change state.
	 *
	 * @param locationId the location id
	 * @param state the state
	 * @return true, if successful
	 */
	boolean changeState(long locationId, int state);


	/**
	 * Gets the all new locations.
	 *
	 * @return the all new locations
	 */
	List<LocationNew> getAllNewLocations();

	/**
	 * Gets the new location by name.
	 *
	 * @param string the string
	 * @return the new location by name
	 */
	LocationNew getNewLocationByName(String string);

	/**
	 * Gets the all used location names.
	 *
	 * @return the all used location names
	 */
	List<String> getAllUsedLocationNames();

	/**
	 * Gets the all location by name.
	 *
	 * @param name the name
	 * @return the all location by name
	 */
	public List<LocationNew> getAllLocationByName(String name);

}
