package com.sapient.statestreetscreeningapplication.utils.converter;

import java.util.ArrayList;
import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.LocationNew;
import com.sapient.statestreetscreeningapplication.ui.bean.LocationNewBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationNewConverter.
 */
public class LocationNewConverter {

	/**
	 * Location entity to bean.
	 *
	 * @param location the location
	 * @return the location new bean
	 */
	public static LocationNewBean locationEntityToBean(LocationNew location) {
		CustomLoggerUtils.INSTANCE.log
				.info("Inside locationEntityToBean method in LocationNewConverter");
		LocationNewBean locationNewBean = new LocationNewBean();
		if (location.getCity() != null)
			locationNewBean.setCity(location.getCity());
		locationNewBean.setCountry(location.getCountry());
		locationNewBean.setIsActive(location.getIsActive());
		locationNewBean.setLocationId(location.getLocationId());
		locationNewBean.setState(location.getState());
		return locationNewBean;
	}

	/**
	 * Location bean to entity.
	 *
	 * @param location the location
	 * @return the location new
	 */
	public static LocationNew locationBeanToEntity(LocationNewBean location) {
		CustomLoggerUtils.INSTANCE.log
				.info("Inside locationBeanToEntity method in LocationNewConverter");
		LocationNew locationNew = new LocationNew();
		locationNew.setCity(location.getCity());
		locationNew.setCountry(location.getCountry());
		locationNew.setLocationId(location.getLocationId());
		locationNew.setState(location.getState());
		locationNew.setIsActive(location.getIsActive());
		return locationNew;
	}

	/**
	 * Location entity list to bean list.
	 *
	 * @param allNewLocations the all new locations
	 * @return the list
	 */
	public static List<LocationNewBean> locationEntityListToBeanList(
			List<LocationNew> allNewLocations) {
		CustomLoggerUtils.INSTANCE.log
				.info("Inside locationEntityListToBeanList method in LocationNewConverter");
		List<LocationNewBean> locationBeanList = new ArrayList<LocationNewBean>();

		for (LocationNew location : allNewLocations) {
			locationBeanList.add(locationEntityToBean(location));
		}
		return locationBeanList;
	}

}
