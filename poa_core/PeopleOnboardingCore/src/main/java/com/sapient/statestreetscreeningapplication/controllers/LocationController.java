package com.sapient.statestreetscreeningapplication.controllers;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.statestreetscreeningapplication.model.service.LocationService;
import com.sapient.statestreetscreeningapplication.ui.bean.LocationBean;
import com.sapient.statestreetscreeningapplication.ui.bean.LocationNewBean;

@RestController
public class LocationController {
	
	@Autowired
	LocationService locationService;
	
	@CrossOrigin
	@RequestMapping("/retrieveAllLocations")
	public List<String> getAllLocations()
	{
		return locationService.getAllUsedLocationNames();
	}
	
	@CrossOrigin
	@RequestMapping("/getLocationBean")
	public LocationBean getLocationBean()
	{
		return new LocationBean();
	}

	
	@CrossOrigin
	@RequestMapping("/retrieveLocations")
	public List<LocationNewBean> getLocations()
	{
		return locationService.getAllLocationNewBeans();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/changeLocationState", method = RequestMethod.POST)
	public void changeState(@RequestBody LocationNewBean locationNewBean)
	{
		locationService.changeState(locationNewBean.getLocationId(),locationNewBean.getIsActive());
	}
	
	@CrossOrigin
	@RequestMapping(value = "/addLocation", method = RequestMethod.POST)
	public void addLocation(@RequestBody LocationNewBean locationBean)
	{
		locationService.saveLocationDetails(locationBean);

	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/addLocationBatch", method = RequestMethod.POST)
	public void executeBatchFile(@RequestBody File locationBatch)
	{
		locationService.saveLocationBatchDetails(locationBatch);
	}
	
}
