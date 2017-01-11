package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.LocationDao;
import com.sapient.statestreetscreeningapplication.model.entity.LocationNew;
import com.sapient.statestreetscreeningapplication.model.service.LocationService;
import com.sapient.statestreetscreeningapplication.ui.bean.LocationBean;
import com.sapient.statestreetscreeningapplication.ui.bean.LocationNewBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.LocationNewConverter;


// TODO: Auto-generated Javadoc
/**
 * The Class LocationServiceImpl.
 */
@Service
public class LocationServiceImpl implements LocationService {
	
	/** The location dao. */
	@Autowired
	LocationDao locationDao;

	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.LocationService#saveLocationDetails(com.sapient.statestreetscreeningapplication.ui.bean.LocationNewBean)
	 */
	@Override
	public boolean saveLocationDetails(LocationNewBean location) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveLocationDetails(LocationNewBean location) method of LocationServiceImpl class");
		CustomLoggerUtils.INSTANCE.log.info("Inside saveLocationDetails method");
		if(location!=null){
			 locationDao.saveLocationDetails(LocationNewConverter.locationBeanToEntity(location));
			 return true;
		}
		CustomLoggerUtils.INSTANCE.log.info("Null location object was passed");
		return false;
	}
	

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.LocationService#saveLocationBatchDetails(java.io.File)
	 */
	@Override
	@Transactional
	public void saveLocationBatchDetails(File locationBatch) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveLocationBatchDetails(File locationBatch) method of LocationServiceImpl class");

		// for csv file
		String csvFile = locationBatch.getPath();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		LocationNew locationExisting;
		List<LocationNew> locationList = new ArrayList<LocationNew>();

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] location = line.split(cvsSplitBy);
				locationExisting = locationDao.getNewLocationByName(location[0]);
				if (locationExisting == null) {
					LocationNew newLocation = new LocationNew();
					newLocation.setCity(location[0]);
					newLocation.setState(location[1]);
					newLocation.setCountry(location[2]);
					locationList.add(newLocation);
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
		locationDao.saveLocationBatch(locationList);

	}

	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.LocationService#changeState(long, int)
	 */
	@Override
	public boolean changeState(long locationId, int state) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeState(long locationId, int state) method of LocationServiceImpl class");
		if(locationId>0){
			 return locationDao.changeState(locationId, state);
		}
		return false;
		
	}
	
	/**
	 * Gets the location dao.
	 *
	 * @return the location dao
	 */
	public LocationDao getLocationDao() {
		return locationDao;
	}

	/**
	 * Sets the location dao.
	 *
	 * @param locationDao the new location dao
	 */
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.LocationService#getLocationByName(java.lang.String)
	 */
	@Override
	public LocationNewBean getLocationByName(String locationName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getLocationByName(String locationName) method of LocationServiceImpl class");
		LocationNewBean locationBean=LocationNewConverter.locationEntityToBean(locationDao.getLocationByName(locationName));
		return locationBean;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.LocationService#getAllLocationNewBeans()
	 */
	@Override
	public List<LocationNewBean> getAllLocationNewBeans() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllLocationNewBeans() method of LocationServiceImpl class");
		List<LocationNewBean> beanList = LocationNewConverter.locationEntityListToBeanList(locationDao.getAllNewLocations());
		/*List<LocationBean> beanList = LocationConverter
				.convertLocationEntityListToLocationBeanList(locationDao
						.getAllUsedLocations());*/
		return beanList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.LocationService#getAllUsedLocationNames()
	 */
	@Override
	public List<String> getAllUsedLocationNames() {
		CustomLoggerUtils.INSTANCE.log.info("inside  getAllUsedLocationNames() method of LocationServiceImpl class");
		return locationDao.getAllUsedLocationNames();
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.LocationService#getNewLocationByName(java.lang.String)
	 */
	@Override
	public LocationNewBean getNewLocationByName(String locationName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getNewLocationByName(String locationName) method of LocationServiceImpl class");
		return LocationNewConverter.locationEntityToBean(locationDao.getNewLocationByName(locationName));
	}


	@Override
	public List<String> getAllLocations() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<LocationBean> getAllLocationBeans() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getAllUsedLocations() {
		// TODO Auto-generated method stub
		return null;
	}

}
