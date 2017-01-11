package com.sapient.statestreetscreeningapplication.controllers;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.statestreetscreeningapplication.model.service.StatusService;
import com.sapient.statestreetscreeningapplication.ui.bean.StatusBean;

@RestController
public class StatusController {
	
	@Autowired
	StatusService statusService;
	
	@CrossOrigin
	@RequestMapping("/displayAllStatusBeans")
	public List<StatusBean> displayAllStatusBeans()
	{
		return statusService.getAllStatusBeans();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/changeState", method = RequestMethod.POST)
	public void changeStatusState(@RequestBody Long statusId, int state)
	{
		statusService.changeState(statusId,state);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/addStatus", method = RequestMethod.POST)
	public void addStatus(@RequestBody StatusBean statusBean)
	{
		statusService.saveStatusDetails(statusBean);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/addStatusBatch", method = RequestMethod.POST)
	public void addStatusBatch(@RequestBody File statusBatch)
	{
		statusService.saveStatusBatchDetails(statusBatch);
	}

}
