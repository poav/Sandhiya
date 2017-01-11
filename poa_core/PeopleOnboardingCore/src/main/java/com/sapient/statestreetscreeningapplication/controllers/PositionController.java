package com.sapient.statestreetscreeningapplication.controllers;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.statestreetscreeningapplication.model.service.PositionService;
import com.sapient.statestreetscreeningapplication.ui.bean.PositionBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PositionNewBean;

@RestController
public class PositionController {
	
	@Autowired
	PositionService positionService;
	
	@CrossOrigin
	@RequestMapping("/retrieveAllPositions")
	private List<String> displayAllPositions()
	{
		return positionService.getAllUsedPositionsByName();
	}
	
	@CrossOrigin
	@RequestMapping("/getPositionBean")
	public PositionNewBean getPositionBean()
	{
		return new PositionNewBean();
	}
	
	@CrossOrigin
	@RequestMapping("/getDesignationNamesList")
	public List<String> getDesignationNamesList()
	{
		return positionService.getDesignationNamesList();
	}
	
	
	
	@CrossOrigin
	@RequestMapping("/getDesignationLevelsNamesList")
	public List<String> getDesignationLevelsNamesList()
	{
		return positionService.getDesignationLevelNamesList();
	}
	
	
	@CrossOrigin
	@RequestMapping("/retrieveAllPositionsBeans")
	public List<PositionBean> displayAllPositionsBeans()
	{
		return positionService.getAllUsedPositions();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/changePositionState", method = RequestMethod.POST)
	public void changeState(@RequestBody PositionBean positionBean)
	{
		
		positionService.changeState(positionBean.getPositionId(),positionBean.getIsUsed());
	}
	
	@CrossOrigin
	@RequestMapping(value = "/batchUploadPosition", method = RequestMethod.POST)
	public void executeBatchFile(@RequestBody File positionBatch)
	{
		positionService.savePositionBatchDetails(positionBatch);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/addTitle", method = RequestMethod.POST)
	public void addTitle(@RequestBody PositionBean positionBean)
	{
		positionService.savePositionDetails(positionBean);
	}
	

	@CrossOrigin
	@RequestMapping(value = "/getPositionsByDomain", method = RequestMethod.GET)
	public List<PositionBean>  getPositionsByDomain(@RequestParam("domainName") String domainName ){
		
			System.out.println("Get Position By Domain: "+domainName);
			return positionService.getAllUsedPositionByDomain(domainName);
	}

}
