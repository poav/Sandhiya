package com.sapient.statestreetscreeningapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.statestreetscreeningapplication.model.service.CategoryService;
import com.sapient.statestreetscreeningapplication.model.service.LocationService;
import com.sapient.statestreetscreeningapplication.model.service.PositionService;
import com.sapient.statestreetscreeningapplication.model.service.RateService;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.RateBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

@RestController
public class RateController {
	
	@Autowired
	private RateService rateService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private CategoryService categoryService;
	
	@CrossOrigin
	@RequestMapping("/displayAllRates")
	public List<RateBean> viewRates()
	{
		return rateService.viewAllUsedRate();
	}
	
	@CrossOrigin
	@RequestMapping("/displayAllUsedRates")
	public List<RateBean> viewUsedRates()
	{
		return rateService.viewAllUsedRate();
	}
	
	@CrossOrigin
	@RequestMapping("/getRateBean")
	public RateBean getRateBean()
	{
		return new RateBean();
	}
	
	@CrossOrigin
	@RequestMapping("/findRate")
	public RateBean findRate(@RequestParam("locationName") String locationName,
			@RequestParam("positionName") String positionName,
			@RequestParam("domainName") String domainName,
			@RequestParam("rateCategory") String rateCategory,
			@RequestParam("rateType") String rateType) 
	{
		return rateService.findRate(locationName, positionName, domainName, rateCategory, rateType);
	}

	
	
	@CrossOrigin
	@RequestMapping(value = "/editRate", method = RequestMethod.POST)
	public void editRates(@RequestBody RateBean rateBean)
	{
		PersonNewBean personBean = new PersonNewBean();
		personBean.setPersonName("suraj");
		personBean.setPersonOracleId(115446);
		String rateIsActiveOrNot= rateBean.getIsUsed() ? "true":"false"; 
		rateService.editRateAdmin(rateBean.getRateId(), rateBean.getRate(), personBean,rateIsActiveOrNot);
		
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/saveRate", method = RequestMethod.POST)
	public Boolean saveRate(@RequestBody RateBean rateBean)
	{
		PersonNewBean personBean = new PersonNewBean();
		personBean.setPersonName("suraj");
		personBean.setPersonOracleId(115446);
		
		if(!rateService.findIfDuplicateRate(rateBean.getLocation().getCity(), rateBean.getPosition().getPositionName(), rateBean.getPosition().getDomain(), rateBean.getCategory().getCategoryName(), rateBean.getRateType())) 
		{
			rateBean.setPosition(positionService.getPositionByName(rateBean.getPosition().getPositionName()));
			rateBean.setCategory(categoryService.getCategoryByName(rateBean.getCategory().getCategoryName()));
			rateBean.setLocation(locationService.getLocationByName(rateBean.getLocation().getCity()));
			rateBean.setUsed(true);
			rateService.saveRate(rateBean, personBean);
			return true;
		}
		return false;

	}
	
	@CrossOrigin
	@RequestMapping(value="/getRate",method = RequestMethod.GET)
	public RateBean returnRate(@RequestParam("location") String locationName,
			@RequestParam("position") String positionName,
			@RequestParam("domain") String domainName,
			@RequestParam("rateCategory") String  rateCategory,
			@RequestParam("rateType") String rateType
			){

		System.out.println(locationName+" "+positionName+" "+domainName+" "+rateCategory+" "+rateType);
		RateBean rateBean=rateService.findRate(locationName, positionName, domainName, rateCategory, rateType);
		return rateBean;		
	}
	@CrossOrigin
	@RequestMapping(value = "/checkIfRateIsDuplicateOrNot", method = RequestMethod.POST)
	public Boolean findIfDuplicateRate(@RequestBody RateBean rateBean){

		CustomLoggerUtils.INSTANCE.log.info("inside findIfDuplicateRate() method and in RateAction");
		return rateService.findIfDuplicateRate(rateBean.getLocation().getCity(), rateBean.getPosition().getPositionName(), rateBean.getCategory().getCategoryName(),rateBean.getRateCategory() , rateBean.getRateType());	

	}

	


	
}
