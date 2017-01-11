package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapient.statestreetscreeningapplication.model.dao.RateDao;
import com.sapient.statestreetscreeningapplication.model.entity.Rate;
import com.sapient.statestreetscreeningapplication.model.entity.RateLog;
import com.sapient.statestreetscreeningapplication.model.service.CategoryService;
import com.sapient.statestreetscreeningapplication.model.service.LocationService;
import com.sapient.statestreetscreeningapplication.model.service.PositionService;
import com.sapient.statestreetscreeningapplication.model.service.RateService;
import com.sapient.statestreetscreeningapplication.ui.bean.CategoryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.LocationNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PositionBean;
import com.sapient.statestreetscreeningapplication.ui.bean.RateBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.CategoryConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.LocationNewConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonNewConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.PositionConverter;
import com.sapient.statestreetscreeningapplication.utils.converter.RateConverter;

@Component
public class RateServiceImpl implements RateService{
	
	@Autowired
	RateDao rateDao;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	PositionService positionService;
	@Autowired
	CategoryService categoryService;

	@Override
	public List<RateBean> viewRate() {
		CustomLoggerUtils.INSTANCE.log.info("inside viewRate() of RateService class");
		return RateConverter.rateEntityListToBeanList(rateDao.viewRate());
	}
	public List<RateBean> viewAllUsedRate() {
		CustomLoggerUtils.INSTANCE.log.info("inside viewAllUsedRate() of RateService class");
		return RateConverter.rateEntityListToBeanList(rateDao.viewAllUsedRate());
	}

	@Override
	public void editRate(int rateId, int rate, PersonNewBean currentUser) {
		CustomLoggerUtils.INSTANCE.log.info("inside editRate(int rateId, int rate, PersonNewBean currentUser) of RateService class");
		boolean changeInActiveStatus=false;
		RateLog rateLog=new RateLog();
		rateDao.enterChangesInRateLog(rate,PersonNewConverter.personBeanToEntity(currentUser),rateId,changeInActiveStatus,rateLog);
		rateDao.editRate( rateId,  rate);
		
	}

	@Override
	public void saveRate(RateBean rateBean, PersonNewBean currentUser) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveRate(RateBean rateBean) of RateService class");
		RateLog rateLog=new RateLog();
		rateDao.saveRate(RateConverter.rateBeanToEntity(rateBean),PersonNewConverter.personBeanToEntity(currentUser),rateLog);
		
	}

	@Override
	public void editRateAdmin(int rateId, int rate, PersonNewBean currentUser,
			String rateIsActiveOrNot) {
		CustomLoggerUtils.INSTANCE.log.info("inside editRateAdmin(int rateId, int rate, PersonNewBean currentUser,String rateIsActiveOrNot) of RateService class");
		rateDao.editRateAdmin( rateId,  rate,  PersonNewConverter.personBeanToEntity(currentUser),rateIsActiveOrNot);
		
	}
	@Override
	public RateBean findRateByRateId(int rateId) {
		CustomLoggerUtils.INSTANCE.log.info("inside findRateByRateId(int rateId) of RateService class");
		
		return RateConverter.rateEntityToBean(rateDao.findRateByRateId( rateId));
	}
	@Override
	public Boolean getIsRateActive(int rateId) {
		CustomLoggerUtils.INSTANCE.log.info("inside findRateByRateId(int rateId) of RateService class");
		
		return (rateDao.findRateByRateId( rateId)).isUsed();
	}
	@Override
	public boolean findIfDuplicateRate(String locationName, String positionName,String domainName, String rateCategory,String rateType) {
		LocationNewBean location=locationService.getLocationByName(locationName);
		PositionBean position=positionService.getPositionByName(positionName);
		CategoryBean category=categoryService.getCategoryByName(domainName);
		Rate rate=rateDao.findRate(LocationNewConverter.locationBeanToEntity(location),PositionConverter.convertPositionBeanToEntity(position),CategoryConverter.convertCategoryBeanToCategoryEntity(category),rateCategory,rateType);
		
		if(rate!=null){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public RateBean findRate(String locationName, String positionName,
			String domainName, String rateCategory, String rateType) {
		LocationNewBean location=locationService.getLocationByName(locationName);
		PositionBean position=positionService.getPositionByName(positionName);
		if(position==null){
			String positionName1 = positionName.split(",")[0].split("=")[1];
			position=positionService.getPositionByName(positionName1);

		}
		CategoryBean category=categoryService.getCategoryByName(domainName);
		Rate rate=rateDao.findRate(LocationNewConverter.locationBeanToEntity(location),PositionConverter.convertPositionBeanToEntity(position),CategoryConverter.convertCategoryBeanToCategoryEntity(category),rateCategory,rateType);
		
		if(rate==null){
			return null;
		}else{
			return RateConverter.rateEntityToBean(rate);
		}
		
	}
	

}
